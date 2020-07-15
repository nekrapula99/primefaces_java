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
import javax.persistence.Transient;

import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;

/**
 * The persistent class for the MDESGR00 database table.
 * 
 */
@Entity
@Table(name="MDESGR00")
public class Mdesgr00 implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTRDS", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrds;

	@Column(name="DCTTDS", nullable=false, length=3000)
	private String dcttds;

	@Column(name="FEACDS", nullable=false)
	private Date feacds;

	@Column(name="MAQUDS", nullable=false, length=100)
	private String maquds;

	@Column(name="PRGMDS", nullable=false, length=20)
	private String prgmds;

	@Column(name="REGIDS", nullable=false)
	private boolean regids;

	@Column(name="USERDS", nullable=false, length=20)
	private String userds;

	//bi-directional many-to-one association to Midiom00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDIDDS", nullable=false)
	private Midiom00 midiom00;

	//bi-directional many-to-one association to Mgenus00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUSDS", referencedColumnName="idtrus")
	private Mgenus00 mgenus00;

	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMDS")
	private Mconca00 mconca00;
	
	//Variable para guardar el indice en la entidaRd del detalle:
	@Transient private int index;

    public Mdesgr00() {
    }

	public int getIdtrds() {
		return this.idtrds;
	}

	public void setIdtrds(int idtrds) {
		this.idtrds = idtrds;
	}

	public String getDcttds() {
		return this.dcttds;
	}

	public void setDcttds(String dcttds) {
		this.dcttds = dcttds;
	}

	public Date getFeacds() {
		return this.feacds;
	}

	public void setFeacds(Date feacds) {
		this.feacds = feacds;
	}

	public String getMaquds() {
		return this.maquds;
	}

	public void setMaquds(String maquds) {
		this.maquds = maquds;
	}

	public String getPrgmds() {
		return this.prgmds;
	}

	public void setPrgmds(String prgmds) {
		this.prgmds = prgmds;
	}

	public boolean getRegids() {
		return this.regids;
	}

	public void setRegids(boolean regids) {
		this.regids = regids;
	}

	public String getUserds() {
		return this.userds;
	}

	public void setUserds(String userds) {
		this.userds = userds;
	}

	public Midiom00 getMidiom00() {
		return this.midiom00;
	}

	public void setMidiom00(Midiom00 midiom00) {
		this.midiom00 = midiom00;
	}
	
	public Mgenus00 getMgenus00() {
		return this.mgenus00;
	}

	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}
	
	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
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
		return "Mdesgr00 [idtrds=" + idtrds + ", dcttds=" + dcttds
				+ ", feacds=" + feacds + ", maquds=" + maquds + ", prgmds="
				+ prgmds + ", regids=" + regids + ", userds=" + userds
				+ ", midiom00=" + midiom00 + ", mgenus00=" + mgenus00
				+ ", mconca00=" + mconca00 + "]";
	}
	
	public String toStringId()
	{
		return "idtrds="+idtrds;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}