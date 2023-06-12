package com.example.demo.repository;

import com.example.demo.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
    Autor getAutorById(Long id);
    Autor getAutorByKorisnickoIme(String ime);
}

