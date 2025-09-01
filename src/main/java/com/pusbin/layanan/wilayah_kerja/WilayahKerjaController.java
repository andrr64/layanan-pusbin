package com.pusbin.layanan.wilayah_kerja;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wilayah_kerja")
public class WilayahKerjaController {
    private final WilayahKerjaService service;

    public WilayahKerjaController(WilayahKerjaService service) {
        this.service = service;
    }

    @GetMapping
    public List<WilayahKerja> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public WilayahKerja create(@RequestBody WilayahKerja entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
