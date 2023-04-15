package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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
    @JoinTable(name = "StavkePolice", joinColumns = @JoinColumn(name = "polica_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "stavkaPolice_id", referencedColumnName = "id"))
    private Set<StavkaPolice> stavkePolice = new HashSet<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPrimarna() {
        return primarna;
    }

    public void setPrimarna(String primarna) {
        this.primarna = primarna;
    }

    public Set<StavkaPolice> getStavkePolice() {
        return stavkePolice;
    }

    public void setStavkePolice(Set<StavkaPolice> stavkePolice) {
        this.stavkePolice = stavkePolice;
    }
}
