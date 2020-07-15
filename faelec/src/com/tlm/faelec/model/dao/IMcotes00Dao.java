package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Mcotes00;

public interface IMcotes00Dao extends GenericDAO<Mcotes00>{
	
	public List<Mcotes00> listMcotes00ByCriteria(Mcotes00 mcotes00, List<String> listMusuco00);
	public List<Mcotes00> listMcotes00Criteria(Mcotes00 mcotes00, List<String> listMusuco00);


}
