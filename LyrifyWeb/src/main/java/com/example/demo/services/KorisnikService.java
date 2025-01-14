package com.example.demo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KorisnikRepository;

import model.Korisnik;
import model.Uloga;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository kr;
	
	@Autowired
	private PasswordEncoder pe;

	public void registrujKorisnika(String username, String email, String lozinka) {
		Korisnik korisnik = new Korisnik();
		korisnik.setKorisnickoIme(username);
		korisnik.setEmail(email);
		korisnik.setLozinka(pe.encode(lozinka));
		korisnik.setUloga(Uloga.USER);
		korisnik.setDatumRegistracije(LocalDate.now());
		kr.save(korisnik);
	}

//	public boolean prijaviKorisnika(String email, String lozinka) {
//		Korisnik korisnik = kr.findByEmail(email);
//		if (korisnik != null && korisnik.getLozinka().equals(lozinka)) {
//			return true;
//		}
//		return false;
//	}

	public Korisnik nadjiKorisnikaPoEmailu(String email) {
		return kr.findByEmail(email);
	}

	public Korisnik nadjiKorisnikaPoId(int korisnikId) {
		return kr.findById(korisnikId).orElse(null); // `kr` je repozitorijum za korisnike
	}

}
