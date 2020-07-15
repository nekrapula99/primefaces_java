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

import com.tlm.faelec.web.controller.SeMidiom00;
import com.tlm.faelecEntities.model.entities.Midiom00;



@ManagedBean
@RequestScoped
public class Midiom00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 9096687522109318795L;
	
	@ManagedProperty("#{seMidiom00}")
	private SeMidiom00 seMidiom00;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Midiom00 idiom00Disable = (Midiom00)session.getAttribute(Midiom00.class.getSimpleName());
		
		if (submittedValue.equals("")) {  
            return new Midiom00();
        } else { 
        	
        	for (Midiom00 p : seMidiom00.getListMidiom00()) {  
                if (p.getCodidi().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }
        	if(idiom00Disable != null && idiom00Disable.getCodidi().equals(submittedValue)){
            	return (Midiom00)idiom00Disable;
        	}
        } 
		
		return new Midiom00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {   
            return "";  
        } else {  
            return String.valueOf(((Midiom00) value).getCodidi());
        } 
	}

	
	/*
	 * Get y Set
	 */
	public SeMidiom00 getSeMidiom00() {
		return seMidiom00;
	}

	public void setSeMidiom00(SeMidiom00 seMidiom00) {
		this.seMidiom00 = seMidiom00;
	}

}
