package com.example.demo.dto;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Recenzija;

import java.util.List;

public class KnjigaDetaljiDto {
    public Knjiga knjiga;
    public List<Recenzija> recenzije;

    public KnjigaDetaljiDto(){}

    public KnjigaDetaljiDto(Knjiga k, List<Recenzija> r) {
        knjiga = k;
        recenzije = r;
    }
}
