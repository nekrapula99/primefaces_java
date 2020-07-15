package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MOPCGR00 database table.
 * 
 */
@Entity
@Table(name="MOPCGR00")
@NamedQuery(name="Mopcgr00.findAll", query="SELECT m FROM Mopcgr00 m")
public class Mopcgr00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Mopcgr00PK id;

	//bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APLOPG",nullable=false, insertable=false, updatable=false)
	private Maplic00 maplic00;

	//bi-directional many-to-one association to Mgrupo00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="APLOPG", referencedColumnName="APLGRU",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="GRUOPG", referencedColumnName="CODGRU",nullable=false, insertable=false, updatable=false)
		})
	private Mgrupo00 mgrupo00;

	//bi-directional many-to-one association to Mmenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="APLOPG", referencedColumnName="APLMEN",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="MENOPG", referencedColumnName="CODMEN",nullable=false, insertable=false, updatable=false)
		})
	private Mmenus00 mmenus00;

	public Mopcgr00() {
	}

	public Mopcgr00PK getId() {
		return this.id;
	}

	public void setId(Mopcgr00PK id) {
		this.id = id;
	}

	public Maplic00 getMaplic00() {
		return this.maplic00;
	}

	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}

	public Mgrupo00 getMgrupo00() {
		return this.mgrupo00;
	}

	public void setMgrupo00(Mgrupo00 mgrupo00) {
		this.mgrupo00 = mgrupo00;
	}

	public Mmenus00 getMmenus00() {
		return this.mmenus00;
	}

	public void setMmenus00(Mmenus00 mmenus00) {
		this.mmenus00 = mmenus00;
	}

}