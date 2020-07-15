package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMgente00Dao;
import com.tlm.faelec.service.maestros.IMgente00Service;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mgente00Service")
public class Mgente00ServiceImpl implements IMgente00Service{

	@Autowired	
	private IMgente00Dao mgente00dao;
	
	@Override
	public List<Mgente00> listMgente00ByCryteria(Mgente00 mgente00, List<String> listMusuco00) {		
		return mgente00dao.listMgente00ByCriteria(mgente00, listMusuco00);
	}
	
	@Override
	public List<Mgente00> listMgente00ByCodtte(Mgente00 mgente00, List<String> listMusuco00) {		
		return mgente00dao.listMgente00ByCodtte(mgente00, listMusuco00);
	}

	@Override
	public Mgente00 MgenteByCodite(String codite) {
		return mgente00dao.MgenteByCodite(codite);
	}

	@Override
	public void save(Mgente00 mgente00, Taudit00 taudit00) throws SQLException {
		mgente00dao.save(mgente00, taudit00);
		
	}

	@Override
	public void remove(Mgente00 mgente00, Taudit00 taudit00) {
		mgente00dao.delete(mgente00, taudit00);	
	}

	@Override
	public Mgente00 cargarDetalles(Integer idtrte) {
		return mgente00dao.cargarDetalles(idtrte);	
	}


	@Override
	public List<Mgente00> listMgente00Asesora(Mgente00 mgente00, List<String> listMusuco00) {
		return mgente00dao.listMgente00Asesora(mgente00, listMusuco00);
	}

	@Override
	public List<Mgente00> listMgente00Cliente(Mgente00 mgente00, List<String> listMusuco00) {
		return mgente00dao.listMgente00Cliente(mgente00, listMusuco00);
	}
	
}
