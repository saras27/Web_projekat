package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StavkaPolice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //one o=to one
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stavka_id")
    private Recenzija recenzije;

    //many to one
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Knjiga knjiga;

    public void setId(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public Recenzija getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Recenzija recenzije) {
        this.recenzije = recenzije;
    }

    @Override
    public String toString() {
        return "Stavka{" +
                "Id=" + Id +
                ", knjiga=" + knjiga +
                '}';
    }
}

