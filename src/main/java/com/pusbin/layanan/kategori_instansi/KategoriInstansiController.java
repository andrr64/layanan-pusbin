package com.pusbin.layanan.kategori_instansi;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/kategori_instansi")
public class KategoriInstansiController {
    private final KategoriInstansiService service;

    public KategoriInstansiController(KategoriInstansiService service) {
        this.service = service;
    }

    @GetMapping
    public List<KategoriInstansi> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public KategoriInstansi create(@RequestBody KategoriInstansi entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
