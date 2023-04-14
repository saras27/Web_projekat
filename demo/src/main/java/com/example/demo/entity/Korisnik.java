package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column
    private String korisnickoIme;
    @Column
    private String mejlAdresa;
    @Column
    private String lozinka;
    @Column
    private String datumRodjenja;
    @Column
    private String opis;
    @Column
    private String uloga;


}
