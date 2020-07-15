package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
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



@Entity
@Table(name = "MRAFRA00")
public class Mrafra00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDRARA",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrara;
	
	//codigo rango
	@Column(name = "CORARA", length=50, nullable=false)
	private String corara;
	
	//Descripcion
	@Column(name = "DESCRA", length=100, nullable=false)
	private String descra;
	
	//Prefijo Rango
	@Column(name = "PREFRA", length=50, nullable=false)
	private String prefra;
	
	//Rango desde
	@Column(name = "RADERA", nullable=false)
	private Integer radera;
	
	//Rango hasta
	@Column(name = "RAHARA", nullable=false)
	private Integer rahara;
	
	//vigente desde
	@Column(name="RAVDRA", nullable=false)
	private Date ravdra;
	
	//vigente hasta
	@Column(name="RAVHRA", nullable=false)
	private Date ravhra;
	
	//Autorización DIAN Nro
	@Column(name = "RAAURA", nullable=false)
	private Integer raaura;
	
	//CKTURA CLAVE TECNICA DIAN
	@Column(name = "CKTURA", length=100, nullable=false)
	private String cktura;
	
	//UBL VersionID
	@Column(name = "UBVERA", length=30, nullable=false)
	private String ubvera;
	
	//vigente hasta
	@Column(name="RAFERA", nullable=false)
	private Date rafera;
	
	@Column(name="REGRRA", nullable=false)
	private boolean regrra;

	@Column(name="USERRA", length=20, nullable=false)
	private String userra;

	@Column(name="PRGRRA", length=500, nullable=false)
	private String prgrra;

	@Column(name="FEARRA", nullable=false)
	private Date fearra;
	
	@Column(name="MAQRRA",  length=100, nullable=false)
	private String maqrra;
	
	//FK MCONCA ID COMPAÑIA
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPGRA", nullable=false)
	private Mconca00 mconca00;
	
	//FK mtiptx00 -- Tipo Transacciòn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTXRA", nullable=false)
	private Mtiptx00 mtiptx00;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="mrafra00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tucauc00> tucauc00s;
	
	public int getIdrara() {
		return idrara;
	}

	public void setIdrara(int idrara) {
		this.idrara = idrara;
	}

	public String getCorara() {
		return corara;
	}

	public void setCorara(String corara) {
		this.corara = corara;
	}

	public String getDescra() {
		return descra;
	}

	public void setDescra(String descra) {
		this.descra = descra;
	}

	public String getPrefra() {
		return prefra;
	}

	public void setPrefra(String prefra) {
		this.prefra = prefra;
	}

	public Integer getRadera() {
		return radera;
	}

	public void setRadera(Integer radera) {
		this.radera = radera;
	}

	public Integer getRahara() {
		return rahara;
	}

	public void setRahara(Integer rahara) {
		this.rahara = rahara;
	}

	public Date getRavdra() {
		return ravdra;
	}

	public void setRavdra(Date ravdra) {
		this.ravdra = ravdra;
	}

	public Date getRavhra() {
		return ravhra;
	}

	public void setRavhra(Date ravhra) {
		this.ravhra = ravhra;
	}

	public Integer getRaaura() {
		return raaura;
	}

	public void setRaaura(Integer raaura) {
		this.raaura = raaura;
	}

	public String getUbvera() {
		return ubvera;
	}

	public void setUbvera(String ubvera) {
		this.ubvera = ubvera;
	}

	public Date getRafera() {
		return rafera;
	}

	public void setRafera(Date rafera) {
		this.rafera = rafera;
	}

	public boolean isRegrra() {
		return regrra;
	}

	public void setRegrra(boolean regrra) {
		this.regrra = regrra;
	}

	public String getUserra() {
		return userra;
	}

	public void setUserra(String userra) {
		this.userra = userra;
	}

	public String getPrgrra() {
		return prgrra;
	}

	public void setPrgrra(String prgrra) {
		this.prgrra = prgrra;
	}

	public Date getFearra() {
		return fearra;
	}

	public void setFearra(Date fearra) {
		this.fearra = fearra;
	}

	public String getMaqrra() {
		return maqrra;
	}

	public void setMaqrra(String maqrra) {
		this.maqrra = maqrra;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	@Override
	public String toString() {
		return "Mrafra00 [idrara=" + idrara + ", corara=" + corara
				+ ", descra=" + descra + ", prefra=" + prefra + ", radera="
				+ radera + ", rahara=" + rahara + ", ravdra=" + ravdra
				+ ", cktura=" + cktura
				+ ", ravhra=" + ravhra + ", raaura=" + raaura + ", ubvera="
				+ ubvera + ", rafera=" + rafera + ", regrra=" + regrra
				+ ", userra=" + userra + ", prgrra=" + prgrra + ", fearra="
				+ fearra + ", maqrra=" + maqrra
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", mtiptx00=" + (mtiptx00!=null?mtiptx00.getIdtptx()+"-"+mtiptx00.getCotrtx():null)
				+ "]";
	}
	
	public String toStringId()
	{
		return "idrara="+idrara;
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

	public List<Tucauc00> getTucauc00s() {
		return tucauc00s;
	}

	public void setTucauc00s(List<Tucauc00> tucauc00s) {
		this.tucauc00s = tucauc00s;
	}

	public String getCktura() {
		return cktura;
	}

	public void setCktura(String cktura) {
		this.cktura = cktura;
	}
	
	
	

}
	