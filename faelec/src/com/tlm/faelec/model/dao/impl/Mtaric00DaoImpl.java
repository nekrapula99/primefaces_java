package com.tlm.faelec.model.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMtaric00Dao;
import com.tlm.faelecEntities.model.entities.Mtaric00;

@Repository
public class Mtaric00DaoImpl extends AbstractDAO<Mtaric00> implements IMtaric00Dao{
	

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
	public List<Mtaric00> listMtaric00ByCriteria(Mtaric00 mtaric00,	List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mtaric00.class);
		
		c.setFetchMode("mgenus001", FetchMode.DEFAULT).createAlias("mgenus001", "mgenus001", Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus002", FetchMode.DEFAULT).createAlias("mgenus002", "mgenus002", Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus003", FetchMode.DEFAULT).createAlias("mgenus003", "mgenus003", Criteria.LEFT_JOIN);
		
		if(mtaric00.isRegcic()!=false){
			c.add(Restrictions.eq("regcic", mtaric00.isRegcic()));			
		}
		
		return c.list();
	}


}
