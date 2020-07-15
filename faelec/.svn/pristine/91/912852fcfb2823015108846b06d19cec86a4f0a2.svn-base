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
import com.tlm.faelecEntities.model.entities.Maccio00;


@ManagedBean
@RequestScoped
public class Maccio00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Maccio00 maccio00Disable = (Maccio00)session.getAttribute(Maccio00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Maccio00();
        } else { 
        	
        	for (Maccio00 p : seListas.getListMaccio00()) {  
                if (p.getPgccio().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(maccio00Disable != null && maccio00Disable.getPgccio().equals(submittedValue)){
            	return (Maccio00)maccio00Disable;
        	}
        } 
		
		return new Maccio00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Maccio00) value).getPgccio()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}
