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
import com.tlm.faelec.model.dao.ITqdrfg00Dao;

import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;

@Repository
public class Tqdrfg00DaoImpl extends AbstractDAO<Tqdrfg00> implements ITqdrfg00Dao{


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
	public List<Tqdrfg00> listTqdrfg00ByCriteria(Tqdrfg00 tqdrfg00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqdrfg00.class);
		
		c.setFetchMode("tqdfqf00", FetchMode.DEFAULT).createAlias("tqdfqf00", "tqdfqf00",Criteria.LEFT_JOIN);
		c.setFetchMode("tqdpqp00", FetchMode.DEFAULT).createAlias("tqdpqp00", "tqdpqp00",Criteria.LEFT_JOIN);

		if(tqdrfg00.isRegcfg()!=false){
			c.add(Restrictions.eq("regcfg", tqdrfg00.isRegcfg()));
			
		}
		
		return c.list();
	}

	@Override
	public List<Tqdrfg00> getTqdrfg00ByCuota(Tqdrfg00 tqdrfg00) {
		
		String hql = "select tqdrfg00 FROM Tqdrfg00 as tqdrfg00 where tqdrfg00.tqdfqf00 = :tqdfqf00 AND tqdrfg00.regcfg = true order by tqdrfg00.idqrfg asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqdfqf00", tqdrfg00.getTqdfqf00());
	    
	    return query.list();   

	}

}