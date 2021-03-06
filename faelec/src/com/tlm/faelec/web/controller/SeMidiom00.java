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

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;


import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMidiom00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Midiom00;

@Controller
@ManagedBean
@SessionScoped
public class SeMidiom00 extends Control implements Serializable{
	private static final long serialVersionUID = 1L;
	//Variables del MBean
	private List<Midiom00> listMidiom00; 
	private List<Midiom00> listMidiom00Usuario; 
	private List<Midiom00> filteredMidiom00;
	private Midiom00 midiom00;
	private Midiom00 midiom00Cop;
	
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
	
	
//	private Midiom00 midiom00Cop;
	
	@ManagedProperty(value = "#{midiom00Service}")
	private IMidiom00Service midiom00Service;
	
	
	//	Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	
	
	@PostConstruct
	public void init(){
		try {
			super.init("MIDIOM00");
			midiom00 = new Midiom00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			cargarDatos();
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	public void cargarDatos() {			
		listMidiom00 = midiom00Service.listMidiom00ByCriteria(new Midiom00(),getSeControlFactura().getCompaniasUsu());
		UtilsJSF.resetDTable("formTable:dTable");
	}
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			midiom00 = new Midiom00();	
			midiom00.setRegidi(true);
			seListas.actualizarListas(NombresListas.listMconca);
			reiniciarListasMaestro();
		} catch (Exception e) {
			 e.printStackTrace();
			 UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
			 FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
		private void reiniciarListasMaestro(){
			try {

				getSeListas().actualizarListas(NombresListas.listMconca);

			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
	
	public void accionModificar(SelectEvent event) { 
		try {
			//Se cargan los parametros deacuerdo a la compa�ia seleccionada
			if(midiom00.getMconca00()!= null){
				getSeControlFactura().cargarParametros(true,null,midiom00.getMconca00().getIdccia());
			}
			seListas.actualizarListas(NombresListas.listMconca);
			setAccion(Constantes.ACCION_MODIFICAR);	
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			midiom00Cop= (Midiom00) midiom00.clone();
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de la lista al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(midiom00.getMconca00(), null);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
		//Validacion lista idgeus tipo identificacion
		if(midiom00.getMconca00() != null &&midiom00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idcmdi")+" "+midiom00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		
		return validacion;
	}
	
	public void save() {
		try {
			colaEstandar();			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					midiom00Service.save(midiom00,parametroVentCata("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,midiom00.toString(),midiom00.toStringId(),midiom00Cop.toString()):null);
				}else return;
			}
			else
			{
				midiom00Service.save(midiom00,parametroVentCata("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,midiom00.toString(),midiom00.toStringId(),null):null);
			}
			cargarDatos();
			seListas.actualizarListas(NombresListas.listMidiom);
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
		   midiom00.setUsecdi(getSeControlFactura().codusu);
		   midiom00.setPrgcdi("Idioma " + getClass().getName());
		   midiom00.setFeacdi(new Date());
		   midiom00.setMaqcdi(getSeControlFactura().ip);
		   
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	}
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			midiom00 = (Midiom00) event.getComponent().getAttributes()
					.get("midiom00");			
			midiom00Service.removeMidiom00(
					midiom00,
					getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,
							midiom00.toString(),midiom00.toStringId()):null);
			cargarDatos();
			seListas.actualizarListas(NombresListas.listMidiom);
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
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
	public void llenarObjectCall(SelectEvent event) {
		
		try
		{
			if (objSeCall == null) {
				return;
			}
			String strClassLlama = objSeCall.getClass().getSimpleName();
			
			if (strClassLlama.equalsIgnoreCase("seMdespr00")) {
				SeMdespr00 mdespr00 = (SeMdespr00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("midiom00")) {
					if (nombreLista.equalsIgnoreCase("idiomas")) {
						mdespr00.getMdespr00().setMidiom00((Midiom00) event.getObject());
						RequestContext.getCurrentInstance().reset("formMdespr00Detalle");
					}
				}
			}
			
			if (strClassLlama.equalsIgnoreCase("seMgenus00")) {
				SeMgenus00 seMgenus00 = (SeMgenus00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("midiom00")) {
					if (nombreLista.equalsIgnoreCase("idiomas")) {
						seMgenus00.getMdesgr00().setMidiom00(
								(Midiom00) event.getObject());
						RequestContext.getCurrentInstance().reset("formMdesgr00Detalle");
						// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
					}
				}
			}
			if (strClassLlama.equalsIgnoreCase("seMparca00")) {
				SeMparca00 seMparca00 = (SeMparca00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("midiom00")) {
					if (nombreLista.equalsIgnoreCase("idiomas")) {
						seMparca00.getMparca00().setMidiom00((Midiom00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
						// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
					}
				}
			}
		
			update=null;
		}
		catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
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
	
	public void evtCloseDialogMidiom(CloseEvent event) {
        try {   
        	update=null;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	//Metodo que autocompleta todas las listas de Midiom00
	public List<Midiom00> completeMidiom(String query) {		

        List<Midiom00> results = new ArrayList<Midiom00>();   
        List<Midiom00> listMidiom = listMidiom00;

        for (Midiom00 obj : listMidiom) {        	
        	if(obj.getCodidi().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	
	
	
	
	
	
	//Metodos de acceso
	public List<Midiom00> getListMidiom00() {
		return listMidiom00;
	}
	public void setListMidiom00(List<Midiom00> listMidiom00) {
		this.listMidiom00 = listMidiom00;
	}
	public List<Midiom00> getListMidiom00Usuario() {
		return listMidiom00Usuario;
	}
	public void setListMidiom00Usuario(List<Midiom00> listMidiom00Usuario) {
		this.listMidiom00Usuario = listMidiom00Usuario;
	}
	public List<Midiom00> getFilteredMidiom00() {
		return filteredMidiom00;
	}
	public void setFilteredMidiom00(List<Midiom00> filteredMidiom00) {
		this.filteredMidiom00 = filteredMidiom00;
	}
	public Midiom00 getMidiom00() {
		return midiom00;
	}
	public void setMidiom00(Midiom00 midiom00) {
		this.midiom00 = midiom00;
	}
	
	public IMidiom00Service getMidiom00Service() {
		return midiom00Service;
	}

	public void setMidiom00Service(IMidiom00Service midiom00Service) {
		this.midiom00Service = midiom00Service;
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

	public Midiom00 getMidiom00Cop() {
		return midiom00Cop;
	}

	public void setMidiom00Cop(Midiom00 midiom00Cop) {
		this.midiom00Cop = midiom00Cop;
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