package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMmesje00Dao;
import com.tlm.faelecEntities.model.entities.Mmesje00;
//import com.tlm.faelec.model.vo.Musuco00VO;

@Repository
public class Mmesje00DaoImpl extends AbstractDAO<Mmesje00> implements IMmesje00Dao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Mmesje00> listMmesje00ByCriteria(Mmesje00 mmesje00, List<String> listMusuco00) {
		// TODO Apéndice de método generado automáticamente
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mmesje00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT)
		  .createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);		
		
		c.addOrder(Property.forName("mconca00.codcia").asc());
		c.addOrder(Property.forName("codums").asc());
	
		
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
