package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMgenus00Dao;
import com.tlm.faelec.model.dao.IMidiom00Dao;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelec.service.maestros.IMidiom00Service;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Midiom00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("midiom00Service")
public class Midiom00ServiceImpl implements IMidiom00Service{

	@Autowired		
	private IMidiom00Dao midiom00dao;
	

	@Override
	public List<Midiom00> listMidiom00ByCriteria(Midiom00 midiom00,List<String> listMusuco00) {
		return midiom00dao.listMidiom00ByCriteria(midiom00,listMusuco00);
	}


	@Override
	public void save(Midiom00 midiom00, Taudit00 taudit00) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		midiom00dao.save(midiom00, taudit00);
		
	}


	@Override
	public void removeMidiom00(Midiom00 midiom00, Taudit00 taudit00) {
		// TODO Apéndice de método generado automáticamente
		midiom00dao.delete(midiom00, taudit00);
		
	}

}
