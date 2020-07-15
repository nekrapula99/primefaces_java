package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMgenus00Dao;
import com.tlm.faelec.service.maestros.IMgenus00Service;
import com.tlm.faelecEntities.model.entities.Mconca00;
//import com.tlm.faelec.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mgenus00Service")
public class Mgenus00ServiceImpl implements IMgenus00Service{

	@Autowired		
	private IMgenus00Dao mgenus00dao;
	
	@Override
	public List<Mgenus00> listMgenus00ByCriteriaAndlistMusuco00(Mgenus00 mgenus00, List<String> listMusuco00) {
		return mgenus00dao.listMgenus00ByCriteriaAndlistMusuco00(mgenus00, listMusuco00);
	}
	
	@Override
	public void removeMgenus00(Mgenus00 mgenus00, Taudit00 taudit00) {
		// TODO Apéndice de método generado automáticamente
		mgenus00dao.delete(mgenus00, taudit00);
		
	}

	@Override
	public void save(Mgenus00 mgenus00, Taudit00 taudit00) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		mgenus00dao.save(mgenus00, taudit00);
		
	}

	@Override
	public Mgenus00 MgenusByCodius(String codius, String codtus) {
		return mgenus00dao.MgenusByCodius(codius, codtus);
	}
	
	@Override
	public List<Mgenus00> MgenusByIdcmus(Mgenus00 mgenus00, Mconca00 mconca00){
		return mgenus00dao.MgenusByIdcmus(mgenus00, mconca00);
	}
	
	@Override
	public Map<String, String> getMapMgenus00ByCodtus(String codtus) {
		return mgenus00dao.getMapMgenus00ByCodtus(codtus);
	}

	@Override
	public List<Mgenus00> listMgenus00ByCriteria(Mgenus00 mgenus00, List<String> listMusuco00) {
		return mgenus00dao.listMgenus00ByCriteria(mgenus00, listMusuco00);
	}
	
	@Override
	public Mgenus00 find(Integer idtrus){
		return mgenus00dao.find(idtrus);
	}
	
	@Override
	public Mgenus00 cargarDetalles(Integer idtrus){
		return mgenus00dao.cargarDetalles(idtrus);
	}
	
	@Override
	public Mgenus00 findMgenus(Mgenus00 mgenus){
		return mgenus00dao.findMgenus(mgenus);
	}
}
