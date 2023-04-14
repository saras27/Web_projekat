package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class StavkaPolice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToMany
    private List<Recenzija> recenzije;
    @OneToOne
    private Knjiga knjiga;
}
