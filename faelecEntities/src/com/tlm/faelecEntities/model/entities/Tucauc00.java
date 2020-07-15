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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TUCAUC00")
public class Tucauc00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDCOUC",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcouc;

	//numero usado
	@Column(name = "NRRUUC", nullable=false)
	private Integer nrruuc;
	
	//fecha uso
	@Column(name="FURAUC", nullable=false)
	private Date furauc;
	
	//Hora uso
	@Column(name="HORAUC", nullable=false)
	private String horauc;
	
	//fecha actualizcion
	@Column(name="RAFEUC", nullable=false)
	private Date rafeuc;
	
	@Column(name="REGRUC", nullable=false)
	private boolean regruc;

	@Column(name="USERUC", length=20, nullable=false)
	private String useruc;

	@Column(name="PRGRUC", length=500, nullable=false)
	private String prgruc;

	@Column(name="FEARUC", nullable=false)
	private Date fearuc;
	
	@Column(name="MAQRUC",  length=100, nullable=false)
	private String maqruc;
	
	//FK MCONCA ID COMPA�IA
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRAUC", nullable=false)
	private Mrafra00 mrafra00;


	public int getIdcouc() {
		return idcouc;
	}

	public void setIdcouc(int idcouc) {
		this.idcouc = idcouc;
	}

	public Integer getNrruuc() {
		return nrruuc;
	}

	public void setNrruuc(Integer nrruuc) {
		this.nrruuc = nrruuc;
	}

	public Date getFurauc() {
		return furauc;
	}

	public void setFurauc(Date furauc) {
		this.furauc = furauc;
	}

	public String getHorauc() {
		return horauc;
	}

	public void setHorauc(String horauc) {
		this.horauc = horauc;
	}

	public Date getRafeuc() {
		return rafeuc;
	}

	public void setRafeuc(Date rafeuc) {
		this.rafeuc = rafeuc;
	}

	public boolean isRegruc() {
		return regruc;
	}

	public void setRegruc(boolean regruc) {
		this.regruc = regruc;
	}

	public String getUseruc() {
		return useruc;
	}

	public void setUseruc(String useruc) {
		this.useruc = useruc;
	}

	public String getPrgruc() {
		return prgruc;
	}

	public void setPrgruc(String prgruc) {
		this.prgruc = prgruc;
	}

	public Date getFearuc() {
		return fearuc;
	}

	public void setFearuc(Date fearuc) {
		this.fearuc = fearuc;
	}

	public String getMaqruc() {
		return maqruc;
	}

	public void setMaqruc(String maqruc) {
		this.maqruc = maqruc;
	}

	public Mrafra00 getMrafra00() {
		return mrafra00;
	}

	public void setMrafra00(Mrafra00 mrafra00) {
		this.mrafra00 = mrafra00;
	}

	public String toStringId()
	{
		return "idcouc="+idcouc;
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
		return "Tucauc00 [idcouc=" + idcouc + ", nrruuc=" + nrruuc
				+ ", furauc=" + furauc + ", horauc=" + horauc + ", rafeuc="
				+ rafeuc + ", regruc=" + regruc + ", useruc=" + useruc
				+ ", prgruc=" + prgruc + ", fearuc=" + fearuc + ", maqruc="
				+ maqruc + ", mrafra00=" + mrafra00 
				+ ", mrafra00=" + (mrafra00!=null?mrafra00.getIdrara()+"-"+mrafra00.getCorara():null)
				+ "]";
	}

}