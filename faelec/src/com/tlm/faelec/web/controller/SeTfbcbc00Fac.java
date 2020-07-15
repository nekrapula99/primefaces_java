package com.tlm.faelec.web.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMestad00Service;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMidiom00Service;
import com.tlm.faelec.service.maestros.IMparca00Service;
import com.tlm.faelec.service.maestros.IMparme00Service;
import com.tlm.faelec.service.maestros.IMrafra00Service;
import com.tlm.faelec.service.maestros.IMtiptx00Service;
import com.tlm.faelec.service.maestros.ITucauc00Service;
import com.tlm.faelec.service.trans.ITfacfc00Service;
import com.tlm.faelec.service.trans.ITfadfd00Service;
import com.tlm.faelec.service.trans.ITfbdbd00Service;
import com.tlm.faelec.service.trans.ITfesfu00Service;
import com.tlm.faelec.service.trans.ITqdrfg00Service;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelec.service.trans.ITfbcbc00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.ReportsUtils;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Mparca00;
import com.tlm.faelecEntities.model.entities.Mparme00;
import com.tlm.faelecEntities.model.entities.Mparpd00;
import com.tlm.faelecEntities.model.entities.Mrafra00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;
import com.tlm.faelecEntities.model.entities.ReportEntity;
import com.tlm.faelecEntities.model.entities.Terfed00;
import com.tlm.faelecEntities.model.entities.Terfet00;
import com.tlm.faelecEntities.model.entities.Tfacfc00;
import com.tlm.faelecEntities.model.entities.Tfadfd00;
import com.tlm.faelecEntities.model.entities.Tfbcbc00;
import com.tlm.faelecEntities.model.entities.Tfbdbd00;
import com.tlm.faelecEntities.model.entities.Tfesfu00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;
import com.tlm.faelecEntities.model.entities.Tucauc00;

@Controller
@ManagedBean
@SessionScoped
public class SeTfbcbc00Fac  extends Control implements Serializable{
	
private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{tfbcbc00Service}")
	private ITfbcbc00Service tfbcbc00Service;

	@ManagedProperty(value = "#{tfacfc00Service}")
	private ITfacfc00Service tfacfc00Service;
	
	@ManagedProperty(value = "#{tfadfd00Service}")
	private ITfadfd00Service tfadfd00Service;
	
	@ManagedProperty(value = "#{tfbdbd00Service}")
	private ITfbdbd00Service tfbdbd00Service;
	
	@ManagedProperty(value = "#{tqdrfg00Service}")
	private ITqdrfg00Service tqdrfg00Service;
	
	@ManagedProperty(value = "#{tqdrqr00Service}")
	private ITqdrqr00Service tqdrqr00Service;

	@ManagedProperty(value = "#{mtiptx00Service}")
	private IMtiptx00Service mtiptx00Service;
	
	@ManagedProperty(value = "#{mrafra00Service}")
	private IMrafra00Service mrafra00Service;
	
	@ManagedProperty(value = "#{tucauc00Service}")
	private ITucauc00Service tucauc00Service;
	
	@ManagedProperty(value = "#{tfesfu00Service}")
	private ITfesfu00Service tfesfu00Service;
	
	@ManagedProperty(value = "#{mparme00Service}")
	private IMparme00Service mparme00Service;
	
	@ManagedProperty(value = "#{mgenus00Service}")
	private IMgenus00Service mgenus00Service;
	
	@ManagedProperty(value = "#{midiom00Service}")
	private IMidiom00Service midiom00Service;
	
	@ManagedProperty(value = "#{mparca00Service}")
	private IMparca00Service mparca00Service;
	
	@ManagedProperty(value = "#{mestad00Service}")
	private IMestad00Service mestad00Service;
	
	@ManagedProperty(value="#{seCabecera}")
	private SeCabecera seCabecera;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mgenus00 mgenus00;

	private List<Tqdrqr00> listTqdrqr00Aux;
	private List<Tqdfqf00> listTqdfqf00Aux;
	private List<Tqdfqf00> listTqdfqf00;
	
	private List<Mtiptx00> listMtiptx00;
	private Mtiptx00 mtiptx00;
	
	private List<Tfbcbc00> listTfbcbc00;
	private List<Tfbcbc00> filteredtfbcbc00;
	private List<Tfbcbc00> listTfbcbc00Aux;
	
	private List<Tfbdbd00> listTfbdbd00;

	private Mtiptx00 mtiptx00Cop;
	
	private SeTqdrqr00 seTqdrqr00;
	private SeTqdfqf00 seTqdfqf00;
	
	//private String action;
	
	//Tqdrqr00 Detalle requerimiento
	private Tqdrqr00 tqdrqr00;
	private List<Mdespr00> filteredTqdrqr00;
	private List<Mdespr00> listTqdrqr00Remove;

	//Detalle producto
	private Tqdfqf00 tqdfqf00; 
	
	private String accionTqdrqr;
	
	private Mconca00 mconca00;

	private Double total;
	private Double totalProCuota;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
		
	private Integer fracDec_CantiM;
	private Integer fracDec_PorceM;
	private String paternDec_CantiM;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
		
	private Tfbcbc00 tfbcbc00;
	private Tqdrfg00 tqdrfg00;
	private Mrafra00 mrafra00;
	private Mrafra00 mrafra00Aux;

	private String mensaje;
	private Double cafabd;
	private Double vabrbd;
	private Double totalBruto;
	
	private Double totalImpCon;
	private Double totalIva;
	private Double totalReteIva;
	private Double totalRtf;
	private Double totalValorFactura;
	
	//variables factura
	private Tfacfc00 tfacfc00;
	private Tfacfc00 tfacfc00Clone;
	private Tfesfu00 tfesfu00;
	private Tfadfd00 tfadfd00;
	private List<Tfadfd00> listTfadfd00;
	
	private Terfet00 terfet00;
	private Terfed00 terfed00;
	private Mestad00 mestad00Tfesfu00;
	private Mestad00 mestad00Terfet00;
	private Mestad00 mestad00Terfed00;
	
	//Rango
	private Tucauc00 tucauc00;
	private List<Tucauc00> listTucauc00;
	private List<Mrafra00> listMrafra00;
	private Integer nrfafc;
	private Tucauc00 tucauc00Clone;
	
	private Tfbcbc00 tfbcbc00Aux;
	
	private String CUFEDC;
	private String CUFEVC;
	private String fechaGeneracion;
	private String nitFacturador;
	
	//Variables estado
	private boolean validarFacturaExist;
	private boolean validarEstadoFlag;
	private boolean estadoFacturada;
	private String descEstado;
	private Mestad00 mestad00;
	private List<Tfacfc00> listTtfacfc00;
	private List<Tfesfu00> listTfesfu00;
	private List<Mparme00> listMparme00;
	private String estadoCuota;
	private String estadoInicioDian;
	private String estadoInicioClte;
	private List<Mestad00> listMestad00;
	private boolean flagRango;
	private boolean flagRangoFecha;
	
    

	
	
	@PostConstruct
	public void init() {
		try {
			super.init("TFBCBC00");
			tfbcbc00 = new Tfbcbc00();
			tfacfc00 = new Tfacfc00();
			tucauc00 = new Tucauc00();
			//tfbcbc00.setMtiptx00(getMtiptx00());
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			nitFacturador = super.getSeControlFactura().getMconca00Session().getNitcia();
		
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			fracDec_CantiM=d1.intValue();
			validarEstadoFlag=false;
			validarFacturaExist = false;
			estadoFacturada = false;
			flagRango = false;
			flagRangoFecha = false;
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cargarDatos() {
		Integer cuotaMayor = 0;
		totalBruto = 0.0;
		cafabd = 0.0;
		vabrbd = 0.0;
		
		Tqigqg00 cotizacion = new Tqigqg00();
		listTfbcbc00 = tfbcbc00Service.listTfbcbc00FacByCriteria(new Tfbcbc00(),getSeControlFactura().getCompaniasUsu());
		listTfbdbd00 = tfbdbd00Service.listTfbdbd00ByCriteria(new Tfbdbd00(),getSeControlFactura().getCompaniasUsu());
		for(Tfbcbc00 obj: listTfbcbc00){
			cotizacion=obj.getTqigqg00();
			cuotaMayor = obtenerMayor(cotizacion);
			if(cotizacion.getIdtqqg().equals(obj.getTqigqg00().getIdtqqg())){
				
				obj.setCuotaMayor(cuotaMayor); //Lleno cuota Mayor
			}
			
			//llenar totales brutos
			for(Tfbdbd00 obj2 : listTfbdbd00){
				if(obj2.getTfbcbc00().getIdfcbc() == obj.getIdfcbc()){
					if(obj2.getVabrbd()==0.0){
						totalBruto = totalBruto + 0.0;
					}else{
						totalBruto = totalBruto + obj2.getVabrbd();
					}
				}
				obj.setTotalValorBruto(totalBruto); //Lleno totalBrutos
					
			}
			totalBruto=0.0;
		}
		
	}

	public Integer obtenerMayor(Tqigqg00 cotizacionNew) {
		Integer cuotaMayor = 0;
		tqdrfg00 = new Tqdrfg00();
		
		
		listTfbcbc00Aux = tfbcbc00Service.listTfbcbc00FacByCriteria(new Tfbcbc00(),getSeControlFactura().getCompaniasUsu());

		for(Tfbcbc00 obj: listTfbcbc00Aux){
			if(!obj.equals(null) && !obj.equals("")){
					if(cotizacionNew.getIdtqqg().equals(obj.getTqigqg00().getIdtqqg())){
						if(obj.getTqdfqf00().getNrcpqf()>cuotaMayor){
							cuotaMayor = obj.getTqdfqf00().getNrcpqf();	
						}
					}
			}	

		}
		return cuotaMayor;
	}
	
	public void accionModificar(SelectEvent event) {
		try {
			
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			
			tfacfc00Clone = (Tfacfc00) tfacfc00.clone();
			tucauc00Clone = (Tucauc00) tucauc00.clone();
			mensaje = "Desea Continuar generando la factura para esta Cotizacion: "+tfbcbc00.getTqigqg00().getNroqqg()+" para el cliente "+tfbcbc00.getMgente00().getDctate()+"";
		
			//RequestContext.getCurrentInstance().update(":formDetalle:dTableTqdrqr00");
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public Mtiptx00 obtenerListaMtiptx00s(String notetx){
		mtiptx00 = new Mtiptx00();
	    mtiptx00.setRegtxt(true);
	    mtiptx00.setNotetx(notetx);
	    mtiptx00 = mtiptx00Service.getMtiptx00ByNotetx(notetx);
		return mtiptx00;
	}
	
	public void obtenerRangoValido(Mtiptx00 mtiptx00,Date fechaHoy){
		mrafra00 = new Mrafra00();
		mrafra00.setMtiptx00(mtiptx00);
		listMrafra00 = mrafra00Service.listMrafra00(mrafra00, getSeControlFactura().getCompaniasUsu());
		
		for(Mrafra00 obj: listMrafra00){
			if(fechaHoy.before(obj.getRavdra()) || fechaHoy.after(obj.getRavhra())){
				 mrafra00 = null;
				 //flagRango = true;
				 flagRangoFecha = true;
				
			}else{
				mrafra00 = obj;
			}
			
		}
		if(flagRango){
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('alertDialogFac').show();");
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El número para esta Factura ya no se encuentra en el rango autorizado por la DIAN");
			return;
		}
		
		if(flagRangoFecha){

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('alertDialogFac').show();");
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "La fecha no se encuentra vigente para este rango");
			return;
		}
		

	}

public void validarRangoDian(){
	try{
		flagRango = false;
		
		mtiptx00 = obtenerListaMtiptx00s("Fact_Nal_Est");
		List<Integer> listNrruucTemp = new ArrayList<Integer>();
		Date fechaHoy = new Date();
		Date today = DateUtils.truncate(fechaHoy, Calendar.DATE);

		
		if(mtiptx00!=null){
			obtenerRangoValido(mtiptx00,today);
		}
		
			System.out.println(" mrafra00 1: "+ mrafra00);
		if(mrafra00!=null){
			
				listTucauc00 = tucauc00Service.listTucauc00ByCriteria(mrafra00);
				if(listTucauc00.isEmpty()){
					listNrruucTemp.add(0);
				}else{
					for(Tucauc00 obj: listTucauc00){
						if(obj.getNrruuc() !=null){
							listNrruucTemp.add(obj.getNrruuc());
						}
					}
				}	
		
		System.out.println(" mrafra00 2: "+ mrafra00);
		
			Integer ultimo = 0;
			if(listNrruucTemp.isEmpty()){
				ultimo = 0;
			}else{
				ultimo = Collections.max(listNrruucTemp);
			}
		
			System.out.println("ultimo: "+ultimo);
			nrfafc = ultimo+1;
			System.out.println("nrfafc: "+nrfafc);
			System.out.println(" mrafra00.getRadera(): "+ mrafra00.getRadera());
			System.out.println(" mrafra00.getRahara(): "+ mrafra00.getRahara());
		
			if(nrfafc >= mrafra00.getRadera() && nrfafc <= mrafra00.getRahara()){
				tfacfc00.setNrfafc(String.valueOf(nrfafc));
			
			}else{
				flagRango = true;
			 //UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Numero Factura ya no se encuentra en el rango autorizado por la DIAN");
			// return;
			}
		
		/*if(fechaHoy.before(mrafra00.getRavdra()) || fechaHoy.after(mrafra00.getRavhra())){
			flagRangoFecha = true;
		}else{
			flagRangoFecha = false;
		}*/
		
			if(flagRango){
	
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('alertDialogFac').show();");
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El número para esta Factura ya no se encuentra en el rango autorizado por la DIAN");
				return;
			}
		}
		
		
	
} catch (Exception e) {
	e.printStackTrace();
	UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
}
}
	
public void llenadoDeCamposFactura(){
		try{
			
			tucauc00 = new Tucauc00();
			Date date = new Date();
			
			DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			mtiptx00 = obtenerListaMtiptx00s("Fact_Nal_Est");
			
			//Se valida que haya un consecutivo para esta factura en Rangos MRAFRA00			
			List<Integer> listNrruucTemp = new ArrayList<Integer>();
			Date fechaHoy = new Date();
			Date today = DateUtils.truncate(fechaHoy, Calendar.DATE);

			
			if(mtiptx00!=null){
				//obtenerRangoValido(mtiptx00,today);
			}
			
			
			if(mrafra00!=null){
				listTucauc00 = tucauc00Service.listTucauc00ByCriteria(mrafra00);
				if(listTucauc00.isEmpty()){
					listNrruucTemp.add(0);
				}else{
					for(Tucauc00 obj: listTucauc00){
						if(obj.getNrruuc() !=null){
							listNrruucTemp.add(obj.getNrruuc());
						}
					}
				}
				
				
			
			Integer ultimo = 0;
			if(listNrruucTemp.isEmpty()){
				ultimo = 0;
			}else{
				ultimo = Collections.max(listNrruucTemp);
			}
			
			nrfafc = ultimo+1;
			if(nrfafc >= mrafra00.getRadera() && nrfafc <= mrafra00.getRahara()){
				tfacfc00.setNrfafc(String.valueOf(nrfafc));
				
			}else{
				flagRango = true;
				 //UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Numero Factura ya no se encuentra en el rango autorizado por la DIAN");
				// return;
			}
			}
			
			

			tfacfc00.setTqdfqf00(tfbcbc00.getTqdfqf00());
						
			if(mtiptx00!=null){
				tfacfc00.setMtiptx00(mtiptx00);
			}
			tfacfc00.setTqigqg00(tfbcbc00.getTqigqg00());
			//Pendiente proceso MRAFRA00
			tfacfc00.setPreffc(mrafra00.getPrefra());
			tfacfc00.setTifafc(tfbcbc00.getTifabc());
			tfacfc00.setMgente00(tfbcbc00.getMgente00());
			tfacfc00.setMgenus001(tfbcbc00.getMgenus001());
			tfacfc00.setMofafc(tfbcbc00.getMofabc());
			tfacfc00.setMgenus002(tfbcbc00.getMgenus002());
			tfacfc00.setMgenus003(tfbcbc00.getMgenus003());
			tfacfc00.setMgenus004(tfbcbc00.getMgenus004());
			tfacfc00.setNrocfc(tfbcbc00.getNrocbc());
			tfacfc00.setDescfc(tfbcbc00.getDescbc());
			tfacfc00.setObfafc(tfbcbc00.getObfabc());
			tfacfc00.setFegefc(new Date());   //Preguntar esta fecha que??
			tfacfc00.setHogefc(hourFormat.format(date));
			tfacfc00.setMconca00(tfbcbc00.getMconca00());
			tfacfc00.setPzdifc(tfbcbc00.getPzdibc());
			tfacfc00.setMrafra00(mrafra00);
			tfacfc00.setCorafc(mrafra00.getCorara());
			tfacfc00.setCktufc(mrafra00.getCktura());
			tfacfc00.setRadefc(mrafra00.getRadera());
			tfacfc00.setRahafc(mrafra00.getRahara());
			tfacfc00.setRadnfc(mrafra00.getRaaura());
			tfacfc00.setFerdfc(mrafra00.getRavdra());
			tfacfc00.setFerhfc(mrafra00.getRavhra());
			tfacfc00.setNoclfc(tfbcbc00.getNoclbc());
			tfacfc00.setDiclfc(tfbcbc00.getDiclbc());
			tfacfc00.setMgenus008(tfbcbc00.getMgenus008());
			tfacfc00.setTidifc(tfbcbc00.getTidibc());
			tfacfc00.setDedifc(tfbcbc00.getDedibc());
			tfacfc00.setNrdifc(tfbcbc00.getNrdibc());
			tfacfc00.setMgenus009(tfbcbc00.getMgenus009());
			tfacfc00.setPaclfc(tfbcbc00.getPaclbc());
			tfacfc00.setDepsfc(tfbcbc00.getDepsbc());
			tfacfc00.setMgenus005(tfbcbc00.getMgenus005());
			tfacfc00.setDeclfc(tfbcbc00.getDeclbc());
			tfacfc00.setDedpfc(tfbcbc00.getDedpbc());
			tfacfc00.setMgenus006(tfbcbc00.getMgenus006());
			tfacfc00.setCdclfc(tfbcbc00.getCdclbc());
			
			tfacfc00.setDecufc(tfbcbc00.getDecubc());
			tfacfc00.setMgenus007(tfbcbc00.getMgenus007());
			tfacfc00.setPeclfc(tfbcbc00.getPeclbc());
			tfacfc00.setDepjfc(tfbcbc00.getDepjbc());
			tfacfc00.setMcotes00(tfbcbc00.getMcotes00());
			tfacfc00.setPncofc(tfbcbc00.getPncobc());
			tfacfc00.setSncofc(tfbcbc00.getSncobc());
			tfacfc00.setPacofc(tfbcbc00.getPacobc());
			tfacfc00.setSacofc(tfbcbc00.getSacobc());
			
			tfacfc00.setIicofc(tfbcbc00.getIicobc());
			tfacfc00.setCoicfc(tfbcbc00.getCoicbc());
			tfacfc00.setIiivfc(tfbcbc00.getIiivbc());
			tfacfc00.setCoivfc(tfbcbc00.getCoivbc());
			tfacfc00.setIrivfc(tfbcbc00.getIrivbc());
			tfacfc00.setCorifc(tfbcbc00.getCoribc());
			tfacfc00.setIrfvfc(tfbcbc00.getIrfvbc());
			tfacfc00.setCorffc(tfbcbc00.getCorfbc());
			
			//Llamar metodo para llenar estos totales ¬¬
			llenarSumatoriaDetalles();
			
			generarCufe();
			
			//Cola
			tfacfc00.setRegcfc(true);
			tfacfc00.setFeacfc(new Date());
			tfacfc00.setUsecfc(getSeControlFactura().codusu);
			tfacfc00.setPrgcfc("Factura_Nacional" + getClass().getName());
			tfacfc00.setMaqcfc(getSeControlFactura().ip);			
		
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}
	
public void llenadoDeCamposDetalle(){
		try{
			
			Integer contador=1; 
			
			listTfbdbd00 = tfbdbd00Service.listTfbdbd00Criteria(tfbcbc00);
			
			
			tfacfc00.setTfadfd00s(new ArrayList<Tfadfd00>());
			if (!Utils.isEmptyOrNull(listTfbdbd00)){
			for(Tfbdbd00 obj: listTfbdbd00){
				
				tfadfd00 = new Tfadfd00();
				tfadfd00.setTfacfc00(getTfacfc00());
				
				tfadfd00.setTqdrqr00(obj.getTqdrqr00());
				tfadfd00.setTqdrfg00(obj.getTqdrfg00());
				tfadfd00.setLiorfd(contador);
				contador++;
				tfadfd00.setDerefd(obj.getTqdrqr00().getDetrqr());
				tfadfd00.setDeprfd(obj.getDeprbd());
				tfadfd00.setTqdpqp00(obj.getTqdpqp00());
				tfadfd00.setCoprfd(obj.getCoprbd());
				tfadfd00.setDempfd(obj.getDempbd());
				tfadfd00.setMgenus001(obj.getMgenus001());
				tfadfd00.setTibsfd(obj.getTibsbd());
				tfadfd00.setMgenus002(obj.getMgenus002());
				tfadfd00.setUnmefd(obj.getUnmebd());
				tfadfd00.setVufafd(obj.getVufabd());
				tfadfd00.setMgenus003(obj.getMgenus003());
				tfadfd00.setTarffd(obj.getTarfbd());
				tfadfd00.setDetafd(obj.getDetabd());
				tfadfd00.setPorffd(obj.getPorfbd());
				tfadfd00.setPoivfd(obj.getPoivbd());
				tfadfd00.setPorifd(obj.getPoribd());
				tfadfd00.setPoicfd(obj.getPoicbd());
				tfadfd00.setCafafd(obj.getCafabd());
				tfadfd00.setVabrfd(obj.getVabrbd());
				tfadfd00.setImcofd(obj.getImcobd());
				tfadfd00.setVaivfd(obj.getVaivbd());
				tfadfd00.setReivfd(obj.getReivbd());
				tfadfd00.setRefufd(obj.getRefubd());
				tfadfd00.setTodefd(obj.getTodebd());
				
				//Cola
				tfadfd00.setRegcfd(true);
				tfadfd00.setFeacfd(new Date());
				tfadfd00.setUsecfd(getSeControlFactura().codusu);
				tfadfd00.setPrgcfd("Factura_Nacional_Detalle" + getClass().getName());
				tfadfd00.setMaqcfd(getSeControlFactura().ip);
				
				tfacfc00.getTfadfd00s().add(tfadfd00);
		
			}
		}		
		
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}

public void generarCufe(){
	try{
		
		Date date = tfacfc00.getFegefc();
		//SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
		SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMddHHMMss");
		fechaGeneracion = formateador.format(date);

		
		CUFEDC = tfacfc00.getNrfafc() 		    +";"+
				 fechaGeneracion 	  		    +";"+
				 tfacfc00.getVabrfc()		    +";"+
				 "01"+";"+ tfacfc00.getImcofc() +";"+ //+Valor impuest01
				 "02"+";"+ 0 					+";"+ //+Valor impuest02
				 "03"+";"+ 0					+";"+ //+Valor impuest03
				 tfacfc00.getVaivfc() 			+";"+
				 nitFacturador		 			+";"+
				 tfacfc00.getTidifc() 			+";"+
				 tfacfc00.getTqdfqf00().getMgente00().getNrnite() +";"+
				 tfacfc00.getCktufc();
		
		
		CUFEVC = DigestUtils.shaHex(CUFEDC);		
		tfacfc00.setCufedc(CUFEDC);
		tfacfc00.setCufevc(CUFEVC);
		
				 
				 
		
	
	}catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}


public void llenarSumatoriaDetalles(){
	try{
		
		totalBruto = 0.0;
		totalImpCon = 0.0;
		totalIva = 0.0;
		totalReteIva = 0.0;
		totalRtf = 0.0;
		totalValorFactura = 0.0;
		
		listTfbdbd00 = tfbdbd00Service.listTfbdbd00Criteria(tfbcbc00);		
		
		for(Tfbdbd00 obj: listTfbdbd00){
			
			if(obj.getCafabd()==0.0){
				totalBruto = totalBruto + 0.0;
				totalImpCon = totalImpCon+0.0;
				totalIva = totalIva + 0.0;
				totalReteIva = totalReteIva + 0.0;
				totalRtf = totalRtf + 0.0;
			}else{
				totalBruto = totalBruto + obj.getVabrbd();
				totalImpCon = totalImpCon+obj.getImcobd();
				totalIva = totalIva + obj.getVaivbd();
				totalReteIva = totalReteIva + obj.getReivbd();
				totalRtf = totalRtf + obj.getRefubd();
			}
		}
		tfacfc00.setVabrfc(totalBruto);
		tfacfc00.setImcofc(totalImpCon);
		tfacfc00.setVaivfc(totalIva);
		tfacfc00.setReivfc(totalReteIva);
		tfacfc00.setRefufc(totalRtf);
		
		totalValorFactura = totalBruto + totalImpCon + totalIva - totalReteIva - totalRtf;
		
		tfacfc00.setTovafc(totalValorFactura);
		
	}catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}


public void llenarTucauc00(){
	try{
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		tucauc00 = new Tucauc00();
		tucauc00.setMrafra00(mrafra00);
		tucauc00.setNrruuc(nrfafc);
		tucauc00.setFurauc(new Date());
		tucauc00.setHorauc(hourFormat.format(date));
		tucauc00.setRafeuc(mrafra00.getRafera());
		
		//Cola
		tucauc00.setRegruc(true);
		tucauc00.setUseruc(getSeControlFactura().codusu);
		tucauc00.setPrgruc("Consecutivo_Autorizado" + getClass().getName());
		tucauc00.setFearuc(new Date());
		tucauc00.setMaqruc(getSeControlFactura().ip);	
		
	}catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}


public void actualizarEstadoTfesfu00(){
	try{
		
		 mestad00Tfesfu00 = new Mestad00();
		 mestad00Terfet00 = new Mestad00();
		 mestad00Terfed00 = new Mestad00();

		 
		 listMestad00 = mestad00Service.listMestad00ByCriteria(new Mestad00(), getSeControlFactura().getCompaniasUsu());
		 for(Mestad00 obj : listMestad00){
			 if(obj.getCotres().equalsIgnoreCase(estadoCuota)){
				 mestad00Tfesfu00 = obj;
			 }
			 
			 if(obj.getCotres().equalsIgnoreCase(estadoInicioClte)){
				 mestad00Terfet00 = obj;
			 }
			 if(obj.getCotres().equalsIgnoreCase(estadoInicioDian)){
				 mestad00Terfed00 = obj;
			 }
		 }
				
		tfacfc00.setTfesfu00s(new ArrayList<Tfesfu00>());
		tfesfu00 = new Tfesfu00();
		tfesfu00.setTqigqg00(tfbcbc00.getTqdfqf00().getTqigqg00());
		tfesfu00.setTqdfqf00(tfbcbc00.getTqdfqf00());
		tfesfu00.setTfacfc00(tfacfc00);
		tfesfu00.setMestad00(mestad00Tfesfu00);
		tfesfu00.setFeesfu(new Date());
		
		//COla
		tfesfu00.setRegcfu(true);
		tfesfu00.setUsecfu(getSeControlFactura().codusu);
		tfesfu00.setPrgcfu("Estados_Facturas_Cuotas" + getClass().getName());
		tfesfu00.setFeacfu(new Date());
		tfesfu00.setMaqcfu(getSeControlFactura().ip);
		
		tfacfc00.getTfesfu00s().add(tfesfu00);
		
		
	}catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}


public void actualizarEstadoTerfet00(){
	try{
				
		tfacfc00.setTerfet00s(new ArrayList<Terfet00>());
		terfet00  = new Terfet00();
		terfet00.setTfacfc00(tfacfc00);
		terfet00.setMestad00(mestad00Terfet00);
		terfet00.setFeenet(new Date());
		terfet00.setEmenet("C");
		terfet00.setReenet("C");
		terfet00.setObreet("Observación Cliente");
		terfet00.setEnreet("E");
		//COla
		terfet00.setRegcet(true);
		terfet00.setUsecet(getSeControlFactura().codusu);
		terfet00.setPrgcet("Estados_Envios_Recepcion_Facturas_Clientes" + getClass().getName());
		terfet00.setFeacet(new Date());
		terfet00.setMaqcet(getSeControlFactura().ip);
		
		tfacfc00.getTerfet00s().add(terfet00);
		
		
	}catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}

public void actualizarEstadoTerfed00(){
	try{
				
		tfacfc00.setTerfed00s(new ArrayList<Terfed00>());
		terfed00 = new Terfed00();
		terfed00.setTfacfc00(tfacfc00);
		terfed00.setMestad00(mestad00Terfed00);
		terfed00.setFeened(new Date());
		terfed00.setEmened("D");
		terfed00.setReened("D");
		terfed00.setObreed("Observación Dian");
		terfed00.setEnreed("E");
		
		//COla
		terfed00.setRegced(true);
		terfed00.setUseced(getSeControlFactura().codusu);
		terfed00.setPrgced("Estados_Envios_Recepcion_Facturas_Dian" + getClass().getName());
		terfed00.setFeaced(new Date());
		terfed00.setMaqced(getSeControlFactura().ip);
		
		tfacfc00.getTerfed00s().add(terfed00);
		
		
	}catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}


public Tfbcbc00 consultarObjetoTfbcbc00(Tqdfqf00 tqdfqf00){
	tfbcbc00 = new Tfbcbc00();
	tfbcbc00.setTqdfqf00(tqdfqf00);
	tfbcbc00 = tfbcbc00Service.getTfbcbc00ByTqdfqf00(tfbcbc00);
	return tfbcbc00;
}


public void removeBorrador() {
	try {
		tfbcbc00Aux = new Tfbcbc00();
		tfbcbc00Aux = consultarObjetoTfbcbc00(tfbcbc00.getTqdfqf00());
		if(tfbcbc00Aux!=null){
			
			if(tfbcbc00Aux.getTqdfqf00().getIdcpqf().equals(tfbcbc00.getTqdfqf00().getIdcpqf())){
				getSeControlFactura().setTitulo(titulo);
				getSeControlFactura().setTabla(tabla);
				tfbcbc00Service.removeTfbcbc00(tfbcbc00Aux,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,tfbcbc00.toString(),tfbcbc00.toStringId()):null);
			}
			cargarDatos();
			RequestContext.getCurrentInstance().update("formDetalle");
			RequestContext.getCurrentInstance().update(":formTable");
			
		}else{
		}
		
	} catch (Exception e) {
		if (UtilsJSF.isReferenceConstraintException(e)) {
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"REFERENCE");
		} else {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
}

public void validarEstado(){
	try {
		tfesfu00 = new Tfesfu00();
		mestad00 = new Mestad00();
		validarFacturaExist = false;
		validarEstadoFlag = false;
		listTtfacfc00 = tfacfc00Service.listTfacfc00ByCriteria(new Tfacfc00(), getSeControlFactura().getCompaniasUsu());
		for(Tfacfc00 obj: listTtfacfc00){
			if(obj.getTqdfqf00().getIdcpqf().equals(tfbcbc00.getTqdfqf00().getIdcpqf())){
				
				validarFacturaExist = true;	
			}
			
		}
		if(validarFacturaExist){
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('alertDialogFac').show();");
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Ya Existe una factura para este borrador");
			return;
		}
		
		Date fechaMayor = new Date();
		List<Date> listFechasTfesfu00= new ArrayList<Date>();
		tfesfu00.setTqdfqf00(tfbcbc00.getTqdfqf00());
		listTfesfu00 = tfesfu00Service.listTfesfu00ByCriteria(tfesfu00, getSeControlFactura().getCompaniasUsu());
		listMparme00 = mparme00Service.ListMparmeByCriteria(getSeControlFactura().getCompaniasUsu(),new Mparme00());
		for(Mparme00 obj: listMparme00){
			if(obj.getNopame()!=null && obj.getVapame() != null){
				if(obj.getNopame().equalsIgnoreCase("Estado_Cuota_Facturada")){
					estadoCuota = obj.getVapame();

				}
				if(obj.getNopame().equalsIgnoreCase("Estado_Envio_Inicio_Dian")){
					estadoInicioDian = obj.getVapame();

				}
				if(obj.getNopame().equalsIgnoreCase("Estado_Envio_Inicio_Clte")){
					estadoInicioClte = obj.getVapame();

				}
			}else{
				estadoCuota = "";
			}
		}

		
		for(Tfesfu00 obj: listTfesfu00){
			if(obj.getTqdfqf00().getIdcpqf().equals(tfbcbc00.getTqdfqf00().getIdcpqf())){
				
				if(obj.getMestad00()!=null){
					listFechasTfesfu00.add(obj.getFeacfu());
					fechaMayor = Collections.max(listFechasTfesfu00);
					
					if(obj.getFeacfu().equals(fechaMayor)){
						mestad00 = obj.getMestad00();
						descEstado = mestad00.getCotres();
						if(!descEstado.equalsIgnoreCase(estadoCuota)){
							validarEstadoFlag = true;
						}
						//estadoFacturada = true;
					}
				}	
			}
			
		}
		if(validarEstadoFlag){
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('alertDialogFac').show();");
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Cuota en estado Facturada");
			return;
			
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}
	

	
	
	public void opcionSi(){
		try {
			update=null;	
			
			validarEstado();
			validarRangoDian();
			if(!flagRango && !flagRangoFecha){
				if(!validarEstadoFlag){
					llenadoDeCamposFactura();
					llenadoDeCamposDetalle();
				
				
					if(getAccion().equals(Constantes.ACCION_MODIFICAR))
					{
						actualizarEstadoTfesfu00();
						actualizarEstadoTerfet00();
						actualizarEstadoTerfed00();
							tfacfc00Service.save(tfacfc00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tfacfc00.toString(),tfacfc00.toStringId(),tfacfc00Clone.toString()):null);
							//seTfbcbc00.getTfbcbc00().getTfbdbd00s().add(tfbdbd00);
							removeBorrador();
							llenarTucauc00();
							tucauc00Service.save(tucauc00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tucauc00.toString(),tucauc00.toStringId(),tucauc00Clone.toString()):null);
		
					} else {
						tfbcbc00Service.save(tfbcbc00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tfbcbc00.toString(),tfbcbc00.toStringId(),null):null);
						
					 }
					
					//tfesfu00Service.save(tfesfu00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tfesfu00.toString(),tfesfu00.toStringId(),tfesfu00Clone.toString()):null);
					clearAllFilters2();
					validarEstadoFlag = false;
					flagRango = false;
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('generFacturaDialog').hide();");
					RequestContext.getCurrentInstance().update("formTable");
					
					UtilsJSF.facesLogFactura(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			}
				
				
			}
			
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				//UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	
	public void opcionNo(){
		try {
			update=null;
			RequestContext.getCurrentInstance().update("formTable"); 
			RequestContext.getCurrentInstance().update("formDetalle");
			clearAllFilters2();
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void clearAllFilters2() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent(":formTable:dTable");
			dataTable.reset();
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update(":formTable:dTable");
	}
	
	
	
	public void preRenderView() {
		try {
			//getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialogTfbcbc00(CloseEvent event) {
        try {  
        	
        	update=null;
        	//evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	
	public void evtCloseDialogNek(CloseEvent event) {
        try {  
        	
        	update=null;
        	//evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	public void evtCloseDialogAlert(CloseEvent event) {
        try {  
        	
        	update=null;
        	
			RequestContext.getCurrentInstance().update(":formTable:dTable");
			RequestContext.getCurrentInstance().update(":formDetalle");
			
			DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
					.getViewRoot()
					.findComponent(":formTable:dTable");
				dataTable.reset();
				RequestContext requestContext = RequestContext.getCurrentInstance();
				requestContext.update(":formTable:dTable");
        	
        	/*//evtCloseDialog(event);
        	RequestContext.getCurrentInstance().update(":formDetalle");
        	RequestContext.getCurrentInstance().update(":formDetalle:dTableTfbdbd00");
        	RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle");*/
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	
	/*public void generarPDF(Tfbcbc00 tfbcbc) throws NamingException, IOException {
		try{
			tqdfqf00 = tfbcbc.getTqdfqf00();
			Mparme00 mparme = new Mparme00();
			mparme.setNopame("IdiomaFacNalEst"); //eSTO QUE?'			
			mparme = mparme00Service.retrieve(mparme);
			
			Midiom00 midiom = new Midiom00();
			if(mparme!=null){
				midiom.setCodidi(mparme.getVapame());
			}else{
				midiom.setCodidi("E");
			}
			List<Midiom00> listMidiom = midiom00Service.listMidiom00ByCriteria(midiom, getSeControlFactura().getCompaniasUsu());
			
			if(listMidiom!=null && !listMidiom.isEmpty()){
				midiom = listMidiom.get(0);
			}
			
			Mgenus00 mgenus = new Mgenus00();
			mgenus.setCodius("FNF");//BFN
			Mtipre00 mtipre = new Mtipre00();
			mtipre.setTipore("H1");
			mgenus.setMtipre00(mtipre);
			mgenus = mgenus00Service.findMgenus(mgenus);
			
			Mparca00 mparca = new Mparca00();
			mparca.setMidiom00(midiom);
			mparca.setMgenus001(mgenus);	
			
			List<Mparca00> listMparca = mparca00Service.listMparca00ByCriteria(mparca, getSeControlFactura().getCompaniasUsu());
			Tfacfc00 tfacfc = getTfacfc00();
			tfacfc.setTqdfqf00(tqdfqf00);
			tfacfc = tfacfc00Service.getTfacfc00ByTqdfqf00(tfacfc);
			
			Tfadfd00 tfadfd = new Tfadfd00(); 
			tfadfd.setTfacfc00(tfacfc);
			tfadfd.setRegcfd(true);
			List<Tfadfd00> listTfadf = tfadfd00Service.listTfadfd00ByTfacfc00(tfadfd);
			
			int rest = listTfadf.size()%30;
			for (int i=0;i<30-rest;i++){
				listTfadf.add(new Tfadfd00());
			}
			Mconca00 mconca = tqdfqf00.getMgente00().getMconca00();
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletResponse response = (HttpServletResponse)externalContext.getResponse();
			HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			List<Mparpd00> listMparpd = new ArrayList<Mparpd00>();
			if(listMparca!=null && !listMparca.isEmpty()){
				mparca = listMparca.get(0);
				listMparpd = listMparca.get(0).getMparpd00s();
			}
			String fileName = String.valueOf(tfacfc.getIdfcfc());
			System.out.println("fileName1: "+fileName);
			
			if(mparca.getObsec5()!=null && !mparca.getObsec5().equals("")){
				fileName = getFileName(mparca.getObsec5(),tfacfc);
			}else{
				fileName = fileName.length()>5?fileName.substring(fileName.length()-5,fileName.length()):fileName;
			}
			System.out.println("fileNam2: "+fileName);
			HashMap<String, Mparpd00> hmMparpd = getHashMapMparpd(listMparpd);
			
			data.put("listTfadf",listTfadf); //Cambiar este parametro!!!
			if(hmMparpd.get("CAIH01").getLogopd()!=null){
				InputStream in = new ByteArrayInputStream(hmMparpd.get("CAIH01").getLogopd());
				BufferedImage logo = ImageIO.read(in);
				data.put("CAIH01", logo);
			}else{
				data.put("CAIH01", null);
			}
			data.put("CADH02", mconca.getNomcia());
			data.put("CATH03", hmMparpd.get("CATH03").getDsrapd());
			data.put("CADH04", tfacfc.getIdfcfc());
			data.put("CATH05", hmMparpd.get("CATH05").getDsrapd());
			data.put("CADH06", mconca.getNitcia());
			data.put("CADH07", mconca.getDircia());
			data.put("CATH08", hmMparpd.get("CATH08").getDsrapd());
			data.put("CADH09", mconca.getTelcia());
			data.put("CADH10", mconca.getMaicia());
			data.put("CADH11", mconca.getMgenus002().getDcttus());
			data.put("CADH12", mconca.getMgenus003().getDcttus());
			data.put("CADH22", tfacfc.getNoclfc());
			data.put("CATH23", tfacfc.getDedifc());
			data.put("CADH24", tfacfc.getNrdifc());
			data.put("CADH25", tfacfc.getDiclfc());
			data.put("CADH26", tfacfc.getDecufc()+", "+tfacfc.getDedpfc());
			data.put("CATH27", hmMparpd.get("CATH27").getDsrapd());
			data.put("CADH28", tfacfc.getPncofc()+" "+tfacfc.getSncofc()+" "+tfacfc.getPacofc());
			data.put("CATH40", hmMparpd.get("CATH40").getDsrapd());
			data.put("CATH41D", hmMparpd.get("CATH41").getDsrapd().split(" ")[0]);
			data.put("CATH41M", hmMparpd.get("CATH41").getDsrapd().split(" ")[1]);
			data.put("CATH41Y", hmMparpd.get("CATH41").getDsrapd().split(" ")[2]);
			String formato = "dd MM yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			data.put("CADH42D", sdf.format(tfacfc.getFegefc()).split(" ")[0]);
			data.put("CADH42M", sdf.format(tfacfc.getFegefc()).split(" ")[1]);
			data.put("CADH42Y", sdf.format(tfacfc.getFegefc()).split(" ")[2]);
			data.put("CATH43", hmMparpd.get("CATH43").getDsrapd());
			data.put("CADH44", tfacfc.getNrocfc());
			data.put("CATH45", hmMparpd.get("CATH45").getDsrapd());
			data.put("CATH46", hmMparpd.get("CATH46").getDsrapd());
			data.put("CATH47D", hmMparpd.get("CATH47").getDsrapd().split(" ")[0]);
			data.put("CATH47M", hmMparpd.get("CATH47").getDsrapd().split(" ")[1]);
			data.put("CATH47Y", hmMparpd.get("CATH47").getDsrapd().split(" ")[2]);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(tfacfc.getFegefc());
			cal.add(Calendar.DATE, tfacfc.getPzdifc());
			data.put("CADH48D", sdf.format(cal.getTime()).split(" ")[0]);
			data.put("CADH48M", sdf.format(cal.getTime()).split(" ")[1]);
			data.put("CADH48Y", sdf.format(cal.getTime()).split(" ")[2]);
			data.put("CATH49", hmMparpd.get("CATH49").getDsrapd());
			data.put("CADH50", sdf.format(tfacfc.getFegefc()));
			data.put("CADH51", hmMparpd.get("CADH51").getDsrapd());
			data.put("CATH52", hmMparpd.get("CATH52").getDsrapd());
			data.put("CADH53", tfacfc.getPzdifc());
			data.put("CATH54", hmMparpd.get("CATH54").getDsrapd());
			data.put("CADH55", tfacfc.getMofafc());
			data.put("CADH56", null);
			
			ReportEntity reportEntity = new ReportEntity();
			reportEntity.setData(data);
			reportEntity.setMparpd(hmMparpd);
			parameters.put("fileName", fileName.replace(" ", "\b"));
			parameters.put("reportEntity", reportEntity);
			parameters.put("reportName", "facturaBorrador");
			parameters.put("reports","reports/jasper/");
			parameters.put("subreport","facturaBorradorDetails");
			parameters.put("context", request.getServletContext().getRealPath(""));
			parameters.put("SUBREPORT_DIR",request.getServletContext().getRealPath("")+"reports/jasper/");
			ReportsUtils.getInstance().getBorradorFactura(response, parameters);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private HashMap<String, Mparpd00> getHashMapMparpd(List<Mparpd00> listMparpd){
		HashMap<String, Mparpd00> hmMparpd = new HashMap<String, Mparpd00>();
		for(Mparpd00 mparpd:listMparpd){
			hmMparpd.put(mparpd.getCodcpd(), mparpd);
		}
		return hmMparpd;
	}
	
	private String getFileName(String fileName,Tfacfc00 tfacfc){
		String[] listParam = fileName.split("@");
		
		int length = Integer.parseInt(listParam[1].substring(listParam[1].indexOf("(")+1,listParam[1].indexOf(")")));
		fileName = fileName.replace("@FNF("+length+")", String.valueOf(tfacfc.getIdfcfc()).length()>=length?String.valueOf(tfacfc.getIdfcfc()).substring(String.valueOf(tfacfc.getIdfcfc()).length()-length):String.valueOf(tfacfc.getIdfcfc()));
		
		return fileName;
	}
*/
	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}


	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public Object getObjSeCall() {
		return objSeCall;
	}

	public void setObjSeCall(Object objSeCall) {
		this.objSeCall = objSeCall;
	}

	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}

	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}

	public Integer getFracDec_PorceM() {
		return fracDec_PorceM;
	}

	public void setFracDec_PorceM(Integer fracDec_PorceM) {
		this.fracDec_PorceM = fracDec_PorceM;
	}

	public Map<String, String> getIdiomasHm() {
		return idiomasHm;
	}

	public void setIdiomasHm(Map<String, String> idiomasHm) {
		this.idiomasHm = idiomasHm;
	}

	public Map<String, String> getIdiomasAyuHm() {
		return idiomasAyuHm;
	}

	public void setIdiomasAyuHm(Map<String, String> idiomasAyuHm) {
		this.idiomasAyuHm = idiomasAyuHm;
	}

	public Map<String, String> getPermisos() {
		return permisos;
	}

	public void setPermisos(Map<String, String> permisos) {
		this.permisos = permisos;
	}

	public HashMap<String, String> getIdiomasTituloHm() {
		return idiomasTituloHm;
	}

	public void setIdiomasTituloHm(HashMap<String, String> idiomasTituloHm) {
		this.idiomasTituloHm = idiomasTituloHm;
	}

	public HashMap<String, Mcampo00> getPermisoCampos() {
		return permisoCampos;
	}

	public void setPermisoCampos(HashMap<String, Mcampo00> permisoCampos) {
		this.permisoCampos = permisoCampos;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
	public SeCabecera getSeCabecera() {
		return seCabecera;
	}
	public void setSeCabecera(SeCabecera seCabecera) {
		this.seCabecera = seCabecera;
	}
	


	public String getPaternDec_CantiM() {
		return paternDec_CantiM;
	}

	public void setPaternDec_CantiM(String paternDec_CantiM) {
		this.paternDec_CantiM = paternDec_CantiM;
	}
	
	public Mgenus00 getMgenus00() {
		return mgenus00;
	}

	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}

	public List<Mtiptx00> getListMtiptx00() {
		return listMtiptx00;
	}

	public void setListMtiptx00(List<Mtiptx00> listMtiptx00) {
		this.listMtiptx00 = listMtiptx00;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	public List<Mdespr00> getFilteredTqdrqr00() {
		return filteredTqdrqr00;
	}

	public void setFilteredTqdrqr00(List<Mdespr00> filteredTqdrqr00) {
		this.filteredTqdrqr00 = filteredTqdrqr00;
	}

	public List<Mdespr00> getListTqdrqr00Remove() {
		return listTqdrqr00Remove;
	}

	public void setListTqdrqr00Remove(List<Mdespr00> listTqdrqr00Remove) {
		this.listTqdrqr00Remove = listTqdrqr00Remove;
	}

	public String getAccionTqdrqr() {
		return accionTqdrqr;
	}

	public void setAccionTqdrqr(String accionTqdrqr) {
		this.accionTqdrqr = accionTqdrqr;
	}

	public Mtiptx00 getMtiptx00Cop() {
		return mtiptx00Cop;
	}

	public void setMtiptx00Cop(Mtiptx00 mtiptx00Cop) {
		this.mtiptx00Cop = mtiptx00Cop;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public Tucauc00 getTucauc00Clone() {
		return tucauc00Clone;
	}

	public void setTucauc00Clone(Tucauc00 tucauc00Clone) {
		this.tucauc00Clone = tucauc00Clone;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public ITqdrqr00Service getTqdrqr00Service() {
		return tqdrqr00Service;
	}

	public void setTqdrqr00Service(ITqdrqr00Service tqdrqr00Service) {
		this.tqdrqr00Service = tqdrqr00Service;
	}

	public List<Tqdrqr00> getListTqdrqr00Aux() {
		return listTqdrqr00Aux;
	}

	public void setListTqdrqr00Aux(List<Tqdrqr00> listTqdrqr00Aux) {
		this.listTqdrqr00Aux = listTqdrqr00Aux;
	}

	public SeTqdrqr00 getSeTqdrqr00() {
		return seTqdrqr00;
	}

	public void setSeTqdrqr00(SeTqdrqr00 seTqdrqr00) {
		this.seTqdrqr00 = seTqdrqr00;
	}



	public SeTqdfqf00 getSeTqdfqf00() {
		return seTqdfqf00;
	}

	public void setSeTqdfqf00(SeTqdfqf00 seTqdfqf00) {
		this.seTqdfqf00 = seTqdfqf00;
	}
	
	public List<Tqdfqf00> getListTqdfqf00Aux() {
		return listTqdfqf00Aux;
	}

	public void setListTqdfqf00Aux(List<Tqdfqf00> listTqdfqf00Aux) {
		this.listTqdfqf00Aux = listTqdfqf00Aux;
	}

	public Tqdrqr00 getTqdrqr00() {
		return tqdrqr00;
	}

	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}


	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalProCuota() {
		return totalProCuota;
	}

	public void setTotalProCuota(Double totalProCuota) {
		this.totalProCuota = totalProCuota;
	}

	public List<Tqdfqf00> getListTqdfqf00() {
		return listTqdfqf00;
	}

	public void setListTqdfqf00(List<Tqdfqf00> listTqdfqf00) {
		this.listTqdfqf00 = listTqdfqf00;
	}

	public Tfbcbc00 getTfbcbc00() {
		return tfbcbc00;
	}

	public void setTfbcbc00(Tfbcbc00 tfbcbc00) {
		this.tfbcbc00 = tfbcbc00;
	}

	public ITfbcbc00Service getTfbcbc00Service() {
		return tfbcbc00Service;
	}

	public void setTfbcbc00Service(ITfbcbc00Service tfbcbc00Service) {
		this.tfbcbc00Service = tfbcbc00Service;
	}

	public List<Tfbcbc00> getListTfbcbc00() {
		return listTfbcbc00;
	}

	public void setListTfbcbc00(List<Tfbcbc00> listTfbcbc00) {
		this.listTfbcbc00 = listTfbcbc00;
	}

	public List<Tfbcbc00> getFilteredtfbcbc00() {
		return filteredtfbcbc00;
	}

	public void setFilteredtfbcbc00(List<Tfbcbc00> filteredtfbcbc00) {
		this.filteredtfbcbc00 = filteredtfbcbc00;
	}

	public ITqdrfg00Service getTqdrfg00Service() {
		return tqdrfg00Service;
	}

	public void setTqdrfg00Service(ITqdrfg00Service tqdrfg00Service) {
		this.tqdrfg00Service = tqdrfg00Service;
	}

	public Tqdrfg00 getTqdrfg00() {
		return tqdrfg00;
	}

	public void setTqdrfg00(Tqdrfg00 tqdrfg00) {
		this.tqdrfg00 = tqdrfg00;
	}

	public List<Tfbdbd00> getListTfbdbd00() {
		return listTfbdbd00;
	}

	public void setListTfbdbd00(List<Tfbdbd00> listTfbdbd00) {
		this.listTfbdbd00 = listTfbdbd00;
	}

	public ITfbdbd00Service getTfbdbd00Service() {
		return tfbdbd00Service;
	}

	public void setTfbdbd00Service(ITfbdbd00Service tfbdbd00Service) {
		this.tfbdbd00Service = tfbdbd00Service;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Tfacfc00 Tfadfd00() {
		return tfacfc00;
	}

	public void setTfacfc00(Tfacfc00 tfacfc00) {
		this.tfacfc00 = tfacfc00;
	}

	public Tfesfu00 getTfesfu00() {
		return tfesfu00;
	}

	public void setTfesfu00(Tfesfu00 tfesfu00) {
		this.tfesfu00 = tfesfu00;
	}

	public Tfadfd00 getTfadfd00() {
		return tfadfd00;
	}

	public void setTfadfd00(Tfadfd00 tfadfd00) {
		this.tfadfd00 = tfadfd00;
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

	public Double getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto(Double totalBruto) {
		this.totalBruto = totalBruto;
	}

	public Mrafra00 getMrafra00() {
		return mrafra00;
	}

	public void setMrafra00(Mrafra00 mrafra00) {
		this.mrafra00 = mrafra00;
	}

	public Mrafra00 getMrafra00Aux() {
		return mrafra00Aux;
	}

	public void setMrafra00Aux(Mrafra00 mrafra00Aux) {
		this.mrafra00Aux = mrafra00Aux;
	}
	
	public IMtiptx00Service getMtiptx00Service() {
		return mtiptx00Service;
	}

	public void setMtiptx00Service(IMtiptx00Service mtiptx00Service) {
		this.mtiptx00Service = mtiptx00Service;
	}

	public IMrafra00Service getMrafra00Service() {
		return mrafra00Service;
	}

	public void setMrafra00Service(IMrafra00Service mrafra00Service) {
		this.mrafra00Service = mrafra00Service;
	}

	public Tfacfc00 getTfacfc00() {
		return tfacfc00;
	}

	public List<Tfadfd00> getListTfadfd00() {
		return listTfadfd00;
	}

	public void setListTfadfd00(List<Tfadfd00> listTfadfd00) {
		this.listTfadfd00 = listTfadfd00;
	}

	public ITfacfc00Service getTfacfc00Service() {
		return tfacfc00Service;
	}

	public void setTfacfc00Service(ITfacfc00Service tfacfc00Service) {
		this.tfacfc00Service = tfacfc00Service;
	}

	public Tfacfc00 getTfacfc00Clone() {
		return tfacfc00Clone;
	}

	public void setTfacfc00Clone(Tfacfc00 tfacfc00Clone) {
		this.tfacfc00Clone = tfacfc00Clone;
	}

	public Tucauc00 getTucauc00() {
		return tucauc00;
	}

	public void setTucauc00(Tucauc00 tucauc00) {
		this.tucauc00 = tucauc00;
	}

	public List<Tucauc00> getListTucauc00() {
		return listTucauc00;
	}

	public void setListTucauc00(List<Tucauc00> listTucauc00) {
		this.listTucauc00 = listTucauc00;
	}

	public Integer getNrfafc() {
		return nrfafc;
	}

	public void setNrfafc(Integer nrfafc) {
		this.nrfafc = nrfafc;
	}

	public List<Mrafra00> getListMrafra00() {
		return listMrafra00;
	}

	public void setListMrafra00(List<Mrafra00> listMrafra00) {
		this.listMrafra00 = listMrafra00;
	}

	public ITucauc00Service getTucauc00Service() {
		return tucauc00Service;
	}

	public void setTucauc00Service(ITucauc00Service tucauc00Service) {
		this.tucauc00Service = tucauc00Service;
	}

	public Tfbcbc00 getTfbcbc00Aux() {
		return tfbcbc00Aux;
	}

	public void setTfbcbc00Aux(Tfbcbc00 tfbcbc00Aux) {
		this.tfbcbc00Aux = tfbcbc00Aux;
	}

	public Double getTotalValorFactura() {
		return totalValorFactura;
	}

	public void setTotalValorFactura(Double totalValorFactura) {
		this.totalValorFactura = totalValorFactura;
	}

	public Double getTotalImpCon() {
		return totalImpCon;
	}

	public void setTotalImpCon(Double totalImpCon) {
		this.totalImpCon = totalImpCon;
	}

	public Double getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(Double totalIva) {
		this.totalIva = totalIva;
	}

	public Double getTotalReteIva() {
		return totalReteIva;
	}

	public void setTotalReteIva(Double totalReteIva) {
		this.totalReteIva = totalReteIva;
	}

	public Double getTotalRtf() {
		return totalRtf;
	}

	public void setTotalRtf(Double totalRtf) {
		this.totalRtf = totalRtf;
	}

	public String getCUFEDC() {
		return CUFEDC;
	}

	public void setCUFEDC(String cUFEDC) {
		CUFEDC = cUFEDC;
	}

	public String getCUFEVC() {
		return CUFEVC;
	}

	public void setCUFEVC(String cUFEVC) {
		CUFEVC = cUFEVC;
	}

	public String getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public String getNitFacturador() {
		return nitFacturador;
	}

	public void setNitFacturador(String nitFacturador) {
		this.nitFacturador = nitFacturador;
	}

	public List<Tfbcbc00> getListTfbcbc00Aux() {
		return listTfbcbc00Aux;
	}

	public void setListTfbcbc00Aux(List<Tfbcbc00> listTfbcbc00Aux) {
		this.listTfbcbc00Aux = listTfbcbc00Aux;
	}

	public boolean isValidarEstadoFlag() {
		return validarEstadoFlag;
	}

	public void setValidarEstadoFlag(boolean validarEstadoFlag) {
		this.validarEstadoFlag = validarEstadoFlag;
	}

	public List<Tfacfc00> getListTtfacfc00() {
		return listTtfacfc00;
	}

	public void setListTtfacfc00(List<Tfacfc00> listTtfacfc00) {
		this.listTtfacfc00 = listTtfacfc00;
	}

	public List<Tfesfu00> getListTfesfu00() {
		return listTfesfu00;
	}

	public void setListTfesfu00(List<Tfesfu00> listTfesfu00) {
		this.listTfesfu00 = listTfesfu00;
	}

	public ITfesfu00Service getTfesfu00Service() {
		return tfesfu00Service;
	}

	public void setTfesfu00Service(ITfesfu00Service tfesfu00Service) {
		this.tfesfu00Service = tfesfu00Service;
	}

	public boolean isValidarFacturaExist() {
		return validarFacturaExist;
	}

	public void setValidarFacturaExist(boolean validarFacturaExist) {
		this.validarFacturaExist = validarFacturaExist;
	}

	public boolean isEstadoFacturada() {
		return estadoFacturada;
	}

	public void setEstadoFacturada(boolean estadoFacturada) {
		this.estadoFacturada = estadoFacturada;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public IMparme00Service getMparme00Service() {
		return mparme00Service;
	}

	public void setMparme00Service(IMparme00Service mparme00Service) {
		this.mparme00Service = mparme00Service;
	}

	public IMidiom00Service getMidiom00Service() {
		return midiom00Service;
	}

	public void setMidiom00Service(IMidiom00Service midiom00Service) {
		this.midiom00Service = midiom00Service;
	}

	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}

	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
	}

	public IMparca00Service getMparca00Service() {
		return mparca00Service;
	}

	public void setMparca00Service(IMparca00Service mparca00Service) {
		this.mparca00Service = mparca00Service;
	}

	public ITfadfd00Service getTfadfd00Service() {
		return tfadfd00Service;
	}

	public void setTfadfd00Service(ITfadfd00Service tfadfd00Service) {
		this.tfadfd00Service = tfadfd00Service;
	}

	public List<Mparme00> getListMparme00() {
		return listMparme00;
	}

	public void setListMparme00(List<Mparme00> listMparme00) {
		this.listMparme00 = listMparme00;
	}

	public String getEstadoCuota() {
		return estadoCuota;
	}

	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}

	public IMestad00Service getMestad00Service() {
		return mestad00Service;
	}

	public void setMestad00Service(IMestad00Service mestad00Service) {
		this.mestad00Service = mestad00Service;
	}

	public List<Mestad00> getListMestad00() {
		return listMestad00;
	}

	public void setListMestad00(List<Mestad00> listMestad00) {
		this.listMestad00 = listMestad00;
	}

	public boolean isFlagRango() {
		return flagRango;
	}

	public void setFlagRango(boolean flagRango) {
		this.flagRango = flagRango;
	}

	public Terfet00 getTerfet00() {
		return terfet00;
	}

	public void setTerfet00(Terfet00 terfet00) {
		this.terfet00 = terfet00;
	}

	public String getEstadoInicioDian() {
		return estadoInicioDian;
	}

	public void setEstadoInicioDian(String estadoInicioDian) {
		this.estadoInicioDian = estadoInicioDian;
	}

	public String getEstadoInicioClte() {
		return estadoInicioClte;
	}

	public void setEstadoInicioClte(String estadoInicioClte) {
		this.estadoInicioClte = estadoInicioClte;
	}

	public Terfed00 getTerfed00() {
		return terfed00;
	}

	public void setTerfed00(Terfed00 terfed00) {
		this.terfed00 = terfed00;
	}

	public Mestad00 getMestad00Tfesfu00() {
		return mestad00Tfesfu00;
	}

	public void setMestad00Tfesfu00(Mestad00 mestad00Tfesfu00) {
		this.mestad00Tfesfu00 = mestad00Tfesfu00;
	}

	public Mestad00 getMestad00Terfet00() {
		return mestad00Terfet00;
	}

	public void setMestad00Terfet00(Mestad00 mestad00Terfet00) {
		this.mestad00Terfet00 = mestad00Terfet00;
	}

	public Mestad00 getMestad00Terfed00() {
		return mestad00Terfed00;
	}

	public void setMestad00Terfed00(Mestad00 mestad00Terfed00) {
		this.mestad00Terfed00 = mestad00Terfed00;
	}
	
}
