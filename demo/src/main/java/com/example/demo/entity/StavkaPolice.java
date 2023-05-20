package com.example.demo.entity;

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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stavka_id")
    private Set<Recenzija> recenzije = new HashSet<>();

    //many to one
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return "Stavka{" +
                "Id=" + Id +
                ", knjiga=" + knjiga +
                '}';
    }
}

