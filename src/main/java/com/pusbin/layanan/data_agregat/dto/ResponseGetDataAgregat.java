package com.pusbin.layanan.data_agregat.dto;

import lombok.Data;

@Data
public class ResponseGetDataAgregat {
    Long kode_instansi;
    String instansi;
    String kategori_instansi;
    String jenis_instansi;
    String jabatan;
    String nama_jabatan;
    String jenjang;
    String nomenklatur;
    String jenis_asn;
    Long jumlah;
    String wilayah_kerja;
    String pokja;
}
