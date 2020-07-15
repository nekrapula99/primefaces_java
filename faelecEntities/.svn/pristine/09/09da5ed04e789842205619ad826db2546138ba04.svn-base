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
@Table(name = "TFESFU00")
public class Tfesfu00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDEFFU",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ideffu;
	
	// FK [id. Cotizacion] TQIGQG00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOFU", nullable=false)
	private Tqigqg00 tqigqg00;
	
	//FK TQDFQF00 id. Cuota
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCUFU", nullable=false)
	private Tqdfqf00 tqdfqf00;
	
	//FK Id Factura TFACFC00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTFFU", nullable=false)
	private Tfacfc00 tfacfc00;
	
	//Id estado Cotizacion MESTAD00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESFU", nullable=false)
	private Mestad00 mestad00;
	
	//fecha facturacion de la cotizacion
	@Column(name="FEESFU", nullable=false)
	private Date feesfu;

	//Borrado logico
	@Column(name="REGCFU", nullable=false)
	private boolean regcfu;

	//user
	@Column(name="USECFU", length=20, nullable=false)
	private String usecfu;

	@Column(name="PRGCFU", length=500, nullable=false)
	private String prgcfu;

	@Column(name="FEACFU", nullable=false)
	private Date feacfu;
	
	//maquina
	@Column(name="MAQCFU",  length=100, nullable=false)
	private String maqcfu;

	public int getIdeffu() {
		return ideffu;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public Tfacfc00 getTfacfc00() {
		return tfacfc00;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public Date getFeesfu() {
		return feesfu;
	}

	public boolean isRegcfu() {
		return regcfu;
	}

	public String getUsecfu() {
		return usecfu;
	}

	public String getPrgcfu() {
		return prgcfu;
	}

	public Date getFeacfu() {
		return feacfu;
	}

	public String getMaqcfu() {
		return maqcfu;
	}

	public void setIdeffu(int ideffu) {
		this.ideffu = ideffu;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}

	public void setTfacfc00(Tfacfc00 tfacfc00) {
		this.tfacfc00 = tfacfc00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public void setFeesfu(Date feesfu) {
		this.feesfu = feesfu;
	}

	public void setRegcfu(boolean regcfu) {
		this.regcfu = regcfu;
	}

	public void setUsecfu(String usecfu) {
		this.usecfu = usecfu;
	}

	public void setPrgcfu(String prgcfu) {
		this.prgcfu = prgcfu;
	}

	public void setFeacfu(Date feacfu) {
		this.feacfu = feacfu;
	}

	public void setMaqcfu(String maqcfu) {
		this.maqcfu = maqcfu;
	}

	@Override
	public String toString() {
		return "Tfesfu00 [ideffu=" + ideffu 
				
				 + ", feesfu=" + feesfu + ", regcfu="
				+ regcfu + ", usecfu=" + usecfu + ", prgcfu=" + prgcfu
				+ ", feacfu=" + feacfu + ", maqcfu=" + maqcfu 
				+ ", tqigqg00=" + (tqigqg00!=null?tqigqg00.getIdtqqg()+"-"+tqigqg00.getNroqqg():null)
				+ ", tqdfqf00=" + (tqdfqf00!=null?tqdfqf00.getIdcpqf()+"-"+tqdfqf00.getNrcpqf():null)
				+ ", tfacfc00=" + (tfacfc00!=null?tfacfc00.getIdfcfc()+"-"+tfacfc00.getNrfafc():null)
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
		return "ideffu="+ideffu;
	}
	
	public Tfesfu00(){}
	
	
}
