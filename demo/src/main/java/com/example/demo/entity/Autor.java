package com.example.demo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Autor extends Korisnik{
    @Column
    private boolean aktivan;
    @Column
    private List<Knjiga> knjige;
}
