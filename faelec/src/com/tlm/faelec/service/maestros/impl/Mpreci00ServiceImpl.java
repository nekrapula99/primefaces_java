package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMpreci00Dao;
import com.tlm.faelec.service.maestros.IMpreci00Service;
import com.tlm.faelecEntities.model.entities.Mpreci00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mpreci00Service")
public class Mpreci00ServiceImpl implements IMpreci00Service{
	
	@Autowired
	private IMpreci00Dao impreci00Dao;

	@Override
	public List<Mpreci00> listMpreci00ByCriteria(Mpreci00 mpreci00, List<String> listMusuco00) {
		return impreci00Dao.listMpreci00ByCriteria(mpreci00, listMusuco00);
	}

	@Override
	public void save(Mpreci00 mpreci00, Taudit00 taudit00) throws SQLException {
		impreci00Dao.save(mpreci00, taudit00);	
	}

	@Override
	public void removeMpreci00(Mpreci00 mpreci00, Taudit00 taudit00) {
		impreci00Dao.delete(mpreci00,taudit00);
		
	}

	@Override
	public List<Mpreci00> listMpreci00(Mpreci00 mpreci00, List<String> companiasUsu) {
		return impreci00Dao.listMpreci00(mpreci00, companiasUsu);
	}
	
}
