package com.tlm.faelec.service.maestros;


import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMcotes00Service {
	
	public List<Mcotes00> listMcotes00ByCriteria(Mcotes00 mcotes00, List<String> listMusuco00);
	public List<Mcotes00> listMcotes00Criteria(Mcotes00 mcotes00, List<String> listMusuco00);
	public void save(Mcotes00 mcotes00, Taudit00 taudit00) throws SQLException;
	public void removeMcotes00 (Mcotes00 mcotes00, Taudit00 taudit00);

}
