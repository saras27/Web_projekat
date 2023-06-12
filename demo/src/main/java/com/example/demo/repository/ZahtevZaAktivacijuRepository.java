package com.example.demo.repository;

import com.example.demo.entity.ZahtevZaAktivaciju;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZahtevZaAktivacijuRepository extends JpaRepository<ZahtevZaAktivaciju, Long>{
    ZahtevZaAktivaciju getZahtevZaAktivacijuById(Long id);
}
