package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMdespr00Service {

	
	//Mconca00
	public List<Mdespr00> listMdespr00ByCriteria(Mdespr00 mdespr00);
	public void save(Mdespr00 mdespr00, Taudit00 taudit00) throws SQLException;
	public void removeMdespr00(Mdespr00 mdespr00,Taudit00 taudit00);

}