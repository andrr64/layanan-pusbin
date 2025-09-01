package com.pusbin.layanan.instansi;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/instansi")
public class InstansiController {
    private final InstansiService service;

    public InstansiController(InstansiService service) {
        this.service = service;
    }

    @GetMapping
    public List<Instansi> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public Instansi create(@RequestBody Instansi entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
