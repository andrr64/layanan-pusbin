package com.pusbin.layanan.jenis_instansi;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jenis_instansi")
public class JenisInstansiController {
    private final JenisInstansiService service;

    public JenisInstansiController(JenisInstansiService service) {
        this.service = service;
    }

    @GetMapping
    public List<JenisInstansi> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public JenisInstansi create(@RequestBody JenisInstansi entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
