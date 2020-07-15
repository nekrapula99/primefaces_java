package com.tlm.faelec.utils;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.faces.FactoryFinder;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.tlm.faelecEntities.model.entities.Taudit00;


public class UtilsJSF {
	private static ResourceBundle rb = Constantes.rb;
	public static final String TAUDIT_NUEVO_REG = "NUEVO";
	public static final String TAUDIT_ACTUALIZAR_REG = "ACTUALIZACIÓN";
	public static final String TAUDIT_ELIMINAR_REG = "ELIMINAR";
	public static final String TIPAME_CADENA = "C";
	public static final String TIPAME_FECHA = "F";
	public static final String TIPAME_NUMERO = "N";
	public static final String TIPAME_MAESTRO = "M";
	
	/**
	 * Cierra dialog jsf
	 * @param id
	 */
	public static void closeDialog(String id){
		try {
			RequestContext.getCurrentInstance().execute("PF('"+id+"').hide()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object tratamientoObjetoDeshabilitadoListas(Object obj, String tipoLista){
		try {
			String nombreClase = obj.getClass().getSimpleName();
			
			FacesContext facesContext=FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute(nombreClase+(tipoLista!=null?tipoLista:""), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Abre dialog jsf
	 * @param id
	 */
	public static void openDialog(String id){
		try {
			RequestContext.getCurrentInstance().execute("PF('"+id+"').show()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Hace reset al DataTable enviado por parametro
	 * Se usa para que no ocurran errores al momento de crear, borrar o modificar registros y volver a cargar la tabla
	 */
	public static void resetDTable(String idTab){
		try {
			DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(idTab);
			if(dataTable != null){
				dataTable.resetValue();				
			}
			RequestContext.getCurrentInstance().update(idTab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param context
	 * @param severity
	 */
	public static void facesLog(FacesContext context, FacesMessage.Severity severity){
		
		if(severity.equals(FacesMessage.SEVERITY_ERROR)){
			context.addMessage(null,new FacesMessage(severity, rb.getString("mj_severity_error"), ""));
		}else if(severity.equals(FacesMessage.SEVERITY_INFO)){
			context.addMessage(null,new FacesMessage(severity, rb.getString("mj_severity_info"), ""));
		}else if(severity.equals(FacesMessage.SEVERITY_FATAL)){
			context.addMessage(null,new FacesMessage(severity, rb.getString("mj_severity_fatal"), ""));
		}else if(severity.equals(FacesMessage.SEVERITY_WARN)){
			context.addMessage(null,new FacesMessage(severity, rb.getString("mj_severity_warn"), ""));
		}	
	}
	
public static void facesLogBorrador(FacesContext context, FacesMessage.Severity severity){
		
		System.out.println("entre alerta");
		if(severity.equals(FacesMessage.SEVERITY_INFO)){
			context.addMessage(null,new FacesMessage(severity, rb.getString("mj_severity_borrador"), ""));
		}	
	}

public static void facesLogFactura(FacesContext context, FacesMessage.Severity severity){
	
	System.out.println("entre alerta");
	if(severity.equals(FacesMessage.SEVERITY_INFO)){
		context.addMessage(null,new FacesMessage(severity, rb.getString("mj_severity_factura"), ""));
	}	
}

public static void facesLogGenerando(FacesContext context, FacesMessage.Severity severity){
	
	System.out.println("entre generando");
	if(severity.equals(FacesMessage.SEVERITY_INFO)){
		context.addMessage(null,new FacesMessage(severity, rb.getString("mj_severity_generando"), ""));
	}	
}
	
	public static void facesLog(FacesMessage.Severity severity, String Mensaje){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage(severity, Mensaje, ""));
	}
	
	/*
	 * 
	 */
	public static void facesLog(FacesContext context, String tipo){		
		if(tipo.equals("UNIQUE_KEY")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_unique_key"), ""));
		
		}if(tipo.equals("REFERENCE")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "+rb.getString("mj_reference_constraint"), ""));
		
		}else if(tipo.equals("NO_MCONCA00")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_no_mconca00"), ""));
		}
		else if(tipo.equals("USUARIO_SIN_COMPANIA")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_usuario_sin_compania"), ""));
		}else if(tipo.equals("RANGO_FECHA_NO_VALIDO")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_rango_fecha_no_valido"), "")); 
		}else if(tipo.equals("NO_MCONDE00")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_no_mconde00"), ""));
		} else if(tipo.equals("VAL_MONEDA_REQUERIDO")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_val_moneda_requerido"), ""));
		} else if(tipo.equals("fechaInicialMayorFechaFinal")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_FechaIni_Mayor_FechaFin"), ""));
		} else if(tipo.equals("añoCampañaDiferenteFechaInical")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_FechaCampa_Dif_FechaIni"), ""));
		}else if(tipo.equals("valor_invalido")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_valor_invalido"), ""));
		}else if(tipo.equals("valida_rango")){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("mj_valida_rango"), ""));
		}
		
	}
	
	/*
	 * Válida si la exepcion corresponde a una clave duplicada
	 */
	public static boolean isDuplicateException(Throwable ex){
		boolean validate = false;
		try {			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			
			if(sw.toString().contains("duplicate key")){
				validate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return validate;
	}
	
	/**
	 * Método que devuelve las claves duplicadas apartir de una exeption tipo DuplicateKeys
	 * @param e
	 * @param idUK
	 */
	public static String getDuplicateKeys(Exception e, String idUK, Integer indexReturn){
		String arrDuplicateUks = new String();
		try {	
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String strException = sw.toString();
			String[] arrSplit = strException.split(idUK);
			//arrDuplicateUks += new String[arrSplit.length - 1] + " ";
			
			for(int i = 1; i<arrSplit.length; i++){
				String str = arrSplit[i];
				//arrDuplicateUks[i-1] = str.substring(str.indexOf("("), str.indexOf(")")+1);
				String ukDiplicado = str.substring(str.indexOf("("), str.indexOf(")")+1);
				
				if(indexReturn != null){
					ukDiplicado = ukDiplicado.replace("(", "").replace(")", "");
					String[] arrSplit_= ukDiplicado.split(",");
					arrDuplicateUks += arrSplit_[indexReturn]+", ";
				}else{
					arrDuplicateUks += str.substring(str.indexOf("("), str.indexOf(")")+1) + " ";
				}
			}
		
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		return arrDuplicateUks;
	}
	
	/*
	 * Válida si la exepcion corresponde a restriccion REFERENCE constraint
	 */
	public static boolean isReferenceConstraintException(Throwable ex){
		boolean validate = false;
		try {			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			
			if(sw.toString().contains("REFERENCE constraint")){
				validate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return validate;
	}
	
	/**
	 * Url Login Dinox
	 * @return
	 */
	public static String getUrlDinox(FacesContext facesContext){
		HttpServletRequest request=(HttpServletRequest)facesContext.getExternalContext().getRequest();
		
		if(request == null) {
			return null;
		}else{
			return "http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/dinox";
		}
	}
	
	/**
	 * Valida si el componente selectOneMenu o oncomplete permiten null
	 * Si permite null se le agrega un item en blanco
	 * @return
	 */
	public static boolean selectOneMenu_permiteNull(){
		boolean permiteNull = false;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String strPermitNull = (String) UIComponent.getCurrentComponent(context).getAttributes().get("SOM_PERMITE_NULL");
			permiteNull = strPermitNull != null && strPermitNull.equalsIgnoreCase("S") ? true : false;
		} catch (Exception e) {
			permiteNull = false;
			e.printStackTrace();
		}
		
		return permiteNull;
	}
	
	/*
	 * Devuelve instancia de facesContext apartir de HttpServletRequest
	 * 	HttpServletRequest req = (HttpServletRequest)request;
	 *  HttpServletResponse resp = (HttpServletResponse)response;
	 */
	public static FacesContext getFacesContext(HttpServletRequest request, HttpServletResponse response) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
          LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
          Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
          FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
          facesContext = contextFactory.getFacesContext(request.getSession().getServletContext(), request, response, lifecycle);
          UIViewRoot view = facesContext.getApplication().getViewHandler().createView(facesContext, "");
          facesContext.setViewRoot(view);
          FacesContextWrapper.setCurrentInstance(facesContext);
        }
        return facesContext;
      }
	
	// Wrap the protected FacesContext.setCurrentInstance() in a inner class.
    private static abstract class FacesContextWrapper extends FacesContext {
      protected static void setCurrentInstance(FacesContext facesContext) {
        FacesContext.setCurrentInstance(facesContext);
      }
    }
    
    public static void resetSession(HttpServletRequest request) {
		try {
			Enumeration<String> enu = request.getSession().getAttributeNames(); 
			while (enu.hasMoreElements()) {
				request.getSession().removeAttribute(enu.nextElement().toString());
			} 			
			
			HttpSession session = request.getSession();
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
    
    public static void resetSession() {
		try {
			
			HttpServletRequest request =  (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			resetSession(request);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
    
    public static void resetSessionJsf(FacesContext faceContext) {
		try {
			HttpServletRequest request=(HttpServletRequest)faceContext.getExternalContext().getRequest();
			
			Enumeration<String> enu = request.getSession().getAttributeNames(); 
			while (enu.hasMoreElements()) {
				request.getSession().removeAttribute(enu.nextElement().toString());
			} 
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		    session.invalidate();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	    
	}
    
    
    /**
     * Convierte ruta de file en StremedConted
     * @param path
     * @param formato
     * @return
     */
    public static StreamedContent convertStrinfileToStreamedContentImg(String path, String formato)
	{
    	try {
    		
    		ByteArrayOutputStream os = null;
    		byte[] bytes = null;
    		
    		if(path!=null)
    		{
    		  File file = new File(path);
    	      BufferedImage image = ImageIO.read(file);
    	      os = new ByteArrayOutputStream();
    	      ImageIO.write(image, "jpg", os);
    	      bytes = os.toByteArray();
    	      return  new DefaultStreamedContent(new ByteArrayInputStream(bytes));
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DefaultStreamedContent();
	}
    
    /**
     * Convierte arreglo de byts en StremedConted
     * @param bts
     * @param formato
     * @return
     */
    public static StreamedContent convertBytesToStreamedContentImg(byte[] bts, String formato)
	{
    	if(bts != null){
    		InputStream is = new ByteArrayInputStream(bts);
    	    StreamedContent image = new DefaultStreamedContent (is, "image/"+formato,Math.random()+".png");
    	    return image; 
    	}else{
        	return null;
    	}		
	}
    
    /**
     * Convierte arreglo de byts en StremedConted
     * @param bts
     * @param formato
     * @return
     */
    public static StreamedContent convertStringToStreamedContentXml(String mytext, String nomFile)
	{
    	if(mytext != null){    	    
    	    InputStream stream = new ByteArrayInputStream( mytext.getBytes() );
    	    return new DefaultStreamedContent(stream, "xml", nomFile+".xml");
    	}else{
        	return null;
    	}		
	}
    
    /**
     * Convierte file a array de bits
     */
    @SuppressWarnings("unused")
	public static byte[] convertUploadedFileToArrBytes(UploadedFile file ){
    	byte[] bits = null;
    	try {
    		 InputStream is = file.getInputstream();
    		 bits = new byte[(int) file.getSize()];
			 int readers = is.read(bits);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return bits;
    }
    
    /**
     * Devuelve la ruta real del proyecto
     * @return
     */
    public static String getRealPath(){
    	String path = new String();
    	try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			path = servletContext.getRealPath("resources");	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return path;
    }
    
    /**
     * Devuelve la ruta real del proyecto
     * @return
     */
    public static Taudit00 crearAuditoria(){
      Taudit00 mtaudit = new Taudit00();
//      mtaudit.setAplaud(aplaud)
//      mtaudit.setConaud(conaud)
//      mtaudit.setDacaud(dacaud)
//      mtaudit.setEveaud(eveaud)
//      mtaudit.setFecaud()
//      mtaudit.
       return mtaudit;
    }
    

	public static Taudit00 registroAuditoria(Taudit00 taudit,String accion,String aplusu,String codusu,String titulo){
		taudit.setAplaud(aplusu);
		taudit.setUsuaud(codusu);
		taudit.setPgmaud(titulo);
		taudit.setMaqaud(Utils.ipLocal());
		taudit.setFecaud(Calendar.getInstance());
		taudit.setEveaud(accion);
		taudit.setDacaud("");
		return taudit;
	}
	
	 public static int fechasDiferenciasMeses(Calendar fechaini, Calendar fechafin, boolean incluirUltimoDia) throws ParseException{
		  int meses = 0;
		 
		  
		  if(fechafin.get(Calendar.YEAR)==fechaini.get(Calendar.YEAR)){
		   meses = (fechafin.get(Calendar.MONTH) + 1)-(fechaini.get(Calendar.MONTH) + 1);
		  }else{
		   meses = ((fechafin.get(Calendar.YEAR)-fechaini.get(Calendar.YEAR))*12)+((fechafin.get(Calendar.MONTH) + 1)-(fechaini.get(Calendar.MONTH) + 1));
		  }
		 
		    if(incluirUltimoDia){
		     if(fechafin.get(Calendar.DAY_OF_MONTH)==fechaini.get(Calendar.DAY_OF_MONTH)){
		       meses--;
		     }
		    }else{
		     if(fechafin.get(Calendar.DAY_OF_MONTH)<=fechaini.get(Calendar.DAY_OF_MONTH)){
		       meses--;
		     }
		    }
		 
		 
		  return Math.abs(meses);
		 }
	 
	 public static int fechasDiferenciasDias(Calendar fechaini, Calendar fechafin) throws ParseException{
		
		  Long diff = fechaini.getTimeInMillis() - fechafin.getTimeInMillis();
		  Long diffDays = diff / (24 * 60 * 60 * 1000);

		 
		  return diffDays.intValue();
		 }
	 public static void resetVistasJSF() 
	 {
		  try {
		   HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		   Enumeration<String> enu = request.getSession().getAttributeNames();
		   while (enu.hasMoreElements()) {
		    String element = enu.nextElement().toString();
		    
		    if(!"aplusu".equals(element)&&
		     !"codusu".equals(element)&&
		     !"ciausu".equals(element)&&
		     !"usua".equals(element)&&
		     !"idtrma".equals(element)&&
		     !"PERMISOSACCION".equals(element)&&
		     !"MENSAJES".equals(element)&&
		     !"PARAMETROS".equals(element)&&
		     !"seCabecera".equals(element)&&
		     !"listCompanias".equals(element)&&
		     !"seControl".equals(element)&&	
		     !"post".equals(element)&&
		     !"mconca00".equals(element)&&
		     !"companiasUsu".equals(element)&&
		     !"seListas".equals(element)&&
		     !"listMconca00sUsuario".equals(element)&&
		     !"org.apache.myfaces.view.facelets.DefaultFaceletsStateManagementHelper.SERIALIZED_VIEW".equals(element)&&
		     !"musuar00".equals(element)){
		     request.getSession().removeAttribute(element);
		    }
		   } 
		  } catch (Exception e) {
		   e.printStackTrace(); 
		  }     
	 }
}
