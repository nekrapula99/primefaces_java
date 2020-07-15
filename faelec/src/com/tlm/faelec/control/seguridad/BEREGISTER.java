package com.tlm.faelec.control.seguridad;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.tlm.faelec.utils.Utils;


/**
 * @author nosorio
 *
 */
@SuppressWarnings("serial")
public class BEREGISTER implements Serializable{
	
    private String Key=null;
    private String attrib="com.tlm.faelec.resources.ApplicationResourcesDinox";
    
	/**
	 * Constructor Vacio
	 */
	public BEREGISTER(){
	}
	
	/**
	 * Función  que obtiene las claves del registro de Windows
	 * @return
	 */
	public String FSREGISTRO() {
		
		try {
			Preferences Mipref = Preferences.systemRoot();
			Mipref = Mipref.node("/software/sis2010");
			if (Mipref.nodeExists("/software/sis2010")) {
				Key = Mipref.get("regapl", "null")+"/";
				Key = Key+Mipref.get("regapl1", "null");
			}			
		} catch (BackingStoreException ex) {
			ex.printStackTrace();
		}
		return Key;
	}

	/**
	 * Función que obtiene devuelve el String con el archivo de licencia
	 * @param ruta de ubicacion del Archivo
	 * @return Información
	 */
	public String BeString(String ruta) {
		StringBuffer buffer = new StringBuffer();
		String line;
		FileReader fReader;
		BufferedReader bReader;
		try{
			File file = new File(ruta);
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
			while ((line=bReader.readLine()) != null){
				buffer.append(line);
			}
			bReader.close();
			fReader.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	/**
	 * Función que devuelve la Ubicación del archivo de licencia
	 * @return ubicacion
	 */
	public String FSDIRREGISTER() {
		String dir = Utils.leerPropiedades(this).getProperty("REGDIR");
		return dir;
	}
	
	/**
	 * Función que valida las claves obtenidas del registro de Windows
	 * @return mensaje
	 */
	public String FSVALREGISTER() {
		String mensaje=null;
		ResourceBundle rb = ResourceBundle.getBundle(attrib);
		if(BeString(FSDIRREGISTER())==null || BeString(FSDIRREGISTER())=="")
			mensaje=rb.getString("REG.ERROR1");
		if(FSREGISTRO().contains("null"))
			mensaje=rb.getString("REG.ERROR2");		
		return mensaje;
	}
	
	/**
	 * Función que valida la coherencia del texto con la 
	 * información obtenida del archivo de licencia luego de desencriptar
	 * @param text
	 * @return mensaje
	 */
	public String FSVALTEXT(String text) {
		String mensaje=null;
		ResourceBundle rb = ResourceBundle.getBundle(attrib);
		if(!text.contains("COMPA")&&!text.contains("USE")&&!text.contains("USC"))
			mensaje=rb.getString("REG.ERROR3");
		return mensaje;
	}
	
	/**
	 * Función que valida que si un usuario ya se encuentra  conectado
	 * @param usucon
	 * @param usuaud
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Boolean FSVALUSUARIO(List usucon, String usuaud) {
		Boolean bandera = false;
		Iterator iter = usucon.iterator();
		Beusuaud00 audus = new Beusuaud00();
		while (iter.hasNext()){
			audus = (Beusuaud00) iter.next();
			if (audus.getUsuaud().equals(usuaud))
				bandera=true;
		}
		return bandera;
	}
	
	/**
	 * Función que valida que el id de sesión sea el mismo que el el registrado 
	 * en la tabla de Auditoria de Usuarios
	 * @param usuario
	 * @param ip
	 * @return boolean
	 * @throws SQLException 
	 */
	public Boolean FSVALSESSION(String usuario, String ip, Connection conn) throws SQLException, Exception {
		Boolean bandera = false;
		Beusuaud00 usuaud = new Beusuaud00();
		usuAuditoriaDAO auditoria = new usuAuditoriaDAO(conn);
		usuaud.setUsuaud(usuario);
		usuaud= (Beusuaud00)auditoria.FBCUSUADI00(usuaud);
		if(usuaud != null && usuaud.getIpuaud().equals(ip))
			bandera = true;
		return bandera;
	}
	
	/**
	 * Funcion que devuelve el numero de licencias operativas por compañia
	 * @param licencia
	 * @param ciausu
	 * @return
	 * @throws Exception
	 */
	public String FSNUSARIOO(String licencia, String ciausu) throws Exception {
		String contLicencia=null;
		int indice1 = licencia.indexOf(":");
		int indice2 = licencia.indexOf(";");
		String compa = "";
		String uso;
		while (indice2>0) {
			compa = (String)licencia.subSequence(indice1+1, indice2);
			indice1 = licencia.indexOf(":", indice2);
			indice2 = licencia.indexOf(";", indice1);
			uso = (String)licencia.subSequence(indice1+1, indice2);
			indice1 = licencia.indexOf(":", indice2);
			indice2 = licencia.indexOf(";", indice1);
			if (indice2 >0) {
				indice1 = licencia.indexOf(":", indice2);
				indice2 = licencia.indexOf(";", indice1);
			}
			if (ciausu.equals(compa))
				contLicencia=uso;
		}
		return contLicencia;
	}
	
	/**
	 * Funcion que devuelve el numero de licencias de control por compañia
	 * @param licencia
	 * @param ciausu
	 * @return
	 * @throws Exception
	 */
	public String FSNUSARIOC(String licencia, String ciausu) throws Exception {
		String contLicencia=null;
		int indice1 = licencia.indexOf(":");
		int indice2 = licencia.indexOf(";");
		String compa = "";
		String usc;
		while (indice2>0) {
			compa = (String)licencia.subSequence(indice1+1, indice2);
			indice1 = licencia.indexOf(":", indice2);
			indice2 = licencia.indexOf(";", indice1);
			indice1 = licencia.indexOf(":", indice2);
			indice2 = licencia.indexOf(";", indice1);
			if (indice2 >0) {
				usc = (String)licencia.subSequence(indice1+1, indice2);
				indice1 = licencia.indexOf(":", indice2);
				indice2 = licencia.indexOf(";", indice1);
			}
			else {
				usc = (String)licencia.substring(indice1+1, licencia.length());
			}
			if (ciausu.equals(compa))
				contLicencia=usc;
		}
		return contLicencia;
	}
}
