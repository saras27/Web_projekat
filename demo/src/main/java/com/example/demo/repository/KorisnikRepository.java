package com.example.demo.repository;

import com.example.demo.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
    Korisnik getByKorisnickoIme(String korisnickoIme);
    Korisnik getKorisnikById(Long id);

    Korisnik save(Korisnik korisnik);
}
