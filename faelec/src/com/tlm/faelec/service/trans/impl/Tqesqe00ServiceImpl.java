package com.tlm.faelec.service.trans.impl;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqesqe00Dao;
import com.tlm.faelec.service.trans.ITqesqe00Service;
import com.tlm.faelecEntities.model.entities.Tqesqe00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tqesqe00Service")
public class Tqesqe00ServiceImpl implements ITqesqe00Service{

	@Autowired
	private ITqesqe00Dao itqesqe00Dao;
	
	@Override
	public List<Tqesqe00> listTqesqe00ByCriteria(Tqesqe00 tqesqe00) {
		return itqesqe00Dao.listTqesqe00ByCriteria(tqesqe00);
	}

	@Override
	public void save(Tqesqe00 tqesqe00, Taudit00 taudit00) throws SQLException {
		itqesqe00Dao.save(tqesqe00, taudit00);
		
	}

	@Override
	public void removeTqesqe00(Tqesqe00 tqesqe00, Taudit00 taudit00) {
		itqesqe00Dao.delete(tqesqe00,taudit00);
		
	}

	@Override
	public Tqesqe00 getTqigqg00ByTqesqe00(Tqesqe00 tqesqe00) {
		return itqesqe00Dao.getTqigqg00ByTqesqe00(tqesqe00);
	}

}
