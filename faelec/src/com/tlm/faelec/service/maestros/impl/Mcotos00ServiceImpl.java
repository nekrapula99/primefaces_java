package com.tlm.faelec.service.maestros.impl;



import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMcotos00Dao;
import com.tlm.faelec.service.maestros.IMcotos00Service;
import com.tlm.faelecEntities.model.entities.Mcotos00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mcotos00Service")
public class Mcotos00ServiceImpl implements IMcotos00Service{

	@Autowired
	private IMcotos00Dao imcotos00Dao;
	
	@Override
	public List<Mcotos00> listMcotos00ByCriteria(Mcotos00 mcotos00,	List<String> listMusuco00) {
		return imcotos00Dao.listMcotos00ByCriteria(mcotos00, listMusuco00);
	}

	@Override
	public void save(Mcotos00 mcotos00, Taudit00 taudit00) throws SQLException {
		imcotos00Dao.save(mcotos00, taudit00);
		
	}

	@Override
	public void removeMcotos00(Mcotos00 mcotos00, Taudit00 taudit00) {
		imcotos00Dao.delete(mcotos00,taudit00);
		
	}

}
