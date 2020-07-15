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
import com.tlm.faelecEntities.model.entities.Mconca00;


@ManagedBean
@RequestScoped
public class Mconca00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mconca00 mconca00Disable = (Mconca00)session.getAttribute(Mconca00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Mconca00();
        } else { 
        	
        	for (Mconca00 p : seListas.getListMconca00Pal()) {  
                if (p.getCodcia().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(mconca00Disable != null && mconca00Disable.getCodcia().equals(submittedValue)){
            	return (Mconca00)mconca00Disable;
        	}
        } 
		
		return new Mconca00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mconca00) value).getCodcia()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}
