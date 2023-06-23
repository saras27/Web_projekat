package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.PolicaRepository;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PolicaService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PolicaService policaService;
    @Autowired
    private PolicaRepository policaRepository;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    //radi
    @PostMapping(value = "api/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> login(@RequestBody LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()) {
            System.out.println("Invalid login data");
            return null;
        }

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getKorisnickoIme());
        if (loggedKorisnik == null) {
            System.out.println("User does not exist!");
            return null;
        }
        session.setAttribute("korisnik", loggedKorisnik);
        sessionService.setSession(session);
        System.out.println(loggedKorisnik.getUloga());
        Uloga uloga = (Uloga) session.getAttribute("uloga");
        //System.out.println(session.getAttribute(uloga.toString()));
        return new ResponseEntity<>(loggedKorisnik, HttpStatus.OK);
    }

    //radi
    @GetMapping("/check-login")
    //@ResponseBody
    public boolean checkLogin(HttpSession session) {
        if (session.getAttribute("korisnik") != null) {
            // User is logged in
            return true;
        } else {
            // User is not logged in
            return false;
        }
    }
    @GetMapping("/api/korisnici")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(HttpSession session){
        List<Korisnik> korisnikList = korisnikService.findAll();

        if(korisnikList == null) {
            System.out.println("Nema korisnika u bazi");
        } else {
            System.out.println(korisnikList);
        }

        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik korisnik : korisnikList){
            KorisnikDto dto = new KorisnikDto(korisnik);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    //radi
    @GetMapping("/api/korisnici/poImenu/{korisnickoIme}")
    public Korisnik getKorisnik(@PathVariable(name = "korisnickoIme") String korisnickoIme){
        Korisnik korisnik = korisnikService.nadjiKorisnik(korisnickoIme);
        if(korisnik == null)
            return null;

        return  korisnik;
    }
    @GetMapping("/api/korisnici/{id}")
    public Korisnik KorisnikById(@PathVariable Long id, HttpSession session){

        return korisnikService.getById(id);
    }

    //radi
    @PostMapping("api/registracija")
    public ResponseEntity<String> registracija(@RequestBody RegistracijaDto registracijaDto, HttpSession session) {
        if(registracijaDto.getKorisnickoIme().isEmpty() || registracijaDto.getIme().isEmpty() ||
                registracijaDto.getPrezime().isEmpty() || registracijaDto.getMejl().isEmpty() ||
                registracijaDto.getLozinka().isEmpty() || registracijaDto.getPotvrda_lozinke().isEmpty())
            return new ResponseEntity("Neispravni podaci za registraciju", HttpStatus.BAD_REQUEST);

        if(registracijaDto.getLozinka().equals(registracijaDto.getPotvrda_lozinke()))
        {
            Korisnik registrovanKorisnik = korisnikService.registruj(registracijaDto.getIme(),
                    registracijaDto.getPrezime(), registracijaDto.getKorisnickoIme(), registracijaDto.getMejl(),
                    registracijaDto.getLozinka());
            if(registrovanKorisnik == null)
                return new ResponseEntity("Vec postoji korisnik sa unetim korisnickim imenom", HttpStatus.BAD_REQUEST);
            else{
                this.korisnikService.registruj(registrovanKorisnik.getIme(), registrovanKorisnik.getPrezime(), registrovanKorisnik.getKorisnickoIme(), registrovanKorisnik.getMejlAdresa(), registrovanKorisnik.getLozinka());
                session.setAttribute("korisnik", registrovanKorisnik);
                return ResponseEntity.ok("Uspesno registrovan!");
            }
        }
        else{
            return new ResponseEntity("Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
        }
    }


    //ne radi
    @PostMapping("/api/obrisiPolicu-prijavljenikorisnik")
    public ResponseEntity<String> obrisiPolicu(@RequestBody NovaPolicaDto novaPolicaDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        Polica polica = policaRepository.getPolicaByNazivAndKorisnik(novaPolicaDto.getNaziv(), prijavljeniKorisnik);
        if(polica == null)
            return new ResponseEntity<>("Polica sa datim imenom ne postoji", HttpStatus.NOT_FOUND);

        if(polica.isPrimarna()){
            return new ResponseEntity<>("Brisanje primarne police nije dozvoljeno", HttpStatus.FORBIDDEN);
        }
        policaRepository.deletePolicaByNazivAndKorisnik(novaPolicaDto.getNaziv(), prijavljeniKorisnik);
        korisnikService.save(prijavljeniKorisnik);
        return new ResponseEntity<>("Polica uspesno obrisana", HttpStatus.OK);

    }

    //radi
    @PostMapping("/api/dodajPolicu-prijavljenikorisnik")
    public ResponseEntity<String> dodajPolicu(@RequestBody NovaPolicaDto novaPolicaDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (checkLogin(session)) {
            Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
            return policaService.save(novaPolicaDto.getNaziv(), prijavljeniKorisnik);
        }
        return new ResponseEntity<>("Niste ulogovani", HttpStatus.OK);
    }


    //radi
    @PostMapping("/api/azurirajProfil/{korisnickoIme}")
    public ResponseEntity<String> azuriranjeProfila(@PathVariable String korisnickoIme, @RequestBody AzuriranjeProfilaDto azuriranjeProfilaDto, HttpSession session){
        if(!checkLogin(session)){
            return new ResponseEntity<>("Niste ulogovani.", HttpStatus.FORBIDDEN);
        }
        Korisnik korisnik = korisnikService.getByKorisnickoIme(korisnickoIme);

        if(korisnik == null){
            return new ResponseEntity<>("Korisnik nije pronadjen.", HttpStatus.NOT_FOUND);
        }

        Korisnik loggedInUsername = (Korisnik) session.getAttribute("korisnik");
        if(!loggedInUsername.getKorisnickoIme().equals(korisnik.getKorisnickoIme())){
            return new ResponseEntity<>("Ne mozete menjati profil drugog korisnika", HttpStatus.FORBIDDEN);
        }
//nisam proverila da li je neki od podataka prazan, npr ako hoce samo da promeni opis profila
        korisnik.setIme(azuriranjeProfilaDto.getIme());
        korisnik.setPrezime(azuriranjeProfilaDto.getPrezime());
        korisnik.setDatumRodjenja(azuriranjeProfilaDto.getDatumRodjenja());
        korisnik.setOpis(azuriranjeProfilaDto.getOpis());
        korisnik.setSlika(azuriranjeProfilaDto.getSlika());

        Korisnik azuriraniProfil = korisnikService.azuriranjeProfila(korisnik);
        return new ResponseEntity<>("Profil uspesno azuriran", HttpStatus.OK);

    }

    //radi
    @PostMapping("api/logout")
    public ResponseEntity<String> Logout(HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }



   /* @PostMapping("/api/save-employee")
    public String saveEmployee(@RequestBody Employee employee) {
        this.employeeService.save(employee);
        return "Successfully saved an employee!";
    }*/
}
