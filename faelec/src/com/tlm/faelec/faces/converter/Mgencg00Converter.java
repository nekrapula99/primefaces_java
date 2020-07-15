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
import com.tlm.faelec.web.controller.SeMgencg00;
import com.tlm.faelecEntities.model.entities.Mgencg00;

@ManagedBean
@RequestScoped
public class Mgencg00Converter implements Converter,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1024669314270293519L;

	@ManagedProperty("#{seMgencg00}")
	private SeMgencg00 seMgencg00;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		submittedValue = submittedValue.trim();
		
		FacesContext contextAux = FacesContext.getCurrentInstance();
		String codtcg = (String) UIComponent.getCurrentComponent(contextAux).getAttributes().get("codtcg");
		
		//Esta variable se utiliza cuando se busca desde el maestro de parametros
		String clase = (String) UIComponent.getCurrentComponent(context).getAttributes().get("clase");
		if(clase!=null){
			 if(clase.equalsIgnoreCase("Parametro")){
				 codtcg=null;	
			 }
		}
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mgencg00 mgencg00Disable = (Mgencg00)session.getAttribute(Mgencg00.class.getSimpleName()+codtcg);
		
        List<Mgencg00> listMgencg00 = new ArrayList<Mgencg00>();
        if (codtcg!=null){
	        if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mtipre00"))){
	        	listMgencg00 = seListas.getListMgencgArbol();
	        }
	        else if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mtiptx00"))){
	        	listMgencg00 = seListas.getListMgencgCodTransaccion();
	        }else if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_mconca00"))){
	        	listMgencg00 = seListas.getListMgencgCompania();
	        }else if (codtcg.equalsIgnoreCase(Utils.paramsRb.getString("mgencg_codtcg_evento"))){
	        	listMgencg00 = seListas.getListMgencg00Evento();
	        }
        }
        
        //Condicion utilizada cuando se realiza una busqueda desde el maestro de parametros
       if(clase!=null){
	        if(clase.equalsIgnoreCase("Parametro")){
	        	listMgencg00 = seMgencg00.getListMgencg00();
	        }
       }
        
		if (submittedValue.equals("")) {  
            return new Mgencg00();
        } else { 
        		if(listMgencg00!=null){
		        	for (Mgencg00 p : listMgencg00) {        		
		                if (p.getCodicg().trim().equals(submittedValue)) {
		                    return p;  
		                }  
		            } 
        		}

        	if(mgencg00Disable != null && mgencg00Disable.getCodicg().equals(submittedValue)){
            	return (Mgencg00)mgencg00Disable;
        	}
        } 
		
		return new Mgencg00();
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mgencg00) value).getCodicg());
        } 
	}
	
	//Metodos Getter y Setter
	public SeMgencg00 getSeMgencg00() {
		return seMgencg00;
	}
	public void setSeMgencg00(SeMgencg00 seMgencg00) {
		this.seMgencg00 = seMgencg00;
	}
	public SeListas getSeListas() {
		return seListas;
	}
	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}
		

}
