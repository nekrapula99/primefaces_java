package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMtaric00Dao;
import com.tlm.faelec.service.maestros.IMtaric00Service;
import com.tlm.faelecEntities.model.entities.Mtaric00;
import com.tlm.faelecEntities.model.entities.Taudit00;


@Transactional
@Service("mtaric00Service")
public class Mtaric00ServiceImpl implements IMtaric00Service {
	
	@Autowired
	private IMtaric00Dao imtaric00Dao;
	
	@Override
	public List<Mtaric00> listMtaric00ByCriteria(Mtaric00 mtaric00,	List<String> listMusuco00) {
		return imtaric00Dao.listMtaric00ByCriteria(mtaric00, listMusuco00);
	}

	@Override
	public void save(Mtaric00 mtaric00, Taudit00 taudit00) throws SQLException {
		imtaric00Dao.save(mtaric00, taudit00);	
		
	}
	
	@Override
	public void removeMtaric00(Mtaric00 mtaric00, Taudit00 taudit00) {
		imtaric00Dao.delete(mtaric00,taudit00);
		
	}

	
}
