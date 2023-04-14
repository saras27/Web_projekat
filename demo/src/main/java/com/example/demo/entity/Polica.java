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

    public List<StavkaPolice> getStavkePolice() {
        return stavkePolice;
    }

    public void setStavkePolice(List<StavkaPolice> stavkePolice) {
        this.stavkePolice = stavkePolice;
    }
}
