INSERT INTO KORISNIK (ime, prezime, korisnicko_ime, mejl_adresa, lozinka, datum_rodjenja, opis, uloga) VALUES ('Petar', 'Petrovic','Petar25', 'petarpetrovic25@gmail.com', 'dhw52Jdjg', '2.7.2001.', 'korisnik', 'Citalac');

--INSERT INTO AUTOR (aktivan) VALUES (true);

INSERT INTO KNJIGA (naslov, naslovna_fotografija, ISBN, datum_objavljivanja, broj_strana, opis, ocena, korisnik_id) VALUES ('Ana Karenjina', ' ', '264875', '6.4.1956.', '800', 'lektira', 9, 1);

INSERT INTO STAVKAPOLICE (knjiga_id) VALUES (1);

INSERT INTO RECENZIJA (ocena, tekst, datum_recenzije, stavka_police_id, korisnik_id) VALUES (6, 'Poucno ali dugacko', '3.12.2020.', 1, 1);

INSERT INTO ZANR (naziv) VALUES ('drama');

INSERT INTO STAVKEPOLICE (polica_id, stavka_police_id) VALUES (1, 1);
INSERT INTO KNJIGE (knjiga_id, zanr_id) VALUES (1, 1);
INSERT INTO ZAHTEVZAAKTIVACIJU (email, telefon, poruka, datum, status) VALUES ('petarpetrovic25@gmail.com', '062154836', 'Zahtev za autora', '12.3.2021.', 'na cekanju');

