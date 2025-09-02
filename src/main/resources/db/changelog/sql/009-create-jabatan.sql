CREATE TABLE IF NOT EXISTS jabatan (
    id_jabatan BIGINT PRIMARY KEY,
    id_nama_jabatan BIGINT,
    id_jenjang BIGINT
);
ALTER TABLE jabatan ADD CONSTRAINT fk__id_nama_jabatan FOREIGN KEY (id_nama_jabatan) REFERENCES nama_jabatan(id_nama_jabatan);
ALTER TABLE jabatan ADD CONSTRAINT fk__id_jenjang FOREIGN KEY (id_jenjang) REFERENCES jenjang(id_jenjang);

