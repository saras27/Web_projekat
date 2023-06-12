package com.example.demo.repository;

import com.example.demo.entity.Recenzija;
import com.example.demo.entity.StavkaPolice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecenzijaRepository extends JpaRepository<Recenzija, Long>{
    List<Recenzija> getRecenzijaByStavka(StavkaPolice stavkaPolice);
}

