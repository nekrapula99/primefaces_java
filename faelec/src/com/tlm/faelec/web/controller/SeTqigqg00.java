package com.tlm.faelec.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMaccio00Service;
import com.tlm.faelec.service.maestros.IMacdio00Service;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMestad00Service;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMteste00Service;
import com.tlm.faelec.service.maestros.IMtiptx00Service;
import com.tlm.faelec.service.trans.ITqdfqf00Service;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelec.service.trans.ITqesqe00Service;
import com.tlm.faelec.service.trans.ITqigqg00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;
import com.tlm.faelecEntities.model.entities.Musuar00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqesqe00;

import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Controller
@ManagedBean
@SessionScoped
public class SeTqigqg00  extends Control implements Serializable{
	
private static final long serialVersionUID = 1L;

	
	@ManagedProperty(value="#{mgenus00Service}")
	IMgenus00Service mgenus00Service;
	
	@ManagedProperty(value = "#{tqigqg00Service}")
	private ITqigqg00Service tqigqg00Service;
	
	@ManagedProperty(value = "#{tqdrqr00Service}")
	private ITqdrqr00Service tqdrqr00Service;
	
	@ManagedProperty(value = "#{tqdfqf00Service}")
	private ITqdfqf00Service tqdfqf00Service;

	@ManagedProperty(value = "#{mtiptx00Service}")
	private IMtiptx00Service mtiptx00Service;
	
	@ManagedProperty(value = "#{tqesqe00Service}")
	private ITqesqe00Service tqesqe00Service;
	
	@ManagedProperty(value = "#{mestad00Service}")
	private IMestad00Service mestad00Service;
	
	@ManagedProperty(value = "#{maccio00Service}")
	private IMaccio00Service maccio00Service;
	
	@ManagedProperty(value = "#{macdio00Service}")
	private IMacdio00Service macdio00Service;
	
	@ManagedProperty(value = "#{mteste00Service}")
	private IMteste00Service mteste00Service;
	
	@ManagedProperty(value = "#{mconca00Service}")
	private IMconca00Service mconca00Service;	
	
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
	
	private Tqigqg00 tqigqg00;
	private Tqigqg00 tqigqg00Cop;
	private List<Tqigqg00> listTqigqg00;
	private List<Tqigqg00> filteredTqigqg00;
	
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
	
	private Tqesqe00 tqesqe00;
	private Tqesqe00 tqesqe00Cop;
	private boolean banId = false;
	
	private Mestad00 mestad00;
	private Maccio00 maccio00;
	private Macdio00 macdio00;
	private Musuar00 musuar00Session;
	private Mconca00 mconca00;
	private Mconca00 mconca00Session;
	
	private String dstiptx;

	private Double total;
	private Double totalProCuota;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
		
	private Integer fracDec_CantiM;
	private Integer fracDec_PorceM;
	private Integer fracDec_ValrtM;
	private String paternDec_CantiM;
	private String paternDec_PorceM;
	private String paternDec_ValrtM;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tablaCotizacion;
	private String accion;
	private String titulo;

	//Visualizacion de pesta�as
	private boolean tabInfBase;
	private boolean tabReq_Prod;
	private boolean tabForma_Pago;
	private boolean infoGuardada;
	
	private String[] pestanas;
	private int tabView=0;
	private List<String> listSufijo;
	private Integer sufijo;
	private String fechaActual;
	private String descEstado;
	private String compac;
	private String compaCodcia;
	private Integer CompaIdccia;
	private boolean numNuevo;
	private boolean numModificar;
	private boolean numModificar2;
	
	private List<Tqesqe00> listTqesqe00;
	private List<Tqesqe00> filteredTqesqe00;
	
	private UploadedFile DocCoti;
	private boolean changeDoc=false;
	private StreamedContent imageStrem;
	private String docAnterior;
	
	private InputText inputTextIdclqgColumn;
	private InputText inputTextIdmoqgColumn;
	private InputText inputTextIdppqgColumn;
	private InputText inputTextIdovqgColumn;
	private InputText inputTextIdcvqgColumn; 
	private InputText inputTextSiglacColumn;
	private InputTextarea inputTextDescqgColumn;
	
	//Variables Botones Cotizaci�n
	private boolean botonModificada;
	private boolean botonAnulada;
	private boolean botonEnviada;
	private boolean botonAprobada;
	private boolean botonLiberadaDsllo;
	private boolean botonReabrir;
	private boolean botonGuardar;
	private boolean mostrarBotonAnulada;
	private boolean mostrarBotonEnviada;
	private boolean mostrarBotonAprobada;
	private boolean mostrarBotonLiberadaDsllo;
	private boolean mostrarBotonReabrir;
	private boolean mostrarBotonGuardar;
	
	//Variables Botones Requerimientos
	private boolean botonAnularReq;
	private boolean botonAprobarReq;
	private boolean botonLiberadaDslloReq;
	private boolean botonReabrirReq;
	private boolean botonGuardarReq;
	private boolean mostrarBotonAnularReq;
	private boolean mostrarBotonAprobarReq;
	private boolean mostrarBotonLiberadaDslloReq;
	private boolean mostrarBotonReabrirReq;
	private boolean mostrarBotonGuardarReq;
	
	//Banderas
	private boolean flagModificada;
	private boolean flagAnulada;
	private boolean flagEnviada;
	private boolean flagAprobada;
	private boolean flagLiberadaDsllo;
	private boolean flagReabrir;
	private boolean flagGuardar;
	private boolean flagNueva;
	
	private List<Integer> listIdeses;
	private List<Mestad00> listMestad00;
	private List<String> listCompac;
	
	private String dEstadoinit;
	private  boolean validacionEst;
	private boolean flagEstadoInicial;
	private Integer idesesInit=0;
	private boolean validacionFecha;
	private boolean varTable;
	
	private List<Tqdfqf00> listTqdfqf00Temp;
	private Mestad00 mestadoInit;
	
	private boolean cotizacionNueva;
	
	//private static final Log LOG = LogFactory.getLog(SeTqigqg00.class);
	
	@PostConstruct
	public void init() {
		try {
			super.init("TQIGQG00");
			tqigqg00 = new Tqigqg00();
			mestadoInit= new Mestad00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tablaCotizacion=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			mconca00Session = super.getSeControlFactura().getMconca00Session();
			compac=super.getSeControlFactura().getMconca00Session().getNomcia();
			compaCodcia = super.getSeControlFactura().getMconca00Session().getCodcia();
			CompaIdccia = super.getSeControlFactura().getMconca00Session().getIdccia();
		
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Numero_Decimales_Cantidades_BF");
			Double d2=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_PorceM");
			Double d3=(Double)super.getSeControlFactura().PARAMETROS.get("Numero_Decimales_COP_BF");
			fracDec_CantiM=d1.intValue();
			fracDec_PorceM=d2.intValue();
			fracDec_ValrtM=d3.intValue();
			String paternDec_CantiMAux = calcularPatern(fracDec_CantiM.toString());
			String paternDec_PorceMAux = calcularPatern(fracDec_PorceM.toString());
			String paternDec_ValrtMAux = calcularPatern(fracDec_ValrtM.toString());
			
			System.out.println("paternDec_CantiM: "+paternDec_CantiMAux);
			System.out.println("paternDec_PorceM: "+paternDec_PorceMAux);
			System.out.println("paternDec_ValrtM: "+paternDec_ValrtMAux);
			
			paternDec_CantiMAux = paternDec_CantiMAux.replace("0", "#");
			paternDec_CantiMAux = "###,##"+paternDec_CantiMAux+"";
			
			paternDec_PorceMAux = paternDec_PorceMAux.replace("0", "#");
			paternDec_PorceMAux = "###,##"+paternDec_PorceMAux+"";
			
			paternDec_ValrtMAux = paternDec_ValrtMAux.replace("0", "#");
			paternDec_ValrtMAux = "###,##"+paternDec_ValrtMAux+"";
			
			
			paternDec_CantiM =  paternDec_CantiMAux;
			paternDec_PorceM =  paternDec_PorceMAux;
			paternDec_ValrtM =  paternDec_ValrtMAux;
			
			
			
			tabInfBase = false;
			tabReq_Prod = false;
			tabForma_Pago = false;
			
			numNuevo = false;
			numModificar = false;
			numModificar2 = false;
			botonModificada = false;
			botonAnulada = false;
			botonEnviada = false;
			botonAprobada = false;
			botonLiberadaDsllo = false;
			botonReabrir = false;
			botonGuardar = false;
			flagModificada = false;
			flagAnulada = false;
			flagEnviada = false;
			flagAprobada = false;
			flagLiberadaDsllo = false;
			flagReabrir = false;
			flagGuardar = false;
			flagNueva = false;
			cargarListas();
			
	    	mostrarBotonAnulada = true;
	    	mostrarBotonLiberadaDsllo = true;
	    	mostrarBotonReabrir = true;
	    	mostrarBotonAprobada = true;
	    	mostrarBotonGuardar = true;
	    	mostrarBotonEnviada = true; 
	    	validacionFecha = false;
	    	varTable=false;
	    	cotizacionNueva = false;
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try{
			getSeListas().actualizarListas(NombresListas.listMgenteCliente);
			getSeListas().actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMgenusMoneda);
			getSeListas().actualizarListas(NombresListas.listMgenusOrganiVentas);
			getSeListas().actualizarListas(NombresListas.listMgenusCanalDistri);
			getSeListas().actualizarListas(NombresListas.listMpropr);
			getSeListas().actualizarListas(NombresListas.listMfunfu);
			getSeListas().actualizarListas(NombresListas.listMtiptx00);
			getSeListas().actualizarListas(NombresListas.listMgenus00MonedaCot);
			
			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	
	public void onTabChange(TabChangeEvent event) {  
	    try{
	
		    if(event.getTab().getId().equalsIgnoreCase("tabInfoGrnl")){
		   
		    	varTable = false;
		    	mostrarBotonAnulada = true;			
		    	mostrarBotonLiberadaDsllo = true;
		    	mostrarBotonReabrir = true;
		    	mostrarBotonAprobada = true;
		    	mostrarBotonGuardar = true;
		    	mostrarBotonEnviada = true;
		    	
		    	RequestContext.getCurrentInstance().update("formDetalle:pnlBotones");
		    		
		    }else if(event.getTab().getId().equalsIgnoreCase("tabReqpro")){
		    	varTable = true;

		    	mostrarBotonAnulada = false;
		    	mostrarBotonLiberadaDsllo = false;
		    	mostrarBotonReabrir = false;
		    	mostrarBotonAprobada = false;
		    	mostrarBotonGuardar = false;
		    	mostrarBotonEnviada = false; 
		    	RequestContext.getCurrentInstance().update("formDetalle:pnlBotones");
		    	
		    }else if(event.getTab().getId().equalsIgnoreCase("tabFrPago")){
		    	varTable = false;

		    	mostrarBotonAnulada = false;
		    	mostrarBotonLiberadaDsllo = false;
		    	mostrarBotonReabrir = false;
		    	mostrarBotonAprobada = false;
		    	mostrarBotonGuardar = false;
		    	mostrarBotonEnviada = false; 
		    	RequestContext.getCurrentInstance().update("formDetalle:pnlBotones");
		    }

	}catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
	}
	    
	}
	
	public void mostrarPestana()
	{
		try{
			
		   pestanas=((String) getSeControlFactura().PARAMETROS.get("Vis_MaeTqi")).split(","); 
		   for (int i=0; i < pestanas.length;i++){
			   if(pestanas[i].equals("1"))
			   {
				   tabInfBase=true;
			   }
			   if(pestanas[i].equals("2"))
			   {
				   tabReq_Prod=true;
			   }
			   if(pestanas[i].equals("3"))
			   {
				   tabForma_Pago=true;
			   }
	
		   }
		   RequestContext.getCurrentInstance().update("formDetalle:tabView");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		listTqigqg00 = tqigqg00Service.listTqigqg00ByCriteria(new Tqigqg00(),getSeControlFactura().getCompaniasUsu());
	}
	
	 public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	 }
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tablaCotizacion);
			total = 0.0;
			seListas.actualizarListas(NombresListas.listMconca);
			tqigqg00 = new Tqigqg00();
			mtiptx00 = new Mtiptx00();
			tqigqg00.setRegcqg(true);
			getSeListas().actualizarListas(NombresListas.listMconca);
			tqigqg00.setTqdrqr00s(new ArrayList<Tqdrqr00>());
			tqigqg00.setTqdfqf00s(new ArrayList<Tqdfqf00>());
			tqigqg00.setTqesqe00s(new ArrayList<Tqesqe00>());
			numNuevo = true;
			numModificar = false;
			numModificar2 = false;
			flagNueva = true;
			generar_Numero_CotizacionSHS();
			estadoCotizacion();
			
			//llenar tqesqe
			tqesqe00 = new Tqesqe00();
			tqesqe00.setRegcqe(true);
			mostrarPestana();
			tabView=0;
			cotizacionNueva = true;
			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	public void accionModificar(SelectEvent event) {
		try {
			
			varTable = true;
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tablaCotizacion);
			tqesqe00 = new Tqesqe00();
			tqesqe00.setRegcqe(true);
			tqigqg00Cop= (Tqigqg00) tqigqg00.clone();
			tqesqe00Cop= (Tqesqe00) tqesqe00.clone();
			mostrarPestana();
			total = 0.0;
			tabView=0;
			numNuevo = false;
			numModificar = true;
			numModificar2 =true;
			
			estadoCotizacion();
			tqigqg00 = tqigqg00Service.cargarDetalles(tqigqg00);
			
			Tqigqg00 tqigqg00Rs = new Tqigqg00();
			tqigqg00Rs = tqigqg00Service.cargarDetalles(tqigqg00.getIdtqqg());
			tqigqg00.setTqdrqr00s(tqigqg00Rs.getTqdrqr00s());
			tqigqg00.setTqdfqf00s(tqigqg00Rs.getTqdfqf00s());

			if(tqigqg00.getMgenus001() != null){
				actualizarListasCompania(compaCodcia);
				getSeControlFactura().cargarParametros(true,null,tqigqg00.getMgenus001().getIdtrus());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}
			
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqigqg00.getMgenus001(), tqigqg00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqigqg00.getMgenus002(), tqigqg00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqigqg00.getMgenus003(), tqigqg00.getMgenus003().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqigqg00.getMtiptx00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqigqg00.getMpropr00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqigqg00.getMfunfu001(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqigqg00.getMfunfu002(), null);

			}
			
			tqdrqr00 = new Tqdrqr00();
			tqdrqr00.setTqigqg00(tqigqg00);
			seTqdrqr00 = new SeTqdrqr00();
			listTqdrqr00Aux = tqdrqr00Service.listTqdrqr00ByCriteria(tqdrqr00);
			seTqdrqr00.setListTqdrqr00(listTqdrqr00Aux);
			
			
			tqdfqf00 = new Tqdfqf00();
			tqdfqf00.setTqigqg00(tqigqg00);
			seTqdfqf00 = new SeTqdfqf00();
			listTqdfqf00Aux = tqdfqf00Service.listTqdfqf00ByCriteria(tqdfqf00,getSeControlFactura().getCompaniasUsu());
			seTqdfqf00.setListTqdfqf00(listTqdfqf00Aux);
			cotizacionNueva = false;
			RequestContext.getCurrentInstance().update(":formDetalle:tabView:dTableTqdrqr00");
			RequestContext.getCurrentInstance().update(":formDetalle:tabView:pnlDetalle2"); 
			RequestContext.getCurrentInstance().update(":formDetalle:tabView:pnlMpropr002"); 
				
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	private UploadedFile file;
	 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() throws IOException {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            System.out.println("file.getFileName(): "+file.getFileName().getBytes());
            
            System.out.println("file getContents(): "+file.getContents());
            System.out.println("getInputstream(): "+file.getInputstream());
            System.out.println("getContentType(): "+file.getContentType());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

	
	

/* public void activarBotones(){
	 //String descripEstado ="";
	 try {
		 
		 if(getAccion().equals(Constantes.ACCION_NUEVO)){
			 botonAnulada = false;
			 botonLiberadaDsllo = false;
			 botonReabrir = false;
			 botonAprobada = false;
			 botonGuardar = true;
			 botonEnviada = false;
		 }else if(getAccion().equals(Constantes.ACCION_MODIFICAR)){
			 
			 Tqesqe00 tqesqe00 = new Tqesqe00();
			 Date fechaMayor = new Date();
			 listTqesqe00 = tqesqe00Service.listTqesqe00ByCriteria(tqesqe00);
			 if(listTqesqe00.isEmpty()){
				 descEstado = "Cot. Es. Nueva";				 
			 }
			 System.out.println("listTqesqe00 : "+listTqesqe00);
			 List<Date> listFechasTqesqe00= new ArrayList<Date>();

				for(Tqesqe00 obj : listTqesqe00)
				{
					if(obj.getTqigqg00().getIdtqqg()==getTqigqg00().getIdtqqg()){
						if(obj.getMestad00()!=null){
							listFechasTqesqe00.add(obj.getFeacqe());
							fechaMayor = Collections.max(listFechasTqesqe00);
							if(obj.getFeacqe().equals(fechaMayor)){
								mestad00 = obj.getMestad00();
								descEstado = mestad00.getDscres(); 
							}
						}
					}
				}
			 if(descEstado.equalsIgnoreCase("Cot. Es. Nueva")){
				 botonAnulada = true;
				 botonLiberadaDsllo = false;
				 botonReabrir = false;
				 botonAprobada = false;
				 botonGuardar = true;
				 botonEnviada = false; 
			 }else if(descEstado.equalsIgnoreCase("Cot. Es. Modificada")){
				 botonAnulada = false;
				 botonLiberadaDsllo = false;
				 botonReabrir = false;
				 botonAprobada = false;
				 botonGuardar = true;
				 botonEnviada = true;	 
			 }else if(descEstado.equalsIgnoreCase("Cot. Es. Enviada")){
				 botonAnulada = true;
				 botonLiberadaDsllo = false;
				 botonReabrir = false;
				 botonAprobada = true;
				 botonGuardar = false;
				 botonEnviada = true;	 
			 }else if(descEstado.equalsIgnoreCase("Cot. Es. Aprobada")){
				 botonAnulada = true;
				 botonLiberadaDsllo = true;
				 botonReabrir = false;
				 botonAprobada = false;
				 botonGuardar = false;
				 botonEnviada = false;	 
			 }else if(descEstado.equalsIgnoreCase("Cot. Es. Anulada")){
				 botonAnulada = false;
				 botonLiberadaDsllo = false;
				 botonReabrir = true;
				 botonAprobada = false;
				 botonGuardar = false;
				 botonEnviada = false;
			 }else if(descEstado.equalsIgnoreCase("Cot. Es. Reabierta")){
				 botonAnulada = false;
				 botonLiberadaDsllo = true;
				 botonReabrir = false;
				 botonAprobada = false;
				 botonGuardar = true;
				 botonEnviada = false;	 
			 }		 
		 }
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
	}
 }*/
	
 public void estadoInicialCotizacion(){
	 try{
		 flagEstadoInicial=false;
		 Mestad00 mestado00 = new Mestad00();
		 
		 listMestad00 = mestad00Service.listMestad00ByCriteria(mestado00, getSeControlFactura().getCompaniasUsu());
		 for(Mestad00 obj : listMestad00){
			 
			 if(obj.getInines()){
				 descEstado = obj.getDscres();
				 mestadoInit = obj;
				 flagEstadoInicial = true;
				 idesesInit = obj.getIdeses();				 
			}
		 }
		 
		 if(!flagEstadoInicial){
			 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
			 RequestContext.getCurrentInstance().update("formTable");  
		 }

	 } catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
	}
	 
 }
 
 public void estadoCotizacion(){
	 try {
		 boolean estadoExist = false;
		 Tqesqe00 tqesqe00 = new Tqesqe00();
		 Date fechaMayor = new Date();
		 listTqesqe00 = tqesqe00Service.listTqesqe00ByCriteria(tqesqe00);
		 List<Date> listFechasTqesqe00= new ArrayList<Date>();

			for(Tqesqe00 obj : listTqesqe00)
			{
				if(obj.getTqigqg00().getIdtqqg()==getTqigqg00().getIdtqqg()){
					if(obj.getMestad00()!=null){
						listFechasTqesqe00.add(obj.getFeacqe());
						fechaMayor = Collections.max(listFechasTqesqe00);
						if(obj.getFeacqe().equals(fechaMayor)){
							mestad00 = obj.getMestad00();
							descEstado = mestad00.getDscres();
							estadoExist=true;
						}
					}
				}
			}
			if(!estadoExist){
				
				flagEstadoInicial=false;
				Mestad00 mestado00 = new Mestad00();
				 
				listMestad00 = mestad00Service.listMestad00ByCriteria(mestado00, getSeControlFactura().getCompaniasUsu());
				for(Mestad00 obj : listMestad00){
					 
					if(obj.getInines()){
						descEstado = obj.getDscres();
						flagEstadoInicial = true;
						idesesInit = obj.getIdeses();
						 mestadoInit = obj;
					}
				}
				 
				if(!flagEstadoInicial){
					 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
					 RequestContext.getCurrentInstance().update("formTable");  
				 }	
			}
			estadoExist=false;
	
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
	}
 }

 //Se valida si con la operaci�n a realizar por el usuario
 //se puede tener una transici�n valdida
 public boolean validarTransicionEstado(){
	 validacionEst = false;
	 boolean flag =false;
	 try {
		 Integer varTempEstdOrigen=0;
		 boolean estadoExist = false;
		 	macdio00 = new Macdio00();
			macdio00.setMaccio00(consultarObjetoMaccio00());
			if(flagNueva){
				macdio00 = consultarObjetoMacdio00("BTN_NUEVO_COTIZACION");
				flagNueva = false;
			}else if(flagGuardar){
				macdio00 = consultarObjetoMacdio00("BTN_GRABAR_COTIZACION");
			}else if(flagAnulada){
				macdio00 = consultarObjetoMacdio00("BTN_ANULAR_COTIZACION");
			}else if(flagAprobada){
				macdio00 = consultarObjetoMacdio00("BTN_APROBAR_COTIZACION");
			}else if(flagLiberadaDsllo){
				macdio00 = consultarObjetoMacdio00("BTN_LIBERAR_COTIZACION");
			}else if(flagReabrir){
				macdio00 = consultarObjetoMacdio00("BTN_REABRIR_COTIZACION");
			}else if(flagEnviada){
				macdio00 = consultarObjetoMacdio00("BTN_ENVIAR");
			}

		 Tqesqe00 tqesqe00 = new Tqesqe00();
		 Date fechaMayor = new Date();
		 listTqesqe00 = tqesqe00Service.listTqesqe00ByCriteria(tqesqe00);
		 List<Date> listFechasTqesqe00= new ArrayList<Date>();

			for(Tqesqe00 obj : listTqesqe00)
			{	
				if(obj.getTqigqg00().getIdtqqg()==getTqigqg00().getIdtqqg()){
					if(obj.getMestad00()!=null){
						listFechasTqesqe00.add(obj.getFeacqe());
						fechaMayor = Collections.max(listFechasTqesqe00);
						if(obj.getFeacqe().equals(fechaMayor)){
							mestad00 = obj.getMestad00();
							varTempEstdOrigen = mestad00.getIdeses(); //capturo estado origen
							flag =true;
						}
					}
				}else{
					System.out.println("DEBE EXISTIR ESTADO"); //MARCAR ERROR (DEBE EXISTIR ESTADO)
				}
			}
			
			
			if(flag){
				if(obtenerTransicionValida(varTempEstdOrigen,macdio00.getMestad00().getIdeses())){
					validacionEst = true;
				}else{	
					validacionEst = false;
					System.out.println("Transicion no valida");
				}
			}else{
				//estadoInicialCotizacion();
						
				varTempEstdOrigen = getIdesesInit();
				obtenerTransicionValida(varTempEstdOrigen,macdio00.getMestad00().getIdeses());
				validacionEst = true;	
				
			}
			
		flag =false;
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
	}
	 return validacionEst;
 }
	

public void generar_Numero_CotizacionSHS(){
	 try {
		 	
		 	listSufijo = mtiptx00Service.cargarUltimoSufijo();
			List<Integer> listMtiptx00Temp = new ArrayList<Integer>(); 
			
			for(String obj : listSufijo)
			{
				if(!obj.equals(null) && !obj.equals("")){
					listMtiptx00Temp.add(Integer.parseInt(obj));
				}
			}
		    Integer mayor = Collections.max(listMtiptx00Temp);
		    sufijo = mayor+1;
		    
		    Date date = new Date();
		    
		 // el que parsea
		    SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
		    // el que formatea
		    SimpleDateFormat formateador = new SimpleDateFormat("yyMMdd");
		    fechaActual = formateador.format(date);
		    		    
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
	}
 }

	
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			
			getSeListas().actualizarListasCompania(NombresListas.listMgenteCliente, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMconca , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusMoneda, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusOrganiVentas, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusCanalDistri , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMpropr, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMfunfu, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMtiptx00, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenus00MonedaCot, listMconca00ActualizarListas);
			RequestContext.getCurrentInstance().update("formTable");
			}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void autocompleteActualizarListas (){
		try{
			if(tqigqg00.getMgenus001() != null){
			actualizarListasCompania(compaCodcia);
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			tqigqg00.setUsecqg(getSeControlFactura().codusu);
			tqigqg00.setPrgcqg("Cotizacion_Estandar " + getClass().getName());
			tqigqg00.setFeacqg(new Date());
			tqigqg00.setMaqcqg(getSeControlFactura().ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Maccio00 consultarObjetoMaccio00(){
		maccio00 = new Maccio00();
		maccio00.setMconca00(getSeControlFactura().getMconca00Session());
		maccio00.setMtiptx00(tqigqg00.getMtiptx00());
		maccio00 = maccio00Service.getMaccio00ByTqigqg00(maccio00, tablaCotizacion);
		return maccio00;
	}
	
	public Macdio00 consultarObjetoMacdio00(String notdio){
		macdio00 = new Macdio00();
		macdio00.setMaccio00(consultarObjetoMaccio00());
		macdio00 = macdio00Service.getMacdio00ByTqigqg00(macdio00,notdio);
		return macdio00;
	}
	
	public Mconca00 consultarObjetoMconca00(String codcia){
		mconca00 = mconca00Service.getMconca00ByCodcia(codcia);
		return mconca00;
	}
	
	public boolean obtenerTransicionValida(Integer varTemp, Integer macIdedio){
		return mteste00Service.obtenerTransicionValida(varTemp, macIdedio);
	}
	
	public Tqigqg00 consultarObjetoTqigqg00(){
		tqigqg00 = new Tqigqg00();
		//tqigqg00.setMaccio00(consultarObjetoMaccio00());
		tqigqg00 = tqigqg00Service.getObjectTqigqg00(new Tqigqg00());
		return tqigqg00;
	}
	
	private void guardarTqesqe00() {
		try {
			
			//if(validarTransicionEstado()){
				tqesqe00 = new Tqesqe00();
				tqesqe00.setRegcqe(true);
				if(getAccion().equals(Constantes.ACCION_NUEVO))
				{
					

					tqesqe00.setMestad00(mestadoInit);
					tqesqe00.setTqigqg00(tqigqg00);	
					tqesqe00.setFeesqe(new Date());
					tqesqe00.setUsecqe(getSeControlFactura().codusu);
					tqesqe00.setPrgcqe("Transaccion_Cotizacion_Estados " + getClass().getName());
					tqesqe00.setFeacqe(new Date());
					tqesqe00.setMaqcqe(getSeControlFactura().ip);
					tqesqe00Cop= (Tqesqe00) tqesqe00.clone();
				}
				
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					tqesqe00.setMestad00(macdio00.getMestad00());
					tqesqe00.setTqigqg00(tqigqg00);	
					tqesqe00.setFeesqe(new Date());
					tqesqe00.setUsecqe(getSeControlFactura().codusu);
					tqesqe00.setPrgcqe("Transaccion_Cotizacion_Estados " + getClass().getName());
					tqesqe00.setFeacqe(new Date());
					tqesqe00.setMaqcqe(getSeControlFactura().ip);
					tqesqe00Cop= (Tqesqe00) tqesqe00.clone();
				}

				tqigqg00.getTqesqe00s().add(tqesqe00);
				
				
			//}else{
				//Agreagr mensaje de error por retorno malo
			//}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*Metodo que se encarga de validar que los registros de las listas esten habilitados
	 * en caso de estar deshabilitado algun registro devuelve true
	 */
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 

		//Validacion lista Cliente o Prospecto
		if(tqigqg00.getMgente00() != null &&  tqigqg00.getMgente00().getRegite()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idclqg")+" "+ tqigqg00.getMgente00().getCodite() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Moneda Cotizacion
		if(tqigqg00.getMgenus001() != null &&  tqigqg00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idmoqg")+" "+ tqigqg00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Organizaci�n Ventas
		if(tqigqg00.getMpropr00() != null &&  tqigqg00.getMpropr00().getRegipr()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idppqg")+" "+ tqigqg00.getMpropr00().getCodcpr() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Funcionario Comercial Responsable
		if(tqigqg00.getMfunfu001() != null &&  tqigqg00.getMfunfu001().isRegufu()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idfrqg")+" "+ tqigqg00.getMfunfu001().getCocufu()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		//Validacion lista  Funcionario Apoyo Tecnico Preventa
		if(tqigqg00.getMfunfu002() != null &&  tqigqg00.getMfunfu002().isRegufu()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idfaqg")+" "+ tqigqg00.getMfunfu002().getCocufu() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Organizaci�n Ventas
		if(tqigqg00.getMgenus002() != null &&  tqigqg00.getMgenus002().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idovqg")+" "+ tqigqg00.getMgenus002().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}

		//Validacion lista  Canal Distribuci�n
		if(tqigqg00.getMgenus003() != null &&  tqigqg00.getMgenus003().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcvqg")+" "+ tqigqg00.getMgenus003().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(tqigqg00.getDescqg()) && permisoCampos.get("descqg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("descqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextDescqgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextDescqgColumn.getClientId());
			validacion = true;
		}else{
			inputTextDescqgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextDescqgColumn.getClientId());	
		}
		

		if(Utils.isEmptyOrNull(tqigqg00.getMgente00()) && permisoCampos.get("siglac").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("siglac")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextSiglacColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextSiglacColumn.getClientId());
			validacion = true;
		}else{
			inputTextSiglacColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextSiglacColumn.getClientId());	
		}
		
		//fecha promesa delivery
		if(Utils.isEmptyOrNull(tqigqg00.getMgente00())&& permisoCampos.get("idclqg").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idclqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		
		if(Utils.isEmptyOrNull(tqigqg00.getMgenus001()) && permisoCampos.get("idmoqg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idmoqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdmoqgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdmoqgColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdmoqgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdmoqgColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqigqg00.getMpropr00()) && permisoCampos.get("idppqg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idppqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdppqgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdppqgColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdppqgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdppqgColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqigqg00.getMgenus002()) && permisoCampos.get("idovqg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idovqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdovqgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdovqgColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdovqgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdovqgColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqigqg00.getMgenus003()) && permisoCampos.get("idcvqg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcvqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcvqgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcvqgColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcvqgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcvqgColumn.getClientId());	
		}
		
		//FECHA CREACI�N
		if(Utils.isEmptyOrNull(tqigqg00.getFecrqg()) && permisoCampos.get("fecrqg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("fecrqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//fecha vigencia hasta
		if(Utils.isEmptyOrNull(tqigqg00.getFeviqg()) && permisoCampos.get("feviqg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("feviqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//fecha promesa delivery
		if(Utils.isEmptyOrNull(tqigqg00.getFepdqg())&& permisoCampos.get("fepdqg").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("fepdqg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
	
		
		return validacion;
	}
	
	public boolean validarFechas(){
		validacionFecha = false;
		if(tqigqg00.getFecrqg().after(tqigqg00.getFeviqg())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha de vigencia debe ser mayor o igual a Fecha creaci�n");
			RequestContext.getCurrentInstance().update("formTable"); 
			validacionFecha = true;		
		}else if(tqigqg00.getFeviqg().after(tqigqg00.getFepdqg())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha Promesa Delivery debe ser mayor o igual a Fecha de vigencia");
			RequestContext.getCurrentInstance().update("formTable"); 
			validacionFecha = true;	
		}
		return validacionFecha;
	}
	
	public void save() {
		try {

			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				flagGuardar = true;
				flagAnulada = false;
				flagAprobada = false;
				flagLiberadaDsllo = false;
				flagReabrir = false;
				flagEnviada = false;
				validarTransicionEstado();
				if(!validacionEst){
					UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
					RequestContext.getCurrentInstance().update("formTable"); 
					return;
				}
			}
			RequestContext context = RequestContext.getCurrentInstance();
			if(tqigqg00.getFecrqg()!=null && tqigqg00.getFeviqg() !=null && tqigqg00.getFeviqg()!=null && tqigqg00.getFepdqg()!=null){
				validarFechas();
				if(validacionFecha){
					return;
				}
			}

			if (validarDatosRequeridos()) {
				return;
			}
			if(tqigqg00.getMgente00().equals(null)){
				tqigqg00.setMgente00(null);
			}
			if(getAccion().equals(Constantes.ACCION_NUEVO))
			{
				flagNueva = true;
				tqigqg00.setNroqqg(tqigqg00.getMgente00().getAliate()+fechaActual+sufijo);
				numNuevo = false;
				tqigqg00.setMconca00(mconca00Session);
			}
			 
			tqigqg00.setMtiptx00(obtenerListaMtiptx00s("Cotizacion_Est"));
			tqigqg00.getMtiptx00().setSufttx(String.valueOf(sufijo));
			//validarFechas();

			colaEstandar();
			guardarTqesqe00();	
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				context.execute("PF('crearDialog').show();");
				
				if(validarListas()==false){
					tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),tqigqg00Cop.toString()):null);
							
					tqesqe00Service.save(tqesqe00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqesqe00.toString(),tqesqe00.toStringId(),tqesqe00Cop.toString()):null);				
					numModificar = false;
					numModificar2 = false;
				}else return;
			} else {
				context.execute("PF('crearDialog').show();");
				tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),null):null);
				flagGuardar = false;
				flagNueva = true;
				
				

				if(getAccion().equals(Constantes.ACCION_NUEVO))
				{
					mtiptx00Service.save(tqigqg00.getMtiptx00(),getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.getMtiptx00().toString(),tqigqg00.getMtiptx00().toStringId(),null):null);
				}	
			 }
			
			
			cargarDatos();
			validacionEst = false;
			infoGuardada=false;
			flagNueva = false;
			context.execute("PF('crearDialog').hide();");
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	
	public void anular() {
		try {
			flagGuardar = false;
			flagAnulada = true;
			flagAprobada = false;
			flagLiberadaDsllo = false;
			flagReabrir = false;
			flagEnviada = false;
			flagNueva = false;
			
			validarTransicionEstado();
			if(!validacionEst){
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
				RequestContext.getCurrentInstance().update("formTable"); 
				return;
			}
			
			if (validarDatosRequeridos()) {
				return;
			}
			if(tqigqg00.getMgente00().equals(null)){
				tqigqg00.setMgente00(null);
			}
			colaEstandar();

			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),tqigqg00Cop.toString()):null);
					guardarTqesqe00();			
					tqesqe00Service.save(tqesqe00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqesqe00.toString(),tqesqe00.toStringId(),tqesqe00Cop.toString()):null);				

				}else return;
			} else {
				guardarTqesqe00();
				tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),null):null);
	
			 }
			
			cargarDatos();
			validacionEst = false;
			infoGuardada=false;
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	
	public void aprobar() {
		try {
			flagGuardar = false;
			flagAnulada = false;
			flagAprobada = true;
			flagLiberadaDsllo = false;
			flagReabrir = false;
			flagEnviada = false;
			flagNueva = false;
			
			validarTransicionEstado();
			if(!validacionEst){
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
				RequestContext.getCurrentInstance().update("formTable"); 
				return;
			}

			
			
			if (validarDatosRequeridos()) {
				return;
			}
			if(tqigqg00.getMgente00().equals(null)){
				tqigqg00.setMgente00(null);
			}
			colaEstandar();

			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),tqigqg00Cop.toString()):null);
					guardarTqesqe00();			
					tqesqe00Service.save(tqesqe00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqesqe00.toString(),tqesqe00.toStringId(),tqesqe00Cop.toString()):null);				

				}else return;
			} else {
				guardarTqesqe00();
				tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),null):null);
	
			 }
			cargarDatos();
			validacionEst = false;
			infoGuardada=false;
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	
	public void liberar() {
		try {
			
			flagGuardar = false;
			flagAnulada = false;
			flagAprobada = false;
			flagLiberadaDsllo = true;
			flagReabrir = false;
			flagEnviada = false;
			flagNueva = false;
			
			validarTransicionEstado();
			if(!validacionEst){
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
				RequestContext.getCurrentInstance().update("formTable"); 
				return;
			}
			
			if (validarDatosRequeridos()) {
				return;
			}
			if(tqigqg00.getMgente00().equals(null)){
				tqigqg00.setMgente00(null);
			}
			colaEstandar();

			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),tqigqg00Cop.toString()):null);
					guardarTqesqe00();			
					tqesqe00Service.save(tqesqe00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqesqe00.toString(),tqesqe00.toStringId(),tqesqe00Cop.toString()):null);				

				}else return;
			} else {
				guardarTqesqe00();
				tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),null):null);
	
			 }
			
			cargarDatos();
			validacionEst = false;
			infoGuardada=false;
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	
	public void reabrir() {
		try {
			
			flagGuardar = false;
			flagAnulada = false;
			flagAprobada = false;
			flagLiberadaDsllo = false;
			flagReabrir = true;
			flagEnviada = false;
			flagNueva = false;
			
			validarTransicionEstado();
			if(!validacionEst){
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
				RequestContext.getCurrentInstance().update("formTable"); 
				return;
			}
			
			if (validarDatosRequeridos()) {
				return;
			}
			if(tqigqg00.getMgente00().equals(null)){
				tqigqg00.setMgente00(null);
			}
			colaEstandar();

			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),tqigqg00Cop.toString()):null);
					guardarTqesqe00();			
					tqesqe00Service.save(tqesqe00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqesqe00.toString(),tqesqe00.toStringId(),tqesqe00Cop.toString()):null);				

				}else return;
			} else {
				guardarTqesqe00();
				tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),null):null);
	
			 }
			cargarDatos();
			validacionEst = false;
			infoGuardada=false;
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tablaCotizacion);
			tqigqg00 = (Tqigqg00) event.getComponent().getAttributes().get("tqigqg00");
			tqigqg00Service.removeTqigqg00(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,tqigqg00.toString(),tqigqg00.toStringId()):null);
			cargarDatos();
			getSeListas().actualizarListas(NombresListas.listMconca);
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	public void preRenderView() {
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialogTqigqg00(CloseEvent event) {
        try {  
        	mostrarBotonAnulada = true;			
	    	mostrarBotonLiberadaDsllo = true;
	    	mostrarBotonReabrir = true;
	    	mostrarBotonAprobada = true;
	    	mostrarBotonGuardar = true;
	    	mostrarBotonEnviada = true;
        	
        	update=null;
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
        		tqigqg00.setMtiptx00(tqigqg00Cop.getMtiptx00());
        		tqigqg00.setMgenus001(tqigqg00Cop.getMgenus001());
        		tqigqg00.setMgenus002(tqigqg00Cop.getMgenus002());
        		tqigqg00.setMgenus003(tqigqg00Cop.getMgenus003());
        		tqigqg00.setMgente00(tqigqg00Cop.getMgente00());
        		tqigqg00.setMpropr00(tqigqg00Cop.getMpropr00());
        		tqigqg00.setMfunfu001(tqigqg00Cop.getMfunfu001());
        		tqigqg00.setMfunfu002(tqigqg00Cop.getMfunfu002());	
        
        	}
        	evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	public void uploadDocCoti(FileUploadEvent event) {
		try {
			 docAnterior= tqigqg00.getAdjuqg();
			 setDocCoti(event.getFile());
			 changeDoc=true;
			 tqigqg00.setAdjuqg("");
			 setImageStrem(new DefaultStreamedContent(event.getFile().getInputstream()));				
							
		}
	    catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeDoc() {
		try {
			docAnterior = tqigqg00.getAdjuqg();
			setDocCoti(null);
			tqigqg00.setAdjuqg(null);
			changeDoc=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//PENDIENTE BORRAR
	public List<Mgenus00> obtenerListaMgenus00(String codtus){
	    mgenus00 = new Mgenus00();
		mgenus00.setRegius(true);
		return mgenus00Service.listMgenus00ByCriteriaAndlistMusuco00(mgenus00, getSeControlFactura().getCompaniasUsu()); 
	}
	
	public Mtiptx00 obtenerListaMtiptx00s(String notetx){
		mtiptx00 = new Mtiptx00();
	    mtiptx00.setRegtxt(true);
	    mtiptx00.setNotetx(notetx);
	    mtiptx00 = mtiptx00Service.getMtiptx00ByNotetx(notetx);
		return mtiptx00;
	}
	
	public void cargarListas() {
		try {
			Mtiptx00 mtiptx00 = new Mtiptx00();
			mtiptx00.setRegtxt(true);
			mtiptx00.setNotetx("Cotizacion_Estandar");
			listMtiptx00 = mtiptx00Service.listMtiptx00ByCriteria(mtiptx00, getSeControlFactura().getCompaniasUsu());
			for(Mtiptx00 obj : listMtiptx00){
				if(obj.getNotetx().equalsIgnoreCase("Cotizacion_Est")){
					dstiptx = obj.getDstitx();	
				}		
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carcularValor(){
		try{
			
			tqdfqf00 = new Tqdfqf00();
			tqdfqf00.setTqigqg00(tqigqg00);
				total = 0.0;
				listTqdfqf00 = tqdfqf00Service.listTqdfqf00ByCriteria(tqdfqf00,getSeControlFactura().getCompaniasUsu());
				for(Tqdfqf00 obj : listTqdfqf00){
					if(obj.getVacpqf()!=0){
						total = total + obj.getVacpqf();
					}
					
	        	}

		}
		catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	

	public ITqigqg00Service getTqigqg00Service() {
		return tqigqg00Service;
	}

	public void setTqigqg00Service(ITqigqg00Service tqigqg00Service) {
		this.tqigqg00Service = tqigqg00Service;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public Tqigqg00 getTqigqg00Cop() {
		return tqigqg00Cop;
	}

	public void setTqigqg00Cop(Tqigqg00 tqigqg00Cop) {
		this.tqigqg00Cop = tqigqg00Cop;
	}

	public List<Tqigqg00> getListTqigqg00() {
		return listTqigqg00;
	}

	public void setListTqigqg00(List<Tqigqg00> listTqigqg00) {
		this.listTqigqg00 = listTqigqg00;
	}

	public List<Tqigqg00> getFilteredTqigqg00() {
		return filteredTqigqg00;
	}

	public void setFilteredTqigqg00(List<Tqigqg00> filteredTqigqg00) {
		this.filteredTqigqg00 = filteredTqigqg00;
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

	public boolean isTabInfBase() {
		return tabInfBase;
	}

	public void setTabInfBase(boolean tabInfBase) {
		this.tabInfBase = tabInfBase;
	}

	
	public String[] getPestanas() {
		return pestanas;
	}

	public void setPestanas(String[] pestanas) {
		this.pestanas = pestanas;
	}

	public int getTabView() {
		return tabView;
	}

	public void setTabView(int tabView) {
		this.tabView = tabView;
	}

	public boolean isInfoGuardada() {
		return infoGuardada;
	}

	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
	}
	
	public SeCabecera getSeCabecera() {
		return seCabecera;
	}
	public void setSeCabecera(SeCabecera seCabecera) {
		this.seCabecera = seCabecera;
	}
	

	public boolean isTabReq_Prod() {
		return tabReq_Prod;
	}

	public void setTabReq_Prod(boolean tabReq_Prod) {
		this.tabReq_Prod = tabReq_Prod;
	}

	public boolean isTabForma_Pago() {
		return tabForma_Pago;
	}

	public void setTabForma_Pago(boolean tabForma_Pago) {
		this.tabForma_Pago = tabForma_Pago;
	}

	public String getPaternDec_CantiM() {
		return paternDec_CantiM;
	}

	public void setPaternDec_CantiM(String paternDec_CantiM) {
		this.paternDec_CantiM = paternDec_CantiM;
	}

	public UploadedFile getDocCoti() {
		return DocCoti;
	}

	public void setDocCoti(UploadedFile docCoti) {
		this.DocCoti = docCoti;
	}

	public boolean isChangeDoc() {
		return changeDoc;
	}

	public void setChangeDoc(boolean changeDoc) {
		this.changeDoc = changeDoc;
	}

	public StreamedContent getImageStrem() {
		return imageStrem;
	}

	public void setImageStrem(StreamedContent imageStrem) {
		this.imageStrem = imageStrem;
	}

	public String getDocAnterior() {
		return docAnterior;
	}

	public void setDocAnterior(String docAnterior) {
		this.docAnterior = docAnterior;
	}
	
	public Mgenus00 getMgenus00() {
		return mgenus00;
	}

	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}

	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
	}

	public IMtiptx00Service getMtiptx00Service() {
		return mtiptx00Service;
	}

	public void setMtiptx00Service(IMtiptx00Service mtiptx00Service) {
		this.mtiptx00Service = mtiptx00Service;
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

	public InputText getInputTextIdclqgColumn() {
		return inputTextIdclqgColumn;
	}

	public void setInputTextIdclqgColumn(InputText inputTextIdclqgColumn) {
		this.inputTextIdclqgColumn = inputTextIdclqgColumn;
	}

	public InputText getInputTextIdmoqgColumn() {
		return inputTextIdmoqgColumn;
	}

	public void setInputTextIdmoqgColumn(InputText inputTextIdmoqgColumn) {
		this.inputTextIdmoqgColumn = inputTextIdmoqgColumn;
	}

	public InputText getInputTextIdppqgColumn() {
		return inputTextIdppqgColumn;
	}

	public void setInputTextIdppqgColumn(InputText inputTextIdppqgColumn) {
		this.inputTextIdppqgColumn = inputTextIdppqgColumn;
	}

	public InputText getInputTextIdovqgColumn() {
		return inputTextIdovqgColumn;
	}

	public void setInputTextIdovqgColumn(InputText inputTextIdovqgColumn) {
		this.inputTextIdovqgColumn = inputTextIdovqgColumn;
	}

	public InputText getInputTextIdcvqgColumn() {
		return inputTextIdcvqgColumn;
	}

	public void setInputTextIdcvqgColumn(InputText inputTextIdcvqgColumn) {
		this.inputTextIdcvqgColumn = inputTextIdcvqgColumn;
	}

	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}

	public InputText getInputTextSiglacColumn() {
		return inputTextSiglacColumn;
	}

	public void setInputTextSiglacColumn(InputText inputTextSiglacColumn) {
		this.inputTextSiglacColumn = inputTextSiglacColumn;
	}

	public List<String> getListSufijo() {
		return listSufijo;
	}

	public void setListSufijo(List<String> listSufijo) {
		this.listSufijo = listSufijo;
	}

	public Integer getSufijo() {
		return sufijo;
	}

	public void setSufijo(Integer sufijo) {
		this.sufijo = sufijo;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Mtiptx00 getMtiptx00Cop() {
		return mtiptx00Cop;
	}

	public void setMtiptx00Cop(Mtiptx00 mtiptx00Cop) {
		this.mtiptx00Cop = mtiptx00Cop;
	}

	public List<Tqesqe00> getListTqesqe00() {
		return listTqesqe00;
	}

	public void setListTqesqe00(List<Tqesqe00> listTqesqe00) {
		this.listTqesqe00 = listTqesqe00;
	}

	public List<Tqesqe00> getFilteredTqesqe00() {
		return filteredTqesqe00;
	}

	public void setFilteredTqesqe00(List<Tqesqe00> filteredTqesqe00) {
		this.filteredTqesqe00 = filteredTqesqe00;
	}

	public ITqesqe00Service getTqesqe00Service() {
		return tqesqe00Service;
	}

	public void setTqesqe00Service(ITqesqe00Service tqesqe00Service) {
		this.tqesqe00Service = tqesqe00Service;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public Tqesqe00 getTqesqe00() {
		return tqesqe00;
	}

	public void setTqesqe00(Tqesqe00 tqesqe00) {
		this.tqesqe00 = tqesqe00;
	}

	public boolean isBanId() {
		return banId;
	}

	public void setBanId(boolean banId) {
		this.banId = banId;
	}

	public Tqesqe00 getTqesqe00Cop() {
		return tqesqe00Cop;
	}

	public void setTqesqe00Cop(Tqesqe00 tqesqe00Cop) {
		this.tqesqe00Cop = tqesqe00Cop;
	}

	public IMestad00Service getMestad00Service() {
		return mestad00Service;
	}

	public void setMestad00Service(IMestad00Service mestad00Service) {
		this.mestad00Service = mestad00Service;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public boolean isNumNuevo() {
		return numNuevo;
	}

	public void setNumNuevo(boolean numNuevo) {
		this.numNuevo = numNuevo;
	}

	public boolean isNumModificar() {
		return numModificar;
	}

	public void setNumModificar(boolean numModificar) {
		this.numModificar = numModificar;
	}

	public boolean isBotonModificada() {
		return botonModificada;
	}

	public void setBotonModificada(boolean botonModificada) {
		this.botonModificada = botonModificada;
	}

	public boolean isBotonAnulada() {
		return botonAnulada;
	}

	public void setBotonAnulada(boolean botonAnulada) {
		this.botonAnulada = botonAnulada;
	}

	public boolean isBotonEnviada() {
		return botonEnviada;
	}

	public void setBotonEnviada(boolean botonEnviada) {
		this.botonEnviada = botonEnviada;
	}

	public boolean isBotonAprobada() {
		return botonAprobada;
	}

	public void setBotonAprobada(boolean botonAprobada) {
		this.botonAprobada = botonAprobada;
	}

	public boolean isBotonLiberadaDsllo() {
		return botonLiberadaDsllo;
	}

	public void setBotonLiberadaDsllo(boolean botonLiberadaDsllo) {
		this.botonLiberadaDsllo = botonLiberadaDsllo;
	}

	public boolean isBotonReabrir() {
		return botonReabrir;
	}

	public void setBotonReabrir(boolean botonReabrir) {
		this.botonReabrir = botonReabrir;
	}

	public boolean isBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(boolean botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public List<Integer> getListIdeses() {
		return listIdeses;
	}

	public void setListIdeses(List<Integer> listIdeses) {
		this.listIdeses = listIdeses;
	}

	public List<Mestad00> getListMestad00() {
		return listMestad00;
	}

	public void setListMestad00(List<Mestad00> listMestad00) {
		this.listMestad00 = listMestad00;
	}

	
	public IMaccio00Service getMaccio00Service() {
		return maccio00Service;
	}


	public void setMaccio00Service(IMaccio00Service maccio00Service) {
		this.maccio00Service = maccio00Service;
	}

	
	public Maccio00 getMaccio00() {
		return maccio00;
	}


	public void setMaccio00(Maccio00 maccio00) {
		this.maccio00 = maccio00;
	}

	public Macdio00 getMacdio00() {
		return macdio00;
	}

	public void setMacdio00(Macdio00 macdio00) {
		this.macdio00 = macdio00;
	}


	public Musuar00 getMusuar00Session() {
		return musuar00Session;
	}

	public void setMusuar00Session(Musuar00 musuar00Session) {
		this.musuar00Session = musuar00Session;
	}

	public IMacdio00Service getMacdio00Service() {
		return macdio00Service;
	}

	public void setMacdio00Service(IMacdio00Service macdio00Service) {
		this.macdio00Service = macdio00Service;
	}

	public String getCompac() {
		return compac;
	}

	public void setCompac(String compac) {
		this.compac = compac;
	}

	public IMteste00Service getMteste00Service() {
		return mteste00Service;
	}
	
	public void setMteste00Service(IMteste00Service mteste00Service) {
		this.mteste00Service = mteste00Service;
	}

	public boolean isFlagModificada() {
		return flagModificada;
	}

	public void setFlagModificada(boolean flagModificada) {
		this.flagModificada = flagModificada;
	}

	public boolean isFlagAnulada() {
		return flagAnulada;
	}

	public void setFlagAnulada(boolean flagAnulada) {
		this.flagAnulada = flagAnulada;
	}

	public boolean isFlagEnviada() {
		return flagEnviada;
	}

	public void setFlagEnviada(boolean flagEnviada) {
		this.flagEnviada = flagEnviada;
	}

	public boolean isFlagAprobada() {
		return flagAprobada;
	}

	public void setFlagAprobada(boolean flagAprobada) {
		this.flagAprobada = flagAprobada;
	}


	public boolean isFlagLiberadaDsllo() {
		return flagLiberadaDsllo;
	}

	public void setFlagLiberadaDsllo(boolean flagLiberadaDsllo) {
		this.flagLiberadaDsllo = flagLiberadaDsllo;
	}

	public boolean isFlagReabrir() {
		return flagReabrir;
	}

	public void setFlagReabrir(boolean flagReabrir) {
		this.flagReabrir = flagReabrir;
	}

	public boolean isFlagGuardar() {
		return flagGuardar;
	}
	
	public void setFlagGuardar(boolean flagGuardar) {
		this.flagGuardar = flagGuardar;
	}


	public String getdEstadoinit() {
		return dEstadoinit;
	}

	public void setdEstadoinit(String dEstadoinit) {
		this.dEstadoinit = dEstadoinit;
	}

	public String getTablaCotizacion() {
		return tablaCotizacion;
	}

	public void setTablaCotizacion(String tablaCotizacion) {
		this.tablaCotizacion = tablaCotizacion;
	}

	public boolean isBotonAnularReq() {
		return botonAnularReq;
	}

	public void setBotonAnularReq(boolean botonAnularReq) {
		this.botonAnularReq = botonAnularReq;
	}

	public boolean isBotonAprobarReq() {
		return botonAprobarReq;
	}

	public void setBotonAprobarReq(boolean botonAprobarReq) {
		this.botonAprobarReq = botonAprobarReq;
	}

	public boolean isBotonLiberadaDslloReq() {
		return botonLiberadaDslloReq;
	}

	public void setBotonLiberadaDslloReq(boolean botonLiberadaDslloReq) {
		this.botonLiberadaDslloReq = botonLiberadaDslloReq;
	}

	public boolean isBotonReabrirReq() {
		return botonReabrirReq;
	}

	public void setBotonReabrirReq(boolean botonReabrirReq) {
		this.botonReabrirReq = botonReabrirReq;
	}

	public boolean isBotonGuardarReq() {
		return botonGuardarReq;
	}

	public void setBotonGuardarReq(boolean botonGuardarReq) {
		this.botonGuardarReq = botonGuardarReq;
	}

	public boolean isMostrarBotonAnulada() {
		return mostrarBotonAnulada;
	}

	public void setMostrarBotonAnulada(boolean mostrarBotonAnulada) {
		this.mostrarBotonAnulada = mostrarBotonAnulada;
	}

	public boolean isMostrarBotonEnviada() {
		return mostrarBotonEnviada;
	}

	public void setMostrarBotonEnviada(boolean mostrarBotonEnviada) {
		this.mostrarBotonEnviada = mostrarBotonEnviada;
	}

	public boolean isMostrarBotonAprobada() {
		return mostrarBotonAprobada;
	}

	public void setMostrarBotonAprobada(boolean mostrarBotonAprobada) {
		this.mostrarBotonAprobada = mostrarBotonAprobada;
	}

	public boolean isMostrarBotonLiberadaDsllo() {
		return mostrarBotonLiberadaDsllo;
	}

	public void setMostrarBotonLiberadaDsllo(boolean mostrarBotonLiberadaDsllo) {
		this.mostrarBotonLiberadaDsllo = mostrarBotonLiberadaDsllo;
	}

	public boolean isMostrarBotonReabrir() {
		return mostrarBotonReabrir;
	}

	public void setMostrarBotonReabrir(boolean mostrarBotonReabrir) {
		this.mostrarBotonReabrir = mostrarBotonReabrir;
	}

	public boolean isMostrarBotonGuardar() {
		return mostrarBotonGuardar;
	}

	public void setMostrarBotonGuardar(boolean mostrarBotonGuardar) {
		this.mostrarBotonGuardar = mostrarBotonGuardar;
	}

	public boolean isMostrarBotonAnularReq() {
		return mostrarBotonAnularReq;
	}

	public void setMostrarBotonAnularReq(boolean mostrarBotonAnularReq) {
		this.mostrarBotonAnularReq = mostrarBotonAnularReq;
	}

	public boolean isMostrarBotonAprobarReq() {
		return mostrarBotonAprobarReq;
	}

	public void setMostrarBotonAprobarReq(boolean mostrarBotonAprobarReq) {
		this.mostrarBotonAprobarReq = mostrarBotonAprobarReq;
	}

	public boolean isMostrarBotonLiberadaDslloReq() {
		return mostrarBotonLiberadaDslloReq;
	}

	public void setMostrarBotonLiberadaDslloReq(boolean mostrarBotonLiberadaDslloReq) {
		this.mostrarBotonLiberadaDslloReq = mostrarBotonLiberadaDslloReq;
	}

	public boolean isMostrarBotonReabrirReq() {
		return mostrarBotonReabrirReq;
	}

	public void setMostrarBotonReabrirReq(boolean mostrarBotonReabrirReq) {
		this.mostrarBotonReabrirReq = mostrarBotonReabrirReq;
	}

	public boolean isMostrarBotonGuardarReq() {
		return mostrarBotonGuardarReq;
	}

	public void setMostrarBotonGuardarReq(boolean mostrarBotonGuardarReq) {
		this.mostrarBotonGuardarReq = mostrarBotonGuardarReq;
	}

	public List<String> getListCompac() {
		return listCompac;
	}

	public void setListCompac(List<String> listCompac) {
		this.listCompac = listCompac;
	}

	public Integer getCompaIdccia() {
		return CompaIdccia;
	}

	public void setCompaIdccia(Integer compaIdccia) {
		CompaIdccia = compaIdccia;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public IMconca00Service getMconca00Service() {
		return mconca00Service;
	}

	public void setMconca00Service(IMconca00Service mconca00Service) {
		this.mconca00Service = mconca00Service;
	}

	public String getCompaCodcia() {
		return compaCodcia;
	}

	public void setCompaCodcia(String compaCodcia) {
		this.compaCodcia = compaCodcia;
	}

	public String getDstiptx() {
		return dstiptx;
	}

	public void setDstiptx(String dstiptx) {
		this.dstiptx = dstiptx;
	}

	public boolean isValidacionEst() {
		return validacionEst;
	}

	public void setValidacionEst(boolean validacionEst) {
		this.validacionEst = validacionEst;
	}

	public boolean isFlagEstadoInicial() {
		return flagEstadoInicial;
	}

	public void setFlagEstadoInicial(boolean flagEstadoInicial) {
		this.flagEstadoInicial = flagEstadoInicial;
	}

	public Integer getIdesesInit() {
		return idesesInit;
	}

	public void setIdesesInit(Integer idesesInit) {
		this.idesesInit = idesesInit;
	}

	public boolean getValidacionFecha() {
		return validacionFecha;
	}

	public void setValidacionFecha(boolean validacionFecha) {
		this.validacionFecha = validacionFecha;
	}

	public InputTextarea getInputTextDescqgColumn() {
		return inputTextDescqgColumn;
	}

	public void setInputTextDescqgColumn(InputTextarea inputTextDescqgColumn) {
		this.inputTextDescqgColumn = inputTextDescqgColumn;
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

	public boolean getVarTable() {
		return varTable;
	}

	public void setVarTable(boolean varTable) {
		this.varTable = varTable;
	}

	public SeTqdfqf00 getSeTqdfqf00() {
		return seTqdfqf00;
	}

	public void setSeTqdfqf00(SeTqdfqf00 seTqdfqf00) {
		this.seTqdfqf00 = seTqdfqf00;
	}

	public ITqdfqf00Service getTqdfqf00Service() {
		return tqdfqf00Service;
	}

	public void setTqdfqf00Service(ITqdfqf00Service tqdfqf00Service) {
		this.tqdfqf00Service = tqdfqf00Service;
	}

	public List<Tqdfqf00> getListTqdfqf00Temp() {
		return listTqdfqf00Temp;
	}

	public void setListTqdfqf00Temp(List<Tqdfqf00> listTqdfqf00Temp) {
		this.listTqdfqf00Temp = listTqdfqf00Temp;
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

	public boolean isNumModificar2() {
		return numModificar2;
	}

	public void setNumModificar2(boolean numModificar2) {
		this.numModificar2 = numModificar2;
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

	public Mestad00 getMestadoInit() {
		return mestadoInit;
	}

	public void setMestadoInit(Mestad00 mestadoInit) {
		this.mestadoInit = mestadoInit;
	}

	public Mconca00 getMconca00Session() {
		return mconca00Session;
	}

	public void setMconca00Session(Mconca00 mconca00Session) {
		this.mconca00Session = mconca00Session;
	}

	public boolean isCotizacionNueva() {
		return cotizacionNueva;
	}

	public void setCotizacionNueva(boolean cotizacionNueva) {
		this.cotizacionNueva = cotizacionNueva;
	}

	public String getPaternDec_PorceM() {
		return paternDec_PorceM;
	}

	public void setPaternDec_PorceM(String paternDec_PorceM) {
		this.paternDec_PorceM = paternDec_PorceM;
	}

	public Integer getFracDec_ValrtM() {
		return fracDec_ValrtM;
	}

	public void setFracDec_ValrtM(Integer fracDec_ValrtM) {
		this.fracDec_ValrtM = fracDec_ValrtM;
	}

	public String getPaternDec_ValrtM() {
		return paternDec_ValrtM;
	}

	public void setPaternDec_ValrtM(String paternDec_ValrtM) {
		this.paternDec_ValrtM = paternDec_ValrtM;
	}
	
}
