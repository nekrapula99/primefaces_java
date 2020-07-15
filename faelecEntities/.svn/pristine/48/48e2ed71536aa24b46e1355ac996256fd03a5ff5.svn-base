package com.tlm.faelecEntities.model.entities;



import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the MINTEM00 database table.
 * 
 */
@Entity
@Table(name="MINTEM00")
@NamedQuery(name="Mintem00.findAll", query="SELECT m FROM Mintem00 m")
public class Mintem00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COMPAM")
	private String compam;

	@Column(name="ICODIG")
	private String icodig;

	@Column(name="IDAERR")
	private String idaerr;

	@Column(name="IDECAM")
	private String idecam;

	@Column(name="IDESCR")
	private String idescr;

	@Column(name="IDINEM")
	private int idinem;

	@Column(name="IFECCO")
	private Date ifecco;

	@Column(name="INOCAM")
	private String inocam;

	@Column(name="INOTAB")
	private String inotab;

	@Column(name="IREGCO")
	private String iregco;

	@Column(name="MAQUCA")
	private String maquca;

	@Column(name="PRGMEM")
	private String prgmem;

	@Column(name="USEREM")
	private String userem;

	//bi-directional many-to-one association to Mconca00
	@ManyToOne
	@JoinColumn(name="IDCCIA")
	private Mconca00 mconca00;

	public Mintem00() {
	}

	public String getCompam() {
		return this.compam;
	}

	public void setCompam(String compam) {
		this.compam = compam;
	}

	public String getIcodig() {
		return this.icodig;
	}

	public void setIcodig(String icodig) {
		this.icodig = icodig;
	}

	public String getIdaerr() {
		return this.idaerr;
	}

	public void setIdaerr(String idaerr) {
		this.idaerr = idaerr;
	}

	public String getIdecam() {
		return this.idecam;
	}

	public void setIdecam(String idecam) {
		this.idecam = idecam;
	}

	public String getIdescr() {
		return this.idescr;
	}

	public void setIdescr(String idescr) {
		this.idescr = idescr;
	}

	public int getIdinem() {
		return this.idinem;
	}

	public void setIdinem(int idinem) {
		this.idinem = idinem;
	}

	public Date getIfecco() {
		return this.ifecco;
	}

	public void setIfecco(Date ifecco) {
		this.ifecco = ifecco;
	}

	public String getInocam() {
		return this.inocam;
	}

	public void setInocam(String inocam) {
		this.inocam = inocam;
	}

	public String getInotab() {
		return this.inotab;
	}

	public void setInotab(String inotab) {
		this.inotab = inotab;
	}

	public String getIregco() {
		return this.iregco;
	}

	public void setIregco(String iregco) {
		this.iregco = iregco;
	}

	public String getMaquca() {
		return this.maquca;
	}

	public void setMaquca(String maquca) {
		this.maquca = maquca;
	}

	public String getPrgmem() {
		return this.prgmem;
	}

	public void setPrgmem(String prgmem) {
		this.prgmem = prgmem;
	}

	public String getUserem() {
		return this.userem;
	}

	public void setUserem(String userem) {
		this.userem = userem;
	}

	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

}