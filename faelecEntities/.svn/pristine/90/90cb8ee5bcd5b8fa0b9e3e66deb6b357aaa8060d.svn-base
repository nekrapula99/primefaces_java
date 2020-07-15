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
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table(name = "MREGCG00")
public class Mregcg00 implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDIECG", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idiecg;
	
	@Column(name="COREGC", length=20,nullable=false)
	private String coregc;

	@Column(name="NOMEGC", length=100,nullable=false)
	private String nomegc;
	
	@Column(name="REGEGC", nullable=false)
	private boolean regegc;

	@Column(name="USEEGC", length=20, nullable=false)
	private String useegc;

	@Column(name="PRGEGC", length=20, nullable=false)
	private String prgegc;

	@Column(name="FEAEGC", nullable=false)
	private Date feaegc;
	
	@Column(name="MAQEGC",  length=100, nullable=false)
	private String maqegc;
	
	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCEGC", nullable=false)
	private Mgenus00 mgenus001;

	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDVEGC", nullable=false)
	private Mgenus00 mgenus002;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mregcg001")
	private List<Mrerer00> mrerer001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mregcg002")
	private List<Mrerer00> mrerer002s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mregcg005")
	private List<Mgente00> mgente001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mregcg004")
	private List<Mconca00> mconca001s;

	//Getters And Setters


	public String getCoregc() {
		return coregc;
	}

	public Integer getIdiecg() {
		return idiecg;
	}

	public void setIdiecg(Integer idiecg) {
		this.idiecg = idiecg;
	}

	public void setCoregc(String coregc) {
		this.coregc = coregc;
	}

	public String getNomegc() {
		return nomegc;
	}

	public void setNomegc(String nomegc) {
		this.nomegc = nomegc;
	}

	public boolean isRegegc() {
		return regegc;
	}

	public void setRegegc(boolean regegc) {
		this.regegc = regegc;
	}

	public String getUseegc() {
		return useegc;
	}

	public void setUseegc(String useegc) {
		this.useegc = useegc;
	}

	public String getPrgegc() {
		return prgegc;
	}

	public void setPrgegc(String prgegc) {
		this.prgegc = prgegc;
	}

	public Date getFeaegc() {
		return feaegc;
	}

	public void setFeaegc(Date feaegc) {
		this.feaegc = feaegc;
	}

	public String getMaqegc() {
		return maqegc;
	}

	public void setMaqegc(String maqegc) {
		this.maqegc = maqegc;
	}


	public Mgenus00 getMgenus002() {
		return mgenus002;
	}

	public void setMgenus002(Mgenus00 mgenus002) {
		this.mgenus002 = mgenus002;
	}

	@Override
	public String toString() {
		return "Mregcg00 [idiecg=" + idiecg + ", coregc=" + coregc
				+ ", nomegc=" + nomegc + ", regegc=" + regegc + ", useegc="
				+ useegc + ", prgegc=" + prgegc + ", feaegc=" + feaegc
				+ ", maqegc=" + maqegc
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null) + "]";
	
	}
	
	public String toStringId()
	{
		return "idiecg="+idiecg;
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

	public List<Mrerer00> getMrerer001s() {
		return mrerer001s;
	}

	public void setMrerer001s(List<Mrerer00> mrerer001s) {
		this.mrerer001s = mrerer001s;
	}

	public List<Mrerer00> getMrerer002s() {
		return mrerer002s;
	}

	public void setMrerer002s(List<Mrerer00> mrerer002s) {
		this.mrerer002s = mrerer002s;
	}

	public List<Mgente00> getMgente001s() {
		return mgente001s;
	}

	public void setMgente001s(List<Mgente00> mgente001s) {
		this.mgente001s = mgente001s;
	}

	public List<Mconca00> getMconca001s() {
		return mconca001s;
	}

	public void setMconca001s(List<Mconca00> mconca001s) {
		this.mconca001s = mconca001s;
	}

	public Mgenus00 getMgenus001() {
		return mgenus001;
	}

	public void setMgenus001(Mgenus00 mgenus001) {
		this.mgenus001 = mgenus001;
	}
	

}
