package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMdespr00Dao;
import com.tlm.faelec.service.maestros.IMdespr00Service;
import com.tlm.faelecEntities.model.entities.Mdespr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mdespr00Service")
public class Mdespr00ServiceImpl implements IMdespr00Service{

	@Autowired	
	private IMdespr00Dao imdespr00Dao;

	@Override
	public List<Mdespr00> listMdespr00ByCriteria(Mdespr00 mdespr00) {
		return imdespr00Dao.listMdespr00ByCriteria(mdespr00);
	}

	@Override
	public void save(Mdespr00 mdespr00, Taudit00 taudit00) throws SQLException {
		imdespr00Dao.save(mdespr00, taudit00);	
	}

	@Override
	public void removeMdespr00(Mdespr00 mdespr00, Taudit00 taudit00) {
		imdespr00Dao.delete(mdespr00, taudit00);
	}
	


}
