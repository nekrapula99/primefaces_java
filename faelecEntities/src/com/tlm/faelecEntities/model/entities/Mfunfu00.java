package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name = "MFUNFU00")
public class Mfunfu00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDCOFU", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcofu;
	
	@Column(name="COCUFU", length=20, nullable=false)
	private String cocufu;
	
	@Column(name="PRNUFU", length=30)
	private String prnufu;
	
	@Column(name="SENUFU", length=30)
	private String senufu;
	
	@Column(name="PRAUFU", length=30)
	private String praufu;
	
	@Column(name="SEAUFU", length=30)
	private String seaufu;
	
	@Column(name="NRNUFU", length=20)
	private String nrnufu;
	
	@Column(name="DIRUFU", length=300)
	private String dirufu;
	
	@Column(name="TELUFU", length=20)
	private String telufu;
	
	@Column(name="EMAUFU", length=20)
	private String emaufu;
	
	@Column(name="REGUFU", nullable=false)
	private boolean regufu;

	@Column(name="USEUFU", length=20, nullable=false)
	private String useufu;

	@Column(name="PRGUFU", length=20, nullable=false)
	private String prgufu;

	@Column(name="FEAUFU", nullable=false)
	private Date feaufu;
	
	@Column(name="MAQUFU",  length=100, nullable=false)
	private String maqufu;
	
	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSUFU", nullable=false)
	private Mgenus00 mgenus001;
	
	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTUFU", nullable=false)
	private Mgenus00 mgenus002;
	
	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPUFU", nullable=false)
	private Mgenus00 mgenus003;
	
	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCUFU", nullable=false)
	private Mgenus00 mgenus004;
	
	//FK Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDIUFU", nullable=false)
	private Mconca00 mconca001;
		
	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDAUFU", nullable=false)
	private Mgenus00 mgenus005;

	//FK Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGUFU", nullable=false)
	private Mgenus00 mgenus006;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mfunfu001")
	private List<Tqigqg00> tqigqg001s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mfunfu002")
	private List<Tqigqg00> tqigqg002s;
	
	

	@Override
	public String toString() {
		return "Mfunfu00 [idcofu=" + idcofu + ", cocufu=" + cocufu
				+ ", prnufu=" + prnufu + ", senufu=" + senufu + ", praufu="
				+ praufu + ", seaufu=" + seaufu + ", nrnufu=" + nrnufu
				+ ", dirufu=" + dirufu + ", telufu=" + telufu + ", emaufu="
				+ emaufu + ", regufu=" + regufu + ", useufu=" + useufu
				+ ", prgufu=" + prgufu + ", feaufu=" + feaufu + ", maqufu="
				+ maqufu 
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)
				+ ", mgenus004=" + (mgenus004!=null?mgenus004.getIdtrus()+"-"+mgenus004.getCodius():null)
				+ ", mconca001=" + (mconca001!=null?mconca001.getIdccia()+"-"+mconca001.getCodcia():null)
				+ ", mgenus005=" + (mgenus005!=null?mgenus005.getIdtrus()+"-"+mgenus005.getCodius():null)
				+ ", mgenus006=" + (mgenus006!=null?mgenus006.getIdtrus()+"-"+mgenus006.getCodius():null)+ "]";
	}
	
	public String toStringId()
	{
		return "idcofu="+idcofu;
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
	
	public int getIdcofu() {
		return idcofu;
	}

	public void setIdcofu(int idcofu) {
		this.idcofu = idcofu;
	}

	public String getCocufu() {
		return cocufu;
	}

	public void setCocufu(String cocufu) {
		this.cocufu = cocufu;
	}

	public String getPrnufu() {
		return prnufu;
	}

	public void setPrnufu(String prnufu) {
		this.prnufu = prnufu;
	}

	public String getSenufu() {
		return senufu;
	}

	public void setSenufu(String senufu) {
		this.senufu = senufu;
	}

	public String getPraufu() {
		return praufu;
	}

	public void setPraufu(String praufu) {
		this.praufu = praufu;
	}

	public String getSeaufu() {
		return seaufu;
	}

	public void setSeaufu(String seaufu) {
		this.seaufu = seaufu;
	}

	public String getNrnufu() {
		return nrnufu;
	}

	public void setNrnufu(String nrnufu) {
		this.nrnufu = nrnufu;
	}

	public String getDirufu() {
		return dirufu;
	}

	public void setDirufu(String dirufu) {
		this.dirufu = dirufu;
	}

	public String getTelufu() {
		return telufu;
	}

	public void setTelufu(String telufu) {
		this.telufu = telufu;
	}

	public String getEmaufu() {
		return emaufu;
	}

	public void setEmaufu(String emaufu) {
		this.emaufu = emaufu;
	}

	public boolean isRegufu() {
		return regufu;
	}

	public void setRegufu(boolean regufu) {
		this.regufu = regufu;
	}

	public String getUseufu() {
		return useufu;
	}

	public void setUseufu(String useufu) {
		this.useufu = useufu;
	}

	public String getPrgufu() {
		return prgufu;
	}

	public void setPrgufu(String prgufu) {
		this.prgufu = prgufu;
	}

	public Date getFeaufu() {
		return feaufu;
	}

	public void setFeaufu(Date feaufu) {
		this.feaufu = feaufu;
	}

	public String getMaqufu() {
		return maqufu;
	}

	public void setMaqufu(String maqufu) {
		this.maqufu = maqufu;
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

	public Mgenus00 getMgenus003() {
		return mgenus003;
	}

	public void setMgenus003(Mgenus00 mgenus003) {
		this.mgenus003 = mgenus003;
	}

	public Mgenus00 getMgenus004() {
		return mgenus004;
	}

	public void setMgenus004(Mgenus00 mgenus004) {
		this.mgenus004 = mgenus004;
	}

	public Mconca00 getMconca001() {
		return mconca001;
	}

	public void setMconca001(Mconca00 mconca001) {
		this.mconca001 = mconca001;
	}

	public Mgenus00 getMgenus005() {
		return mgenus005;
	}

	public void setMgenus005(Mgenus00 mgenus005) {
		this.mgenus005 = mgenus005;
	}

	public Mgenus00 getMgenus006() {
		return mgenus006;
	}

	public void setMgenus006(Mgenus00 mgenus006) {
		this.mgenus006 = mgenus006;
	}

	public List<Tqigqg00> getTqigqg001s() {
		return tqigqg001s;
	}

	public void setTqigqg001s(List<Tqigqg00> tqigqg001s) {
		this.tqigqg001s = tqigqg001s;
	}

	public List<Tqigqg00> getTqigqg002s() {
		return tqigqg002s;
	}

	public void setTqigqg002s(List<Tqigqg00> tqigqg002s) {
		this.tqigqg002s = tqigqg002s;
	}		
	

}
