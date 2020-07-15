package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tlm.faelecEntities.model.entities.Mparca00;

/**
 * The persistent class for the MPARPD00 database table.
 * 
 */
@Entity
@Table(name="MPARPD00")
public class Mparpd00 implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTRPD", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrpd;

	@Column(name="CODCPD", nullable=false, length=20)
	private String codcpd;

	@Column(name="COLOPD", length=20)
	private String colopd;

	@Column(name="DSCAPD", nullable=false, length=50)
	private String dscapd;

	@Column(name="DSRAPD", length=100)
	private String dsrapd;

	@Column(name="FEADPD", nullable=false)
	private Date feadpd;

	@Column(name="FUENPD", length=50)
	private String fuenpd;

	@Column(name="INCCPD", length=1)
	private String inccpd;
	
	@Column(name="FFECPD", length=12)
	private String ffecpd;

	@Column(name="INCFPD", length=1)
	private String incfpd;
	
	@Column(name="INCNPD", nullable=false, length=1)
	private String incnpd;

	@Column(name="INDNPD", nullable=false, length=1)
	private String indnpd;
	
	@Column(name="INRPPD", nullable=false, length=1)
	private String inrppd;

	@Column(name="INSDPD", length=1)
	private String insdpd;
   
    @Lob()
	@Column(name="LOGOPD")
	private byte[] logopd;

	@Column(name="MAQDPD", nullable=false, length=100)
	private String maqdpd;

	@Column(name="NRDEPD", precision=5)
	private Integer nrdepd;

	@Column(name="PRGDPD", nullable=false, length=20)
	private String prgdpd;

	@Column(name="REGIPD", nullable=false)
	private boolean regipd;

	@Column(name="TAMAPD", precision=2)
	private Integer tamapd;

	@Column(name="USEDPD", nullable=false, length=20)
	private String usedpd;

	@Column(name="VRCCPD", length=100)
	private String vrccpd;

	//bi-directional many-to-one association to Mparca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCAPD", nullable=false)
	private Mparca00 mparca00;


//	//bi-directional many-to-one association to Mconca00
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="IDCMPD", unique=true)
//	private Mconca00 mconca00;

	public Mparpd00(){
		inrppd="N";
		incnpd="N";
		indnpd="N";
	}

	public int getIdtrpd() {
		return this.idtrpd;
	}

	public void setIdtrpd(int idtrpd) {
		this.idtrpd = idtrpd;
	}

	public String getCodcpd() {
		return this.codcpd;
	}

	public void setCodcpd(String codcpd) {
		this.codcpd = codcpd;
	}

	public String getColopd() {
		return this.colopd;
	}

	public void setColopd(String colopd) {
		if(colopd.contains("#")){
			this.colopd = colopd;
		}else 
			this.colopd = "#"+colopd;
	}

	public String getDscapd() {
		return this.dscapd;
	}

	public void setDscapd(String dscapd) {
		this.dscapd = dscapd;
	}

	public String getDsrapd() {
		return this.dsrapd;
	}

	public void setDsrapd(String dsrapd) {
		this.dsrapd = dsrapd;
	}

	public Date getFeadpd() {
		return this.feadpd;
	}

	public void setFeadpd(Date feadpd) {
		this.feadpd = feadpd;
	}

	public String getFuenpd() {
		return this.fuenpd;
	}

	public void setFuenpd(String fuenpd) {
		this.fuenpd = fuenpd;
	}

	public String getInccpd() {
		return this.inccpd;
	}

	public void setInccpd(String inccpd) {
		this.inccpd = inccpd;
	}

	public boolean getIncnpd() {
		return incnpd.equalsIgnoreCase("S");
	}

	public void setIncnpd(boolean incnpd) {
		if(incnpd){
			this.incnpd ="S";
		}else{
			this.incnpd ="N";
		}
	}

	public boolean getInrppd() {
		return inrppd.equalsIgnoreCase("S");
	}

	public void setInrppd(boolean inrppd) {
		if(inrppd){
			this.inrppd ="S";
		}else{
			this.inrppd ="N";
		}
	}

	public String getInsdpd() {
		return this.insdpd;
	}

	public void setInsdpd(String insdpd) {
		this.insdpd = insdpd;
	}

	public String getMaqdpd() {
		return this.maqdpd;
	}

	public void setMaqdpd(String maqdpd) {
		this.maqdpd = maqdpd;
	}

	public Integer getNrdepd() {
		return this.nrdepd;
	}

	public void setNrdepd(Integer nrdepd) {
		this.nrdepd = nrdepd;
	}

	public String getPrgdpd() {
		return this.prgdpd;
	}

	public void setPrgdpd(String prgdpd) {
		this.prgdpd = prgdpd;
	}

	public boolean getRegipd() {
		return this.regipd;
	}

	public void setRegipd(boolean regipd) {
		this.regipd = regipd;
	}

	public Integer getTamapd() {
		return this.tamapd;
	}

	public void setTamapd(Integer tamapd) {
		this.tamapd = tamapd;
	}

	public String getUsedpd() {
		return this.usedpd;
	}

	public void setUsedpd(String usedpd) {
		this.usedpd = usedpd;
	}

	public String getVrccpd() {
		return this.vrccpd;
	}

	public void setVrccpd(String vrccpd) {
		this.vrccpd = vrccpd;
	}

	public Mparca00 getMparca00() {
		return this.mparca00;
	}

	public void setMparca00(Mparca00 mparca00) {
		this.mparca00 = mparca00;
	}

	public boolean getIndnpd() {
		return indnpd.equalsIgnoreCase("S");
	}

	public void setIndnpd(boolean indnpd) {
		if(indnpd){
			this.indnpd ="S";
		}else{
			this.indnpd ="N";
		}
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

	public String getFfecpd() {
		return ffecpd;
	}

	public void setFfecpd(String ffecpd) {
		this.ffecpd = ffecpd;
	}

	public String getIncfpd() {
		return incfpd;
	}

	public void setIncfpd(String incfpd) {
		this.incfpd = incfpd;
	}

	public byte[] getLogopd() {
		return logopd;
	}

	public void setLogopd(byte[] logopd) {
		this.logopd = logopd;
	}
	
}