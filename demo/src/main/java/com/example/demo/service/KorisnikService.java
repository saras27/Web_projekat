package com.example.demo.service;

import com.example.demo.dto.AktivacijaAutoraDto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Polica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Korisnik;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.PolicaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PolicaService policaService;
    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private KnjigaService knjigaService;

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
    public Polica obrisiPolicu(Long id, String naziv){
        if(naziv == "Read" || naziv == "Want to Read" || naziv == "Currently Reading")
            return null;    //ne moze da obrise primarne police
        Polica polica = policaService.findOne(naziv);

        if(polica == null)
            return null;
        else{
            policaRepository.delete(polica);
            return polica;
        }
    }
    public Korisnik registruj(String ime, String prezime, String korisnickoIme, String mejl, String lozinka){
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(korisnik == null){  //ako ne postoji korisnik sa tim korisnickim imenom, uspesno se registruje
            policaService.save("Read", korisnik.getId());
            policaService.save("Currently Reading", korisnik.getId());
            policaService.save("Want to Read", korisnik.getId());
            return korisnikRepository.save(korisnik);
        }
        else
            return null;
    }

    public Korisnik azuriranjeProfila(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public Korisnik getById(Long id) {
        return korisnikRepository.getKorisnikById(id);
    }

    public Korisnik getByKorisnickoIme(String ime) {
        return korisnikRepository.getByKorisnickoIme(ime);
    }
}
