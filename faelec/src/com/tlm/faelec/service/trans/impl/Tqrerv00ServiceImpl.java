package com.tlm.faelec.service.trans.impl;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqrerv00Dao;
import com.tlm.faelec.service.trans.ITqrerv00Service;
import com.tlm.faelecEntities.model.entities.Tqrerv00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tqrerv00Service")
public class Tqrerv00ServiceImpl implements ITqrerv00Service{

	@Autowired
	private ITqrerv00Dao itqrerv00Dao;
	
	@Override
	public List<Tqrerv00> listTqrerv00ByCriteria(Tqrerv00 tqrerv00) {
		return itqrerv00Dao.listTqrerv00ByCriteria(tqrerv00);
	}

	@Override
	public void save(Tqrerv00 tqrerv00, Taudit00 taudit00) throws SQLException {
		itqrerv00Dao.save(tqrerv00, taudit00);
		
	}

	@Override
	public void removeTqrerv00(Tqrerv00 tqrerv00, Taudit00 taudit00) {
		itqrerv00Dao.delete(tqrerv00,taudit00);
		
	}

}