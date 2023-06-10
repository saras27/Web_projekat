package com.example.demo.dto;

import java.time.LocalDate;

public class AzuriranjeProfilaDto {
    private String ime;
    private String prezime;
    private LocalDate datumRodjenja;
    private String slika;
    private String opis;

    public AzuriranjeProfilaDto() {}

    public AzuriranjeProfilaDto(String ime, String prezime, LocalDate datumRodjenja, String slika, String opis) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.slika = slika;
        this.opis = opis;
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

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
