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

import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mconca00;

/**
 * The persistent class for the MPARME00 database table.
 * 
 */
@Entity
@Table(name = "MPARME00")
public class Mparme00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDMPME", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idmpme;

	@Column(name = "DCPAME", nullable = false, length = 200)
	private String dcpame;

	@Column(name = "FEACME", nullable = false)
	private Date feacme;

	@Column(name = "FEPAME")
	private Date fepame;

	// bi-directional many-to-one association to Mconca00  //idccme
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCCME", unique = true)
	private Mconca00 mconca00;

	@Column(name = "MAQUME", nullable = false, length = 100)
	private String maqume;

	@Column(name = "NOMAME", length = 20)
	private String nomame;

	@Column(name = "NOPAME", unique = true, nullable = false, length = 50)
	private String nopame;

	@Column(name = "NUPAME")
	private Double nupame;

	@Column(name = "PRGMME", nullable = false, length = 20)
	private String prgmme;

	@Column(name = "TIPAME", nullable = false, length = 1)
	private String tipame;

	@Column(name = "TRMAME", length = 4)
	private String trmame;

	@Column(name = "USUAME", nullable = false, length = 20)
	private String usuame;

	@Column(name = "VAPAME", length = 100)
	private String vapame;
	
	@Column(name="REGIME", nullable=false)
	private boolean regime;
	
	@Column(name = "VAPAMH", length = 100)
	private String vapamh;
	
	@Column(name = "TRMAMH", length = 100)
	private String trmamh;
	
//	@Column(name = "APLIME", length = 20, nullable=false)
//	private String aplime;

//	 bi-directional many-to-one association to Maplic00
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APLIME", unique = true, nullable = true)//modificar a nulable false luego de probar
	private Maplic00 maplic00;

	public Mparme00() {
	}

	public int getIdmpme() {
		return this.idmpme;
	}

	public void setIdmpme(int idmpme) {
		this.idmpme = idmpme;
	}

	public String getDcpame() {
		return this.dcpame;
	}

	public void setDcpame(String dcpame) {
		this.dcpame = dcpame;
	}

	public Date getFeacme() {
		return this.feacme;
	}

	public void setFeacme(Date feacme) {
		this.feacme = feacme;
	}

	public Date getFepame() {
		return this.fepame;
	}

	public void setFepame(Date fepame) {
		this.fepame = fepame;
	}

	public String getMaqume() {
		return this.maqume;
	}

	public void setMaqume(String maqume) {
		this.maqume = maqume;
	}

	public String getNomame() {
		return this.nomame;
	}

	public void setNomame(String nomame) {
		this.nomame = nomame;
	}

	public String getNopame() {
		return this.nopame;
	}

	public void setNopame(String nopame) {
		this.nopame = nopame;
	}

	public Double getNupame() {
		return this.nupame;
	}

	public void setNupame(Double nupame) {
		this.nupame = nupame;
	}

	public String getPrgmme() {
		return this.prgmme;
	}

	public void setPrgmme(String prgmme) {
		this.prgmme = prgmme;
	}

	public String getTipame() {
		return this.tipame;
	}

	public void setTipame(String tipame) {
		this.tipame = tipame;
	}

	public String getTrmame() {
		return this.trmame;
	}

	public void setTrmame(String trmame) {
		this.trmame = trmame;
	}

	public String getUsuame() {
		return this.usuame;
	}

	public void setUsuame(String usuame) {
		this.usuame = usuame;
	}

	public String getVapame() {
		return this.vapame;
	}

	public void setVapame(String vapame) {
		this.vapame = vapame;
	}

	public Maplic00 getMaplic00() {
		return this.maplic00;
	}

	public boolean isRegime() {
		return regime;
	}

	public void setRegime(boolean regime) {
		this.regime = regime;
	}

	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}

	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public String getVapamh() {
		return vapamh;
	}

	public void setVapamh(String vapamh) {
		this.vapamh = vapamh;
	}

	public String getTrmamh() {
		return trmamh;
	}

	public void setTrmamh(String trmamh) {
		this.trmamh = trmamh;
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
		return "Mparme00 [idmpme=" + idmpme
				+ ", nomame=" + nomame + ", nopame=" + nopame + ", nupame="
				+ nupame + ", tipame=" + tipame + ", trmame=" + trmame  
				+ ", vapame=" + vapame + ", vapamh=" + vapamh + ", trmamh=" + trmamh +", regime=" + regime
				+ ", mconca00=" + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null) + "]";
	}
	public String toStringId()
	{
		return "idmpme="+idmpme;
	}

//	public String getAplime() {
//		return aplime;
//	}
//
//	public void setAplime(String aplime) {
//		this.aplime = aplime;
//	}

}