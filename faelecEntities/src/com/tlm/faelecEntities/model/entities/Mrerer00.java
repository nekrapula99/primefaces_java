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
@Table(name = "MRERER00")
public class Mrerer00 implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDIRER", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idirer;
	
	@Column(name="CODRER", length=20,nullable=false)
	private String codrer;
	
	@Column(name="NOMRER", length=100,nullable=false)
	private String nomrer;
	
	@Column(name="REIRER", length=1,nullable=false)
	private String reirer;
	
	@Column(name="REFRER", length=1,nullable=false)
	private String refrer;
	
	@Column(name="EXIRER", length=1,nullable=false)
	private String exirer;
	
	@Column(name="PCIRER", nullable=false)
	private Double pcirer;
	
	@Column(name="REGRER", nullable=false)
	private boolean regrer;

	@Column(name="USERER", length=20, nullable=false)
	private String userer;

	@Column(name="PRGRER", length=20, nullable=false)
	private String prgrer;

	@Column(name="FEARER", nullable=false)
	private Date fearer;
	
	@Column(name="MAQRER",  length=100, nullable=false)
	private String maqrer;
	
	//FK Mregcg00 (comprador)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCRER", nullable=false)
	private Mregcg00 mregcg001;
	
	//FK Mregcg00 (vendedor)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDVRER", nullable=false)
	private Mregcg00 mregcg002;

	public int getIdirer() {
		return idirer;
	}

	public void setIdirer(int idirer) {
		this.idirer = idirer;
	}

	public String getCodrer() {
		return codrer;
	}

	public void setCodrer(String codrer) {
		this.codrer = codrer;
	}

	public String getNomrer() {
		return nomrer;
	}

	public void setNomrer(String nomrer) {
		this.nomrer = nomrer;
	}

	public String getReirer() {
		return reirer;
	}

	public void setReirer(String reirer) {
		this.reirer = reirer;
	}

	public String getRefrer() {
		return refrer;
	}

	public void setRefrer(String refrer) {
		this.refrer = refrer;
	}

	public String getExirer() {
		return exirer;
	}

	public void setExirer(String exirer) {
		this.exirer = exirer;
	}

	public Double getPcirer() {
		return pcirer;
	}

	public void setPcirer(Double pcirer) {
		this.pcirer = pcirer;
	}

	public boolean isRegrer() {
		return regrer;
	}

	public void setRegrer(boolean regrer) {
		this.regrer = regrer;
	}

	public String getUserer() {
		return userer;
	}

	public void setUserer(String userer) {
		this.userer = userer;
	}

	public String getPrgrer() {
		return prgrer;
	}

	public void setPrgrer(String prgrer) {
		this.prgrer = prgrer;
	}

	public Date getFearer() {
		return fearer;
	}

	public void setFearer(Date fearer) {
		this.fearer = fearer;
	}

	public String getMaqrer() {
		return maqrer;
	}

	public void setMaqrer(String maqrer) {
		this.maqrer = maqrer;
	}


	@Override
	public String toString() {
		return "Mrerer00 [idirer=" + idirer + ", codrer=" + codrer
				+ ", nomrer=" + nomrer + ", reirer=" + reirer + ", refrer="
				+ refrer + ", exirer=" + exirer + ", pcirer=" + pcirer
				+ ", regrer=" + regrer + ", userer=" + userer + ", prgrer="
				+ prgrer + ", fearer=" + fearer 
				+ ", maqrer=" + maqrer
				+ ", mregcg001=" + (mregcg001!=null?mregcg001.getIdiecg()+"-"+mregcg001.getCoregc():null)
				+ ", mregcg002=" + (mregcg002!=null?mregcg002.getIdiecg()+"-"+mregcg002.getCoregc():null) + "]";
	}
	
	public String toStringId()
	{
		return "idirer="+idirer;
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

	public Mregcg00 getMregcg001() {
		return mregcg001;
	}

	public void setMregcg001(Mregcg00 mregcg001) {
		this.mregcg001 = mregcg001;
	}

	public Mregcg00 getMregcg002() {
		return mregcg002;
	}

	public void setMregcg002(Mregcg00 mregcg002) {
		this.mregcg002 = mregcg002;
	}
	

}

	

