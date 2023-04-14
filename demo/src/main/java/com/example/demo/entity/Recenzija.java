package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Recenzija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private double ocena;
    @Column
    private String tekst;
    @Column
    private String datumRecenzije;
    @OneToOne
    private Korisnik korisnik;
}
