package com.pusbin.layanan.jabatan.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pusbin.layanan.jabatan.Jabatan;
import com.pusbin.layanan.jabatan.JabatanRepository;
import com.pusbin.layanan.jabatan.JabatanService;
import com.pusbin.layanan.jabatan.dto.ResponseGetJabatan;

@Service
public class JabatanServiceImpl implements JabatanService {

    private final JabatanRepository repository;

    @Autowired
    public JabatanServiceImpl(JabatanRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ResponseGetJabatan> getAll() {
        // 1. Ambil data mentah dari database
        List<Jabatan> data = repository.findAll();

        // 2. Siapkan list kosong untuk menampung hasil DTO
        List<ResponseGetJabatan> hasil = new ArrayList<>();

        // 3. Loop data satu per satu
        for (Jabatan jabatan : data) {
            String stringJabatan = String.format("%s, %s",
                    jabatan.getDataJabatan().getNamaJabatan(),
                    jabatan.getDataJenjang().getJenjang());

            ResponseGetJabatan dto = new ResponseGetJabatan();
            dto.setIdJabatan(jabatan.getIdJabatan());
            dto.setJabatan(stringJabatan);
            dto.setNomenklatur(jabatan.getDataJabatan().getNomenklatur().getNamaNomenklatur());

            // tambahkan ke list hasil
            hasil.add(dto);
        }

        // 4. Return list hasil
        return hasil;
    }

}
