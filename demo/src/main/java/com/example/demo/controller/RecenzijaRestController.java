package com.example.demo.controller;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Recenzija;
import com.example.demo.service.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.RecenzijaService;
import com.example.demo.dto.RecenzijaDto;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecenzijaRestController {

    @Autowired
    private RecenzijaService recenzijaService;
    @GetMapping("/api/recenzije")
    public ResponseEntity<List<RecenzijaDto>> getRecenzije(HttpSession session){
        List<Recenzija> recenzije = recenzijaService.findAll();

        Recenzija nadjiRecenzije = (Recenzija) session.getAttribute("recenzija");
        if(nadjiRecenzije == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(nadjiRecenzije);
        }

        List<RecenzijaDto> dtos = new ArrayList<>();
        for(Recenzija recenzija : recenzije){
            RecenzijaDto dto = new RecenzijaDto(recenzija);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
}
