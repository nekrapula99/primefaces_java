package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tfbcbc00;
import com.tlm.faelecEntities.model.entities.Tfbdbd00;

public interface ITfbdbd00Service {
	
	public List<Tfbdbd00> listTfbdbd00ByCriteria(Tfbdbd00 tfbdbd00,List<String> listMusuco00);
	public void save(Tfbdbd00 tfbdbd00, Taudit00 taudit00) throws SQLException;
	public void removeTfbdbd00 (Tfbdbd00 tfbdbd00, Taudit00 taudit00);
	public List<Tfbdbd00> listTfbdbd00Criteria(Tfbcbc00 tfbcbc00);
	public List<Tfbdbd00> listTfbdbd00ByTfbcbc00(Tfbdbd00 tfbdbd00);
}


