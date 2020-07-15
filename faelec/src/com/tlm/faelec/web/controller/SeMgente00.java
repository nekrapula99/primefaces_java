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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMcotes00Service;
import com.tlm.faelec.service.maestros.IMgente00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
//import com.tlm.faelec.model.entities.Mactga00;
//import com.tlm.faelecEntities.model.entities.Maczon00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
//import com.tlm.faelecEntities.model.entities.Mcpcpc00;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
//import com.tlm.faelecEntities.model.entities.Mgentc00;
//import com.tlm.faelecEntities.model.entities.Mgentd00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Taudit00;
//import com.tlm.faelecEntities.model.entities.Mgetcu00;
//import com.tlm.faelecEntities.model.entities.Mgetma00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
//import com.tlm.faelecEntities.model.entities.Mzonza00;

@Controller
@ManagedBean
@SessionScoped
public class SeMgente00 extends Control implements Serializable{
	private static final long serialVersionUID = -3448834135859248411L;
	
	//variables del managed bean
	@ManagedProperty("#{mgente00Service}")
	private IMgente00Service mgente00Service;
	
	@ManagedProperty("#{mcotes00Service}")
	private IMcotes00Service mcotes00Service;	
	
//	@ManagedProperty("#{seMgetma00}")
//	private SeMgetma00 seMgetma00;
		
	private Mgente00 mgente00;
	//Objeto utilizado para la lista de Asesora gerente
	private Mgente00 mgente002;
	private Mgente00 mgente003;
	//private Mgentc00 mgentc00;
	//private Mgentd00 mgentd00;
	//private Mgetma00 mgetma00;
	private List<Mgente00> listMgente00;
	private List<Mgente00> listMgente; //Lista usada en el formulario de parametros
	private List<Mgente00> filteredMgente00;
	
	private List<Mcotes00> listMcotes00;
	private List<Mcotes00> filteredMcotes00;
	
	private UploadedFile logoCompaFile;
	private Mgente00 mgente00Cop;
	//	Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	private String[] pestanas;
	private boolean tabCategorias;
	private boolean tabDirecciones;
	private boolean tabCupos;
	private boolean tabMarcaCatalogo;
	//Campos
	private boolean txtPrimerNombre;
	private boolean txtSegundoNombre;
	private boolean txtPrimerApellido;
	private boolean txtSegundoApellido;
	private boolean txtDescripcion;
	private boolean infoGuardada;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	
	private String accion;
	private String titulo;
	
	private String tiPersona;
	private boolean perNatural;
	private boolean perJuridica;
	private boolean flagLista;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	private int tabView=0;
	
	private String valorConcatenado;
	
	private InputText inputTextIdcmteColumn;
	private InputText inputTextCodtteColumn;
	private InputText inputTextIdnjteColumn;
	private InputText inputTextIddpteColumn;
	private InputText inputTextIdciteColumn;
	private InputText inputTextIdovteColumn;
	private InputText inputTextIdcdteColumn;
	private InputText inputTextCoditeColumn; 
	private InputText inputTextIdtcteColumn;	
	private InputTextarea inputTextDctateColumn;
	private InputTextarea inputTextDireteColumn;
	private InputText inputTextAliateColumn;
	private InputTextarea inputTextPrnmteColumn;
	private InputTextarea inputTextPrapteColumn;
		
	private Taudit00 taudit00;

	@PostConstruct
	public void init(){
		try {
			super.init("MGENTE00");
			mgente00 = new Mgente00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			cargarDatos();
			valorConcatenado="";
			tabCategorias=false;
			tabCupos=false;
			tabDirecciones=false;
			tabMarcaCatalogo=false;
			infoGuardada = false;
			//Campos
			txtPrimerNombre = false;
			txtSegundoNombre = false;
			txtPrimerApellido = false;
			txtSegundoApellido = false;
			txtDescripcion = false;
			
			perJuridica=true;
			perNatural=true;
			flagLista = false;
			reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			seListas.actualizarListas(NombresListas.listMconca);
			seListas.actualizarListas(NombresListas.listMcampa);
			seListas.actualizarListas(NombresListas.listMgenusTident);
			seListas.actualizarListas(NombresListas.listMgenusDepartamento);
			seListas.actualizarListas(NombresListas.listMgenusCiudad);
			seListas.actualizarListas(NombresListas.listMgenusPaises);
			seListas.actualizarListas(NombresListas.listMgenusMarcaCatalogo);
			seListas.actualizarListas(NombresListas.listMgenusTipoNaturaleza);
			seListas.actualizarListas(NombresListas.listMtipreTerceros);
			seListas.actualizarListas(NombresListas.listMzonza);
			seListas.actualizarListas(NombresListas.listMgenteCliente);
			seListas.actualizarListas(NombresListas.listMgenusTipoAsesora);
			seListas.actualizarListas(NombresListas.listMgenusSegmentoAsesora);
			seListas.actualizarListas(NombresListas.listMgenteGerenteAsesora);
			seListas.actualizarListas(NombresListas.listMgenusMarcaCatalogo);
			seListas.actualizarListas(NombresListas.listMregcg00);
			seListas.actualizarListas(NombresListas.listMgenusMedioPago);
			seListas.actualizarListas(NombresListas.listMgenus00MonedaCot);
			seListas.actualizarListas(NombresListas.listMgenusIncoterms);
			seListas.actualizarListas(NombresListas.listMgenusOrganiVentas);
			seListas.actualizarListas(NombresListas.listMgenusCanalDistri);
			seListas.actualizarListas(NombresListas.listMgencgCompania);
			seListas.actualizarListas(NombresListas.listMgenusBarrioSector);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//metodos del managed bean 
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			tabView=0;
			valorConcatenado="";
			mgente00 = new Mgente00();
			mgente00.setRegite(true);
			seListas.actualizarListas(NombresListas.listMconca);
			
			
			infoGuardada = false;
			mostrarPestana();
			mostrarCampos();
			validarFormatoFecha();		
			reiniciarListasMaestro();
			//Pesta�as ocultas por defecto 
			tabCategorias=false; 
			tabDirecciones=false;
			tabCupos=false;
			tabMarcaCatalogo=false;
			//Campos ocultos por defecto 
			txtPrimerNombre = false;
			txtSegundoNombre = false;
			txtPrimerApellido = false;
		    txtSegundoApellido = false;
			txtDescripcion = false;	
			
			perJuridica=false;
			perNatural=false;
			mgente00.setPrnmte("");
			mgente00.setPrapte("");
			mgente00.setDctate(" ");
			
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
			seListas.actualizarListasCompania(NombresListas.listMconca,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMcampa,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusTident,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusDepartamento,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusCiudad,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusPaises,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusMarcaCatalogo,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusTipoNaturaleza,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMtipreTerceros,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMzonza,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusTipoAsesora,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusSegmentoAsesora,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusMarcaCatalogo,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMregcg00,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusMedioPago,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenus00MonedaCot,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusIncoterms,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusOrganiVentas,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusCanalDistri,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgencgCompania,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenteCliente,listMconca00ActualizarListas);
			seListas.actualizarListasCompania(NombresListas.listMgenusBarrioSector,listMconca00ActualizarListas);

			validarFormatoFecha();
			//RequestContext.getCurrentInstance().update("formMgentc:fechtcColumn");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Se dispara este metodo al ejecutarse la accion con el autocompletar
	public void autocompleteActualizarListas (){
		try{
			String codigoCompania = null;
			if(mgente00.getMconca00() != null){
			codigoCompania= mgente00.getMconca00().getCodcia();
			//Se cargan los parametros deacuerdo a la compa�ia seleccionada
			getSeControlFactura().cargarParametros(true,null,mgente00.getMconca00().getIdccia());
			actualizarListasCompania(codigoCompania);
			}else{
				reiniciarListasMaestro();
				getSeControlFactura().cargarParametros(true,null,null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void cargarDatos(){
		try{
		    mgente00 = new Mgente00();
		    mgente00.setMtipre00(new Mtipre00());
			listMgente00 =  mgente00Service.listMgente00ByCryteria(mgente00, getSeControlFactura().getCompaniasUsu()); 
			//UtilsJSF.resetDTable("formTable:dTable");
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	//Metodo llamado desde el formulario de parametros
	public void obtenerListaMgente002(){	
		String compania;
		Mgente00 mgenteAux = null;
		//Lista necesria para filtrar las listas de valores por compa�ia
		List<String> listMconca00ActualizarListas;
		
		FacesContext context = FacesContext.getCurrentInstance();
		String codtte = (String) UIComponent.getCurrentComponent(context).getAttributes().get("codtte");

		if(codtte!=null)
		{
		    mgenteAux = new Mgente00();
			mgenteAux.setMtipre00(new Mtipre00());
			mgenteAux.getMtipre00().setTipore(codtte);
			mgenteAux.setRegite(true);
		}
		
		compania = (String) UIComponent.getCurrentComponent(context).getAttributes().get("compania");
		if(compania != null){
		listMconca00ActualizarListas = new ArrayList<String>();
		listMconca00ActualizarListas.add(compania);	
		}else{
			listMconca00ActualizarListas = null;
		}
		
		if(mgenteAux != null){
			listMgente =  mgente00Service.listMgente00ByCodtte(mgenteAux, listMconca00ActualizarListas); 
		}
	}
	
	public void accionModificar(SelectEvent event) {
		try {
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			//Se cargan los parametros deacuerdo a la compa�ia seleccionada	y se actualizan las listas
			if(mgente00.getMconca00()!= null){
				getSeControlFactura().cargarParametros(true,null,mgente00.getMconca00().getIdccia());
			actualizarListasCompania(mgente00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();
				getSeControlFactura().cargarParametros(true,null,null);
				validarFormatoFecha();
			}
			
			Mgente00 mgente00Rs = new Mgente00();
		    mgente00Rs = mgente00Service.cargarDetalles(mgente00.getIdtrte());		    
		    infoGuardada = false;
		    seListas.actualizarListas(NombresListas.listMconca);
			mostrarPestana();
			mostrarCampos();
			mgente00Cop= (Mgente00) mgente00.clone();
//			Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgente00.getMconca00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgente00.getMtipre00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgente00.getMgenus001(), mgente00.getMgenus001().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgente00.getMgenus002(), mgente00.getMgenus002().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgente00.getMgenus003(), mgente00.getMgenus003().getMtipre00().getTipore());
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgente00.getMgencg00(), mgente00.getMgencg00().getMtipre00().getTipore());

			}
			//Se validan las listas de valores de los detalles tabla MGETMA00
			//validarListasMgetma00();
			//se Obtiene la lista de marcas Catalogo y se poponen si las marcas catalogo
//			llenarMarcaCatalogo();
			/* Se iguala la lista listMgetma00 a mgente00.getMgetma00s() ya que esta viene llena
			 * esto se realiza para que aparesca toda la lista en check true 
			 */			
			//listMgetma00Aux = mgente00.getMgetma00s();
			
			/*
			 * Esta es la descripion que se mostrara en todas la pesta�as al lado del codigo
			 */
			concatenar();
			tabView=0;

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	public void action()
	{
		mostrarPestana();
	}
	
	
	/*Metodo que se encarga de validar que los registros de las listas esten habilitados
	 * en caso de estar deshabilitado algun registro devuelve true
	 */
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
		
		if(mgente00.getMtipre00() != null && mgente00.getMtipre00().getRegitr() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("codtte")+" "+mgente00.getMtipre00().getTipore()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgente00.getMconca00() != null && mgente00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idcmte")+" "+mgente00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgente00.getMgenus001() != null && mgente00.getMgenus001().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idtdte")+" "+mgente00.getMgenus001().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgente00.getMgenus002() != null && mgente00.getMgenus002().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("iddpte")+" "+mgente00.getMgenus002().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgente00.getMgenus003() != null && mgente00.getMgenus003().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idcite")+" "+mgente00.getMgenus003().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgente00.getMregcg005() != null && mgente00.getMregcg005().isRegegc() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idtcte")+" "+mgente00.getMregcg005().getCoregc()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		} 
		if(mgente00.getMgenus006() != null && mgente00.getMgenus006().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idmpte")+" "+mgente00.getMgenus006().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgente00.getMgenus007() != null && mgente00.getMgenus007().getRegius() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idmote")+" "+mgente00.getMgenus007().getCodius()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgente00.getMgencg00() != null && mgente00.getMgencg00().isRegicg() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idopte")+" "+mgente00.getMgencg00().getCodicg()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}	
		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		
		//Codigo tercero CODTTE
		if(Utils.isEmptyOrNull(mgente00.getCodite()) && permisoCampos.get("codite").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("codite")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextCoditeColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextCoditeColumn.getClientId());
			validacion = true;
		}else{
				inputTextCoditeColumn.setStyle("border-color: #9a9a9a;");			 
				RequestContext.getCurrentInstance().update(inputTextCoditeColumn.getClientId());	
		}
		
		//Primer nombre tercero 
		if(Utils.isEmptyOrNull(mgente00.getPrnmte()) && permisoCampos.get("prnmte").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("prnmte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextPrnmteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextPrnmteColumn.getClientId());
			validacion = true;
		}else{
			inputTextPrnmteColumn.setStyle("border-color: #9a9a9a;");			 
				RequestContext.getCurrentInstance().update(inputTextPrnmteColumn.getClientId());	
		}
		
		//Primer apellido
		if(Utils.isEmptyOrNull(mgente00.getPrapte()) && permisoCampos.get("prapte").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("prapte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextPrapteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextPrapteColumn.getClientId());
			validacion = true;
		}else{
			inputTextPrapteColumn.setStyle("border-color: #9a9a9a;");			 
				RequestContext.getCurrentInstance().update(inputTextPrapteColumn.getClientId());
		}
		
		//DESCRIPCION
		if(Utils.isEmptyOrNull(mgente00.getDctate()) && permisoCampos.get("dctate").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("dctate")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextDctateColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextDctateColumn.getClientId());
			validacion = true;
		}else{
			inputTextDctateColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextDctateColumn.getClientId());	
		}
		
		//Compa�ia se valida por dinox si es requerida
		if(Utils.isEmptyOrNull(mgente00.getDirete())&& permisoCampos.get("direte").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("direte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextDireteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextDireteColumn.getClientId());
			validacion = true;
		}else{
			inputTextDireteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextDireteColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(mgente00.getAliate()) && permisoCampos.get("aliate").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("aliate")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextAliateColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextAliateColumn.getClientId());
			validacion = true;
		}else{
			inputTextAliateColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextAliateColumn.getClientId());	
		}
		
		if(Utils.isEmptyOrNull(mgente00.getMconca00()) && permisoCampos.get("idcmte").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcmte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcmteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcmteColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcmteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcmteColumn.getClientId());	
		}
		if(Utils.isEmptyOrNull(mgente00.getMtipre00()) && permisoCampos.get("codtte").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("codtte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextCodtteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextCodtteColumn.getClientId());
			validacion = true;
		}else{
			inputTextCodtteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextCodtteColumn.getClientId());
		}
		if(Utils.isEmptyOrNull(mgente00.getMgenus004()) && permisoCampos.get("idnjte").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idnjte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdnjteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdnjteColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdnjteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdnjteColumn.getClientId());
		}
		if(Utils.isEmptyOrNull(mgente00.getMgenus002()) && permisoCampos.get("iddpte").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("iddpte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIddpteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIddpteColumn.getClientId());
			validacion = true;
		}else{
			inputTextIddpteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIddpteColumn.getClientId());
		}
		if(Utils.isEmptyOrNull(mgente00.getMgenus003()) && permisoCampos.get("idcite").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcite")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdciteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdciteColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdciteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdciteColumn.getClientId());
		}
		if(Utils.isEmptyOrNull(mgente00.getMgenus009()) && permisoCampos.get("idovte").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idovte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdovteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdovteColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdovteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdovteColumn.getClientId());
		}
		if(Utils.isEmptyOrNull(mgente00.getMgenus010()) && permisoCampos.get("idcdte").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idcdte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdcdteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdcdteColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdcdteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdcdteColumn.getClientId());
		}
		
		if(Utils.isEmptyOrNull(mgente00.getMregcg005()) && permisoCampos.get("idtcte").getReqcam().equals("S")){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("idtcte")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextIdtcteColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextIdtcteColumn.getClientId());
			validacion = true;
		}else{
			inputTextIdtcteColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextIdtcteColumn.getClientId());
		}
		
		return validacion;
	}
	
	public void save(){
		try {
			if(mgente00.getPrnmte()==null || mgente00.getPrapte()==null ){
				
				mgente00.setPrnmte("");
				mgente00.setPrapte("");
			}else{
				if(mgente00.getDctate()==null){
					mgente00.setDctate("");
				}
				
			}
						
			if (validarDatosRequeridos()) {
				return;
			}
			
			//if(!validarDatos()){
				//return;
			//}
			
			// Si es persona Natural se concatenan nombres y apellidos y se guardan en la descripcion, si es juridica se limpian los datos de nombres
			/*if(!mgente00.getMgenus004().getCm1aus().equals(Utils.paramsRb.getString("mgenus_cm1aus_personaNatural"))){
			   mgente00.setDctate(mgente00.getPrnmte()+" "+mgente00.getSenmte()+" "+mgente00.getPrapte()+" "+mgente00.getSeapte());
			}*/
			colaEstandar();

			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
					mgente00Service.save(mgente00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mgente00.toString(),mgente00.toStringId(),mgente00Cop.toString()):null);
				}else return;
			} else {
				mgente00Service.save(mgente00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mgente00.toString(),mgente00.toStringId(),null):null);
			}
			seListas.actualizarListas(validarNombreLista(mgente00.getMtipre00().getTipore()));	
			cargarDatos();
			infoGuardada = true;
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
	
	private void colaEstandar(){
		try {
			mgente00.setUserte(getSeControlFactura().codusu);
			mgente00.setPrgmte("Generico Terceros " + getClass().getName());
			mgente00.setFeacte(new Date());
			mgente00.setMaqute(getSeControlFactura().ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarCampos()
	{
		if(mgente00.getMgenus004() != null){
			if(mgente00.getMgenus004().getCm1aus().equals(Utils.paramsRb.getString("mgenus_cm1aus_personaJuridica"))){
				//mgente00.setPrnmte(null);
				//mgente00.setSenmte(null);
				//mgente00.setPrapte(null);
				//mgente00.setSeapte(null);		
				txtDescripcion = true;
			}else{
				txtDescripcion = false;
			}
			
			if(mgente00.getMgenus004().getCm1aus().equals(Utils.paramsRb.getString("mgenus_cm1aus_personaNatural"))){
				//mgente00.setDctate(null);
				txtPrimerNombre=true;
				txtSegundoNombre=true;
				txtPrimerApellido=true;
				txtSegundoApellido=true;
			}else{
				txtPrimerNombre=false;
				txtSegundoNombre=false;
				txtPrimerApellido=false;
				txtSegundoApellido=false;				
			}
		}else{
			txtDescripcion = false;
			txtPrimerNombre=false;
			txtSegundoNombre=false;
			txtPrimerApellido=false;
			txtSegundoApellido=false;				
		}
	}
	
	public void handleSelect(AjaxBehaviorEvent event)
	{
		mostrarPestana();
	}
	
	public void mostrarDatos(AjaxBehaviorEvent event)
	{
		mostrarCampos();
		limpiarDatosConcatenados();
	}
	
	public void mostrarPestana (){
		tabCategorias = false;
		tabDirecciones = false;
		tabCupos = false;
		tabMarcaCatalogo = false;
		if(mgente00.getMtipre00()!=null){
//			 if(mgente00.getMtipre00().getTipore().equals("Z1"))
//			 {
			   pestanas=mgente00.getMtipre00().getInpvtr().split(","); 
			   for (int i=0; i < pestanas.length;i++){
				   if(pestanas[i].equals("4"))
				   {
					   tabCategorias=true;
				   }
				   if(pestanas[i].equals("5"))
				   {
					   tabDirecciones=true;
				   }
				   if(pestanas[i].equals("6"))
				   {
					   tabCupos=true;
				   }
				   if(pestanas[i].equals("7"))
				   {
					   tabMarcaCatalogo=true;
				   }
			   }
//			 }else {
//				 tabCategorias=false; 
//				 tabDirecciones=false;
//				 tabCupos=false;
//				 tabMarcaCatalogo=false;
//			 }		  
		 }
		 RequestContext.getCurrentInstance().update("formDetalle:tabView");
	}
	
		
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mgente00 = (Mgente00) event.getComponent().getAttributes().get("mgente00");
			mgente00Service.remove(mgente00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,mgente00.toString(),mgente00.toStringId()):null);
			seListas.actualizarListas(validarNombreLista(mgente00.getMtipre00().getTipore()));
			cargarDatos();
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
	
	public String tipoNaturaleza(String dato){
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tiPersona;
	}
		

	public void llenarObjectCall(SelectEvent event) {
		
		try
		{
			if (objSeCall == null) {
				return;
			}
			String strClassLlama = objSeCall.getClass().getSimpleName();
			// Mconca
			
			if (strClassLlama.equalsIgnoreCase("SeMconca00")) {
				SeMconca00 mconca00 = (SeMconca00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("RepLegal")) {
						mconca00.getMconca00().setMgente00((Mgente00) event.getObject());						
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMparme00")) {
				SeMparme00 semparme00 = (SeMparme00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					semparme00.setMgente00((Mgente00) event.getObject());
					semparme00.getMparme00().setVapame(semparme00.getMgente00().getCodite());
					semparme00.getMparme00().setVapamh(semparme00.getMgente00().getCodite() +" - "+semparme00.getMgente00().getDctate());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMparca00")) {
				SeMparca00 semparca00 = (SeMparca00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("funci1")) {
						semparca00.getMparca00().setMgente001((Mgente00) event.getObject());						
						RequestContext.getCurrentInstance().reset("formDetalle");
	//					RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00"
					}else if (nombreLista.equalsIgnoreCase("funci2")) {
						semparca00.getMparca00().setMgente002((Mgente00) event.getObject());						
						RequestContext.getCurrentInstance().reset("formDetalle");
	//					RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00"
					}else if (nombreLista.equalsIgnoreCase("funci3")) {
						semparca00.getMparca00().setMgente003((Mgente00) event.getObject());						
						RequestContext.getCurrentInstance().reset("formDetalle");
	//					RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00"
					}
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMpreci00")) {
				SeMpreci00 mpreci00 = (SeMpreci00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("cliente")) {
						mpreci00.getMpreci00().setMgente00((Mgente00) event.getObject());						
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMcotes00")) {
				SeMcotes00 mcotes00 = (SeMcotes00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("terceroPpl")) {
						mcotes00.getMcotes00().setMgente001((Mgente00) event.getObject());						
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			if (strClassLlama.equalsIgnoreCase("SeMcotos00")) {
				SeMcotos00 mcotos00 = (SeMcotos00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("terceroAdici")) {
						mcotos00.getMcotos00().setMgente001((Mgente00) event.getObject());						
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			
			if (strClassLlama.equalsIgnoreCase("seTqigqg00")) {
				SeTqigqg00 seTqigqg00 = (SeTqigqg00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("sigla")) {
						seTqigqg00.getTqigqg00().setMgente00((Mgente00) event.getObject());
							if(mgente00.getMgenus004() != null){
								if(mgente00.getMgenus004().getCm1aus().equals(Utils.paramsRb.getString("mgenus_cm1aus_personaJuridica"))){
									perJuridica=true;
								}else{
									perJuridica=false;	
								}
								if(mgente00.getMgenus004().getCm1aus().equals(Utils.paramsRb.getString("mgenus_cm1aus_personaNatural"))){
									perNatural=true;
								}else{
									perNatural=false;	
								}	
							}
							RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle");
	
					}
				}
			}
			
			//DESCOMENTAR
			if (strClassLlama.equalsIgnoreCase("seTqdfqf00")) {
				SeTqdfqf00 seTqdfqf00 = (SeTqdfqf00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("cliente")) {
						seTqdfqf00.getTqdfqf00().setMgente00((Mgente00) event.getObject());
						cargarListaContactosPorGrupo(seTqdfqf00.getTqdfqf00().getMgente00());
						flagLista = true;
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			//21052018
			if (strClassLlama.equalsIgnoreCase("seMswfsw00")) {
				SeMswfsw00 seMswfsw00 = (SeMswfsw00) objSeCall;
				if (nombreTabla.equalsIgnoreCase("mgente00")) {
					if (nombreLista.equalsIgnoreCase("cliente")) {
						seMswfsw00.getMswfsw00().setMgente00((Mgente00) event.getObject());
						cargarListaContactosPorGrupo(seMswfsw00.getMswfsw00().getMgente00());
						flagLista = true;
						RequestContext.getCurrentInstance().reset("formDetalle");
					}
				}
			}
			
			
			
		}
		catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
	}
	
	
	//Maestro MPRECI01 nosorio-24102017
	public void cargarListaContactosPorGrupo(Mgente00 mgente00) {
		try{
			
			Mcotes00 mcotesAux = new Mcotes00();
			mcotesAux.setRegtes(true);
			listMcotes00 = mcotes00Service.listMcotes00Criteria(mcotesAux, getSeControlFactura().getCompaniasUsu());
			List<Mcotes00> listMcotes00Aux  = new ArrayList<Mcotes00>();
			for(Mcotes00 obj : listMcotes00){
				if(obj.getMgente001()!=null){
					if(obj.getMgente001().getIdtrte() == (mgente00.getIdtrte())){
						listMcotes00Aux.add(obj);
					}	
				}	
			}
			
			setListMcotes00(listMcotes00Aux);
			//flagLista = false;
			RequestContext.getCurrentInstance().reset("formDetalle");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Metodo que autocompleta todas las listas de Mgente00
	public List<Mgente00> completeMgente(String query) {		

		FacesContext context = FacesContext.getCurrentInstance();
		String codtte = (String) UIComponent.getCurrentComponent(context).getAttributes().get("codtte");
		
		//Esta variable se utiliza cuando se busca desde el maestro de parametros
		String clase = (String) UIComponent.getCurrentComponent(context).getAttributes().get("clase");
			if(clase!=null){
				 if(clase.equalsIgnoreCase("Parametro")){
					 codtte=null;
				 }
			}
		
        List<Mgente00> results = new ArrayList<Mgente00>();   
        List<Mgente00> listMgente00Aux = new ArrayList<Mgente00>();
        if (codtte!=null){
	        if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_representanteLegal"))){
	        	listMgente00Aux = seListas.getListMgenteReplegal();
	        }else if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_asesoraGerente"))){
	        	listMgente00Aux = seListas.getListMgenteGerenteAsesora();
	        }else if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_Asesora"))){
	        	listMgente00Aux = seListas.getListMgenteAsesora();
	        }else if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_cliente"))){
	        	listMgente00Aux = seListas.getListMgenteCliente();
	        }else if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_proveedor"))){
	        	listMgente00Aux = seListas.getListMgenteCodtte();
	        }
        }
        
        //Condicion utilizada cuando se realiza una busqueda desde el maestro de parametros
        if(clase!=null){
	        if(clase.equalsIgnoreCase("Parametro")){
	        	listMgente00Aux = listMgente00;
	        }
        }
        

        for (Mgente00 obj : listMgente00Aux) {        	
        	if(obj.getCodite().toLowerCase().contains(query.toLowerCase())){
        		results.add(obj);        		
    		}			
		}
        return results;
    }
	
	//Este metodo valida el nombre de la lista que se va a actualizar guardar o eliminar
	private NombresListas validarNombreLista(String tipoRegistro){
		NombresListas nombre = null;
		if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_representanteLegal"))){
			nombre=NombresListas.listMgenteRepresentanteLegal;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_asesoraGerente"))){
			nombre=NombresListas.listMgenteGerenteAsesora;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_Asesora"))){
			nombre=NombresListas.listMgenteAsesora;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_cliente"))){
			nombre=NombresListas.listMgenteCliente;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_proveedor"))){
			nombre=NombresListas.listMgenteCodtte;
		}
			return nombre;
	}	
	
	public void uploadFirmaDigitalFile(FileUploadEvent event) {
		try {
			logoCompaFile = event.getFile();
			mgente00.setLogtte(UtilsJSF.convertUploadedFileToArrBytes(logoCompaFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void borrarFirmaDigitalFile() {
		try {			
			mgente00.setLogtte(null);
//			RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalleLogo");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getFirmteStreamedContent() {
		try {
			if (mgente00 != null) {
				return UtilsJSF.convertBytesToStreamedContentImg(
						mgente00.getLogtte(), "jpg");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	public void evtCloseDlgMgente00(CloseEvent event) {
		try {
			update = null;
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
    		mgente00.setMconca00(mgente00Cop.getMconca00());
    		mgente00.setMtipre00(mgente00Cop.getMtipre00());
        	mgente00.setMgenus001(mgente00Cop.getMgenus001());
        	mgente00.setMgenus002(mgente00Cop.getMgenus002());
        	mgente00.setMgenus003(mgente00Cop.getMgenus003());
        	mgente00.setMgenus004(mgente00Cop.getMgenus004());
        	}
        	evtCloseDialog(event);
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	/*
	 * De acuerdo al tipo de naturaleza se limpian los datos 
	 */
	public void limpiarDatosConcatenados(){
		try {
		   mgente00.setDctate(null);
		   mgente00.setPrnmte(null);
		   mgente00.setSenmte(null);
		   mgente00.setPrapte(null);
		   mgente00.setSeapte(null);
		   valorConcatenado="";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Concatena los que seran mostrados en la cabecera de los demas tabs
	public void concatenar(){
		try {
			valorConcatenado="";
			if(!Utils.isEmptyOrNull(mgente00.getDctate())){
				valorConcatenado = valorConcatenado + " " +mgente00.getDctate();
			}
		    if(!Utils.isEmptyOrNull(mgente00.getPrnmte())){
			   valorConcatenado = valorConcatenado + " " + mgente00.getPrnmte();
			}
			if(!Utils.isEmptyOrNull(mgente00.getSenmte())){
				valorConcatenado = valorConcatenado + " " + mgente00.getSenmte();
			}
			if(!Utils.isEmptyOrNull(mgente00.getPrapte())){
				valorConcatenado = valorConcatenado + " " + mgente00.getPrapte();
			}
			if(!Utils.isEmptyOrNull(mgente00.getSeapte())){
				valorConcatenado = valorConcatenado + " " +mgente00.getSeapte();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//	metodos de acceso
	public Mgente00 getMgente00() {
		return mgente00;
	}

	public void setMgente00(Mgente00 mgente00) {
		this.mgente00 = mgente00;
	}

	public IMgente00Service getMgente00Service() {
		return mgente00Service;
	}

	public void setMgente00Service(IMgente00Service mgente00Service) {
		this.mgente00Service = mgente00Service;
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

	
	public List<Mgente00> getListMgente00() {
		return listMgente00;
	}


	public void setListMgente00(List<Mgente00> listMgente00) {
		this.listMgente00 = listMgente00;
	}


	public List<Mgente00> getFilteredMgente00() {
		return filteredMgente00;
	}


	public void setFilteredMgente00(List<Mgente00> filteredMgente00) {
		this.filteredMgente00 = filteredMgente00;
	}



	public UploadedFile getLogoCompaFile() {
		return logoCompaFile;
	}


	public void setLogoCompaFile(UploadedFile logoCompaFile) {
		this.logoCompaFile = logoCompaFile;
	}


	public Mgente00 getMgente00Cop() {
		return mgente00Cop;
	}


	public void setMgente00Cop(Mgente00 mgente00Cop) {
		this.mgente00Cop = mgente00Cop;
	}


	public String[] getPestanas() {
		return pestanas;
	}


	public void setPestanas(String[] pestanas) {
		this.pestanas = pestanas;
	}


	public boolean isTabCategorias() {
		return tabCategorias;
	}


	public void setTabCategorias(boolean tabCategorias) {
		this.tabCategorias = tabCategorias;
	}


	public boolean isTabDirecciones() {
		return tabDirecciones;
	}


	public void setTabDirecciones(boolean tabDirecciones) {
		this.tabDirecciones = tabDirecciones;
	}


	public boolean isTabCupos() {
		return tabCupos;
	}


	public void setTabCupos(boolean tabCupos) {
		this.tabCupos = tabCupos;
	}


	public boolean isTxtPrimerNombre() {
		return txtPrimerNombre;
	}


	public void setTxtPrimerNombre(boolean txtPrimerNombre) {
		this.txtPrimerNombre = txtPrimerNombre;
	}


	public boolean isTxtSegundoNombre() {
		return txtSegundoNombre;
	}


	public void setTxtSegundoNombre(boolean txtSegundoNombre) {
		this.txtSegundoNombre = txtSegundoNombre;
	}


	public boolean isTxtPrimerApellido() {
		return txtPrimerApellido;
	}


	public void setTxtPrimerApellido(boolean txtPrimerApellido) {
		this.txtPrimerApellido = txtPrimerApellido;
	}


	public boolean isTxtSegundoApellido() {
		return txtSegundoApellido;
	}


	public void setTxtSegundoApellido(boolean txtSegundoApellido) {
		this.txtSegundoApellido = txtSegundoApellido;
	}


	public boolean isTxtDescripcion() {
		return txtDescripcion;
	}


	public void setTxtDescripcion(boolean txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}


	public Mgente00 getMgente002() {
		return mgente002;
	}


	public void setMgente002(Mgente00 mgente002) {
		this.mgente002 = mgente002;
	}


	public Mgente00 getMgente003() {
		return mgente003;
	}


	public void setMgente003(Mgente00 mgente003) {
		this.mgente003 = mgente003;
	}


	public boolean isTabMarcaCatalogo() {
		return tabMarcaCatalogo;
	}


	public void setTabMarcaCatalogo(boolean tabMarcaCatalogo) {
		this.tabMarcaCatalogo = tabMarcaCatalogo;
	}


	public List<Mgente00> getListMgente() {
		return listMgente;
	}


	public void setListMgente(List<Mgente00> listMgente) {
		this.listMgente = listMgente;
	}


	public String getValorConcatenado() {
		return valorConcatenado;
	}

	public void setValorConcatenado(String valorConcatenado) {
		this.valorConcatenado = valorConcatenado;
	}

	public int getTabView() {
		return tabView;
	}

	public void setTabView(int tabView) {
		this.tabView = tabView;
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

	public InputText getInputTextIdcmteColumn() {
		return inputTextIdcmteColumn;
	}

	public void setInputTextIdcmteColumn(InputText inputTextIdcmteColumn) {
		this.inputTextIdcmteColumn = inputTextIdcmteColumn;
	}

	public InputText getInputTextCodtteColumn() {
		return inputTextCodtteColumn;
	}

	public void setInputTextCodtteColumn(InputText inputTextCodtteColumn) {
		this.inputTextCodtteColumn = inputTextCodtteColumn;
	}

	public InputText getInputTextIdnjteColumn() {
		return inputTextIdnjteColumn;
	}

	public void setInputTextIdnjteColumn(InputText inputTextIdnjteColumn) {
		this.inputTextIdnjteColumn = inputTextIdnjteColumn;
	}

	public InputText getInputTextIddpteColumn() {
		return inputTextIddpteColumn;
	}

	public void setInputTextIddpteColumn(InputText inputTextIddpteColumn) {
		this.inputTextIddpteColumn = inputTextIddpteColumn;
	}

	public InputText getInputTextIdciteColumn() {
		return inputTextIdciteColumn;
	}

	public void setInputTextIdciteColumn(InputText inputTextIdciteColumn) {
		this.inputTextIdciteColumn = inputTextIdciteColumn;
	}

	public InputText getInputTextIdovteColumn() {
		return inputTextIdovteColumn;
	}

	public void setInputTextIdovteColumn(InputText inputTextIdovteColumn) {
		this.inputTextIdovteColumn = inputTextIdovteColumn;
	}

	public InputText getInputTextIdcdteColumn() {
		return inputTextIdcdteColumn;
	}

	public void setInputTextIdcdteColumn(InputText inputTextIdcdteColumn) {
		this.inputTextIdcdteColumn = inputTextIdcdteColumn;
	}

	public InputText getInputTextCoditeColumn() {
		return inputTextCoditeColumn;
	}

	public void setInputTextCoditeColumn(InputText inputTextCoditeColumn) {
		this.inputTextCoditeColumn = inputTextCoditeColumn;
	}


	public InputTextarea getInputTextDctateColumn() {
		return inputTextDctateColumn;
	}

	public void setInputTextDctateColumn(InputTextarea inputTextDctateColumn) {
		this.inputTextDctateColumn = inputTextDctateColumn;
	}

	public InputTextarea getInputTextDireteColumn() {
		return inputTextDireteColumn;
	}

	public void setInputTextDireteColumn(InputTextarea inputTextDireteColumn) {
		this.inputTextDireteColumn = inputTextDireteColumn;
	}

	public InputText getInputTextAliateColumn() {
		return inputTextAliateColumn;
	}

	public void setInputTextAliateColumn(InputText inputTextAliateColumn) {
		this.inputTextAliateColumn = inputTextAliateColumn;
	}

	public Taudit00 getTaudit00() {
		return taudit00;
	}

	public void setTaudit00(Taudit00 taudit00) {
		this.taudit00 = taudit00;
	}

	public String getTiPersona() {
		return tiPersona;
	}

	public void setTiPersona(String tiPersona) {
		this.tiPersona = tiPersona;
	}

	public boolean getPerNatural() {
		return perNatural;
	}

	public void setPerNatural(boolean perNatural) {
		this.perNatural = perNatural;
	}

	public boolean getPerJuridica() {
		return perJuridica;
	}

	public void setPerJuridica(boolean perJuridica) {
		this.perJuridica = perJuridica;
	}

	public InputTextarea getInputTextPrnmteColumn() {
		return inputTextPrnmteColumn;
	}

	public void setInputTextPrnmteColumn(InputTextarea inputTextPrnmteColumn) {
		this.inputTextPrnmteColumn = inputTextPrnmteColumn;
	}

	public InputTextarea getInputTextPrapteColumn() {
		return inputTextPrapteColumn;
	}

	public void setInputTextPrapteColumn(InputTextarea inputTextPrapteColumn) {
		this.inputTextPrapteColumn = inputTextPrapteColumn;
	}

	public List<Mcotes00> getListMcotes00() {
		return listMcotes00;
	}

	public void setListMcotes00(List<Mcotes00> listMcotes00) {
		this.listMcotes00 = listMcotes00;
	}

	public List<Mcotes00> getFilteredMcotes00() {
		return filteredMcotes00;
	}

	public void setFilteredMcotes00(List<Mcotes00> filteredMcotes00) {
		this.filteredMcotes00 = filteredMcotes00;
	}

	public IMcotes00Service getMcotes00Service() {
		return mcotes00Service;
	}

	public void setMcotes00Service(IMcotes00Service mcotes00Service) {
		this.mcotes00Service = mcotes00Service;
	}

	public boolean isFlagLista() {
		return flagLista;
	}

	public void setFlagLista(boolean flagLista) {
		this.flagLista = flagLista;
	}

	public InputText getInputTextIdtcteColumn() {
		return inputTextIdtcteColumn;
	}

	public void setInputTextIdtcteColumn(InputText inputTextIdtcteColumn) {
		this.inputTextIdtcteColumn = inputTextIdtcteColumn;
	}	

}