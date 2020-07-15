package com.tlm.faelec.model.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITresre00Dao;

import com.tlm.faelecEntities.model.entities.Tresre00;

@Repository
public class Tresre00DaoImpl extends AbstractDAO<Tresre00> implements ITresre00Dao{


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
	public List<Tresre00> listTresre00ByCriteria(Tresre00 tresre00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tresre00.class);
		
		c.setFetchMode("tqdrqr00", FetchMode.DEFAULT).createAlias("tqdrqr00", "tqdrqr00",Criteria.LEFT_JOIN);
		c.setFetchMode("mestad00", FetchMode.DEFAULT).createAlias("mestad00", "mestad00",Criteria.LEFT_JOIN);
		
		if(tresre00.isRegcre()!=false){
			c.add(Restrictions.eq("regcre", tresre00.isRegcre()));
			
		}
		
		return c.list();
	}

}