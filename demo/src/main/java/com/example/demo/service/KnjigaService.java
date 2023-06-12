package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.repository.KnjigaRepository;
import com.example.demo.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.dto.*;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    public Knjiga findKnjigu(String naslov){
        Optional<Knjiga> foundKnjiga = Optional.ofNullable(knjigaRepository.getByNaslov(naslov));
        if (foundKnjiga.isPresent())
            return foundKnjiga.get();

        return null;
    }

    public List<Knjiga> findAll(){
        return knjigaRepository.findAll();
    }

    public Knjiga save(Knjiga knjiga){
        return knjigaRepository.save(knjiga);
    }
    public ResponseEntity<String> azuriranjeKnjige(Long id, AzuriranjeKnjigeDto azuriranjeKnjigeDto){
        Knjiga knjiga = new Knjiga();
        if(knjigaRepository.findById(id) == null){
            return new ResponseEntity<>("Knjiga nije pronadjena", HttpStatus.NOT_FOUND);
        }
        knjiga.setNaslov(azuriranjeKnjigeDto.getNaslov());
        knjiga.setOpis(azuriranjeKnjigeDto.getOpis());
        knjiga.setNaslovnaFotografija(azuriranjeKnjigeDto.getNaslovnaFotografija());
        knjigaRepository.save(knjiga);
        return new ResponseEntity<>("Knjiga je azurirana", HttpStatus.OK);
    }

    /*public Knjiga login(String username, String password) {
        Knjiga employee = knjigaRepository.getByNaslov(username);
        if(employee == null || !employee.getPassword().equals(password))
            return null;
        return  employee;
    }*/
}
