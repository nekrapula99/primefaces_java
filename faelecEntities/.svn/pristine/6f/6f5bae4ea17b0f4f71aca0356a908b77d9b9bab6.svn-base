package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mdecai00PK;
import com.tlm.faelecEntities.model.entities.Mtabla00;

/**
 * The persistent class for the MDECAI00 database table.
 * 
 */
@Entity
@Table(name="MDECAI00")
public class Mdecai00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Mdecai00PK id;

	@Column(name="DESAVI", length=70)
	private String desavi;

	@Column(name="DESAYU", length=3000)
	private String desayu;

	@Column(name="DESDEI", nullable=false, length=2000)
	private String desdei;

	@Column(name="TREDEI", unique=true, length=4)
	private String tredei;

	//bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APLDEI", nullable=false, insertable=false, updatable=false)
	private Maplic00 maplic00;

	//bi-directional many-to-one association to Mtabla00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="APLDEI", referencedColumnName="APLTAB", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="TABDEI", referencedColumnName="CODTAB", nullable=false, insertable=false, updatable=false)
		})
	private Mtabla00 mtabla00;

    public Mdecai00() {
    }

	public Mdecai00PK getId() {
		return this.id;
	}

	public void setId(Mdecai00PK id) {
		this.id = id;
	}
	
	public String getDesavi() {
		return this.desavi;
	}

	public void setDesavi(String desavi) {
		this.desavi = desavi;
	}

	public String getDesayu() {
		return this.desayu;
	}

	public void setDesayu(String desayu) {
		this.desayu = desayu;
	}

	public String getDesdei() {
		return this.desdei;
	}

	public void setDesdei(String desdei) {
		this.desdei = desdei;
	}

	public String getTredei() {
		return this.tredei;
	}

	public void setTredei(String tredei) {
		this.tredei = tredei;
	}

	public Maplic00 getMaplic00() {
		return this.maplic00;
	}

	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}
	
	public Mtabla00 getMtabla00() {
		return this.mtabla00;
	}

	public void setMtabla00(Mtabla00 mtabla00) {
		this.mtabla00 = mtabla00;
	}
	
}