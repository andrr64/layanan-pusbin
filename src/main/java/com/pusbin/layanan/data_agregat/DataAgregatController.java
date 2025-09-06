package com.pusbin.layanan.data_agregat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pusbin.layanan.data_agregat.dto.ResponseGetDataAgregat;

@RestController
@RequestMapping("/api/v1/data-agregat")
public class DataAgregatController {

    private final DataAgregatService service;

    public DataAgregatController(DataAgregatService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResponseGetDataAgregat> getAll(
            @RequestParam(name="instansi", required = false) Long idInstansi,
            @RequestParam(name="pokja", required=false) Long idPokja
    ) {
        return service.getAll(idInstansi, idPokja);
    }
}
