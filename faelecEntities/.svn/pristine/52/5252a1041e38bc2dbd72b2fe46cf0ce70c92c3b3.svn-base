package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the MOPCGR00 database table.
 * 
 */
@Embeddable
public class Mopcgr00PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APLOPG", insertable=false, updatable=false, length=20)
	private String aplopg;

	@Column(name="GRUOPG", insertable=false, updatable=false, length=20)
	private String gruopg;

	@Column(name="MENOPG", insertable=false, updatable=false, length=20)
	private String menopg;

	@Column(name="NUMOPG")
	private long numopg;

	public Mopcgr00PK() {
	}
	public String getAplopg() {
		return this.aplopg;
	}
	public void setAplopg(String aplopg) {
		this.aplopg = aplopg;
	}
	public String getGruopg() {
		return this.gruopg;
	}
	public void setGruopg(String gruopg) {
		this.gruopg = gruopg;
	}
	public String getMenopg() {
		return this.menopg;
	}
	public void setMenopg(String menopg) {
		this.menopg = menopg;
	}
	public long getNumopg() {
		return this.numopg;
	}
	public void setNumopg(long numopg) {
		this.numopg = numopg;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Mopcgr00PK)) {
			return false;
		}
		Mopcgr00PK castOther = (Mopcgr00PK)other;
		return 
			this.aplopg.equals(castOther.aplopg)
			&& this.gruopg.equals(castOther.gruopg)
			&& this.menopg.equals(castOther.menopg)
			&& (this.numopg == castOther.numopg);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.aplopg.hashCode();
		hash = hash * prime + this.gruopg.hashCode();
		hash = hash * prime + this.menopg.hashCode();
		hash = hash * prime + ((int) (this.numopg ^ (this.numopg >>> 32)));
		
		return hash;
	}
}