package com.tlm.faelec.model.dao;


import java.util.List;

import com.tlm.faelecEntities.model.entities.Mconca00;

public interface IMconca00Dao extends GenericDAO<Mconca00>{
	
	public List<Mconca00> listMconca00ByCriteria(Mconca00 mconca00);
	
	public List<Mconca00> listMconca00ByRegcia(Mconca00 mconca00, List<String> listMusuco00);
	
	public Mconca00 getMconca00ById(int idccia);
	
	public Mconca00 getMconca00ByCodcia(String codcia);
	
	public Mconca00 getMconca00(String codcia);
	
	
	
}