package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMconca00Dao;
import com.tlm.faelec.model.dao.IMvaria00Dao;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelec.service.maestros.IMvaria00Service;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mvaria00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mvaria00Service")
public class Mvaria00ServiceImpl implements IMvaria00Service{

	@Autowired		
	private IMvaria00Dao iMvaria00Dao ;

	@Override
	public List<Mvaria00> listMvaria00(Mvaria00 mvaria00, List<String> listMusuco00, Mgenus00 mgenus00) {
		return iMvaria00Dao.listMvaria00(mvaria00, listMusuco00, mgenus00);
	}

	@Override
	public void save(Mvaria00 mvaria, Taudit00 taudit00) throws SQLException {
		iMvaria00Dao.save(mvaria, taudit00);		
	}

	@Override
	public void removeMvaria00(Mvaria00 mvaria00, Taudit00 taudit00) {
		iMvaria00Dao.delete(mvaria00, taudit00);
	}
	


}