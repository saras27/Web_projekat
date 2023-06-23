INSERT INTO KORISNIK(ime, prezime, korisnicko_ime, mejl_adresa, lozinka, datum_rodjenja, slika, opis, uloga) VALUES('Petar', 'Petrovic', 'PecaMeca', 'peca@gmail.com', '2djH&65', '2003-05-26', 'slika1234', 'nesto', 1);
INSERT INTO KORISNIK(ime, prezime, korisnicko_ime, mejl_adresa, lozinka, datum_rodjenja, slika, opis, uloga) VALUES('Jovana', 'Jovanovic', 'Jocii', 'jocke@gmail.com', 'fs548f6', '2001-04-22', 'slika215', 'nesto', 2);
INSERT INTO KORISNIK(ime, prezime, korisnicko_ime, mejl_adresa, lozinka, datum_rodjenja, slika, opis, uloga) VALUES('Marija', 'Marjanovic', 'Mara', 'mara@gmail.com', 'Jlpd7f', '1998-01-26', 'slika34', 'nesto', 0);
INSERT INTO KORISNIK(ime, prezime, korisnicko_ime, mejl_adresa, lozinka, datum_rodjenja, slika, opis, uloga) VALUES('Naki', 'Nakic', 'Naky', 'naky@gmail.com', 'oloshy', '1998-01-26', 'slika22', 'nesto', 0);

INSERT INTO ZANR (naziv) VALUES ('drama');
INSERT INTO ZANR (naziv) VALUES ('epska fantastika');
INSERT INTO ZANR (naziv) VALUES ('popularna psihologija');
INSERT INTO ZANR (naziv) VALUES ('poezija');

INSERT INTO AUTOR(id, aktivan) VALUES(1, false);
INSERT INTO KNJIGA (naslov, naslovna_fotografija, ISBN, datum_objavljivanja, broj_strana, opis, ocena, autor_id, zanr_id) VALUES ('Ana Karenjina', 'ana.jpg', '264875', '1956-04-06', '800', 'lektira', 9, 1, 1);
INSERT INTO KNJIGA (naslov, naslovna_fotografija, ISBN, datum_objavljivanja, broj_strana, opis, ocena, autor_id, zanr_id) VALUES ('Sofijin svet', ' ', '864343', '1991-12-05', '800', 'roman', 9, 1, 1);
INSERT INTO KNJIGA (naslov, naslovna_fotografija, ISBN, datum_objavljivanja, broj_strana, opis, ocena, autor_id, zanr_id) VALUES ('Pesma leda i vatre', 'zmaj.jpg', '555333', '1991-12-05', '100', 'roman', 10, 1, 2);

INSERT INTO STAVKA_POLICE(knjiga_id) VALUES(1);
INSERT INTO STAVKA_POLICE(knjiga_id) VALUES(1);
INSERT INTO STAVKA_POLICE(knjiga_id) VALUES(3);
INSERT INTO RECENZIJA(ocena, tekst, datum_recenzije, korisnik_id, stavka_id) VALUES(8, 'Prelepo, nemam rechyyyy', '2021-06-24', 4, 2);
INSERT INTO RECENZIJA(ocena, tekst, datum_recenzije, korisnik_id, stavka_id) VALUES(4, 'Previse dugacko i dosadno, nije bilo sanse procitati, a ni film nije nesto', '2021-06-24', 1, 1);
INSERT INTO POLICA(naziv, primarna, korisnik_id) VALUES('Want to read', 1, 1);
INSERT INTO POLICA(naziv, primarna, korisnik_id) VALUES('Currently Reading', 1, 1);
INSERT INTO POLICA(naziv, primarna, korisnik_id) VALUES('Read', 1, 1);
INSERT INTO POLICA(naziv, primarna, korisnik_id) VALUES('Read', 1, 2);

INSERT INTO ZAHTEV_ZA_AKTIVACIJU(email, telefon, poruka, datum, status) VALUES('joca@gmail.com', '062456678', 'Zahtev za autora', '2021-06-06', 1 );
INSERT INTO POLICE_STAVKE(stavka_id, polica_id) VALUES(1, 2);
INSERT INTO POLICE_STAVKE(stavka_id, polica_id) VALUES(2, 2);
INSERT INTO POLICE_STAVKE(stavka_id, polica_id) VALUES(3, 1);