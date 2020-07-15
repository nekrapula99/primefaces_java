package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="MAREAS00")
public class Mareas00 implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Mareas00PK id;
	
	@Column(name = "NOMARE", length=2000)
	private String nomare;

	//bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APLARE", nullable=false, insertable=false, updatable=false)
	private Maplic00 maplic00;
	
	public Mareas00PK getId() {
		return id;
	}

	public void setId(Mareas00PK id) {
		this.id = id;
	}

	public String getNomare() {
		return nomare;
	}

	public void setNomare(String nomare) {
		this.nomare = nomare;
	}

	public Maplic00 getMaplic00() {
		return maplic00;
	}

	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}
	


}
