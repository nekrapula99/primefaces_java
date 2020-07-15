package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;

public interface ITqdrfg00Service {

	public List<Tqdrfg00> listTqdrfg00ByCriteria(Tqdrfg00 tqdrfg00);
	public void save(Tqdrfg00 tqdrfg00, Taudit00 taudit00) throws SQLException;
	public void removeTqdrfg00 (Tqdrfg00 tqdrfg00, Taudit00 taudit00);
	public List<Tqdrfg00> getTqdrfg00ByCuota(Tqdrfg00 tqdrfg00);

}
