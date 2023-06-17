package com.example.demo.repository;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.entity.StavkaPolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository

public interface PolicaRepository extends JpaRepository<Polica, Long>{
     Polica getPolicaByNaziv(String naziv);
     Polica getPolicaByNazivAndKorisnik(String naziv, Korisnik korisnik);
     Polica getPolicaById(Long id);
     void deletePolicaByNazivAndKorisnik(String naziv, Korisnik korisnik);
     void deletePolicaById(Long id);
     Polica findPolicaByNazivAndKorisnik_Id(String policaName, Long idKorisnika);
     void delete(Polica polica);
     List<Polica> findAllByNaziv(String naziv);
     List<Polica> findAllByNazivAndKorisnik(String imePolice, Korisnik korisnik);

     /*@Query(value = "SELECT * FROM Polica p WHERE p.naziv = naziv",
     nativeQuery = true)
     Set<Polica> getAllByNaziv(String naziv);*/




}

