package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.StavkaPolice;
import com.example.demo.repository.KnjigaRepository;
import com.example.demo.repository.RecenzijaRepository;
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
    private RecenzijaRepository recenzijaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    public Knjiga findKnjigu(String naslov){
        Optional<Knjiga> foundKnjiga = Optional.ofNullable(knjigaRepository.getByNaslov(naslov));
        if (foundKnjiga.isPresent())
            return foundKnjiga.get();

        return null;
    }
    public void remove(Knjiga knjiga){knjigaRepository.delete(knjiga);}

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

    public ResponseEntity<String> brisanjeKnjigeAdmin(Long id){
        Knjiga knjiga = knjigaRepository.getById(id);
        StavkaPolice stavka = stavkaPoliceRepository.existsByKnjiga_Id(id);
        if(recenzijaRepository.getRecenzijaByStavka(stavka) == null){
            return new ResponseEntity<>("Nemoguce je obrisati knjigu koja ima recenzije", HttpStatus.BAD_REQUEST);
        }
        remove(knjiga);
        return new ResponseEntity<>("Knjiga je obrisana", HttpStatus.OK);

    }
    
}
