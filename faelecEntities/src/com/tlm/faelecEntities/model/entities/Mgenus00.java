package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mpropr00;

/**
 * The persistent class for the MGENUS00 database table.
 * 
 */
@Entity
@Table(name="MGENUS00")
public class Mgenus00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTRUS", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idtrus;
	
	@Column(name="COMPUS", length=300 , nullable=false)
	private String compus;

	@Column(name="CM1AUS", length=300)
	private String cm1aus;

	@Column(name="CM1DUS")
	private Date cm1dus;

	@Column(name="CM1NUS", precision=18)
	private BigDecimal cm1nus;

	@Column(name="CMDTUS")
	private Date cmdtus;

	@Column(name="CODIUS", unique=true, nullable=false, length=20)
	private String codius;

	@Column(name="COMAUS", length=60)
	private String comaus;

	@Column(name="COMNUS", precision=12, scale=6)
	private BigDecimal comnus;

	@Column(name="DCTTUS", nullable=false, length=300)
	private String dcttus;

	@Column(name="FEACUS", nullable=false)
	private Date feacus;

	@Column(name="MAQUUS", nullable=false, length=100)
	private String maquus;

	@Column(name="PRGMUS", nullable=false, length=20)
	private String prgmus;

	@Column(name="REGIUS", nullable=false)
	private boolean regius;

	@Column(name="USERUS", nullable=false, length=20)
	private String userus;

	//bi-directional many-to-one association to Mconca00
	@OneToMany(mappedBy="mgenus001")
	private List<Mconca00> mconca00s1;

	//-bi-directional many-to-one association to Mconca00
	@OneToMany(mappedBy="mgenus002")
	private List<Mconca00> mconca00s2;

	//bi-directional many-to-one association to Mconca00
	@OneToMany(mappedBy="mgenus003")
	private List<Mconca00> mconca00s3;
	
	//-bi-directional many-to-one association to Mconca00
	@OneToMany(mappedBy="mgenus002")
	private List<Mconca00> mconca00s4;

	//bi-directional many-to-one association to Mconca00
	@OneToMany(mappedBy="mgenus003")
	private List<Mconca00> mconca00s5;

	//bi-directional many-to-one association to Mdesgr00
	@OneToMany(cascade=CascadeType.ALL,targetEntity = Mdesgr00.class, mappedBy="mgenus00", fetch = FetchType.LAZY,orphanRemoval=true)
	private List<Mdesgr00> mdesgr00s;


	//bi-directional many-to-one association to Mgente00
	@OneToMany(mappedBy="mgenus001")
	private List<Mgente00> mgente00s1;

	//bi-directional many-to-one association to Mgente00
	@OneToMany(mappedBy="mgenus002")
	private List<Mgente00> mgente00s2;

	//bi-directional many-to-one association to Mgente00
	@OneToMany(mappedBy="mgenus003")
	private List<Mgente00> mgente00s3;
	
	//bi-directional many-to-one association to Mgente00
	@OneToMany(mappedBy="mgenus004")
	private List<Mgente00> mgente00s4;

	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMUS", unique=true)
	private Mconca00 mconca00;
	
	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODTUS", referencedColumnName="tipore")
	private Mtipre00 mtipre00;

	//bi-directional many-to-one association to Mparca00
	@OneToMany(mappedBy="mgenus001")
	private List<Mparca00> mparca00s1;

	//bi-directional many-to-one association to Mpropr00
	@OneToMany(mappedBy="mgenus001")
	private List<Mpropr00> mpropr00s1;

	//bi-directional many-to-one association to Mpropr00
	@OneToMany(mappedBy="mgenus002")
	private List<Mpropr00> mpropr00s2;

	//bi-directional many-to-one association to Mpropr00
	@OneToMany(mappedBy="mgenus003")
	private List<Mpropr00> mpropr00s3;

	//bi-directional many-to-one association to Mpropr00
	@OneToMany(mappedBy="mgenus004")
	private List<Mpropr00> mpropr00s4;

	//bi-directional many-to-one association to Mpropr00
	@OneToMany(mappedBy="mgenus005")
	private List<Mpropr00> mpropr00s5;
	
	//bi-directional many-to-one association to Mpropr00
	@OneToMany(mappedBy="mgenus006")
	private List<Mpropr00> mpropr001s;
	
	//bi-directional many-to-one association to Mpropr00
	@OneToMany(mappedBy="mgenus007")
	private List<Mpropr00> propr002s;
	
	//bi-directional many-to-one association to Mpreci01
	@OneToMany(mappedBy="mgenus00")
	private List<Mpreci00> mpreci00s;
	
	//bi-directional one-to-Many association to Mregcg00
	@OneToMany(mappedBy="mgenus001")
	private List<Mregcg00> mregcg001s;
	
	//bi-directional one-to-Many association to Mregcg00
	@OneToMany(mappedBy="mgenus002")
	private List<Mregcg00> mregcg002s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus001")
	private List<Mcotes00> mcotes001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus002")
	private List<Mcotes00> mcotes002s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus003")
	private List<Mcotes00> mcotes003s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus004")
	private List<Mcotes00> mcotes004s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus005")
	private List<Mcotes00> mcotes005s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus006")
	private List<Mcotes00> mcotes006s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus001")
	private List<Mcotos00> mcotos001s;
		
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus002")
	private List<Mcotos00> mcotos002s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus001")
	private List<Mfunfu00> mfunfu001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus002")
	private List<Mfunfu00> mfunfu002s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus003")
	private List<Mfunfu00> mfunfu003s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus004")
	private List<Mfunfu00> mfunfu004s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus005")
	private List<Mfunfu00> mfunfu005s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus006")
	private List<Mfunfu00> mfunfu006s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus001")
	private List<Tqigqg00> tqigqg001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus002")
	private List<Tqigqg00> tqigqg002s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus003")
	private List<Tqigqg00> tqigqg003s;
	
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus001")
	private List<Tqdrqr00> tqdrqr001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus002")
	private List<Tqdrqr00> tqdrqr002s;

	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus003")
	private List<Tqdrqr00> tqdrqr003s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus00")
	private List<Tqdpqp00> tqdpqp00s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus001")
	private List<Tqdfqf00> tqdfqf001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus002")
	private List<Tqdfqf00> tqdfqf002s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus001")
	private List<Mtaric00> mtaric001s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus002")
	private List<Mtaric00> mtaric002s;
	
	//bi-directional one-to-Many association to Mrerer00
	@OneToMany(mappedBy="mgenus003")
	private List<Mtaric00> mtaric003s;

	public Mgenus00() {
    }

	public Integer getIdtrus() {
		return this.idtrus;
	}

	public void setIdtrus(Integer idtrus) {
		this.idtrus = idtrus;
	}

	public String getCm1aus() {
		return this.cm1aus;
	}

	public void setCm1aus(String cm1aus) {
		this.cm1aus = cm1aus;
	}

	public Date getCm1dus() {
		return this.cm1dus;
	}

	public void setCm1dus(Date cm1dus) {
		this.cm1dus = cm1dus;
	}

	public BigDecimal getCm1nus() {
		return this.cm1nus;
	}

	public void setCm1nus(BigDecimal cm1nus) {
		this.cm1nus = cm1nus;
	}

	public Date getCmdtus() {
		return this.cmdtus;
	}

	public void setCmdtus(Date cmdtus) {
		this.cmdtus = cmdtus;
	}

	public String getCodius() {
		return this.codius;
	}

	public void setCodius(String codius) {
		this.codius = codius;
	}
	
	public String getComaus() {
		return this.comaus;
	}

	public void setComaus(String comaus) {
		this.comaus = comaus;
	}

	public BigDecimal getComnus() {
		return this.comnus;
	}

	public void setComnus(BigDecimal comnus) {
		this.comnus = comnus;
	}

	public String getDcttus() {
		return this.dcttus;
	}

	public void setDcttus(String dcttus) {
		this.dcttus = dcttus;
	}

	public Date getFeacus() {
		return this.feacus;
	}

	public void setFeacus(Date feacus) {
		this.feacus = feacus;
	}

	public String getMaquus() {
		return this.maquus;
	}

	public void setMaquus(String maquus) {
		this.maquus = maquus;
	}

	public String getPrgmus() {
		return this.prgmus;
	}

	public void setPrgmus(String prgmus) {
		this.prgmus = prgmus;
	}

	public boolean getRegius() {
		return this.regius;
	}

	public void setRegius(boolean regius) {
		this.regius = regius;
	}

	public String getUserus() {
		return this.userus;
	}

	public void setUserus(String userus) {
		this.userus = userus;
	}
	
	public List<Mconca00> getMconca00s1() {
		return this.mconca00s1;
	}

	public void setMconca00s1(List<Mconca00> mconca00s1) {
		this.mconca00s1 = mconca00s1;
	}
	
	public List<Mconca00> getMconca00s2() {
		return this.mconca00s2;
	}

	public void setMconca00s2(List<Mconca00> mconca00s2) {
		this.mconca00s2 = mconca00s2;
	}
	
	public List<Mconca00> getMconca00s3() {
		return this.mconca00s3;
	}

	public void setMconca00s3(List<Mconca00> mconca00s3) {
		this.mconca00s3 = mconca00s3;
	}
	
	public List<Mdesgr00> getMdesgr00s() {
		return this.mdesgr00s;
	}

	public void setMdesgr00s(List<Mdesgr00> mdesgr00s) {
		this.mdesgr00s = mdesgr00s;
	}
	
	public List<Mgente00> getMgente00s1() {
		return this.mgente00s1;
	}

	public void setMgente00s1(List<Mgente00> mgente00s1) {
		this.mgente00s1 = mgente00s1;
	}
	
	public List<Mgente00> getMgente00s2() {
		return this.mgente00s2;
	}

	public void setMgente00s2(List<Mgente00> mgente00s2) {
		this.mgente00s2 = mgente00s2;
	}
	
	public List<Mgente00> getMgente00s3() {
		return this.mgente00s3;
	}

	public void setMgente00s3(List<Mgente00> mgente00s3) {
		this.mgente00s3 = mgente00s3;
	}
	
	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}
	
	public List<Mparca00> getMparca00s1() {
		return this.mparca00s1;
	}

	public void setMparca00s1(List<Mparca00> mparca00s1) {
		this.mparca00s1 = mparca00s1;
	}

	public List<Mpropr00> getMpropr00s1() {
		return this.mpropr00s1;
	}

	public void setMpropr00s1(List<Mpropr00> mpropr00s1) {
		this.mpropr00s1 = mpropr00s1;
	}
	
	public List<Mpropr00> getMpropr00s2() {
		return this.mpropr00s2;
	}

	public void setMpropr00s2(List<Mpropr00> mpropr00s2) {
		this.mpropr00s2 = mpropr00s2;
	}
	
	public List<Mpropr00> getMpropr00s3() {
		return this.mpropr00s3;
	}

	public void setMpropr00s3(List<Mpropr00> mpropr00s3) {
		this.mpropr00s3 = mpropr00s3;
	}
	
	public List<Mpropr00> getMpropr00s4() {
		return this.mpropr00s4;
	}

	public void setMpropr00s4(List<Mpropr00> mpropr00s4) {
		this.mpropr00s4 = mpropr00s4;
	}
	
	public List<Mpropr00> getMpropr00s5() {
		return this.mpropr00s5;
	}

	public void setMpropr00s5(List<Mpropr00> mpropr00s5) {
		this.mpropr00s5 = mpropr00s5;
	}
	
	public Mtipre00 getMtipre00() {
		return mtipre00;
	}

	public void setMtipre00(Mtipre00 mtipre00) {
		this.mtipre00 = mtipre00;
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
		return "Mgenus00 [idtrus=" + idtrus + ", cm1aus=" + cm1aus
				+ ", cm1dus=" + cm1dus + ", cm1nus=" + cm1nus + ", cmdtus="
				+ cmdtus + ", codius=" + codius + ", comaus=" + comaus
				+ ", comnus=" + comnus + ", dcttus=" + dcttus + ", regius=" + regius + 
				", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null) +
				", mtipre00=" + (mtipre00!=null?mtipre00.getIdtrtr()+"-"+mtipre00.getTipore():null) + "]";
	}
	public String toStringId()
	{
		return "idtrus="+idtrus;
	}

	public String getCompus() {
		return compus;
	}

	public void setCompus(String compus) {
		this.compus = compus;
	}

	public List<Mgente00> getMgente00s4() {
		return mgente00s4;
	}

	public void setMgente00s4(List<Mgente00> mgente00s4) {
		this.mgente00s4 = mgente00s4;
	}

	public List<Mpropr00> getMpropr001s() {
		return mpropr001s;
	}

	public void setMpropr001s(List<Mpropr00> mpropr001s) {
		this.mpropr001s = mpropr001s;
	}

	public List<Mpropr00> getPropr002s() {
		return propr002s;
	}

	public void setPropr002s(List<Mpropr00> propr002s) {
		this.propr002s = propr002s;
	}
	
	 public List<Mpreci00> getMpreci00s() {
		return mpreci00s;
	}

	public void setMpreci00s(List<Mpreci00> mpreci00s) {
		this.mpreci00s = mpreci00s;
	}

	public List<Mregcg00> getMregcg002s() {
		return mregcg002s;
	}

	public void setMregcg002s(List<Mregcg00> mregcg002s) {
		this.mregcg002s = mregcg002s;
	}

	public List<Mcotes00> getMcotes001s() {
		return mcotes001s;
	}

	public void setMcotes001s(List<Mcotes00> mcotes001s) {
		this.mcotes001s = mcotes001s;
	}

	public List<Mcotes00> getMcotes002s() {
		return mcotes002s;
	}

	public void setMcotes002s(List<Mcotes00> mcotes002s) {
		this.mcotes002s = mcotes002s;
	}

	public List<Mcotes00> getMcotes003s() {
		return mcotes003s;
	}

	public void setMcotes003s(List<Mcotes00> mcotes003s) {
		this.mcotes003s = mcotes003s;
	}

	public List<Mcotes00> getMcotes004s() {
		return mcotes004s;
	}

	public void setMcotes004s(List<Mcotes00> mcotes004s) {
		this.mcotes004s = mcotes004s;
	}

	public List<Mcotes00> getMcotes005s() {
		return mcotes005s;
	}

	public void setMcotes005s(List<Mcotes00> mcotes005s) {
		this.mcotes005s = mcotes005s;
	}

	public List<Mcotes00> getMcotes006s() {
		return mcotes006s;
	}

	public void setMcotes006s(List<Mcotes00> mcotes006s) {
		this.mcotes006s = mcotes006s;
	}

	public List<Mcotos00> getMcotos001s() {
		return mcotos001s;
	}

	public void setMcotos001s(List<Mcotos00> mcotos001s) {
		this.mcotos001s = mcotos001s;
	}

	public List<Mcotos00> getMcotos002s() {
		return mcotos002s;
	}

	public void setMcotos002s(List<Mcotos00> mcotos002s) {
		this.mcotos002s = mcotos002s;
	}

	public List<Mfunfu00> getMfunfu001s() {
		return mfunfu001s;
	}

	public void setMfunfu001s(List<Mfunfu00> mfunfu001s) {
		this.mfunfu001s = mfunfu001s;
	}

	public List<Mfunfu00> getMfunfu002s() {
		return mfunfu002s;
	}

	public void setMfunfu002s(List<Mfunfu00> mfunfu002s) {
		this.mfunfu002s = mfunfu002s;
	}

	public List<Mfunfu00> getMfunfu003s() {
		return mfunfu003s;
	}

	public void setMfunfu003s(List<Mfunfu00> mfunfu003s) {
		this.mfunfu003s = mfunfu003s;
	}

	public List<Mfunfu00> getMfunfu004s() {
		return mfunfu004s;
	}

	public void setMfunfu004s(List<Mfunfu00> mfunfu004s) {
		this.mfunfu004s = mfunfu004s;
	}

	public List<Mfunfu00> getMfunfu005s() {
		return mfunfu005s;
	}

	public void setMfunfu005s(List<Mfunfu00> mfunfu005s) {
		this.mfunfu005s = mfunfu005s;
	}

	public List<Mfunfu00> getMfunfu006s() {
		return mfunfu006s;
	}

	public void setMfunfu006s(List<Mfunfu00> mfunfu006s) {
		this.mfunfu006s = mfunfu006s;
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

	public List<Tqigqg00> getTqigqg003s() {
		return tqigqg003s;
	}

	public void setTqigqg003s(List<Tqigqg00> tqigqg003s) {
		this.tqigqg003s = tqigqg003s;
	}

	public List<Tqdrqr00> getTqdrqr001s() {
		return tqdrqr001s;
	}

	public void setTqdrqr001s(List<Tqdrqr00> tqdrqr001s) {
		this.tqdrqr001s = tqdrqr001s;
	}

	public List<Tqdrqr00> getTqdrqr002s() {
		return tqdrqr002s;
	}

	public void setTqdrqr002s(List<Tqdrqr00> tqdrqr002s) {
		this.tqdrqr002s = tqdrqr002s;
	}
	
	public List<Tqdrqr00> getTqdrqr003s() {
		return tqdrqr003s;
	}

	public void setTqdrqr003s(List<Tqdrqr00> tqdrqr003s) {
		this.tqdrqr003s = tqdrqr003s;
	}

	public List<Tqdpqp00> getTqdpqp00s() {
		return tqdpqp00s;
	}

	public void setTqdpqp00s(List<Tqdpqp00> tqdpqp00s) {
		this.tqdpqp00s = tqdpqp00s;
	}

	public List<Tqdfqf00> getTqdfqf001s() {
		return tqdfqf001s;
	}

	public void setTqdfqf001s(List<Tqdfqf00> tqdfqf001s) {
		this.tqdfqf001s = tqdfqf001s;
	}

	public List<Tqdfqf00> getTqdfqf002s() {
		return tqdfqf002s;
	}

	public void setTqdfqf002s(List<Tqdfqf00> tqdfqf002s) {
		this.tqdfqf002s = tqdfqf002s;
	}

	public List<Mtaric00> getMtaric001s() {
		return mtaric001s;
	}

	public void setMtaric001s(List<Mtaric00> mtaric001s) {
		this.mtaric001s = mtaric001s;
	}

	public List<Mtaric00> getMtaric002s() {
		return mtaric002s;
	}

	public void setMtaric002s(List<Mtaric00> mtaric002s) {
		this.mtaric002s = mtaric002s;
	}

	public List<Mtaric00> getMtaric003s() {
		return mtaric003s;
	}

	public void setMtaric003s(List<Mtaric00> mtaric003s) {
		this.mtaric003s = mtaric003s;
	}

	public List<Mregcg00> getMregcg001s() {
		return mregcg001s;
	}

	public void setMregcg001s(List<Mregcg00> mregcg001s) {
		this.mregcg001s = mregcg001s;
	}

	public List<Mconca00> getMconca00s4() {
		return mconca00s4;
	}

	public void setMconca00s4(List<Mconca00> mconca00s4) {
		this.mconca00s4 = mconca00s4;
	}

	public List<Mconca00> getMconca00s5() {
		return mconca00s5;
	}

	public void setMconca00s5(List<Mconca00> mconca00s5) {
		this.mconca00s5 = mconca00s5;
	}


}