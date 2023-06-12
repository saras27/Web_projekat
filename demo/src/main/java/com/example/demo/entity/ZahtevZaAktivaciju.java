package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ZahtevZaAktivaciju implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String telefon;
    @Column
    private String poruka;
    @Column
    private LocalDate datum;
    @Column
    private Status status;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Autor autor;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getPoruka() {
        return poruka;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Zahtev{" +
                "Id=" + id +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", poruka='" + poruka + '\'' +
                ", datum='" + datum + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
