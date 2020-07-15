package com.tlm.faelec.model.dao;

import java.util.List;
import java.util.Map;

import com.tlm.faelecEntities.model.entities.Mdecai00;



public interface IMdecai00Dao extends GenericDAO<Mdecai00> {
	
	public List<Mdecai00> listMdecai00ByCriteria(Mdecai00 mdecai00);
	public Map<String, Object> hmMdecai00ByCriteria(Mdecai00 mdecai00);

}
