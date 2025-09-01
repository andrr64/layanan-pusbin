package com.pusbin.layanan.nomenklatur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nomenklatur")
@Data
@NoArgsConstructor
public class Nomenklatur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNomenklatur;

    @Column(nullable = false, length = 100)
    private String namaNomenklatur;
}
