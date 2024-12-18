package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.OcenaTekstaRepository;
import com.example.demo.repositories.PesmaRepository;
import com.example.demo.repositories.TekstPesmeRepository;
import com.example.demo.repositories.ZanrRepository;

import model.Korisnik;
import model.OcenaTeksta;
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
    private KorisnikRepository kr;
	
	@Autowired
	private OcenaTekstaRepository otr;
	
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
        return (TekstPesme) tpr.findByPesmaId(pesmaId);
    }
    
    public void registrujKorisnika(String username, String email, String lozinka) {
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(username);
        korisnik.setEmail(email);
        korisnik.setLozinka(lozinka); // Lozinka bi trebala biti hashirana
        korisnik.setUloga(Uloga.USER);
        korisnik.setDatumRegistracije(LocalDate.now());
        kr.save(korisnik);
    }

    public boolean prijaviKorisnika(String email, String lozinka) {
        Korisnik korisnik = kr.findByEmail(email);
        if (korisnik != null && korisnik.getLozinka().equals(lozinka)) {
            return true; 
        }
        return false;
    }
    
    public Korisnik nadjiKorisnikaPoEmailu(String email) {
        return kr.findByEmail(email);
    }
    
    public void dodajTekstPesme(int pesmaId, String tekst, Korisnik korisnik) {
        TekstPesme tekstPesme = new TekstPesme();
        Pesma pesma = pr.findById(pesmaId).orElseThrow(() -> new RuntimeException("Pesma nije pronađena."));
        tekstPesme.setPesma(pesma);
        tekstPesme.setKorisnik(korisnik);
        tekstPesme.setTekst(tekst);
        tekstPesme.setVerifikovan(false);
        tpr.save(tekstPesme);
    }

    
    public List<TekstPesme> nadjiTekstoveZaPesmu(int pesmaId) {
        return tpr.findByPesmaId(pesmaId);
    }
    
    public TekstPesme nadjiTekstPesmePoId(int tekstId) {
        return tpr.findById(tekstId).orElseThrow(() -> new RuntimeException("Tekst nije pronađen."));
    }

    public List<TekstPesme> nadjiTekstoveZaKorisnika(int korisnikId) {
        return tpr.findByKorisnikId(korisnikId);
    }
    
    public TekstPesme nadjiTekstPoId(int tekstId) {
        return tpr.findById(tekstId)
        		.orElseThrow(() -> new RuntimeException("Tekst nije pronađen."));
    }

    public void dodajPesmuINjenTekst(String naziv, String izvodjac, int zanrId, String tekst, Korisnik korisnik) {
        Pesma pesma = new Pesma();
        pesma.setNaziv(naziv);
        pesma.setIzvodjac(izvodjac);
        pesma.setZanr(zr.findById(zanrId).orElseThrow(() -> new RuntimeException("Zanr nije pronađen")));
        pesma.setKorisnik(korisnik);
        pr.save(pesma); // Sačuvaj pesmu

        TekstPesme tekstPesme = new TekstPesme();
        tekstPesme.setPesma(pesma); // Poveži tekst sa pesmom
        tekstPesme.setTekst(tekst);
        tekstPesme.setKorisnik(korisnik); // Poveži tekst sa korisnikom
        tekstPesme.setVerifikovan(false); // Podrazumevano nije verifikovan
        tpr.save(tekstPesme); // Sačuvaj tekst
    }
    
    public List<Pesma> pretraziPesmePoImenuIliTekstu(String tekst) {
        // Pretraga po imenu
        List<Pesma> pesmePoImenu = pr.findByNazivContainingIgnoreCase(tekst);

        // Pretraga po tekstu
        System.out.println("Pretraga po tekstu: " + tekst);
        List<TekstPesme> tekstovi = tpr.findByTekstContainingIgnoreCase(tekst);
        System.out.println("Pronađeni tekstovi: " + tekstovi);
        List<Pesma> pesmePoTekstu = tekstovi.stream()
                                            .map(TekstPesme::getPesma)
                                            .distinct()
                                            .toList();

        // Kombinuj rezultate
        pesmePoImenu.addAll(pesmePoTekstu);
        return pesmePoImenu.stream().distinct().toList(); // Uklanjanje duplikata
    }

    
    //prosecna ocena
    
    public void dodajOcenuTeksta(int tekstPesmeId, int korisnikId, int ocena) {
        TekstPesme tekstPesme = tpr.findById(tekstPesmeId)
            .orElseThrow(() -> new RuntimeException("Tekst nije pronađen."));
        Korisnik korisnik = kr.findById(korisnikId)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));

//        // Proveri da li je korisnik već ocenio ovaj tekst
//        List<OcenaTeksta> postojeceOcene = otr.findByTekstPesmeIdAndKorisnikId(tekstPesmeId, korisnikId);
//        if (!postojeceOcene.isEmpty()) {
//            throw new RuntimeException("Već ste ocenili ovaj tekst.");
//        }

        OcenaTeksta novaOcena = new OcenaTeksta();
        novaOcena.setTekstPesme(tekstPesme);
        novaOcena.setKorisnik(korisnik);
        novaOcena.setOcena(ocena);
        otr.save(novaOcena);
    }

    public Double nadjiProsecnuOcenu(int tekstPesmeId) {
        return otr.findProsecnaOcena(tekstPesmeId);
    }
    
    public boolean vecOcenioTekst(int tekstPesmeId, int korisnikId) {
        List<OcenaTeksta> postojeceOcene = otr.findByTekstPesmeIdAndKorisnikId(tekstPesmeId, korisnikId);
        return !postojeceOcene.isEmpty();
    }



}
