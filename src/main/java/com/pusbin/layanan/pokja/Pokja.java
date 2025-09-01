package com.pusbin.layanan.pokja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokja")
@Data
@NoArgsConstructor
public class Pokja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPokja;

    @Column(nullable = false, length = 100)
    private String namaPokja;
}
