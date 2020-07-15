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

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMdespr00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Mcotos00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Mparpd00;
import com.tlm.faelecEntities.model.entities.Mpropr00;

@Controller
@ManagedBean
@SessionScoped
public class SeMdespr00 extends Control implements Serializable{
	private static final long serialVersionUID = 7213183176463668661L;

	
	
	
	@ManagedProperty(value = "#{mdespr00Service}")
	private IMdespr00Service mdespr00Service;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	@ManagedProperty(value = "#{seMpropr00}")
	private SeMpropr00 seMpropr00;
	
	private Mdespr00 mdespr00;
	private Mdespr00 mdespr00Clone;
	private List<Mdespr00> listMdespr00;
	private List<Mdespr00> filteredMdespr00;
	private boolean infoGuardada;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	
	private String tabla;
	private String accion;
	private String titulo;
	
	//Metodos del ManagedBean
	
	@PostConstruct
	public void init() {
		try {
			super.init("MDESPR00");
			mdespr00 = new Mdespr00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
		private void reiniciarListasMaestro(){
			try{
				 seListas.actualizarListas(NombresListas.listMconca); 
				 seListas.actualizarListas(NombresListas.listMtiptx00); 
				 seListas.actualizarListas(NombresListas.listMidiom);
				}catch (Exception e) {
				    e.printStackTrace();
				}		
		}
	

	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			mdespr00 = new Mdespr00();
			mdespr00.setRegidp(true);
			mdespr00.setMconca00(seMpropr00.getMpropr00().getMconca00());
			mdespr00.setMpropr00(seMpropr00.getMpropr00());	
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(SelectEvent event){ 
		try {
			setAccion(Constantes.ACCION_MODIFICAR);//Modificar registro
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mdespr00Clone = (Mdespr00) mdespr00.clone();
			infoGuardada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	private void colaEstandar() {
		try {
			mdespr00.setUserdp(getSeControlFactura().codusu);
			mdespr00.setPrgmdp(getSeControlFactura().aplusu);
			mdespr00.setFeacdp(new Date());
			mdespr00.setMaqudp(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metodo que valida la Unique Key en la lista Mcpcct00
	 private boolean validarUKMdespr00() throws Exception{
		  try {
			  getSeControlFactura().setTitulo(titulo);
			  getSeControlFactura().setTabla(tabla);
		   if(!Utils.isEmptyOrNull(seMpropr00.getMpropr00().getMdespr00s())){
		    List<Mdespr00> listTemp = new ArrayList<Mdespr00>(seMpropr00.getMpropr00().getMdespr00s());
		    listTemp.remove(mdespr00);
		    
		    for (Mdespr00 objIter : listTemp) {
		     if(objIter.getMidiom00().getCodidi().equals(mdespr00.getMidiom00().getCodidi()) &&
		        objIter.getMtiptx00().getIndstx().equals(mdespr00.getMtiptx00().getIndstx())){
		      return false;
		     }
		    }
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  return true;
		 }
	 
	//Se validan los datos que son requeridos por BD o por Dinox
		private boolean validarDatosRequeridos(){
			boolean validacion = false;
			//Tipo de transaccion
			if(Utils.isEmptyOrNull(mdespr00.getMtiptx00())){
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idttdp")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
				validacion = true;
			}
			//Idioma
			if(Utils.isEmptyOrNull(mdespr00.getMidiom00()))
			{
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("ididdp")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
				validacion = true;
			}
			//Descripcion
			if(Utils.isEmptyOrNull(mdespr00.getDscrdp()))
			{
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("dscrdp")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
				validacion = true;
			}
			return validacion;
		}
	
		//Limpia el filtro del DataTable
	public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:tabView:dTableMdespr00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:tabView:dTableMdespr00");
		}
	}
		
	public void save(ActionEvent event) {
		try {
			//Se validan los datos que son requeridos
			if (validarDatosRequeridos()) {
				return;
			}
			colaEstandar();
			if(validarUKMdespr00()){
				if(getAccion().equals(Constantes.ACCION_NUEVO))
				{
					seMpropr00.getMpropr00().getMdespr00s().add(mdespr00);	
				}
			}else{			
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");				
				infoGuardada = false;			
				return;
			}
			infoGuardada = true;
		    UtilsJSF.closeDialog("dlgNuevoUpdateMdespr00");
		    UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		    RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableMdespr00");
		}
	catch (Exception e) {	
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			return;
		}
	}	
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mdespr00 = (Mdespr00) event.getComponent().getAttributes().get("mdespr00");
			seMpropr00.getMpropr00().getMdespr00s().remove(mdespr00);
			UtilsJSF.resetDTable(":formDetalle:tabView:dTableMdespr00");
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
	
	public void preRenderView(){
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialog(CloseEvent event) {
		//Asigno los valores iniciales cuando la informacion no fue guardada
		if (getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
			mdespr00.setMtiptx00(mdespr00Clone.getMtiptx00());
			mdespr00.setMidiom00(mdespr00Clone.getMidiom00());
			mdespr00.setDscrdp(mdespr00Clone.getDscrdp());
			mdespr00.setRegidp(mdespr00Clone.getRegidp());
			RequestContext.getCurrentInstance().update("formDetalle:tabView:dTableMdespr00");
		}		
	}
	
	//Metodos de acceso

	public IMdespr00Service getMdespr00Service() {
		return mdespr00Service;
	}
	public void setMdespr00Service(IMdespr00Service mdespr00Service) {
		this.mdespr00Service = mdespr00Service;
	}
	public SeMpropr00 getSeMpropr00() {
		return seMpropr00;
	}
	public void setSeMpropr00(SeMpropr00 seMpropr00) {
		this.seMpropr00 = seMpropr00;
	}
	public Mdespr00 getMdespr00() {
		return mdespr00;
	}
	public void setMdespr00(Mdespr00 mdespr00) {
		this.mdespr00 = mdespr00;
	}
	public Mdespr00 getMdespr00Clone() {
		return mdespr00Clone;
	}
	public void setMdespr00Clone(Mdespr00 mdespr00Clone) {
		this.mdespr00Clone = mdespr00Clone;
	}
	public List<Mdespr00> getListMdespr00() {
		return listMdespr00;
	}
	public void setListMdespr00(List<Mdespr00> listMdespr00) {
		this.listMdespr00 = listMdespr00;
	}
	public List<Mdespr00> getFilteredMdespr00() {
		return filteredMdespr00;
	}
	public void setFilteredMdespr00(List<Mdespr00> filteredMdespr00) {
		this.filteredMdespr00 = filteredMdespr00;
	}
	public boolean isInfoGuardada() {
		return infoGuardada;
	}
	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
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
	
	
}
