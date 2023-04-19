package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column(name = "korisnicko_ime")
    private String korisnickoIme;
    @Column(name = "mejl_adresa")
    private String mejlAdresa;
    @Column
    private String lozinka;
    @Column(name = "datum_rodjenja")
    private String datumRodjenja;
    @Column
    private String opis;
    @Column
    private String uloga;

    public Long getId() {
        return Id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getMejlAdresa() {
        return mejlAdresa;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getOpis() {
        return opis;
    }

    public String getUloga() {
        return uloga;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setMejlAdresa(String mejlAdresa) {
        this.mejlAdresa = mejlAdresa;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "Id=" + Id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", mejlAdresa='" + mejlAdresa + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", datumRodjenja='" + datumRodjenja + '\'' +
                ", opis='" + opis + '\'' +
                ", uloga='" + uloga + '\'' +
                '}';
    }
}
