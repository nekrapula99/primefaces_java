package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMswfsw00Dao;
import com.tlm.faelec.service.maestros.IMswfsw00Service;
import com.tlm.faelecEntities.model.entities.Mswfsw00;
import com.tlm.faelecEntities.model.entities.Taudit00;


@Transactional
@Service("mswfsw00Service")
public class Mswfsw00ServiceImpl implements IMswfsw00Service {
	
	@Autowired
	private IMswfsw00Dao imswfsw00Dao;
	
	@Override
	public List<Mswfsw00> listMswfsw00ByCriteria(Mswfsw00 mswfsw00,	List<String> listMusuco00) {
		return imswfsw00Dao.listMswfsw00ByCriteria(mswfsw00, listMusuco00);
	}

	@Override
	public void save(Mswfsw00 mswfsw00, Taudit00 taudit00) throws SQLException {
		imswfsw00Dao.save(mswfsw00, taudit00);	
		
	}
	
	@Override
	public void removeMswfsw00(Mswfsw00 mswfsw00, Taudit00 taudit00) {
		imswfsw00Dao.delete(mswfsw00,taudit00);
		
	}

}

