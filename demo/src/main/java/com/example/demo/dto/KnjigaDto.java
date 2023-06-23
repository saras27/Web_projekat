package com.example.demo.dto;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Zanr;

import javax.persistence.*;
import java.time.LocalDate;

public class KnjigaDto {
    private Long Id;
    private String naslov;
    private String naslovnaFotografija;
    private String ISBN;
    private LocalDate datumObjavljivanja;
    private int brojStrana;
    private String opis;
    private Zanr zanr;
    private Autor autor;
    private double ocena;
    public KnjigaDto(){
    }
    public KnjigaDto(Long Id, String naslov, String naslovnaFotografija, String ISBN, LocalDate
            datumObjavljivanja, int brStr, String opis, Zanr zanr, Autor autor, double ocena){
        this.Id = Id;
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.ISBN = ISBN;
        this.datumObjavljivanja = datumObjavljivanja;
        this.brojStrana = brStr;
        this.opis = opis;
        this.zanr = zanr;
        this.autor = autor;
        this.ocena = ocena;
    }
    public KnjigaDto(Knjiga knjiga){
        this.Id = knjiga.getId();
        this.naslov = knjiga.getNaslov();
        this.naslovnaFotografija = knjiga.getNaslovnaFotografija();
        this.ISBN = knjiga.getISBN();
        this.datumObjavljivanja = knjiga.getDatumObjavljivanja();
        this.brojStrana = knjiga.getBrojStrana();
        this.opis = knjiga.getOpis();
        this.zanr = knjiga.getZanr();
        this.autor = knjiga.getAutor();
        this.ocena = knjiga.getOcena();
    }
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
