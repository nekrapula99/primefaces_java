package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Tqdpqp00;

public interface ITqdpqp00Dao extends GenericDAO<Tqdpqp00>{
	
	public List<Tqdpqp00> listTqdpqp00ByCriteria(Tqdpqp00 tqdpqp00);
	public List<Tqdpqp00> listTqdpqp00Criteria(Tqdpqp00 tqdpqp00, List<String> listMusuco00);


}
