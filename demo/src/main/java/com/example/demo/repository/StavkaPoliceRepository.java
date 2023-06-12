package com.example.demo.repository;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Polica;
import com.example.demo.entity.StavkaPolice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StavkaPoliceRepository extends JpaRepository<StavkaPolice, Long>{
   //boolean existsByKnjigaContaining(Knjiga knjiga, Polica polica);
}

