package com.tlm.faelec.model.dao;


import java.util.List;

import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mvaria00;

public interface IMvaria00Dao extends GenericDAO<Mvaria00>{

	
	public List<Mvaria00> listMvaria00(Mvaria00 mvaria00, List<String> listMusuco00, Mgenus00 mgenus00);
	
}