package com.example.demo.controller;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Recenzija;
import com.example.demo.service.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.RecenzijaService;
import com.example.demo.dto.RecenzijaDto;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecenzijaRestController {

    @Autowired
    private RecenzijaService recenzijaService;
    @GetMapping("/api/recenzije")
    public ResponseEntity<List<RecenzijaDto>> getRecenzije(HttpSession session){
        List<Recenzija> recenzije = recenzijaService.findAll();

        Recenzija nadjiRecenzije = (Recenzija) session.getAttribute("recenzija");
        if(nadjiRecenzije == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(nadjiRecenzije);
        }

        List<RecenzijaDto> dtos = new ArrayList<>();
        for(Recenzija recenzija : recenzije){
            RecenzijaDto dto = new RecenzijaDto(recenzija);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    //ne radi
    @PostMapping("/api/dodaj-recenziju/{id}")
    public ResponseEntity<String> dodajRecenziju(@RequestBody RecenzijaDto recenzijaDto, @PathVariable Long id, HttpSession session){
        Recenzija recenzija = new Recenzija();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(loggedKorisnik != null){
            return recenzijaService.addRecenzija(recenzijaDto, loggedKorisnik, id);
        }
        return  new ResponseEntity<>("Niste ulogovani", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/api/recenzije/{id}")
    public Recenzija getRecenzija(@PathVariable Long id, HttpSession session){
        //Recenzija recenzija = (Recenzija) session.getAttribute("user");

        //session.invalidate();
        return recenzijaService.findRecenziju(id);
    }
}
