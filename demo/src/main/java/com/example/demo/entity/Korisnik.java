package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column(name = "korisnicko_ime", unique = true)
    private String korisnickoIme;
    @Column(name = "mejl_adresa", unique = true)
    private String mejlAdresa;
    @Column
    private String lozinka;
    @Column(name = "datum_rodjenja")
    private LocalDate datumRodjenja;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id")
    private Set<Polica> police = new HashSet<>();
    //dodaj listu polica korisnika
    @Column(name = "slika")
    private String slika;
    //string slika

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private  Set<Recenzija> recenzije = new HashSet<>();

    @Column
    private String opis;
    @Column
    private Uloga uloga;

    public Long getId() {
        return id;
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

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getOpis() {
        return opis;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setId(Long id) {
        id = id;
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

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public Set<Polica> getPolice() {
        return police;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }


    @Override
    public String toString() {
        return "Korisnik{" +
                "Id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", datumRodjenja='" + datumRodjenja + '\'' +
                ", opis='" + opis + '\'' +
                ", uloga='" + uloga + '\'' +
                ", police= " + police +
                '}';
    }
}
