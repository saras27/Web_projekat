package com.example.demo.dto;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.StavkaPolice;
import com.example.demo.entity.Polica;
import java.util.Set;

public class PolicaDto {
    private Long id;
    private String naziv;
    private boolean primarna;
    private Korisnik korisnik;
    private Set<StavkaPolice> stavke;

    public PolicaDto(){

    }

    public PolicaDto(String naziv) {
        this.naziv = naziv;
        this.primarna = false;
    }

    public PolicaDto(Polica polica) {
        this.id = polica.getId();
        this.naziv = polica.getNaziv();
        this.primarna = polica.isPrimarna();
        this.korisnik = polica.getKorisnik();
        this.stavke = polica.getStavke();
    }

    public PolicaDto(Long id, String naziv, boolean primarna, Korisnik korisnik, Set<StavkaPolice> stavke) {
        this.id = id;
        this.naziv = naziv;
        this.primarna = primarna;
        this.korisnik = korisnik;
        this.stavke = stavke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Set<StavkaPolice> getStavke() {
        return stavke;
    }

    public void setStavke(Set<StavkaPolice> stavke) {
        this.stavke = stavke;
    }
}
