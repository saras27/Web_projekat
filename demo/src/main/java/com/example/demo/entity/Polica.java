package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Polica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String naziv;
    @Column
    private String primarna;
    @ManyToMany
    private List<StavkaPolice> stavkePolice;
}
