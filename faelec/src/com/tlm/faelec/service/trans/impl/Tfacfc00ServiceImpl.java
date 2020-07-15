package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITfacfc00Dao;
import com.tlm.faelec.service.trans.ITfacfc00Service;
import com.tlm.faelecEntities.model.entities.Tfacfc00;
import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tfbcbc00;

@Transactional
@Service("tfacfc00Service")
public class Tfacfc00ServiceImpl implements ITfacfc00Service{

	@Autowired
	private ITfacfc00Dao itfacfc00Dao;
	
	@Override
	public List<Tfacfc00> listTfacfc00ByCriteria(Tfacfc00 tfacfc00,	List<String> listMusuco00) {
		return itfacfc00Dao.listTfacfc00ByCriteria(tfacfc00,listMusuco00);
	}
	
	@Override
	public void save(Tfacfc00 tfacfc00, Taudit00 taudit00) throws SQLException {
		itfacfc00Dao.save(tfacfc00, taudit00);
		
	}

	@Override
	public void removeTfacfc00(Tfacfc00 tfacfc00, Taudit00 taudit00) {
		itfacfc00Dao.delete(tfacfc00,taudit00);
		
	}
	
	@Override
	public Tfacfc00 getTfacfc00ByTqdfqf00 (Tfacfc00 tfacfc00) {
		return itfacfc00Dao.getTfacfc00ByTqdfqf00(tfacfc00);
	}

}


