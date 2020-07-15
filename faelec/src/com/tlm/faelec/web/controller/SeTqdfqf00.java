package com.tlm.faelec.web.controller;

import java.io.Serializable;
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

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Controller;

//import com.ibm.disthub2.impl.formats.Multi.Constants.neighbors_table_type;
import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMcotes00Service;
import com.tlm.faelec.service.maestros.IMgente00Service;
import com.tlm.faelec.service.trans.ITqdfqf00Service;
import com.tlm.faelec.service.trans.ITqdpqp00Service;
import com.tlm.faelec.service.trans.ITqdrfg00Service;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;


@Controller
@ManagedBean
@SessionScoped
public class SeTqdfqf00  extends Control implements Serializable{
	
	private static final long serialVersionUID = 7213183176463668661L;
	
	@ManagedProperty(value = "#{tqdfqf00Service}")
	private ITqdfqf00Service tqdfqf00Service;
	
	@ManagedProperty(value = "#{tqdpqp00Service}")
	private ITqdpqp00Service tqdpqp00Service;
	
	@ManagedProperty(value = "#{tqdrqr00Service}")
	private ITqdrqr00Service tqdrqr00Service;
	
	@ManagedProperty(value = "#{tqdrfg00Service}")
	private ITqdrfg00Service tqdrfg00Service;
	
	@ManagedProperty("#{mgente00Service}")
	private IMgente00Service mgente00Service;
	
	@ManagedProperty("#{mcotes00Service}")
	private IMcotes00Service mcotes00Service;	
		
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	@ManagedProperty(value = "#{seTqigqg00}")
	private SeTqigqg00 seTqigqg00;
	
	@ManagedProperty(value = "#{seTqdrqr00}")
	private SeTqdrqr00 seTqdrqr00;
	
	@ManagedProperty(value = "#{seMgente00}")
	private SeMgente00 seMgente00;
	
	//@ManagedProperty(value = "#{seTqdpqp00}")
	private SeTqdpqp00 seTqdpqp00;

	private Tqigqg00 tqigqg00;
	
	private Tqdfqf00 tqdfqf00New;
	private Tqdfqf00 tqdfqf00Clone;
	private List<Tqdfqf00> listTqdfqf00;
	private List<Tqdfqf00> filteredTqdfqf00;
	private boolean infoGuardada;
	
	private List<Tqdpqp00> filteredTqdpqp00;
	private List<Tqdpqp00> listTqdpqp00Remove;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	
	private String tabla;
	private String accion;
	private String titulo;
	private String paternDec_CantiM;
	private Integer fracDec_CantiM;
	
	private InputText inputTextIdmpqfColumn;
	private InputText inputTextIdmqqfColumn;
	//private InputText inputTextIdtfqfColumn;
	private InputText inputTextIdfcqfColumn;
	private InputText inputTextPzdiqfColumn;
	private InputTextarea inputTextAreaDecpqfColumn;
	
	private String button;
	private String update;
	
	private Integer numCuota;
	private boolean numNuevo;
	private boolean numModificar;
	private Double totalCuota;
	private Double totalProCuota;
	
	//Carga de datos Req - Prod
	private Tqdpqp00 tqdpqp00;
	private Tqdrqr00 tqdrqr00;
	private List<Tqdpqp00> listTqdpqp00;
	private List<Tqdrqr00> listTqdrqr00;
	//private List<Tqdpqp00> listTqdpqp00Aux;

	private Tqdfqf00 tqdfqf00;
	private Tqdrfg00 tqdrfg00;
	private Tqdpqp00 Tqdfqf00Aux;
	private List<Tqdrfg00> listTqdrfg00Aux;
	private List<Tqdrfg00> listTqdrfg00;
	private List<Tqdrfg00> filteredTqdrfg00;
	private boolean flagTqdrfg00;
	private boolean flagTqdfqf00;
	
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	
	private List<Tqdrfg00> listTqdrfg00Delete;
	private boolean flagCheck;
	private boolean flagCalculo;
	private boolean flagCell;
	private List<Mgente00> listMgenteCliente;
	private List<Mgente00> listMgenteClienteAux;
	private List<Mgente00> filteredMgenteCliente;
	
	private List<Mcotes00> listMcotes00;
	private List<Mcotes00> filteredMcotes00;
	private boolean flagLista;
	

	@PostConstruct
	public void init() {
		try {
			super.init("TQDFQF00");
			tqdfqf00 = new Tqdfqf00();
			tqdrqr00 = new Tqdrqr00();
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
			paternDec_CantiM = calcularPatern(fracDec_CantiM.toString());
			numNuevo = false;
			numModificar = false;
			flagTqdrfg00=false;
			flagCheck = false;
			flagCalculo = false;
			flagCell = false;
			flagLista = false;
			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Maestro MPRECI01 nosorio-24102017
	private void cargarListaClienteporSigla() {
		try{
			tqdfqf00.getTqigqg00().getMgente00().getAliate();
			Mgente00 mgente00Aux = new Mgente00();
			mgente00Aux.setMtipre00(new Mtipre00());
			mgente00Aux.getMtipre00().setTipore(Utils.paramsRb.getString("mgente_codtte_cliente"));
			mgente00Aux.setRegite(true);
			List<Mgente00> listMgenteClienteAux  = new ArrayList<Mgente00>();
			listMgenteCliente = mgente00Service.listMgente00Cliente(mgente00Aux, getSeControlFactura().getCompaniasUsu());
			for(Mgente00 obj : listMgenteCliente){
				if(obj.getSigrte().equalsIgnoreCase(tqdfqf00.getTqigqg00().getMgente00().getSigrte())){
					listMgenteClienteAux.add(obj);
				}
				
			}
			setListMgenteCliente(listMgenteClienteAux);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void buttonAction(ActionEvent actionEvent) {
		this.setButton("OK");
    }
	
	 public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	 }
	
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try{
			 seListas.actualizarListas(NombresListas.listMcotes00); 
			 seListas.actualizarListas(NombresListas.listMgenusMedioPago); 
			 seListas.actualizarListas(NombresListas.listMgenus00MonedaCot);
			 seListas.actualizarListas(NombresListas.listMgenteCliente);
			 seListas.actualizarListas(NombresListas.listTqdpqp00Aux);
			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	
	public void cargarDatos(Tqdfqf00 tqdfqf00) {
		//listTqdfqf00 = seTqigqg00.consultarListTqdfqf00(tqigqg00);
		listTqdfqf00 = tqdfqf00Service.listTqdfqf00ByCriteria(tqdfqf00,getSeControlFactura().getCompaniasUsu());
		//UtilsJSF.resetDTable(":formDetalle:tabView:dTableTqdfqf00");
	}
	
	/*public void cargarDatosTqdrfg00(Tqdfqf00 tqdfqf00) {
		flagTqdrfg00 = false;
		tqdrfg00 = new Tqdrfg00();
		
		tqdrfg00.setTqdfqf00(tqdfqf00);
		listTqdrfg00 = tqdrfg00Service.getTqdrfg00ByCuota(tqdrfg00);
		if(listTqdrfg00.isEmpty()){
			flagTqdrfg00 = true;
		}
		//UtilsJSF.resetDTable(":formDetalle:tabView:dTableTqdfqf00");
	}*/
	
	//Maestro MPRECI01 nosorio-24102017
	public void cargarListaContactosPorGrupo2(Mgente00 mgente00) {
		try{
			
			Mcotes00 mcotesAux = new Mcotes00();
			mcotesAux.setRegtes(true);
			listMcotes00 = mcotes00Service.listMcotes00Criteria(mcotesAux, getSeControlFactura().getCompaniasUsu());
			List<Mcotes00> listMcotes00Aux  = new ArrayList<Mcotes00>();
			for(Mcotes00 obj : listMcotes00){
				if(obj.getMgente001()!=null){
					if(obj.getMgente001().getIdtrte() == (mgente00.getIdtrte())){
						listMcotes00Aux.add(obj);
					}	
				}	
			}
			
			setListMcotes00(listMcotes00Aux);
			//flagLista = false;
			RequestContext.getCurrentInstance().reset("formDetalle");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			
			
			tqdfqf00 = new Tqdfqf00();
			tqdfqf00.setRegcqf(true);
			tqdrqr00 = new Tqdrqr00();
			tqdrqr00.setRegcqr(true);
			tqdfqf00.setTqdrfg00s(new ArrayList<Tqdrfg00>());
			setTqdrfg00(null);
			flagCalculo = false;
			reiniciarListasMaestro();
			tqdfqf00.setTqigqg00(seTqigqg00.getTqigqg00());
			tqdrqr00.setTqigqg00(seTqigqg00.getTqigqg00());
			
			if(!seTqigqg00.isCotizacionNueva()){
				
				cargarDatos(tqdfqf00);
				cargarListaClienteporSigla();
				generarNumeroCuota(tqdfqf00);
				seListas.cargarDatosReqProd(tqdrqr00);
			
			seMgente00.setFlagLista(false);
			cargarListaContactosPorGrupo2(tqdfqf00.getTqigqg00().getMgente00());
			
			//cargarDatosReqProd(tqdrqr00);
			llenarProductoServicio();
				
			}
			
			
			tqdfqf00Clone = (Tqdfqf00) tqdfqf00.clone();
			numNuevo = true;
			numModificar = false;
			seTqigqg00.setNumModificar2(false);
			seMgente00.setFlagLista(false);
			totalCuota = 0.0;
			listTqdrfg00Aux = tqdfqf00.getTqdrfg00s();
			UtilsJSF.resetDTable(":formDetalle:tabView:dTableTqdfqf00");
			 RequestContext.getCurrentInstance().update(":formDetalle");
			 RequestContext.getCurrentInstance().update(":formTqdfqf00Detalle");
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event){ 
		try {
			
			setAccion(Constantes.ACCION_MODIFICAR);//Modificar registro
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			numModificar = true;
			numNuevo =false;
			seTqigqg00.setNumModificar2(false);
			seMgente00.setFlagLista(false);
			tqdfqf00.setRegcqf(true);
			tqdfqf00.setTqigqg00(seTqigqg00.getTqigqg00());
			
			Tqdfqf00 tqdfqf00Rs = new Tqdfqf00();
			tqdfqf00Rs = tqdfqf00Service.cargarDetalles(tqdfqf00.getIdcpqf());
			tqdfqf00.setTqdrfg00s(tqdfqf00Rs.getTqdrfg00s());
			flagCalculo = false;
			//cargarDatosReqProd(tqdrqr00);
			
			//Aqui
			
			
			reiniciarListasMaestro();
			totalCuota = 0.0;
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdfqf00.getMgenus001(), tqdfqf00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdfqf00.getMgenus002(), tqdfqf00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdfqf00.getMgente00(), tqdfqf00.getMgente00().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdfqf00.getMcotes00(), null);

			}
			validarListasTqdrfg00();
			listTqdrfg00Aux = tqdfqf00.getTqdrfg00s();
			carcularValorCuota();
			
			cargarDatos(tqdfqf00);
			cargarListaClienteporSigla();
				
			seMgente00.setFlagLista(true);
			seMgente00.cargarListaContactosPorGrupo(tqdfqf00.getMgente00());
			seListas.cargarDatosReqProd(tqdrqr00);
			//llenarProductoServicio();
			tqdfqf00Clone = (Tqdfqf00) tqdfqf00.clone();
			infoGuardada = false;
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}	
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			
			getSeListas().actualizarListasCompania(NombresListas.listMgenteCliente, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenus00MonedaCot, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusMedioPago , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMcotes00, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listTqdpqp00Aux, listMconca00ActualizarListas);
			//llenarProductoServicio();
			//RequestContext.getCurrentInstance().update("formTqdfqf00Detalle");
			}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void autocompleteActualizarListas (){
		try{
			if(tqdfqf00.getMgenus001() != null){
			actualizarListasCompania(tqdfqf00.getMgenus001().getCodius());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			tqdfqf00.setUsecqf(getSeControlFactura().codusu);
			tqdfqf00.setPrgcqf(getSeControlFactura().aplusu);
			tqdfqf00.setFeacqf(new Date());
			tqdfqf00.setMaqcqf(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	//Limpia el filtro del DataTable
	public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:tabView:dTableTqdfqf00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:tabView:dTableTqdfqf00");
		}
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(tqdfqf00.getMgenus001()) && permisoCampos.get("idmpqf").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idmpqf")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdmpqfColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdmpqfColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdmpqfColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdmpqfColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqdfqf00.getMgenus002()) && permisoCampos.get("idmqqf").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idmqqf")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdmqqfColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdmqqfColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdmqqfColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdmqqfColumn.getClientId());	
		}

		if(Utils.isEmptyOrNull(tqdfqf00.getMcotes00()) && permisoCampos.get("idfcqf").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idfcqf")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdfcqfColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdfcqfColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdfcqfColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdfcqfColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqdfqf00.getDecpqf()) && permisoCampos.get("decpqf").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("decpqf")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextAreaDecpqfColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextAreaDecpqfColumn.getClientId());
			validacion = true;
		}else{
			inputTextAreaDecpqfColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextAreaDecpqfColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(tqdfqf00.getPzdiqf()) && permisoCampos.get("pzdiqf").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("pzdiqf")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextPzdiqfColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextPzdiqfColumn.getClientId());
			validacion = true;
		}else{
			inputTextPzdiqfColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextPzdiqfColumn.getClientId());	
		}
		
	
		
		return validacion;
	}
	
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
//		
		//Validacion lista  medio pago
		if(tqdfqf00.getMgenus001() != null &&  tqdfqf00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idmpqf")+" "+ tqdfqf00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Moneda Cotizacion
		if(tqdfqf00.getMgenus002() != null &&  tqdfqf00.getMgenus002().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idmqf")+" "+ tqdfqf00.getMgenus002().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista Cliente o Prospecto
		if(tqdfqf00.getMgente00() != null &&  tqdfqf00.getMgente00().getRegite()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idtfqf")+" "+ tqdfqf00.getMgente00().getCodite() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Organizaci�n Ventas
		if(tqdfqf00.getMcotes00() != null &&  tqdfqf00.getMcotes00().isRegtes()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idfcqf")+" "+ tqdfqf00.getMcotes00().getCoctes() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}		
		return validacion;
	}
	
	public void generarNumeroCuota(Tqdfqf00 tqdfqf00){
		try {
			Integer mayor = 0;
			listTqdfqf00 = tqdfqf00Service.listTqdfqf00ByCriteria(tqdfqf00,getSeControlFactura().getCompaniasUsu());
			List<Integer> listNrcpqf = new ArrayList<Integer>(); 
			
			for(Tqdfqf00 obj : listTqdfqf00){
				
				if(obj != null){
					listNrcpqf.add(obj.getNrcpqf());
				}
			}
			
			if(listNrcpqf.isEmpty()){
				mayor = 0;
			}else{
				mayor = Collections.max(listNrcpqf);
			}
			
			numCuota = mayor+1;
			
		}
		catch (Exception e) {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
			}
		 }
	public void carcularValorCuota(){
		try{
			
			tqdrfg00 = new Tqdrfg00();
			
			if(flagCalculo){ //Cuando se presiono el Boton recalcular cuota
				System.out.println("Entro a calcular cuota");
				setTotalCuota(0.0);
				for(Tqdrfg00 obj : listTqdrfg00Aux){
					
	        		if(obj.getTqdpqp00().getVamiqp().equalsIgnoreCase("M")){
	        			if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVumpqp()!=null){
	        				  totalProCuota=obj.getCafpfg() * obj.getTqdpqp00().getVumpqp();
							  totalCuota = totalCuota + totalProCuota;
	        			   }
	        		}else{
	        			if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVupiqp()!=null){
	        				  totalProCuota=obj.getCafpfg() * obj.getTqdpqp00().getVupiqp();
							  totalCuota = totalCuota + totalProCuota;
	        			}
	        			
	        		}
	        		if(obj.getCafpfg().equals(null)){
						obj.setCafpfg(0.0);
					}
	        	}
				
				flagCalculo = false;
			}else{						//Calculo automatico cuando se abre el dialogo
				tqdrfg00.setTqdfqf00(getTqdfqf00());
				listTqdrfg00 = tqdrfg00Service.getTqdrfg00ByCuota(tqdrfg00);
				for(Tqdrfg00 obj: listTqdrfg00){
					if(obj.getTqdpqp00().getVamiqp().equalsIgnoreCase("M")){
						if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVumpqp()!=null){
							totalProCuota=obj.getCafpfg() * obj.getTqdpqp00().getVumpqp();
							totalCuota = totalCuota + totalProCuota;
						}
					}else{
						if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVupiqp()!=null){
							totalProCuota=obj.getCafpfg() * obj.getTqdpqp00().getVupiqp();
							totalCuota = totalCuota + totalProCuota;
						}
						
					}
					if(obj.getCafpfg()==null){
						obj.setCafpfg(0.0);
					}
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	//valida el dato del campo CAFPFG (cant a facturar)
	public void onCellEdit(CellEditEvent event){
		try{
			setFlagCell(false);
			tqdrfg00 = new Tqdrfg00();
			tqdrfg00.setTqdfqf00(getTqdfqf00());
			setTotalCuota(0.0);
		        Object oldValue = event.getOldValue();
		        Object newValue = event.getNewValue();
		        if(newValue != null && !newValue.equals(oldValue)) {
					for(Tqdrfg00 obj : listTqdrfg00Aux){
						if(obj.getCafpfg()!=null){
							
							if(obj.getCafpfg().equals((Double) newValue)){
								if((Double) newValue<= obj.getTqdpqp00().getCacoqp()){
							        tqdrfg00.setCafpfg((Double) newValue);
							        flagCalculo = true;
							        tqdfqf00.getTqdrfg00s().add(tqdrfg00);
							        //carcularValorCuota();
										
								}else{
									flagCalculo = false;
									UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "La cantidad a facturar debe ser menor o igual a la cantidad cotizada");
									return;
								}	
							}
							
						}
						
						if(obj.getTqdpqp00().getVamiqp().equalsIgnoreCase("M")){
		        			if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVumpqp()!=null){
		        				  totalProCuota=obj.getCafpfg() * obj.getTqdpqp00().getVumpqp();
								  totalCuota = totalCuota + totalProCuota;
								  tqdfqf00.setVacpqf(totalCuota);
								  setFlagCell(true);
		        			   }
		        		}else{
		        			if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVupiqp()!=null){
		        				  totalProCuota=obj.getCafpfg() * obj.getTqdpqp00().getVupiqp();
								  totalCuota = totalCuota + totalProCuota;
								  tqdfqf00.setVacpqf(totalCuota);
								  setFlagCell(true);
		        			}
		        			
		        		}
						if(obj.getCafpfg()==null){
							obj.setCafpfg(0.0);
						}
						
						
						
					}
		        	
		            //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
		            //FacesContext.getCurrentInstance().addMessage(null, msg);
		        }    
		        
			
		}
		catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Tqdrfg00>  consultarListTqdrfg00(){
		tqdrfg00 = new Tqdrfg00();
		tqdrfg00.setTqdfqf00(getTqdfqf00());
		listTqdrfg00 = tqdrfg00Service.getTqdrfg00ByCuota(tqdrfg00);
		return listTqdrfg00;
	}
	
	private void guardarDetalleProductoServicio(){
		try{
			tqdpqp00 = new Tqdpqp00();
			for(Tqdpqp00 obj: seListas.getListTqdpqp00Aux()){
				tqdpqp00 = obj;		
			}

			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				List<Tqdrfg00> listTqdrfg00Temp = new ArrayList<Tqdrfg00>();
				listTqdrfg00Temp = consultarListTqdrfg00();
				
				tqdfqf00.setTqdrfg00s(new ArrayList<Tqdrfg00>());
				if (!(listTqdrfg00Aux.isEmpty())){
					for(Tqdrfg00 obj : listTqdrfg00Aux){
						for(Tqdrfg00 obj2 : listTqdrfg00Temp){
							if(flagCheck){ //Cuando hay un evento de checkbox en algun momento
								if(obj.getIdqrfg()==obj2.getIdqrfg()){
									if(obj2.getCafpfg()==null){
										obj2.setCafpfg(0.0);
									}else{
										obj2.setCafpfg(obj.getCafpfg());
									}
									
									
									tqdfqf00.getTqdrfg00s().add(obj2);
									
								}else{
									tqdrfg00Service.removeTqdrfg00(obj2,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,obj2.toString(),obj2.toStringId()):null);

								}	
							}else{ //Cuando no se usan los checkbox en ningun momento
					    		tqdfqf00.setTqdrfg00s(new ArrayList<Tqdrfg00>());
								for(Tqdrfg00 item: listTqdrfg00Aux){	
							    	if(item.getTqdfqf00() == null){
							    		Tqdrfg00 tqdrfg00Aux = new Tqdrfg00();
							    		
							    		tqdrfg00Aux.setTqdfqf00(getTqdfqf00());
							    		tqdrfg00Aux.setTqdpqp00(tqdpqp00);
							    		if(item.getCafpfg()==null){
							    			tqdrfg00Aux.setCafpfg(0.0);
							    		}else{
							    			tqdrfg00Aux.setCafpfg(item.getCafpfg());
							    		}
							    		
							    		tqdrfg00Aux.setRegcfg(item.isRegcfg());
							    		tqdrfg00Aux.setUsecfg(getSeControlFactura().codusu);
							    		tqdrfg00Aux.setPrgcfg("Producto - Servicio " + getClass().getName());
							    		tqdrfg00Aux.setFeacfg(new Date());
							    		tqdrfg00Aux.setMaqcfg(getSeControlFactura().ip);
							    		tqdfqf00.getTqdrfg00s().add(tqdrfg00Aux);
							    		
							    	}else{
							    		if(item.getCafpfg()==null){
							    			item.setCafpfg(0.0);
							    		}
							    		tqdfqf00.getTqdrfg00s().add(item);
							    	}
							    }
								
							}
							
							if(obj.getIdqrfg()==0){
								//listTqdrfg00Aux = tqdfqf00.getTqdrfg00s();
								listTqdrfg00Aux = tqdfqf00.getTqdrfg00s();
								tqdfqf00.setTqdrfg00s(new ArrayList<Tqdrfg00>());
								//if (!Utils.isEmptyOrNull(listTqdrfg00Aux)){

								    for(Tqdrfg00 obj3: listTqdrfg00Aux){
								    	if(obj3.getCafpfg()==null){
								    		obj3.setCafpfg(0.0);
								    	}
								    		tqdfqf00.getTqdrfg00s().add(obj3);	    	
								    }
								//}

							}
							
						}
						//tqdfqf00.getTqdrfg00s().add(obj);
					}
				}else{ //Cuando se deseleccionan todos ls registros y la lista queda vacia
					tqdfqf00.setTqdrfg00s(new ArrayList<Tqdrfg00>());
					if(flagCheck){
						for(Tqdrfg00 obj: listTqdrfg00Temp){
							tqdrfg00Service.removeTqdrfg00(obj,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,obj.toString(),obj.toStringId()):null);
						}
					}else{
						for(Tqdrfg00 obj: listTqdrfg00Aux){	
						    	if(obj.getTqdfqf00() == null){
						    		Tqdrfg00 tqdrfg00Aux = new Tqdrfg00();
						    		
						    		tqdrfg00Aux.setTqdfqf00(getTqdfqf00());
						    		tqdrfg00Aux.setTqdpqp00(tqdpqp00);
						    		if(obj.getCafpfg()==null){
						    			tqdrfg00Aux.setCafpfg(0.0);
						    		}else{
						    			tqdrfg00Aux.setCafpfg(obj.getCafpfg());
						    		}
						    		
						    		tqdrfg00Aux.setRegcfg(obj.isRegcfg());
						    		tqdrfg00Aux.setUsecfg(getSeControlFactura().codusu);
						    		tqdrfg00Aux.setPrgcfg("Producto - Servicio " + getClass().getName());
						    		tqdrfg00Aux.setFeacfg(new Date());
						    		tqdrfg00Aux.setMaqcfg(getSeControlFactura().ip);
						    		tqdfqf00.getTqdrfg00s().add(tqdrfg00Aux);
						    	}else{
						    		if(obj.getCafpfg()==null){
						    			obj.setCafpfg(0.0);
						    		}
						    		tqdfqf00.getTqdrfg00s().add(obj);
						    	}
						    }
					
					}
				}
			}
			if(getAccion().equals(Constantes.ACCION_NUEVO)) //Cuando se esta creando una nueva cuota
			{
				
				tqdfqf00.setTqdrfg00s(new ArrayList<Tqdrfg00>());
				if (!Utils.isEmptyOrNull(listTqdrfg00Aux)){
				    for(Tqdrfg00 obj: listTqdrfg00Aux){	
				    	if(obj.getTqdfqf00() == null){
				    		Tqdrfg00 tqdrfg00Aux = new Tqdrfg00();
				    		
				    		tqdrfg00Aux.setTqdfqf00(getTqdfqf00());
				    		tqdrfg00Aux.setTqdpqp00(tqdpqp00);
				    		if(obj.getCafpfg()==null){
				    			tqdrfg00Aux.setCafpfg(0.0);
				    		}else{
				    			tqdrfg00Aux.setCafpfg(obj.getCafpfg());
				    		}
				    		
				    		tqdrfg00Aux.setRegcfg(obj.isRegcfg());
				    		tqdrfg00Aux.setUsecfg(getSeControlFactura().codusu);
				    		tqdrfg00Aux.setPrgcfg("Producto - Servicio " + getClass().getName());
				    		tqdrfg00Aux.setFeacfg(new Date());
				    		tqdrfg00Aux.setMaqcfg(getSeControlFactura().ip);
				    		tqdfqf00.getTqdrfg00s().add(tqdrfg00Aux);
				    	}else{
				    		if(obj.getCafpfg()==null){
				    			obj.setCafpfg(0.0);
				    		}
				    		tqdfqf00.getTqdrfg00s().add(obj);
				    	}
				    }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void llenarProductoServicio(){
		seTqdpqp00 = new SeTqdpqp00();
		if(tqdfqf00.getTqdrfg00s().isEmpty() || tqdfqf00.getTqdrfg00s() == null){//Cuando el registro es nuevo
			for (Tqdpqp00 item : seListas.getListTqdpqp00Aux()) {
				tqdrfg00= new Tqdrfg00();
				tqdrfg00.setTqdfqf00(tqdfqf00);
				tqdrfg00.setTqdpqp00(item);
				tqdrfg00.setRegcfg(true);
				tqdrfg00.setUsecfg(getSeControlFactura().codusu);
				tqdrfg00.setPrgcfg("Producto - Servicio " + getClass().getName());
				tqdrfg00.setFeacfg(new Date());
				tqdrfg00.setMaqcfg(getSeControlFactura().ip);
				tqdfqf00.getTqdrfg00s().add(tqdrfg00);
			}
		}else{//Cuando el registro es por modificar
			for (Tqdpqp00 item : seListas.getListTqdpqp00Aux())  {
				if (!Utils.isEmptyOrNull(item.getMpropr00()) && validarProducto(item)==false) {
					tqdrfg00= new Tqdrfg00();
					tqdrfg00.setTqdfqf00(tqdfqf00);
					tqdrfg00.setTqdpqp00(item);
					tqdrfg00.setRegcfg(true);
					tqdrfg00.setUsecfg(getSeControlFactura().codusu);
					tqdrfg00.setPrgcfg("Producto - Servicio" + getClass().getName());
					tqdrfg00.setFeacfg(new Date());
					tqdrfg00.setMaqcfg(getSeControlFactura().ip);
					tqdfqf00.getTqdrfg00s().add(tqdrfg00);
				}
			listTqdrfg00Aux = tqdfqf00.getTqdrfg00s();
		}
	  }
	}
	
	private boolean validarProducto(Tqdpqp00 obj){
		boolean respuesta = false;
		try {
			for (Tqdrfg00 item : tqdfqf00.getTqdrfg00s()) {
				 if (item.getTqdpqp00().getIdqpqp()==(obj.getIdqpqp())) {
					respuesta = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return respuesta;	
	}
	
	private boolean validarListasTqdrfg00(){
		boolean validacion = true;
//		super.init("MGETMA00");
		if(!Utils.isEmptyOrNull(tqdfqf00.getTqdrfg00s())){
			for(Tqdrfg00 tqdrfg : tqdfqf00.getTqdrfg00s()){
				  if(tqdrfg.getTqdpqp00() != null && tqdrfg.getTqdpqp00().isRegcqp() == false){
				     UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
					 getIdiomasHm().get("marcat")+" "+tqdrfg.getTqdpqp00().getDeprqp()+" "+
							 tqdrfg.getTqdpqp00().getDeprqp()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
				   //Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				     UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdrfg.getTqdpqp00(),null);
				     validacion = false;
				  }
			}
		}
		
		return validacion;
	}
	
	
	public void save(ActionEvent event) {
		try {
			if(!seTqigqg00.isCotizacionNueva()){
				//Se validan los datos que son requeridos
				if (validarDatosRequeridos()) {
					return;
				}
				if(tqdfqf00.getMgente00()==null){
					tqdfqf00.getTqigqg00().getMgente00();
					tqdfqf00.setMgente00(tqdfqf00.getTqigqg00().getMgente00());	
				}
				
				if(validarListasTqdrfg00()==false){
					return;
				}
				
				if(getAccion().equals(Constantes.ACCION_NUEVO))
				{
					if(!flagCell){
						tqdfqf00.setVacpqf(totalCuota);
					}
					tqdfqf00.setNrcpqf(numCuota);
					
					numNuevo = false;
				}
				guardarDetalleProductoServicio();
				
				colaEstandar();
				
				
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					if(!flagCell){
						tqdfqf00.setVacpqf(totalCuota);
					}
					
					if(validarListas()==false){
						tqdfqf00Service.save(tqdfqf00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdfqf00.toString(),tqdfqf00.toStringId(),tqdfqf00Clone.toString()):null);
						seTqigqg00.getTqigqg00().getTqdfqf00s().add(tqdfqf00);
						numModificar = false;

					}else return;
				} else {
					tqdfqf00Service.save(tqdfqf00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdfqf00.toString(),tqdfqf00.toStringId(),null):null);
					
				 }
			flagCheck = false;
			infoGuardada = true;
			cargarDatos(tqdfqf00);
		    UtilsJSF.closeDialog("dlgNuevoUpdateTqdfqf00");
		    UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		    RequestContext.getCurrentInstance().update(":formDetalle:tabView:dTableTqdfqf00");
		    RequestContext.getCurrentInstance().update(":formTqdfqf00Detalle");
		    		    
		}else{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No se ha creado una cotizaci�n para este registro");
			RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdfqf00"); 
			return;	
			
		}
	}
	catch (Exception e) {	
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
	}
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			seTqigqg00.setNumModificar2(false);
			tqdfqf00 = (Tqdfqf00) event.getComponent().getAttributes().get("tqdfqf00");
			tqdfqf00Service.removeTqdfqf00(tqdfqf00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,tqdfqf00.toString(),tqdfqf00.toStringId()):null);
			cargarDatos(tqdfqf00);
			UtilsJSF.resetDTable(":formDetalle:tabView:dTableTqdfqf00");
			 RequestContext.getCurrentInstance().update(":formDetalle");
			 RequestContext.getCurrentInstance().update(":formTqdfqf00Detalle");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	public void preRenderView(){
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialog(CloseEvent event) {
		setUpdate(null);
		RequestContext.getCurrentInstance().update(":formTqdfqf00Detalle");
		
	}
	
	public void onRowSelect(SelectEvent event) {
	    flagCheck = true;
 
	}

	public void onRowUnselect(UnselectEvent event) {
	    flagCheck = true;
	    listTqdrfg00Delete = listTqdrfg00Aux;
	}
	
	public ITqdfqf00Service getTqdfqf00Service() {
		return tqdfqf00Service;
	}
	public void setTqdfqf00Service(ITqdfqf00Service tqdfqf00Service) {
		this.tqdfqf00Service = tqdfqf00Service;
	}
	public SeListas getSeListas() {
		return seListas;
	}
	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}
	public SeTqigqg00 getSeTqigqg00() {
		return seTqigqg00;
	}
	public void setSeTqigqg00(SeTqigqg00 seTqigqg00) {
		this.seTqigqg00 = seTqigqg00;
	}
	public Tqdfqf00 getTqdfqf00() {
		if(tqdfqf00==null){
			tqdfqf00 = new Tqdfqf00();
		}
		return this.tqdfqf00;
	}
	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}
	public Tqdfqf00 getTqdfqf00Clone() {
		return tqdfqf00Clone;
	}
	public void setTqdfqf00Clone(Tqdfqf00 tqdfqf00Clone) {
		this.tqdfqf00Clone = tqdfqf00Clone;
	}
	public List<Tqdfqf00> getListTqdfqf00() {
		return listTqdfqf00;
	}
	public void setListTqdfqf00(List<Tqdfqf00> listTqdfqf00) {
		this.listTqdfqf00 = listTqdfqf00;
	}
	public List<Tqdfqf00> getFilteredTqdfqf00() {
		return filteredTqdfqf00;
	}
	public void setFilteredTqdfqf00(List<Tqdfqf00> filteredTqdfqf00) {
		this.filteredTqdfqf00 = filteredTqdfqf00;
	}
	public boolean isInfoGuardada() {
		return infoGuardada;
	}
	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
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
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
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


	public String getPaternDec_CantiM() {
		return paternDec_CantiM;
	}

	public void setPaternDec_CantiM(String paternDec_CantiM) {
		this.paternDec_CantiM = paternDec_CantiM;
	}
	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}


	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}


	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public Tqdpqp00 getTqdpqp00() {
		if(tqdpqp00==null)
		{
			tqdpqp00= new Tqdpqp00();
		}
		return tqdpqp00;

	}

	public void setTqdpqp00(Tqdpqp00 tqdpqp00) {
		this.tqdpqp00 = tqdpqp00;
	}

	public List<Tqdpqp00> getFilteredTqdpqp00() {
		return filteredTqdpqp00;
	}

	public void setFilteredTqdpqp00(List<Tqdpqp00> filteredTqdpqp00) {
		this.filteredTqdpqp00 = filteredTqdpqp00;
	}

	public List<Tqdpqp00> getListTqdpqp00Remove() {
		return listTqdpqp00Remove;
	}

	public void setListTqdpqp00Remove(List<Tqdpqp00> listTqdpqp00Remove) {
		this.listTqdpqp00Remove = listTqdpqp00Remove;
	}

	public InputText getInputTextIdmpqfColumn() {
		return inputTextIdmpqfColumn;
	}

	public void setInputTextIdmpqfColumn(InputText inputTextIdmpqfColumn) {
		this.inputTextIdmpqfColumn = inputTextIdmpqfColumn;
	}

	public InputText getInputTextIdmqqfColumn() {
		return inputTextIdmqqfColumn;
	}

	public void setInputTextIdmqqfColumn(InputText inputTextIdmqqfColumn) {
		this.inputTextIdmqqfColumn = inputTextIdmqqfColumn;
	}

	public InputText getInputTextIdfcqfColumn() {
		return inputTextIdfcqfColumn;
	}

	public void setInputTextIdfcqfColumn(InputText inputTextIdfcqfColumn) {
		this.inputTextIdfcqfColumn = inputTextIdfcqfColumn;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public Integer getNumCuota() {
		return numCuota;
	}

	public void setNumCuota(Integer numCuota) {
		this.numCuota = numCuota;
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

	public Double getTotalCuota() {
		return totalCuota;
	}

	public void setTotalCuota(Double totalCuota) {
		this.totalCuota = totalCuota;
	}

	public List<Tqdpqp00> getListTqdpqp00() {
		return listTqdpqp00;
	}

	public void setListTqdpqp00(List<Tqdpqp00> listTqdpqp00) {
		this.listTqdpqp00 = listTqdpqp00;
	}

	public ITqdpqp00Service getTqdpqp00Service() {
		return tqdpqp00Service;
	}

	public void setTqdpqp00Service(ITqdpqp00Service tqdpqp00Service) {
		this.tqdpqp00Service = tqdpqp00Service;
	}

	public ITqdrqr00Service getTqdrqr00Service() {
		return tqdrqr00Service;
	}

	public void setTqdrqr00Service(ITqdrqr00Service tqdrqr00Service) {
		this.tqdrqr00Service = tqdrqr00Service;
	}

	public Tqdrqr00 getTqdrqr00() {
		return tqdrqr00;
	}

	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}

	public List<Tqdrqr00> getListTqdrqr00() {
		return listTqdrqr00;
	}

	public void setListTqdrqr00(List<Tqdrqr00> listTqdrqr00) {
		this.listTqdrqr00 = listTqdrqr00;
	}

	/*public List<Tqdpqp00> getListTqdpqp00Aux() {
		return listTqdpqp00Aux;
	}

	public void setListTqdpqp00Aux(List<Tqdpqp00> listTqdpqp00Aux) {
		this.listTqdpqp00Aux = listTqdpqp00Aux;
	}*/

	public Tqdpqp00 getTqdfqf00Aux() {
		return Tqdfqf00Aux;
	}

	public void setTqdfqf00Aux(Tqdpqp00 tqdfqf00Aux) {
		Tqdfqf00Aux = tqdfqf00Aux;
	}

	public List<Tqdrfg00> getListTqdrfg00() {
		return listTqdrfg00;
	}

	public void setListTqdrfg00(List<Tqdrfg00> listTqdrfg00) {
		this.listTqdrfg00 = listTqdrfg00;
	}

	public Tqdrfg00 getTqdrfg00() {
		return tqdrfg00;
	}

	public void setTqdrfg00(Tqdrfg00 tqdrfg00) {
		this.tqdrfg00 = tqdrfg00;
	}

	public ITqdrfg00Service getTqdrfg00Service() {
		return tqdrfg00Service;
	}

	public void setTqdrfg00Service(ITqdrfg00Service tqdrfg00Service) {
		this.tqdrfg00Service = tqdrfg00Service;
	}

	public boolean isFlagTqdrfg00() {
		return flagTqdrfg00;
	}

	public void setFlagTqdrfg00(boolean flagTqdrfg00) {
		this.flagTqdrfg00 = flagTqdrfg00;
	}

	public boolean isFlagTqdfqf00() {
		return flagTqdfqf00;
	}

	public void setFlagTqdfqf00(boolean flagTqdfqf00) {
		this.flagTqdfqf00 = flagTqdfqf00;
	}

	public Tqdfqf00 getTqdfqf00New() {
		return tqdfqf00New;
	}

	public void setTqdfqf00New(Tqdfqf00 tqdfqf00New) {
		this.tqdfqf00New = tqdfqf00New;
	}

	public List<Tqdrfg00> getListTqdrfg00Aux() {
		return listTqdrfg00Aux;
	}

	public void setListTqdrfg00Aux(List<Tqdrfg00> listTqdrfg00Aux) {
		this.listTqdrfg00Aux = listTqdrfg00Aux;
	}

	public List<Tqdrfg00> getFilteredTqdrfg00() {
		return filteredTqdrfg00;
	}

	public void setFilteredTqdrfg00(List<Tqdrfg00> filteredTqdrfg00) {
		this.filteredTqdrfg00 = filteredTqdrfg00;
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

	public InputTextarea getInputTextAreaDecpqfColumn() {
		return inputTextAreaDecpqfColumn;
	}

	public void setInputTextAreaDecpqfColumn(InputTextarea inputTextAreaDecpqfColumn) {
		this.inputTextAreaDecpqfColumn = inputTextAreaDecpqfColumn;
	}

	public List<Tqdrfg00> getListTqdrfg00Delete() {
		return listTqdrfg00Delete;
	}

	public void setListTqdrfg00Delete(List<Tqdrfg00> listTqdrfg00Delete) {
		this.listTqdrfg00Delete = listTqdrfg00Delete;
	}

	public boolean isFlagCheck() {
		return flagCheck;
	}

	public void setFlagCheck(boolean flagCheck) {
		this.flagCheck = flagCheck;
	}

	public Double getTotalProCuota() {
		return totalProCuota;
	}

	public void setTotalProCuota(Double totalProCuota) {
		this.totalProCuota = totalProCuota;
	}

	public boolean isFlagCalculo() {
		return flagCalculo;
	}

	public void setFlagCalculo(boolean flagCalculo) {
		this.flagCalculo = flagCalculo;
	}

	public SeTqdpqp00 getSeTqdpqp00() {
		return seTqdpqp00;
	}

	public void setSeTqdpqp00(SeTqdpqp00 seTqdpqp00) {
		this.seTqdpqp00 = seTqdpqp00;
	}

	public List<Mgente00> getListMgenteCliente() {
		return listMgenteCliente;
	}

	public void setListMgenteCliente(List<Mgente00> listMgenteCliente) {
		this.listMgenteCliente = listMgenteCliente;
	}

	public List<Mgente00> getFilteredMgenteCliente() {
		return filteredMgenteCliente;
	}

	public void setFilteredMgenteCliente(List<Mgente00> filteredMgenteCliente) {
		this.filteredMgenteCliente = filteredMgenteCliente;
	}

	public IMgente00Service getMgente00Service() {
		return mgente00Service;
	}

	public void setMgente00Service(IMgente00Service mgente00Service) {
		this.mgente00Service = mgente00Service;
	}

	public List<Mgente00> getListMgenteClienteAux() {
		return listMgenteClienteAux;
	}

	public void setListMgenteClienteAux(List<Mgente00> listMgenteClienteAux) {
		this.listMgenteClienteAux = listMgenteClienteAux;
	}

	public SeTqdrqr00 getSeTqdrqr00() {
		return seTqdrqr00;
	}

	public void setSeTqdrqr00(SeTqdrqr00 seTqdrqr00) {
		this.seTqdrqr00 = seTqdrqr00;
	}

	public SeMgente00 getSeMgente00() {
		return seMgente00;
	}

	public void setSeMgente00(SeMgente00 seMgente00) {
		this.seMgente00 = seMgente00;
	}

	public boolean isFlagCell() {
		return flagCell;
	}

	public void setFlagCell(boolean flagCell) {
		this.flagCell = flagCell;
	}

	public InputText getInputTextPzdiqfColumn() {
		return inputTextPzdiqfColumn;
	}

	public void setInputTextPzdiqfColumn(InputText inputTextPzdiqfColumn) {
		this.inputTextPzdiqfColumn = inputTextPzdiqfColumn;
	}

	public List<Mcotes00> getFilteredMcotes00() {
		return filteredMcotes00;
	}

	public void setFilteredMcotes00(List<Mcotes00> filteredMcotes00) {
		this.filteredMcotes00 = filteredMcotes00;
	}

	public boolean isFlagLista() {
		return flagLista;
	}

	public void setFlagLista(boolean flagLista) {
		this.flagLista = flagLista;
	}

	public IMcotes00Service getMcotes00Service() {
		return mcotes00Service;
	}

	public void setMcotes00Service(IMcotes00Service mcotes00Service) {
		this.mcotes00Service = mcotes00Service;
	}

	public List<Mcotes00> getListMcotes00() {
		return listMcotes00;
	}

	public void setListMcotes00(List<Mcotes00> listMcotes00) {
		this.listMcotes00 = listMcotes00;
	}	
	
	
	
}
