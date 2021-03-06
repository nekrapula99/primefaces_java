package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMconca00Dao;
import com.tlm.faelec.service.maestros.IMconca00Service;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mconca00Service")
public class Mconca00ServiceImpl implements IMconca00Service{

	@Autowired		
	private IMconca00Dao imconca00dao;
	
	@Override
	public List<Mconca00> listMconca00ByCriteria(Mconca00 mconca00) {
		return imconca00dao.listMconca00ByCriteria(mconca00);
	}
	
	@Override
	public void save(Mconca00 mconca, Taudit00 taudit00) throws SQLException {
		imconca00dao.save(mconca, taudit00);
	}
	
	public Mconca00 getMconcaById(int idccia) {
		return imconca00dao.getMconca00ById(idccia);
	}
	
	@Override
	public Mconca00 getMconca00ByCodcia(String codcia)
	{
		return imconca00dao.getMconca00ByCodcia(codcia);
	}

	@Override
	public List<Mconca00> listMconca00ByRegcia(Mconca00 mconca00, List<String> listMusuco00) {	
		return imconca00dao.listMconca00ByRegcia(mconca00, listMusuco00);
	}

	@Override
	public void removeMconca00(Mconca00 mconca00, Taudit00 taudit00) {
		imconca00dao.delete(mconca00,taudit00);	
	}

	@Override
	public Mconca00 getMconca00(String codcia) {
		return imconca00dao.getMconca00(codcia);
	}

}