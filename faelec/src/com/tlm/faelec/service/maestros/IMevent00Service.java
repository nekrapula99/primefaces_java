package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mevent00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMevent00Service {

	public List<Mevent00> listMevent00ByCriteria(Mevent00 mevent00,List<String> listMusuco00);
	public void save(Mevent00 mevent00, Taudit00 taudit00) throws SQLException;
	public void removeMevent00(Mevent00 mevent00,Taudit00 taudit00);
	
}
