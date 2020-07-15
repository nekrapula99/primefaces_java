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
@Table(name = "MCOTOS00")
public class Mcotos00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDCTOS", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idctos;
	
	@Column(name="EMATOS", length=100, nullable=false)
	private String ematos;
	
	@Column(name="TELTOS", length=20, nullable=false)
	private String teltos;
	
	@Column(name="REGTOS", nullable=false)
	private boolean regtos;

	@Column(name="USETOS", length=20, nullable=false)
	private String usetos;

	@Column(name="PRGTOS", length=500, nullable=false)
	private String prgtos;

	@Column(name="FEATOS", nullable=false)
	private Date featos;
	
	@Column(name="MAQTOS",  length=100, nullable=false)
	private String maqtos;
	
	
	//FK MCOTES00 ID CONTACTO
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCSOS", nullable=false)
	private Mcotes00 mcotes001;
	
	//FK Mgente00 Tercero
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTAOS", nullable=false)
	private Mgente00 mgente001;
	
	//FK Mgenus00 AREA
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDATOS", nullable=false)
	private Mgenus00 mgenus001;
	
	//FK Mgenus00 CARGO
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGTOS", nullable=false)
	private Mgenus00 mgenus002;
	
	
	@Override
	public String toString() {
		return "Mcotos00 [idctos=" + idctos + ", ematos=" + ematos
				+ ", teltos=" + teltos + ", regtos=" + regtos + ", usetos="
				+ usetos + ", prgtos=" + prgtos + ", featos=" + featos
				+ ", maqtos=" + maqtos 
				+ ", mcotes001=" + (mcotes001!=null?mcotes001.getIdctes()+"-"+mcotes001.getCoctes():null)
				+ ", mgente001=" + (mgente001!=null?mgente001.getIdtrte()+"-"+mgente001.getCodite():null)
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)+ "]";

	}
	
	public String toStringId()
	{
		return "idctos="+idctos;
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


	public String getEmatos() {
		return ematos;
	}

	public void setEmatos(String ematos) {
		this.ematos = ematos;
	}

	public String getTeltos() {
		return teltos;
	}

	public void setTeltos(String teltos) {
		this.teltos = teltos;
	}

	public boolean isRegtos() {
		return regtos;
	}

	public void setRegtos(boolean regtos) {
		this.regtos = regtos;
	}

	public String getUsetos() {
		return usetos;
	}

	public void setUsetos(String usetos) {
		this.usetos = usetos;
	}

	public String getPrgtos() {
		return prgtos;
	}

	public void setPrgtos(String prgtos) {
		this.prgtos = prgtos;
	}

	public Date getFeatos() {
		return featos;
	}

	public void setFeatos(Date featos) {
		this.featos = featos;
	}

	public String getMaqtos() {
		return maqtos;
	}

	public void setMaqtos(String maqtos) {
		this.maqtos = maqtos;
	}

	public Mcotes00 getMcotes001() {
		return mcotes001;
	}

	public void setMcotes001(Mcotes00 mcotes001) {
		this.mcotes001 = mcotes001;
	}

	public Mgente00 getMgente001() {
		return mgente001;
	}

	public void setMgente001(Mgente00 mgente001) {
		this.mgente001 = mgente001;
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

	public int getIdctos() {
		return idctos;
	}

	public void setIdctos(int idctos) {
		this.idctos = idctos;
	}
	


}
