package com.tlm.faelec.model.dao;

import java.util.List;

//import com.tlm.faelec.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;

public interface IMidiom00Dao extends GenericDAO<Midiom00>{

	public List<Midiom00> listMidiom00ByCriteria(Midiom00 midiom00,List<String> listMusuco00);
	
}
