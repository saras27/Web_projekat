package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ZahtevZaAktivaciju {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String email;
    @Column
    private String telefon;
    @Column
    private String poruka;
    @Column
    private String datum;
    @Column
    private String status;
}
