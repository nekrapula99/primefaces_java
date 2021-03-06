package com.tlm.faelec.service.maestros.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMteste00Dao;
import com.tlm.faelec.service.maestros.IMteste00Service;
import com.tlm.faelecEntities.model.entities.Mteste00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Transactional
@Service("mteste00Service")
public class Mteste00ServiceImpl implements IMteste00Service{

	@Autowired		
	private IMteste00Dao imteste00dao;
	
	@Override
	public List<Mteste00> listMteste00ByCriteria(Mteste00 mteste00, List<String> listMusuco00) {
		return imteste00dao.listMteste00ByCriteria(mteste00, listMusuco00);
	}
	
	@Override
	public void save(Mteste00 mteste, Taudit00 taudit00) throws SQLException {
		imteste00dao.save(mteste, taudit00);
	}
	
	@Override
	public void removeMteste00(Mteste00 mteste00, Taudit00 taudit00) {
		imteste00dao.delete(mteste00, taudit00);	
	}

	@Override
	public void saveLista(List<Mteste00> mteste00Detalles,Taudit00 taudit00) {
		imteste00dao.saveLista(mteste00Detalles,taudit00);
	}

	@Override
	public List<Mteste00> listMteste00Detalles(Mteste00 mteste00) {
		return imteste00dao.listMteste00Detalles(mteste00);
	}

	@Override
	public void removeLista(Mteste00 mteste00) {
		imteste00dao.removeLista(mteste00);		
	}

	@Override
	public boolean obtenerTransicionValida(Integer vtIdesqe, Integer macIdedio) {
		return imteste00dao.obtenerTransicionValida(vtIdesqe, macIdedio);
	}

}