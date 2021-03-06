package com.tlm.faelec.web.controller;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;

import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Maccgr00;
import com.tlm.faelecEntities.model.entities.Maccgr00PK;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mopcme00;


@ManagedBean
@SessionScoped
public class SeTemplate implements Serializable{
	private static final long serialVersionUID = 8166551710594648404L;
	
	private Mconca00 mconca00;
	private boolean vistaApp;
    private FacesContext faceContext;
    public HttpServletRequest request;
    private HttpSession session;		
	private ResourceBundle rb = Constantes.rb;  
	private Calendar fechaActual;
	
	public String WINDOW_HEIGHT;
	public String DOCUMENT_HEIGHT;
	public String WINDOW_WIDTH;
	public String DOCUMENT_WIDTH;
	public int MAX_HEIGHT_DIALOG;
	public int MAX_WIDTH_DIALOG;
	/**
	 * Instancia de otros managed beans
	 */
//	@ManagedProperty("#{seMconca00}")
//	private SeMconca00 seMconca00; 
	
	@ManagedProperty("#{seControlFact}")
	private SeControlFactura seControl; 
	
	
	public SeTemplate(){
		fechaActual = new GregorianCalendar();
	}
	
	@ManagedProperty(value="#{seCabecera}")
	private SeCabecera cabeceraBean;

	public void setCabeceraBean(SeCabecera cabeceraBean) {
		this.cabeceraBean = cabeceraBean;
	}
	
	
	
	/**
	 * Metodo encargado de calcular las dimensiones de la pantalla
	 */
	public void calcularDimensionPantalla(){
		try {
			Map<String, String> mapForm = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

			WINDOW_HEIGHT = mapForm.get("formTemplate:WINDOW_HEIGHT");
			DOCUMENT_HEIGHT = mapForm.get("formTemplate:DOCUMENT_HEIGHT");
			WINDOW_WIDTH = mapForm.get("formTemplate:WINDOW_WIDTH");
			DOCUMENT_WIDTH = mapForm.get("formTemplate:DOCUMENT_WIDTH");
			
			MAX_HEIGHT_DIALOG = Integer.parseInt(WINDOW_HEIGHT) - 220; 
			MAX_WIDTH_DIALOG = Integer.parseInt(WINDOW_WIDTH) - 100;
			
//			System.out.println("//////////////////////////////");
//			System.out.println("//WINDOW_HEIGHT: "+WINDOW_HEIGHT);
//			System.out.println("//DOCUMENT_HEIGHT: "+DOCUMENT_HEIGHT);
//			System.out.println("//WINDOW_WIDTH: "+WINDOW_WIDTH);
//			System.out.println("//DOCUMENT_WIDTH: "+DOCUMENT_WIDTH);
//			System.out.println("//////////////////////////////");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Evento que se ejectua al seleccionar un men�
	 * @param event
	 */
	public String evtSeleccionMenu(ActionEvent event){
		String url = null;
		String menopm= null;
		String numopm=null;		
		
		try {			
		
			MenuActionEvent j = (MenuActionEvent) event;
	        DefaultMenuItem itm = (DefaultMenuItem) j.getMenuItem();
			Map<String, List<String>> mapa = itm.getParams();		
			List<String> param = (List<String>)mapa.get("url");
			menopm = mapa.get("menopm").get(0);
			numopm = mapa.get("numopm").get(0);	
			Maccgr00 maccgr00= new Maccgr00();
			maccgr00.setId(new Maccgr00PK());
			maccgr00.getId().setCoapac(seControl.aplusu);
			maccgr00.getId().setCograc(seControl.getMusuar00Session().getGruusu());
			maccgr00.getId().setComeac(menopm);
			maccgr00.getId().setOpmeac(Integer.parseInt(numopm));			
			seControl.cargarPermisosPorAccion(maccgr00);
			cabeceraBean.setBotonNuevo(true);
			url = param.get(0);
			UtilsJSF.resetVistasJSF();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	@PostConstruct
	public void SeTemplatePost(){  
		seControl.validarSession();
		faceContext=FacesContext.getCurrentInstance(); 
		request=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        session = (HttpSession) faceContext.getExternalContext().getSession(true);
		vistaApp = !((Mconca00)session.getAttribute("mconca00") == null);
	}
	
	
	/**
	 * Funcion utilizada para los filtros de fecha en todos los formularios
	 * @param value
	 * @param filter
	 * @param locale
	 * @return
	 */
	public boolean filterByDate(Object value, Object filter, Locale locale) {
		
        try {
        	String filterText = (filter == null) ? null : filter.toString().trim();
            if("".equals(filterText)) {
                return true;
            }
            
			DateFormat df = new SimpleDateFormat(getRb().getString("cln_patternFecha"));
			Date valueDate = (Date)value;
			
			if(df.format(valueDate).contains(filter.toString())){
				return true;
			}
      	} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        return false;
    }
	
	/**
	 * 
	 * @return
	 */
	public String asignarCompaSession(){ 
		session.setAttribute("mconca00", mconca00);
		seControl.setMconca00Session(mconca00);
		return Constantes.NAME_VISTA_APP;
	}
	
	
	public void validarCompaniasUsuario_(){
		try {
			if(!seControl.validarCompaniasUsuario()){
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "USUARIO_SIN_COMPANIA");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//pendiente borrar
	public String redirect(){
		if(seControl.isSessionValida()){  
			if((Mconca00)session.getAttribute("mconca00") == null){ 
				seControl.setMconca00Session(null);
				return "vistaMcompa00";
			}else{
				return Constantes.NAME_VISTA_APP;
			}
		}else{			
			RequestContext requestContext = RequestContext.getCurrentInstance(); 
			requestContext.execute("window.location='"+UtilsJSF.getUrlDinox(FacesContext.getCurrentInstance())+"'");
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String cambiarMcompa00(){ 
		try {
			session.removeAttribute("mconca00");
			vistaApp = false;
			
			HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			Enumeration<String> enu = request.getSession().getAttributeNames();
			while (enu.hasMoreElements()) {
				String element = enu.nextElement().toString();
				
				if(	!"aplusu".equals(element)&&
					!"usua".equals(element)&&
					!"post".equals(element)&&
					!"org.apache.myfaces.view.facelets.DefaultFaceletsStateManagementHelper.SERIALIZED_VIEW".equals(element)&&
					!"musuar00".equals(element)){
					request.getSession().removeAttribute(element);
				}
			} 
			
			return "vistaMcompa00";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * M�todo encargado de inhabilitar la session de jsf
	 */
	public void resetSession() {
		try {
			UtilsJSF.resetSessionJsf(FacesContext.getCurrentInstance());
		    FacesContext.getCurrentInstance().getExternalContext().redirect(UtilsJSF.getUrlDinox(FacesContext.getCurrentInstance()));
		} catch (Exception e) {
			e.printStackTrace(); 
		}	    
	}
	

	/*
	 * Get y Set
	 */
	public Mconca00 getMconca00() {
		return mconca00;
	}
	
	public void setMconca00(Mconca00 mconca00) {
		this.mconca00 = mconca00;
	}

	public boolean isVistaApp() {
		return vistaApp;
	}

	public void setVistaApp(boolean vistaApp) {  
		this.vistaApp = vistaApp; 
	}

	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}

	public Calendar getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Calendar fechaActual) {
		this.fechaActual = fechaActual;
	}

//	public SeMconca00 getSeMconca00() {
//		return seMconca00;
//	}
//
//	public void setSeMconca00(SeMconca00 seMconca00) {
//		this.seMconca00 = seMconca00;
//	}
	
	public String getWINDOW_HEIGHT() {
		return WINDOW_HEIGHT;
	}
	public String getDOCUMENT_HEIGHT() {
		return DOCUMENT_HEIGHT;
	}
	public String getWINDOW_WIDTH() {
		return WINDOW_WIDTH;
	}
	public String getDOCUMENT_WIDTH() {
		return DOCUMENT_WIDTH;
	}
	public int getMAX_HEIGHT_DIALOG() {
		return MAX_HEIGHT_DIALOG;
	}
	public int getMAX_WIDTH_DIALOG() {
		return MAX_WIDTH_DIALOG;
	}



	public SeControlFactura getSeControl() {
		return seControl;
	}



	public void setSeControl(SeControlFactura seControl) {
		this.seControl = seControl;
	}



	public SeCabecera getCabeceraBean() {
		return cabeceraBean;
	}
	
}