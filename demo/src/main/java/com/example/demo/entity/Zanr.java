package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Zanr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String naziv;

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
    /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Knjiga knjiga;*/

    @Override
    public String toString() {
        return "Zanr{" +
                "Id=" + Id +
                ", naziv='" + naziv + '\'' +
                //", knjiga=" + knjiga +
                '}';
    }
}
