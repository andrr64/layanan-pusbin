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

# Loop untuk bikin folder & file
foreach ($entity in $entities) {
    $folder = Join-Path $basePath $entity
    if (!(Test-Path $folder)) {
        New-Item -ItemType Directory -Force -Path $folder | Out-Null
    }

    $className = To-PascalCase $entity
    $packageName = "com.pusbin.layanan.$entity"

    # File templates
    $files = @{
        "$className.java" = @"
package $packageName;

import jakarta.persistence.*;

@Entity
@Table(name = "$entity")
public class $className {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
"@

        "$className`Repository.java" = @"
package $packageName;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ${className}Repository extends JpaRepository<$className, Long> {
}
"@

        "$className`Service.java" = @"
package $packageName;

public interface ${className}Service {
}
"@

        "$className`Controller.java" = @"
package $packageName;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/$entity")
public class ${className}Controller {
    private final ${className}Service service;

    public ${className}Controller(${className}Service service) {
        this.service = service;
    }

    @GetMapping
    public List<$className> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public $className create(@RequestBody $className entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
"@
    }

    # Generate files
    foreach ($file in $files.Keys) {
        $filePath = Join-Path $folder $file
        if (!(Test-Path $filePath)) {
            $files[$file] | Out-File -FilePath $filePath -Encoding UTF8
            Write-Host "File dibuat: $filePath"
        } else {
            Write-Host "File sudah ada: $filePath"
        }
    }
}
