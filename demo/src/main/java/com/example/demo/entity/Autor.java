package com.example.demo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Autor extends Korisnik{
    @Column
    private boolean aktivan;
    @OneToMany
    private List<Knjiga> knjige;
}
