package com.pusbin.layanan.jabatan;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pusbin.layanan.jabatan.dto.ResponseGetJabatan;

@RestController
@RequestMapping("/api/v1/jabatan")
public class JabatanController {
    private final JabatanService service;

    public JabatanController(JabatanService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResponseGetJabatan> getAllJabatan(){
        return service.getAll();
    }
}
