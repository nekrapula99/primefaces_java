package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Tqdrfg00;

public interface ITqdrfg00Dao extends GenericDAO<Tqdrfg00>{
	
	public List<Tqdrfg00> listTqdrfg00ByCriteria(Tqdrfg00 tqdrfg00);
	public List<Tqdrfg00> getTqdrfg00ByCuota(Tqdrfg00 tqdrfg00);

}
