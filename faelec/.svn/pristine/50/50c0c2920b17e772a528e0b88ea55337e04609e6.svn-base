package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;


import com.tlm.faelecEntities.model.entities.Mteste00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMteste00Service {
	
	//Mteste00
	public List<Mteste00> listMteste00ByCriteria(Mteste00 mteste00, List<String> listMusuco00);
	public List<Mteste00> listMteste00Detalles (Mteste00 mteste00);
	public void save(Mteste00 mteste, Taudit00 taudit00) throws SQLException;
	public void removeMteste00(Mteste00 mteste00, Taudit00 taudit00);
	public void saveLista (List<Mteste00> mteste00Detalles,Taudit00 taudit00);
	public void removeLista(Mteste00 mteste00);
	public boolean obtenerTransicionValida (Integer vtIdesqe, Integer macIdedio);
}