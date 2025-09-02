package com.pusbin.layanan.jabatan.dto;
import lombok.Data;

@Data
public class ResponseGetJabatan {
    private Long idJabatan;
    private String jabatan;
    private String nomenklatur;
}