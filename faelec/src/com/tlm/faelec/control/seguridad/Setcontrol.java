package com.tlm.faelec.control.seguridad;

/**
 * 
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.tlm.faelecEntities.model.entities.Musuar00;
import com.tlm.faelecEntities.model.entities.Musuar00PK;
import com.tlm.faelec.utils.Utils;

/**
 * @author nosorio
 *
 */
public class Setcontrol { 
	/**
	 * Declaración de Variables
	 */
	Date fecha = new Date(); 
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public Connection connSafer = null;
	public String aplusu="";//se espera del login	
	public String codusu="";//se espera del login
	public String post="";
	public String ip="";
	public String ciausu="";
	public Musuar00 vomusuar = new Musuar00();
	public String idicia="E";//Pendiente redefinir  ...E=Español
	public String idtrma;
	/**
	 * @return
	 */
	public String getFechaSystema() {
		return fechaSystema;
	}

	/**
	 * @param string
	 */
	public void setFechaSystema(String string) {
		fechaSystema = string;
	}
	
	public String fechaSystema=format.format(fecha);
	
	/**
	 * Funcion que valida si la sesion del usuario se encuentra activa
	 * @return boolean
	 * @throws Exception 
	 */
	public Boolean FSVUSUACTIVO(HttpServletRequest request) throws SQLException, Exception{
		Boolean bandera = false;
		BEREGISTER br = new BEREGISTER();
		Connection conn = null;
		conn=ConexionSafer.getConexion();
		String usuario= (String)request.getSession().getAttribute("usua");
		String ip = request.getRemoteAddr();
		bandera = br.FSVALSESSION(usuario, ip, conn);
		if(conn != null){try {conn.close();} catch (Exception e) {}}
		return bandera;
	}
	
	/**
	 * Función que sube a sesión las variables necesarias para trabajar
	 * @param request
	 * @return Parametros de codusu
	 * @throws Exception
	 */
	public void Post() throws Exception{
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		aplusu= (String)req.getSession().getAttribute("aplusu");		      		
		
//		connSafer=ConexionSafer.getConexion();
		aplusu=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("aplusu");
		codusu=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codusu");
		ciausu=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ciausu");
		idtrma=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mgetma");
		
		if(aplusu != null && !aplusu.equals("") && FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aplusu")==null)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aplusu", aplusu);
		else
			aplusu=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aplusu");
		
		if(ciausu != null && !ciausu.equals("") && FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ciausu")==null)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ciausu", ciausu);
		else
			ciausu=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ciausu");
	
		if(codusu != null && !codusu.equals("") && FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usua")==null)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usua", codusu);
		else
			codusu=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usua");
		
		if(idtrma != null && !idtrma.equals("") && FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idtrma")==null)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idtrma", idtrma);
		else
			idtrma=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idtrma");
				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		ip = request.getHeader( "X-FORWARDED-FOR" );
		if ( ip == null ) {
		    ip = request.getRemoteAddr();  
		}
		
		post = post+"aplusu="+aplusu+"&";
		post = post+"codusu="+codusu;
		
		if(post != null && !post.equals("") && FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("post")==null)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("post", post);
		else{
			post=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("post");
		}
		vomusuar.setId(new Musuar00PK());
		vomusuar.getId().setAplusu(aplusu);
		vomusuar.getId().setCodusu(codusu);		
		
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("vomusuar")==null){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("vomusuar", vomusuar);
		}
	}
	
	
	/**
	 * Función que sube a sesión las variables necesarias para trabajar
	 * @param request
	 * @return Parametros de codusu
	 * @throws Exception
	 */
	public void validarSesion() throws Exception{
		System.out.println("Estoy en validarSesion--------------------------------------");
		 Musuar00 vomusuar1 = (Musuar00)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("vomusuar");
		 
		 if(!(vomusuar1!=null && vomusuar1.getId().getCodusu()!=null && !vomusuar1.getId().getCodusu().equals(""))){
			 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			    ec.invalidateSession();
			    HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			    String url = req.getRequestURL().toString();
			    String url2 = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
			    url2 = Utils.replaceAll(url2, "/faelec/", "");
			    ec.redirect(ec.encodeActionURL(url2+"/dinox"));
		 }
	}
	
	/**
	 * Funcion que valida si la sesion del usuario se encuentra activa
	 * @return boolean
	 * @throws Exception 
	 */
	public void FSVUSUACTIVO() throws SQLException, Exception{
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Boolean bandera = false;
		BEREGISTER br = new BEREGISTER();
		Connection conn = null;
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try{
		conn=ConexionSafer.getConexion();
		String usuario= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codusu");
		bandera = br.FSVALSESSION(usuario, ip, conn);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn != null){try {conn.close();} catch (Exception e) {}}
			
		}
		if(!bandera){
			ec.invalidateSession();
			String url = req.getRequestURL().toString();
		    String url2 = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
		    url2 = Utils.replaceAll(url2, "/faelec/", "");
		    ec.redirect(ec.encodeActionURL(url2+"/dinox"));
		}
	}
	
	public void logout() throws IOException {
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String url = req.getRequestURL().toString();
	    String url2 = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
	    url2 = Utils.replaceAll(url2, "/faelec/", "");
	    ec.redirect(ec.encodeActionURL(url2+"/dinox"));
	}
	
	public void login() throws IOException {
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String url = req.getRequestURL().toString();
	    String url2 = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
	    url2 = Utils.replaceAll(url2, "/faelec/", "");
	    ec.redirect(ec.encodeActionURL(url2+"/dinox"));
	}
	
}
