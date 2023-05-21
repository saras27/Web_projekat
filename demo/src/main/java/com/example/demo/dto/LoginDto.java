package com.example.demo.dto;

public class LoginDto {
    private Long Id;
    private String korisnickoIme;

    /*private String password;*/

    public LoginDto() {
    }

    public LoginDto(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
        //this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    /*public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
