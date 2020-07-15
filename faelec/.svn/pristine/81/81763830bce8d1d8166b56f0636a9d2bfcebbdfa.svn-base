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
import com.tlm.faelecEntities.model.entities.Mfunfu00;


@ManagedBean
@RequestScoped
public class Mfunfu00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mfunfu00 Mfunfu00Disable = (Mfunfu00)session.getAttribute(Mfunfu00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Mfunfu00();
        } else { 
        	
        	for (Mfunfu00 p : seListas.getListMfunfu()) {  
                if (p.getCocufu().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(Mfunfu00Disable != null && Mfunfu00Disable.getCocufu().equals(submittedValue)){
            	return (Mfunfu00)Mfunfu00Disable;
        	}
        } 
		
		return new Mfunfu00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mfunfu00) value).getCocufu()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}

