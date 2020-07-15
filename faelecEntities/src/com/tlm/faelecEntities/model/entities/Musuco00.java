package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MUSUCO00 database table.
 * 
 */
@Entity
@Table(name="MUSUCO00")
@NamedQuery(name="Musuco00.findAll", query="SELECT m FROM Musuco00 m")
public class Musuco00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="APLUCO")
	private String apluco;

	@Column(name="AREUCO")
	private String areuco;

	@Column(name="CIAUCO")
	private String ciauco;

	@Column(name="GRUUCO")
	private String gruuco;

	@Column(name="INCDCO")
	private String incdco;

	@Column(name="USUUCO")
	private String usuuco;

	public Musuco00() {
	}

	public String getApluco() {
		return this.apluco;
	}

	public void setApluco(String apluco) {
		this.apluco = apluco;
	}

	public String getAreuco() {
		return this.areuco;
	}

	public void setAreuco(String areuco) {
		this.areuco = areuco;
	}

	public String getCiauco() {
		return this.ciauco;
	}

	public void setCiauco(String ciauco) {
		this.ciauco = ciauco;
	}

	public String getGruuco() {
		return this.gruuco;
	}

	public void setGruuco(String gruuco) {
		this.gruuco = gruuco;
	}

	public String getIncdco() {
		return this.incdco;
	}

	public void setIncdco(String incdco) {
		this.incdco = incdco;
	}

	public String getUsuuco() {
		return this.usuuco;
	}

	public void setUsuuco(String usuuco) {
		this.usuuco = usuuco;
	}

}