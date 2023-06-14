package com.example.demo.service;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.dto.RecenzijaDto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Recenzija;
import com.example.demo.entity.StavkaPolice;
import com.example.demo.repository.KnjigaRepository;
import com.example.demo.repository.RecenzijaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecenzijaService {
    @Autowired
    private RecenzijaRepository recenzijaRepository;
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<Recenzija> findAll(){
        return recenzijaRepository.findAll();
    }

    public Recenzija save(Recenzija recenzija){
        return recenzijaRepository.save(recenzija);
    }

    public ResponseEntity<String> addRecenzija(RecenzijaDto recenzijaDto, Korisnik korisnik, Long id){
        Knjiga knjiga = knjigaRepository.getById(id);
        if(knjiga == null){
            return new ResponseEntity<>("Knjiga ne postoji", HttpStatus.NOT_FOUND);
        }
        //problem negde ovde
        StavkaPolice stavka = stavkaPoliceRepository.getStavkaPoliceByKnjiga(knjiga);
        Recenzija recenzija = new Recenzija();
        recenzija.setOcena(recenzijaDto.getOcena());
        recenzija.setTekst(recenzijaDto.getTekst());
        recenzija.setDatumRecenzije(recenzijaDto.getDatumRecenzije());
        recenzija.setKorisnik(korisnik);
        save(recenzija);
        stavka.setRecenzije(recenzija);
        stavkaPoliceRepository.save(stavka);
        korisnikRepository.save(korisnik);
        return new ResponseEntity<>("Recenzija je dodata", HttpStatus.OK);
    }
}
