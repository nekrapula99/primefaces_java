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
import javax.persistence.Transient;

@Entity
@Table(name = "TFACFC00")
public class Tfacfc00 implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDFCFC",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idfcfc;
	
	//[TQDFQF00] Id Cuota de la Cotizaci�n
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCUFC", nullable=false)
	private Tqdfqf00 tqdfqf00;
	
	//[MTIPTX00] Id. Tipo Transacci�n 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTXFC", nullable=false)
	private Mtiptx00 mtiptx00;
	
	//TQIGQG00 FK ID COOTIZACION
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOFC", nullable=false)
	private Tqigqg00 tqigqg00;
	
	//Prefijo Factura
	@Column(name = "PREFFC", length=50, nullable=false)
	private String preffc;
	
	//Nro.Factura
	@Column(name = "NRFAFC", length=20, nullable=false)
	private String nrfafc;
	
	//Tipo de Factura
	@Column(name = "TIFAFC", length=1, nullable=false)
	private String tifafc;
	
	//Id. Cliente al que se le factura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "IDCLFC", nullable=false)
	private Mgente00 mgente00;
	
	//Id. Moneda Facturacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMOFC", nullable=false)
	private Mgenus00 mgenus001;
	
	//Moneda
	@Column(name = "MOFAFC", length=20, nullable=false)
	private String mofafc;
	
	//Id. Medio de pago
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMPFC", nullable=false)
	private Mgenus00 mgenus002;
	
	//Id. Organizaci�n Ventas
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOVFC", nullable=false)
	private Mgenus00 mgenus003;
	
	//Id. Canal Distribucion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCVFC", nullable=false)
	private Mgenus00 mgenus004;
	
	//Nro orden Compra
	@Column(name = "NROCFC", length=30, nullable=false)
	private String nrocfc;

	//Descripcion de la Factura
	@Column(name = "DESCFC", length=500, nullable=false)
	private String descfc;
	
	//Observacion de la Factura
	@Column(name = "OBFAFC", length=500, nullable=false)
	private String obfafc;
	
	//Fecha genraci�n
	@Column(name="FEGEFC", nullable=false)
	private Date fegefc;
	
	//hora generaci�n
	@Column(name="HOGEFC", nullable=false)
	private String hogefc;

	//Id del rango usado 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRUFC", nullable=false)
	private Mrafra00 mrafra00;
	
	//cod rango
	@Column(name = "CORAFC", length=50,nullable=false)
	private String corafc;
	
	//clave tecnica
	@Column(name = "CKTUFC", length=100,nullable=false)
	private String cktufc;
	
	//Rango desde autorizado por la Dian
	@Column(name = "RADEFC", nullable=false)
	private Integer radefc;
	
	//Rango hasta autorizado por la Dian
	@Column(name = "RAHAFC", nullable=false)
	private Integer rahafc;
	
	//Rango hasta autorizado por la Dian
	@Column(name = "RADNFC",length=20, nullable=false)
	private Integer radnfc;
		
	
	//Fecha vigencia rango desde
	@Column(name="FERDFC", nullable=false)
	private Date ferdfc;
	
	//Fecha vigencia rango hasta
	@Column(name="FERHFC", nullable=false)
	private Date ferhfc;
	
	//Nombre del cliente
	@Column(name = "NOCLFC", length=300, nullable=false)
	private String noclfc;
	
	//Dirreccion cliente
	@Column(name = "DICLFC", length=300, nullable=false)
	private String diclfc;
	
	//Id de la Compa��a Facturadora
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMFC", nullable=false)
	private Mconca00 mconca00;
	
	//Plazo en dias para pago de factura
	@Column(name = "PZDIFC", nullable=false)
	private Integer pzdifc;
	
	//Descripcion Documento Identidad
	@Column(name = "DEDIFC", length=300, nullable=false)
	private String dedifc;
	
	//Descripcion pais del cliente
	@Column(name = "DEPSFC", length=300, nullable=false)
	private String depsfc;
	
	//Descripcion del Departamento del cliente
	@Column(name = "DEDPFC", length=300, nullable=false)
	private String dedpfc;
	
	//Descripcion Ciudad del Cliente
	@Column(name = "DECUFC", length=300, nullable=false)
	private String decufc;
	
	//Descripcion Ciudad del Cliente
	@Column(name = "DEPJFC", length=300, nullable=false)
	private String depjfc;

	//Tipo documento identidad Cliente 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTDFC", nullable=false)
	private Mgenus00 mgenus008;
	
	//tipo docuemnto identidad
	@Column(name = "TIDIFC", length=20,nullable=false)
	private String tidifc;
	
	//Nro docuemnto identidad
	@Column(name = "NRDIFC", length=20,nullable=false)
	private String nrdifc;
	
	//Id pais del cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPCFC", nullable=false)
	private Mgenus00 mgenus009;
	
	//Pais del cliente
	@Column(name = "PACLFC", length=20,nullable=false)
	private String paclfc;
	
	//ID DPTO CLIENTE
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDDCFC", nullable=false)
	private Mgenus00 mgenus005;
	
	//Departamento del Cliente
	@Column(name = "DECLFC", length=20,nullable=false)
	private String declfc;
	
	//Id ciudad del cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCDFC", nullable=false)
	private Mgenus00 mgenus006;
	
	//ciudad del Cliente
	@Column(name = "CDCLFC", length=20,nullable=false)
	private String cdclfc;
	
	//id tipo persona nat o jur del cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTPFC", nullable=false)
	private Mgenus00 mgenus007;
	
	
	//Persona Natural o Juridica del Cliente
	@Column(name = "PECLFC", length=20,nullable=false)
	private String peclfc;
	
	//id contacto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCNFC", nullable=false)
	private Mcotes00 mcotes00;

	//Primer Nombre Contacto
	@Column(name = "PNCOFC", length=30,nullable=false)
	private String pncofc;
	
	//segundo Nombre Contacto
	@Column(name = "SNCOFC", length=30,nullable=false)
	private String sncofc;
	
	//Primer apellido Contacto
	@Column(name = "PACOFC", length=30,nullable=false)
	private String pacofc;
	
	//segundo apellido Contacto
	@Column(name = "SACOFC", length=30,nullable=false)
	private String sacofc;
	
	//CUFE definido
	@Column(name = "CUFEDC", length=300,nullable=false)
	private String cufedc;
	
	//CUFE obtenido o valor con SHA-1
	@Column(name = "CUFEVC", length=300,nullable=false)
	private String cufevc;
	
	//Valor Bruto Factura
	@Column(name = "VABRFC", nullable=false)
	private Double vabrfc;
	
	//ID Impto consumo
	@Column(name = "IICOFC")
	private Integer iicofc;
	
	//Codigo impto consumo
	@Column(name = "COICFC", length=20)
	private String coicfc;
	
	//Impuesto Consumo Factura
	@Column(name = "IMCOFC", nullable=false)
	private Double imcofc;
	
	//ID Impto IVA
	@Column(name = "IIIVFC")
	private Integer iiivfc;
	
	//Codigo impto IVA
	@Column(name = "COIVFC", length=20)
	private String coivfc;
	
	//IVA Factura
	@Column(name = "VAIVFC", nullable=false)
	private Double vaivfc;
	
	//ID retencion IVA
	@Column(name = "IRIVFC")
	private Integer irivfc;
	
	//Codigo retencion IVA
	@Column(name = "CORIFC", length=20)
	private String corifc;
	
	
	//Retencion Iva Factura
	@Column(name = "REIVFC", nullable=false)
	private Double reivfc;
	
	//ID retencion fuente
	@Column(name = "IRFVFC")
	private Integer irfvfc;
	
	//Codigo retencion fuente
	@Column(name = "CORFFC", length=20)
	private String corffc;
	
	//Rete Fuente Factura
	@Column(name = "REFUFC", nullable=false)
	private Double refufc;
	
	//totalValor Factura
	@Column(name = "TOVAFC", nullable=false)
	private Double tovafc;

	//borrado logico
	@Column(name="REGCFC", nullable=false)
	private boolean regcfc;

	@Column(name="USECFC", length=20, nullable=false)
	private String usecfc;

	//programa
	@Column(name="PRGCFC", length=500, nullable=false)
	private String prgcfc;

	//Fecha actualizacion
	@Column(name="FEACFC", nullable=false)
	private Date feacfc;
	
	//maquina
	@Column(name="MAQCFC",  length=100, nullable=false)
	private String maqcfc;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tfacfc00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tfesfu00> tfesfu00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tfacfc00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Terfet00> terfet00s;
	
	//bi-directional many-to-one association to tqigqg00
	@OneToMany(mappedBy="tfacfc00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Terfed00> terfed00s;
	
	
	//bi-directional many-to-one association to tqigqg00
	//@OneToMany(mappedBy="tfacfc00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	//private List<Terfed00> terfed00s;
	
	
	@OneToMany(mappedBy="tfacfc00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tfadfd00> tfadfd00s;
	
	
	@Transient
	private int cuotaMayor;
	
	@Transient
	private Double totalValorBruto;
	
	@Transient
	private Terfet00 TerfetAux;
	
	@Transient
	private Terfed00 TerfedAux;
	
	@Transient private String aaaler;
	@Transient private String mmaler;
	@Transient private String ddaler;
	@Transient private String hhaler;
	@Transient private Date fecbas;
	@Transient private String alarma;
	@Transient private boolean flagAmarillo;
	@Transient private boolean flagRojo;
	

	public int getIdfcfc() {
		return idfcfc;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public String getPreffc() {
		return preffc;
	}

	public String getNrfafc() {
		return nrfafc;
	}

	public String getTifafc() {
		return tifafc;
	}



	public Mgenus00 getMgenus001() {
		return mgenus001;
	}

	public Mgenus00 getMgenus002() {
		return mgenus002;
	}

	public Mgenus00 getMgenus003() {
		return mgenus003;
	}

	public Mgenus00 getMgenus004() {
		return mgenus004;
	}

	public String getNrocfc() {
		return nrocfc;
	}

	public String getDescfc() {
		return descfc;
	}

	public String getObfafc() {
		return obfafc;
	}

	public Date getFegefc() {
		return fegefc;
	}



	public String getCorafc() {
		return corafc;
	}

	public Date getFerdfc() {
		return ferdfc;
	}

	public Date getFerhfc() {
		return ferhfc;
	}

	public String getNoclfc() {
		return noclfc;
	}

	public String getDiclfc() {
		return diclfc;
	}

	public String getTidifc() {
		return tidifc;
	}

	public String getNrdifc() {
		return nrdifc;
	}


	public String getPaclfc() {
		return paclfc;
	}

	public String getDeclfc() {
		return declfc;
	}


	public String getCdclfc() {
		return cdclfc;
	}


	public String getPeclfc() {
		return peclfc;
	}



	public String getPncofc() {
		return pncofc;
	}

	public String getSncofc() {
		return sncofc;
	}

	public String getPacofc() {
		return pacofc;
	}

	public String getSacofc() {
		return sacofc;
	}

	public String getCufedc() {
		return cufedc;
	}

	public String getCufevc() {
		return cufevc;
	}

	public boolean isRegcfc() {
		return regcfc;
	}

	public String getUsecfc() {
		return usecfc;
	}

	public String getPrgcfc() {
		return prgcfc;
	}

	public Date getFeacfc() {
		return feacfc;
	}

	public String getMaqcfc() {
		return maqcfc;
	}

	public void setIdfcfc(int idfcfc) {
		this.idfcfc = idfcfc;
	}

	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public void setPreffc(String preffc) {
		this.preffc = preffc;
	}

	public void setNrfafc(String nrfafc) {
		this.nrfafc = nrfafc;
	}

	public void setTifafc(String tifafc) {
		this.tifafc = tifafc;
	}
	
	public void setMgenus001(Mgenus00 mgenus001) {
		this.mgenus001 = mgenus001;
	}

	public void setMgenus002(Mgenus00 mgenus002) {
		this.mgenus002 = mgenus002;
	}

	public void setMgenus003(Mgenus00 mgenus003) {
		this.mgenus003 = mgenus003;
	}

	public void setMgenus004(Mgenus00 mgenus004) {
		this.mgenus004 = mgenus004;
	}

	public void setNrocfc(String nrocfc) {
		this.nrocfc = nrocfc;
	}

	public void setDescfc(String descfc) {
		this.descfc = descfc;
	}

	public void setObfafc(String obfafc) {
		this.obfafc = obfafc;
	}

	public void setFegefc(Date fegefc) {
		this.fegefc = fegefc;
	}

	public void setCorafc(String corafc) {
		this.corafc = corafc;
	}

	public void setFerdfc(Date ferdfc) {
		this.ferdfc = ferdfc;
	}

	public void setFerhfc(Date ferhfc) {
		this.ferhfc = ferhfc;
	}

	public void setNoclfc(String noclfc) {
		this.noclfc = noclfc;
	}

	public void setDiclfc(String diclfc) {
		this.diclfc = diclfc;
	}

	public void setTidifc(String tidifc) {
		this.tidifc = tidifc;
	}

	public void setNrdifc(String nrdifc) {
		this.nrdifc = nrdifc;
	}

	public void setPaclfc(String paclfc) {
		this.paclfc = paclfc;
	}

	public void setDeclfc(String declfc) {
		this.declfc = declfc;
	}

	public void setCdclfc(String cdclfc) {
		this.cdclfc = cdclfc;
	}

	public void setPeclfc(String peclfc) {
		this.peclfc = peclfc;
	}

	public void setPncofc(String pncofc) {
		this.pncofc = pncofc;
	}

	public void setSncofc(String sncofc) {
		this.sncofc = sncofc;
	}

	public void setPacofc(String pacofc) {
		this.pacofc = pacofc;
	}

	public void setSacofc(String sacofc) {
		this.sacofc = sacofc;
	}

	public void setCufedc(String cufedc) {
		this.cufedc = cufedc;
	}

	public void setCufevc(String cufevc) {
		this.cufevc = cufevc;
	}



	public void setRegcfc(boolean regcfc) {
		this.regcfc = regcfc;
	}

	public void setUsecfc(String usecfc) {
		this.usecfc = usecfc;
	}

	public void setPrgcfc(String prgcfc) {
		this.prgcfc = prgcfc;
	}

	public void setFeacfc(Date feacfc) {
		this.feacfc = feacfc;
	}

	public void setMaqcfc(String maqcfc) {
		this.maqcfc = maqcfc;
	}

	@Override
	public String toString() {
		return "Tfacfc00 [idfcfc=" + idfcfc
				+ ", preffc=" + preffc + ", nrfafc=" + nrfafc + ", tifafc="
				+ tifafc + ", nrocfc=" + nrocfc + ", cktufc=" + cktufc + ", radefc=" + radefc
				+ ", descfc=" + descfc + ", obfafc=" + obfafc + ", fegefc=" + ", rahafc=" + rahafc
				+ fegefc + ", hogefc=" + hogefc + ", corafc=" + corafc + ", radnfc=" + radnfc
				+ ", ferdfc=" + ferdfc + ", ferhfc=" + ferhfc + ", noclfc=" + noclfc + ", tovafc=" + tovafc
				+ noclfc + ", diclfc=" + diclfc + ", pzdifc=" + pzdifc
				+ ", tidifc=" + tidifc + ", nrdifc=" + nrdifc + ", mofafc=" + mofafc
				+ ", paclfc=" + paclfc + ", dedifc=" + dedifc + ", depsfc=" + depsfc + ", dedpfc=" + dedpfc
				+ ", declfc=" + declfc + ", cdclfc=" + ", decufc=" + decufc + ", depjfc=" + depjfc 
				+ cdclfc + ", peclfc=" + peclfc
				+ ", iicofc=" + iicofc + ", coicfc=" + coicfc + ", iiivfc=" + iiivfc
				+ ", coivfc=" + coivfc + ", irivfc=" + irivfc + ", corifc=" + corifc
				+ ", irfvfc=" + irfvfc + ", corffc=" + corffc
				+ ", pncofc=" + pncofc + ", sncofc="
				+ sncofc + ", pacofc=" + pacofc + ", sacofc=" + sacofc
				+ ", cufedc=" + cufedc + ", cufevc=" + cufevc + ", vabrfc="
				+ vabrfc + ", imcofc=" + imcofc + ", vaivfc=" + vaivfc
				+ ", reivfc=" + reivfc + ", refufc=" + refufc + ", regcfc="
				+ regcfc + ", usecfc=" + usecfc + ", prgcfc=" + prgcfc
				+ ", feacfc=" + feacfc + ", maqcfc=" + maqcfc 
				+ ", tqdfqf00=" +  (tqdfqf00!=null?tqdfqf00.getIdcpqf()+"-"+tqdfqf00.getNrcpqf():null)
				+ ", mtiptx00=" +  (mtiptx00!=null?mtiptx00.getIdtptx()+"-"+mtiptx00.getCotrtx():null)
				+ ", mconca00="  + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", tqigqg00=" +  (tqigqg00!=null?tqigqg00.getIdtqqg()+"-"+tqigqg00.getNroqqg():null)
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)
				+ ", mgenus004=" + (mgenus004!=null?mgenus004.getIdtrus()+"-"+mgenus004.getCodius():null)
				+ ", mgenus005=" + (mgenus005!=null?mgenus005.getIdtrus()+"-"+mgenus005.getCodius():null)
				+ ", mgenus006=" + (mgenus006!=null?mgenus006.getIdtrus()+"-"+mgenus006.getCodius():null)
				+ ", mgenus007=" + (mgenus007!=null?mgenus007.getIdtrus()+"-"+mgenus007.getCodius():null)
				+ ", mgenus008=" + (mgenus008!=null?mgenus008.getIdtrus()+"-"+mgenus008.getCodius():null)
				+ ", mgenus009=" + (mgenus009!=null?mgenus009.getIdtrus()+"-"+mgenus009.getCodius():null)
				+ ", mrafra00=" + (mrafra00!=null?mrafra00.getIdrara()+"-"+mrafra00.getCorara():null)
				+ ", mcotes00=" + (mcotes00!=null?mcotes00.getIdctes()+"-"+mcotes00.getCoctes():null)
				+ ", mgente00="  + (mgente00!=null?mgente00.getIdtrte()+"-"+mgente00.getCodite():null)

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
		return "idfcfc="+idfcfc;
	}
	
	public Tfacfc00(){}

	public List<Tfesfu00> getTfesfu00s() {
		return tfesfu00s;
	}

	public void setTfesfu00s(List<Tfesfu00> tfesfu00s) {
		this.tfesfu00s = tfesfu00s;
	}
	
	public List<Tfadfd00> getTfadfd00s() {
		return tfadfd00s;
	}

	public void setTfadfd00s(List<Tfadfd00> tfadfd00s) {
		this.tfadfd00s = tfadfd00s;
	}

	public Double getVabrfc() {
		return vabrfc;
	}

	public void setVabrfc(Double vabrfc) {
		this.vabrfc = vabrfc;
	}

	public Double getImcofc() {
		return imcofc;
	}

	public void setImcofc(Double imcofc) {
		this.imcofc = imcofc;
	}

	public Double getVaivfc() {
		return vaivfc;
	}

	public void setVaivfc(Double vaivfc) {
		this.vaivfc = vaivfc;
	}

	public Double getReivfc() {
		return reivfc;
	}

	public void setReivfc(Double reivfc) {
		this.reivfc = reivfc;
	}

	public Double getRefufc() {
		return refufc;
	}

	public void setRefufc(Double refufc) {
		this.refufc = refufc;
	}

	public Mgente00 getMgente00() {
		return mgente00;
	}

	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}

	public String getHogefc() {
		return hogefc;
	}

	public void setHogefc(String hogefc) {
		this.hogefc = hogefc;
	}

	public Mrafra00 getMrafra00() {
		return mrafra00;
	}

	public void setMrafra00(Mrafra00 mrafra00) {
		this.mrafra00 = mrafra00;
	}

	public String getCktufc() {
		return cktufc;
	}

	public void setCktufc(String cktufc) {
		this.cktufc = cktufc;
	}

	public Integer getRadefc() {
		return radefc;
	}

	public void setRadefc(Integer radefc) {
		this.radefc = radefc;
	}

	public Integer getRahafc() {
		return rahafc;
	}

	public void setRahafc(Integer rahafc) {
		this.rahafc = rahafc;
	}

	public Integer getRadnfc() {
		return radnfc;
	}

	public void setRadnfc(Integer radnfc) {
		this.radnfc = radnfc;
	}

	public Double getTovafc() {
		return tovafc;
	}

	public void setTovafc(Double tovafc) {
		this.tovafc = tovafc;
	}

	public Mgenus00 getMgenus008() {
		return mgenus008;
	}

	public void setMgenus008(Mgenus00 mgenus008) {
		this.mgenus008 = mgenus008;
	}

	public Mgenus00 getMgenus009() {
		return mgenus009;
	}

	public void setMgenus009(Mgenus00 mgenus009) {
		this.mgenus009 = mgenus009;
	}

	public Mgenus00 getMgenus005() {
		return mgenus005;
	}

	public void setMgenus005(Mgenus00 mgenus005) {
		this.mgenus005 = mgenus005;
	}

	public Mgenus00 getMgenus006() {
		return mgenus006;
	}

	public void setMgenus006(Mgenus00 mgenus006) {
		this.mgenus006 = mgenus006;
	}

	public Mgenus00 getMgenus007() {
		return mgenus007;
	}

	public void setMgenus007(Mgenus00 mgenus007) {
		this.mgenus007 = mgenus007;
	}

	public Mcotes00 getMcotes00() {
		return mcotes00;
	}

	public void setMcotes00(Mcotes00 mcotes00) {
		this.mcotes00 = mcotes00;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public Integer getPzdifc() {
		return pzdifc;
	}

	public void setPzdifc(Integer pzdifc) {
		this.pzdifc = pzdifc;
	}

	public String getDedifc() {
		return dedifc;
	}

	public void setDedifc(String dedifc) {
		this.dedifc = dedifc;
	}

	public String getDepsfc() {
		return depsfc;
	}

	public void setDepsfc(String depsfc) {
		this.depsfc = depsfc;
	}

	public String getDedpfc() {
		return dedpfc;
	}

	public void setDedpfc(String dedpfc) {
		this.dedpfc = dedpfc;
	}

	public String getDecufc() {
		return decufc;
	}

	public void setDecufc(String decufc) {
		this.decufc = decufc;
	}

	public String getDepjfc() {
		return depjfc;
	}

	public void setDepjfc(String depjfc) {
		this.depjfc = depjfc;
	}

	public String getMofafc() {
		return mofafc;
	}

	public void setMofafc(String mofafc) {
		this.mofafc = mofafc;
	}

	public int getCuotaMayor() {
		return cuotaMayor;
	}

	public void setCuotaMayor(int cuotaMayor) {
		this.cuotaMayor = cuotaMayor;
	}

	public Double getTotalValorBruto() {
		return totalValorBruto;
	}

	public void setTotalValorBruto(Double totalValorBruto) {
		this.totalValorBruto = totalValorBruto;
	}

	public List<Terfet00> getTerfet00s() {
		return terfet00s;
	}

	public void setTerfet00s(List<Terfet00> terfet00s) {
		this.terfet00s = terfet00s;
	}

	public List<Terfed00> getTerfed00s() {
		return terfed00s;
	}

	public void setTerfed00s(List<Terfed00> terfed00s) {
		this.terfed00s = terfed00s;
	}

	public Terfet00 getTerfetAux() {

		return this.getTerfet00s().get(this.getTerfet00s().size()-1);
	}
	
	public Terfed00 getTerfedAux() {

		return this.getTerfed00s().get(this.getTerfed00s().size()-1);
	}

	public String getAaaler() {
		return aaaler;
	}

	public void setAaaler(String aaaler) {
		this.aaaler = aaaler;
	}

	public String getMmaler() {
		return mmaler;
	}

	public void setMmaler(String mmaler) {
		this.mmaler = mmaler;
	}

	public String getDdaler() {
		return ddaler;
	}

	public void setDdaler(String ddaler) {
		this.ddaler = ddaler;
	}

	public String getHhaler() {
		return hhaler;
	}

	public void setHhaler(String hhaler) {
		this.hhaler = hhaler;
	}

	public Date getFecbas() {
		return fecbas;
	}

	public void setFecbas(Date fecbas) {
		this.fecbas = fecbas;
	}

	public String getAlarma() {
		return alarma;
	}

	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}

	public boolean isFlagAmarillo() {
		return flagAmarillo;
	}

	public void setFlagAmarillo(boolean flagAmarillo) {
		this.flagAmarillo = flagAmarillo;
	}

	public boolean isFlagRojo() {
		return flagRojo;
	}

	public void setFlagRojo(boolean flagRojo) {
		this.flagRojo = flagRojo;
	}

	public Integer getIicofc() {
		return iicofc;
	}

	public void setIicofc(Integer iicofc) {
		this.iicofc = iicofc;
	}

	public String getCoicfc() {
		return coicfc;
	}

	public void setCoicfc(String coicfc) {
		this.coicfc = coicfc;
	}

	public Integer getIiivfc() {
		return iiivfc;
	}

	public void setIiivfc(Integer iiivfc) {
		this.iiivfc = iiivfc;
	}

	public String getCoivfc() {
		return coivfc;
	}

	public void setCoivfc(String coivfc) {
		this.coivfc = coivfc;
	}

	public Integer getIrivfc() {
		return irivfc;
	}

	public void setIrivfc(Integer irivfc) {
		this.irivfc = irivfc;
	}

	public String getCorifc() {
		return corifc;
	}

	public void setCorifc(String corifc) {
		this.corifc = corifc;
	}

	public Integer getIrfvfc() {
		return irfvfc;
	}

	public void setIrfvfc(Integer irfvfc) {
		this.irfvfc = irfvfc;
	}

	public String getCorffc() {
		return corffc;
	}

	public void setCorffc(String corffc) {
		this.corffc = corffc;
	}
	
}
