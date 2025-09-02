package com.pusbin.layanan.nama_jabatan;

import com.pusbin.layanan.nomenklatur.Nomenklatur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nama_jabatan")
@Data
@NoArgsConstructor
public class NamaJabatan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNamaJabatan;

    @Column(nullable = false, length = 100)
    private String namaJabatan;

    @ManyToOne
    @JoinColumn(name = "id_nomenklatur", nullable = false)
    private Nomenklatur nomenklatur;
}
