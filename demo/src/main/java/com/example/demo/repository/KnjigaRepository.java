package com.example.demo.repository;

import com.example.demo.entity.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long>{
    Knjiga getByNaslov(String naslov);
    Knjiga getById(Long id);

    List<Knjiga> findAll();

}