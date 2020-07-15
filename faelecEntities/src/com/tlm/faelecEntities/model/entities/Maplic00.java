package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mdecai00;
import com.tlm.faelecEntities.model.entities.Mparme00;
import com.tlm.faelecEntities.model.entities.Mtabla00;

/**
 * The persistent class for the MAPLIC00 database table.
 * 
 */
@Entity
@Table(name="MAPLIC00")
public class Maplic00 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODAPL", unique=true, nullable=false, length=20)
	private String codapl;

	@Column(name="DINAPL")
	private Integer dinapl;

	@Column(name="LOGAPL")
	private byte[] logapl;

	@Column(name="NEMAPL", length=50)
	private String nemapl;

	@Column(name="NOMAPL", nullable=false, length=2000)
	private String nomapl;

	//bi-directional many-to-one association to Mcampo00
	@OneToMany(mappedBy="maplic00")
	private List<Mcampo00> mcampo00s;

	//bi-directional many-to-one association to Mdecai00
	@OneToMany(mappedBy="maplic00")
	private List<Mdecai00> mdecai00s;

	//bi-directional many-to-one association to Mparme00
	@OneToMany(mappedBy="maplic00")
	private List<Mparme00> mparme00s;

	//bi-directional many-to-one association to Mtabla00
	@OneToMany(mappedBy="maplic00")
	private List<Mtabla00> mtabla00s;	
	
	//bi-directional many-to-one association to Maccgr00
	@OneToMany(mappedBy="maplic00")
	private List<Maccgr00> maccgr00s;	
	
	//bi-directional many-to-one association to Mgrupo00
	@OneToMany(mappedBy="maplic00")
	private List<Mgrupo00> mgrupo00s;
	

	
    public Maplic00() {
    }

	public String getCodapl() {
		return this.codapl;
	}

	public void setCodapl(String codapl) {
		this.codapl = codapl;
	}

	public Integer getDinapl() {
		return this.dinapl;
	}

	public void setDinapl(Integer dinapl) {
		this.dinapl = dinapl;
	}

	public byte[] getLogapl() {
		return this.logapl;
	}

	public void setLogapl(byte[] logapl) {
		this.logapl = logapl;
	}

	public String getNemapl() {
		return this.nemapl;
	}

	public void setNemapl(String nemapl) {
		this.nemapl = nemapl;
	}

	public String getNomapl() {
		return this.nomapl;
	}

	public void setNomapl(String nomapl) {
		this.nomapl = nomapl;
	}

	public List<Mcampo00> getMcampo00s() {
		return this.mcampo00s;
	}

	public void setMcampo00s(List<Mcampo00> mcampo00s) {
		this.mcampo00s = mcampo00s;
	}
	
	public List<Mdecai00> getMdecai00s() {
		return this.mdecai00s;
	}

	public void setMdecai00s(List<Mdecai00> mdecai00s) {
		this.mdecai00s = mdecai00s;
	}
	
	public List<Mparme00> getMparme00s() {
		return this.mparme00s;
	}

	public void setMparme00s(List<Mparme00> mparme00s) {
		this.mparme00s = mparme00s;
	}
	
	public List<Mtabla00> getMtabla00s() {
		return this.mtabla00s;
	}

	public void setMtabla00s(List<Mtabla00> mtabla00s) {
		this.mtabla00s = mtabla00s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codapl == null) ? 0 : codapl.hashCode());
		result = prime * result + ((dinapl == null) ? 0 : dinapl.hashCode());
		result = prime * result + Arrays.hashCode(logapl);
		result = prime * result
				+ ((mcampo00s == null) ? 0 : mcampo00s.hashCode());
		result = prime * result
				+ ((mdecai00s == null) ? 0 : mdecai00s.hashCode());
		result = prime * result
				+ ((mparme00s == null) ? 0 : mparme00s.hashCode());
		result = prime * result
				+ ((mtabla00s == null) ? 0 : mtabla00s.hashCode());
		result = prime * result + ((nemapl == null) ? 0 : nemapl.hashCode());
		result = prime * result + ((nomapl == null) ? 0 : nomapl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maplic00 other = (Maplic00) obj;
		if (codapl == null) {
			if (other.codapl != null)
				return false;
		} else if (!codapl.equals(other.codapl))
			return false;
		if (dinapl == null) {
			if (other.dinapl != null)
				return false;
		} else if (!dinapl.equals(other.dinapl))
			return false;
		if (!Arrays.equals(logapl, other.logapl))
			return false;
		if (mcampo00s == null) {
			if (other.mcampo00s != null)
				return false;
		} else if (!mcampo00s.equals(other.mcampo00s))
			return false;
		if (mdecai00s == null) {
			if (other.mdecai00s != null)
				return false;
		} else if (!mdecai00s.equals(other.mdecai00s))
			return false;
		if (mparme00s == null) {
			if (other.mparme00s != null)
				return false;
		} else if (!mparme00s.equals(other.mparme00s))
			return false;
		if (mtabla00s == null) {
			if (other.mtabla00s != null)
				return false;
		} else if (!mtabla00s.equals(other.mtabla00s))
			return false;
		if (nemapl == null) {
			if (other.nemapl != null)
				return false;
		} else if (!nemapl.equals(other.nemapl))
			return false;
		if (nomapl == null) {
			if (other.nomapl != null)
				return false;
		} else if (!nomapl.equals(other.nomapl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Maplic00 [codapl=" + codapl + ", dinapl=" + dinapl
				+ ", logapl=" + Arrays.toString(logapl) + ", nemapl=" + nemapl
				+ ", nomapl=" + nomapl + ", mcampo00s=" + mcampo00s
				+ ", mdecai00s=" + mdecai00s + ", mparme00s=" + mparme00s
				+ ", mtabla00s=" + mtabla00s + "]";
	}

	public List<Maccgr00> getMaccgr00s() {
		return maccgr00s;
	}

	public void setMaccgr00s(List<Maccgr00> maccgr00s) {
		this.maccgr00s = maccgr00s;
	}

	public List<Mgrupo00> getMgrupo00s() {
		return mgrupo00s;
	}

	public void setMgrupo00s(List<Mgrupo00> mgrupo00s) {
		this.mgrupo00s = mgrupo00s;
	}


}