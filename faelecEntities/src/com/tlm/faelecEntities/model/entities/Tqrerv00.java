package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "TQRERV00")
public class Tqrerv00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDRQRV",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrqrv;
	
	@Column(name="REGCRV", nullable=false)
	private boolean regcrv;

	@Column(name="USECRV", length=20, nullable=false)
	private String usecrv;

	@Column(name="PRGCRV", length=500, nullable=false)
	private String prgcrv;

	@Column(name="FEACRV", nullable=false)
	private Date feacrv;
	
	@Column(name="MAQCRV",  length=100, nullable=false)
	private String maqcrv;

	//FK  id. Envio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDQERV", nullable=false)
	private Tqenqv00 tqenqv00;
	
	//FK MCOTES00 id Receptor del envio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCRRV", nullable=false)
	private Mcotes00 mcotes00;

	
	public String toStringId()
	{
		return "idtrqrv="+idtrqrv;
	}
	
	@Override
	public String toString() {
		return "Tqrerv00 [idtrqrv=" + idtrqrv + ", regcrv=" + regcrv
				+ ", usecrv=" + usecrv + ", prgcrv=" + prgcrv + ", feacrv="
				+ feacrv + ", maqcrv=" + maqcrv
				+ ", tqenqv00=" + (tqenqv00!=null?tqenqv00.getIdenqv():null)
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
	
	
	public int getIdtrqrv() {
		return idtrqrv;
	}

	public void setIdtrqrv(int idtrqrv) {
		this.idtrqrv = idtrqrv;
	}

	public boolean isRegcrv() {
		return regcrv;
	}

	public void setRegcrv(boolean regcrv) {
		this.regcrv = regcrv;
	}

	public String getUsecrv() {
		return usecrv;
	}

	public void setUsecrv(String usecrv) {
		this.usecrv = usecrv;
	}

	public String getPrgcrv() {
		return prgcrv;
	}

	public void setPrgcrv(String prgcrv) {
		this.prgcrv = prgcrv;
	}

	public Date getFeacrv() {
		return feacrv;
	}

	public void setFeacrv(Date feacrv) {
		this.feacrv = feacrv;
	}

	public String getMaqcrv() {
		return maqcrv;
	}

	public void setMaqcrv(String maqcrv) {
		this.maqcrv = maqcrv;
	}

	public Tqenqv00 getTqenqv00() {
		return tqenqv00;
	}

	public void setTqenqv00(Tqenqv00 tqenqv00) {
		this.tqenqv00 = tqenqv00;
	}

	public Mcotes00 getMcotes00() {
		return mcotes00;
	}

	public void setMcotes00(Mcotes00 mcotes00) {
		this.mcotes00 = mcotes00;
	}

}
