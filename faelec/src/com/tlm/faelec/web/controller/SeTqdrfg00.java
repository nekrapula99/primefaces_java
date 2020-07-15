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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;

@Controller
@ManagedBean
@SessionScoped
public class SeTqdrfg00 extends Control implements Serializable{
	
	private static final long serialVersionUID = 7213183176463668661L;
	
	
	private Tqdrfg00 tqdrfg00;
	
	@ManagedProperty("#{seTqdfqf00}")
	private SeTqdfqf00 seTqdfqf00;
	
	@ManagedProperty(value = "#{seTqigqg00}")
	private SeTqigqg00 seTqigqg00;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private boolean infoGuardada;
	private Tqdrfg00 tqdrfg00Cop;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	
	private Tqdfqf00 tqdfqf00;
	private Tqdrqr00 tqdrqr00;
	
	private String tabla;
	private String accion;
	private String titulo;
	private String paternDec_CantiM;
	private String update;
	
	@PostConstruct
	public void init(){
		try {
			super.init("TQDRFG00");
			
			tqdrfg00= new Tqdrfg00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);//aqui
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			tqdrfg00 = new Tqdrfg00();
			tqdrfg00.setRegcfg(true);
			tqdrfg00.setTqdfqf00(seTqdfqf00.getTqdfqf00());
			tqdrqr00 = new Tqdrqr00();
			tqdrqr00.setRegcqr(true);
			tqdrqr00.setTqigqg00(seTqigqg00.getTqigqg00());
			seListas.cargarDatosReqProd(tqdrqr00);
			tqdrfg00.setTqdfqf00(tqdfqf00);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void accionModificar(ActionEvent event){ 
		try {
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			tqdrfg00 = (Tqdrfg00) event.getComponent().getAttributes().get("tqdrfg00");
			tqdrfg00Cop = (Tqdrfg00) tqdrfg00.clone();
			infoGuardada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	 public void colaEstandar(){
			
			tqdrfg00.setRegcfg(true);
			tqdrfg00.setUsecfg(getSeControlFactura().codusu);
			tqdrfg00.setPrgcfg(getSeControlFactura().aplusu);
			tqdrfg00.setFeacfg(new Date());
			tqdrfg00.setMaqcfg(getSeControlFactura().ip);
	 }
	 
		//metodo que valida la Unique Key en la lista Mcpcct00
	 private boolean validarUKMqdrfg00() throws Exception{
		  try {   
			  if(!Utils.isEmptyOrNull(seTqdfqf00.getTqdfqf00().getTqdrfg00s())){
				  List<Tqdrfg00> listTemp = new ArrayList<Tqdrfg00>(seTqdfqf00.getTqdfqf00().getTqdrfg00s());
				  listTemp.remove(tqdrfg00);
		    
				  for (Tqdrfg00 objIter : listTemp) {
					  //objIter.getTqdfqf00().getIdcpqf() == tqdrfg00.getTqdfqf00().getIdcpqf()
					  if(objIter.getTqdpqp00().getIdqpqp() == tqdrfg00.getTqdpqp00().getIdqpqp()){
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
	
			colaEstandar();
			tqdrfg00.setTqdfqf00(seTqdfqf00.getTqdfqf00());
			
			if(validarUKMqdrfg00()){
				if(getAccion().equals(Constantes.ACCION_NUEVO))
				{
					seTqdfqf00.getTqdfqf00().getTqdrfg00s().add(tqdrfg00);
//					seMqdfqf00.getListMqdrfg00Aux().add(mqdrfg00);
				}
			}else{			
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");				
				infoGuardada = false;			
				return;
			}
			infoGuardada = true;
		    UtilsJSF.closeDialog("dlgNuevoUpdateTqdrfg00");
		    UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		    RequestContext.getCurrentInstance().update(":formTqdfqf00Detalle:dTabletqdrfg00");
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
			tqdrfg00 = (Tqdrfg00) event.getComponent().getAttributes().get("tqdrfg00");
			seTqdfqf00.getTqdfqf00().getTqdrfg00s().remove(tqdrfg00);
			seTqdfqf00.getListTqdrfg00Aux().remove(tqdrfg00);
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
	
	//Limpia el filtro del DataTable
	public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent(":formTqdfqf00Detalle:dTabletqdrfg00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update(":formTqdfqf00Detalle:dTabletqdrfg00");
		}
	}
	
	public void evtCloseDialog(CloseEvent event) {
		//Asigno los valores iniciales cuando la informacion no fue guardada
		setUpdate(null);
		RequestContext.getCurrentInstance().update(":formTqdfqf00Detalle:dTabletqdrfg00");
				
	}

	public Tqdrfg00 getTqdrfg00() {
		return tqdrfg00;
	}

	public void setTqdrfg00(Tqdrfg00 tqdrfg00) {
		this.tqdrfg00 = tqdrfg00;
	}

	public SeTqdfqf00 getSeTqdfqf00() {
		return seTqdfqf00;
	}

	public void setSeTqdfqf00(SeTqdfqf00 seTqdfqf00) {
		this.seTqdfqf00 = seTqdfqf00;
	}

	public boolean isInfoGuardada() {
		return infoGuardada;
	}

	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
	}

	public Tqdrfg00 getTqdrfg00Cop() {
		return tqdrfg00Cop;
	}

	public void setTqdrfg00Cop(Tqdrfg00 tqdrfg00Cop) {
		this.tqdrfg00Cop = tqdrfg00Cop;
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

	public String getPaternDec_CantiM() {
		return paternDec_CantiM;
	}

	public void setPaternDec_CantiM(String paternDec_CantiM) {
		this.paternDec_CantiM = paternDec_CantiM;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}

	public SeTqigqg00 getSeTqigqg00() {
		return seTqigqg00;
	}

	public void setSeTqigqg00(SeTqigqg00 seTqigqg00) {
		this.seTqigqg00 = seTqigqg00;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public Tqdrqr00 getTqdrqr00() {
		return tqdrqr00;
	}

	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}
	
}
