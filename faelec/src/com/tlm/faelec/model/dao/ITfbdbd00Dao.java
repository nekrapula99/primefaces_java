package com.tlm.faelec.model.dao;


import java.util.List;

import com.tlm.faelecEntities.model.entities.Tfbcbc00;
import com.tlm.faelecEntities.model.entities.Tfbdbd00;

public interface ITfbdbd00Dao  extends GenericDAO<Tfbdbd00>{
	
	public List<Tfbdbd00> listTfbdbd00ByCriteria(Tfbdbd00 tfbdbd00,List<String> listMusuco00);
	public List<Tfbdbd00> listTfbdbd00Criteria(Tfbcbc00 tfbcbc00);
	public List<Tfbdbd00> listTfbdbd00ByTfbcbc00(Tfbdbd00 tfbdbd00);
}
