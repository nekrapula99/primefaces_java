package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.tlm.faelecEntities.model.entities.Mconca00;
//import com.tlm.faelec.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
//import com.tlm.faelec.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMgenus00Service {
	
	public List<Mgenus00> listMgenus00ByCriteriaAndlistMusuco00(Mgenus00 mgenus00, List<String> listMusuco00);
	public List<Mgenus00> listMgenus00ByCriteria(Mgenus00 mgenus00,List<String> listMusuco00);
	public void removeMgenus00(Mgenus00 mgenus00, Taudit00 taudit00);
	public void save(Mgenus00 mgenus00, Taudit00 taudit00) throws SQLException;
	public Mgenus00 MgenusByCodius (String codius, String codtus);
	public List<Mgenus00> MgenusByIdcmus(Mgenus00 mgenus00, Mconca00 mconca00);
	public Mgenus00 cargarDetalles(Integer idtrus);
	public Mgenus00 find(Integer idtrus);
	public Map<String, String> getMapMgenus00ByCodtus(String codtus);
	public Mgenus00 findMgenus(Mgenus00 mgenus);
}
