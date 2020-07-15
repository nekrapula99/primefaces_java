package com.tlm.faelec.model.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITqenqv00Dao;

import com.tlm.faelecEntities.model.entities.Tqenqv00;

@Repository
public class Tqenqv00DaoImpl extends AbstractDAO<Tqenqv00> implements ITqenqv00Dao{


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
	public List<Tqenqv00> listTqenqv00ByCriteria(Tqenqv00 tqenqv00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqenqv00.class);
		
		c.setFetchMode("tqigqg00", FetchMode.DEFAULT).createAlias("tqigqg00", "tqigqg00",Criteria.LEFT_JOIN);

		if(tqenqv00.isRegcqv()!=false){
			c.add(Restrictions.eq("regcqg", tqenqv00.isRegcqv()));
			
		}
		
		return c.list();
	}

}