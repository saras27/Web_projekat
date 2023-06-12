package com.example.demo.controller;

import com.example.demo.dto.NovaKnjigaDto;
import com.example.demo.dto.NovaPolicaDto;
import com.example.demo.dto.PolicaDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.service.PolicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.dto.RegistracijaDto;
import com.example.demo.entity.Knjiga;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpSession;
import java.security.spec.ECPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class PolicaRestController {
    @Autowired
    private PolicaService policaService;

    @PostMapping("/api/dodajPolicu")
    public ResponseEntity<String> novaPolica(@RequestBody NovaPolicaDto novaPolicaDto, HttpServletRequest request,HttpSession session){
        if(checkLogin(request)) {
            if (policaService.findOne(novaPolicaDto.getNaziv()) != null && novaPolicaDto.getNaziv() != " ") {
                System.out.println("Polica sa ovim imenom vec postoji ili ste uneli prazan string.");
            } else {
                Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
                return policaService.save(novaPolicaDto.getNaziv(), korisnik);
            }
            return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
        } else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/police")
    public ResponseEntity<List<PolicaDto>> getPolice(HttpSession session){
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

    @GetMapping("/check-user-login")
    public boolean checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            // User is logged in
            return true;
        } else {
            // User is not logged in
            return false;
        }
    }

    @PostMapping("/api/dodaj-knjigu{imePolice}")
    public ResponseEntity<String> dodajKnjigu(@RequestBody NovaKnjigaDto novaknjigaDto,@PathVariable String naziv, HttpServletRequest req, HttpSession session){
        if(checkLogin(req)) {
                if (policaService.findOne(novaknjigaDto.getNaslov()) != null && novaknjigaDto.getNaslov() != " ") {
                    Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
                    Set<Polica>police = korisnik.getPolice();
                    for(Polica polica1 : police){
                        if (polica1.getNaziv().equals(naziv)) {
                            return policaService.dodavanjeNaPolicu(novaknjigaDto.getNaslov(),polica1, korisnik.getId());
                        }
                    }
                    return new ResponseEntity<>("Polica na koju zelite da dodate knjigu ne postoji", HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>("Knjiga sa ovim naslovom ne postoji u bazi ili ste uneli prazan string", HttpStatus.NOT_FOUND);
                }
        }else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }




}