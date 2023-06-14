package com.example.demo.service;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.StavkaPolice;
import com.example.demo.repository.AutorRepository;
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
    private AutorRepository autorRepository;
    @Autowired
    private KnjigaService knjigaService;
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

    //radi
    public ResponseEntity<String> azuriranjeKnjige(Long id, AzuriranjeKnjigeDto azuriranjeKnjigeDto){
        Optional<Knjiga> dbKnjiga = knjigaRepository.findById(id);
        if(!dbKnjiga.isPresent()){
            return new ResponseEntity<>("Knjiga nije pronadjena", HttpStatus.NOT_FOUND);
        }
        Knjiga knjiga = dbKnjiga.get();
        knjiga.setNaslov(azuriranjeKnjigeDto.getNaslov());
        knjiga.setOpis(azuriranjeKnjigeDto.getOpis());
        knjiga.setNaslovnaFotografija(azuriranjeKnjigeDto.getNaslovnaFotografija());
        knjigaRepository.save(knjiga);
        return new ResponseEntity<>("Knjiga je azurirana", HttpStatus.OK);
    }

    public void dodavanjeKnjige(KnjigaDto novaKnjigaDto, Long id){
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(novaKnjigaDto.getNaslov());
        knjiga.setNaslovnaFotografija(novaKnjigaDto.getNaslovnaFotografija());
        knjiga.setISBN(novaKnjigaDto.getISBN());
        knjiga.setDatumObjavljivanja(novaKnjigaDto.getDatumObjavljivanja());
        knjiga.setBrojStrana(novaKnjigaDto.getBrojStrana());
        knjiga.setOpis(novaKnjigaDto.getOpis());
        knjiga.setZanr(novaKnjigaDto.getZanr());
        Autor autor = autorRepository.getAutorById(id);
        if(autor != null) {
            knjiga.setAutor(autor);
            knjigaService.save(knjiga);
            return;
        }
        knjiga.setAutor(novaKnjigaDto.getAutor());
        knjigaService.save(knjiga);
    }

    public ResponseEntity<String> brisanjeKnjigeAdmin(Long id){
        Knjiga knjiga = knjigaRepository.getById(id);

        //problem
        StavkaPolice stavka = stavkaPoliceRepository.existsByKnjiga_Id(id);
        if(recenzijaRepository.getRecenzijaByStavka(stavka) == null){
            return new ResponseEntity<>("Nemoguce je obrisati knjigu koja ima recenzije", HttpStatus.BAD_REQUEST);
        }
        remove(knjiga);
        return new ResponseEntity<>("Knjiga je obrisana", HttpStatus.OK);

    }
    
}
