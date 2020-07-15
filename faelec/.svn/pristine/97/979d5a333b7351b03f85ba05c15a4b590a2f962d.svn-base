package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMcotos00Dao;
import com.tlm.faelecEntities.model.entities.Mcotos00;

@Repository
public class Mcotos00DaoImpl extends AbstractDAO<Mcotos00> implements IMcotos00Dao{

	
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
	public List<Mcotos00> listMcotos00ByCriteria(Mcotos00 mcotos00,	List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mcotos00.class);
		
		c.setFetchMode("mcotes001", FetchMode.DEFAULT).createAlias("mcotes001", "mcotes001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgente001", FetchMode.DEFAULT).createAlias("mgente001", "mgente001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus001", FetchMode.DEFAULT).createAlias("mgenus001", "mgenus001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus002", FetchMode.DEFAULT).createAlias("mgenus002", "mgenus002",Criteria.LEFT_JOIN);

		if(mcotos00.isRegtos()!=false){
			c.add(Restrictions.eq("regtos", mcotos00.isRegtos()));
			
		}
		
		return c.list();
	}



}