package com.example.demo;

import com.example.demo.entity.Recenzija;
import com.example.demo.entity.StavkaPolice;
import com.example.demo.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	/* Da bismo testirali repozitorijum, direktno smo pozvali u glavnoj klasi metode,
	   inace bi pozivi bili u npr. nekom od servisa.
	 */
	@Autowired
	private RecenzijaRepository recenzijaRepository;


	@Override
	public void run(String... args) {

		// kreiramo novi objekat klase Employee
		Recenzija recenzija = new Recenzija();
		recenzija.setOcena(5);
		recenzija.setTekst("lepo!");
		recenzija.setDatumRecenzije(LocalDate.ofEpochDay(2016-02-26));
		/*stavka.setNaslovnaFotografija(" ");
		knjiga.setISBN("2643581");
		knjiga.setDatumObjavljivanja("26.5.2001.");
		knjiga.setBrojStrana(256);
		knjiga.setOpis("divno!");
		knjiga.setOcena(9);*/
		/*recenzija.setTekst("super!");
		recenzija.setDatumRecenzije("25.3.2021.");*/


		// čuvamo objekat u bazi
		this.recenzijaRepository.save(recenzija);

		List<Recenzija> recenzije = this.recenzijaRepository.findAll();
//		List<Employee> employees = this.employeeRepository.findAllByPositionOrderByFirstName("radnik");
//		List<Employee> employees = this.employeeRepository.findByFirstNameOrLastName("Aleksandar", "Milić");
//		List<Employee> employees = this.employeeRepository.findByFirstNameIgnoreCase("jovanka");
//		List<Employee> employees = this.employeeRepository.findByDepartmentName("Menadžment");

		for (Recenzija r: recenzije){
			System.out.println(r);
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}