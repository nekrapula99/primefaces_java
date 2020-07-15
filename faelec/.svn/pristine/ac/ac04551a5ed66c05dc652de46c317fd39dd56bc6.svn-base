package com.tlm.faelec.faces.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelecEntities.model.entities.Mmesje00;



@ManagedBean
@RequestScoped
public class Mmesje00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
//	@ManagedProperty("#{seMmesje00}")
//	private SeMmesje00 seMmesje00;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		if (submittedValue.equals("")) {  
            return new Mmesje00();
        } else { 
        	
        	for (Mmesje00 p : seListas.getListMmesje00()) {  
                if (p.getCodums().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        } 
		
		return new Mmesje00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {   
            return "";  
        } else {  
            return String.valueOf(((Mmesje00) value).getCodums());
        } 
	}

	
	/*
	 * Get y Set
	 */
	

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

//	public SeMmesje00 getSeMmesje00() {
//		return seMmesje00;
//	}
//
//	public void setSeMmesje00(SeMmesje00 seMmesje00) {
//		this.seMmesje00 = seMmesje00;
//	}




}
