package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the omiljena_lista database table.
 * 
 */
@Entity
@Table(name="omiljena_lista")
@NamedQuery(name="OmiljenaLista.findAll", query="SELECT o FROM OmiljenaLista o")
public class OmiljenaLista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String naziv;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-many association to Pesma
	@ManyToMany
	@JoinTable(
		name="omiljena_lista_pesma"
		, joinColumns={
			@JoinColumn(name="omiljena_lista_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="pesma_id")
			}
		)
	private List<Pesma> pesmas;

	public OmiljenaLista() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Pesma> getPesmas() {
		return this.pesmas;
	}

	public void setPesmas(List<Pesma> pesmas) {
		this.pesmas = pesmas;
	}

}