package com.example.demo.service;

import com.example.demo.dto.NoviZanrDto;
import com.example.demo.entity.Recenzija;
import com.example.demo.entity.Zanr;
import com.example.demo.repository.RecenzijaRepository;
import com.example.demo.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> dodavanjeZanra(NoviZanrDto zanr){
        Zanr zanr1 = new Zanr();
        zanr1.setNaziv(zanr.getNaziv());
        if(zanrRepository.existsByNaziv(zanr1.getNaziv()))
            return new ResponseEntity<>("Zanr sa ovim imenom  vec postoji", HttpStatus.BAD_REQUEST);

        save(zanr1);
        return new ResponseEntity<>("Zanr uspesno dodat", HttpStatus.OK);
    }
}
