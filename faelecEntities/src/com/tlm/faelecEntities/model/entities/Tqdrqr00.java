package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TQDRQR00")
public class Tqdrqr00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDTRQR",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrqr;
	
	@Column(name="CRQRQR", length=500)
	private String crqrqr;
	
	@Column(name="DETRQR", length=500)
	private String detrqr;
	
	@Column(name="VAREQR", nullable=false)
	private Double vareqr;	
	
	@Column(name="FEPEQR", nullable=false)
	private Date fepeqr;
	
	@Column(name="OBQRQR", length=300)
	private String obqrqr;
	
	@Column(name="AJQRQR", length=400)
	private String ajqrqr;
	
	@Column(name="REGCQR", nullable=false)
	private boolean regcqr;

	@Column(name="USECQR", length=20, nullable=false)
	private String usecqr;

	@Column(name="PRGCQR", length=500, nullable=false)
	private String prgcqr;

	@Column(name="FEACQR", nullable=false)
	private Date feacqr;
	
	@Column(name="MAQCQR",  length=100, nullable=false)
	private String maqcqr;
	
	//FK Id Cotizacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTQQR", nullable=false)
	private Tqigqg00 tqigqg00;
	
	//FK Estandar/ Particular EP
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDEPQR", nullable=false)
	private Mgenus00 mgenus001;
	
	//FK Complejidad CJ
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCJQR", nullable=false)
	private Mgenus00 mgenus002;
	
	//FK LEGAL  LG
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDLGQR", nullable=false)
	private Mgenus00 mgenus003;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqdrqr00",cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	private List<Tresre00> tresre00s;
	
	//bi-directional many-to-one association to tqigqg00
	//@OneToMany(fetch=FetchType.EAGER, mappedBy = "tqdrqr00", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "tqdrqr00", cascade = CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	private List<Tqdpqp00> tqdpqp00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqdrqr00", cascade = CascadeType.ALL)
	private List<Tfbdbd00> tfbdbd00s;
	
	@Transient
	private Tresre00 tresre00Aux;

	
	public String toStringId()
	{
		return "idtrqr="+idtrqr;
	}
	
	
	@Override
	public String toString() {
		return "Tqdrqr00 [idtrqr=" + idtrqr + ", detrqr=" + detrqr + ", crqrqr=" + crqrqr
				+ ", vareqr=" + vareqr + ", fepeqr=" + fepeqr + ", obqrqr=" + obqrqr + ", ajqrqr="
				+ ajqrqr + ", regcqr=" + regcqr + ", usecqr=" + usecqr
				+ ", prgcqr=" + prgcqr + ", feacqr=" + feacqr + ", maqcqr="	+ maqcqr 
				+ ", tqigqg00=" + (tqigqg00!=null?tqigqg00.getIdtqqg()+"-"+tqigqg00.getNroqqg():null)
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)
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

	public int getIdtrqr() {
		return idtrqr;
	}

	public void setIdtrqr(int idtrqr) {
		this.idtrqr = idtrqr;
	}

	public String getDetrqr() {
		return detrqr;
	}

	public void setDetrqr(String detrqr) {
		this.detrqr = detrqr;
	}

	public Double getVareqr() {
		return vareqr;
	}

	public void setVareqr(Double vareqr) {
		this.vareqr = vareqr;
	}

	public String getObqrqr() {
		return obqrqr;
	}

	public void setObqrqr(String obqrqr) {
		this.obqrqr = obqrqr;
	}

	public String getAjqrqr() {
		return ajqrqr;
	}

	public void setAjqrqr(String ajqrqr) {
		this.ajqrqr = ajqrqr;
	}

	public boolean isRegcqr() {
		return regcqr;
	}

	public void setRegcqr(boolean regcqr) {
		this.regcqr = regcqr;
	}

	public String getUsecqr() {
		return usecqr;
	}

	public void setUsecqr(String usecqr) {
		this.usecqr = usecqr;
	}

	public String getPrgcqr() {
		return prgcqr;
	}

	public void setPrgcqr(String prgcqr) {
		this.prgcqr = prgcqr;
	}

	public Date getFeacqr() {
		return feacqr;
	}

	public void setFeacqr(Date feacqr) {
		this.feacqr = feacqr;
	}

	public String getMaqcqr() {
		return maqcqr;
	}

	public void setMaqcqr(String maqcqr) {
		this.maqcqr = maqcqr;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
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


	public Date getFepeqr() {
		return fepeqr;
	}


	public void setFepeqr(Date fepeqr) {
		this.fepeqr = fepeqr;
	}


	public String getCrqrqr() {
		return crqrqr;
	}


	public void setCrqrqr(String crqrqr) {
		this.crqrqr = crqrqr;
	}


	public List<Tqdpqp00> getTqdpqp00s() {
		if(tqdpqp00s == null){
			tqdpqp00s = new ArrayList<Tqdpqp00>();
		}
		return this.tqdpqp00s;
	}
	
	public void setTqdpqp00s(List<Tqdpqp00> tqdpqp00s) {
		this.tqdpqp00s = tqdpqp00s;
	}


	public List<Tresre00> getTresre00s() {
		return tresre00s;
	}


	public void setTresre00s(List<Tresre00> tresre00s) {
		this.tresre00s = tresre00s;
	}

	public List<Tfbdbd00> getTfbdbd00s() {
		return tfbdbd00s;
	}


	public void setTfbdbd00s(List<Tfbdbd00> tfbdbd00s) {
		this.tfbdbd00s = tfbdbd00s;
	}

	public Tresre00 getTresre00Aux() {
		return this.getTresre00s().get(this.getTresre00s().size()-1);
	}
}
