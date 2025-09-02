package com.pusbin.layanan.jenjang;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jenjang")
@Data
@NoArgsConstructor
public class Jenjang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJenjang;

    @Column(nullable = false, length = 100)
    private String jenjang;
}
