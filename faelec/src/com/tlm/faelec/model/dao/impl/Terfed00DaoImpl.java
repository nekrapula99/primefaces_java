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
import com.tlm.faelec.model.dao.ITerfed00Dao;

import com.tlm.faelecEntities.model.entities.Terfed00;

@Repository
public class Terfed00DaoImpl extends AbstractDAO<Terfed00> implements ITerfed00Dao{


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
	public List<Terfed00> listTerfed00ByCriteria(Terfed00 terfed00,List<String> listMusuco00) {
		
		String hql ="";
		
		 hql = "SELECT terfed FROM Terfed00 AS terfed WHERE terfed.tfacfc00 = :tfacfc00 " +
				    "and terfed.regced = :regced";
		 
			  Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			  query.setParameter("tfacfc00", terfed00.getTfacfc00());
			  query.setParameter("regced", true);
			  return query.list();
		
	}

}