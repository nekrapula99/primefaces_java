package com.tlm.faelec.web.controller;

import java.io.Serializable;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

//import com.ibm.org.omg.CORBA.ExceptionDescription;
import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMswfsw00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mswfsw00;


@Controller
@ManagedBean
@SessionScoped
public class SeMswfsw00 extends Control implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mswfsw00Service}")
	private IMswfsw00Service mswfsw00Service;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mswfsw00 mswfsw00;
	private Mswfsw00 mswfsw00Cop;
	private List<Mswfsw00> listMswfsw00;
	private List<Mswfsw00> filteredMswfsw00;
	private Integer fracDec_CantiM; //
	private List<Mswfsw00> listMswfsw00After;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	private boolean tabInfBase; //Tab Informacion base
	private boolean tabOfertas; //Tab ofertas
	private String[] pestanas;
	boolean validacionRango;
	private Mgente00 mgente00;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	private InputText inputTextIdpvswColumn; 
	private InputText inputTextIdcmswColumn;
	
	private String fechaAutorizacion;
	
	
	@PostConstruct
	public void init(){
		try{
			super.init("MSWFSW00");
			mswfsw00 = new Mswfsw00();
			
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();			
			
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			setFracDec_CantiM(d1.intValue());
			
			Double d2=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			setFracDec_CantiM(d2.intValue());
			validacionRango = false;
			cargarDatos();					
		}catch(Exception e){
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
		
	}
		//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			getSeListas().actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMgenteCodtte);

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
			
			mswfsw00 = new Mswfsw00();
			mswfsw00.setRegrsw(true);
			getSeListas().actualizarListas(NombresListas.listMconca);
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
			if(mswfsw00.getMconca00() != null){
				actualizarListasCompania(mswfsw00.getMconca00().getCodcia());
				getSeControlFactura().cargarParametros(true,null,mswfsw00.getMconca00().getIdccia());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}

			
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mswfsw00.getMgente00(), mswfsw00.getMgente00().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mswfsw00.getMconca00(), null);
			}
			mswfsw00Cop= (Mswfsw00) mswfsw00.clone();	
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
				getSeListas().actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenteCodtte, listMconca00ActualizarListas);
				//RequestContext.getCurrentInstance().update("formTable");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	public void autocompleteActualizarListas (){
		try{
			if(mswfsw00.getMconca00() != null){
			actualizarListasCompania(mswfsw00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		try{
			listMswfsw00 = mswfsw00Service.listMswfsw00ByCriteria(new Mswfsw00(),getSeControlFactura().getCompaniasUsu());			
			List<Integer> listMswfsw00Temp = new ArrayList<Integer>(); 
			List<Mswfsw00> listMswfsw00Aux = new ArrayList<Mswfsw00>(); 
			for(Mswfsw00 obj: listMswfsw00){
				listMswfsw00Temp.add(Integer.parseInt(obj.getCoswsw()));
			
			}
			Collections.sort(listMswfsw00Temp);
			
			for(Integer intObj : listMswfsw00Temp){
				
				for(Mswfsw00 obj: listMswfsw00){
										
					if(intObj.equals(Integer.parseInt(obj.getCoswsw()))){
						listMswfsw00Aux.add(obj);
					}
					
				}

			}

			setListMswfsw00After(listMswfsw00Aux);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			mswfsw00.setUsersw(getSeControlFactura().codusu);
			mswfsw00.setPrgrsw("Sw para F.E x Compa��a " + getClass().getName());
			mswfsw00.setFearsw(new Date());
			mswfsw00.setMaqrsw(getSeControlFactura().ip);

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
//			//Validacion lista Cliente (Tercero)
		if(mswfsw00.getMgente00() != null && mswfsw00.getMgente00().getRegite() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idteci")+" "+mswfsw00.getMgente00().getCodite()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}

//			//Validacion lista Compania
		if(mswfsw00.getMconca00() != null && mswfsw00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcmci")+" "+mswfsw00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}	
		
		return validacion;
	}


	private boolean validarDatosRequeridos(){
		boolean validacion = false;

	
		if(Utils.isEmptyOrNull(mswfsw00.getMconca00())) //&& permisoCampos.get("idcmsw").getReqcam().equals("S")
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcmsw")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcmswColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcmswColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcmswColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcmswColumn.getClientId());	
		}
		
		/*if(Utils.isEmptyOrNull(mswfsw00.getMgente00())) //&& permisoCampos.get("idpvsw").getReqcam().equals("S")
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idpvsw")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdpvswColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdpvswColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdpvswColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdpvswColumn.getClientId());	
		}*/
	
		
		return validacion;
	}
	
	public void save() {
		try {										
			
			if(validarDatosRequeridos()){
				return;
			}
			if(mswfsw00.getMgente00()==null){
				//mswfsw00.setMgente00(mswfsw00.getMconca00().getNitcia()); //ESTO QUE
			}else{
			}
						
			
			
			colaEstandar();
			
				
				
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					
					 if(validarListas()==false){
					    	mswfsw00Service.save(mswfsw00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mswfsw00.toString(),mswfsw00.toStringId(),mswfsw00Cop.toString()):null);

					  }else return;
					
				    
				}else {
					mswfsw00Service.save(mswfsw00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mswfsw00.toString(),mswfsw00.toStringId(),null):null);
				 }
			cargarDatos();	
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}
			else{	
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
			mswfsw00 = (Mswfsw00) event.getComponent().getAttributes().get("mswfsw00");
			mswfsw00Service.removeMswfsw00(mswfsw00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mswfsw00.toString(),mswfsw00.toStringId()):null);
			cargarDatos();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().reset(":formTable:dTable");
			//RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
			}
		}
	}
		
	public void evtCloseDialogMswfsw(CloseEvent event) {
		try {
			update = null;
			//RequestContext.getCurrentInstance().update(":formDetalle:pnlMswfsw00");
			//RequestContext.getCurrentInstance().update("formTable");
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

	//Getters and Setters

	public IMswfsw00Service getMswfsw00Service() {
		return mswfsw00Service;
	}

	public void setMswfsw00Service(IMswfsw00Service mswfsw00Service) {
		this.mswfsw00Service = mswfsw00Service;
	}

	public Mswfsw00 getMswfsw00() {
		return mswfsw00;
	}

	public void setMswfsw00(Mswfsw00 mswfsw00) {
		this.mswfsw00 = mswfsw00;
	}

	public Mswfsw00 getMswfsw00Cop() {
		return mswfsw00Cop;
	}

	public void setMswfsw00Cop(Mswfsw00 mswfsw00Cop) {
		this.mswfsw00Cop = mswfsw00Cop;
	}

	public List<Mswfsw00> getListMswfsw00() {
		return listMswfsw00;
	}

	public void setListMswfsw00(List<Mswfsw00> listMswfsw00) {
		this.listMswfsw00 = listMswfsw00;
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

	public boolean isTabInfBase() {
		return tabInfBase;
	}

	public void setTabInfBase(boolean tabInfBase) {
		this.tabInfBase = tabInfBase;
	}

	public boolean isTabOfertas() {
		return tabOfertas;
	}

	public void setTabOfertas(boolean tabOfertas) {
		this.tabOfertas = tabOfertas;
	}

	public String[] getPestanas() {
		return pestanas;
	}

	public void setPestanas(String[] pestanas) {
		this.pestanas = pestanas;
	}

	public List<Mswfsw00> getFilteredMswfsw00() {
		return filteredMswfsw00;
	}
	public void setFilteredMswfsw00(List<Mswfsw00> filteredMswfsw00) {
		this.filteredMswfsw00 = filteredMswfsw00;
	}
	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}
	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
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
	
	public InputText getInputTextIdpvswColumn() {
		return inputTextIdpvswColumn;
	}
	public void setInputTextIdpvswColumn(InputText inputTextIdpvswColumn) {
		this.inputTextIdpvswColumn = inputTextIdpvswColumn;
	}
	public InputText getInputTextIdcmswColumn() {
		return inputTextIdcmswColumn;
	}
	public void setInputTextIdcmswColumn(InputText inputTextIdcmswColumn) {
		this.inputTextIdcmswColumn = inputTextIdcmswColumn;
	}
	public Mgente00 getMgente00() {
		return mgente00;
	}
	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}
	
	public String getFechaAutorizacion() {
		return fechaAutorizacion;
	}
	public void setFechaAutorizacion(String fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public List<Mswfsw00> getListMswfsw00After() {
		return listMswfsw00After;
	}
	public void setListMswfsw00After(List<Mswfsw00> listMswfsw00After) {
		this.listMswfsw00After = listMswfsw00After;
	}
	
	
	
}
