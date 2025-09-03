CREATE TABLE IF NOT EXISTS data_agregat (
    id SERIAL PRIMARY KEY,
    id_instansi INT,
    id_jabatan INT,
    id_jenis_asn INT,
    jumlah BIGINT
);
ALTER TABLE data_agregat ADD CONSTRAINT fk__id_instansi FOREIGN KEY (id_instansi) REFERENCES instansi(id_instansi);
ALTER TABLE data_agregat ADD CONSTRAINT fk__id_jabatan FOREIGN KEY (id_jabatan) REFERENCES jabatan(id_jabatan);
ALTER TABLE data_agregat ADD CONSTRAINT fk__id_jenis_asn FOREIGN KEY (id_jenis_asn) REFERENCES asn(id_asn);