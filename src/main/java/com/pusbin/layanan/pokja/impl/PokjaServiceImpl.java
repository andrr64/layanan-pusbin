package com.pusbin.layanan.pokja.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pusbin.layanan.pokja.Pokja;
import com.pusbin.layanan.pokja.PokjaRepository;
import com.pusbin.layanan.pokja.PokjaService;

@Service
public class PokjaServiceImpl implements PokjaService {

    private final PokjaRepository repository;

    public PokjaServiceImpl(PokjaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pokja> getAll() {
        return repository.findAll();
    }

    @Override
    public Pokja getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pokja not found"));
    }

    @Override
    public Pokja create(String namaPokja) {
        Pokja dataBaru = new Pokja();
        dataBaru.setNamaPokja(namaPokja);
        return repository.save(dataBaru);
    }

    @Override
    public Pokja update(Long id, Pokja entity) {
        Pokja existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Pokja not found"));
        existing.setNamaPokja(entity.getNamaPokja());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
