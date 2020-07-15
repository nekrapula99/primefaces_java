package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITresre00Dao;
import com.tlm.faelec.service.trans.ITresre00Service;
import com.tlm.faelecEntities.model.entities.Tresre00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tresre00Service")
public class Tresre00ServiceImpl implements ITresre00Service{

	@Autowired
	private ITresre00Dao itresre00Dao;
	
	@Override
	public List<Tresre00> listTresre00ByCriteria(Tresre00 tresre00) {
		return itresre00Dao.listTresre00ByCriteria(tresre00);
	}

	@Override
	public void save(Tresre00 tresre00, Taudit00 taudit00) throws SQLException {
		itresre00Dao.save(tresre00, taudit00);
		
	}

	@Override
	public void removeTresre00(Tresre00 tresre00, Taudit00 taudit00) {
		itresre00Dao.delete(tresre00,taudit00);
		
	}

}