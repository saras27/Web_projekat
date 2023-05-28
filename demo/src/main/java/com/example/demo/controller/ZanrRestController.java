package com.example.demo.controller;

import com.example.demo.dto.RecenzijaDto;
import com.example.demo.dto.ZanrDto;
import com.example.demo.entity.Recenzija;
import com.example.demo.entity.Zanr;
import com.example.demo.service.ZanrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ZanrRestController {
    @Autowired
    private ZanrService zanrService;
    @GetMapping("/api/zanrovi")
    public ResponseEntity<List<ZanrDto>> getZanrovi(HttpSession session){
        List<Zanr> zanrovi = zanrService.findAll();

        Zanr nadjiZanr = (Zanr) session.getAttribute("zanr");
        if(nadjiZanr == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(nadjiZanr);
        }

        List<ZanrDto> dtos = new ArrayList<>();
        for(Zanr zanr : zanrovi){
            ZanrDto dto = new ZanrDto(zanr);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
}
