package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Terfed00;

public interface ITerfed00Service {
	
	public List<Terfed00> listTerfed00ByCriteria(Terfed00 terfed00,List<String> listMusuco00);
	public void save(Terfed00 terfed00, Taudit00 taudit00) throws SQLException;
	public void removeTerfed00 (Terfed00 terfed00, Taudit00 taudit00);

}
