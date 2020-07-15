package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqdrqr00Dao;
import com.tlm.faelec.service.trans.ITqdrqr00Service;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Transactional
@Service("tqdrqr00Service")
public class Tqdrqr00ServiceImpl implements ITqdrqr00Service{

	@Autowired
	private ITqdrqr00Dao itqdrqr00Dao;
	
	@Override
	public List<Tqdrqr00> listTqdrqr00ByCriteria(Tqdrqr00 tqdrqr00) {
		return itqdrqr00Dao.listTqdrqr00ByCriteria(tqdrqr00);
	}
	
	@Override
	public List<Tqdrqr00> listTqdrqr00ByCriteria(Tqigqg00 tqigqg00) {
		return itqdrqr00Dao.listTqdrqr00ByCriteria(tqigqg00);
	}
	
	/*@Override
	public List<Tqdrqr00> listTqdrqr00Criteria(Tqdrqr00 tqdrqr00, List<String> listMusuco00) {
		return itqdrqr00Dao.listTqdrqr00Criteria(tqdrqr00, listMusuco00);
	}*/
	
	@Override
	public void save(Tqdrqr00 tqdrqr00, Taudit00 taudit00) throws SQLException {
		itqdrqr00Dao.save(tqdrqr00, taudit00);
		
	}

	@Override
	public void removeTqdrqr00(Tqdrqr00 tqdrqr00, Taudit00 taudit00) {
		itqdrqr00Dao.delete(tqdrqr00,taudit00);
		
	}

	@Override
	public List<Tqdrqr00> getTqdrqr00ByTqigqg00(Tqdrqr00 tqdrqr00) {
		return itqdrqr00Dao.getTqdrqr00ByTqigqg00(tqdrqr00);
		
	}

	@Override
	public Tqdrqr00 cargarDetalles(Integer idtrqr) {
		return itqdrqr00Dao.cargarDetalles(idtrqr);
	}

	@Override
	public Tqdrqr00 getTqdrqr00ByObject(Tqigqg00 tqigqg00) {
		return itqdrqr00Dao.getTqdrqr00ByObject(tqigqg00);
	}

}