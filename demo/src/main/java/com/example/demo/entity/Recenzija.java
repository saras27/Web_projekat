package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Recenzija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private double ocena;
    @Column
    private String tekst;
    @Column
    private String datumRecenzije;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Korisnik korisnik;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(String datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
