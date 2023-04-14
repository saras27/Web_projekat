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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(List<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }
}
