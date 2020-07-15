package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Table(name = "TQIGQG00")
public class Tqigqg00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;;
	
	@Id
	@Column(name="IDTQQG",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idtqqg;
	
	@Column(name = "NROQQG", length=20, nullable=false)
	private String nroqqg;
	
	@Column(name="VACOQG") //float
	private Double vacoqg;
	
	@Column(name = "NROPQG", length=30)
	private String nropqg;
	
	@Column(name = "IDNCQG")
	private Integer idncqg;
	
	@Column(name = "NROCQG", length=30)
	private String nrocqg;
	
	@Column(name="FECRQG", nullable=false)
	private Date fecrqg;
	
	@Column(name="FEVIQG", nullable=false)
	private Date feviqg;
	
	@Column(name="FEPDQG", nullable=false)
	private Date fepdqg;
	
	@Column(name = "DESCQG", length=500, nullable=false)
	private String descqg;
	
	@Column(name = "ADJUQG", length=300)
	private String adjuqg;
	
	@Column(name="REGCQG", nullable=false)
	private boolean regcqg;

	@Column(name="USECQG", length=20, nullable=false)
	private String usecqg;

	@Column(name="PRGCQG", length=500, nullable=false)
	private String prgcqg;

	@Column(name="FEACQG", nullable=false)
	private Date feacqg;
	
	@Column(name="MAQCQG",  length=100, nullable=false)
	private String maqcqg;
	
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "tqigqg00", cascade = CascadeType.ALL)
	//@OneToMany(fetch = FetchType.LAZY,mappedBy = "tqigqg00", cascade = CascadeType.ALL,  orphanRemoval = true)
	@OneToMany(mappedBy="tqigqg00",fetch = FetchType.EAGER,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tqdrqr00> tqdrqr00s;
	
	//bi-directional many-to-one association to tqigqg00
	//@OneToMany(mappedBy="tqigqg00")
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "tqigqg00", cascade = CascadeType.ALL)
	@OneToMany(mappedBy="tqigqg00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tqdfqf00> tqdfqf00s;
	
	//FK Compa�ia que factura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMQG", nullable=false)
	private Mconca00 mconca00;
	
	//FK mtiptx00 -- Tipo Transacci�n
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTXQG", nullable=false)
	private Mtiptx00 mtiptx00;
	
	//FK mgente00 -- Cliente o Prospecto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCLQG", nullable=false)
	private Mgente00 mgente00;
	
	//FK Mgenus001 -- Moneda Cotizacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMOQG", nullable=false)
	private Mgenus00 mgenus001;
	
	//FK Mpropr00 --producto Principal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPPQG", nullable=false)
	private Mpropr00 mpropr00;
	
	//FK Mfunfu001 --Funcionario Comercial Responsable
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFRQG")
	private Mfunfu00 mfunfu001;
	
	//FK Mfunfu002 --Funcionario Apoyo Tecnico Preventa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFAQG")
	private Mfunfu00 mfunfu002;
	
	//FK Mgenus002 Organizaci�n Ventas
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOVQG", nullable=false)
	private Mgenus00 mgenus002;
	
	//FK Mgenus003 Canal Distribuci�n
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCVQG", nullable=false)
	private Mgenus00 mgenus003;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqigqg00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tqesqe00> tqesqe00s;
		
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqigqg00",cascade=CascadeType.ALL)
	private List<Tqenqv00> Tqenqv00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqigqg00", cascade = CascadeType.ALL)
	private List<Tfesfu00> tfesfu00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqigqg00", cascade = CascadeType.ALL)
	private List<Tfacfc00> tfacfc00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqigqg00", cascade = CascadeType.ALL)
	private List<Tfbcbc00> tfbcbc00s;
	
	@Transient
	private Tqesqe00 TqesqeAux;
	
	@Override
	public String toString() {
		return "Tqigqg00 [idtqqg=" + idtqqg + ", nroqqg=" + nroqqg
				+ ", vacoqg=" + vacoqg + ", nropqg=" + nropqg + ", idncqg=" + idncqg
				+ ", nrocqg=" + nrocqg + ", fecrqg=" + fecrqg + ", feviqg=" + feviqg
				+ ", fepdqg=" + fepdqg + ", descqg=" + descqg + ", adjuqg="
				+ adjuqg + ", regcqg=" + regcqg + ", usecqg=" + usecqg
				+ ", prgcqg=" + prgcqg + ", feacqg=" + feacqg + ", maqcqg=" + maqcqg
				+ ", mtiptx00=" + (mtiptx00!=null?mtiptx00.getIdtptx()+"-"+mtiptx00.getCotrtx():null)
				+ ", mgente00=" + (mgente00!=null?mgente00.getIdtrte()+"-"+mgente00.getCodite():null)
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mpropr00=" + (mpropr00!=null?mpropr00.getIdcapr()+"-"+mpropr00.getCodcpr():null)
				+ ", mfunfu001=" + (mfunfu001!=null?mfunfu001.getIdcofu()+"-"+mfunfu001.getCocufu():null)
				+ ", mfunfu002=" + (mfunfu002!=null?mfunfu002.getIdcofu()+"-"+mfunfu002.getCocufu():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null) + "]";

	}

	public String toStringId()
	{
		return "idtqqg="+idtqqg;
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


	public Integer getIdtqqg() {
		return idtqqg;
	}

	public void setIdtqqg(Integer idtqqg) {
		this.idtqqg = idtqqg;
	}

	public String getNroqqg() {
		return nroqqg;
	}

	public void setNroqqg(String nroqqg) {
		this.nroqqg = nroqqg;
	}

	public Double getVacoqg() {
		return vacoqg;
	}

	public void setVacoqg(Double vacoqg) {
		this.vacoqg = vacoqg;
	}

	public String getNropqg() {
		return nropqg;
	}

	public void setNropqg(String nropqg) {
		this.nropqg = nropqg;
	}

	public String getNrocqg() {
		return nrocqg;
	}

	public void setNrocqg(String nrocqg) {
		this.nrocqg = nrocqg;
	}

	public Date getFeviqg() {
		return feviqg;
	}

	public void setFeviqg(Date feviqg) {
		this.feviqg = feviqg;
	}

	public Date getFepdqg() {
		return fepdqg;
	}

	public void setFepdqg(Date fepdqg) {
		this.fepdqg = fepdqg;
	}

	public String getDescqg() {
		return descqg;
	}

	public void setDescqg(String descqg) {
		this.descqg = descqg;
	}

	public String getAdjuqg() {
		return adjuqg;
	}

	public void setAdjuqg(String adjuqg) {
		this.adjuqg = adjuqg;
	}

	public boolean isRegcqg() {
		return regcqg;
	}

	public void setRegcqg(boolean regcqg) {
		this.regcqg = regcqg;
	}

	public String getUsecqg() {
		return usecqg;
	}

	public void setUsecqg(String usecqg) {
		this.usecqg = usecqg;
	}

	public String getPrgcqg() {
		return prgcqg;
	}

	public void setPrgcqg(String prgcqg) {
		this.prgcqg = prgcqg;
	}

	public Date getFeacqg() {
		return feacqg;
	}

	public void setFeacqg(Date feacqg) {
		this.feacqg = feacqg;
	}

	public String getMaqcqg() {
		return maqcqg;
	}

	public void setMaqcqg(String maqcqg) {
		this.maqcqg = maqcqg;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	public Mgente00 getMgente00() {
		return mgente00;
	}

	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}

	public Mgenus00 getMgenus001() {
		return mgenus001;
	}

	public void setMgenus001(Mgenus00 mgenus001) {
		this.mgenus001 = mgenus001;
	}

	public Mpropr00 getMpropr00() {
		return mpropr00;
	}

	public void setMpropr00(Mpropr00 mpropr00) {
		this.mpropr00 = mpropr00;
	}

	public Mfunfu00 getMfunfu001() {
		return mfunfu001;
	}

	public void setMfunfu001(Mfunfu00 mfunfu001) {
		this.mfunfu001 = mfunfu001;
	}

	public Mfunfu00 getMfunfu002() {
		return mfunfu002;
	}

	public void setMfunfu002(Mfunfu00 mfunfu002) {
		this.mfunfu002 = mfunfu002;
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

	public Date getFecrqg() {
		return fecrqg;
	}

	public void setFecrqg(Date fecrqg) {
		this.fecrqg = fecrqg;
	}

	public Integer getIdncqg() {
		return idncqg;
	}

	public void setIdncqg(Integer idncqg) {
		this.idncqg = idncqg;
	}

	public List<Tqesqe00> getTqesqe00s() {
		return tqesqe00s;
	}

	public void setTqesqe00s(List<Tqesqe00> tqesqe00s) {
		this.tqesqe00s = tqesqe00s;
	}

	public List<Tqdrqr00> getTqdrqr00s() {
		if(tqdrqr00s == null){
			tqdrqr00s = new ArrayList<Tqdrqr00>();
		}
		return this.tqdrqr00s;
	}

	public void setTqdrqr00s(List<Tqdrqr00> tqdrqr00s) {
		this.tqdrqr00s = tqdrqr00s;
	}

	public List<Tqdfqf00> getTqdfqf00s() {
		
		if(tqdfqf00s == null){
			tqdfqf00s = new ArrayList<Tqdfqf00>();
		}
		return this.tqdfqf00s;
	

	}

	public void setTqdfqf00s(List<Tqdfqf00> tqdfqf00s) {
		this.tqdfqf00s = tqdfqf00s;
	}

	public List<Tqenqv00> getTqenqv00s() {
		return Tqenqv00s;
	}

	public void setTqenqv00s(List<Tqenqv00> tqenqv00s) {
		Tqenqv00s = tqenqv00s;
	}

	public List<Tfesfu00> getTfesfu00s() {
		return tfesfu00s;
	}

	public void setTfesfu00s(List<Tfesfu00> tfesfu00s) {
		this.tfesfu00s = tfesfu00s;
	}

	public List<Tfacfc00> getTfacfc00s() {
		return tfacfc00s;
	}

	public void setTfacfc00s(List<Tfacfc00> tfacfc00s) {
		this.tfacfc00s = tfacfc00s;
	}

	public List<Tfbcbc00> getTfbcbc00s() {
		return tfbcbc00s;
	}

	public void setTfbcbc00s(List<Tfbcbc00> tfbcbc00s) {
		this.tfbcbc00s = tfbcbc00s;
	}

	public Tqesqe00 getTqesqeAux() {

			return this.getTqesqe00s().get(this.getTqesqe00s().size()-1);
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}


}
