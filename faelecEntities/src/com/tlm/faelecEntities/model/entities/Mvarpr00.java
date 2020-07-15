package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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



/**
 * The persistent class for the MCONCA00 database table.
 * 
 */
@Entity
@Table(name="MVARPR00")
public class Mvarpr00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDIDVP", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ididvp;
	
	@Column(name="DSCRVP", unique=true, length=100, nullable=false)
	private String dscrvp;	
   
	@Column(name="IMAFVP")
	private String imafvp;
	
	@Column(name="REGIVP", nullable=false)
	private boolean regivp;
	
	@Column(name="USERVP", length=20, nullable=false)
	private String  uservp;
	
	@Column(name="PRGMVP", length=500, nullable=false)
	private String  prgmvp;
	
	@Column(name="FEACVP", nullable=false)
	private Date feacvp;
	
	@Column(name="MAQUVP", length=100, nullable=false)
	private String maquvp;
	
	//bi-directional many-to-one association to Mpropr00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idprvp", referencedColumnName="idcapr")
	private Mpropr00 mpropr00;
	
	//bi-directional many-to-one association to Mdevpr00
	//@OneToMany(cascade = CascadeType.ALL, mappedBy="mvarpr00", fetch = FetchType.LAZY, orphanRemoval=true)
	@OneToMany(mappedBy="mvarpr00",fetch = FetchType.LAZY, orphanRemoval=true)
	private List<Mdevpr00> mdevpr00s;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idgrvp", referencedColumnName="idgrvt")
	private Mvaria00 mvaria00;	
	
	@Transient
	private Object imagen;
	
	@Transient
	private List<String> subVariantes;
	

	public Mvarpr00(){
		
	}
	public Integer getIdidvp() {
		return ididvp;
	}

	public void setIdidvp(Integer ididvp) {
		this.ididvp = ididvp;
	}

	public String getDscrvp() {
		return dscrvp;
	}

	public void setDscrvp(String dscrvp) {
		this.dscrvp = dscrvp;
	}

	public boolean isRegivp() {
		return regivp;
	}

	public void setRegivp(boolean regivp) {
		this.regivp = regivp;
	}

	public String getUservp() {
		return uservp;
	}

	public void setUservp(String uservp) {
		this.uservp = uservp;
	}

	public String getPrgmvp() {
		return prgmvp;
	}

	public void setPrgmvp(String prgmvp) {
		this.prgmvp = prgmvp;
	}

	public Date getFeacvp() {
		return feacvp;
	}

	public void setFeacvp(Date feacvp) {
		this.feacvp = feacvp;
	}

	public String getMaquvp() {
		return maquvp;
	}

	public void setMaquvp(String maquvp) {
		this.maquvp = maquvp;
	}

	public Mpropr00 getMpropr00() {
		return mpropr00;
	}

	public void setMpropr00(Mpropr00 mpropr00) {
		this.mpropr00 = mpropr00;
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
	public List<Mdevpr00> getMdevpr00s() {
		return mdevpr00s;
	}
	public void setMdevpr00s(List<Mdevpr00> mdevpr00s) {
		this.mdevpr00s = mdevpr00s;
	}
	public Mvaria00 getMvaria00() {
		return mvaria00;
	}
	public void setMvaria00(Mvaria00 mvaria00) {
		this.mvaria00 = mvaria00;
	}
	public String getImafvp() {
		return imafvp;
	}
	public void setImafvp(String imafvp) {
		this.imafvp = imafvp;
	}
	public Object getImagen() {
		return imagen;
	}
	public void setImagen(Object imagen) {
		this.imagen = imagen;
	}
	public List<String> getSubVariantes() {
		return subVariantes;
	}
	public void setSubVariantes(List<String> subVariantes) {
		this.subVariantes = subVariantes;
	}	
	

}