package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.AdminService;
import com.example.demo.service.ZanrService;
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
    private KnjigaService knjigaService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ZanrService zanrService;
    @GetMapping("/api/zahtevi")
    ResponseEntity<List<AktivacijaAutoraDto>> listaZahteva(HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null){
            System.out.println("Nema sesije");
            return ResponseEntity.badRequest().build();
        }

            if(korisnik.getUloga() == Uloga.ADMINISTRATOR){
            List<ZahtevZaAktivaciju> sviZahtevi = adminService.findAll();
            List<AktivacijaAutoraDto> naCekanju = null;
            for (ZahtevZaAktivaciju z : sviZahtevi) {
                //    if(z.getStatus() == Status.CEKANJE){
                AktivacijaAutoraDto zahtev = new AktivacijaAutoraDto();
                naCekanju.add(zahtev);
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
    @PostMapping("/api/dodajZanr")
    public ResponseEntity<String> dodavanjeZanra(@RequestBody NoviZanrDto zanrDto, HttpSession session){
        if (checkLogin(session)) {
            Uloga uloga = (Uloga) session.getAttribute("uloga");
            if(uloga == Uloga.ADMINISTRATOR){
                Zanr noviZanr = new Zanr();
                noviZanr.setNaziv(zanrDto.getNaziv());
                return zanrService.dodavanjeZanra(noviZanr);
            }
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/api/dodajKnjigu-admin")
    public ResponseEntity<String> dodavanjeKnjiga(@RequestBody NovaKnjigaDto novaKnjigaDto, HttpSession session){
        if(checkLogin(session)) {
            if (knjigaService.findKnjigu(novaKnjigaDto.getNaslov()) != null && novaKnjigaDto.getNaslov() != " ") {
                System.out.println("Knjiga sa ovim imenom vec postoji ili ste uneli prazan string.");
            } else {
                Knjiga knjiga = new Knjiga();
                knjiga.setNaslov(novaKnjigaDto.getNaslov());
                knjiga.setNaslovnaFotografija(novaKnjigaDto.getNaslovnaFotografija());
                knjiga.setISBN(novaKnjigaDto.getISBN());
                knjiga.setDatumObjavljivanja(novaKnjigaDto.getDatumObjavljivanja());
                knjiga.setBrojStrana(novaKnjigaDto.getBrojStrana());
                knjiga.setOpis(novaKnjigaDto.getOpis());
                knjiga.setZanr(novaKnjigaDto.getZanr());
                knjiga.setAutor(novaKnjigaDto.getAutor());
                knjigaService.save(knjiga);
                return ResponseEntity.ok("Knjiga uspesno dodata");
            }
            return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
        } else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/azuriranjeKnjige-admin/{id}")
    public ResponseEntity<String> azuriranjeKnjiga(@RequestBody AzuriranjeKnjigeDto azuriranjeKnjigeDto, @PathVariable Long id, HttpSession session) {
        if (checkLogin(session)) {
            Uloga uloga = (Uloga) session.getAttribute("uloga");
            if (uloga == Uloga.ADMINISTRATOR) {
                knjigaService.azuriranjeKnjige(id, azuriranjeKnjigeDto);
            }
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/api/brisanjeKnjige-admin/{id}")
    public ResponseEntity<String> brisanjeKnjiga(@PathVariable Long id, HttpSession session) {
        if (checkLogin(session)) {
            Uloga uloga = (Uloga) session.getAttribute("uloga");
            if (uloga == Uloga.ADMINISTRATOR) {
                knjigaService.brisanjeKnjigeAdmin(id);
            }
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/api/azurirajProfil-admin/{id}")
    public ResponseEntity<String> azuriranjeProfila(@RequestBody AzuriranjeProfilaDto azuriranjeProfilaDto, @PathVariable Long id, HttpSession session){
        if (checkLogin(session)) {
            Uloga uloga = (Uloga) session.getAttribute("uloga");
            if (uloga == Uloga.ADMINISTRATOR) {
                Autor autor = autorRepository.getAutorById(id);
                if(autor.isAktivan() == false){

                }
                return new ResponseEntity<>("Ne mozete azurirati profil koji je aktivan", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Samo administratori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/api/check-login-admin")
    @ResponseBody
    public boolean checkLogin(HttpSession session) {
        if (session.getAttribute("administrator") != null && session != null) {
            return true;
        } else {
            return false;
        }
    }
}
