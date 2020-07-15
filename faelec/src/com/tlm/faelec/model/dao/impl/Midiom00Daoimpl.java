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
import com.tlm.faelec.model.dao.IMidiom00Dao;
import com.tlm.faelecEntities.model.entities.Midiom00;

@Repository
public class Midiom00Daoimpl extends AbstractDAO<Midiom00> implements IMidiom00Dao{

	@Autowired
	private SessionFactory sessionFactory;


	@SuppressWarnings("unchecked")
	public List<Midiom00> listMidiom00ByCriteria(Midiom00 midiom00,List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Midiom00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		if(midiom00.getRegidi()!=false)
		{
		  c.add(Restrictions.eq("regidi", midiom00.getRegidi()));	
		}
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);	
		c.addOrder(Property.forName("mconca00.codcia").asc());
		c.addOrder(Property.forName("codidi").asc());
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
