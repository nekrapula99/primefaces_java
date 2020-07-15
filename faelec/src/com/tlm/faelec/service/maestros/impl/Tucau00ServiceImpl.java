package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITucauc00Dao;
import com.tlm.faelec.service.maestros.ITucauc00Service;
import com.tlm.faelecEntities.model.entities.Mrafra00;
import com.tlm.faelecEntities.model.entities.Tucauc00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tucauc00Service")
public class Tucau00ServiceImpl implements ITucauc00Service{

	@Autowired
	private ITucauc00Dao itucauc00Dao;
	
	@Override
	public List<Tucauc00> listTucauc00ByCriteria(Mrafra00 mrafra00) {
		return itucauc00Dao.listTucauc00ByCriteria(mrafra00);
	}
	
	@Override
	public void save(Tucauc00 tucauc00, Taudit00 taudit00) throws SQLException {
		itucauc00Dao.save(tucauc00, taudit00);
		
	}

	@Override
	public void removeTucauc00(Tucauc00 tucauc00, Taudit00 taudit00) {
		itucauc00Dao.delete(tucauc00,taudit00);
		
	}

}