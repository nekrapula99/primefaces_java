package com.tlm.faelec.model.dao;


import java.util.List;

import com.tlm.faelecEntities.model.entities.Tfacfc00;

public interface ITfacfc00Dao  extends GenericDAO<Tfacfc00>{
	
	public List<Tfacfc00> listTfacfc00ByCriteria(Tfacfc00 tfacfc00,List<String> listMusuco00);
	public Tfacfc00 getTfacfc00ByTqdfqf00 (Tfacfc00 tfacfc00);

}
