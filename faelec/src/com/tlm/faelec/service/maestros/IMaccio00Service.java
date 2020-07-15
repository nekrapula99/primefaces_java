package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;


import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMaccio00Service {
	
	public List<Maccio00> listMaccio00ByCriteria(Maccio00 maccio00, List<String> listMusuco00);
	public void save(Maccio00 maccio00, Taudit00 taudit00) throws SQLException;
	public void removeMaccio00(Maccio00 maccio00,Taudit00 taudit00);
	public List<Maccio00> getListMaccio00(Maccio00 maccio00, List<String> listMusuco00);
	public Maccio00 getMaccio00ByTqigqg00 (Maccio00 maccio00, String tabla);
}