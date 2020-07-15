package com.tlm.faelec.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMdecai00Dao;
import com.tlm.faelecEntities.model.entities.Mdecai00;

@Repository
public class Mdecai00DaoImpl extends AbstractDAO<Mdecai00> implements IMdecai00Dao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Mdecai00> listMdecai00ByCriteria(Mdecai00 mdecai00) {
		
		Criteria c =getSessionFactory().getCurrentSession().createCriteria(Mdecai00.class);
		
		if (mdecai00.getId().getApldei() != null && !mdecai00.getId().getApldei().equals("")) {
			c.add(Restrictions.like("id.apldei",mdecai00.getId().getApldei()));
		}
		
		if (mdecai00.getId().getCamdei() != null && !mdecai00.getId().getCamdei().equals("")) {
			c.add(Restrictions.like("id.camdei",mdecai00.getId().getCamdei()));
		}
		
		if (mdecai00.getId().getCoddei() != null && !mdecai00.getId().getCoddei().equals("")) {
			c.add(Restrictions.like("id.coddei",mdecai00.getId().getCoddei()));
		}
		
		if (mdecai00.getId().getTabdei() != null && !mdecai00.getId().getTabdei().equals("")) {
			c.add(Restrictions.like("id.tabdei",mdecai00.getId().getTabdei()));
		}		
		
		c.setFetchMode("maplic00", FetchMode.DEFAULT)
		  .createAlias("maplic00", "maplic00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mtabla00", FetchMode.DEFAULT)
		  .createAlias("mtabla00", "mtabla00", Criteria.LEFT_JOIN);
		
		return c.list();
	}

	@Override
	public Map<String, Object> hmMdecai00ByCriteria(Mdecai00 mdecai00) {
		Map<String, Object> hm = new HashMap<String, Object>();
		Map<String, String> hmDescrip = new HashMap<String, String>();
		Map<String, String> hmAyuda = new HashMap<String, String>();
		
		List<Mdecai00> list = listMdecai00ByCriteria(mdecai00);  
		 
		for(Mdecai00 m : list){
			hmDescrip.put(m.getId().getCamdei().toLowerCase(), m.getDesdei());
			hmAyuda.put(m.getId().getCamdei().toLowerCase(), m.getDesayu());
		}		
		
		hm.put("HmDescrip", hmDescrip);
		hm.put("HmAyuda", hmAyuda);		
		
		return hm;	

	}
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}
