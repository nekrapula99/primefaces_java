package com.tlm.faelec.service.maestros.impl;



import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMcotes00Dao;
import com.tlm.faelec.service.maestros.IMcotes00Service;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mcotes00Service")
public class Mcotes00ServiceImpl implements IMcotes00Service{

	@Autowired
	private IMcotes00Dao imcotes00Dao;
	
	@Override
	public List<Mcotes00> listMcotes00ByCriteria(Mcotes00 mcotes00,	List<String> listMusuco00) {
		return imcotes00Dao.listMcotes00ByCriteria(mcotes00, listMusuco00);
	}
	
	@Override
	public List<Mcotes00> listMcotes00Criteria(Mcotes00 mcotes00, List<String> listMusuco00) {
		return imcotes00Dao.listMcotes00ByCriteria(mcotes00, listMusuco00);
	}

	@Override
	public void save(Mcotes00 mcotes00, Taudit00 taudit00) throws SQLException {
		imcotes00Dao.save(mcotes00, taudit00);
		
	}

	@Override
	public void removeMcotes00(Mcotes00 mcotes00, Taudit00 taudit00) {
		imcotes00Dao.delete(mcotes00,taudit00);
		
	}

}
