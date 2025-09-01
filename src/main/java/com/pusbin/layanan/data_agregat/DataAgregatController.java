package com.pusbin.layanan.data_agregat;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/data_agregat")
public class DataAgregatController {
    private final DataAgregatService service;

    public DataAgregatController(DataAgregatService service) {
        this.service = service;
    }

    @GetMapping
    public List<DataAgregat> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public DataAgregat create(@RequestBody DataAgregat entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
