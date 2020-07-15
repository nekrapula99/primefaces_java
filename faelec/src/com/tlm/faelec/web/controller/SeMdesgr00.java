package com.tlm.faelec.web.controller;

import java.io.Serializable;
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
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMdesgr00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotos00;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelec.utils.Utils;

@Controller
@ManagedBean
@SessionScoped
public class SeMdesgr00 extends Control implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Variables del Managedbean
	@ManagedProperty(value="#{seCabecera}")
	private SeCabecera seCabecera;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
		
	@ManagedProperty(value = "#{mdesgr00Service}")
	private IMdesgr00Service mdesgr00Service;
	
	private Mdesgr00 mdesgr00;
	private Mdesgr00 mdesgr00Cop;
	
	private List<Mdesgr00> listMdesgr00;
	private List<Mdesgr00> filteredMdesgr00;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	//Metodos del ManagedBean
	
	@PostConstruct
	public void init() {
		try {
			super.init("MDESGR00");
			mdesgr00 = new Mdesgr00();
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
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try{
			 seListas.actualizarListas(NombresListas.listMconca); 
			 seListas.actualizarListas(NombresListas.listMidiom);
			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			mdesgr00 = new Mdesgr00();
			mdesgr00.setRegids(true);
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	public void accionModificar(SelectEvent event) {
		try {
			reiniciarListasMaestro();
			setAccion(Constantes.ACCION_MODIFICAR);
			mdesgr00Cop= (Mdesgr00) mdesgr00.clone();

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	private void colaEstandar() {
		try {
			mdesgr00.setUserds(getSeControlFactura().codusu);
			mdesgr00.setPrgmds(getSeControlFactura().aplusu);
			mdesgr00.setFeacds(new Date());
			mdesgr00.setMaquds(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		listMdesgr00 = mdesgr00Service.listMdesgr00ByCriteria(new Mdesgr00());
		UtilsJSF.resetDTable("formTable:dTable");
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		//Codigo producto
		if(Utils.isEmptyOrNull(mdesgr00.getMidiom00()) && permisoCampos.get("ididds").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("ididds")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		
		return validacion;
	}
	
	public void save() {
		try {
			colaEstandar();	
			
			if (validarDatosRequeridos()) {
				return;
			}
			if(getAccion().equals("M"))
			{
				mdesgr00Service.save(mdesgr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mdesgr00.toString(),mdesgr00.toStringId(),mdesgr00Cop.toString()):null);
			}
			else
			{
				mdesgr00Service.save(mdesgr00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mdesgr00.toString(),mdesgr00.toStringId(),null):null);
			}
			cargarDatos();
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			cargarDatos();
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
						"UNIQUE_KEY");
				return;
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
						FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mdesgr00 = (Mdesgr00) event.getComponent().getAttributes()
					.get("mdespr00");
			mdesgr00Service.removeMdesgr00(mdesgr00, getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mdesgr00.toString()));
			cargarDatos();

			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
						"REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
						FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	//Limpia el filtro del DataTable
	public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:tabView:dTableMdesgr00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:tabView:dTableMdesgr00");
		}
	}
	
	public void preRenderView(){
		try {
			seCabecera.setBotonNuevo(true);
			seCabecera.setBotonExcel(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public SeCabecera getSeCabecera() {
		return seCabecera;
	}

	public void setSeCabecera(SeCabecera seCabecera) {
		this.seCabecera = seCabecera;
	}

	public IMdesgr00Service getMdesgr00Service() {
		return mdesgr00Service;
	}

	public void setMdesgr00Service(IMdesgr00Service mdesgr00Service) {
		this.mdesgr00Service = mdesgr00Service;
	}

	public Mdesgr00 getMdesgr00() {
		if(mdesgr00==null)
		{
			mdesgr00= new Mdesgr00();	
		}
		return mdesgr00;
	}

	public void setMdesgr00(Mdesgr00 mdesgr00) {
		this.mdesgr00 = mdesgr00;
	}

	public Mdesgr00 getMdesgr00Cop() {
		return mdesgr00Cop;
	}

	public void setMdesgr00Cop(Mdesgr00 mdesgr00Cop) {
		this.mdesgr00Cop = mdesgr00Cop;
	}

	public List<Mdesgr00> getListMdesgr00() {
		return listMdesgr00;
	}

	public void setListMdesgr00(List<Mdesgr00> listMdesgr00) {
		this.listMdesgr00 = listMdesgr00;
	}

	public List<Mdesgr00> getFilteredMdesgr00() {
		return filteredMdesgr00;
	}

	public void setFilteredMdesgr00(List<Mdesgr00> filteredMdesgr00) {
		this.filteredMdesgr00 = filteredMdesgr00;
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
	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}
		
	
}
