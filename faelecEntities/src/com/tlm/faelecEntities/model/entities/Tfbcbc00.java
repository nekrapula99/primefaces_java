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
@Table(name = "TFBCBC00")
public class Tfbcbc00 implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IDFCBC",unique = true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idfcbc;
	
	//TQDFQF00.NRCPQF num cuota
	@Column(name = "NRCUBC", nullable=false)
	private Integer nrcubc;
	
	//tipo trans
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTXBC", nullable=false)
	private Mtiptx00 mtiptx00;

	//Tipo factura
	@Column(name = "TIFABC", length=1, nullable=false)
	private String tifabc;
	
	//id cliene a quien factura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCLBC", nullable=false)
	private Mgente00 mgente00;
	
	//Moneda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMOBC", nullable=false)
	private Mgenus00 mgenus001;
	
	//Moneda
	@Column(name = "MOFABC", length=20, nullable=false)
	private String mofabc;
		
	//Medio pago 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMPBC", nullable=false)
	private Mgenus00 mgenus002;
	
	//Tipo factura
	@Column(name = "MEPABC", length=20, nullable=false)
	private String mepabc;
	
	//idioma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDIDBC", nullable=false)
	private Midiom00 midiom00;
	
	//Id. Organización Ventas
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOVBC", nullable=false)
	private Mgenus00 mgenus003;
	
	//id. Canal Distribucion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCVBC", nullable=false)
	private Mgenus00 mgenus004;
	
	//Nro orden Compra
	@Column(name = "NROCBC", length=30)
	private String nrocbc;
	
	//Descripcion de la Cuota en la Factura
	@Column(name = "DESCBC", length=500,nullable=false)
	private String descbc;
	
	//observacion de la Factura
	@Column(name = "OBFABC", length=500)
	private String obfabc;
	
	//fecha generacion
	@Column(name="FEGEBC", nullable=false)
	private Date fegebc;
	
	//hora geenracion
	@Column(name="HOGEBC", nullable=false)
	private String hogebc;
	
	//hora geenracion
	@Column(name="CORABC",length=50, nullable=false)
	private String corabc;
	
	//fecha vigencia rango desde
	@Column(name="FERDBC", nullable=false)
	private Date ferdbc;
	
	//fecha vigencia rango hasta
	@Column(name="FERHBC", nullable=false)
	private Date ferhbc;
	
	//Nombre del cliente
	@Column(name = "NOCLBC", length=300,nullable=false)
	private String noclbc;
	
	//Direccion Cliente
	@Column(name = "DICLBC", length=300,nullable=false)
	private String diclbc;
	
	//plazo en dias
	@Column(name = "PZDIBC",nullable=false)
	private Integer pzdibc;
	
	//Tipo documento identidad Cliente 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTDBC", nullable=false)
	private Mgenus00 mgenus008;
	
	//tipo docuemnto identidad
	@Column(name = "TIDIBC", length=20,nullable=false)
	private String tidibc;
	
	//Nro docuemnto identidad
	@Column(name = "NRDIBC", length=20,nullable=false)
	private String nrdibc;
	
	//Id pais del cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPCBC", nullable=false)
	private Mgenus00 mgenus009;
	
	//pais del cliente
	@Column(name = "PACLBC", length=20,nullable=false)
	private String paclbc;
	
	//ID DPTO CLIENTE
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDDCBC", nullable=false)
	private Mgenus00 mgenus005;
	
	//ID DPTO CLIENTE
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCMBC", nullable=false)
	private Mconca00 mconca00;
	
	
	//dpto del cliente
	@Column(name = "DECLBC", length=20,nullable=false)
	private String declbc;
	
	//Id ciudad del cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCDBC", nullable=false)
	private Mgenus00 mgenus006;
	

	//ciudad del cliente
	@Column(name = "CDCLBC", length=20,nullable=false)
	private String cdclbc;
	
	//Descripcion documento identidad
	@Column(name = "DEDIBC", length=300,nullable=false)
	private String dedibc;

	//Descripcion Descripcion pais del cliente
	@Column(name = "DEPSBC", length=300,nullable=false)
	private String depsbc;
	
	//Descripcion del Departamento del cliente
	@Column(name = "DEDPBC", length=300,nullable=false)
	private String dedpbc;
	
	//Descripcion del Departamento del cliente
	@Column(name = "DECUBC", length=300,nullable=false)
	private String decubc;
		
	//Descripcion del Departamento del cliente
	@Column(name = "DEPJBC", length=300,nullable=false)
	private String depjbc;
	
	//id tipo persona nat o jur del cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTPBC", nullable=false)
	private Mgenus00 mgenus007;
	
	//id tipo persona nat o jur del cliente
	@Column(name = "PECLBC", length=20, nullable=false)
	private String peclbc;
	
	//id contacto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCNBC", nullable=false)
	private Mcotes00 mcotes00;
	
	//primer nombre contacto
	@Column(name = "PNCOBC", length=30)
	private String pncobc;
	
	//segundo nombre contacto
	@Column(name = "SNCOBC", length=30)
	private String sncobc;
	
	//primer apellido contacto
	@Column(name = "PACOBC", length=30)
	private String pacobc;
	
	//segundo apellido contacto
	@Column(name = "SACOBC", length=30)
	private String sacobc;
	
	//CUFEBC
	@Column(name = "CUFEBC", length=30)
	private String cufebc;
	
	//ID Impto consumo
	@Column(name = "IICOBC")
	private Integer iicobc;
	
	//Codigo impto consumo
	@Column(name = "COICBC", length=20)
	private String coicbc;
	
	//ID Impto IVA
	@Column(name = "IIIVBC")
	private Integer iiivbc;
	
	//Codigo impto IVA
	@Column(name = "COIVBC", length=20)
	private String coivbc;
	
	//ID retencion IVA
	@Column(name = "IRIVBC")
	private Integer irivbc;
	
	//Codigo retencion IVA
	@Column(name = "CORIBC", length=20)
	private String coribc;
	
	//ID retencion fuente
	@Column(name = "IRFVBC")
	private Integer irfvbc;
	
	//Codigo retencion fuente
	@Column(name = "CORFBC", length=20)
	private String corfbc;
	
	@Column(name="REGCBC", nullable=false)
	private boolean regcbc;

	@Column(name="USECBC", length=20, nullable=false)
	private String usecbc;

	@Column(name="PRGCBC", length=500, nullable=false)
	private String prgcbc;

	@Column(name="FEACBC", nullable=false)
	private Date feacbc;
	
	@Column(name="MAQCBC",  length=100, nullable=false)
	private String maqcbc;

	//FK TQDFQF00.IDCPQF --cuota de la cotizacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCUBC", nullable=false)
	private Tqdfqf00 tqdfqf00;
	
	//FK, TQIGQG00.IDTQQG, Index_Cotizacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOBC", nullable=false)
	private Tqigqg00 tqigqg00;
	
	@OneToMany(mappedBy="tfbcbc00",fetch = FetchType.LAZY,orphanRemoval=true,cascade = CascadeType.ALL)
	private List<Tfbdbd00> tfbdbd00s;
	
	@Transient
	private int cuotaMayor;
	
	@Transient
	private Double totalValorBruto;
	

	public int getIdfcbc() {
		return idfcbc;
	}

	public void setIdfcbc(int idfcbc) {
		this.idfcbc = idfcbc;
	}

	public Integer getNrcubc() {
		return nrcubc;
	}

	public void setNrcubc(Integer nrcubc) {
		this.nrcubc = nrcubc;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	public String getTifabc() {
		return tifabc;
	}

	public void setTifabc(String tifabc) {
		this.tifabc = tifabc;
	}

	public Mgente00 getMgente00() {
		return mgente00;
	}

	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
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

	public Midiom00 getMidiom00() {
		return midiom00;
	}

	public void setMidiom00(Midiom00 midiom00) {
		this.midiom00 = midiom00;
	}

	public Mgenus00 getMgenus003() {
		return mgenus003;
	}

	public void setMgenus003(Mgenus00 mgenus003) {
		this.mgenus003 = mgenus003;
	}

	public Mgenus00 getMgenus004() {
		return mgenus004;
	}

	public void setMgenus004(Mgenus00 mgenus004) {
		this.mgenus004 = mgenus004;
	}

	public String getNrocbc() {
		return nrocbc;
	}

	public void setNrocbc(String nrocbc) {
		this.nrocbc = nrocbc;
	}

	public String getDescbc() {
		return descbc;
	}

	public void setDescbc(String descbc) {
		this.descbc = descbc;
	}

	public String getObfabc() {
		return obfabc;
	}

	public void setObfabc(String obfabc) {
		this.obfabc = obfabc;
	}

	public Date getFegebc() {
		return fegebc;
	}

	public void setFegebc(Date fegebc) {
		this.fegebc = fegebc;
	}

	public String getHogebc() {
		return hogebc;
	}

	public void setHogebc(String hogebc) {
		this.hogebc = hogebc;
	}

	public String getCorabc() {
		return corabc;
	}

	public void setCorabc(String corabc) {
		this.corabc = corabc;
	}

	public Date getFerdbc() {
		return ferdbc;
	}

	public void setFerdbc(Date ferdbc) {
		this.ferdbc = ferdbc;
	}

	public Date getFerhbc() {
		return ferhbc;
	}

	public void setFerhbc(Date ferhbc) {
		this.ferhbc = ferhbc;
	}

	public String getNoclbc() {
		return noclbc;
	}

	public void setNoclbc(String noclbc) {
		this.noclbc = noclbc;
	}

	public String getDiclbc() {
		return diclbc;
	}

	public void setDiclbc(String diclbc) {
		this.diclbc = diclbc;
	}

	public String getTidibc() {
		return tidibc;
	}

	public void setTidibc(String tidibc) {
		this.tidibc = tidibc;
	}

	public String getNrdibc() {
		return nrdibc;
	}

	public void setNrdibc(String nrdibc) {
		this.nrdibc = nrdibc;
	}

	public String getPaclbc() {
		return paclbc;
	}

	public void setPaclbc(String paclbc) {
		this.paclbc = paclbc;
	}

	public String getDeclbc() {
		return declbc;
	}

	public void setDeclbc(String declbc) {
		this.declbc = declbc;
	}

	public String getCdclbc() {
		return cdclbc;
	}

	public void setCdclbc(String cdclbc) {
		this.cdclbc = cdclbc;
	}

	public String getPeclbc() {
		return peclbc;
	}

	public void setPeclbc(String peclbc) {
		this.peclbc = peclbc;
	}


	public String getPncobc() {
		return pncobc;
	}

	public void setPncobc(String pncobc) {
		this.pncobc = pncobc;
	}

	public String getSncobc() {
		return sncobc;
	}

	public void setSncobc(String sncobc) {
		this.sncobc = sncobc;
	}

	public String getPacobc() {
		return pacobc;
	}

	public void setPacobc(String pacobc) {
		this.pacobc = pacobc;
	}

	public String getSacobc() {
		return sacobc;
	}

	public void setSacobc(String sacobc) {
		this.sacobc = sacobc;
	}

	public String getCufebc() {
		return cufebc;
	}

	public void setCufebc(String cufebc) {
		this.cufebc = cufebc;
	}

	public boolean isRegcbc() {
		return regcbc;
	}

	public void setRegcbc(boolean regcbc) {
		this.regcbc = regcbc;
	}

	public String getUsecbc() {
		return usecbc;
	}

	public void setUsecbc(String usecbc) {
		this.usecbc = usecbc;
	}

	public String getPrgcbc() {
		return prgcbc;
	}

	public void setPrgcbc(String prgcbc) {
		this.prgcbc = prgcbc;
	}

	public Date getFeacbc() {
		return feacbc;
	}

	public void setFeacbc(Date feacbc) {
		this.feacbc = feacbc;
	}

	public String getMaqcbc() {
		return maqcbc;
	}

	public void setMaqcbc(String maqcbc) {
		this.maqcbc = maqcbc;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}
	
	public String toStringId()
	{
		return "idfcbc="+idfcbc;
	}
	
	
	
	public String getMofabc() {
		return mofabc;
	}

	public void setMofabc(String mofabc) {
		this.mofabc = mofabc;
	}

	public String getMepabc() {
		return mepabc;
	}

	public void setMepabc(String mepabc) {
		this.mepabc = mepabc;
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
		return "Tfbcbc00 [idfcbc=" + idfcbc + ", nrcubc=" + nrcubc
				+ ", mtiptx00=" + mtiptx00 + ", tifabc=" + tifabc
				+ ", nrocbc=" + nrocbc + ", descbc=" + descbc + ", obfabc="
				+ ", mofabc=" + mofabc +  ", mepabc=" + mepabc 
				+ obfabc + ", fegebc=" + fegebc + ", hogebc=" + hogebc
				+ ", corabc=" + corabc + ", ferdbc=" + ferdbc + ", ferhbc="
				+ ", iicobc=" + iicobc + ", coicbc=" + coicbc
				+ ", iiivbc=" + iiivbc + ", coivbc=" + coivbc
				+ ", irivbc=" + irivbc + ", coribc=" + coribc
				+ ", irfvbc=" + irfvbc + ", corfbc=" + corfbc
				+ ferhbc + ", noclbc=" + noclbc + ", diclbc=" + diclbc
				+ ", tidibc=" + tidibc + ", nrdibc="
				+ nrdibc  + ", paclbc=" + paclbc  + ", dedibc=" + dedibc 
				+ ", declbc=" + declbc + ", pzdibc=" + pzdibc 
				+ ", cdclbc=" + cdclbc + ", idtpbc=" + ", depjbc=" + depjbc 
				+ ", decubc=" + decubc   + ", dedpbc=" + dedpbc  + ", depsbc=" 
				+ depsbc + ", depsbc=" + depsbc 
				+ ", peclbc=" + peclbc + ", pncobc=" 
				+ pncobc + ", sncobc=" + sncobc + ", pacobc=" + pacobc
				+ ", sacobc=" + sacobc + ", cufebc=" + cufebc + ", regcbc="
				+ regcbc + ", usecbc=" + usecbc + ", prgcbc=" + prgcbc
				+ ", feacbc=" + feacbc + ", maqcbc=" + maqcbc	
				+ ", mgente00="  + (mgente00!=null?mgente00.getIdtrte()+"-"+mgente00.getCodite():null)
				+ ", mconca00="  + (mconca00!=null?mconca00.getIdccia()+"-"+mconca00.getCodcia():null)
				+ ", mgenus001=" + (mgenus001!=null?mgenus001.getIdtrus()+"-"+mgenus001.getCodius():null)
				+ ", mgenus002=" + (mgenus002!=null?mgenus002.getIdtrus()+"-"+mgenus002.getCodius():null)
				+ ", mgenus003=" + (mgenus003!=null?mgenus003.getIdtrus()+"-"+mgenus003.getCodius():null)
				+ ", mgenus004=" + (mgenus004!=null?mgenus004.getIdtrus()+"-"+mgenus004.getCodius():null)
				+ ", mgenus005=" + (mgenus005!=null?mgenus005.getIdtrus()+"-"+mgenus005.getCodius():null)
				+ ", mgenus006=" + (mgenus006!=null?mgenus006.getIdtrus()+"-"+mgenus006.getCodius():null)
				+ ", mgenus007=" + (mgenus007!=null?mgenus007.getIdtrus()+"-"+mgenus007.getCodius():null)
				+ ", mgenus008=" + (mgenus008!=null?mgenus008.getIdtrus()+"-"+mgenus008.getCodius():null)
				+ ", mgenus009=" + (mgenus009!=null?mgenus009.getIdtrus()+"-"+mgenus009.getCodius():null)
				+ ", tqdfqf00=" + (tqdfqf00!=null?tqdfqf00.getIdcpqf()+"-"+tqdfqf00.getNrcpqf():null)
				+ ", mcotes00=" + (mcotes00!=null?mcotes00.getIdctes()+"-"+mcotes00.getCoctes():null)
				+ ", tqigqg00="  + (tqigqg00!=null?tqigqg00.getIdtqqg()+"-"+tqigqg00.getNroqqg():null) + "]";
	}

	public Tfbcbc00(){}

	public List<Tfbdbd00> getTfbdbd00s() {
		return tfbdbd00s;
	}

	public void setTfbdbd00s(List<Tfbdbd00> tfbdbd00s) {
		this.tfbdbd00s = tfbdbd00s;
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

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public Integer getPzdibc() {
		return pzdibc;
	}

	public void setPzdibc(Integer pzdibc) {
		this.pzdibc = pzdibc;
	}

	public String getDedibc() {
		return dedibc;
	}

	public void setDedibc(String dedibc) {
		this.dedibc = dedibc;
	}

	public String getDepsbc() {
		return depsbc;
	}

	public void setDepsbc(String depsbc) {
		this.depsbc = depsbc;
	}

	public String getDedpbc() {
		return dedpbc;
	}

	public void setDedpbc(String dedpbc) {
		this.dedpbc = dedpbc;
	}

	public String getDecubc() {
		return decubc;
	}

	public void setDecubc(String decubc) {
		this.decubc = decubc;
	}

	public String getDepjbc() {
		return depjbc;
	}

	public void setDepjbc(String depjbc) {
		this.depjbc = depjbc;
	}

	public Integer getIicobc() {
		return iicobc;
	}

	public void setIicobc(Integer iicobc) {
		this.iicobc = iicobc;
	}

	public String getCoicbc() {
		return coicbc;
	}

	public void setCoicbc(String coicbc) {
		this.coicbc = coicbc;
	}

	public Integer getIiivbc() {
		return iiivbc;
	}

	public void setIiivbc(Integer iiivbc) {
		this.iiivbc = iiivbc;
	}

	public String getCoivbc() {
		return coivbc;
	}

	public void setCoivbc(String coivbc) {
		this.coivbc = coivbc;
	}

	public Integer getIrivbc() {
		return irivbc;
	}

	public void setIrivbc(Integer irivbc) {
		this.irivbc = irivbc;
	}

	public String getCoribc() {
		return coribc;
	}

	public void setCoribc(String coribc) {
		this.coribc = coribc;
	}

	public Integer getIrfvbc() {
		return irfvbc;
	}

	public void setIrfvbc(Integer irfvbc) {
		this.irfvbc = irfvbc;
	}

	public String getCorfbc() {
		return corfbc;
	}

	public void setCorfbc(String corfbc) {
		this.corfbc = corfbc;
	}
	
}
