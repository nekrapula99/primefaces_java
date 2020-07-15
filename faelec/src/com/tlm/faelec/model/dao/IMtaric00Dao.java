package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Mtaric00;

public interface IMtaric00Dao extends GenericDAO<Mtaric00>{
	
	public List<Mtaric00> listMtaric00ByCriteria(Mtaric00 mtaric00, List<String> listMusuco00);

}
