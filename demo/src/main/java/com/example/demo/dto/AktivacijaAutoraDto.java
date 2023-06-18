package com.example.demo.dto;

public class AktivacijaAutoraDto {
    private String email;
    private String telefon;
    private String poruka;

    public AktivacijaAutoraDto() {
    }

    public AktivacijaAutoraDto(String email, String telefon, String poruka) {
        this.email = email;
        this.telefon = telefon;
        this.poruka = poruka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}
