package com.example.demo.repository;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Polica;
import com.example.demo.entity.Recenzija;
import com.example.demo.entity.StavkaPolice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface StavkaPoliceRepository extends JpaRepository<StavkaPolice, Long>{
    StavkaPolice existsByKnjiga_Id(Long id);
    StavkaPolice getStavkaPoliceByKnjiga(Knjiga knjiga);

}

