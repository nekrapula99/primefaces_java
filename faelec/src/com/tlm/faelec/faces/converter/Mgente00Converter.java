package com.tlm.faelec.faces.converter;

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
import com.tlm.faelec.web.controller.SeMgente00;
import com.tlm.faelecEntities.model.entities.Mgente00;



@ManagedBean
@RequestScoped
public class Mgente00Converter implements Converter{
	
	@ManagedProperty("#{seMgente00}")
	private SeMgente00 seMgente00;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext contextAux = FacesContext.getCurrentInstance();
		String codtte = (String) UIComponent.getCurrentComponent(contextAux).getAttributes().get("codtte");
		
		//Esta variable se utiliza cuando se busca desde el maestro de parametros
		String clase = (String) UIComponent.getCurrentComponent(context).getAttributes().get("clase");
			if(clase!=null){
				 if(clase.equalsIgnoreCase("Parametro")){
					 codtte=null;
				 }
			}		
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mgente00 mgente00Disable = (Mgente00)session.getAttribute(Mgente00.class.getSimpleName()+codtte);
		
        List<Mgente00> listMgente00Aux = new ArrayList<Mgente00>();
        if (codtte!=null){
        	if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_proveedor"))){
	        	listMgente00Aux = seListas.getListMgenteCodtte();
	        }    
	        else if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_representanteLegal"))){
	        	listMgente00Aux = seListas.getListMgenteReplegal();
	        }else if (codtte.equalsIgnoreCase(Utils.paramsRb.getString("mgente_codtte_cliente"))){
	        	listMgente00Aux = seListas.getListMgenteCliente();
	        }
        }
        
        //Condicion utilizada cuando se realiza una busqueda desde el maestro de parametros
        if(clase!=null){
	        if(clase.equalsIgnoreCase("Parametro")){
	        	listMgente00Aux = seMgente00.getListMgente00();
	        }
        }
		
		if (submittedValue.equals("")) {  
            return new Mgente00();
        } else { 
        	for (Mgente00 p : listMgente00Aux) {  
                if (p.getCodite().trim().equals(submittedValue)) {
                    return p;  
                }  
            }  
        	
        	if(mgente00Disable != null && mgente00Disable.getCodite().equals(submittedValue)){
            	return (Mgente00)mgente00Disable;
        	}
        } 
		
		return new Mgente00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mgente00) value).getCodite());
        } 
	}

	
	/*
	 * Get y Set
	 */
	public SeMgente00 getSeMgente00() {
		return seMgente00;
	}

	public void setSeMgente00(SeMgente00 seMgente00) {
		this.seMgente00 = seMgente00;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

}
