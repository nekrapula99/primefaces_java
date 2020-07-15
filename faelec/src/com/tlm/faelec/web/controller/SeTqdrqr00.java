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
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMaccio00Service;
import com.tlm.faelec.service.maestros.IMacdio00Service;
import com.tlm.faelec.service.maestros.IMestad00Service;
import com.tlm.faelec.service.maestros.IMteste00Service;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelec.service.trans.ITresre00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;
import com.tlm.faelecEntities.model.entities.Tresre00;

@Controller
@ManagedBean
@SessionScoped
public class SeTqdrqr00  extends Control implements Serializable{
	
	private static final long serialVersionUID = 7213183176463668661L;
	
	@ManagedProperty(value = "#{tqdrqr00Service}")
	private ITqdrqr00Service tqdrqr00Service;
	
	@ManagedProperty(value = "#{maccio00Service}")
	private IMaccio00Service maccio00Service;
	
	@ManagedProperty(value = "#{macdio00Service}")
	private IMacdio00Service macdio00Service;
	
	@ManagedProperty(value = "#{mteste00Service}")
	private IMteste00Service mteste00Service;
	
	@ManagedProperty(value = "#{tresre00Service}")
	private ITresre00Service tresre00Service;
	
	@ManagedProperty(value = "#{mestad00Service}")
	private IMestad00Service mestad00Service;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	@ManagedProperty(value = "#{seTqigqg00}")
	private SeTqigqg00 seTqigqg00;
	
	private Tqdfqf00 tqdfqf00;
	
	private Tqdrqr00 tqdrqr00;
	private Tqdrqr00 tqdrqr00Clone;
	private List<Tqdrqr00> listTqdrqr00;
	private List<Tqdrqr00> filteredTqdrqr00;
	private boolean infoGuardada;
	
	private Tqdpqp00 tqdpqp00;
	private List<Tqdpqp00> filteredTqdpqp00;
	private List<Tqdpqp00> listTqdpqp00Remove;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	
	private String tablaReq;
	private String accion;
	private String titulo;
	private String paternDec_CantiM;
	private Integer fracDec_CantiM;
	
	private InputText inputTextIdlgqrColumn;
	private InputText inputTextIdcjqrColumn;
	private InputText inputTextIdepqrColumn;
	private InputText inputTextCrqrqr;
	private InputTextarea inputTextAreaDetrqr;
	
	private String button;
	private String update;
	
	private Tqigqg00 tqigqg00;
	private Mestad00 mestad00;
	private Maccio00 maccio00;
	private Macdio00 macdio00;
	private Tresre00 tresre00;
	private Tresre00 tresre00Cop;
	
	private String descEstadoReq;
	private boolean flagEstadoInicial;
	private Integer idesesInit=0;
	private  boolean validacionEst;
	private String estadoInitReq;
	
	private List<Tresre00> listTresre00;
	private List<Tresre00> filteredTresre00;
	
	private List<Mestad00> listMestad00;
	
	//Banderas
	private boolean flagAnulada;
	private boolean flagAprobada;
	private boolean flagLiberadaDsllo;
	private boolean flagReabrir;
	private boolean flagGuardar;
	private boolean flagNueva;
	
	//botones
	private boolean botonModificaReq;
	private boolean botonAnularReq;
	private boolean botonEnviarReq;
	private boolean botonAprobarReq;
	private boolean botonLiberadaDslloReq;
	private boolean botonReabrirReq;
	private boolean botonGuardarReq;
	
	private boolean varDataTable;
	private boolean validacionFecha;
	
	private boolean numModificar;
	
	//Metodos del ManagedBean
	

	@PostConstruct
	public void init() {
		try {
			super.init("TQDRQR00");
			tqdfqf00 = new Tqdfqf00();
			tqdrqr00 = new Tqdrqr00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tablaReq=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			fracDec_CantiM=d1.intValue();			
			botonAnularReq = false;
			botonEnviarReq = false;
			botonAprobarReq = false;
			botonReabrirReq = false;
			botonGuardarReq = false;
			flagAnulada = false;
			flagAprobada = false;
			flagReabrir = false;
			flagGuardar = false;
			flagNueva = false;
			flagEstadoInicial = false;
			varDataTable = false;
			validacionFecha = false;
			numModificar = false;
	
			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	public void cargarDatos(Tqdrqr00 tqdrqr00) {
		
		listTqdrqr00 = tqdrqr00Service.listTqdrqr00ByCriteria(tqdrqr00);
		
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
			 seListas.actualizarListas(NombresListas.listMconca); 
			 seListas.actualizarListas(NombresListas.listMgenusEstdPart); 
			 seListas.actualizarListas(NombresListas.listMgenusComplejidad);
			 seListas.actualizarListas(NombresListas.listMgenusLegal);
			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	

	
	public void accionNuevo() {
		try {
			varDataTable = true;
			setAccion(Constantes.ACCION_NUEVO);
			tqdrqr00 = new Tqdrqr00();
			tqdrqr00.setRegcqr(true);
			tqdrqr00.setTqigqg00(seTqigqg00.getTqigqg00());
			tqdrqr00.setTqdpqp00s(new ArrayList<Tqdpqp00>());
			setTqdpqp00(null);
			estadoRequerimiento();
			if(!seTqigqg00.isNumNuevo()){
				cargarDatos(tqdrqr00);
			}

			tqdrqr00Clone = (Tqdrqr00) tqdrqr00.clone();
			flagNueva = true;
			numModificar = false;
			seTqigqg00.setNumModificar(false);
			UtilsJSF.resetDTable(":formDetalle:tabView:dTableTqdrqr00");
			 RequestContext.getCurrentInstance().update(":formDetalle");
			 RequestContext.getCurrentInstance().update(":formTqdrqr00Detalle");
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event){ 
		try {
			varDataTable = true;
			seTqigqg00.setNumModificar(false);
			setAccion(Constantes.ACCION_MODIFICAR);//Modificar registro
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tablaReq);
			tqdrqr00 = (Tqdrqr00)event.getObject();

			tqdrqr00.setTqigqg00(seTqigqg00.getTqigqg00());
			estadoRequerimiento();
			Tqdrqr00 tqdrqr00Rs = new Tqdrqr00();
			tqdrqr00Rs = tqdrqr00Service.cargarDetalles(tqdrqr00.getIdtrqr());
			tqdrqr00.setTqdpqp00s(tqdrqr00Rs.getTqdpqp00s());

			cargarDatos(tqdrqr00);
			if(tqdrqr00.getMgenus001() != null){
				actualizarListasCompania(tqdrqr00.getMgenus001().getCodius());
				getSeControlFactura().cargarParametros(true,null,tqdrqr00.getMgenus001().getIdtrus());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}

			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdrqr00.getMgenus001(), tqdrqr00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdrqr00.getMgenus002(), tqdrqr00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(tqdrqr00.getMgenus003(), tqdrqr00.getMgenus003().getMtipre00().getTipore());

			}
			seListas.cargarDatosReqProd(tqdrqr00);
			numModificar = true;
			
			tqdrqr00Clone = (Tqdrqr00) tqdrqr00.clone();
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
			
			getSeListas().actualizarListasCompania(NombresListas.listMgenusEstdPart, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusComplejidad, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusLegal , listMconca00ActualizarListas);			
			}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void autocompleteActualizarListas (){
		try{
			if(tqdrqr00.getMgenus001() != null){
			actualizarListasCompania(tqdrqr00.getMgenus001().getCodius());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			tqdrqr00.setUsecqr(getSeControlFactura().codusu);
			tqdrqr00.setPrgcqr(getSeControlFactura().aplusu);
			tqdrqr00.setFeacqr(new Date());
			tqdrqr00.setMaqcqr(getSeControlFactura().ip);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metodo que valida la Unique Key en la lista Mcpcct00
	 private boolean validarUKTqdrqr00() throws Exception{
		  try {
			  
			  //eliminarRepetido();
			  getSeControlFactura().setTitulo(titulo);
			  getSeControlFactura().setTabla(tablaReq);
		   if(!Utils.isEmptyOrNull(seTqigqg00.getTqigqg00().getTqdrqr00s())){
		    List<Tqdrqr00> listTemp = new ArrayList<Tqdrqr00>(seTqigqg00.getTqigqg00().getTqdrqr00s());
		    listTemp.remove(tqdrqr00);
		    for (Tqdrqr00 objIter : listTemp) {
		     //if(objIter.getIdtrqr() != tqdrqr00.getIdtrqr()){
		    	//objIter.getTqigqg00().get (tqdrqr00.getTqigqg00().getIdtqqg()) 
		    	 if((objIter.getCrqrqr().equals(tqdrqr00.getCrqrqr()))){
		    		 return false;
		    	// }
		     }	
		    }
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return true;
		 }
		 
	//Limpia el filtro del DataTable
	public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:tabView:dTableTqdrqr00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:tabView:dTableTqdrqr00");
		}
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(tqdrqr00.getMgenus001()) && permisoCampos.get("idepqr").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idepqr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdepqrColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdepqrColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdepqrColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdepqrColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqdrqr00.getMgenus002()) && permisoCampos.get("idcjqr").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcjqr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcjqrColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcjqrColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcjqrColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcjqrColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqdrqr00.getMgenus003()) && permisoCampos.get("idlgqr").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idlgqr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdlgqrColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdlgqrColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdlgqrColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdlgqrColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqdrqr00.getCrqrqr()) && permisoCampos.get("crqrqr").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("crqrqr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextCrqrqr.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextCrqrqr.getClientId());
			validacion = true;
		}else{
			inputTextCrqrqr.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextCrqrqr.getClientId());	
		}
		if(Utils.isEmptyOrNull(tqdrqr00.getDetrqr()) && permisoCampos.get("detrqr").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("detrqr")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextAreaDetrqr.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextAreaDetrqr.getClientId());
			validacion = true;
		}else{
			inputTextAreaDetrqr.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextAreaDetrqr.getClientId());	
		}
		
		return validacion;
	}
	
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
//		
		//Validacion lista  medio pago
		if(tqdrqr00.getMgenus001() != null &&  tqdrqr00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idepqr")+" "+ tqdrqr00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Moneda Cotizacion
		if(tqdrqr00.getMgenus002() != null &&  tqdrqr00.getMgenus002().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcjqr")+" "+ tqdrqr00.getMgenus002().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista Cliente o Prospecto
		if(tqdrqr00.getMgenus003() != null &&  tqdrqr00.getMgenus003().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idlqgr")+" "+ tqdrqr00.getMgenus003().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		return validacion;
	}
	
 /*public void activarBotones(){
	 //String descripEstado ="";
	 try {
		 
		 if(getAccion().equals(Constantes.ACCION_NUEVO)){
			 botonAnularReq = false;
			 //botonLiberadaDslloReq = false;
			 botonReabrirReq = false;
			 botonAprobarReq = false;
			 botonGuardarReq = true;
			 //botonEnviadaReq = false;
		 }else if(getAccion().equals(Constantes.ACCION_MODIFICAR)){
			 
			 Tresre00 tresre00 = new Tresre00();
			 Date fechaMayor = new Date();
			 listTresre00 = tresre00Service.listTresre00ByCriteria(tresre00);
			 if(listTresre00.isEmpty()){
				 descEstadoReq="Cot. Es. Req Nuevo";
			 }
			 List<Date> listFechasTresre00= new ArrayList<Date>();

				for(Tresre00 obj : listTresre00)
				{
					    if(obj.getTqdrqr00().getIdtrqr()==getTqdrqr00().getIdtrqr()){
					    	if(obj.getMestad00()!=null){
								listFechasTresre00.add(obj.getFeacre());
								fechaMayor = Collections.max(listFechasTresre00);
								if(obj.getFeacre().equals(fechaMayor)){
									mestad00 = obj.getMestad00();
									descEstadoReq = mestad00.getDscres(); 
								}
					    	}
						}
					
				}
				
			 if(descEstadoReq.equalsIgnoreCase("Cot. Es. Req Nuevo")){
				 botonAnularReq = true;
				 //botonLiberadaDsllo = false;
				 botonReabrirReq = false;
				 botonAprobarReq = false;
				 botonGuardarReq = true;
				//botonEnviarReq = false; 
			 }else if(descEstadoReq.equalsIgnoreCase("Cot. Es. Req. Actualizado")){
				 botonAnularReq = false;
				 //botonLiberadaDsllo = false;
				 botonReabrirReq = false;
				 botonAprobarReq = false;
				 botonGuardarReq = true;
				 //botonEnviada = true;	 
			 /*}else if(descEstado.equalsIgnoreCase("Cot. Es. Enviada")){
				 botonAnulada = true;
				 botonLiberadaDsllo = false;
				 botonReabrir = false;
				 botonAprobada = true;
				 botonGuardar = false;
				 botonEnviada = true;	*/ 
			/* }else if(descEstadoReq.equalsIgnoreCase("Cot. Es. Req. Aprobado")){
				 botonAnularReq = true;
				 //botonLiberadaDsllo = true;
				 botonReabrirReq = false;
				 botonAprobarReq = false;
				 botonGuardarReq = false;
				 //botonEnviada = false;	 
			 }else if(descEstadoReq.equalsIgnoreCase("Cot. Es. Req. Anulado")){
				 botonAnularReq = false;
				//botonLiberadaDsllo = false;
				 botonReabrirReq = true;
				 botonAprobarReq = false;
				 botonGuardarReq = false;
				 //botonEnviada = false;
			 }else if(descEstadoReq.equalsIgnoreCase("Cot. Es. Req. Reabierto")){
				 botonAnularReq = false;
				 //botonLiberadaDsllo = true;
				 botonReabrirReq = false;
				 botonAprobarReq = false;
				 botonGuardarReq = true;
				 //botonEnviada = false;	 
			 }		 
		 }

	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
	}
 }*/
	
	
	
	 public void estadoInicialReq(){
		 try{
			 flagEstadoInicial=false;
			 
			 //estadoInitReq= (String) super.getSeControlFactura().PARAMETROS.get("Est_IniRequer");
			 Mestad00 mestado00 = new Mestad00();
			 
			 listMestad00 = mestad00Service.listMestad00ByCriteria(mestado00, getSeControlFactura().getCompaniasUsu());
			 for(Mestad00 obj : listMestad00){
				 
				 if(obj.getBfeaes().equalsIgnoreCase("S")){
					 descEstadoReq = obj.getDscres();
					 flagEstadoInicial = true;
					 idesesInit = obj.getIdeses();
					 
				}
			 }
			 
			 if(!flagEstadoInicial){
				 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para este requerimiento");
				 RequestContext.getCurrentInstance().update("formTable");  
			 }

		 } catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
		 
	 }
	
	
	public void estadoRequerimiento(){
		 try {
			 boolean estadoExist = false;
			 Tresre00 tresre00 = new Tresre00();
			 Date fechaMayor = new Date();
			 listTresre00 = tresre00Service.listTresre00ByCriteria(tresre00);
			 List<Date> listFechasTqesqe00= new ArrayList<Date>();
				for(Tresre00 obj : listTresre00)
				{	
					  if(obj.getTqdrqr00().getIdtrqr()==getTqdrqr00().getIdtrqr()){
						  if(obj.getMestad00()!=null){
							listFechasTqesqe00.add(obj.getFeacre());
							fechaMayor = Collections.max(listFechasTqesqe00);
							if(obj.getFeacre().equals(fechaMayor)){
								mestad00 = obj.getMestad00();
								descEstadoReq = mestad00.getDscres();
								estadoExist = true;
							}
						}
					}
				}
				
				if(!estadoExist){
					
					flagEstadoInicial=false;
					Mestad00 mestado00 = new Mestad00();
					 
					listMestad00 = mestad00Service.listMestad00ByCriteria(mestado00, getSeControlFactura().getCompaniasUsu());
					for(Mestad00 obj : listMestad00){
						 
						 if(obj.getBfeaes().equalsIgnoreCase("S")){
							 descEstadoReq = (String) super.getSeControlFactura().PARAMETROS.get("Est_IniRequer");
							 flagEstadoInicial = true;
							 idesesInit = obj.getIdeses();
							 
						}
					}
					 
					if(!flagEstadoInicial){
						 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para esta cotizaci�n");
						 RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdrqr00");
					 }	
				}
				estadoExist=false;
				
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	 }
	
	
	public boolean validarTransicionEstado(){
		
		 validacionEst = false;
		 boolean flag =false;
		 try {
			 Integer varTempEstdOrigen=0;
			 	
			 
				macdio00 = new Macdio00();
				macdio00.setMaccio00(consultarObjetoMaccio00());

				if(flagGuardar){
					System.out.println("guardado Req!!");
					macdio00 = consultarObjetoMacdio00("BTN_GRABAR_REQUERIMIENTO");
				}else if(flagAnulada){
					System.out.println("Anulado Req!!");
					macdio00 = consultarObjetoMacdio00("BTN_ANULAR_REQUERIMIENTO");
				}else if(flagAprobada){
					System.out.println("Aprobado Req!!");
					macdio00 = consultarObjetoMacdio00("BTN_APROBAR_REQUERIMIENTO");
				}else if(flagReabrir){
					System.out.println("Reabierto!!");
					macdio00 = consultarObjetoMacdio00("BTN_REABRIR_REQUERIMIENTO");
				}
				
			 Tresre00 tresre00 = new Tresre00();
			 Date fechaMayor = new Date();
			 listTresre00 = tresre00Service.listTresre00ByCriteria(tresre00);
			 List<Date> listFechasTqesqe00= new ArrayList<Date>();
			 
			 
				for(Tresre00 obj : listTresre00)
				{
					if(obj.getTqdrqr00().getIdtrqr()== tqdrqr00.getIdtrqr()){
						if(obj.getMestad00()!=null){
							listFechasTqesqe00.add(obj.getFeacre());
					    	fechaMayor = Collections.max(listFechasTqesqe00);
					    	if(obj.getFeacre().equals(fechaMayor)){
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
					}
				}else{
					varTempEstdOrigen = getIdesesInit();
					
					
					if(obtenerTransicionValida(varTempEstdOrigen,macdio00.getMestad00().getIdeses())){
						validacionEst = true;
					}else{	
						validacionEst = false;
					}	
					
				}			
				
			flag =false;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
		 return validacionEst;
	 }
	
	
	public Maccio00 consultarObjetoMaccio00(){
		maccio00 = new Maccio00();
		maccio00.setMconca00(getSeControlFactura().getMconca00Session());
		maccio00.setMtiptx00(tqdrqr00.getTqigqg00().getMtiptx00());
		maccio00 = maccio00Service.getMaccio00ByTqigqg00(maccio00, tablaReq);
		return maccio00;
	}
	
	public Macdio00 consultarObjetoMacdio00(String notdio){
		macdio00 = new Macdio00();
		macdio00.setMaccio00(consultarObjetoMaccio00());
		macdio00 = macdio00Service.getMacdio00ByTqigqg00(macdio00,notdio);
		return macdio00;
	}
	
	public boolean obtenerTransicionValida(Integer varTemp, Integer macIdedio){
		return mteste00Service.obtenerTransicionValida(varTemp, macIdedio);
	}
	
	//Nunca se usa
	public List<Tqdrqr00> consultarObjetoTqdrqr00(Tqigqg00 tqigqg00){
		tqdrqr00 = new Tqdrqr00();
		tqdrqr00.setTqigqg00(tqigqg00);
		tqdrqr00.setCrqrqr(tqdrqr00.getCrqrqr());
		List<Tqdrqr00> listTqdrqr00Temp = new ArrayList<Tqdrqr00>(); 
		listTqdrqr00Temp = tqdrqr00Service.getTqdrqr00ByTqigqg00(tqdrqr00);
		return listTqdrqr00Temp;
	}
	
	private void guardarTresre00() {
		try {
			
			
				tresre00 = new Tresre00();
				
				tqdrqr00.getTqigqg00();
				tresre00.setMestad00(macdio00.getMestad00());
				
				tresre00.setTqdrqr00(tqdrqr00);
				tresre00.setRegcre(true);
				tresre00.setFeesre(new Date());
				tresre00.setUsecre(getSeControlFactura().codusu);
				tresre00.setPrgcre("Transaccion_Cotizacion_Requeri_Estados " + getClass().getName());
				tresre00.setFeacre(new Date());
				tresre00.setMaqcre(getSeControlFactura().ip);
				tresre00Cop= (Tresre00) tresre00.clone();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean validarFechas(){
		validacionFecha = false;
		
		if(tqdrqr00.getFepeqr().after(tqdrqr00.getTqigqg00().getFepdqg())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha prometida de entrega debe ser menor o igual a la fecha Promesa Delivery de la cotizaci�n");
			RequestContext.getCurrentInstance().update(":formDetalle"); 
			validacionFecha = true;
			
		}
		return validacionFecha;
	}
	
	
	
	public void save(ActionEvent event) {
		try {
			seTqigqg00.setNumModificar(false);
			
			if(!seTqigqg00.isCotizacionNueva()){
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					flagGuardar = true;
					flagAnulada = false;
					flagAprobada = false;
					flagReabrir = false;
					
					validarTransicionEstado();
					if(!validacionEst){
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para este requerimiento");
						RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle"); 
						return;
					}
				}
				if (validarDatosRequeridos()) {
					return;
				}
				tqdrqr00.getTqigqg00().getFepdqg();
				if(tqdrqr00.getFepeqr()!=null && tqdrqr00.getTqigqg00().getFepdqg() !=null){
					validarFechas();
					if(validacionFecha){
						return;
					}
				}
				
				colaEstandar();
				
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					if(validarListas()==false){
						tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),tqdrqr00Clone.toString()):null);
						seTqigqg00.getTqigqg00().getTqdrqr00s().add(tqdrqr00);
						guardarTresre00();
						seTqigqg00.setNumModificar(false);
						numModificar = true;
						tresre00Service.save(tresre00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tresre00.toString(),tresre00.toStringId(),tresre00Cop.toString()):null);
					}else return;
				} else {
					tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),null):null);
					seTqigqg00.getTqigqg00().getTqdrqr00s().add(tqdrqr00);
					flagGuardar = false;
					flagNueva = true;
					numModificar = true;
					seTqigqg00.setNumModificar(false);
				 }
				
				flagGuardar = false;
				flagNueva = false;
				validacionEst = false;
				infoGuardada = true;
				cargarDatos(tqdrqr00);
			    UtilsJSF.closeDialog("dlgNuevoUpdateTqdrqr00");
			    UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			    RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdrqr00");
			    RequestContext.getCurrentInstance().update(":formTqdrqr00Detalle");
		}
		else{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No se ha creado una cotizaci�n para este requerimiento");
			RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdrqr00"); 
			return;	
			
		}
	}
	catch (Exception e) {	
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
	}
	
	public void anular(ActionEvent event) {
		try {
			
			if(getTqdrqr00().getTqigqg00().getIdtqqg()!=0){
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					flagGuardar = false;
					flagAnulada = true;
					flagAprobada = false;
					flagLiberadaDsllo = false;
					flagReabrir = false;
					flagNueva = false;
					validarTransicionEstado();
					if(!validacionEst){
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para este requerimiento");
						RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle"); 
						return;
					}
				}

			if (validarDatosRequeridos()) {
				return;
			}
			colaEstandar();
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),tqdrqr00Clone.toString()):null);
					seTqigqg00.getTqigqg00().getTqdrqr00s().add(tqdrqr00);
					
					guardarTresre00();
					tresre00Service.save(tresre00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tresre00.toString(),tresre00.toStringId(),tresre00Cop.toString()):null);
				}else return;
			} else {
				tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),null):null);
				flagGuardar = false;
				flagNueva = true;
			 }
			cargarDatos(tqdrqr00);
			flagAnulada = false;
			flagNueva = false;
			validacionEst = false;
			infoGuardada = true;	
		    UtilsJSF.closeDialog("dlgNuevoUpdateTqdrqr00");
		    UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		    RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdrqr00");
		    RequestContext.getCurrentInstance().update(":formTqdrqr00Detalle");
		}
	}
	catch (Exception e) {	
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
	}
	
	public void aprobar(ActionEvent event) {
		try {
			
			if(getTqdrqr00().getTqigqg00().getIdtqqg()!=0){
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					flagGuardar = false;
					flagAnulada = false;
					flagAprobada = true;
					flagLiberadaDsllo = false;
					flagReabrir = false;
					flagNueva = false;
					
					validarTransicionEstado();
					if(!validacionEst){
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para este requerimiento");
						RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle"); 
						return;
					}
				}
	
			
			if (validarDatosRequeridos()) {
				return;
			}
			colaEstandar();
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),tqdrqr00Clone.toString()):null);
					seTqigqg00.getTqigqg00().getTqdrqr00s().add(tqdrqr00);
					
					guardarTresre00();
					tresre00Service.save(tresre00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tresre00.toString(),tresre00.toStringId(),tresre00Cop.toString()):null);
				}else return;
			} else {
				tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),null):null);
				flagGuardar = false;
				flagNueva = true;
			 }
			cargarDatos(tqdrqr00);
			flagAprobada = false;
			validacionEst = false;
			flagNueva = false;
			infoGuardada = true;
		    UtilsJSF.closeDialog("dlgNuevoUpdateTqdrqr00");
		    UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		    RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdrqr00");
		    RequestContext.getCurrentInstance().update(":formTqdrqr00Detalle");
		}
	}
	catch (Exception e) {	
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
	}
	
	public void reabrir(ActionEvent event) {
		try {
			
			if(getTqdrqr00().getTqigqg00().getIdtqqg()!=0){
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					flagGuardar = false;
					flagAnulada = false;
					flagAprobada = false;
					flagLiberadaDsllo = false;
					flagReabrir = true;
					flagNueva = false;
					validarTransicionEstado();
					if(!validacionEst){
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un estado valido para este requerimiento");
						RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle"); 
						return;
					}
				}
			//Se validan los datos que son requeridos
			if (validarDatosRequeridos()) {
				return;
			}
			colaEstandar();
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),tqdrqr00Clone.toString()):null);
					seTqigqg00.getTqigqg00().getTqdrqr00s().add(tqdrqr00);
					
					guardarTresre00();
					tresre00Service.save(tresre00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tresre00.toString(),tresre00.toStringId(),tresre00Cop.toString()):null);
				}else return;
			} else {
				tqdrqr00Service.save(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqdrqr00.toString(),tqdrqr00.toStringId(),null):null);
				flagGuardar = false;
				flagNueva = true;
			 }
			cargarDatos(tqdrqr00);
			flagReabrir = false;
			validacionEst = false;
			flagNueva = false;
			infoGuardada = true;
		    UtilsJSF.closeDialog("dlgNuevoUpdateTqdrqr00");
		    UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		    RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdrqr00");
		    RequestContext.getCurrentInstance().update(":formTqdrqr00Detalle");
		}
	}
	catch (Exception e) {	
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
	}

	
	
	//VALIDAR QUE la cotizaci�n no este  en estado aprobada o facturada.
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tablaReq);
			tqdrqr00 = (Tqdrqr00) event.getComponent().getAttributes().get("tqdrqr00");
			tqdrqr00Service.removeTqdrqr00(tqdrqr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,tqdrqr00.toString(),tqdrqr00.toStringId()):null);
			seTqigqg00.getTqigqg00().getTqdrqr00s().remove(tqdrqr00);
			cargarDatos(tqdrqr00);
			seTqigqg00.setNumModificar(false);
			UtilsJSF.resetDTable(":formDetalle:tabView:dTableTqdrqr00");
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
		RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableTqdrqr00");
		RequestContext.getCurrentInstance().update(":formTqdrqr00Detalle");
		

	}
	
	public ITqdrqr00Service getTqdrqr00Service() {
		return tqdrqr00Service;
	}
	public void setTqdrqr00Service(ITqdrqr00Service tqdrqr00Service) {
		this.tqdrqr00Service = tqdrqr00Service;
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
	public Tqdrqr00 getTqdrqr00() {
		if(tqdrqr00==null){
			tqdrqr00 = new Tqdrqr00();
		}
		return this.tqdrqr00;
	}
	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}
	public Tqdrqr00 getTqdrqr00Clone() {
		return tqdrqr00Clone;
	}
	public void setTqdrqr00Clone(Tqdrqr00 tqdrqr00Clone) {
		this.tqdrqr00Clone = tqdrqr00Clone;
	}
	public List<Tqdrqr00> getListTqdrqr00() {
		return listTqdrqr00;
	}
	public void setListTqdrqr00(List<Tqdrqr00> listTqdrqr00) {
		this.listTqdrqr00 = listTqdrqr00;
	}
	public List<Tqdrqr00> getFilteredTqdrqr00() {
		return filteredTqdrqr00;
	}
	public void setFilteredTqdrqr00(List<Tqdrqr00> filteredTqdrqr00) {
		this.filteredTqdrqr00 = filteredTqdrqr00;
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
	
	public String getTablaReq() {
		return tablaReq;
	}

	public void setTablaReq(String tablaReq) {
		this.tablaReq = tablaReq;
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


	public InputText getInputTextIdlgqrColumn() {
		return inputTextIdlgqrColumn;
	}


	public void setInputTextIdlgqrColumn(InputText inputTextIdlgqrColumn) {
		this.inputTextIdlgqrColumn = inputTextIdlgqrColumn;
	}


	public InputText getInputTextIdcjqrColumn() {
		return inputTextIdcjqrColumn;
	}


	public void setInputTextIdcjqrColumn(InputText inputTextIdcjqrColumn) {
		this.inputTextIdcjqrColumn = inputTextIdcjqrColumn;
	}


	public InputText getInputTextIdepqrColumn() {
		return inputTextIdepqrColumn;
	}


	public void setInputTextIdepqrColumn(InputText inputTextIdepqrColumn) {
		this.inputTextIdepqrColumn = inputTextIdepqrColumn;
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

	public boolean isFlagAnulada() {
		return flagAnulada;
	}

	public void setFlagAnulada(boolean flagAnulada) {
		this.flagAnulada = flagAnulada;
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

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
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

	public Tresre00 getTresre00() {
		return tresre00;
	}

	public void setTresre00(Tresre00 tresre00) {
		this.tresre00 = tresre00;
	}

	public Tresre00 getTresre00Cop() {
		return tresre00Cop;
	}

	public void setTresre00Cop(Tresre00 tresre00Cop) {
		this.tresre00Cop = tresre00Cop;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public IMaccio00Service getMaccio00Service() {
		return maccio00Service;
	}

	public void setMaccio00Service(IMaccio00Service maccio00Service) {
		this.maccio00Service = maccio00Service;
	}

	public IMacdio00Service getMacdio00Service() {
		return macdio00Service;
	}

	public void setMacdio00Service(IMacdio00Service macdio00Service) {
		this.macdio00Service = macdio00Service;
	}

	public List<Tresre00> getListTresre00() {
		return listTresre00;
	}

	public void setListTresre00(List<Tresre00> listTresre00) {
		this.listTresre00 = listTresre00;
	}

	public List<Tresre00> getFilteredTresre00() {
		return filteredTresre00;
	}

	public void setFilteredTresre00(List<Tresre00> filteredTresre00) {
		this.filteredTresre00 = filteredTresre00;
	}

	public IMteste00Service getMteste00Service() {
		return mteste00Service;
	}

	public void setMteste00Service(IMteste00Service mteste00Service) {
		this.mteste00Service = mteste00Service;
	}

	public ITresre00Service getTresre00Service() {
		return tresre00Service;
	}

	public void setTresre00Service(ITresre00Service tresre00Service) {
		this.tresre00Service = tresre00Service;
	}

	public boolean isBotonModificaReq() {
		return botonModificaReq;
	}

	public void setBotonModificaReq(boolean botonModificaReq) {
		this.botonModificaReq = botonModificaReq;
	}

	public boolean isBotonAnularReq() {
		return botonAnularReq;
	}

	public void setBotonAnularReq(boolean botonAnularReq) {
		this.botonAnularReq = botonAnularReq;
	}

	public boolean isBotonEnviarReq() {
		return botonEnviarReq;
	}

	public void setBotonEnviarReq(boolean botonEnviarReq) {
		this.botonEnviarReq = botonEnviarReq;
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

	public String getDescEstadoReq() {
		return descEstadoReq;
	}

	public void setDescEstadoReq(String descEstadoReq) {
		this.descEstadoReq = descEstadoReq;
	}

	public List<Mestad00> getListMestad00() {
		return listMestad00;
	}

	public void setListMestad00(List<Mestad00> listMestad00) {
		this.listMestad00 = listMestad00;
	}

	public IMestad00Service getMestad00Service() {
		return mestad00Service;
	}

	public void setMestad00Service(IMestad00Service mestad00Service) {
		this.mestad00Service = mestad00Service;
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

	public boolean isValidacionEst() {
		return validacionEst;
	}

	public void setValidacionEst(boolean validacionEst) {
		this.validacionEst = validacionEst;
	}

	public String getEstadoInitReq() {
		return estadoInitReq;
	}

	public void setEstadoInitReq(String estadoInitReq) {
		this.estadoInitReq = estadoInitReq;
	}

	public InputText getInputTextCrqrqr() {
		return inputTextCrqrqr;
	}

	public void setInputTextCrqrqr(InputText inputTextCrqrqr) {
		this.inputTextCrqrqr = inputTextCrqrqr;
	}

	public InputTextarea getInputTextAreaDetrqr() {
		return inputTextAreaDetrqr;
	}

	public void setInputTextAreaDetrqr(InputTextarea inputTextAreaDetrqr) {
		this.inputTextAreaDetrqr = inputTextAreaDetrqr;
	}

	public boolean isVarDataTable() {
		return varDataTable;
	}

	public void setVarDataTable(boolean varDataTable) {
		this.varDataTable = varDataTable;
	}

	public boolean isValidacionFecha() {
		return validacionFecha;
	}

	public void setValidacionFecha(boolean validacionFecha) {
		this.validacionFecha = validacionFecha;
	}


	public boolean isNumModificar() {
		return numModificar;
	}


	public void setNumModificar(boolean numModificar) {
		this.numModificar = numModificar;
	}


	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}


	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}


	public String getUpdate() {
		return update;
	}


	public void setUpdate(String update) {
		this.update = update;
	}

	
	
}
