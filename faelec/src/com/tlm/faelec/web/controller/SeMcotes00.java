package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Mcotos00;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelec.service.maestros.IMcotes00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelec.utils.Utils;


@Controller
@ManagedBean
@SessionScoped
public class SeMcotes00 extends Control implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mcotes00Service}")
	private IMcotes00Service mcotes00Service;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mcotes00 mcotes00;
	private Mcotes00 mcotes00Cop;
	private List<Mcotes00> listMcotes00;
	private List<Mcotes00> filteredMcotes00;
	//private String action;

	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
		
	private Integer fracDec_CantiM;
	private Integer fracDec_PorceM;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	
	private boolean tabInfBase; //Tab Informacion base
	private boolean tabTercero; //Tab Tercero
	private String[] pestanas;
	private int tabView=0;
	
	private InputText inputTextIdptesColumn;
	private InputText inputTextIdciesColumn;
	
	@PostConstruct
	public void init() {
		try {
			super.init("Mcotes00");
			mcotes00 = new Mcotes00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");	
			//Double d2=(Double)PARAMETROS.get("Dec_ValruM");	
			setFracDec_CantiM(d1.intValue());
			//setFracDec_ValruM(d2.intValue());
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try{
			getSeListas().actualizarListas(NombresListas.listMgenteCliente);
			getSeListas().actualizarListas(NombresListas.listMgenusSaludo);
			getSeListas().actualizarListas(NombresListas.listMgenusAreaLabor);
			getSeListas().actualizarListas(NombresListas.listMgenusCargoTercero);
			getSeListas().actualizarListas(NombresListas.listMgenusCiudad);
			getSeListas().actualizarListas(NombresListas.listMgenusPaises);
			getSeListas().actualizarListas(NombresListas.listMgenusTident);

			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	
	public void mostrarPestana(){
		try {
		    tabInfBase = false;
		    tabTercero = false;
		    pestanas=((String) getSeControlFactura().PARAMETROS.get("Vis_MaeCot")).split(","); 
			   for (int i=0; i < pestanas.length;i++){
				   if(pestanas[i].equals("1"))
				   {
					   tabInfBase=true;
				   }
				   if(pestanas[i].equals("2"))
				   {
					   tabTercero=true;
				   }
			   }
			 RequestContext.getCurrentInstance().update("formDetalle:tabView");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void accionNuevo() {
		try {
			mostrarPestana();
			accion=Constantes.ACCION_NUEVO;			
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);


			tabView=0;
			mcotes00 = new Mcotes00();
			mcotes00.setRegtes(true);
			mcotes00.setMcotos001s(new ArrayList<Mcotos00>());
			//seListas.actualizarListas(NombresListas.listMconca);

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
			mcotes00Cop= (Mcotes00) mcotes00.clone();
			if(mcotes00.getMgenus001() != null){
				actualizarListasCompania(mcotes00.getMgenus001().getCodius());
				getSeControlFactura().cargarParametros(true,null,mcotes00.getMgenus001().getIdtrus());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}
			mostrarPestana();
			tabView=0;
			Mcotes00 mcotes00Rs = new Mcotes00();
			//mcotes00Rs = mcotes00Service.cargarDetalles(mcotes00.getIdctes());
			mcotes00.setMcotos001s(mcotes00Rs.getMcotos001s());	
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotes00.getMgenus001(), mcotes00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotes00.getMgenus002(), mcotes00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotes00.getMgenus003(), mcotes00.getMgenus003().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotes00.getMgenus004(), mcotes00.getMgenus004().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotes00.getMgenus005(), mcotes00.getMgenus005().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotes00.getMgenus006(), mcotes00.getMgenus006().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotes00.getMgente001(), mcotes00.getMgente001().getMtipre00().getTipore());

			}
			mcotes00Cop= (Mcotes00) mcotes00.clone();	
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
			
			getSeListas().actualizarListasCompania(NombresListas.listMgenusSaludo, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusAreaLabor , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusCargoTercero, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusPaises, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusCiudad , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusTident, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenteCliente, listMconca00ActualizarListas);

			//getSeListas().actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
			
			}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void autocompleteActualizarListas (){
		try{
			if(mcotes00.getMgenus001() != null){
			actualizarListasCompania(mcotes00.getMgenus001().getCodius());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void cargarDatos() {
		try{
			listMcotes00 = mcotes00Service.listMcotes00ByCriteria(new Mcotes00(),getSeControlFactura().getCompaniasUsu());
			
			//UtilsJSF.resetDTable("formTable:dTable");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	private void colaEstandar() {
		try {
			mcotes00.setUsetes(getSeControlFactura().codusu);
			mcotes00.setPrgtes("Contactos " + getClass().getName());
			mcotes00.setFeates(new Date());
			mcotes00.setMaqtes(getSeControlFactura().ip);

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
//		
		//Validacion lista  Saludo 
		if(mcotes00.getMgenus001() != null &&  mcotes00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idstes")+" "+ mcotes00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
//	//Validacion lista  Area Labor 
		if(mcotes00.getMgenus005() != null &&  mcotes00.getMgenus005().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idates")+" "+ mcotes00.getMgenus005().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		//Validacion lista  Cargo Tercero 
		if(mcotes00.getMgenus006() != null &&  mcotes00.getMgenus006().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idgtes")+" "+ mcotes00.getMgenus006().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		//Validacion lista  Documento 
		if(mcotes00.getMgenus002() != null &&  mcotes00.getMgenus002().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idttes")+" "+ mcotes00.getMgenus002().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}

		//Validacion lista  Paises 
		if(mcotes00.getMgenus003() != null &&  mcotes00.getMgenus003().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idptes")+" "+ mcotes00.getMgenus003().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  Ciudad 
		if(mcotes00.getMgenus004() != null &&  mcotes00.getMgenus004().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcies")+" "+ mcotes00.getMgenus004().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		//Validacion lista  Tercero Ppl 
		if(mcotes00.getMgente001() != null &&  mcotes00.getMgente001().getRegite()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idtres")+" "+ mcotes00.getMgente001().getCodite() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}

		
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(mcotes00.getMgenus003()) && permisoCampos.get("idptes").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idptes")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdptesColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdptesColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdptesColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdptesColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mcotes00.getMgenus004()) && permisoCampos.get("idcies").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcies")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdciesColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdciesColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdciesColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdciesColumn.getClientId());	
		}
		
		return validacion;
	}

	public void save() {
		try {
			
			if(validarDatosRequeridos()){
				return;
			}
									
			//Se guardan los datos de la cola estandar
			validarRango();
			colaEstandar();
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
				mcotes00Service.save(mcotes00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mcotes00.toString(),mcotes00.toStringId(),mcotes00Cop.toString()):null);
				}else return;
			} else {
				mcotes00Service.save(mcotes00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mcotes00.toString(),mcotes00.toStringId(),null):null);
			 }
			cargarDatos();	
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

	
	public void validarRango()
	{
		
	}
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mcotes00 = (Mcotes00) event.getComponent().getAttributes().get("mcotes00");
			mcotes00Service.removeMcotes00(mcotes00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mcotes00.toString(),mcotes00.toStringId()):null);
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
	
	public void llenarObjectCall(SelectEvent event) {
		if (objSeCall == null) {
			return;
		}
		String strClassLlama = objSeCall.getClass().getSimpleName();
		
		//DESCOMENTAR
		//Maestro MFUNFU00 nosorio-12122018 
		if (strClassLlama.equalsIgnoreCase("seTqdfqf00")) {
			SeTqdfqf00 seTqdfqf00 = (SeTqdfqf00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mcotes00")) {
				if (nombreLista.equalsIgnoreCase("FunciEnvia")) {
					seTqdfqf00.getTqdfqf00().setMcotes00((Mcotes00) event.getObject());
					RequestContext.getCurrentInstance().reset("formTqdfqf00Detalle");	
				}			
			}
		}
		
		update=null;	
		
	}
	//Metodo que autocompleta todas las listas de Mfunfu00
	public List<Mcotes00> completeMfunfu(String query) {		

        List<Mcotes00> results = new ArrayList<Mcotes00>();   
        List<Mcotes00> listMcotes00 = getSeListas().getListMcotes00();

        for (Mcotes00 obj : listMcotes00) {        	
        	if(obj.getCoctes().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	
	
	public void evtCloseDialogMcotes(CloseEvent event) {
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

	public IMcotes00Service getMcotes00Service() {
		return mcotes00Service;
	}

	public void setMcotes00Service(IMcotes00Service mcotes00Service) {
		this.mcotes00Service = mcotes00Service;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public Mcotes00 getMcotes00() {
		return mcotes00;
	}

	public void setMcotes00(Mcotes00 mcotes00) {
		this.mcotes00 = mcotes00;
	}

	public Mcotes00 getMcotes00Cop() {
		return mcotes00Cop;
	}

	public void setMcotes00Cop(Mcotes00 mcotes00Cop) {
		this.mcotes00Cop = mcotes00Cop;
	}

	public List<Mcotes00> getListMcotes00() {
		return listMcotes00;
	}

	public void setListMcotes00(List<Mcotes00> listMcotes00) {
		this.listMcotes00 = listMcotes00;
	}

	public List<Mcotes00> getFilteredMcotes00() {
		return filteredMcotes00;
	}

	public void setFilteredMcotes00(List<Mcotes00> filteredMcotes00) {
		this.filteredMcotes00 = filteredMcotes00;
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

	public Integer getFracDec_PorceM() {
		return fracDec_PorceM;
	}

	public void setFracDec_PorceM(Integer fracDec_PorceM) {
		this.fracDec_PorceM = fracDec_PorceM;
	}

	public boolean isTabInfBase() {
		return tabInfBase;
	}

	public void setTabInfBase(boolean tabInfBase) {
		this.tabInfBase = tabInfBase;
	}

	public boolean isTabTercero() {
		return tabTercero;
	}

	public void setTabTercero(boolean tabTercero) {
		this.tabTercero = tabTercero;
	}

	public String[] getPestanas() {
		return pestanas;
	}

	public void setPestanas(String[] pestanas) {
		this.pestanas = pestanas;
	}

	public int getTabView() {
		return tabView;
	}

	public void setTabView(int tabView) {
		this.tabView = tabView;
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

	public InputText getInputTextIdptesColumn() {
		return inputTextIdptesColumn;
	}

	public void setInputTextIdptesColumn(InputText inputTextIdptesColumn) {
		this.inputTextIdptesColumn = inputTextIdptesColumn;
	}

	public InputText getInputTextIdciesColumn() {
		return inputTextIdciesColumn;
	}

	public void setInputTextIdciesColumn(InputText inputTextIdciesColumn) {
		this.inputTextIdciesColumn = inputTextIdciesColumn;
	}

	
	

}
