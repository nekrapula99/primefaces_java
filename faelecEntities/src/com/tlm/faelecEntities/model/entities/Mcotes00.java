package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "MCOTES00")
public class Mcotes00 implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDCTES", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idctes;
	
	@Column(name="COCTES", length=20)
	private String coctes;
	
	@Column(name="PRNTES", length=30)
	private String prntes;
	
	@Column(name="SENTES", length=30)
	private String sentes;
	
	@Column(name="PRATES", length=30)
	private String prates;
	
	@Column(name="SEATES", length=30)
	private String seates;
	
	@Column(name="NRNTES", length=20)
	private String nrntes;
	
	@Column(name="DIRTES", length=300, nullable=false)
	private String dirtes;
	
	@Column(name="TELTES", length=300)
	private String teltes;
	
	@Column(name="EMATES", length=100)
	private String emates;
	
	
	@Column(name="REGTES", nullable=false)
	private boolean regtes;

	@Column(name="USETES", length=20, nullable=false)
	private String usetes;

	@Column(name="PRGTES", length=500, nullable=false)
	private String prgtes;

	@Column(name="FEATES", nullable=false)
	private Date feates;
	
	@Column(name="MAQTES",  length=100, nullable=false)
	private String maqtes;
	
	
	//FK Mgenus00 -- SALUDO
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSTES", nullable=false)
	private Mgenus00 mgenus001;
	
	//FK Mgenus00 Documento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTTES", nullable=false)
	private Mgenus00 mgenus002;
	
	//FK Mgenus00 paises
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPTES", nullable=false)
	private Mgenus00 mgenus003;
	
	//FK Mgenus00 ciudad
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCIES", nullable=false)
	private Mgenus00 mgenus004;
	
	//FK Mgente00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTRES", nullable=false)
	private Mgente00 mgente001;
		
	//FK Mgenus00 -- AREA LABOR
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDATES", nullable=false)
	private Mgenus00 mgenus005;

	//FK Mgenus00 --Cargo Tercero
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGTES", nullable=false)
	private Mgenus00 mgenus006;
	
	//bi-directional many-to-one association to Mcpcct00
	@OneToMany(mappedBy="mcotes001",cascade = CascadeType.ALL)
	private List<Mcotos00> mcotos001s;
	
	//bi-directional many-to-one association to Mcpcct00
	@OneToMany(mappedBy="mcotes00")
	private List<Tqdfqf00> tqdfqf00s;
	
	//bi-directional many-to-one association to Mcpcct00
	@OneToMany(mappedBy="mcotes00")
	private List<Tqrerv00> tqrerv00s;


	@Override
	public String toString() {
		return "Mcotes00 [idctes=" + idctes + ", coctes=" + coctes
				+ ", prntes=" + prntes + ", sentes=" + sentes + ", prates="
				+ prates + ", seates=" + seates + ", nrntes=" + nrntes
				+ ", dirtes=" + dirtes + ", teltes=" + teltes + ", emates="
				+ emates + ", regtes=" + regtes + ", usetes=" + usetes
				+ ", prgtes=" + prgtes + ", feates=" + feates + ", maqtes="
				+ maqtes
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)
				+ ", mgenus004=" + (mgenus004!=null?mgenus004.getIdtrus()+"-"+mgenus004.getCodius():null)
				+ ", mgente001=" + (mgente001!=null?mgente001.getIdtrte()+"-"+mgente001.getCodite():null)
				+ ", mgenus005=" + (mgenus005!=null?mgenus005.getIdtrus()+"-"+mgenus005.getCodius():null)
				+ ", mgenus006=" + (mgenus006!=null?mgenus006.getIdtrus()+"-"+mgenus006.getCodius():null)+ "]";
				
	}
	
	public String toStringId()
	{
		return "idctes="+idctes;
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

	public int getIdctes() {
		return idctes;
	}

	public void setIdctes(int idctes) {
		this.idctes = idctes;
	}

	public String getCoctes() {
		return coctes;
	}

	public void setCoctes(String coctes) {
		this.coctes = coctes;
	}

	public String getPrntes() {
		return prntes;
	}

	public void setPrntes(String prntes) {
		this.prntes = prntes;
	}

	public String getSentes() {
		return sentes;
	}

	public void setSentes(String sentes) {
		this.sentes = sentes;
	}

	public String getPrates() {
		return prates;
	}

	public void setPrates(String prates) {
		this.prates = prates;
	}

	public String getSeates() {
		return seates;
	}

	public void setSeates(String seates) {
		this.seates = seates;
	}

	public String getNrntes() {
		return nrntes;
	}

	public void setNrntes(String nrntes) {
		this.nrntes = nrntes;
	}

	public String getDirtes() {
		return dirtes;
	}

	public void setDirtes(String dirtes) {
		this.dirtes = dirtes;
	}

	public String getTeltes() {
		return teltes;
	}

	public void setTeltes(String teltes) {
		this.teltes = teltes;
	}

	public String getEmates() {
		return emates;
	}

	public void setEmates(String emates) {
		this.emates = emates;
	}

	public boolean isRegtes() {
		return regtes;
	}

	public void setRegtes(boolean regtes) {
		this.regtes = regtes;
	}

	public String getUsetes() {
		return usetes;
	}

	public void setUsetes(String usetes) {
		this.usetes = usetes;
	}

	public String getPrgtes() {
		return prgtes;
	}

	public void setPrgtes(String prgtes) {
		this.prgtes = prgtes;
	}

	public Date getFeates() {
		return feates;
	}

	public void setFeates(Date feates) {
		this.feates = feates;
	}

	public String getMaqtes() {
		return maqtes;
	}

	public void setMaqtes(String maqtes) {
		this.maqtes = maqtes;
	}

	public Mgenus00 getMgenus001() {
		return mgenus001;
	}

	public void setMgenus001(Mgenus00 mgenus001) {
		this.mgenus001 = mgenus001;
	}

	public Mgenus00 getMgenus002() {
		return mgenus002;
	}

	public void setMgenus002(Mgenus00 mgenus002) {
		this.mgenus002 = mgenus002;
	}

	public Mgenus00 getMgenus003() {
		return mgenus003;
	}

	public void setMgenus003(Mgenus00 mgenus003) {
		this.mgenus003 = mgenus003;
	}

	public Mgenus00 getMgenus004() {
		return mgenus004;
	}

	public void setMgenus004(Mgenus00 mgenus004) {
		this.mgenus004 = mgenus004;
	}

	public Mgente00 getMgente001() {
		return mgente001;
	}

	public void setMgente001(Mgente00 mgente001) {
		this.mgente001 = mgente001;
	}

	public Mgenus00 getMgenus005() {
		return mgenus005;
	}

	public void setMgenus005(Mgenus00 mgenus005) {
		this.mgenus005 = mgenus005;
	}

	public Mgenus00 getMgenus006() {
		return mgenus006;
	}

	public void setMgenus006(Mgenus00 mgenus006) {
		this.mgenus006 = mgenus006;
	}

	public List<Mcotos00> getMcotos001s() {
		return mcotos001s;
	}

	public void setMcotos001s(List<Mcotos00> mcotos001s) {
		this.mcotos001s = mcotos001s;
	}

	public List<Tqdfqf00> getTqdfqf00s() {
		return tqdfqf00s;
	}

	public void setTqdfqf00s(List<Tqdfqf00> tqdfqf00s) {
		this.tqdfqf00s = tqdfqf00s;
	}

	public List<Tqrerv00> getTqrerv00s() {
		return tqrerv00s;
	}

	public void setTqrerv00s(List<Tqrerv00> tqrerv00s) {
		this.tqrerv00s = tqrerv00s;
	}
	
}
