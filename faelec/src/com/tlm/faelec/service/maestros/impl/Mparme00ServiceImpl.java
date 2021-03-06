package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tlm.faelec.model.dao.IMparme00Dao;
import com.tlm.faelec.service.maestros.IMparme00Service;
import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mparme00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mparme00Service")
public class Mparme00ServiceImpl implements IMparme00Service{

	@Autowired		
	private IMparme00Dao imparme00Dao;
	
	@Override
	public List<Mparme00> getListMparme00s(Integer idccia,String aplime){
		return imparme00Dao.getListMparme00s(idccia, aplime);
	}

	@Override
	public List<Mparme00> ListMparmeByCriteria(List<String> listMusuco00, Mparme00 mparme00) {		
		return imparme00Dao.ListMparmeByCriteria(listMusuco00, mparme00);
	}

	@Override
	public void save(Mparme00 mparme, Taudit00 taudit00) throws SQLException {
		imparme00Dao.save(mparme, taudit00);
		
	}

	@Override
	public void removeMparme00(Mparme00 mparme, Taudit00 taudit00) throws SQLException {		
		imparme00Dao.delete(mparme, taudit00);
	}

	@Override
	public Maplic00 consultarObjetoMaplic00(Maplic00 maplic00) {
		return imparme00Dao.consultarObjetoMaplic00(maplic00);
	}

	@Override
	public Mparme00 retrieve(Mparme00 mparme){
		return imparme00Dao.retrieve(mparme);
	}
	
}
