package com.example.demo.repository;

import com.example.demo.entity.Polica;
import com.example.demo.entity.StavkaPolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface PolicaRepository extends JpaRepository<Polica, Long>{
     Polica getPolicaByNaziv(String naziv);
     Polica getPolicaById(Long id);
     void deletePolicaById(Long id);
     void delete(Polica polica);



}

