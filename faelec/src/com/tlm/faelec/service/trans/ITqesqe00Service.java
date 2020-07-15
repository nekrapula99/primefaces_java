package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqesqe00;

public interface ITqesqe00Service {

	public List<Tqesqe00> listTqesqe00ByCriteria(Tqesqe00 tqesqe00);
	public void save(Tqesqe00 tqesqe00, Taudit00 taudit00) throws SQLException;
	public void removeTqesqe00 (Tqesqe00 tqesqe00, Taudit00 taudit00);
	public Tqesqe00 getTqigqg00ByTqesqe00 (Tqesqe00 tqesqe00);

}
