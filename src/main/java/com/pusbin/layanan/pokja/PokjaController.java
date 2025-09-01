package com.pusbin.layanan.pokja;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pokja")
public class PokjaController {
    private final PokjaService service;

    public PokjaController(PokjaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pokja> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public Pokja create(@RequestBody Pokja entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
