# Root project src folder
$basePath = "src\main\java\com\pusbin\layanan"

# Daftar entitas sesuai UML
$entities = @(
    "data_agregat",
    "instansi",
    "kategori_instansi",
    "jenis_instansi",
    "pokja",
    "wilayah_kerja",
    "jabatan",
    "nama_jabatan",
    "jenjang",
    "nomenklatur",
    "asn"
)

# Fungsi untuk konversi snake_case jadi PascalCase
function To-PascalCase($name) {
    if ([string]::IsNullOrWhiteSpace($name)) { return "UnknownClass" }

    $parts = $name -split "_"
    $converted = foreach ($p in $parts) {
        if ($p.Length -gt 0) {
            $p.Substring(0,1).ToUpper() + $p.Substring(1)
        }
    }
    return ($converted -join "")
}

# Loop untuk bikin folder impl + ServiceImpl
foreach ($entity in $entities) {
    $entityFolder = Join-Path $basePath $entity
    $implFolder = Join-Path $entityFolder "impl"

    # Pastikan folder entity ada
    if (!(Test-Path $entityFolder)) {
        New-Item -ItemType Directory -Force -Path $entityFolder | Out-Null
        Write-Host "Folder dibuat: $entityFolder"
    }

    # Bikin folder impl
    if (!(Test-Path $implFolder)) {
        New-Item -ItemType Directory -Force -Path $implFolder | Out-Null
        Write-Host "Folder impl dibuat: $implFolder"
    }

    # Nama class (PascalCase)
    $className = To-PascalCase $entity
    $filePath = Join-Path $implFolder "$className`ServiceImpl.java"

    # Kalau belum ada file, buat
    if (!(Test-Path $filePath)) {
        $packageName = "com.pusbin.layanan.$entity.impl"
        $serviceImport = "com.pusbin.layanan.$entity.${className}Service"

@"
package $packageName;

import org.springframework.stereotype.Service;
import $serviceImport;

@Service
public class ${className}ServiceImpl implements ${className}Service {

}
"@ | Out-File -FilePath $filePath -Encoding UTF8

        Write-Host "File dibuat: $filePath"
    } else {
        Write-Host "File sudah ada: $filePath"
    }
}
