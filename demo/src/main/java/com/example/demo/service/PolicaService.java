package com.example.demo.service;


import com.example.demo.controller.RecenzijaRestController;
import com.example.demo.dto.RecenzijaDto;
import com.example.demo.entity.*;
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
    private AutorRepository autorRepository;
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    public Polica findOne(String imePolice){
        Optional<Polica> foundPolica = Optional.ofNullable(policaRepository.getPolicaByNaziv(imePolice));
        return foundPolica.orElse(null);

    }
    public ResponseEntity<String> obrisiIzBaze(Polica polica, Long userId){
        if(polica.isPrimarna())
            return new ResponseEntity<>("Nije mmoguce brisanje primarne police", HttpStatus.FORBIDDEN);
        else{
            policaRepository.delete(polica);
            Autor autor = autorRepository.getAutorById(userId);
            autorRepository.save(autor);
            return new ResponseEntity<>("Polica obrisana", HttpStatus.OK);

        }
    }


    public List<Polica> findAll(){return policaRepository.findAll();}
    //public List<Polica> findAllPoImenu(String ime){return policaRepository.find;}

    public ResponseEntity<String> save(String imePolice, Korisnik korisnik){
        List<Polica> dbpolica = policaRepository.findAllByNazivAndKorisnik(imePolice, korisnik);
        if(dbpolica != null && !dbpolica.isEmpty()){
            return new ResponseEntity<>("Dodavanje police nije moguce, postoji polica sa tim imenom", HttpStatus.BAD_REQUEST);
        }else {
            if(imePolice.equals("Read") || imePolice.equals("Want to Read") || imePolice.equals("Currently Reading")) {
                Polica polica1 = new Polica(imePolice, true);
                korisnik.getPolice().add(polica1);
                korisnikRepository.save(korisnik);
                policaRepository.save(polica1);
                return new ResponseEntity("Polica je uspesno dodata", HttpStatus.OK);
            }
            Polica polica1 = new Polica(imePolice, false);
            korisnik.getPolice().add(polica1);
            korisnikRepository.save(korisnik);
            //policaRepository.save(polica1);
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
    public ResponseEntity<String> dodavanjeNaPolicu(Knjiga knjiga, Polica polica, Long idKorisnik, Recenzija recenzija){
        Polica read = policaRepository.findPolicaByNazivAndKorisnik_Id("Read", idKorisnik);
        Polica reading = policaRepository.findPolicaByNazivAndKorisnik_Id("Currently Reading", idKorisnik);
        Polica wantToRead = policaRepository.findPolicaByNazivAndKorisnik_Id("Want to Read", idKorisnik);
        //Knjiga novaKnjiga = knjigaRepository.getByNaslov(imeKnjige);
        StavkaPolice stavkaPolice = new StavkaPolice();
        if(containsStavka(knjiga.getId(), read) || containsStavka(knjiga.getId(), reading) ||containsStavka(knjiga.getId(), wantToRead)){
            if(polica.isPrimarna()){
                return new ResponseEntity("Ne mozete dodati knjigu na vise od jedne primarne police", HttpStatus.BAD_REQUEST);
            } else{
                stavkaPolice.setKnjiga(knjiga);
                polica.setStavka(stavkaPolice);
                policaRepository.save(polica);
                return ResponseEntity.ok("Knjiga dodata na policu");
            }
        } else if(polica.isPrimarna()){
            if(polica.equals(read)){
                stavkaPolice.setKnjiga(knjiga);
                if(recenzija != null){
                    stavkaPolice.setRecenzije(recenzija);
                }
                polica.setStavka(stavkaPolice);
                policaRepository.save(polica);
                return ResponseEntity.ok("Knjiga dodata na policu");

            } else{
                stavkaPolice.setKnjiga(knjiga);
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
