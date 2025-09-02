package com.pusbin.layanan.wilayah_kerja.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUpdateWilayahKerja {
    @NotBlank(message = "Nama wilayah tidak boleh kosong")
    @Size(max = 100, message = "Nama wilayah maksimal 100 karakter")
    private String namaWilayah;
}
