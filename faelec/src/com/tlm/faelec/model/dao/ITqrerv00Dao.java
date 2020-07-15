package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Tqrerv00;

public interface ITqrerv00Dao extends GenericDAO<Tqrerv00>{
	
	public List<Tqrerv00> listTqrerv00ByCriteria(Tqrerv00 tqrerv00);

}
