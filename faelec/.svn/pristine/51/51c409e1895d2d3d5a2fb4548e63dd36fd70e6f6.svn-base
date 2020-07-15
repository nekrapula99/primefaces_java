package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMpropr00Dao;
import com.tlm.faelec.service.maestros.IMpropr00Service;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mpropr00Service")
public class Mpropr00ServiceImpl implements IMpropr00Service {

	@Autowired	
	 private IMpropr00Dao mpropr00dao;
	
	@Override
	public List<Mpropr00> listMpropr00ByCriteria(Mpropr00 mpropr00, List<String> listMusuco00) {
		return mpropr00dao.listMpropr00ByCriteria(mpropr00, listMusuco00);
	}
	
	@Override
	public List<Mpropr00> listMpropr00Criteria(Mpropr00 mpropr00, List<String> listMusuco00) {
		return mpropr00dao.listMpropr00ByCriteria(mpropr00, listMusuco00);
	}

	@Override
	public void save(Mpropr00 mpropr00, Taudit00 taudit00) throws SQLException {
		mpropr00dao.save(mpropr00, taudit00);		
	}

	@Override
	public void removeMpropr00(Mpropr00 mpropr00, Taudit00 taudit00) {
		mpropr00dao.delete(mpropr00, taudit00);		
	}

	@Override
	public Mpropr00 cargarDetalles(Mpropr00 mpropr00){
		return mpropr00dao.cargarDetalles(mpropr00);
	}

	
}
