package com.example.demo.dto;

public class LoginDto {
    private String korisnickoIme;

    /*private String password;*/

    public LoginDto() {
    }

    public LoginDto(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
        //this.password = password;
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
