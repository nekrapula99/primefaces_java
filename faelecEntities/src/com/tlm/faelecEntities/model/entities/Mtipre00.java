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

import com.tlm.faelecEntities.model.entities.Mconca00;

@Entity
@Table(name="MTIPRE00")
public class Mtipre00 implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTRTR", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idtrtr;
	
	@Column(name="TIPORE", unique=true, nullable=false, length=4)
	private String tipore;
	
	@Column(name="DESCRE", nullable=false, length=300)
	private String descre;
	
	@Column(name="TPERRE", unique=true, nullable=false, length=4)
	private String tperre;
	
	//bi-directional many-to-one association 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMTR", unique=true)
	private Mconca00 mconca00;
	
	@Column(name="REGITR", nullable=false)
	private Boolean regitr;
	
	@Column(name="USERTR", nullable=false, length=20)
	private String usertr;
	
	@Column(name="PRGMTR", nullable=false, length=20)
	private String prgmtr;
	
	@Column(name="FEACTR", nullable=false)
	private Date feactr;
	
	@Column(name="MAQUTR", nullable=false, length=100)
	private String maqutr;
	
	@Column(name="INPVTR", unique=true, nullable=false, length=4)
	private String inpvtr;
	
	
	//bi-directional many-to-one association to Mparca00
	@OneToMany(mappedBy="mtipre00")
	private List<Mgenus00> mgenus00s;
	
	@OneToMany(mappedBy="mtipre00")
	private List<Mgente00> mgente00s;
	
	
	//Metodos de acceso
	public Integer getIdtrtr() {
		return idtrtr;
	}

	public void setIdtrtr(Integer idtrtr) {
		this.idtrtr = idtrtr;
	}

	public String getTipore() {
		return tipore;
	}

	public void setTipore(String tipore) {
		this.tipore = tipore;
	}

	public String getDescre() {
		return descre;
	}

	public void setDescre(String descre) {
		this.descre = descre;
	}

	public String getTperre() {
		return tperre;
	}

	public void setTperre(String tperre) {
		this.tperre = tperre;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public Boolean getRegitr() {
		return regitr;
	}

	public void setRegitr(Boolean regitr) {
		this.regitr = regitr;
	}

	public String getUsertr() {
		return usertr;
	}

	public void setUsertr(String usertr) {
		this.usertr = usertr;
	}

	public String getPrgmtr() {
		return prgmtr;
	}

	public void setPrgmtr(String prgmtr) {
		this.prgmtr = prgmtr;
	}

	public Date getFeactr() {
		return feactr;
	}

	public void setFeactr(Date feactr) {
		this.feactr = feactr;
	}

	public String getMaqutr() {
		return maqutr;
	}

	public void setMaqutr(String maqutr) {
		this.maqutr = maqutr;
	}

	public List<Mgenus00> getMgenus00s() {
		return mgenus00s;
	}

	public void setMgenus00s(List<Mgenus00> mgenus00s) {
		this.mgenus00s = mgenus00s;
	}

	public String getInpvtr() {
		return inpvtr;
	}

	public void setInpvtr(String inpvtr) {
		this.inpvtr = inpvtr;
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
		return "Mtipre00 [idtrtr=" + idtrtr + ", tipore=" + tipore
				+ ", descre=" + descre + ", tperre=" + tperre + ", inpvtr=" + inpvtr +", regitr=" + regitr 
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)+ "]";
	}
	public String toStringId()
	{
		return "idtrtr="+idtrtr;
	}

	public List<Mgente00> getMgente00s() {
		return mgente00s;
	}

	public void setMgente00s(List<Mgente00> mgente00s) {
		this.mgente00s = mgente00s;
	}

	
}
