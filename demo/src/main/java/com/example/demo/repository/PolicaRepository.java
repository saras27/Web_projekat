package com.example.demo.repository;

import com.example.demo.entity.Polica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PolicaRepository extends JpaRepository<Polica, Long>{
     Polica getPolicaByNaziv(String naziv);
     Polica getPolicaById(Long id);

     void deletePolicaById(Long id);
     void delete(Polica polica);



}

