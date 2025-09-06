package com.pusbin.layanan.data_agregat.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pusbin.layanan.data_agregat.DataAgregat;
import com.pusbin.layanan.data_agregat.DataAgregatRepository;
import com.pusbin.layanan.data_agregat.DataAgregatService;
import com.pusbin.layanan.data_agregat.DataAgregatSpecification;
import com.pusbin.layanan.data_agregat.dto.ResponseGetDataAgregat;

@Service
public class DataAgregatServiceImpl implements DataAgregatService {

    private final DataAgregatRepository repository;

    @Autowired
    public DataAgregatServiceImpl(DataAgregatRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ResponseGetDataAgregat> getAll(Long idInstansi, Long idPokja) {
        Specification<DataAgregat> spec = (root, query, cb) -> cb.conjunction();

        if (idInstansi != null) {
            spec = spec.and(DataAgregatSpecification.hasInstansi(idInstansi));
        }

        if (idPokja != null){
            spec = spec.and(DataAgregatSpecification.hasPokja(idPokja));
        }
        
        List<DataAgregat> listDataRaw = repository.findAll(spec);
        List<ResponseGetDataAgregat> listDataAgregat = new ArrayList<>();

        for (DataAgregat data : listDataRaw) {
            Long kode_instansi = data.getInstansi().getIdInstansi();
            String instansi = data.getInstansi().getNamaInstansi();
            String kategori_instansi = data.getInstansi().getKategoriInstansi().getKategoriInstansi();
            String jenis_instansi = data.getInstansi().getJenisInstansi().getJenisInstansi();
            String nama_jabatan = data.getJabatan().getDataJabatan().getNamaJabatan();
            String jenjang = data.getJabatan().getDataJenjang().getJenjang();
            String jabatan = String.format("%s, %s", nama_jabatan, jenjang);
            String nomenklatur = data.getJabatan().getDataJabatan().getNomenklatur().getNamaNomenklatur();
            String jenis_asn = data.getAsn().getJenisAsn();
            Long jumlah = data.getJumlah();
            String wilayah_kerja = data.getInstansi().getWilayahKerja().getNamaWilayah();
            String pokja = data.getInstansi().getPokja().getNamaPokja();

            ResponseGetDataAgregat dataAgregat = new ResponseGetDataAgregat();
            dataAgregat.setKode_instansi(kode_instansi);
            dataAgregat.setInstansi(instansi);
            dataAgregat.setKategori_instansi(kategori_instansi);
            dataAgregat.setJenis_instansi(jenis_instansi);
            dataAgregat.setNama_jabatan(nama_jabatan);
            dataAgregat.setJenjang(jenjang);
            dataAgregat.setJabatan(jabatan);
            dataAgregat.setNomenklatur(nomenklatur);
            dataAgregat.setJenis_asn(jenis_asn);
            dataAgregat.setJumlah(jumlah);
            dataAgregat.setWilayah_kerja(wilayah_kerja);
            dataAgregat.setPokja(pokja);

            // tambahkan ke list response kalau perlu
            listDataAgregat.add(dataAgregat);
        }
        return listDataAgregat;
    }
}
