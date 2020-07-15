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
import com.tlm.faelec.web.controller.SeMtipre00;
import com.tlm.faelecEntities.model.entities.Mtipre00;


@ManagedBean
@RequestScoped
public class Mtipre00Converter implements Converter, Serializable{
	private static final long serialVersionUID = 9096687522109318795L;
	
//	@ManagedProperty("#{seMtipre00}")
//	private SeMtipre00 seMtipre00;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		if(submittedValue != null){
			submittedValue = submittedValue.trim();	
		}else return new Mtipre00();
		
		
		FacesContext contextAux = FacesContext.getCurrentInstance();
		String tperre = (String) UIComponent.getCurrentComponent(contextAux).getAttributes().get("tperre");
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mtipre00 mtipre00Disable = (Mtipre00)session.getAttribute(Mtipre00.class.getSimpleName());
		
        List<Mtipre00> listMtipreAux = new ArrayList<Mtipre00>();
        
        if (tperre==null){
        	listMtipreAux=seListas.getListMtipre00();
        }else{
        	if(tperre.equalsIgnoreCase(Utils.paramsRb.getString("mtipre_tperre_mgente00"))){
        		listMtipreAux=seListas.getListMtipreTerceros();
        	}
        	if(tperre.equalsIgnoreCase(Utils.paramsRb.getString("mtipre_tperre_mgencg00"))){
        		listMtipreAux=seListas.getListMtipreGConfi();
        	}
        }
        
		if (submittedValue.equals("")) {  
            return new Mtipre00();
        } else { 
        	
        	for (Mtipre00 p : listMtipreAux) {  
                if (p.getTipore().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }
        	if(mtipre00Disable != null && mtipre00Disable.getTipore().equals(submittedValue)){
            	return (Mtipre00)mtipre00Disable;
        	}
        } 
		
		return new Mtipre00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {   
            return "";  
        } else {  
            return String.valueOf(((Mtipre00) value).getTipore());
        } 
	}


	/*
	 * Get y Set
	 */
	
	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}
	
//	public SeMtipre00 getSeMtipre00() {
//		return seMtipre00;
//	}
//
//	public void setSeMtipre00(SeMtipre00 seMtipre00) {
//		this.seMtipre00 = seMtipre00;
//	}

}
