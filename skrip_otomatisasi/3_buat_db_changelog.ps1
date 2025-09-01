# ==============================================================================
# SCRIPT GENERATOR LIQUIBASE CHANGELOG DARI PLANTUML v2.2 (Final Fix)
# - Sudah support stereotype <<PK>> dan <<FK>>
# - ConstraintName FK selalu unik (pakai nama tabel + kolom, ada fallback)
# - Urutan tabel dibuat pakai Topological Sort
# ==============================================================================

param(
    [string]$plantUMLFile = "dokumen_penting/ERD.puml"  # Bisa diganti ke file eksternal
)

# 1. BACA INPUT: Desain PlantUML
if (Test-Path $plantUMLFile) {
    $plantUML = Get-Content $plantUMLFile -Raw
}
else {
    Write-Host "❌ File $plantUMLFile tidak ditemukan!" -ForegroundColor Red
    exit
}

# 2. SIAPKAN FOLDER OUTPUT
$basePath = "src/main/resources/db/changelog"
if (Test-Path $basePath) {
    Write-Host "Membersihkan direktori changelog lama..." -ForegroundColor Yellow
    Remove-Item -Path "$basePath\*.yaml" -Force
}
else {
    Write-Host "Membuat direktori changelog..." -ForegroundColor Green
    New-Item -ItemType Directory -Force -Path $basePath | Out-Null
}

# 3. PARSING ENTITY & KOLOM
Write-Host "Mem-parsing desain PlantUML..." -ForegroundColor Cyan
$tables = @()

$entityRegex = [regex] '(?s)entity\s+([\w_]+)\s*\{([^}]+)\}'
$entityMatches = $entityRegex.Matches($plantUML)

foreach ($match in $entityMatches) {
    $tableName = $match.Groups[1].Value
    $columnsContent = $match.Groups[2].Value
    $columns = @()

    $columnsContent.Split("`n") | ForEach-Object {
        $line = $_.Trim()
        # Tangkap nama, tipe, dan stereotype opsional <<PK>> / <<FK>>
        if ($line -match '^(\*?)\s*([\w_]+)\s*:\s*([\w\d_]+)(?:\s*<<(\w+)>>)?') {
            $isPk = $matches[1] -eq '*'
            $colName = $matches[2]
            $colType = $matches[3]
            $stereotype = $matches[4]

            $columns += @{
                Name       = $colName
                Type       = $colType
                PrimaryKey = $isPk -or ($stereotype -eq "PK")
                FKTable    = $null
                FKColumn   = $null
                IsFK       = ($stereotype -eq "FK")
            }
        }
    }

    $tables += @{
        Name    = $tableName
        Columns = $columns
    }
}

# 4. PARSING RELATIONSHIP (FK)
$fkRegex = [regex] '([\w_]+)::([\w_]+)\s*\}o--\|\|\s*([\w_]+)::([\w_]+)'
$fkMatches = $fkRegex.Matches($plantUML)

foreach ($match in $fkMatches) {
    $baseTable = $match.Groups[1].Value
    $baseColumn = $match.Groups[2].Value
    $referencedTable = $match.Groups[3].Value
    $referencedColumn = $match.Groups[4].Value

    $tableToUpdate = $tables | Where-Object { $_.Name -eq $baseTable }
    if ($tableToUpdate) {
        $colToUpdate = $tableToUpdate.Columns | Where-Object { $_.Name -eq $baseColumn }
        if ($colToUpdate) {
            $colToUpdate.FKTable = $referencedTable
            $colToUpdate.FKColumn = $referencedColumn
        }
    }
}

# 5. TOPOLOGICAL SORT (agar parent dibuat dulu sebelum child)
function TopoSort($tables) {
    $visited = @{}
    $result = New-Object System.Collections.Generic.List[object]

    function Visit($t, $tables, $visited, $result) {
        if ($visited[$t.Name] -eq "temp") {
            throw "❌ Circular dependency terdeteksi di tabel $($t.Name)"
        }
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

# Urutkan pakai topological sort
$sortedTables = TopoSort $tables

# 6. GENERATE FILE YAML
Write-Host "Menghasilkan file Liquibase changelog..." -ForegroundColor Cyan
$masterFile = Join-Path $basePath "db.changelog-master.yaml"
"databaseChangeLog:" | Out-File -Encoding UTF8 $masterFile

$counter = 1
foreach ($table in $sortedTables) {
    $fileName = "{0:000}-create-{1}.yaml" -f $counter, $table.Name
    $filePath = Join-Path $basePath $fileName

    $yamlContent = @"
databaseChangeLog:
  - changeSet:
      id: $counter
      author: pusbin-generator
      changes:
        - createTable:
            tableName: $($table.Name)
            columns:
"@

    foreach ($col in $table.Columns) {
        $sqlType = switch ($col.Type.ToLower()) {
            "int" { "BIGINT" }
            "varchar" { "VARCHAR(255)" }
            default { $col.Type.ToUpper() }
        }

        $yamlContent += "`n              - column:"
        $yamlContent += "`n                  name: $($col.Name)"
        $yamlContent += "`n                  type: $sqlType"
        if ($col.PrimaryKey) { 
            $yamlContent += "`n                  autoIncrement: true" 
        }

        $constraints = @()
        if ($col.PrimaryKey) { $constraints += "primaryKey: true" }
        if ($col.PrimaryKey -or $col.FKTable -or ($col.Type -eq 'varchar')) { $constraints += "nullable: false" }

        if ($constraints.Count -gt 0) {
            $yamlContent += "`n                  constraints:"
            foreach ($c in $constraints) { $yamlContent += "`n                    $c" }
        }
    }

    # Tambahkan FK constraints
    foreach ($col in $table.Columns) {
        if ($col.FKTable) {
            $colNameSafe = if ([string]::IsNullOrEmpty($col.Name)) { "col$counter" } else { $col.Name }
            $fkConstraintName = "fk_${($table.Name)}_${colNameSafe}"
            $yamlContent += @"

        - addForeignKeyConstraint:
            baseTableName: $($table.Name)
            baseColumnNames: $colNameSafe
            constraintName: $fkConstraintName
            referencedTableName: $($col.FKTable)
            referencedColumnNames: $($col.FKColumn)
"@
        }
    }

    $yamlContent | Out-File -Encoding UTF8 $filePath
    "  - include:`n      file: db/changelog/$fileName" | Add-Content -Encoding UTF8 $masterFile

    Write-Host "  -> Berhasil membuat: $fileName"
    $counter++
}

Write-Host "✅ Sukses! Semua file Liquibase changelog sudah dibuat di '$basePath'." -ForegroundColor Green
