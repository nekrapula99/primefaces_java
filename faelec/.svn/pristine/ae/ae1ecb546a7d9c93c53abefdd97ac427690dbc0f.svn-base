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
import com.tlm.faelecEntities.model.entities.Mregcg00;


@ManagedBean
@RequestScoped
public class Mregcg00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mregcg00 Mregcg00Disable = (Mregcg00)session.getAttribute(Mregcg00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Mregcg00();
        } else { 
        	
        	for (Mregcg00 p : seListas.getListMregcg00()) {  
                if (p.getCoregc().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(Mregcg00Disable != null && Mregcg00Disable.getCoregc().equals(submittedValue)){
            	return (Mregcg00)Mregcg00Disable;
        	}
        } 
		
		return new Mregcg00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mregcg00) value).getCoregc()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}

