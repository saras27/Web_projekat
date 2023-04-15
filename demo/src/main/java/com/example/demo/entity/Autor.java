package com.example.demo.entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Autor extends Korisnik{
    @Column
    private boolean aktivan;
    @ManyToMany
    @JoinTable(name = "Knjige", joinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "knjiga_id", referencedColumnName = "id"))
    private Set<Knjiga> knjige = new HashSet<>();

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public Set<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(Set<Knjiga> knjige) {
        this.knjige = knjige;
    }
}
