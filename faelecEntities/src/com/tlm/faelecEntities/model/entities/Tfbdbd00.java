package com.tlm.faelecEntities.model.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "TFBDBD00")
public class Tfbdbd00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDDFBD",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddfbd;
	
	// FK [TFBCBC00] Id. Factura Cabecera
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFCBD", nullable=false)
	private Tfbcbc00 tfbcbc00;
	
	//FK [TQDRQR00]Id. Requerimiento a Facturar
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRFBD", nullable=false)
	private Tqdrqr00 tqdrqr00;
	
	//FK [TQDRFG00] Id Detalle Requerimiento-Producto en la Cuota
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDQRBD", nullable=false)
	private Tqdrfg00 tqdrfg00;
	
	//Linea o Renglon
	@Column(name = "LIORBD", nullable=false)
	private Integer liorbd;
	
	//Descripcion Requerimiento
	@Column(name = "DERFBD", length=500, nullable=false)
	private String derfbd;
	
	//Id. Producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRBD", nullable=false)
	private Tqdpqp00 tqdpqp00;
	
	
	//Codigo  Producto
	@Column(name = "COPRBD", length=20, nullable=false)
	private String coprbd;

	//Descripcion producto seg�n Cotizacion
	@Column(name = "DEPRBD", length=300, nullable=false)
	private String deprbd;
	
	//Descripcion Maestro Producto
	@Column(name = "DEMPBD", length=300, nullable=false)
	private String dempbd;
	
	//Id. Tipo de bien o servicio asociado al producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTBBD", nullable=false)
	private Mgenus00 mgenus001;
	
	//Tipo e bien o servicio asociado al Producto
	@Column(name = "TIBSBD", length=300, nullable=false)
	private String tibsbd;
	
	//id. Unidad de Medida	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUMBD", nullable=false)
	private Mgenus00 mgenus002;
	
	//Unidad de Medida
	@Column(name = "UNMEBD", length=300, nullable=false)
	private String unmebd;
	
	//Valor Unitario Factura
	@Column(name = "VUFABD", nullable=false)
	private Double vufabd;
	
	//id tarifa Retefuente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTRBD", nullable=false)
	private Mgenus00 mgenus003;
	
	//tarifa Retefuente
	@Column(name = "TARFBD", length=20, nullable=false)
	private String tarfbd;
	
	//Descripcion Tarifa Retefuente
	@Column(name = "DETABD", length=300, nullable=false)
	private String detabd;
	
	//Porcentaje Retefuente
	@Column(name = "PORFBD", nullable=false)
	private Double porfbd;
	
	//Porcentaje IVA
	@Column(name = "POIVBD", nullable=false)
	private Double poivbd;
	
	//Porcentaje Impuesto Consumo
	@Column(name = "POICBD", nullable=false)
	private Double poicbd;
	
	//Porcentaje Reteiva
	@Column(name = "PORIBD")
	private Double poribd;
	
	//Cantidad Facturada
	@Column(name = "CAFABD", nullable=false)
	private Double cafabd;
	
	//Valor Bruto detalle
	@Column(name = "VABRBD", nullable=false)
	private Double vabrbd;
	
	//Impuesto Consumo detalle
	@Column(name = "IMCOBD", nullable=false)
	private Double imcobd;
	
	//IVA
	@Column(name = "VAIVBD", nullable=false)
	private Double vaivbd;
	
	//Reteiva
	@Column(name = "REIVBD", nullable=false)
	private Double reivbd;
	
	//Retefuente
	@Column(name = "REFUBD", nullable=false)
	private Double refubd;
	
	//Total detalle
	@Column(name = "TODEBD", nullable=false)
	private Double todebd;
	
	//borrado logico
	@Column(name="REGCBD", nullable=false)
	private boolean regcbd;

	@Column(name="USECBD", length=20, nullable=false)
	private String usecbd;

	//programa
	@Column(name="PRGCBD", length=500, nullable=false)
	private String prgcbd;

	//Fecha actualizacion
	@Column(name="FEACBD", nullable=false)
	private Date feacbd;
	
	//maquina
	@Column(name="MAQCBD",  length=100, nullable=false)
	private String maqcbd;
	
	
	//bi-directional many-to-one association to tqigqg00
	/*@OneToMany(mappedBy="tfbdbd00", cascade = CascadeType.ALL)
	private List<Tfadfd00> tfadfd001s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tfbdbd00", cascade = CascadeType.ALL)
	private List<Tfadfd00> tfadfd002s;*/
	
	
	public int getIddfbd() {
		return iddfbd;
	}

	public void setIddfbd(int iddfbd) {
		this.iddfbd = iddfbd;
	}

	public Tfbcbc00 getTfbcbc00() {
		return tfbcbc00;
	}

	public void setTfbcbc00(Tfbcbc00 tfbcbc00) {
		this.tfbcbc00 = tfbcbc00;
	}

	public Tqdrqr00 getTqdrqr00() {
		return tqdrqr00;
	}

	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}

	public Tqdrfg00 getTqdrfg00() {
		return tqdrfg00;
	}

	public void setTqdrfg00(Tqdrfg00 tqdrfg00) {
		this.tqdrfg00 = tqdrfg00;
	}

	public Integer getLiorbd() {
		return liorbd;
	}

	public void setLiorbd(Integer liorbd) {
		this.liorbd = liorbd;
	}

	public String getCoprbd() {
		return coprbd;
	}

	public void setCoprbd(String coprbd) {
		this.coprbd = coprbd;
	}

	public String getDeprbd() {
		return deprbd;
	}

	public void setDeprbd(String deprbd) {
		this.deprbd = deprbd;
	}

	public String getDempbd() {
		return dempbd;
	}

	public void setDempbd(String dempbd) {
		this.dempbd = dempbd;
	}

	public String getTibsbd() {
		return tibsbd;
	}

	public void setTibsbd(String tibsbd) {
		this.tibsbd = tibsbd;
	}

	public String getUnmebd() {
		return unmebd;
	}

	public void setUnmebd(String unmebd) {
		this.unmebd = unmebd;
	}

	public Double getVufabd() {
		return vufabd;
	}

	public void setVufabd(Double vufabd) {
		this.vufabd = vufabd;
	}

	public String getTarfbd() {
		return tarfbd;
	}

	public void setTarfbd(String tarfbd) {
		this.tarfbd = tarfbd;
	}

	public String getDetabd() {
		return detabd;
	}

	public void setDetabd(String detabd) {
		this.detabd = detabd;
	}

	public Double getPorfbd() {
		return porfbd;
	}

	public void setPorfbd(Double porfbd) {
		this.porfbd = porfbd;
	}

	public Double getPoivbd() {
		return poivbd;
	}

	public void setPoivbd(Double poivbd) {
		this.poivbd = poivbd;
	}

	public Double getPoicbd() {
		return poicbd;
	}

	public void setPoicbd(Double poicbd) {
		this.poicbd = poicbd;
	}

	public Double getCafabd() {
		return cafabd;
	}

	public void setCafabd(Double cafabd) {
		this.cafabd = cafabd;
	}

	public Double getVabrbd() {
		return vabrbd;
	}

	public void setVabrbd(Double vabrbd) {
		this.vabrbd = vabrbd;
	}

	public Double getImcobd() {
		return imcobd;
	}

	public void setImcobd(Double imcobd) {
		this.imcobd = imcobd;
	}

	public Double getVaivbd() {
		return vaivbd;
	}

	public void setVaivbd(Double vaivbd) {
		this.vaivbd = vaivbd;
	}

	public Double getReivbd() {
		return reivbd;
	}

	public void setReivbd(Double reivbd) {
		this.reivbd = reivbd;
	}

	public Double getRefubd() {
		return refubd;
	}

	public void setRefubd(Double refubd) {
		this.refubd = refubd;
	}

	public boolean isRegcbd() {
		return regcbd;
	}

	public void setRegcbd(boolean regcbd) {
		this.regcbd = regcbd;
	}

	public String getUsecbd() {
		return usecbd;
	}

	public void setUsecbd(String usecbd) {
		this.usecbd = usecbd;
	}

	public String getPrgcbd() {
		return prgcbd;
	}

	public void setPrgcbd(String prgcbd) {
		this.prgcbd = prgcbd;
	}

	public Date getFeacbd() {
		return feacbd;
	}

	public void setFeacbd(Date feacbd) {
		this.feacbd = feacbd;
	}

	public String getMaqcbd() {
		return maqcbd;
	}

	public void setMaqcbd(String maqcbd) {
		this.maqcbd = maqcbd;
	}

	@Override
	public String toString() {
		return "Tfbdbd00 [iddfbd=" + iddfbd
				+ ", liorbd=" + liorbd + ", derfbd=" + derfbd 
				+ ", coprbd=" + coprbd + ", deprbd=" + deprbd
				+ ", dempbd=" + dempbd + ", tibsbd=" + tibsbd  + ", unmebd=" + unmebd
				+ ", vufabd=" + vufabd + ",  tarfbd="
				+ tarfbd + ", detabd=" + detabd + ", porfbd=" + porfbd + ", poribd=" + poribd  
				+ ", poivbd=" + poivbd + ", poicbd=" + poicbd + ", cafabd="
				+ cafabd + ", vabrbd=" + vabrbd + ", imcobd=" + imcobd + ", todebd=" + todebd
				+ ", vaivbd=" + vaivbd + ", reivbd=" + reivbd + ", refubd="	+ refubd 
				+ ", regcbd=" + regcbd + ", usecbd=" + usecbd
				+ ", prgcbd=" + prgcbd + ", feacbd=" + feacbd + ", maqcbd="	+ maqcbd 
		 		+ ", tfbcbc00=" + (tfbcbc00!=null?tfbcbc00.getIdfcbc()+"-"+tfbcbc00.getNrcubc():null)
		 		+ ", tqdrqr00=" + (tqdrqr00!=null?tqdrqr00.getIdtrqr()+"-"+tqdrqr00.getCrqrqr():null)
		 		+ ", tqdrfg00=" + (tqdrfg00!=null?tqdrfg00.getIdqrfg()+"-"+tqdrfg00.getCafpfg():null)
		 		+ ", tqdpqp00=" + (tqdpqp00!=null?tqdpqp00.getIdqpqp()+"-"+tqdpqp00.getCacoqp():null)
		 		+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)

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
	
	public String toStringId()
	{
		return "iddfbd="+iddfbd;
	}
	
	public Tfbdbd00(){}

	public Tqdpqp00 getTqdpqp00() {
		return tqdpqp00;
	}

	public void setTqdpqp00(Tqdpqp00 tqdpqp00) {
		this.tqdpqp00 = tqdpqp00;
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

	public String getDerfbd() {
		return derfbd;
	}

	public void setDerfbd(String derfbd) {
		this.derfbd = derfbd;
	}

	public Double getTodebd() {
		return todebd;
	}

	public void setTodebd(Double todebd) {
		this.todebd = todebd;
	}

	public Double getPoribd() {
		return poribd;
	}

	public void setPoribd(Double poribd) {
		this.poribd = poribd;
	}
}
