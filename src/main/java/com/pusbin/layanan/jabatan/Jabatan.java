package com.pusbin.layanan.jabatan;

import com.pusbin.layanan.jenjang.Jenjang;
import com.pusbin.layanan.nama_jabatan.NamaJabatan;

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
@Table(name = "jabatan")
@Data
@NoArgsConstructor
public class Jabatan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJabatan;

    @ManyToOne
    @JoinColumn(name = "id_nama_jabatan", nullable = false)
    private NamaJabatan namaJabatan;

    @ManyToOne
    @JoinColumn(name = "id_jenjang", nullable = false)
    private Jenjang jenjang;
}
