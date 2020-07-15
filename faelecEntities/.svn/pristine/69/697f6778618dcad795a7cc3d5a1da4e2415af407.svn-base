package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Mgrupo00PK implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7903156413612396288L;

	@Column(name="APLGRU", unique=true, nullable=false, length=20)
	private String aplgru;

	@Column(name="CODGRU", unique=true, nullable=false, length=20)
	private String codgru;
	
	public Mgrupo00PK()
	{
		
	}

	public String getAplgru() {
		return aplgru;
	}

	public void setAplgru(String aplgru) {
		this.aplgru = aplgru;
	}

	public String getCodgru() {
		return codgru;
	}

	public void setCodgru(String codgru) {
		this.codgru = codgru;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aplgru == null) ? 0 : aplgru.hashCode());
		result = prime * result + ((codgru == null) ? 0 : codgru.hashCode());
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
		Mgrupo00PK other = (Mgrupo00PK) obj;
		if (aplgru == null) {
			if (other.aplgru != null)
				return false;
		} else if (!aplgru.equals(other.aplgru))
			return false;
		if (codgru == null) {
			if (other.codgru != null)
				return false;
		} else if (!codgru.equals(other.codgru))
			return false;
		return true;
	}
	
	
}
