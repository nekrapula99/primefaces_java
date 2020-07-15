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

import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mparca00;

/**
 * The persistent class for the MIDIOM00 database table.
 * 
 */
@Entity
@Table(name="MIDIOM00")
public class Midiom00 implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDIDDI", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ididdi;

	@Column(name="COALDI", length=20)
	private String coaldi;

	@Column(name="CODIDI", unique=true, nullable=false, length=20)
	private String codidi;

	@Column(name="FEACDI", nullable=false)
	private Date feacdi;

	@Column(name="MAQCDI", nullable=false, length=100)
	private String maqcdi;

	@Column(name="NOMIDI", nullable=false, length=2000)
	private String nomidi;

	@Column(name="PRGCDI", nullable=false, length=20)
	private String prgcdi;

	@Column(name="REGIDI", nullable=false)
	private boolean regidi;

	@Column(name="USECDI", nullable=false, length=20)
	private String usecdi;

	//bi-directional many-to-one association to Mdesgr00
	@OneToMany(mappedBy="midiom00")
	private List<Mdesgr00> mdesgr00s;
	
	//bi-directional many-to-one association to Mdespr00
	@OneToMany(mappedBy="midiom00")
	private List<Mdespr00> mdespr00s;

	//bi-directional many-to-one association to Mconca00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMDI", unique=true)
	private Mconca00 mconca00;

	//bi-directional many-to-one association to Mparca00
	@OneToMany(mappedBy="midiom00")
	private List<Mparca00> mparca00s;

    public Midiom00() {
    }

	public int getIdiddi() {
		return this.ididdi;
	}

	public void setIdiddi(int ididdi) {
		this.ididdi = ididdi;
	}

	public String getCoaldi() {
		return this.coaldi;
	}

	public void setCoaldi(String coaldi) {
		this.coaldi = coaldi;
	}

	public String getCodidi() {
		return this.codidi;
	}

	public void setCodidi(String codidi) {
		this.codidi = codidi;
	}


	public String getNomidi() {
		return this.nomidi;
	}

	public void setNomidi(String nomidi) {
		this.nomidi = nomidi;
	}


	public boolean getRegidi() {
		return this.regidi;
	}

	public void setRegidi(boolean regidi) {
		this.regidi = regidi;
	}
	
	public List<Mdesgr00> getMdesgr00s() {
		return this.mdesgr00s;
	}

	public void setMdesgr00s(List<Mdesgr00> mdesgr00s) {
		this.mdesgr00s = mdesgr00s;
	}
	
	public Mconca00 getMconca00() {
		return this.mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}
	
	public List<Mparca00> getMparca00s() {
		return this.mparca00s;
	}

	public void setMparca00s(List<Mparca00> mparca00s) {
		this.mparca00s = mparca00s;
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
		return "Midiom00 [ididdi=" + ididdi + ", coaldi=" + coaldi
				+ ", codidi=" + codidi + ", idcmdi=" + mconca00 +", regidi=" + regidi + "]";
	}
	public String toStringId()
	{
		return "ididdi="+ididdi;
	}

	public Date getFeacdi() {
		return feacdi;
	}

	public void setFeacdi(Date feacdi) {
		this.feacdi = feacdi;
	}

	public String getMaqcdi() {
		return maqcdi;
	}

	public void setMaqcdi(String maqcdi) {
		this.maqcdi = maqcdi;
	}

	public String getPrgcdi() {
		return prgcdi;
	}

	public void setPrgcdi(String prgcdi) {
		this.prgcdi = prgcdi;
	}

	public String getUsecdi() {
		return usecdi;
	}

	public void setUsecdi(String usecdi) {
		this.usecdi = usecdi;
	}

	public List<Mdespr00> getMdespr00s() {
		return mdespr00s;
	}

	public void setMdespr00s(List<Mdespr00> mdespr00s) {
		this.mdespr00s = mdespr00s;
	}
}