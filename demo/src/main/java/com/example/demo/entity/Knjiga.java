package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Knjiga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String naslov;
    @Column(name = "naslovna_fotografija")
    private String naslovnaFotografija;
    @Column(unique = true)
    private String ISBN;
    @Column(name = "datum_objavljivanja")
    private LocalDate datumObjavljivanja;
    @Column(name = "broj_strana")
    private int brojStrana;
    @Column
    private String opis;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "zanr_id")
    private Zanr zanr;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Autor autor;
    @Column
    private double ocena;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getNaslovnaFotografija() {
        return naslovnaFotografija;
    }

    public void setNaslovnaFotografija(String naslovnaFotografija) {
        this.naslovnaFotografija = naslovnaFotografija;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public void setDatumObjavljivanja(LocalDate datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }

    public int getBrojStrana() {
        return brojStrana;
    }

    public void setBrojStrana(int brojStrana) {
        this.brojStrana = brojStrana;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Knjiga{" +
                "Id=" + Id +
                ", naslov='" + naslov + '\'' +
                ", naslovnaFotografija='" + naslovnaFotografija + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", datumObjavljivanja='" + datumObjavljivanja + '\'' +
                ", brojStrana=" + brojStrana +
                ", opis='" + opis + '\'' +
                ", ocena=" + ocena +
                ", autor=" + autor +
                ", zanr=" + zanr +
                '}';
    }
}
