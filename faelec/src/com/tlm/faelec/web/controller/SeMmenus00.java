package com.tlm.faelec.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.tlm.faelec.service.maestros.IMGeneralService;
import com.tlm.faelecEntities.model.entities.Mmenus00;
import com.tlm.faelecEntities.model.entities.Mopcme00;

@ManagedBean
@SessionScoped
public class SeMmenus00 
{
	private String mensaje;
	
	
	
	@ManagedProperty(value="#{seControlFact}")
	SeControlFactura seControl;
	
	@ManagedProperty(value="#{mgeneralService}")
	IMGeneralService mgeneralService;
	
	/**
	 * Variables ManagedBean
	 */
	private MenuModel menuModel; 
	private List<Mmenus00> listMmenus;
	
	
	/**
	 * Constructor
	 */
	public SeMmenus00(){	
		
	}
	
	/**
	 * Post Constructor
	 */
	@PostConstruct
	public void cargarMenu(){
		try {
			listMmenus = mgeneralService.listMmenus00(seControl.aplusu,seControl.getMusuar00Session().getGruusu());		
			menuModel = new DefaultMenuModel();			
			DefaultSubMenu dfMenu;
			
			for (Mmenus00 menu : listMmenus) {
				Mopcme00 mopcme00Aux= new Mopcme00();			
				mopcme00Aux.setMenopm(menu.getCodmen());
				mopcme00Aux.setAplopm(seControl.aplusu);
				List<Mopcme00> listSubMenu = mgeneralService.listMopcme00ByCriteria(mopcme00Aux, seControl.getMusuar00Session().getGruusu());
				dfMenu = new DefaultSubMenu(menu.getNommen());				
				dfMenu.setIcon(menu.getIconen());
				for (Mopcme00 mopcme00 : listSubMenu) {
						DefaultSubMenu dfMenuAnidado = isMenuItem(mopcme00, dfMenu, null);
						
						if(dfMenuAnidado == null){
						}else{
							dfMenu = dfMenuAnidado;
						}
				}				
				menuModel.addElement(dfMenu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Mètodo encargado de anidar los submenu que hacen referencia a otro menu
	 * @param subMenu
	 * @return
	 */
	private DefaultSubMenu isMenuItem(Mopcme00 mopcme00, DefaultSubMenu dfMenu, DefaultSubMenu padre){
		try {
			if((mopcme00.getMniopm() == null || mopcme00.getMniopm().trim().equals(""))){
				DefaultMenuItem dfSubMenu = new DefaultMenuItem(mopcme00.getNomopm());
				//dfSubMenu.setCommand(mopcme00.getPgiopm());
				
				dfSubMenu.setParam("url", mopcme00.getPgiopm()!=null?mopcme00.getPgiopm().trim():null);	
				dfSubMenu.setParam("menopm", mopcme00.getMenopm());
				dfSubMenu.setParam("numopm", mopcme00.getNumopm());
				dfSubMenu.setParam("mopcme00", mopcme00);
				dfSubMenu.setCommand("#{seTemplate.evtSeleccionMenu}");	
				dfSubMenu.setIcon(mopcme00.getIconpm());
				dfSubMenu.setAjax(false);			
				
				if(validarUrl(mopcme00)){//Opcional
					if(padre == null){					
						dfMenu.addElement(dfSubMenu);
					}else{
						padre.addElement(dfSubMenu);
					}
				}
				
				return null;
			}else{
				DefaultSubMenu dfMenuPadre = new DefaultSubMenu(mopcme00.getNomopm());	
				mopcme00.setMenopm(mopcme00.getMniopm());
				List<Mopcme00> listMopcme00 = mgeneralService.listMopcme00ByCriteria(mopcme00,seControl.getMusuar00Session().getGruusu());
				
				for(Mopcme00 subMenuAux : listMopcme00){
					isMenuItem(subMenuAux, dfMenu, dfMenuPadre);
				}				
				dfMenu.addElement(dfMenuPadre);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return dfMenu;
	}
	
	
	private boolean validarUrl(Mopcme00 mopcme00){
		boolean isValida = false;
		try {
			if(mopcme00.getPgiopm() != null && !mopcme00.getPgiopm().equals("")){
				isValida = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValida;
	}
	
	
	public void prueba(){
		try {

			Mopcme00 mopcme00 = new Mopcme00();		
			mopcme00.setAplopm("03");
			mopcme00.setMenopm("00051");
			
			List<Mopcme00>list = mgeneralService.listMopcme00ByCriteria(mopcme00,"01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Getter y Setter
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public List<Mmenus00> getListMmenus() {
		return listMmenus;
	}

	public void setListMmenus(List<Mmenus00> listMmenus) {
		this.listMmenus = listMmenus;
	}

	
	public IMGeneralService getMgeneralService() {
		return mgeneralService;
	}

	public void setMgeneralService(IMGeneralService mgeneralService) {
		this.mgeneralService = mgeneralService;
	}

	public SeControlFactura getSeControl() {
		return seControl;
	}

	public void setSeControl(SeControlFactura seControl) {
		this.seControl = seControl;
	}
	
}