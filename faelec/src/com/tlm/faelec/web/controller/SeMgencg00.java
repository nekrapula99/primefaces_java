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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.component.inputnumber.InputNumber;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMgencg00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mtipre00;

@Controller
@ManagedBean
@SessionScoped
public class SeMgencg00 extends Control implements Serializable {
	private static final long serialVersionUID = 1L;

	//Variables del MBean
	@ManagedProperty(value = "#{mgencg00Service}")
	private IMgencg00Service mgencg00Service;
		
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	private Mgencg00 mgencg00;
	private Mgencg00 mgencg00Cop;
	private List<Mgencg00> listMgencg00;
	private List<Mgencg00> filteredMgencg00;
	private InputNumber inputNumber;
	
	//Variables de listas
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	private Integer fracDec_CantiM;
	private String cantidadDecimales;
	private String patternFecha;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	private InputText inputTextCodtcgColumn;
	private InputText   inputTextCodicgColumn;
	private InputTextarea inputTextDcttcgColumn;

	
	@PostConstruct
	public void init(){
		try {
			super.init("MGENCG00");
			mgencg00 = new Mgencg00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
//			Double d1=(Double)PARAMETROS.get("Dec_CantiM");
//			fracDec_CantiM=d1.intValue();
			cantidadDecimales = getSeControlFactura().PARAMETROS.get("Dec_CantiM").toString();
			inputNumber = new InputNumber();
			inputNumber.setDecimalPlaces(cantidadDecimales);
			cargarDatos();
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Metodos del managed bean
	
	public void cargarDatos()
	{
		Mgencg00 mgencgAux= new Mgencg00();
		mgencgAux.setMtipre00(new Mtipre00());
		listMgencg00=  mgencg00Service.listMgencg00ByCriteria(mgencgAux, getSeControlFactura().getCompaniasUsu()); 
		UtilsJSF.resetDTable("formTable:dTable");
	}
	
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
		
		if(mgencg00.getMtipre00() != null && mgencg00.getMtipre00().getRegitr() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("codtcg")+" "+mgencg00.getMtipre00().getTipore()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgencg00.getMconca00() != null && mgencg00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcmcg")+" "+mgencg00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}

		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(mgencg00.getMtipre00()) && permisoCampos.get("codtcg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("codtcg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextCodtcgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextCodtcgColumn.getClientId());
			validacion = true;
		}else{
			inputTextCodtcgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextCodtcgColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mgencg00.getCodicg()) && permisoCampos.get("codicg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("codicg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextCodicgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextCodicgColumn.getClientId());
			validacion = true;
		}else{
			inputTextCodicgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextCodicgColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mgencg00.getDcttcg()) && permisoCampos.get("dcttcg").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("dcttcg")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextDcttcgColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextDcttcgColumn.getClientId());
			validacion = true;
		}else{
			inputTextDcttcgColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextDcttcgColumn.getClientId());	
		}
		
		
		return validacion;
	}
	
	
	public void save() {
		try {
//			asignarVlrsDecimales();
			colaEstandar();
			if(validarDatosRequeridos()){
				return;
			}
//			if(cantidadDecimales!=null)
//			{
//			 mgencg00.setComncg(Double.parseDouble(cantidadDecimales));	
//			}
			if(getAccion().equals("M"))
			{
				if(validarListas()==false){
				mgencg00Service.save(mgencg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mgencg00.toString(),mgencg00.toStringId(),mgencg00Cop.toString()):null);
				}else return;
			}
			else
			{
			  mgencg00Service.save(mgencg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mgencg00.toString(),mgencg00.toStringId(),null):null);
			}
			cargarDatos();
			seListas.actualizarListas(validarNombreLista(mgencg00.getMtipre00().getTipore()));
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	
	private void colaEstandar() {
		try {				
			mgencg00.setUsercg(getSeControlFactura().codusu);
			mgencg00.setPrgmcg(getSeControlFactura().aplusu);
			mgencg00.setFeaccg(new Date());
			mgencg00.setMaqucg(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Metodo llamado desde el formulario de parametros
	public void obtenerListaMgencg002(){	
		//Lista necesria para filtrar las listas de valores por compa�ia
		List<String> listMconca00ActualizarListas;
		Mgencg00 mgencgAux = null;
		listMgencg00 = null;
		String compania;
		
		FacesContext context = FacesContext.getCurrentInstance();
		String codtcg = (String) UIComponent.getCurrentComponent(context).getAttributes().get("codtcg");	
		compania = (String) UIComponent.getCurrentComponent(context).getAttributes().get("compania");
		
		if(compania != null){
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(compania);	
		}else{
			listMconca00ActualizarListas = null;
		}
	     		
		if(codtcg!=null)
		{
			mgencgAux = new Mgencg00();
			mgencgAux.setMtipre00(new Mtipre00());
			mgencgAux.getMtipre00().setTipore(codtcg);
			mgencgAux.setRegicg(true);
			listMgencg00 =  mgencg00Service.listMgencg00ByCriteria(mgencgAux, listMconca00ActualizarListas);
		}	 
	}
	
//	private void asignarVlrsDecimales(){
//		try{
//			//Decimal (Cantidad en Mparme00)
//			if(mgencg00.getComncg()!=null)
//			{
//				mgencg00.setComncg(round(mgencg00.getComncg(), fracDec_CantiM));
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}	
//	}
	
	// Este metodo se llama desde el formulario de parametros
	public void cargarLista(AjaxBehaviorEvent event)
	{
		obtenerListaMgencg002();
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
	
	public void evtCloseDialogMgencg(CloseEvent event) {
        try {   
        	update=null;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			seListas.actualizarListas(NombresListas.listMconca);
			seListas.actualizarListas(NombresListas.listMtipreGConfi);
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
			//Se cargan los parametros al ingresar al controlador
			getSeControlFactura().cargarParametros(true,null,null);
			mgencg00 = new Mgencg00();
			mgencg00.setRegicg(true);
			validarFormatoFecha();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	public void accionModificar(SelectEvent event) {
		try {
			setAccion(Constantes.ACCION_MODIFICAR);
			//se captura la compa�ia, se cargan las listas y los parametros de acuerdo a la compa�ia
			if(mgencg00.getMconca00() != null){
				getSeControlFactura().cargarParametros(true,null,mgencg00.getMconca00().getIdccia());
				actualizarListasCompania(mgencg00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();
				getSeControlFactura().cargarParametros(true,null,null);
				validarFormatoFecha();
			}
			
			mgencg00Cop= (Mgencg00) mgencg00.clone();
			if(mgencg00.getComncg()!=null)
			{
			  cantidadDecimales=String.valueOf(mgencg00.getComncg());
			}
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgencg00.getMconca00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgencg00.getMtipre00(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			seListas.actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMtipreGConfi, listMconca00ActualizarListas);
			validarFormatoFecha();
			RequestContext.getCurrentInstance().update("formDetalle:cmdtcgColumn");
			RequestContext.getCurrentInstance().update("formDetalle:cm1dcgColumn");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void autocompleteActualizarListas (){
		try{
			if(mgencg00.getMconca00() != null){
			//Se cargan los parametros deacuerdo a la compa�ia seleccionada
				getSeControlFactura().cargarParametros(true,null,mgencg00.getMconca00().getIdccia());
				actualizarListasCompania(mgencg00.getMconca00().getCodcia());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mgencg00 = (Mgencg00) event.getComponent().getAttributes() .get("mgencg00");
			mgencg00Service.removeMgencg00(mgencg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR, mgencg00.toString(),mgencg00.toStringId()):null);
			cargarDatos();
			seListas.actualizarListas(validarNombreLista(mgencg00.getMtipre00().getTipore()));
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
						FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	public void llenarObjectCall(SelectEvent event) {

		try {

			if (objSeCall == null) {
				return;
			}
			String strClassLlama = objSeCall.getClass().getSimpleName();
	
			if (strClassLlama.equalsIgnoreCase("SeMtiptx00")) {
				SeMtiptx00 seMtiptx00 = (SeMtiptx00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgencg00")) {
					if (nombreLista.equalsIgnoreCase("codtrans")){
					seMtiptx00.getMtiptx00().setMgencg00(((Mgencg00) event.getObject()));				
					RequestContext.getCurrentInstance().reset("formDetalle");
					}			
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMparme00")) {
				SeMparme00 semparme00 = (SeMparme00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgencg00")) {
					semparme00.setMgencg00((Mgencg00) event.getObject());
					semparme00.getMparme00().setVapame(semparme00.getMgencg00().getCodicg());
					semparme00.getMparme00().setVapamh(semparme00.getMgencg00().getCodicg() +" - "+semparme00.getMgencg00().getDcttcg());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}			
			if (strClassLlama.equalsIgnoreCase("seMconca00")) {
				SeMconca00 seMconca00 = (SeMconca00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgencg00")) {
					if (nombreLista.equalsIgnoreCase("compania")){
					seMconca00.getMconca00().setMgencg00(((Mgencg00) event.getObject()));				
					RequestContext.getCurrentInstance().reset("formDetalle");
					}			
				}
			}
			//Maestro MCOTES00 nosorio-12122017 
			if (strClassLlama.equalsIgnoreCase("seMgente00")) {
				SeMgente00 seMgente00 = (SeMgente00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgencg00")) {
					if (nombreLista.equalsIgnoreCase("factura")) {
						seMgente00.getMgente00().setMgencg00((Mgencg00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
					
				}
			}
			if (strClassLlama.equalsIgnoreCase("seMacdio00")) {
				SeMacdio00 seMacdio00 = (SeMacdio00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgencg00")) {
					if (nombreLista.equalsIgnoreCase("evento")) {
						seMacdio00.getMacdio00().setMgencg00((Mgencg00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}

		 update=null;
	}
	
	//Metodo que se encarga de autocompletar mientras se esta escribiendo en las listas de Mgenus00
		public List<Mgencg00> completeMgencg(String query) {
			
			FacesContext context = FacesContext.getCurrentInstance();
			String codtcg = (String) UIComponent.getCurrentComponent(context).getAttributes().get("codtcg");
			
			//Esta variable se utiliza cuando se busca desde el maestro de parametros
			String clase = (String) UIComponent.getCurrentComponent(context).getAttributes().get("clase");
				if(clase!=null){
					 if(clase.equalsIgnoreCase("Parametro")){
						 codtcg=null;
					 }
				}
						
			List<Mgencg00> results = new ArrayList<Mgencg00>();
	        List<Mgencg00> listMgencg00Aux = new ArrayList<Mgencg00>();
	        if (codtcg!=null){
		        if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mtipre00"))){
		        	listMgencg00Aux = seListas.getListMgencgArbol();
		        }else if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mtiptx00"))){
		        	listMgencg00Aux = seListas.getListMgencgCodTransaccion();
		        }else if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mconca00"))){
		        	listMgencg00Aux = seListas.getListMgencgCompania();
		        }else if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_evento"))){
		        	listMgencg00Aux = seListas.getListMgencg00Evento();
		        }
	        }
	        if(UtilsJSF.selectOneMenu_permiteNull()){
	        	Mgencg00 vacia = new Mgencg00();
	        	vacia.setCodicg(Constantes.rb.getString("select_sin_vacio"));
	        	results.add(vacia);//Mgenus Null
	        }
	        
	      //Condicion utilizada cuando se realiza una busqueda desde el maestro de parametros
	        if(clase!=null){
		        if(clase.equalsIgnoreCase("Parametro")){
		        	listMgencg00Aux = listMgencg00;
		        }
	        }

	        for (Mgencg00 obj : listMgencg00Aux) {
	        	
//	        	if(obj.getMtipre00().getTipore().equalsIgnoreCase(codtus)){
	        		if(obj.getCodicg().toLowerCase().contains(query.toLowerCase())){
	            		results.add(obj);        		
	        		}
//	        	}		
			}
	        return results;
	   }
		
		//Este metodo valida el nombre de la lista que se va a actualizar guardar o eliminar
		private NombresListas validarNombreLista(String tipoRegistro){
			NombresListas nombre = null;
			if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mtipre00"))){
				nombre=NombresListas.listMgencgArbol;
			}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mtiptx00"))){
				nombre=NombresListas.listMgencgCodTransaccion;
			}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mconca00"))){
				nombre=NombresListas.listMgencgCompania;
			}
			return nombre;
		}
		
	//Metodos Getter y Setter

	public IMgencg00Service getMgencg00Service() {
		return mgencg00Service;
	}

	public void setMgencg00Service(IMgencg00Service mgencg00Service) {
		this.mgencg00Service = mgencg00Service;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public Mgencg00 getMgencg00() {
		return mgencg00;
	}

	public void setMgencg00(Mgencg00 mgencg00) {
		this.mgencg00 = mgencg00;
	}

	public Mgencg00 getMgencg00Cop() {
		return mgencg00Cop;
	}

	public void setMgencg00Cop(Mgencg00 mgencg00Cop) {
		this.mgencg00Cop = mgencg00Cop;
	}

	public List<Mgencg00> getListMgencg00() {
		return listMgencg00;
	}

	public void setListMgencg00(List<Mgencg00> listMgencg00) {
		this.listMgencg00 = listMgencg00;
	}

	public List<Mgencg00> getFilteredMgencg00() {
		return filteredMgencg00;
	}

	public void setFilteredMgencg00(List<Mgencg00> filteredMgencg00) {
		this.filteredMgencg00 = filteredMgencg00;
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

	public String getcantidadDecimales() {
		return cantidadDecimales;
	}

	public void setcantidadDecimales(String cantidadDecimales) {
		this.cantidadDecimales = cantidadDecimales;
	}

	public InputNumber getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(InputNumber inputNumber) {
		this.inputNumber = inputNumber;
	}

	public String getPatternFecha() {
		return patternFecha;
	}

	public void setPatternFecha(String patternFecha) {
		this.patternFecha = patternFecha;
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

	public InputText getInputTextCodtcgColumn() {
		return inputTextCodtcgColumn;
	}

	public void setInputTextCodtcgColumn(InputText inputTextCodtcgColumn) {
		this.inputTextCodtcgColumn = inputTextCodtcgColumn;
	}

	public InputText getInputTextCodicgColumn() {
		return inputTextCodicgColumn;
	}

	public void setInputTextCodicgColumn(InputText inputTextCodicgColumn) {
		this.inputTextCodicgColumn = inputTextCodicgColumn;
	}

	public InputTextarea getInputTextDcttcgColumn() {
		return inputTextDcttcgColumn;
	}

	public void setInputTextDcttcgColumn(InputTextarea inputTextDcttcgColumn) {
		this.inputTextDcttcgColumn = inputTextDcttcgColumn;
	}
	
}
