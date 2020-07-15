package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMrerer00Dao;
import com.tlm.faelec.service.maestros.IMrerer00Service;
import com.tlm.faelecEntities.model.entities.Mrerer00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mrerer00Service")
public class Mrerer00ServiceImpl implements IMrerer00Service{

	@Autowired
	private IMrerer00Dao imrerer00Dao;
	
	@Override
	public List<Mrerer00> listMrerer00ByCriteria(Mrerer00 mrerer00,	List<String> listMusuco00) {
		return imrerer00Dao.listMrerer00ByCriteria(mrerer00, listMusuco00);
	}

	@Override
	public void save(Mrerer00 mrerer00, Taudit00 taudit00) throws SQLException {
		imrerer00Dao.save(mrerer00, taudit00);
		
	}

	@Override
	public void removeMrerer00(Mrerer00 mrerer00, Taudit00 taudit00) {
		imrerer00Dao.delete(mrerer00,taudit00);
		
	}
	@Override
	public boolean obtenerRegistroValido(Integer idx1, Integer idx2) {
		return imrerer00Dao.obtenerRegistroValido(idx1, idx2);
	}

}
