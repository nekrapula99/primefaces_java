package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMtipre00Dao;
import com.tlm.faelec.service.maestros.IMtipre00Service;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mtipre00Service")
public class Mtipre00ServiceImpl implements IMtipre00Service{

	@Autowired
	private IMtipre00Dao imtipre00Dao;
	
	@Override
	public List<Mtipre00> listMtipre00ByCriteria(Mtipre00 mtipre00, List<String> listMusuco00) {
		return imtipre00Dao.listMtipre00ByCriteria(mtipre00, listMusuco00);
	}

	@Override
	public void save(Mtipre00 mtipre00, Taudit00 taudit00) throws SQLException {
	    imtipre00Dao.save(mtipre00, taudit00);
	}

	@Override
	public void removeMtipre00(Mtipre00 mtipre00, Taudit00 taudit00) {
		imtipre00Dao.delete(mtipre00,taudit00);
		
	}

	@Override
	public List<Mtipre00> listMtipre00ByCriteriaByTperre(Mtipre00 mtipre00) {
		return imtipre00Dao.listMtipre00ByCriteriaByTperre(mtipre00);
	}

	@Override
	public Mtipre00 MtipreByTipore(String tipore) {	
		return imtipre00Dao.MtipreByTipore(tipore);
	}
	


}
