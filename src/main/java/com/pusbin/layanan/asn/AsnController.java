package com.pusbin.layanan.asn;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asn")
public class AsnController {
    private final AsnService service;

    public AsnController(AsnService service) {
        this.service = service;
    }

    @GetMapping
    public List<Asn> getAll() {
        // implementasi nanti di ServiceImpl
        return null;
    }

    @PostMapping()
    public Asn create(@RequestBody Asn entity) {
        // implementasi nanti di ServiceImpl
        return null;
    }
}
