package com.tlm.faelec.faces.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.servlet.http.HttpSession;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;

@ManagedBean
@RequestScoped
public class Mestad00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		
		Integer idTxt = (Integer) UIComponent.getCurrentComponent(facesContext).getAttributes().get("idTxt");
			
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mestad00 mestad00Disable = (Mestad00)session.getAttribute(Mestad00.class.getSimpleName());

		
		 List<Mestad00> listMestad00 = new ArrayList<Mestad00>();
			
			if (submittedValue.equals("")) { 
	            return new Mtiptx00();
	        } else { 
	        	if(!Utils.isEmptyOrNull(idTxt))
	        	{	        	
	        		listMestad00=seListas.getListMestad00();
	        	}
	        	else
	        	{
	        		listMestad00= seListas.getListMestad00();
	        	}
	        }
		
		if (submittedValue.equals("")) { 
            return new Mestad00();
        } else { 
        	
        	for (Mestad00 p : listMestad00) {  
                if (p.getDscres().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(mestad00Disable != null && mestad00Disable.getDscres().equals(submittedValue)){
            	return (Mestad00)mestad00Disable;
        	}
        } 
		
		return new Mestad00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mestad00) value).getDscres()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	

}
