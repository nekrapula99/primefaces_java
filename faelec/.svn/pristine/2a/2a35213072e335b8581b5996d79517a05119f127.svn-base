package com.tlm.faelec.model.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITqrerv00Dao;

import com.tlm.faelecEntities.model.entities.Tqrerv00;

@Repository
public class Tqrerv00DaoImpl extends AbstractDAO<Tqrerv00> implements ITqrerv00Dao{


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tqrerv00> listTqrerv00ByCriteria(Tqrerv00 tqrerv00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqrerv00.class);
		
		c.setFetchMode("tqenqv00", FetchMode.DEFAULT).createAlias("tqenqv00", "tqenqv00",Criteria.LEFT_JOIN);
		c.setFetchMode("mcotes00", FetchMode.DEFAULT).createAlias("mcotes00", "mcotes00",Criteria.LEFT_JOIN);

		if(tqrerv00.isRegcrv()!=false){
			c.add(Restrictions.eq("regcrv", tqrerv00.isRegcrv()));
			
		}
		
		return c.list();
	}

}