package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;


import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mvaria00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMvaria00Service {
	
	//Mvaria00
	public List<Mvaria00> listMvaria00(Mvaria00 mvaria00, List<String> listMusuco00, Mgenus00 mgenus00);
	public void save(Mvaria00 mvaria, Taudit00 taudit00) throws SQLException;
	public void removeMvaria00(Mvaria00 mvaria00,Taudit00 taudit00);
}