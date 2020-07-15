package com.tlm.faelec.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMsegca00Dao;
import com.tlm.faelecEntities.model.entities.Msegca00;

@Repository
public class Msegca00DaoImpl extends AbstractDAO<Msegca00> implements IMsegca00Dao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Msegca00> getListByCriteria(Msegca00 msegca00) {
		Criteria c= getSessionFactory().getCurrentSession().createCriteria(Msegca00.class);
		
		if(msegca00.getAplseg() != null && !msegca00.getAplseg().equals("")){
			c.add(Restrictions.eq("aplseg", msegca00.getAplseg()));
		}
		
		if(msegca00.getCamseg() != null && !msegca00.getCamseg().equals("")){
			c.add(Restrictions.eq("camseg", msegca00.getCamseg()));
		}
		
		if(msegca00.getTabseg() != null && !msegca00.getTabseg().equals("")){
			c.add(Restrictions.eq("tabseg", msegca00.getTabseg()));
		}
		
		if(msegca00.getTabseg() != null && !msegca00.getTabseg().equals("")){
			c.add(Restrictions.eq("tabseg", msegca00.getTabseg()));
		}
		
		return c.list();
	}

	@Override
	public Map<String, String> getPermiByCriteria(Msegca00 msegca00) {
		
		Map<String, String> permisos = new HashMap<String, String>();		
		
		List<Msegca00> list = getListByCriteria(msegca00);  
		 
		for(Msegca00 m : list)
		{
			permisos.put(m.getCamseg().toLowerCase(), m.getTipseg());
	
		}	
		return permisos;
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

}
