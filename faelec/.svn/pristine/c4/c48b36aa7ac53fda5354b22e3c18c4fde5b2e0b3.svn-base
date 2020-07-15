package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMdesgr00Dao;
import com.tlm.faelecEntities.model.entities.Mdesgr00;



@Repository
public class Mdesgr00DaoImpl extends AbstractDAO<Mdesgr00> implements IMdesgr00Dao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Mdesgr00> listMdesgr00ByCriteria(Mdesgr00 mdesgr00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mdesgr00.class);
		
		c.setFetchMode("mgenus00", FetchMode.DEFAULT)
		.createAlias("mgenus00", "mgenus00", Criteria.LEFT_JOIN);	
		
		c.setFetchMode("midiom00", FetchMode.DEFAULT)
		.createAlias("midiom00", "midiom00", Criteria.LEFT_JOIN);	
		
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
