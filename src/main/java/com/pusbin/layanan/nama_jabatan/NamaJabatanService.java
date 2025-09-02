package com.pusbin.layanan.nama_jabatan;

import com.pusbin.layanan.nama_jabatan.dto.RequestTambahNamaJabatan;
import java.util.List;

public interface NamaJabatanService {
    List<NamaJabatan> getAll();
    NamaJabatan create(RequestTambahNamaJabatan request);
}
