package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the prijateljstvo database table.
 * 
 */
@Embeddable
public class PrijateljstvoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="korisnik_id1", insertable=false, updatable=false)
	private int korisnikId1;

	@Column(name="korisnik_id2", insertable=false, updatable=false)
	private int korisnikId2;

	public PrijateljstvoPK() {
	}
	public int getKorisnikId1() {
		return this.korisnikId1;
	}
	public void setKorisnikId1(int korisnikId1) {
		this.korisnikId1 = korisnikId1;
	}
	public int getKorisnikId2() {
		return this.korisnikId2;
	}
	public void setKorisnikId2(int korisnikId2) {
		this.korisnikId2 = korisnikId2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrijateljstvoPK)) {
			return false;
		}
		PrijateljstvoPK castOther = (PrijateljstvoPK)other;
		return 
			(this.korisnikId1 == castOther.korisnikId1)
			&& (this.korisnikId2 == castOther.korisnikId2);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.korisnikId1;
		hash = hash * prime + this.korisnikId2;
		
		return hash;
	}
}