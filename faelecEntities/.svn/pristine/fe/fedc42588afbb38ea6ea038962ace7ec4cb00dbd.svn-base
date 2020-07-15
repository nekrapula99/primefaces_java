package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Mparpd00;

/**
 * The persistent class for the MPARCA00 database table.
 * 
 */
@Entity
@Table(name="MPARCA00")
public class Mparca00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTRCA", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrca;

	@Column(name="ASUNCA", length=100)
	private String asunca;

	@Column(name="COMICA", length=1000)
	private String comica;

	@Column(name="DESPCA", length=100)
	private String despca;

	@Column(name="DIRICA", length=300)
	private String dirica;

	@Column(name="FEACCA", nullable=false)
	private Date feacca;

	@Column(name="INFRCA", nullable=false, length=1)
	private String infrca;

	@Column(name="INSTCA", length=1000)
	private String instca;

	@Column(name="MAQUCA", nullable=false, length=100)
	private String maquca;

	@Column(name="OBSEC1", length=2000)
	private String obsec1;

	@Column(name="OBSEC2", length=2000)
	private String obsec2;

	@Column(name="OBSEC3", length=2000)
	private String obsec3;

	@Column(name="OBSEC4", length=2000)
	private String obsec4;

	@Column(name="OBSEC5", length=2000)
	private String obsec5;

	@Column(name="PRGMCA", nullable=false, length=20)
	private String prgmca;

	@Column(name="REGICA", nullable=false)
	private boolean regica;

	@Column(name="TINTCA", length=1000)
	private String tintca;

	@Column(name="USERCA", nullable=false, length=20)
	private String userca;

	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMCA", unique=true)
	private Mconca00 mconca00;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDDCCA", unique=true, nullable=false)
	private Mgenus00 mgenus001;

	//bi-directional many-to-one association to Midiom00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDIOCA", unique=true, nullable=false)
	private Midiom00 midiom00;

	//bi-directional many-to-one association to Mgente00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDF1CA")
	private Mgente00 mgente001;

	//bi-directional many-to-one association to Mgente00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDF2CA")
	private Mgente00 mgente002;

	//bi-directional many-to-one association to Mgente00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDF3CA")
	private Mgente00 mgente003;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCDCA", nullable=false)
	private Mtiptx00 mtiptx00;

	//bi-directional many-to-one association to Mparpd00
	@OneToMany(cascade = CascadeType.ALL, mappedBy="mparca00", fetch = FetchType.EAGER,orphanRemoval=true)
	private List<Mparpd00> mparpd00s;

    public Mparca00() {
    }

	public int getIdtrca() {
		return this.idtrca;
	}

	public void setIdtrca(int idtrca) {
		this.idtrca = idtrca;
	}

	public String getAsunca() {
		return this.asunca;
	}

	public void setAsunca(String asunca) {
		this.asunca = asunca;
	}

	public String getComica() {
		return this.comica;
	}

	public void setComica(String comica) {
		this.comica = comica;
	}

	public String getDespca() {
		return this.despca;
	}

	public void setDespca(String despca) {
		this.despca = despca;
	}

	public String getDirica() {
		return this.dirica;
	}

	public void setDirica(String dirica) {
		this.dirica = dirica;
	}

	public Date getFeacca() {
		return this.feacca;
	}

	public void setFeacca(Date feacca) {
		this.feacca = feacca;
	}

	public String getInfrca() {
		return this.infrca;
	}

	public void setInfrca(String infrca) {
		this.infrca = infrca;
	}

	public String getInstca() {
		return this.instca;
	}

	public void setInstca(String instca) {
		this.instca = instca;
	}

	public String getMaquca() {
		return this.maquca;
	}

	public void setMaquca(String maquca) {
		this.maquca = maquca;
	}

	public String getObsec1() {
		return this.obsec1;
	}

	public void setObsec1(String obsec1) {
		this.obsec1 = obsec1;
	}

	public String getObsec2() {
		return this.obsec2;
	}

	public void setObsec2(String obsec2) {
		this.obsec2 = obsec2;
	}

	public String getObsec3() {
		return this.obsec3;
	}

	public void setObsec3(String obsec3) {
		this.obsec3 = obsec3;
	}

	public String getObsec4() {
		return this.obsec4;
	}

	public void setObsec4(String obsec4) {
		this.obsec4 = obsec4;
	}

	public String getObsec5() {
		return this.obsec5;
	}

	public void setObsec5(String obsec5) {
		this.obsec5 = obsec5;
	}

	public String getPrgmca() {
		return this.prgmca;
	}

	public void setPrgmca(String prgmca) {
		this.prgmca = prgmca;
	}

	public boolean getRegica() {
		return this.regica;
	}

	public void setRegica(boolean regica) {
		this.regica = regica;
	}

	public String getTintca() {
		return this.tintca;
	}

	public void setTintca(String tintca) {
		this.tintca = tintca;
	}

	public String getUserca() {
		return this.userca;
	}

	public void setUserca(String userca) {
		this.userca = userca;
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
	
	public Midiom00 getMidiom00() {
		return this.midiom00;
	}

	public void setMidiom00(Midiom00 midiom00) {
		this.midiom00 = midiom00;
	}
	
	public Mgente00 getMgente001() {
		return this.mgente001;
	}

	public void setMgente001(Mgente00 mgente001) {
		this.mgente001 = mgente001;
	}
	
	public Mgente00 getMgente002() {
		return this.mgente002;
	}

	public void setMgente002(Mgente00 mgente002) {
		this.mgente002 = mgente002;
	}
	
	public Mgente00 getMgente003() {
		return this.mgente003;
	}

	public void setMgente003(Mgente00 mgente003) {
		this.mgente003 = mgente003;
	}
	
	public List<Mparpd00> getMparpd00s() {
		return this.mparpd00s;
	}

	public void setMparpd00s(List<Mparpd00> mparpd00s) {
		this.mparpd00s = mparpd00s;
	}
	
	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
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
		return "Mparca00 [idtrca=" + idtrca + ", asunca=" + asunca
				+ ", comica=" + comica + ", despca=" + despca + ", dirica="
				+ dirica + ", infrca=" + infrca + ", instca=" + instca  
				+ ", obsec1=" + obsec1 + ", obsec2=" + obsec2 + ", obsec3=" + obsec3 
				+ ", obsec4=" + obsec4+ ", obsec5=" + obsec5 +", regica=" + regica
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null) 
				+ ", mtiptx00=" + (mtiptx00!=null?mtiptx00.getIdtptx()+"-"+mtiptx00.getCotrtx():null)
				+ ", midiom00=" + (midiom00!=null?midiom00.getIdiddi()+"-"+midiom00.getCodidi():null) 
				+ ", midiom00=" + (midiom00!=null?midiom00.getIdiddi()+"-"+midiom00.getCodidi():null)
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", mgente001=" + (mgente001!=null?mgente001.getIdtrte()+"-"+mgente001.getCodite():null)
				+ ", mgente002=" + (mgente002!=null?mgente002.getIdtrte()+"-"+mgente002.getCodite():null)
				+ ", mgente003=" + (mgente003!=null?mgente003.getIdtrte()+"-"+mgente003.getCodite():null)+"]";
	}
	public String toStringId()
	{
		return "idtrca="+idtrca;
	}
	
}