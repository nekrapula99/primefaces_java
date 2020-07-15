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
import com.tlm.faelecEntities.model.entities.Mpropr00;


@ManagedBean
@RequestScoped
public class Mpropr00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mpropr00 Mpropr00Disable = (Mpropr00)session.getAttribute(Mpropr00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Mpropr00();
        } else { 
        	
        	for (Mpropr00 p : seListas.getListMpropr00()) {  
                if (p.getCodcpr().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(Mpropr00Disable != null && Mpropr00Disable.getCodcpr().equals(submittedValue)){
            	return (Mpropr00)Mpropr00Disable;
        	}
        } 
		
		return new Mpropr00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mpropr00) value).getCodcpr()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}
