package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Knjiga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String naslov;
    @Column
    private String naslovnaFotografija;
    @Column
    private String ISBN;
    @Column
    private String datumObjavljivanja;
    @Column
    private int brojStrana;
    @Column
    private String opis;
    @ManyToOne
    private Zanr zanr;
    @Column
    private double ocena;
}
