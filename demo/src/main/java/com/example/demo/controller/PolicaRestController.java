package com.example.demo.controller;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.service.KnjigaService;
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

import java.util.List;
@RestController
public class PolicaRestController {
    /*@Autowired
    private PolicaService policaService;

    @GetMapping("/api/newShelf")
    public */
}