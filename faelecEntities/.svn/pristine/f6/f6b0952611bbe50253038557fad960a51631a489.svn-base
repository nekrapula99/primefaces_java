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
import javax.persistence.Transient;

@Entity
@Table(name = "TQDRFG00")
public class Tqdrfg00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDQRFG",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idqrfg;
	
	@Column(name="CAFPFG")
	private Double cafpfg;	
	
	@Column(name="REGCFG", nullable=false)
	private boolean regcfg;

	@Column(name="USECFG", length=20, nullable=false)
	private String usecfg;

	@Column(name="PRGCFG", length=500, nullable=false)
	private String prgcfg;

	@Column(name="FEACFG", nullable=false)
	private Date feacfg;
	
	@Column(name="MAQCFG",  length=100, nullable=false)
	private String maqcfg;
	
	
	//FK Id. Cuota de Pago TQDFQF00.IDCPQF
	@ManyToOne
	@JoinColumn(name="IDCPFG", nullable=false, referencedColumnName="idcpqf")
	private Tqdfqf00 tqdfqf00;
	
	//FK TQDPQP00Id. Detalle Requerimiento-Producto en la cuota
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFRFG", nullable=false)
	private Tqdpqp00 tqdpqp00;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tqdrfg00", cascade = CascadeType.ALL)
	private List<Tfbdbd00> tfbdbd00s;
	
	@Transient
	private Double valorBruto;
	
	@Transient
	private Double impConsumo;
	
	@Transient
	private Double iva;
	
	@Transient
	private Double reteIva;
	
	@Transient
	private Double reteFuente;
	
	@Transient
	private Double valorPagar;
	
	
	
	public Tqdrfg00(){}
	
	public String toStringId()
	{
		return "idqrfg="+idqrfg;
	}
	
	@Override
	public String toString() {
		return "Tqdrfg00 [idqrfg=" + idqrfg + ", cafpfg=" + cafpfg
				+ ", regcfg=" + regcfg + ", usecfg=" + usecfg + ", prgcfg="
				+ prgcfg + ", feacfg=" + feacfg + ", maqcfg=" + maqcfg
				+ ", tqdfqf00=" + (tqdfqf00!=null?tqdfqf00.getIdcpqf()+"-"+tqdfqf00.getNracqf():null)
				+ ", tqdpqp00=" + (tqdpqp00!=null?tqdpqp00.getIdqpqp()+"-"+tqdpqp00.getIdqpqp():null)	
				+ "]";
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

	public int getIdqrfg() {
		return idqrfg;
	}

	public void setIdqrfg(int idqrfg) {
		this.idqrfg = idqrfg;
	}

	public Double getCafpfg() {
		return cafpfg;
	}

	public void setCafpfg(Double cafpfg) {
		this.cafpfg = cafpfg;
	}

	public boolean isRegcfg() {
		return regcfg;
	}

	public void setRegcfg(boolean regcfg) {
		this.regcfg = regcfg;
	}

	public String getUsecfg() {
		return usecfg;
	}

	public void setUsecfg(String usecfg) {
		this.usecfg = usecfg;
	}

	public String getPrgcfg() {
		return prgcfg;
	}

	public void setPrgcfg(String prgcfg) {
		this.prgcfg = prgcfg;
	}

	public Date getFeacfg() {
		return feacfg;
	}

	public void setFeacfg(Date feacfg) {
		this.feacfg = feacfg;
	}

	public String getMaqcfg() {
		return maqcfg;
	}

	public void setMaqcfg(String maqcfg) {
		this.maqcfg = maqcfg;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}

	public Tqdpqp00 getTqdpqp00() {
		return tqdpqp00;
	}

	public void setTqdpqp00(Tqdpqp00 tqdpqp00) {
		this.tqdpqp00 = tqdpqp00;
	}

	public List<Tfbdbd00> getTfbdbd00s() {
		return tfbdbd00s;
	}

	public void setTfbdbd00s(List<Tfbdbd00> tfbdbd00s) {
		this.tfbdbd00s = tfbdbd00s;
	}

	public Double getImpConsumo() {
		return impConsumo;
	}

	public void setImpConsumo(Double impConsumo) {
		this.impConsumo = impConsumo;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getReteIva() {
		return reteIva;
	}

	public void setReteIva(Double reteIva) {
		this.reteIva = reteIva;
	}

	public Double getReteFuente() {
		return reteFuente;
	}

	public void setReteFuente(Double reteFuente) {
		this.reteFuente = reteFuente;
	}

	public Double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	
}
