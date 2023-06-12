package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PolicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/login")
    public Set<Polica> login(@RequestBody LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()) {
            System.out.println("Invalid login data");
            return null;
        }

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getKorisnickoIme());
        if (loggedKorisnik == null){
            System.out.println("User does not exist!");
            return null;
        }

        session.setAttribute("korisnik", loggedKorisnik);
        return loggedKorisnik.getPolice();
    }

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
    @GetMapping("/api/{KorisnickoIme}")
    public Korisnik getKorisnik(@PathVariable(name = "KorisnickoIme") String naslov, HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("KorisnickoIme");
        System.out.println(korisnik);
        //session.invalidate();
        return korisnikService.nadjiKorisnik(korisnik.getKorisnickoIme());
    }
    @PostMapping("api/registracija")
    public ResponseEntity<String> registracija(@RequestBody RegistracijaDto registracijaDto, HttpSession session) {
        if(registracijaDto.getKorisnickoIme().isEmpty() || registracijaDto.getIme().isEmpty() ||
                registracijaDto.getPrezime().isEmpty() || registracijaDto.getMejl().isEmpty() ||
                registracijaDto.getLozinka().isEmpty() || registracijaDto.getPotvrda_lozinke().isEmpty())
            return new ResponseEntity("Neispravni podaci za registraciju", HttpStatus.BAD_REQUEST);


        /*Scanner in1 = new Scanner(System.in);
        String ime = in1.nextLine();

        Scanner in2 = new Scanner(System.in);
        String prezime = in2.nextLine();

        Scanner in3 = new Scanner(System.in);
        String korisnickoIme = in3.nextLine();

        Scanner in4 = new Scanner(System.in);
        String mejl = in4.nextLine();

        Scanner in5 = new Scanner(System.in);
        String lozinka = in5.nextLine();
    //lozinka treba da se unese 2 puta radi potvrde
        Scanner in6 = new Scanner(System.in);
        String potvrda = in6.nextLine();*/

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

    @PostMapping("/api/obrisiPolicu-prijavljenikorisnik")
    public ResponseEntity<String> obrisiPolicu(@RequestBody NovaPolicaDto novaPolicaDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        Polica polica = policaService.getByNaziv(novaPolicaDto.getNaziv());
        if(polica == null)
            return new ResponseEntity<>("Polica sa datim imenom ne postoji", HttpStatus.NOT_FOUND);

        if(polica.isPrimarna()){
            return new ResponseEntity<>("Brisanje primarne police nije dozvoljeno", HttpStatus.FORBIDDEN);
        }

        Korisnik korisnikIzBaze = korisnikService.getByKorisnickoIme(prijavljeniKorisnik.getKorisnickoIme());
        korisnikIzBaze.getPolice().remove(polica);
        Korisnik korisnik = korisnikService.save(korisnikIzBaze);
        policaService.delete(polica);

        return new ResponseEntity<>("Polica uspesno obrisana", HttpStatus.OK);

    }

    @PostMapping("/api/dodajPolicu-prijavljenikorisnik")
    public ResponseEntity<String> dodajPolicu(@RequestBody NovaPolicaDto novaPolicaDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (checkLogin(session)) {
            Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
            return policaService.save(novaPolicaDto.getNaziv(), prijavljeniKorisnik);
        }
        return new ResponseEntity<>("Niste ulogovani", HttpStatus.OK);
    }

    @PostMapping("/api/azurirajProfil/{korisnickoIme")
    public ResponseEntity<String> azuriranjeProfila(@PathVariable String korisnickoIme, @RequestBody AzuriranjeProfilaDto azuriranjeProfilaDto, HttpSession session){
        if(!checkLogin(session)){
            return new ResponseEntity<>("Niste ulogovani.", HttpStatus.FORBIDDEN);
        }
        Korisnik korisnik = korisnikService.getByKorisnickoIme(korisnickoIme);

        if(korisnik == null){
            return new ResponseEntity<>("Korisnik nije pronadjen.", HttpStatus.NOT_FOUND);
        }

        String loggedInUsername = (String) session.getAttribute("ulogovaniKorisnik");
        if(!loggedInUsername.equals(korisnik.getKorisnickoIme())){
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


    /*@PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Employee loggedEmployee = (Employee) session.getAttribute("employee");

        if (loggedEmployee == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }*/



   /* @PostMapping("/api/save-employee")
    public String saveEmployee(@RequestBody Employee employee) {
        this.employeeService.save(employee);
        return "Successfully saved an employee!";
    }*/
}
