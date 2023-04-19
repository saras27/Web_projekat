package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Autor extends Korisnik implements Serializable {
    @Column
    private boolean aktivan;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Set<Knjiga> knjige = new HashSet<>();

    /*@Override
    public Long getId() {
        return Id;
    }*/

    public boolean isAktivan() {
        return aktivan;
    }

    public Set<Knjiga> getKnjige() {
        return knjige;
    }

    /*@Override
    public void setId(Long id) {
        Id = id;
    }*/

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public void setKnjige(Set<Knjiga> knjige) {
        this.knjige = knjige;
    }

    @Override
    public String toString() {
        return "Autor{" +
                // "Id=" + Id +
                super.toString() +
                ", aktivan=" + aktivan +
                //", knjige=" + knjige +
                '}';
    }
}
