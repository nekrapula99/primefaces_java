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
@Table(name = "TQENQV00")
public class Tqenqv00  implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDENQV",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idenqv;
	
	@Column(name="FEEVQV", nullable=false)
	private Date feevqv;
	
	@Column(name="PDFVQV", length=500, nullable=false)
	private String pdfvqf;
	
	@Column(name="DPDFQV", length=500)
	private String dpdfqv;
	
	@Column(name="ASMLQV", length=500)
	private String asmlqv;
	
	@Column(name="TEXVQV", length=500)
	private String texvqv;
	
	@Column(name="DMXVQV", length=500)
	private String dmxvqv;
	
	@Column(name="REGCQV", nullable=false)
	private boolean regcqv;

	@Column(name="USECQV", length=20, nullable=false)
	private String usecqv;

	@Column(name="PRGCQV", length=500, nullable=false)
	private String prgcqv;

	@Column(name="FEACQV", nullable=false)
	private Date feacqv;
	
	@Column(name="MAQCQV",  length=100, nullable=false)
	private String maqcqv;
	
	//FK Cotización TQIGQG00.IDTQQG
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDQCQV", nullable=false)
	private Tqigqg00 tqigqg00;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqenqv00")
	private List<Tqrerv00> tqrerv00s;
	
	public String toStringId()
	{
		return "idenqv="+idenqv;
	}
	
	@Override
	public String toString() {
		return "Tqenqv00 [idenqv=" + idenqv + ", feevqv=" + feevqv
				+ ", pdfvqf=" + pdfvqf + ", dpdfqv=" + dpdfqv + ", asmlqv="
				+ asmlqv + ", texvqv=" + texvqv + ", dmxvqv=" + dmxvqv
				+ ", regcqv=" + regcqv + ", usecqv=" + usecqv + ", prgcqv="
				+ prgcqv + ", feacqv=" + feacqv + ", maqcqv=" + maqcqv
				+ ", tqigqg00=" + (tqigqg00!=null?tqigqg00.getIdtqqg()+"-"+tqigqg00.getNroqqg():null)
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

	public int getIdenqv() {
		return idenqv;
	}

	public void setIdenqv(int idenqv) {
		this.idenqv = idenqv;
	}

	public Date getFeevqv() {
		return feevqv;
	}

	public void setFeevqv(Date feevqv) {
		this.feevqv = feevqv;
	}

	public String getPdfvqf() {
		return pdfvqf;
	}

	public void setPdfvqf(String pdfvqf) {
		this.pdfvqf = pdfvqf;
	}

	public String getDpdfqv() {
		return dpdfqv;
	}

	public void setDpdfqv(String dpdfqv) {
		this.dpdfqv = dpdfqv;
	}

	public String getAsmlqv() {
		return asmlqv;
	}

	public void setAsmlqv(String asmlqv) {
		this.asmlqv = asmlqv;
	}

	public String getTexvqv() {
		return texvqv;
	}

	public void setTexvqv(String texvqv) {
		this.texvqv = texvqv;
	}

	public String getDmxvqv() {
		return dmxvqv;
	}

	public void setDmxvqv(String dmxvqv) {
		this.dmxvqv = dmxvqv;
	}

	public boolean isRegcqv() {
		return regcqv;
	}

	public void setRegcqv(boolean regcqv) {
		this.regcqv = regcqv;
	}

	public String getUsecqv() {
		return usecqv;
	}

	public void setUsecqv(String usecqv) {
		this.usecqv = usecqv;
	}

	public String getPrgcqv() {
		return prgcqv;
	}

	public void setPrgcqv(String prgcqv) {
		this.prgcqv = prgcqv;
	}

	public Date getFeacqv() {
		return feacqv;
	}

	public void setFeacqv(Date feacqv) {
		this.feacqv = feacqv;
	}

	public String getMaqcqv() {
		return maqcqv;
	}

	public void setMaqcqv(String maqcqv) {
		this.maqcqv = maqcqv;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public List<Tqrerv00> getTqrerv00s() {
		return tqrerv00s;
	}

	public void setTqrerv00s(List<Tqrerv00> tqrerv00s) {
		this.tqrerv00s = tqrerv00s;
	}	

}
