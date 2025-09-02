package com.pusbin.layanan.pokja.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestTambahPokja {
    @NotBlank(message="Nama pokja tidak boleh kosong")
    @Size(max=100, message= "Nama pokja maksimal 100 karakter")
    private String namaPokja;
}
