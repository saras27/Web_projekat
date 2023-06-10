package com.example.demo.dto;

import com.example.demo.entity.Knjiga;

public class AzuriranjeKnjigeDto {
    private Long Id;
    private String naslov;
    private String naslovnaFotografija;
    private String opis;

    public AzuriranjeKnjigeDto(){

    }
    public AzuriranjeKnjigeDto(Long id, String naslov, String naslovnaFotografija, String opis) {
        Id = id;
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.opis = opis;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
