package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITqigqg00Dao;
import com.tlm.faelec.service.trans.ITqigqg00Service;
import com.tlm.faelecEntities.model.entities.Tqesqe00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("tqigqg00Service")
public class Tqigqg00ServiceImpl implements ITqigqg00Service{

	@Autowired
	private ITqigqg00Dao itqigqg00Dao;
	
	@Override
	public List<Tqigqg00> listTqigqg00ByCriteria(Tqigqg00 tqigqg00,	List<String> listMusuco00) {
		return itqigqg00Dao.listTqigqg00ByCriteria(tqigqg00,listMusuco00);
	}

	@Override
	public List<Tqigqg00> listTqigqg00Criteria(Tqigqg00 tqigqg00, List<String> listMusuco00) {
		return itqigqg00Dao.listTqigqg00Criteria(tqigqg00, listMusuco00);
	}
	
	@Override
	public void save(Tqigqg00 tqigqg00, Taudit00 taudit00) throws SQLException {
		itqigqg00Dao.save(tqigqg00, taudit00);
		
	}

	@Override
	public void removeTqigqg00(Tqigqg00 tqigqg00, Taudit00 taudit00) {
		itqigqg00Dao.delete(tqigqg00,taudit00);
		
	}
	
	@Override
	public Tqigqg00 cargarDetalles(Tqigqg00 tqigqg00){
		return itqigqg00Dao.cargarDetalles(tqigqg00);
	}

	@Override
	public List<Tqesqe00> listTqigqg00ByCriteriaFechas(Tqesqe00 tqesqe00) {
		return itqigqg00Dao.listTqigqg00ByCriteriaFechas(tqesqe00);
	}

	@Override
	public Tqigqg00 cargarDetalles(Integer idtqqg) {
			return itqigqg00Dao.cargarDetalles(idtqqg);
		}
	public Tqigqg00 cargarListaEstado(Integer idtqqg){
		return itqigqg00Dao.cargarDetalles(idtqqg);
	}

	@Override
	public Tqigqg00 getObjectTqigqg00(Tqigqg00 tqigqg00) {
		return itqigqg00Dao.getObjectTqigqg00(tqigqg00);
	}

}
