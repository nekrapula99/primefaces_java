package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.hibernate.annotations.CascadeType;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

import com.tlm.faelecEntities.model.entities.Mvarpr00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Mgenus00;

/**
 * The persistent class for the MPROPR00 database table.
 * 
 */
@Entity
@Table(name="MPROPR00")
public class Mpropr00 implements Serializable,Cloneable  {
	private static final long serialVersionUID = 2820030322270803878L;

	@Id
	@Column(name="IDCAPR", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcapr;

	@Column(name="CODCPR", unique=true, nullable=false, length=30)
	private String codcpr;

	@Column(name="CODIPR", length=30)
	private String codipr;

	@Column(name="COSTPR", nullable=false, precision=53)
	private Double costpr;

	@Column(name="CTDIPR", precision=53)
	private Double ctdipr;

	@Column(name="CTEXPR", precision=53)
	private Double ctexpr;

	@Column(name="DSCPPR", precision=53)
	private Double dscppr;

	@Column(name="DSCRPR", nullable=false, length=1000)
	private String dscrpr;
	
	@Column(name="CAUIPR", nullable=false, length=1000)
	private String cauipr;

	@Column(name="FEACPR", nullable=false)
	private Date feacpr;

	@Column(name="IMPCPR", precision=53)
	private Double impcpr;

	@Column(name="INPTPR", nullable=false, length=1)
	private String inptpr;

	@Column(name="IVAPPR", precision=53)
	private Double ivappr;

	@Column(name="MAQUPR", nullable=false, length=100)
	private String maqupr;

	@Column(name="NOMFPR", length=100)
	private String nomfpr;

	@Column(name="PRGMPR", nullable=false, length=20)
	private String prgmpr;

	@Column(name="REGIPR", nullable=false)
	private boolean regipr;

	@Column(name="REGSPR", length=50)
	private String regspr;

	@Column(name="USERPR", nullable=false, length=20)
	private String userpr;

	@Column(name="VLRUPR", nullable=false, precision=53)
	private Double vlrupr;
		
	@Column(name="TEXBPR", length=200)
	private String texbpr;

	
	//bi-directional many-to-one association to Mdespr00
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "tcimpb00", fetch = FetchType.EAGER, orphanRemoval=true)
	//@OneToMany(cascade = CascadeType.ALL, mappedBy="mpropr00", fetch = FetchType.EAGER, orphanRemoval=true)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mpropr00", cascade = CascadeType.ALL)
	private List<Mdespr00> mdespr00s;
	
	//bi-directional many-to-one association to Mdespr00
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "tcimpb00", fetch = FetchType.EAGER, orphanRemoval=true)
	//@OneToMany(cascade = CascadeType.ALL, mappedBy="mpropr00", fetch = FetchType.LAZY, orphanRemoval=true)
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "mpropr00", cascade = CascadeType.ALL)
	//private List<Mvarpr00> mvarpr00s;
		
	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMPR", unique=true)
	private Mconca00 mconca00;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMRPR")
	private Mgenus00 mgenus001;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDLIPR", nullable=false)
	private Mgenus00 mgenus002;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSLPR", nullable=false)
	private Mgenus00 mgenus003;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUMPR", nullable=false)
	private Mgenus00 mgenus004;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCCPR", nullable=false)
	private Mgenus00 mgenus005;
	
	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDVPR1", nullable=false)
	private Mgenus00 mgenus006;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDVPR2", nullable=false)
	private Mgenus00 mgenus007;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUIPR", nullable=false)
	private Mgenus00 mgenus008;
	
	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTBPR", nullable=false)
	private Mgenus00 mgenus009;
	
	//bi-directional many-to-one association to Mpreci01
	@OneToMany(mappedBy="mpropr00")
	private List<Mpreci00> mpreci00s;
	

	//bi-directional many-to-one association to Tqigqg00
	@OneToMany(mappedBy="mpropr00")
	private List<Tqigqg00> tqigqg00s;
	
	//bi-directional many-to-one association to Tqigqg00
	@OneToMany(mappedBy="mpropr00")
	private List<Tqdpqp00> tqdpqp00s;
	
	@Transient
	private List<Object> imagenes;
	
	@Transient
	private Mvarpr00 variante;
	
	@Transient
	private Mdevpr00 subVariante;
	
	
    public Mpropr00() {
    	inptpr="N";
    }

	public int getIdcapr() {
		return this.idcapr;
	}

	public void setIdcapr(int idcapr) {
		this.idcapr = idcapr;
	}

	public String getCodcpr() {
		return this.codcpr;
	}

	public void setCodcpr(String codcpr) {
		this.codcpr = codcpr;
	}

	public String getCodipr() {
		return this.codipr;
	}

	public void setCodipr(String codipr) {
		this.codipr = codipr;
	}

	public Double getCostpr() {
		return this.costpr;
	}

	public void setCostpr(Double costpr) {
		this.costpr = costpr;
	}

	public Double getCtdipr() {
		return this.ctdipr;
	}

	public void setCtdipr(Double ctdipr) {
		this.ctdipr = ctdipr;
	}

	public Double getCtexpr() {
		return this.ctexpr;
	}

	public void setCtexpr(Double ctexpr) {
		this.ctexpr = ctexpr;
	}

	public Double getDscppr() {
		return this.dscppr;
	}

	public void setDscppr(Double dscppr) {
		this.dscppr = dscppr;
	}

	public String getDscrpr() {
		return this.dscrpr;
	}

	public void setDscrpr(String dscrpr) {
		this.dscrpr = dscrpr;
	}

	public Date getFeacpr() {
		return this.feacpr;
	}

	public void setFeacpr(Date feacpr) {
		this.feacpr = feacpr;
	}

	public Double getImpcpr() {
		return this.impcpr;
	}

	public void setImpcpr(Double impcpr) {
		this.impcpr = impcpr;
	}

	public boolean getInptpr() {
		return inptpr.equalsIgnoreCase("S");
	}

	public void setInptpr(boolean inptpr) {
		if(inptpr){
			this.inptpr="S";
		}else{
			this.inptpr="N";
		}
	}

	public Double getIvappr() {
		return this.ivappr;
	}

	public void setIvappr(Double ivappr) {
		this.ivappr = ivappr;
	}

	public String getMaqupr() {
		return this.maqupr;
	}

	public void setMaqupr(String maqupr) {
		this.maqupr = maqupr;
	}

	public String getNomfpr() {
		return this.nomfpr;
	}

	public void setNomfpr(String nomfpr) {
		this.nomfpr = nomfpr;
	}

	public String getPrgmpr() {
		return this.prgmpr;
	}

	public void setPrgmpr(String prgmpr) {
		this.prgmpr = prgmpr;
	}

	public boolean getRegipr() {
		return this.regipr;
	}

	public void setRegipr(boolean regipr) {
		this.regipr = regipr;
	}

	public String getRegspr() {
		return this.regspr;
	}

	public void setRegspr(String regspr) {
		this.regspr = regspr;
	}

	public String getUserpr() {
		return this.userpr;
	}

	public void setUserpr(String userpr) {
		this.userpr = userpr;
	}

	public Double getVlrupr() {
		return this.vlrupr;
	}

	public void setVlrupr(Double vlrupr) {
		this.vlrupr = vlrupr;
	}

	public List<Mdespr00> getMdespr00s() {
		return this.mdespr00s;
	}

	public void setMdespr00s(List<Mdespr00> mdespr00s) {
		this.mdespr00s = mdespr00s;
	}
	
	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}
	
	public Mgenus00 getMgenus001() {
		return this.mgenus001;
	}

	public void setMgenus001(Mgenus00 mgenus001) {
		this.mgenus001 = mgenus001;
	}
	
	public Mgenus00 getMgenus002() {
		return this.mgenus002;
	}

	public void setMgenus002(Mgenus00 mgenus002) {
		this.mgenus002 = mgenus002;
	}
	
	public Mgenus00 getMgenus003() {
		return this.mgenus003;
	}

	public void setMgenus003(Mgenus00 mgenus003) {
		this.mgenus003 = mgenus003;
	}
	
	public Mgenus00 getMgenus004() {
		return this.mgenus004;
	}

	public void setMgenus004(Mgenus00 mgenus004) {
		this.mgenus004 = mgenus004;
	}
	
	public Mgenus00 getMgenus005() {
		return this.mgenus005;
	}

	public void setMgenus005(Mgenus00 mgenus005) {
		this.mgenus005 = mgenus005;
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
		return "Mpropr00 [idcapr=" + idcapr + ", codcpr=" + codcpr
				+ ", codipr=" + codipr + ", costpr=" + costpr + ", ctdipr="
				+ ctdipr + ", ctexpr=" + ctexpr + ", dscppr=" + dscppr  
				+ ", dscrpr=" + dscrpr + ", impcpr=" + impcpr + ", vlrupr=" + vlrupr 
				+ ", inptpr=" + inptpr+ ", nomfpr=" + nomfpr +", regspr=" + regspr + ", texbpr=" + texbpr 
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)
				+ ", mgenus004=" + (mgenus004!=null?mgenus004.getIdtrus()+"-"+mgenus004.getCodius():null)
				+ ", mgenus005=" + (mgenus005!=null?mgenus005.getIdtrus()+"-"+mgenus005.getCodius():null)
				+ ", mgenus009=" + (mgenus009!=null?mgenus009.getIdtrus()+"-"+mgenus009.getCodius():null)

				+"]";
	}
	public String toStringId()
	{
		return "idcapr="+idcapr;
	}

	public String getTexbpr() {
		return texbpr;
	}

	public void setTexbpr(String texbpr) {
		this.texbpr = texbpr;
	}

	public Mgenus00 getMgenus006() {
		return mgenus006;
	}

	public void setMgenus006(Mgenus00 mgenus006) {
		this.mgenus006 = mgenus006;
	}

	public Mgenus00 getMgenus007() {
		return mgenus007;
	}

	public void setMgenus007(Mgenus00 mgenus007) {
		this.mgenus007 = mgenus007;
	}

	public List<Object> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Object> imagenes) {
		this.imagenes = imagenes;
	}

	public Mvarpr00 getVariante() {
		return variante;
	}

	public void setVariante(Mvarpr00 variante) {
		this.variante = variante;
	}

	public Mdevpr00 getSubVariante() {
		return subVariante;
	}

	public void setSubVariante(Mdevpr00 subVariante) {
		this.subVariante = subVariante;
	}

	public List<Mpreci00> getMpreci00s() {
		return mpreci00s;
	}

	public void setMpreci00s(List<Mpreci00> mpreci00s) {
		this.mpreci00s = mpreci00s;
	}

	public Mgenus00 getMgenus008() {
		return mgenus008;
	}

	public void setMgenus008(Mgenus00 mgenus008) {
		this.mgenus008 = mgenus008;
	}

	public String getCauipr() {
		return cauipr;
	}

	public void setCauipr(String cauipr) {
		this.cauipr = cauipr;
	}


	public List<Tqdpqp00> getTqdpqp00s() {
		return tqdpqp00s;
	}

	public void setTqdpqp00s(List<Tqdpqp00> tqdpqp00s) {
		this.tqdpqp00s = tqdpqp00s;
	}

	public List<Tqigqg00> getTqigqg00s() {
		return tqigqg00s;
	}

	public void setTqigqg00s(List<Tqigqg00> tqigqg00s) {
		this.tqigqg00s = tqigqg00s;
	}

	public Mgenus00 getMgenus009() {
		return mgenus009;
	}

	public void setMgenus009(Mgenus00 mgenus009) {
		this.mgenus009 = mgenus009;
	}

	
}