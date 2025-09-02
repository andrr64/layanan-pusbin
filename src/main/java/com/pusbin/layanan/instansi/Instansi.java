package com.pusbin.layanan.instansi;

import com.pusbin.layanan.jenis_instansi.JenisInstansi;
import com.pusbin.layanan.kategori_instansi.KategoriInstansi;
import com.pusbin.layanan.pokja.Pokja;
import com.pusbin.layanan.wilayah_kerja.WilayahKerja;

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
@Table(name = "instansi")
@Data
@NoArgsConstructor
public class Instansi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstansi;

    @Column(nullable = false, length = 150)
    private String namaInstansi;

    @ManyToOne
    @JoinColumn(name = "id_kategori_instansi", nullable = false)
    private KategoriInstansi kategoriInstansi;

    @ManyToOne
    @JoinColumn(name = "id_jenis_instansi", nullable = false)
    private JenisInstansi jenisInstansi;

    @ManyToOne
    @JoinColumn(name = "id_wilayah_kerja", nullable = false)
    private WilayahKerja wilayahKerja;

    @ManyToOne
    @JoinColumn(name = "id_pokja", nullable = false)
    private Pokja pokja;
}
