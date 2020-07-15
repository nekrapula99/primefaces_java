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
import com.tlm.faelecEntities.model.entities.Mcampo00PK;
import com.tlm.faelecEntities.model.entities.Mtabla00;

/**
 * The persistent class for the MCAMPO00 database table.
 * 
 */
@Entity
@Table(name="MCAMPO00")
public class Mcampo00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Mcampo00PK id;

	@Column(name="REQCAM", nullable=false, length=1)
	private String reqcam;

	@Column(name="REQPAN", length=1)
	private String reqpan;

	//bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APLCAM", nullable=false, insertable=false, updatable=false)
	private Maplic00 maplic00;

	//bi-directional many-to-one association to Mtabla00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="APLCAM", referencedColumnName="APLTAB", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="TABCAM", referencedColumnName="CODTAB", nullable=false, insertable=false, updatable=false)
		})
	private Mtabla00 mtabla00;

    public Mcampo00() {
    }

	public Mcampo00PK getId() {
		return this.id;
	}

	public void setId(Mcampo00PK id) {
		this.id = id;
	}
	
	public String getReqcam() {
		return this.reqcam;
	}

	public void setReqcam(String reqcam) {
		this.reqcam = reqcam;
	}

	public String getReqpan() {
		return this.reqpan;
	}

	public void setReqpan(String reqpan) {
		this.reqpan = reqpan;
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