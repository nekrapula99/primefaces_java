package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMparca00Dao;
import com.tlm.faelec.service.maestros.IMparca00Service;
import com.tlm.faelecEntities.model.entities.Mparca00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mparca00Service")
public class Mparca00ServiceImpl implements IMparca00Service {

	@Autowired	
	 private IMparca00Dao mparca00dao;
	
	@Override
	public List<Mparca00> listMparca00ByCriteria(Mparca00 mparca00, List<String> listMusuco00) {
		return mparca00dao.listMparca00ByCriteria(mparca00, listMusuco00);
	}

	@Override
	public void save(Mparca00 mparca00, Taudit00 taudit00) throws SQLException {
		mparca00dao.save(mparca00, taudit00);		
	}

	@Override
	public void removeMpropr00(Mparca00 mparca00, Taudit00 taudit00) {
		mparca00dao.delete(mparca00, taudit00);		
	}

}
