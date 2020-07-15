package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
@Table(name = "MSWFSW00")
public class Mswfsw00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDSWSW",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idswsw;
	
	//Codigo del Sw
	@Column(name = "COSWSW", length=20, nullable=false)
	private String coswsw;
	
	//schemeAgencyName del Proveedor del SSW
	@Column(name = "SANPSW", length=100, nullable=false)
	private String sanpsw;
	
	//schemeAgencyID del Proveedor del SW
	@Column(name = "SAIPSW", length=20, nullable=false)
	private String saipsw;
	
	//schemeAgencyName del provedor SW
	@Column(name = "SANSSW", length=100, nullable=false)
	private String sanssw;
	
	//schemeAgencyID del SW
	@Column(name = "SAISSW", length=20, nullable=false)
	private String saissw;
	
	//schemeAgencyID del SW
	@Column(name = "IIDSSW", length=64 ,nullable=false)
	private String iidssw;
	
	//Descripcion del Sw
	@Column(name = "DSCSSW", length=100, nullable=false)
	private String dscssw;
	
	//Fecha autorizaci�n del SW
	@Column(name="FEASSW", nullable=false)
	private Date feassw;
	
	//schemeAgencyName del codigo seguridad del SW
	@Column(name = "SANCSW", length=100, nullable=false)
	private String sancsw;
	
	//schemeAgencyName del codigo seguridad del SW
	@Column(name = "SAICSW", length=20, nullable=false)
	private String saicsw;
	
	@Column(name="REGRSW", nullable=false)
	private boolean regrsw;

	@Column(name="USERSW", length=20, nullable=false)
	private String usersw;

	@Column(name="PRGRSW", length=500, nullable=false)
	private String prgrsw;

	@Column(name="FEARSW", nullable=false)
	private Date fearsw;
	
	@Column(name="MAQRSW",  length=100, nullable=false)
	private String maqrsw;

	//FK MCONCA ID COMPA�IA
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMSW", nullable=false)
	private Mconca00 mconca00;
	
	
	//FK mtiptx00 -- Tipo Transacci�n
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPVSW")
	private Mgente00 mgente00;

	
	public String toStringId()
	{
		return "idswsw="+idswsw;
	}
	
	@Override
	public String toString() {
		return "Mswfsw00 [idswsw=" + idswsw + ", coswsw=" + coswsw
				+ ", sanpsw=" + sanpsw + ", saipsw=" + saipsw + ", sanssw="
				+ sanssw + ", saissw=" + saissw + ", iidssw=" + iidssw
				+ ", dscssw=" + dscssw + ", feassw=" + feassw + ", sancsw="
				+ sancsw + ", saicsw=" + saicsw + ", regrsw=" + regrsw
				+ ", usersw=" + usersw + ", prgrsw=" + prgrsw + ", fearsw="
				+ fearsw + ", maqrsw=" + maqrsw 
				+ ", mconca00="  + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", mgente00="  + (mgente00!=null?mgente00.getIdtrte()+"-"+mgente00.getCodite():null)
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

	public int getIdswsw() {
		return idswsw;
	}

	public void setIdswsw(int idswsw) {
		this.idswsw = idswsw;
	}

	public String getSanpsw() {
		return sanpsw;
	}

	public void setSanpsw(String sanpsw) {
		this.sanpsw = sanpsw;
	}

	public String getSaipsw() {
		return saipsw;
	}

	public void setSaipsw(String saipsw) {
		this.saipsw = saipsw;
	}

	public String getSanssw() {
		return sanssw;
	}

	public void setSanssw(String sanssw) {
		this.sanssw = sanssw;
	}

	public String getSaissw() {
		return saissw;
	}

	public void setSaissw(String saissw) {
		this.saissw = saissw;
	}

	public String getIidssw() {
		return iidssw;
	}

	public void setIidssw(String iidssw) {
		this.iidssw = iidssw;
	}

	public String getDscssw() {
		return dscssw;
	}

	public void setDscssw(String dscssw) {
		this.dscssw = dscssw;
	}

	public Date getFeassw() {
		return feassw;
	}

	public void setFeassw(Date feassw) {
		this.feassw = feassw;
	}

	public String getSancsw() {
		return sancsw;
	}

	public void setSancsw(String sancsw) {
		this.sancsw = sancsw;
	}

	public String getSaicsw() {
		return saicsw;
	}

	public void setSaicsw(String saicsw) {
		this.saicsw = saicsw;
	}

	public boolean isRegrsw() {
		return regrsw;
	}

	public void setRegrsw(boolean regrsw) {
		this.regrsw = regrsw;
	}

	public String getUsersw() {
		return usersw;
	}

	public void setUsersw(String usersw) {
		this.usersw = usersw;
	}

	public String getPrgrsw() {
		return prgrsw;
	}

	public void setPrgrsw(String prgrsw) {
		this.prgrsw = prgrsw;
	}

	public Date getFearsw() {
		return fearsw;
	}

	public void setFearsw(Date fearsw) {
		this.fearsw = fearsw;
	}

	public String getMaqrsw() {
		return maqrsw;
	}

	public void setMaqrsw(String maqrsw) {
		this.maqrsw = maqrsw;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public Mgente00 getMgente00() {
		return mgente00;
	}

	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}

	public String getCoswsw() {
		return coswsw;
	}

	public void setCoswsw(String coswsw) {
		this.coswsw = coswsw;
	}

}
	