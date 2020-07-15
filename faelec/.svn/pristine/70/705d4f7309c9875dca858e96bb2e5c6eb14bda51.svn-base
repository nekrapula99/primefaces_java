package com.tlm.faelec.utils;



import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Image;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


public class Utils
{
	
	public static final String CODIGO_TIPO_REG = "TR";
	public static ResourceBundle paramsRb = ResourceBundle.getBundle("com.tlm.faelec.utils.params");
	public static final String ID_APLICACION = paramsRb.getString("id_aplicacion");
	

	
	/**
	 * Función que delimita un doble a un numero fijo de decimales
	 * @param doub
	 * @param decimales
	 * @return double
	 */
	public static double delimitDouble(Double doub, int decimales) {
		String valor = String.valueOf(doub);
		if (doub != null && !doub.equals(""))
			valor = String.format(Locale.US, "%."+decimales+"f", doub);
		
		double cadenaFormateada	=	0;
		try
		{
			cadenaFormateada	=	Utils.FSCONVERT(valor);
		}
		catch(Exception nfe)
		{
			cadenaFormateada	=	Utils.FSCONVERT("0");
		}
		return cadenaFormateada;
	}
	
	/**
	 * Valida objeto vacio o nulo
	 * @param cadena
	 * @return
	 */
	public static boolean isEmptyOrNull(Object obj){
		boolean empty = true;
		try {
			if(obj != null){
				empty = obj.toString().isEmpty();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return empty;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	public static String replaceAll(String streng, String soeg, String erstat)
	{
		String st = null;
		
		if ( streng != null && ! streng.equals(""))
		{
			//if (streng != "") {
			st = streng;
			if (soeg.length() == 0)
				return st;
			int idx = st.indexOf(soeg);
			while (idx >= 0)
			{
				st = st.substring(0, idx) + erstat + st.substring(idx + soeg.length());
				idx = st.indexOf(soeg);
			}
		}
	
		return st;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	//Metodo que retorno la fecha actual en el formato yyyy-MM-dd H:mm:ss.SS
	public static String getDateSystemWithMili()
	{
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		
		return format2.format(new Date());
	}
	
	/**
	 * Método encargado de reemplazar variables en un String
	 * En el string se debe especificar la variable entre dos #
	 * Ejm: #variable#
	 * @param cadena
	 * @param arrParametros
	 * @return
	 */
	public static String reemplazarParametrosString(String cadena, String[] arrParametros)
	{
		try {
			for (int i = 0; i < arrParametros.length; i++) {
				cadena = cadena.replace("#"+i+"#", arrParametros[i]);
			}
		} catch (Exception e) {
			return cadena;
		}		
		return cadena;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	//Metodo que retorna objeto Calendar en formato yyyy-MM-dd H:mm:ss.SS
		public static String fechaCalendarToString(Calendar calendar)
		{
			Date date = calendar.getTime();
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
			
			return format2.format(date);
		}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	//Metodo que retorno un objeto tipo Calendar en formato String
	public static String convertDateFormat(Date date, String format)
	{
		SimpleDateFormat format2 = new SimpleDateFormat(format);
		
		return format2.format(date);
	}
	
	
	public static String convertDateFormatAnexosMM(Calendar calendar)
	{
		Date date = calendar.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
		
		return format1.format(date)+"T"+format2.format(date);
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	//Metodo que retorno la fecha actual en el formato yyyy-MM-dd
	public static String getDateSystem()
	{
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		
		return format2.format(new Date());
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------
	//
	public static String darFormatoCifras(String cifras,String cadena)
	{
		String ceros = ".";
		int numero = (int)Float.parseFloat(cifras==null?"0":cifras);
			  
		for(int i=0;i<numero;i++)
			ceros +="0";
		Locale.setDefault(Locale.US);
		
		DecimalFormat formateador= new DecimalFormat("#,###,###,##0");
		if(numero!=0)
			formateador= new DecimalFormat("#,###,###,##0"+ceros);
		cadena = formateador.format(FSCONVERT(cadena));

		return cadena;
	}
	
//	------------------------------------------------------------------------------------------------------------------------------------------------
	//
	public static String darFormatoCifras(String cadena)
	{
		
		Locale.setDefault(Locale.US);  
		DecimalFormat  formateador= new DecimalFormat("#########.########");
		cadena = formateador.format(FSCONVERT(cadena));

		return cadena;
	}
	
//	------------------------------------------------------------------------------------------------------------------------------------------------
	//
	public static String darFormatoCifrasNoDecimal(String cadena)
	{
		
		Locale.setDefault(Locale.US);  
		DecimalFormat  formateador= new DecimalFormat("#########");
		cadena = formateador.format(FSCONVERT(cadena));

		return cadena;
	}
	
	//	------------------------------------------------------------------------------------------------------------------------------------
	//	Metodo para agregar ceros. ya que al convertirlos a double o long cuando un varchar es escrito con numeros se pierden los de adelante
	public static String agregarCeros(String cadena)
	{
		String retorno	=	"";
			
		int i = soloCeros(cadena) ? 1 : 0;
		
		if(!cadena.trim().equals(""))
		{
			if(cadena.charAt(0) == '0')
			{
				for(; i < cadena.length(); i++)
				{
					if(cadena.charAt(i) == '0')
						retorno	+= "0";
					else
						break;	
				}
			}
		}

		return retorno;
	}

	// Metodo que valida si un objeto String posee caracteres iguales a '0' y '.'
	public static boolean soloCeros(String cadena)
	{
		boolean resultado	=	true;
		
		if(!cadena.trim().equals(""))
		{
			if(cadena.charAt(0) == '0')
			{
				for(int i = 0; i < cadena.length(); i++)
				{
					if(cadena.charAt(i) != '0' && cadena.charAt(i) !='.')
						resultado = false;				
				}
			}
		}	
		
		return resultado;
	}
	
	
	
	public static Double FBRBDECIM1(String string)
	{
		//System.out.println("Estoy en bedecim "  + string);
		
		StringTokenizer strTk=null;
		String resultado="";
		if(string==null )
		{
			return null;
		}
		else
			if(string.equals(""))
			{
				return null;
			}
		
		if(string.equals("null"))
		{
			return null;
		}
			
		if(string.trim().equals("?"))
		{
			return null;
		}
				
		strTk = new StringTokenizer(string,",");
		int tamanio = strTk.countTokens();
		
		for(int i=0;i<tamanio;i++)
		{
			resultado = resultado+strTk.nextElement();
		}
		
		return Double.valueOf(resultado);
	}
	
	/**
	 * Método que convierte un valor string a double tiene en cuenta 's y null's
	 */
	public static double FSCONVERT(String string)
	{
		if(string!=null)
		{
			if(string.equals(""))
				return 0;
			if(string.equals("null"))
				return 0;
		}
		else
			return 0;
		
		return (string!=null?FBRBDECIM1(string).doubleValue():0);
	}
	

//	----------------------------------------------------------------------------------------------------------------------------------------- 
	 public static String completarIzquierda(String cadena,int tamano,char letraCompletar){

	 	int diferencia = 0;
	 	String pad	= "";
	 	
	 	if(cadena.length() < tamano){

	 		diferencia = tamano - cadena.length();
	 		
	 		for(int i = 0;  i < diferencia; i++)
	 			pad +=letraCompletar;

	 		cadena = pad + cadena;
	 		
	 	}
	 	else if(cadena.length() > tamano)
	 		cadena = cadena.substring(0,(tamano));

	 	
	  	return cadena;
	 }	
	
	public static String retornarReferenciaSap(String referencia){

		String sb	=	null;
		try{
			if(referencia!=null&&!referencia.contains(".")&&!referencia.contains(",")){
//				String dato  = (""+darFormatoCifrasNoDecimal(""+Utils.FSCONVERT(referencia)));
				sb = completarIzquierda(referencia, 18, '0');
			}else
				sb = referencia;
		}catch(Exception nfe){
			sb = referencia;
			
		}
		return sb;
	}
	
	public static String quitarCerosReferenciaSap(String referencia){

		 String sb	=	null;

			try{

				int i = Integer.parseInt(referencia);
				sb = String.valueOf(i);

			}catch(Exception nfe){
				sb = referencia;
			}
			return sb;

	 }	
	
	public static String setDouble(String string)
	{	
		StringTokenizer strTk = null;
		String resultado = "";
		if(string == null )
		{
			return null;
		}
		else{
			if(string.equals("null"))
			{
				
				return null;
			}
			else
				if(string.trim().equals(""))
				{
					
					return null;
				}
		}		
		
		strTk = new StringTokenizer(string,",");
		int tamanio = strTk.countTokens();
		for(int i=0; i<tamanio; i++)
		{
			resultado = resultado+strTk.nextElement();
		}	
		
		return Double.valueOf(resultado).toString();
	}
	
	public static float FSCONVERTFLOAT(String string)
	{
		if(string!=null)
		{
			if(string.equals(""))
				return 0;
			if(string.equals("null"))
				return 0;
		}
		
		return (string!=null?getFloat(string).floatValue():0);
	}
	
	private static Float getFloat(String string)
	{	
		StringTokenizer strTk = null;
		String resultado = "";
		if(string == null )
		{
			return new Float(0);
		}
		else{
			if(string.equals("null"))
			{
				return new Float(0);
			}
			else
				if(string.trim().equals(""))
				{
					return new Float(0);
				}
		}		
		
		strTk = new StringTokenizer(string,",");
		int tamanio = strTk.countTokens();
		for(int i=0; i<tamanio; i++)
		{
			resultado = resultado+strTk.nextElement();
		}	
		
		return Float.valueOf(resultado);
	}
	
	/**
	 * Andrés Jaramillo 09/06/2011
	 * Funcion para convertir el formato String a Date con hora y Minuto teniendo encuenta los nulls
	 * SOLO FORMATO yyyy-MM-dd HH:mm, de lo contrario devuelve null
	 * @param fecha
	 * @return fecha
	 */
	public static Date convertirFechaDateTime(String fecha) {
		if(fecha != null && !fecha.equals("")) {
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				return format2.parse(fecha);
			} catch (ParseException e) {
				return null;
			}
		}
		else
			return null;
	}
	
	/**
	 * Andrés Jaramillo 09/06/2011
	 * Funcion para convertir el formato String a Date teniendo encuenta los nulls
	 * SOLO FORMATO yyyy-MM-dd, de lo contrario devuelve null
	 * @param fecha
	 * @return
	 */
	public static Date convertirFechaDate(String fecha) {
		if(fecha != null && !fecha.equals("")) {
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format2.parse(fecha);
			} catch (ParseException e) {
				return null;
			}
		}
		else
			return null;
	}
	
	
	
	/**
	 * Andrés Jaramillo 09/06/2011
	 * Funcion que devuelve la fecha de un campo en String
	 * @param fecha
	 * @return fecha
	 */
	public static String ObtenerFechaString(String fecha) {
		if (fecha != null && !fecha.equals("")) 
			return fecha.substring(0, fecha.indexOf(" "));
		else
			return null;
	}
	
	/**
	 * Andrés Jaramillo 09/06/2011
	 * Función que Convierte un String en Integer
	 * @param inte
	 * @return
	 */
	public static Integer convertStringInteger(String inte){
		if(inte != null && !inte.equals("")){
			return (int)FSCONVERT(inte);
		}
		else
			return null;	
	}
	
	
	/**
	 * Andrés Jaramillo 26/07/2011
	 * Función que devuelve la parte entera sin comas
	 * @param valor
	 * @return entero
	 */
	public static String obtenerParteEntera(String valor){
		String result = "0";
		if(valor != null && !valor.equals("")){
			if(valor.contains("."))
				result = valor.substring(0, valor.indexOf("."));
			else
				result = valor;
		}
		result = result.replaceAll(",", "");		
		return result;
	}
	
	/**
	 * Funcion que convierte un objeto en 
	 * @param objeto
	 * @return
	 */
	public static String convertirString(Object objeto){
		String result = "";
		if(objeto!=null)
			result = objeto.toString();
		return result;
	}
	

//	------------------------------------------------------------------------------------------------------------------------------------------------
	//
	public static String darFormatoCifrasSinComas(String cifras,String cadena)
	{
		String ceros = "";
		int numero = (int)Float.parseFloat(cifras==null?"0":cifras);
			  
		for(int i=0;i<numero;i++)
			ceros +="0";
		Locale.setDefault(Locale.US); 
		DecimalFormat  formateador= new DecimalFormat("#########0."+ceros);
		cadena = formateador.format(FSCONVERT(cadena));

		return cadena;
	}
	/**
	 * Formato con decimales personalizados
	 * @param customized
	 * @return
	 */
	public static Properties  leerPropiedades(Object object)
		{
		 Properties props=null;
		 try {
			 props = new Properties();
			 InputStream inputStream = object.getClass().getClassLoader().getResourceAsStream("conf.properties");
			 if (inputStream == null) {
           
				 throw new FileNotFoundException("property file '" + "conf.properties"+ "' not found in the classpath");
			
			 }

			 props.load(inputStream);
        
		 	 } catch (Exception e) {
				e.printStackTrace();
		 	 }
		
		return props;
	}
	
	public static String nullToBlank(String strPrm){
		String strRtrn = "";
		if(strPrm!=null){
			strRtrn = strPrm;
		}
		return strRtrn;
	}
	
	/**
	 *Metodo que suma o resta días a una fecha en formato yyyy-MM-dd
	 *Retorna String de Fecha en formato yyyy-MM-dd
	 */
	public static String sumaDias(String fecha, String dias)
	{
		if(fecha!=null){
			if(dias!=null){
				Date dfecha = Utils.convertirFechaDate(fecha);
				Calendar cal = new GregorianCalendar();
				cal.setTimeInMillis(dfecha.getTime());
				cal.add(Calendar.DATE, convertStringInteger(dias));
				dfecha = new Date(cal.getTimeInMillis());
				return convertirFechaString(dfecha);
			}else
				return fecha;
		}			
		else
			return null;
	}
	
	/**
	 *Metodo que suma o resta meses a una fecha en formato yyyy-MM-dd
	 *Retorna String de Fecha en formato yyyy-MM-dd
	 */
	public static String sumaMeses(String fecha, String meses)
	{
		if(fecha!=null){
			if(meses!=null){
				Date dfecha = Utils.convertirFechaDate(fecha);
				Calendar cal = new GregorianCalendar();
				cal.setTimeInMillis(dfecha.getTime());
				cal.add(Calendar.MONTH, convertStringInteger(meses));
				dfecha = new Date(cal.getTimeInMillis());
				return convertirFechaString(dfecha);
			}else
				return fecha;
		}			
		else
			return null;
	}
	
	/**
	 *Metodo que suma o resta años a una fecha en formato yyyy-MM-dd
	 *Retorna String de Fecha en formato yyyy-MM-dd
	 */
	public static String sumaAnos(String fecha, String anos)
	{
		if(fecha!=null){
			if(anos!=null){
				Date dfecha = Utils.convertirFechaDate(fecha);
				Calendar cal = new GregorianCalendar();
				cal.setTimeInMillis(dfecha.getTime());
				cal.add(Calendar.YEAR, convertStringInteger(anos));
				dfecha = new Date(cal.getTimeInMillis());
				return convertirFechaString(dfecha);
			}else
				return fecha;
		}			
		else
			return null;
	}

	/**
	 * Funcion para convertir el formato Date a String tomando encuenta nulls
	 * Retorna Formato yyyy-MM-dd
	 * @param fecha
	 * @return campo
	 */
	public static String convertirFechaString(Date fecha) {
		if (fecha != null && !fecha.equals("")) {
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			return format2.format(fecha);
		}
		else
			return null;
	}
	
	/**
	 *Metodo que suma o resta fechas en formato yyyy-MM-dd
	 *Retorna numero de dias
	 */
	public static long restarFechas(String fechaInicial, String fechaFinal)
	{
		if(fechaInicial!=null){
			if(fechaFinal!=null){
				return ( Utils.convertirFechaDate(fechaInicial).getTime() - Utils.convertirFechaDate(fechaFinal).getTime() )/(24 * 60 * 60 * 1000);
				
			}else
				return ( Utils.convertirFechaDate(fechaInicial).getTime())/(24 * 60 * 60 * 1000);
		}			
		else
			return 0;
	}

	public static String agregarCerosIzquierda(String cadena, int longitud) {
		String resultado = "";
		if(cadena!=null){
			if(cadena.length()>longitud)
				resultado = cadena.substring(0, longitud);
			else{
				int i = longitud-cadena.length();
				for(int j=0;j<i;j++){
					resultado = resultado + "0";
				}
				resultado = resultado + cadena;
			}
		}
		return resultado;
	}
	
	/**
	 * Método que obtiene el nombre del archivo adjunto apartir de una url
	 * @return
	 */
	public static String obtenerNomAdjunto(String url){
		String nomAdj = "";
		try {
			if(url != null && !url.equals("")){
				File fl = new File(url);
				
				if(!fl.isDirectory()){
					String[] arrSpt = url.split("\\"+File.separator);
					nomAdj = arrSpt[arrSpt.length - 1];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nomAdj;
	}
	
	/**
	 * Método encargado de validar la url es un directorio
	 * @param url
	 * @return
	 */
	public static boolean directorioValido(String url){
		try {
			File file = new File(url);
			return file.isDirectory();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método encargado de copiar el archivo adjuntado en un directorio especifico
	 * @param nombreArchivo
	 * @param in
	 */
	public static boolean copiarArchivo(String nombreArchivo, InputStream in, String destino) {
		try {
			OutputStream out = new FileOutputStream(new File(destino + nombreArchivo));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			in.close();
			out.flush();
			out.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo en el cual se le ingresa un string(streng), busca un string(soeg) y lo reemplaza (erstat)
	 * @param streng
	 * @param soeg
	 * @param erstat
	 * @return
	 */		
	@SuppressWarnings("rawtypes")
	public static String inyectarValoresDelHash(String streng, HashMap getHm) {
		
		Set unset=getHm.keySet();
		Iterator iterator=unset.iterator();
	
		while(iterator.hasNext())
		{
			String elemento=(String) iterator.next().toString();
			//System.out.println("elemento: " + elemento);
			//System.out.println("control elemento: " + mconsta2.getHm().get(elemento));
		
			streng =  replaceAll(streng, "<#"+elemento+"#>", (String) getHm.get(elemento));
		}

		 return streng;
	}
	
	/**
	 * Método encargado de validar si la fecha inicial es mayor a la final; 
	 * cuando la fecha ini es mayor retorna falso.
	 * @param fechaIni
	 * @param fechaFin
	 * @return
	 */
	public static boolean validarFechaMayor(Date fechaIni, Date fechaFin){
		boolean isOk = true;
		try {
			
			if(fechaIni.getTime() > fechaFin.getTime()){
				isOk = false;
			}
			
		} catch (Exception e) {
			return false;
		}
		
		return isOk;
	}
	
	
	/**
	 * 
	 * @param fileName
	 * @param in
	 */
//	public static void copyFile(String fileName, InputStream in, String destination) {
//        try {
//           
//           
//             // write the inputStream to a FileOutputStream
//             OutputStream out = new FileOutputStream(new File(destination + fileName));
//           
//             int read = 0;
//             byte[] bytes = new byte[1024*4];
//           
//             while ((read = in.read(bytes)) != -1) {
//                 out.write(bytes, 0, read);
//             }
//           
//             in.close();
//             out.flush();
//             out.close();
//             } catch (IOException e) {
//            	 e.printStackTrace();
//             }
//	}
	
	public static void copyFile(String fileName, InputStream in, String destination) {

		try {
			
			File outFile = new File(destination+fileName);
			FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while( (c = in.read() ) != -1)
				out.write(c);

//			in.close();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param text
	 * @param strPattern
	 * @return
	 */
	public static boolean validatePattern(String text, String strPattern) {
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
	
	public static String ipLocal(){
		try {
			return Inet4Address.getLocalHost().getHostAddress();
		} catch (Exception e) {
			return "";
		}		
	}
	
	/**
	 * 
	 * @param cls
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String generarXmlJaxb(Class cls, Object obj, String encoding){
		String xml = "";
		try {

			JAXBContext context = JAXBContext.newInstance(cls);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");
			
			if(encoding != null){
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}
			
			OutputStream output = new OutputStream() {
				private StringBuilder string = new StringBuilder();

				@Override
				public void write(int b) throws IOException {
					this.string.append((char) b);
				}

				@Override
				public String toString() {
					return this.string.toString();
				}
			};
			
			
			//////////////////////
			
			
			////////////////////////
			

			marshaller.marshal(obj, output);
			xml = output.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return xml;
	}
	
	
	/**
	 * Agrega ceros a la hizquierda hasta completar la longitud maxima
	 * @param cadena
	 * @param maxLength
	 * @return
	 */
	public static String agregarCeros(String cadena, int maxLength){		
		try {
			if(cadena != null){
                //Agrega ceros a la hizquierda hasta completar la longitud maxima
                cadena = String.format("%0"+(maxLength-1)+"d", 0).concat(cadena);
            }	                    
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return cadena;
	}
	
	/**
	 * Remueve todos los caracteres especiales asignados en Constantes_.CARACTERES_ESPECIALES del string ingresado 
	 * @param cadena
	 * @return
	 */
	public static String removerCaracteresEspeciales(String cadena){
		try {
			if(cadena != null){
				for (int i = 0; i < Constantes.CARACTERES_ESPECIALES.length(); i++) {
					cadena = cadena.replaceAll("\\"+Constantes.CARACTERES_ESPECIALES.charAt(i), "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadena;
	}
	
	/**
	 * Convierte una cadena String a tipo int
	 * @param cadena
	 * @return
	 */
	public static int convertToInt(String cadena){
		int return_  = 0;
		try {
			if(cadena != null){
				return_ = Integer.parseInt(cadena);
			}
		} catch (Exception e) {
			return_ = 0;
		}
		return return_;
	}
	
	
	/**
	 * Convierte una cadena String a tipo int
	 * @param cadena
	 * @return
	 */
	public static Long convertToLong(String cadena){
		Long return_ = 0L;
		try {
			if(cadena != null){
				return_ = Long.parseLong(cadena);
			}
		} catch (Exception e) {
			return return_;
		}
		return return_;
	}
	
	public static Integer convertInteger(Object obj){
		try {
			if(!isEmptyOrNull(obj)){
				return new Integer(obj.toString());
			}else return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Substrae de la cadena principal el fragmento desde el index inicial hasta el final enviado como parametro.
	 * Si el index final supera el tamaño de la cadena entonces devuelve la misma cadena.
	 * @param cadena
	 * @param indexIni
	 * @param indexFin
	 * @return
	 */
    public static String strSubstring(String cadena, int indexIni, int indexFin){
        try {
            if(cadena != null && cadena.length() >= indexFin){
                cadena = cadena.substring(indexIni, indexFin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return cadena;
    }
    
    public static String splitCampos(String cadena,String regex1, String regex2){
	
    	String resultado=""; 
    	if(cadena != null && regex1 !=null && regex2!=null)
    	{
    		String[] cadenaAux= cadena.split(regex1);
    		for(String cadAux:cadenaAux)
    		{
    			String[] cadAux2= cadAux.split(regex2);
    			resultado=resultado+cadAux2[0];
    		}    		
    	}    	
    	return resultado;
    }
    
    public static Long generarNumeroAleatorio(){
		try {
			Random  rnd = new Random();
			return rnd.nextLong();
		} catch (Exception e) {
			return 0L;
		} 
	}
    
    /*
     * Metodo encargado de encriptar la contraseña al momento de hacer login
     */
	public static String encriptar(String texto) throws IllegalStateException {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA"); // Instancia de generador SHA-1
		}
		catch(NoSuchAlgorithmException e) {
			throw new IllegalStateException(e.getMessage());
		}

		try {
			md.update(texto.getBytes("UTF-8")); // Generación de resumen de mensaje
		}
		catch(UnsupportedEncodingException e) {
			throw new IllegalStateException(e.getMessage());
		}

		byte raw[] = md.digest(); // Obtención del resumen de mensaje
		String hash = (new BASE64Encoder()).encode(raw); // Traducción a BASE64
		return hash;
	}
	
	public static BufferedImage getQR(HashMap<String, String> params){
		int qr_image_width = 236;
	    int qr_image_height = 236;
		BitMatrix matrix;
		Writer writer = new QRCodeWriter();
		String data = "";
		int i=0;
		BufferedImage bi=null;
		for(Entry<String, String> entry:params.entrySet()){
			i++;
			if(i==params.size()){
				data += entry.getKey()+entry.getValue();
			}else{
				data += entry.getKey()+entry.getValue()+"\r\n";
			}
		}
//		Image image=null;
		try {
			matrix = writer.encode(data, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
			bi = new BufferedImage(qr_image_width,qr_image_height, BufferedImage.TYPE_INT_RGB);
			
			for (int y = 0; y < qr_image_height; y++) {
	            for (int x = 0; x < qr_image_width; x++) {
	                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
	                bi.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
	            }
	        }
			
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write( bi, "png", baos );
//			baos.flush();
//			byte[] imageInByte = baos.toByteArray();
//			image = Image.getInstance(imageInByte);
//			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bi;
	}
	
}
