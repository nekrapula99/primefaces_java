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
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;


@ManagedBean
@RequestScoped
public class Mtiptx00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();

		FacesContext facesContext=FacesContext.getCurrentInstance();
		
		String listMtipo = (String) UIComponent.getCurrentComponent(facesContext).getAttributes().get("listMtipo");
		
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mtiptx00 mtiptx00Disable = (Mtiptx00)session.getAttribute(Mtiptx00.class.getSimpleName());
		
		 List<Mtiptx00> listMtiptx00 = new ArrayList<Mtiptx00>();
		
		if (submittedValue.equals("")) { 
            return new Mtiptx00();
        } else { 
        	if(!Utils.isEmptyOrNull(listMtipo) && listMtipo.equalsIgnoreCase(Utils.paramsRb.getString("mtiptx_estado")))
        	{
        		listMtiptx00= seListas.getListMtiptxEstado();
        	}
        	else
        	{
        		listMtiptx00= seListas.getListMtiptx00();
        	}
        }
        	
		if (submittedValue.equals("")) {
			return new Mtiptx00();
		} else {
			if (listMtiptx00 != null) {
				for (Mtiptx00 p : listMtiptx00) {
					if (p.getCotrtx().equals(submittedValue)) { 
						return p;
					}
				}
			}
			if (mtiptx00Disable != null
					&& mtiptx00Disable.getCotrtx().equals(submittedValue)) {
				return (Mtiptx00) mtiptx00Disable;
			}
		}
        
		
		return new Mtiptx00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mtiptx00) value).getCotrtx()); //Campo de la clave primaria 
        } 
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}
