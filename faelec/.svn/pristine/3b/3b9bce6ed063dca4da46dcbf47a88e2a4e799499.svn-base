package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqenqv00Dao;
import com.tlm.faelec.service.trans.ITqenqv00Service;
import com.tlm.faelecEntities.model.entities.Tqenqv00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tqenqv00Service")
public class Tqenqv00ServiceImpl implements ITqenqv00Service{

	@Autowired
	private ITqenqv00Dao itqenqv00Dao;
	
	@Override
	public List<Tqenqv00> listTqenqv00ByCriteria(Tqenqv00 tqenqv00) {
		return itqenqv00Dao.listTqenqv00ByCriteria(tqenqv00);
	}

	@Override
	public void save(Tqenqv00 tqenqv00, Taudit00 taudit00) throws SQLException {
		itqenqv00Dao.save(tqenqv00, taudit00);
		
	}

	@Override
	public void removeTqenqv00(Tqenqv00 tqenqv00, Taudit00 taudit00) {
		itqenqv00Dao.delete(tqenqv00,taudit00);
		
	}

}
