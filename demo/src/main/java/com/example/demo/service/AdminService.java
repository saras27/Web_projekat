package com.example.demo.service;

import com.example.demo.dto.AktivacijaAutoraDto;
import com.example.demo.dto.AutorDto;
import com.example.demo.dto.AzuriranjeProfilaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.ZahtevZaAktivacijuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private ZahtevZaAktivacijuRepository zahtevZaAktivacijuRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private AutorRepository autorRepository;

    public List<ZahtevZaAktivaciju> findAll(){return zahtevZaAktivacijuRepository.findAll();}

    public void save(ZahtevZaAktivaciju zahtev){
        zahtevZaAktivacijuRepository.save(zahtev);
        //ako se zahtev ne odnosi na autora, nece se sacuvati
    }

    public ResponseEntity<String> odgovor(boolean odgovor, ZahtevZaAktivaciju zahtev, Autor autor){
        if(odgovor == false){
            zahtev.setStatus(Status.ODBIJEN);
            zahtevZaAktivacijuRepository.save(zahtev);
            return new ResponseEntity<>("Bice Vam poslat mejl, zahtev odbijen", HttpStatus.BAD_REQUEST);
        }
        autor.setAktivan(true);
        zahtev.setStatus(Status.ODOBREN);
        autorRepository.save(autor);
        zahtevZaAktivacijuRepository.save(zahtev);
        return new ResponseEntity<>("Profil autora je aktiviran", HttpStatus.OK);
    }
    public ResponseEntity<String> dodajAutora(AutorDto autorDto){
        Autor noviAutor = new Autor();
        noviAutor.setAktivan(false);
        noviAutor.setUloga(Uloga.AUTOR);
        noviAutor.setSlika(autorDto.getSlika());
        noviAutor.setDatumRodjenja(autorDto.getDatumRodjenja());
        noviAutor.setOpis(autorDto.getOpis());
        noviAutor.setPrezime(autorDto.getPrezime());
        noviAutor.setIme(autorDto.getIme());
        autorRepository.save(noviAutor);
        return ResponseEntity.ok("Autor dodat u bazu.");
    }
    public ResponseEntity<String> azuriraj(Autor autor, AzuriranjeProfilaDto azuriranjeProfilaDto){
        autor.setIme(azuriranjeProfilaDto.getIme());
        autor.setPrezime(azuriranjeProfilaDto.getPrezime());
        autor.setDatumRodjenja(azuriranjeProfilaDto.getDatumRodjenja());
        autor.setSlika(azuriranjeProfilaDto.getSlika());
        autor.setOpis(azuriranjeProfilaDto.getOpis());
        autorRepository.save(autor);
        return new ResponseEntity<>("Uspesno azuriran profil", HttpStatus.OK);
    }

}
