package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mswfsw00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMswfsw00Service {
	
	public List<Mswfsw00> listMswfsw00ByCriteria(Mswfsw00 mswfsw00, List<String> listMusuco00);
	public void save(Mswfsw00 mswfsw00, Taudit00 taudit00) throws SQLException;
	public void removeMswfsw00 (Mswfsw00 mswfsw00, Taudit00 taudit00);

}
