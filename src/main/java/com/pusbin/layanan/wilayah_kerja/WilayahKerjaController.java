package com.pusbin.layanan.wilayah_kerja;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pusbin.layanan.wilayah_kerja.dto.RequestTambahWilayahKerja;
import com.pusbin.layanan.wilayah_kerja.dto.RequestUpdateWilayahKerja;

@RestController
@RequestMapping("/api/v1/wilayah_kerja")
public class WilayahKerjaController {

    private final WilayahKerjaService service;

    public WilayahKerjaController(WilayahKerjaService service) {
        this.service = service;
    }

    // GET /wilayah_kerja
    @GetMapping
    public List<WilayahKerja> getAll() {
        return service.getAllWilayah();
    }

    // GET /wilayah_kerja/{id}
    @GetMapping("/{id}")
    public ResponseEntity<WilayahKerja> getById(@PathVariable Long id) {
        return service.getWilayahById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /wilayah_kerja
    @PostMapping
    public WilayahKerja create(@RequestBody RequestTambahWilayahKerja body) {
        return service.createWilayah(body.getNamaWilayah());
    }

    // PUT /wilayah_kerja/{id}
    @PutMapping("/{id}")
    public ResponseEntity<WilayahKerja> update(@PathVariable Long id, @RequestBody RequestUpdateWilayahKerja body) {
        try {
            WilayahKerja updated = service.updateWilayah(id, body.getNamaWilayah());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /wilayah_kerja/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteWilayah(id);
        return ResponseEntity.noContent().build();
    }
}
