package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class StavkaPolice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToMany(mappedBy = "stavkaPolice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Knjiga knjiga;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }
}
