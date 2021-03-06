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

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;

import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMtiptx00Service;

import com.tlm.faelec.service.maestros.IMrafra00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mpreci00;

import com.tlm.faelecEntities.model.entities.Mtiptx00;

import com.tlm.faelecEntities.model.entities.Tucauc00;

import com.tlm.faelecEntities.model.entities.Mrafra00;

@Controller
@ManagedBean
@SessionScoped
public class SeMrafra00  extends Control implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mrafra00Service}")
	private IMrafra00Service mrafra00Service;
	

	@ManagedProperty(value = "#{mtiptx00Service}")
	private IMtiptx00Service mtiptx00Service;

	@ManagedProperty(value = "#{mconca00Service}")
	private IMconca00Service mconca00Service;	
	
	@ManagedProperty(value="#{seCabecera}")
	private SeCabecera seCabecera;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	

	
	private List<Mtiptx00> listMtiptx00;
	private Mtiptx00 mtiptx00;
	
	private Mrafra00 mrafra00;
	private Mrafra00 Mrafra00Cop;
	private List<Mrafra00> listMrafra00;
	private List<Mrafra00> filteredMrafra00;

	private Mconca00 mconca00;
	
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
		
	private Integer fracDec_CantiM;
	private Integer fracDec_PorceM;
	private String paternDec_CantiM;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;

	//Visualizacion de pesta�as
	private boolean tabInfBase;

	private boolean infoGuardada;
	private String[] pestanas;
	
	private String compaCodcia;
	private boolean validacionFecha;
	private boolean validacionRango;
	private boolean validacionRangofechas;
	
	
	private InputText inputTextIdtxraColumn;
	private InputText inputTextIdpgraColumn;

	@PostConstruct
	public void init() {
		try {
			super.init("Mrafra00");
			mrafra00 = new Mrafra00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			compaCodcia = super.getSeControlFactura().getMconca00Session().getCodcia();
		
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			fracDec_CantiM=d1.intValue();
			
			tabInfBase = false;
			validacionFecha  = false;
			validacionRangofechas = false;
			
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try{
			getSeListas().actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMtiptx00);
			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	
	public void cargarDatos() {
		listMrafra00 = mrafra00Service.listMrafra00ByCriteria(new Mrafra00(),getSeControlFactura().getCompaniasUsu());
	}
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			seListas.actualizarListas(NombresListas.listMconca);
			mrafra00 = new Mrafra00();
			mrafra00.setRegrra(true);
			getSeListas().actualizarListas(NombresListas.listMconca);

			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	public void accionModificar(SelectEvent event) {
		try {
			
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			Mrafra00Cop= (Mrafra00) mrafra00.clone();
		
			//Mrafra00 = mrafra00Service.cargarDetalles(Mrafra00);


			if(validarListas()==true){
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mrafra00.getMtiptx00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mrafra00.getMconca00(), null);

			}
			reiniciarListasMaestro();
			infoGuardada = false;
			RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00"); 
				
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			
			getSeListas().actualizarListasCompania(NombresListas.listMconca , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMtiptx00, listMconca00ActualizarListas);
			RequestContext.getCurrentInstance().update("formTable");
			}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void autocompleteActualizarListas (){
		try{
			if(mrafra00.getMconca00() != null){
				actualizarListasCompania(compaCodcia);
				}else{
					reiniciarListasMaestro();
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void colaEstandar() {
		try {
			mrafra00.setUserra(getSeControlFactura().codusu);
			mrafra00.setPrgrra("Rangos " + getClass().getName());
			mrafra00.setFearra(new Date());
			mrafra00.setMaqrra(getSeControlFactura().ip);
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

		//Validacion lista compa�iaIDPGRA
		if(mrafra00.getMconca00() != null &&  mrafra00.getMconca00().getRegcia()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idpgra")+" "+ mrafra00.getMconca00().getCodcia() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		
		//Validacion lista  transacin IDTXRA
		if(mrafra00.getMtiptx00()!= null &&  mrafra00.getMtiptx00().isRegtxt()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
		    getIdiomasHm().get("idtxra")+" "+ mrafra00.getMconca00().getCodcia() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
	
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;

		if(Utils.isEmptyOrNull(mrafra00.getMconca00()) && permisoCampos.get("idpgra").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idpgra")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdpgraColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdpgraColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdpgraColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdpgraColumn.getClientId());	
		}
		

		if(Utils.isEmptyOrNull(mrafra00.getMtiptx00()) && permisoCampos.get("idtxra").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idtxra")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdtxraColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdtxraColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdtxraColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdtxraColumn.getClientId());	
		}
		
		//fecha Vigente desde
		if(Utils.isEmptyOrNull(mrafra00.getRavdra()) && permisoCampos.get("ravdra").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("ravdra")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		//fecha vigencia hasta
		if(Utils.isEmptyOrNull(mrafra00.getRavhra()) && permisoCampos.get("ravhra").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("ravhra")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
		
		//fecha autorizacion
		if(Utils.isEmptyOrNull(mrafra00.getRafera()) && permisoCampos.get("rafera").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("rafera")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			validacion = true;
		}
	
		
		return validacion;
	}
	
	public boolean validarFechas(){
		validacionFecha = false;
		if(mrafra00.getRavdra().after(mrafra00.getRavhra())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha de vigencia hasta debe ser mayor o igual a Fecha vigencia desde");
			RequestContext.getCurrentInstance().update("formDetalle"); 
			validacionFecha = true;		
		}else if(mrafra00.getRahara() < mrafra00.getRadera()){
		UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Rango hasta debe ser mayor o igual al rango desde");
			RequestContext.getCurrentInstance().update("formDetalle"); 
			validacionFecha = true;	
		}
		
		/*else if(mrafra00.getFeviqg().after(mrafra00.getFepdqg())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha de autorizaci�n debe ser mayor o igual a la fecha de hoy");
			RequestContext.getCurrentInstance().update("formTable"); 
			validacionFecha = true;	
		}*/
		return validacionFecha;
	}
	
	public boolean validarRango()
	 {
		validacionRango=true;
		List<Mrafra00> listMrafra00 = mrafra00Service.listMrafra00(mrafra00,getSeControlFactura().getCompaniasUsu());
	   //validacionRango=true;
		System.out.println("en valida listMrafra00: "+listMrafra00);

		   for (Mrafra00 mrafra : listMrafra00) 
		   {
				   if(mrafra00.getIdrara()!=mrafra.getIdrara())
				   {
					   if(mrafra00.getRadera() > mrafra.getRadera() && mrafra00.getRadera() < mrafra.getRahara())
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El rango ya se encuentra para la compa�ia "+mrafra00.getMconca00().getNomcia()+" y para el prefijo "+mrafra00.getPrefra()+"");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00"); 
						   validacionRango=false;
						   break; 
					   }
					   if(mrafra00.getRahara() > mrafra.getRadera() && mrafra00.getRahara() < mrafra.getRahara())
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El rango ya se encuentra para la compa�ia "+mrafra00.getMconca00().getNomcia()+" y para el prefijo "+mrafra00.getPrefra()+"");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00"); 
						   validacionRango=false;
						   //return;
						   break;
					   }
					   if(mrafra00.getRahara() > mrafra.getRadera() && mrafra00.getRahara() < mrafra.getRahara())
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El rango ya se encuentra para la compa�ia "+mrafra00.getMconca00().getNomcia()+" y para el prefijo "+mrafra00.getPrefra()+"");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00");
						   validacionRango=false;
						   break;  
					   }
					   if(mrafra00.getRadera().equals(mrafra.getRadera()) || mrafra00.getRadera().equals(mrafra.getRahara()))
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El rango ya se encuentra para la compa�ia "+mrafra00.getMconca00().getNomcia()+" y para el prefijo "+mrafra00.getPrefra()+"");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00");
						   validacionRango=false;
						   break;  
					   }
					   if(mrafra00.getRahara().equals(mrafra.getRadera()) || mrafra00.getRahara().equals(mrafra.getRahara()))
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El rango ya se encuentra para la compa�ia "+mrafra00.getMconca00().getNomcia()+" y para el prefijo "+mrafra00.getPrefra()+"");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00");
						   validacionRango=false;
						   break;  
					   }
				   }
		   }
	   return validacionRango;
	 }
	
	
	public boolean validarRangoFechas()
	 {
		validacionRangofechas=true;
		List<Mrafra00> listMrafra00 = mrafra00Service.listMrafra00(mrafra00,getSeControlFactura().getCompaniasUsu());
	   //validacionRango=true;
		System.out.println("en valida listMrafra00: "+listMrafra00);

		   for (Mrafra00 mrafra : listMrafra00) 
		   {
				   if(mrafra00.getIdrara()!=mrafra.getIdrara())
				   {
					   if(mrafra00.getRavdra().after(mrafra.getRavdra()) && mrafra00.getRavdra().before(mrafra.getRavhra()))
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El registro ya se encuentra para ese prefijo y ese rango de fechas");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00"); 
						   validacionRangofechas=false;
						   break; 
					   }
					   if(mrafra00.getRavhra().after(mrafra.getRavdra()) && mrafra00.getRavhra().before(mrafra.getRavhra()))
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El registro ya se encuentra para ese prefijo y ese rango de fechas");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00"); 
						   validacionRangofechas=false;
						   //return;
						   break;
					   }
					   if(mrafra00.getRavhra().after(mrafra.getRavdra()) && mrafra00.getRavhra().before(mrafra.getRavhra()))
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El registro ya se encuentra para ese prefijo y ese rango de fechas");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00");
						   validacionRangofechas=false;
						   break;  
					   }
					   if(mrafra00.getRavdra().equals(mrafra.getRavdra()) || mrafra00.getRavdra().equals(mrafra.getRavhra()))
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El registro ya se encuentra para ese prefijo y ese rango de fechas");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00");
						   validacionRangofechas=false;
						   break;  
					   }
					   if(mrafra00.getRavhra().equals(mrafra.getRavdra()) || mrafra00.getRavhra().equals(mrafra.getRavhra()))
					   {
						   UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "El registro ya se encuentra para ese prefijo y ese rango de fechas");
						   RequestContext.getCurrentInstance().update(":formDetalle:pnlMrafra00");
						   validacionRangofechas=false;
						   break;  
					   }
				   }
		   }
	   return validacionRangofechas;
	 }
	
	
	
	
	
	public void save() {
		try {

			
			if(mrafra00.getRavdra()!=null && mrafra00.getRavhra() !=null && mrafra00.getRadera()!=null){
				validarFechas();
				if(validacionFecha){
					return;
				}
			}
			
			validarRango();
			if(!validacionRango){
			      return;
			}
			
			validarRangoFechas();
			if(!validacionRangofechas){
			      return;
			}
			
			if (validarDatosRequeridos()) {
				return;
			}
			

			colaEstandar();
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				
				if(validarListas()==false){
					mrafra00Service.save(mrafra00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mrafra00.toString(),mrafra00.toStringId(),Mrafra00Cop.toString()):null);
							
				}else return;
			} else {
				mrafra00Service.save(mrafra00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mrafra00.toString(),mrafra00.toStringId(),null):null);
			 }
			
			cargarDatos();
			infoGuardada=false;
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
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
			mrafra00 = (Mrafra00) event.getComponent().getAttributes().get("mrafra00");
			mrafra00Service.removeMrafra00(mrafra00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mrafra00.toString(),mrafra00.toStringId()):null);
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
	
	public void preRenderView() {
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialogMrafra00(CloseEvent event) {
        try {  

        	
        	update=null;
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
        		mrafra00.setMtiptx00(Mrafra00Cop.getMtiptx00());
        		mrafra00.setMconca00(Mrafra00Cop.getMconca00());
        
        	}
        	evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	

	public IMrafra00Service getMrafra00Service() {
		return mrafra00Service;
	}

	public void setMrafra00Service(IMrafra00Service mrafra00Service) {
		this.mrafra00Service = mrafra00Service;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public Mrafra00 getMrafra00() {
		return mrafra00;
	}

	public void setMrafra00(Mrafra00 mrafra00) {
		this.mrafra00 = mrafra00;
	}

	public Mrafra00 getMrafra00Cop() {
		return Mrafra00Cop;
	}

	public void setMrafra00Cop(Mrafra00 Mrafra00Cop) {
		this.Mrafra00Cop = Mrafra00Cop;
	}

	public List<Mrafra00> getListMrafra00() {
		return listMrafra00;
	}

	public void setListMrafra00(List<Mrafra00> listMrafra00) {
		this.listMrafra00 = listMrafra00;
	}

	public List<Mrafra00> getFilteredMrafra00() {
		return filteredMrafra00;
	}

	public void setFilteredMrafra00(List<Mrafra00> filteredMrafra00) {
		this.filteredMrafra00 = filteredMrafra00;
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

	public boolean isTabInfBase() {
		return tabInfBase;
	}

	public void setTabInfBase(boolean tabInfBase) {
		this.tabInfBase = tabInfBase;
	}

	
	public String[] getPestanas() {
		return pestanas;
	}

	public void setPestanas(String[] pestanas) {
		this.pestanas = pestanas;
	}

	public SeCabecera getSeCabecera() {
		return seCabecera;
	}
	public void setSeCabecera(SeCabecera seCabecera) {
		this.seCabecera = seCabecera;
	}
	
	public String getPaternDec_CantiM() {
		return paternDec_CantiM;
	}

	public void setPaternDec_CantiM(String paternDec_CantiM) {
		this.paternDec_CantiM = paternDec_CantiM;
	}

	public IMtiptx00Service getMtiptx00Service() {
		return mtiptx00Service;
	}

	public void setMtiptx00Service(IMtiptx00Service mtiptx00Service) {
		this.mtiptx00Service = mtiptx00Service;
	}

	public List<Mtiptx00> getListMtiptx00() {
		return listMtiptx00;
	}

	public void setListMtiptx00(List<Mtiptx00> listMtiptx00) {
		this.listMtiptx00 = listMtiptx00;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public IMconca00Service getMconca00Service() {
		return mconca00Service;
	}

	public void setMconca00Service(IMconca00Service mconca00Service) {
		this.mconca00Service = mconca00Service;
	}

	public boolean isInfoGuardada() {
		return infoGuardada;
	}

	public void setInfoGuardada(boolean infoGuardada) {
		this.infoGuardada = infoGuardada;
	}

	public String getCompaCodcia() {
		return compaCodcia;
	}

	public void setCompaCodcia(String compaCodcia) {
		this.compaCodcia = compaCodcia;
	}

	public InputText getInputTextIdtxraColumn() {
		return inputTextIdtxraColumn;
	}

	public void setInputTextIdtxraColumn(InputText inputTextIdtxraColumn) {
		this.inputTextIdtxraColumn = inputTextIdtxraColumn;
	}

	public InputText getInputTextIdpgraColumn() {
		return inputTextIdpgraColumn;
	}

	public void setInputTextIdpgraColumn(InputText inputTextIdpgraColumn) {
		this.inputTextIdpgraColumn = inputTextIdpgraColumn;
	}

	public boolean isValidacionFecha() {
		return validacionFecha;
	}

	public void setValidacionFecha(boolean validacionFecha) {
		this.validacionFecha = validacionFecha;
	}

	public boolean isValidacionRango() {
		return validacionRango;
	}

	public void setValidacionRango(boolean validacionRango) {
		this.validacionRango = validacionRango;
	}	
	
}
