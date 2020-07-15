package com.tlm.faelecEntities.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MGRUPO00")
public class Mgrupo00 {
	
	@EmbeddedId	
	private Mgrupo00PK id;
	
	@Column(name="NOMGRU", nullable=false,length=2000)
	private String nomgru;

	@Column(name="INDGRU", length=1)
	private String indgru;

	@Column(name="ADMGRU",  length=1)
	private String admgru;	
	
	public Mgrupo00()
	{
		
	}	
	//bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APLGRU", nullable=false, insertable=false, updatable=false)
	private Maplic00 maplic00;
	
	//bi-directional many-to-one association to Mtabla00
	@OneToMany(mappedBy="mgrupo00")
	private List<Maccgr00> maccgr00s;	

	public Mgrupo00PK getId() {
		return id;
	}

	public void setId(Mgrupo00PK id) {
		this.id = id;
	}

	public String getNomgru() {
		return nomgru;
	}

	public void setNomgru(String nomgru) {
		this.nomgru = nomgru;
	}

	public String getIndgru() {
		return indgru;
	}

	public void setIndgru(String indgru) {
		this.indgru = indgru;
	}

	public String getAdmgru() {
		return admgru;
	}

	public void setAdmgru(String admgru) {
		this.admgru = admgru;
	}

	public Maplic00 getMaplic00() {
		return maplic00;
	}

	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}

	@Override
	public String toString() {
		return "Mgrupo00 [id=" + id + ", nomgru=" + nomgru + ", indgru="
				+ indgru + ", admgru=" + admgru + ", maplic00=" + maplic00
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admgru == null) ? 0 : admgru.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indgru == null) ? 0 : indgru.hashCode());
		result = prime * result
				+ ((maplic00 == null) ? 0 : maplic00.hashCode());
		result = prime * result + ((nomgru == null) ? 0 : nomgru.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mgrupo00 other = (Mgrupo00) obj;
		if (admgru == null) {
			if (other.admgru != null)
				return false;
		} else if (!admgru.equals(other.admgru))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indgru == null) {
			if (other.indgru != null)
				return false;
		} else if (!indgru.equals(other.indgru))
			return false;
		if (maplic00 == null) {
			if (other.maplic00 != null)
				return false;
		} else if (!maplic00.equals(other.maplic00))
			return false;
		if (nomgru == null) {
			if (other.nomgru != null)
				return false;
		} else if (!nomgru.equals(other.nomgru))
			return false;
		return true;
	}

	public List<Maccgr00> getMaccgr00s() {
		return maccgr00s;
	}

	public void setMaccgr00s(List<Maccgr00> maccgr00s) {
		this.maccgr00s = maccgr00s;
	}

	
	
	

}
