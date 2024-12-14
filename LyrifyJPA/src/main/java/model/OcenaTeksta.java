package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the ocena_teksta database table.
 * 
 */
@Entity
@Table(name="ocena_teksta")
@NamedQuery(name="OcenaTeksta.findAll", query="SELECT o FROM OcenaTeksta o")
public class OcenaTeksta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int ocena;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Pesma
	@ManyToOne
	private Pesma pesma;

	public OcenaTeksta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOcena() {
		return this.ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Pesma getPesma() {
		return this.pesma;
	}

	public void setPesma(Pesma pesma) {
		this.pesma = pesma;
	}

}