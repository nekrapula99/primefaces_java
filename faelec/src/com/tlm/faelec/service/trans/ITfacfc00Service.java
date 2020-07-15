package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tfacfc00;

public interface ITfacfc00Service {
	
	public List<Tfacfc00> listTfacfc00ByCriteria(Tfacfc00 tfacfc00,List<String> listMusuco00);
	public void save(Tfacfc00 tfacfc00, Taudit00 taudit00) throws SQLException;
	public void removeTfacfc00 (Tfacfc00 tfacfc00, Taudit00 taudit00);
	public Tfacfc00 getTfacfc00ByTqdfqf00 (Tfacfc00 tfacfc00);
}

