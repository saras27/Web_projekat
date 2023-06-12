package com.example.demo.service;


import com.example.demo.controller.RecenzijaRestController;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.entity.StavkaPolice;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    public Polica findOne(String imePolice){
        Optional<Polica> foundPolica = Optional.ofNullable(policaRepository.getPolicaByNaziv(imePolice));
        return foundPolica.orElse(null);

    }
    public boolean obrisiIzBaze(String naziv){
        Polica polica = policaRepository.getPolicaByNaziv(naziv);

        if(polica == null)
            return true;
        else return false;
    }

    public List<Polica> findAll(){return policaRepository.findAll();}

    public ResponseEntity<String> save(String imePolice, Korisnik korisnik){
        Polica polica = policaRepository.getPolicaByNaziv(imePolice);
        if(findOne(imePolice) != null){
            return new ResponseEntity<>("Dodavanje police nije moguce, postoji polica sa tim imenom", HttpStatus.BAD_REQUEST);
        }else {
            Polica polica1 = new Polica(imePolice, false);
            korisnik.getPolice().add(polica1);
            korisnikRepository.save(korisnik);
            policaRepository.save(polica1);
            return new ResponseEntity("Polica je uspesno dodata", HttpStatus.OK);


        }
    }

    public boolean containsStavka(Long id, Polica polica){
        for(StavkaPolice stavkaPolice1 : polica.getStavke()){
            if(stavkaPolice1.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public ResponseEntity<String> dodavanjeNaPolicu(String imeKnjige, Polica polica, Long id){
        Polica read = policaRepository.findPolicaByNazivAndKorisnik_Id("Read", id);
        Polica reading = policaRepository.findPolicaByNazivAndKorisnik_Id("Currently Reading", id);
        Polica wantToRead = policaRepository.findPolicaByNazivAndKorisnik_Id("Want to Read", id);
        Knjiga novaKnjiga = knjigaRepository.getByNaslov(imeKnjige);
        StavkaPolice stavkaPolice = stavkaPoliceRepository.getStavkaPoliceByKnjiga(novaKnjiga);
        if(containsStavka(novaKnjiga.getId(), read) || containsStavka(novaKnjiga.getId(), reading) ||containsStavka(novaKnjiga.getId(), wantToRead)){
            if(polica.isPrimarna()){
                return new ResponseEntity("Ne mozete dodati knjigu na vise od jedne primarne police", HttpStatus.BAD_REQUEST);
            }else{
                stavkaPolice.setKnjiga(novaKnjiga);
                polica.setStavka(stavkaPolice);
                policaRepository.save(polica);
                return ResponseEntity.ok("Knjiga dodata na policu");
            }
        } if(polica.isPrimarna()){
            if(polica.equals(read)){
                //recenzija dijalog

                return ResponseEntity.ok("Knjiga dodata na policu");

            }else{
                stavkaPolice.setKnjiga(novaKnjiga);
                polica.setStavka(stavkaPolice);
                policaRepository.save(polica);
                return ResponseEntity.ok("Knjiga dodata na policu");
            }
        }
        return new ResponseEntity<>("Knjigu prvo morate dodati na neku od primarnih polica", HttpStatus.BAD_REQUEST);
    }

    public Polica getByNaziv(String ime) {
        return policaRepository.getPolicaByNaziv(ime);
    }

    public Polica getById(Long id){return policaRepository.getPolicaById(id);}
    public void delete(Polica polica){
        policaRepository.delete(polica);
    }
}
