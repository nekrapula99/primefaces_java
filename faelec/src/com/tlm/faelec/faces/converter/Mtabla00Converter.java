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
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.web.controller.SeMtabla00;
import com.tlm.faelecEntities.model.entities.Mtabla00;


@ManagedBean
@RequestScoped
public class Mtabla00Converter implements Converter{
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		if(!Utils.isEmptyOrNull(submittedValue)){
			submittedValue = submittedValue.trim();
		}else {
			return new Mtabla00();
		}
		
		
		if (submittedValue.equals("")) {  
            return new Mtabla00();
        } else { 
        	
        	for (Mtabla00 p : seListas.getListMtabla00()) {  
                if (p.getId().getCodtab().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        } 
		
		return new Mtabla00();
	}

	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {   
            return "";  
        } else {
        	if(((Mtabla00)value).getId()!=null)
        	{ 	  
        		return String.valueOf(((Mtabla00) value).getId().getCodtab());
        	}
        	else
        		return "";
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

}
