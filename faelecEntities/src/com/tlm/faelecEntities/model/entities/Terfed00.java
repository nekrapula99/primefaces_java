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
@Table(name = "TERFED00")
public class Terfed00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDENED",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idened;
	
	// FK Tfacfc00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFAED", nullable=false)
	private Tfacfc00 tfacfc00;
	
	//FK MESTAD00 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESED", nullable=false)
	private Mestad00 mestad00;
	
	//Fecha Envio
	@Column(name="FEENED", nullable=false)
	private Date feened;
	
	// Emisor del Envio
	@Column(name="EMENED", length=1, nullable=false)
	private String emened;
	
	// Receptor del envio
	@Column(name="REENED", length=1, nullable=false)
	private String reened;
	
	// Observaciones
	@Column(name="OBREED", length=300, nullable=false)
	private String obreed;
	
	// Envio o Recepcion con relacion a Factura
	@Column(name="ENREED", length=1, nullable=false)
	private String enreed;
		

	//Borrado logico
	@Column(name="REGCED", nullable=false)
	private boolean regced;

	//user
	@Column(name="USECED", length=20, nullable=false)
	private String useced;

	@Column(name="PRGCED", length=500, nullable=false)
	private String prgced;

	@Column(name="FEACED", nullable=false)
	private Date feaced;
	
	//maquina
	@Column(name="MAQCED",  length=100, nullable=false)
	private String maqced;

	
	
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
		return "idened="+idened;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Terfed00 [idened=" + idened
				+ ", feened=" + feened + ", emened="
				+ emened + ", reened=" + reened + ", obreed=" + obreed
				+ ", enreed=" + enreed + ", regced=" + regced + ", useced="
				+ useced + ", prgced=" + prgced + ", feaced=" + feaced
				+ ", maqced=" + maqced 
				+ ", tfacfc00=" + (tfacfc00!=null?tfacfc00.getIdfcfc()+"-"+tfacfc00.getNrfafc():null)
				+ ", mestad00=" + (mestad00!=null?mestad00.getIdeses()+"-"+mestad00.getCotres():null)
				+ "]";
				
	}

	public Terfed00() {}

	public int getIdened() {
		return idened;
	}

	public void setIdened(int idened) {
		this.idened = idened;
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

	public Date getFeened() {
		return feened;
	}

	public void setFeened(Date feened) {
		this.feened = feened;
	}

	public String getEmened() {
		return emened;
	}

	public void setEmened(String emened) {
		this.emened = emened;
	}

	public String getReened() {
		return reened;
	}

	public void setReened(String reened) {
		this.reened = reened;
	}

	public String getObreed() {
		return obreed;
	}

	public void setObreed(String obreed) {
		this.obreed = obreed;
	}

	public String getEnreed() {
		return enreed;
	}

	public void setEnreed(String enreed) {
		this.enreed = enreed;
	}

	public boolean isRegced() {
		return regced;
	}

	public void setRegced(boolean regced) {
		this.regced = regced;
	}

	public String getUseced() {
		return useced;
	}

	public void setUseced(String useced) {
		this.useced = useced;
	}

	public String getPrgced() {
		return prgced;
	}

	public void setPrgced(String prgced) {
		this.prgced = prgced;
	}

	public Date getFeaced() {
		return feaced;
	}

	public void setFeaced(Date feaced) {
		this.feaced = feaced;
	}

	public String getMaqced() {
		return maqced;
	}

	public void setMaqced(String maqced) {
		this.maqced = maqced;
	}

	
}
