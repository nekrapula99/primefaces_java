package com.tlm.faelec.web.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Controller;

import com.tlm.faelec.listas.SeListas;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mregcg00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;

@Controller
@ManagedBean
@SessionScoped
public class SeMgenus00 extends Control implements Serializable{
	private static final long serialVersionUID = 3725446386885905907L;
	
	//Variables del MBean
	@ManagedProperty(value = "#{mgenus00Service}")
	private IMgenus00Service mgenus00Service;
	
		
	private Mgenus00 mgenus00;
	private Mgenus00 mgenus00Cop;
	private Mdesgr00 mdesgr00; 
	private boolean infoGuardada;
	
	private List<Mgenus00> listMgenus00Ppal;// Lista principal de los registros de Mgenus00
	private List<Mgenus00> listMgenus00Pal; //Lista que se invoca desde el formulario de parametros
	private List<Mgenus00> filteredMgenus00;
	
	private List<Mdesgr00> filteredMdesgr00;
	private List<Mdesgr00> listMdesgr00Remove;
	private String accionMdesgr;
	private String comnusString;
	
	private Integer fracDec_CantiM; 
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	
	private String accion;
	private String titulo;
	
	@ManagedProperty("#{seListas}")
	private SeListas seListas;

	private int tabView=0;
	private InputText inputTextCodtusColumn;
	
	/*Variable utilizada para validacion al invocar el
	 * metodo obtenerListaMegenus002
	 */
	private String codtusAux;

	public String getCodtusAux() {
		return codtusAux;
	}

	public void setCodtusAux(String codtusAux) {
		this.codtusAux = codtusAux;
	}

	//	Variables Carga de lista
	private String update;
	private String nombreTabla;
	private String nombreLista;
	private Object objSeCall;
//	private String resetDTable;
	
	
	@PostConstruct
	public void init(){
		try {
			super.init("MGENUS00");
			mgenus00 = new Mgenus00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			
			Double d1=(Double)super.getSeControlFactura().PARAMETROS.get("Dec_CanMax");
			setFracDec_CantiM(d1.intValue());
			cargarDatos();
			infoGuardada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	//Metodos del managed bean
	
	public void cargarDatos(){
		Mgenus00 mgenusAux= new Mgenus00();
		mgenusAux.setMtipre00(new Mtipre00());
		listMgenus00Ppal=  mgenus00Service.listMgenus00ByCriteria(mgenusAux, getSeControlFactura().getCompaniasUsu()); 
		UtilsJSF.resetDTable("formTable:dTable");
	}

	/**
	 * Obtiene lista de Mgenus00s
	 */
	public List<Mgenus00> obtenerListaMgenus00(String codtus){
	    mgenus00 = new Mgenus00();
	    mgenus00.setMtipre00(new Mtipre00());
		mgenus00.getMtipre00().setTipore(codtus);
		mgenus00.setRegius(true);
		return mgenus00Service.listMgenus00ByCriteriaAndlistMusuco00(mgenus00, getSeControlFactura().getCompaniasUsu()); 
	}
	
	//Metodo que se ejecuta desde el formulario de parametros 
	public void obtenerListaMgenus002(){
		try {
			listMgenus00Pal = null;
			String codtus;
			String compania;
			//Lista necesria para filtrar las listas de valores por compa�ia
			List<String> listMconca00ActualizarListas;
			
			FacesContext context = FacesContext.getCurrentInstance();
			
			if(codtusAux != null){
				codtus = codtusAux;
			}else{
			    codtus = (String) UIComponent.getCurrentComponent(context).getAttributes().get("codtus");
			}
			
			compania = (String) UIComponent.getCurrentComponent(context).getAttributes().get("compania");
			if(compania != null){
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(compania);	
			}else{
				listMconca00ActualizarListas = null;
			}
			
			if(codtus != null && !codtus.equalsIgnoreCase("")){
				Mgenus00 mgenus00Aux = new Mgenus00();	
				mgenus00Aux.setMtipre00(new Mtipre00());
				mgenus00Aux.getMtipre00().setTipore(codtus);
				listMgenus00Pal =  mgenus00Service.listMgenus00ByCriteriaAndlistMusuco00(mgenus00Aux, listMconca00ActualizarListas); 
			}
			codtusAux = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void llenarObjectCall(SelectEvent event) {
		if (objSeCall == null) {
			return;
		}
		String strClassLlama = objSeCall.getClass().getSimpleName();

		// Mconca
		if (strClassLlama.equalsIgnoreCase("SeMconca00")) {
			SeMconca00 mconca00 = (SeMconca00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("ciudad")) {
					mconca00.getMconca00().setMgenus002((Mgenus00) event.getObject());			
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");

				} else if (nombreLista.equalsIgnoreCase("tident")) {
					mconca00.getMconca00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
				}else if (nombreLista.equalsIgnoreCase("pais")) {
					mconca00.getMconca00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
				}
				else if (nombreLista.equalsIgnoreCase("contribuyente")) {
					mconca00.getMconca00().setMregcg004((Mregcg00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
				}
				else if (nombreLista.equalsIgnoreCase("barrioSector")) {
					mconca00.getMconca00().setMgenus004((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
				}
				else if (nombreLista.equalsIgnoreCase("Departamento")) {
					mconca00.getMconca00().setMgenus005((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
				}
			}

		}
		if (strClassLlama.equalsIgnoreCase("seMpropr00")) {
			SeMpropr00 seMpropr00 = (SeMpropr00) objSeCall;		
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("marcaComercial")) {
					seMpropr00.getMpropr00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				} else if (nombreLista.equalsIgnoreCase("lineaProductos")) {
					seMpropr00.getMpropr00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				} else if (nombreLista.equalsIgnoreCase("subLineaProductos")) {
					seMpropr00.getMpropr00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				} else if (nombreLista.equalsIgnoreCase("unidadMedida")) {
					seMpropr00.getMpropr00().setMgenus004((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("unidadMedidaIng")) {
					seMpropr00.getMpropr00().setMgenus008((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("TipoBS")) {
					seMpropr00.getMpropr00().setMgenus009((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				} else if (nombreLista.equalsIgnoreCase("centroCostos")) {
					seMpropr00.getMpropr00().setMgenus005((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}  else if (nombreLista.equalsIgnoreCase("variantePpal")) {
					seMpropr00.getMpropr00().setMgenus006((Mgenus00) event.getObject());					
					if (!Utils.isEmptyOrNull(seMpropr00.getMpropr00().getMgenus007()) && seMpropr00.getMpropr00().getMgenus006().getIdtrus().equals(seMpropr00.getMpropr00().getMgenus007().getIdtrus())) {
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR," "+getSeControlFactura().MENSAJES.get("Varian_Dif"));
						seMpropr00.getMpropr00().setMgenus006(null);
						RequestContext.getCurrentInstance().reset("formDetalle");
						return;
					}
					seMpropr00.mostrarPestana();
					RequestContext.getCurrentInstance().reset("formDetalle");
					RequestContext.getCurrentInstance().update("formDetalle:tabView:tabVariantes");					
				} else if (nombreLista.equalsIgnoreCase("varianteSec")) {
					seMpropr00.getMpropr00().setMgenus007((Mgenus00) event.getObject());
					
					if (!Utils.isEmptyOrNull(seMpropr00.getMpropr00().getMgenus006()) && seMpropr00.getMpropr00().getMgenus007().getIdtrus().equals(seMpropr00.getMpropr00().getMgenus006().getIdtrus())) {
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR," "+getSeControlFactura().MENSAJES.get("Varian_Dif"));
						seMpropr00.getMpropr00().setMgenus007(null);
						RequestContext.getCurrentInstance().reset("formDetalle");
						return;
					}
					RequestContext.getCurrentInstance().reset("formDetalle");
					RequestContext.getCurrentInstance().update("formDetalle:tabView:tabVariantes");				
				} 
			}
		}
//		if (strClassLlama.equalsIgnoreCase("SeMdespr00")) {
//			SeMdespr00 mdespr00 = (SeMdespr00) objSeCall;
//			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
//				if (nombreLista.equalsIgnoreCase("TipoTransaccion")) {
//					mdespr00.getMdespr00().setMgenus00((Mgenus00) event.getObject());
//					RequestContext.getCurrentInstance().reset("formMdespr00Detalle");
//				}
//			}
//		}
		if (strClassLlama.equalsIgnoreCase("SeMparme00")) {
			SeMparme00 semparme00 = (SeMparme00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				     semparme00.setMgenus00((Mgenus00) event.getObject());
				     semparme00.getMparme00().setVapame(semparme00.getMgenus00().getCodius());
				     semparme00.getMparme00().setVapamh(semparme00.getMgenus00().getCodius()+" - "+semparme00.getMgenus00().getDcttus());
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
			}
		}
		if (strClassLlama.equalsIgnoreCase("SeMparca00")) {
			SeMparca00 seMparca00  = (SeMparca00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("Documento")) {
				 seMparca00.getMparca00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
					// RequestContext.getCurrentInstance().update("formDetalle:pnlMconca00");
				}//else if(nombreLista.equalsIgnoreCase("ClaseDocumento")){
//					 seMparca00.getMparca00().setMgenus002((Mgenus00) event.getObject());
//						RequestContext.getCurrentInstance().reset("formDetalle");					
//				}
			}
		}

		if (strClassLlama.equalsIgnoreCase("SeMgente00")) {
			SeMgente00 seMgente00 = (SeMgente00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("tident")) {
					seMgente00.getMgente00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}else if (nombreLista.equalsIgnoreCase("pais")) {
					seMgente00.getMgente00().setMgenus011((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("Departamento")) {
					seMgente00.getMgente00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}else if (nombreLista.equalsIgnoreCase("ciudad")) {
					seMgente00.getMgente00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("mediopago")) {
					seMgente00.getMgente00().setMgenus006((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("moneda")) {
					seMgente00.getMgente00().setMgenus007((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("Incoterms")) {
					seMgente00.getMgente00().setMgenus008((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("barrioSector")) {
					seMgente00.getMgente00().setMgenus012((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("OrganiVentas")) {
					seMgente00.getMgente00().setMgenus009((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("CanalDistri")) {
					seMgente00.getMgente00().setMgenus010((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("TipoNaturaleza")) {
					seMgente00.getMgente00().setMgenus004((Mgenus00) event.getObject());
					seMgente00.mostrarCampos();
					seMgente00.limpiarDatosConcatenados();
					
					if(seMgente00.getMgente00().getMgenus004() != null){
						if(seMgente00.getMgente00().getMgenus004().getCm1aus().equals(Utils.paramsRb.getString("mgenus_cm1aus_personaJuridica"))){
							seMgente00.getMgente00().setPrnmte(" ");
							seMgente00.getMgente00().setPrapte(" ");

						}
						else if(seMgente00.getMgente00().getMgenus004().getCm1aus().equals(Utils.paramsRb.getString("mgenus_cm1aus_personaNatural"))){
							seMgente00.getMgente00().setDctate(" ");
						}	
					}
					
					
					RequestContext.getCurrentInstance().reset("formDetalle");	
					RequestContext.getCurrentInstance().update("formDetalle:tabView:pnlDetalle");
				}
			}
		}
	
		//Maestro MPRECI01 nosorio-24102017 
		if (strClassLlama.equalsIgnoreCase("seMpreci00")) {
			SeMpreci00 seMpreci00 = (SeMpreci00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("Moneda")) {
					seMpreci00.getMpreci00().setMgenus00((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
			}
		}
		//Maestro MREGCG00 nosorio-30102017 
		if (strClassLlama.equalsIgnoreCase("seMregcg00")) {
			SeMregcg00 seMregcg00 = (SeMregcg00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("contribuyente")) {
					seMregcg00.getMregcg00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("regimen")) {
					seMregcg00.getMregcg00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
			}
		}
		
		//Maestro MCOTES00 nosorio-12122017 
		if (strClassLlama.equalsIgnoreCase("seMcotes00")) {
			SeMcotes00 seMcotes00 = (SeMcotes00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("saludo")) {
					seMcotes00.getMcotes00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
				else if (nombreLista.equalsIgnoreCase("areaLabor")) {
					seMcotes00.getMcotes00().setMgenus005((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("cargoTercero")) {
					seMcotes00.getMcotes00().setMgenus006((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("tident")) {
					seMcotes00.getMcotes00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("ciudad")) {
					seMcotes00.getMcotes00().setMgenus004((Mgenus00) event.getObject());			
					RequestContext.getCurrentInstance().reset("formDetalle");

				}else if (nombreLista.equalsIgnoreCase("pais")) {
					seMcotes00.getMcotes00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
				
			}
		}
		//Maestro MCOTOS00 nosorio-13122017 
		if (strClassLlama.equalsIgnoreCase("seMcotos00")) {
			SeMcotos00 seMcotos00 = (SeMcotos00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("areaLabor")) {
					seMcotos00.getMcotos00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("cargoTercero")) {
					seMcotos00.getMcotos00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				
			}
		}
		
		//Maestro MFUNFU00 nosorio-12122017 
		if (strClassLlama.equalsIgnoreCase("seMfunfu00")) {
			SeMfunfu00 seMfunfu00 = (SeMfunfu00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("saludo")) {
					seMfunfu00.getMfunfu00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("areaLabor")) {
					seMfunfu00.getMfunfu00().setMgenus005((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("cargoTercero")) {
					seMfunfu00.getMfunfu00().setMgenus006((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("tident")) {
					seMfunfu00.getMfunfu00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}else if (nombreLista.equalsIgnoreCase("ciudad")) {
					seMfunfu00.getMfunfu00().setMgenus004((Mgenus00) event.getObject());			
					RequestContext.getCurrentInstance().reset("formDetalle");

				}else if (nombreLista.equalsIgnoreCase("pais")) {
					seMfunfu00.getMfunfu00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");
				}
			}
		}		
		
		//Maestro Tqigqg00 nosorio-12122017 
		if (strClassLlama.equalsIgnoreCase("seTqigqg00")) {
			SeTqigqg00 seTqigqg00 = (SeTqigqg00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("moneda")) {
					seTqigqg00.getTqigqg00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("OrganiVentas")) {
					seTqigqg00.getTqigqg00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("CanalDistri")) {
					seTqigqg00.getTqigqg00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
			
			}
		}
		//Maestro Tqdrqr00 nosorio-22122018 
		if (strClassLlama.equalsIgnoreCase("seTqdrqr00")) {
			SeTqdrqr00 seTqdrqr00 = (SeTqdrqr00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("Estd_Part")) {
					seTqdrqr00.getTqdrqr00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formTqdrqr00Detalle");	
				}
				else if (nombreLista.equalsIgnoreCase("Complejidad")) {
					seTqdrqr00.getTqdrqr00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formTqdrqr00Detalle");	
				}
				else if (nombreLista.equalsIgnoreCase("Legal")) {
					seTqdrqr00.getTqdrqr00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formTqdrqr00Detalle");	
				}
				else if (nombreLista.equalsIgnoreCase("UniMedida")) {
					seTqdrqr00.getTqdpqp00().setMgenus00((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				
			
			}
		}
		//Maestro Tqdrqr00 nosorio-22122018 
		if (strClassLlama.equalsIgnoreCase("seMtaric00")) {
			SeMtaric00 seMtaric00 = (SeMtaric00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("TipoBS")) {
					seMtaric00.getMtaric00().setMgenus001((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("TarifaDIAN")) {
					seMtaric00.getMtaric00().setMgenus002((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
				else if (nombreLista.equalsIgnoreCase("Ciudad")) {
					seMtaric00.getMtaric00().setMgenus003((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
			
			}
		}
		
		//DESCOMENTAR
		//Maestro Tqdpqp00 nosorio-01022018 
		if (strClassLlama.equalsIgnoreCase("seTqdpqp00")) {
			SeTqdpqp00 seTqdpqp00 = (SeTqdpqp00) objSeCall;
			if (nombreTabla.equalsIgnoreCase("mgenus00")) {
				if (nombreLista.equalsIgnoreCase("UniMedida")) {
					seTqdpqp00.getTqdpqp00().setMgenus00((Mgenus00) event.getObject());
					RequestContext.getCurrentInstance().reset("formDetalle");	
				}
			}
		}
		
		//Maestro seTqdfqf00 nosorio-02122018 
				if (strClassLlama.equalsIgnoreCase("seTqdfqf00")) {
					SeTqdfqf00 seTqdfqf00 = (SeTqdfqf00) objSeCall;
					if (nombreTabla.equalsIgnoreCase("mgenus00")) {
						if (nombreLista.equalsIgnoreCase("mediopago")) {
							seTqdfqf00.getTqdfqf00().setMgenus001((Mgenus00) event.getObject());
							RequestContext.getCurrentInstance().reset("formTqdfqf00Detalle");	
						}
						else if (nombreLista.equalsIgnoreCase("moneda")) {
							seTqdfqf00.getTqdfqf00().setMgenus002((Mgenus00) event.getObject());
							RequestContext.getCurrentInstance().reset("formTqdfqf00Detalle");	
						}
						
					
					}
				}


		update=null;
	}
	
	private boolean validarListas(){
		boolean validacion = false;
		//Se valida que las listas que tiene el usuario en el formulario esten activas 
		
		if(mgenus00.getMtipre00() != null && mgenus00.getMtipre00().getRegitr() != false && mgenus00.getMtipre00().getRegitr() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("codtus")+" "+mgenus00.getMtipre00().getTipore()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}
		if(mgenus00.getMconca00() != null && mgenus00.getMconca00().getRegcia() == false){
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, 
			getIdiomasHm().get("idcmus")+" "+mgenus00.getMconca00().getCodcia()+" "+getSeControlFactura().MENSAJES.get("Cam_Deshab"));
			validacion = true;
		}

		return validacion;
	}
	
	private boolean validarDatosRequeridos(){
		boolean validacion = false;
		//Codigo producto
		if(Utils.isEmptyOrNull(mgenus00.getMtipre00()) && permisoCampos.get("codtus").getReqcam().equals("S"))
		{
			UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("codtus")+" "+getSeControlFactura().MENSAJES.get("Cam_Obliga"));
			inputTextCodtusColumn.setStyle("border-color: #d2524f;");				
		    RequestContext.getCurrentInstance().update(inputTextCodtusColumn.getClientId());
			validacion = true;
		}else{
			inputTextCodtusColumn.setStyle("border-color: #9a9a9a;");			 
			RequestContext.getCurrentInstance().update(inputTextCodtusColumn.getClientId());	
		}
		
		return validacion;
	}

	
	public void save() {
		try {

			colaEstandar();
			
			if (validarDatosRequeridos()) {
				return;
			}
			//Modificar el Registro para todos los detalles de Mgenus00		
			
//			if(comnusString!=null && !comnusString.isEmpty())
//			{
//			 mgenus00.setComnus(new BigDecimal(comnusString));	
//			}
			if(!mgenus00.getRegius())
			{
			  for(Mdesgr00 mdesgr : mgenus00.getMdesgr00s())
			  {
				mdesgr.setRegids(mgenus00.getRegius());  
			  }
			}			
			mgenus00.setCompus(mgenus00.getMconca00()==null?null:mgenus00.getMconca00().getCodcia());
			if(getAccion().equals(Constantes.ACCION_MODIFICAR))
			{
				if(validarListas()==false){
				mgenus00Service.save(mgenus00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mgenus00.toString(),mgenus00.toStringId(),mgenus00Cop.toString()):null);
				}else return;
			}
			else
			{
				mgenus00Service.save(mgenus00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(null,mgenus00.toString(),mgenus00.toStringId(),null):null);
			}
			cargarDatos();
			infoGuardada = true;
			seListas.actualizarListas(validarNombreLista(mgenus00.getMtipre00().getTipore()));
			UtilsJSF.closeDialog("dlgNuevoUpdate");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formTable");
		} catch (Exception e) {
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),"UNIQUE_KEY");
				return;
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
				return;
			}
		}
	}

	private void colaEstandar() {
		try {				
			mgenus00.setUserus(getSeControlFactura().codusu);
			mgenus00.setPrgmus(getSeControlFactura().aplusu);
			mgenus00.setFeacus(new Date());
			mgenus00.setMaquus(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void preRenderView(){
		try {
			getSeCabecera().setBotonNuevo(true);
			getSeCabecera().setBotonExcel(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void evtCloseDialogMgenus(CloseEvent event) {
        try {   
        	update=null;
        	
        	if (!Utils.isEmptyOrNull(getAccion()) && getAccion().equals(Constantes.ACCION_MODIFICAR) && !infoGuardada){
            	
        		mgenus00.setMconca00(mgenus00Cop.getMconca00());
            	mgenus00.setMtipre00(mgenus00Cop.getMtipre00());
            	mgenus00.setCmdtus(mgenus00Cop.getCmdtus());
            	mgenus00.setCm1dus(mgenus00Cop.getCm1dus());
            	}
        	evtCloseDialog(event);
        }catch(Exception e) {
        	e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
        }
     }
	
	//Se reinician los valores por defecto de las listas de valores
	private void reiniciarListasMaestro(){
		try {
			getSeListas().actualizarListas(NombresListas.listMconca);
			getSeListas().actualizarListas(NombresListas.listMtipre);
			getSeListas().actualizarListas(NombresListas.listMidiom);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setAccion(Constantes.ACCION_NUEVO);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mgenus00 = new Mgenus00();
			mgenus00.setRegius(true);
			mgenus00.setMdesgr00s(new ArrayList<Mdesgr00>());
			setMdesgr00(null);
			seListas.actualizarListas(NombresListas.listMconca);

			reiniciarListasMaestro();			
			tabView=0;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	public void accionModificar(SelectEvent event) {
		try {
			setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setAccion(Constantes.ACCION_MODIFICAR);
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			
			//Se actualizan las listas que contiene el maestro para que muestren los datos segun la compa�ia escogida
			if(mgenus00.getMconca00()!=null){
				actualizarListasCompania(mgenus00.getMconca00().getCodcia());
			}else{
				reiniciarListasMaestro();
			}
			
			if(mgenus00.getComnus()!=null)
			{
			 comnusString=String.valueOf(mgenus00.getComnus().doubleValue());
			}
			Mgenus00 mgenus00Rs = new Mgenus00();
			mgenus00Rs = mgenus00Service.cargarDetalles(mgenus00.getIdtrus());
			mgenus00.setMdesgr00s(mgenus00Rs.getMdesgr00s());
			mgenus00Cop= (Mgenus00) mgenus00.clone();
			//seListas.actualizarListas(NombresListas.listMconca);

			//Se valida si las listas del formulario tienen campos deshabilitados
			if(validarListas()==true){
				//Se ejecuta el metodo para que no se pierda el objeto de las listas al momento de hacer un submit en el guardar
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgenus00.getMconca00(), null);
				UtilsJSF.tratamientoObjetoDeshabilitadoListas(mgenus00.getMtipre00(), null);
			}
			tabView=0;
			infoGuardada = false;
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	// metedo que captura el objeto al modificar y actualiza las listas de compa�ias 
	private void actualizarListasCompania (String codigoCompania){
		try{
			List<String> listMconca00ActualizarListas;
			listMconca00ActualizarListas = new ArrayList<String>();
			listMconca00ActualizarListas.add(codigoCompania);
			getSeListas().actualizarListasCompania(NombresListas.listMconca, listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMtipre , listMconca00ActualizarListas);
			getSeListas().actualizarListasCompania(NombresListas.listMidiom, listMconca00ActualizarListas);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void autocompleteActualizarListas (){
		try{
			String codigoCompania = null;
			if(mgenus00.getMconca00() != null){
				codigoCompania= mgenus00.getMconca00().getCodcia();
				actualizarListasCompania(codigoCompania);
			}else{
				reiniciarListasMaestro();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mgenus00 = (Mgenus00) event.getComponent().getAttributes() .get("mgenus00");
			mgenus00Service.removeMgenus00(mgenus00,getSeControlFactura().PARAMETROS.get("Reg_Audito").equals("S")?getSeControlFactura().crearAuditoria(Constantes.AUDIT_ELIMINAR, mgenus00.toString(),mgenus00.toStringId()):null);
			cargarDatos();
			seListas.actualizarListas(validarNombreLista(mgenus00.getMtipre00().getTipore()));
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
						FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	public void accionNuevoMdesgr() {
		try {
			accionMdesgr = Constantes.ACCION_NUEVO;
			mdesgr00 = new Mdesgr00();
			mdesgr00.setRegids(true);
			mdesgr00.setMconca00(getMgenus00().getMconca00());
			mdesgr00.setMgenus00(getMgenus00());
//			setMdesgr00(mdesgr00);	
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),
					FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void saveMdesgr(ActionEvent event) {
		try {	
			if(Constantes.ACCION_NUEVO.equals(accionMdesgr)){		
				mdesgr00.setUserds(getSeControlFactura().codusu);
				mdesgr00.setPrgmds("Descripcion Idiomas " + getClass().getName());
				mdesgr00.setFeacds(new Date());
				mdesgr00.setMaquds(getSeControlFactura().ip);
				mgenus00.getMdesgr00s().add(mdesgr00);
				UtilsJSF.resetDTable(":formDetalle:tabView:dTableMdesgr00");
				UtilsJSF.closeDialog("dlgNuevoUpdateMdesgr00");
			}
			else 	
			 {
				UtilsJSF.resetDTable(":formDetalle:tabView:dTableMdesgr00");
				UtilsJSF.closeDialog("dlgNuevoUpdateMdesgr00");
			 }
				
		} catch (Exception e) {
//			cargarDatos();
			if (UtilsJSF.isDuplicateException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "UNIQUE_KEY");
				return;
			} else { 	
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
				return;
			}
		}	
	}
	
	public void removeMdesgr(ActionEvent event) {
		try {
			mdesgr00 = (Mdesgr00) event.getComponent().getAttributes().get("mdesgr00");
			mgenus00.getMdesgr00s().remove(mdesgr00);
			UtilsJSF.resetDTable(":formDetalle:tabView:dTableMdesgr00");
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			if (UtilsJSF.isReferenceConstraintException(e)) {
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), "REFERENCE");
			} else {
				e.printStackTrace();
				UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
			}
		}
	}
	public void accionModificarMdesgr(SelectEvent event){ 
		try {
			accionMdesgr=Constantes.ACCION_MODIFICAR;//Modificar registro				
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_ERROR);
		}
	}
	

	//Metodo que se encarga de autocompletar mientras se esta escribiendo en las listas de Mgenus00
	public List<Mgenus00> completeMgenus(String query) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		String codtus = (String) UIComponent.getCurrentComponent(context).getAttributes().get("codtus");
		
		//Esta variable se utiliza cuando se busca desde el maestro de parametros
		String clase = (String) UIComponent.getCurrentComponent(context).getAttributes().get("clase");
			if(clase!=null){
				 if(clase.equalsIgnoreCase("Parametro")){
					 codtus=null;
				 }
			}
		
        List<Mgenus00> results = new ArrayList<Mgenus00>();
        List<Mgenus00> listMgenus00 = new ArrayList<Mgenus00>();
        if(codtus != null){
        	
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
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Region"))){
	        	listMgenus00 = seListas.getListMgenusRegion();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Departamento"))){
	        	listMgenus00 = seListas.getListMgenusDepartamento();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Sector"))){
	        	listMgenus00 = seListas.getListMgenusSector();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoAsesora"))){
	        	listMgenus00 = seListas.getListMgenusTipoAsesora();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoNaturaleza"))){
	        	listMgenus00 = seListas.getListMgenusTipoNaturaleza();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_segmentoAsesora"))){
	        	listMgenus00 = seListas.getListMgenusSegmentoAsesora();
	        } else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_actividad"))){
	        	listMgenus00 = seListas.getListMgenusActividad();
	        }  else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_grupoActividad"))){
	        	listMgenus00 = seListas.getListMgenusGrupoActividad();
	        } else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_cicloAsesora"))){
	        	listMgenus00 = seListas.getListMgenusCicloAsesora();
	        } else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoCPC"))){
	        	listMgenus00 = seListas.getListMgenusTipoCPC();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_modalidadCPC"))){
	        	listMgenus00 = seListas.getListMgenusModalidadCPC();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_CatalogoPara"))){
	        	listMgenus00 = seListas.getListMgenus00CatalogoPara();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_variantes"))){
	        	listMgenus00 = seListas.getListMgenus00Variantes();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_evento"))){
	        	listMgenus00 = seListas.getListMgenus00Evento();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipo_regimen"))){
	        	listMgenus00 = seListas.getListMgenusRegimen();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_saludo"))){
	        	listMgenus00 = seListas.getListMgenusSaludo();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_areaLabor"))){
	        	listMgenus00 = seListas.getListMgenusAreaLabor();
	        }else if (codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_cargoTercero"))){
	        	listMgenus00 = seListas.getListMgenusCargoTercero();
	        } else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"))){
	        	listMgenus00 = seListas.getListMgenusUnidadMedidaIng();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TipoTransaccion"))){
	        	listMgenus00 = seListas.getListMgenusTipoTransaccion();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_EstdPart"))){
	        	listMgenus00 = seListas.getListMgenusEstdPart();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Complejidad"))){
	        	listMgenus00 = seListas.getListMgenusComplejidad();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Legal"))){
	        	listMgenus00 = seListas.getListMgenusLegal();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TipoBienServi"))){
	        	listMgenus00 = seListas.getListMgenusTipoBienServi();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TarifaRetefDIAN"))){
	        	listMgenus00 = seListas.getListMgenusTarifaRetefDIAN();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_mediopago"))){
	        	listMgenus00 = seListas.getListMgenusMedioPago();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_monedas"))){
	        	listMgenus00 = seListas.getListMgenusMoneda();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipo_contribuyente"))){
	        	listMgenus00 = seListas.getListMgenusContribuyente();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Incoterm"))){
	        	listMgenus00 = seListas.getListMgenusIncoterms();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_OrganiVentas"))){
	        	listMgenus00 = seListas.getListMgenusOrganiVentas();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_CanalDistri"))){
	        	listMgenus00 = seListas.getListMgenusCanalDistri();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Cotizacion"))){
	        	listMgenus00 = seListas.getListMgenus00MonedaCot();
	        }else if(codtus.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_BarrioSector"))){
	        	listMgenus00 = seListas.getListMgenusBarrioSector();
	        }
        }
        if(UtilsJSF.selectOneMenu_permiteNull()){
        	Mgenus00 vacia = new Mgenus00();
        	vacia.setCodius(Constantes.rb.getString("select_sin_vacio"));
        	results.add(vacia);//Mgenus Null
        }
        
      //Condicion utilizada cuando se realiza una busqueda desde el maestro de parametros
        if(clase!=null){
	        if(clase.equalsIgnoreCase("Parametro")){
	        	listMgenus00 = listMgenus00Pal;
	        }
        }

        for (Mgenus00 obj : listMgenus00) {
        	
//        	if(obj.getMtipre00().getTipore().equalsIgnoreCase(codtus)){
        		if(obj.getCodius().toLowerCase().contains(query.toLowerCase())){
            		results.add(obj);        		
        		}
//        	}		
		}
        return results;
    }
	
	//Este metodo valida el nombre de la lista que se va a actualizar guardar o eliminar
	private NombresListas validarNombreLista(String tipoRegistro){
		NombresListas nombre = null;
		if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_pais"))){
			nombre=NombresListas.listMgenusPaises;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_ciudades"))){
			nombre=NombresListas.listMgenusCiudad;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoIdentificacion"))){
			nombre=NombresListas.listMgenusTident;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_marcaComercial"))){
			nombre=NombresListas.listMgenusMarcaComercial;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_lineaProductos"))){
			nombre=NombresListas.listMgenusLineaProductos;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_sublineaProductos"))){
			nombre=NombresListas.listMgenusSubLineaProductos;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"))){
			nombre=NombresListas.listMgenusUnidadMedida;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_centroCostos"))){
			nombre=NombresListas.listMgenusCentroCostos;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_ClaseDocumentos"))){
			nombre=NombresListas.listMgenusClaseDocumentos;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_documento"))){
			nombre=NombresListas.listMgenusDocumentos;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_marcaCatalogo"))){
			nombre=NombresListas.listMgenusMarcaCatalogo;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Region"))){
			nombre=NombresListas.listMgenusRegion;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Departamento"))){
			nombre=NombresListas.listMgenusDepartamento;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Sector"))){
			nombre=NombresListas.listMgenusSector;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoAsesora"))){
			nombre=NombresListas.listMgenusTipoAsesora;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoNaturaleza"))){
			nombre=NombresListas.listMgenusTipoNaturaleza;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_actividad"))){
			nombre=NombresListas.listMgenusActividad;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_grupoActividad"))){
			nombre=NombresListas.listMgenusGrupoActividad;
		}else if(tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_cicloAsesora"))){
			nombre=NombresListas.listMgenusCicloAsesora;
		}else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipoCPC"))){
			nombre=NombresListas.listMgenusTipoCPC;
		}else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_modalidadCPC"))){
			nombre=NombresListas.listMgenusModalidadCPC;
		}else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_CatalogoPara"))){
        	nombre = NombresListas.listMgenus00CatalogoPara;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_variantes"))){
        	nombre = NombresListas.listMgenus00Variantes;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_tipo_regimen"))){
        	nombre = NombresListas.listMgenusRegimen;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_saludo"))){
        	nombre = NombresListas.listMgenusSaludo;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_areaLabor"))){
        	nombre = NombresListas.listMgenusAreaLabor;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_cargoTercero"))){
        	nombre = NombresListas.listMgenusCargoTercero;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"))){
        	nombre = NombresListas.listMgenusUnidadMedidaIng;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TipoTransaccion"))){
        	nombre = NombresListas.listMgenusTipoTransaccion;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_EstdPart"))){
        	nombre = NombresListas.listMgenusEstdPart;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Complejidad"))){
        	nombre = NombresListas.listMgenusComplejidad;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_Legal"))){
        	nombre = NombresListas.listMgenusLegal;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TipoBienServi"))){
        	nombre = NombresListas.listMgenusTipoBienServi;
        }else if (tipoRegistro.equalsIgnoreCase(Utils.paramsRb.getString("mgenus_codtus_TarifaRetefDIAN"))){
        	nombre = NombresListas.listMgenusTarifaRetefDIAN;
        }	
	   return nombre;
	}
	
	public void onTabChange(TabChangeEvent event)
	{  
	 RequestContext.getCurrentInstance().update("formDetalle:tabView:codtusColumnDesc");	
	 RequestContext.getCurrentInstance().update("formDetalle:tabView:tiporegistro");	
	}
	
	//Metodos de acceso
	public Mgenus00 getMgenus00() {
		return mgenus00;
	}
	
	public void setMgenus00(Mgenus00 mgenus00) {
		this.mgenus00 = mgenus00;
	}
	
	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}

	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
	}
	
	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public Object getObjSeCall() {
		return objSeCall;
	}

	public void setObjSeCall(Object objSeCall) {
		this.objSeCall = objSeCall;
	}

	public List<Mgenus00> getListMgenus00Ppal() {
		return listMgenus00Ppal;
	}

	public void setListMgenus00Ppal(List<Mgenus00> listMgenus00Ppal) {
		this.listMgenus00Ppal = listMgenus00Ppal;
	}

	
	public Mgenus00 getMgenus00Cop() {
		return mgenus00Cop;
	}

	public void setMgenus00Cop(Mgenus00 mgenus00Cop) {
		this.mgenus00Cop = mgenus00Cop;
	}

	public List<Mgenus00> getFilteredMgenus00() {
		return filteredMgenus00;
	}

	public void setFilteredMgenus00(List<Mgenus00> filteredMgenus00) {
		this.filteredMgenus00 = filteredMgenus00;
	}

	public List<Mgenus00> getListMgenus00Pal() {
		return listMgenus00Pal;
	}

	public void setListMgenus00Pal(List<Mgenus00> listMgenus00Pal) {
		this.listMgenus00Pal = listMgenus00Pal;
	}
	
	public List<Mdesgr00> getFilteredMdesgr00() {
		return filteredMdesgr00;
	}

	public void setFilteredMdesgr00(List<Mdesgr00> filteredMdesgr00) {
		this.filteredMdesgr00 = filteredMdesgr00;
	}

	public List<Mdesgr00> getListMdesgr00Remove() {
		return listMdesgr00Remove;
	}

	public void setListMdesgr00Remove(List<Mdesgr00> listMdesgr00Remove) {
		this.listMdesgr00Remove = listMdesgr00Remove;
	}

	public Mdesgr00 getMdesgr00() {
		if(mdesgr00==null)
		{
		  mdesgr00= new Mdesgr00();
		}
		return mdesgr00;
	}

	public void setMdesgr00(Mdesgr00 mdesgr00) {
		this.mdesgr00 = mdesgr00;
	}

	public String getAccionMdesgr() {
		return accionMdesgr;
	}

	public void setAccionMdesgr(String accionMdesgr) {
		this.accionMdesgr = accionMdesgr;
	}

	public String getComnusString() {
		return comnusString;
	}

	public void setComnusString(String comnusString) {
		this.comnusString = comnusString;
	}

	public int getTabView() {
		return tabView;
	}

	public void setTabView(int tabView) {
		this.tabView = tabView;
	}

	public Map<String, String> getIdiomasHm() {
		return idiomasHm;
	}

	public void setIdiomasHm(Map<String, String> idiomasHm) {
		this.idiomasHm = idiomasHm;
	}

	public Map<String, String> getIdiomasAyuHm() {
		return idiomasAyuHm;
	}

	public void setIdiomasAyuHm(Map<String, String> idiomasAyuHm) {
		this.idiomasAyuHm = idiomasAyuHm;
	}

	public Map<String, String> getPermisos() {
		return permisos;
	}

	public void setPermisos(Map<String, String> permisos) {
		this.permisos = permisos;
	}

	public HashMap<String, String> getIdiomasTituloHm() {
		return idiomasTituloHm;
	}

	public void setIdiomasTituloHm(HashMap<String, String> idiomasTituloHm) {
		this.idiomasTituloHm = idiomasTituloHm;
	}

	public HashMap<String, Mcampo00> getPermisoCampos() {
		return permisoCampos;
	}

	public void setPermisoCampos(HashMap<String, Mcampo00> permisoCampos) {
		this.permisoCampos = permisoCampos;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public SeListas getSeListas() {
		return seListas;
	}

	public void setSeListas(SeListas seListas) {
		this.seListas = seListas;
	}

	public InputText getInputTextCodtusColumn() {
		return inputTextCodtusColumn;
	}

	public void setInputTextCodtusColumn(InputText inputTextCodtusColumn) {
		this.inputTextCodtusColumn = inputTextCodtusColumn;
	}

	public Integer getFracDec_CantiM() {
		return fracDec_CantiM;
	}

	public void setFracDec_CantiM(Integer fracDec_CantiM) {
		this.fracDec_CantiM = fracDec_CantiM;
	}

	
}