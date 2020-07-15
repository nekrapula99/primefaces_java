package com.tlm.faelec.model.dao;

import java.util.List;
import java.util.Map;

import com.tlm.faelecEntities.model.entities.Msegca00;

public interface IMsegca00Dao extends GenericDAO<Msegca00> {
  
	public List<Msegca00> getListByCriteria(Msegca00 msegca00);
	public Map<String, String> getPermiByCriteria(Msegca00 msegca00);
}
