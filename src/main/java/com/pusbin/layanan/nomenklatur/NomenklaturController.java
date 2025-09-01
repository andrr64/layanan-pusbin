package com.pusbin.layanan.nomenklatur;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/nomenklatur")
public class NomenklaturController {
    private final NomenklaturService service;

    public NomenklaturController(NomenklaturService service) {
        this.service = service;
    }

    @GetMapping
    public List<Nomenklatur> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public Nomenklatur create(@RequestBody Nomenklatur entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
