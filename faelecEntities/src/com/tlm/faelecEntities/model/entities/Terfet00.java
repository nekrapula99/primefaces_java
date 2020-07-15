package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "TERFET00")
public class Terfet00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDENET",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idenet;
	
	// FK Tfacfc00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFAET", nullable=false)
	private Tfacfc00 tfacfc00;
	
	//FK MESTAD00 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESET", nullable=false)
	private Mestad00 mestad00;
	
	//Fecha Envio
	@Column(name="FEENET", nullable=false)
	private Date feenet;
	
	// Emisor del Envio
	@Column(name="EMENET", length=1, nullable=false)
	private String emenet;
	
	// Receptor del envio
	@Column(name="REENET", length=1, nullable=false)
	private String reenet;
	
	// Observaciones
	@Column(name="OBREET", length=300, nullable=false)
	private String obreet;
	
	// Envio o Recepcion con relacion a Factura
	@Column(name="ENREET", length=1, nullable=false)
	private String enreet;
		

	//Borrado logico
	@Column(name="REGCET", nullable=false)
	private boolean regcet;

	//user
	@Column(name="USECET", length=20, nullable=false)
	private String usecet;

	@Column(name="PRGCET", length=500, nullable=false)
	private String prgcet;

	@Column(name="FEACET", nullable=false)
	private Date feacet;
	
	//maquina
	@Column(name="MAQCET",  length=100, nullable=false)
	private String maqcet;

	
	
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
		return "idenet="+idenet;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Terfet00 [idenet=" + idenet
				+ ", feenet=" + feenet + ", emenet="
				+ emenet + ", reenet=" + reenet + ", obreet=" + obreet
				+ ", enreet=" + enreet + ", regcet=" + regcet + ", usecet="
				+ usecet + ", prgcet=" + prgcet + ", feacet=" + feacet
				+ ", maqcet=" + maqcet
				+ ", tfacfc00=" + (tfacfc00!=null?tfacfc00.getIdfcfc()+"-"+tfacfc00.getNrfafc():null)
				+ ", mestad00=" + (mestad00!=null?mestad00.getIdeses()+"-"+mestad00.getCotres():null)
				+ "]";
	}

	public Terfet00() {}

	public int getIdenet() {
		return idenet;
	}

	public void setIdenet(int idenet) {
		this.idenet = idenet;
	}

	public Tfacfc00 getTfacfc00() {
		return tfacfc00;
	}

	public void setTfacfc00(Tfacfc00 tfacfc00) {
		this.tfacfc00 = tfacfc00;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public Date getFeenet() {
		return feenet;
	}

	public void setFeenet(Date feenet) {
		this.feenet = feenet;
	}

	public String getEmenet() {
		return emenet;
	}

	public void setEmenet(String emenet) {
		this.emenet = emenet;
	}

	public String getReenet() {
		return reenet;
	}

	public void setReenet(String reenet) {
		this.reenet = reenet;
	}

	public String getObreet() {
		return obreet;
	}

	public void setObreet(String obreet) {
		this.obreet = obreet;
	}

	public String getEnreet() {
		return enreet;
	}

	public void setEnreet(String enreet) {
		this.enreet = enreet;
	}

	public boolean isRegcet() {
		return regcet;
	}

	public void setRegcet(boolean regcet) {
		this.regcet = regcet;
	}

	public String getUsecet() {
		return usecet;
	}

	public void setUsecet(String usecet) {
		this.usecet = usecet;
	}

	public String getPrgcet() {
		return prgcet;
	}

	public void setPrgcet(String prgcet) {
		this.prgcet = prgcet;
	}

	public Date getFeacet() {
		return feacet;
	}

	public void setFeacet(Date feacet) {
		this.feacet = feacet;
	}

	public String getMaqcet() {
		return maqcet;
	}

	public void setMaqcet(String maqcet) {
		this.maqcet = maqcet;
	}
	
	
}
