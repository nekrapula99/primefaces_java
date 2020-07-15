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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMregcg00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mregcg00;
import com.tlm.faelecEntities.model.entities.Mtipre00;


@Controller
@ManagedBean
@SessionScoped
public class SeMregcg00 extends Control implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mregcg00Service}")
	private IMregcg00Service mregcg00Service;
	
	@ManagedProperty(value = "#{mconca00Service}")
	private IMconca00Service mconca00Service;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mconca00 mconca00;
	
	private Mregcg00 mregcg00;
	private Mregcg00 mregcg00Cop;
	private List<Mregcg00> listMregcg00;
	private List<Mregcg00> filteredMregcg00;
	
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

	private InputText inputTextIdcegcColumn;
	private InputText inputTextIdvegcColumn;


	@PostConstruct
	public void init(){
		try{
			super.init("MREGCG00");
			mregcg00 = new Mregcg00();
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
			getSeListas().actualizarListas(NombresListas.listMgenusRegimen);
			getSeListas().actualizarListas(NombresListas.listMgenusContribuyente);
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
			//seListas.actualizarListas(NombresListas.listMconca);
			mregcg00 = new Mregcg00();
			mregcg00.setRegegc(true);
			//getSeListas().actualizarListas(NombresListas.listMconca);
			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event) {
		try {
			//accion=Constantes.ACCION_MODIFICAR;
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			seListas.actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMgenusRegimen);
			getSeListas().actualizarListas(NombresListas.listMregcg00);
			getSeListas().actualizarListas(NombresListas.listMgenusContribuyente);
			//actualizarListasCompania(mregcg00.getMgenus001().getCodius());
			if(mregcg00.getMgenus001() != null){
				actualizarListasCompania(mregcg00.getMgenus001().getCodius());
				getSeControlFactura().cargarParametros(true,null,mregcg00.getMgenus001().getIdtrus());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}
			infoGuardada = false;

			reiniciarListasMaestro();
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mregcg00.getMgenus001(),mregcg00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mregcg00.getMgenus002(), mregcg00.getMgenus002().getMtipre00().getTipore());
			}
			mregcg00Cop= (Mregcg00) mregcg00.clone();	
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compañias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusRegimen, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMregcg00, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusContribuyente, listMconca00ActualizarListas);

			RequestContext.getCurrentInstance().update("formTable");
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	
	public void autocompleteActualizarListas (){
		try{
			if(mregcg00.getMgenus001() != null){
			actualizarListasCompania(mregcg00.getMgenus001().getCodius());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		try{
			listMregcg00= mregcg00Service.listMregcg00ByCriteria(new Mregcg00(),getSeControlFactura().getCompaniasUsu());			
			//UtilsJSF.resetDTable("formTable:dTable");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			mregcg00.setUseegc(getSeControlFactura().codusu);
			mregcg00.setPrgegc("Contribuyentes " + getClass().getName());
			mregcg00.setFeaegc(new Date());
			mregcg00.setMaqegc(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
//		
		//Validacion lista  Tipo contribuyente 
		if(mregcg00.getMgenus001() != null &&  mregcg00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcegc")+" "+ mregcg00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
//		//Validacion lista Tipo Régimen 
		if(mregcg00.getMgenus002() != null && mregcg00.getMgenus002().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idvegc")+" "+mregcg00.getMgenus002().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}

		
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(mregcg00.getMgenus001()) && permisoCampos.get("idcegc").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcegc")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcegcColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcegcColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcegcColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcegcColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mregcg00.getMgenus002()) && permisoCampos.get("idvegc").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idvegc")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdvegcColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdvegcColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdvegcColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdvegcColumn.getClientId());	
		}
		
		return validacion;
	}
	
	
	public void save() {
		try {										
			//Se guardan los datos de la cola estandar
			colaEstandar();
			
			if(validarDatosRequeridos()){
				return;
			}
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					mregcg00Service.save(mregcg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mregcg00.toString(),mregcg00.toStringId(),mregcg00Cop.toString()):null);
				}else return;
			} else {

				mregcg00Service.save(mregcg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mregcg00.toString(),mregcg00.toStringId(),null):null);
			 }
			cargarDatos();
			infoGuardada = true;
			mregcg00Cop= (Mregcg00) mregcg00.clone();
			getSeListas().actualizarListas(NombresListas.listMconca);	
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
			mregcg00 = (Mregcg00) event.getComponent().getAttributes().get("mregcg00");
			mregcg00Service.removeMregcg00(mregcg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mregcg00.toString(),mregcg00.toStringId()):null);
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
	
	public void evtCloseDialogMregcg(CloseEvent event) {
		try {
			update = null;
			
			if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){

	        	mregcg00.setMgenus001(mregcg00Cop.getMgenus001());
	        	mregcg00.setMgenus002(mregcg00Cop.getMgenus002());
	        	
	        	}
	        	evtCloseDialog(event);
			
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
		
		//Maestro MREGCG00 nosorio-23012018 
		if (strClassLlama.equalsIgnoreCase("seMrerer00")) {
			SeMrerer00 seMrerer00 = (SeMrerer00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mregcg00")) {
				System.out.println("Mregcg 1");
					seMrerer00.getMrerer00().setMregcg001((Mregcg00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
		}
		//Maestro MREGCG00 nosorio-23012018 
		if (strClassLlama.equalsIgnoreCase("seMrerer00")) {
			SeMrerer00 seMrerer00 = (SeMrerer00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mregcg00")) {
				System.out.println("Mregcg 2");
					seMrerer00.getMrerer00().setMregcg002((Mregcg00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
		}
		
		// 23012018 - Mconca00
		if (strClassLlama.equalsIgnoreCase("seMconca00")) {
			SeMconca00 seMconca00 = (SeMconca00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mregcg00")) {
				if (nombreLista.equalsIgnoreCase("contribuyente")) {
					seMconca00.getMconca00().setMregcg004((Mregcg00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");

				}
			
			}
		}
		//MGENTE00
		if (strClassLlama.equalsIgnoreCase("seMgente00")) {
			SeMgente00 seMgente00 = (SeMgente00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mregcg00")) {
				if (nombreLista.equalsIgnoreCase("contribuyente")) {
					seMgente00.getMgente00().setMregcg005((Mregcg00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");

				}
			
			}
		}
		
		update=null;
		
		
	}
	
	//Metodo que autocompleta todas las listas de Mfunfu00
		public List<Mregcg00> completeMregcg(String query) {		

	        List<Mregcg00> results = new ArrayList<Mregcg00>();   
	        List<Mregcg00> listMregcg00 = getSeListas().getListMregcg00();

	        for (Mregcg00 obj : listMregcg00) {        	
	        	if(obj.getCoregc().toLowerCase().contains(query.toLowerCase())){
	        		results.add(obj);        		
	    		}			
			}
	        return results;
	    }

	public IMregcg00Service getMregcg00Service() {
		return mregcg00Service;
	}

	public void setMregcg00Service(IMregcg00Service mregcg00Service) {
		this.mregcg00Service = mregcg00Service;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public Mregcg00 getMregcg00() {
		return mregcg00;
	}

	public void setMregcg00(Mregcg00 mregcg00) {
		this.mregcg00 = mregcg00;
	}

	public Mregcg00 getMregcg00Cop() {
		return mregcg00Cop;
	}

	public void setMregcg00Cop(Mregcg00 mregcg00Cop) {
		this.mregcg00Cop = mregcg00Cop;
	}

	public List<Mregcg00> getListMregcg00() {
		return listMregcg00;
	}

	public void setListMregcg00(List<Mregcg00> listMregcg00) {
		this.listMregcg00 = listMregcg00;
	}

	public List<Mregcg00> getFilteredMregcg00() {
		return filteredMregcg00;
	}

	public void setFilteredMregcg00(List<Mregcg00> filteredMregcg00) {
		this.filteredMregcg00 = filteredMregcg00;
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

	public IMconca00Service getMconca00Service() {
		return mconca00Service;
	}

	public void setMconca00Service(IMconca00Service mconca00Service) {
		this.mconca00Service = mconca00Service;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}
	
	public boolean isInfoGuardada() {
		return infoGuardada;
	}

	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
	}
	public InputText getInputTextIdcegcColumn() {
		return inputTextIdcegcColumn;
	}

	public void setInputTextIdcegcColumn(InputText inputTextIdcegcColumn) {
		this.inputTextIdcegcColumn = inputTextIdcegcColumn;
	}

	public InputText getInputTextIdvegcColumn() {
		return inputTextIdvegcColumn;
	}

	public void setInputTextIdvegcColumn(InputText inputTextIdvegcColumn) {
		this.inputTextIdvegcColumn = inputTextIdvegcColumn;
	}

}
