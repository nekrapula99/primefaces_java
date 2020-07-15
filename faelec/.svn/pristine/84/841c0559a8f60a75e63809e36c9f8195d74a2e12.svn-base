package com.tlm.faelec.service.maestros.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMtabla00Dao;
import com.tlm.faelec.service.maestros.IMtabla00Service;
import com.tlm.faelecEntities.model.entities.Mtabla00;

@Transactional
@Service("mtabla00Service")
public class Mtabla00ServiceImpl implements IMtabla00Service {

	@Autowired
	private IMtabla00Dao imtabla00Dao;


	@Override
	public List<Mtabla00> listMtabla00ByCriteria(String aplusu, Mtabla00 mtabla00) {		
		return imtabla00Dao.listMtabla00ByCriteria(aplusu, mtabla00);
	}


	@Override
	public Mtabla00 MtablaByCodtab(String codtab) {	
		return imtabla00Dao.MtablaByCodtab(codtab);
	}

}
