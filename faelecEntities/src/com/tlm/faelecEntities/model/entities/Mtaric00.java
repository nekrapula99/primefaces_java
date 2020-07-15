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
@Table(name = "Mtaric00")
public class Mtaric00 implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDICIC", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idicic;

	@Column(name="PRVAIC", nullable=false)
	private Double prvaic;
	
	@Column(name="PRICIC", nullable=false)
	private Double pricic;
	
	@Column(name="FEVHIC", nullable=false)
	private Date fevhic;
	
	@Column(name="REGCIC", nullable=false)
	private boolean regcic;

	@Column(name="USECIC", length=20, nullable=false)
	private String usecic;

	@Column(name="PRGCIC", length=20, nullable=false)
	private String prgcic;

	@Column(name="FEACIC", nullable=false)
	private Date feacic;
	
	@Column(name="MAQCIC",  length=100, nullable=false)
	private String maqcic;
	
	//FK Mgenus00 Tipo Bien o servicio genus  TBS
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIIC", nullable=false)
	private Mgenus00 mgenus001;
	
	//FK Mgenus00 Tarifa Retefuente correspondiente (DIAN) TRF
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCRIC", nullable=false)
	private Mgenus00 mgenus002;
	
	//FK Mgenus00  Ciudad de Venta o Factura -- genus B
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCUIC", nullable=false)
	private Mgenus00 mgenus003;

	
	
	@Override
	public String toString() {
		return "Mtaric00 [idicic=" + idicic + ", prvaic=" + prvaic
				+ ", pricic=" + pricic + ", fevhic=" + fevhic + ", regcic="
				+ regcic + ", usecic=" + usecic + ", prgcic=" + prgcic
				+ ", feacic=" + feacic + ", maqcic=" + maqcic 
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)
				+"]";
	}

	public String toStringId()
	{
		return "idicic="+idicic;
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
	
	public int getIdicic() {
		return idicic;
	}

	public void setIdicic(int idicic) {
		this.idicic = idicic;
	}

	public Double getPrvaic() {
		return prvaic;
	}

	public void setPrvaic(Double prvaic) {
		this.prvaic = prvaic;
	}

	public Double getPricic() {
		return pricic;
	}

	public void setPricic(Double pricic) {
		this.pricic = pricic;
	}

	public Date getFevhic() {
		return fevhic;
	}

	public void setFevhic(Date fevhic) {
		this.fevhic = fevhic;
	}

	public boolean isRegcic() {
		return regcic;
	}

	public void setRegcic(boolean regcic) {
		this.regcic = regcic;
	}

	public String getUsecic() {
		return usecic;
	}

	public void setUsecic(String usecic) {
		this.usecic = usecic;
	}

	public String getPrgcic() {
		return prgcic;
	}

	public void setPrgcic(String prgcic) {
		this.prgcic = prgcic;
	}

	public Date getFeacic() {
		return feacic;
	}

	public void setFeacic(Date feacic) {
		this.feacic = feacic;
	}

	public String getMaqcic() {
		return maqcic;
	}

	public void setMaqcic(String maqcic) {
		this.maqcic = maqcic;
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

}
