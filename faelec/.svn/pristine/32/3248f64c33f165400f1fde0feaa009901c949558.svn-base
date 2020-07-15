package com.tlm.faelec.model.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMdespr00Dao;
import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mdespr00;


@Repository
public class Mdespr00DaoImpl extends AbstractDAO<Mdespr00> implements IMdespr00Dao
{

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Mdespr00> listMdespr00ByCriteria(Mdespr00 mdespr00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mdespr00.class);
		
		c.setFetchMode("mpropr00", FetchMode.DEFAULT)
		.createAlias("mpropr00", "mpropr00", Criteria.LEFT_JOIN);
		
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