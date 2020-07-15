package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Terfet00;

public interface ITerfet00Service {
	
	public List<Terfet00> listTerfet00ByCriteria(Terfet00 terfet00,List<String> listMusuco00);
	public void save(Terfet00 terfet00, Taudit00 taudit00) throws SQLException;
	public void removeTerfet00 (Terfet00 terfet00, Taudit00 taudit00);

}
