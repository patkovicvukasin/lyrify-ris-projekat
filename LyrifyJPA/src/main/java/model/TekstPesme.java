package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the tekst_pesme database table.
 * 
 */
@Entity
@Table(name="tekst_pesme")
@NamedQuery(name="TekstPesme.findAll", query="SELECT t FROM TekstPesme t")
public class TekstPesme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String tekst;
	
	private Boolean verifikovan = false;

	//bi-directional many-to-one association to Pesma
	@ManyToOne
	private Pesma pesma;

	public TekstPesme() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	
	public Boolean getVerifikovan() {
        return verifikovan;
    }

    public void setVerifikovan(Boolean verifikovan) {
        this.verifikovan = verifikovan;
    }

	public Pesma getPesma() {
		return this.pesma;
	}

	public void setPesma(Pesma pesma) {
		this.pesma = pesma;
	}

}