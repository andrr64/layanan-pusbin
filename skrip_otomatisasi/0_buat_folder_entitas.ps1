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

# Loop untuk bikin folder
foreach ($entity in $entities) {
    $path = Join-Path $basePath $entity
    if (!(Test-Path $path)) {
        New-Item -ItemType Directory -Force -Path $path | Out-Null
        Write-Host "Folder dibuat: $path"
    } else {
        Write-Host "Folder sudah ada: $path"
    }
}
