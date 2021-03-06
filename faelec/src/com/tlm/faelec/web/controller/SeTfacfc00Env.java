package com.tlm.faelec.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import org.springframework.stereotype.Controller;


import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMidiom00Service;
import com.tlm.faelec.service.maestros.IMparca00Service;
import com.tlm.faelec.service.maestros.IMparme00Service;
import com.tlm.faelec.service.trans.ITerfed00Service;
import com.tlm.faelec.service.trans.ITerfet00Service;
import com.tlm.faelec.service.trans.ITfacfc00Service;
import com.tlm.faelec.service.trans.ITfadfd00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.ReportsUtils;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Mparca00;
import com.tlm.faelecEntities.model.entities.Mparme00;
import com.tlm.faelecEntities.model.entities.Mparpd00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;
import com.tlm.faelecEntities.model.entities.ReportEntity;
import com.tlm.faelecEntities.model.entities.Terfed00;
import com.tlm.faelecEntities.model.entities.Terfet00;
import com.tlm.faelecEntities.model.entities.Tfacfc00;
import com.tlm.faelecEntities.model.entities.Tfadfd00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

//import java.io.IOException;
//import java.nio.file.Path;

@Controller
@ManagedBean
@SessionScoped
public class SeTfacfc00Env  extends Control implements Serializable{
	
private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{tfacfc00Service}")
	private ITfacfc00Service tfacfc00Service;
	
	@ManagedProperty(value = "#{tfadfd00Service}")
	private ITfadfd00Service tfadfd00Service;

	@ManagedProperty(value = "#{mparme00Service}")
	private IMparme00Service mparme00Service;
	
	@ManagedProperty(value = "#{mgenus00Service}")
	private IMgenus00Service mgenus00Service;
	
	@ManagedProperty(value = "#{midiom00Service}")
	private IMidiom00Service midiom00Service;
	
	@ManagedProperty(value = "#{mparca00Service}")
	private IMparca00Service mparca00Service;
	
	@ManagedProperty(value = "#{terfet00Service}")
	private ITerfet00Service terfet00Service; 
	
	@ManagedProperty(value = "#{terfed00Service}")
	private ITerfed00Service terfed00Service;

	
	@ManagedProperty(value="#{seCabecera}")
	private SeCabecera seCabecera;
	
	private SeTfacfc00Env seTfacfc00;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mgenus00 mgenus00;

	private List<Tqdfqf00> listTqdfqf00Aux;
	private List<Tqdfqf00> listTqdfqf00;
	
	private Mtiptx00 mtiptx00;
	
	private List<Tfacfc00> listTfacfc00;
	private List<Tfacfc00> filteredtfacfc00;
	private List<Tfacfc00> listTfacfc00Aux;
	private List<Mparme00> listMparme00;
	
	private Mtiptx00 mtiptx00Cop;

	//Detalle producto
	private Tqdfqf00 tqdfqf00; 
		
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
		
	private Tfacfc00 tfacfc00;
	private Tqdrfg00 tqdrfg00;

	private String mensaje;
	private Double cafabd;
	private Double vabrbd;
	private Double totalBruto;
	
	//variables factura
	private Tfacfc00 tfacfc00Clone;
	private Tfadfd00 tfadfd00;
	private List<Tfadfd00> listTfadfd00;
	
	private Tfacfc00 tfacfc00Aux;
	
	//Listas Estados
	private List<Terfet00> listTerfet00;
	private List<Terfet00> filteredTerfet00;
	private List<Terfed00> listTerfed00;
	private List<Terfed00> filteredTerfed00;
	private Terfet00 terfet00;
	private Terfed00 terfed00;
	private boolean flagOpen;
	
	private String getHorasAmarilla;
	private String getHorasRoja;
	
	
	@PostConstruct
	public void init() {
		try {
			super.init("Tfacfc00");
			tfacfc00 = new Tfacfc00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
		
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			fracDec_CantiM=d1.intValue();
			flagOpen = false;
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cargarDatos() throws ParseException {
		int cont = 1;
		 int aaales = 0;
		 int mmales = 0;
		 int ddales = 0;
		 int hhales = 0;
		 
		 Date fechaHoy = new Date();
		 
			listMparme00 = mparme00Service.ListMparmeByCriteria(getSeControlFactura().getCompaniasUsu(),new Mparme00());
			for(Mparme00 obj: listMparme00){
				if(obj.getNopame()!=null && obj.getVapame() != null){
					if(obj.getNopame().equalsIgnoreCase("Horas_Limite_Para_Alarma_Amarilla")){
						getHorasAmarilla = obj.getVapame();

					}
					if(obj.getNopame().equalsIgnoreCase("Horas_Limite_Para_Alarma_Rojo")){
						getHorasRoja = obj.getVapame();
					}
					
				}
			}
		 
		listTfacfc00 = tfacfc00Service.listTfacfc00ByCriteria(new Tfacfc00(),getSeControlFactura().getCompaniasUsu());
		listTfadfd00 = tfadfd00Service.listTfadfd00ByCriteria(new Tfadfd00(),getSeControlFactura().getCompaniasUsu());
		
		for(Tfacfc00 obj: listTfacfc00){
						
			if(!obj.getTerfed00s().isEmpty()){
				
				if((obj.getTerfedAux().getMestad00().getAaales().equalsIgnoreCase("") || obj.getTerfedAux().getMestad00().getAaales().isEmpty()) 
					|| (obj.getTerfedAux().getMestad00().getMmales().equalsIgnoreCase("") || obj.getTerfedAux().getMestad00().getMmales().isEmpty())
					|| (obj.getTerfedAux().getMestad00().getDdales().equalsIgnoreCase("") || obj.getTerfedAux().getMestad00().getDdales().isEmpty())
					|| (obj.getTerfedAux().getMestad00().getHhales().equalsIgnoreCase("") || obj.getTerfedAux().getMestad00().getHhales().isEmpty())
					){
					
					aaales = 0;
					mmales = 0;
					ddales = 0;
					hhales = 0;
					
				}else{
					aaales = Integer.parseInt(obj.getTerfedAux().getMestad00().getAaales());
					mmales = Integer.parseInt(obj.getTerfedAux().getMestad00().getMmales());
					ddales = Integer.parseInt(obj.getTerfedAux().getMestad00().getDdales());
					hhales = Integer.parseInt(obj.getTerfedAux().getMestad00().getHhales());
					
				}				
					
				if(aaales >0 || mmales >0 || ddales > 0 || hhales >0){

					if(obj.getTerfedAux().getMestad00().getBfeaes().equalsIgnoreCase("D")){
								
						obj.setFecbas(obj.getFegefc());	
					}
					if(obj.getTerfedAux().getMestad00().getBfeaes().equalsIgnoreCase("E")){
								
						obj.setFecbas(obj.getTerfedAux().getFeened());
					}
							
				}
				Calendar calendar = Calendar.getInstance();
				if(hhales>0){
							 
					calendar.setTime(obj.getFecbas()); 
					calendar.add(Calendar.HOUR, hhales);
				 }
				 if(ddales > 0){
					 calendar.setTime(obj.getFecbas()); 
					 calendar.add(Calendar.DAY_OF_YEAR, ddales);
				 }
				 if(mmales > 0){
					 calendar.setTime(obj.getFecbas()); 
					 calendar.add(Calendar.MONTH, mmales);
				 }
				 if(aaales > 0){
					 calendar.setTime(obj.getFecbas()); 
					 calendar.add(Calendar.YEAR, aaales);
				 }
				 
				 obj.setFecbas(calendar.getTime());
				 
				 
				 
			 	String formato = "yyyy-MM-dd-HH-mm-ss";
				SimpleDateFormat sdf = new SimpleDateFormat(formato);
				
				String fechaFecbas = sdf.format(obj.getFecbas());
				String fechadeHoy = sdf.format(fechaHoy);
				

				Date fechaFecbasDate = sdf.parse(fechaFecbas);
				Date fechadeHoyDate = sdf.parse(fechadeHoy);
				
				
				Integer horas=(int) ((fechaFecbasDate.getTime()-fechadeHoyDate.getTime())/3600000);
				
				int dias = Math.round(horas/24);
				int horasAux = horas%24;
				
				String alarma = ""+dias+" D�as "+horasAux+" Horas";

				obj.setAlarma(alarma);
				
				obj.setFlagAmarillo(false);
				obj.setFlagRojo(false);
				
				
				if((horas)< Integer.parseInt(getHorasAmarilla)){
					obj.setFlagAmarillo(true);

				}else{
					obj.setFlagAmarillo(false);
				}
				
				if((horas)< Integer.parseInt(getHorasRoja)){
					obj.setFlagRojo(true);
				}else{
					obj.setFlagRojo(false);
				}
				
				if(obj.isFlagRojo()){
					obj.setFlagAmarillo(false);
				}
				
				
				
				/*System.out.println("Estos son los dias: "+dias);
				
				String[] afechaFecbas = fechaFecbas.split("-");
				Integer anosFecbas = Integer.parseInt(afechaFecbas[0]);
				Integer mesesFecbas = Integer.parseInt(afechaFecbas[1]);
				Integer diasFecbas = Integer.parseInt(afechaFecbas[2]);
				Integer horasFecbas = Integer.parseInt(afechaFecbas[3]);
				Integer minutosFecbas = Integer.parseInt(afechaFecbas[4]);
				Integer segundosFecbas = Integer.parseInt(afechaFecbas[5]);
				
				
				String[] afechaHoy = fechadeHoy.split("-");
				Integer anosFechaHoy = Integer.parseInt(afechaHoy[0]);
				Integer mesesFechaHoy= Integer.parseInt(afechaHoy[1]);
				Integer diasFechaHoy = Integer.parseInt(afechaHoy[2]);
				Integer horasFechaHoy = Integer.parseInt(afechaHoy[3]);
				Integer minutosFechaHoy= Integer.parseInt(afechaHoy[4]);
				Integer segundosFechaHoy= Integer.parseInt(afechaHoy[5]);
						
				String restaAnos = String.valueOf(anosFecbas - anosFechaHoy);
				String restaMeses= String.valueOf(mesesFecbas - mesesFechaHoy);
				String restaDias = String.valueOf(diasFecbas - diasFechaHoy);
				String restaHoras = String.valueOf(horasFecbas - horasFechaHoy);
				String restaMinutos = String.valueOf(minutosFecbas - minutosFechaHoy);
				String restaSegundos = String.valueOf(segundosFecbas - segundosFechaHoy);
				*/
				
							
		
			}
			
			cont ++;
		}
		
	}
	
	public void openDialog(){
		
		try {
			flagOpen =true;
			
			if(flagOpen){
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('confirm').show();");
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
		
	}
	
	public void cargaObject(ActionEvent event){
		try{
			
			tfacfc00 = new Tfacfc00();
			terfet00 = new Terfet00();
			terfed00 = new Terfed00();
			
			tfacfc00Aux = (Tfacfc00) event.getComponent().getAttributes().get("tfacfc00");
			
			terfet00.setTfacfc00(tfacfc00Aux);
			terfed00.setTfacfc00(tfacfc00Aux);
			
			listTerfet00 = terfet00Service.listTerfet00ByCriteria(terfet00,getSeControlFactura().getCompaniasUsu());
			listTerfed00 = terfed00Service.listTerfed00ByCriteria(terfed00,getSeControlFactura().getCompaniasUsu());
			
		}
		catch (Exception e) {	
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
		
	}
	
	public void enviarCliente(){
		try {
			
			System.out.println("Enviar Cliente");

			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	public void enviarDian(){
		try {
			
			System.out.println("Enviar Dian");

			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event) {
		try {
			
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			
			tfacfc00Clone = (Tfacfc00) tfacfc00.clone();
			mensaje = "Desea Continuar generando PDF para la factura: "+tfacfc00.getPreffc()+tfacfc00.getNrfafc()+" para el cliente "+tfacfc00.getNoclfc()+"";
		
			//RequestContext.getCurrentInstance().update(":formDetalle:dTableTqdrqr00");

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
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
	
	public void evtCloseDialogTfacfc00(CloseEvent event) {
        try {  
        	
        	update=null;
        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	public void generarPDF(Tqdfqf00 tqdfqf) throws NamingException, IOException {
		try{
			
			tqdfqf00 = tqdfqf;
			
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
			Tfacfc00 tfacfc = new Tfacfc00();// seTfacfc00.getTfacfc00();
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
				mparca = listMparca.get(1);
				listMparpd = listMparca.get(1).getMparpd00s();
			}
			String fileName = String.valueOf(tfacfc.getIdfcfc());
			
			if(mparca.getObsec5()!=null && !mparca.getObsec5().equals("")){
				fileName = getFileName(mparca.getObsec5(),tfacfc);
			}else{
				fileName = fileName.length()>5?fileName.substring(fileName.length()-5,fileName.length()):fileName;
			}
			HashMap<String, Mparpd00> hmMparpd = getHashMapMparpd(listMparpd);
			
			data.put("listTfadf",listTfadf); //Cambiar este parametro!!! en IREPORT
			if(hmMparpd.get("CAIH01").getLogopd()!=null){
				InputStream in = new ByteArrayInputStream(hmMparpd.get("CAIH01").getLogopd());
				BufferedImage logo = ImageIO.read(in);
				data.put("CAIH01", logo);
			}else{
				data.put("CAIH01", null);
			}
			data.put("CADH02", mconca.getNomcia());
			data.put("CATH03", hmMparpd.get("CATH03").getDsrapd()); //Factura de venta Numero
			data.put("CADH04", tfacfc.getPreffc()+tfacfc.getNrfafc());
			data.put("CATH05", hmMparpd.get("CATH05").getDsrapd());//Nit cia
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
			data.put("CADH51", tfacfc.getCufevc()); //CUFE
			data.put("CATH52", hmMparpd.get("CATH52").getDsrapd());
			data.put("CADH53", tfacfc.getPzdifc());
			data.put("CATH54", hmMparpd.get("CATH54").getDsrapd());
			data.put("CADH55", tfacfc.getMofafc());
			
			data.put("CADS03", tfacfc.getVabrfc());
			data.put("CADS06", tfacfc.getVaivfc());
			data.put("CADS09", tfacfc.getReivfc());
			data.put("CADS12", tfacfc.getRefufc());
			data.put("CADS15", tfacfc.getTovafc());
			
			//Datos para Geenraci�n QR
			DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			hourFormat.format(tfacfc.getFegefc());

			HashMap<String, String> params = new LinkedHashMap<String, String>();
			
			params.put(hmMparpd.get("CATS20").getDscapd()+":", tfacfc.getPreffc()+tfacfc.getNrfafc());
			params.put(hmMparpd.get("CATS21").getDscapd()+":", sdf.format(tfacfc.getFegefc()).split(" ")[2]+sdf.format(tfacfc.getFegefc()).split(" ")[1]+sdf.format(tfacfc.getFegefc()).split(" ")[0]+hourFormat.format(tfacfc.getFegefc()).split(":")[0]+hourFormat.format(tfacfc.getFegefc()).split(":")[1]+hourFormat.format(tfacfc.getFegefc()).split(":")[2]); //Fecha LISTO
			params.put(hmMparpd.get("CATS22").getDscapd()+":", mconca.getNitcia());
			params.put(hmMparpd.get("CATS23").getDscapd()+":", tfacfc.getTqdfqf00().getMgente00().getNrnite());
			params.put(hmMparpd.get("CATS24").getDscapd()+":", String.valueOf((tfacfc.getVabrfc()))); // ValFac
			params.put(hmMparpd.get("CATS25").getDscapd()+":", String.valueOf((tfacfc.getVaivfc())));//ValIva
			params.put(hmMparpd.get("CATS26").getDscapd()+":", String.valueOf((tfacfc.getImcofc()))); //EN DUDA CADS15  ValOtroIm
			params.put(hmMparpd.get("CATS27").getDscapd()+":", String.valueOf((tfacfc.getTovafc())));//ValFacIm
			params.put(hmMparpd.get("CATS28").getDscapd()+":", (tfacfc.getCufevc())); //Cufe
			
			
			data.put("CADH56", Utils.getQR(params));
			
			ReportEntity reportEntity = new ReportEntity();
			reportEntity.setData(data);
			reportEntity.setMparpd(hmMparpd);
			
			parameters.put("fileName", fileName.replace(" ", "\b"));
			parameters.put("reportEntity", reportEntity);
			parameters.put("reportName", "facturaFinal");
			parameters.put("reports","reports/jasper/");
			parameters.put("subreport","facturaFinalDetails");
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

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
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

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	
	public List<Tqdfqf00> getListTqdfqf00Aux() {
		return listTqdfqf00Aux;
	}

	public void setListTqdfqf00Aux(List<Tqdfqf00> listTqdfqf00Aux) {
		this.listTqdfqf00Aux = listTqdfqf00Aux;
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

	public List<Tfacfc00> getListTfacfc00() {
		return listTfacfc00;
	}

	public void setListTfacfc00(List<Tfacfc00> listTfacfc00) {
		this.listTfacfc00 = listTfacfc00;
	}

	public List<Tfacfc00> getFilteredtfacfc00() {
		return filteredtfacfc00;
	}

	public void setFilteredtfacfc00(List<Tfacfc00> filteredtfacfc00) {
		this.filteredtfacfc00 = filteredtfacfc00;
	}

	public Tqdrfg00 getTqdrfg00() {
		return tqdrfg00;
	}

	public void setTqdrfg00(Tqdrfg00 tqdrfg00) {
		this.tqdrfg00 = tqdrfg00;
	}

	public List<Tfadfd00> getListTfadfd00() {
		return listTfadfd00;
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

	public Tfacfc00 getTfacfc00() {
		return tfacfc00;
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

	public Tfacfc00 getTfacfc00Aux() {
		return tfacfc00Aux;
	}

	public void setTfacfc00Aux(Tfacfc00 tfacfc00Aux) {
		this.tfacfc00Aux = tfacfc00Aux;
	}

	public List<Tfacfc00> getListTfacfc00Aux() {
		return listTfacfc00Aux;
	}

	public void setListTfacfc00Aux(List<Tfacfc00> listTfacfc00Aux) {
		this.listTfacfc00Aux = listTfacfc00Aux;
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

	public SeTfacfc00Env getSeTfacfc00() {
		return seTfacfc00;
	}

	public void setSeTfacfc00(SeTfacfc00Env seTfacfc00) {
		this.seTfacfc00 = seTfacfc00;
	}

	public List<Terfet00> getListTerfet00() {
		return listTerfet00;
	}

	public void setListTerfet00(List<Terfet00> listTerfet00) {
		this.listTerfet00 = listTerfet00;
	}

	public List<Terfet00> getFilteredTerfet00() {
		return filteredTerfet00;
	}

	public void setFilteredTerfet00(List<Terfet00> filteredTerfet00) {
		this.filteredTerfet00 = filteredTerfet00;
	}

	public List<Terfed00> getListTerfed00() {
		return listTerfed00;
	}

	public void setListTerfed00(List<Terfed00> listTerfed00) {
		this.listTerfed00 = listTerfed00;
	}

	public List<Terfed00> getFilteredTerfed00() {
		return filteredTerfed00;
	}

	public void setFilteredTerfed00(List<Terfed00> filteredTerfed00) {
		this.filteredTerfed00 = filteredTerfed00;
	}

	public ITerfed00Service getTerfed00Service() {
		return terfed00Service;
	}

	public void setTerfed00Service(ITerfed00Service terfed00Service) {
		this.terfed00Service = terfed00Service;
	}

	public ITerfet00Service getTerfet00Service() {
		return terfet00Service;
	}

	public void setTerfet00Service(ITerfet00Service terfet00Service) {
		this.terfet00Service = terfet00Service;
	}

	public Terfet00 getTerfet00() {
		return terfet00;
	}

	public void setTerfet00(Terfet00 terfet00) {
		this.terfet00 = terfet00;
	}

	public Terfed00 getTerfed00() {
		return terfed00;
	}

	public void setTerfed00(Terfed00 terfed00) {
		this.terfed00 = terfed00;
	}

	public boolean isFlagOpen() {
		return flagOpen;
	}

	public void setFlagOpen(boolean flagOpen) {
		this.flagOpen = flagOpen;
	}

	public String getGetHorasAmarilla() {
		return getHorasAmarilla;
	}

	public void setGetHorasAmarilla(String getHorasAmarilla) {
		this.getHorasAmarilla = getHorasAmarilla;
	}

	public String getGetHorasRoja() {
		return getHorasRoja;
	}

	public void setGetHorasRoja(String getHorasRoja) {
		this.getHorasRoja = getHorasRoja;
	}
	
}
