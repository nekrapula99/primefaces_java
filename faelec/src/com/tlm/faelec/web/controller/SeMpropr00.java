package com.tlm.faelec.web.controller;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;


import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;


import org.springframework.stereotype.Controller;

import com.ibm.xtq.bcel.generic.NEW;
import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMpreci00Service;
import com.tlm.faelec.service.maestros.IMpropr00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;

import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Mpreci00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Mvarpr00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;

@Controller
@ManagedBean
@SessionScoped
public class SeMpropr00 extends Control implements Serializable{
	private static final long serialVersionUID = 1L;

	//Variables del Managedbean
	@ManagedProperty(value="#{seCabecera}")
	private SeCabecera seCabecera;
	
	@ManagedProperty(value = "#{mpropr00Service}")
	private IMpropr00Service mpropr00Service;
	
	@ManagedProperty(value = "#{mpreci00Service}")
	private IMpreci00Service mpreci00Service;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	private SeTqdpqp00 seTqdpqp00;
	
	private Mpropr00 mpropr00;
	private Mdespr00 mdespr00; 
	private Mpropr00 mpropr00Cop;
	private List<Mpropr00> listMpropr00;
	private List<Mpropr00> filteredMpropr00;
//	private List<Mdespr00> listMdespr00;
	private List<Mdespr00> filteredMdespr00;
	private List<Mdespr00> listMdespr00Remove;
	private Integer fracDec_CantiM;
	private Integer fracDec_ValruM;
	private Integer fracDec_PorceM;
	private String accionMdespr;
	
	//	Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	
	//Patern campos numericos
	private String paternDec_CantiM;
	private String paternDec_ValruM;
	private String paternDec_PorceM;
	
	//Visualizacion de pesta�as
	private boolean tabInformacionGnrl;
	private boolean tabOtrosDatos;
	private boolean tabDescOtrosIdiomas;
	//private boolean tabVariantes;
	private String[] pestanas;
	private boolean infoGuardada;
	private UploadedFile imagenPro;
	private boolean changeImage=false;
	private StreamedContent imageStrem;
	private String imagenAnterior;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	private InputText inputTextIdcmprColumn;
	private InputText inputTextIdliprColumn;
	private InputText inputTextIdslprColumn;
	private InputText inputTextIduiprColumn;
	private InputText inputTextIdtbprColumn;	
	
	private String unidadMedida;
	private Double valorPresci;
	
	//TabView
	private int tabView=0;
	
	//Metodos del ManagedBean
	
	@PostConstruct
	public void init() {
		try {
			super.init("MPROPR00");
			
			mpropr00 = new Mpropr00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
				
			//Se inician las variables donde se capturan la cantidad de decimales
	
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			setFracDec_CantiM(d1.intValue());
			
			Double d2=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_ValruM");
			setFracDec_ValruM(d2.intValue());
			
			Double d3=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_ValruM");
			setFracDec_PorceM(d3.intValue());
			

		   // paternDec_CantiM = calcularPatern(fracDec_CantiM.toString());
		    //paternDec_ValruM = calcularPatern(fracDec_ValruM.toString());
		    //paternDec_PorceM = calcularPatern(fracDec_PorceM.toString());
		    
		    //Visualizacion pesta�as
		     tabInformacionGnrl= false;
			 tabOtrosDatos= false;
			 tabDescOtrosIdiomas= false;
			 //tabVariantes= false;	
			 
		     cargarDatos();
    
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void mostrarPestana()
	{
		try{
		   pestanas=((String) getSeControlFactura().PARAMETROS.get("Vis_MaePro")).split(","); 
		   for (int i=0; i < pestanas.length;i++){
			   if(pestanas[i].equals("1"))
			   {
				   tabInformacionGnrl=true;
			   }
			   if(pestanas[i].equals("2"))
			   {
				   tabOtrosDatos=true;
			   }
			   if(pestanas[i].equals("3"))
			   {
				   tabDescOtrosIdiomas=true;
			   }
			  
		   }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
		
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			seListas.actualizarListas(NombresListas.listMconca);
			seListas.actualizarListas(NombresListas.listMgenusMarcaComercial);
			seListas.actualizarListas(NombresListas.listMgenusLineaProductos);
			seListas.actualizarListas(NombresListas.listMgenusSubLineaProductos);
			seListas.actualizarListas(NombresListas.listMgenus00UnidadMedidaCot);
			seListas.actualizarListas(NombresListas.listMgenusCentroCostos);
			seListas.actualizarListas(NombresListas.listMgenusClaseDocumentos);
			seListas.actualizarListas(NombresListas.listMidiom);	
			seListas.actualizarListas(NombresListas.listMtiptx00);
			seListas.actualizarListas(NombresListas.listMgenus00Variantes);
			seListas.actualizarListas(NombresListas.listMgenus00UnidadMedidaCot);
			getSeListas().actualizarListas(NombresListas.listMgenusTipoBienServi);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void accionNuevo() {
		try {
			//accion=Constantes.ACCION_NUEVO;
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			//setAccion(Constantes.ACCION_NUEVO);
			reiniciarListasMaestro();
			tabView=0;
			mpropr00 = new Mpropr00();
			mpropr00.setRegipr(true);
			mpropr00.setMdespr00s(new ArrayList<Mdespr00>());
			setMdespr00(null);
			mostrarPestana();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event) {
		try {
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mpropr00Cop= (Mpropr00) mpropr00.clone();
			mostrarPestana();
			tabView=0;
			mpropr00 = mpropr00Service.cargarDetalles(mpropr00);
			infoGuardada = false;
			if(mpropr00.getMconca00() != null){
				actualizarListasCompania(mpropr00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();
			}
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mpropr00.getMconca00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mpropr00.getMgenus001(), mpropr00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mpropr00.getMgenus002(), mpropr00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mpropr00.getMgenus003(), mpropr00.getMgenus003().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mpropr00.getMgenus004(), mpropr00.getMgenus004().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mpropr00.getMgenus005(), mpropr00.getMgenus005().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mpropr00.getMgenus008(), mpropr00.getMgenus008().getMtipre00().getTipore());

			}
			
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
				getSeListas().actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusMarcaComercial, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusLineaProductos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusSubLineaProductos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenus00UnidadMedidaCot, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusCentroCostos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusClaseDocumentos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMidiom, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMtiptx00, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenus00Variantes, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenus00UnidadMedidaCot, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusTipoBienServi, listMconca00ActualizarListas);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		// Se dispara este metodo al ejecutarse la accion con el autocompletar
		public void autocompleteActualizarListas (){
			try{
				String codigoCompania = null;
				if(mpropr00.getMconca00() != null){
					codigoCompania= mpropr00.getMconca00().getCodcia();
					actualizarListasCompania(codigoCompania);
				}else{
					reiniciarListasMaestro();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	private void colaEstandar() {
		try {
			mpropr00.setUserpr(getSeControlFactura().codusu);
			mpropr00.setPrgmpr("Productos " + getClass().getName());
			mpropr00.setFeacpr(new Date());
			mpropr00.setMaqupr(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		listMpropr00 = mpropr00Service.listMpropr00ByCriteria(new Mpropr00(), getSeControlFactura().getCompaniasUsu());
		//UtilsJSF.resetDTable("formTable:dTable");
	}
	
	/*Metodo que se encarga de validar que los registros de las listas esten habilitados
	 * en caso de estar deshabilitado algun registro devuelve true
	 */
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
		
		if(mpropr00.getMconca00() != null && mpropr00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcmpr")+" "+mpropr00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus001() != null && mpropr00.getMgenus001().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idmrpr")+" "+mpropr00.getMgenus001().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus002() != null && mpropr00.getMgenus002().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idlipr")+" "+mpropr00.getMgenus002().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus003() != null && mpropr00.getMgenus003().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idslpr")+" "+mpropr00.getMgenus003().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus004() != null && mpropr00.getMgenus004().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idumpr")+" "+mpropr00.getMgenus004().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus005() != null && mpropr00.getMgenus005().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idccpr")+" "+mpropr00.getMgenus005().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus006() != null && mpropr00.getMgenus006().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idvpr1")+" "+mpropr00.getMgenus006().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus007() != null && mpropr00.getMgenus007().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idvpr2")+" "+mpropr00.getMgenus007().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus008() != null && mpropr00.getMgenus008().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("iduipr")+" "+mpropr00.getMgenus008().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mpropr00.getMgenus009() != null && mpropr00.getMgenus009().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idtbpr")+" "+mpropr00.getMgenus009().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		
		return validacion;
	}
	//metodo que asigna la cantidad de decimales por parametro al los valores numericos del Mpropr00
	private void asignarVlrsDecimales(){
		try{
			//Decimal (Cantidad en Mparme00)
			if(mpropr00.getVlrupr()!=null)
			{
				mpropr00.setVlrupr(round(mpropr00.getVlrupr(), fracDec_ValruM));
			}
			if(mpropr00.getCostpr()!=null)
			{
				mpropr00.setCostpr(round(mpropr00.getCostpr(), fracDec_ValruM));
			}
			if(mpropr00.getCtdipr()!=null)
			{
				mpropr00.setCtdipr(round(mpropr00.getCtdipr(), fracDec_CantiM));		
			}
			if(mpropr00.getCtexpr()!=null)
			{
				mpropr00.setCtexpr(round(mpropr00.getCtexpr(), fracDec_CantiM));
			}
			if(mpropr00.getDscppr()!=null)
			{
				mpropr00.setDscppr(round(mpropr00.getDscppr(), fracDec_PorceM));
			}
			if(mpropr00.getIvappr()!=null)
			{
				mpropr00.setIvappr(round(mpropr00.getIvappr(), fracDec_PorceM));
			}
			if(mpropr00.getImpcpr()!=null)
			{
				mpropr00.setImpcpr(round(mpropr00.getImpcpr(), fracDec_PorceM));
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	//Se validan los datos que son requeridos por BD o por Dinox
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		//Codigo producto
		if(Utils.isEmptyOrNull(mpropr00.getCodcpr()) && permisoCampos.get("codcpr").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("codcpr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Compa�ia
		if(Utils.isEmptyOrNull(mpropr00.getMconca00()) && permisoCampos.get("idcmpr").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcmpr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcmprColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcmprColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcmprColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcmprColumn.getClientId());
		}
		//Descripcion
		if(Utils.isEmptyOrNull(mpropr00.getDscrpr())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("dscrpr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Indicativo pdto tercro
		if(Utils.isEmptyOrNull(mpropr00.getInptpr())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("inptpr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Linea de productos
		if(Utils.isEmptyOrNull(mpropr00.getMgenus002()) && permisoCampos.get("idlipr").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idlipr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdliprColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdliprColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdliprColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdliprColumn.getClientId());
		}
		//SubLinea de productos
		if(Utils.isEmptyOrNull(mpropr00.getMgenus003()) && permisoCampos.get("idslpr").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idslpr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdslprColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdslprColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdslprColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdslprColumn.getClientId());
		}
		//Valor Unitario ponderado
		if(Utils.isEmptyOrNull(mpropr00.getVlrupr())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("vlrupr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Costo unitario
		if(Utils.isEmptyOrNull(mpropr00.getCostpr())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("costpr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}	
	
		//Unidad de medida
		if(Utils.isEmptyOrNull(mpropr00.getMgenus008()) && permisoCampos.get("iduipr").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("iduipr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIduiprColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIduiprColumn.getClientId());
			validacion = true;
		}else{
			inputTextIduiprColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIduiprColumn.getClientId());
		}
		//Cantidad a Controlar de Unidad Medida  CAUIPR
		if(Utils.isEmptyOrNull(mpropr00.getCauipr()) && permisoCampos.get("cauipr").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("cauipr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//id. Tipo de bien o Servic IDTBPR
		if(Utils.isEmptyOrNull(mpropr00.getMgenus009()) && permisoCampos.get("idtbpr").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idtbpr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdtbprColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdtbprColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdtbprColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdtbprColumn.getClientId());
		}
		
		return validacion;
	}
	
	public void save() {
		try {
			
			if (validarDatosRequeridos()) {
				return;
			}
			
			colaEstandar();
//			asignarVlrsDecimales();
			guardarImagen();
			if(getAccion().equals("M"))
			{
				if(validarListas()==false){
					mpropr00Service.save(mpropr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mpropr00.toString(),mpropr00.toStringId(),mpropr00Cop.toString()):null);
				}else return;
			}
			else
			{
				mpropr00Service.save(mpropr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mpropr00.toString(),mpropr00.toStringId(),null):null);
			}
			cargarDatos();
			infoGuardada = true;
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "UNIQUE_KEY");
				return;
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}

	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
				mpropr00 = (Mpropr00) event.getComponent().getAttributes() .get("mpropr00");
				mpropr00Service.removeMpropr00(mpropr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mpropr00.toString(),mpropr00.toStringId()):null);
				cargarDatos();
				getSeListas().actualizarListas(NombresListas.listMconca);
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
	
	
	public void modificarRegistroActivoDetalles(){
		try{
			//Modificar el Registro para todos los detalles de Mparpd00
		    for(Mdespr00 mdespr : mpropr00.getMdespr00s())
			  {
		    	mdespr.setRegidp(mpropr00.getRegipr());  
			  }	
		   
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Nuevo y Excel cabecera general
	public void preRenderView(){
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void onTabChange(TabChangeEvent event)
	{  
	 RequestContext.getCurrentInstance().update("formDetalle:tabView:codcprColumn2");	
	 RequestContext.getCurrentInstance().update("formDetalle:tabView:producto");
	}
	
	public void evtCloseDialogMpropr(CloseEvent event) {
        try {   
        	update=null;
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
        	mpropr00.setMconca00(mpropr00Cop.getMconca00());
        	mpropr00.setMgenus001(mpropr00Cop.getMgenus001());
        	mpropr00.setMgenus002(mpropr00Cop.getMgenus002());
        	mpropr00.setMgenus003(mpropr00Cop.getMgenus003());
        	mpropr00.setMgenus004(mpropr00Cop.getMgenus004());
        	mpropr00.setMgenus005(mpropr00Cop.getMgenus005());
        	mpropr00.setMgenus006(mpropr00Cop.getMgenus006());
        	mpropr00.setMgenus007(mpropr00Cop.getMgenus007());
        	mpropr00.setMgenus008(mpropr00Cop.getMgenus008());
        	}
        	evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	 
    public double consultarValorxMpreci00(Mpropr00 mpropr00){
		
    	double varPresci = 0;
    	boolean flagNull = false;
		List<Mpreci00> ListMpreci00Temp = new ArrayList<Mpreci00>();
		List<Mpreci00> ListMpreci00Null = new ArrayList<Mpreci00>();
		List<Mpreci00> ListMpreci00NotNull = new ArrayList<Mpreci00>();
		ListMpreci00Temp = mpreci00Service.listMpreci00ByCriteria(new Mpreci00(), getSeControlFactura().getCompaniasUsu());
		for(Mpreci00 obj : ListMpreci00Temp){
			if(obj.getMgente00()!=null){
				if(obj.getMpropr00().getIdcapr() == mpropr00.getIdcapr()){
					ListMpreci00NotNull.add(obj);
					flagNull = true;
				}		
			}else{
				if(obj.getMpropr00().getIdcapr() == mpropr00.getIdcapr()){
					ListMpreci00Null.add(obj);
				}
				
			}		
		}
		if(flagNull){
			for(Mpreci00 obj : ListMpreci00NotNull){
				varPresci = obj.getPresci();
			}
			
		}else{
			for(Mpreci00 obj : ListMpreci00Null){
				varPresci = obj.getPresci();
			}
			
		}
		
		return varPresci;
	}
	
	
	public void llenarObjectCall(SelectEvent event) {
		
		try
		{
			if (objSeCall == null) {
				return;
			}
			String strClassLlama = objSeCall.getClass().getSimpleName();
			/*if (strClassLlama.equalsIgnoreCase("seMprcxc00")) {
				SeMprcxc00 seMprcxc00 = (SeMprcxc00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mpropr00")) {
					seMprcxc00.getMprcxc00().setMpropr00((Mpropr00) event.getObject());
						RequestContext.getCurrentInstance().reset("formMprcxc00");
				}
			}
			if (strClassLlama.equalsIgnoreCase("seMdfifa00")) {
				SeMdfifa00 seMdfifa00 = (SeMdfifa00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mpropr00")) {
					seMdfifa00.getMdfifa00().setMpropr00((Mpropr00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}*/
			if (strClassLlama.equalsIgnoreCase("seMpreci00")) {
				SeMpreci00 seMpreci00= (SeMpreci00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mpropr00")) {
					seMpreci00.getMpreci00().setMpropr00((Mpropr00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			
			if (strClassLlama.equalsIgnoreCase("seTqigqg00")) {
				SeTqigqg00 seTqigqg00= (SeTqigqg00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mpropr00")) {
					seTqigqg00.getTqigqg00().setMpropr00((Mpropr00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			
			if (strClassLlama.equalsIgnoreCase("seTqdpqp00")) {
				SeTqdpqp00 seTqdpqp00= (SeTqdpqp00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mpropr00")) {
					seTqdpqp00.getTqdpqp00().setMpropr00((Mpropr00) event.getObject());
					if(mpropr00.getMgenus004() != null){
						
						    seTqdpqp00 = new SeTqdpqp00();
						    unidadMedida = mpropr00.getMgenus004().getCodius()+"  "+mpropr00.getMgenus004().getDcttus();
						    valorPresci = consultarValorxMpreci00(mpropr00);
							System.out.println("valorPresci final: "+valorPresci);

					}
					
					//RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle");
						RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			if (strClassLlama.equalsIgnoreCase("seTqdrqr00")) {
				SeTqdrqr00 seTqdrqr00= (SeTqdrqr00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mpropr00")) {
					seTqdrqr00.getTqdpqp00().setMpropr00((Mpropr00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			update=null;
		}
		catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Metodo que autocompleta todas las listas de Midiom00
	public List<Mpropr00> completeMpropr(String query) {		

        List<Mpropr00> results = new ArrayList<Mpropr00>();   
        List<Mpropr00> listMpropr00 = seListas.getListMpropr00();

        for (Mpropr00 obj : listMpropr00) {        	
        	if(obj.getCodcpr().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	
	public void guardarImagen()
	{
		String urlFolder = (String) getSeControlFactura().PARAMETROS.get("RutaImgPro");
		File folder = new File((String)getSeControlFactura().PARAMETROS.get("RutaImgPro")+File.separator+mpropr00.getIdcapr());
		try
		{
			
			if(imagenAnterior!=null)
			{
			 File folder2 = new File(imagenAnterior);
			 if(folder2.exists())
			 {
				 folder2.delete();
			 }
			}	
			
			if(imagenPro!=null)
			{		
				if (Utils.directorioValido(urlFolder)) {
	
					String nombreAdj = crearNombreAdjunto();
					if(!folder.exists())
					{
						folder.mkdir();
					}
	
					if (Utils.copiarArchivo(nombreAdj, imagenPro
							.getInputstream(), urlFolder + File.separator + mpropr00.getIdcapr()
							+ File.separator)) 
					{
						mpropr00.setNomfpr(urlFolder + File.separator + mpropr00.getIdcapr()
								+ File.separator + nombreAdj);
					}
					
			  }
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	 public String crearNombreAdjunto() 
	   {

	      String strNombre = new String();
		  try 
		  {
				strNombre = mpropr00.getIdcapr() + "_" 
				+ Utils.obtenerNomAdjunto(imagenPro.getFileName());
		  } 
		  catch (Exception e) {
			e.printStackTrace();
		  }
	        return strNombre;
	  }		
	
	 public void uploadImagenPro(FileUploadEvent event) {
			try {
				 imagenAnterior= mpropr00.getNomfpr();
				 imagenPro= event.getFile();
				 changeImage=true;
				 mpropr00.setNomfpr("");
				 imageStrem = new DefaultStreamedContent(event.getFile().getInputstream());				
								
			}
		    catch (Exception e) {
				e.printStackTrace();
			}
		}

	
	public void removeImagenFoto() {
		try {
			imagenAnterior = mpropr00.getNomfpr();
			imagenPro=null;
			mpropr00.setNomfpr(null);
			changeImage=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Metodos de acceso
	public Mpropr00 getMpropr00() {		
		return mpropr00;
	}
	public void setMpropr00(Mpropr00 mpropr00) {
		this.mpropr00 = mpropr00;
	}
	public List<Mpropr00> getListMpropr00() {
		return listMpropr00;
	}
	public void setListMpropr00(List<Mpropr00> listMpropr00) {
		this.listMpropr00 = listMpropr00;
	}
	public List<Mpropr00> getFilteredMpropr00() {
		return filteredMpropr00;
	}
	public void setFilteredMpropr00(List<Mpropr00> filteredMpropr00) {
		this.filteredMpropr00 = filteredMpropr00;
	}
	public SeCabecera getSeCabecera() {
		return seCabecera;
	}
	public void setSeCabecera(SeCabecera seCabecera) {
		this.seCabecera = seCabecera;
	}
	
	public IMpropr00Service getMpropr00Service() {
		return mpropr00Service;
	}

	public void setMpropr00Service(IMpropr00Service mpropr00Service) {
		this.mpropr00Service = mpropr00Service;
	}

	public Mdespr00 getMdespr00() {
		return mdespr00;
	}

	public void setMdespr00(Mdespr00 mdespr00) {
		this.mdespr00 = mdespr00;
	}

	public Integer getFraccion() {
		return fracDec_CantiM;
	}

	public void setFraccion(Integer fraccion) {
		this.fracDec_CantiM = fraccion;
	}

	public String getAccionMdespr() {
		return accionMdespr;
	}

	public void setAccionMdespr(String accionMdespr) {
		this.accionMdespr = accionMdespr;
	}

//	public List<Mdespr00> getListMdespr00() {
//		return listMdespr00;
//	}
//
//	public void setListMdespr00(List<Mdespr00> listMdespr00) {
//		this.listMdespr00 = listMdespr00;
//	}

	public List<Mdespr00> getFilteredMdespr00() {
		return filteredMdespr00;
	}

	public void setFilteredMdespr00(List<Mdespr00> filteredMdespr00) {
		this.filteredMdespr00 = filteredMdespr00;
	}

	public List<Mdespr00> getListMdespr00Remove() {
		return listMdespr00Remove;
	}

	public void setListMdespr00Remove(List<Mdespr00> listMdespr00Remove) {
		this.listMdespr00Remove = listMdespr00Remove;
	}

	public Integer getFraccion2() {
		return fracDec_ValruM;
	}

	public void setFraccion2(Integer fraccion2) {
		this.fracDec_ValruM = fraccion2;
	}

	public Integer getFraccion3() {
		return fracDec_PorceM;
	}

	public void setFraccion3(Integer fraccion3) {
		this.fracDec_PorceM = fraccion3;
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

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public String getPaternDec_CantiM() {
		return paternDec_CantiM;
	}

	public void setPaternDec_CantiM(String paternDec_CantiM) {
		this.paternDec_CantiM = paternDec_CantiM;
	}

	public String getPaternDec_ValruM() {
		return paternDec_ValruM;
	}

	public void setPaternDec_ValruM(String paternDec_ValruM) {
		this.paternDec_ValruM = paternDec_ValruM;
	}

	public String getPaternDec_PorceM() {
		return paternDec_PorceM;
	}

	public void setPaternDec_PorceM(String paternDec_PorceM) {
		this.paternDec_PorceM = paternDec_PorceM;
	}

	public boolean isTabInformacionGnrl() {
		return tabInformacionGnrl;
	}

	public void setTabInformacionGnrl(boolean tabInformacionGnrl) {
		this.tabInformacionGnrl = tabInformacionGnrl;
	}

	public boolean isTabOtrosDatos() {
		return tabOtrosDatos;
	}

	public void setTabOtrosDatos(boolean tabOtrosDatos) {
		this.tabOtrosDatos = tabOtrosDatos;
	}

	public boolean isTabDescOtrosIdiomas() {
		return tabDescOtrosIdiomas;
	}

	public void setTabDescOtrosIdiomas(boolean tabDescOtrosIdiomas) {
		this.tabDescOtrosIdiomas = tabDescOtrosIdiomas;
	}

	public UploadedFile getImagenPro() {
		return imagenPro;
	}

	public void setImagenPro(UploadedFile imagenPro) {
		this.imagenPro = imagenPro;
	}

	public boolean isChangeImage() {
		return changeImage;
	}

	public void setChangeImage(boolean changeImage) {
		this.changeImage = changeImage;
	}

	public StreamedContent getImageStrem() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				return new DefaultStreamedContent();
			} else {
				if (!changeImage) {					
					imageStrem = UtilsJSF.convertStrinfileToStreamedContentImg(mpropr00.getNomfpr(), "jpg");
				} else {
					changeImage = false;
					return imageStrem;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return imageStrem;
	}

	public void setImageStrem(StreamedContent imageStrem) {
		this.imageStrem = imageStrem;
	}

	public int getTabView() {
		return tabView;
	}

	public void setTabView(int tabView) {
		this.tabView = tabView;
	}

	public Mpropr00 getMpropr00Cop() {
		return mpropr00Cop;
	}

	public void setMpropr00Cop(Mpropr00 mpropr00Cop) {
		this.mpropr00Cop = mpropr00Cop;
	}

	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}

	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}

	public Integer getFracDec_ValruM() {
		return fracDec_ValruM;
	}

	public void setFracDec_ValruM(Integer fracDec_ValruM) {
		this.fracDec_ValruM = fracDec_ValruM;
	}

	public Integer getFracDec_PorceM() {
		return fracDec_PorceM;
	}

	public void setFracDec_PorceM(Integer fracDec_PorceM) {
		this.fracDec_PorceM = fracDec_PorceM;
	}

	public String[] getPestanas() {
		return pestanas;
	}

	public void setPestanas(String[] pestanas) {
		this.pestanas = pestanas;
	}

	public boolean isInfoGuardada() {
		return infoGuardada;
	}

	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
	}

	public String getImagenAnterior() {
		return imagenAnterior;
	}

	public void setImagenAnterior(String imagenAnterior) {
		this.imagenAnterior = imagenAnterior;
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

	public InputText getInputTextIdcmprColumn() {
		return inputTextIdcmprColumn;
	}

	public void setInputTextIdcmprColumn(InputText inputTextIdcmprColumn) {
		this.inputTextIdcmprColumn = inputTextIdcmprColumn;
	}

	public InputText getInputTextIdliprColumn() {
		return inputTextIdliprColumn;
	}

	public void setInputTextIdliprColumn(InputText inputTextIdliprColumn) {
		this.inputTextIdliprColumn = inputTextIdliprColumn;
	}

	public InputText getInputTextIdslprColumn() {
		return inputTextIdslprColumn;
	}

	public void setInputTextIdslprColumn(InputText inputTextIdslprColumn) {
		this.inputTextIdslprColumn = inputTextIdslprColumn;
	}

	public InputText getInputTextIduiprColumn() {
		return inputTextIduiprColumn;
	}

	public void setInputTextIduiprColumn(InputText inputTextIduiprColumn) {
		this.inputTextIduiprColumn = inputTextIduiprColumn;
	}

	public InputText getInputTextIdtbprColumn() {
		return inputTextIdtbprColumn;
	}

	public void setInputTextIdtbprColumn(InputText inputTextIdtbprColumn) {
		this.inputTextIdtbprColumn = inputTextIdtbprColumn;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Double getValorPresci() {
		return valorPresci;
	}

	public void setValorPresci(Double valorPresci) {
		this.valorPresci = valorPresci;
	}

	public SeTqdpqp00 getSeTqdpqp00() {
		return seTqdpqp00;
	}

	public void setSeTqdpqp00(SeTqdpqp00 seTqdpqp00) {
		this.seTqdpqp00 = seTqdpqp00;
	}

	public IMpreci00Service getMpreci00Service() {
		return mpreci00Service;
	}

	public void setMpreci00Service(IMpreci00Service mpreci00Service) {
		this.mpreci00Service = mpreci00Service;
	}
		
}
