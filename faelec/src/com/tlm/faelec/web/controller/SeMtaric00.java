package com.tlm.faelec.web.controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMtaric00Service;

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

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMtaric00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mtaric00;

import com.tlm.faelecEntities.model.entities.Mtipre00;


@Controller
@ManagedBean
@SessionScoped
public class SeMtaric00  extends Control implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mtaric00Service}")
	private IMtaric00Service mtaric00Service;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mtaric00 mtaric00;
	private Mtaric00 mtaric00Cop;
	private List<Mtaric00> listMtaric00;
	private List<Mtaric00> filteredMtaric00;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	private Integer fracDec_CantiM;
	private InputText inputTextIdcricColumn;
	private InputText inputTextIdtiicColumn; 
	
	
	@PostConstruct
	public void init(){
		try{
			super.init("MTARIC00");
			mtaric00 = new Mtaric00();
			
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			setFracDec_CantiM(d1.intValue());
			
			Double d2=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			setFracDec_CantiM(d2.intValue());
	
		   // paternDec_CantiM = calcularPatern(fracDec_CantiM.toString());
		    //paternDec_PorceM = |calcularPatern(fracDec_PorceM.toString());
			
			cargarDatos();
		}catch(Exception e){
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
		
	}

	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			getSeListas().actualizarListas(NombresListas.listMgenusTipoBienServi);
			getSeListas().actualizarListas(NombresListas.listMgenusTarifaRetefDIAN);
			getSeListas().actualizarListas(NombresListas.listMgenusCiudad);
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
			mtaric00 = new Mtaric00();
			mtaric00.setRegcic(true);
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
			seListas.actualizarListas(NombresListas.listMconca);
			if(mtaric00.getMgenus001() != null){
				actualizarListasCompania(mtaric00.getMgenus001().getCodius());
				getSeControlFactura().cargarParametros(true,null,mtaric00.getMgenus001().getIdtrus());
			}else{
				getSeControlFactura().cargarParametros(true,null,null);
				reiniciarListasMaestro();
			}

			
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mtaric00.getMgenus001(), mtaric00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mtaric00.getMgenus002(), mtaric00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mtaric00.getMgenus003(), mtaric00.getMgenus003().getMtipre00().getTipore());

			}
			mtaric00Cop= (Mtaric00) mtaric00.clone();	
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusTipoBienServi, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusTarifaRetefDIAN, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusCiudad, listMconca00ActualizarListas);
			RequestContext.getCurrentInstance().update("formTable");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void autocompleteActualizarListas (){
		try{
			if(mtaric00.getMgenus001() != null){
			actualizarListasCompania(mtaric00.getMgenus001().getCodius());
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarDatos() {
		try{
			
			listMtaric00= mtaric00Service.listMtaric00ByCriteria(new Mtaric00(),getSeControlFactura().getCompaniasUsu());			
			UtilsJSF.resetDTable("formTable:dTable");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			mtaric00.setUsecic(getSeControlFactura().codusu);
			mtaric00.setPrgcic("Tarifas " + getClass().getName());
			mtaric00.setFeacic(new Date());
			mtaric00.setMaqcic(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
//		
		//Validacion lista  Tipo bIEN SERVICIO 
		if(mtaric00.getMgenus001() != null &&  mtaric00.getMgenus001().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idtiic")+" "+ mtaric00.getMgenus001().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
//		//Validacion lista Tarifa DIAN
		if(mtaric00.getMgenus002() != null && mtaric00.getMgenus002().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcric")+" "+mtaric00.getMgenus002().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista Ciudad
		if(mtaric00.getMgenus003() != null && mtaric00.getMgenus003().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idcuic")+" "+mtaric00.getMgenus003().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}

		
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		
		//Tipo Bn Servicio
		if(Utils.isEmptyOrNull(mtaric00.getMgenus001()) && permisoCampos.get("idtiic").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idtiic")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdtiicColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdtiicColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdtiicColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdtiicColumn.getClientId());
		}
	
		//id. Tipo de bien o Servic IDTBPR
		if(Utils.isEmptyOrNull(mtaric00.getMgenus002()) && permisoCampos.get("idcric").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcric")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcricColumn .setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcricColumn .getClientId());
			validacion = true;
		}else{
			inputTextIdcricColumn .setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcricColumn .getClientId());
		}
		
		return validacion;
	}
	
	
	
	public void save() {
		try {
			if(validarDatosRequeridos()){
				return;
			}
			colaEstandar();
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					mtaric00Service.save(mtaric00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mtaric00.toString(),mtaric00.toStringId(),mtaric00Cop.toString()):null);
				}else {
					return;
							}
			} else {
				mtaric00Service.save(mtaric00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mtaric00.toString(),mtaric00.toStringId(),null):null);
			 }
			cargarDatos();
			mtaric00Cop= (Mtaric00) mtaric00.clone();
			getSeListas().actualizarListas(NombresListas.listMconca);	
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
	
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mtaric00 = (Mtaric00) event.getComponent().getAttributes().get("mtaric00");
			mtaric00Service.removeMtaric00(mtaric00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mtaric00.toString(),mtaric00.toStringId()):null);
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
	
	/*public void evtCloseDialogMtaric(CloseEvent event) {
		try {
			update = null;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}*/
	
	public void preRenderView() {
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public IMtaric00Service getMtaric00Service() {
		return mtaric00Service;
	}


	public void setMtaric00Service(IMtaric00Service mtaric00Service) {
		this.mtaric00Service = mtaric00Service;
	}


	public SeListas getSeListas() {
		return seListas;
	}


	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}


	public Mtaric00 getMtaric00() {
		return mtaric00;
	}


	public void setMtaric00(Mtaric00 mtaric00) {
		this.mtaric00 = mtaric00;
	}


	public Mtaric00 getMtaric00Cop() {
		return mtaric00Cop;
	}


	public void setMtaric00Cop(Mtaric00 mtaric00Cop) {
		this.mtaric00Cop = mtaric00Cop;
	}


	public List<Mtaric00> getListMtaric00() {
		return listMtaric00;
	}


	public void setListMtaric00(List<Mtaric00> listMtaric00) {
		this.listMtaric00 = listMtaric00;
	}


	public List<Mtaric00> getFilteredMtaric00() {
		return filteredMtaric00;
	}


	public void setFilteredMtaric00(List<Mtaric00> filteredMtaric00) {
		this.filteredMtaric00 = filteredMtaric00;
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


	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}


	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}

	public InputText getInputTextIdcricColumn() {
		return inputTextIdcricColumn;
	}

	public void setInputTextIdcricColumn(InputText inputTextIdcricColumn) {
		this.inputTextIdcricColumn = inputTextIdcricColumn;
	}

	public InputText getInputTextIdtiicColumn() {
		return inputTextIdtiicColumn;
	}

	public void setInputTextIdtiicColumn(InputText inputTextIdtiicColumn) {
		this.inputTextIdtiicColumn = inputTextIdtiicColumn;
	}
	
}
