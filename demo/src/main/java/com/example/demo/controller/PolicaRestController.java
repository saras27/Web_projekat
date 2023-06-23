package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.PolicaRepository;
import com.example.demo.repository.RecenzijaRepository;
import com.example.demo.repository.StavkaPoliceRepository;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.RecenzijaService;
import com.example.demo.service.PolicaService;
import com.example.demo.service.SessionService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaRepository;

    @Autowired
    private SessionService sessionService;

    //drugi put kad se napravi polica sa istim imenom ne radi, prvi put radi
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

    //radi
    @GetMapping("/api/police")
    public ResponseEntity<List<PolicaDto>> getPolice(){
        HttpSession session = sessionService.getSession();
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(korisnik);
        }

        List<Polica> police = policaService.getKorisnikovePolice(korisnik.getId());
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

        if (session != null && session.getAttribute("korisnik") != null) {
            // User is logged in
            return true;
        } else {
            // User is not logged in
            return false;
        }
    }

    @PostMapping("/api/dodaj-knjigu/{idPolice}")
    public ResponseEntity<String> dodajKnjigu(@RequestBody DodajKnjiguRequestDto requestDto, @PathVariable Long idPolice, HttpServletRequest req, HttpSession session){
        if(checkLogin(req)) {
            if (knjigaService.findKnjigu(requestDto.knjigaDto.getNaslov()) != null && requestDto.knjigaDto.getNaslov() != "") {
                Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
                Polica polica = policaService.getById(idPolice);
                if(polica == null || polica.getKorisnik().getId() != korisnik.getId()) {
                    return new ResponseEntity<>("Polica na koju zelite da dodate knjigu ne postoji", HttpStatus.NOT_FOUND);
                }else{
                    Knjiga knjiga = knjigaService.findKnjigu(requestDto.knjigaDto.getNaslov());
                    Recenzija recenzija;
                    if(requestDto.recenzijaDto == null){
                        recenzija = null;
                    }else{
                        recenzija = recenzijaService.createModelFromDto(requestDto.recenzijaDto);
                    }
                    return policaService.dodavanjeNaPolicu(knjiga, polica, korisnik.getId(), recenzija);
                }
            } else {
                return new ResponseEntity<>("Knjiga sa ovim naslovom ne postoji u bazi ili ste uneli prazan string", HttpStatus.NOT_FOUND);
            }
        }else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/polica/{idPolice}/{idKnjige}")
    public ResponseEntity<String>obrisiKnjiguSaPolice(@PathVariable Long idPolice, @PathVariable Long idKnjige, HttpSession session, HttpServletRequest req){
        session = sessionService.getSession();
        if(session == null) {
            return  new ResponseEntity("Nema sesije", HttpStatus.BAD_REQUEST);
        }
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null) {
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        Set<Polica> korisnikPolice = korisnik.getPolice();
        if(korisnikPolice == null) {
            return  new ResponseEntity("Nema polica", HttpStatus.BAD_REQUEST);
        }
        Polica police = korisnikPolice.stream().filter(p -> p.getId() == idPolice).findFirst().orElse(null);
        if(police != null && police.getStavke() != null){
            StavkaPolice stavka = police.getStavke().stream().filter(s->s.getKnjiga().getId() == idKnjige).findFirst().orElse(null);
            if(stavka != null){
                police.getStavke().remove(stavka);
                policaRepository.save(police);
                stavkaRepository.delete(stavka);
               // policaRepository.save(police);
                return  new ResponseEntity("Uspesno obrisana", HttpStatus.OK);
            }
        }
        return  new ResponseEntity("Nema knjige na polici", HttpStatus.BAD_REQUEST);
//        if(checkLogin(req)) {
//            Polica police = policaService.getById(idPolice);
//            if(police == null){
//                return  new ResponseEntity("Ne postoji polica", HttpStatus.BAD_REQUEST);
//            }
//            if(police.getStavke() != null){
//                StavkaPolice stavka = police.getStavke().stream().filter(s->s.getKnjiga().getId() == idKnjige).findFirst().orElse(null);
//                if(stavka != null){
//                    police.getStavke().remove(stavka);
//                    policaRepository.save(police);
//                    return  new ResponseEntity("Uspesno obrisana", HttpStatus.OK);
//                }
//            }
//            return  new ResponseEntity("Nema knjige na polici", HttpStatus.BAD_REQUEST);
//
//        }
//        return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
    }

    //@PostMapping




}