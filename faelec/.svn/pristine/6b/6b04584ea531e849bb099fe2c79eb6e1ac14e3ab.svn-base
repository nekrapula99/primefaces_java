package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITerfet00Dao;
import com.tlm.faelec.service.trans.ITerfet00Service;
import com.tlm.faelecEntities.model.entities.Terfet00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("terfet00Service")
public class Terfet00ServiceImpl implements ITerfet00Service{

	@Autowired
	private ITerfet00Dao iterfet00Dao;
	
	@Override
	public List<Terfet00> listTerfet00ByCriteria(Terfet00 terfet00,	List<String> listMusuco00) {
		return iterfet00Dao.listTerfet00ByCriteria(terfet00,listMusuco00);
	}
	
	@Override
	public void save(Terfet00 terfet00, Taudit00 taudit00) throws SQLException {
		iterfet00Dao.save(terfet00, taudit00);
		
	}

	@Override
	public void removeTerfet00(Terfet00 terfet00, Taudit00 taudit00) {
		iterfet00Dao.delete(terfet00,taudit00);
		
	}

}


