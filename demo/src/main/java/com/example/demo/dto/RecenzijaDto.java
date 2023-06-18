package com.example.demo.dto;

import com.example.demo.entity.*;

import javax.persistence.*;
import java.time.LocalDate;

public class RecenzijaDto {

    private Long Id;
    private double ocena;
    private String tekst;
    private LocalDate datumRecenzije;
    private Korisnik korisnik;
    public RecenzijaDto(){
    }
    public RecenzijaDto(Long Id, double ocena, String tekst, LocalDate datumRecenzije){
        this.Id = Id;
        this.ocena = ocena;
        this.tekst = tekst;
        this.datumRecenzije = datumRecenzije;
    }
    public RecenzijaDto(Recenzija recenzija){
        this.Id = recenzija.getId();
        this.ocena = recenzija.getOcena();
        this.tekst = recenzija.getTekst();
        this.datumRecenzije = recenzija.getDatumRecenzije();
        this.korisnik = recenzija.getKorisnik();
    }
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

    public LocalDate getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(LocalDate datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
