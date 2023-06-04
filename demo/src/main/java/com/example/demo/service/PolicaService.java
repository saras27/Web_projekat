package com.example.demo.service;


import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Polica;
import com.example.demo.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;

    public Polica findOne(String imePolice){
        Optional<Polica> foundPolica = Optional.ofNullable(policaRepository.getPolicaByNaziv(imePolice));
        return foundPolica.orElse(null);

    }

    public List<Polica> findAll(){return policaRepository.findAll();}

    public Polica save(String imePolice){
        Polica polica = policaRepository.getPolicaByNaziv(imePolice);
        if(findOne(imePolice) != null){
            return polica;
        }else {

             return policaRepository.save(imePolice);
        }
    }
}
