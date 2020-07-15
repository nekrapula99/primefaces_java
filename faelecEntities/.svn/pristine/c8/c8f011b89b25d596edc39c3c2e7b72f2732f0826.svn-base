package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.tlm.faelecEntities.model.entities.Mcampo00PK;

/**
 * The primary key class for the MCAMPO00 database table.
 * 
 */
@Embeddable
public class Mcampo00PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APLCAM", unique=true, nullable=false, length=20)
	private String aplcam;

	@Column(name="CODCAM", unique=true, nullable=false, length=20)
	private String codcam;

	@Column(name="TABCAM", unique=true, nullable=false, length=20)
	private String tabcam;

    public Mcampo00PK() {
    }
	public String getAplcam() {
		return this.aplcam;
	}
	public void setAplcam(String aplcam) {
		this.aplcam = aplcam;
	}
	public String getCodcam() {
		return this.codcam;
	}
	public void setCodcam(String codcam) {
		this.codcam = codcam;
	}
	public String getTabcam() {
		return this.tabcam;
	}
	public void setTabcam(String tabcam) {
		this.tabcam = tabcam;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Mcampo00PK)) {
			return false;
		}
		Mcampo00PK castOther = (Mcampo00PK)other;
		return 
			this.aplcam.equals(castOther.aplcam)
			&& this.codcam.equals(castOther.codcam)
			&& this.tabcam.equals(castOther.tabcam);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.aplcam.hashCode();
		hash = hash * prime + this.codcam.hashCode();
		hash = hash * prime + this.tabcam.hashCode();
		
		return hash;
    }
}