package com.example.demo.controller;

import com.example.demo.dto.KnjigaDetaljiDto;
import com.example.demo.dto.KnjigaDto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Recenzija;
import com.example.demo.repository.RecenzijaRepository;
import com.example.demo.repository.StavkaPoliceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.service.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("/api/knjige/{naslov}")
//    public Knjiga getKnjiga(@PathVariable(name = "naslov") String naslov, HttpSession session){
//        Knjiga knjiga = (Knjiga) session.getAttribute("user");
//        System.out.println(knjiga.getNaslov());
//        session.invalidate();
//        return knjigaService.findKnjigu(naslov);
//    }

    @GetMapping("/api/knjige/{id}")
    public KnjigaDetaljiDto getKnjigaById(@PathVariable(name = "id") Long id, HttpSession session){
        Knjiga knjiga = knjigaService.findById(id);
        List<Recenzija> recenzije = knjigaService.getRecenzijeKnjige(id);
        return new KnjigaDetaljiDto(knjiga, recenzije);
    }

    @GetMapping("/api/knjige-zanr/{zanrId}")
    public ResponseEntity<?> getKnjigaByZanrId(@PathVariable(name = "zanrId") Long zanrId){
        List<Knjiga> knjige = knjigaService.getByZanr(zanrId);
        return new ResponseEntity<>(knjige, HttpStatus.OK);
    }



    //dodavanje knjige na policu
}
