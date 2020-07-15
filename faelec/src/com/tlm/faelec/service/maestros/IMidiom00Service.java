package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;


import com.tlm.faelecEntities.model.entities.Midiom00;
//import com.tlm.faelec.model.entities.Mmesje00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMidiom00Service {
	
	public List<Midiom00> listMidiom00ByCriteria(Midiom00 midiom00, List<String> listMusuco00);
	public void save(Midiom00 midiom00, Taudit00 taudit00) throws SQLException;
	public void removeMidiom00(Midiom00 midiom00,Taudit00 taudit00);

}
