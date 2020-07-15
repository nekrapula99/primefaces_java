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
@Table(name = "TQDFQF00")
public class Tqdfqf00  implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDCPQF", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idcpqf;
	
	@Column(name="NRCPQF", nullable=false)
	private Integer nrcpqf;	
	
	@Column(name="DECPQF", length=500, nullable=false)
	private String decpqf;

	@Column(name="FELFQF")
	private Date felfqf;
	
	@Column(name="VACPQF")
	private Double vacpqf;	
	
	@Column(name="NRACQF", length=30)
	private String nracqf;

	@Column(name="OBIFQF", length=300)
	private String obifqf;
	
	@Column(name="ADCPQF", length=300)
	private String adcpqf;
	
	@Column(name="PZDIQF",nullable=false)
	private Integer pzdiqf;
	
	
	@Column(name="REGCQF", nullable=false)
	private boolean regcqf;

	@Column(name="USECQF", length=20, nullable=false)
	private String usecqf;

	@Column(name="PRGCQF", length=500, nullable=false)
	private String prgcqf;

	@Column(name="FEACQF", nullable=false)
	private Date feacqf;
	
	@Column(name="MAQCQF",  length=100, nullable=false)
	private String maqcqf;
	
	@Column(name="IDFAQF", nullable=false)  //PENDIENTE POR CREAR RELACION CON ALGUNA TABLA
	private Integer idfaqf;	
	
	//FK Id. Cotización 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDQCQF", nullable=false)
	private Tqigqg00 tqigqg00;
	
	//FK Id Medio de pago de la cuota
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMPQF", nullable=false)
	private Mgenus00 mgenus001;
	
	//FK Id. Moneda facturacion cuota
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMQQF", nullable=false)
	private Mgenus00 mgenus002;
	
	//FK MGENTE00  Tercero diferente al cliente a Facturar
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTFQF")
	private Mgente00 mgente00;
	
	//FK MCOTES00 Funcionario del clte o prospecto a quien se emvia la factura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFCQF", nullable=false)
	private Mcotes00 mcotes00;
	
	//bi-directional many-to-one association to tqigqg00
	//@OneToMany(mappedBy="tqdfqf00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	@OneToMany(mappedBy="tqdfqf00",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Tqdrfg00> tqdrfg00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqdfqf00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tfesfu00> tfesfu00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqdfqf00", cascade = CascadeType.ALL)
	private List<Tfacfc00> tfacfc00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqdfqf00", cascade = CascadeType.ALL)
	private List<Tfbcbc00> tfbcbc00s;
	
	public String toStringId()
	{
		return "idcpqf="+idcpqf;
	}
	public Tqdfqf00(){}
	
	@Transient
	private boolean pdfBorrador;
	
	@Transient
	private Tfesfu00 TfesfuAux;

	@Override
	public String toString() {
		return "Tqdfqf00 [idcpqf=" + idcpqf + ", nrcpqf=" + nrcpqf
				+ ", decpqf=" + decpqf + ", felfqf=" + felfqf + ", vacpqf="
				+ vacpqf + ", nracqf=" + nracqf + ", obifqf=" + obifqf
				+ ", adcpqf=" + adcpqf + ", regcqf=" + regcqf + ", usecqf="
				+ usecqf + ", prgcqf=" + prgcqf + ", feacqf=" + feacqf
				+ ", maqcqf=" + maqcqf + ", idfaqf=" + idfaqf + ", pzdiqf=" + pzdiqf  
				+ ", tqigqg00=" + (tqigqg00!=null?tqigqg00.getIdtqqg()+"-"+tqigqg00.getNroqqg():null)
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgente00=" + (mgente00!=null?mgente00.getIdtrte()+"-"+mgente00.getCodite():null)
				+ ", mcotes00=" + (mcotes00!=null?mcotes00.getIdctes()+"-"+mcotes00.getCoctes():null)
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

	public Integer getIdcpqf() {
		return idcpqf;
	}
	public void setIdcpqf(Integer idcpqf) {
		this.idcpqf = idcpqf;
	}
	public Integer getNrcpqf() {
		return nrcpqf;
	}

	public void setNrcpqf(Integer nrcpqf) {
		this.nrcpqf = nrcpqf;
	}

	public String getDecpqf() {
		return decpqf;
	}

	public void setDecpqf(String decpqf) {
		this.decpqf = decpqf;
	}

	public Date getFelfqf() {
		return felfqf;
	}

	public void setFelfqf(Date felfqf) {
		this.felfqf = felfqf;
	}

	public Double getVacpqf() {
		return vacpqf;
	}

	public void setVacpqf(Double vacpqf) {
		this.vacpqf = vacpqf;
	}

	public String getNracqf() {
		return nracqf;
	}

	public void setNracqf(String nracqf) {
		this.nracqf = nracqf;
	}

	public String getObifqf() {
		return obifqf;
	}

	public void setObifqf(String obifqf) {
		this.obifqf = obifqf;
	}

	public String getAdcpqf() {
		return adcpqf;
	}

	public void setAdcpqf(String adcpqf) {
		this.adcpqf = adcpqf;
	}

	public boolean isRegcqf() {
		return regcqf;
	}

	public void setRegcqf(boolean regcqf) {
		this.regcqf = regcqf;
	}

	public String getUsecqf() {
		return usecqf;
	}

	public void setUsecqf(String usecqf) {
		this.usecqf = usecqf;
	}

	public String getPrgcqf() {
		return prgcqf;
	}

	public void setPrgcqf(String prgcqf) {
		this.prgcqf = prgcqf;
	}

	public Date getFeacqf() {
		return feacqf;
	}

	public void setFeacqf(Date feacqf) {
		this.feacqf = feacqf;
	}

	public String getMaqcqf() {
		return maqcqf;
	}

	public void setMaqcqf(String maqcqf) {
		this.maqcqf = maqcqf;
	}

	public Integer getIdfaqf() {
		return idfaqf;
	}

	public void setIdfaqf(Integer idfaqf) {
		this.idfaqf = idfaqf;
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

	public Mgente00 getMgente00() {
		return mgente00;
	}

	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}

	public Mcotes00 getMcotes00() {
		return mcotes00;
	}

	public void setMcotes00(Mcotes00 mcotes00) {
		this.mcotes00 = mcotes00;
	}


	public List<Tqdrfg00> getTqdrfg00s() {
		return tqdrfg00s;
	}


	public void setTqdrfg00s(List<Tqdrfg00> tqdrfg00s) {
		this.tqdrfg00s = tqdrfg00s;
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
	public Integer getPzdiqf() {
		return pzdiqf;
	}
	public void setPzdiqf(Integer pzdiqf) {
		this.pzdiqf = pzdiqf;
	}
	public boolean isPdfBorrador() {
		return pdfBorrador;
	}
	public void setPdfBorrador(boolean pdfBorrador) {
		this.pdfBorrador = pdfBorrador;
	}
	
	public Tfesfu00 getTfesfuAux() {

		return this.getTfesfu00s().get(this.getTfesfu00s().size()-1);
	}
	
	
}
