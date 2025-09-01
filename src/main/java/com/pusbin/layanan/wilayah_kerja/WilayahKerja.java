package com.pusbin.layanan.wilayah_kerja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wilayah_kerja")
@Data
@NoArgsConstructor
public class WilayahKerja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWilayah;

    @Column(nullable = false, length = 100)
    private String namaWilayah;
}
