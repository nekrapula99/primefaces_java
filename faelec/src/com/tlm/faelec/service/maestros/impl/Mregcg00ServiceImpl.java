package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMregcg00Dao;
import com.tlm.faelec.service.maestros.IMregcg00Service;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mregcg00;
import com.tlm.faelecEntities.model.entities.Taudit00;


@Transactional
@Service("mregcg00Service")
public class Mregcg00ServiceImpl implements IMregcg00Service {
	
	@Autowired
	private IMregcg00Dao imregcg00Dao;
	
	@Override
	public List<Mregcg00> listMregcg00ByCriteria(Mregcg00 mregcg00,	List<String> listMusuco00) {
		return imregcg00Dao.listMregcg00ByCriteria(mregcg00, listMusuco00);
	}
	
	@Override
	public List<Mregcg00> listMregcg00Criteria(Mregcg00 mregcg00, List<String> listMusuco00) {
		return imregcg00Dao.listMregcg00ByCriteria(mregcg00, listMusuco00);
	}

	@Override
	public void save(Mregcg00 mregcg00, Taudit00 taudit00) throws SQLException {
		imregcg00Dao.save(mregcg00, taudit00);	
		
	}
	
	@Override
	public void removeMregcg00(Mregcg00 mregcg00, Taudit00 taudit00) {
		imregcg00Dao.delete(mregcg00,taudit00);
		
	}

	
}
