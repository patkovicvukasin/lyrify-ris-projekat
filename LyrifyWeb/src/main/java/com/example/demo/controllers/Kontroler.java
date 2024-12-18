package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.Servis;

import jakarta.servlet.http.HttpSession;
import model.Korisnik;
import model.Pesma;
import model.TekstPesme;
import model.Zanr;

@Controller
public class Kontroler {
	
	@Autowired
    private Servis s;
	
	@GetMapping("/pretraga")
    public String pretragaPesme(@RequestParam("pesma") String unos, HttpSession session, Model m) {
    	System.out.println("Unos za pretragu: " + unos);
    	List<Pesma> pesme;

        Korisnik ulogovani = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        if (ulogovani != null) {
            // Registrovan korisnik: pretraga po imenu ili tekstu
            pesme = s.pretraziPesmePoImenuIliTekstu(unos);
        } else {
            // Neregistrovan korisnik: samo pretraga po imenu
            pesme = s.pretraziPesmePoImenu(unos);
        }

        System.out.println("Pronađene pesme: " + pesme);
        m.addAttribute("pesme", pesme);
        m.addAttribute("pretraga", unos);
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
    
//    @GetMapping("/tekstPesme/{id}")
//    public String prikaziTekstPesme(@PathVariable("id") Integer pesmaId, Model m) {
//        TekstPesme tekstPesme = s.pronadjiTekstPesmePoPesmaId(pesmaId);
//        if (tekstPesme == null) {
//            throw new RuntimeException("Tekst pesme nije pronađen.");
//        }
//        m.addAttribute("tekstPesme", tekstPesme);
//        return "tekst";
//    }

    
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
                                @RequestParam("lozinka") String lozinka,
                                HttpSession session) 
    {
        boolean uspesno = s.prijaviKorisnika(email, lozinka);
        if (uspesno) {
            Korisnik k = s.nadjiKorisnikaPoEmailu(email);
            session.setAttribute("ulogovaniKorisnik", k);
            
            System.out.println("Prijava USPELA za korisnika: " + k.getKorisnickoIme());
            return "redirect:/";
        } else {
            System.out.println("Neuspesna prijava");
            return "redirect:/prijava?error=NeuspesnaPrijava";
        }
    }

    
    @GetMapping("/dodajTekst")
    public String dodajTekst(@RequestParam(value="zanrId", required=false) Integer zanrId,
                             HttpSession session,
                             Model model) 
    {
        // Proverimo da li je korisnik ulogovan
        Korisnik ulogovani = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        if (ulogovani == null) {
            return "redirect:/prijava";
        }

        // Uzimamo sve zanrove
        List<Zanr> zanrovi = s.zanrovi();
        model.addAttribute("zanrovi", zanrovi);

        // Ako je korisnik izabrao zanr, ucitamo pesme tog zanra
        if (zanrId != null) {
            List<Pesma> pesme = s.pretraziPesmePoZanru(zanrId);
            model.addAttribute("pesme", pesme);
        }

        return "dodajTekst"; 
    }

    @GetMapping("/mojNalog")
    public String prikaziMojNalog(HttpSession session, Model model) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        if (ulogovani == null) {
            return "redirect:/prijava";
        }
        // Samo prikaz mojnalog.jsp, bez dohvaćanja pesama
        model.addAttribute("korisnik", ulogovani);
        
        List<TekstPesme> tekstovi = s.nadjiTekstoveZaKorisnika(ulogovani.getId());
        model.addAttribute("tekstovi", tekstovi);
        
        // Ovde dodaj logiku za dobijanje prosečne ocene svakog teksta
        Map<Integer, Double> mapeProsecneOcene = new HashMap<>();
        for (TekstPesme tp : tekstovi) {
            Double prosek = s.nadjiProsecnuOcenu(tp.getId());
            mapeProsecneOcene.put(tp.getId(), prosek); 
        }
        model.addAttribute("mapaOcena", mapeProsecneOcene);
        
        return "mojnalog";
    }
    
    @GetMapping("/potvrda")
    public String potvrda() {
        return "potvrda";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("ulogovaniKorisnik");
        return "redirect:/";
    }

    
    @PostMapping("/processDodajTekst")
    public String processDodajTekst(
            @RequestParam("pesmaId") int pesmaId,
            @RequestParam("tekst") String tekst,
            HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        if (ulogovani == null) {
            return "redirect:/prijava";
        }

        // Pozivamo servis da sačuva tekst
        s.dodajTekstPesme(pesmaId, tekst, ulogovani);

        // Preusmeravamo na stranicu "uspesno.jsp"
        return "uspesno";
    }
    
    @GetMapping("/tekstovi/{pesmaId}")
    public String prikaziTekstoveZaPesmu(
            @PathVariable("pesmaId") int pesmaId, Model model) {
        List<TekstPesme> tekstovi = s.nadjiTekstoveZaPesmu(pesmaId);
        model.addAttribute("tekstovi", tekstovi);
        return "tekstovi";
    }
    
    @GetMapping("/tekstPesme/{pesmaId}")
    public String prikaziTekstPesme(
            @PathVariable("pesmaId") int pesmaId, Model model, HttpSession session) {
        List<TekstPesme> tekstovi = s.nadjiTekstoveZaPesmu(pesmaId);

        if (tekstovi.size() == 1) {
            // Ako postoji samo jedan tekst, direktno prikaži tekst.jsp
        	TekstPesme tekstPesme = tekstovi.get(0);
        	model.addAttribute("tekstPesme", tekstPesme);
        	
        	Double prosecnaOcena = s.nadjiProsecnuOcenu(tekstPesme.getId());
        	model.addAttribute("prosecnaOcena", prosecnaOcena);
        	
        	Korisnik ulogovani = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        	model.addAttribute("ulogovaniKorisnik",ulogovani);
        	
            return "tekst";
        } else {
            // Ako ih ima više, prikaži listu svih tekstova
            model.addAttribute("tekstovi", tekstovi);
            return "tekstovi";
        }
    }
    
    @GetMapping("/tekst/{tekstId}")
    public String prikaziPojedinacanTekst(@PathVariable int tekstId, Model model,HttpSession session) {
        TekstPesme tekstPesme = s.nadjiTekstPesmePoId(tekstId);
        //prosecna ocena
        Double prosecnaOcena = s.nadjiProsecnuOcenu(tekstId);
        if (tekstPesme == null) {
            throw new RuntimeException("Tekst nije pronađen.");
        }
        model.addAttribute("tekstPesme", tekstPesme);
        model.addAttribute("prosecnaOcena", prosecnaOcena);
        
        Korisnik ulogovani = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        model.addAttribute("ulogovaniKorisnik", ulogovani);
        
        return "tekst"; // Ovo otvara postojeću `tekst.jsp` stranicu
    }
    
    @GetMapping("/tekstKorisnika/{tekstId}")
    public String prikaziTekstKorisnika(@PathVariable("tekstId") int tekstId,HttpSession session, Model model) {
    	Korisnik ulogovani = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        if (ulogovani == null) {
            return "redirect:/prijava";
        }
    	
    	TekstPesme tekstPesme = s.nadjiTekstPoId(tekstId); // Pronađi tekst po ID-u
        if (tekstPesme == null) {
            throw new RuntimeException("Tekst nije pronađen.");
        }
        model.addAttribute("tekstPesme", tekstPesme); // Prosleđujemo tekst na tekst.jsp
        return "tekst";
    }

    
    @GetMapping("/dodajPesmu")
    public String prikaziDodajPesmu(Model model) {
        model.addAttribute("zanrovi", s.zanrovi()); // Dodaj zanrove u model
        return "dodajPesmu"; // Prikaz nove JSP stranice
    }

    @PostMapping("/processDodajPesmu")
    public String processDodajPesmu(
            @RequestParam("naziv") String naziv,
            @RequestParam("izvodjac") String izvodjac,
            @RequestParam("zanrId") int zanrId,
            @RequestParam("tekst") String tekst,
            HttpSession session) {

        Korisnik ulogovanKorisnik = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        if (ulogovanKorisnik == null) {
            return "redirect:/prijava";
        }

        // Pozivamo servis da doda pesmu i njen tekst
        s.dodajPesmuINjenTekst(naziv, izvodjac, zanrId, tekst, ulogovanKorisnik);

        return "redirect:/"; // Povratak na početnu stranicu
    }
    
    
    //ocena teksta
    @PostMapping("/oceniTekst")
    public String oceniTekst(@RequestParam("tekstId") int tekstId,
                             @RequestParam("ocena") int ocena,
                             HttpSession session,
                             Model m) {
        Korisnik korisnik = (Korisnik) session.getAttribute("ulogovaniKorisnik");
        if (korisnik == null) return "redirect:/prijava";
        
        // Ako pokušava da oceni svoj tekst
        TekstPesme tekstPesme = s.nadjiTekstPesmePoId(tekstId);
        if (tekstPesme.getKorisnik().getId() == korisnik.getId()) {
            // možeš baciti grešku ili ignorisati unos
            return "redirect:/tekst/" + tekstId; 
        }
        
        // Proveri da li je korisnik već ocenio tekst
        boolean ocenio = s.vecOcenioTekst(tekstId, korisnik.getId());
        if (ocenio) {
            m.addAttribute("poruka", "Već ste ocenili ovaj tekst.");
            return "vecOcenjeno";
        }

        s.dodajOcenuTeksta(tekstId, korisnik.getId(), ocena);
        return "redirect:/tekst/" + tekstId;
    }
    
    


   
}
