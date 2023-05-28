package com.example.demo.service;

import com.example.demo.entity.Recenzija;
import com.example.demo.entity.Zanr;
import com.example.demo.repository.RecenzijaRepository;
import com.example.demo.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZanrService {
    @Autowired
    private ZanrRepository zanrRepository;



    public List<Zanr> findAll(){
        return zanrRepository.findAll();
    }

    public Zanr save(Zanr zanr){
        return zanrRepository.save(zanr);
    }
}
