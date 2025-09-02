package com.pusbin.layanan.wilayah_kerja.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pusbin.layanan.wilayah_kerja.WilayahKerja;
import com.pusbin.layanan.wilayah_kerja.WilayahKerjaRepository;
import com.pusbin.layanan.wilayah_kerja.WilayahKerjaService;

@Service
public class WilayahKerjaServiceImpl implements WilayahKerjaService {

    private final WilayahKerjaRepository repository;

    @Autowired
    public WilayahKerjaServiceImpl(WilayahKerjaRepository repository) {
        this.repository = repository;
    }

    @Override
    public WilayahKerja createWilayah(String namaWilayah) {
        WilayahKerja dataBaru = new WilayahKerja();
        dataBaru.setNamaWilayah(namaWilayah);
        return repository.save(dataBaru);
    }

    @Override
    public Optional<WilayahKerja> getWilayahById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<WilayahKerja> getAllWilayah() {
        return repository.findAll();
    }

    @Override
    public WilayahKerja updateWilayah(Long id, String namaWilayah) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNamaWilayah(namaWilayah); // pakai parameter langsung
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Wilayah tidak ditemukan dengan id: " + id));
    }

    @Override
    public void deleteWilayah(Long id) {
        repository.deleteById(id);
    }
}
