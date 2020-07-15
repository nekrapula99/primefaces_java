package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="MACCGR00")
public class Maccgr00 implements Serializable
{
  /**
	 * 
	 */
  private static final long serialVersionUID = 5663255695718423033L;
  
	@EmbeddedId	
	private Maccgr00PK id;
  
  //bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COAPAC", nullable=false, insertable=false, updatable=false)
	private Maplic00 maplic00;
	
	//bi-directional many-to-one association to Mtabla00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="COAPAC", referencedColumnName="APLGRU", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="COGRAC", referencedColumnName="CODGRU", nullable=false, insertable=false, updatable=false)
	})
	private Mgrupo00 mgrupo00;
	
	
	//bi-directional many-to-one association to Mtabla00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="COAPAC", referencedColumnName="APLMEN", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="COMEAC", referencedColumnName="CODMEN", nullable=false, insertable=false, updatable=false)
	})
	private Mmenus00 mmenus00;

	
	public Maccgr00()
	{
		
	}

	public Maccgr00PK getId() {
		return id;
	}


	public void setId(Maccgr00PK id) {
		this.id = id;
	}


	public Maplic00 getMaplic00() {
		return maplic00;
	}


	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}


	public Mgrupo00 getMgrupo00() {
		return mgrupo00;
	}


	public void setMgrupo00(Mgrupo00 mgrupo00) {
		this.mgrupo00 = mgrupo00;
	}


	public Mmenus00 getMmenus00() {
		return mmenus00;
	}


	public void setMmenus00(Mmenus00 mmenus00) {
		this.mmenus00 = mmenus00;
	}

	@Override
	public String toString() {
		return "Maccgr00 [id=" + id + ", maplic00=" + maplic00 + ", mgrupo00="
				+ mgrupo00 + ", mmenus00=" + mmenus00 + "]";
	}
  
  
}
