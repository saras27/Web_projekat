package com.example.demo.dto;

import com.example.demo.entity.Zanr;

public class ZanrDto {
    private Long Id;
    private String naziv;
    public ZanrDto(){}
    public ZanrDto(Long Id, String naziv){
        this.Id = Id;
        this.naziv = naziv;
    }
    public ZanrDto(Zanr z){
        this.Id = z.getId();
        this.naziv = z.getNaziv();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
