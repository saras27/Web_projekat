package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;

    public Polica findOne(String imePolice){
        Optional<Polica> foundPolica = Optional.ofNullable(policaRepository.getPolicaByNaziv(imePolice));
        if (foundPolica.isPresent())
            return foundPolica.get();

        return null;
    }

    public Polica addPolica(String imePolice){
        Polica polica = policaRepository.getPolicaByNaziv(imePolice);
        if(polica != null){
            return polica;
        }else
            return null;
    }
}
