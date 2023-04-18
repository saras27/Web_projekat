/*package com.example.demo.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Autor extends Korisnik implements Serializable {
    @Column
    private boolean aktivan;
    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return "Autor{" +
                "aktivan=" + aktivan +
                ", knjige=" + knjige +
                '}';
    }
}*/
