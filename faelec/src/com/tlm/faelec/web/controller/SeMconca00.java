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
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.StreamedContent;

import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;

import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;

@Controller
@ManagedBean
@SessionScoped
public class SeMconca00 extends Control implements Serializable {
	private static final long serialVersionUID = 1L;

//	 variables del managed bean
	@ManagedProperty(value = "#{mconca00Service}")
	private IMconca00Service mconca00Service;
	
	@ManagedProperty(value="#{mgenus00Service}")
	IMgenus00Service mgenus00Service;

	private Mconca00 mconca00;
	private Mconca00 mconca00Cop;
	private List<Mconca00> listMconca00;
	private List<Mconca00> filteredMconca00;
	private List<String> listMconca00ActualizarListas;//En esta lista se guardan todas las compa�ias
	private UploadedFile logoCompaFile;
	// Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	private boolean infoGuardada;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	

	@PostConstruct
	public void init() {
		try {
			super.init("MCONCA00");
			mconca00 = new Mconca00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();			
			cargarDatos();		
			infoGuardada = false;			
//			seLogin.iniciarSesion("CPONCE", "1");	
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			getSeListas().actualizarListas(NombresListas.listMgenusTident);
			getSeListas().actualizarListas(NombresListas.listMgenusCiudad);
			getSeListas().actualizarListas(NombresListas.listMgenusPaises);
			getSeListas().actualizarListas(NombresListas.listMgenusDepartamento);
			getSeListas().actualizarListas(NombresListas.listMgenusBarrioSector);
			getSeListas().actualizarListas(NombresListas.listMgenteRepresentanteLegal);
			getSeListas().actualizarListas(NombresListas.listMgencgCompania);
			getSeListas().actualizarListas(NombresListas.listMregcg00);
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
			mconca00 = new Mconca00();
			mconca00.setRegcia(true);
			//System.out.println(idiomasHm.get("codcia"));
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
			
			//Se actualizan las listas para la compa�ia seleccionada
			actualizarListasCompania(getMconca00().getCodcia());				
			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mconca00.getMgenus001(), mconca00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mconca00.getMgenus002(), mconca00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mconca00.getMgenus003(), mconca00.getMgenus003().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mconca00.getMregcg004(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mconca00.getMgente00(), mconca00.getMgente00().getMtipre00().getTipore());
			}
			mconca00Cop= (Mconca00) mconca00.clone();	
			reiniciarListasMaestro();
			infoGuardada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusTident, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusCiudad, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusPaises, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenteRepresentanteLegal, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgencgCompania , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMregcg00, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusDepartamento, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMgenusBarrioSector, listMconca00ActualizarListas);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public void cargarDatos() {
		try{
			listMconca00 = getSeControlFactura().mconca00Service.listMconca00ByRegcia(new Mconca00(),getSeControlFactura().getCompaniasUsu());
			UtilsJSF.resetDTable("formTable:dTable");
			mconca00 = new Mconca00();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	private void colaEstandar() {
		try {
			mconca00.setUsecia(getSeControlFactura().codusu);
			mconca00.setPrgcia("Compa�ias " + getClass().getName());
			mconca00.setFeacia(new Date());
			mconca00.setMaqcia(getSeControlFactura().ip);

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
		//Validacion lista idgeus tipo identificacion
		if(mconca00.getMgenus001() != null && mconca00.getMgenus001().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("idgeus")+" "+mconca00.getMgenus001().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista idciud Ciudad 
		if(mconca00.getMgenus002() != null &&  mconca00.getMgenus002().getRegius()==false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("idciud")+" "+ mconca00.getMgenus002().getCodius() +" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));			
			validacion = true;
		}
		//Validacion lista idpcia Pais
		if(mconca00.getMgenus003() != null && mconca00.getMgenus003().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("idpcia")+" "+mconca00.getMgenus003().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
	
		//Validacion lista sector barrio
		if(mconca00.getMgenus004() != null && mconca00.getMgenus004().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("idstia")+" "+mconca00.getMgenus004().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		
		//Validacion lista departamento
		if(mconca00.getMgenus005() != null && mconca00.getMgenus005().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("iddpia")+" "+mconca00.getMgenus005().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista idtcia Contribuyente
		if(mconca00.getMregcg004() != null && mconca00.getMregcg004().isRegegc() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("idtcia")+" "+mconca00.getMregcg004().getCoregc()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}	
		
		//Validacion lista idgete Funcionario Representante Legal
		if(mconca00.getMgente00() != null && mconca00.getMgente00().getRegite() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("idgete")+" "+mconca00.getMgente00().getCodite()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		//Validacion lista gencg representante
		if(mconca00.getMgencg00()!= null && mconca00.getMgencg00().isRegicg() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			idiomasHm.get("idocia")+" "+mconca00.getMgencg00().getCodicg()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		return validacion;
	}
	

	public void save() {
		try {
	
			//Se guardan los datos de la cola estandar 
			
			/*if(validarDatosRequeridos()){
				return;
			}*/
			
			colaEstandar();
			if(mconca00.getDivcia().isEmpty()){
				mconca00.setDivcia(null);
			}
			
			//Validacion si el Logo es requerido por Dinox
			if(mconca00.getLogcia()== null && permisoCampos.get("logcia").getReqcam().equals("S"))
			{
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, idiomasHm.get("logcia")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
				return;
			}
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					mconca00Service.save(mconca00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mconca00.toString(),mconca00.toStringId(),mconca00Cop.toString()):null);

					//getSeControlFactura().mconca00Service.save(mconca00,parametroVentCata("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mconca00.toString(),mconca00.toStringId(),mconca00Cop.toString()):null);
				}else return;
			} else {
				mconca00Service.save(mconca00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mconca00.toString(),mconca00.toStringId(),null):null);

				//getSeControlFactura().getMconca00Service().save(mconca00,parametroVentCata("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mconca00.toString(),mconca00.toStringId(),null):null);
			 }
			cargarDatos();
			mconca00Cop= (Mconca00) mconca00.clone();
			infoGuardada = true;
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
			mconca00 = (Mconca00) event.getComponent().getAttributes().get("mconca00");
			getSeControlFactura().getMconca00Service().removeMconca00(mconca00,parametroVentCata("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mconca00.toString(),mconca00.toStringId()):null);
			cargarDatos();
			getSeListas().actualizarListas(NombresListas.listMconca);
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

	public void uploadFirmaDigitalFile(FileUploadEvent event) {
		try {
			logoCompaFile = event.getFile();
			mconca00.setLogcia(UtilsJSF.convertUploadedFileToArrBytes(logoCompaFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void borrarFirmaDigitalFile() {
		try {			
			mconca00.setLogcia(null);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getFirmteStreamedContent() {
		try {
			if (mconca00 != null) {
				return UtilsJSF.convertBytesToStreamedContentImg(
						mconca00.getLogcia(), "jpg");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void llenarObjectCall(SelectEvent event) {

		try {
			if (objSeCall == null) {
				return;
			}
			getSeControlFactura().cargarParametros(true,null,mconca00.getIdccia());
			listMconca00ActualizarListas = new ArrayList<String>();
			String strClassLlama = objSeCall.getClass().getSimpleName();

			if (strClassLlama.equalsIgnoreCase("SeMtipre00")) {
				SeMtipre00 mtipre00 = (SeMtipre00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						mtipre00.getMtipre00().setMconca00((Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMpropr00")) {
				SeMpropr00 seMpropr00 = (SeMpropr00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMpropr00.getMpropr00().setMconca00((Mconca00) event.getObject());		
						RequestContext.getCurrentInstance().reset("formDetalle");	
					}
				}
				listMconca00ActualizarListas.add(mconca00.getCodcia());
				getSeListas().actualizarListasCompania(NombresListas.listMgenusMarcaComercial, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusLineaProductos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusSubLineaProductos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusUnidadMedida, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusCentroCostos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusClaseDocumentos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMidiom, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMtiptx00, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenus00Variantes, listMconca00ActualizarListas);				
			}
			if (strClassLlama.equalsIgnoreCase("SeMmesje00")) {
				SeMmesje00 mmesje00 = (SeMmesje00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						mmesje00.getMmesje00().setMconca00((Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMevent00")) {
				SeMevent00 mevent00 = (SeMevent00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						mevent00.getMevent00().setMconca00((Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}

			if (strClassLlama.equalsIgnoreCase("SeMparme00")) {		
				SeMparme00 mparme00 = (SeMparme00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						mparme00.getMparme00().setMconca00((Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				mparme00.autocompleteActualizarListas();
//				listMconca00ActualizarListas.add(mconca00.getCodcia());
//				seListas.actualizarListasCompania(NombresListas.listMtipre, listMconca00ActualizarListas);					
			}
			if (strClassLlama.equalsIgnoreCase("SeMgenus00")) {
				SeMgenus00 seMgenus00 = (SeMgenus00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMgenus00.getMgenus00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				listMconca00ActualizarListas.add(mconca00.getCodcia());
				getSeListas().actualizarListasCompania(NombresListas.listMtipre, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMidiom, listMconca00ActualizarListas);
			}
			if (strClassLlama.equalsIgnoreCase("SeMparca00")) {
				SeMparca00 seMparca00 = (SeMparca00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMparca00.getMparca00().setMconca00((Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				listMconca00ActualizarListas.add(mconca00.getCodcia());
				getSeListas().actualizarListasCompania(NombresListas.listMgenusDocumentos, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMidiom, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenteRepresentanteLegal, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusClaseDocumentos, listMconca00ActualizarListas);
			}
	
			if (strClassLlama.equalsIgnoreCase("SeMgente00")) {
				SeMgente00 seMgente00 = (SeMgente00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMgente00.getMgente00().setMconca00((Mconca00) event.getObject());
//						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				seMgente00.autocompleteActualizarListas();
				RequestContext.getCurrentInstance().reset("formDetalle");
				return;
			}
			if (strClassLlama.equalsIgnoreCase("seMgencg00")) {
				SeMgencg00 seMgencg00 = (SeMgencg00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMgencg00.getMgencg00().setMconca00((Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				seMgencg00.autocompleteActualizarListas();
			}

	
			if (strClassLlama.equalsIgnoreCase("seMidiom00")) {
				SeMidiom00 seMidiom00 = (SeMidiom00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMidiom00.getMidiom00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
			}
		
			//Maestro MPRECI01 nosorio-23102017 
			if (strClassLlama.equalsIgnoreCase("seMpreci00")) {
				SeMpreci00 seMpreci00 = (SeMpreci00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMpreci00.getMpreci00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				listMconca00ActualizarListas.add(mconca00.getCodcia());
				seListas.actualizarListasCompania(NombresListas.listMgenteCliente, listMconca00ActualizarListas);
				seListas.actualizarListasCompania(NombresListas.listMpropr, listMconca00ActualizarListas);
				seListas.actualizarListasCompania(NombresListas.listMgenusMoneda, listMconca00ActualizarListas);
				//seListas.actualizarListasCompania(NombresListas.listMgenusSegmentoAsesora, listMconca00ActualizarListas); NO VA
			}
			//MFUNFU nosorio 13122017
			if (strClassLlama.equalsIgnoreCase("seMfunfu00")) {
				SeMfunfu00 seMfunfu00 = (SeMfunfu00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMfunfu00.getMfunfu00().setMconca001( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
			}
			
			
			if (strClassLlama.equalsIgnoreCase("seMtiptx00")) {
				SeMtiptx00 seMtiptx00 = (SeMtiptx00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMtiptx00.getMtiptx00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				listMconca00ActualizarListas.add(mconca00.getCodcia());
				getSeListas().actualizarListasCompania(NombresListas.listMgencgCodTransaccion, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenusClaseDocumentos, listMconca00ActualizarListas);
			}
				
			if (strClassLlama.equalsIgnoreCase("seMestad00")) {
				SeMestad00 seMestad00 = (SeMestad00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMestad00.getMestad00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				seMestad00.actualizarListasCompania(mconca00.getCodcia());
			}	
			if (strClassLlama.equalsIgnoreCase("seMteste00")) {
				SeMteste00 seMteste00 = (SeMteste00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMteste00.getMteste00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				listMconca00ActualizarListas.add(mconca00.getCodcia());
				getSeListas().actualizarListasCompania(NombresListas.listMtiptxEstado, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMestad00, listMconca00ActualizarListas);
			}
			if (strClassLlama.equalsIgnoreCase("seMaccio00")) {
				SeMaccio00 seMaccio00 = (SeMaccio00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMaccio00.getMaccio00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
				listMconca00ActualizarListas.add(mconca00.getCodcia());
				getSeListas().actualizarListasCompania(NombresListas.listMtiptx00, listMconca00ActualizarListas);
				getSeListas().actualizarListasCompania(NombresListas.listMgenus00Evento, listMconca00ActualizarListas);
			}
			//MFUNFU nosorio 08052018
			if (strClassLlama.equalsIgnoreCase("seMrafra00")) {
				SeMrafra00 seMrafra00 = (SeMrafra00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMrafra00.getMrafra00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
			}
			
			//nosorio 21052018
			if (strClassLlama.equalsIgnoreCase("seMswfsw00")) {
				SeMswfsw00 seMswfsw00 = (SeMswfsw00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mconca00")) {
					if (nombreLista.equalsIgnoreCase("companias")) {
						seMswfsw00.getMswfsw00().setMconca00( (Mconca00) event.getObject());
						RequestContext.getCurrentInstance().reset("formDetalle");						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}

		 update=null;
	}
	
	
	public void evtCloseDialogMconca(CloseEvent event) {
		try {
			update = null;
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
        	mconca00.setMgenus001(mconca00Cop.getMgenus001());
        	mconca00.setMgenus002(mconca00Cop.getMgenus002());
        	mconca00.setMgenus003(mconca00Cop.getMgenus003());
        	mconca00.setMgente00(mconca00Cop.getMgente00());
        	}
        	super.evtCloseDialog(event);
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
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Metodo que autocompleta todas las listas de Mconca00
	public List<Mconca00> completeMconca(String query) {		

        List<Mconca00> results = new ArrayList<Mconca00>();   
        List<Mconca00> listMconca00 = getSeListas().getListMconca00Pal();

        for (Mconca00 obj : listMconca00) {        	
        	if(obj.getCodcia().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	

	// Metodos de acceso

	public List<Mconca00> getListMconca00() {
		return listMconca00;
	}

	public void setListMconca00(List<Mconca00> listMconca00) {
		this.listMconca00 = listMconca00;
	}

	public void register() {
		listMconca00 = getSeControlFactura().getMconca00Service().listMconca00ByCriteria(new Mconca00());
	}

	public void clearFields() {
		this.mconca00 = new Mconca00();
	}
	
	public Mconca00 getMconca00() {
		return mconca00;
	}

	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	
	public List<Mconca00> getFilteredMconca00() {
		return filteredMconca00;
	}

	public void setFilteredMconca00(List<Mconca00> filteredMconca00) {
		this.filteredMconca00 = filteredMconca00;
	}

	public UploadedFile getLogoCompaFile() {
		return logoCompaFile;
	}

	public void setLogoCompaFile(UploadedFile logoCompaFile) {
		this.logoCompaFile = logoCompaFile;
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
	

//	public List<Mconca00> getListMconca00Pal() {
//		return listMconca00Pal;
//	}
//
//	public void setListMconca00Pal(List<Mconca00> listMconca00Pal) {
//		this.listMconca00Pal = listMconca00Pal;
//	}
//
//	public List<Mconca00> getFilteredMconca00Pal() {
//		return filteredMconca00Pal;
//	}
//
//	public void setFilteredMconca00Pal(List<Mconca00> filteredMconca00Pal) {
//		this.filteredMconca00Pal = filteredMconca00Pal;
//	}

	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}

	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
	}

	
	public List<String> getListMconca00ActualizarListas() {
		return listMconca00ActualizarListas;
	}

	public void setListMconca00ActualizarListas(
			List<String> listMconca00ActualizarListas) {
		this.listMconca00ActualizarListas = listMconca00ActualizarListas;
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

	public IMconca00Service getMconca00Service() {
		return mconca00Service;
	}

	public void setMconca00Service(IMconca00Service mconca00Service) {
		this.mconca00Service = mconca00Service;
	}

}