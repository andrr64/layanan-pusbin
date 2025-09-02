package com.pusbin.layanan.nama_jabatan.impl;

import com.pusbin.layanan.nama_jabatan.dto.RequestTambahNamaJabatan;
import com.pusbin.layanan.nomenklatur.Nomenklatur;
import com.pusbin.layanan.nomenklatur.NomenklaturRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import com.pusbin.layanan.nama_jabatan.NamaJabatan;
import com.pusbin.layanan.nama_jabatan.NamaJabatanRepository;
import com.pusbin.layanan.nama_jabatan.NamaJabatanService;

@Service
public class NamaJabatanServiceImpl implements NamaJabatanService {
    
    private final NamaJabatanRepository namaJabatanRepository;
    private final NomenklaturRepository nomenklaturRepository;

    public NamaJabatanServiceImpl(NamaJabatanRepository namaJabatanRepository, 
                                  NomenklaturRepository nomenklaturRepository) {
        this.namaJabatanRepository = namaJabatanRepository;
        this.nomenklaturRepository = nomenklaturRepository;
    }

    @Override
    public List<NamaJabatan> getAll() {
        return namaJabatanRepository.findAll();
    }

    @Override
    public NamaJabatan create(RequestTambahNamaJabatan request) {
        Nomenklatur nomenklatur = nomenklaturRepository.findById(request.getIdNomenklatur())
                .orElseThrow(() -> new RuntimeException("Nomenklatur tidak ditemukan"));

        NamaJabatan namaJabatan = new NamaJabatan();
        namaJabatan.setNamaJabatan(request.getNamaJabatan());
        namaJabatan.setNomenklatur(nomenklatur);

        return namaJabatanRepository.save(namaJabatan);
    }
}
