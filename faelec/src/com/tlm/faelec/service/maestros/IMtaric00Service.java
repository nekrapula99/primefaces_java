package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mtaric00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMtaric00Service {
	
	public List<Mtaric00> listMtaric00ByCriteria(Mtaric00 mtaric00, List<String> listMusuco00);
	public void save(Mtaric00 mtaric00, Taudit00 taudit00) throws SQLException;
	public void removeMtaric00 (Mtaric00 mtaric00, Taudit00 taudit00);

}
