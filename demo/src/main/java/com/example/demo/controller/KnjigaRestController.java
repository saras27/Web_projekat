package com.example.demo.controller;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.entity.Knjiga;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.service.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KnjigaRestController {

    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/api/knjige")
    public ResponseEntity<List<KnjigaDto>> getKnjige(HttpSession session){
        List<Knjiga> knjige = knjigaService.findAll();

        if(knjige == null) {
            System.out.println("Nema knjiga u bazi");
        } else {
            System.out.println(knjige);
        }

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : knjige){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/knjige/{naslov}")
    public Knjiga getKnjiga(@PathVariable(name = "naslov") String naslov, HttpSession session){
        Knjiga knjiga = (Knjiga) session.getAttribute("user");
        System.out.println(knjiga.getNaslov());
        session.invalidate();
        return knjigaService.findKnjigu(naslov);
    }



    //dodavanje knjige na policu
}
