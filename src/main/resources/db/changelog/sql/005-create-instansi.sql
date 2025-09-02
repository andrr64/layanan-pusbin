CREATE TABLE IF NOT EXISTS instansi (
    id_instansi SERIAL PRIMARY KEY,
    nama_instansi VARCHAR(255),
    id_kategori_instansi INT,
    id_jenis_instansi INT,
    id_wilayah_kerja INT,
    id_pokja INT
);
ALTER TABLE instansi ADD CONSTRAINT fk__id_kategori_instansi FOREIGN KEY (id_kategori_instansi) REFERENCES kategori_instansi(id_kategori_instansi);
ALTER TABLE instansi ADD CONSTRAINT fk__id_jenis_instansi FOREIGN KEY (id_jenis_instansi) REFERENCES jenis_instansi(id_jenis_instansi);
ALTER TABLE instansi ADD CONSTRAINT fk__id_wilayah_kerja FOREIGN KEY (id_wilayah_kerja) REFERENCES wilayah_kerja(id_wilayah);
ALTER TABLE instansi ADD CONSTRAINT fk__id_pokja FOREIGN KEY (id_pokja) REFERENCES pokja(id_pokja);

