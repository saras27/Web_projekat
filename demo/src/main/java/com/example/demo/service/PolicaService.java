package com.example.demo.service;


import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.entity.StavkaPolice;
import com.example.demo.repository.KnjigaRepository;
import com.example.demo.repository.PolicaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   /* public ResponseEntity<String> dodavanjeNaPolicu(String imeKnjige, Polica polica){
        Polica read = policaRepository.getPolicaByNaziv("Read");
        Polica reading = policaRepository.getPolicaByNaziv("Currently Reading");
        Polica wantToRead = policaRepository.getPolicaByNaziv("Want to Read");
        Knjiga novaKnjiga = knjigaRepository.getByNaslov(imeKnjige);
        if(stavkaPoliceRepository.existsByKnjigaContaining(novaKnjiga,  policaRepository.getPolicaByNaziv("Read")) || stavkaPoliceRepository.existsByKnjigaContaining(novaKnjiga, policaRepository.getPolicaByNaziv("Currently Reading")) || stavkaPoliceRepository.existsByKnjigaContaining(novaKnjiga,policaRepository.getPolicaByNaziv("Want to Read"))){
            if(polica.isPrimarna()){
                return new ResponseEntity("Ne mozete dodati knjigu na vise od jedne primarne police", HttpStatus.BAD_REQUEST);
            }else{
                StavkaPolice stavkaPolice = new StavkaPolice();
                stavkaPolice.setKnjiga(novaKnjiga);
                polica.setStavka(stavkaPolice);
                policaRepository.save(polica);
                return ResponseEntity.ok("Knjiga dodata na policu");
            }
        } if(polica.isPrimarna()){
            if(polica.equals(read)){
                //recenzija
                return ResponseEntity.ok("Knjiga dodata na policu");

            }else{
                StavkaPolice stavkaPolice = new StavkaPolice();
                stavkaPolice.setKnjiga(novaKnjiga);
                polica.setStavka(stavkaPolice);
                policaRepository.save(polica);
                return ResponseEntity.ok("Knjiga dodata na policu");
            }
        }
        return new ResponseEntity<>("Knjigu prvo morate dodati na neku od primarnih polica", HttpStatus.BAD_REQUEST);
    }*/

    public Polica getByNaziv(String ime) {
        return policaRepository.getPolicaByNaziv(ime);
    }

    public Polica getById(Long id){return policaRepository.getPolicaById(id);}
    public void delete(Polica polica){
        policaRepository.delete(polica);
    }
}
