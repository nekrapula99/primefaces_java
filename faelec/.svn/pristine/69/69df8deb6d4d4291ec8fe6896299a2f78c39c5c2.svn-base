package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITfesfu00Dao;

import com.tlm.faelecEntities.model.entities.Tfesfu00;

@Repository
public class Tfesfu00DaoImpl extends AbstractDAO<Tfesfu00> implements ITfesfu00Dao{


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
	public List<Tfesfu00> listTfesfu00ByCriteria(Tfesfu00 tfesfu00,List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tfesfu00.class);
		
		c.setFetchMode("tqigqg00", FetchMode.DEFAULT).createAlias("tqigqg00", "tqigqg00",Criteria.LEFT_JOIN);
		c.setFetchMode("tqdfqf00", FetchMode.DEFAULT).createAlias("tqdfqf00", "tqdfqf00",Criteria.LEFT_JOIN);
		c.setFetchMode("tfacfc00", FetchMode.DEFAULT).createAlias("tfacfc00", "tfacfc00",Criteria.LEFT_JOIN);
		c.setFetchMode("mestad00", FetchMode.DEFAULT).createAlias("mestad00", "mestad00",Criteria.LEFT_JOIN);
		
		
		if(tfesfu00.isRegcfu()!=false){
			c.add(Restrictions.eq("regcfu", tfesfu00.isRegcfu()));
			
		}
		
		return c.list();
	}

}