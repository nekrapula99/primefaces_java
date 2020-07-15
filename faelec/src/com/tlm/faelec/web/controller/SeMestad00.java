package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMestad00Service;
import com.tlm.faelec.service.maestros.IMgencg00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mtipre00;



@Controller
@ManagedBean
@SessionScoped
public class SeMestad00 extends Control implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables del managed bean
	@ManagedProperty(value = "#{mestad00Service}")
	private IMestad00Service mestad00Service;
	
	@ManagedProperty(value = "#{mgencg00Service}")
	private IMgencg00Service mgencg00Service;
	
	private List<Mestad00> filteredMestad00;
	private List<Mestad00> listMestad00;
	private Mestad00 mestad00;
	private Mestad00 mestad00Cop;
	private boolean infoGuardada;
	private List<Mgencg00> listMgencg00;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	
	private String accion;
	private String titulo;
	
	private InputText inputTextIdttesColumn;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	// Variables para el manejo de las listas de valores
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private String nombreLista2;
	private Object objSeCall;
	
	//Metodos del managedBean
	@PostConstruct
	public void init() {
		try {
			super.init("MESTAD00");
			//mestad00 = new Mestad00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			infoGuardada = false;
		    tabla=super.getTabla();
		    titulo=getSeControlFactura().getTitulo();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cargarDatos() {	
		try {
			listMestad00 = mestad00Service.listMestad00ByCriteria(new Mestad00(),getSeControlFactura().getCompaniasUsu() );
			//Cargamos la Base fecha alerta
			Mgencg00 mgencg00 = new Mgencg00();
			mgencg00.setMtipre00(new Mtipre00());
			mgencg00.setRegicg(true);
			mgencg00.getMtipre00().setTipore(Utils.paramsRb.getString("mgencg_codtcg_fechaalerta"));
			listMgencg00 = mgencg00Service.listMgencg00ByCriteria(mgencg00, getSeControlFactura().getCompaniasUsu()); 
			UtilsJSF.resetDTable("formTable:dTable");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			seListas.actualizarListas(NombresListas.listMconca);
			seListas.actualizarListas(NombresListas.listMtiptx00Mestad00);
			seListas.actualizarListas(NombresListas.listMacdio00);
			seListas.actualizarListas(NombresListas.listMtiptx00);
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
			mestad00 = new Mestad00();
			mestad00.setRegtes(true);
			reiniciarListasMaestro();
			infoGuardada = false;
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
			mestad00Cop=(Mestad00) mestad00.clone(); 
			//Se cargan los valores de las listas deacuerdo a la compa�ia que tenga el objeto
			if(mestad00.getMconca00() != null){
				actualizarListasCompania(mestad00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();
			}
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de la lista al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mestad00.getMconca00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mestad00.getMtiptx00(), null);				
			}		
			infoGuardada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
		public void actualizarListasCompania (String codigoCompania){
			try{
				List<String> listMconca00ActualizarListas;
				listMconca00ActualizarListas = new ArrayList<String>();
				listMconca00ActualizarListas.add(codigoCompania);
				seListas.actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
				seListas.actualizarListasCompania(NombresListas.listMtiptx00Mestad00, listMconca00ActualizarListas);
				seListas.actualizarListasCompania(NombresListas.listMacdio00, listMconca00ActualizarListas);
				seListas.actualizarListasCompania(NombresListas.listMtiptx00, listMconca00ActualizarListas);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	//Actualiza las listas compa�ia desde el metodo autocompletar
		public void autocompleteActualizarListas (){
			try{
				if(mestad00.getMconca00() != null){
					actualizarListasCompania(mestad00.getMconca00().getCodcia());
				}else{
					reiniciarListasMaestro();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	//Se valida que las listas que tiene el usuario en el formulario esten activas 
	private boolean validarListas(){
		boolean validacion = false;
		
		//Validacion lista FK_MESTAD00_MCONCA00_IDCMES
		if(mestad00.getMconca00() != null && mestad00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idcmes")+" "+mestad00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista FK_MESTAD00_MTIPTX00_IDTTES
		if(mestad00.getMtiptx00() != null && mestad00.getMtiptx00().isRegtxt() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idttes")+" "+mestad00.getMtiptx00().getCotrtx()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista FK_MACDIO00
		/*if(mestad00.getMacdio00() != null && mestad00.getMacdio00().isRegdio() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idaees")+" "+mestad00.getMacdio00().getDscdio()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}*/
		
		return validacion;
	}
	
	//Se validan los datos que son requeridos por BD o por Dinox
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		//Id tipo transacci�n 
		
		
		if(Utils.isEmptyOrNull(mestad00.getMtiptx00()) && permisoCampos.get("idttes").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,getIdiomasHm().get("idttes")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdttesColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdttesColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdttesColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdttesColumn.getClientId());	
		}
		//Codigo estado
		if(Utils.isEmptyOrNull(mestad00.getCotres())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,getIdiomasHm().get("cotres")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Compa�ia
		if(Utils.isEmptyOrNull(mestad00.getMconca00()) && getPermisoCampos().get("idcmes").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcmes")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Descripcion
		if(Utils.isEmptyOrNull(mestad00.getDscres()) && getPermisoCampos().get("dscres").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,getIdiomasHm().get("dscres")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Estado
		/*if(Utils.isEmptyOrNull(mestad00.getMacdio00()) && getPermisoCampos().get("idaees").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idaees")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}*/
		//A�o Alerta
		if(Utils.isEmptyOrNull(mestad00.getAaales()) && getPermisoCampos().get("aaales").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,getIdiomasHm().get("aaales")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Mes Alerta
		if(Utils.isEmptyOrNull(mestad00.getMmales()) && getPermisoCampos().get("mmales").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("mmales")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Dia Alerta
		if(Utils.isEmptyOrNull(mestad00.getDdales()) && getPermisoCampos().get("ddales").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("ddales")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Hora Alerta
		if(Utils.isEmptyOrNull(mestad00.getHhales()) && getPermisoCampos().get("hhales").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("hhales")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//Base fecha Alerta
		if(Utils.isEmptyOrNull(mestad00.getBfeaes()) && getPermisoCampos().get("bfeaes").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("bfeaes")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}

		return validacion;
	}
	
	public void save() {
		try {
			
			if(validarDatosRequeridos()){
				return;
			}
			
			colaEstandar();
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
				mestad00Service.save(mestad00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mestad00.toString(),mestad00.toStringId(),mestad00Cop.toString()):null);
				}else return;
			}
			else
			{
			   mestad00Service.save(mestad00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mestad00.toString(),mestad00.toStringId(),null):null);
			}
			cargarDatos();
			seListas.actualizarListas(NombresListas.listMestad00);
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
			mestad00 = (Mestad00) event.getComponent().getAttributes().get("mestad00");
			mestad00Service.removeMestad00(mestad00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mestad00.toString(),mestad00.toString()):null);
			cargarDatos();
			seListas.actualizarListas(NombresListas.listMestad00);
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
		
	private void colaEstandar() {
		try {
			mestad00.setUsuaes(getSeControlFactura().codusu);
			mestad00.setPrgres("Estado" + getClass().getName());
			mestad00.setFeaces(new Date());
			mestad00.setMaques(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void preRenderView() {
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialogMestad00(CloseEvent event) {
		try {
			 update=null;
			if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && infoGuardada==false){
	        	mestad00.setMconca00(mestad00Cop.getMconca00());
	        	mestad00.setMtiptx00(mestad00Cop.getMtiptx00());
	        	//mestad00.setMacdio00(mestad00Cop.getMacdio00());
	        	}
			evtCloseDialog(event);
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	//Metodo que autocompleta todas las listas de Mconca00
		public List<Mestad00> completeMestad00(String query) {		

	        List<Mestad00> results = new ArrayList<Mestad00>();   
	        List<Mestad00> listMestad00 = seListas.getListMestad00();

	        for (Mestad00 obj : listMestad00) {        	
	        	if(obj.getDscres().toLowerCase().contains(query.toLowerCase())){
	        		results.add(obj);        		
	    		}			
			}
	        return results;
	    }
		
		public void llenarObjectCall(SelectEvent event) {
			try {
					if (objSeCall == null) {
						return;
					}
					String strClassLlama = objSeCall.getClass().getSimpleName();
					if (strClassLlama.equalsIgnoreCase("seMteste00")) {
						SeMteste00 seMteste00 = (SeMteste00) objSeCall;
						if (nombreTabla.equalsIgnoreCase("mestad00")) {
							if (nombreLista.equalsIgnoreCase("estadoOrigen")) {
								seMteste00.getMteste00Detalles().setMestad001((Mestad00) event.getObject());								
								RequestContext.getCurrentInstance().update(update);
							}else if (nombreLista.equalsIgnoreCase("estadoDestino")) {
								seMteste00.getMteste00Detalles().setMestad002((Mestad00) event.getObject());							
								RequestContext.getCurrentInstance().update(update);
							}
						}
					}
					
					//Maestro MACDIO00 nosorio-25012018 
					if (strClassLlama.equalsIgnoreCase("SeMacdio00")) {
						SeMacdio00 seMacdio00 = (SeMacdio00) objSeCall;
						if (nombreTabla.equalsIgnoreCase("mestad00")) {
							if (nombreLista.equalsIgnoreCase("EstadoDesti")) {
								seMacdio00.getMacdio00().setMestad00((Mestad00) event.getObject());
								RequestContext.getCurrentInstance().reset("formMacdio00Detalle");	
							}
							
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				}
	
				update=null;
			}
		
//		public void llenarObjectCall2(SelectEvent event)
//		{
//			try {
//				System.out.println("Prueba...........2");
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	
	public void validarEstadoIncial() {
		try {
			if (getAccion().equalsIgnoreCase(Constantes.ACCION_MODIFICAR) && mestad00.getMconca00()!= null && mestad00.getMtiptx00()!=null) {
				for (Mestad00 mestad : listMestad00) {
					if (mestad.getMtiptx00().getIdtptx().equals(mestad00.getMtiptx00().getIdtptx())
						&& mestad.getMconca00().getIdccia().equals(mestad00.getMconca00().getIdccia())
						&& mestad.getInines() == true && !mestad.getCotres().equalsIgnoreCase(mestad00.getCotres())) {
						UtilsJSF.facesLog( FacesMessage.SEVERITY_ERROR, getSeControlFactura().MENSAJES.get("Es_Inicial") + " " + mestad.getDscres()+ " " + mestad.getCotres());
						mestad00.setInines(false);
					}
				}
			} else if(getAccion().equalsIgnoreCase(Constantes.ACCION_NUEVO) && mestad00.getMconca00()!= null && mestad00.getMtiptx00()!=null){
				for (Mestad00 mestad : listMestad00) {
					if (mestad.getMtiptx00().getIdtptx().equals(mestad00.getMtiptx00().getIdtptx())
							&& mestad.getMconca00().getIdccia() .equals(mestad00.getMconca00().getIdccia())
							&& mestad.getInines() == true) {
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,getSeControlFactura().MENSAJES.get("Es_Inicial") + " " + mestad.getDscres()+ " " + mestad.getCotres());
						mestad00.setInines(false);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters and Setters
	 */

	public IMestad00Service getMestad00Service() {
		return mestad00Service;
	}

	public void setMestad00Service(IMestad00Service mestad00Service) {
		this.mestad00Service = mestad00Service;
	}


	public List<Mestad00> getFilteredMestad00() {
		return filteredMestad00;
	}

	public void setFilteredMestad00(List<Mestad00> filteredMestad00) {
		this.filteredMestad00 = filteredMestad00;
	}

	public List<Mestad00> getListMestad00() {
		return listMestad00;
	}

	public void setListMestad00(List<Mestad00> listMestad00) {
		this.listMestad00 = listMestad00;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public Mestad00 getMestad00Cop() {
		return mestad00Cop;
	}

	public void setMestad00Cop(Mestad00 mestad00Cop) {
		this.mestad00Cop = mestad00Cop;
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

	
	public IMgencg00Service getMgencg00Service() {
		return mgencg00Service;
	}

	public void setMgencg00Service(IMgencg00Service mgencg00Service) {
		this.mgencg00Service = mgencg00Service;
	}

	public List<Mgencg00> getListMgencg00() {
		return listMgencg00;
	}

	public void setListMgencg00(List<Mgencg00> listMgencg00) {
		this.listMgencg00 = listMgencg00;
	}

	public String getNombreLista2() {
		return nombreLista2;
	}

	public void setNombreLista2(String nombreLista2) {
		this.nombreLista2 = nombreLista2;
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

	public InputText getInputTextIdttesColumn() {
		return inputTextIdttesColumn;
	}

	public void setInputTextIdttesColumn(InputText inputTextIdttesColumn) {
		this.inputTextIdttesColumn = inputTextIdttesColumn;
	}

}