package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the MVARIA00 database table.
 * 
 */
@Entity
@Table(name="MVARIA00")
public class Mvaria00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDGRVT", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idgrvt;

	@Column(name="DSCRVT", unique=true, nullable=false, length=100)
	private String dscrvt;

	@Column(name="INAFVT", nullable=false, length=1)
	private String inafvt;

 
	@Column(name="IMABVT")
	private String imabvt;

	@Column(name="REGIVT", nullable=false)
	private boolean regivt;

	@Column(name="USERVT", nullable=false, length=20)
	private String uservt;

	@Column(name="PRGMVT", nullable=false, length=500)
	private String prgmvt;

	@Column(name="FEACVT", nullable=false)
	private Date feacvt;

	@Column(name="MAQUVT", nullable=false, length=100)
	private String maquvt;
	
	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDVAVT")
	private Mgenus00 mgenus00;
	
	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMVT")
	private Mconca00 mconca00;
	
	@OneToMany(mappedBy="mvaria00")
	private List<Mvarpr00> Mvarpr00s;
	
	@OneToMany(mappedBy="mvaria00")
	private List<Mdevpr00> mdevpr00s;

    public Mvaria00() {
    	inafvt = "N";	
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
		return "idgrvt="+idgrvt;
	}

	@Override
	public String toString() {
		return "Mvaria00 [idgrvt=" + idgrvt + ", dscrvt=" + dscrvt
				+ ", inafvt=" + inafvt + ", imabvt=" + imabvt
				+ ", regivt=" + regivt + ", uservt=" + uservt + ", prgmvt="
				+ prgmvt + ", feacvt=" + feacvt + ", maquvt=" + maquvt 
				+ ", mgenus00=" + (mgenus00!=null?mgenus00.getIdtrus()+"-"+mgenus00.getIdtrus():null)
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getIdccia():null) + "]";
	}

	/*
	 * Getters and Setters
	 */
	public Integer getIdgrvt() {
		return idgrvt;
	}

	public void setIdgrvt(Integer idgrvt) {
		this.idgrvt = idgrvt;
	}

	public String getDscrvt() {
		return dscrvt;
	}

	public void setDscrvt(String dscrvt) {
		this.dscrvt = dscrvt;
	}

	public boolean getInafvt() {
		return inafvt.equalsIgnoreCase("S");
	}

	public void setInafvt(boolean inafvt) {
		if(inafvt){
			this.inafvt="S";
		}else{
			this.inafvt="N";
		}
	}

	public String getImabvt() {
		return imabvt;
	}

	public void setImabvt(String imabvt) {
		this.imabvt = imabvt;
	}

	public boolean isRegivt() {
		return regivt;
	}

	public void setRegivt(boolean regivt) {
		this.regivt = regivt;
	}

	public String getUservt() {
		return uservt;
	}

	public void setUservt(String uservt) {
		this.uservt = uservt;
	}

	public String getPrgmvt() {
		return prgmvt;
	}

	public void setPrgmvt(String prgmvt) {
		this.prgmvt = prgmvt;
	}

	public Date getFeacvt() {
		return feacvt;
	}

	public void setFeacvt(Date feacvt) {
		this.feacvt = feacvt;
	}

	public String getMaquvt() {
		return maquvt;
	}

	public void setMaquvt(String maquvt) {
		this.maquvt = maquvt;
	}

	public Mgenus00 getMgenus00() {
		return mgenus00;
	}

	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public List<Mvarpr00> getMvarpr00s() {
		return Mvarpr00s;
	}

	public void setMvarpr00s(List<Mvarpr00> mvarpr00s) {
		Mvarpr00s = mvarpr00s;
	}

	public List<Mdevpr00> getMdevpr00s() {
		return mdevpr00s;
	}

	public void setMdevpr00s(List<Mdevpr00> mdevpr00s) {
		this.mdevpr00s = mdevpr00s;
	}

}