package com.tlm.faelec.faces.converter;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelecEntities.model.entities.Fuentes;


@ManagedBean
@RequestScoped
@FacesConverter("fuentesConverter")
public class FuentesConverter implements Converter, Serializable {
	private static final long serialVersionUID = 8148756390411412978L;
	/**
	 * 
	 */
	//@ManagedProperty("#{seMparpd00}")
	//private SeMparpd00 seMparpd00;
	
	
	@Override
	 public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {  
            	HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
//        		Mconca00 mconca00Disable = (Mconca00)session.getAttribute(Mconca00.class.getSimpleName());
            	@SuppressWarnings("unchecked")
				List<Fuentes> listFuentes = (List<Fuentes>)session.getAttribute("listaFuentes");
            			
                for (Fuentes f : listFuentes) {  
                    if (f.getFuente().equals(value)) {
                        return f;  
                    }  
                }  
            } catch(NumberFormatException e) {
                e.printStackTrace();
            }
        }
        else {
            return null;
        }
        
        return null;
    }
	
	@Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Fuentes) object).getFuente());
        }
        else {
            return null;
        }
    }

	/*public SeMparpd00 getSeMparpd00() {
		return seMparpd00;
	}

	public void setSeMparpd00(SeMparpd00 seMparpd00) {
		this.seMparpd00 = seMparpd00;
	}*/

}
