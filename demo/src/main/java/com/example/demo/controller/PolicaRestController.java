package com.example.demo.controller;

import com.example.demo.dto.PolicaDto;
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


import javax.servlet.http.HttpSession;
import java.security.spec.ECPoint;
import java.util.ArrayList;
import java.util.List;


@RestController
public class PolicaRestController {
    @Autowired
    private PolicaService policaService;

    @PostMapping("/api/newShelf")
    public ResponseEntity<String> novaPolica(@PathVariable(name = "naziv") String naziv, HttpSession session){
        if(policaService.findOne(naziv)!= null){
            System.out.println("Polica sa ovim imenom vec postoji.");
        }else{
            Polica addedPolica = policaService.save(naziv);
            return ResponseEntity.ok("Polica uspesno dodata");
        }
        return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
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
}