package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITfbcbc00Dao;
import com.tlm.faelec.service.trans.ITfbcbc00Service;
import com.tlm.faelecEntities.model.entities.Tfbcbc00;
import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;

@Transactional
@Service("tfbcbc00Service")
public class Tfbcbc00ServiceImpl implements ITfbcbc00Service{

	@Autowired
	private ITfbcbc00Dao itfbcbc00Dao;
	
	@Override
	public List<Tfbcbc00> listTfbcbc00ByCriteria(Tfbcbc00 tfbcbc00,	List<String> listMusuco00) {
		return itfbcbc00Dao.listTfbcbc00ByCriteria(tfbcbc00,listMusuco00);
	}
	
	@Override
	public void save(Tfbcbc00 tfbcbc00, Taudit00 taudit00) throws SQLException {
		itfbcbc00Dao.save(tfbcbc00, taudit00);
		
	}

	@Override
	public void removeTfbcbc00(Tfbcbc00 tfbcbc00, Taudit00 taudit00) {
		itfbcbc00Dao.delete(tfbcbc00,taudit00);
		
	}

	@Override
	public boolean validarBorrador(Tfbcbc00 tfbcbc00) {
		return itfbcbc00Dao.validarBorrador(tfbcbc00);
	}

	@Override
	public Tfbcbc00 getTfbcbc00ByTqdfqf00(Tfbcbc00 tfbcbc00) {
		return itfbcbc00Dao.getTfbcbc00ByTqdfqf00(tfbcbc00);
	}

	@Override
	public List<Tfbcbc00> listTfbcbc00FacByCriteria(Tfbcbc00 tfbcbc00,List<String> listMusuco00) {
		return itfbcbc00Dao.listTfbcbc00FacByCriteria(tfbcbc00,listMusuco00);
	}

}


