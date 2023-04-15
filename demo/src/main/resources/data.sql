INSERT INTO КORISNIK (ime, prezime, korisnickoIme, mejlAdresa, lozinka, datumRodjenja, opis, uloga) VALUES ('Petar', 'Petrovic','Petar25', 'petarpetrovic25@gmail.com', 'dhw52Jdjg', '2.7.2001.', 'korisnik', 'Citalac');

INSERT INTO ZANR (naziv) VALUES ('drama');

INSERT INTO KNJIGA (naslov, naslovnaFotografija, ISBN, datumObjavljivanja, brojStrana, opis, ocena, zanr_id) VALUES ('Ana Karenjina', ' ', '264875', '6.4.1956.', '800', 'lektira', 1, '9.0');

INSERT INTO STAVKAPOLICE (knjiga_id) VALUES (1);

INSERT INTO RECENZIJA (ocena, tekst, datumRecenzije, stavkaPolice_id, korisnik_id) VALUES ('6', 'Poucno ali dugacko', '3.12.2020.', 1, 1);

INSERT INTO STAVKEPOLICE (polica_id, stavkaPolice_id) VALUES (1, 1);
INSERT INTO KNJIGE (knjiga_id, autor_id) VALUES (1, 1);
INSERT INTO ZAHTEVZAAKTIVACIJU (email, telefon, poruka, datum, status) VALUES ('petarpetrovic25@gmail.com', '062154836', 'Zahtev za autora', '12.3.2021.', 'na cekanju');

