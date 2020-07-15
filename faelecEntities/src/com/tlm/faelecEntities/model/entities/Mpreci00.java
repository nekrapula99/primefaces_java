package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="MPRECI00")
public class Mpreci00 implements Serializable,Cloneable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDPRCI", unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idprci;

	@Column(name="DEPRCI", length=100)
	private String deprci;

	@Column(name="CMINCI", nullable=false)
	private Double cminci;

	@Column(name="CMAXCI", nullable=false)
	private Double cmaxci;

	@Column(name="PRESCI", nullable=false)
	private Double presci;

	@Column(name="PRPACI", nullable=false)
	private Double prpaci;

	@Column(name="FEFVCI")
	private Date fefvci;

	@Column(name="OBPRCI", length=100)
	private String obprci;

	@Column(name="REGCCI", nullable=false)
	private boolean regcci;

	@Column(name="USECCI", length=20, nullable=false)
	private String usecci;

	@Column(name="PRGCCI", length=500, nullable=false)
	private String prgcci;

	@Column(name="FEACCI", nullable=false)
	private Date feacci;
	
	@Column(name="MAQCCI",  length=100, nullable=false)
	private String maqcci;
	
	//FK mgente00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECI")
	private Mgente00 mgente00;
	
	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPTCI", nullable=false)
	private Mpropr00 mpropr00;
	
	//FK Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMCI", nullable=false)
	private Mconca00 mconca00;

	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMOCI", nullable=false)
	private Mgenus00 mgenus00;
	
	public Mpreci00() {
    	
    }

	public int getIdprci() {
		return idprci;
	}


	public void setIdprci(int idprci) {
		this.idprci = idprci;
	}


	public String getDeprci() {
		return deprci;
	}


	public void setDeprci(String deprci) {
		this.deprci = deprci;
	}


	public Double getCminci() {
		return cminci;
	}


	public void setCminci(Double cminci) {
		this.cminci = cminci;
	}


	public Double getCmaxci() {
		return cmaxci;
	}


	public void setCmaxci(Double cmaxci) {
		this.cmaxci = cmaxci;
	}


	public Double getPresci() {
		return presci;
	}


	public void setPresci(Double presci) {
		this.presci = presci;
	}


	public Double getPrpaci() {
		return prpaci;
	}


	public void setPrpaci(Double prpaci) {
		this.prpaci = prpaci;
	}


	public Date getFefvci() {
		return fefvci;
	}


	public void setFefvci(Date fefvci) {
		this.fefvci = fefvci;
	}


	public String getObprci() {
		return obprci;
	}


	public void setObprci(String obprci) {
		this.obprci = obprci;
	}


	public boolean isRegcci() {
		return regcci;
	}


	public void setRegcci(boolean regcci) {
		this.regcci = regcci;
	}


	public String getUsecci() {
		return usecci;
	}


	public void setUsecci(String usecci) {
		this.usecci = usecci;
	}


	public String getPrgcci() {
		return prgcci;
	}


	public void setPrgcci(String prgcci) {
		this.prgcci = prgcci;
	}


	public Date getFeacci() {
		return feacci;
	}


	public void setFeacci(Date feacci) {
		this.feacci = feacci;
	}


	public String getMaqcci() {
		return maqcci;
	}


	public void setMaqcci(String maqcci) {
		this.maqcci = maqcci;
	}


	public Mgente00 getMgente00() {
		return mgente00;
	}


	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}


	public Mpropr00 getMpropr00() {
		return mpropr00;
	}


	public void setMpropr00(Mpropr00 mpropr00) {
		this.mpropr00 = mpropr00;
	}


	public Mconca00 getMconca00() {
		return mconca00;
	}


	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}


	public Mgenus00 getMgenus00() {
		return mgenus00;
	}


	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}

	@Override
	public String toString() {
		return "Mpreci00 [idprci=" + idprci + ", deprci=" + deprci
				+ ", cminci=" + cminci + ", cmaxci=" + cmaxci + ", presci="
				+ presci + ", prpaci=" + prpaci + ", fefvci=" + fefvci
				+ ", obprci=" + obprci + ", regcci=" + regcci + ", usecci="
				+ usecci + ", prgcci=" + prgcci + ", feacci=" + feacci
				+ ", maqcci=" + maqcci 
				+ ", mgente00=" + (mgente00!=null?mgente00.getIdtrte()+"-"+mgente00.getCodite():null)
				+ ", mpropr00=" + (mpropr00!=null?mpropr00.getIdcapr()+"-"+mpropr00.getCodcpr():null)
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", mgenus00=" + (mgenus00!=null?mgenus00.getIdtrus()+"-"+mgenus00.getCodius():null) + "]";
	}
	
	public String toStringId()
	{
		return "idprci="+idprci;
	}
	
	
	
	public Mpreci00(int idprci, String deprci, Double cminci, Double cmaxci,
			Double presci, Double prpaci, Date fefvci, String obprci,
			boolean regcci, String usecci, String prgcci, Date feacci,
			String maqcci, Mgente00 mgente00, Mpropr00 mpropr00,
			Mconca00 mconca00, Mgenus00 mgenus00) {
		super();
		this.idprci = idprci;
		this.deprci = deprci;
		this.cminci = cminci;
		this.cmaxci = cmaxci;
		this.presci = presci;
		this.prpaci = prpaci;
		this.fefvci = fefvci;
		this.obprci = obprci;
		this.regcci = regcci;
		this.usecci = usecci;
		this.prgcci = prgcci;
		this.feacci = feacci;
		this.maqcci = maqcci;
		this.mgente00 = mgente00;
		this.mpropr00 = mpropr00;
		this.mconca00 = mconca00;
		this.mgenus00 = mgenus00;
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
	
}
