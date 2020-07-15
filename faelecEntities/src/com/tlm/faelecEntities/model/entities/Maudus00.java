package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MAUDUS00")
public class Maudus00 implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USUAUD",unique=true,nullable=false,length=30)
	private String usuaud;
	
	@Column(name= "FECAUD",nullable=false, length=30)
	private String fecaud;
	
	@Column(name= "IPUAUD", nullable=false,length=30)
	private String ipuaud;
	
	@Column(name= "SESAUD",nullable=false, length=50)
	private String sesaud;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecaud == null) ? 0 : fecaud.hashCode());
		result = prime * result + ((ipuaud == null) ? 0 : ipuaud.hashCode());
		result = prime * result + ((sesaud == null) ? 0 : sesaud.hashCode());
		result = prime * result + ((usuaud == null) ? 0 : usuaud.hashCode());
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
		Maudus00 other = (Maudus00) obj;
		if (fecaud == null) {
			if (other.fecaud != null)
				return false;
		} else if (!fecaud.equals(other.fecaud))
			return false;
		if (ipuaud == null) {
			if (other.ipuaud != null)
				return false;
		} else if (!ipuaud.equals(other.ipuaud))
			return false;
		if (sesaud == null) {
			if (other.sesaud != null)
				return false;
		} else if (!sesaud.equals(other.sesaud))
			return false;
		if (usuaud == null) {
			if (other.usuaud != null)
				return false;
		} else if (!usuaud.equals(other.usuaud))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Maudus00 [usuaud=" + usuaud + ", fecaud=" + fecaud
				+ ", ipuaud=" + ipuaud + ", sesaud=" + sesaud + "]";
	}

	public String getUsuaud() {
		return usuaud;
	}

	public void setUsuaud(String usuaud) {
		this.usuaud = usuaud;
	}

	public String getFecaud() {
		return fecaud;
	}

	public void setFecaud(String fecaud) {
		this.fecaud = fecaud;
	}

	public String getIpuaud() {
		return ipuaud;
	}

	public void setIpuaud(String ipuaud) {
		this.ipuaud = ipuaud;
	}

	public String getSesaud() {
		return sesaud;
	}

	public void setSesaud(String sesaud) {
		this.sesaud = sesaud;
	}

}
