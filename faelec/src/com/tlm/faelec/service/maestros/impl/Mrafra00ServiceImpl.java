package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMrafra00Dao;
import com.tlm.faelec.service.maestros.IMrafra00Service;
import com.tlm.faelecEntities.model.entities.Mrafra00;
import com.tlm.faelecEntities.model.entities.Taudit00;


@Transactional
@Service("mrafra00Service")
public class Mrafra00ServiceImpl implements IMrafra00Service {
	
	@Autowired
	private IMrafra00Dao imrafra00Dao;
	
	@Override
	public List<Mrafra00> listMrafra00ByCriteria(Mrafra00 mrafra00,	List<String> listMusuco00) {
		return imrafra00Dao.listMrafra00ByCriteria(mrafra00, listMusuco00);
	}

	@Override
	public void save(Mrafra00 mrafra00, Taudit00 taudit00) throws SQLException {
		imrafra00Dao.save(mrafra00, taudit00);	
		
	}
	
	@Override
	public void removeMrafra00(Mrafra00 mrafra00, Taudit00 taudit00) {
		imrafra00Dao.delete(mrafra00,taudit00);
		
	}

	@Override
	public List<Mrafra00> listMrafra00(Mrafra00 mrafra00,List<String> companiasUsu) {
		return	imrafra00Dao.listMrafra00(mrafra00,companiasUsu);
		 
	}

	@Override
	public Mrafra00 getRegistroRango(int idTransaccion, Date fechahoy) {
		return imrafra00Dao.getRegistroRango(idTransaccion, fechahoy);
	}

}
