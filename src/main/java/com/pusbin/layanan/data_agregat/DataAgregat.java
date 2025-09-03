package com.pusbin.layanan.data_agregat;

import com.pusbin.layanan.asn.Asn;
import com.pusbin.layanan.instansi.Instansi;
import com.pusbin.layanan.jabatan.Jabatan;

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
@Table(name = "data_agregat")
@Data
@NoArgsConstructor
public class DataAgregat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_instansi", nullable = false)
    private Instansi instansi;

    @ManyToOne
    @JoinColumn(name = "id_jabatan", nullable = false)
    private Jabatan jabatan;

    @ManyToOne
    @JoinColumn(name = "id_jenis_asn", nullable = false)
    private Asn asn;

    private Long jumlah;
}
