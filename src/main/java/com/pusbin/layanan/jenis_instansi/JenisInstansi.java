package com.pusbin.layanan.jenis_instansi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jenis_instansi")
@Data
@NoArgsConstructor
public class JenisInstansi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJenisInstansi;

    @Column(nullable = false, length = 100)
    private String jenisInstansi;
}
