package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity	 
@Table(name="MESTAD00")
public class Mestad00 implements Serializable,Cloneable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDESES",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ideses;
	
	@Column(name="COTRES",nullable=false, length=30)
	private String cotres;
	
	@Column(name="DSCRES", length=100)
	private String dscres;
	
	@Column(name="ININES", length=1)
	private String inines;
	
	@Column(name="REGTES", nullable=false)
	private boolean regtes;
	
	@Column(name="USUAES", nullable=false, length=20)
	private String usuaes;
	
	@Column (name="PRGRES", nullable=false, length=500)
	private String prgres;
	
	@Column (name="FEACES", nullable=false)
	private Date feaces;
	
	@Column (name="MAQUES", nullable=false, length=100)
	private String maques; 
	
	@Column (name="AAALES", nullable=false, length=5)
	private String aaales;  
	
	@Column (name="MMALES", nullable=false, length=5)
	private String mmales;
	
	@Column (name="DDALES", nullable=false, length=5)
	private String ddales;
	
	@Column (name="HHALES", nullable=false, length=5)
	private String hhales;
	
	@Column (name="BFEAES", nullable=false, length=1)
	private String bfeaes;
	
	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMES")
	private Mconca00 mconca00;
	
	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTTES")
	private Mtiptx00 mtiptx00;
	
	//bi-directional many-to-one association to Mconca00
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="IDAEES")
	//private Macdio00 macdio00; 
	
	//bi-directional many-to-one association to Mteste00
	@OneToMany(mappedBy="mestad001",cascade=CascadeType.ALL)
	private List<Mteste00> mteste00s1;
	
	//bi-directional many-to-one association to Mteste00
	@OneToMany(mappedBy="mestad002",cascade=CascadeType.ALL)
	private List<Mteste00> mteste00s2;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="mestad00",cascade=CascadeType.ALL)
	private List<Tqesqe00> tqesqe00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="mestad00",cascade=CascadeType.ALL)
	private List<Tresre00> tresre00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="mestad00",cascade=CascadeType.ALL)
	private List<Macdio00> macdio00s;

	/*
	 * To String and Clone
	 */
	
	@Override
	public String toString() {
		return "Mestad00 [ideses=" + ideses + ", cotres=" + cotres
				+ ", dscres=" + dscres + ", inines=" + inines + ", regtes="
				+ regtes + ", usuaes=" + usuaes + ", prgres=" + prgres
				+ ", feaces=" + feaces + ", maques=" + maques 
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null) 
				+ ", mtiptx00=" + (mtiptx00!=null?mtiptx00.getIdtptx()+"-"+mtiptx00.getCotrtx():null) + "]";
	}
	
	public String toStringId()
	{
		return "ideses="+ideses;
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
	 * Constructor
	 */
	public Mestad00(){
		inines = "N";
	}
	
	/*
	 * Getters and Setters
	 */
	public Integer getIdeses() {
		return ideses;
	}

	public void setIdeses(Integer ideses) {
		this.ideses = ideses;
	}

	public String getCotres() {
		return cotres;
	}

	public void setCotres(String cotres) {
		this.cotres = cotres;
	}

	public String getDscres() {
		return dscres;
	}

	public void setDscres(String dscres) {
		this.dscres = dscres;
	}

	public boolean getInines() {
		return inines.equalsIgnoreCase("S");
	}

	public void setInines(boolean inines) {
		if (inines) {
			this.inines = "S";
		}else{
			this.inines = "N";
		}
		
	}

	public boolean getRegtes() {
		return regtes;
	}

	public void setRegtes(boolean regtes) {
		this.regtes = regtes;
	}

	public String getUsuaes() {
		return usuaes;
	}

	public void setUsuaes(String usuaes) {
		this.usuaes = usuaes;
	}

	public String getPrgres() {
		return prgres;
	}

	public void setPrgres(String prgres) {
		this.prgres = prgres;
	}

	public Date getFeaces() {
		return feaces;
	}

	public void setFeaces(Date feaces) {
		this.feaces = feaces;
	}

	public String getMaques() {
		return maques;
	}

	public void setMaques(String maques) {
		this.maques = maques;
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

	public List<Mteste00> getMteste00s1() {
		return mteste00s1;
	}

	public void setMteste00s1(List<Mteste00> mteste00s1) {
		this.mteste00s1 = mteste00s1;
	}

	public List<Mteste00> getMteste00s2() {
		return mteste00s2;
	}

	public void setMteste00s2(List<Mteste00> mteste00s2) {
		this.mteste00s2 = mteste00s2;
	}

	public String getAaales() {
		return aaales;
	}

	public void setAaales(String aaales) {
		this.aaales = aaales;
	}

	public String getMmales() {
		return mmales;
	}

	public void setMmales(String mmales) {
		this.mmales = mmales;
	}

	/*public Macdio00 getMacdio00() {
		return macdio00;
	}

	public void setMacdio00(Macdio00 macdio00) {
		this.macdio00 = macdio00;
	}*/

	public void setInines(String inines) {
		this.inines = inines;
	}

	public String getDdales() {
		return ddales;
	}

	public void setDdales(String ddales) {
		this.ddales = ddales;
	}

	public String getHhales() {
		return hhales;
	}

	public void setHhales(String hhales) {
		this.hhales = hhales;
	}

	public String getBfeaes() {
		return bfeaes;
	}

	public void setBfeaes(String bfeaes) {
		this.bfeaes = bfeaes;
	}

	public List<Tqesqe00> getTqesqe00s() {
		return tqesqe00s;
	}

	public void setTqesqe00s(List<Tqesqe00> tqesqe00s) {
		this.tqesqe00s = tqesqe00s;
	}

	public List<Tresre00> getTresre00s() {
		return tresre00s;
	}

	public void setTresre00s(List<Tresre00> tresre00s) {
		this.tresre00s = tresre00s;
	}

	public List<Macdio00> getMacdio00s() {
		return macdio00s;
	}

	public void setMacdio00s(List<Macdio00> macdio00s) {
		this.macdio00s = macdio00s;
	}
	
}
