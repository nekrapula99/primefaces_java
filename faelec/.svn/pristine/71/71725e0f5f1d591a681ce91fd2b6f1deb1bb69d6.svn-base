package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqdrfg00Dao;
import com.tlm.faelec.service.trans.ITqdrfg00Service;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tqdrfg00Service")
public class Tqdrfg00ServiceImpl implements ITqdrfg00Service{

	@Autowired
	private ITqdrfg00Dao itqdrfg00Dao;
	
	@Override
	public List<Tqdrfg00> listTqdrfg00ByCriteria(Tqdrfg00 tqdrfg00) {
		return itqdrfg00Dao.listTqdrfg00ByCriteria(tqdrfg00);
	}

	@Override
	public void save(Tqdrfg00 tqdrfg00, Taudit00 taudit00) throws SQLException {
		itqdrfg00Dao.save(tqdrfg00, taudit00);
		
	}

	@Override
	public void removeTqdrfg00(Tqdrfg00 tqdrfg00, Taudit00 taudit00) {
		itqdrfg00Dao.delete(tqdrfg00,taudit00);
		
	}

	@Override
	public List<Tqdrfg00> getTqdrfg00ByCuota(Tqdrfg00 tqdrfg00) {
		return itqdrfg00Dao.getTqdrfg00ByCuota(tqdrfg00);
	}

}