package com.tlm.faelec.model.dao;

import java.util.Date;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Mrafra00;

public interface IMrafra00Dao extends GenericDAO<Mrafra00>{
	
	public List<Mrafra00> listMrafra00ByCriteria(Mrafra00 mrafra00, List<String> listMusuco00);
	public List<Mrafra00> listMrafra00Criteria(Mrafra00 mrafra00, List<String> listMusuco00);
	public List<Mrafra00> listMrafra00(Mrafra00 mrafra00, List<String> companiasUsu);
	public Mrafra00 getRegistroRango(int idTransaccion,Date fechahoy);


}
