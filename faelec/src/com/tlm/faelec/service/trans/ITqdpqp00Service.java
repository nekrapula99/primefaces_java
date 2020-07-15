package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;

public interface ITqdpqp00Service {
	public List<Tqdpqp00> listTqdpqp00ByCriteria(Tqdpqp00 tqdpqp00);
	public List<Tqdpqp00> listTqdpqp00Criteria(Tqdpqp00 tqdpqp00, List<String> listMusuco00);
	public void save(Tqdpqp00 tqdpqp00, Taudit00 taudit00) throws SQLException;
	public void removeTqdpqp00 (Tqdpqp00 tqdpqp00, Taudit00 taudit00);

}
