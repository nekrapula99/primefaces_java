package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

public interface ITqdfqf00Service {

	public List<Tqdfqf00> listTqdfqf00ByCriteria(Tqdfqf00 tqdfqf00,	List<String> listMusuco00);
	public List<Tqdfqf00> listTqdfqf00ByCriteria(Tqigqg00 tqigqg00);
	//public List<Tqdfqf00> listTqdfqf00Criteria(Tqdfqf00 tqdfqf00, List<String> listMusuco00);
	public void save(Tqdfqf00 tqdfqf00, Taudit00 taudit00) throws SQLException;
	public void removeTqdfqf00 (Tqdfqf00 tqdfqf00, Taudit00 taudit00);
	public Tqdfqf00 cargarDetalles(Integer idcpqf);
	public List<Tqdfqf00> getTqdfqf00ByObject(Tqigqg00 tqigqg00);

}
