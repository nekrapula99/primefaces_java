package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MMESJE00")
public class Mmesje00 implements Serializable, Cloneable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6921362411410862928L;

	@Id
	@Column(name="IDTRMS", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idtrms;

	@Column(name="CODUMS", length=20)
	private String codums;	

	@Column(name="DESCMS", length=300)
	private String descms;   

	@Column(name="REGIMS", nullable=false)
	private Boolean regims;	

	@Column(name="USERMS", nullable=false, length=20)
	private String userms;
	
	@Column(name="PRGMMS", nullable=false, length=20)
	private String prgmms;
	
	@Column(name="FEACMS", nullable=false)
	private Date feacms;
	
	@Column(name="MAQUMS", nullable=false, length=100)
	private String maqums;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMMS")
	private Mconca00 mconca00;
	
	//bi-directional many-to-one association to Mmesje00
	@OneToMany(mappedBy="mmesje00")
	private List<Mevent00> mevent00s;
	
	public Mmesje00()
	{
		
	}

	public Integer getIdtrms() {
		return idtrms;
	}

	public void setIdtrms(Integer idtrms) {
		this.idtrms = idtrms;
	}

	public String getCodums() {
		return codums;
	}

	public void setCodums(String codums) {
		this.codums = codums;
	}

	public String getDescms() {
		return descms;
	}

	public void setDescms(String descms) {
		this.descms = descms;
	}

	public Boolean getRegims() {
		return regims;
	}

	public void setRegims(Boolean regims) {
		this.regims = regims;
	}

	public String getUserms() {
		return userms;
	}

	public void setUserms(String userms) {
		this.userms = userms;
	}

	public String getPrgmms() {
		return prgmms;
	}

	public void setPrgmms(String prgmms) {
		this.prgmms = prgmms;
	}

	public Date getFeacms() {
		return feacms;
	}

	public void setFeacms(Date feacms) {
		this.feacms = feacms;
	}

	public String getMaqums() {
		return maqums;
	}

	public void setMaqums(String maqums) {
		this.maqums = maqums;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public List<Mevent00> getMevent00s() {
		return mevent00s;
	}

	public void setMevent00s(List<Mevent00> mevent00s) {
		this.mevent00s = mevent00s;
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
		return "Mmesje00 [idtrms=" + idtrms + ", codums=" + codums
				+ ", descms=" + descms + ", regims=" + regims + 
				", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null) +"]";
	}
	public String toStringId()
	{
		return "idtrms="+ idtrms;
	}

}
