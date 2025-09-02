package com.pusbin.layanan.nama_jabatan;

import com.pusbin.layanan.nama_jabatan.dto.RequestTambahNamaJabatan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/nama_jabatan")
public class NamaJabatanController {
    private final NamaJabatanService service;

    public NamaJabatanController(NamaJabatanService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NamaJabatan>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<NamaJabatan> create(
            @Valid @RequestBody RequestTambahNamaJabatan request) {
        return ResponseEntity.ok(service.create(request));
    }
}
