package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity	 
@Table(name="MTESTE00")
public class Mteste00 implements Serializable,Cloneable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDTETE",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idtete;
	
	@Column(name="REGTTE", nullable=false)
	private boolean regtte;
	
	@Column(name="USUATE", nullable=false, length=20)
	private String usuate;
	
	@Column (name="PRGRTE", nullable=false, length=500)
	private String prgrte;
	
	@Column (name="FEACTE", nullable=false)
	private Date feacte;
	
	@Column (name="MAQUTE", nullable=false, length=100)
	private String maqute;
	
	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMTE")
	private Mconca00 mconca00;
	
	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTTTE")
	private Mtiptx00 mtiptx00;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ideote", referencedColumnName="ideses")
	private Mestad00 mestad001;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idedte", referencedColumnName="ideses")
	private Mestad00 mestad002;

	/*
	 * To String and Clone
	 */

	@Override
	public String toString() {
		return "Mteste00 [idtete=" + idtete + ", regtte=" + regtte
				+ ", usuate=" + usuate + ", prgrte=" + prgrte + ", feacte="
				+ feacte + ", maqute=" + maqute 
				+ ", mtiptx00=" + (mtiptx00!=null?mtiptx00.getIdtptx()+"-"+mtiptx00.getCotrtx():null) 
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null) 
				+ ", mestad001=" + (mestad001!=null?mestad001.getIdeses()+"-"+mestad001.getCotres():null)
				+ ", mestad002=" + (mestad002!=null?mestad002.getIdeses()+"-"+mestad002.getCotres():null) + "]";
	}
	
	public String toStringId()
	{
		return "idtete="+idtete;
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
	
	/*
	 * Getters and Setters
	 */
	
	public Integer getIdtete() {
		return idtete;
	}

	public void setIdtete(Integer idtete) {
		this.idtete = idtete;
	}

	public boolean isRegtte() {
		return regtte;
	}

	public void setRegtte(boolean regtte) {
		this.regtte = regtte;
	}

	public String getUsuate() {
		return usuate;
	}

	public void setUsuate(String usuate) {
		this.usuate = usuate;
	}

	public String getPrgrte() {
		return prgrte;
	}

	public void setPrgrte(String prgrte) {
		this.prgrte = prgrte;
	}

	public Date getFeacte() {
		return feacte;
	}

	public void setFeacte(Date feacte) {
		this.feacte = feacte;
	}

	public String getMaqute() {
		return maqute;
	}

	public void setMaqute(String maqute) {
		this.maqute = maqute;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	public Mestad00 getMestad001() {
		return mestad001;
	}

	public void setMestad001(Mestad00 mestad001) {
		this.mestad001 = mestad001;
	}

	public Mestad00 getMestad002() {
		return mestad002;
	}

	public void setMestad002(Mestad00 mestad002) {
		this.mestad002 = mestad002;
	}

}
