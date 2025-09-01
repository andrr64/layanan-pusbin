package com.pusbin.layanan.asn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asn")
@Data
@NoArgsConstructor
public class Asn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsn;

    @Column(nullable = false, length = 100)
    private String jenisAsn;
}
