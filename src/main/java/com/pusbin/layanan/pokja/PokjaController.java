package com.pusbin.layanan.pokja;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pusbin.layanan.pokja.dto.RequestTambahPokja;

@RestController
@RequestMapping("/api/v1/pokja")
public class PokjaController {

    private final PokjaService service;

    public PokjaController(PokjaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pokja> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Pokja getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Pokja create(@RequestBody RequestTambahPokja body) {
        return service.create(body.getNamaPokja());
    }

    @PutMapping("/{id}")
    public Pokja update(@PathVariable Long id, @RequestBody Pokja entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
