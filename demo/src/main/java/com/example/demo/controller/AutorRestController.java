package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.repository.KnjigaRepository;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.PolicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AutorRestController {
    @Autowired
    private PolicaService policaService;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private KnjigaRepository knjigaRepository;

    @PostMapping("/api/dodajPolicuAutor")
    public ResponseEntity<String> novaPolicaAutor(@RequestBody NovaPolicaDto novaPolicaDto, HttpServletRequest request/*HttpSession session*/){
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("korisnik") != null) {
            if (policaService.findOne(novaPolicaDto.getNaziv()) != null && novaPolicaDto.getNaziv() != " ") {
                System.out.println("Polica sa ovim imenom vec postoji ili ste uneli prazan string.");
            } else {
                // String korisnickoIme = (String) session.getAttribute("")
                Long id = (Long) session.getAttribute("id");
                Polica addedPolica = policaService.save(novaPolicaDto.getNaziv(), id);
                return ResponseEntity.ok("Polica uspesno dodata");
            }
            return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
        } else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/dodajKnjigu")
    public ResponseEntity<String> novaKnjiga(@RequestBody KnjigaDto novaKnjigaDto, HttpServletRequest request/*HttpSession session*/){
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("korisnik") != null) {
            if (knjigaService.findKnjigu(novaKnjigaDto.getNaslov()) != null && novaKnjigaDto.getNaslov() != " ") {
                System.out.println("Knjiga sa ovim imenom vec postoji ili ste uneli prazan string.");
            } else {
                // String korisnickoIme = (String) session.getAttribute("")
                Long id = (Long) session.getAttribute("id");
                Knjiga addedKnjiga = knjigaService.save(knjigaService.findKnjigu(novaKnjigaDto.getNaslov()));
                return ResponseEntity.ok("Knjiga uspesno dodata");
            }
            return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
        } else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/policeAutor")
    public ResponseEntity<List<PolicaDto>> getPoliceAutor(HttpSession session){
        List<Polica> police = policaService.findAll();

        Polica polica1 = (Polica) session.getAttribute("polica");
        if(polica1 == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(polica1);
        }

        List<PolicaDto> dtos = new ArrayList<>();
        for(Polica polica : police){
            PolicaDto dto = new PolicaDto(polica);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/check-user-login-autor")
    public boolean checkLoginAutor(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            // User is logged in
            return true;
        } else {
            // User is not logged in
            return false;
        }
    }
    @GetMapping("/check-login-autor")
    @ResponseBody
    public boolean checkLoginAutor(HttpSession session) {
        if (session.getAttribute("autor") != null) {
            // User is logged in
            return true;
        } else {
            // User is not logged in
            return false;
        }
    }
    @PostMapping("/api/azurirajKnjige/{id}")
    public ResponseEntity<String> azuriranjeKnjige(@PathVariable Long id, @RequestBody AzuriranjeKnjigeDto azuriranjeProfilaDto, HttpSession session){
        if(!checkLoginAutor(session)){
            return new ResponseEntity<>("Niste ulogovani.", HttpStatus.FORBIDDEN);
        }
        Knjiga knjiga = knjigaRepository.getById(id);

        if(knjiga == null){
            return new ResponseEntity<>("Knjiga nije pronadjena.", HttpStatus.NOT_FOUND);
        }


        knjiga.setNaslov(azuriranjeProfilaDto.getNaslov());
        knjiga.setNaslovnaFotografija(azuriranjeProfilaDto.getNaslovnaFotografija());
        knjiga.setOpis(azuriranjeProfilaDto.getOpis());


        Knjiga azuriranaKnjiga = knjigaService.azuriranjeKnjige(knjiga);
        return new ResponseEntity<>("Knjiga uspesno azurirana", HttpStatus.OK);

    }

}
