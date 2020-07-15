package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMdesgr00Dao;
import com.tlm.faelec.service.maestros.IMdesgr00Service;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mdesgr00Service")
public class Mdesgr00ServiceImpl implements IMdesgr00Service {

	@Autowired	
	private IMdesgr00Dao imdesgr00Dao;	
	
	@Override
	public List<Mdesgr00> listMdesgr00ByCriteria(Mdesgr00 mdesgr00) {
		return imdesgr00Dao.listMdesgr00ByCriteria(mdesgr00);
	}

	@Override
	public void save(Mdesgr00 mdesgr00, Taudit00 taudit00) throws SQLException {
		imdesgr00Dao.save(mdesgr00, taudit00);	
	}

	@Override
	public void removeMdesgr00(Mdesgr00 mdesgr00, Taudit00 taudit00) {
		imdesgr00Dao.delete(mdesgr00, taudit00);
	}
	
}
