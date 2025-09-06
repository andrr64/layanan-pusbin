package com.pusbin.layanan.data_agregat;

import org.springframework.data.jpa.domain.Specification;

public class DataAgregatSpecification {

    public static Specification<DataAgregat> hasInstansi(Long instansiId) {
        return (root, query, cb)
                -> instansiId == null ? null : cb.equal(root.get("instansi").get("idInstansi"), instansiId);
    }

    public static Specification<DataAgregat> hasJabatan(Long jabatanId) {
        return (root, query, cb)
                -> jabatanId == null ? null : cb.equal(root.get("jabatan").get("idJabatan"), jabatanId);
    }

    public static Specification<DataAgregat> hasAsn(Long asnId) {
        return (root, query, cb)
                -> asnId == null ? null : cb.equal(root.get("asn").get("idAsn"), asnId);
    }

        public static Specification<DataAgregat> hasPokja(Long idPokja) {
        return (root, query, cb)
                -> idPokja == null ? null : cb.equal(
                    root
                        .get("instansi")
                        .get("pokja")
                        .get("idPokja"), idPokja
                );
    }
}
