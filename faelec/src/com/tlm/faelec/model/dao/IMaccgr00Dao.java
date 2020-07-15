package com.tlm.faelec.model.dao;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Maccgr00;

public interface IMaccgr00Dao extends GenericDAO<Maccgr00>{
	
	public List<Maccgr00> listMaccgr00ByCriteria(Maccgr00 maccgr00);

}
