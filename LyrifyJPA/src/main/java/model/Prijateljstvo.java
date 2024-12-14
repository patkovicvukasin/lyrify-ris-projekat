package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the prijateljstvo database table.
 * 
 */
@Entity
@NamedQuery(name="Prijateljstvo.findAll", query="SELECT p FROM Prijateljstvo p")
public class Prijateljstvo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PrijateljstvoPK id;

	private String status;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_id1")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_id2")
	private Korisnik korisnik2;

	public Prijateljstvo() {
	}

	public PrijateljstvoPK getId() {
		return this.id;
	}

	public void setId(PrijateljstvoPK id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Korisnik getKorisnik1() {
		return this.korisnik1;
	}

	public void setKorisnik1(Korisnik korisnik1) {
		this.korisnik1 = korisnik1;
	}

	public Korisnik getKorisnik2() {
		return this.korisnik2;
	}

	public void setKorisnik2(Korisnik korisnik2) {
		this.korisnik2 = korisnik2;
	}

}