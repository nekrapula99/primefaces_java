package com.tlm.faelec.model.dao;


import java.util.List;

import com.tlm.faelecEntities.model.entities.Mestad00;

public interface IMestad00Dao extends GenericDAO<Mestad00>{

	public List<Mestad00> listMestad00ByCriteria(Mestad00 mestad00, List<String> listMusuco00);
	public Mestad00 mestad00ByTcpedi00(Mestad00 mestad00);
	public Mestad00 consultarObjetoMestad00(Mestad00 mestad00);

}