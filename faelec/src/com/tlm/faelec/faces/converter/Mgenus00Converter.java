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
//import com.tlm.decimex.forms.mgenus00.seListas;
//import com.tlm.decimex.persist.entity.Mgenus00;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.web.controller.SeMgenus00;
import com.tlm.faelec.web.controller.SeTqigqg00;
import com.tlm.faelecEntities.model.entities.Mgenus00;

@ManagedBean
@RequestScoped
public class Mgenus00Converter implements Converter{
	
	@ManagedProperty("#{seMgenus00}")
	private SeMgenus00 seMgenus00;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) throws ConverterException {

		if(submittedValue==null)
		{
		  return null;	
		}
		
		submittedValue = submittedValue.trim();
		 
		
		FacesContext contextAux = FacesContext.getCurrentInstance();
		String codtus = (String) UIComponent.getCurrentComponent(contextAux).getAttributes().get("codtus");
		
		//Esta variable se utiliza cuando se busca desde el maestro de parametros
		String clase = (String) UIComponent.getCurrentComponent(context).getAttributes().get("clase");
		if(clase!=null){
			 if(clase.equalsIgnoreCase("Parametro")){
				 codtus=null;	
			 }
		}
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Mgenus00 mgenus00Disable = (Mgenus00)session.getAttribute(Mgenus00.class.getSimpleName()+codtus);
		
        List<Mgenus00> listMgenus00 = new ArrayList<Mgenus00>();
        if (codtus!=null){
	        if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoIdentificacion"))){
	        	listMgenus00 = seListas.getListMgenusTident00();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_ciudades"))){
	        	listMgenus00 = seListas.getListMgenusCiudad00();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_pais"))){
	        	listMgenus00 = seListas.getListMgenusPaises();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_marcaComercial"))){
	        	listMgenus00 = seListas.getListMgenusMarcaComercial00();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_lineaProductos"))){
	        	listMgenus00 = seListas.getListMgenusLineaProductos();
	        } else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_sublineaProductos"))){
	        	listMgenus00 = seListas.getListMgenusSubLineaProductos();
	        } else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"))){
	        	listMgenus00 = seListas.getListMgenusUnidadMedida();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_centroCostos"))){
	        	listMgenus00 = seListas.getListMgenusCentroCostos();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_ClaseDocumentos"))){
	        	listMgenus00 = seListas.getListMgenusClaseDocumentos();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_documento"))){
	        	listMgenus00 = seListas.getListMgenusDocumentos();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Region"))){
	        	listMgenus00 = seListas.getListMgenusRegion();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Departamento"))){
	        	listMgenus00 = seListas.getListMgenusDepartamento();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoNaturaleza"))){
	        	listMgenus00 = seListas.getListMgenusTipoNaturaleza();
	        } else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_variantes"))){
	        	listMgenus00 = seListas.getListMgenus00Variantes();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_evento"))){
	        	listMgenus00 = seListas.getListMgenus00Evento();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_monedas"))){
	        	listMgenus00 = seListas.getListMgenusMoneda();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipo_contribuyente"))){
	        	listMgenus00 = seListas.getListMgenusContribuyente();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipo_regimen"))){
	        	listMgenus00 = seListas.getListMgenusRegimen();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_saludo"))){
	        	listMgenus00 = seListas.getListMgenusSaludo();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_areaLabor"))){
	        	listMgenus00 = seListas.getListMgenusAreaLabor();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_cargoTercero"))){
	        	listMgenus00 = seListas.getListMgenusCargoTercero();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_mediopago"))){
	        	listMgenus00 = seListas.getListMgenusMedioPago();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Incoterm"))){
	        	listMgenus00 = seListas.getListMgenusIncoterms();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_OrganiVentas"))){
	        	listMgenus00 = seListas.getListMgenusOrganiVentas();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_CanalDistri"))){
	        	listMgenus00 = seListas.getListMgenusCanalDistri();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"))){
	        	listMgenus00 = seListas.getListMgenusUnidadMedidaIng();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TipoTransaccion"))){
	        	listMgenus00 = seListas.getListMgenusTipoTransaccion();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_EstdPart"))){
	        	listMgenus00 = seListas.getListMgenusEstdPart();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Complejidad"))){
	        	listMgenus00 = seListas.getListMgenusComplejidad();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Legal"))){
	        	listMgenus00 = seListas.getListMgenusLegal();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TipoBienServi"))){
	        	listMgenus00 = seListas.getListMgenusTipoBienServi();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TarifaRetefDIAN"))){
	        	listMgenus00 = seListas.getListMgenusTarifaRetefDIAN();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Cotizacion"))){
	        	listMgenus00 = seListas.getListMgenus00MonedaCot();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_BarrioSector"))){
	        	listMgenus00 = seListas.getListMgenusBarrioSector();
	        }
        }
        
        //Condicion utilizada cuando se realiza una busqueda desde el maestro de parametros
       if(clase!=null){
	        if(clase.equalsIgnoreCase("Parametro")){
	        	listMgenus00 = seMgenus00.getListMgenus00Pal();
	        }
       }
        
		if (submittedValue.equals("")) {  
            return new Mgenus00();
        } else { 
        		if(listMgenus00!=null){
		        	for (Mgenus00 p : listMgenus00) {        		
		                if (p.getCodius().trim().equals(submittedValue)) {
		                    return p;  
		                }  
		            } 
        		}

        	if(mgenus00Disable != null && mgenus00Disable.getCodius().equals(submittedValue)){
            	return (Mgenus00)mgenus00Disable;
        	}
        } 
		
		return new Mgenus00();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Mgenus00) value).getCodius());
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

	public SeMgenus00 getSeMgenus00() {
		return seMgenus00;
	}

	public void setSeMgenus00(SeMgenus00 seMgenus00) {
		this.seMgenus00 = seMgenus00;
	}

	
	
}