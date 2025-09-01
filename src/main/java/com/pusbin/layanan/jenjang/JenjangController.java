package com.pusbin.layanan.jenjang;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jenjang")
public class JenjangController {
    private final JenjangService service;

    public JenjangController(JenjangService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jenjang> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public Jenjang create(@RequestBody Jenjang entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
