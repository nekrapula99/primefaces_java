package com.tlm.faelec.model.dao.impl;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMvaria00Dao;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mvaria00;

@Repository
public class Mvaria00DaoImpl extends AbstractDAO<Mvaria00> implements IMvaria00Dao
{

	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Mvaria00> listMvaria00(Mvaria00 mvaria00, List<String> listMusuco00, Mgenus00 mgenus00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mvaria00.class);
		
		if(!Utils.isEmptyOrNull(listMusuco00))
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		if(mvaria00.isRegivt()!= false)
		{
			c.add(Restrictions.eq("regivt", mvaria00.isRegivt()));
		}
	
		if(mgenus00 != null){
			c.add(Restrictions.eq("mgenus00", mgenus00));
			
		}
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		
		c.addOrder(Property.forName("mconca00.codcia").asc());

		return c.list();
	}


}