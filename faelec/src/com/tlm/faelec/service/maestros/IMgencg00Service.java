package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Taudit00;


public interface IMgencg00Service {
	
	public List<Mgencg00> listMgencg00ByCriteria(Mgencg00 mgencg00,List<String> listMusuco00);
	public void removeMgencg00(Mgencg00 mgencg00, Taudit00 taudit00);
	public void save(Mgencg00 mgencg00, Taudit00 taudit00) throws SQLException;
	public Mgencg00 Mgencg00ByCodicg (String codicg);
	public Mgencg00 Mgencg00ByCodicgEv(String codicg, String codtcg);

}
