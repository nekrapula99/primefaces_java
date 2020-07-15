package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMacdio00Service {
	
	public List<Macdio00> listMacdio00ByCriteria(List<String> listMusuco00);
	public void save(Macdio00 macdio00, Taudit00 taudit00) throws SQLException;
	public void removeMacdio00 (Macdio00 macdio00, Taudit00 taudit00);
	public Macdio00 getMacdio00ByTqigqg00 (Macdio00 macdio00, String notdio);
	

}
