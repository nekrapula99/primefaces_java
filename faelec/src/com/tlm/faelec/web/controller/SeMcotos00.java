package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMcotos00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotos00;
import com.tlm.faelecEntities.model.entities.Mparpd00;


@Controller
@ManagedBean
@SessionScoped
public class SeMcotos00 extends Control implements Serializable{
	private static final long serialVersionUID = -687534026013328678L;

	@ManagedProperty("#{seMcotes00}")
	private SeMcotes00 seMcotes00;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;

	@ManagedProperty(value = "#{mcotos00Service}")
	private IMcotos00Service mcotos00Service;

	
	
	private Mcotos00 mcotos00;
	private Mcotos00 mcotos00Cop;
	private List<Mcotos00> listMcotos00;
	private List<Mcotos00> filteredMcotos00;
	
	private Integer fracDec_CantiM;
	private Integer fracDec_ValruM;	
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	private InputText inputTextIdtaosColumn;
	
	
	@PostConstruct
	public void init(){
		try {
			super.init("MCOTOS00");
			mcotos00 = new Mcotos00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			cargarDatos();
			reiniciarListasMaestro();
				
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	

	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try{
			 seListas.actualizarListas(NombresListas.listMgenteCliente); 
			 seListas.actualizarListas(NombresListas.listMgenusAreaLabor);
			 seListas.actualizarListas(NombresListas.listMgenusCargoTercero);
			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			seListas.actualizarListasCompania(NombresListas.listMgenusAreaLabor, listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusCargoTercero, listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenteCliente, listMconca00ActualizarListas);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void accionNuevo() {
		try {
			//mcotos00.setMcotes001(seMcotes00.getMcotes00());
			setAccion(Constantes.ACCION_NUEVO);
			mcotos00 = new Mcotos00();
			mcotos00.setRegtos(true);
			mcotos00.setMcotes001(seMcotes00.getMcotes00());
		
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event){ 
		try {
			reiniciarListasMaestro();
			setAccion(Constantes.ACCION_MODIFICAR);//Modificar registro	
			if(mcotos00.getMgenus001() != null){
				actualizarListasCompania(mcotos00.getMgenus001().getCodius());
				getSeControlFactura().cargarParametros(true,null,mcotos00.getMgenus001().getIdtrus());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotos00.getMgenus001(), mcotos00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotos00.getMgenus002(), mcotos00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mcotos00.getMgente001(), mcotos00.getMgente001().getMtipre00().getTipore());

			}
			cargarDatos();
			mcotos00Cop= (Mcotos00) mcotos00.clone();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Limpia el filtro del DataTable
	/*public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:tabView:dTableMcotos00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:tabView:dTableMcotos00");
		}
	}*/
	

	
	/*Metodo que se encarga de validar que los registros de las listas esten habilitados
	 * en caso de estar deshabilitado algun registro devuelve true
	 */
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
		//Validacion lista  Tercero Adicional 
		if(mcotos00.getMgente001() != null &&  mcotos00.getMgente001().getRegite()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idtaos")+" "+ mcotos00.getMgente001().getCodite() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		//Validacion lista  Area 
		if(mcotos00.getMgenus001() != null &&  mcotos00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idatos")+" "+ mcotos00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		//Validacion lista  Cargo 
		if(mcotos00.getMgenus002() != null &&  mcotos00.getMgenus002().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idgtos")+" "+ mcotos00.getMgenus002().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(mcotos00.getMgente001()) && permisoCampos.get("idptes").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idptes")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdtaosColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdtaosColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdtaosColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdtaosColumn.getClientId());	
		}
	
		
		return validacion;
	}
	
	//metodo que valida la Unique Key en la lista Mcpcct00
		 private boolean validarUKMcotes00() throws Exception{
			  try {   
			   if(!Utils.isEmptyOrNull(seMcotes00.getMcotes00().getMcotos001s())){
			    List<Mcotos00> listTemp = new ArrayList<Mcotos00>(seMcotes00.getMcotes00().getMcotos001s());
			    listTemp.remove(mcotos00);
			    for (Mcotos00 objIter : listTemp) {
			     if(objIter.getMcotes001().getIdctes() == mcotos00.getMcotes001().getIdctes()){
			    	 return false;
			     }
			    }
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			  
			  return true;
			 }
	
	 
	public void save(ActionEvent event) {
		try {
			//mcotos00.setMcotes001(seMcotes00.getMcotes00());
			if(validarDatosRequeridos()){
				return;
			}
			
			colaEstandar();
			
			/*if(getAccion().equals(Constantes.ACCION_NUEVO)) {
				if(validarListas()==false){
					System.out.println("mcotos00: "+mcotos00);
					seMcotes00.getMcotes00().getMcotos001s().add(mcotos00);
				}else return;
			}  */
			
	
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					//seMcotes00.getMcotes00().getMcotos001s().add(mcotos00);
					mcotos00Service.save(mcotos00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mcotos00.toString(),mcotos00.toStringId(),mcotos00Cop.toString()):null);
				}else return;
			} else {
				mcotos00Service.save(mcotos00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mcotos00.toString(),mcotos00.toStringId(),null):null);
			}
			 
			
			/*if(validarUKMcotes00()){
				if(getAccion().equals(Constantes.ACCION_NUEVO))
				{
					seMcotes00.getMcotes00().getMcotos001s().add(mcotos00);	
				}
			}else{
				if (getAccion().equals(Constantes.ACCION_NUEVO)){
					seMcotes00.getMcotes00().getMcotos001s().remove(mcotos00);
				}else{
					seMcotes00.getMcotes00().getMcotos001s().remove(mcotos00);
					seMcotes00.getMcotes00().getMcotos001s().add(mcotos00Cop);
				}
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle");
				return;
			}*/
	
			
			cargarDatos();
			mcotos00Cop= (Mcotos00) mcotos00.clone();
			UtilsJSF.closeDialog("dlgNuevoUpdateMcotos00");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableMcotos00");
		} catch (Exception e) {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	
	public void autocompleteActualizarListas (){
		try{
			if(mcotos00.getMgenus001() != null){
			actualizarListasCompania(mcotos00.getMgenus001().getCodius());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		try{
			listMcotos00 = mcotos00Service.listMcotos00ByCriteria(new Mcotos00(),getSeControlFactura().getCompaniasUsu());
			//UtilsJSF.resetDTable("formDetalle:tabView:dTableMcotos00");
			System.out.println("listMcotos00: "+listMcotos00);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			mcotos00.setUsetos(getSeControlFactura().codusu);
			mcotos00.setPrgtos("Tercero " + getClass().getName());
			mcotos00.setFeatos(new Date());
			mcotos00.setMaqtos(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			
			mcotos00 = (Mcotos00) event.getComponent().getAttributes().get("mcotos00");
			mcotos00Service.removeMcotos00(mcotos00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mcotos00.toString(),mcotos00.toStringId()):null);
			seMcotes00.getMcotes00().getMcotos001s().remove(mcotos00);
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlMcotos00");
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			}
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
	
	/*
	 * Metodos de acceso
	 */

	public SeMcotes00 getSeMcotes00() {
		return seMcotes00;
	}

	public void setSeMcotes00(SeMcotes00 seMcotes00) {
		this.seMcotes00 = seMcotes00;
	}

	public Mcotos00 getMcotos00() {
		return mcotos00;
	}

	public void setMcotos00(Mcotos00 mcotos00) {
		this.mcotos00 = mcotos00;
	}

	public List<Mcotos00> getFilteredMcotos00() {
		return filteredMcotos00;
	}

	public void setFilteredMcotos00(List<Mcotos00> filteredMcotos00) {
		this.filteredMcotos00 = filteredMcotos00;
	}


	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}

	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}

	public Integer getFracDec_ValruM() {
		return fracDec_ValruM;
	}

	public void setFracDec_ValruM(Integer fracDec_ValruM) {
		this.fracDec_ValruM = fracDec_ValruM;
	}
	
	public IMcotos00Service getMcotos00Service() {
		return mcotos00Service;
	}

	public void setMcotos00Service(IMcotos00Service mcotos00Service) {
		this.mcotos00Service = mcotos00Service;
	}

	public Mcotos00 getMcotos00Cop() {
		return mcotos00Cop;
	}

	public void setMcotos00Cop(Mcotos00 mcotos00Cop) {
		this.mcotos00Cop = mcotos00Cop;
	}

	public List<Mcotos00> getListMcotos00() {
		return listMcotos00;
	}

	public void setListMcotos00(List<Mcotos00> listMcotos00) {
		this.listMcotos00 = listMcotos00;
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


	public InputText getInputTextIdtaosColumn() {
		return inputTextIdtaosColumn;
	}


	public void setInputTextIdtaosColumn(InputText inputTextIdtaosColumn) {
		this.inputTextIdtaosColumn = inputTextIdtaosColumn;
	}	

}
