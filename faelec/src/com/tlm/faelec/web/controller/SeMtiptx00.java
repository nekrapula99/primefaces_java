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
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMtiptx00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;


@Controller
@ManagedBean
@SessionScoped
public class SeMtiptx00 extends Control implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4313790462907639363L;

	// variables del managed bean
	@ManagedProperty(value = "#{mtiptx00Service}")
	private IMtiptx00Service mtiptx00Service;		
			
	private List<Mtiptx00> listMtiptx00;
	private Mtiptx00 mtiptx00;
	private List<Mtiptx00> filteredMtiptx00;
	private Mtiptx00 mtiptx00Cop;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	private boolean infoGuardada;
	private String[] pestanas;
	private boolean tabInfGral1;
	private boolean tabInfGral2;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	
	private String accion;
	private String titulo;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	private InputText inputTextIdcmtxColumn;
	private InputText inputTextIdtttxColumn;
	private InputTextarea inputTextDstitxColumn;
	private InputText inputTextNotetxColumn;
	private InputText inputTextNrdctxColumn;
	
	
	//Variables para pesta�a
	private int tabView=0;
	
	//Metodos del managedBean
	@PostConstruct
	public void init() {
		try {
			super.init("MTIPTX00");
			mtiptx00 = new Mtiptx00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabInfGral1=false;
			tabInfGral2=false;
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
		    cargarDatos();			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
			FacesMessage.SEVERITY_ERROR);
		}
	}
	public void cargarDatos() {
		Mtiptx00 mtiptx00Aux00= new Mtiptx00();	
		listMtiptx00 = mtiptx00Service.listMtiptx00ByCriteria(mtiptx00Aux00, getSeControlFactura().getCompaniasUsu());
		UtilsJSF.resetDTable("formTable:dTable");
	}
	
	public void mostrarPestana()
	{
		try{
			   pestanas=((String) getSeControlFactura().PARAMETROS.get("Vis_MaeTXT")).split(","); 
			   for (int i=0; i < pestanas.length;i++){
				   if(pestanas[i].equals("1"))
				   {
					   tabInfGral1=true;
				   }
				   if(pestanas[i].equals("2"))
				   {
					   tabInfGral2=true;
				   }
			   }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Se actualizan las listas que contiene el maestro para que muestren los datos por default
	private void reiniciarListasMaestro(){
		try {
			seListas.actualizarListas(NombresListas.listMconca);
			seListas.actualizarListas(NombresListas.listMgencgCodTransaccion);
			seListas.actualizarListas(NombresListas.listMgenusClaseDocumentos);
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
			reiniciarListasMaestro();
			mostrarPestana();
			tabView=0;		
			mtiptx00 = new Mtiptx00();			
			mtiptx00.setRegtxt(true);
			mtiptx00.setIncptx(1); // Si es nuevo se propone 1
			mtiptx00.setInpctx("C");//Si es nuevo se propone CPC 
			//Cuando el registro es nuevo los combos posicion simbolo tienen como valor por defecto blanco 
			mtiptx00.setPscqtx(Utils.paramsRb.getString("mtiptx_blanco"));
			mtiptx00.setPsprtx(Utils.paramsRb.getString("mtiptx_blanco"));
			mtiptx00.setPsvltx(Utils.paramsRb.getString("mtiptx_blanco"));
			infoGuardada = false;
		} catch (Exception e) {
			 e.printStackTrace();
			 UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
			 FacesMessage.SEVERITY_ERROR);
		}
	}
	public void accionModificar(SelectEvent event) { 
		try {
			mostrarPestana();
			tabView=0;
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mtiptx00Cop= (Mtiptx00) mtiptx00.clone();			
			//se captura la compa�ia y cargan las listas deacuerdo a la compa�ia escogida por el usuario
			if(mtiptx00.getMconca00() != null){
				actualizarListasCompania(mtiptx00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();
			}
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de la lista al momento de hacer un submit en el guardar
//				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mtiptx00.getMgenus00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mtiptx00.getMgencg00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mtiptx00.getMconca00(), null);
				
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
				seListas.actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
				seListas.actualizarListasCompania(NombresListas.listMgencgCodTransaccion, listMconca00ActualizarListas);
				seListas.actualizarListasCompania(NombresListas.listMgenusClaseDocumentos, listMconca00ActualizarListas);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void autocompleteActualizarListas (){
			try{
				if(mtiptx00.getMconca00() != null){
					actualizarListasCompania(mtiptx00.getMconca00().getCodcia());
				}else{
					reiniciarListasMaestro();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
		//Validacion lista idgeus tipo identificacion
		if(mtiptx00.getMconca00() != null &&mtiptx00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idcmtx")+" "+mtiptx00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
//		if(mtiptx00.getMgenus00() != null && mtiptx00.getMgenus00().getRegius() == false){
//			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
//		    getIdiomasHm().get("cotrtx")+" "+mtiptx00.getMgenus00().getCodius()+" "+MENSAJES.get("Cam_Deshab"));
//			validacion = true;
//		}
		if(mtiptx00.getMgencg00() != null && mtiptx00.getMgencg00().isRegicg() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idtttx")+" "+mtiptx00.getMgencg00().getCodicg()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		
		return validacion;
	}
	
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		
		
		if(Utils.isEmptyOrNull(mtiptx00.getMconca00()) && permisoCampos.get("idcmtx").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("Idcmtx")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcmtxColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcmtxColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcmtxColumn.setStyle("border-color: #9a9a9a;");			 
				RequestContext.getCurrentInstance().update(inputTextIdcmtxColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mtiptx00.getMgencg00()) && permisoCampos.get("idtttx").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idtttx")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdtttxColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdtttxColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdtttxColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdtttxColumn.getClientId());	
		}
		
		
		return validacion;
	}
	
	public void save() {
		try {
			
			if(validarDatosRequeridos()){
				return;
			}
			colaEstandar();
			//Variables Factores			
			if(getAccion().equals("M"))
			{
				if(validarListas()==false){
				mtiptx00Service.save(mtiptx00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mtiptx00.toString(),mtiptx00.toStringId(),mtiptx00Cop.toString()):null);
				}else return;
			}
			else
			{
				mtiptx00Service.save(mtiptx00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mtiptx00.toString(),mtiptx00.toStringId(),null):null);
			}
			cargarDatos();
			seListas.actualizarListas(NombresListas.listMtiptx00);
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
	private void colaEstandar(){
		  try {
		  mtiptx00.setUsetxt(getSeControlFactura().codusu);
		  mtiptx00.setPrgtxt("tipo transaccion " + getClass().getName());
		  mtiptx00.setFeatxt(new Date());
		  mtiptx00.setMaqtxt(getSeControlFactura().ip);
		   
		  } catch (Exception e) {
		   e.printStackTrace();
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
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mtiptx00 = (Mtiptx00) event.getComponent().getAttributes()
					.get("mtiptx00");
			mtiptx00Service.removeMtiptx00(
					mtiptx00,
					getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,
							mtiptx00.toString(),mtiptx00.toStringId()):null);
			cargarDatos();
			seListas.actualizarListas(NombresListas.listMtiptx00);
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	public void llenarObjectCall(SelectEvent event) {
		try {

			if (objSeCall == null) {
				return;
			}
			String strClassLlama = objSeCall.getClass().getSimpleName();
			if (strClassLlama.equalsIgnoreCase("SeMparca00")) {
				SeMparca00 seMparca00 = (SeMparca00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mtiptx00")) {			
					seMparca00.getMparca00().setMtiptx00((Mtiptx00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
			}
			else if (strClassLlama.equalsIgnoreCase("SeMestad00")) {
				SeMestad00 seMestad00 = (SeMestad00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mtiptx00")) {			
					seMestad00.getMestad00().setMtiptx00((Mtiptx00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
			}
			else if (strClassLlama.equalsIgnoreCase("SeMteste00")) {
				SeMteste00 seMteste00 = (SeMteste00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mtiptx00")) {			
					seMteste00.getMteste00().setMtiptx00((Mtiptx00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
					seMteste00.autocompleteActualizarListMestad00();
				}
			}
			else if (strClassLlama.equalsIgnoreCase("SeMdespr00")) {
				SeMdespr00 seMdespr00 = (SeMdespr00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mtiptx00")) {			
					seMdespr00.getMdespr00().setMtiptx00((Mtiptx00) event.getObject());
					RequestContext.getCurrentInstance().reset("formMdespr00Detalle");
				}
			}

			else if (strClassLlama.equalsIgnoreCase("SeMaccio00")) {
				SeMaccio00 seMaccio00 = (SeMaccio00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mtiptx00")) {			
					seMaccio00.getMaccio00().setMtiptx00((Mtiptx00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			else if (strClassLlama.equalsIgnoreCase("SeTqigqg00")) {
				SeTqigqg00 seTqigqg00 = (SeTqigqg00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mtiptx00")) {			
					seTqigqg00.getTqigqg00().setMtiptx00((Mtiptx00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			else if (strClassLlama.equalsIgnoreCase("SeMrafra00")) {
				SeMrafra00 seMrafra00 = (SeMrafra00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mtiptx00")) {			
					seMrafra00.getMrafra00().setMtiptx00((Mtiptx00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			
		}catch (Exception e) {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
						FacesMessage.SEVERITY_ERROR);
			}
			update = null;
		}
			
	//Metodo que autocompleta todas las listas de Mconca00
	public List<Mtiptx00> completeMtiptx00(String query) {		

        List<Mtiptx00> results = new ArrayList<Mtiptx00>();   
        List<Mtiptx00> listMconca00 = seListas.getListMtiptx00();

        for (Mtiptx00 obj : listMconca00) {
        	
        	if(obj.getCotrtx().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	
	public List<Mtiptx00> completeMtiptxEstado(String query) {		

        List<Mtiptx00> results = new ArrayList<Mtiptx00>();   
        List<Mtiptx00> listMconca00 = seListas.getListMtiptxEstado();

        for (Mtiptx00 obj : listMconca00) {        	
        	if(obj.getCotrtx().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	
	public void evtCloseDialogMtiptx00(CloseEvent event) {
		try {
			update = null;
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
        	mtiptx00.setMconca00(mtiptx00Cop.getMconca00());
        	mtiptx00.setMgencg00(mtiptx00Cop.getMgencg00());
        	}
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	public void evtCloseDialogMtiptxEstado(CloseEvent event)
	{
		try {
			update = null;
        	//if (!Utils.isEmptyOrNull(getSeControlFactura().getAccion()) && getSeControlFactura().getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
        	//mtiptx00.setMconca00(mtiptx00Cop.getMconca00());
        	//mtiptx00.setMgencg00(mtiptx00Cop.getMgencg00());
        	//}
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Getter y Setter
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
	public List<Mtiptx00> getFilteredMtiptx00() {
		return filteredMtiptx00;
	}
	public void setFilteredMtiptx00(List<Mtiptx00> filteredMtiptx00) {
		this.filteredMtiptx00 = filteredMtiptx00;
	}
	public Mtiptx00 getMtiptx00Cop() {
		return mtiptx00Cop;
	}
	public void setMtiptx00Cop(Mtiptx00 mtiptx00Cop) {
		this.mtiptx00Cop = mtiptx00Cop;
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
	public boolean getTabInfGral1() {
		return tabInfGral1;
	}
	public void setTabInfGral1(boolean tabInfGral1) {
		this.tabInfGral1 = tabInfGral1;
	}
	public boolean getTabInfGral2() {
		return tabInfGral2;
	}
	public void setTabInfGral2(boolean tabInfGral2) {
		this.tabInfGral2 = tabInfGral2;
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
	public InputText getInputTextIdcmtxColumn() {
		return inputTextIdcmtxColumn;
	}
	public void setInputTextIdcmtxColumn(InputText inputTextIdcmtxColumn) {
		this.inputTextIdcmtxColumn = inputTextIdcmtxColumn;
	}
	public InputText getInputTextIdtttxColumn() {
		return inputTextIdtttxColumn;
	}
	public void setInputTextIdtttxColumn(InputText inputTextIdtttxColumn) {
		this.inputTextIdtttxColumn = inputTextIdtttxColumn;
	}
	public InputTextarea getInputTextDstitxColumn() {
		return inputTextDstitxColumn;
	}
	public void setInputTextDstitxColumn(InputTextarea inputTextDstitxColumn) {
		this.inputTextDstitxColumn = inputTextDstitxColumn;
	}
	public InputText getInputTextNotetxColumn() {
		return inputTextNotetxColumn;
	}
	public void setInputTextNotetxColumn(InputText inputTextNotetxColumn) {
		this.inputTextNotetxColumn = inputTextNotetxColumn;
	}
	public InputText getInputTextNrdctxColumn() {
		return inputTextNrdctxColumn;
	}
	public void setInputTextNrdctxColumn(InputText inputTextNrdctxColumn) {
		this.inputTextNrdctxColumn = inputTextNrdctxColumn;
	}
	
	
	
}