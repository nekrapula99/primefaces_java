package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMaccio00Dao;
import com.tlm.faelec.service.maestros.IMaccio00Service;
import com.tlm.faelecEntities.model.entities.Maccio00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("maccio00Service")
public class Maccio00ServiceImpl implements IMaccio00Service{

	@Autowired		
	private IMaccio00Dao imaccio00dao;

	@Override
	public List<Maccio00> listMaccio00ByCriteria(Maccio00 maccio00, List<String> listMusuco00) {		
		return imaccio00dao.listMaccio00ByCriteria(maccio00, listMusuco00);
	}

	@Override
	public void save(Maccio00 maccio00, Taudit00 taudit00) throws SQLException {
		imaccio00dao.save(maccio00, taudit00); 
	}

	@Override
	public void removeMaccio00(Maccio00 maccio00, Taudit00 taudit00) {
		imaccio00dao.delete(maccio00, taudit00);
	}

	@Override
	public List<Maccio00> getListMaccio00(Maccio00 maccio00,List<String> listMusuco00) {
		return imaccio00dao.getListMaccio00(maccio00,listMusuco00);
	}
	
	@Override
	public Maccio00 getMaccio00ByTqigqg00 (Maccio00 maccio00, String tabla) {
		return imaccio00dao.getMaccio00ByTqigqg00(maccio00,tabla);
	}
	
}