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

import com.tlm.faelec.web.controller.SeTqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;


@ManagedBean
@RequestScoped
public class Tqdpqp00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seTqdfqf00}")
	private SeTqdfqf00 seTqdfqf00;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Tqdpqp00 Tqdpqp00Disable = (Tqdpqp00)session.getAttribute(Tqdpqp00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Tqdpqp00();
        } else { 
        	
        	for (Tqdpqp00 p : seTqdfqf00.getListTqdpqp00()) {  
                if (p.getMpropr00().getCodipr().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(Tqdpqp00Disable != null && Tqdpqp00Disable.getMpropr00().getCodcpr().equals(submittedValue)){
            	return (Tqdpqp00)Tqdpqp00Disable;
        	}
        } 
		
		return new Tqdpqp00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Tqdpqp00) value).getMpropr00().getCodcpr()); //Campo de la clave primaria 
        } 
	}


	public SeTqdfqf00 getSeTqdfqf00() {
		return seTqdfqf00;
	}

	public void setSeTqdfqf00(SeTqdfqf00 seTqdfqf00) {
		this.seTqdfqf00 = seTqdfqf00;
	}

}

