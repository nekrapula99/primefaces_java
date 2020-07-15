package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITfesfu00Dao;
import com.tlm.faelec.service.trans.ITfesfu00Service;
import com.tlm.faelecEntities.model.entities.Tfesfu00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tfesfu00Service")
public class Tfesfu00ServiceImpl implements ITfesfu00Service{

	@Autowired
	private ITfesfu00Dao itfesfu00Dao;
	
	@Override
	public List<Tfesfu00> listTfesfu00ByCriteria(Tfesfu00 tfesfu00,	List<String> listMusuco00) {
		return itfesfu00Dao.listTfesfu00ByCriteria(tfesfu00,listMusuco00);
	}
	
	@Override
	public void save(Tfesfu00 tfesfu00, Taudit00 taudit00) throws SQLException {
		itfesfu00Dao.save(tfesfu00, taudit00);
		
	}

	@Override
	public void removeTfesfu00(Tfesfu00 tfesfu00, Taudit00 taudit00) {
		itfesfu00Dao.delete(tfesfu00,taudit00);
		
	}

}


