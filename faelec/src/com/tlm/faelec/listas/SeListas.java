package com.tlm.faelec.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


import com.tlm.faelec.service.maestros.IMaccio00Service;
import com.tlm.faelec.service.maestros.IMacdio00Service;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMcotes00Service;
import com.tlm.faelec.service.maestros.IMestad00Service;
import com.tlm.faelec.service.maestros.IMfunfu00Service;
import com.tlm.faelec.service.maestros.IMgencg00Service;
import com.tlm.faelec.service.maestros.IMgente00Service;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMidiom00Service;
import com.tlm.faelec.service.maestros.IMmesje00Service;
import com.tlm.faelec.service.maestros.IMpropr00Service;
import com.tlm.faelec.service.maestros.IMregcg00Service;
import com.tlm.faelec.service.maestros.IMtabla00Service;
import com.tlm.faelec.service.maestros.IMtipre00Service;
import com.tlm.faelec.service.maestros.IMtiptx00Service;
import com.tlm.faelec.service.maestros.IMvaria00Service;
import com.tlm.faelec.service.trans.ITqdpqp00Service;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.web.controller.SeControlFactura;
import com.tlm.faelec.web.controller.SeControlFactura.NombresListas;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Mmesje00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Mregcg00;
import com.tlm.faelecEntities.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Mtiptx00;
import com.tlm.faelecEntities.model.entities.Mvaria00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;


@ManagedBean
@SessionScoped
public class SeListas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mgenus00Service}")
	private IMgenus00Service mgenus00Service;
	
	@ManagedProperty(value="#{seControlFact}")
	private SeControlFactura seControlFactura;
	
	@ManagedProperty(value = "#{mconca00Service}")
	private IMconca00Service imconcaservice;
	
	@ManagedProperty(value = "#{mvaria00Service}")
	private IMvaria00Service mvaria00Service;
		
	@ManagedProperty(value = "#{mtipre00Service}")
	private IMtipre00Service mtipre00Service;
	
	@ManagedProperty(value = "#{mmesje00Service}")
	private IMmesje00Service mmesje00Service;
	
	@ManagedProperty("#{mgente00Service}")
	private IMgente00Service mgente00Service;

	@ManagedProperty(value = "#{midiom00Service}")
	private IMidiom00Service midiom00Service;
	
	@ManagedProperty(value = "#{mpropr00Service}")
	private IMpropr00Service mpropr00Service;
	
	@ManagedProperty(value = "#{mgencg00Service}")
	private IMgencg00Service mgencg00Service;
	
	@ManagedProperty(value = "#{mtiptx00Service}")
	private IMtiptx00Service imtiptx00Service;
	
	@ManagedProperty(value = "#{mestad00Service}")
	private IMestad00Service mestad00Service;
	
	@ManagedProperty(value = "#{macdio00Service}")
	private IMacdio00Service macdio00Service;
	
	@ManagedProperty(value = "#{maccio00Service}")
	private IMaccio00Service maccio00Service;
	
	@ManagedProperty(value = "#{mtabla00Service}")
	private IMtabla00Service mtabla00Service;
	
	@ManagedProperty(value = "#{mfunfu00Service}")
	private IMfunfu00Service mfunfu00Service;
	
	@ManagedProperty(value = "#{mcotes00Service}")
	private IMcotes00Service mcotes00Service;
	
	@ManagedProperty(value = "#{mregcg00Service}")
	private IMregcg00Service mregcg00Service;
	
	@ManagedProperty(value = "#{tqdpqp00Service}")
	private ITqdpqp00Service tqdpqp00Service;
	
	@ManagedProperty(value = "#{tqdrqr00Service}")
	private ITqdrqr00Service tqdrqr00Service;
	
	
	private List<Mconca00> listMconca00Pal;//Lista Mconca
	private List<Mconca00> filteredMconca00Pal;

	//Lista Mtipre
	private List<Mtipre00> filteredMtipre00; 
	private List<Mtipre00> listMtipre00;
	private List<Mtipre00> listMtipreTerceros;
	private List<Mtipre00> filteredMtipreTerceros;
	private List<Mtipre00> listMtipreGConfi;
	private List<Mtipre00> filteredMtipreGConfi;
	
	private List<Mmesje00> listMmesje00; // Lista Mmesje
	private List<Mmesje00> filteredMmesje00;
	
	//Lista de Idiomas
	private List<Midiom00> listMidiom00;
	private List<Midiom00> filteredMidiom00;
	
	
	//listas de Mgente
	private List<Mgente00> listMgenteReplegal;
	private List<Mgente00> filteredMgenteReplegal;	
	private List<Mgente00> listMgenteGerenteAsesora;
	private List<Mgente00> filteredMgenteGerenteAsesora;
	private List<Mgente00> listMgenteAsesora;
	private List<Mgente00> filteredMgenteAsesora;
	private List<Mgente00> listMgenteCliente;
	private List<Mgente00> filteredMgenteCliente;
	private List<Mgente00> listMgenteCodtte;
	private List<Mgente00> filteredMgenteCodtte;

	//Listas de mgenus
	private List<Mgenus00> listMgenusCiudad00;
	private List<Mgenus00> filteredMgenusCiudad00;
	private List<Mgenus00> listMgenusPaises;
	private List<Mgenus00> filteredMgenusPais;
	private List<Mgenus00> filteredMgenusTident00;
	private List<Mgenus00> listMgenusTident00;
	private List<Mgenus00> filteredMgenusMarcaComercial00;
	private List<Mgenus00> listMgenusMarcaComercial00;
	private List<Mgenus00> listMgenusLineaProductos;
	private List<Mgenus00> filteredMgenusLineaProductos;
	private List<Mgenus00> listMgenusSubLineaProductos;
	private List<Mgenus00> filteredMgenusSubLineaProductos;
	private List<Mgenus00> listMgenusUnidadMedida;
	private List<Mgenus00> filteredMgenusUnidadMedida;
	private List<Mgenus00> listMgenusCentroCostos;
	private List<Mgenus00> filteredMgenusCentroCostos;
	private List<Mgenus00> listMgenusClaseDocumentos;
	private List<Mgenus00> filteredMgenusClaseDocumentos;
	private List<Mgenus00> listMgenusDocumentos;
	private List<Mgenus00> filteredMgenusDocumentos;
	private List<Mgenus00> listMgenusUnidadMedidaIng;
	private List<Mgenus00> filteredMgenusUnidadMedidaIng;
	private List<Mgenus00> listMgenusTipoTransaccion;
	private List<Mgenus00> filteredMgenusTipoTransaccion;
	private List<Mgenus00> listMgenusBarrioSector;
	private List<Mgenus00> filteredMgenusBarrioSector;

	private List<Mgenus00> listMgenusRegion;
	private List<Mgenus00> filteredMgenusRegion;
	private List<Mgenus00> listMgenusDepartamento;
	private List<Mgenus00> filteredMgenusDepartamento;
	private List<Mgenus00> listMgenusSector;
	private List<Mgenus00> filteredMgenusSector;
	private List<Mgenus00> listMgenusTipoAsesora;
	private List<Mgenus00> filteredMgenusTipoAsesora;
	private List<Mgenus00> listMgenusTipoNaturaleza;
	private List<Mgenus00> filteredMgenusTipoNaturaleza;
	private List<Mgenus00> listMgenusSegmentoAsesora;
	private List<Mgenus00> filteredMgenusSegmentoAsesora;	
	private List<Mgenus00> listMgenusGrupoActividad;
	private List<Mgenus00> filteredMgenusGrupoActividad;
	private List<Mgenus00> listMgenusActividad;
	private List<Mgenus00> filteredMgenusActividad;
	private List<Mgenus00> listMgenusCicloAsesora;
	private List<Mgenus00> filteredMgenusCicloAsesora;
	private List<Mgenus00> listMgenusTipoCPC;
	private List<Mgenus00> filteredMgenusTipoCPC;
	private List<Mgenus00> listMgenusModalidadCPC;
	private List<Mgenus00> filteredMgenusModalidadCPC;	
	private List<Mgenus00> listMgenus00CatalogoPara;
	private List<Mgenus00> filteredMgenus00CatalogoPara;
	private List<Mgenus00> listMgenus00Variantes;
	private List<Mgenus00> filteredMgenus00Variantes;
	private List<Mgenus00> listMgenus00Evento;
	private List<Mgenus00> filteredMgenus00Evento;
	private List<Mgenus00> listMgenusMoneda;
	private List<Mgenus00> filteredMgenusMoneda;
	private List<Mgenus00> listMgenusMedioPago;
	private List<Mgenus00> filteredMgenusMedioPago;
	private List<Mgenus00> listMgenusContribuyente;
	private List<Mgenus00> filteredMgenusContribuyente;
	private List<Mgenus00> listMgenusRegimen;
	private List<Mgenus00> filteredMgenusRegimen;
	private List<Mgenus00> listMgenusSaludo;
	private List<Mgenus00> filteredMgenusSaludo;
	private List<Mgenus00> listMgenusAreaLabor;
	private List<Mgenus00> filteredMgenusAreaLabor;
	private List<Mgenus00> listMgenusCargoTercero;
	private List<Mgenus00> filteredMgenusCargoTercero;
	private List<Mgenus00> listMgenusIncoterms;
	private List<Mgenus00> filteredMgenusIncoterms;
	private List<Mgenus00> listMgenusOrganiVentas;
	private List<Mgenus00> filteredMgenusOrganiVentas;
	private List<Mgenus00> listMgenusCanalDistri;
	private List<Mgenus00> filteredMgenusCanalDistri;
	private List<Mgenus00> listMgenusEstdPart;
	private List<Mgenus00> filteredMgenusEstdPart;
	private List<Mgenus00> listMgenusComplejidad;
	private List<Mgenus00> filteredMgenusComplejidad;
	private List<Mgenus00> listMgenusLegal;
	private List<Mgenus00> filteredMgenusLegal;
	private List<Mgenus00> listMgenusTipoBienServi;
	private List<Mgenus00> filteredMgenusTipoBienServi;
	private List<Mgenus00> listMgenusTarifaRetefDIAN;
	private List<Mgenus00> filteredMgenusTarifaRetefDIAN;
	private List<Mgenus00> listMgenus00MonedaCot;
	private List<Mgenus00> filteredMgenus00MonedaCot;
	private List<Mgenus00> listMgenus00UnidadMedidaCot;
	private List<Mgenus00> filteredMgenus00UnidadMedidaCot;
	
	//Lista de Mpropr00
	private List<Mpropr00> listMpropr00;
	private List<Mpropr00> filteredMpropr00;

	//Lista de Mfunfu00
	private List<Mfunfu00> listMfunfu;
	private List<Mfunfu00> filteredMfunfu;
	
	//Lista de Mfunfu00
	private List<Mcotes00> listMcotes00;
	private List<Mcotes00> filteredMcotes00;
	
	//Lista de Mregcg00
	private List<Mregcg00> listMregcg00;
	private List<Mregcg00> filteredMregcg00;


	//Listas Mvaria00
	private List<Mvaria00> listMvaria00;
	private List<Mvaria00> filteredMvaria00;
	private List<Mvaria00> listMvaria00Principal;
	private List<Mvaria00> filteredMvaria00Principal;
	private List<Mvaria00> listMvaria00Secundaria;
	private List<Mvaria00> filteredMvaria00Secundaria;
	
	
	//Listas Mgencg
	private List<Mgencg00> listMgencg00;
	private List<Mgencg00> filteredMgencg00;
	private List<Mgencg00> listMgencgArbol;
	private List<Mgencg00> filteredMgencgArbol;
	private List<Mgencg00> listMgencgCodTransaccion;
	private List<Mgencg00> filteredMgencgCodTransaccion;
	private List<Mgencg00> listMgencgCompania;
	private List<Mgencg00> filteredMgencgCompania;
	private List<Mgencg00> listMgencg00Evento;
	private List<Mgencg00> filteredMgencg00Evento;
	
	
	//ListaMtiptx
	private List<Mtiptx00> listMtiptx00;
	private List<Mtiptx00> filteredMtiptx00;
	
	//ListaMtiptx
	private List<Mtiptx00> listMtiptxEstado;
	private List<Mtiptx00> filteredMtiptxEstado;
	
	
	//Lista Mestad00
	private List<Mestad00> listMestad00;
	private List<Mestad00> filteredMestad00;
	
	private List<Mestad00> listMestadTxt00;
	private List<Mestad00> filteredMestadTxt00;
	
	private String textoBienvenida;
	
	// Lista Mestad00
	private List<Macdio00> listMacdio00;
	private List<Macdio00> filteredMacdio00;
	
	// Lista Mtabla00
	private List<Mtabla00> listMtabla00; 
	private List<Mtabla00> filteredMtabla00;
	
	/*
	 * Lista Acciones 
	 */
	private List<Maccio00> listMaccio00;
	private List<Maccio00> filteredMaccio00;
	
	//Lista Tqdpqp00 para Coti
	//Carga de datos Req - Prod
	private Tqdpqp00 tqdpqp00;
	private Tqdrqr00 tqdrqr00;
	private List<Tqdpqp00> listTqdpqp00;
	private List<Tqdrqr00> listTqdrqr00;
	private List<Tqdpqp00> listTqdpqp00Aux;
	private List<Tqdpqp00> filteredTqdpqp00;
	
 	/*
	 * Construct
	 */
	public SeListas() {

	}

	@PostConstruct
	public void init(){
		try {
			textoBienvenida = "";
			textoBienvenida = (String)getSeControlFactura().PARAMETROS.get("Tex_BienvE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarListas(){	
		cargarListaMpropr();
		cargarListaMfunfu(null);
		cargarListaMgente00Codtte(null);
		cargarListaMregcg00();
		cargarListaMconca();
		cargarListasMgenus();
		cargarListaMtipre(null);
		cargarListaMmesje();
		cargarlistaMgenteRepresentanteLegal(null);
		cargarListaMidiom(null);			
		cargarlistasMgencgArbol(null);
		cargarlistasMgencg(null);		
		cargarListaMgente00Asesora();
		cargarListaMgente00Cliente();
		cargarListaMtiptx();
		cargarListaMestad00(null);
		cargarListaMestadTxt00(null,null);
		cargarListaMacdio00(null);		
		cargarListaMaccio00(null);
		cargarListaMtiptxEstado(null);
		cargarlistasMgencgCompania(null);
		cargarListaMgenusMonedaCot(null);
		cargarListaMgenusUnidadMedidaCot(null);
		cargarListaMcotes(null);
		//cargarDatosReqProd();
		
	}
	public Tqdrqr00 consultarObjectTqdrqr00(){
		tqdrqr00 = new Tqdrqr00();
		
		return tqdrqr00;
	}
	
	public void cargarDatosReqProd(Tqdrqr00 tqdrqr00) {
		
		listTqdpqp00 = tqdpqp00Service.listTqdpqp00Criteria(new Tqdpqp00(),getSeControlFactura().getCompaniasUsu());
		listTqdrqr00 = tqdrqr00Service.listTqdrqr00ByCriteria(tqdrqr00);
		listTqdpqp00Aux = new ArrayList<Tqdpqp00>();
		for(Tqdpqp00 objPro: listTqdpqp00){
			for(Tqdrqr00 objReq: listTqdrqr00){
				if(objPro.getTqdrqr00().getIdtrqr() == objReq.getIdtrqr()){
					listTqdpqp00Aux.add(objPro);				
				}
			}
		}
		//setListTqdpqp00Aux(listTqdpqp00);
	}
	
	
	
	private void cargarListaMmesje() {
		try{
			
		listMmesje00 = mmesje00Service.listMmesje00ByCriteria(new Mmesje00(),getSeControlFactura().getCompaniasUsu());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void cargarListaMpropr() {
		try{
			Mpropr00 mproprAux = new Mpropr00();
			mproprAux.setRegipr(true);
			listMpropr00 = mpropr00Service.listMpropr00Criteria(mproprAux,getSeControlFactura().getCompaniasUsu());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void cargarListaMgenusMonedaCot(List<String> companias) {
		try{
			Mgenus00 mgenus00 = new Mgenus00();
			mgenus00.setMtipre00(new Mtipre00());
			mgenus00.getMtipre00().setTipore(Utils.paramsRb.getString("mgenus_codtus_Cotizacion"));
			mgenus00.setRegius(true);
			listMgenus00MonedaCot = mgenus00Service.listMgenus00ByCriteriaAndlistMusuco00(mgenus00, getSeControlFactura().getCompaniasUsu()); 
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	private void cargarListaMgenusUnidadMedidaCot(List<String> companias) {
		try{
			Mgenus00 mgenus00 = new Mgenus00();
			mgenus00.setMtipre00(new Mtipre00());
			mgenus00.getMtipre00().setTipore(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"));
			mgenus00.setRegius(true);
			listMgenus00UnidadMedidaCot = mgenus00Service.listMgenus00ByCriteriaAndlistMusuco00(mgenus00, getSeControlFactura().getCompaniasUsu()); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void cargarListaMfunfu(List<String> companias) {
		try{
			Mfunfu00 mfunfuAux = new Mfunfu00();
			mfunfuAux.setRegufu(true);
			listMfunfu  = mfunfu00Service.listMfunfu00Criteria(mfunfuAux, companias);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	private void cargarListaMcotes(List<String> companias) {
		try{
			Mcotes00 mcotesAux = new Mcotes00();
			mcotesAux.setRegtes(true);
			listMcotes00  = mcotes00Service.listMcotes00Criteria(mcotesAux, companias);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	private void cargarListaMregcg00() {
		try{
			Mregcg00 mregcgAux = new Mregcg00();
			mregcgAux.setRegegc(true);
			listMregcg00  = mregcg00Service.listMregcg00Criteria(mregcgAux, getSeControlFactura().getCompaniasUsu());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Mvaria00> cargarListaMvaria00(List<String> companias, Mgenus00 mgenus00) {
		try{
			Mvaria00 mvaria00 = new Mvaria00();
			mvaria00.setRegivt(true);
			return mvaria00Service.listMvaria00(mvaria00, companias, mgenus00);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	private void cargarListaMconca() {
		try{
			Mconca00 mconcaAux = new Mconca00();
			mconcaAux.setRegcia(true);
			listMconca00Pal = imconcaservice.listMconca00ByRegcia(mconcaAux,getSeControlFactura().getCompaniasUsu());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void cargarListaMgente00Asesora() {
		try{
			Mgente00 mgente00Aux = new Mgente00();
			mgente00Aux.setMtipre00(new Mtipre00());
			mgente00Aux.getMtipre00().setTipore(Utils.paramsRb.getString("mgente_codtte_Asesora"));
			mgente00Aux.setRegite(true);
			listMgenteAsesora = mgente00Service.listMgente00Asesora(mgente00Aux, getSeControlFactura().getCompaniasUsu());			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	//Maestro MPRECI01 nosorio-24102017
		private void cargarListaMgente00Cliente() {
			try{
				Mgente00 mgente00Aux = new Mgente00();
				mgente00Aux.setMtipre00(new Mtipre00());
				mgente00Aux.getMtipre00().setTipore(Utils.paramsRb.getString("mgente_codtte_cliente"));
				mgente00Aux.setRegite(true);
				listMgenteCliente = mgente00Service.listMgente00Cliente(mgente00Aux, getSeControlFactura().getCompaniasUsu());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	public void obtenerMtipre(Mtipre00 mtipre, List<String> companias){
		listMtipre00 = mtipre00Service.listMtipre00ByCriteria(mtipre, companias);
	}

	private void cargarListaMtipre(List<String> companias) {
		try{
			listMtipreTerceros = obtenerListaMtipre(Utils.paramsRb.getString("mtipre_tperre_mgente00"),companias);
			listMtipre00 =  obtenerListaMtipre(Utils.paramsRb.getString("mtipre_tperre_mgenus00"),companias);
			listMtipreGConfi= obtenerListaMtipre(Utils.paramsRb.getString("mtipre_tperre_mgencg00"),companias);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void cargarListaMidiom(List<String> companias) {
		try{
			Midiom00 midiomAux00 = new Midiom00();
			midiomAux00.setRegidi(true);
		    listMidiom00 = midiom00Service.listMidiom00ByCriteria(midiomAux00, companias);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<Mtipre00> obtenerListaMtipre(String tperre,List<String> companias){
			Mtipre00 mtipre00 = new Mtipre00();
			mtipre00.setTperre(tperre);
			mtipre00.setRegitr(true);
			return mtipre00Service.listMtipre00ByCriteria(mtipre00,companias);	
	}
	private void cargarlistaMgenteRepresentanteLegal(List<String> companias){
		listMgenteReplegal = obtenerListaMgente00(Utils.paramsRb.getString("mgente_codtte_representanteLegal"), companias);
	}
	
	private void cargarlistasMgencg(List<String> companias){
		Mgencg00 MgencgAux00= new Mgencg00();
		MgencgAux00.setRegicg(true);
		MgencgAux00.setMtipre00(new Mtipre00());
		MgencgAux00.getMtipre00().setTipore(Utils.paramsRb.getString("mgencg_codtcg_mtiptx00"));		
		listMgencgCodTransaccion = mgencg00Service.listMgencg00ByCriteria(MgencgAux00,companias);
	}
	private void cargarlistasMgencgArbol(List<String> companias){
		Mgencg00 MgencgAux00= new Mgencg00();
		MgencgAux00.setRegicg(true);
		MgencgAux00.setMtipre00(new Mtipre00());
		MgencgAux00.getMtipre00().setTipore(Utils.paramsRb.getString("mgencg_codtcg_mtipre00"));		
		listMgencgArbol = mgencg00Service.listMgencg00ByCriteria(MgencgAux00, companias);
	}
	
	private void cargarlistasMgencgCompania(List<String> companias){
		Mgencg00 MgencgAux00= new Mgencg00();
		MgencgAux00.setRegicg(true);
		MgencgAux00.setMtipre00(new Mtipre00());
		MgencgAux00.getMtipre00().setTipore(Utils.paramsRb.getString("mgencg_codtcg_mconca00"));		
		listMgencgCompania = mgencg00Service.listMgencg00ByCriteria(MgencgAux00, companias);
	}
	
	private void cargarlistaMgencgEvent(List<String> companias){
		Mgencg00 MgencgAux00= new Mgencg00();
		MgencgAux00.setRegicg(true);
		MgencgAux00.setMtipre00(new Mtipre00());
		MgencgAux00.getMtipre00().setTipore(Utils.paramsRb.getString("mgencg_codtcg_evento"));		
		listMgencg00Evento = mgencg00Service.listMgencg00ByCriteria(MgencgAux00,companias);
	}

	public List<Mgente00> obtenerListaMgente00(String codtte, List<String> companias){
	    Mgente00 mgente00 = new Mgente00();
		mgente00.setMtipre00(new Mtipre00());
		mgente00.getMtipre00().setTipore(codtte);
		mgente00.setRegite(true);
		return mgente00Service.listMgente00ByCodtte(mgente00, companias); 
	}
	
	private void cargarListasMgenus(){
		listMgenusPaises = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_pais"),null);
		listMgenusCiudad00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_ciudades"),null);
		listMgenusTident00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoIdentificacion"),null);
		listMgenusMarcaComercial00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_marcaComercial"),null);
		listMgenusLineaProductos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_lineaProductos"),null);
		listMgenusSubLineaProductos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_sublineaProductos"),null);
		//listMgenusUnidadMedida = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"),null);
		listMgenusCentroCostos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_centroCostos"),null);
		listMgenusClaseDocumentos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_ClaseDocumentos"),null);
		listMgenusDocumentos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_documento"),null);
		listMgenusRegion = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Region"),null);
		listMgenusDepartamento = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Departamento"),null);
		listMgenusBarrioSector = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_BarrioSector"),null);
		listMgenusSector = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Sector"),null);
		listMgenusTipoAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoAsesora"),null);
		listMgenusTipoNaturaleza = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoNaturaleza"),null);
		listMgenusSegmentoAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_segmentoAsesora"),null);
		listMgenusActividad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_actividad"),null);
	    listMgenusGrupoActividad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_grupoActividad"),null);
	    listMgenusCicloAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_cicloAsesora"),null);
	    listMgenusTipoCPC = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoCPC"),null);
	    listMgenusModalidadCPC = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_modalidadCPC"),null);
	    listMgenus00CatalogoPara = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_CatalogoPara"),null);
	    listMgenus00Variantes = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_variantes"),null);
	    listMgenus00Evento = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_evento"),null); 
	    listMgenusMoneda = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_monedas"), null);
	    listMgenusMedioPago = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_mediopago"), null);
	    listMgenusContribuyente = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipo_contribuyente"), null);
	    listMgenusRegimen = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipo_regimen"), null);
	    listMgenusSaludo = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_saludo"), null);
	    listMgenusAreaLabor = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_areaLabor"), null);
	    listMgenusCargoTercero = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_cargoTercero"), null);
	    listMgenusIncoterms = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Incoterm"), null);
	    listMgenusOrganiVentas = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_OrganiVentas"), null);
	    listMgenusCanalDistri = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_CanalDistri"), null);
		//listMgenusUnidadMedidaIng = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"),null);
		listMgenusTipoTransaccion= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TipoTransaccion"),null);
		listMgenusEstdPart= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_EstdPart"), null);
		listMgenusComplejidad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Complejidad"), null);
		listMgenusLegal = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Legal"), null);
		listMgenusTipoBienServi = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TipoBienServi"), null);
		listMgenusTarifaRetefDIAN = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TarifaRetefDIAN"), null);
		
		System.out.println("listMgenusContribuyente: "+listMgenusContribuyente);

	}
	
	
	private List<Mgenus00> obtenerListaMgenus00(String codtus,List<String> listMconca00){
	    Mgenus00 mgenus00 = new Mgenus00();
	    mgenus00.setMtipre00(new Mtipre00());
		mgenus00.getMtipre00().setTipore(codtus);
		mgenus00.setRegius(true);
	    return mgenus00Service.listMgenus00ByCriteriaAndlistMusuco00(mgenus00, getSeControlFactura().getCompaniasUsu());
	}	
	
	private void cargarListaMtiptx() {
		try{
			Mtiptx00 mtiptx00 = new Mtiptx00();
			mtiptx00.setRegtxt(true);
			listMtiptx00 = imtiptx00Service.listMtiptx00ByCriteria(mtiptx00, getSeControlFactura().getCompaniasUsu());		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void cargarListaMtiptxEstado(List<String> companias) {
		try{
			Mtiptx00 mtiptx00 = new Mtiptx00();
			mtiptx00.setRegtxt(true);
			listMtiptxEstado = imtiptx00Service.listMtiptx00ByCriteriaEstado(mtiptx00, companias);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void cargarListaMtiptx00Mestad00(List<String> companias) {
		try{
			listMtiptxEstado = imtiptx00Service.listMtiptx00ByMestad00(companias);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarListaMestad00(List<String> companias) {
		try{
			Mestad00 mestad00 = new Mestad00();
			listMestad00 = mestad00Service.listMestad00ByCriteria(mestad00, companias);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarListaMestadTxt00(Integer idTxt,List<String> companias) {
		try{
			Mestad00 mestad00 = new Mestad00();	
			mestad00.setMtiptx00(new Mtiptx00());
			mestad00.getMtiptx00().setIdtptx(idTxt);
			
			List<Mestad00> listMestadTxt00Temp = new ArrayList<Mestad00>(); 
			listMestadTxt00 = mestad00Service.listMestad00ByCriteria(mestad00, companias);
			 for (Mestad00 obj : listMestadTxt00) {        	
		        	if(obj.getRegtes()){
		        		listMestadTxt00Temp.add(obj);        		
		    		}			
				}
			 listMestadTxt00 = listMestadTxt00Temp;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Maestro MPRECI01 nosorio-24102017
	private void cargarListaMgente00Codtte(List<String> companias) {
		try{
			Mgente00 mgente00Aux = new Mgente00();
			mgente00Aux.setMtipre00(new Mtipre00());
			mgente00Aux.getMtipre00().setTipore(Utils.paramsRb.getString("mgente_codtte_proveedor"));
			mgente00Aux.setRegite(true);
			listMgenteCodtte = mgente00Service.listMgente00Cliente(mgente00Aux, getSeControlFactura().getCompaniasUsu());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private void cargarListaMacdio00(List<String> companias) {
		try{			
			listMacdio00 = macdio00Service.listMacdio00ByCriteria(companias);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void cargarListaMaccio00(List<String> companias) {
		try{
			Maccio00 maccio00 = new Maccio00();
			maccio00.setRegcio(true);
			listMaccio00 = maccio00Service.getListMaccio00(maccio00, companias);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cargarListaMtabla00(){
		try {
			Mtabla00 mtabla00 = new Mtabla00();
			mtabla00.setUspaab("S");
			listMtabla00 = mtabla00Service.listMtabla00ByCriteria(getSeControlFactura().aplusu, mtabla00);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarListas(NombresListas lista){
		try{
			switch (lista) {
	         case listMconca:
	        	 cargarListaMconca();
	             break;
	         case listMtipre:
	        	 cargarListaMtipre(null);
	             break;
	         case listMgenusTident:
	        	 listMgenusTident00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoIdentificacion"),null);
	        	 break;			
	         case listMgenusPaises:
	        	 listMgenusPaises = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_pais"),null);
	        	 break;
	         case listMgenusCiudad:
	        	 listMgenusCiudad00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_ciudades"),null);
	        	 break;
	         case listMgenusMarcaComercial:
	        	 listMgenusMarcaComercial00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_marcaComercial"),null);
	        	 break;
	         case listMgenusLineaProductos:
	        	 listMgenusLineaProductos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_lineaProductos"),null);
	        	 break;
	         case listMgenusSubLineaProductos:
	        	 listMgenusSubLineaProductos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_sublineaProductos"),null);
	        	 break;
	        // case listMgenusUnidadMedida:
	        	// listMgenusUnidadMedida = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"),null);
	        	 //break;
	         case listMgenusCentroCostos:
	        	 listMgenusCentroCostos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_centroCostos"),null);
	        	 break;
	         case listMgenusClaseDocumentos:
	        	 listMgenusClaseDocumentos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_ClaseDocumentos"),null);
	        	 break;
	         case listMgenusDocumentos:
	        	 listMgenusDocumentos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_documento"),null);
	        	 break;
	         case listMgenusRegion:
	        	 listMgenusRegion = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Region"),null);
	        	 break;
	         case listMgenusDepartamento:
	        	 listMgenusDepartamento = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Departamento"),null);
	        	 break;
	         case listMgenusBarrioSector:
	        	 listMgenusBarrioSector = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_BarrioSector"),null);
	        	 break;
	         case listMgenusSector:
	        	 listMgenusSector = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Sector"),null);
	        	 break;
	         case listMgenusTipoAsesora:
	        	 listMgenusTipoAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoAsesora"),null);
	        	 break;
	         case listMgenusTipoNaturaleza:
	        	 listMgenusTipoNaturaleza = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoNaturaleza"),null);
	        	 break;
	         case listMgenusActividad:
	        	 listMgenusActividad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_actividad"),null);
	        	 break;
	         case listMgenusGrupoActividad:
	        	 listMgenusGrupoActividad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_grupoActividad"),null);
	        	 break;
	         case listMgenusTipoCPC:
	        	 listMgenusTipoCPC = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoCPC"),null);
	        	 break;
	         case listMgenusModalidadCPC:
	        	 listMgenusModalidadCPC = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_modalidadCPC"),null);
	        	 break;
	         case listMgenusCicloAsesora:
	        	 listMgenusCicloAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_cicloAsesora"),null);
	        	 break;	 
	         case listMgenusSegmentoAsesora:
	        	 listMgenusSegmentoAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_segmentoAsesora"),null);
	        	 break;	
	         case listMidiom:
	        	 cargarListaMidiom(null);
	        	 break;
	         case listMmesje:
	        	 cargarListaMmesje();
	        	 break;
	         case listMgencg:
	        	 cargarlistasMgencg(null);
	        	 break;
	         case listMgencgArbol:
	        	 cargarlistasMgencgArbol(null);
	        	 break;
	         case listMgenteAsesora:
	        	 cargarListaMgente00Asesora();
	        	 break;
	         case listMgenteCliente:
	        	 cargarListaMgente00Cliente();
	        	 break;
	         case listMgenus00MonedaCot:
	        	cargarListaMgenusMonedaCot(null);
	        	 break;
	         case listMgenus00UnidadMedidaCot:
	        	 cargarListaMgenusUnidadMedidaCot(null);
		        break;
	        /* case listTqdpqp00Aux:
	        	 cargarDatosReqProd();
			    break;*/
	         case listMgenteRepresentanteLegal:
	        	 cargarlistaMgenteRepresentanteLegal(null);
	        	 break;	
	         case listMgencgCodTransaccion:
	        	 cargarlistasMgencg(null);
	        	 break;
	         case listMtipreGConfi:
	        	 listMtipreGConfi=obtenerListaMtipre(Utils.paramsRb.getString("mtipre_tperre_mgencg00"),null);
	        	 break;
	         case listMtipreTerceros:
	        	 listMtipreTerceros=obtenerListaMtipre(Utils.paramsRb.getString("mtipre_tperre_mgente00"),null);
	        	 break;
	         case listMgenusContribuyente:
		         listMgenusContribuyente= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipo_contribuyente"), null);
		         break;
	         case listMpropr:
	        	 cargarListaMpropr();
	        	 break;
	         case listMfunfu:
	        	 cargarListaMfunfu(null);
	        	 break;
	         case listMgenteCodtte:
	        	 cargarListaMgente00Codtte(null);
	        	 break;
	         case listMcotes00:
	        	 cargarListaMcotes(null);
	        	 break;
	         case listMregcg00:
	        	 cargarListaMregcg00();
	        	 break;
	         case listMgenus00CatalogoPara:
	        	 listMgenus00CatalogoPara = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_CatalogoPara"),null);
	        	 break;
	         case listMgenus00Variantes:
	        	 listMgenus00Variantes = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_variantes"),null);
	        	 break;
	         case listMtiptx00:
	        	 cargarListaMtiptx();
	        	 break;
	         case listMestad00:
	        	 cargarListaMestad00(null);
	        	 break;
	         case listMgenus00Evento:
	        	 listMgenus00Evento = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_evento"),null);
	        	 break;	        
	         case listMacdio00:
	        	cargarListaMacdio00(null);
	        	 break;	        	 
	         case listMaccio00:
	        	 cargarListaMaccio00(null);
	        	 break;
	         case listMtiptxEstado:
	        	 cargarListaMtiptxEstado(null);
	        	 break;
	         case listMtiptx00Mestad00:
	        	 cargarListaMtiptx00Mestad00(null);
	        	 break;
	         case listMestadTxt00:
	        	 cargarListaMestadTxt00(null,null);
	        	 break;
	         case listMgencgCompania:
	        	 cargarlistasMgencgCompania(null);
	        	 break;
	         case listMgencg00Evento:
	        	 cargarlistaMgencgEvent(null);
	        	 break; 
	         case listMgenusSaludo:
	        	 listMgenusSaludo= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_saludo"), null);
		        break;
	         case listMgenusAreaLabor:
	        	 listMgenusAreaLabor= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_areaLabor"), null);
		        	 break;
	         case listMgenusCargoTercero:
	        	 listMgenusCargoTercero= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_cargoTercero"), null);
		        break;
	         case listMgenusMoneda:
		        listMgenusMoneda= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_monedas"), null);
		        break;
	         case listMgenusMedioPago:
			    listMgenusMedioPago= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_mediopago"), null);
			    break;
	         case listMgenusRegimen:
	        	  listMgenusRegimen= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipo_regimen"), null);
				    break;
	         case listMgenusIncoterms:
			        listMgenusIncoterms= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Incoterm"), null);
			        break;
	         case listMgenusOrganiVentas:
	        	 listMgenusOrganiVentas= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_OrganiVentas"), null);
			        break;
	         case listMgenusCanalDistri:
	        	 listMgenusCanalDistri= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_CanalDistri"), null);
			        break;
	         //case listMgenusUnidadMedidaIng:
	        	// listMgenusUnidadMedidaIng = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"),null);
	        	 //break;
	         case listMgenusTipoTransaccion:
	        	 listMgenusTipoTransaccion = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TipoTransaccion"),null);
	        	 break;
	         case listMgenusEstdPart:
	        	 listMgenusEstdPart= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_EstdPart"), null);
			        break;
	         case listMgenusComplejidad:
	        	 listMgenusComplejidad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Complejidad"),null);
	        	 break;
	         case listMgenusLegal:
	        	 listMgenusLegal = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Legal"),null);
	        	 break;
	         case listMgenusTipoBienServi:
	        	 listMgenusTipoBienServi = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TipoBienServi"),null);
	        	 break;
	         case listMgenusTarifaRetefDIAN:
	        	 listMgenusTarifaRetefDIAN = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TarifaRetefDIAN"),null);
	        	 break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void actualizarListasCompania(NombresListas lista,List<String> listMconca00){
		try{
			switch (lista) {
	         case listMconca:
	        	 cargarListaMconca();
	             break;
	         case listMtipre:
	        	 cargarListaMtipre(listMconca00);
	             break;
	         case listMgenusTident:
	        	 listMgenusTident00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoIdentificacion"),listMconca00);
	        	 break;			
	         case listMgenusPaises:
	        	 listMgenusPaises = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_pais"),listMconca00);
	        	 break;
	         case listMgenusCiudad:
	        	 listMgenusCiudad00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_ciudades"),listMconca00);
	        	 break;
	         case listMgenusMarcaComercial:
	        	 listMgenusMarcaComercial00 = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_marcaComercial"),listMconca00);
	        	 break;
	         case listMgenusLineaProductos:
	        	 listMgenusLineaProductos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_lineaProductos"),listMconca00);
	        	 break;
	         case listMgenusSubLineaProductos:
	        	 listMgenusSubLineaProductos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_sublineaProductos"),listMconca00);
	        	 break;
	         //case listMgenusUnidadMedida:
	        	// listMgenusUnidadMedida = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"),listMconca00);
	        	 //break;
	         case listMgenusCentroCostos:
	        	 listMgenusCentroCostos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_centroCostos"),listMconca00);
	        	 break;
	         case listMgenusClaseDocumentos:
	        	 listMgenusClaseDocumentos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_ClaseDocumentos"),listMconca00);
	        	 break;
	         case listMgenusDocumentos:
	        	 listMgenusDocumentos = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_documento"),listMconca00);
	        	 break;
	    
	         case listMgenusRegion:
	        	 listMgenusRegion = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Region"),listMconca00);
	        	 break;
	         case listMgenusDepartamento:
	        	 listMgenusDepartamento = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Departamento"),listMconca00);
	        	 break;
	         case listMgenusBarrioSector:
	        	 listMgenusBarrioSector = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_BarrioSector"),listMconca00);
	        	 break;
	         case listMgenusSector:
	        	 listMgenusSector = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Sector"),listMconca00);
	        	 break;
	         case listMgenusTipoAsesora:
	        	 listMgenusTipoAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoAsesora"),listMconca00);
	        	 break;
	         case listMgenusTipoNaturaleza:
	        	 listMgenusTipoNaturaleza = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoNaturaleza"),listMconca00);
	        	 break;
	         case listMgenusActividad:
	        	 listMgenusActividad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_actividad"),listMconca00);
	        	 break;
	         case listMgenusGrupoActividad:
	        	 listMgenusGrupoActividad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_grupoActividad"),listMconca00);
	        	 break;
	         case listMgenusTipoCPC:
	        	 listMgenusTipoCPC = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipoCPC"),listMconca00);
	        	 break;
	         case listMgenusModalidadCPC:
	        	 listMgenusModalidadCPC = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_modalidadCPC"),listMconca00);
	        	 break;
	         case listMgenusCicloAsesora:
	        	 listMgenusCicloAsesora = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_cicloAsesora"),listMconca00);
	        	 break;	        	 
	         case listMidiom:
	        	 cargarListaMidiom(listMconca00);
	        	 break;
	         case listMmesje:
	        	 cargarListaMmesje();
	        	 break;
	         case listMgenteGerenteAsesora:
//	        	 cargarListaAsesoraGerente(listMconca00);
	        	 break;
	         case listMgencg:
	        	 cargarlistasMgencg(listMconca00);
	        	 break;
	         case listMgencgArbol:
	        	 cargarlistasMgencgArbol(listMconca00);
	        	 break;
	         case listMgenteAsesora:
	        	 cargarListaMgente00Asesora();
	        	 break;
	         case listMgenteCliente:
	        	 cargarListaMgente00Cliente();
	        	 break;
	         case listMgenus00MonedaCot:
	        	 cargarListaMgenusMonedaCot(listMconca00);
	        	 break;
	        /* case listTqdpqp00Aux:
	        	 cargarDatosReqProd();
			    break;*/
	         case listMgenus00UnidadMedidaCot:
	        	 cargarListaMgenusUnidadMedidaCot(listMconca00);
	        	 break;
	         case listMgenteRepresentanteLegal:
	        	 cargarlistaMgenteRepresentanteLegal(listMconca00);
	        	 break;	
	         case listMgencgCodTransaccion:
	        	 cargarlistasMgencg(listMconca00);
	        	 break;
	         case listMgencgCompania:
	        	 cargarlistasMgencgCompania(listMconca00);
	        	 break;
	         case listMgencg00Evento:
	        	 cargarlistaMgencgEvent(listMconca00);
	        	 break;
	         case listMtipreGConfi:
	        	 listMtipreGConfi=obtenerListaMtipre(Utils.paramsRb.getString("mtipre_tperre_mgencg00"),listMconca00);
	        	 break;
	         case listMtipreTerceros:
	        	 listMtipreTerceros=obtenerListaMtipre(Utils.paramsRb.getString("mtipre_tperre_mgente00"),listMconca00);
	        	 break;
	         case listMpropr:
	        	 cargarListaMpropr();
	        	 break;
	         case listMgenteCodtte:
	        	 cargarListaMgente00Codtte(listMconca00);
	        	 break;
	         case listMcotes00:
	        	 cargarListaMcotes(listMconca00);
	        	 break;
	         case listMregcg00:
	        	 cargarListaMregcg00();
	        	 break;
	         case listMgenusContribuyente:
	        	 listMgenusContribuyente=obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipo_contribuyente"), listMconca00);
	        	 break;
	         case listMgenus00CatalogoPara:
	        	 listMgenus00CatalogoPara = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_CatalogoPara"),listMconca00);
	        	 break;
	         case listMgenus00Variantes:
	        	 listMgenus00Variantes = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_variantes"),listMconca00);
	        	 break;
	         case listMtiptx00:
	        	 cargarListaMtiptx();
	        	 break;
	         case listMestad00:
	        	 cargarListaMestad00(listMconca00);
	        	 break;
	         case listMgenus00Evento:
	        	 listMgenus00Evento = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_evento"),listMconca00);
	        	 break;
	         case listMacdio00:
		        cargarListaMacdio00(listMconca00);
		        break;
	         case listMaccio00:
	        	 cargarListaMaccio00(listMconca00);
	        	 break;
	         case listMtiptxEstado:
	        	 cargarListaMtiptxEstado(listMconca00);
	        	 break;
	         case listMtiptx00Mestad00:
	        	 cargarListaMtiptx00Mestad00(listMconca00);
	        	 break;
	         case listMestadTxt00:
	        	 cargarListaMestadTxt00(null,listMconca00);
	        	 break;
	         case listMgenusSaludo:
	        	 listMgenusSaludo=obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_saludo"), listMconca00);
	        	 break;
	         case listMgenusAreaLabor:
	        	 listMgenusAreaLabor=obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_areaLabor"), listMconca00);
	        	 break;
	         case listMgenusCargoTercero:
	        	 listMgenusCargoTercero=obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_cargoTercero"), listMconca00);
	        	 break;
	         case listMgenusMoneda:
	        	 listMgenusMoneda= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_monedas"), listMconca00);
		      break;
	         case listMgenusMedioPago:
	        	 listMgenusMedioPago= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_mediopago"), listMconca00);
		      break;
	         case listMgenusRegimen:
	        	 listMgenusRegimen= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_tipo_regimen"), listMconca00);
		      break;
	         case listMgenusIncoterms:
	        	 listMgenusIncoterms= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Incoterm"), listMconca00);
		      break;
	         case listMgenusOrganiVentas:
	        	 listMgenusOrganiVentas= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_OrganiVentas"), listMconca00);
		      break;
	         case listMgenusCanalDistri:
	        	 listMgenusCanalDistri= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_CanalDistri"), listMconca00);
		      break;
	         //case listMgenusUnidadMedidaIng:
	        	// listMgenusUnidadMedidaIng = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_unidadMedida"),listMconca00);
	        	 //break;
	         case listMgenusTipoTransaccion:
	        	 listMgenusTipoTransaccion = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TipoTransaccion"),listMconca00);
	        	 break;
	         case listMgenusEstdPart:
	        	 listMgenusEstdPart= obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_EstdPart"), listMconca00);
		      break;
	         case listMgenusComplejidad:
	        	 listMgenusComplejidad = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Complejidad"),listMconca00);
	        	 break;
	         case listMgenusLegal:
	        	 listMgenusLegal = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_Legal"),listMconca00);
	        	 break;
	         case listMgenusTipoBienServi:
	        	 listMgenusTipoBienServi = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TipoBienServi"),listMconca00);
	        	 break;
	         case listMgenusTarifaRetefDIAN:
	        	 listMgenusTarifaRetefDIAN = obtenerListaMgenus00(Utils.paramsRb.getString("mgenus_codtus_TarifaRetefDIAN"),listMconca00);
	        	 break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Gettes and Setters
	 */

	public List<Mconca00> getListMconca00Pal() {
		return listMconca00Pal;
	}

	public void setListMconca00Pal(List<Mconca00> listMconca00Pal) {
		this.listMconca00Pal = listMconca00Pal;
	}

	public List<Mconca00> getFilteredMconca00Pal() {
		return filteredMconca00Pal;
	}

	public void setFilteredMconca00Pal(List<Mconca00> filteredMconca00Pal) {
		this.filteredMconca00Pal = filteredMconca00Pal;
	}

	public IMconca00Service getImconcaservice() {
		return imconcaservice;
	}

	public void setImconcaservice(IMconca00Service imconcaservice) {
		this.imconcaservice = imconcaservice;
	}

	public IMgenus00Service getMgenus00Service() {
		return mgenus00Service;
	}

	public void setMgenus00Service(IMgenus00Service mgenus00Service) {
		this.mgenus00Service = mgenus00Service;
	}

	public List<Mgenus00> getListMgenusCiudad00() {
		return listMgenusCiudad00;
	}

	public List<Mgenus00> getFilteredMgenusCiudad00() {
		return filteredMgenusCiudad00;
	}

	public List<Mgenus00> getListMgenusPaises() {
		return listMgenusPaises;
	}

	public List<Mgenus00> getFilteredMgenusPais() {
		return filteredMgenusPais;
	}

	public List<Mgenus00> getFilteredMgenusTident00() {
		return filteredMgenusTident00;
	}

	public List<Mgenus00> getListMgenusTident00() {
		return listMgenusTident00;
	}

	public List<Mgenus00> getFilteredMgenusMarcaComercial00() {
		return filteredMgenusMarcaComercial00;
	}

	public List<Mgenus00> getListMgenusMarcaComercial00() {
		return listMgenusMarcaComercial00;
	}

	public List<Mgenus00> getListMgenusLineaProductos() {
		return listMgenusLineaProductos;
	}

	public List<Mgenus00> getFilteredMgenusLineaProductos() {
		return filteredMgenusLineaProductos;
	}

	public List<Mgenus00> getListMgenusSubLineaProductos() {
		return listMgenusSubLineaProductos;
	}

	public List<Mgenus00> getFilteredMgenusSubLineaProductos() {
		return filteredMgenusSubLineaProductos;
	}

	public List<Mgenus00> getListMgenusUnidadMedida() {
		return listMgenusUnidadMedida;
	}

	public List<Mgenus00> getFilteredMgenusUnidadMedida() {
		return filteredMgenusUnidadMedida;
	}

	public List<Mgenus00> getListMgenusCentroCostos() {
		return listMgenusCentroCostos;
	}

	public List<Mgenus00> getFilteredMgenusCentroCostos() {
		return filteredMgenusCentroCostos;
	}

	public List<Mgenus00> getListMgenusClaseDocumentos() {
		return listMgenusClaseDocumentos;
	}

	public List<Mgenus00> getFilteredMgenusClaseDocumentos() {
		return filteredMgenusClaseDocumentos;
	}

	public List<Mgenus00> getListMgenusDocumentos() {
		return listMgenusDocumentos;
	}

	public List<Mgenus00> getFilteredMgenusDocumentos() {
		return filteredMgenusDocumentos;
	}



	public List<Mgenus00> getListMgenusRegion() {
		return listMgenusRegion;
	}

	public List<Mgenus00> getFilteredMgenusRegion() {
		return filteredMgenusRegion;
	}

	public List<Mgenus00> getListMgenusDepartamento() {
		return listMgenusDepartamento;
	}

	public List<Mgenus00> getFilteredMgenusDepartamento() {
		return filteredMgenusDepartamento;
	}

	public List<Mgenus00> getListMgenusSector() {
		return listMgenusSector;
	}

	public List<Mgenus00> getFilteredMgenusSector() {
		return filteredMgenusSector;
	}

	public void setListMgenusCiudad00(List<Mgenus00> listMgenusCiudad00) {
		this.listMgenusCiudad00 = listMgenusCiudad00;
	}

	public void setFilteredMgenusCiudad00(List<Mgenus00> filteredMgenusCiudad00) {
		this.filteredMgenusCiudad00 = filteredMgenusCiudad00;
	}

	public void setListMgenusPaises(List<Mgenus00> listMgenusPaises) {
		this.listMgenusPaises = listMgenusPaises;
	}

	public void setFilteredMgenusPais(List<Mgenus00> filteredMgenusPais) {
		this.filteredMgenusPais = filteredMgenusPais;
	}

	public void setFilteredMgenusTident00(List<Mgenus00> filteredMgenusTident00) {
		this.filteredMgenusTident00 = filteredMgenusTident00;
	}

	public void setListMgenusTident00(List<Mgenus00> listMgenusTident00) {
		this.listMgenusTident00 = listMgenusTident00;
	}

	public void setFilteredMgenusMarcaComercial00(
			List<Mgenus00> filteredMgenusMarcaComercial00) {
		this.filteredMgenusMarcaComercial00 = filteredMgenusMarcaComercial00;
	}

	public void setListMgenusMarcaComercial00(
			List<Mgenus00> listMgenusMarcaComercial00) {
		this.listMgenusMarcaComercial00 = listMgenusMarcaComercial00;
	}

	public void setListMgenusLineaProductos(List<Mgenus00> listMgenusLineaProductos) {
		this.listMgenusLineaProductos = listMgenusLineaProductos;
	}

	public void setFilteredMgenusLineaProductos(
			List<Mgenus00> filteredMgenusLineaProductos) {
		this.filteredMgenusLineaProductos = filteredMgenusLineaProductos;
	}

	public void setListMgenusSubLineaProductos(
			List<Mgenus00> listMgenusSubLineaProductos) {
		this.listMgenusSubLineaProductos = listMgenusSubLineaProductos;
	}

	public void setFilteredMgenusSubLineaProductos(
			List<Mgenus00> filteredMgenusSubLineaProductos) {
		this.filteredMgenusSubLineaProductos = filteredMgenusSubLineaProductos;
	}

	public void setListMgenusUnidadMedida(List<Mgenus00> listMgenusUnidadMedida) {
		this.listMgenusUnidadMedida = listMgenusUnidadMedida;
	}

	public void setFilteredMgenusUnidadMedida(
			List<Mgenus00> filteredMgenusUnidadMedida) {
		this.filteredMgenusUnidadMedida = filteredMgenusUnidadMedida;
	}

	public void setListMgenusCentroCostos(List<Mgenus00> listMgenusCentroCostos) {
		this.listMgenusCentroCostos = listMgenusCentroCostos;
	}

	public void setFilteredMgenusCentroCostos(
			List<Mgenus00> filteredMgenusCentroCostos) {
		this.filteredMgenusCentroCostos = filteredMgenusCentroCostos;
	}

	public void setListMgenusClaseDocumentos(
			List<Mgenus00> listMgenusClaseDocumentos) {
		this.listMgenusClaseDocumentos = listMgenusClaseDocumentos;
	}

	public void setFilteredMgenusClaseDocumentos(
			List<Mgenus00> filteredMgenusClaseDocumentos) {
		this.filteredMgenusClaseDocumentos = filteredMgenusClaseDocumentos;
	}

	public void setListMgenusDocumentos(List<Mgenus00> listMgenusDocumentos) {
		this.listMgenusDocumentos = listMgenusDocumentos;
	}

	public void setFilteredMgenusDocumentos(List<Mgenus00> filteredMgenusDocumentos) {
		this.filteredMgenusDocumentos = filteredMgenusDocumentos;
	}

	public void setListMgenusRegion(List<Mgenus00> listMgenusRegion) {
		this.listMgenusRegion = listMgenusRegion;
	}

	public void setFilteredMgenusRegion(List<Mgenus00> filteredMgenusRegion) {
		this.filteredMgenusRegion = filteredMgenusRegion;
	}

	public void setListMgenusDepartamento(List<Mgenus00> listMgenusDepartamento) {
		this.listMgenusDepartamento = listMgenusDepartamento;
	}

	public void setFilteredMgenusDepartamento(
			List<Mgenus00> filteredMgenusDepartamento) {
		this.filteredMgenusDepartamento = filteredMgenusDepartamento;
	}

	public void setListMgenusSector(List<Mgenus00> listMgenusSector) {
		this.listMgenusSector = listMgenusSector;
	}

	public void setFilteredMgenusSector(List<Mgenus00> filteredMgenusSector) {
		this.filteredMgenusSector = filteredMgenusSector;
	}

	public List<Mtipre00> getFilteredMtipre00() {
		return filteredMtipre00;
	}

	public List<Mtipre00> getListMtipre00() {
		return listMtipre00;
	}

	public void setFilteredMtipre00(List<Mtipre00> filteredMtipre00) {
		this.filteredMtipre00 = filteredMtipre00;
	}

	public void setListMtipre00(List<Mtipre00> listMtipre00) {
		this.listMtipre00 = listMtipre00;
	}

	public IMtipre00Service getMtipre00Service() {
		return mtipre00Service;
	}

	public void setMtipre00Service(IMtipre00Service mtipre00Service) {
		this.mtipre00Service = mtipre00Service;
	}

	public List<Mmesje00> getListMmesje00() {
		return listMmesje00;
	}

	public List<Mmesje00> getFilteredMmesje00() {
		return filteredMmesje00;
	}

	public void setListMmesje00(List<Mmesje00> listMmesje00) {
		this.listMmesje00 = listMmesje00;
	}

	public void setFilteredMmesje00(List<Mmesje00> filteredMmesje00) {
		this.filteredMmesje00 = filteredMmesje00;
	}

	public IMmesje00Service getMmesje00Service() {
		return mmesje00Service;
	}

	public void setMmesje00Service(IMmesje00Service mmesje00Service) {
		this.mmesje00Service = mmesje00Service;
	}

	public List<Mgente00> getListMgenteReplegal() {
		return listMgenteReplegal;
	}

	public void setFilteredMgenteReplegal(List<Mgente00> filteredMgenteReplegal) {
		this.filteredMgenteReplegal = filteredMgenteReplegal;
	}

	public IMgente00Service getMgente00Service() {
		return mgente00Service;
	}

	public void setMgente00Service(IMgente00Service mgente00Service) {
		this.mgente00Service = mgente00Service;
	}

	public List<Mtipre00> getListMtipreTerceros() {
		return listMtipreTerceros;
	}

	public void setListMtipreTerceros(List<Mtipre00> listMtipreTerceros) {
		this.listMtipreTerceros = listMtipreTerceros;
	}

	public List<Mtipre00> getFilteredMtipreTerceros() {
		return filteredMtipreTerceros;
	}

	public void setFilteredMtipreTerceros(List<Mtipre00> filteredMtipreTerceros) {
		this.filteredMtipreTerceros = filteredMtipreTerceros;
	}

	public List<Mgenus00> getListMgenusTipoAsesora() {
		return listMgenusTipoAsesora;
	}

	public void setListMgenusTipoAsesora(List<Mgenus00> listMgenusTipoAsesora) {
		this.listMgenusTipoAsesora = listMgenusTipoAsesora;
	}

	public List<Mgenus00> getFilteredMgenusTipoAsesora() {
		return filteredMgenusTipoAsesora;
	}

	public void setFilteredMgenusTipoAsesora(List<Mgenus00> filteredMgenusTipoAsesora) {
		this.filteredMgenusTipoAsesora = filteredMgenusTipoAsesora;
	}

	public List<Mgenus00> getListMgenusTipoNaturaleza() {
		return listMgenusTipoNaturaleza;
	}

	public void setListMgenusTipoNaturaleza(List<Mgenus00> listMgenusTipoNaturaleza) {
		this.listMgenusTipoNaturaleza = listMgenusTipoNaturaleza;
	}

	public List<Mgenus00> getFilteredMgenusTipoNaturaleza() {
		return filteredMgenusTipoNaturaleza;
	}

	public void setFilteredMgenusTipoNaturaleza(
			List<Mgenus00> filteredMgenusTipoNaturaleza) {
		this.filteredMgenusTipoNaturaleza = filteredMgenusTipoNaturaleza;
	}
	
	public List<Mgenus00> getListMgenusSegmentoAsesora() {
		return listMgenusSegmentoAsesora;
	}

	public void setListMgenusSegmentoAsesora(
			List<Mgenus00> listMgenusSegmentoAsesora) {
		this.listMgenusSegmentoAsesora = listMgenusSegmentoAsesora;
	}

	public List<Mgenus00> getFilteredMgenusSegmentoAsesora() {
		return filteredMgenusSegmentoAsesora;
	}

	public void setFilteredMgenusSegmentoAsesora(
			List<Mgenus00> filteredMgenusSegmentoAsesora) {
		this.filteredMgenusSegmentoAsesora = filteredMgenusSegmentoAsesora;
	}

	public List<Mgenus00> getListMgenusGrupoActividad() {
		return listMgenusGrupoActividad;
	}

	public void setListMgenusGrupoActividad(List<Mgenus00> listMgenusGrupoActividad) {
		this.listMgenusGrupoActividad = listMgenusGrupoActividad;
	}

	public List<Mgenus00> getFilteredMgenusGrupoActividad() {
		return filteredMgenusGrupoActividad;
	}

	public void setFilteredMgenusGrupoActividad(
			List<Mgenus00> filteredMgenusGrupoActividad) {
		this.filteredMgenusGrupoActividad = filteredMgenusGrupoActividad;
	}

	public List<Mgenus00> getListMgenusActividad() {
		return listMgenusActividad;
	}

	public void setListMgenusActividad(List<Mgenus00> listMgenusActividad) {
		this.listMgenusActividad = listMgenusActividad;
	}

	public List<Mgenus00> getFilteredMgenusActividad() {
		return filteredMgenusActividad;
	}

	public void setFilteredMgenusActividad(List<Mgenus00> filteredMgenusActividad) {
		this.filteredMgenusActividad = filteredMgenusActividad;
	}
	
	public List<Mgente00> getListMgenteGerenteAsesora() {
		return listMgenteGerenteAsesora;
	}

	public void setListMgenteGerenteAsesora(List<Mgente00> listMgenteGerenteAsesora) {
		this.listMgenteGerenteAsesora = listMgenteGerenteAsesora;
	}

	public List<Mgente00> getFilteredMgenteGerenteAsesora() {
		return filteredMgenteGerenteAsesora;
	}

	public void setFilteredMgenteGerenteAsesora(
			List<Mgente00> filteredMgenteGerenteAsesora) {
		this.filteredMgenteGerenteAsesora = filteredMgenteGerenteAsesora;
	}

	public List<Mgente00> getFilteredMgenteReplegal() {
		return filteredMgenteReplegal;
	}

	public void setListMgenteReplegal(List<Mgente00> listMgenteReplegal) {
		this.listMgenteReplegal = listMgenteReplegal;
	}

	public List<Mgenus00> getListMgenusCicloAsesora() {
		return listMgenusCicloAsesora;
	}

	public void setListMgenusCicloAsesora(List<Mgenus00> listMgenusCicloAsesora) {
		this.listMgenusCicloAsesora = listMgenusCicloAsesora;
	}

	public List<Mgenus00> getFilteredMgenusCicloAsesora() {
		return filteredMgenusCicloAsesora;
	}

	public void setFilteredMgenusCicloAsesora(
			List<Mgenus00> filteredMgenusCicloAsesora) {
		this.filteredMgenusCicloAsesora = filteredMgenusCicloAsesora;
	}

	public IMidiom00Service getMidiom00Service() {
		return midiom00Service;
	}

	public void setMidiom00Service(IMidiom00Service midiom00Service) {
		this.midiom00Service = midiom00Service;
	}

	public List<Midiom00> getListMidiom00() {
		return listMidiom00;
	}

	public void setListMidiom00(List<Midiom00> listMidiom00) {
		this.listMidiom00 = listMidiom00;
	}

	public List<Midiom00> getFilteredMidiom00() {
		return filteredMidiom00;
	}

	public void setFilteredMidiom00(List<Midiom00> filteredMidiom00) {
		this.filteredMidiom00 = filteredMidiom00;
	}

	public List<Mpropr00> getListMpropr00() {
		return listMpropr00;
	}

	public void setListMpropr00(List<Mpropr00> listMpropr00) {
		this.listMpropr00 = listMpropr00;
	}

	public List<Mpropr00> getFilteredMpropr00() {
		return filteredMpropr00;
	}

	public void setFilteredMpropr00(List<Mpropr00> filteredMpropr00) {
		this.filteredMpropr00 = filteredMpropr00;
	}

	public IMpropr00Service getMpropr00Service() {
		return mpropr00Service;
	}

	public void setMpropr00Service(IMpropr00Service mpropr00Service) {
		this.mpropr00Service = mpropr00Service;
	}

	public List<Mgenus00> getListMgenusTipoCPC() {
		return listMgenusTipoCPC;
	}

	public void setListMgenusTipoCPC(List<Mgenus00> listMgenusTipoCPC) {
		this.listMgenusTipoCPC = listMgenusTipoCPC;
	}

	public List<Mgenus00> getFilteredMgenusTipoCPC() {
		return filteredMgenusTipoCPC;
	}

	public void setFilteredMgenusTipoCPC(List<Mgenus00> filteredMgenusTipoCPC) {
		this.filteredMgenusTipoCPC = filteredMgenusTipoCPC;
	}

	public List<Mgenus00> getListMgenusModalidadCPC() {
		return listMgenusModalidadCPC;
	}

	public void setListMgenusModalidadCPC(List<Mgenus00> listMgenusModalidadCPC) {
		this.listMgenusModalidadCPC = listMgenusModalidadCPC;
	}

	public List<Mgenus00> getFilteredMgenusModalidadCPC() {
		return filteredMgenusModalidadCPC;
	}

	public void setFilteredMgenusModalidadCPC(
			List<Mgenus00> filteredMgenusModalidadCPC) {
		this.filteredMgenusModalidadCPC = filteredMgenusModalidadCPC;
	}

	public IMgencg00Service getMgencg00Service() {
		return mgencg00Service;
	}

	public void setMgencg00Service(IMgencg00Service mgencg00Service) {
		this.mgencg00Service = mgencg00Service;
	}

	public List<Mgencg00> getListMgencg00() {
		return listMgencg00;
	}

	public void setListMgencg00(List<Mgencg00> listMgencg00) {
		this.listMgencg00 = listMgencg00;
	}

	public List<Mgencg00> getFilteredMgencg00() {
		return filteredMgencg00;
	}

	public void setFilteredMgencg00(List<Mgencg00> filteredMgencg00) {
		this.filteredMgencg00 = filteredMgencg00;
	}

	public List<Mgencg00> getListMgencgArbol() {
		return listMgencgArbol;
	}

	public void setListMgencgArbol(List<Mgencg00> listMgencgArbol) {
		this.listMgencgArbol = listMgencgArbol;
	}

	public List<Mgencg00> getFilteredMgencgArbol() {
		return filteredMgencgArbol;
	}

	public void setFilteredMgencgArbol(List<Mgencg00> filteredMgencgArbol) {
		this.filteredMgencgArbol = filteredMgencgArbol;
	}

	public List<Mgente00> getListMgenteAsesora() {
		return listMgenteAsesora;
	}

	public void setListMgenteAsesora(List<Mgente00> listMgenteAsesora) {
		this.listMgenteAsesora = listMgenteAsesora;
	}

	public List<Mgente00> getFilteredMgenteAsesora() {
		return filteredMgenteAsesora;
	}

	public void setFilteredMgenteAsesora(List<Mgente00> filteredMgenteAsesora) {
		this.filteredMgenteAsesora = filteredMgenteAsesora;
	}

	public List<Mgencg00> getListMgencgCodTransaccion() {
		return listMgencgCodTransaccion;
	}

	public void setListMgencgCodTransaccion(List<Mgencg00> listMgencgCodTransaccion) {
		this.listMgencgCodTransaccion = listMgencgCodTransaccion;
	}

	public List<Mgencg00> getFilteredMgencgCodTransaccion() {
		return filteredMgencgCodTransaccion;
	}

	public void setFilteredMgencgCodTransaccion(
			List<Mgencg00> filteredMgencgCodTransaccion) {
		this.filteredMgencgCodTransaccion = filteredMgencgCodTransaccion;
	}

	public List<Mtipre00> getListMtipreGConfi() {
		return listMtipreGConfi;
	}

	public void setListMtipreGConfi(List<Mtipre00> listMtipreGConfi) {
		this.listMtipreGConfi = listMtipreGConfi;
	}

	public List<Mtipre00> getFilteredMtipreGConfi() {
		return filteredMtipreGConfi;
	}

	public void setFilteredMtipreGConfi(List<Mtipre00> filteredMtipreGConfi) {
		this.filteredMtipreGConfi = filteredMtipreGConfi;
	}

	public List<Mgenus00> getListMgenus00CatalogoPara() {
		return listMgenus00CatalogoPara;
	}

	public void setListMgenus00CatalogoPara(List<Mgenus00> listMgenus00CatalogoPara) {
		this.listMgenus00CatalogoPara = listMgenus00CatalogoPara;
	}

	public List<Mgenus00> getFilteredMgenus00CatalogoPara() {
		return filteredMgenus00CatalogoPara;
	}

	public void setFilteredMgenus00CatalogoPara(
			List<Mgenus00> filteredMgenus00CatalogoPara) {
		this.filteredMgenus00CatalogoPara = filteredMgenus00CatalogoPara;
	}

	public List<Mgenus00> getListMgenus00Variantes() {
		return listMgenus00Variantes;
	}

	public void setListMgenus00Variantes(List<Mgenus00> listMgenus00Variantes) {
		this.listMgenus00Variantes = listMgenus00Variantes;
	}

	public List<Mgenus00> getFilteredMgenus00Variantes() {
		return filteredMgenus00Variantes;
	}

	public void setFilteredMgenus00Variantes(
			List<Mgenus00> filteredMgenus00Variantes) {
		this.filteredMgenus00Variantes = filteredMgenus00Variantes;
	}

	public List<Mtiptx00> getListMtiptx00() {
		return listMtiptx00;
	}

	public void setListMtiptx00(List<Mtiptx00> listMtiptx00) {
		this.listMtiptx00 = listMtiptx00;
	}

	public List<Mtiptx00> getFilteredMtiptx00() {
		return filteredMtiptx00;
	}

	public void setFilteredMtiptx00(List<Mtiptx00> filteredMtiptx00) {
		this.filteredMtiptx00 = filteredMtiptx00;
	}

	public IMtiptx00Service getImtiptx00Service() {
		return imtiptx00Service;
	}

	public void setImtiptx00Service(IMtiptx00Service imtiptx00Service) {
		this.imtiptx00Service = imtiptx00Service;
	}

	public List<Mestad00> getListMestad00() {
		return listMestad00;
	}

	public void setListMestad00(List<Mestad00> listMestad00) {
		this.listMestad00 = listMestad00;
	}

	public List<Mestad00> getFilteredMestad00() {
		return filteredMestad00;
	}

	public void setFilteredMestad00(List<Mestad00> filteredMestad00) {
		this.filteredMestad00 = filteredMestad00;
	}

	public IMestad00Service getMestad00Service() {
		return mestad00Service;
	}

	public void setMestad00Service(IMestad00Service mestad00Service) {
		this.mestad00Service = mestad00Service;
	}

	public String getTextoBienvenida() {
		return textoBienvenida;
	}

	public void setTextoBienvenida(String textoBienvenida) {
		this.textoBienvenida = textoBienvenida;
	}

	public List<Mgenus00> getListMgenus00Evento() {
		return listMgenus00Evento;
	}

	public void setListMgenus00Evento(List<Mgenus00> listMgenus00Evento) {
		this.listMgenus00Evento = listMgenus00Evento;
	}

	public List<Mgenus00> getFilteredMgenus00Evento() {
		return filteredMgenus00Evento;
	}

	public void setFilteredMgenus00Evento(List<Mgenus00> filteredMgenus00Evento) {
		this.filteredMgenus00Evento = filteredMgenus00Evento;
	}

	
	public List<Macdio00> getListMacdio00() {
		return listMacdio00;
	}

	public void setListMacdio00(List<Macdio00> listMacdio00) {
		this.listMacdio00 = listMacdio00;
	}

	public List<Macdio00> getFilteredMacdio00() {
		return filteredMacdio00;
	}

	public void setFilteredMacdio00(List<Macdio00> filteredMacdio00) {
		this.filteredMacdio00 = filteredMacdio00;
	}

	public List<Maccio00> getListMaccio00() {
		return listMaccio00;
	}

	public void setListMaccio00(List<Maccio00> listMaccio00) {
		this.listMaccio00 = listMaccio00;
	}

	public List<Maccio00> getFilteredMaccio00() {
		return filteredMaccio00;
	}

	public void setFilteredMaccio00(List<Maccio00> filteredMaccio00) {
		this.filteredMaccio00 = filteredMaccio00;
	}

	public IMaccio00Service getMaccio00Service() {
		return maccio00Service;
	}

	
	public void setMaccio00Service(IMaccio00Service maccio00Service) {
		this.maccio00Service = maccio00Service;
	}

	public IMacdio00Service getMacdio00Service() {
		return macdio00Service;
	}

	public void setMacdio00Service(IMacdio00Service macdio00Service) {
		this.macdio00Service = macdio00Service;
	}

	public List<Mtiptx00> getListMtiptxEstado() {
		return listMtiptxEstado;
	}

	public void setListMtiptxEstado(List<Mtiptx00> listMtiptxEstado) {
		this.listMtiptxEstado = listMtiptxEstado;
	}

	public List<Mtiptx00> getFilteredMtiptxEstado() {
		return filteredMtiptxEstado;
	}

	public void setFilteredMtiptxEstado(List<Mtiptx00> filteredMtiptxEstado) {
		this.filteredMtiptxEstado = filteredMtiptxEstado;
	}

	public List<Mestad00> getListMestadTxt00() {
		return listMestadTxt00;
	}

	public void setListMestadTxt00(List<Mestad00> listMestadTxt00) {
		this.listMestadTxt00 = listMestadTxt00;
	}

	public List<Mestad00> getFilteredMestadTxt00() {
		return filteredMestadTxt00;
	}

	public void setFilteredMestadTxt00(List<Mestad00> filteredMestadTxt00) {
		this.filteredMestadTxt00 = filteredMestadTxt00;
	}

	public List<Mtabla00> getListMtabla00() {
		return listMtabla00;
	}

	public void setListMtabla00(List<Mtabla00> listMtabla00) {
		this.listMtabla00 = listMtabla00;
	}

	public List<Mtabla00> getFilteredMtabla00() {
		return filteredMtabla00;
	}

	public void setFilteredMtabla00(List<Mtabla00> filteredMtabla00) {
		this.filteredMtabla00 = filteredMtabla00;
	}

	public IMtabla00Service getMtabla00Service() {
		return mtabla00Service;
	}

	public void setMtabla00Service(IMtabla00Service mtabla00Service) {
		this.mtabla00Service = mtabla00Service;
	}

	public List<Mgencg00> getListMgencgCompania() {
		return listMgencgCompania;
	}

	public void setListMgencgCompania(List<Mgencg00> listMgencgCompania) {
		this.listMgencgCompania = listMgencgCompania;
	}

	public List<Mgencg00> getFilteredMgencgCompania() {
		return filteredMgencgCompania;
	}

	public void setFilteredMgencgCompania(List<Mgencg00> filteredMgencgCompania) {
		this.filteredMgencgCompania = filteredMgencgCompania;
	}

	public List<Mgenus00> getListMgenusMoneda() {
		return listMgenusMoneda;
	}

	public void setListMgenusMoneda(List<Mgenus00> listMgenusMoneda) {
		this.listMgenusMoneda = listMgenusMoneda;
	}

	public List<Mgenus00> getFilteredMgenusMoneda() {
		return filteredMgenusMoneda;
	}

	public void setFilteredMgenusMoneda(List<Mgenus00> filteredMgenusMoneda) {
		this.filteredMgenusMoneda = filteredMgenusMoneda;
	}

	public List<Mgenus00> getListMgenusMedioPago() {
		return listMgenusMedioPago;
	}

	public void setListMgenusMedioPago(List<Mgenus00> listMgenusMedioPago) {
		this.listMgenusMedioPago = listMgenusMedioPago;
	}

	public List<Mgenus00> getFilteredMgenusMedioPago() {
		return filteredMgenusMedioPago;
	}

	public void setFilteredMgenusMedioPago(List<Mgenus00> filteredMgenusMedioPago) {
		this.filteredMgenusMedioPago = filteredMgenusMedioPago;
	}
	
	public List<Mgente00> getListMgenteCliente() {
		return listMgenteCliente;
	}

	public void setListMgenteCliente(List<Mgente00> listMgenteCliente) {
		this.listMgenteCliente = listMgenteCliente;
	}

	public List<Mgente00> getFilteredMgenteCliente() {
		return filteredMgenteCliente;
	}

	public void setFilteredMgenteCliente(List<Mgente00> filteredMgenteCliente) {
		this.filteredMgenteCliente = filteredMgenteCliente;
	}

	public List<Mgenus00> getListMgenusRegimen() {
		return listMgenusRegimen;
	}

	public void setListMgenusRegimen(List<Mgenus00> listMgenusRegimen) {
		this.listMgenusRegimen = listMgenusRegimen;
	}

	public List<Mgenus00> getFilteredMgenusRegimen() {
		return filteredMgenusRegimen;
	}

	public void setFilteredMgenusRegimen(List<Mgenus00> filteredMgenusRegimen) {
		this.filteredMgenusRegimen = filteredMgenusRegimen;
	}

	public List<Mgenus00> getListMgenusSaludo() {
		return listMgenusSaludo;
	}

	public void setListMgenusSaludo(List<Mgenus00> listMgenusSaludo) {
		this.listMgenusSaludo = listMgenusSaludo;
	}

	public List<Mgenus00> getFilteredMgenusSaludo() {
		return filteredMgenusSaludo;
	}

	public void setFilteredMgenusSaludo(List<Mgenus00> filteredMgenusSaludo) {
		this.filteredMgenusSaludo = filteredMgenusSaludo;
	}

	public List<Mgenus00> getListMgenusAreaLabor() {
		return listMgenusAreaLabor;
	}

	public void setListMgenusAreaLabor(List<Mgenus00> listMgenusAreaLabor) {
		this.listMgenusAreaLabor = listMgenusAreaLabor;
	}

	public List<Mgenus00> getFilteredMgenusAreaLabor() {
		return filteredMgenusAreaLabor;
	}

	public void setFilteredMgenusAreaLabor(List<Mgenus00> filteredMgenusAreaLabor) {
		this.filteredMgenusAreaLabor = filteredMgenusAreaLabor;
	}

	public List<Mgenus00> getListMgenusCargoTercero() {
		return listMgenusCargoTercero;
	}

	public void setListMgenusCargoTercero(List<Mgenus00> listMgenusCargoTercero) {
		this.listMgenusCargoTercero = listMgenusCargoTercero;
	}

	public List<Mgenus00> getFilteredMgenusCargoTercero() {
		return filteredMgenusCargoTercero;
	}

	public void setFilteredMgenusCargoTercero(
			List<Mgenus00> filteredMgenusCargoTercero) {
		this.filteredMgenusCargoTercero = filteredMgenusCargoTercero;
	}
	
	public SeControlFactura getSeControlFactura() {
		return seControlFactura;
	}

	public void setSeControlFactura(SeControlFactura seControlFactura) {
		this.seControlFactura = seControlFactura;
	}

	public List<Mgenus00> getListMgenusIncoterms() {
		return listMgenusIncoterms;
	}

	public void setListMgenusIncoterms(List<Mgenus00> listMgenusIncoterms) {
		this.listMgenusIncoterms = listMgenusIncoterms;
	}

	public List<Mgenus00> getFilteredMgenusIncoterms() {
		return filteredMgenusIncoterms;
	}

	public void setFilteredMgenusIncoterms(List<Mgenus00> filteredMgenusIncoterms) {
		this.filteredMgenusIncoterms = filteredMgenusIncoterms;
	}

	public List<Mgenus00> getListMgenusOrganiVentas() {
		return listMgenusOrganiVentas;
	}

	public void setListMgenusOrganiVentas(List<Mgenus00> listMgenusOrganiVentas) {
		this.listMgenusOrganiVentas = listMgenusOrganiVentas;
	}

	public List<Mgenus00> getFilteredMgenusOrganiVentas() {
		return filteredMgenusOrganiVentas;
	}

	public void setFilteredMgenusOrganiVentas(
			List<Mgenus00> filteredMgenusOrganiVentas) {
		this.filteredMgenusOrganiVentas = filteredMgenusOrganiVentas;
	}

	public List<Mgenus00> getListMgenusCanalDistri() {
		return listMgenusCanalDistri;
	}

	public void setListMgenusCanalDistri(List<Mgenus00> listMgenusCanalDistri) {
		this.listMgenusCanalDistri = listMgenusCanalDistri;
	}

	public List<Mgenus00> getFilteredMgenusCanalDistri() {
		return filteredMgenusCanalDistri;
	}

	public void setFilteredMgenusCanalDistri(
			List<Mgenus00> filteredMgenusCanalDistri) {
		this.filteredMgenusCanalDistri = filteredMgenusCanalDistri;
	}

	public IMvaria00Service getMvaria00Service() {
		return mvaria00Service;
	}

	public void setMvaria00Service(IMvaria00Service mvaria00Service) {
		this.mvaria00Service = mvaria00Service;
	}

	public List<Mvaria00> getListMvaria00Principal() {
		return listMvaria00Principal;
	}

	public void setListMvaria00Principal(List<Mvaria00> listMvaria00Principal) {
		this.listMvaria00Principal = listMvaria00Principal;
	}

	public List<Mvaria00> getFilteredMvaria00Principal() {
		return filteredMvaria00Principal;
	}

	public void setFilteredMvaria00Principal(
			List<Mvaria00> filteredMvaria00Principal) {
		this.filteredMvaria00Principal = filteredMvaria00Principal;
	}

	public List<Mvaria00> getListMvaria00() {
		return listMvaria00;
	}

	public void setListMvaria00(List<Mvaria00> listMvaria00) {
		this.listMvaria00 = listMvaria00;
	}

	public List<Mvaria00> getFilteredMvaria00() {
		return filteredMvaria00;
	}

	public void setFilteredMvaria00(List<Mvaria00> filteredMvaria00) {
		this.filteredMvaria00 = filteredMvaria00;
	}

	public List<Mvaria00> getListMvaria00Secundaria() {
		return listMvaria00Secundaria;
	}

	public void setListMvaria00Secundaria(List<Mvaria00> listMvaria00Secundaria) {
		this.listMvaria00Secundaria = listMvaria00Secundaria;
	}

	public List<Mvaria00> getFilteredMvaria00Secundaria() {
		return filteredMvaria00Secundaria;
	}

	public void setFilteredMvaria00Secundaria(
			List<Mvaria00> filteredMvaria00Secundaria) {
		this.filteredMvaria00Secundaria = filteredMvaria00Secundaria;
	}
	
	public List<Mgenus00> getListMgenusUnidadMedidaIng() {
		return listMgenusUnidadMedidaIng;
	}

	public void setListMgenusUnidadMedidaIng(
			List<Mgenus00> listMgenusUnidadMedidaIng) {
		this.listMgenusUnidadMedidaIng = listMgenusUnidadMedidaIng;
	}

	public List<Mgenus00> getFilteredMgenusUnidadMedidaIng() {
		return filteredMgenusUnidadMedidaIng;
	}

	public void setFilteredMgenusUnidadMedidaIng(
			List<Mgenus00> filteredMgenusUnidadMedidaIng) {
		this.filteredMgenusUnidadMedidaIng = filteredMgenusUnidadMedidaIng;
	}

	public List<Mgenus00> getListMgenusTipoTransaccion() {
		return listMgenusTipoTransaccion;
	}

	public void setListMgenusTipoTransaccion(
			List<Mgenus00> listMgenusTipoTransaccion) {
		this.listMgenusTipoTransaccion = listMgenusTipoTransaccion;
	}

	public List<Mgenus00> getFilteredMgenusTipoTransaccion() {
		return filteredMgenusTipoTransaccion;
	}

	public void setFilteredMgenusTipoTransaccion(
			List<Mgenus00> filteredMgenusTipoTransaccion) {
		this.filteredMgenusTipoTransaccion = filteredMgenusTipoTransaccion;
	}

	public List<Mfunfu00> getListMfunfu() {
		return listMfunfu;
	}

	public void setListMfunfu(List<Mfunfu00> listMfunfu) {
		this.listMfunfu = listMfunfu;
	}

	public List<Mfunfu00> getFilteredMfunfu() {
		return filteredMfunfu;
	}

	public void setFilteredMfunfu(List<Mfunfu00> filteredMfunfu) {
		this.filteredMfunfu = filteredMfunfu;
	}

	public IMfunfu00Service getMfunfu00Service() {
		return mfunfu00Service;
	}

	public void setMfunfu00Service(IMfunfu00Service mfunfu00Service) {
		this.mfunfu00Service = mfunfu00Service;
	}

	public List<Mgenus00> getListMgenusEstdPart() {
		return listMgenusEstdPart;
	}

	public void setListMgenusEstdPart(List<Mgenus00> listMgenusEstdPart) {
		this.listMgenusEstdPart = listMgenusEstdPart;
	}

	public List<Mgenus00> getFilteredMgenusEstdPart() {
		return filteredMgenusEstdPart;
	}

	public void setFilteredMgenusEstdPart(List<Mgenus00> filteredMgenusEstdPart) {
		this.filteredMgenusEstdPart = filteredMgenusEstdPart;
	}

	public List<Mgenus00> getListMgenusComplejidad() {
		return listMgenusComplejidad;
	}

	public void setListMgenusComplejidad(List<Mgenus00> listMgenusComplejidad) {
		this.listMgenusComplejidad = listMgenusComplejidad;
	}

	public List<Mgenus00> getFilteredMgenusComplejidad() {
		return filteredMgenusComplejidad;
	}

	public void setFilteredMgenusComplejidad(
			List<Mgenus00> filteredMgenusComplejidad) {
		this.filteredMgenusComplejidad = filteredMgenusComplejidad;
	}

	public List<Mgenus00> getListMgenusLegal() {
		return listMgenusLegal;
	}

	public void setListMgenusLegal(List<Mgenus00> listMgenusLegal) {
		this.listMgenusLegal = listMgenusLegal;
	}

	public List<Mgenus00> getFilteredMgenusLegal() {
		return filteredMgenusLegal;
	}

	public void setFilteredMgenusLegal(List<Mgenus00> filteredMgenusLegal) {
		this.filteredMgenusLegal = filteredMgenusLegal;
	}

	public List<Mgenus00> getListMgenusTipoBienServi() {
		return listMgenusTipoBienServi;
	}

	public void setListMgenusTipoBienServi(List<Mgenus00> listMgenusTipoBienServi) {
		this.listMgenusTipoBienServi = listMgenusTipoBienServi;
	}

	public List<Mgenus00> getfilteredMgenusTipoBienServi() {
		return filteredMgenusTipoBienServi;
	}

	public void setfilteredMgenusTipoBienServi(
			List<Mgenus00> filteredMgenusTipoBienServi) {
		this.filteredMgenusTipoBienServi = filteredMgenusTipoBienServi;
	}

	public List<Mgenus00> getListMgenusTarifaRetefDIAN() {
		return listMgenusTarifaRetefDIAN;
	}

	public void setListMgenusTarifaRetefDIAN(
			List<Mgenus00> listMgenusTarifaRetefDIAN) {
		this.listMgenusTarifaRetefDIAN = listMgenusTarifaRetefDIAN;
	}

	public List<Mgenus00> getFilteredMgenusTarifaRetefDIAN() {
		return filteredMgenusTarifaRetefDIAN;
	}

	public void setFilteredMgenusTarifaRetefDIAN(
			List<Mgenus00> filteredMgenusTarifaRetefDIAN) {
		this.filteredMgenusTarifaRetefDIAN = filteredMgenusTarifaRetefDIAN;
	}

	public List<Mregcg00> getListMregcg00() {
		return listMregcg00;
	}

	public void setListMregcg00(List<Mregcg00> listMregcg00) {
		this.listMregcg00 = listMregcg00;
	}

	public List<Mregcg00> getFilteredMregcg00() {
		return filteredMregcg00;
	}

	public void setFilteredMregcg00(List<Mregcg00> filteredMregcg00) {
		this.filteredMregcg00 = filteredMregcg00;
	}

	public IMregcg00Service getMregcg00Service() {
		return mregcg00Service;
	}

	public void setMregcg00Service(IMregcg00Service mregcg00Service) {
		this.mregcg00Service = mregcg00Service;
	}

	public List<Mgenus00> getFilteredMgenusTipoBienServi() {
		return filteredMgenusTipoBienServi;
	}

	public void setFilteredMgenusTipoBienServi(
			List<Mgenus00> filteredMgenusTipoBienServi) {
		this.filteredMgenusTipoBienServi = filteredMgenusTipoBienServi;
	}

	public List<Mgenus00> getListMgenusContribuyente() {
		return listMgenusContribuyente;
	}

	public void setListMgenusContribuyente(List<Mgenus00> listMgenusContribuyente) {
		this.listMgenusContribuyente = listMgenusContribuyente;
	}

	public List<Mgenus00> getFilteredMgenusContribuyente() {
		return filteredMgenusContribuyente;
	}

	public void setFilteredMgenusContribuyente(
			List<Mgenus00> filteredMgenusContribuyente) {
		this.filteredMgenusContribuyente = filteredMgenusContribuyente;
	}

	public List<Mgencg00> getListMgencg00Evento() {
		return listMgencg00Evento;
	}

	public void setListMgencg00Evento(List<Mgencg00> listMgencg00Evento) {
		this.listMgencg00Evento = listMgencg00Evento;
	}

	public List<Mgencg00> getFilteredMgencg00Evento() {
		return filteredMgencg00Evento;
	}

	public void setFilteredMgencg00Evento(List<Mgencg00> filteredMgencg00Evento) {
		this.filteredMgencg00Evento = filteredMgencg00Evento;
	}

	public List<Mcotes00> getListMcotes00() {
		return listMcotes00;
	}

	public void setListMcotes00(List<Mcotes00> listMcotes00) {
		this.listMcotes00 = listMcotes00;
	}

	public List<Mcotes00> getFilteredMcotes00() {
		return filteredMcotes00;
	}

	public void setFilteredMcotes00(List<Mcotes00> filteredMcotes00) {
		this.filteredMcotes00 = filteredMcotes00;
	}

	public IMcotes00Service getMcotes00Service() {
		return mcotes00Service;
	}

	public void setMcotes00Service(IMcotes00Service mcotes00Service) {
		this.mcotes00Service = mcotes00Service;
	}

	public List<Mgenus00> getListMgenus00MonedaCot() {
		return listMgenus00MonedaCot;
	}

	public void setListMgenus00MonedaCot(List<Mgenus00> listMgenus00MonedaCot) {
		this.listMgenus00MonedaCot = listMgenus00MonedaCot;
	}

	public List<Mgenus00> getFilteredMgenus00MonedaCot() {
		return filteredMgenus00MonedaCot;
	}

	public void setFilteredMgenus00MonedaCot(
			List<Mgenus00> filteredMgenus00MonedaCot) {
		this.filteredMgenus00MonedaCot = filteredMgenus00MonedaCot;
	}

	public List<Mgenus00> getListMgenus00UnidadMedidaCot() {
		return listMgenus00UnidadMedidaCot;
	}

	public void setListMgenus00UnidadMedidaCot(
			List<Mgenus00> listMgenus00UnidadMedidaCot) {
		this.listMgenus00UnidadMedidaCot = listMgenus00UnidadMedidaCot;
	}

	public List<Mgenus00> getFilteredMgenus00UnidadMedidaCot() {
		return filteredMgenus00UnidadMedidaCot;
	}

	public void setFilteredMgenus00UnidadMedidaCot(
			List<Mgenus00> filteredMgenus00UnidadMedidaCot) {
		this.filteredMgenus00UnidadMedidaCot = filteredMgenus00UnidadMedidaCot;
	}

	public List<Tqdpqp00> getListTqdpqp00Aux() {
		return listTqdpqp00Aux;
	}

	public void setListTqdpqp00Aux(List<Tqdpqp00> listTqdpqp00Aux) {
		this.listTqdpqp00Aux = listTqdpqp00Aux;
	}

	public List<Tqdpqp00> getFilteredTqdpqp00() {
		return filteredTqdpqp00;
	}

	public void setFilteredTqdpqp00(List<Tqdpqp00> filteredTqdpqp00) {
		this.filteredTqdpqp00 = filteredTqdpqp00;
	}

	public ITqdpqp00Service getTqdpqp00Service() {
		return tqdpqp00Service;
	}

	public void setTqdpqp00Service(ITqdpqp00Service tqdpqp00Service) {
		this.tqdpqp00Service = tqdpqp00Service;
	}

	public ITqdrqr00Service getTqdrqr00Service() {
		return tqdrqr00Service;
	}

	public void setTqdrqr00Service(ITqdrqr00Service tqdrqr00Service) {
		this.tqdrqr00Service = tqdrqr00Service;
	}

	public Tqdpqp00 getTqdpqp00() {
		return tqdpqp00;
	}

	public void setTqdpqp00(Tqdpqp00 tqdpqp00) {
		this.tqdpqp00 = tqdpqp00;
	}

	public Tqdrqr00 getTqdrqr00() {
		return tqdrqr00;
	}

	public void setTqdrqr00(Tqdrqr00 tqdrqr00) {
		this.tqdrqr00 = tqdrqr00;
	}

	public List<Tqdpqp00> getListTqdpqp00() {
		return listTqdpqp00;
	}

	public void setListTqdpqp00(List<Tqdpqp00> listTqdpqp00) {
		this.listTqdpqp00 = listTqdpqp00;
	}

	public List<Tqdrqr00> getListTqdrqr00() {
		return listTqdrqr00;
	}

	public void setListTqdrqr00(List<Tqdrqr00> listTqdrqr00) {
		this.listTqdrqr00 = listTqdrqr00;
	}

	public List<Mgente00> getListMgenteCodtte() {
		return listMgenteCodtte;
	}

	public void setListMgenteCodtte(List<Mgente00> listMgenteCodtte) {
		this.listMgenteCodtte = listMgenteCodtte;
	}

	public List<Mgente00> getFilteredMgenteCodtte() {
		return filteredMgenteCodtte;
	}

	public void setFilteredMgenteCodtte(List<Mgente00> filteredMgenteCodtte) {
		this.filteredMgenteCodtte = filteredMgenteCodtte;
	}

	public List<Mgenus00> getListMgenusBarrioSector() {
		return listMgenusBarrioSector;
	}

	public void setListMgenusBarrioSector(List<Mgenus00> listMgenusBarrioSector) {
		this.listMgenusBarrioSector = listMgenusBarrioSector;
	}

	public List<Mgenus00> getFilteredMgenusBarrioSector() {
		return filteredMgenusBarrioSector;
	}

	public void setFilteredMgenusBarrioSector(
			List<Mgenus00> filteredMgenusBarrioSector) {
		this.filteredMgenusBarrioSector = filteredMgenusBarrioSector;
	}	
	
}