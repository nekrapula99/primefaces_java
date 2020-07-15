package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Maccgr00PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8458426099979017052L;

	@Column(name="COAPAC", unique=true, nullable=false, length=20)
	private String coapac;

	@Column(name="COGRAC", unique=true, nullable=false, length=20)
	private String cograc;

	@Column(name="COMEAC", unique=true, nullable=false, length=20)
	private String comeac;
	
	@Column(name="OPMEAC", unique=true, nullable=false, length=18)
	private Integer opmeac;
	
	@Column(name="NRITAC", unique=true, nullable=false, length=18)
	private Integer nritac;
	
	@Column(name="TRACAC", unique=true, nullable=false, length=4)
	private String tracac;

	@Column(name="COACAC", unique=true, nullable=false, length=20)
	private String coacac;
	
	public Maccgr00PK()
	{
		
	}

	public String getCoapac() {
		return coapac;
	}

	public void setCoapac(String coapac) {
		this.coapac = coapac;
	}

	public String getCograc() {
		return cograc;
	}

	public void setCograc(String cograc) {
		this.cograc = cograc;
	}

	public String getComeac() {
		return comeac;
	}

	public void setComeac(String comeac) {
		this.comeac = comeac;
	}

	public Integer getOpmeac() {
		return opmeac;
	}

	public void setOpmeac(Integer opmeac) {
		this.opmeac = opmeac;
	}

	public Integer getNritac() {
		return nritac;
	}

	public void setNritac(Integer nritac) {
		this.nritac = nritac;
	}

	public String getTracac() {
		return tracac;
	}

	public void setTracac(String tracac) {
		this.tracac = tracac;
	}

	public String getCoacac() {
		return coacac;
	}

	public void setCoacac(String coacac) {
		this.coacac = coacac;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coacac == null) ? 0 : coacac.hashCode());
		result = prime * result + ((coapac == null) ? 0 : coapac.hashCode());
		result = prime * result + ((cograc == null) ? 0 : cograc.hashCode());
		result = prime * result + ((comeac == null) ? 0 : comeac.hashCode());
		result = prime * result + ((nritac == null) ? 0 : nritac.hashCode());
		result = prime * result + ((opmeac == null) ? 0 : opmeac.hashCode());
		result = prime * result + ((tracac == null) ? 0 : tracac.hashCode());
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
		Maccgr00PK other = (Maccgr00PK) obj;
		if (coacac == null) {
			if (other.coacac != null)
				return false;
		} else if (!coacac.equals(other.coacac))
			return false;
		if (coapac == null) {
			if (other.coapac != null)
				return false;
		} else if (!coapac.equals(other.coapac))
			return false;
		if (cograc == null) {
			if (other.cograc != null)
				return false;
		} else if (!cograc.equals(other.cograc))
			return false;
		if (comeac == null) {
			if (other.comeac != null)
				return false;
		} else if (!comeac.equals(other.comeac))
			return false;
		if (nritac == null) {
			if (other.nritac != null)
				return false;
		} else if (!nritac.equals(other.nritac))
			return false;
		if (opmeac == null) {
			if (other.opmeac != null)
				return false;
		} else if (!opmeac.equals(other.opmeac))
			return false;
		if (tracac == null) {
			if (other.tracac != null)
				return false;
		} else if (!tracac.equals(other.tracac))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Maccgr00PK [coapac=" + coapac + ", cograc=" + cograc
				+ ", comeac=" + comeac + ", opmeac=" + opmeac + ", nritac="
				+ nritac + ", tracac=" + tracac + ", coacac=" + coacac + "]";
	}
	
	
	
	
}
