package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Mparca00;

public interface IMparca00Dao extends GenericDAO<Mparca00>{

	public List<Mparca00> listMparca00ByCriteria(Mparca00 mparca00, List<String> listMusuco00);
	
}
