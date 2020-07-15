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

import com.tlm.faelecEntities.model.entities.Mgenus00;

/**
 * The persistent class for the MCONCA00 database table.
 * 
 */
@Entity
@Table(name="MACDIO00")
public class Macdio00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDCDIO", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idcdio;

	@Column(name="DSCDIO", length=100, nullable=false)
	private String dscdio;
	
	@Column(name="NOTDIO", length=60)
	private String notdio;

	@Column(name="REGDIO", nullable=false)
	private boolean regdio;

	@Column(name="USEDIO", length=20, nullable=false)
	private String usedio;

	@Column(name="PRGDIO", length=500, nullable=false)
	private String prgdio;	
	
	@Column(name="FEADIO", nullable=false)
	private Date feadio;
	
	@Column(name="MAQDIO", length=100, nullable=false)
	private String maqdio;	

	//bi-directional many-to-one association to Maccio00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IACDIO", referencedColumnName="idccio")
	private Maccio00 maccio00;
	
	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDADIO")
	private Mgencg00 mgencg00;
	
	
	//bi-directional many-to-one association to MESTAD00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDEDIO")
	private Mestad00 mestad00;

    public Macdio00() {
    }

	@Override
	public String toString() {
		return "Macdio00 [idcdio=" + idcdio + ", dscdio=" + dscdio +", notdio=" + notdio
				+ ", regdio=" + regdio + ", usedio=" + usedio + ", prgdio="
				+ prgdio + ", feadio=" + feadio + ", maqdio=" + maqdio
				+ ", mestad00=" + (mestad00!=null?mestad00.getIdeses()+"-"+mestad00.getCotres():null)
				+ "]";
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

	public String toStringId()
	{
		return "idcdio="+idcdio;
	}

	
	/*
	 * Getters and Setters
	 */
	public Integer getIdcdio() {
		return idcdio;
	}

	public void setIdcdio(Integer idcdio) {
		this.idcdio = idcdio;
	}

	public String getDscdio() {
		return dscdio;
	}

	public void setDscdio(String dscdio) {
		this.dscdio = dscdio;
	}

	public boolean isRegdio() {
		return regdio;
	}

	public void setRegdio(boolean regdio) {
		this.regdio = regdio;
	}

	public String getUsedio() {
		return usedio;
	}

	public void setUsedio(String usedio) {
		this.usedio = usedio;
	}

	public String getPrgdio() {
		return prgdio;
	}

	public void setPrgdio(String prgdio) {
		this.prgdio = prgdio;
	}

	public Date getFeadio() {
		return feadio;
	}

	public void setFeadio(Date feadio) {
		this.feadio = feadio;
	}

	public String getMaqdio() {
		return maqdio;
	}

	public void setMaqdio(String maqdio) {
		this.maqdio = maqdio;
	}

	public Maccio00 getMaccio00() {
		return maccio00;
	}

	public void setMaccio00(Maccio00 maccio00) {
		this.maccio00 = maccio00;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public String getNotdio() {
		return notdio;
	}

	public void setNotdio(String notdio) {
		this.notdio = notdio;
	}

	public Mgencg00 getMgencg00() {
		return mgencg00;
	}

	public void setMgencg00(Mgencg00 mgencg00) {
		this.mgencg00 = mgencg00;
	}
	
}