package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="datum_registracije")
	private LocalDate datumRegistracije;
	
	@Column(name = "uloga", nullable = false)
    @Enumerated(EnumType.STRING)
    private Uloga uloga;

	private String email;

	@Column(name="korisnicko_ime")
	private String korisnickoIme;

	private String lozinka;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to OcenaTeksta
	@OneToMany(mappedBy="korisnik")
	private List<OcenaTeksta> ocenaTekstas;

	//bi-directional many-to-one association to OmiljenaLista
	@OneToMany(mappedBy="korisnik")
	private List<OmiljenaLista> omiljenaListas;

	//bi-directional many-to-one association to Pesma
	@OneToMany(mappedBy="korisnik")
	private List<Pesma> pesmas;

	//bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy="korisnik1")
	private List<Poruka> porukas1;

	//bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy="korisnik2")
	private List<Poruka> porukas2;

	//bi-directional many-to-one association to Prijateljstvo
	@OneToMany(mappedBy="korisnik1")
	private List<Prijateljstvo> prijateljstvos1;

	//bi-directional many-to-one association to Prijateljstvo
	@OneToMany(mappedBy="korisnik2")
	private List<Prijateljstvo> prijateljstvos2;

	public Korisnik() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

	public LocalDate getDatumRegistracije() {
		return this.datumRegistracije;
	}

	public void setDatumRegistracije(LocalDate datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public List<OcenaTeksta> getOcenaTekstas() {
		return this.ocenaTekstas;
	}

	public void setOcenaTekstas(List<OcenaTeksta> ocenaTekstas) {
		this.ocenaTekstas = ocenaTekstas;
	}

	public OcenaTeksta addOcenaTeksta(OcenaTeksta ocenaTeksta) {
		getOcenaTekstas().add(ocenaTeksta);
		ocenaTeksta.setKorisnik(this);

		return ocenaTeksta;
	}

	public OcenaTeksta removeOcenaTeksta(OcenaTeksta ocenaTeksta) {
		getOcenaTekstas().remove(ocenaTeksta);
		ocenaTeksta.setKorisnik(null);

		return ocenaTeksta;
	}

	public List<OmiljenaLista> getOmiljenaListas() {
		return this.omiljenaListas;
	}

	public void setOmiljenaListas(List<OmiljenaLista> omiljenaListas) {
		this.omiljenaListas = omiljenaListas;
	}

	public OmiljenaLista addOmiljenaLista(OmiljenaLista omiljenaLista) {
		getOmiljenaListas().add(omiljenaLista);
		omiljenaLista.setKorisnik(this);

		return omiljenaLista;
	}

	public OmiljenaLista removeOmiljenaLista(OmiljenaLista omiljenaLista) {
		getOmiljenaListas().remove(omiljenaLista);
		omiljenaLista.setKorisnik(null);

		return omiljenaLista;
	}

	public List<Pesma> getPesmas() {
		return this.pesmas;
	}

	public void setPesmas(List<Pesma> pesmas) {
		this.pesmas = pesmas;
	}

	public Pesma addPesma(Pesma pesma) {
		getPesmas().add(pesma);
		pesma.setKorisnik(this);

		return pesma;
	}

	public Pesma removePesma(Pesma pesma) {
		getPesmas().remove(pesma);
		pesma.setKorisnik(null);

		return pesma;
	}

	public List<Poruka> getPorukas1() {
		return this.porukas1;
	}

	public void setPorukas1(List<Poruka> porukas1) {
		this.porukas1 = porukas1;
	}

	public Poruka addPorukas1(Poruka porukas1) {
		getPorukas1().add(porukas1);
		porukas1.setKorisnik1(this);

		return porukas1;
	}

	public Poruka removePorukas1(Poruka porukas1) {
		getPorukas1().remove(porukas1);
		porukas1.setKorisnik1(null);

		return porukas1;
	}

	public List<Poruka> getPorukas2() {
		return this.porukas2;
	}

	public void setPorukas2(List<Poruka> porukas2) {
		this.porukas2 = porukas2;
	}

	public Poruka addPorukas2(Poruka porukas2) {
		getPorukas2().add(porukas2);
		porukas2.setKorisnik2(this);

		return porukas2;
	}

	public Poruka removePorukas2(Poruka porukas2) {
		getPorukas2().remove(porukas2);
		porukas2.setKorisnik2(null);

		return porukas2;
	}

	public List<Prijateljstvo> getPrijateljstvos1() {
		return this.prijateljstvos1;
	}

	public void setPrijateljstvos1(List<Prijateljstvo> prijateljstvos1) {
		this.prijateljstvos1 = prijateljstvos1;
	}

	public Prijateljstvo addPrijateljstvos1(Prijateljstvo prijateljstvos1) {
		getPrijateljstvos1().add(prijateljstvos1);
		prijateljstvos1.setKorisnik1(this);

		return prijateljstvos1;
	}

	public Prijateljstvo removePrijateljstvos1(Prijateljstvo prijateljstvos1) {
		getPrijateljstvos1().remove(prijateljstvos1);
		prijateljstvos1.setKorisnik1(null);

		return prijateljstvos1;
	}

	public List<Prijateljstvo> getPrijateljstvos2() {
		return this.prijateljstvos2;
	}

	public void setPrijateljstvos2(List<Prijateljstvo> prijateljstvos2) {
		this.prijateljstvos2 = prijateljstvos2;
	}

	public Prijateljstvo addPrijateljstvos2(Prijateljstvo prijateljstvos2) {
		getPrijateljstvos2().add(prijateljstvos2);
		prijateljstvos2.setKorisnik2(this);

		return prijateljstvos2;
	}

	public Prijateljstvo removePrijateljstvos2(Prijateljstvo prijateljstvos2) {
		getPrijateljstvos2().remove(prijateljstvos2);
		prijateljstvos2.setKorisnik2(null);

		return prijateljstvos2;
	}

}