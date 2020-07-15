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

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMteste00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;

import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mteste00;

@Controller
@ManagedBean
@SessionScoped
public class SeMteste00 extends Control implements Serializable {
	private static final long serialVersionUID = 1L;

//	 variables del managed bean
	@ManagedProperty(value="#{mteste00Service}")
	IMteste00Service mteste00Service;

		
	private Mteste00 mteste00;
	private Mteste00 mteste00Cop;
	private List<Mteste00> listMteste00;
	private List<Mteste00> filteredMteste00;
	private boolean infoGuardada;
	
	//Detalles
	private Mteste00 mteste00Detalles;
	private List<Mteste00> listMteste00CopDetalles;
	private List<Mteste00> listMteste00Detalles;
	private List<Mteste00> filteredMteste00Detalles;
	private int posicion; // Se utiliza para iterar la lista 
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	
	private String accion;
	private String titulo;
	
	private InputText inputTextIdttteColumn;
	private InputText inputTextIdcmteColumn;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@PostConstruct
	public void init() {
		try {
			super.init("Mteste00");
			mteste00 = new Mteste00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			infoGuardada = false;
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			cargarDatos();
			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cargarDatos() {
		try{
			listMteste00 = mteste00Service.listMteste00ByCriteria(new Mteste00(),getSeControlFactura().getCompaniasUsu());
			UtilsJSF.resetDTable("formTable:dTable");
			System.out.println("listica: "+listMteste00);
			//mteste00 = new Mteste00();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			getSeListas().actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMestad00);
			getSeListas().actualizarListas(NombresListas.listMtiptxEstado);
			getSeListas().actualizarListas(NombresListas.listMtiptx00);
			getSeListas().actualizarListas(NombresListas.listMestadTxt00);
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
			mteste00 = new Mteste00();
			mteste00.setRegtte(true);
			getSeListas().actualizarListas(NombresListas.listMconca);
			listMteste00Detalles =  new ArrayList<Mteste00>();
			reiniciarListasMaestro();
			clearAllFilters2();
			UtilsJSF.resetDTable("formTable:dTable");
			RequestContext.getCurrentInstance().reset("formDetalle:pnlDetalle");	
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}

	public void accionNuevoDetalles() {
		try {
			clearAllFilters2();
			if(listMteste00Detalles.size()==0){
				mteste00Detalles = new Mteste00();
				mteste00Detalles.setRegtte(true);
				listMteste00Detalles.add(mteste00Detalles);
			}else{
				//Se valida que la posicion anterior no tenga campos nulos si no tiene campos nulos agrega un nuevo registro
				posicion=listMteste00Detalles.size();
				if(!Utils.isEmptyOrNull(listMteste00Detalles.get(posicion-1).getMestad001()) && !Utils.isEmptyOrNull(listMteste00Detalles.get(posicion-1).getMestad002())){
					mteste00Detalles = new Mteste00();
					mteste00Detalles.setRegtte(true);
					listMteste00Detalles.add(mteste00Detalles);
				}else{//si tiene registros nulos se muestra mensaje de error
					if(!validarDatosNulosLista())
					{
					 return;	
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validarDatosNulosLista()
	{
		posicion=listMteste00Detalles.size();
		boolean valor=true;
		if(Utils.isEmptyOrNull(listMteste00Detalles.get(posicion-1).getMestad001())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("ideote")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			valor= false;
		}
		if(Utils.isEmptyOrNull(listMteste00Detalles.get(posicion-1).getMestad002())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idedte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			valor= false;
		}
		return valor;
	}

	public void accionModificar(SelectEvent event) {
		try {
			setAccion(Constantes.ACCION_MODIFICAR);	
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			autocompleteActualizarListMestad00();
			//Se cargan las listas deacuerdo a la compañia seleccionada	y se actualizan las listas
			if(mteste00.getMconca00()!= null){
			actualizarListasCompania(mteste00.getMconca00().getCodcia());
			}else{
			   reiniciarListasMaestro();
			}
			listMteste00Detalles = mteste00Service.listMteste00Detalles(mteste00);
			//Se clona la lista de detalles
			listMteste00CopDetalles= new ArrayList<Mteste00>();
			for (Mteste00 mteste : listMteste00Detalles) {
				mteste00Cop= (Mteste00) mteste.clone();
				listMteste00CopDetalles.add(mteste00Cop);
			}
			clearAllFilters2();
			mteste00Cop= (Mteste00) mteste00.clone();
			infoGuardada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// Se dispara este metodo al ejecutarse la accion con el autocompletar
		public void autocompleteActualizarListas (){
			try{
				String codigoCompania = null;
				if(mteste00.getMconca00() != null){
				codigoCompania= mteste00.getMconca00().getCodcia();
				actualizarListasCompania(codigoCompania);
				}else{
					reiniciarListasMaestro();
				}			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	//Metodo para actualizar la lista de estados según el tipo de transacción selecionado.
	public void autocompleteActualizarListMestad00(){
			try {
				List<String> listMteste00ActualizarListas;
				listMteste00ActualizarListas = new ArrayList<String>();
				listMteste00ActualizarListas.add(mteste00.getMconca00().getCodcia());
				getSeListas().cargarListaMestadTxt00(mteste00.getMtiptx00().getIdtptx(), mteste00.getMconca00()!=null?listMteste00ActualizarListas:null);
				getSeListas().actualizarListasCompania(NombresListas.listMconca, listMteste00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMtiptx00Mestad00, listMteste00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMacdio00, listMteste00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMtiptx00, listMteste00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMestadTxt00, listMteste00ActualizarListas);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compañias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMteste00ActualizarListas;
			listMteste00ActualizarListas = new ArrayList<String>();
			listMteste00ActualizarListas.add(codigoCompania);
			getSeListas().actualizarListasCompania(NombresListas.listMconca, listMteste00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMestad00, listMteste00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMtiptxEstado, listMteste00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMtiptx00, listMteste00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMestadTxt00, listMteste00ActualizarListas);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Se recorre la lista de detalles y a cada uno se le asigna 
	 * la compañia si fue cambiada y el tipo de transaccion
	 */
	private void completarDetalles(){
		try {
			for (Mteste00 mteste : listMteste00Detalles) {
				if(mteste00.getMconca00()!=null){
					mteste.setMconca00(mteste00.getMconca00());
				}
				if(mteste00.getMtiptx00() != null){
					mteste.setMtiptx00(mteste00.getMtiptx00());
				}
				mteste.setUsuate(getSeControlFactura().codusu);
				mteste.setPrgrte("Tipo txt " + getClass().getName());
				mteste.setFeacte(new Date());
				mteste.setMaqute(getSeControlFactura().ip);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Se validan los datos que son requeridos por BD o por Dinox
		private boolean validarDatosRequeridos(){
			boolean validacion = false;
			//Id tipo transacción 
			
			if(Utils.isEmptyOrNull(mteste00.getMtiptx00()) && permisoCampos.get("idttte").getReqcam().equals("S")){
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,getIdiomasHm().get("idttte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
				inputTextIdttteColumn.setStyle("border-color: #d2524f;");				
			    RequestContext.getCurrentInstance().update(inputTextIdttteColumn.getClientId());
				validacion = true;
			}else{
				inputTextIdttteColumn.setStyle("border-color: #9a9a9a;");			 
				RequestContext.getCurrentInstance().update(inputTextIdttteColumn.getClientId());	
			}
			//Codigo estado
			if(Utils.isEmptyOrNull(mteste00.getMconca00()) && permisoCampos.get("idcmte").getReqcam().equals("S")){
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR,getIdiomasHm().get("idcmte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
				inputTextIdcmteColumn.setStyle("border-color: #d2524f;");				
			    RequestContext.getCurrentInstance().update(inputTextIdcmteColumn.getClientId());
				validacion = true;
			}else{
				inputTextIdcmteColumn.setStyle("border-color: #9a9a9a;");			 
				RequestContext.getCurrentInstance().update(inputTextIdcmteColumn.getClientId());	
			}
			return validacion;
		}
		
	

	public void save() {
		try {
			//Se completan los datos de los detalles
			if(validarDatosRequeridos()){
				return;
			}
			
			completarDetalles();			
			if(validarDatosNulosLista())
			{
				if(getAccion().equals(Constantes.ACCION_MODIFICAR))
				{
					System.out.println("detalle : "+listMteste00Detalles);
					mteste00Service.saveLista(listMteste00Detalles,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mteste00Detalles.toString(),mteste00Detalles.toStringId(),mteste00Cop.toString()):null);
			    	//mpreci00Service.save(mpreci00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mpreci00.toString(),mpreci00.toStringId(),mpreci00Cop.toString()):null);

				}
				else
				{
					mteste00Service.saveLista(listMteste00Detalles,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mteste00Detalles.toString(),mteste00Detalles.toStringId(),null):null);
				}
				cargarDatos();
				clearAllFilters2();
				infoGuardada = true;	
				UtilsJSF.closeDialog("dlgNuevoUpdate");
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
				RequestContext.getCurrentInstance().update("formTable");
			}		
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

	//Borrado Principal
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mteste00 = (Mteste00) event.getComponent().getAttributes().get("mteste00");
			mteste00Service.removeLista(mteste00);
			//cargarDatos();
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
	
	public void removeDetalles(ActionEvent event) {
		try {
			mteste00Detalles = (Mteste00) event.getComponent().getAttributes().get("mteste00Detalles");
			listMteste00Detalles.remove(mteste00Detalles);
			mteste00Service.removeMteste00(mteste00Detalles,parametroVentCata("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mteste00Detalles.toString(),mteste00Detalles.toStringId()):null);
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

	//Se ejecuta cuando se cierra el dialogo
	public void evtCloseDialogMteste00(CloseEvent event) {
		try {
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && infoGuardada==false){
        		//Se devuelve al estado original el objeto de la cabecera antes de ser seleccionado
        		mteste00.setMtiptx00(mteste00Cop.getMtiptx00());
            	mteste00.setMconca00(mteste00Cop.getMconca00());
        		listMteste00Detalles = new ArrayList<Mteste00>();
        		//Se devuelve al estado original la lista de detalles si no se realizaron cambios
        		for (Mteste00 mteste : listMteste00CopDetalles) {
        			listMteste00Detalles.add(mteste);
				}
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
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Limpia el filtro del DataTable
	public void clearAllFilters2() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:dTableMteste00Detalle");
			dataTable.reset();
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:dTableMteste00Detalle");
	}
	
	/*
	 * Getters and Setters
	 */

	public Mteste00 getMteste00() {
		return mteste00;
	}

	public void setMteste00(Mteste00 mteste00) {
		this.mteste00 = mteste00;
	}

	public List<Mteste00> getListMteste00() {
		return listMteste00;
	}

	public void setListMteste00(List<Mteste00> listMteste00) {
		this.listMteste00 = listMteste00;
	}

	public List<Mteste00> getFilteredMteste00() {
		return filteredMteste00;
	}

	public void setFilteredMteste00(List<Mteste00> filteredMteste00) {
		this.filteredMteste00 = filteredMteste00;
	}

	public IMteste00Service getMteste00Service() {
		return mteste00Service;
	}

	public void setMteste00Service(IMteste00Service mteste00Service) {
		this.mteste00Service = mteste00Service;
	}

		
	/*
	 * Detalleses
	 */

	public Mteste00 getMteste00Detalles() {
		return mteste00Detalles;
	}

	public void setMteste00Detalles(Mteste00 mteste00Detalles) {
		this.mteste00Detalles = mteste00Detalles;
	}

	public List<Mteste00> getListMteste00Detalles() {
		return listMteste00Detalles;
	}

	public void setListMteste00Detalles(List<Mteste00> listMteste00Detalles) {
		this.listMteste00Detalles = listMteste00Detalles;
	}

	public List<Mteste00> getFilteredMteste00Detalles() {
		return filteredMteste00Detalles;
	}

	public void setFilteredMteste00Detalles(List<Mteste00> filteredMteste00Detalles) {
		this.filteredMteste00Detalles = filteredMteste00Detalles;
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

	public InputText getInputTextIdttteColumn() {
		return inputTextIdttteColumn;
	}

	public void setInputTextIdttteColumn(InputText inputTextIdttteColumn) {
		this.inputTextIdttteColumn = inputTextIdttteColumn;
	}

	public InputText getInputTextIdcmteColumn() {
		return inputTextIdcmteColumn;
	}

	public void setInputTextIdcmteColumn(InputText inputTextIdcmteColumn) {
		this.inputTextIdcmteColumn = inputTextIdcmteColumn;
	}
	
	
}