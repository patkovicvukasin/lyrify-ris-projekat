package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the pesma database table.
 * 
 */
@Entity
@NamedQuery(name="Pesma.findAll", query="SELECT p FROM Pesma p")
public class Pesma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String izvodjac;

	@Column(name="link_ka_mediju")
	private String linkKaMediju;

	private String naziv;

	private String slika;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="pesma")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to OcenaTeksta
	@OneToMany(mappedBy="pesma")
	private List<OcenaTeksta> ocenaTekstas;

	//bi-directional many-to-many association to OmiljenaLista
	@ManyToMany(mappedBy="pesmas")
	private List<OmiljenaLista> omiljenaListas;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Zanr
	@ManyToOne
	private Zanr zanr;

	//bi-directional many-to-one association to TekstPesme
	@OneToMany(mappedBy="pesma")
	private List<TekstPesme> tekstPesmes;

	public Pesma() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIzvodjac() {
		return this.izvodjac;
	}

	public void setIzvodjac(String izvodjac) {
		this.izvodjac = izvodjac;
	}

	public String getLinkKaMediju() {
		return this.linkKaMediju;
	}

	public void setLinkKaMediju(String linkKaMediju) {
		this.linkKaMediju = linkKaMediju;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

//	public byte getVerifikovana() {
//		return this.verifikovana;
//	}
//
//	public void setVerifikovana(byte verifikovana) {
//		this.verifikovana = verifikovana;
//	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setPesma(this);
		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setPesma(null);

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
		ocenaTeksta.setPesma(this);

		return ocenaTeksta;
	}

	public OcenaTeksta removeOcenaTeksta(OcenaTeksta ocenaTeksta) {
		getOcenaTekstas().remove(ocenaTeksta);
		ocenaTeksta.setPesma(null);

		return ocenaTeksta;
	}

	public List<OmiljenaLista> getOmiljenaListas() {
		return this.omiljenaListas;
	}

	public void setOmiljenaListas(List<OmiljenaLista> omiljenaListas) {
		this.omiljenaListas = omiljenaListas;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Zanr getZanr() {
		return this.zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public List<TekstPesme> getTekstPesmes() {
		return this.tekstPesmes;
	}

	public void setTekstPesmes(List<TekstPesme> tekstPesmes) {
		this.tekstPesmes = tekstPesmes;
	}

	public TekstPesme addTekstPesme(TekstPesme tekstPesme) {
		getTekstPesmes().add(tekstPesme);
		tekstPesme.setPesma(this);

		return tekstPesme;
	}

	public TekstPesme removeTekstPesme(TekstPesme tekstPesme) {
		getTekstPesmes().remove(tekstPesme);
		tekstPesme.setPesma(null);

		return tekstPesme;
	}

	@Override
	public String toString() {
		return naziv;
	}
	
	

}