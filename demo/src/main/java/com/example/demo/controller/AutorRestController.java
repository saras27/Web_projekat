package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.KnjigaRepository;
import com.example.demo.repository.PolicaRepository;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PolicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AutorRestController {
    @Autowired
    private PolicaService policaService;
    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KnjigaRepository knjigaRepository;

    @PostMapping("/api/dodajPolicuAutor")
    public ResponseEntity<String> novaPolicaAutor(@RequestBody NovaPolicaDto novaPolicaDto, HttpServletRequest request/*HttpSession session*/){
        HttpSession session = request.getSession();
        if(checkLoginAutor(request)) {
            if (policaService.findOne(novaPolicaDto.getNaziv()) != null && novaPolicaDto.getNaziv() != " ") {
                System.out.println("Polica sa ovim imenom vec postoji ili ste uneli prazan string.");
            } else {
                Autor korisnik = (Autor) session.getAttribute("autor");
                return policaService.save(novaPolicaDto.getNaziv(), korisnik);
                //return ResponseEntity.ok("Polica uspesno dodata");
             /*   // String korisnickoIme = (String) session.getAttribute("")
                Long id = (Long) session.getAttribute("id");
                Polica addedPolica = policaService.save(novaPolicaDto.getNaziv(), );
                return ResponseEntity.ok("Polica uspesno dodata");*/
            }
            return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
        } else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/dodajKnjigu")
    public ResponseEntity<String> novaKnjiga(@RequestBody NovaKnjigaDto novaKnjigaDto, HttpSession session){
        if(checkLoginAutor(session)) {
            if (knjigaService.findKnjigu(novaKnjigaDto.getNaslov()) != null && novaKnjigaDto.getNaslov() != " ") {
                System.out.println("Knjiga sa ovim imenom vec postoji ili ste uneli prazan string.");
            } else {
                Knjiga knjiga = new Knjiga();
                knjiga.setNaslov(novaKnjigaDto.getNaslov());
                knjiga.setNaslovnaFotografija(novaKnjigaDto.getNaslovnaFotografija());
                knjiga.setISBN(novaKnjigaDto.getISBN());
                knjiga.setDatumObjavljivanja(novaKnjigaDto.getDatumObjavljivanja());
                knjiga.setBrojStrana(novaKnjigaDto.getBrojStrana());
                knjiga.setOpis(novaKnjigaDto.getOpis());
                knjiga.setZanr(novaKnjigaDto.getZanr());
                knjiga.setAutor(novaKnjigaDto.getAutor());
                knjigaService.save(knjiga);
                return ResponseEntity.ok("Knjiga uspesno dodata");
            }
            return new ResponseEntity("Neispravni podaci", HttpStatus.BAD_REQUEST);
        } else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/izbrisiPolicuAutor")
    public ResponseEntity<String> izbrisiPolicuAutor(@RequestBody NovaPolicaDto policaDto, HttpSession session){
        if(checkLoginAutor(session)) {
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.UNAUTHORIZED);
        }
        Autor ulogovanKorisnik = (Autor) session.getAttribute("autor");
        if (ulogovanKorisnik.getUloga().equals(Uloga.AUTOR)) {
            Polica polica = policaService.getByNaziv(policaDto.getNaziv());
            if(polica == null)
                return new ResponseEntity<>("Polica sa datim imenom ne postoji", HttpStatus.NOT_FOUND);

            if(polica.isPrimarna()){
                return new ResponseEntity<>("Brisanje primarne police nije dozvoljeno", HttpStatus.FORBIDDEN);
            }

            Autor korisnikIzBaze = korisnikService.getAutorById(ulogovanKorisnik.getId());
            korisnikIzBaze.getPolice().remove(polica);
            Korisnik korisnik = korisnikService.save(korisnikIzBaze);
            policaService.delete(polica);

            return new ResponseEntity<>("Polica uspesno obrisana", HttpStatus.OK);
        }
        return new ResponseEntity<>("Niste autor", HttpStatus.UNAUTHORIZED);



    }
  /*  @PostMapping("/api/dodaj-knjigu")
    public ResponseEntity<String> dodajKnjigu(@RequestBody KnjigaDto knjigaDto,@PathVariable Long policaId, HttpServletRequest request){
        if(checkLoginAutor(request)) {
            Polica polica = policaService.getById(policaId);
            if (polica != null) {
                if (policaService.findOne(knjigaDto.getNaslov()) != null) {
                    return policaService.dodavanjeNaPolicu(knjigaDto.getNaslov(), polica);
                } else {
                    return new ResponseEntity<>("Knjiga sa ovim naslovom ne postoji u bazi", HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Polica na koju zelite da dodate knjigu ne postoji", HttpStatus.NOT_FOUND);
            }
        }else{
            return  new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
    }
  */

    @GetMapping("/api/policeAutor")
    public ResponseEntity<List<PolicaDto>> getPoliceAutor(HttpSession session){
        List<Polica> police = policaService.findAll();

        Polica polica1 = (Polica) session.getAttribute("polica");
        if(polica1 == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(polica1);
        }

        List<PolicaDto> dtos = new ArrayList<>();
        for(Polica polica : police){
            PolicaDto dto = new PolicaDto(polica);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/check-user-login-autor")
    public boolean checkLoginAutor(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("autor") != null) {
            // User is logged in
            return true;
        } else {
            // User is not logged in
            return false;
        }
    }
    @GetMapping("/api/check-login-autor")
    //@ResponseBody
    public boolean checkLoginAutor(HttpSession session) {
        if (session.getAttribute("autor") != null) {
            // User is logged in
            return true;
        } else {
            // User is not logged in
            return false;
        }
    }
    @PostMapping("/api/azurirajKnjige/{id}")
    public ResponseEntity<String> azuriranjeKnjige(@PathVariable Long id, @RequestBody AzuriranjeKnjigeDto azuriranjeKnjigeDto, HttpSession session){
        if (checkLoginAutor(session)) {
            Uloga uloga = (Uloga) session.getAttribute("uloga");
            if (uloga == Uloga.AUTOR) {
                knjigaService.azuriranjeKnjige(id, azuriranjeKnjigeDto);
            }
            return new ResponseEntity<>("Samo autori imaju pristup", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
    }

}
