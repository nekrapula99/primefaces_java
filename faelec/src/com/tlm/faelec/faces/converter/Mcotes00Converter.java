package com.tlm.faelec.faces.converter;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.servlet.http.HttpSession;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelecEntities.model.entities.Mcotes00;


@ManagedBean
@RequestScoped
public class Mcotes00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mcotes00 Mcotes00Disable = (Mcotes00)session.getAttribute(Mcotes00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Mcotes00();
        } else { 
        	
        	for (Mcotes00 p : seListas.getListMcotes00()) {  
                if (p.getCoctes().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(Mcotes00Disable != null && Mcotes00Disable.getCoctes().equals(submittedValue)){
            	return (Mcotes00)Mcotes00Disable;
        	}
        } 
		
		return new Mcotes00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mcotes00) value).getCoctes()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}

