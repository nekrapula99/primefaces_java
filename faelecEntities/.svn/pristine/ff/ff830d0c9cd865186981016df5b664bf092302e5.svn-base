package com.tlm.faelecEntities.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "TFADFD00")
public class Tfadfd00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDDFFD",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddffd;
	
	// Fk Id. Factura Cabecera TFACFC00
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFCFD", nullable=false)
	private Tfacfc00 tfacfc00;
	
	// FK Id. Requerimiento a Facturar
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRFFD", nullable=false)
	private Tqdrqr00 tqdrqr00;
	
	//FK [TQDRFG00] Id Detalle Requerimiento-Producto en la Cuota
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDQRFD", nullable=false)
	private Tqdrfg00 tqdrfg00;
	
	//Linea o Renglon
	@Column(name = "LIORFD", nullable=false)
	private Integer liorfd;
	
	//Descripcion producto según Cotizacion
	@Column(name="DEPRFD", length=300, nullable=false)
	private String deprfd;
	
	//Descripcion requerimiento
	@Column(name="DEREFD", length=500, nullable=false)
	private String derefd;
	
	//Id. Producto	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRFD", nullable=false)
	private Tqdpqp00 tqdpqp00;
	
	//Codigo  Producto
	@Column(name = "COPRFD", length=20, nullable=false)
	private String coprfd;

	//Descripcion maestro producto
	@Column(name = "DEMPFD", length=300, nullable=false)
	private String dempfd;
	
	//Id. Tipo de bien o servicio asociado al producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTBFD", nullable=false)
	private Mgenus00 mgenus001;
	
	//Tipo e bien o servicio asociado al Producto
	@Column(name = "TIBSFD", length=500, nullable=false)
	private String tibsfd;
	
	//id. Unidad de Medida	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUMFD", nullable=false)
	private Mgenus00 mgenus002;
	
	
	//Unidad de Medida
	@Column(name = "UNMEFD", length=300, nullable=false)
	private String unmefd;
	
	//Valor Unitario Factura
	@Column(name = "VUFAFD", nullable=false)
	private Double vufafd;
	
	//id tarifa Retefuente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTRFD", nullable=false)
	private Mgenus00 mgenus003;
	
	//tarifa Retefuente
	@Column(name = "TARFFD", length=20, nullable=false)
	private String tarffd;
	
	//Descripcion Tarifa Retefuente
	@Column(name = "DETAFD", length=300, nullable=false)
	private String detafd;
	
	//Porcentaje Retefuente
	@Column(name = "PORFFD", nullable=false)
	private Double porffd;
	
	//Porcentaje IVA
	@Column(name = "POIVFD", nullable=false)
	private Double poivfd;
	
	//Porcentaje Reteiva
	@Column(name = "PORIFD", nullable=false)
	private Double porifd;
	
	//Porcentaje Impuesto Consumo
	@Column(name = "POICFD", nullable=false)
	private Double poicfd;
	
	//Cantidad Facturada
	@Column(name = "CAFAFD", nullable=false)
	private Double cafafd;
	
	//Valor Bruto detalle
	@Column(name = "VABRFD", nullable=false)
	private Double vabrfd;
	
	//Impuesto Consumo detalle
	@Column(name = "IMCOFD", nullable=false)
	private Double imcofd;
	
	//IVA
	@Column(name = "VAIVFD", nullable=false)
	private Double vaivfd;
	
	//Reteiva
	@Column(name = "REIVFD", nullable=false)
	private Double reivfd;
	
	//Retefuente
	@Column(name = "REFUFD", nullable=false)
	private Double refufd;
	
	//Total detalle
	@Column(name = "TODEFD", nullable=false)
	private Double todefd;
	
	//borrado logico
	@Column(name="REGCFD", nullable=false)
	private boolean regcfd;

	@Column(name="USECFD", length=20, nullable=false)
	private String usecfd;

	//programa
	@Column(name="PRGCFD", length=500, nullable=false)
	private String prgcfd;

	//Fecha actualizacion
	@Column(name="FEACFD", nullable=false)
	private Date feacfd;
	
	//maquina
	@Column(name="MAQCFD",  length=100, nullable=false)
	private String maqcfd;

	public int getIddffd() {
		return iddffd;
	}

	public Tfacfc00 getTfacfc00() {
		return tfacfc00;
	}


	public Integer getLiorfd() {
		return liorfd;
	}

	public String getDeprfd() {
		return deprfd;
	}

	public String getCoprfd() {
		return coprfd;
	}

	public String getDempfd() {
		return dempfd;
	}

	public String getTibsfd() {
		return tibsfd;
	}

	public String getUnmefd() {
		return unmefd;
	}

	public Double getVufafd() {
		return vufafd;
	}

	public String getTarffd() {
		return tarffd;
	}

	public String getDetafd() {
		return detafd;
	}

	public Double getPorffd() {
		return porffd;
	}

	public Double getPoivfd() {
		return poivfd;
	}

	public Double getPoicfd() {
		return poicfd;
	}

	public Double getCafafd() {
		return cafafd;
	}

	public Double getVabrfd() {
		return vabrfd;
	}

	public Double getImcofd() {
		return imcofd;
	}

	public Double getVaivfd() {
		return vaivfd;
	}

	public Double getReivfd() {
		return reivfd;
	}

	public Double getRefufd() {
		return refufd;
	}

	public boolean isRegcfd() {
		return regcfd;
	}

	public String getUsecfd() {
		return usecfd;
	}

	public String getPrgcfd() {
		return prgcfd;
	}

	public Date getFeacfd() {
		return feacfd;
	}

	public String getMaqcfd() {
		return maqcfd;
	}

	public void setIddffd(int iddffd) {
		this.iddffd = iddffd;
	}

	public void setTfacfc00(Tfacfc00 tfacfc00) {
		this.tfacfc00 = tfacfc00;
	}
	
	public void setLiorfd(Integer liorfd) {
		this.liorfd = liorfd;
	}

	public void setDeprfd(String deprfd) {
		this.deprfd = deprfd;
	}

	public void setCoprfd(String coprfd) {
		this.coprfd = coprfd;
	}

	public void setDempfd(String dempfd) {
		this.dempfd = dempfd;
	}

	public void setTibsfd(String tibsfd) {
		this.tibsfd = tibsfd;
	}

	public void setUnmefd(String unmefd) {
		this.unmefd = unmefd;
	}

	public void setVufafd(Double vufafd) {
		this.vufafd = vufafd;
	}

	public void setTarffd(String tarffd) {
		this.tarffd = tarffd;
	}

	public void setDetafd(String detafd) {
		this.detafd = detafd;
	}

	public void setPorffd(Double porffd) {
		this.porffd = porffd;
	}

	public void setPoivfd(Double poivfd) {
		this.poivfd = poivfd;
	}

	public void setPoicfd(Double poicfd) {
		this.poicfd = poicfd;
	}

	public void setCafafd(Double cafafd) {
		this.cafafd = cafafd;
	}

	public void setVabrfd(Double vabrfd) {
		this.vabrfd = vabrfd;
	}

	public void setImcofd(Double imcofd) {
		this.imcofd = imcofd;
	}

	public void setVaivfd(Double vaivfd) {
		this.vaivfd = vaivfd;
	}

	public void setReivfd(Double reivfd) {
		this.reivfd = reivfd;
	}

	public void setRefufd(Double refufd) {
		this.refufd = refufd;
	}

	public void setRegcfd(boolean regcfd) {
		this.regcfd = regcfd;
	}

	public void setUsecfd(String usecfd) {
		this.usecfd = usecfd;
	}

	public void setPrgcfd(String prgcfd) {
		this.prgcfd = prgcfd;
	}

	public void setFeacfd(Date feacfd) {
		this.feacfd = feacfd;
	}

	public void setMaqcfd(String maqcfd) {
		this.maqcfd = maqcfd;
	}

	@Override
	public String toString() {
		return "Tfadfd00 [iddffd=" + iddffd
				+ ", liorfd=" + liorfd + ", deprfd=" + deprfd
				+ ", coprfd=" + coprfd + ", dempfd=" + dempfd
				+ ", tibsfd=" + tibsfd + ", unmefd=" + unmefd 
				+ ", vufafd=" + vufafd + ", tarffd=" + tarffd + ", detafd="
				+ detafd + ", porffd=" + porffd + ", poivfd=" + poivfd + ", porifd=" + porifd 
				+ ", poicfd=" + poicfd + ", cafafd=" + cafafd + ", vabrfd="
				+ vabrfd + ", imcofd=" + imcofd + ", vaivfd=" + vaivfd
				+ ", reivfd=" + reivfd + ", refufd=" + refufd + ", regcfd="
				+ regcfd + ", usecfd=" + usecfd + ", prgcfd=" + prgcfd + ", derefd=" + derefd 
				+ ", feacfd=" + feacfd + ", maqcfd=" + maqcfd  + ", todefd=" + todefd 
				+ ", tfacfc00=" +  (tfacfc00!=null?tfacfc00.getIdfcfc()+"-"+tfacfc00.getNrfafc():null)
				+ ", tqdrqr00=" + (tqdrqr00!=null?tqdrqr00.getIdtrqr()+"-"+tqdrqr00.getCrqrqr():null)
				+ ", tqdpqp00=" + (tqdpqp00!=null?tqdpqp00.getIdqpqp()+"-"+tqdpqp00.getCacoqp():null)
				+ ", tfbdbd002=" + (tqdrfg00!=null?tqdrfg00.getIdqrfg()+"-"+tqdrfg00.getCafpfg():null)
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
		return "iddffd="+iddffd;
	}
	
	public Tfadfd00(){}

	public String getDerefd() {
		return derefd;
	}

	public void setDerefd(String derefd) {
		this.derefd = derefd;
	}

	public Double getTodefd() {
		return todefd;
	}

	public void setTodefd(Double todefd) {
		this.todefd = todefd;
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

	public Double getPorifd() {
		return porifd;
	}

	public void setPorifd(Double porifd) {
		this.porifd = porifd;
	}
}
