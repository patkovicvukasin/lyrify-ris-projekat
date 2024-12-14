package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.Servis;

import model.Pesma;
import model.TekstPesme;
import model.Zanr;

@Controller
public class Kontroler {
	
	@Autowired
    private Servis s;
	
	@GetMapping("/pretragaPoImenu")
	public String pretragaPoImenu(@RequestParam("pesma") String naziv, Model m) {
	    System.out.println("Naziv za pretragu: " + naziv); // Debug log
	    List<Pesma> pesme = s.pretraziPesmePoImenu(naziv);
	    //System.out.println("Pronađene pesme: " + pesme); // Debug log
	    m.addAttribute("pesme", pesme);
	    m.addAttribute("pretraga", naziv);
	    return "rezultatPretrage";
	}
	
    @GetMapping("/")
    public String zanrovi(Model m) {
    	List<Zanr> zanrovi = s.zanrovi();
        //System.out.println("Zanrovi u kontroleru: " + zanrovi);
        m.addAttribute("zanrovi", zanrovi);
        return "index";
    }
    
    @GetMapping("/pretragaPoZanru")
    public String pretragaPoZanru(@RequestParam("zanrId") Integer zanrId, Model m) {
        System.out.println("ID izabranog žanra: " + zanrId); // Debug log
        List<Pesma> pesme = s.pretraziPesmePoZanru(zanrId);
        //System.out.println("Pronađene pesme za žanr: " + pesme); // Debug log
        Zanr z = s.pronadjiZanrPoId(zanrId);
        m.addAttribute("pesme", pesme);
        m.addAttribute("nazivZanra", z.getNaziv());
        return "rezultatPretrageZanr";
    }
    
    @GetMapping("/tekstPesme/{id}")
    public String prikaziTekstPesme(@PathVariable("id") Integer pesmaId, Model m) {
        TekstPesme tekstPesme = s.pronadjiTekstPesmePoPesmaId(pesmaId);
        if (tekstPesme == null) {
            throw new RuntimeException("Tekst pesme nije pronađen.");
        }
        m.addAttribute("tekstPesme", tekstPesme);
        return "tekst";
    }

    
    @GetMapping("/prijava")
    public String prikaziPrijavu() {
        return "prijava";
    }

    @GetMapping("/registracija")
    public String prikaziRegistraciju() {
        return "registracija";
    }

    @PostMapping("/processRegistracija")
    public String obradiRegistraciju(@RequestParam("username") String username, 
                                     @RequestParam("email") String email, 
                                     @RequestParam("lozinka") String lozinka) {
        // Pozovi servis za registraciju korisnika
        s.registrujKorisnika(username, email, lozinka);
        return "redirect:/";
    }

    @PostMapping("/processPrijava")
    public String obradiPrijavu(@RequestParam("email") String email, 
                                @RequestParam("lozinka") String lozinka) {
        // Pozovi servis za autentifikaciju korisnika
        boolean uspesno = s.prijaviKorisnika(email, lozinka);
        if (uspesno) {
            return "redirect:/"; // Uspešna prijava
        } else {
            return "redirect:/prijava?error"; // Neuspešna prijava
        }
    }

}
