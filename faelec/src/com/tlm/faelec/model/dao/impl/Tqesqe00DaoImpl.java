package com.tlm.faelec.model.dao.impl;

import java.util.List;
import org.hibernate.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITqesqe00Dao;

import com.tlm.faelecEntities.model.entities.Tqesqe00;

@Repository
public class Tqesqe00DaoImpl extends AbstractDAO<Tqesqe00> implements ITqesqe00Dao{


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
	public List<Tqesqe00> listTqesqe00ByCriteria(Tqesqe00 tqesqe00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqesqe00.class);
		
		c.setFetchMode("tqigqg00", FetchMode.DEFAULT).createAlias("tqigqg00", "tqigqg00",Criteria.LEFT_JOIN);
		c.setFetchMode("mestad00", FetchMode.DEFAULT).createAlias("mestad00", "mestad00",Criteria.LEFT_JOIN);
		
		if(tqesqe00.isRegcqe()!=false){
			c.add(Restrictions.eq("regcqe", tqesqe00.isRegcqe()));
			
		}
		
		return c.list();
	}
	
	@Override
	public Tqesqe00 getTqigqg00ByTqesqe00 (Tqesqe00 tqesqe00){
		String hql = "SELECT tqesqe00 FROM Tqesqe00 AS tqesqe00 WHERE tqesqe00.tqigqg00 =:tqigqg00";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqigqg00", tqesqe00.getTqigqg00());
	    return (Tqesqe00) query.uniqueResult();
	}

}