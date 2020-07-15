package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.tlm.faelecEntities.model.entities.Mdecai00PK;

/**
 * The primary key class for the MDECAI00 database table.
 * 
 */
@Embeddable
public class Mdecai00PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APLDEI", unique=true, nullable=false, length=20)
	private String apldei;

	@Column(name="CAMDEI", unique=true, nullable=false, length=20)
	private String camdei;

	@Column(name="CODDEI", unique=true, nullable=false, length=20)
	private String coddei;

	@Column(name="TABDEI", unique=true, nullable=false, length=20)
	private String tabdei;

    public Mdecai00PK() {
    }
	public String getApldei() {
		return this.apldei;
	}
	public void setApldei(String apldei) {
		this.apldei = apldei;
	}
	public String getCamdei() {
		return this.camdei;
	}
	public void setCamdei(String camdei) {
		this.camdei = camdei;
	}
	public String getCoddei() {
		return this.coddei;
	}
	public void setCoddei(String coddei) {
		this.coddei = coddei;
	}
	public String getTabdei() {
		return this.tabdei;
	}
	public void setTabdei(String tabdei) {
		this.tabdei = tabdei;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Mdecai00PK)) {
			return false;
		}
		Mdecai00PK castOther = (Mdecai00PK)other;
		return 
			this.apldei.equals(castOther.apldei)
			&& this.camdei.equals(castOther.camdei)
			&& this.coddei.equals(castOther.coddei)
			&& this.tabdei.equals(castOther.tabdei);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.apldei.hashCode();
		hash = hash * prime + this.camdei.hashCode();
		hash = hash * prime + this.coddei.hashCode();
		hash = hash * prime + this.tabdei.hashCode();
		
		return hash;
    }
}