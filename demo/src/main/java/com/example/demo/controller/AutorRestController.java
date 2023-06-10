package com.example.demo.controller;

import com.example.demo.dto.NovaPolicaDto;
import com.example.demo.dto.PolicaDto;
import com.example.demo.entity.Polica;
import com.example.demo.service.PolicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AutorRestController {
    @Autowired
    private PolicaService policaService;

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

}
