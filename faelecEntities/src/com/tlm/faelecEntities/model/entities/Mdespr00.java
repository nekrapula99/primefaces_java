package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Mpropr00;

/**
 * The persistent class for the MDESPR00 database table.
 * 
 */
@Entity
@Table(name="MDESPR00")
public class Mdespr00 implements Serializable,Cloneable {
	private static final long serialVersionUID = -2691536787213887396L;

	@Id
	@Column(name="IDCADP", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcadp;

	@Column(name="DSCRDP", nullable=false, length=1000)
	private String dscrdp;

	@Column(name="FEACDP", nullable=false)
	private Date feacdp;

	@Column(name="MAQUDP", nullable=false, length=100)
	private String maqudp;

	@Column(name="PRGMDP", nullable=false, length=20)
	private String prgmdp;

	@Column(name="REGIDP", nullable=false)
	private boolean regidp;

	@Column(name="USERDP", nullable=false, length=20)
	private String userdp;

	//bi-directional many-to-one association to Mpropr00
	@ManyToOne(fetch=FetchType.LAZY) //EAGER
	@JoinColumn(name="idprdp", referencedColumnName="idcapr")
	private Mpropr00 mpropr00;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTTDP")
	private Mtiptx00 mtiptx00;

	//bi-directional many-to-one association to Midiom00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDIDDP")
	private Midiom00 midiom00;

	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMDP", unique=true)
	private Mconca00 mconca00;

    public Mdespr00() {
    }

	public int getIdcadp() {
		return this.idcadp;
	}

	public void setIdcadp(int idcadp) {
		this.idcadp = idcadp;
	}

	public String getDscrdp() {
		return this.dscrdp;
	}

	public void setDscrdp(String dscrdp) {
		this.dscrdp = dscrdp;
	}

	public Date getFeacdp() {
		return this.feacdp;
	}

	public void setFeacdp(Date feacdp) {
		this.feacdp = feacdp;
	}

	public String getMaqudp() {
		return this.maqudp;
	}

	public void setMaqudp(String maqudp) {
		this.maqudp = maqudp;
	}

	public String getPrgmdp() {
		return this.prgmdp;
	}

	public void setPrgmdp(String prgmdp) {
		this.prgmdp = prgmdp;
	}

	public boolean getRegidp() {
		return this.regidp;
	}

	public void setRegidp(boolean regidp) {
		this.regidp = regidp;
	}

	public String getUserdp() {
		return this.userdp;
	}

	public void setUserdp(String userdp) {
		this.userdp = userdp;
	}

	public Mpropr00 getMpropr00() {
		return this.mpropr00;
	}

	public void setMpropr00(Mpropr00 mpropr00) {
		this.mpropr00 = mpropr00;
	}
	
	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	public Midiom00 getMidiom00() {
		return this.midiom00;
	}

	public void setMidiom00(Midiom00 midiom00) {
		this.midiom00 = midiom00;
	}
	
	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
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
	@Override
	public String toString() {
		return "Mdespr00 [idcadp=" + idcadp + ", dscrdp=" + dscrdp
				+ ", idprdp=" + mpropr00.getIdcapr() + ", idttdp=" + mtiptx00.getIdtptx() + ", ididdp="
				+ midiom00.getIdiddi() + ", idcmdp=" + mconca00.getIdccia() + ", regidp=" + regidp + "]";
	}
	public String toStringId()
	{
		return "idcadp="+idcadp;
	}

}