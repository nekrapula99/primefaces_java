package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMfunfu00Service {
	
	public List<Mfunfu00> listMfunfu00ByCriteria(Mfunfu00 mfunfu00, List<String> listMusuco00);
	public List<Mfunfu00> listMfunfu00Criteria(Mfunfu00 mfunfu00, List<String> listMusuco00);
	public void save(Mfunfu00 mfunfu00, Taudit00 taudit00) throws SQLException;
	public void removeMfunfu00 (Mfunfu00 mfunfu00, Taudit00 taudit00);

}
