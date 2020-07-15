package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mrafra00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMrafra00Service {
	
	public List<Mrafra00> listMrafra00ByCriteria(Mrafra00 mrafra00, List<String> listMusuco00);
	public void save(Mrafra00 mrafra00, Taudit00 taudit00) throws SQLException;
	public void removeMrafra00 (Mrafra00 mrafra00, Taudit00 taudit00);
	public List<Mrafra00> listMrafra00(Mrafra00 mrafra00, List<String> companiasUsu);
	public Mrafra00 getRegistroRango(int idTransaccion, Date fechaHoy);

}
