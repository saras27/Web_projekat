package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.repository.KnjigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepository knjigaRepository;

    public Knjiga findKnjigu(String naslov){
        Optional<Knjiga> foundKnjiga = Optional.ofNullable(knjigaRepository.getByNaslov(naslov));
        if (foundKnjiga.isPresent())
            return foundKnjiga.get();

        return null;
    }

    public List<Knjiga> findAll(){
        return knjigaRepository.findAll();
    }

    public Knjiga save(Knjiga employee){
        return knjigaRepository.save(employee);
    }

    /*public Knjiga login(String username, String password) {
        Knjiga employee = knjigaRepository.getByNaslov(username);
        if(employee == null || !employee.getPassword().equals(password))
            return null;
        return  employee;
    }*/
}
