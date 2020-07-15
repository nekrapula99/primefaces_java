package com.tlm.faelec.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mdecai00;
import com.tlm.faelecEntities.model.entities.Mdecai00PK;
import com.tlm.faelecEntities.model.entities.Mintem00;
import com.tlm.faelecEntities.model.entities.Msegca00;
import com.tlm.faelecEntities.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtabla00PK;

public class Control {
	
	
	@ManagedProperty(value="#{seControlFact}")
	private SeControlFactura seControlFactura; //Preguntar si se debe realizr una general
	
	private String titulo;   

    
	private String patternFecha;
	private String patternFechaHora;
	
	//Variables para los campos decimales
	private String patern_Dec_CantiM;
	private String patern_Dec_ValruM;
	private String patern_Dec_PorceM;
	private String patern_Dec_ValrtM;
	
	//Idiomas y Permisos
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	

	@ManagedProperty(value = "#{seCabecera}")
	private SeCabecera seCabecera;

	@PostConstruct
	public void ControlPost(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void init(String keyTabla){
		try {
			Mdecai00 mdecai00 = new Mdecai00();			
			mdecai00.setId(new Mdecai00PK());
			mdecai00.getId().setApldei(seControlFactura.aplusu);
			mdecai00.getId().setCoddei(seControlFactura.idicia);
			mdecai00.getId().setTabdei(keyTabla); 
			Map<String, Object> hmMdecai = seControlFactura.mgeneralService.hmMdecai00ByCriteria(mdecai00);
			
			idiomasHm = (Map<String, String>)hmMdecai.get("HmDescrip");
			idiomasAyuHm=(Map<String, String>)hmMdecai.get("HmAyuda");  
			tabla=keyTabla;
					
			//Cargar Permisos campos
			Msegca00 msegca00= new Msegca00();		
			msegca00.setAplseg(seControlFactura.aplusu);
			msegca00.setTabseg(keyTabla);
			msegca00.setGruseg(seControlFactura.getMusuar00Session().getGruusu());
			
			Mcampo00 mcampo00 = new Mcampo00();
			mcampo00.setMaplic00(new Maplic00());
			mcampo00.getMaplic00().setCodapl(seControlFactura.aplusu);
			mcampo00.setMtabla00(new Mtabla00());
			mcampo00.getMtabla00().setId(new Mtabla00PK());
			mcampo00.getMtabla00().getId().setCodtab(keyTabla);
		
			permisos=seControlFactura.mgeneralService.permisosMsegca00ByCriteria(msegca00);	
			
			permisoCampos =(HashMap<String, Mcampo00>) seControlFactura.mgeneralService.listMcampo00(mcampo00);
			
			
			//Carga De los titulos de los Formularios
			Mtabla00PK idTab = new Mtabla00PK();
			idTab.setApltab(seControlFactura.aplusu);
			idTab.setCodtab(keyTabla); 
			Mtabla00 mtabla00 = seControlFactura.mgeneralService.getMtabla00(idTab); 
			
			if(mtabla00 != null){
				seControlFactura.setTitulo(mtabla00.getNomtab()); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/*
	 * Obtiene parametro de la aplicacion configurado en mparme00
	 */
	@SuppressWarnings("unchecked")
	public Object parametroVentCata(String key){
		Object valor = null;
		try {
			seControlFactura.PARAMETROS = (HashMap<String, Object>)seControlFactura.session.getAttribute("PARAMETROS");
			valor = seControlFactura.PARAMETROS.get(key);
			
			//Si no se encuentra el valor del parametro se inserta registro de inconsistencia para que sea configurado
			if(valor == null){				
				String[] arrParametros = {key, seControlFactura.getMconca00Session().getCodcia()};
				String mensaje = Utils.reemplazarParametrosString(seControlFactura.getRb().getString("mj_mparme00_no_property"), arrParametros);
				
				Mintem00 mintem00 = new Mintem00();
				mintem00.setIcodig("");
				mintem00.setInocam(key);		
				mintem00.setIregco("N");			
				mintem00.setIdaerr(key);
				mintem00.setIdescr(mensaje);
				saveInconDecimex(mintem00);
				
				UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, mensaje);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valor;
	}
	
	/**
	 * Evento que se ejecuta cada vez que se cierra una ventana.
	 * Refresca la pagina y en caso de que hayan ocurrido errores de validación entonces
	 * se pueda seguir navegando sin problemas
	 * @param event
	 */
	public void saveInconDecimex(Mintem00 mintem00) {
        try 
        {    
        	mintem00.setInotab(seControlFactura.getTabla());        	
        	mintem00.setUserem(seControlFactura.codusu);
        	mintem00.setPrgmem(seControlFactura.aplusu);
        	mintem00.setIfecco(new Date());
        	mintem00.setMaquca(seControlFactura.ip);
        	mintem00.setMconca00(seControlFactura.getMconca00Session());
        	mintem00.setCompam(seControlFactura.getMconca00Session().getCodcia());
//        	maestrosFacade.saveMintem00(mintem00);        
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * Evento que se ejecuta cada vez que se cierra una ventana.
	 * Refresca la pagina y en caso de que hayan ocurrido errores de validación entonces
	 * se pueda seguir navegando sin problemas
	 * @param event
	 */
	public void evtCloseDialog(CloseEvent event) {
        try { 
        	//clearAllFiltersMparpd();
        	//clearAllFilters();
        	FacesContext context = FacesContext.getCurrentInstance(); 
        	String viewId = context.getViewRoot().getViewId();
        	ViewHandler handler = context.getApplication().getViewHandler();
        	UIViewRoot root = handler.createView(context, viewId);
        	root.setViewId(viewId); 
        	context.setViewRoot(root);
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	//Limpia el filtro del DataTable
	public void clearAllFiltersMparpd() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('fTableMpardp00').clearFilters();");
		
		System.out.println("Limpiando desde Mparpd00");
	
	}
	
	//Limpia el filtro del DataTable
	public void clearAllFilters() {
		System.out.println("Entro ?? Limpiando");
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot().findComponent("formTable:dTable");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formTable:dTable");
		}
	}
	//Limpia el filtro del DataTable
	public void clearAllFiltersMparca() {
		System.out.println("Limpiando desde Mparpd");
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot().findComponent("formDetalle:tabView:dTableMparpd00");
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formDetalle:tabView:dTableMparpd00");
	}
	
	/**
	  * Recibe el parametro de numero de decimales y contruye la cadena de formato de moneda a aplicar
	  * @param patern Nombre del parametro de numero de decimales a aplicar en el formato
	  * @return Formato de moneda a aplicar
	  */
	protected String calcularPatern(String parametro) {
		Formatter nroFormateado = new Formatter();
		int nro = 0;
		String patern = "";
		try {
			if (!(Utils.isEmptyOrNull(parametro))) {
				nroFormateado.format("%0" + parametro + "d", nro);
				patern += ((String) Utils.paramsRb
						.getString("pattern_decimalInicial"))
						+ String.valueOf(nroFormateado);
			} else {
				patern = (String) Utils.paramsRb.getString("pattern_decimal");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patern;
	}
	
	// Se valida el formato de la fecha deacuerdo a la compañia seleccionada
	public void validarFormatoFechaHora() {
		try {
			// Se Valida el formato de la fecha y si no se encuentra se pone uno
			// por default
			if (parametroVentCata("For_FecHor") != "") {
				patternFechaHora = ((String) parametroVentCata("For_FecHor"));
			} else {
				setPatternFecha("dd-MM-yyyy HH:mm:ss");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Metodo que se encarga de asignar los valores decimales definidos por los parametros
	public Double round(Double value, Integer fraccion) {

		Double aux = null;
		try {
			BigDecimal bd = new BigDecimal(value);
			bd = bd.setScale(fraccion, RoundingMode.HALF_UP);
			aux = bd.doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
		return aux;
	}
	
	// Calcula la cantidad de decimales configurados por parametro
		public void calcularDecimales(){
			try{
				//Se inician las variables donde se capturan la cantidad de decimales
				Double d1=(Double)getSeControlFactura().PARAMETROS.get("Dec_CantiM");	
				Double d2=(Double)getSeControlFactura().PARAMETROS.get("Dec_ValruM");
				Double d3=(Double)getSeControlFactura().PARAMETROS.get("Dec_PorceM");
				Double d4=(Double)getSeControlFactura().PARAMETROS.get("Dec_ValrtM");
				Integer fracDec_CantiM=d1.intValue();
				Integer fracDec_ValruM=d2.intValue();
				Integer fracDec_PorceM=d3.intValue();
				Integer fracDec_ValrtM=d4.intValue();
				
				patern_Dec_CantiM = calcularPatern(fracDec_CantiM.toString());
			    patern_Dec_ValruM = calcularPatern(fracDec_ValruM.toString());
			    patern_Dec_PorceM = calcularPatern(fracDec_PorceM.toString());
			    patern_Dec_ValrtM = calcularPatern(fracDec_ValrtM.toString());
			    
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	// Se valida el formato de la fecha deacuerdo a la compañia seleccionada
	public void validarFormatoFecha() {
		try {
			// Se Valida el formato de la fecha y si no se encuentra se pone uno
			// por default
			if (parametroVentCata("For_Fechas") != "") {
				patternFecha = ((String) parametroVentCata("For_Fechas"));
			} else {
				setPatternFecha("dd-MM-yyyy");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public SeControlFactura getSeControlFactura() {
		return seControlFactura;
	}

	public void setSeControlFactura(SeControlFactura seControlFactura) {
		this.seControlFactura = seControlFactura;
	}

	public SeCabecera getSeCabecera() {
		return seCabecera;
	}

	public void setSeCabecera(SeCabecera seCabecera) {
		this.seCabecera = seCabecera;
	}
	
	//public String getTitulo() {
		//return seControlFactura.getTitulo();
	//}

	public String getPatternFecha() {
		return patternFecha;
	}

	public void setPatternFecha(String patternFecha) {
		this.patternFecha = patternFecha;
	}

	public String getPatternFechaHora() {
		return patternFechaHora;
	}

	public void setPatternFechaHora(String patternFechaHora) {
		this.patternFechaHora = patternFechaHora;
	}

	public String getPatern_Dec_CantiM() {
		return patern_Dec_CantiM;
	}

	public void setPatern_Dec_CantiM(String patern_Dec_CantiM) {
		this.patern_Dec_CantiM = patern_Dec_CantiM;
	}

	public String getPatern_Dec_ValruM() {
		return patern_Dec_ValruM;
	}

	public void setPatern_Dec_ValruM(String patern_Dec_ValruM) {
		this.patern_Dec_ValruM = patern_Dec_ValruM;
	}

	public String getPatern_Dec_PorceM() {
		return patern_Dec_PorceM;
	}

	public void setPatern_Dec_PorceM(String patern_Dec_PorceM) {
		this.patern_Dec_PorceM = patern_Dec_PorceM;
	}

	public String getPatern_Dec_ValrtM() {
		return patern_Dec_ValrtM;
	}

	public void setPatern_Dec_ValrtM(String patern_Dec_ValrtM) {
		this.patern_Dec_ValrtM = patern_Dec_ValrtM;
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

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

}