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
import com.tlm.faelecEntities.model.entities.Macdio00;


@ManagedBean
@RequestScoped
public class Macdio00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Macdio00 macdio00Disable = (Macdio00)session.getAttribute(Macdio00.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Macdio00();
        } else { 
        	
        	for (Macdio00 p : seListas.getListMacdio00()) {  
               // if (p.getMgenus00().getCodius().trim().equals(submittedValue)) {  //Campo de la clave primaria
                 //   return p;  
                //}  
            }  
        	
        	//if(macdio00Disable != null && macdio00Disable.getMgenus00().getCodius().equals(submittedValue)){
            	//return (Macdio00)macdio00Disable;
        	//}
        } 
		
		return new Macdio00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return null;//String.valueOf(((Macdio00) value).getMgenus00().getCodius()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}
