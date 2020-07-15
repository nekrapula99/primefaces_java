package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMevent00Dao;
import com.tlm.faelecEntities.model.entities.Mevent00;

@Repository
public class Mevent00DaoImpl extends AbstractDAO<Mevent00> implements IMevent00Dao {

	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<Mevent00> listMevent00ByCriteria(Mevent00 mevent00,List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mevent00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT)
		  .createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		
		return c.list();
	}
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
