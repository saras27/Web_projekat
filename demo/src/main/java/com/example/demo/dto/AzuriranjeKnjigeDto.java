package com.example.demo.dto;

import com.example.demo.entity.Knjiga;

public class AzuriranjeKnjigeDto {
    private Long id;
    private String naslov;
    private String naslovnaFotografija;
    private String opis;

    public AzuriranjeKnjigeDto(){

    }
    public AzuriranjeKnjigeDto(Long id, String naslov, String naslovnaFotografija, String opis) {
        id = id;
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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
