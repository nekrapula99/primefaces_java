package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

//import com.tlm.faelec.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMtipre00Service {

	//Mtipre00
	public List<Mtipre00> listMtipre00ByCriteria(Mtipre00 mtipre00, List<String> listMusuco00);
	public void save(Mtipre00 mtipre00, Taudit00 taudit00) throws SQLException;
	public void removeMtipre00 (Mtipre00 mtipre00, Taudit00 taudit00);
	public List<Mtipre00> listMtipre00ByCriteriaByTperre(Mtipre00 mtipre00);
	public Mtipre00 MtipreByTipore (String tipore);

}
