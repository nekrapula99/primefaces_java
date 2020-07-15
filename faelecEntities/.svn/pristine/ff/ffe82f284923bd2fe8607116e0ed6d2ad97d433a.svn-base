package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.tlm.faelecEntities.model.entities.Mtabla00PK;

/**
 * The primary key class for the MTABLA00 database table.
 * 
 */
@Embeddable
public class Mtabla00PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APLTAB", unique=true, nullable=false, length=20)
	private String apltab;

	@Column(name="CODTAB", unique=true, nullable=false, length=20)
	private String codtab;

    public Mtabla00PK() {
    }
	public String getApltab() {
		return this.apltab;
	}
	public void setApltab(String apltab) {
		this.apltab = apltab;
	}
	public String getCodtab() {
		return this.codtab;
	}
	public void setCodtab(String codtab) {
		this.codtab = codtab;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Mtabla00PK)) {
			return false;
		}
		Mtabla00PK castOther = (Mtabla00PK)other;
		return 
			this.apltab.equals(castOther.apltab)
			&& this.codtab.equals(castOther.codtab);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.apltab.hashCode();
		hash = hash * prime + this.codtab.hashCode();
		
		return hash;
    }
}