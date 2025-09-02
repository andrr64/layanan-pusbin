package com.pusbin.layanan.pokja;

import java.util.List;

public interface PokjaService {
    List<Pokja> getAll();
    Pokja getById(Long id);
    Pokja create(Pokja entity);
    Pokja update(Long id, Pokja entity);
    void delete(Long id);
}
