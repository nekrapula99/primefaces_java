package com.tlm.faelec.service.maestros;

import java.util.List;

import com.tlm.faelecEntities.model.entities.Mtabla00;

public interface IMtabla00Service {

	//Mtipre00
	public List<Mtabla00> listMtabla00ByCriteria(String aplusu, Mtabla00 mtabla00);
	public Mtabla00 MtablaByCodtab (String codtab);

}
