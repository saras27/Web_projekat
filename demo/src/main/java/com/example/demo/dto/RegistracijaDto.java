package com.example.demo.dto;

public class RegistracijaDto {
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String mejl;
    private String lozinka;
    private String potvrda_lozinke;
    public RegistracijaDto(){}
    public RegistracijaDto(String ime, String prezime, String korisnickoIme, String mejl, String lozinka, String potvrda){
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.mejl = mejl;
        this.lozinka = lozinka;
        this.potvrda_lozinke = potvrda;
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

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPotvrda_lozinke() {
        return potvrda_lozinke;
    }

    public void setPotvrda_lozinke(String potvrda_lozinke) {
        this.potvrda_lozinke = potvrda_lozinke;
    }
}
