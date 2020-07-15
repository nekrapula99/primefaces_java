package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMestad00Dao;
import com.tlm.faelec.service.maestros.IMestad00Service;
import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mtipre00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mestad00Service")
public class Mestad00ServiceImpl implements IMestad00Service{

	@Autowired		
	private IMestad00Dao imestad00dao;
	
	@Override
	public List<Mestad00> listMestad00ByCriteria(Mestad00 mestad00, List<String> listMusuco00) {
		return imestad00dao.listMestad00ByCriteria(mestad00, listMusuco00);
	}
	
	@Override
	public void save(Mestad00 mestad, Taudit00 taudit00) throws SQLException {
		imestad00dao.save(mestad, taudit00);
	}


	@Override
	public void removeMestad00(Mestad00 mestad00, Taudit00 taudit00) {
		imestad00dao.delete(mestad00,taudit00);	
	}

	@Override
	public Mestad00 mestad00ByTcpedi00(Mestad00 mestad00) {
		return imestad00dao.mestad00ByTcpedi00(mestad00);
	}

	@Override
	public Mestad00 consultarObjetoMestad00(Mestad00 mestad00) {
		return imestad00dao.consultarObjetoMestad00(mestad00);
	}

}