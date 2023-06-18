package com.example.demo.service;

import com.example.demo.dto.AzuriranjeProfilaDto;
import com.example.demo.entity.Autor;
import com.example.demo.entity.Korisnik;
import com.example.demo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public ResponseEntity<String> azuriraj(AzuriranjeProfilaDto adto, Long id){
        Autor autor = autorRepository.getAutorById(id);
        if(autor == null || autor.isAktivan() == true){
            return new ResponseEntity<>("Korisnik kog trazite je aktivan ili ne postoji", HttpStatus.FORBIDDEN);
        }
        autor.setOpis(adto.getOpis());
        autor.setIme(adto.getIme());
        autor.setPrezime(adto.getPrezime());
        autor.setDatumRodjenja(adto.getDatumRodjenja());
        autor.setSlika(adto.getSlika());
        autorRepository.save(autor);
        return new ResponseEntity<>("Profl je azuriran", HttpStatus.OK);

    }
    public Autor getAutorByEmail(String email){
        Optional<Autor> foundAutor = Optional.ofNullable(autorRepository.getAutorByMejlAdresa(email));
        if (foundAutor.isPresent())
            return foundAutor.get();

        return null;
    }

}
