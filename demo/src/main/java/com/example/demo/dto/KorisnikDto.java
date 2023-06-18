package com.example.demo.dto;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;

public class KorisnikDto {
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String mejlAdresa;

    public KorisnikDto(Korisnik korisnik) {
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.mejlAdresa = korisnik.getMejlAdresa();
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getMejlAdresa() {
        return mejlAdresa;
    }

    public void setMejlAdresa(String mejlAdresa) {
        this.mejlAdresa = mejlAdresa;
    }
}
