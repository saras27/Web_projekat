package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Recenzija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private double ocena;
    @Column
    private String tekst;
    @Column(name = "datum_recenzije")
    private String datumRecenzije;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private StavkaPolice stavka;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Korisnik korisnik;

    public Long getId() {
        return Id;
    }

    public double getOcena() {
        return ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public String getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
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

    @Override
    public String toString() {
        return "Recenzija{" +
                "Id=" + Id +
                ", ocena=" + ocena +
                ", tekst='" + tekst + '\'' +
                ", datumRecenzije='" + datumRecenzije + '\'' +
                ", stavka=" + stavka +
                ", korisnik=" + korisnik +
                '}';
    }
}
