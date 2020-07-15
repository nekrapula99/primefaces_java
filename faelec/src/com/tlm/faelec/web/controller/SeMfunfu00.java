package com.tlm.faelec.web.controller;


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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMfunfu00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mfunfu00;

import com.tlm.faelec.utils.Utils;


@Controller
@ManagedBean
@SessionScoped
public class SeMfunfu00 extends Control implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mfunfu00Service}")
	private IMfunfu00Service mfunfu00Service;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mfunfu00 mfunfu00;
	private Mfunfu00 mfunfu00Cop;
	private List<Mfunfu00> listMfunfu00;
	private List<Mfunfu00> filteredMfunfu00;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	private boolean infoGuardada;
	
	private InputText inputTextIdpufuColumn;
	private InputText inputTextIdcufuColumn;

	

	
	@PostConstruct
	public void init(){
		try{
			super.init("MFUNFU00");
			mfunfu00 = new Mfunfu00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			cargarDatos();
			infoGuardada = false;
		}catch(Exception e){
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			getSeListas().actualizarListas(NombresListas.listMgenusSaludo);
			getSeListas().actualizarListas(NombresListas.listMgenusTident);
			getSeListas().actualizarListas(NombresListas.listMgenusPaises);
			getSeListas().actualizarListas(NombresListas.listMgenusCiudad);
			getSeListas().actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMgenusAreaLabor);
			getSeListas().actualizarListas(NombresListas.listMgenusCargoTercero);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mfunfu00 = new Mfunfu00();
			mfunfu00.setRegufu(true);
			seListas.actualizarListas(NombresListas.listMconca);
//			getSeListas().actualizarListas(NombresListas.listMconca);
			reiniciarListasMaestro();
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
			seListas.actualizarListas(NombresListas.listMconca);
			mfunfu00Cop= (Mfunfu00) mfunfu00.clone();	
			if(mfunfu00.getMgenus001() != null){
				actualizarListasCompania(mfunfu00.getMgenus001().getCodius());
				getSeControlFactura().cargarParametros(true,null,mfunfu00.getMgenus001().getIdtrus());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}

			
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMgenus001(), mfunfu00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMgenus002(), mfunfu00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMgenus003(), mfunfu00.getMgenus003().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMgenus004(), mfunfu00.getMgenus004().getMtipre00().getTipore());
				//UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMconca001(), mfunfu00.getMconca001().getMtipre00s(),null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMconca001(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMgenus005(), mfunfu00.getMgenus005().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mfunfu00.getMgenus006(), mfunfu00.getMgenus006().getMtipre00().getTipore());
			
			}
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
					getSeListas().actualizarListasCompania(NombresListas.listMgenusSaludo, listMconca00ActualizarListas);
					getSeListas().actualizarListasCompania(NombresListas.listMgenusTident, listMconca00ActualizarListas);
					getSeListas().actualizarListasCompania(NombresListas.listMgenusPaises, listMconca00ActualizarListas);
					getSeListas().actualizarListasCompania(NombresListas.listMgenusPaises, listMconca00ActualizarListas);
					getSeListas().actualizarListasCompania(NombresListas.listMgenusCiudad , listMconca00ActualizarListas);
					getSeListas().actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
					getSeListas().actualizarListasCompania(NombresListas.listMgenusAreaLabor , listMconca00ActualizarListas);
					getSeListas().actualizarListasCompania(NombresListas.listMgenusCargoTercero, listMconca00ActualizarListas);
					
					//RequestContext.getCurrentInstance().update("formTable");
				}catch(Exception e){
					e.printStackTrace();
				}
			}


	
	public void autocompleteActualizarListas (){
		try{
			if(mfunfu00.getMgenus001() != null){
			actualizarListasCompania(mfunfu00.getMgenus001().getCodius());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		try{
			listMfunfu00= mfunfu00Service.listMfunfu00ByCriteria(new Mfunfu00(),getSeControlFactura().getCompaniasUsu());			
			//UtilsJSF.resetDTable("formTable:dTable");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			mfunfu00.setUseufu(getSeControlFactura().codusu);
			mfunfu00.setPrgufu("Funcionarios " + getClass().getName());
			mfunfu00.setFeaufu(new Date());
			mfunfu00.setMaqufu(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
//		
		//Validacion lista  Tipo contribuyente 
		if(mfunfu00.getMgenus001() != null &&  mfunfu00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idsufu")+" "+ mfunfu00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
//		//Validacion lista Tipo R�gimen 
		if(mfunfu00.getMgenus002() != null && mfunfu00.getMgenus002().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idtufu")+" "+mfunfu00.getMgenus002().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista  Tipo contribuyente 
		if(mfunfu00.getMgenus003() != null &&  mfunfu00.getMgenus003().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idpufu")+" "+ mfunfu00.getMgenus003().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
//				//Validacion lista Tipo R�gimen 
		if(mfunfu00.getMgenus004() != null && mfunfu00.getMgenus004().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcufu")+" "+mfunfu00.getMgenus004().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista  Tipo contribuyente 
		if(mfunfu00.getMgenus005() != null &&  mfunfu00.getMgenus005().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idaufu")+" "+ mfunfu00.getMgenus005().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
//		//Validacion lista Tipo R�gimen 
		if(mfunfu00.getMgenus006() != null && mfunfu00.getMgenus006().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idgufu")+" "+mfunfu00.getMgenus006().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista Compa�ia
		if(mfunfu00.getMconca001() != null && mfunfu00.getMconca001().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idiufu")+" "+mfunfu00.getMconca001().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(mfunfu00.getMgenus003()) && permisoCampos.get("idpufu").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idpufu")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdpufuColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdpufuColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdpufuColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdpufuColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mfunfu00.getMgenus004()) && permisoCampos.get("idcufu").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcufu")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcufuColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcufuColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcufuColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcufuColumn.getClientId());	
		}
		
		return validacion;
	}
	
	
	public void save() {
		try {										
			//Se guardan los datos de la cola estandar
			//validarRango();
			
			if(validarDatosRequeridos()){
				return;
			}
			colaEstandar();
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					
					mfunfu00Service.save(mfunfu00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mfunfu00.toString(),mfunfu00.toStringId(),mfunfu00Cop.toString()):null);
				}else return;
			} else {
				mfunfu00Service.save(mfunfu00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mfunfu00.toString(),mfunfu00.toStringId(),null):null);
			 }
			cargarDatos();
			infoGuardada = true;
			mfunfu00Cop= (Mfunfu00) mfunfu00.clone();
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
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
			mfunfu00 = (Mfunfu00) event.getComponent().getAttributes().get("mfunfu00");
			mfunfu00Service.removeMfunfu00(mfunfu00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mfunfu00.toString(),mfunfu00.toStringId()):null);
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
	
	public void evtCloseDialogMpreci(CloseEvent event) {
		try {
			update = null;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
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
	
	public void llenarObjectCall(SelectEvent event) {
		if (objSeCall == null) {
			return;
		}
		String strClassLlama = objSeCall.getClass().getSimpleName();
		
		//Maestro MFUNFU00 nosorio-12122018 
				if (strClassLlama.equalsIgnoreCase("seTqigqg00")) {
					SeTqigqg00 seTqigqg00 = (SeTqigqg00) objSeCall;
					if (nombreTabla.equalsIgnoreCase("mfunfu00")) {
						if (nombreLista.equalsIgnoreCase("FunciComer")) {
							seTqigqg00.getTqigqg00().setMfunfu001((Mfunfu00) event.getObject());
							RequestContext.getCurrentInstance().reset("formDetalle");	
						}
						else if (nombreLista.equalsIgnoreCase("FunciApoyo")) {
							seTqigqg00.getTqigqg00().setMfunfu002((Mfunfu00) event.getObject());
							RequestContext.getCurrentInstance().reset("formDetalle");	
						}
					
					}
				}
		
		update=null;
		
		
	}
	
	//Metodo que autocompleta todas las listas de Mfunfu00
	public List<Mfunfu00> completeMfunfu(String query) {		

        List<Mfunfu00> results = new ArrayList<Mfunfu00>();   
        List<Mfunfu00> listMfunfu00 = getSeListas().getListMfunfu();

        for (Mfunfu00 obj : listMfunfu00) {        	
        	if(obj.getCocufu().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
		
	public void evtCloseDialogMfunfu(CloseEvent event) {
        try {   
        	update=null;
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
            	
        		mfunfu00.setMgenus001(mfunfu00Cop.getMgenus001());
        		mfunfu00.setMgenus002(mfunfu00Cop.getMgenus001());
        		mfunfu00.setMgenus003(mfunfu00Cop.getMgenus001());
        		mfunfu00.setMgenus004(mfunfu00Cop.getMgenus001());
        		mfunfu00.setMgenus005(mfunfu00Cop.getMgenus001());
        		mfunfu00.setMgenus006(mfunfu00Cop.getMgenus001());
        		mfunfu00.setMconca001(mfunfu00Cop.getMconca001());
        		
            
            	}
        	evtCloseDialog(event);
        }catch(Exception e) {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	

	public IMfunfu00Service getMfunfu00Service() {
		return mfunfu00Service;
	}

	public void setMfunfu00Service(IMfunfu00Service mfunfu00Service) {
		this.mfunfu00Service = mfunfu00Service;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}


	public Mfunfu00 getMfunfu00() {
		return mfunfu00;
	}

	public void setMfunfu00(Mfunfu00 mfunfu00) {
		this.mfunfu00 = mfunfu00;
	}

	public Mfunfu00 getMfunfu00Cop() {
		return mfunfu00Cop;
	}

	public void setMfunfu00Cop(Mfunfu00 mfunfu00Cop) {
		this.mfunfu00Cop = mfunfu00Cop;
	}

	public List<Mfunfu00> getListMfunfu00() {
		return listMfunfu00;
	}

	public void setListMfunfu00(List<Mfunfu00> listMfunfu00) {
		this.listMfunfu00 = listMfunfu00;
	}

	public List<Mfunfu00> getFilteredMfunfu00() {
		return filteredMfunfu00;
	}

	public void setFilteredMfunfu00(List<Mfunfu00> filteredMfunfu00) {
		this.filteredMfunfu00 = filteredMfunfu00;
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
	//Metodos de acceso 


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

	public InputText getInputTextIdpufuColumn() {
		return inputTextIdpufuColumn;
	}

	public void setInputTextIdpufuColumn(InputText inputTextIdpufuColumn) {
		this.inputTextIdpufuColumn = inputTextIdpufuColumn;
	}

	public InputText getInputTextIdcufuColumn() {
		return inputTextIdcufuColumn;
	}

	public void setInputTextIdcufuColumn(InputText inputTextIdcufuColumn) {
		this.inputTextIdcufuColumn = inputTextIdcufuColumn;
	}	

}

