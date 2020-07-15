package com.tlm.faelec.service.maestros.impl;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMfunfu00Dao;
import com.tlm.faelec.service.maestros.IMfunfu00Service;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mfunfu00Service")
public class Mfunfu00ServiceImpl implements IMfunfu00Service{

	@Autowired
	private IMfunfu00Dao imfunfu00Dao;
	
	@Override
	public List<Mfunfu00> listMfunfu00ByCriteria(Mfunfu00 mfunfu00,	List<String> listMusuco00) {
		return imfunfu00Dao.listMfunfu00ByCriteria(mfunfu00, listMusuco00);
	}
	
	@Override
	public List<Mfunfu00> listMfunfu00Criteria(Mfunfu00 mfunfu00, List<String> listMusuco00) {
		return imfunfu00Dao.listMfunfu00ByCriteria(mfunfu00, listMusuco00);
	}

	@Override
	public void save(Mfunfu00 mfunfu00, Taudit00 taudit00) throws SQLException {
		imfunfu00Dao.save(mfunfu00, taudit00);
		
	}

	@Override
	public void removeMfunfu00(Mfunfu00 mfunfu00, Taudit00 taudit00) {
		imfunfu00Dao.delete(mfunfu00,taudit00);
		
	}

	


}
