package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqdpqp00Dao;
import com.tlm.faelec.service.trans.ITqdpqp00Service;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;

@Transactional
@Service("tqdpqp00Service")
public class Tqdpqp00ServiceImpl implements ITqdpqp00Service{

	@Autowired
	private ITqdpqp00Dao itqdpqp00Dao;
	
	@Override
	public List<Tqdpqp00> listTqdpqp00ByCriteria(Tqdpqp00 tqdpqp00) {
		return itqdpqp00Dao.listTqdpqp00ByCriteria(tqdpqp00);
	}
	
	@Override
	public List<Tqdpqp00> listTqdpqp00Criteria(Tqdpqp00 tqdpqp00, List<String> listMusuco00) {
		return itqdpqp00Dao.listTqdpqp00Criteria(tqdpqp00, listMusuco00);
	}

	@Override
	public void save(Tqdpqp00 tqdpqp00, Taudit00 taudit00) throws SQLException {
		itqdpqp00Dao.save(tqdpqp00, taudit00);
		
	}

	@Override
	public void removeTqdpqp00(Tqdpqp00 tqdpqp00, Taudit00 taudit00) {
		itqdpqp00Dao.delete(tqdpqp00,taudit00);
		
	}

}