package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Korisnik;
import com.example.demo.repository.KorisnikRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik findOne(Long id){
        Optional<Korisnik> foundKorisnik = korisnikRepository.findById(id);
        if (foundKorisnik.isPresent())
            return foundKorisnik.get();

        return null;
    }
    public Korisnik nadjiKorisnik(String KorisnickoIme){
        Optional<Korisnik> foundKorisnik = Optional.ofNullable(korisnikRepository.getByKorisnickoIme(KorisnickoIme));
        if (foundKorisnik.isPresent())
            return foundKorisnik.get();

        return null;
    }

    public List<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public Korisnik login(String korisnickoIme) {
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(korisnik == null)
            return null;
        return  korisnik;
    }
    public Korisnik registruj(String ime, String prezime, String korisnickoIme, String mejl, String lozinka){
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(korisnik != null){  //ako ne postoji korisnik sa tim korisnickim imenom, uspesno se registruje
            return korisnikRepository.save(korisnik);
        }
        else
            return null;
    }
}
