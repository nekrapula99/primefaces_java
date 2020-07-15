package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITerfed00Dao;
import com.tlm.faelec.service.trans.ITerfed00Service;
import com.tlm.faelecEntities.model.entities.Terfed00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("terfed00Service")
public class Terfed00ServiceImpl implements ITerfed00Service{

	@Autowired
	private ITerfed00Dao iterfed00Dao;
	
	@Override
	public List<Terfed00> listTerfed00ByCriteria(Terfed00 terfed00,	List<String> listMusuco00) {
		return iterfed00Dao.listTerfed00ByCriteria(terfed00,listMusuco00);
	}
	
	@Override
	public void save(Terfed00 terfed00, Taudit00 taudit00) throws SQLException {
		iterfed00Dao.save(terfed00, taudit00);
		
	}

	@Override
	public void removeTerfed00(Terfed00 terfed00, Taudit00 taudit00) {
		iterfed00Dao.delete(terfed00,taudit00);
		
	}

}


