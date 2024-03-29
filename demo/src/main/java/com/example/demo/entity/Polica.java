package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Long id;
    @Column
    private String naziv;
    @Column
    private boolean primarna;
    @ManyToMany(cascade = CascadeType.ALL)///////////
    @JoinTable(name = "police_stavke",
            joinColumns = @JoinColumn(name = "polica_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stavka_id", referencedColumnName = "id"))
    private Set<StavkaPolice> stavke = new HashSet<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Korisnik korisnik;

    public Polica() {
    }

    public Polica(String naziv, boolean primarna){
        this.naziv = naziv;
        this.primarna = primarna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isPrimarna() {
        return primarna;
    }

    public void setPrimarna(boolean primarna) {
        this.primarna = primarna;
    }

    public Set<StavkaPolice> getStavke() {
        return stavke;
    }

    public void setStavke(Set<StavkaPolice> stavke) {
        this.stavke = stavke;
    }
    public void setStavka(StavkaPolice stavka) {
        this.stavke.add(stavka);
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
