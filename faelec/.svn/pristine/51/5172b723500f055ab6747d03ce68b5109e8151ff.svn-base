package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;


import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMtabla00Service;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mgente00;


@Controller
@ManagedBean
@SessionScoped
public class SeMtabla00 extends Control implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 *Variables MBean 
	 */
	@ManagedProperty(value = "#{mtabla00Service}")
	private IMtabla00Service mtabla00Service;
	
	
	private List<Mtabla00> listMtabla00; 
	private List<Mtabla00> listMtabla00Usuario; 
	private List<Mtabla00> filteredMtabla00;
	private Mtabla00 mtabla00;
	private List<String> listMconca00ActualizarListas;//En esta lista se guardan todas las compañias
	
	@ManagedProperty(value="#{seMtipre00}")
	private SeMtipre00 semtipre00;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private Object objSeCall;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	
	private String accion;
	private String titulo;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	
	@PostConstruct
	public void init(){
		try {
			super.init("MTABLA00");
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			mtabla00 = new Mtabla00();
			mtabla00.setUspaab("S");
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
//			listMtabla00 = mtabla00Service.listMtabla00ByCriteria(super.aplusu, mtabla00);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void llenarObjectCall(SelectEvent event) {

		try {
			listMconca00ActualizarListas = new ArrayList<String>();
			if (objSeCall == null) {
				return;
			}
			String strClassLlama = objSeCall.getClass().getSimpleName();			
			if (strClassLlama.equalsIgnoreCase("SeMparme00")) {
				SeMparme00 seMparme00 = (SeMparme00) objSeCall;
				//Se agrega a la lista de compañias la que se encuentra en el maestro del cual se esta llamando
				if(seMparme00.getMparme00().getMconca00() != null){
				listMconca00ActualizarListas.add(seMparme00.getMparme00().getMconca00().getCodcia());
				}else{
					listMconca00ActualizarListas = null;
				}	
				if (nombreTabla.equalsIgnoreCase("mtabla00")) {
					seMparme00.setMtabla00((Mtabla00) event.getObject());
					seMparme00.setVisibleCadena(false);
					seMparme00.setVisibleMtipre(false);
					seMparme00.setVisibleMgenus(false);
					seMparme00.setVisibleMgente(false);
					seMparme00.setListMtipre00(null);
					seMparme00.getMparme00().setVapame(null);
					seMparme00.getMparme00().setVapamh(null);
					seMparme00.getMparme00().setTrmame(null);
					seMparme00.getMparme00().setNomame(null);
					seMparme00.setMtipre00(new Mtipre00());
					seMparme00.setMgenus00(new Mgenus00());
					seMparme00.setMgente00(new Mgente00());
					if (seMparme00.getMtabla00().getId().getCodtab().equals("MGENUS00")) {
						seMparme00.getMtipre00().setTperre("U");
						seListas.obtenerMtipre(seMparme00.getMtipre00(),listMconca00ActualizarListas);
						seMparme00.setVisibleMtipre(true);
						seMparme00.setVisibleMgenus(true);
					} else if (seMparme00.getMtabla00().getId().getCodtab().equals("MGENTE00")) {
						seMparme00.getMtipre00().setTperre("T");
						seListas.obtenerMtipre(seMparme00.getMtipre00(),listMconca00ActualizarListas);
						seMparme00.setVisibleMtipre(true);
						seMparme00.setVisibleMgente(true);
					}else if (seMparme00.getMtabla00().getId().getCodtab().equals("MGENCG00")) {
						seMparme00.getMtipre00().setTperre("C");
						seListas.obtenerMtipre(seMparme00.getMtipre00(),listMconca00ActualizarListas);
						seMparme00.setVisibleMtipre(true);
						seMparme00.setVisibleMgencg(true);
					}else{
						seMparme00.setVisibleCadena(true);
					}
					seMparme00.getMparme00().setNomame(seMparme00.getMtabla00().getId().getCodtab());
					RequestContext.getCurrentInstance().reset("formDetalle");
					RequestContext.getCurrentInstance().update("formDetalle:pnlDetalle");
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}

		this.update = null;
	}
	
	public void evtCloseDialogMtabla(CloseEvent event) {
		try {
			update = null;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	//Metodo que autocompleta todas las listas de Mtabla00
	public List<Mtabla00> completeMtabla(String query) {		

        List<Mtabla00> results = new ArrayList<Mtabla00>();   
        List<Mtabla00> listMtabla00aux = seListas.getListMtabla00();

        for (Mtabla00 obj : listMtabla00aux) {        	
        	if(obj.getId().getCodtab().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	
	//Metodos de acceso
	
	public List<Mtabla00> getListMtabla00() {
		return listMtabla00;
	}


	public void setListMtabla00(List<Mtabla00> listMtabla00) {
		this.listMtabla00 = listMtabla00;
	}


	public List<Mtabla00> getFilteredMtabla00() {
		return filteredMtabla00;
	}


	public void setFilteredMtabla00(List<Mtabla00> filteredMtabla00) {
		this.filteredMtabla00 = filteredMtabla00;
	}


	public Mtabla00 getMtabla00() {
		return mtabla00; 
	}


	public void setMtabla00(Mtabla00 mtabla00) {
		this.mtabla00 = mtabla00;
	}
	
	public List<Mtabla00> getListMtabla00Usuario() {
		return listMtabla00Usuario;
	}
	public void setListMtabla00Usuario(List<Mtabla00> listMtabla00Usuario) {
		this.listMtabla00Usuario = listMtabla00Usuario;
	}


	public IMtabla00Service getMtabla00Service() {
		return mtabla00Service;
	}


	public void setMtabla00Service(IMtabla00Service mtabla00Service) {
		this.mtabla00Service = mtabla00Service;
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


	public Object getObjSeCall() {
		return objSeCall;
	}


	public void setObjSeCall(Object objSeCall) {
		this.objSeCall = objSeCall;
	}

	public SeMtipre00 getSemtipre00() {
		return semtipre00;
	}

	public void setSemtipre00(SeMtipre00 semtipre00) {
		this.semtipre00 = semtipre00;
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