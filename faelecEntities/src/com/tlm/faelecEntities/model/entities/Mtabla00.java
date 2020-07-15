package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mdecai00;
import com.tlm.faelecEntities.model.entities.Mtabla00PK;

/**
 * The persistent class for the MTABLA00 database table.
 * 
 */
@Entity
@Table(name="MTABLA00")
public class Mtabla00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Mtabla00PK id;

	@Column(name="NOMTAB", nullable=false, length=2000)
	private String nomtab;

	@Column(name="USPAAB", length=1)
	private String uspaab;

	//bi-directional many-to-one association to Mcampo00
	@OneToMany(mappedBy="mtabla00")
	private List<Mcampo00> mcampo00s;

	//bi-directional many-to-one association to Mdecai00
	@OneToMany(mappedBy="mtabla00")
	private List<Mdecai00> mdecai00s;

	//bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APLTAB", nullable=false, insertable=false, updatable=false)
	private Maplic00 maplic00;

    public Mtabla00() {
    }

	public Mtabla00PK getId() {
		return this.id;
	}

	public void setId(Mtabla00PK id) {
		this.id = id;
	}
	
	public String getNomtab() {
		return this.nomtab;
	}

	public void setNomtab(String nomtab) {
		this.nomtab = nomtab;
	}

	public String getUspaab() {
		return this.uspaab;
	}

	public void setUspaab(String uspaab) {
		this.uspaab = uspaab;
	}

	public List<Mcampo00> getMcampo00s() {
		return this.mcampo00s;
	}

	public void setMcampo00s(List<Mcampo00> mcampo00s) {
		this.mcampo00s = mcampo00s;
	}
	
	public List<Mdecai00> getMdecai00s() {
		return this.mdecai00s;
	}

	public void setMdecai00s(List<Mdecai00> mdecai00s) {
		this.mdecai00s = mdecai00s;
	}
	
	public Maplic00 getMaplic00() {
		return this.maplic00;
	}

	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}
	
}