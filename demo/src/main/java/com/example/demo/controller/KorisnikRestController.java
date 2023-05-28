package com.example.demo.controller;

import com.example.demo.dto.RegistracijaDto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.KnjigaDto;


import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getKorisnickoIme().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getKorisnickoIme());
        if (loggedKorisnik == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("employee", loggedKorisnik);
        return ResponseEntity.ok("Successfully logged in!");
    }
    @GetMapping("/api/{KorisnickoIme}")
    public Korisnik getKorisnik(@PathVariable(name = "KorisnickoIme") String naslov, HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("KorisnickoIme");
        System.out.println(korisnik);
        session.invalidate();
        return korisnikService.nadjiKorisnik(korisnik.getKorisnickoIme());
    }
    @PostMapping("api/registracija")
    public ResponseEntity<String> registracija(@RequestBody RegistracijaDto registracijaDto, HttpSession session) {
        if(registracijaDto.getKorisnickoIme().isEmpty() || registracijaDto.getIme().isEmpty() ||
                registracijaDto.getPrezime().isEmpty() || registracijaDto.getMejl().isEmpty() ||
                registracijaDto.getLozinka().isEmpty())
            return new ResponseEntity("Neispravni podaci za registraciju", HttpStatus.BAD_REQUEST);


        Scanner in1 = new Scanner(System.in);
        String ime = in1.nextLine();

        Scanner in2 = new Scanner(System.in);
        String prezime = in2.nextLine();

        Scanner in3 = new Scanner(System.in);
        String korisnickoIme = in3.nextLine();

        Scanner in4 = new Scanner(System.in);
        String mejl = in4.nextLine();

        Scanner in5 = new Scanner(System.in);
        String lozinka = in5.nextLine();

        if(registracijaDto.getIme().equals(ime) && registracijaDto.getPrezime().equals(prezime) &&
                registracijaDto.getKorisnickoIme().equals(korisnickoIme) && registracijaDto.getLozinka().equals(lozinka) &&
        registracijaDto.getMejl().equals(mejl))
        {
            Korisnik registrovanKorisnik = korisnikService.registruj(registracijaDto.getIme(),
                    registracijaDto.getPrezime(), registracijaDto.getKorisnickoIme(), registracijaDto.getMejl(),
                    registracijaDto.getLozinka());
            if(registrovanKorisnik == null)
                return new ResponseEntity("Vec postoji korisnik sa unetim korisnickim imenom", HttpStatus.BAD_REQUEST);
            else{
                //this.korisnikService.save(registrovanKorisnik);
                session.setAttribute("korisnik", registrovanKorisnik);
                return ResponseEntity.ok("Uspesno registrovan!");
            }
        }
        else{
            return new ResponseEntity("Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
        }
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
