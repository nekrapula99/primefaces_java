package com.tlm.faelec.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Controller;

//import com.ibm.disthub2.impl.formats.Multi.Constants.neighbors_table_type;
import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMgente00Service;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMidiom00Service;
import com.tlm.faelec.service.maestros.IMparca00Service;
import com.tlm.faelec.service.maestros.IMparme00Service;
import com.tlm.faelec.service.maestros.IMrerer00Service;
import com.tlm.faelec.service.maestros.IMtaric00Service;
import com.tlm.faelec.service.maestros.IMtiptx00Service;
import com.tlm.faelec.service.maestros.impl.Midiom00ServiceImpl;
import com.tlm.faelec.service.trans.ITfbcbc00Service;
import com.tlm.faelec.service.trans.ITfbdbd00Service;
import com.tlm.faelec.service.trans.ITfesfu00Service;
import com.tlm.faelec.service.trans.ITqdfqf00Service;
import com.tlm.faelec.service.trans.ITqdpqp00Service;
import com.tlm.faelec.service.trans.ITqdrfg00Service;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.ReportsUtils;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Mparca00;
import com.tlm.faelecEntities.model.entities.Mparme00;
import com.tlm.faelecEntities.model.entities.Mparpd00;
import com.tlm.faelecEntities.model.entities.Mregcg00;
import com.tlm.faelecEntities.model.entities.Mrerer00;
import com.tlm.faelecEntities.model.entities.Mtaric00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;
import com.tlm.faelecEntities.model.entities.ReportEntity;
import com.tlm.faelecEntities.model.entities.Tfbcbc00;
import com.tlm.faelecEntities.model.entities.Tfesfu00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tfbdbd00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;


@Controller
@ManagedBean
@SessionScoped
public class SeTfbdbd00  extends Control implements Serializable{
	
	private static final long serialVersionUID = 7213183176463668661L;
	
	@ManagedProperty(value = "#{tfbdbd00Service}")
	private ITfbdbd00Service tfbdbd00Service;
	
	@ManagedProperty(value = "#{tfbcbc00Service}")
	private ITfbcbc00Service tfbcbc00Service;
	
	@ManagedProperty(value = "#{tqdpqp00Service}")
	private ITqdpqp00Service tqdpqp00Service;
	
	@ManagedProperty(value = "#{tqdrqr00Service}")
	private ITqdrqr00Service tqdrqr00Service;
	
	@ManagedProperty(value = "#{tqdrfg00Service}")
	private ITqdrfg00Service tqdrfg00Service;
	
	@ManagedProperty(value = "#{mtaric00Service}")
	private IMtaric00Service mtaric00Service;
	
	@ManagedProperty(value = "#{mgenus00Service}")
	private IMgenus00Service mgenus00Service;
	
	@ManagedProperty(value = "#{midiom00Service}")
	private IMidiom00Service midiom00Service;
	
	@ManagedProperty(value = "#{mparme00Service}")
	private IMparme00Service mparme00Service;
	
	@ManagedProperty(value = "#{mrerer00Service}")
	private IMrerer00Service mrerer00Service;
	
	@ManagedProperty("#{mgente00Service}")
	private IMgente00Service mgente00Service;
	
	@ManagedProperty(value = "#{tqdfqf00Service}")
	private ITqdfqf00Service tqdfqf00Service;
	
	@ManagedProperty(value = "#{mtiptx00Service}")
	private IMtiptx00Service mtiptx00Service;
	
	@ManagedProperty(value = "#{tfesfu00Service}")
	private ITfesfu00Service tfesfu00Service;
			
	
	@ManagedProperty(value = "#{seListas}") 
	private SeListas seListas;
	
	@ManagedProperty(value = "#{seTfbcbc00}")
	private SeTfbcbc00 seTfbcbc00;
	
	@ManagedProperty(value = "#{seTqdrqr00}")
	private SeTqdrqr00 seTqdrqr00;
	
	@ManagedProperty(value = "#{seMgente00}")
	private SeMgente00 seMgente00;
	
	@ManagedProperty(value = "#{mconca00Service}")
	private IMconca00Service mconca00Service;
	
	@ManagedProperty(value = "#{mparca00Service}")
	private IMparca00Service mparca00Service;
	
	//@ManagedProperty(value = "#{seTqdpqp00}")
	private SeTqdpqp00 seTqdpqp00;

	private Tqigqg00 tqigqg00;
	
	private Tfbdbd00 tfbdbd00New;
	private Tqdfqf00 tfbdbd00Clone;
	private List<Tqdfqf00> listTfbdbd00;
	private List<Tfbdbd00> filteredTfbdbd00;
	private boolean infoGuardada;
	
	private List<Tqdpqp00> filteredTqdpqp00;
	private List<Tqdpqp00> listTqdpqp00Remove;
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	private  HashMap<String, Mcampo00> permisoCampos;
	
	private String tabla;
	private String accion;
	private String titulo;
	private String paternDec_CantiM;
	private Integer fracDec_CantiM;
	

	private String button;
	private String update;

	
	//Carga de datos Req - Prod
	private Tqdpqp00 tqdpqp00;
	private Tqdrqr00 tqdrqr00;
	private List<Tqdpqp00> listTqdpqp00;
	private List<Tqdrqr00> listTqdrqr00;
	//private List<Tqdpqp00> listTqdpqp00Aux;

	private Tfbdbd00 tfbdbd00;
	private Tqdrfg00 tqdrfg00;
	private Tqdpqp00 Tfbdbd00Aux;
	private List<Tqdrfg00> listTqdrfg00Aux;
	private List<Tqdrfg00> listTqdrfg00;
	private List<Tqdrfg00> filteredTqdrfg00;
	private boolean flagTqdrfg00;
	private boolean flagTfbdbd00;
	
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
	
	private boolean showAlerta;
	private Tqdfqf00 tqdfqf00;
	private List<Tqdfqf00> filteredTqdfqf00;
	private SeTqdfqf00 seTqdfqf00;
	private List<Tqdfqf00> listTqdfqf00Aux;
	private List<Tqdfqf00> listTqdfqf00;
	private String tipoFactura;

	private List<Mtaric00> listMtaric00;
	private List<Mrerer00> listMrerer00;
	
	private Tfbcbc00 tfbcbc00;
	private Tfbcbc00 tfbcbc00Aux;
	private Mtiptx00 mtiptx00;
	
	private Tfbcbc00 tfbcbc00Clone;
	private Integer linea;
	
	private List<Tfbdbd00> listTfbdbd00Cons;
	private List<Mgenus00> listMgenus00;
	private List<Midiom00> listMidiom00;
	private List<Mparme00> listMparme00;
	
	
	private Double porfbd;
	private Double poivbd;
	private Double poicbd;
	private Double cafabd;
	private Double vabrbd;
	private Double imcobd;
	private Double vaivbd;
	private Double reivbd;
	private Double refubd;
	private Double todebd;
	
	private Mgenus00 tarifaRtf;
	private String tarRetefuente;
	private String descTarRetefuente;
	//tfbdbd00.setTarfbd(idtrbd);
	//tfbdbd00.setDetabd(detabd);
	
	private Double totalBruto;
	private Double totalImpCon;
	private Double totalIva;
	private Double totalReteIva;
	private Double totalRtf;
	private Double totolVPagar;
	
	private boolean noShowDialog;
	private List<Double> listTotales;
	private Integer idTipoContriCompa;
	private Mconca00 compaIdccia;
	private boolean regValido;
	
	private String exirer;
	private String reirer;
	private String refrer;
	private Double pcirer;
	private boolean disableButton;
	private boolean pdfRendered;
	private List<Tfbcbc00> listTfbcbc00;
	
	//EsatdoFEsfu
	private Tfesfu00 tfesfu00;
	private List<Mparme00> listMparme00Aux;
	private List<Tfesfu00> listTfesfu00;
	private String estadoCuota;
	private Mestad00 mestad00;
	private String descEstado;
	private boolean validarEstadoFlag;
	private boolean validarSiguienteCuota;
	private boolean flagFound;
	private boolean flagFoundNoFacturada;
	
	
	@PostConstruct
	public void init() {
		try {
			super.init("TFBDBD00");
			tfbdbd00 = new Tfbdbd00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			idTipoContriCompa = super.getSeControlFactura().getMconca00Session().getMregcg004().getIdiecg();
			compaIdccia = super.getSeControlFactura().getMconca00Session();
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CantiM");
			fracDec_CantiM=d1.intValue();
			paternDec_CantiM = calcularPatern(fracDec_CantiM.toString());
			showAlerta = false;
			noShowDialog = false;
			regValido = false;
			disableButton = false;
			pdfRendered = false;
			flagFound = false;
			flagFoundNoFacturada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}

	
	 public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	 }
	

	public void cargarDatos() {
		
		listTfbdbd00 = tqdfqf00Service.listTqdfqf00ByCriteria(new Tqdfqf00(),getSeControlFactura().getCompaniasUsu());
		for(Tqdfqf00 obj: listTfbdbd00){
			setBandera(obj);
			obj.setPdfBorrador(pdfRendered);
			
		}
	
	}
	
	//Limpia el filtro del DataTable
	public void clearAllFilters2() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:dTableTfbdbd00");
			dataTable.reset();
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:dTableTfbdbd00");
	}

	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			
			
			tfbdbd00 = new Tfbdbd00();
			tfbdbd00.setRegcbd(true);
			setTqdrfg00(null);
			tfbdbd00.setTfbcbc00(seTfbcbc00.getTfbcbc00());
					
			cargarDatos();
			clearAllFilters2();
			tfbdbd00Clone = (Tqdfqf00) tqdfqf00.clone();
			disableButton = false;
			 RequestContext.getCurrentInstance().update(":formDetalle");
			
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

			tfbcbc00 = new Tfbcbc00();
			tfbdbd00 = new Tfbdbd00();
			tfbdbd00.setRegcbd(true);
			tfbcbc00.setTfbdbd00s(new ArrayList<Tfbdbd00>());
			cargarDatos();
			clearAllFilters2();
			tfbcbc00Clone = (Tfbcbc00) tfbcbc00.clone();
			infoGuardada = false;
			tqdrfg00 = new Tqdrfg00();
			tqdrfg00.setTqdfqf00(tqdfqf00);
			listTqdrfg00Aux = tqdrfg00Service.getTqdrfg00ByCuota(tqdrfg00);
			validarCuota();
			if(!regValido){
				calcularDatos();
			}
			disableButton = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	public boolean obtenerRegistroValido(Integer idComprador, Integer idVendedor){
		return mrerer00Service.obtenerRegistroValido(idComprador, idVendedor);
	}
	
	
	public void validarCuota(){
		try {
			Date fechaHoy = new Date();
			tfesfu00 = new Tfesfu00();
			mestad00 = new Mestad00();
			regValido = false;
			validarEstadoFlag = false;
			validarSiguienteCuota = false;

			if(tqdfqf00.getTqigqg00().getMgente00().getMregcg005().getIdiecg()!=null && idTipoContriCompa !=null){
				if(!obtenerRegistroValido(tqdfqf00.getTqigqg00().getMgente00().getMregcg005().getIdiecg(), idTipoContriCompa)){
					regValido = true;
				}
			}
			
			if(regValido){

				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('alertDialog').show();");
				 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un registro valido en el maestro de reglas para esta cotización");
				 regValido = false;
				 noShowDialog = true;
				
			}

			
			
			//2.Cuota en estado facturada
			//UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No se permite generar borrador a una cuota ya Facturada");
			
			Date fechaMayor = new Date();
			List<Date> listFechasTfesfu00= new ArrayList<Date>();
			tfesfu00.setTqdfqf00(tqdfqf00);
			listTfesfu00 = tfesfu00Service.listTfesfu00ByCriteria(tfesfu00, getSeControlFactura().getCompaniasUsu());
			listMparme00 = mparme00Service.ListMparmeByCriteria(getSeControlFactura().getCompaniasUsu(),new Mparme00());
			for(Mparme00 obj: listMparme00){
				if(obj.getNopame()!=null && obj.getVapame() != null){
					if(obj.getNopame().equalsIgnoreCase("Estado_Cuota_Facturada")){
						estadoCuota = obj.getVapame();

					}	
				}else{
					estadoCuota = "";
				}
			}
			
			for(Tfesfu00 obj: listTfesfu00){
				if(obj.getTqdfqf00().getIdcpqf().equals(getTqdfqf00().getIdcpqf())){					
					if(obj.getMestad00()!=null){
						listFechasTfesfu00.add(obj.getFeacfu());
						fechaMayor = Collections.max(listFechasTfesfu00);
						
						if(obj.getFeacfu().equals(fechaMayor)){
							mestad00 = obj.getMestad00();
							descEstado = mestad00.getCotres();
							if(descEstado.equalsIgnoreCase(estadoCuota)){
								validarEstadoFlag = true;
							}
							//estadoFacturada = true;
						}
					}	
				}
				
			}
			
			if(validarEstadoFlag){

				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('alertDialog').show();");
				 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No se permite generar borrador a una cuota ya Facturada.");
				 noShowDialog = true;
				
			}
			
			
			
			//3.priemra cuota sin facturar
			//UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Debe facturar antes otra cuota anterior a esta seleccionada");
			
			Tqdfqf00 tqdfqf00Sig = new Tqdfqf00();
			
			listTqdfqf00Aux = tqdfqf00Service.listTqdfqf00ByCriteria(tqdfqf00,getSeControlFactura().getCompaniasUsu());
			
			for(Tqdfqf00 obj: listTqdfqf00Aux){
				if(obj.getTqigqg00().getIdtqqg().equals(getTqdfqf00().getTqigqg00().getIdtqqg())){
					buscarEnFesfu00(obj);
					if(!flagFound || (flagFoundNoFacturada && flagFound)){
						tqdfqf00Sig  = obj;
						break;
					}
				}
			}

			if(tqdfqf00Sig!=null){
				if(!tqdfqf00Sig.getIdcpqf().equals((getTqdfqf00().getIdcpqf()))){

					validarSiguienteCuota = true;
				}
				
			}
			
			if(!validarEstadoFlag){
				if(validarSiguienteCuota){

					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('alertDialog').show();");
					 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Debe facturar antes otra cuota anterior a esta seleccionada.");
					 validarSiguienteCuota = false;
					 noShowDialog = true;
				}	
			}
			
			//1.validacion fecha
			if(!validarSiguienteCuota){
				if(tqdfqf00.getFelfqf()!=null){
					if(tqdfqf00.getFelfqf().after(fechaHoy)){

						RequestContext context = RequestContext.getCurrentInstance();
						context.execute("PF('alertDialog').show();");
						 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No se permite generar borrador, la Fecha de liberación debe ser anterior a la Fecha Actual");
						noShowDialog = true;
					}
				}
				
			}	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void buscarEnFesfu00(Tqdfqf00 tqdfqf){
		
		flagFound = false;
		flagFoundNoFacturada = false;
		Date fechaMayor = new Date();
		List<Date> listFechasTfesfu00= new ArrayList<Date>();
		tfesfu00.setTqdfqf00(tqdfqf00);
		
		listTfesfu00 = tfesfu00Service.listTfesfu00ByCriteria(tfesfu00, getSeControlFactura().getCompaniasUsu());
		
		for(Tfesfu00 objfesfu: listTfesfu00){
			if(tqdfqf.getIdcpqf().equals(objfesfu.getTqdfqf00().getIdcpqf())){
				flagFound = true;
				if(objfesfu.getMestad00()!=null){
					listFechasTfesfu00.add(objfesfu.getFeacfu());
					fechaMayor = Collections.max(listFechasTfesfu00);
						
					if(objfesfu.getFeacfu().equals(fechaMayor)){
						descEstado = objfesfu.getMestad00().getCotres();

								
						if(!descEstado.equalsIgnoreCase(estadoCuota)){

							flagFoundNoFacturada = true;
									
						}
					}
				}	
				
				
			}
			
		}
		
	}
	
	
	//Limpia el filtro del DataTable
	public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formDetalle:dTableTfbdbd00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext.getCurrentInstance();
		}
	}
		
	
	public void evtCloseDialogTfbdbd00(CloseEvent event) {
        try {  
        	
        	for(Tqdfqf00 obj2: listTqdfqf00Aux){
				setBandera(obj2);
				obj2.setPdfBorrador(pdfRendered);
			
        	}
        	
        	update=null;
        	noShowDialog = false;
        	RequestContext.getCurrentInstance().update("formTfbdbd00Detalle"); 
			RequestContext.getCurrentInstance().update(":formDetalle:dTableTfbdbd00");
			RequestContext.getCurrentInstance().update(":formDetalle:messages");
			RequestContext.getCurrentInstance().update(":formDetalle");
        	//evtCloseDialog(event);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	public void evtCloseDialogAlert(CloseEvent event) {
        try {  
        	
        	update=null;
        	
        	noShowDialog = false;
			RequestContext.getCurrentInstance().update("formTfbdbd00Detalle"); 
			RequestContext.getCurrentInstance().update(":formDetalle:dTableTfbdbd00");
			RequestContext.getCurrentInstance().update(":formDetalle");

        }
        catch(Exception e)
        {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }

	
	public Mtiptx00 obtenerListaMtiptx00s(String notetx){
		mtiptx00 = new Mtiptx00();
	    mtiptx00.setRegtxt(true);
	    mtiptx00.setNotetx(notetx);
	    mtiptx00 = mtiptx00Service.getMtiptx00ByNotetx(notetx);
		return mtiptx00;
	}
	
	public void removeBorrador(Tfbcbc00 tfbcbc00Aux) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			tfbcbc00Service.removeTfbcbc00(tfbcbc00Aux,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,tfbcbc00.toString(),tfbcbc00.toStringId()):null);
			RequestContext.getCurrentInstance().update("formTfbdbd00Detalle");
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	
	public Tfbcbc00 consultarObjetoTfbcbc00(Tqdfqf00 tqdfqf00){
		tfbcbc00.setTqdfqf00(tqdfqf00);
		tfbcbc00 = tfbcbc00Service.getTfbcbc00ByTqdfqf00(tfbcbc00);
		return tfbcbc00;
	}
	public void calcularDatos(){
		try {
			totalBruto = 0.0;
			totalImpCon = 0.0;
			totalIva = 0.0;
			totalReteIva = 0.0;
			totalRtf = 0.0;
			totolVPagar = 0.0;
			vabrbd = 0.0;
			poicbd = 0.0;
			cafabd = 0.0;
			imcobd = 0.0; //Imp consumo x detalle
			poivbd = 0.0;
			vaivbd = 0.0; //Impuesto IVA
			reivbd = 0.0;
			porfbd = 0.0; //%retefuente
			refubd = 0.0;
			
			Date fechaHoy = new Date();
			listTotales = new ArrayList<Double>();
			listMtaric00 = mtaric00Service.listMtaric00ByCriteria(new Mtaric00(), getSeControlFactura().getCompaniasUsu());
			listMrerer00 = mrerer00Service.listMrerer00ByCriteria(new Mrerer00(), getSeControlFactura().getCompaniasUsu());
						
			//Capturo datos de MAestro Reglas Mrere00
			for(Mrerer00 objMrerer:listMrerer00){
				if(idTipoContriCompa !=null || objMrerer.getMregcg001()!=null || objMrerer.getMregcg002()!=null || tqdfqf00.getTqigqg00().getMgente00().getMregcg005()!=null ){
					if(idTipoContriCompa.equals(objMrerer.getMregcg002().getIdiecg()) && objMrerer.getMregcg001().getIdiecg().equals(tqdfqf00.getTqigqg00().getMgente00().getMregcg005().getIdiecg())){
						exirer = objMrerer.getExirer();
						reirer = objMrerer.getReirer();
						refrer = objMrerer.getRefrer();
						pcirer = objMrerer.getPcirer();
						 disableButton = true;
						 tfbdbd00.setPoribd(pcirer);
					}
				}else{
					exirer = "";
					reirer = "";
					refrer = "";
					pcirer = 0.0;
					disableButton = false;
					tfbdbd00.setPoribd(pcirer);
					RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:pnlBotones");
					UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un registro valido en el maestro de reglas para esta cotización");
					return;

				}
			}			
			
			
			
			for(Tqdrfg00 obj: listTqdrfg00Aux){
				if(obj.getCafpfg()!=null){
					cafabd = obj.getCafpfg();//Cant facturada cafpfg
				}else{
					cafabd = 0.0;

				}
				
				if(obj.getTqdpqp00().getVamiqp().equals("M")){
					if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVumpqp()!=null){
						vabrbd = obj.getTqdpqp00().getVumpqp()*obj.getCafpfg(); //Valor bruto
						obj.setValorBruto(vabrbd);
					}
				}else{
					if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVupiqp()!=null){
						vabrbd = obj.getTqdpqp00().getVupiqp()*obj.getCafpfg();
						obj.setValorBruto(vabrbd);
					}
				}
				
				for(Mtaric00 objMtaric: listMtaric00){
					if(obj.getTqdpqp00().getMpropr00().getMgenus009()!=null && objMtaric.getMgenus001()!=null ){
						if(obj.getTqdpqp00().getMpropr00().getMgenus009().getIdtrus().equals(objMtaric.getMgenus001().getIdtrus())){
							if(objMtaric.getMgenus003()!=null){ //Si la ciudad no es null 
								if(objMtaric.getFevhic().after(fechaHoy)){
									if(objMtaric.getPricic()!=null){
										obj.setImpConsumo((objMtaric.getPricic()*vabrbd)/100);
										poicbd = objMtaric.getPricic();  //% imp consumo
										imcobd = (poicbd*vabrbd)/100; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

									}else{
										obj.setImpConsumo(0.0);
										poicbd = 0.0;  //% imp consumo
										imcobd = poicbd*vabrbd; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

										
									}	
									//LLENADO DE TARIFA

									if(objMtaric.getMgenus002()!=null){
										
										tarifaRtf = objMtaric.getMgenus002();
										tarRetefuente = objMtaric.getMgenus002().getCodius();
										descTarRetefuente  = objMtaric.getMgenus002().getDcttus();

										tfbdbd00.setMgenus003(tarifaRtf);
										tfbdbd00.setTarfbd(tarRetefuente);
										tfbdbd00.setDetabd(descTarRetefuente);
									}
									
									if(exirer.equalsIgnoreCase("N")){ // Si exento iva = N ,,,
										if(objMtaric.getPrvaic()!=null){
											obj.setIva((objMtaric.getPrvaic()*vabrbd)/100);
											poivbd = objMtaric.getPrvaic();
											vaivbd = (objMtaric.getPrvaic()*vabrbd)/100; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}else{
											obj.setIva(0.0);
											poivbd = 0.0;
											vaivbd = 0.0*vabrbd; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}
									}else{
										obj.setIva(0.0);
										poivbd = 0.0;
										vaivbd = 0.0;
										tfbdbd00.setPoivbd(poivbd);
										tfbdbd00.setVaivbd(vaivbd);
									}
									if(reirer.equalsIgnoreCase("S")){ //Si Retiene IVA= S
										obj.setReteIva((pcirer*vaivbd)/100); //Asigno % reteIva.
										reivbd = (pcirer*vaivbd)/100;
										tfbdbd00.setReivbd(reivbd);
									}else{
										obj.setReteIva(0.0);
										reivbd = 0.0;
										tfbdbd00.setReivbd(reivbd);

									}
									if(refrer.equalsIgnoreCase("S")){ //SI Retiene Fuente
										if(objMtaric.getMgenus002().getComnus() != null){
											obj.setReteFuente((objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100);
											porfbd = objMtaric.getMgenus002().getComnus().doubleValue(); //%retefuente
											refubd = (objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100; //retefuente
										}else{
											obj.setReteFuente(0.0);
											porfbd = 0.0; //%retefuente
											refubd = 0.0*vabrbd; //retefuente	
										}	
										
									}else{
										obj.setReteFuente(0.0);
										porfbd = 0.0; //%retefuente
										refubd = 0.0*vabrbd; //retefuente
									}
												
								}else{
									disableButton = false;
									RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:pnlBotones");
									 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha Vigencia menor a Hoy");
									
									return;
								}		
								
							}else{
								if(objMtaric.getFevhic().after(fechaHoy)){
									if(objMtaric.getPricic()!=null){
										obj.setImpConsumo((objMtaric.getPricic()*vabrbd)/100);
										poicbd = objMtaric.getPricic();  //% imp consumo
										imcobd = (poicbd*vabrbd)/100; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

									}else{
										obj.setImpConsumo(0.0);
										poicbd = 0.0;  //% imp consumo
										imcobd = poicbd*vabrbd; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

										
									}	
									//LLENADO DE TARIFA

									if(objMtaric.getMgenus002()!=null){
										
										tarifaRtf = objMtaric.getMgenus002();
										tarRetefuente = objMtaric.getMgenus002().getCodius();
										descTarRetefuente  = objMtaric.getMgenus002().getDcttus();

										tfbdbd00.setMgenus003(tarifaRtf);
										tfbdbd00.setTarfbd(tarRetefuente);
										tfbdbd00.setDetabd(descTarRetefuente);
									}
									
									if(exirer.equalsIgnoreCase("N")){ // Si exento iva = N ,,,
										if(objMtaric.getPrvaic()!=null){
											obj.setIva((objMtaric.getPrvaic()*vabrbd)/100);
											poivbd = objMtaric.getPrvaic();
											vaivbd = (objMtaric.getPrvaic()*vabrbd)/100; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}else{
											obj.setIva(0.0);
											poivbd = 0.0;
											vaivbd = 0.0*vabrbd; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}
									}else{
										obj.setIva(0.0);
										poivbd = 0.0;
										vaivbd = 0.0;
										tfbdbd00.setPoivbd(poivbd);
										tfbdbd00.setVaivbd(vaivbd);
									}
									if(reirer.equalsIgnoreCase("S")){ //Si Retiene IVA= S
										obj.setReteIva((pcirer*vaivbd)/100); //Asigno % reteIva.
										reivbd = (pcirer*vaivbd)/100;
										tfbdbd00.setReivbd(reivbd);
									}else{
										obj.setReteIva(0.0);
										reivbd = 0.0;
										tfbdbd00.setReivbd(reivbd);

									}
									if(refrer.equalsIgnoreCase("S")){ //SI Retiene Fuente
										if(objMtaric.getMgenus002().getComnus() != null){
											obj.setReteFuente((objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100);
											porfbd = objMtaric.getMgenus002().getComnus().doubleValue(); //%retefuente
											refubd = (objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100; //retefuente
										}else{
											obj.setReteFuente(0.0);
											porfbd = 0.0; //%retefuente
											refubd = 0.0*vabrbd; //retefuente	
										}	
										
									}else{
										obj.setReteFuente(0.0);
										porfbd = 0.0; //%retefuente
										refubd = 0.0*vabrbd; //retefuente
									}
												
								}else{
									disableButton = false;
									RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:pnlBotones");
									 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha Vigencia menor a Hoy");
									
									return;
								}
								
							}
										
										
						}
								
					}
				}
				if(cafabd == 0.0){
					totalBruto = totalBruto + 0.0;
					totalImpCon = totalImpCon+0.0;
					totalIva = totalIva + 0.0;
					totalReteIva = totalReteIva + 0.0;
					totalRtf = totalRtf + 0.0;
			
				}else{
					totalBruto = totalBruto + vabrbd;
					totalImpCon = totalImpCon+imcobd;
					totalIva = totalIva + vaivbd;
					totalReteIva = totalReteIva + reivbd;
					totalRtf = totalRtf + refubd;

					
				}


				
			}

				
			totolVPagar = (totalBruto) + (totalImpCon) + (totalIva) - (totalReteIva) - (totalRtf);

			listTotales.add(totalBruto);
			listTotales.add(totalImpCon);
			listTotales.add(totalIva);
			listTotales.add(totalReteIva);
			listTotales.add(totalRtf);
			listTotales.add(totolVPagar);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void llenarTfbcbc00(){
		try{
			tfbcbc00 = new Tfbcbc00();
			tfbcbc00Aux = new Tfbcbc00();
			Date date = new Date();
			DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			
			//Consulto que el borrador no este en DB
			tfbcbc00Aux = consultarObjetoTfbcbc00(tqdfqf00);
			if(tfbcbc00Aux!=null){
				
				if(tfbcbc00Aux.getTqdfqf00().getIdcpqf().equals(tfbcbc00.getTqdfqf00().getIdcpqf())){
					removeBorrador(tfbcbc00Aux);
				}
			}
			tfbcbc00 = new Tfbcbc00();
			tfbcbc00.setTqdfqf00(tqdfqf00);
			tfbcbc00.setNrcubc(tqdfqf00.getNrcpqf());
			mtiptx00 = obtenerListaMtiptx00s("Borr_Fac_Est");
			if(mtiptx00!=null){
				tfbcbc00.setMtiptx00(mtiptx00);
			}	
			tfbcbc00.setTqigqg00(tqdfqf00.getTqigqg00());
			listMidiom00 = midiom00Service.listMidiom00ByCriteria(new Midiom00(), getSeControlFactura().getCompaniasUsu());
			listMparme00 = mparme00Service.ListMparmeByCriteria(getSeControlFactura().getCompaniasUsu(),new Mparme00());
			for(Mparme00 obj: listMparme00){
				if(obj.getNopame()!=null && obj.getVapame() != null){
					if(obj.getNopame().equalsIgnoreCase("Fac-Nal-Dian")){
						tipoFactura = obj.getVapame();

					}	
				}else{
					tipoFactura = "";
				}	
			}
			//tipoFactura = (String) super.getSeControlFactura().PARAMETROS.get("Fac-Nal-Dian");
			listMgenus00 = mgenus00Service.listMgenus00ByCriteria(new Mgenus00(), getSeControlFactura().getCompaniasUsu());
			for(Mgenus00 obj: listMgenus00){
				if(obj.getCodius()!=null && tipoFactura != null){
					if(obj.getCodius().equalsIgnoreCase(tipoFactura)){
						if(obj.getComaus()!=null){
							tfbcbc00.setTifabc(obj.getComaus());
						}else{
							tfbcbc00.setTifabc("");
						}	
					}	
				}else{
					tfbcbc00.setTifabc("");
				}
				
				if(obj.getMconca00()!=null){
					if(obj.getRegius() && obj.getMconca00().equals(compaIdccia) && obj.getMtipre00().getTipore().equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Impuestos"))){
						if(obj.getCm1aus().equalsIgnoreCase("ICO")){
							tfbcbc00.setIicobc(obj.getIdtrus());
							tfbcbc00.setCoicbc(obj.getComaus());
						}
						if(obj.getCm1aus().equalsIgnoreCase("IVA")){
							tfbcbc00.setIiivbc(obj.getIdtrus());
							tfbcbc00.setCoivbc(obj.getComaus());
						}
						if(obj.getCm1aus().equalsIgnoreCase("RIV")){
							tfbcbc00.setIrivbc(obj.getIdtrus());
							tfbcbc00.setCoribc(obj.getComaus());
						}
						if(obj.getCm1aus().equalsIgnoreCase("RFT")){
							tfbcbc00.setIrfvbc(obj.getIdtrus());
							tfbcbc00.setCorfbc(obj.getComaus());
						}
						
						
					}else{
						if(obj.getRegius() && obj.getMtipre00().getTipore().equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Impuestos"))){
							
							if(obj.getCm1aus().equalsIgnoreCase("ICO")){
								tfbcbc00.setIicobc(obj.getIdtrus());
								tfbcbc00.setCoicbc(obj.getComaus());
							}
							if(obj.getCm1aus().equalsIgnoreCase("IVA")){
								tfbcbc00.setIiivbc(obj.getIdtrus());
								tfbcbc00.setCoivbc(obj.getComaus());
							}
							if(obj.getCm1aus().equalsIgnoreCase("RIV")){
								tfbcbc00.setIrivbc(obj.getIdtrus());
								tfbcbc00.setCoribc(obj.getComaus());
							}
							if(obj.getCm1aus().equalsIgnoreCase("RFT")){
								tfbcbc00.setIrfvbc(obj.getIdtrus());
								tfbcbc00.setCorfbc(obj.getComaus());
							}
								
						}
						
					}
					
				}
				
				
			}
			
			if(tqdfqf00.getMgente00()!=null){
				tfbcbc00.setMgente00(tqdfqf00.getMgente00());
			}else{
				tfbcbc00.setMgente00(tqdfqf00.getTqigqg00().getMgente00());
			}
			tfbcbc00.setMgenus001(tqdfqf00.getMgenus002());
			tfbcbc00.setMofabc(tqdfqf00.getMgenus002().getCodius());
			tfbcbc00.setMgenus002(tqdfqf00.getMgenus001());
			tfbcbc00.setMepabc(tqdfqf00.getMgenus001().getCodius());
			tfbcbc00.setMidiom00(listMidiom00.get(0));//P
			tfbcbc00.setMgenus003(tqdfqf00.getTqigqg00().getMgenus002());
			tfbcbc00.setMgenus004(tqdfqf00.getTqigqg00().getMgenus003());
			tfbcbc00.setNrocbc(tqdfqf00.getTqigqg00().getNrocqg());
			tfbcbc00.setDescbc(tqdfqf00.getDecpqf());
			tfbcbc00.setObfabc(tqdfqf00.getObifqf());
			tfbcbc00.setMconca00(tqdfqf00.getTqigqg00().getMconca00());
			tfbcbc00.setPzdibc(tqdfqf00.getPzdiqf());
			tfbcbc00.setFegebc(new Date());
			tfbcbc00.setHogebc(hourFormat.format(date));
			tfbcbc00.setCorabc("pendiente");//P
			tfbcbc00.setFerdbc(new Date());//p
			tfbcbc00.setFerhbc(new Date());//p
			if(tqdfqf00.getMgente00()!=null){
				tfbcbc00.setNoclbc(tqdfqf00.getMgente00().getDctate());
				tfbcbc00.setDiclbc(tqdfqf00.getMgente00().getDirete());
				tfbcbc00.setMgenus008(tqdfqf00.getMgente00().getMgenus001());
				tfbcbc00.setTidibc(tqdfqf00.getMgente00().getMgenus001().getCodius());
				tfbcbc00.setDedibc(tqdfqf00.getMgente00().getMgenus001().getDcttus());
				tfbcbc00.setNrdibc(tqdfqf00.getMgente00().getNrnite());
				tfbcbc00.setMgenus009(tqdfqf00.getMgente00().getMgenus011()); 
				tfbcbc00.setPaclbc(tqdfqf00.getMgente00().getMgenus011().getCodius());//null?
				tfbcbc00.setDepsbc(tqdfqf00.getMgente00().getMgenus011().getDcttus());
				tfbcbc00.setMgenus005(tqdfqf00.getMgente00().getMgenus002());
				tfbcbc00.setDeclbc(tqdfqf00.getMgente00().getMgenus002().getCodius());
				tfbcbc00.setDedpbc(tqdfqf00.getMgente00().getMgenus002().getDcttus());
				tfbcbc00.setMgenus006(tqdfqf00.getMgente00().getMgenus003());
				tfbcbc00.setCdclbc(tqdfqf00.getMgente00().getMgenus003().getCodius());
				tfbcbc00.setDecubc(tqdfqf00.getMgente00().getMgenus003().getDcttus());
				tfbcbc00.setMgenus007(tqdfqf00.getMgente00().getMgenus004());
				tfbcbc00.setPeclbc(tqdfqf00.getMgente00().getMgenus004().getCodius());
				tfbcbc00.setDepjbc(tqdfqf00.getMgente00().getMgenus004().getDcttus());
			}else{
				tfbcbc00.setNoclbc(tqdfqf00.getTqigqg00().getMgente00().getDctate());
				tfbcbc00.setDiclbc(tqdfqf00.getTqigqg00().getMgente00().getDirete());
				tfbcbc00.setMgenus008(tqdfqf00.getTqigqg00().getMgente00().getMgenus001());
				tfbcbc00.setTidibc(tqdfqf00.getTqigqg00().getMgente00().getMgenus001().getCodius());
				tfbcbc00.setDedibc(tqdfqf00.getTqigqg00().getMgente00().getMgenus001().getDcttus());
				tfbcbc00.setNrdibc(tqdfqf00.getTqigqg00().getMgente00().getNrnite());
				tfbcbc00.setMgenus009(tqdfqf00.getTqigqg00().getMgente00().getMgenus011()); 
				tfbcbc00.setPaclbc(tqdfqf00.getTqigqg00().getMgente00().getMgenus011().getCodius());
				tfbcbc00.setDepsbc(tqdfqf00.getTqigqg00().getMgente00().getMgenus011().getDcttus());
				tfbcbc00.setMgenus005(tqdfqf00.getTqigqg00().getMgente00().getMgenus002());
				tfbcbc00.setDeclbc(tqdfqf00.getTqigqg00().getMgente00().getMgenus002().getCodius());
				tfbcbc00.setDedpbc(tqdfqf00.getTqigqg00().getMgente00().getMgenus002().getDcttus());
				tfbcbc00.setMgenus006(tqdfqf00.getTqigqg00().getMgente00().getMgenus003());
				tfbcbc00.setCdclbc(tqdfqf00.getTqigqg00().getMgente00().getMgenus003().getCodius());
				tfbcbc00.setDecubc(tqdfqf00.getTqigqg00().getMgente00().getMgenus003().getDcttus());
				tfbcbc00.setMgenus007(tqdfqf00.getTqigqg00().getMgente00().getMgenus004());
				tfbcbc00.setPeclbc(tqdfqf00.getTqigqg00().getMgente00().getMgenus004().getCodius());
				tfbcbc00.setDepjbc(tqdfqf00.getTqigqg00().getMgente00().getMgenus004().getDcttus());
			}
			tfbcbc00.setMcotes00(tqdfqf00.getMcotes00());
			tfbcbc00.setPncobc(tqdfqf00.getMcotes00().getPrntes());
			tfbcbc00.setSncobc(tqdfqf00.getMcotes00().getSentes());
			tfbcbc00.setPacobc(tqdfqf00.getMcotes00().getPrates());
			tfbcbc00.setSacobc(tqdfqf00.getMcotes00().getSeates());
			tfbcbc00.setCufebc(" ");
			
			//Cola
			tfbcbc00.setRegcbc(true);
			tfbcbc00.setFeacbc(new Date());
			tfbcbc00.setUsecbc(getSeControlFactura().codusu);
			tfbcbc00.setPrgcbc(getSeControlFactura().aplusu);
			tfbcbc00.setMaqcbc(getSeControlFactura().ip);			
		
	} catch (Exception e) {
		e.printStackTrace();
		UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
	}
}
	
	public void llenarTfbdbd00(){
		try{
			
			totalBruto = 0.0;
			totalImpCon = 0.0;
			totalIva = 0.0;
			totalReteIva = 0.0;
			totalRtf = 0.0;
			totolVPagar = 0.0;
			vabrbd = 0.0;
			poicbd = 0.0;
			cafabd = 0.0;
			imcobd = 0.0; //Imp consumo x detalle
			poivbd = 0.0;
			vaivbd = 0.0; //Impuesto IVA
			reivbd = 0.0;
			porfbd = 0.0; //%retefuente
			refubd = 0.0;

			
			Date fechaHoy = new Date();
			Integer contador=1; 
			listTotales = new ArrayList<Double>();
			listMtaric00 = mtaric00Service.listMtaric00ByCriteria(new Mtaric00(), getSeControlFactura().getCompaniasUsu());
			listMrerer00 = mrerer00Service.listMrerer00ByCriteria(new Mrerer00(), getSeControlFactura().getCompaniasUsu());			
			
			//Capturo datos de MAestro Reglas Mrere00
			for(Mrerer00 objMrerer:listMrerer00){
				if(idTipoContriCompa !=null || objMrerer.getMregcg001()!=null || objMrerer.getMregcg002()!=null || tqdfqf00.getTqigqg00().getMgente00().getMregcg005()!=null ){
					if(idTipoContriCompa.equals(objMrerer.getMregcg002().getIdiecg()) && objMrerer.getMregcg001().getIdiecg().equals(tqdfqf00.getTqigqg00().getMgente00().getMregcg005().getIdiecg())){

						exirer = objMrerer.getExirer();
						reirer = objMrerer.getReirer();
						refrer = objMrerer.getRefrer();
						pcirer = objMrerer.getPcirer();
						tfbdbd00.setPoribd(pcirer);
						 disableButton = true;

					}
				}else{
					exirer = "";
					reirer = "";
					refrer = "";
					pcirer = 0.0;
					disableButton = false;
					tfbdbd00.setPoribd(pcirer);
					UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "No hay un registro valido en el maestro de reglas para esta cotización");
					RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:pnlBotones");
					return;
				}
			}			
			
			
			tfbcbc00.setTfbdbd00s(new ArrayList<Tfbdbd00>());
			if (!Utils.isEmptyOrNull(listTqdrfg00Aux)){
			for(Tqdrfg00 obj: listTqdrfg00Aux){
				
				
				
				tfbdbd00 = new Tfbdbd00();
				tfbdbd00.setTfbcbc00(getTfbcbc00());
				
				tfbdbd00.setTqdrqr00(obj.getTqdpqp00().getTqdrqr00());
				tfbdbd00.setTqdrfg00(obj);
				tfbdbd00.setLiorbd(contador);
				contador++;
				
				tfbdbd00.setPoribd(pcirer);
				
				tfbdbd00.setDerfbd(obj.getTqdpqp00().getTqdrqr00().getDetrqr());
				tfbdbd00.setTqdpqp00(obj.getTqdpqp00());
				tfbdbd00.setCoprbd(obj.getTqdpqp00().getMpropr00().getCodcpr());
				tfbdbd00.setDeprbd(obj.getTqdpqp00().getDeprqp());
				tfbdbd00.setDempbd(obj.getTqdpqp00().getMpropr00().getDscrpr());
				tfbdbd00.setMgenus001(obj.getTqdpqp00().getMpropr00().getMgenus009());
				tfbdbd00.setTibsbd(obj.getTqdpqp00().getMpropr00().getMgenus009().getCodius());
				tfbdbd00.setMgenus002(obj.getTqdpqp00().getMgenus00());
				tfbdbd00.setUnmebd(obj.getTqdpqp00().getMgenus00().getCodius());
				if(obj.getTqdpqp00().getVamiqp().equals("M")){
					tfbdbd00.setVufabd(obj.getTqdpqp00().getVumpqp());
				}else{
					tfbdbd00.setVufabd(obj.getTqdpqp00().getVupiqp());
				}
				
				//COLA
				tfbdbd00.setRegcbd(true);
				tfbdbd00.setUsecbd(getSeControlFactura().codusu);
				tfbdbd00.setPrgcbd(getSeControlFactura().aplusu);
				tfbdbd00.setFeacbd(new Date());
				tfbdbd00.setMaqcbd(getSeControlFactura().ip);
				
				//Etapa Calcular datos (no olvidar los SET de tfbdbd00)
				
				if(obj.getCafpfg()!=null){
					cafabd = obj.getCafpfg();//Cant facturada cafpfg
					tfbdbd00.setCafabd(cafabd);
				}else{
					cafabd = 0.0;
					tfbdbd00.setCafabd(cafabd);
				}
				if(obj.getTqdpqp00().getVamiqp().equals("M")){
					if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVumpqp()!=null){
						vabrbd = obj.getTqdpqp00().getVumpqp()*obj.getCafpfg(); //Valor bruto
						obj.setValorBruto(vabrbd);
						tfbdbd00.setVabrbd(vabrbd);
					}
				}else{
					if(obj.getCafpfg()!=null && obj.getTqdpqp00().getVupiqp()!=null){
						vabrbd = obj.getTqdpqp00().getVupiqp()*obj.getCafpfg();
						obj.setValorBruto(vabrbd);
						tfbdbd00.setVabrbd(vabrbd);
					}
				}
				for(Mtaric00 objMtaric: listMtaric00){
					if(obj.getTqdpqp00().getMpropr00().getMgenus009()!=null && objMtaric.getMgenus001()!=null ){
						if(obj.getTqdpqp00().getMpropr00().getMgenus009().getIdtrus().equals(objMtaric.getMgenus001().getIdtrus())){
							if(objMtaric.getMgenus003()!=null){ //Si la ciudad no es null 

								if(objMtaric.getFevhic().after(fechaHoy)){
									if(objMtaric.getPricic()!=null){
										obj.setImpConsumo((objMtaric.getPricic()*vabrbd)/100);
										poicbd = objMtaric.getPricic();  //% imp consumo
										imcobd = (poicbd*vabrbd)/100; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

									}else{
										obj.setImpConsumo(0.0);
										poicbd = 0.0;  //% imp consumo
										imcobd = poicbd*vabrbd; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

										
									}	
									//LLENADO DE TARIFA

									if(objMtaric.getMgenus002()!=null){
										
										tarifaRtf = objMtaric.getMgenus002();
										tarRetefuente = objMtaric.getMgenus002().getCodius();
										descTarRetefuente  = objMtaric.getMgenus002().getDcttus();

										tfbdbd00.setMgenus003(tarifaRtf);
										tfbdbd00.setTarfbd(tarRetefuente);
										tfbdbd00.setDetabd(descTarRetefuente);
									}
									
									if(exirer.equalsIgnoreCase("N")){ // Si exento iva = N ,,,
										if(objMtaric.getPrvaic()!=null){
											obj.setIva((objMtaric.getPrvaic()*vabrbd)/100);
											poivbd = objMtaric.getPrvaic();
											vaivbd = (objMtaric.getPrvaic()*vabrbd)/100; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}else{
											obj.setIva(0.0);
											poivbd = 0.0;
											vaivbd = 0.0*vabrbd; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}
									}else{
										obj.setIva(0.0);
										poivbd = 0.0;
										vaivbd = 0.0;
										tfbdbd00.setPoivbd(poivbd);
										tfbdbd00.setVaivbd(vaivbd);
									}
									if(reirer.equalsIgnoreCase("S")){ //Si Retiene IVA= S
										obj.setReteIva((pcirer*vaivbd)/100); //Asigno % reteIva.
										reivbd = (pcirer*vaivbd)/100;
										tfbdbd00.setReivbd(reivbd);
									}else{
										obj.setReteIva(0.0);
										reivbd = 0.0;
										tfbdbd00.setReivbd(reivbd);

									}
									if(refrer.equalsIgnoreCase("S")){ //SI Retiene Fuente
										if(objMtaric.getMgenus002().getComnus() != null){
											obj.setReteFuente((objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100);
											porfbd = objMtaric.getMgenus002().getComnus().doubleValue(); //%retefuente
											refubd = (objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100; //retefuente
											tfbdbd00.setPorfbd(porfbd);
											tfbdbd00.setRefubd(refubd);
										}else{
											obj.setReteFuente(0.0);
											porfbd = 0.0; //%retefuente
											refubd = 0.0*vabrbd; //retefuente
											tfbdbd00.setPorfbd(0.0);
											tfbdbd00.setRefubd(0.0);
										}	
										
									}else{
										obj.setReteFuente(0.0);
										porfbd = 0.0; //%retefuente
										refubd = 0.0*vabrbd; //retefuente
										tfbdbd00.setPorfbd(0.0);
										tfbdbd00.setRefubd(0.0);
									}
								}else{
									disableButton = false;
									RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:pnlBotones");
									 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha Vigencia menor a Hoy");
									return;
								}		
								
							}else{ //Sin ciudad
								if(objMtaric.getFevhic().after(fechaHoy)){
									if(objMtaric.getPricic()!=null){
										obj.setImpConsumo((objMtaric.getPricic()*vabrbd)/100);
										poicbd = objMtaric.getPricic();  //% imp consumo
										imcobd = (poicbd*vabrbd)/100; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

									}else{
										obj.setImpConsumo(0.0);
										poicbd = 0.0;  //% imp consumo
										imcobd = poicbd*vabrbd; //Imp consumo x detalle
										tfbdbd00.setPoicbd(poicbd);
										tfbdbd00.setImcobd(imcobd);

										
									}	
									//LLENADO DE TARIFA

									if(objMtaric.getMgenus002()!=null){
										
										tarifaRtf = objMtaric.getMgenus002();
										tarRetefuente = objMtaric.getMgenus002().getCodius();
										descTarRetefuente  = objMtaric.getMgenus002().getDcttus();

										tfbdbd00.setMgenus003(tarifaRtf);
										tfbdbd00.setTarfbd(tarRetefuente);
										tfbdbd00.setDetabd(descTarRetefuente);
									}
									
									if(exirer.equalsIgnoreCase("N")){ // Si exento iva = N ,,,
										if(objMtaric.getPrvaic()!=null){
											obj.setIva((objMtaric.getPrvaic()*vabrbd)/100);
											poivbd = objMtaric.getPrvaic();
											vaivbd = (objMtaric.getPrvaic()*vabrbd)/100; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}else{
											obj.setIva(0.0);
											poivbd = 0.0;
											vaivbd = 0.0*vabrbd; //Impuesto IVA
											tfbdbd00.setPoivbd(poivbd);
											tfbdbd00.setVaivbd(vaivbd);

										}
									}else{
										obj.setIva(0.0);
										poivbd = 0.0;
										vaivbd = 0.0;
										tfbdbd00.setPoivbd(poivbd);
										tfbdbd00.setVaivbd(vaivbd);
									}
									if(reirer.equalsIgnoreCase("S")){ //Si Retiene IVA= S
										obj.setReteIva((pcirer*vaivbd)/100); //Asigno % reteIva.
										reivbd = (pcirer*vaivbd)/100;
										tfbdbd00.setReivbd(reivbd);
									}else{
										obj.setReteIva(0.0);
										reivbd = 0.0;
										tfbdbd00.setReivbd(reivbd);

									}
									if(refrer.equalsIgnoreCase("S")){ //SI Retiene Fuente
										if(objMtaric.getMgenus002().getComnus() != null){
											obj.setReteFuente((objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100);
											porfbd = objMtaric.getMgenus002().getComnus().doubleValue(); //%retefuente
											refubd = (objMtaric.getMgenus002().getComnus().doubleValue()*vabrbd)/100; //retefuente
											tfbdbd00.setPorfbd(porfbd);
											tfbdbd00.setRefubd(refubd);
											
										}else{
											obj.setReteFuente(0.0);
											porfbd = 0.0; //%retefuente
											refubd = 0.0*vabrbd; //retefuente
											tfbdbd00.setPorfbd(0.0);
											tfbdbd00.setRefubd(0.0);
										}	
										
									}else{
										obj.setReteFuente(0.0);
										porfbd = 0.0; //%retefuente
										refubd = 0.0*vabrbd; //retefuente
										tfbdbd00.setPorfbd(porfbd);
										tfbdbd00.setRefubd(0.0);
									}
								}else{
									disableButton = false;
									RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:pnlBotones");
									 UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, "Fecha Vigencia menor a Hoy");
									return;
								}
								
							}
										
										
						}
								
					}
				}

				if(cafabd == 0.0){
					
					totalBruto = totalBruto + 0.0;
					totalImpCon = totalImpCon+0.0;
					totalIva = totalIva + 0.0;
					totalReteIva = totalReteIva + 0.0;
					totalRtf = totalRtf + 0.0;
			
				}else{
					
					totalBruto = totalBruto + vabrbd;
					totalImpCon = totalImpCon+imcobd;
					totalIva = totalIva + vaivbd;
					totalReteIva = totalReteIva + reivbd;
					totalRtf = totalRtf + refubd;
					
				}
				//Lleno la lista
				//tfbcbc00.getTfbdbd00s().add(tfbdbd00);
				todebd = tfbdbd00.getVabrbd() + tfbdbd00.getImcobd() +tfbdbd00.getVaivbd() - tfbdbd00.getReivbd() - tfbdbd00.getRefubd();
				tfbdbd00.setTodebd(todebd);
				//System.out.println("tfbdbd00: "+tfbdbd00);
				tfbcbc00.getTfbdbd00s().add(tfbdbd00);
			}
			
			totolVPagar = ((totalBruto) + (totalImpCon) + (totalIva) - (totalReteIva) - (totalRtf));
			listTotales.add(totalBruto);
			listTotales.add(totalImpCon);
			listTotales.add(totalIva);
			listTotales.add(totalReteIva);
			listTotales.add(totalRtf);
			listTotales.add(totolVPagar);
			
			
			}	
			
			
			
			RequestContext.getCurrentInstance().update("formTfbdbd00Detalle"); 
			RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:dTabletqdrfg00");
			RequestContext.getCurrentInstance().update(":formDetalle:messages");
			RequestContext.getCurrentInstance().update(":formDetalle");
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	private boolean setBandera(Tqdfqf00 tqdfqf00) {

		pdfRendered = false;
		listTfbcbc00 = tfbcbc00Service.listTfbcbc00FacByCriteria(new Tfbcbc00(),getSeControlFactura().getCompaniasUsu());			
		
		for(Tfbcbc00 obj1: listTfbcbc00){
			if(obj1.getTqdfqf00().getIdcpqf().equals(tqdfqf00.getIdcpqf())){
				pdfRendered = true;
			}

		}
		return pdfRendered;
}	
		 
	public void generar(){
		try {
			//LLenar borrador cabecera
			
			llenarTfbcbc00();
			
			//llenar borrador Detalle
			llenarTfbdbd00();
				
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
					tfbcbc00Service.save(tfbcbc00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tfbcbc00.toString(),tfbcbc00.toStringId(),tfbcbc00Clone.toString()):null);
			} else {
				tfbcbc00Service.save(tfbcbc00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,tfbcbc00.toString(),tfbcbc00.toStringId(),null):null);
				
			 }
			
			listTqdfqf00Aux = tqdfqf00Service.listTqdfqf00ByCriteria(tqdfqf00,getSeControlFactura().getCompaniasUsu());
			
			for(Tqdfqf00 obj2: listTqdfqf00Aux){
				setBandera(obj2);
				obj2.setPdfBorrador(pdfRendered);
				
			}
			seTfbcbc00.setShowList(false);
			update=null;
			clearAllFilters2();
			RequestContext.getCurrentInstance().update("formDetalle:dTableTfbdbd00");
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('generDialog').hide();");
			RequestContext.getCurrentInstance().update("formTfbdbd00Detalle"); 
			RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle:dTabletqdrfg00");
			UtilsJSF.facesLogBorrador(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
			

		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				//UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			}else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
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
	
	public void regresarCuotas(){
		try {
			update=null;
			RequestContext.getCurrentInstance().update("formTfbdbd00Detalle"); 
			RequestContext.getCurrentInstance().update(":formDetalle:dTableTfbdbd00"); 
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void totales(){
		try {
			update=null;
			
			//Totales
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	
	
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			tfbdbd00 = (Tfbdbd00) event.getComponent().getAttributes().get("tfbdbd00");
			tfbdbd00Service.removeTfbdbd00(tfbdbd00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR,tfbdbd00.toString(),tfbdbd00.toStringId()):null);
			cargarDatos();
			UtilsJSF.resetDTable(":formDetalle:tabView:dTableTfbdbd00");
			 RequestContext.getCurrentInstance().update(":formDetalle");
			 RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle");
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
			//getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialog(CloseEvent event) {
		setUpdate(null);
		//RequestContext.getCurrentInstance().update(":formTfbdbd00Detalle");
		
	}
	
	public ITfbdbd00Service getTfbdbd00Service() {
		return tfbdbd00Service;
	}
	public void setTfbdbd00Service(ITfbdbd00Service tfbdbd00Service) {
		this.tfbdbd00Service = tfbdbd00Service;
	}
	public SeListas getSeListas() {
		return seListas;
	}
	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}
	public SeTfbcbc00 getSeTfbcbc00() {
		return seTfbcbc00;
	}
	public void setSeTfbcbc00(SeTfbcbc00 seTfbcbc00) {
		this.seTfbcbc00 = seTfbcbc00;
	}
	public Tfbdbd00 getTfbdbd00() {
		if(tfbdbd00==null){
			tfbdbd00 = new Tfbdbd00();
		}
		return this.tfbdbd00;
	}
	public void setTfbdbd00(Tfbdbd00 tfbdbd00) {
		this.tfbdbd00 = tfbdbd00;
	}
	
	public List<Tqdfqf00> getListTfbdbd00() {
		return listTfbdbd00;
	}


	public void setListTfbdbd00(List<Tqdfqf00> listTfbdbd00) {
		this.listTfbdbd00 = listTfbdbd00;
	}


	public List<Tfbdbd00> getFilteredTfbdbd00() {
		return filteredTfbdbd00;
	}
	public void setFilteredTfbdbd00(List<Tfbdbd00> filteredTfbdbd00) {
		this.filteredTfbdbd00 = filteredTfbdbd00;
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


	public String getPaternDec_CantiM() {
		return paternDec_CantiM;
	}

	public void setPaternDec_CantiM(String paternDec_CantiM) {
		this.paternDec_CantiM = paternDec_CantiM;
	}
	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}


	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}


	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public Tqdpqp00 getTqdpqp00() {
		if(tqdpqp00==null)
		{
			tqdpqp00= new Tqdpqp00();
		}
		return tqdpqp00;

	}

	public void setTqdpqp00(Tqdpqp00 tqdpqp00) {
		this.tqdpqp00 = tqdpqp00;
	}

	public List<Tqdpqp00> getFilteredTqdpqp00() {
		return filteredTqdpqp00;
	}

	public void setFilteredTqdpqp00(List<Tqdpqp00> filteredTqdpqp00) {
		this.filteredTqdpqp00 = filteredTqdpqp00;
	}

	public List<Tqdpqp00> getListTqdpqp00Remove() {
		return listTqdpqp00Remove;
	}

	public void setListTqdpqp00Remove(List<Tqdpqp00> listTqdpqp00Remove) {
		this.listTqdpqp00Remove = listTqdpqp00Remove;
	}



	public Tqigqg00 getTqigqg00() {
		return tqigqg00;
	}

	public void setTqigqg00(Tqigqg00 tqigqg00) {
		this.tqigqg00 = tqigqg00;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<Tqdpqp00> getListTqdpqp00() {
		return listTqdpqp00;
	}

	public void setListTqdpqp00(List<Tqdpqp00> listTqdpqp00) {
		this.listTqdpqp00 = listTqdpqp00;
	}

	public ITqdpqp00Service getTqdpqp00Service() {
		return tqdpqp00Service;
	}

	public void setTqdpqp00Service(ITqdpqp00Service tqdpqp00Service) {
		this.tqdpqp00Service = tqdpqp00Service;
	}

	public ITqdrqr00Service getTqdrqr00Service() {
		return tqdrqr00Service;
	}

	public void setTqdrqr00Service(ITqdrqr00Service tqdrqr00Service) {
		this.tqdrqr00Service = tqdrqr00Service;
	}

	public Tqdrqr00 getTqdrqr00() {
		return tqdrqr00;
	}

	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}

	public List<Tqdrqr00> getListTqdrqr00() {
		return listTqdrqr00;
	}

	public void setListTqdrqr00(List<Tqdrqr00> listTqdrqr00) {
		this.listTqdrqr00 = listTqdrqr00;
	}

	public Tqdpqp00 getTfbdbd00Aux() {
		return Tfbdbd00Aux;
	}

	public void setTfbdbd00Aux(Tqdpqp00 tfbdbd00Aux) {
		Tfbdbd00Aux = tfbdbd00Aux;
	}

	public List<Tqdrfg00> getListTqdrfg00() {
		return listTqdrfg00;
	}

	public void setListTqdrfg00(List<Tqdrfg00> listTqdrfg00) {
		this.listTqdrfg00 = listTqdrfg00;
	}

	public Tqdrfg00 getTqdrfg00() {
		return tqdrfg00;
	}

	public void setTqdrfg00(Tqdrfg00 tqdrfg00) {
		this.tqdrfg00 = tqdrfg00;
	}

	public ITqdrfg00Service getTqdrfg00Service() {
		return tqdrfg00Service;
	}

	public void setTqdrfg00Service(ITqdrfg00Service tqdrfg00Service) {
		this.tqdrfg00Service = tqdrfg00Service;
	}

	public boolean isFlagTqdrfg00() {
		return flagTqdrfg00;
	}

	public void setFlagTqdrfg00(boolean flagTqdrfg00) {
		this.flagTqdrfg00 = flagTqdrfg00;
	}

	public boolean isFlagTfbdbd00() {
		return flagTfbdbd00;
	}

	public void setFlagTfbdbd00(boolean flagTfbdbd00) {
		this.flagTfbdbd00 = flagTfbdbd00;
	}

	public Tfbdbd00 getTfbdbd00New() {
		return tfbdbd00New;
	}

	public void setTfbdbd00New(Tfbdbd00 tfbdbd00New) {
		this.tfbdbd00New = tfbdbd00New;
	}

	public List<Tqdrfg00> getListTqdrfg00Aux() {
		return listTqdrfg00Aux;
	}

	public void setListTqdrfg00Aux(List<Tqdrfg00> listTqdrfg00Aux) {
		this.listTqdrfg00Aux = listTqdrfg00Aux;
	}

	public List<Tqdrfg00> getFilteredTqdrfg00() {
		return filteredTqdrfg00;
	}

	public void setFilteredTqdrfg00(List<Tqdrfg00> filteredTqdrfg00) {
		this.filteredTqdrfg00 = filteredTqdrfg00;
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

	public SeTqdpqp00 getSeTqdpqp00() {
		return seTqdpqp00;
	}

	public void setSeTqdpqp00(SeTqdpqp00 seTqdpqp00) {
		this.seTqdpqp00 = seTqdpqp00;
	}

	public IMgente00Service getMgente00Service() {
		return mgente00Service;
	}

	public void setMgente00Service(IMgente00Service mgente00Service) {
		this.mgente00Service = mgente00Service;
	}

	public SeTqdrqr00 getSeTqdrqr00() {
		return seTqdrqr00;
	}

	public void setSeTqdrqr00(SeTqdrqr00 seTqdrqr00) {
		this.seTqdrqr00 = seTqdrqr00;
	}

	public SeMgente00 getSeMgente00() {
		return seMgente00;
	}

	public void setSeMgente00(SeMgente00 seMgente00) {
		this.seMgente00 = seMgente00;
	}

	public ITqdfqf00Service getTqdfqf00Service() {
		return tqdfqf00Service;
	}

	public void setTqdfqf00Service(ITqdfqf00Service tqdfqf00Service) {
		this.tqdfqf00Service = tqdfqf00Service;
	}

	public boolean isShowAlerta() {
		return showAlerta;
	}

	public void setShowAlerta(boolean showAlerta) {
		this.showAlerta = showAlerta;
	}

	public SeTqdfqf00 getSeTqdfqf00() {
		return seTqdfqf00;
	}

	public void setSeTqdfqf00(SeTqdfqf00 seTqdfqf00) {
		this.seTqdfqf00 = seTqdfqf00;
	}

	public List<Tqdfqf00> getListTqdfqf00Aux() {
		return listTqdfqf00Aux;
	}

	public void setListTqdfqf00Aux(List<Tqdfqf00> listTqdfqf00Aux) {
		this.listTqdfqf00Aux = listTqdfqf00Aux;
	}

	public List<Tqdfqf00> getListTqdfqf00() {
		return listTqdfqf00;
	}

	public void setListTqdfqf00(List<Tqdfqf00> listTqdfqf00) {
		this.listTqdfqf00 = listTqdfqf00;
	}

	public Tqdfqf00 getTqdfqf00() {
		return tqdfqf00;
	}

	public void setFilteredTqdfqf00(List<Tqdfqf00> filteredTqdfqf00) {
		this.filteredTqdfqf00 = filteredTqdfqf00;
	}

	public List<Tqdfqf00> getFilteredTqdfqf00() {
		return filteredTqdfqf00;
	}
	
	public void setTqdfqf00(Tqdfqf00 tqdfqf00) {
		this.tqdfqf00 = tqdfqf00;
	}

	public IMtaric00Service getMtaric00Service() {
		return mtaric00Service;
	}

	public void setMtaric00Service(IMtaric00Service mtaric00Service) {
		this.mtaric00Service = mtaric00Service;
	}

	public List<Mtaric00> getListMtaric00() {
		return listMtaric00;
	}

	public void setListMtaric00(List<Mtaric00> listMtaric00) {
		this.listMtaric00 = listMtaric00;
	}

	public List<Mrerer00> getListMrerer00() {
		return listMrerer00;
	}

	public void setListMrerer00(List<Mrerer00> listMrerer00) {
		this.listMrerer00 = listMrerer00;
	}

	public IMrerer00Service getMrerer00Service() {
		return mrerer00Service;
	}

	public void setMrerer00Service(IMrerer00Service mrerer00Service) {
		this.mrerer00Service = mrerer00Service;
	}

	public Tfbcbc00 getTfbcbc00() {
		return tfbcbc00;
	}

	public void setTfbcbc00(Tfbcbc00 tfbcbc00) {
		this.tfbcbc00 = tfbcbc00;
	}

	public ITfbcbc00Service getTfbcbc00Service() {
		return tfbcbc00Service;
	}

	public void setTfbcbc00Service(ITfbcbc00Service tfbcbc00Service) {
		this.tfbcbc00Service = tfbcbc00Service;
	}

	public IMtiptx00Service getMtiptx00Service() {
		return mtiptx00Service;
	}

	public void setMtiptx00Service(IMtiptx00Service mtiptx00Service) {
		this.mtiptx00Service = mtiptx00Service;
	}

	public Mtiptx00 getMtiptx00() {
		return mtiptx00;
	}

	public void setMtiptx00(Mtiptx00 mtiptx00) {
		this.mtiptx00 = mtiptx00;
	}



	public List<Tfbdbd00> getListTfbdbd00Cons() {
		return listTfbdbd00Cons;
	}

	public void setListTfbdbd00Cons(List<Tfbdbd00> listTfbdbd00Cons) {
		this.listTfbdbd00Cons = listTfbdbd00Cons;
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public Double getVaivbd() {
		return vaivbd;
	}

	public void setVaivbd(Double vaivbd) {
		this.vaivbd = vaivbd;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public List<Mgenus00> getListMgenus00() {
		return listMgenus00;
	}

	public void setListMgenus00(List<Mgenus00> listMgenus00) {
		this.listMgenus00 = listMgenus00;
	}

	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}

	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
	}

	public List<Midiom00> getListMidiom00() {
		return listMidiom00;
	}

	public void setListMidiom00(List<Midiom00> listMidiom00) {
		this.listMidiom00 = listMidiom00;
	}

	public IMidiom00Service getMidiom00Service() {
		return midiom00Service;
	}

	public void setMidiom00Service(IMidiom00Service midiom00Service) {
		this.midiom00Service = midiom00Service;
	}

	public Double getPorfbd() {
		return porfbd;
	}

	public void setPorfbd(Double porfbd) {
		this.porfbd = porfbd;
	}

	public Double getPoivbd() {
		return poivbd;
	}

	public void setPoivbd(Double poivbd) {
		this.poivbd = poivbd;
	}

	public Double getPoicbd() {
		return poicbd;
	}

	public void setPoicbd(Double poicbd) {
		this.poicbd = poicbd;
	}

	public Double getCafabd() {
		return cafabd;
	}

	public void setCafabd(Double cafabd) {
		this.cafabd = cafabd;
	}

	public Double getVabrbd() {
		return vabrbd;
	}

	public void setVabrbd(Double vabrbd) {
		this.vabrbd = vabrbd;
	}

	public Double getImcobd() {
		return imcobd;
	}

	public void setImcobd(Double imcobd) {
		this.imcobd = imcobd;
	}

	public Double getReivbd() {
		return reivbd;
	}

	public void setReivbd(Double reivbd) {
		this.reivbd = reivbd;
	}

	public Double getRefubd() {
		return refubd;
	}
	
	public void setRefubd(Double refubd) {
		this.refubd = refubd;
	}

	public Tqdfqf00 getTfbdbd00Clone() {
		return tfbdbd00Clone;
	}

	public void setTfbdbd00Clone(Tqdfqf00 tfbdbd00Clone) {
		this.tfbdbd00Clone = tfbdbd00Clone;
	}


	public Tfbcbc00 getTfbcbc00Clone() {
		return tfbcbc00Clone;
	}


	public void setTfbcbc00Clone(Tfbcbc00 tfbcbc00Clone) {
		this.tfbcbc00Clone = tfbcbc00Clone;
	}


	public Mgenus00 getTarifaRtf() {
		return tarifaRtf;
	}


	public void setTarifaRtf(Mgenus00 tarifaRtf) {
		this.tarifaRtf = tarifaRtf;
	}


	public String getTarRetefuente() {
		return tarRetefuente;
	}


	public void setTarRetefuente(String tarRetefuente) {
		this.tarRetefuente = tarRetefuente;
	}


	public String getDescTarRetefuente() {
		return descTarRetefuente;
	}


	public void setDescTarRetefuente(String descTarRetefuente) {
		this.descTarRetefuente = descTarRetefuente;
	}


	public Double getTotalBruto() {
		return totalBruto;
	}


	public void setTotalBruto(Double totalBruto) {
		this.totalBruto = totalBruto;
	}


	public Double getTotalImpCon() {
		return totalImpCon;
	}


	public void setTotalImpCon(Double totalImpCon) {
		this.totalImpCon = totalImpCon;
	}


	public Double getTotalIva() {
		return totalIva;
	}


	public void setTotalIva(Double totalIva) {
		this.totalIva = totalIva;
	}


	public Double getTotalReteIva() {
		return totalReteIva;
	}


	public void setTotalReteIva(Double totalReteIva) {
		this.totalReteIva = totalReteIva;
	}


	public Double getTotalRtf() {
		return totalRtf;
	}


	public void setTotalRtf(Double totalRtf) {
		this.totalRtf = totalRtf;
	}


	public Double getTotolVPagar() {
		return totolVPagar;
	}


	public void setTotolVPagar(Double totolVPagar) {
		this.totolVPagar = totolVPagar;
	}


	public List<Mparme00> getListMparme00() {
		return listMparme00;
	}


	public void setListMparme00(List<Mparme00> listMparme00) {
		this.listMparme00 = listMparme00;
	}


	public IMparme00Service getMparme00Service() {
		return mparme00Service;
	}


	public void setMparme00Service(IMparme00Service mparme00Service) {
		this.mparme00Service = mparme00Service;
	}


	public Tfbcbc00 getTfbcbc00Aux() {
		return tfbcbc00Aux;
	}


	public void setTfbcbc00Aux(Tfbcbc00 tfbcbc00Aux) {
		this.tfbcbc00Aux = tfbcbc00Aux;
	}


	public boolean isNoShowDialog() {
		return noShowDialog;
	}

	public void setNoShowDialog(boolean noShowDialog) {
		this.noShowDialog = noShowDialog;
	}

	public List<Double> getListTotales() {
		return listTotales;
	}

	public void setListTotales(List<Double> listTotales) {
		this.listTotales = listTotales;
	}

	public Integer getIdTipoContriCompa() {
		return idTipoContriCompa;
	}


	public void setIdTipoContriCompa(Integer idTipoContriCompa) {
		this.idTipoContriCompa = idTipoContriCompa;
	}


	public boolean isRegValido() {
		return regValido;
	}

	public void setRegValido(boolean regValido) {
		this.regValido = regValido;
	}

	public String getExirer() {
		return exirer;
	}

	public void setExirer(String exirer) {
		this.exirer = exirer;
	}

	public String getReirer() {
		return reirer;
	}

	public void setReirer(String reirer) {
		this.reirer = reirer;
	}


	public String getRefrer() {
		return refrer;
	}

	public void setRefrer(String refrer) {
		this.refrer = refrer;
	}

	public Double getPcirer() {
		return pcirer;
	}
	
	public void setPcirer(Double pcirer) {
		this.pcirer = pcirer;
	}


	public boolean isDisableButton() {
		return disableButton;
	}


	public void setDisableButton(boolean disableButton) {
		this.disableButton = disableButton;
	}

	public Double getTodebd() {
		return todebd;
	}


	public void setTodebd(Double todebd) {
		this.todebd = todebd;
	}
	
	public void generarPDF(Tqdfqf00 tqdfqf) throws NamingException, IOException {
		try{
			tqdfqf00 = tqdfqf;
			Mparme00 mparme = new Mparme00();
			mparme.setNopame("IdiomaBorradorFacNalEst");
			mparme = mparme00Service.retrieve(mparme);
			
			Midiom00 midiom = new Midiom00();
			if(mparme!=null){
				midiom.setCodidi(mparme.getVapame());
			}else{
				midiom.setCodidi("E");
			}
			List<Midiom00> listMidiom = midiom00Service.listMidiom00ByCriteria(midiom, getSeControlFactura().getCompaniasUsu());
			
			if(listMidiom!=null && !listMidiom.isEmpty()){
				midiom = listMidiom.get(0);
			}
			
			Mgenus00 mgenus = new Mgenus00();
			mgenus.setCodius("BFN");
			Mtipre00 mtipre = new Mtipre00();
			mtipre.setTipore("H1");
			mgenus.setMtipre00(mtipre);
			mgenus = mgenus00Service.findMgenus(mgenus);
			
			Mparca00 mparca = new Mparca00();
			mparca.setMidiom00(midiom);
			mparca.setMgenus001(mgenus);	
			
			List<Mparca00> listMparca = mparca00Service.listMparca00ByCriteria(mparca, getSeControlFactura().getCompaniasUsu());
			Tfbcbc00 tfbcbc = seTfbcbc00.getTfbcbc00();
			tfbcbc.setTqdfqf00(tqdfqf00);
			tfbcbc = tfbcbc00Service.getTfbcbc00ByTqdfqf00(tfbcbc);
			
			Tfbdbd00 tfbdbd = new Tfbdbd00(); 
			tfbdbd.setTfbcbc00(tfbcbc);
			tfbdbd.setRegcbd(true);
			List<Tfbdbd00> listTfbdb = tfbdbd00Service.listTfbdbd00ByTfbcbc00(tfbdbd);
			
			int rest = listTfbdb.size()%30;
			for (int i=0;i<30-rest;i++){
				listTfbdb.add(new Tfbdbd00());
			}
			Mconca00 mconca = tqdfqf00.getMgente00().getMconca00();
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletResponse response = (HttpServletResponse)externalContext.getResponse();
			HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			List<Mparpd00> listMparpd = new ArrayList<Mparpd00>();
			if(listMparca!=null && !listMparca.isEmpty()){
				mparca = listMparca.get(0);
				listMparpd = listMparca.get(0).getMparpd00s();
			}
			String fileName = String.valueOf(tfbcbc.getIdfcbc());  
			
			if(mparca.getObsec5()!=null && !mparca.getObsec5().equals("")){
				fileName = getFileName(mparca.getObsec5(),tfbcbc);
			}else{
				fileName = fileName.length()>5?fileName.substring(fileName.length()-5,fileName.length()):fileName;
			}
			HashMap<String, Mparpd00> hmMparpd = getHashMapMparpd(listMparpd);
			
			data.put("listTfbdb",listTfbdb);
			if(hmMparpd.get("CAIH01").getLogopd()!=null){
				InputStream in = new ByteArrayInputStream(hmMparpd.get("CAIH01").getLogopd());
				BufferedImage logo = ImageIO.read(in);
				data.put("CAIH01", logo);
			}else{
				data.put("CAIH01", null);
			}
			data.put("CADH02", mconca.getNomcia());
			data.put("CATH03", hmMparpd.get("CATH03").getDsrapd());
			data.put("CADH04", tfbcbc.getIdfcbc());
			data.put("CATH05", hmMparpd.get("CATH05").getDsrapd());
			data.put("CADH06", mconca.getNitcia());
			data.put("CADH07", mconca.getDircia());
			data.put("CATH08", hmMparpd.get("CATH08").getDsrapd());
			data.put("CADH09", mconca.getTelcia());
			data.put("CADH10", mconca.getMaicia());
			data.put("CADH11", mconca.getMgenus002().getDcttus());
			data.put("CADH12", mconca.getMgenus003().getDcttus());
			data.put("CADH22", tfbcbc.getNoclbc());
			data.put("CATH23", tfbcbc.getDedibc());
			data.put("CADH24", tfbcbc.getNrdibc());
			data.put("CADH25", tfbcbc.getDiclbc());
			data.put("CADH26", tfbcbc.getDecubc()+", "+tfbcbc.getDedpbc());
			data.put("CATH27", hmMparpd.get("CATH27").getDsrapd());
			data.put("CADH28", tfbcbc.getPncobc()+" "+tfbcbc.getSncobc()+" "+tfbcbc.getPacobc());
			data.put("CATH40", hmMparpd.get("CATH40").getDsrapd());
			data.put("CATH41D", hmMparpd.get("CATH41").getDsrapd().split(" ")[0]);
			data.put("CATH41M", hmMparpd.get("CATH41").getDsrapd().split(" ")[1]);
			data.put("CATH41Y", hmMparpd.get("CATH41").getDsrapd().split(" ")[2]);
			String formato = "dd MM yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			data.put("CADH42D", sdf.format(tfbcbc.getFegebc()).split(" ")[0]);
			data.put("CADH42M", sdf.format(tfbcbc.getFegebc()).split(" ")[1]);
			data.put("CADH42Y", sdf.format(tfbcbc.getFegebc()).split(" ")[2]);
			data.put("CATH43", hmMparpd.get("CATH43").getDsrapd());
			data.put("CADH44", tfbcbc.getNrocbc());
			data.put("CATH45", hmMparpd.get("CATH45").getDsrapd());
			data.put("CATH46", hmMparpd.get("CATH46").getDsrapd());
			data.put("CATH47D", hmMparpd.get("CATH47").getDsrapd().split(" ")[0]);
			data.put("CATH47M", hmMparpd.get("CATH47").getDsrapd().split(" ")[1]);
			data.put("CATH47Y", hmMparpd.get("CATH47").getDsrapd().split(" ")[2]);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(tfbcbc.getFegebc());
			cal.add(Calendar.DATE, tfbcbc.getPzdibc());
			data.put("CADH48D", sdf.format(cal.getTime()).split(" ")[0]);
			data.put("CADH48M", sdf.format(cal.getTime()).split(" ")[1]);
			data.put("CADH48Y", sdf.format(cal.getTime()).split(" ")[2]);
			data.put("CATH49", hmMparpd.get("CATH49").getDsrapd());
			data.put("CADH50", sdf.format(tfbcbc.getFegebc()));
			data.put("CADH51", hmMparpd.get("CADH51").getDsrapd());
			data.put("CATH52", hmMparpd.get("CATH52").getDsrapd());
			data.put("CADH53", tfbcbc.getPzdibc());
			data.put("CATH54", hmMparpd.get("CATH54").getDsrapd());
			data.put("CADH55", tfbcbc.getMofabc());
			data.put("CADH56", null); //ESTE ES EL QR
			
			ReportEntity reportEntity = new ReportEntity();
			reportEntity.setData(data);
			reportEntity.setMparpd(hmMparpd);
			parameters.put("fileName", fileName.replace(" ", "\b"));
			parameters.put("reportEntity", reportEntity);
			parameters.put("reportName", "facturaBorrador");
			parameters.put("reports","reports/jasper/");
			parameters.put("subreport","facturaBorradorDetails");
			parameters.put("context", request.getServletContext().getRealPath(""));
			parameters.put("SUBREPORT_DIR",request.getServletContext().getRealPath("")+"reports/jasper/");
			ReportsUtils.getInstance().getBorradorFactura(response, parameters);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private HashMap<String, Mparpd00> getHashMapMparpd(List<Mparpd00> listMparpd){
		HashMap<String, Mparpd00> hmMparpd = new HashMap<String, Mparpd00>();
		for(Mparpd00 mparpd:listMparpd){
			hmMparpd.put(mparpd.getCodcpd(), mparpd);
		}
		return hmMparpd;
	}

	public IMconca00Service getMconca00Service() {
		return mconca00Service;
	}

	public void setMconca00Service(IMconca00Service mconca00Service) {
		this.mconca00Service = mconca00Service;
	}

	public IMparca00Service getMparca00Service() {
		return mparca00Service;
	}

	public void setMparca00Service(IMparca00Service mparca00Service) {
		this.mparca00Service = mparca00Service;
	}
	
	private String getFileName(String fileName,Tfbcbc00 tfbcbc){
		String[] listParam = fileName.split("@");
		
		int length = Integer.parseInt(listParam[1].substring(listParam[1].indexOf("(")+1,listParam[1].indexOf(")")));
		fileName = fileName.replace("@BFN("+length+")", String.valueOf(tfbcbc.getIdfcbc()).length()>=length?String.valueOf(tfbcbc.getIdfcbc()).substring(String.valueOf(tfbcbc.getIdfcbc()).length()-length):String.valueOf(tfbcbc.getIdfcbc()));
		
		return fileName;
	}

	public boolean isPdfRendered() {
		return pdfRendered;
	}

	public void setPdfRendered(boolean pdfRendered) {
		this.pdfRendered = pdfRendered;
	}

	public List<Mparme00> getListMparme00Aux() {
		return listMparme00Aux;
	}

	public void setListMparme00Aux(List<Mparme00> listMparme00Aux) {
		this.listMparme00Aux = listMparme00Aux;
	}

	public boolean isValidarEstadoFlag() {
		return validarEstadoFlag;
	}

	public void setValidarEstadoFlag(boolean validarEstadoFlag) {
		this.validarEstadoFlag = validarEstadoFlag;
	}

	public ITfesfu00Service getTfesfu00Service() {
		return tfesfu00Service;
	}

	public void setTfesfu00Service(ITfesfu00Service tfesfu00Service) {
		this.tfesfu00Service = tfesfu00Service;
	}

	public List<Tfbcbc00> getListTfbcbc00() {
		return listTfbcbc00;
	}

	public void setListTfbcbc00(List<Tfbcbc00> listTfbcbc00) {
		this.listTfbcbc00 = listTfbcbc00;
	}

	public Tfesfu00 getTfesfu00() {
		return tfesfu00;
	}

	public void setTfesfu00(Tfesfu00 tfesfu00) {
		this.tfesfu00 = tfesfu00;
	}

	public List<Tfesfu00> getListTfesfu00() {
		return listTfesfu00;
	}

	public void setListTfesfu00(List<Tfesfu00> listTfesfu00) {
		this.listTfesfu00 = listTfesfu00;
	}

	public String getEstadoCuota() {
		return estadoCuota;
	}

	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}

	public Mestad00 getMestad00() {
		return mestad00;
	}

	public void setMestad00(Mestad00 mestad00) {
		this.mestad00 = mestad00;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public boolean isFlagFound() {
		return flagFound;
	}

	public void setFlagFound(boolean flagFound) {
		this.flagFound = flagFound;
	}

	public boolean isFlagFoundNoFacturada() {
		return flagFoundNoFacturada;
	}

	public void setFlagFoundNoFacturada(boolean flagFoundNoFacturada) {
		this.flagFoundNoFacturada = flagFoundNoFacturada;
	}

	public Mconca00 getCompaIdccia() {
		return compaIdccia;
	}
	
	public void setCompaIdccia(Mconca00 compaIdccia) {
		this.compaIdccia = compaIdccia;
	}

}
