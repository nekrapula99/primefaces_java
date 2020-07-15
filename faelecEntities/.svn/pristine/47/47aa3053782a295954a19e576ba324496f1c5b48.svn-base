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

@Entity
@Table(name="MEVENT00")
public class Mevent00 implements Serializable,Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6602678834681618938L;

	@Id
	@Column(name="IDTREV", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idtrev;

	@Column(name="CODUEV", length=20)
	private String coduev;	

	@Column(name="REGIEV", nullable=false)
	private Boolean regiev;	

	@Column(name="USEREV", nullable=false, length=20)
	private String userev;
	
	@Column(name="PRGMEV", nullable=false, length=20)
	private String prgmev;
	
	@Column(name="FEACEV", nullable=false)
	private Date feacev;
	
	@Column(name="MAQUEV", nullable=false, length=100)
	private String maquev;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMEV")
	private Mconca00 mconca00;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMSEV")
	private Mmesje00 mmesje00;
	
	public Mevent00()
	{
		
	}
	
	public Integer getIdtrev() {
		return idtrev;
	}

	public void setIdtrev(Integer idtrev) {
		this.idtrev = idtrev;
	}

	public String getCoduev() {
		return coduev;
	}

	public void setCoduev(String coduev) {
		this.coduev = coduev;
	}

	public Boolean getRegiev() {
		return regiev;
	}

	public void setRegiev(Boolean regiev) {
		this.regiev = regiev;
	}

	public String getUserev() {
		return userev;
	}

	public void setUserev(String userev) {
		this.userev = userev;
	}

	public String getPrgmev() {
		return prgmev;
	}

	public void setPrgmev(String prgmev) {
		this.prgmev = prgmev;
	}

	public Date getFeacev() {
		return feacev;
	}

	public void setFeacev(Date feacev) {
		this.feacev = feacev;
	}

	public String getMaquev() {
		return maquev;
	}

	public void setMaquev(String maquev) {
		this.maquev = maquev;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public Mmesje00 getMmesje00() {
		return mmesje00;
	}

	public void setMmesje00(Mmesje00 mmesje00) {
		this.mmesje00 = mmesje00;
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
		return "Mevent00 [idtrev=" + idtrev + ", coduev=" + coduev
				+ ", regiev=" + regiev + 
				", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null) +
				", mmesje00=" + (mmesje00!=null?mmesje00.getIdtrms()+"-"+mmesje00.getCodums():null) +"]";
	}
	public String toStringId()
	{
		return "idtrev ="+idtrev;
	}

}
