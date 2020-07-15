package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tlm.faelecEntities.model.entities.Mabcub00;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
//import com.tlm.faelecEntities.model.entities.Mgentc00;
//import com.tlm.faelecEntities.model.entities.Mgentd00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Mparca00;
import com.tlm.faelecEntities.model.entities.Mparme00;
import com.tlm.faelecEntities.model.entities.Mparpd00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Mtipre00;


/**
 * The persistent class for the MCONCA00 database table.
 * 
 */
@Entity
@Table(name="MDEVPR00")
public class Mdevpr00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDIDDV", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ididdv;
	
	@Column(name="DSCRDV", length=100)
	private String dscrdv;

	
	@Column(name="IMAFDV")
	private String imafdv;
	
	@Column(name="REGIDV", nullable=false)
	private boolean regidv;
	
	@Column(name="USERDV", length=20, nullable=false)
	private String  userdv;
	
	@Column(name="PRGMdv", length=500, nullable=false)
	private String  prgmdv;
	
	@Column(name="FEACDV", nullable=false)
	private Date feacdv;
	
	@Column(name="MAQUDV", length=100, nullable=false)
	private String maqudv;
	
	//bi-directional many-to-one association to Mpropr00
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idipdv", referencedColumnName="ididvp")
	private Mvarpr00 mvarpr00;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idgrdv", referencedColumnName="idgrvt")
	private Mvaria00 mvaria00;
	
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

	//Getters and Setters
	public Integer getIdiddv() {
		return ididdv;
	}

	public void setIdiddv(Integer ididdv) {
		this.ididdv = ididdv;
	}

	public String getDscrdv() {
		return dscrdv;
	}

	public void setDscrdv(String dscrdv) {
		this.dscrdv = dscrdv;
	}

	public String getImafdv() {
		return imafdv;
	}

	public void setImafdv(String imafdv) {
		this.imafdv = imafdv;
	}

	public boolean isRegidv() {
		return regidv;
	}

	public void setRegidv(boolean regidv) {
		this.regidv = regidv;
	}

	public String getUserdv() {
		return userdv;
	}

	public void setUserdv(String userdv) {
		this.userdv = userdv;
	}

	public String getPrgmdv() {
		return prgmdv;
	}

	public void setPrgmdv(String prgmdv) {
		this.prgmdv = prgmdv;
	}

	public Date getFeacdv() {
		return feacdv;
	}

	public void setFeacdv(Date feacdv) {
		this.feacdv = feacdv;
	}

	public String getMaqudv() {
		return maqudv;
	}

	public void setMaqudv(String maqudv) {
		this.maqudv = maqudv;
	}

	public Mvarpr00 getMvarpr00() {
		return mvarpr00;
	}

	public void setMvarpr00(Mvarpr00 mvarpr00) {
		this.mvarpr00 = mvarpr00;
	}

	public Mvaria00 getMvaria00() {
		return mvaria00;
	}

	public void setMvaria00(Mvaria00 mvaria00) {
		this.mvaria00 = mvaria00;
	}
}