CREATE TABLE IF NOT EXISTS nama_jabatan (
    id_nama_jabatan BIGINT PRIMARY KEY,
    nama_jabatan VARCHAR(255),
    id_nomenklatur BIGINT
);
ALTER TABLE nama_jabatan ADD CONSTRAINT fk__id_nomenklatur FOREIGN KEY (id_nomenklatur) REFERENCES nomenklatur(id_nomenklatur);

