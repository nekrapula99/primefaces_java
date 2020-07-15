package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mregcg00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMregcg00Service {
	
	public List<Mregcg00> listMregcg00ByCriteria(Mregcg00 mregcg00, List<String> listMusuco00);
	public List<Mregcg00> listMregcg00Criteria(Mregcg00 mregcg00, List<String> listMusuco00);
	public void save(Mregcg00 mregcg00, Taudit00 taudit00) throws SQLException;
	public void removeMregcg00 (Mregcg00 mregcg00, Taudit00 taudit00);

}
