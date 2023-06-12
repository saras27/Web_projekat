package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Recenzija;
import com.example.demo.repository.KnjigaRepository;
import com.example.demo.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecenzijaService {
    @Autowired
    private RecenzijaRepository recenzijaRepository;


    public List<Recenzija> findAll(){
        return recenzijaRepository.findAll();
    }

    public Recenzija save(Recenzija recenzija){
        return recenzijaRepository.save(recenzija);
    }
}
