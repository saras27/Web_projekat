INSERT INTO KORISNIK(ime, prezime, korisnicko_ime, mejl_adresa, lozinka, datum_rodjenja, opis, uloga) VALUES('Petar', 'Petrovic', 'PecaMeca', 'peca@gmail.com', '2djH&65', '12.3.2003.', 'nesto', 'citalac');

INSERT INTO ZANR (naziv) VALUES ('drama');

INSERT INTO AUTOR(id, aktivan) VALUES(1, false);

INSERT INTO KNJIGA (naslov, naslovna_fotografija, ISBN, datum_objavljivanja, broj_strana, opis, ocena, autor_id, zanr_id) VALUES ('Ana Karenjina', ' ', '264875', '6.4.1956.', '800', 'lektira', 9, 1, 1);

INSERT INTO STAVKA_POLICE(knjiga_id) VALUES(1);

INSERT INTO RECENZIJA(ocena, tekst, datum_recenzije, stavka_id, korisnik_id) VALUES(8, 'opsirno', '4.6.2015.', 1, 1);

INSERT INTO POLICA(naziv, primarna) VALUES('prva', 'jeste');

INSERT INTO ZAHTEV_ZA_AKTIVACIJU(email, telefon, poruka, datum, status) VALUES('joca@gmail.com', '062456678', 'Zahtev za autora', '6.6.2021.', 'na cekanju');

INSERT INTO POLICE_STAVKE(stavka_id, polica_id) VALUES(1, 1);

