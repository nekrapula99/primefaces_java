package com.tlm.faelec.service.trans.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlm.faelec.model.dao.ITfadfd00Dao;
import com.tlm.faelec.service.trans.ITfadfd00Service;
import com.tlm.faelecEntities.model.entities.Tfadfd00;
import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tfbdbd00;

@Transactional
@Service("tfadfd00Service")
public class Tfadfd00ServiceImpl implements ITfadfd00Service{

	@Autowired
	private ITfadfd00Dao itfadfd00Dao;
	
	@Override
	public List<Tfadfd00> listTfadfd00ByCriteria(Tfadfd00 tfadfd00,	List<String> listMusuco00) {
		return itfadfd00Dao.listTfadfd00ByCriteria(tfadfd00,listMusuco00);
	}
	
	@Override
	public void save(Tfadfd00 tfadfd00, Taudit00 taudit00) throws SQLException {
		itfadfd00Dao.save(tfadfd00, taudit00);
		
	}

	@Override
	public void removeTfadfd00(Tfadfd00 tfadfd00, Taudit00 taudit00) {
		itfadfd00Dao.delete(tfadfd00,taudit00);
		
	}

	@Override
	public List<Tfadfd00> listTfadfd00ByTfacfc00(Tfadfd00 tfadfd00) {
		return itfadfd00Dao.listTfadfd00ByTfacfc00(tfadfd00);
	}	

}



