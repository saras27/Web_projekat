package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private AutorService autorService;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ZanrService zanrService;
    @GetMapping("/api/zahtevi")
    ResponseEntity<?> listaZahteva(HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null){
            System.out.println("Nema sesije");
            return ResponseEntity.badRequest().build();
        }

            if(korisnik.getUloga() == Uloga.ADMINISTRATOR){
            List<ZahtevZaAktivaciju> sviZahtevi = adminService.findAll();
            List<AktivacijaAutoraDto> naCekanju = null;
            for (ZahtevZaAktivaciju z : sviZahtevi) {
                if(z.getStatus() == Status.CEKANJE) {
                    AktivacijaAutoraDto zahtev = new AktivacijaAutoraDto();
                    naCekanju.add(zahtev);
                }
            }
            return ResponseEntity.ok(naCekanju);
            }
        System.out.println("Samo administratori imaju pristup zahtevima");
        return null;
    }

    @PostMapping("/api/posalji-zahtev")
    public ResponseEntity<String> posaljiZahtev(@RequestBody AktivacijaAutoraDto aktivacijaAutoraDto, HttpSession session){
        if(checkLogin(session)){
            return new ResponseEntity<>("Ne mozete podneti zahtev ukoliko ste ulogovani", HttpStatus.FORBIDDEN);
        }else{
            ZahtevZaAktivaciju zahtevZaAktivaciju = new ZahtevZaAktivaciju();
            zahtevZaAktivaciju.setEmail(aktivacijaAutoraDto.getEmail());
            zahtevZaAktivaciju.setPoruka(aktivacijaAutoraDto.getPoruka());
            zahtevZaAktivaciju.setTelefon(aktivacijaAutoraDto.getTelefon());
            zahtevZaAktivaciju.setDatum(LocalDate.now());
            zahtevZaAktivaciju.setStatus(Status.CEKANJE);
            adminService.save(zahtevZaAktivaciju);
            return new ResponseEntity<>("Zahtev je podnesen", HttpStatus.OK);
        }

    }

    @PostMapping("/api/{id}")
    public ResponseEntity<String> odgovorNaZahtev(@PathVariable Long id,@RequestBody OdgovorNaZahtevDto odgovor, HttpSession session){
        if (!checkLogin(session)) {
            Uloga uloga = (Uloga) session.getAttribute("uloga");
            if(uloga == Uloga.ADMINISTRATOR){
                return adminService.odgovor(odgovor.isPrihvacen(),id);
            }
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
    }

    //radi
    @PostMapping("/api/dodajZanr")
    public ResponseEntity<String> dodavanjeZanra(@RequestBody NoviZanrDto zanrDto, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik.getUloga() == Uloga.ADMINISTRATOR) {

             return zanrService.dodavanjeZanra(zanrDto);

            }else
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/api/dodajKnjigu-admin")
    public ResponseEntity<String> dodavanjeKnjiga(@RequestBody KnjigaDto novaKnjigaDto, HttpSession session){
        if(checkLogin(session)) {
            Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
            if(loggedKorisnik.getUloga() == Uloga.ADMINISTRATOR){
                if (knjigaService.findKnjigu(novaKnjigaDto.getNaslov()) != null && novaKnjigaDto.getNaslov() != " ") {
                    System.out.println("Knjiga sa ovim imenom vec postoji ili ste uneli prazan string.");
                } else {
                    knjigaService.dodavanjeKnjige(novaKnjigaDto, loggedKorisnik.getId());
                    return ResponseEntity.ok("Knjiga uspesno dodata");
                }
                return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
            }return new ResponseEntity<>("Samo admin ima pristup", HttpStatus.UNAUTHORIZED);
        } else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }

    //radi
    @PostMapping("/api/azuriranjeKnjige-admin/{id}")
    public ResponseEntity<String> azuriranjeKnjiga(@RequestBody AzuriranjeKnjigeDto azuriranjeKnjigeDto, @PathVariable Long id, HttpSession session) {
        if (checkLogin(session)) {
            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
            if (korisnik.getUloga() == Uloga.ADMINISTRATOR) {
                knjigaService.azuriranjeKnjige(id, azuriranjeKnjigeDto);
                return new ResponseEntity<>("Azurirana", HttpStatus.OK);
            }
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/api/brisanjeKnjige-admin/{id}")
    public ResponseEntity<String> brisanjeKnjiga(@PathVariable Long id, HttpSession session) {
        if (checkLogin(session)) {
           Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
            if (korisnik.getUloga() == Uloga.ADMINISTRATOR) {
                knjigaService.brisanjeKnjigeAdmin(id);
                return new ResponseEntity<>("Azurirana", HttpStatus.OK);
            }
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

    }

    //prosledi id autora da bi se njegov profil azurirao

    @PostMapping("/api/azurirajProfil-admin/{id}")
    public ResponseEntity<String> azuriranjeProfila(@PathVariable Long id,@RequestBody AzuriranjeProfilaDto azuriranjeProfilaDto,HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        Long userId = null;
        if(korisnik == null){
            return new ResponseEntity<>("Nema sesije, potrebno logovanje", HttpStatus.UNAUTHORIZED);
        }
        if(korisnik.getUloga() == Uloga.AUTOR || korisnik.getUloga() == Uloga.ADMINISTRATOR){
            return autorService.azuriraj(azuriranjeProfilaDto, id);
        }
        return ResponseEntity.badRequest().body("Doslo je do greske, niste autor niti administrator");

    }

    //valjda ga doda
    @PostMapping("/api/dodaj-autora")
    public ResponseEntity<String> kreiranjeAutora(@RequestBody AutorDto autorDto, HttpSession session){
        if (checkLogin(session)) {
            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
            if (korisnik.getUloga() == Uloga.ADMINISTRATOR) {
                adminService.dodajAutora(autorDto);
                return ResponseEntity.ok("Autor dodat u bazu.");
            }
            return ResponseEntity.badRequest().body("Niste administrator");
        }
        return ResponseEntity.badRequest().body("Niste ulogovani");
    }

    @GetMapping("/api/check-login-admin")
    @ResponseBody
    public boolean checkLogin(HttpSession session) {
        if (session.getAttribute("korisnik") != null) {
            return true;
        } else {
            return false;
        }
    }
}
