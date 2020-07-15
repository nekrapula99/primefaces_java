package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITfadfd00Dao;

import com.tlm.faelecEntities.model.entities.Tfadfd00;
import com.tlm.faelecEntities.model.entities.Tfbdbd00;

@Repository
public class Tfadfd00DaoImpl extends AbstractDAO<Tfadfd00> implements ITfadfd00Dao{


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
	public List<Tfadfd00> listTfadfd00ByCriteria(Tfadfd00 tfadfd00, List<String> listMusuco00) {
		
		String hql = "select tfadfd FROM Tfadfd00 as tfadfd  where tfadfd.regcfd = true order by tfadfd.iddffd asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    return query.list();
	   
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Tfadfd00> listTfadfd00ByTfacfc00(Tfadfd00 tfadfd00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tfadfd00.class);		
		c.add(Restrictions.eq("tfacfc00.idfcfc", tfadfd00.getTfacfc00().getIdfcfc()));
		c.add(Restrictions.eq("regcfd", tfadfd00.isRegcfd()));
		c.addOrder(Property.forName("tfacfc00.idfcfc").asc());
		c.addOrder(Property.forName("tqdrqr00.idtrqr").asc());
		c.addOrder(Property.forName("tqdrfg00.idqrfg").asc());
		return c.list();
	}
	
}
