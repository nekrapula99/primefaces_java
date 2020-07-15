package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMaccio00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mrerer00;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@Controller
@ManagedBean
@SessionScoped
public class SeMaccio00 extends Control implements Serializable{
	private static final long serialVersionUID = 1L;

	//Variables del Managedbean
	
	@ManagedProperty(value = "#{maccio00Service}")
	private IMaccio00Service maccio00Service;
	
	private Maccio00 maccio00;
	private Maccio00 maccio00Cop;
	private List<Maccio00> listMaccio00;
	private List<Maccio00> filteredMaccio00;
	private boolean infoGuardada;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	
	private String tabla;
	private String accion;
	private String titulo;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	
	private int tabView=0;

	private InputText inputTextIdtcioColumn;
	private InputText inputTextIdmcioColumn;
	
	//Metodos del ManagedBean
	
	@PostConstruct
	public void init() {
		try {
			super.init("MACCIO00");
			maccio00 = new Maccio00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			titulo=super.getSeControlFactura().getTitulo();
			accion=super.getSeControlFactura().getAccion();			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cargarDatos() {
		listMaccio00 = maccio00Service.listMaccio00ByCriteria(new Maccio00(), getSeControlFactura().getCompaniasUsu());
		UtilsJSF.resetDTable("formTable:dTable");
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			getSeListas().actualizarListas(NombresListas.listMtiptx00);
			getSeListas().actualizarListas(NombresListas.listMgenus00Evento);
			getSeListas().actualizarListas(NombresListas.listMconca);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void accionNuevo() {
		try {				
			accion=Constantes.ACCION_NUEVO;
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			tabView=0;
			maccio00 = new Maccio00();
			maccio00.setRegcio(true);
			maccio00.setMacdio00s(new ArrayList<Macdio00>());						
			reiniciarListasMaestro();		
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event) {
		try {			
			accion=Constantes.ACCION_MODIFICAR;
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			tabView=0;
			maccio00Cop= (Maccio00) maccio00.clone();	
			infoGuardada = false;
			if(maccio00.getMconca00() != null){
				actualizarListasCompania(maccio00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();	
			}
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(maccio00.getMconca00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(maccio00.getMtiptx00(), null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compañias 
		private void actualizarListasCompania (String codigoCompania){
			try{
				List<String> listMconca00ActualizarListas;
				listMconca00ActualizarListas = new ArrayList<String>();
				listMconca00ActualizarListas.add(codigoCompania);
				getSeListas().actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMtiptx00 , listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenus00Evento, listMconca00ActualizarListas);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		// Se dispara este metodo al ejecutarse la accion con el autocompletar
		public void autocompleteActualizarListas (){
			try{
				if(maccio00.getMconca00() != null){
					actualizarListasCompania(maccio00.getMconca00().getCodcia());
				}else{
					reiniciarListasMaestro();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	private void colaEstandar() {
		try {
			maccio00.setUsecio(getSeControlFactura().codusu);
			maccio00.setPrgcio("Acciones " + getClass().getName());
			maccio00.setFeacio(new Date());
			maccio00.setMaqcio(getSeControlFactura().ip);

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
		
		if(maccio00.getMconca00() != null && maccio00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idmcio")+" "+maccio00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(maccio00.getMtiptx00() != null && maccio00.getMtiptx00().isRegtxt() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idtcio")+" "+maccio00.getMtiptx00().getCotrtx()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		
		return validacion;
	}
	
	//Se validan los datos que son requeridos por BD o por Dinox
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		
		//Transaccion 
		if(Utils.isEmptyOrNull(maccio00.getMtiptx00()) && permisoCampos.get("idtcio").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idtcio")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdtcioColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdtcioColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdtcioColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdtcioColumn.getClientId());	
		}		
		//Compañia
		if(Utils.isEmptyOrNull(maccio00.getMconca00()) && permisoCampos.get("idmcio").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idmcio")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdmcioColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdmcioColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdmcioColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdmcioColumn.getClientId());	
		}
		//Pestaña de eventos
		if (maccio00.getMacdio00s().size()==0) {
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,(String)getSeControlFactura().MENSAJES.get("Exi_Macdio"));
			validacion = true;
		}
		
		return validacion;
	}
	
	public void save() {
		try {
			
			if (validarDatosRequeridos()) {
				return;
			}		
			colaEstandar();

			if(accion.equals("M"))
			{
				if(validarListas()==false){
					maccio00Service.save(maccio00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,maccio00.toString(),maccio00.toStringId(),maccio00Cop.toString()):null);
				}else return;
			}
			else
			{
				maccio00Service.save(maccio00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,maccio00.toString(),maccio00.toStringId(),null):null);
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
				maccio00 = (Maccio00) event.getComponent().getAttributes() .get("maccio00");
				maccio00Service.removeMaccio00(maccio00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,maccio00.toString(),maccio00.toStringId()):null);
				cargarDatos();
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
			//Modificar el Registro para todos los detalles de Maccio00
		    for(Macdio00 macdio00 : maccio00.getMacdio00s())
			  {
		    	macdio00.setRegdio(maccio00.isRegcio());  
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

	public void evtCloseDialogMaccio(CloseEvent event) {
        try {   
        	     	
        	if (accion  != null && accion.equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
        	maccio00.setMconca00(maccio00Cop.getMconca00());
        	maccio00.setMtiptx00(maccio00Cop.getMtiptx00());
        	}
        	evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	
	
	//Metodo que autocompleta todas las listas de Mconca00
		public List<Maccio00> completeMaccio00(String query) {		

	        List<Maccio00> results = new ArrayList<Maccio00>();   
	        List<Maccio00> listMaccio00 = seListas.getListMaccio00();

	        for (Maccio00 obj : listMaccio00) {        	
	        	if(obj.getPgccio().toLowerCase().contains(query.toLowerCase())){
	        		results.add(obj);        		
	    		}			
			}
	        return results;
	    }
	
	/*
	 * Getters and Setters
	 */
	
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

	public Maccio00 getMaccio00Cop() {
		return maccio00Cop;
	}

	public void setMaccio00Cop(Maccio00 maccio00Cop) {
		this.maccio00Cop = maccio00Cop;
	}

	public List<Maccio00> getListMaccio00() {
		return listMaccio00;
	}

	public void setListMaccio00(List<Maccio00> listMaccio00) {
		this.listMaccio00 = listMaccio00;
	}

	public List<Maccio00> getFilteredMaccio00() {
		return filteredMaccio00;
	}

	public void setFilteredMaccio00(List<Maccio00> filteredMaccio00) {
		this.filteredMaccio00 = filteredMaccio00;
	}

	public boolean isInfoGuardada() {
		return infoGuardada;
	}

	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
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

	public int getTabView() {
		return tabView;
	}

	public void setTabView(int tabView) {
		this.tabView = tabView;
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

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public InputText getInputTextIdtcioColumn() {
		return inputTextIdtcioColumn;
	}

	public void setInputTextIdtcioColumn(InputText inputTextIdtcioColumn) {
		this.inputTextIdtcioColumn = inputTextIdtcioColumn;
	}

	public InputText getInputTextIdmcioColumn() {
		return inputTextIdmcioColumn;
	}

	public void setInputTextIdmcioColumn(InputText inputTextIdmcioColumn) {
		this.inputTextIdmcioColumn = inputTextIdmcioColumn;
	}
	
}