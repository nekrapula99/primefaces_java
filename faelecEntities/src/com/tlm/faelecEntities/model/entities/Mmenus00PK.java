package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;

/**
 * The primary key class for the MMENUS00 database table.
 * 
 */
public class Mmenus00PK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8301328451640627440L;
	private String aplmen;
	private String codmen;
	
	
	/*
	 * Metodos Get y Set
	 */
	public Mmenus00PK(){}

	public String getAplmen() {
		return aplmen;
	}

	public void setAplmen(String aplmen) {
		this.aplmen = aplmen;
	}

	public String getCodmen() {
		return codmen;
	}

	public void setCodmen(String codmen) {
		this.codmen = codmen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aplmen == null) ? 0 : aplmen.hashCode());
		result = prime * result + ((codmen == null) ? 0 : codmen.hashCode());
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
		Mmenus00PK other = (Mmenus00PK) obj;
		if (aplmen == null) {
			if (other.aplmen != null)
				return false;
		} else if (!aplmen.equals(other.aplmen))
			return false;
		if (codmen == null) {
			if (other.codmen != null)
				return false;
		} else if (!codmen.equals(other.codmen))
			return false;
		return true;
	}
}