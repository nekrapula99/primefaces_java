package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mrerer00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMrerer00Service {
	
	public List<Mrerer00> listMrerer00ByCriteria(Mrerer00 mrerer00, List<String> listMusuco00);
	public void save(Mrerer00 mrerer00, Taudit00 taudit00) throws SQLException;
	public void removeMrerer00 (Mrerer00 mrerer00, Taudit00 taudit00);
	public boolean  obtenerRegistroValido(Integer idx1, Integer idx2);

}
