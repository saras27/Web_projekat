package com.example.demo.repository;

import com.example.demo.entity.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long>{
    Knjiga getByNaslov(String naslov);
    Knjiga getById(Long id);

    List<Knjiga> findAll();

    @Query("SELECT k FROM Knjiga k WHERE k.zanr.Id = ?1")
    List<Knjiga> getKnjigePoZanru(Long zanrId);

}