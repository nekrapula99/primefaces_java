package com.tlm.faelec.model.dao;


import java.util.List;

import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mparme00;


public interface IMparme00Dao extends GenericDAO<Mparme00> {

	public List<Mparme00> getListMparme00s(Integer idccia,String aplime);
	public List<Mparme00>ListMparmeByCriteria(List<String> listMusuco00, Mparme00 mparme00);	
	public Maplic00 consultarObjetoMaplic00 (Maplic00 maplic00);
	public Mparme00 retrieve(Mparme00 mparme);
}
