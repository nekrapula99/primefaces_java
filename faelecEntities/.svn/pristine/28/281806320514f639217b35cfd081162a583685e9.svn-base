package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;

/**
 * The primary key class for the MOPCME00 database table.
 * 
 */

public class Mopcme00PK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -653303218711692128L;
	private String aplopm;
	private String menopm;
	private String numopm;
	
	/*
	 * Get y SEt
	 */
	public String getAplopm() {
		return aplopm;
	}
	public void setAplopm(String aplopm) {
		this.aplopm = aplopm;
	}
	public String getMenopm() {
		return menopm;
	}
	public void setMenopm(String menopm) {
		this.menopm = menopm;
	}
	public String getNumopm() {
		return numopm;
	}
	public void setNumopm(String numopm) {
		this.numopm = numopm;
	}
	
	/*
	 * ..
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aplopm == null) ? 0 : aplopm.hashCode());
		result = prime * result + ((menopm == null) ? 0 : menopm.hashCode());
		result = prime * result + ((numopm == null) ? 0 : numopm.hashCode());
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
		Mopcme00PK other = (Mopcme00PK) obj;
		if (aplopm == null) {
			if (other.aplopm != null)
				return false;
		} else if (!aplopm.equals(other.aplopm))
			return false;
		if (menopm == null) {
			if (other.menopm != null)
				return false;
		} else if (!menopm.equals(other.menopm))
			return false;
		if (numopm == null) {
			if (other.numopm != null)
				return false;
		} else if (!numopm.equals(other.numopm))
			return false;
		return true;
	}
}