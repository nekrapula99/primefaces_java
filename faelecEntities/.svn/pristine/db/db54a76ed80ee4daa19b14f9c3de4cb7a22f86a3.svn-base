package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.tlm.faelecEntities.model.entities.Mconca00;

/**
 * The persistent class for the MABCUB00 database table.
 * 
 */
@Entity
@Table(name="MABCUB00")
public class Mabcub00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTRUB", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrub;

	@Column(name="CODUUB", unique=true, nullable=false, length=20)
	private String coduub;

	@Column(name="FEACUB", nullable=false)
	private Date feacub;

	@Column(name="MAQUUB", nullable=false, length=100)
	private String maquub;

	@Column(name="ORPRUB", precision=5)
	private Integer orprub;

	@Column(name="PRGMUB", nullable=false, length=20)
	private String prgmub;

	@Column(name="REGIUB", nullable=false)
	private boolean regiub;

	@Column(name="UBIAUB", length=50)
	private String ubiaub;

	@Column(name="UBIBUB", length=50)
	private String ubibub;

	@Column(name="UBICUB", length=50)
	private String ubicub;

	@Column(name="USERUB", nullable=false, length=20)
	private String userub;

	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMUB", unique=true)
	private Mconca00 mconca00;

    public Mabcub00() {
    }

	public int getIdtrub() {
		return this.idtrub;
	}

	public void setIdtrub(int idtrub) {
		this.idtrub = idtrub;
	}

	public String getCoduub() {
		return this.coduub;
	}

	public void setCoduub(String coduub) {
		this.coduub = coduub;
	}

	public Date getFeacub() {
		return this.feacub;
	}

	public void setFeacub(Date feacub) {
		this.feacub = feacub;
	}

	public String getMaquub() {
		return this.maquub;
	}

	public void setMaquub(String maquub) {
		this.maquub = maquub;
	}

	public Integer getOrprub() {
		return this.orprub;
	}

	public void setOrprub(Integer orprub) {
		this.orprub = orprub;
	}

	public String getPrgmub() {
		return this.prgmub;
	}

	public void setPrgmub(String prgmub) {
		this.prgmub = prgmub;
	}

	public boolean getRegiub() {
		return this.regiub;
	}

	public void setRegiub(boolean regiub) {
		this.regiub = regiub;
	}

	public String getUbiaub() {
		return this.ubiaub;
	}

	public void setUbiaub(String ubiaub) {
		this.ubiaub = ubiaub;
	}

	public String getUbibub() {
		return this.ubibub;
	}

	public void setUbibub(String ubibub) {
		this.ubibub = ubibub;
	}

	public String getUbicub() {
		return this.ubicub;
	}

	public void setUbicub(String ubicub) {
		this.ubicub = ubicub;
	}

	public String getUserub() {
		return this.userub;
	}

	public void setUserub(String userub) {
		this.userub = userub;
	}

	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	@Override
	public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar");
        }
        return obj;
    }
	@Override
	public String toString() {
		return "Mabcub00 [idtrub=" + idtrub + ", coduub=" + coduub
				+ ", ubiaub=" + ubiaub + ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+", ubibub=" + ubibub + ", ubicub=" + ubicub + ", regiub=" + regiub + "]";
	}
	public String toStringId()
	{
		return "idtrub="+idtrub;
	}
	
}