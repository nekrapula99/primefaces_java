package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mparca00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMparca00Service {

	public List<Mparca00> listMparca00ByCriteria(Mparca00 mparca00, List<String> listMusuco00);
	public void save(Mparca00 mparca00, Taudit00 taudit00) throws SQLException;
	public void removeMpropr00(Mparca00 mparca00,Taudit00 taudit00);
}
