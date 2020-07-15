package com.tlm.faelec.web.controller;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import org.springframework.stereotype.Controller;

//import com.ibm.jsse2.m;
import com.tlm.faelec.utils.Constantes;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.utils.UtilsJSF;
import com.tlm.faelecEntities.model.entities.Mcampo00;
import com.tlm.faelecEntities.model.entities.Mcotos00;
import com.tlm.faelecEntities.model.entities.Mdevpr00;
//import com.tlm.faelecEntities.model.entities.Mgentc00;
import com.tlm.faelecEntities.model.entities.Mvarpr00;

@Controller
@ManagedBean
@SessionScoped
public class SeMdevpr00 extends Control implements Serializable{
	private static final long serialVersionUID = 1L;
	

	//Variables del Managedbean
	@ManagedProperty(value = "#{seMvarpr00}")
	//private SeMvarpr00 seMvarpr00;
	private Mvarpr00 mvarpr00;
	private Mdevpr00 mdevpr00;
//	private Mdevpr00 mdevpr00Clone;
	private List<Mdevpr00> filteredMdevpr00;
	private UploadedFile logoCompaFile;
	private String imagen;
	private String valorFiltroRegistroActivo; 
	private boolean renderedPnlMdevpr00;	
	private boolean changeImageSub=false;
	private StreamedContent imageStremSubT;
	
	//Imagen
	private UploadedFile imagenFotoSub;
	private StreamedContent imageStremSub;
	private String imagenAnterior;
//	private boolean infoGuardada;
	
	//Metodos del ManagedBean
	
	private Map<String, String> idiomasHm;
	private Map<String, String> idiomasAyuHm;
	private Map<String, String> permisos;
	private HashMap<String, String> idiomasTituloHm;
	public  HashMap<String, Mcampo00> permisoCampos;
	private String tabla;
	private String accion;
	private String titulo;
	
	@PostConstruct
	public void init() {
		try {
			super.init("MDEVPR00");
			mdevpr00 = new Mdevpr00();
			idiomasHm= super.getIdiomasHm();
			idiomasAyuHm= super.getIdiomasAyuHm();
			idiomasTituloHm = super.getIdiomasTituloHm();
			permisos = super.getPermisos();
			permisoCampos= super.getPermisoCampos();
			tabla=super.getTabla();
			accion=super.getSeControlFactura().getAccion();
			titulo=super.getSeControlFactura().getTitulo();
			//cargarDatos();
			//reiniciarListasMaestro();
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void agregarDetalles(){
		renderedPnlMdevpr00 = true;
	}
	

	public void accionNuevo() {
		try {
			setAccion(Constantes.ACCION_NUEVO);
			
			
			mdevpr00 = new Mdevpr00();
			//mdevpr00.setMvarpr00(seMvarpr00.getMvarpr00());
			mdevpr00.setRegidv(true);
			colaEstandar();			
			//seMvarpr00.getMvarpr00().getMdevpr00s().add(mdevpr00);
			UtilsJSF.resetDTable("formMvarpr00:dTableMdevpr00");
		} catch (Exception e) {
			e.printStackTrace();
			UtilsJSF.facesLog(FacesContext.getCurrentInstance(),FacesMessage.SEVERITY_ERROR);
		}
	}
	

	private void colaEstandar() {
		try {
			mdevpr00.setUserdv(getSeControlFactura().codusu);
			mdevpr00.setPrgmdv("Productos " + getClass().getName());
			mdevpr00.setFeacdv(new Date());
			mdevpr00.setMaqudv(getSeControlFactura().ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Limpia el filtro del DataTable
	public void clearAllFilters() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formMvarpr00:dTableMdevpr00");
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("formMvarpr00:dTableMdevpr00");
		}
	}
	
	//metodo que valida la Unique Key en la lista Mdevpr00
	 public boolean validarUKMdevpr00() throws Exception{
		  try {   
		  /* if(!Utils.isEmptyOrNull(seMvarpr00.getMvarpr00().getMdevpr00s())){
		    List<Mdevpr00> listTemp = new ArrayList<Mdevpr00>(seMvarpr00.getMvarpr00().getMdevpr00s());
		    listTemp.remove(mdevpr00);
		    int i=0;
		    for (Mdevpr00 objIter : listTemp) {
		    	i = i + 1;
		    	if(mdevpr00 != null && seMvarpr00.getMvarpr00().getMdevpr00s().size() > i){
			    	if(objIter.getDscrdv().equalsIgnoreCase(mdevpr00.getDscrdv()) && objIter.getMvaria00().equals(mdevpr00.getMvaria00())){
						UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("dscrdv") +": "+mdevpr00.getDscrdv()+" "+getSeControlFactura().getRb().getString("mj_unique_key"));
//						RequestContext.getCurrentInstance().update("formMvarpr00");
					    return false;
			        }
		       }
		    }
		   }*/
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  return true;
		 }
	
//	public boolean validarUKMdevpr00(){	
//		boolean validar = true;
//		if(mdevpr00 != null && seMvarpr00.getMvarpr00().getMdevpr00s().size() > 1){
//			for(Mdevpr00 obj : seMvarpr00.getMvarpr00().getMdevpr00s()){
//				if(obj.getDscrdv().equalsIgnoreCase(mdevpr00.getDscrdv())){
//					UtilsJSF.facesLog(FacesMessage.SEVERITY_ERROR, getIdiomasHm().get("dscrdv") +": "+mdevpr00.getDscrdv()+" "+getRb().getString("mj_unique_key"));
//					RequestContext.getCurrentInstance().update("formMvarpr00:pnlDetalleMdevpr00");
//					 return validar=false;
//				}
//			}
//		}
//		return validar;
//	}
	
	 public void insertDscrdv(){
		 try{
			 mdevpr00.setDscrdv(mdevpr00.getMvaria00().getDscrvt());
			 RequestContext.getCurrentInstance().update("formMvarpr00:pnlMdevpr00");
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	
	public void remove(ActionEvent event) {
		try {
			getSeControlFactura().setTitulo(titulo);
			getSeControlFactura().setTabla(tabla);
			mdevpr00 = (Mdevpr00) event.getComponent().getAttributes().get("mdevpr00");
			//seMvarpr00.getMvarpr00().getMdevpr00s().remove(mdevpr00);
			UtilsJSF.resetDTable("formMvarpr00:dTableMdevpr00");
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
	
	public void guardarImagen()
	{
		String urlFolder = (String) getSeControlFactura().PARAMETROS.get("RutaImgPro");
		File folder = new File((String)getSeControlFactura().PARAMETROS.get("RutaImgPro")+File.separator+mdevpr00.getMvarpr00().getMpropr00().getIdcapr());
		try
		{
			if(imagenFotoSub!=null)
			{
				if (Utils.directorioValido(urlFolder)) {
	
					String nombreAdj = crearNombreAdjunto();
					if(!folder.exists())
					{
						folder.mkdir();
					}
	
					if (Utils.copiarArchivo(nombreAdj, imagenFotoSub
							.getInputstream(), urlFolder + File.separator + mdevpr00.getMvarpr00().getMpropr00().getIdcapr()
							+ File.separator)) 
					{
						mdevpr00.setImafdv(urlFolder + File.separator + mdevpr00.getMvarpr00().getMpropr00().getIdcapr()
								+ File.separator + nombreAdj);
					}
				
				UtilsJSF.closeDialog("dlgImagenFotoMdevpr");	
			    FacesContext.getCurrentInstance().addMessage("formMvarpr00:msgMvarpr00", new FacesMessage(FacesMessage.SEVERITY_INFO,(String)getSeControlFactura().MENSAJES.get("Guard_Foto"),(String)getSeControlFactura().MENSAJES.get("Guard_Foto")));
			    RequestContext.getCurrentInstance().update("formMvarpr00:pnlMsj");
				
				
				
			  }
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	 public String crearNombreAdjunto() 
	   {

	      String strNombre = new String();
		  try 
		  {
				strNombre = mdevpr00.getMvarpr00().getMpropr00().getIdcapr() + "_" 
				+ Utils.obtenerNomAdjunto(imagenFotoSub.getFileName());
		  } 
		  catch (Exception e) {
			e.printStackTrace();
		  }
	        return strNombre;
	  }		
	 
	 public void guardarImagenFoto()
	 {
		 if(imagenAnterior!=null)
		 {
			 File folder = new File(imagenAnterior);
			 if(folder.exists())
			 {
				 folder.delete();
			 }
		 }
		 guardarImagen();
	 }
	
	public void uploadImagenFoto(FileUploadEvent event) {
		try {
			imagenAnterior= mdevpr00.getImafdv();
			logoCompaFile = event.getFile();		
		    imagenFotoSub= event.getFile();
		    mdevpr00.setImafdv("");
		    changeImageSub=true;		 
		    imageStremSub = new DefaultStreamedContent(event.getFile().getInputstream());
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
	public void removeImagenFoto() {
		try {			
			mdevpr00.setImafdv(null);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Metodos de acceso
	
	public Mvarpr00 getMvarpr00() {
		return mvarpr00;
	}
	public void setMvarpr00(Mvarpr00 mvarpr00) {
		this.mvarpr00 = mvarpr00;
	}

	public List<Mdevpr00> getFilteredMdevpr00() {
		return filteredMdevpr00;
	}
	public void setFilteredMdevpr00(List<Mdevpr00> filteredMdevpr00) {
		this.filteredMdevpr00 = filteredMdevpr00;
	}
	public Mdevpr00 getMdevpr00() {
		return mdevpr00;
	}
	public void setMdevpr00(Mdevpr00 mdevpr00) {
		this.mdevpr00 = mdevpr00;
	}
	public UploadedFile getLogoCompaFile() {
		return logoCompaFile;
	}
	public void setLogoCompaFile(UploadedFile logoCompaFile) {
		this.logoCompaFile = logoCompaFile;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getValorFiltroRegistroActivo() {
		return valorFiltroRegistroActivo;
	}
	public void setValorFiltroRegistroActivo(String valorFiltroRegistroActivo) {
		this.valorFiltroRegistroActivo = valorFiltroRegistroActivo;
	}

	public boolean getRenderedPnlMdevpr00() {
		return renderedPnlMdevpr00;
	}

	public void setRenderedPnlMdevpr00(boolean renderedPnlMdevpr00) {
		this.renderedPnlMdevpr00 = renderedPnlMdevpr00;
	}

	
	public UploadedFile getImagenFotoSub() {
		return imagenFotoSub;
	}

	public void setImagenFotoSub(UploadedFile imagenFotoSub) {
		this.imagenFotoSub = imagenFotoSub;
	}

	public StreamedContent getImageStremSub() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				return new DefaultStreamedContent();
			} else {
				if (!changeImageSub) {
					imageStremSub = UtilsJSF.convertStrinfileToStreamedContentImg(
							mdevpr00.getImafdv(), "jpg");
				} else {
					changeImageSub = false;
					return imageStremSub;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return imageStremSub;
	}

	public void setImageStremSub(StreamedContent imageStremSub) {
		this.imageStremSub = imageStremSub;
	}


	public boolean isChangeImageSub() {
		return changeImageSub;
	}

	public void setChangeImageSub(boolean changeImageSub) {
		this.changeImageSub = changeImageSub;
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

	
}
