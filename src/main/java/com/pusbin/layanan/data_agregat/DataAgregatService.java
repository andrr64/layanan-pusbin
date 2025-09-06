package com.pusbin.layanan.data_agregat;

import java.util.List;

import com.pusbin.layanan.data_agregat.dto.ResponseGetDataAgregat;

public interface DataAgregatService {
    List<ResponseGetDataAgregat> getAll(Long idInstansi, Long idPokja);
}