package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.PesmaRepository;
import com.example.demo.repositories.TekstPesmeRepository;
import com.example.demo.repositories.ZanrRepository;

import model.Korisnik;
import model.Pesma;
import model.TekstPesme;
import model.Uloga;
import model.Zanr;

@Service
public class Servis {

	@Autowired
	private ZanrRepository zr;	
	
	@Autowired
	private PesmaRepository pr;
	
	@Autowired
	private TekstPesmeRepository tpr;
	
	@Autowired
    private KorisnikRepository korisnikRepository;
	
	public List<Pesma> pretraziPesmePoImenu(String naziv) {
        List<Pesma> pesme = pr.findByNazivContainingIgnoreCase(naziv);
        System.out.println("Pesme pronađene u bazi: " + pesme); // Log za bazu
        return pesme;
    }

    public List<Zanr> zanrovi() {
    	List<Zanr> zanrovi = zr.findAll();
        //System.out.println("Zanrovi iz baze: " + zanrovi);
        return zanrovi;
    }
    
    public Zanr pronadjiZanrPoId(Integer zanrId) {
    	return zr.findById(zanrId).get();
    }

    public Zanr dodajZanr(Zanr zanr) {
        return zr.save(zanr);
    }
    
    public List<Pesma> pretraziPesmePoZanru(Integer zanrId) {
        return pr.findByZanrId(zanrId);
    }
    
    public TekstPesme pronadjiTekstPesmePoPesmaId(Integer pesmaId) {
        return tpr.findByPesmaId(pesmaId)
            .orElseThrow(() -> new RuntimeException("Tekst za pesmu nije pronađen."));
    }
    
    public void registrujKorisnika(String username, String email, String lozinka) {
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(username);
        korisnik.setEmail(email);
        korisnik.setLozinka(lozinka); // Lozinka bi trebala biti hashirana
        korisnik.setUloga(Uloga.USER);
        korisnik.setDatumRegistracije(LocalDate.now());
        korisnikRepository.save(korisnik);
    }

    public boolean prijaviKorisnika(String email, String lozinka) {
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        return korisnik != null && korisnik.getLozinka().equals(lozinka);
    }


    
    
	
}
