package com.pusbin.layanan.nama_jabatan;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/nama_jabatan")
public class NamaJabatanController {
    private final NamaJabatanService service;

    public NamaJabatanController(NamaJabatanService service) {
        this.service = service;
    }

    @GetMapping
    public List<NamaJabatan> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping
    public NamaJabatan create(@RequestBody NamaJabatan entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
