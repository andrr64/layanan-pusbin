param(
    [string]$plantUMLFile = "dokumen_penting/ERD.puml"
)

# 1. BACA INPUT
if (Test-Path $plantUMLFile) {
    $plantUML = Get-Content $plantUMLFile -Raw
}
else {
    Write-Host "❌ File $plantUMLFile tidak ditemukan!" -ForegroundColor Red
    exit
}

# 2. SIAPKAN FOLDER OUTPUT
$basePath = "src/main/resources/db/changelog/sql"
if (-not (Test-Path $basePath)) { New-Item -ItemType Directory -Force -Path $basePath | Out-Null }

# 3. PARSING ENTITY & KOLOM
$tables = @()
$entityRegex = [regex] '(?s)entity\s+([\w_]+)\s*\{([^}]+)\}'
$entityMatches = $entityRegex.Matches($plantUML)

foreach ($match in $entityMatches) {
    $tableName = $match.Groups[1].Value
    $columnsContent = $match.Groups[2].Value
    $columns = @()
    $columnsContent.Split("`n") | ForEach-Object {
        $line = $_.Trim()
        if ($line -match '^(\*?)\s*([\w_]+)\s*:\s*([\w\d_]+)(?:\s*<<(\w+)>>)?') {
            $isPk = $matches[1] -eq '*' -or $matches[4] -eq "PK"
            $columns += @{
                Name       = $matches[2]
                Type       = $matches[3]
                PrimaryKey = $isPk
                FKTable    = $null
                FKColumn   = $null
            }
        }
    }
    $tables += @{ Name = $tableName; Columns = $columns }
}

# 4. PARSING RELATIONSHIP (FK)
$fkRegex = [regex] '([\w_]+)::([\w_]+)\s*\}o--\|\|\s*([\w_]+)::([\w_]+)'
$fkMatches = $fkRegex.Matches($plantUML)
foreach ($match in $fkMatches) {
    $baseTable = $match.Groups[1].Value
    $baseColumn = $match.Groups[2].Value
    $refTable = $match.Groups[3].Value
    $refColumn = $match.Groups[4].Value
    $tableToUpdate = $tables | Where-Object { $_.Name -eq $baseTable }
    if ($tableToUpdate) {
        $colToUpdate = $tableToUpdate.Columns | Where-Object { $_.Name -eq $baseColumn }
        if ($colToUpdate) {
            $colToUpdate.FKTable = $refTable
            $colToUpdate.FKColumn = $refColumn
        }
    }
}

# 5. TOPOLOGICAL SORT
function TopoSort($tables) {
    $visited = @{}
    $result = New-Object System.Collections.Generic.List[object]
    function Visit($t, $tables, $visited, $result) {
        if ($visited[$t.Name] -eq "temp") { throw "Circular dependency $($t.Name)" }
        if (-not $visited.ContainsKey($t.Name)) {
            $visited[$t.Name] = "temp"
            foreach ($c in $t.Columns) {
                if ($c.FKTable) {
                    $dep = $tables | Where-Object { $_.Name -eq $c.FKTable }
                    if ($dep) { Visit $dep $tables $visited $result }
                }
            }
            $visited[$t.Name] = "perm"
            $result.Add($t)
        }
    }
    foreach ($t in $tables) { Visit $t $tables $visited $result }
    return $result
}
$sortedTables = TopoSort $tables

# 6. GENERATE SQL
$counter = 1
foreach ($table in $sortedTables) {
    $fileName = "{0:000}-create-$($table.Name).sql" -f $counter
    $filePath = Join-Path $basePath $fileName
    $sql = "CREATE TABLE IF NOT EXISTS $($table.Name) (" + "`n"
    $cols = @()
    foreach ($col in $table.Columns) {
        $type = switch ($col.Type.ToLower()) {
            "int" { "BIGINT" }
            "varchar" { "VARCHAR(255)" }
            default { $col.Type.ToUpper() }
        }
        $line = "    $($col.Name) $type"
        if ($col.PrimaryKey) { $line += " PRIMARY KEY" }
        if (-not $col.PrimaryKey -and $type -like "VARCHAR%") { $line += " NOT NULL" }
        $cols += $line
    }
    $sql += ($cols -join ",`n")
    $sql += "`n);`n"

    # FK constraints
    foreach ($col in $table.Columns) {
        if ($col.FKTable) {
            $colNameSafe = if ([string]::IsNullOrEmpty($col.Name)) { "col$counter" } else { $col.Name }
            $fkName = "fk_${($table.Name)}_${colNameSafe}"
            $sql += "ALTER TABLE $($table.Name) ADD CONSTRAINT $fkName FOREIGN KEY ($colNameSafe) REFERENCES $($col.FKTable)($($col.FKColumn));`n"
        }
    }


    $sql | Out-File -Encoding UTF8 $filePath
    Write-Host "  -> SQL dibuat: $fileName"
    $counter++
}

Write-Host "✅ Semua file SQL berhasil dibuat di '$basePath'."
