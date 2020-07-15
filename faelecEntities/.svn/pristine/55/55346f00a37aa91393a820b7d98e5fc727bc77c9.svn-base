package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the MUSUAR00 database table.
 * 
 */
@Embeddable
public class Musuar00PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APLUSU", insertable=false, updatable=false)
	private String aplusu;

	@Column(name="CODUSU")
	private String codusu;

	public Musuar00PK() {
	}
	public String getAplusu() {
		return this.aplusu;
	}
	public void setAplusu(String aplusu) {
		this.aplusu = aplusu;
	}
	public String getCodusu() {
		return this.codusu;
	}
	public void setCodusu(String codusu) {
		this.codusu = codusu;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Musuar00PK)) {
			return false;
		}
		Musuar00PK castOther = (Musuar00PK)other;
		return 
			this.aplusu.equals(castOther.aplusu)
			&& this.codusu.equals(castOther.codusu);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.aplusu.hashCode();
		hash = hash * prime + this.codusu.hashCode();
		
		return hash;
	}
}