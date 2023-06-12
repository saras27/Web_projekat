package com.example.demo.dto;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Zanr;

import java.time.LocalDate;

public class NovaKnjigaDto {
    private String naslov;
    private String naslovnaFotografija;
    private String ISBN;
    private LocalDate datumObjavljivanja;
    private int brojStrana;
    private String opis;
    private Zanr zanr;
    private Autor autor;

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

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
