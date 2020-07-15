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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMgencg00Service;
import com.tlm.faelec.service.maestros.IMgente00Service;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMparme00Service;
import com.tlm.faelec.service.maestros.IMtabla00Service;
import com.tlm.faelec.service.maestros.IMtipre00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mparme00;
import com.tlm.faelecEntities.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtipre00;

@Controller
@ManagedBean
@SessionScoped
public class SeMparme00 extends Control implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	 *Variables MBean
	 */

		
	@ManagedProperty(value = "#{seMgenus00}")
	private SeMgenus00 seMgenus00;
	
	@ManagedProperty(value = "#{mtipre00Service}")
	private IMtipre00Service mtipre00Service;
	
//	@ManagedProperty(value = "#{mparme00Service}")
//	private IMparme00Service mparme00Service;
	
	@ManagedProperty(value = "#{mtabla00Service}")
	private IMtabla00Service mtabla00Service;
	
	@ManagedProperty(value = "#{mgenus00Service}")
	private IMgenus00Service mgenus00Service;
	
	@ManagedProperty("#{mgente00Service}")
	private IMgente00Service mgente00Service;
	
	@ManagedProperty("#{mgencg00Service}")
	private IMgencg00Service mgencg00Service;
	

	private List<Mconca00> listMconca00;
	private List<Mparme00> filteredMparme00;
	private List<Mtipre00> listMtipre00;
	private List<Mtipre00> filteredMtipre00;
	private List<Mparme00> listMparme00;
	private Mparme00 mparme00;
	private Mparme00 mparme00Cop;
	private Mtipre00 mtipre00; 
	private Mtabla00 mtabla00; 
	private Mgenus00 mgenus00;
	private Mgente00 mgente00;
	private Mgencg00 mgencg00;
	private Mconca00 mconca00;
	private Maplic00 maplic00;
	private Boolean visibleCadena;
	private Boolean visibleFecha;
	private Boolean visibleNumero;
	private Boolean visibleTabla;
	private Boolean visibleRegistro;
	private Boolean visibleMgente;
	private Boolean visibleMgenus;
	private Boolean visibleMgencg;
	private Boolean visibleMconca;
	private Boolean visibleMtipre;
	private Date date;
	private String dialogoCargaMasiva;
	private List<Mgenus00> listMgenus00TiposParametro;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	
	private String accion;
	private String titulo;
	
	private InputTextarea inputTextNopameColumn;
	private InputTextarea inputTextDcpameColumn;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	
	public SeMgenus00 getSeMgenus00() {
		return seMgenus00;
	}

	public void setSeMgenus00(SeMgenus00 seMgenus00) {
		this.seMgenus00 = seMgenus00;
	}
	private String idparam;
	private Map<String, String> mapTipoParametros = null;

	
	//Metodos del Managedbean
	
	@PostConstruct
	public void init() {
		try {
			super.init("MPARME00");
			mparme00 = new Mparme00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			cargarDatos();
			listMgenus00TiposParametro = seMgenus00.obtenerListaMgenus00(Utils.paramsRb.getString(("mgenus_codtus_tipoParametro")));
		   	mapTipoParametros = mgenus00Service.getMapMgenus00ByCodtus(Utils.paramsRb.getString(("mgenus_codtus_tipoParametro")));	
		   	
		} catch (Exception e) {
			e.printStackTrace();
			 UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
			 FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			seListas.actualizarListas(NombresListas.listMconca);
			seListas.actualizarListas(NombresListas.listMtipre);
			seListas.cargarListaMtabla00();
			seListas.actualizarListas(NombresListas.listMtiptxEstado);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void accionNuevo(){
		try {
			//Se cargan los parametros al ingresar al controlador
			getSeControlFactura().cargarParametros(true,null,null);
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			reiniciarListasMaestro();
			mparme00 = new Mparme00();
			mparme00.setRegime(true);
			mtabla00 = new Mtabla00();
			mtipre00 = new Mtipre00();
			mgenus00 = new Mgenus00();
			mgente00 = new Mgente00();
			mgencg00 = new Mgencg00();
			validarFormatoFecha();
			setMostrarValores(true,false,false,false,false,false,false,false,false,false);
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event){
		try {
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			
			//Se cargan los parametros deacuerdo a la compa�ia seleccionada
			if(mparme00.getMconca00()!= null){
				getSeControlFactura().cargarParametros(true,null,mparme00.getMconca00().getIdccia());
				actualizarListasCompania(mparme00.getMconca00().getCodcia());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				validarFormatoFecha();
				reiniciarListasMaestro();
			}
			setMostrarValores(false,false,false,false,false,false,false,false,false,false);
			if(mparme00.getNomame()!=null && !mparme00.getNomame().equals(""))
			{
	            setVisibleRegistro(true);
	            setVisibleTabla(true);
	            setMtabla00(mtabla00Service.MtablaByCodtab(mparme00.getNomame()));
				if (mparme00.getNomame().equals("MGENTE00")) {
					setMtipre00(mtipre00Service.MtipreByTipore(mparme00.getTrmame()));
					setMgente00(mgente00Service.MgenteByCodite(mparme00.getVapame()));
					setVisibleMtipre(true);
					setVisibleMgente(true);
				} else if (mparme00.getNomame().equals("MGENUS00")) {
					setMtipre00(mtipre00Service.MtipreByTipore(mparme00.getTrmame()));
					setMgenus00(mgenus00Service.MgenusByCodius(mparme00.getVapame(), mtipre00.getTipore()));
					seMgenus00.setCodtusAux(getMtipre00().getTipore());
					seMgenus00.obtenerListaMgenus002();
					setVisibleMtipre(true);
					setVisibleMgenus(true);
				} else if (mparme00.getNomame().equals("MGENCG00")) {	
					setMtipre00(mtipre00Service.MtipreByTipore(mparme00.getTrmame()));
					setMgencg00(mgencg00Service.Mgencg00ByCodicg(mparme00.getVapame()));
					setVisibleMtipre(true);
					setVisibleMgencg(true);
				}else if (mparme00.getNomame().equals("MGENCG00")) {	
						setMtipre00(mtipre00Service.MtipreByTipore(mparme00.getTrmame()));
						setMgencg00(mgencg00Service.Mgencg00ByCodicgEv(mparme00.getVapame(),mtipre00.getTipore()));
						setVisibleMtipre(true);
						setVisibleMgencg(true);	
				}else {
					setVisibleRegistro(false);
					setVisibleCadena(true);
				}
			}
			
			if(mparme00.getTipame().equals("C")){
				setVisibleCadena(true);
			}else if(mparme00.getTipame().equals("F")){
				setVisibleFecha(true);
			}else if(mparme00.getTipame().equals("N")){
				setVisibleNumero(true);
			}
			
			mparme00Cop= (Mparme00) mparme00.clone();
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cargarDatos() {
		//Mparme00 mgencgAux= new Mgencg00();
		listMparme00 = getSeControlFactura().mparme00Service.ListMparmeByCriteria(getSeControlFactura().getCompaniasUsu(),new Mparme00());
		//UtilsJSF.resetDTable("formTable:dTable");
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			seListas.actualizarListasCompania(NombresListas.listMtipre, listMconca00ActualizarListas);
			validarFormatoFecha();
			RequestContext.getCurrentInstance().update("formDetalle:fepameColumn");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Se dispara este metodo al ejecutarse la accion con el autocompletar
	public void autocompleteActualizarListas (){
		try{
			String codigoCompania = null;
			if(mparme00.getMconca00() != null){
				codigoCompania= mparme00.getMconca00().getCodcia();
				//Se cargan los parametros deacuerdo a la compa�ia seleccionada
				getSeControlFactura().cargarParametros(true,null,mparme00.getMconca00().getIdccia());
				actualizarListasCompania(codigoCompania);
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Maplic00 consultarObjetoMaplic00(){ //Metodo que consulta el objeto Maplic que se encuentra en BD con la aplicacion en sesion
		maplic00 = new Maplic00(); //Se crea instancia de Maplic00 y se ingresa la aplicacion que esta actualmente en sesion
		maplic00.setCodapl(getSeControlFactura().aplusu);
		return getSeControlFactura().mparme00Service.consultarObjetoMaplic00(maplic00);
	}
	
   public void colaEstandar(){
	   	try{
			mparme00.setUsuame(getSeControlFactura().codusu);
			mparme00.setMaplic00(consultarObjetoMaplic00());
			mparme00.setPrgmme("parametros " + getClass().getName());
			mparme00.setFeacme(new Date());
			mparme00.setMaqume(getSeControlFactura().ip);
	   	}catch(Exception e){
	   		e.printStackTrace();
	   		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	   	}
   }
   private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(mparme00.getNopame()) && permisoCampos.get("nopame").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("nopame")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextNopameColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextNopameColumn.getClientId());
			validacion = true;
		}else{
			inputTextNopameColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextNopameColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mparme00.getDcpame()) && permisoCampos.get("dcpame").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("dcpame")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextDcpameColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextDcpameColumn.getClientId());
			validacion = true;
		}else{
			inputTextDcpameColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextDcpameColumn.getClientId());	
		}
	
		
		return validacion;
	}
	
   
   
	public void save() {
		try {
			
			if(validarDatosRequeridos()){
				return;
			}
			/*Se validan los campos obligatorios desde Java para evitar que el submit al momento
			 * de seleccionar algo en el drop down list bloquee la vista
			 */
			//Se guardan los datos de la cola estandar 
			colaEstandar();
			
			if (mgenus00 != null && mgenus00.getMtipre00() != null){
					mparme00.setTrmame(mgenus00.getMtipre00().getTipore());
					mparme00.setTrmamh(mgenus00.getMtipre00().getTipore()+" "+mgenus00.getMtipre00().getDescre());
				
			}else if (mgente00 != null && mgente00.getMtipre00() !=null){
				if(mgente00.getMtipre00().getTipore() !=null){
					mparme00.setTrmame(getMtipre00().getTipore());
					mparme00.setTrmamh(mtipre00.getTipore()+ " "+mtipre00.getDescre());	
				}
			}else if (mgencg00 != null && mgencg00.getMtipre00() != null){
				mparme00.setTrmame(getMtipre00().getTipore());
				mparme00.setTrmamh(mtipre00.getTipore()+ " "+mtipre00.getDescre());
			}
			
			if(mparme00.getTipame().equals("C")){
				mparme00.setVapamh(mparme00.getVapame());	
			}
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				getSeControlFactura().mparme00Service.save(mparme00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mparme00.toString(),mparme00.toStringId(),mparme00Cop.toString()):null);
			}
			else
			{
				getSeControlFactura().mparme00Service.save(mparme00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mparme00.toString(),mparme00.toStringId(),null):null);
			}
			cargarDatos();
			mparme00Cop= (Mparme00) mparme00.clone();
			getSeControlFactura().cargarParametros(true, listMparme00, null);
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_INFO);
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
			mparme00 = (Mparme00) event.getComponent().getAttributes().get("mparme00");
			getSeControlFactura().mparme00Service.removeMparme00(mparme00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mparme00.toString(),mparme00.toStringId()):null);
			cargarDatos();
			getSeControlFactura().cargarParametros(true, listMparme00, null);
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
	
	public void mostrarOnChangeAutoComplete(){
		try{
			setVisibleMtipre(false);
			setVisibleMgenus(false);
			setVisibleMgente(false);
			setVisibleMgencg(false);
			setVisibleCadena(false);
			setListMtipre00(null);
			getMparme00().setVapame(null);
			getMparme00().setVapamh(null);
			getMparme00().setTrmame(null);
			getMparme00().setNomame(null);
			setMtipre00(new Mtipre00());
			setMgenus00(new Mgenus00());
			setMgente00(new Mgente00());
			if(mtabla00 != null && mtabla00.getId().getCodtab() != null){
				if (mtabla00.getId().getCodtab().equals("MGENUS00")){
					setVisibleMtipre(true);
					setVisibleMgenus(true);
				}else if(mtabla00.getId().getCodtab().equals("MGENTE00")){
					setVisibleMtipre(true);
					setVisibleMgente(true);			
				}else if(mtabla00.getId().getCodtab().equals("MGENCG00")){
					setVisibleMtipre(true);
					setVisibleMgencg(true);			
				}else{
					setVisibleCadena(true);
				}
				RequestContext.getCurrentInstance().reset("formDetalle");
				RequestContext.getCurrentInstance().update("formDetalle:pnlDetalle");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//Metodo que se encarga de llenar la lista de Mtipre en el formulario de parametros
	public void obtenerCodtabMtabla(AjaxBehaviorEvent event){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			if(mparme00.getMconca00() != null){
				listMconca00ActualizarListas.add(mparme00.getMconca00().getCodcia());
			}else{
				listMconca00ActualizarListas = null;
			}
			if (getMtabla00().getId().getCodtab().equals("MGENUS00")) {
				getMtipre00().setTperre("U");
				seListas.obtenerMtipre(getMtipre00(), listMconca00ActualizarListas);
			}
			if (getMtabla00().getId().getCodtab().equals("MGENTE00")) {
				getMtipre00().setTperre("T");
				seListas.obtenerMtipre(getMtipre00(), listMconca00ActualizarListas);
			}
			if (getMtabla00().getId().getCodtab().equals("MGENCG00")) {
				getMtipre00().setTperre("C");
				seListas.obtenerMtipre(getMtipre00(), listMconca00ActualizarListas);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setMostrarValores(Boolean cadena,Boolean fecha, Boolean numero,Boolean tabla,Boolean registro,
				Boolean mgente, Boolean mgenus, Boolean mconca,Boolean mtipre, Boolean mgencg){
			visibleCadena = cadena;
			visibleFecha = fecha;
			visibleNumero = numero;
			visibleTabla = tabla;
			visibleRegistro = registro;
			visibleMgente = mgente;
			visibleMgenus = mgenus;
			visibleMconca = mconca;
			visibleMtipre = mtipre;
			visibleMgencg = mgencg;
	}
	
//	//Se valida el formato de la fecha deacuerdo a la compa�ia seleccionada
//	private void validarFormatoFecha(){
//		try{
//			//Se Valida el formato de la fecha y si no se encuentra se pone uno por default
//			if(parametroVentCata("For_Fechas")!=""){	
//				setPatternFecha((String) parametroVentCata("For_Fechas"));
//			}else{
//				setPatternFecha("dd-MM-yyyy");
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Evento que se ejecuta al seleccionar un tipo de parametro desde selectOneMenu id="tipameColumn"
	 */
	public void mostrarCampo(){		
		mtabla00 = new Mtabla00();
		mtipre00 = new Mtipre00();
		mgenus00 = new Mgenus00();
		mgente00 = new Mgente00();
		setDate(null);
		
		mparme00.setVapame(null);
		mparme00.setNupame(null);
		mparme00.setTrmame(null);
		mparme00.setNomame(null);

		
		if(mparme00.getTipame().equals(UtilsJSF.TIPAME_CADENA)){
			mparme00.setFepame(null);
			setMostrarValores(true,false,false,false,false,false,false,false,false,false);
			
		}else if(mparme00.getTipame().equals(UtilsJSF.TIPAME_FECHA)){
			mparme00.setFepame(new Date());
			setMostrarValores(false,true,false,false,false,false,false,false,false,false);
			
		}else if(mparme00.getTipame().equals(UtilsJSF.TIPAME_NUMERO)){
			mparme00.setFepame(null);
			setMostrarValores(false,false,true,false,false,false,false,false,false,false);
			
		}else if(mparme00.getTipame().equals(UtilsJSF.TIPAME_MAESTRO)){
			mparme00.setFepame(null);
			setMostrarValores(false,false,false,true,false,false,false,false,false,false);
		}
				
	}
	
//	public void obtenerMtipre(){
//		listMtipre00 = mtipre00Service.listMtipre00ByCriteriaByTperre(mtipre00);
//	}
	
	public void preRenderView(){
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	
	//Metodos de acceso

	public List<Mparme00> getListMparme00() {
		return listMparme00;
	}
	public void setListMparme00(List<Mparme00> listMparme00) {
		this.listMparme00 = listMparme00;
	}
	public List<Mconca00> getListMconca00() {
		return listMconca00;
	}
	public void setListMconca00(List<Mconca00> listMconca00) {
		this.listMconca00 = listMconca00;
	}
	public List<Mparme00> getFilteredMparme00() {
		return filteredMparme00;
	}
	public void setFilteredMparme00(List<Mparme00> filteredMparme00) {
		this.filteredMparme00 = filteredMparme00;
	}
	public List<Mtipre00> getListMtipre00() {
		return listMtipre00;
	}
	public void setListMtipre00(List<Mtipre00> listMtipre00) {
		this.listMtipre00 = listMtipre00;
	}
	public List<Mtipre00> getFilteredMtipre00() {
		return filteredMtipre00;
	}
	public void setFilteredMtipre00(List<Mtipre00> filteredMtipre00) {
		this.filteredMtipre00 = filteredMtipre00;
	}
	public Mparme00 getMparme00() {
		return mparme00;
	}
	public void setMparme00(Mparme00 mparme00) {
		this.mparme00 = mparme00;
	}
	public Mtipre00 getMtipre00() {
		return mtipre00;
	}
	public void setMtipre00(Mtipre00 mtipre00) {
		this.mtipre00 = mtipre00;
	}
	public Mtabla00 getMtabla00() {
		return mtabla00;
	}
	public void setMtabla00(Mtabla00 mtabla00) {
		this.mtabla00 = mtabla00;
	}
	public Mgenus00 getMgenus00() {
		return mgenus00;
	}
	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}
	public Mgente00 getMgente00() {
		return mgente00;
	}
	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}
	public Mconca00 getMconca00() {
		return mconca00;
	}
	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}
	public Boolean getVisibleCadena() {
		return visibleCadena;
	}
	public void setVisibleCadena(Boolean visibleCadena) {
		this.visibleCadena = visibleCadena;
	}
	public Boolean getVisibleFecha() {
		return visibleFecha;
	}
	public void setVisibleFecha(Boolean visibleFecha) {
		this.visibleFecha = visibleFecha;
	}
	public Boolean getVisibleNumero() {
		return visibleNumero;
	}
	public void setVisibleNumero(Boolean visibleNumero) {
		this.visibleNumero = visibleNumero;
	}
	
	public Boolean getVisibleTabla() {
		return visibleTabla;
	}
	public void setVisibleTabla(Boolean visibleTabla) {
		this.visibleTabla = visibleTabla;
	}
	public Boolean getVisibleRegistro() {
		return visibleRegistro;
	}
	public void setVisibleRegistro(Boolean visibleRegistro) {
		this.visibleRegistro = visibleRegistro;
	}
	public Boolean getVisibleMgente() {
		return visibleMgente;
	}
	public void setVisibleMgente(Boolean visibleMgente) {
		this.visibleMgente = visibleMgente;
	}
	public Boolean getVisibleMgenus() {
		return visibleMgenus;
	}
	public void setVisibleMgenus(Boolean visibleMgenus) {
		this.visibleMgenus = visibleMgenus;
	}
	public Boolean getVisibleMconca() {
		return visibleMconca;
	}
	public void setVisibleMconca(Boolean visibleMconca) {
		this.visibleMconca = visibleMconca;
	}

	public Boolean getVisibleMtipre() {
		return visibleMtipre;
	}
	public void setVisibleMtipre(Boolean visibleMtipre) {
		this.visibleMtipre = visibleMtipre;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDialogoCargaMasiva() {
		return dialogoCargaMasiva;
	}
	public void setDialogoCargaMasiva(String dialogoCargaMasiva) {
		this.dialogoCargaMasiva = dialogoCargaMasiva;
	}
	public List<Mgenus00> getListMgenus00TiposParametro() {
		return listMgenus00TiposParametro;
	}
	public void setListMgenus00TiposParametro(
			List<Mgenus00> listMgenus00TiposParametro) {
		this.listMgenus00TiposParametro = listMgenus00TiposParametro;
	}
	public String getIdparam() {
		return idparam;
	}
	public void setIdparam(String idparam) {
		this.idparam = idparam;
	}
	public Map<String, String> getMapTipoParametros() {
		return mapTipoParametros;
	}
	public void setMapTipoParametros(Map<String, String> mapTipoParametros) {
		this.mapTipoParametros = mapTipoParametros;
	}


	public IMtipre00Service getMtipre00Service() {
		return mtipre00Service;
	}


	public void setMtipre00Service(IMtipre00Service mtipre00Service) {
		this.mtipre00Service = mtipre00Service;
	}


//	public IMparme00Service getMparme00Service() {
//		return mparme00Service;
//	}
//
//
//	public void setMparme00Service(IMparme00Service mparme00Service) {
//		this.mparme00Service = mparme00Service;
//	}


	public Maplic00 getMaplic00() {
		return maplic00;
	}


	public void setMaplic00(Maplic00 maplic00) {
		this.maplic00 = maplic00;
	}


	public IMtabla00Service getMtabla00Service() {
		return mtabla00Service;
	}


	public void setMtabla00Service(IMtabla00Service mtabla00Service) {
		this.mtabla00Service = mtabla00Service;
	}


	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}


	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
	}
	
	public IMgente00Service getMgente00Service() {
		return mgente00Service;
	}


	public void setMgente00Service(IMgente00Service mgente00Service) {
		this.mgente00Service = mgente00Service;
	}

	public Boolean getVisibleMgencg() {
		return visibleMgencg;
	}

	public void setVisibleMgencg(Boolean visibleMgencg) {
		this.visibleMgencg = visibleMgencg;
	}

	public Mgencg00 getMgencg00() {
		return mgencg00;
	}

	public void setMgencg00(Mgencg00 mgencg00) {
		this.mgencg00 = mgencg00;
	}

	
	public IMgencg00Service getMgencg00Service() {
		return mgencg00Service;
	}

	public void setMgencg00Service(IMgencg00Service mgencg00Service) {
		this.mgencg00Service = mgencg00Service;
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

	public InputTextarea getInputTextNopameColumn() {
		return inputTextNopameColumn;
	}

	public void setInputTextNopameColumn(InputTextarea inputTextNopameColumn) {
		this.inputTextNopameColumn = inputTextNopameColumn;
	}

	public InputTextarea getInputTextDcpameColumn() {
		return inputTextDcpameColumn;
	}

	public void setInputTextDcpameColumn(InputTextarea inputTextDcpameColumn) {
		this.inputTextDcpameColumn = inputTextDcpameColumn;
	}


}