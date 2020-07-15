package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqdfqf00Dao;
import com.tlm.faelec.service.trans.ITqdfqf00Service;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Transactional
@Service("tqdfqf00Service")
public class Tqdfqf00ServiceImpl implements ITqdfqf00Service{

	@Autowired
	private ITqdfqf00Dao itqdfqf00Dao;
	
	@Override
	public List<Tqdfqf00> listTqdfqf00ByCriteria(Tqdfqf00 tqdfqf00,	List<String> listMusuco00) {
		return itqdfqf00Dao.listTqdfqf00ByCriteria(tqdfqf00, listMusuco00);
	}
	
	@Override
	public List<Tqdfqf00> listTqdfqf00ByCriteria(Tqigqg00 tqigqg00) {
		return itqdfqf00Dao.listTqdfqf00ByCriteria(tqigqg00);
	}
	
	/*@Override
	public List<Tqdfqf00> listTqdfqf00Criteria(Tqdfqf00 tqdfqf00, List<String> listMusuco00) {
		return itqdfqf00Dao.listTqdfqf00Criteria(tqdfqf00, listMusuco00);
	}*/
	

	@Override
	public void save(Tqdfqf00 tqdfqf00, Taudit00 taudit00) throws SQLException {
		itqdfqf00Dao.save(tqdfqf00, taudit00);
		
	}

	@Override
	public void removeTqdfqf00(Tqdfqf00 tqdfqf00, Taudit00 taudit00) {
		itqdfqf00Dao.delete(tqdfqf00,taudit00);
		
	}

	@Override
	public Tqdfqf00 cargarDetalles(Integer idcpqf) {
		return itqdfqf00Dao.cargarDetalles(idcpqf);
	}

	@Override
	public List<Tqdfqf00> getTqdfqf00ByObject(Tqigqg00 tqigqg00) {
		return itqdfqf00Dao.getTqdfqf00ByObject(tqigqg00);
	}

}