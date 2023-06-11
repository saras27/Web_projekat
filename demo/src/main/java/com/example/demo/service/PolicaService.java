package com.example.demo.service;


import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.repository.PolicaRepository;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

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

    public Polica save(String imePolice, Long id){
        Polica polica = policaRepository.getPolicaByNaziv(imePolice);
        Korisnik korisnik = korisnikRepository.getKorisnikById(id);
        if(findOne(imePolice) != null){
            return null;
        }else {
            if(imePolice.equals("Read") || imePolice.equals("Currently Reading") || imePolice.equals("Want to Read")){
                Polica polica1 = new Polica(imePolice, true);
                korisnik.getPolice().add(polica1);
                korisnikRepository.save(korisnik);
                return policaRepository.save(polica1);
            }
            Polica polica1 = new Polica(imePolice, false);
            korisnik.getPolice().add(polica1);
            korisnikRepository.save(korisnik);
            return policaRepository.save(polica1);

        }
    }


    public Polica getByNaziv(String ime) {
        return policaRepository.getPolicaByNaziv(ime);
    }

    public void delete(Polica polica){
        policaRepository.delete(polica);
    }
}
