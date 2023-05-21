package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Polica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String naziv;
    @Column
    private boolean primarna;
    @ManyToMany
    @JoinTable(name = "police_stavke",
            joinColumns = @JoinColumn(name = "polica_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stavka_id", referencedColumnName = "id"))
    private Set<StavkaPolice> stavke = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Korisnik korisnik;
}
