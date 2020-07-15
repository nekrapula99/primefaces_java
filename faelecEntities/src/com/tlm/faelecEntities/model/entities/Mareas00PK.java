package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Mareas00PK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="APLARE", unique=true, nullable=false, length=20)
	private String aplare;

	@Column(name="CODARE", unique=true, nullable=false, length=20)
	private String codare;
	
	public Mareas00PK() {
    }

	public String getAplare() {
		return aplare;
	}

	public void setAplare(String aplare) {
		this.aplare = aplare;
	}

	public String getCodare() {
		return codare;
	}

	public void setCodare(String codare) {
		this.codare = codare;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aplare == null) ? 0 : aplare.hashCode());
		result = prime * result + ((codare == null) ? 0 : codare.hashCode());
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
		Mareas00PK other = (Mareas00PK) obj;
		if (aplare == null) {
			if (other.aplare != null)
				return false;
		} else if (!aplare.equals(other.aplare))
			return false;
		if (codare == null) {
			if (other.codare != null)
				return false;
		} else if (!codare.equals(other.codare))
			return false;
		return true;
	}
	
	

}
