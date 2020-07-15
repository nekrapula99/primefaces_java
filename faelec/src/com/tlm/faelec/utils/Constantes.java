package com.tlm.faelec.utils;


import java.util.Locale;
import java.util.ResourceBundle;

public class Constantes {
	public  static ResourceBundle params = ResourceBundle.getBundle("com.tlm.faelec.utils.params");
	public  static ResourceBundle rb = ResourceBundle.getBundle("com.tlm.faelec.resources.ApplicationResources",Locale.getDefault());
	/*
	 * Encoding
	 */
	public static final String ENCODING_ISO88591 = "ISO-8859-1";
	
	
	/*
	 * 
	 */
	public static final String FORMATO_FECHA_ESTANDAR = "yyyy-MM-dd";
	public static final String CARACTERES_ESPECIALES = ".,-";
	
	
	/*
	 *	Formularios 
	 */
	public static final String ACCION_NUEVO = "N";
	public static final String ACCION_MODIFICAR = "M";
	public static final String ACCION_LISTAR = "L";
	public static final String COD_MCONCA00_ADMIN = "00";
	public static final String NAME_VISTA_APP = "vistaApp";
	
	/*
	 *	Listas de valores 
	 */
	public static final String CODIGO_TIPO_REG = Utils.CODIGO_TIPO_REG;
	
	/*
	 * Constantes Auditoria
	 */
	public static final String AUDIT_NUEVO = "NUEVO";
	public static final String AUDIT_ACTUALIZAR = "ACTUALIZAR";
	public static final String AUDIT_ELIMINAR = "ELIMINAR";
	
	/*
	 * Constantes eventos
	 */
	public static final String EVENTO_NUEVO = "NUEVO";
	public static final String EVENTO_MODIFICAR = "ACTUALIZAR";
	public static final String EVENTO_ELIMINAR = "ELIMINAR";
	public static final String EVENTO_IMPRIMIR = "EXCEL";
}
