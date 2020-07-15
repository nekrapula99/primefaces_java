package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMevent00Dao;
import com.tlm.faelec.service.maestros.IMevent00Service;
import com.tlm.faelecEntities.model.entities.Mevent00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mevent00Service")
public class Mevent00ServiceImpl implements IMevent00Service{

	@Autowired		
	private IMevent00Dao imevent00dao;

	@Override
	public List<Mevent00> listMevent00ByCriteria(Mevent00 mevent00,List<String> listMusuco00) {
		// TODO Apéndice de método generado automáticamente
		return imevent00dao.listMevent00ByCriteria(mevent00,listMusuco00);
	}

	@Override
	public void save(Mevent00 mevent00, Taudit00 taudit00) throws SQLException {
		imevent00dao.save(mevent00, taudit00);
		
	}

	@Override
	public void removeMevent00(Mevent00 mevent00, Taudit00 taudit00) {
		imevent00dao.delete(mevent00, taudit00);
		
	}
	
}
