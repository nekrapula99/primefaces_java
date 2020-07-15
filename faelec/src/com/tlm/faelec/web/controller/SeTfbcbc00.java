package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMaccio00Service;
import com.tlm.faelec.service.maestros.IMacdio00Service;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMestad00Service;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMteste00Service;
import com.tlm.faelec.service.maestros.IMtiptx00Service;
import com.tlm.faelec.service.trans.ITfbcbc00Service;
import com.tlm.faelec.service.trans.ITqdfqf00Service;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelec.service.trans.ITqesqe00Service;
import com.tlm.faelec.service.trans.ITqigqg00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;
import com.tlm.faelecEntities.model.entities.Musuar00;
import com.tlm.faelecEntities.model.entities.Tfbcbc00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqesqe00;

import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Controller
@ManagedBean
@SessionScoped
public class SeTfbcbc00  extends Control implements Serializable{
	
private static final long serialVersionUID = 1L;

	
	@ManagedProperty(value="#{mgenus00Service}")
	IMgenus00Service mgenus00Service;
	
	@ManagedProperty(value = "#{tqigqg00Service}")
	private ITqigqg00Service tqigqg00Service;
	
	@ManagedProperty(value = "#{tqdrqr00Service}")
	private ITqdrqr00Service tqdrqr00Service;
	
	@ManagedProperty(value = "#{tqdfqf00Service}")
	private ITqdfqf00Service tqdfqf00Service;

	@ManagedProperty(value = "#{mtiptx00Service}")
	private IMtiptx00Service mtiptx00Service;
	
	@ManagedProperty(value = "#{tqesqe00Service}")
	private ITqesqe00Service tqesqe00Service;
	
	@ManagedProperty(value = "#{mestad00Service}")
	private IMestad00Service mestad00Service;
	
	@ManagedProperty(value = "#{maccio00Service}")
	private IMaccio00Service maccio00Service;
	
	@ManagedProperty(value = "#{macdio00Service}")
	private IMacdio00Service macdio00Service;
	
	@ManagedProperty(value = "#{mteste00Service}")
	private IMteste00Service mteste00Service;
	
	@ManagedProperty(value = "#{mconca00Service}")
	private IMconca00Service mconca00Service;	
	
	@ManagedProperty(value = "#{tfbcbc00Service}")
	private ITfbcbc00Service tfbcbc00Service;
	
	@ManagedProperty(value="#{seCabecera}")
	private SeCabecera seCabecera;
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	private Mgenus00 mgenus00;

	private List<Tqdrqr00> listTqdrqr00Aux;
	private List<Tqdfqf00> listTqdfqf00Aux;
	private List<Tqdfqf00> listTqdfqf00;
	
	private List<Mtiptx00> listMtiptx00;
	private Mtiptx00 mtiptx00;
	
	private Tqigqg00 tqigqg00;
	private Tqigqg00 tqigqg00Cop;
	private List<Tqigqg00> listTqigqg00;
	private List<Tqigqg00> filteredTqigqg00;
	
	private Mtiptx00 mtiptx00Cop;
	
	private SeTqdrqr00 seTqdrqr00;
	private SeTqdfqf00 seTqdfqf00;
	
	//private String action;
	
	//Tqdrqr00 Detalle requerimiento
	private Tqdrqr00 tqdrqr00;
	private List<Mdespr00> filteredTqdrqr00;
	private List<Mdespr00> listTqdrqr00Remove;

	//Detalle producto
	private Tqdfqf00 tqdfqf00; 
	
	private String accionTqdrqr;
	
	private Tqesqe00 tqesqe00;
	private Tqesqe00 tqesqe00Cop;
	private boolean banId = false;
	
	private Mestad00 mestad00;
	private Maccio00 maccio00;
	private Macdio00 macdio00;
	private Musuar00 musuar00Session;
	private Mconca00 mconca00;
	
	private String dstiptx;

	private Double total;
	private Double totalProCuota;
	
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
	private String tablaCotizacion;
	private String accion;
	private String titulo;


	private List<String> listSufijo;
	private Integer sufijo;
	private String fechaActual;
	private String descEstado;
	private String compac;
	private String compaCodcia;
	private Integer CompaIdccia;
	private boolean numNuevo;
	private boolean numModificar;


	
	private List<Integer> listIdeses;
	private List<Mestad00> listMestad00;
	private List<String> listCompac;
	
	private String dEstadoinit;
	private  boolean validacionEst;
	private boolean flagEstadoInicial;
	private Integer idesesInit=0;
	private boolean validacionFecha;
	
	//private static final Log LOG = LogFactory.getLog(SeTqigqg00.class);
	
	private Tfbcbc00 tfbcbc00;
	private boolean showAlerta;
	private boolean flagBorrador;
	private boolean showList;
	
	private List<Tfbcbc00> listTfbcbc00;
	
	@PostConstruct
	public void init() {
		try {
			super.init("TFBCBC00");
			tqigqg00 = new Tqigqg00();
			tfbcbc00 = new Tfbcbc00();
			//tqigqg00.setMtiptx00(getMtiptx00());
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tablaCotizacion=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			compac=super.getSeControlFactura().getMconca00Session().getNomcia();
			compaCodcia = super.getSeControlFactura().getMconca00Session().getCodcia();
			CompaIdccia = super.getSeControlFactura().getMconca00Session().getIdccia();
		
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			fracDec_CantiM=d1.intValue();
	
			
			numNuevo = false;
			numModificar = false;
			showList = false;
			showAlerta = false;
	    	validacionFecha = false;
	    	flagBorrador = false;
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try{
			getSeListas().actualizarListas(NombresListas.listMgenteCliente);
			getSeListas().actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMgenusMoneda);
			getSeListas().actualizarListas(NombresListas.listMgenusOrganiVentas);
			getSeListas().actualizarListas(NombresListas.listMgenusCanalDistri);
			getSeListas().actualizarListas(NombresListas.listMpropr);
			getSeListas().actualizarListas(NombresListas.listMfunfu);
			getSeListas().actualizarListas(NombresListas.listMtiptx00);
			getSeListas().actualizarListas(NombresListas.listMgenus00MonedaCot);
			
			}catch (Exception e) {
			    e.printStackTrace();
			}		
	}
	

	public void cargarDatos() {
		listTqigqg00 = tqigqg00Service.listTqigqg00ByCriteria(new Tqigqg00(),getSeControlFactura().getCompaniasUsu());
	}
	
	 public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	 }
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tablaCotizacion);
			total = 0.0;
			//validarTransicionEstado();
			seListas.actualizarListas(NombresListas.listMconca);
			tqigqg00 = new Tqigqg00();
			mtiptx00 = new Mtiptx00();
			tqigqg00.setRegcqg(true);
			numNuevo = true;
			numModificar = false;


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
			getSeControlFactura().setTabla(tablaCotizacion);
						

			tqdfqf00 = new Tqdfqf00();
			tqdfqf00.setTqigqg00(tqigqg00);
			seTqdfqf00 = new SeTqdfqf00();
			showList = true;
			listTqdfqf00Aux = tqdfqf00Service.listTqdfqf00ByCriteria(tqdfqf00,getSeControlFactura().getCompaniasUsu());
			seTqdfqf00.setListTqdfqf00(listTqdfqf00Aux);
			

				for(Tqdfqf00 obj2: listTqdfqf00Aux){
						setBandera(obj2);
						obj2.setPdfBorrador(flagBorrador);
					
				}
				
			System.out.println("showList: "+showList);
			
			RequestContext.getCurrentInstance().update(":formDetalle:pnlTfbcbc00");
			RequestContext.getCurrentInstance().update(":formDetalle:dTableTfbdbd00");
				
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	private boolean setBandera(Tqdfqf00 tqdfqf00) {

			flagBorrador = false;
			listTfbcbc00 = tfbcbc00Service.listTfbcbc00FacByCriteria(new Tfbcbc00(),getSeControlFactura().getCompaniasUsu());			
			
			for(Tfbcbc00 obj1: listTfbcbc00){
				if(obj1.getTqdfqf00().getIdcpqf().equals(tqdfqf00.getIdcpqf())){
					flagBorrador = true;
				}
	
			}
			return flagBorrador;
	}
	
	
	
	private void colaEstandar() {
		try {
			tqigqg00.setUsecqg(getSeControlFactura().codusu);
			tqigqg00.setPrgcqg("Cotizacion_Estandar " + getClass().getName());
			tqigqg00.setFeacqg(new Date());
			tqigqg00.setMaqcqg(getSeControlFactura().ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Maccio00 consultarObjetoMaccio00(){
		maccio00 = new Maccio00();
		maccio00.setMconca00(getSeControlFactura().getMconca00Session());
		maccio00.setMtiptx00(tqigqg00.getMtiptx00());
		maccio00 = maccio00Service.getMaccio00ByTqigqg00(maccio00, tablaCotizacion);
		return maccio00;
	}
	
	public Macdio00 consultarObjetoMacdio00(String notdio){
		macdio00 = new Macdio00();
		macdio00.setMaccio00(consultarObjetoMaccio00());
		macdio00 = macdio00Service.getMacdio00ByTqigqg00(macdio00,notdio);
		return macdio00;
	}
	
	public Mconca00 consultarObjetoMconca00(String codcia){
		mconca00 = mconca00Service.getMconca00ByCodcia(codcia);
		return mconca00;
	}
	
	/*Metodo que se encarga de validar que los registros de las listas esten habilitados
	 * en caso de estar deshabilitado algun registro devuelve true
	 */
	
	
	public boolean validarFechas(){
		validacionFecha = false;
		if(tqigqg00.getFecrqg().after(tqigqg00.getFeviqg())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha de vigencia debe ser mayor o igual a Fecha creación");
			RequestContext.getCurrentInstance().update("formTable"); 
			validacionFecha = true;		
		}else if(tqigqg00.getFeviqg().after(tqigqg00.getFepdqg())){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha Promesa Delivery debe ser mayor o igual a Fecha de vigencia");
			RequestContext.getCurrentInstance().update("formTable"); 
			validacionFecha = true;	
		}
		return validacionFecha;
	}
	
	public void regresar(){
		try {
			update=null;
			RequestContext.getCurrentInstance().update("formTable"); 
			RequestContext.getCurrentInstance().update("formDetalle"); 
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	

	public void validarCuota(){
		try {
			Date fechaHoy = new Date();
			
			for(Tqdfqf00 obj: listTqdfqf00){
				if(obj.getFelfqf().after(fechaHoy)){
					showAlerta = true;
					UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha de liberación anterior a Fecha Actual");
					RequestContext.getCurrentInstance().update("formDetalle"); 
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void save() {
		try {

			colaEstandar();
			
			
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				
				
					tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),tqigqg00Cop.toString()):null);
					tqesqe00Service.save(tqesqe00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqesqe00.toString(),tqesqe00.toStringId(),tqesqe00Cop.toString()):null);				
					numModificar = false;
				
			} else {
				tqigqg00Service.save(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.toString(),tqigqg00.toStringId(),null):null);
				if(getAccion().equals(Constantes.ACCION_NUEVO))
				{
					mtiptx00Service.save(tqigqg00.getMtiptx00(),getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tqigqg00.getMtiptx00().toString(),tqigqg00.getMtiptx00().toStringId(),null):null);

				}	
			 }
			cargarDatos();
			validacionEst = false;
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
			getSeControlFactura().setTabla(tablaCotizacion);
			tqigqg00 = (Tqigqg00) event.getComponent().getAttributes().get("tqigqg00");
			tqigqg00Service.removeTqigqg00(tqigqg00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,tqigqg00.toString(),tqigqg00.toStringId()):null);
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
			//getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);

		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialogTfbcbc00(CloseEvent event) {
        try {  
        	
        	update=null;
        	
        	//evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }

	

	public ITqigqg00Service getTqigqg00Service() {
		return tqigqg00Service;
	}

	public void setTqigqg00Service(ITqigqg00Service tqigqg00Service) {
		this.tqigqg00Service = tqigqg00Service;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public Tqigqg00 getTqigqg00Cop() {
		return tqigqg00Cop;
	}

	public void setTqigqg00Cop(Tqigqg00 tqigqg00Cop) {
		this.tqigqg00Cop = tqigqg00Cop;
	}

	public List<Tqigqg00> getListTqigqg00() {
		return listTqigqg00;
	}

	public void setListTqigqg00(List<Tqigqg00> listTqigqg00) {
		this.listTqigqg00 = listTqigqg00;
	}

	public List<Tqigqg00> getFilteredTqigqg00() {
		return filteredTqigqg00;
	}

	public void setFilteredTqigqg00(List<Tqigqg00> filteredTqigqg00) {
		this.filteredTqigqg00 = filteredTqigqg00;
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



	
	public Mgenus00 getMgenus00() {
		return mgenus00;
	}

	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}

	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
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

	public List<Mdespr00> getFilteredTqdrqr00() {
		return filteredTqdrqr00;
	}

	public void setFilteredTqdrqr00(List<Mdespr00> filteredTqdrqr00) {
		this.filteredTqdrqr00 = filteredTqdrqr00;
	}

	public List<Mdespr00> getListTqdrqr00Remove() {
		return listTqdrqr00Remove;
	}

	public void setListTqdrqr00Remove(List<Mdespr00> listTqdrqr00Remove) {
		this.listTqdrqr00Remove = listTqdrqr00Remove;
	}

	public String getAccionTqdrqr() {
		return accionTqdrqr;
	}

	public void setAccionTqdrqr(String accionTqdrqr) {
		this.accionTqdrqr = accionTqdrqr;
	}



	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}



	public List<String> getListSufijo() {
		return listSufijo;
	}

	public void setListSufijo(List<String> listSufijo) {
		this.listSufijo = listSufijo;
	}

	public Integer getSufijo() {
		return sufijo;
	}

	public void setSufijo(Integer sufijo) {
		this.sufijo = sufijo;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Mtiptx00 getMtiptx00Cop() {
		return mtiptx00Cop;
	}

	public void setMtiptx00Cop(Mtiptx00 mtiptx00Cop) {
		this.mtiptx00Cop = mtiptx00Cop;
	}

	public ITqesqe00Service getTqesqe00Service() {
		return tqesqe00Service;
	}

	public void setTqesqe00Service(ITqesqe00Service tqesqe00Service) {
		this.tqesqe00Service = tqesqe00Service;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public Tqesqe00 getTqesqe00() {
		return tqesqe00;
	}

	public void setTqesqe00(Tqesqe00 tqesqe00) {
		this.tqesqe00 = tqesqe00;
	}

	public boolean isBanId() {
		return banId;
	}

	public void setBanId(boolean banId) {
		this.banId = banId;
	}

	public Tqesqe00 getTqesqe00Cop() {
		return tqesqe00Cop;
	}

	public void setTqesqe00Cop(Tqesqe00 tqesqe00Cop) {
		this.tqesqe00Cop = tqesqe00Cop;
	}

	public IMestad00Service getMestad00Service() {
		return mestad00Service;
	}

	public void setMestad00Service(IMestad00Service mestad00Service) {
		this.mestad00Service = mestad00Service;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public boolean isNumNuevo() {
		return numNuevo;
	}

	public void setNumNuevo(boolean numNuevo) {
		this.numNuevo = numNuevo;
	}

	public boolean isNumModificar() {
		return numModificar;
	}

	public void setNumModificar(boolean numModificar) {
		this.numModificar = numModificar;
	}




	public List<Integer> getListIdeses() {
		return listIdeses;
	}

	public void setListIdeses(List<Integer> listIdeses) {
		this.listIdeses = listIdeses;
	}

	public List<Mestad00> getListMestad00() {
		return listMestad00;
	}

	public void setListMestad00(List<Mestad00> listMestad00) {
		this.listMestad00 = listMestad00;
	}

	
	public IMaccio00Service getMaccio00Service() {
		return maccio00Service;
	}


	public void setMaccio00Service(IMaccio00Service maccio00Service) {
		this.maccio00Service = maccio00Service;
	}

	
	public Maccio00 getMaccio00() {
		return maccio00;
	}


	public void setMaccio00(Maccio00 maccio00) {
		this.maccio00 = maccio00;
	}

	public Macdio00 getMacdio00() {
		return macdio00;
	}

	public void setMacdio00(Macdio00 macdio00) {
		this.macdio00 = macdio00;
	}


	public Musuar00 getMusuar00Session() {
		return musuar00Session;
	}

	public void setMusuar00Session(Musuar00 musuar00Session) {
		this.musuar00Session = musuar00Session;
	}

	public IMacdio00Service getMacdio00Service() {
		return macdio00Service;
	}

	public void setMacdio00Service(IMacdio00Service macdio00Service) {
		this.macdio00Service = macdio00Service;
	}

	public String getCompac() {
		return compac;
	}

	public void setCompac(String compac) {
		this.compac = compac;
	}

	public IMteste00Service getMteste00Service() {
		return mteste00Service;
	}
	
	public void setMteste00Service(IMteste00Service mteste00Service) {
		this.mteste00Service = mteste00Service;
	}



	public String getdEstadoinit() {
		return dEstadoinit;
	}

	public void setdEstadoinit(String dEstadoinit) {
		this.dEstadoinit = dEstadoinit;
	}

	public String getTablaCotizacion() {
		return tablaCotizacion;
	}

	public void setTablaCotizacion(String tablaCotizacion) {
		this.tablaCotizacion = tablaCotizacion;
	}

	public List<String> getListCompac() {
		return listCompac;
	}

	public void setListCompac(List<String> listCompac) {
		this.listCompac = listCompac;
	}

	public Integer getCompaIdccia() {
		return CompaIdccia;
	}

	public void setCompaIdccia(Integer compaIdccia) {
		CompaIdccia = compaIdccia;
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

	public String getCompaCodcia() {
		return compaCodcia;
	}

	public void setCompaCodcia(String compaCodcia) {
		this.compaCodcia = compaCodcia;
	}

	public String getDstiptx() {
		return dstiptx;
	}

	public void setDstiptx(String dstiptx) {
		this.dstiptx = dstiptx;
	}

	public boolean isValidacionEst() {
		return validacionEst;
	}

	public void setValidacionEst(boolean validacionEst) {
		this.validacionEst = validacionEst;
	}

	public boolean isFlagEstadoInicial() {
		return flagEstadoInicial;
	}

	public void setFlagEstadoInicial(boolean flagEstadoInicial) {
		this.flagEstadoInicial = flagEstadoInicial;
	}

	public Integer getIdesesInit() {
		return idesesInit;
	}

	public void setIdesesInit(Integer idesesInit) {
		this.idesesInit = idesesInit;
	}

	public boolean getValidacionFecha() {
		return validacionFecha;
	}

	public void setValidacionFecha(boolean validacionFecha) {
		this.validacionFecha = validacionFecha;
	}


	public ITqdrqr00Service getTqdrqr00Service() {
		return tqdrqr00Service;
	}

	public void setTqdrqr00Service(ITqdrqr00Service tqdrqr00Service) {
		this.tqdrqr00Service = tqdrqr00Service;
	}

	public List<Tqdrqr00> getListTqdrqr00Aux() {
		return listTqdrqr00Aux;
	}

	public void setListTqdrqr00Aux(List<Tqdrqr00> listTqdrqr00Aux) {
		this.listTqdrqr00Aux = listTqdrqr00Aux;
	}

	public SeTqdrqr00 getSeTqdrqr00() {
		return seTqdrqr00;
	}

	public void setSeTqdrqr00(SeTqdrqr00 seTqdrqr00) {
		this.seTqdrqr00 = seTqdrqr00;
	}



	public SeTqdfqf00 getSeTqdfqf00() {
		return seTqdfqf00;
	}

	public void setSeTqdfqf00(SeTqdfqf00 seTqdfqf00) {
		this.seTqdfqf00 = seTqdfqf00;
	}

	public ITqdfqf00Service getTqdfqf00Service() {
		return tqdfqf00Service;
	}

	public void setTqdfqf00Service(ITqdfqf00Service tqdfqf00Service) {
		this.tqdfqf00Service = tqdfqf00Service;
	}



	public List<Tqdfqf00> getListTqdfqf00Aux() {
		return listTqdfqf00Aux;
	}

	public void setListTqdfqf00Aux(List<Tqdfqf00> listTqdfqf00Aux) {
		this.listTqdfqf00Aux = listTqdfqf00Aux;
	}

	public Tqdrqr00 getTqdrqr00() {
		return tqdrqr00;
	}

	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}


	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalProCuota() {
		return totalProCuota;
	}

	public void setTotalProCuota(Double totalProCuota) {
		this.totalProCuota = totalProCuota;
	}

	public List<Tqdfqf00> getListTqdfqf00() {
		return listTqdfqf00;
	}

	public void setListTqdfqf00(List<Tqdfqf00> listTqdfqf00) {
		this.listTqdfqf00 = listTqdfqf00;
	}

	public Tfbcbc00 getTfbcbc00() {
		return tfbcbc00;
	}

	public void setTfbcbc00(Tfbcbc00 tfbcbc00) {
		this.tfbcbc00 = tfbcbc00;
	}

	public boolean isShowAlerta() {
		return showAlerta;
	}

	public void setShowAlerta(boolean showAlerta) {
		this.showAlerta = showAlerta;
	}

	public List<Tfbcbc00> getListTfbcbc00() {
		return listTfbcbc00;
	}

	public void setListTfbcbc00(List<Tfbcbc00> listTfbcbc00) {
		this.listTfbcbc00 = listTfbcbc00;
	}

	public ITfbcbc00Service getTfbcbc00Service() {
		return tfbcbc00Service;
	}

	public void setTfbcbc00Service(ITfbcbc00Service tfbcbc00Service) {
		this.tfbcbc00Service = tfbcbc00Service;
	}

	public boolean isFlagBorrador() {
		return flagBorrador;
	}

	public void setFlagBorrador(boolean flagBorrador) {
		this.flagBorrador = flagBorrador;
	}

	public boolean isShowList() {
		return showList;
	}

	public void setShowList(boolean showList) {
		this.showList = showList;
	}
	
	
}
