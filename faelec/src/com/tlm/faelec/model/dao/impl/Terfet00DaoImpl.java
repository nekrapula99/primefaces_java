package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITerfet00Dao;

import com.tlm.faelecEntities.model.entities.Terfet00;

@Repository
public class Terfet00DaoImpl extends AbstractDAO<Terfet00> implements ITerfet00Dao{


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
	public List<Terfet00> listTerfet00ByCriteria(Terfet00 terfet00,List<String> listMusuco00) {
		String hql ="";
		
		 hql = "SELECT terfet FROM Terfet00 AS terfet WHERE terfet.tfacfc00 = :tfacfc00 " +
				    "and terfet.regcet = :regcet";
		 
			  Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			  query.setParameter("tfacfc00", terfet00.getTfacfc00());
			  query.setParameter("regcet", true);
			  return query.list();
	}

}