package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

public interface ITqdrqr00Service {
	
	public List<Tqdrqr00> listTqdrqr00ByCriteria(Tqdrqr00 tqdrqr00);
	public List<Tqdrqr00> listTqdrqr00ByCriteria(Tqigqg00 tqigqg00);
	//public List<Tqdrqr00> listTqdrqr00Criteria(Tqdrqr00 tqdrqr00, List<String> listMusuco00);
	public void save(Tqdrqr00 tqdrqr00, Taudit00 taudit00) throws SQLException;
	public void removeTqdrqr00 (Tqdrqr00 tqdrqr00, Taudit00 taudit00);
	public Tqdrqr00 cargarDetalles(Integer idtrqr);
	public List<Tqdrqr00> getTqdrqr00ByTqigqg00 (Tqdrqr00 tqdrqr00);
	public Tqdrqr00 getTqdrqr00ByObject (Tqigqg00 tqigqg00);

}
