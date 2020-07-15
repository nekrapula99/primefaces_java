package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class SeCabecera implements Serializable
{
	/**
	 * 
	 */
	
	@ManagedProperty("#{seControlFact}")
	private SeControlFactura seControl; 
	

	private static final long serialVersionUID = 4703686445457372820L;
	/*
	 * Variables Managed
	 */
	private String pac;
	private Object objCall;  //objeto que recibe el MBean desde donde se hace el llamado
	private String indusu = "D"; //Pendiente
	private String accion;
	private String varAdicional;
	private boolean botonNuevo;
	private boolean botonExcel;
	private boolean botonPdf;	
	

	/*
	 * Botones
	 */
	public static String ID_BOTON_NUEVO = "nuevoLink";
	public static String ID_BOTON_EXCEL = "excelLink";
	public static String ID_BOTON_PDF = "pdfLink";

	
	/*
	 * Construct
	 */
	public SeCabecera(){
	}
	
	public void postConstruct(){
		RequestContext.getCurrentInstance().update("formCabecera:pnlButtons");
		getSeControl().getTitulo();
		System.out.println("Valor titulo cabecera nekra"+getSeControl().getTitulo());
	}
	
	public void desactivarBotones(){
		this.botonNuevo = false;
		this.botonExcel = false;
		this.botonPdf = false;
	}
	
	
	/*
	 * Pinta los botones que se hayan enviado por parametro desde cabecera.xhtml
	 */
	public void pintarBotones(String ...boton){
		try {
			if(boton != null){
				String botones = Arrays.toString(boton);
				this.botonNuevo = botones.contains(ID_BOTON_NUEVO);
				this.botonExcel = botones.contains(ID_BOTON_EXCEL);
				this.botonPdf = botones.contains(ID_BOTON_PDF);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
		/**
	 * 
	 */
	public void ejecutarAccion(){
		try {
			//String strObjCall = objCall.getClass().getSimpleName();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Ejecuta cualquier accion de cada Managed Beean
	 */
	public void nuevoRegistro(){
		try {
			
			if(objCall == null){
				return;
			}
			
			String strClassLlama = objCall.getClass().getSimpleName();
			
			if(strClassLlama.equalsIgnoreCase("SeMpaise00")){
//				SeMpaise00 seMpaise00 = (SeMpaise00)objCall;
//				seMpaise00.accionNuevo();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean permisoNuevo(){
		boolean permiso = true;
		try {
			FacesContext facesContext=FacesContext.getCurrentInstance();
			HttpServletRequest request=(HttpServletRequest)facesContext.getExternalContext().getRequest();
			String paginaActual = (String)request.getSession().getAttribute("PAGINA_ACTUAL");
			System.out.println("//paginaActual: "+paginaActual);
			
			if(paginaActual.contains("pacMparme00.xhtml")){
				permiso = false;
				RequestContext.getCurrentInstance().update("formCabecera");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return permiso;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public Object getObjCall() {
		return objCall;
	}

	public void setObjCall(Object objCall) {
		this.objCall = objCall;
	}

	public String getIndusu() {
		return indusu;
	}

	public void setIndusu(String indusu) {
		this.indusu = indusu;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getVarAdicional() {
		return varAdicional;
	}

	public void setVarAdicional(String varAdicional) {
		this.varAdicional = varAdicional;
	}

	public boolean isBotonNuevo() {
		return botonNuevo;
	}

	public void setBotonNuevo(boolean botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	public SeControlFactura getSeControl() {
		return seControl;
	}

	public void setSeControl(SeControlFactura seControl) {
		this.seControl = seControl;
	}

	public boolean isBotonExcel() {
		return botonExcel;
	}

	public void setBotonExcel(boolean botonExcel) {
		this.botonExcel = botonExcel;
	}
	
	public boolean isBotonPdf() {
		return botonPdf;
	}

	public void setBotonPdf(boolean botonPdf) {
		this.botonPdf = botonPdf;
	}

}
