package com.tlm.faelec.service.maestros.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.IMaccgr00Dao;
import com.tlm.faelec.service.maestros.IMaccgr00Service;
import com.tlm.faelecEntities.model.entities.Maccgr00;


@Transactional
@Service("maccgr00Service")
public class Maccgr00ServiceImpl implements IMaccgr00Service {

	@Autowired
	private IMaccgr00Dao imaccgr00Dao;
	
	@Override
	public List<Maccgr00> listMaccgr00ByCriteria(Maccgr00 maccgr00) {
	  return imaccgr00Dao.listMaccgr00ByCriteria(maccgr00);
	}

}
