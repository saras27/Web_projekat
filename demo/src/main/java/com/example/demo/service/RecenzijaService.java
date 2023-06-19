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

import java.time.LocalDate;
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
    public Recenzija findRecenziju(Long id){
        Optional<Recenzija> dbRecenzija = recenzijaRepository.findById(id);
        if(!dbRecenzija.isPresent()){
            return null;
        }
        Recenzija recenzija = dbRecenzija.get();
        return recenzija;
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
        recenzija.setDatumRecenzije(LocalDate.now());
        recenzijaRepository.save(recenzija);
        recenzija.setKorisnik(korisnik);
        recenzija.setStavka(stavka);
        recenzijaRepository.save(recenzija);
        //stavka.setRecenzije(recenzija);
        //stavkaPoliceRepository.save(stavka);
        korisnikRepository.save(korisnik);
        return new ResponseEntity<>("Recenzija je dodata", HttpStatus.OK);
    }
    public Recenzija createModelFromDto(RecenzijaDto dto){
        Recenzija recenzija = new Recenzija();
        recenzija.setKorisnik(dto.getKorisnik());
        recenzija.setDatumRecenzije(dto.getDatumRecenzije());
        recenzija.setTekst(dto.getTekst());
        recenzija.setOcena(dto.getOcena());
        return recenzija;
    }

    public ResponseEntity<String> azurirajRecenziju(Recenzija azuriranaRecenzija, RecenzijaDto recenzijaDto){
        azuriranaRecenzija.setOcena(recenzijaDto.getOcena());
        azuriranaRecenzija.setTekst(recenzijaDto.getTekst());
        azuriranaRecenzija.setDatumRecenzije(LocalDate.now());
        recenzijaRepository.save(azuriranaRecenzija);
        return new ResponseEntity<>("Recenzija uspesno azurirana", HttpStatus.OK);
    }
}
