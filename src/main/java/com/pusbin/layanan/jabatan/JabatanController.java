package com.pusbin.layanan.jabatan;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jabatan")
public class JabatanController {
    private final JabatanService service;

    public JabatanController(JabatanService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jabatan> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public Jabatan create(@RequestBody Jabatan entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
