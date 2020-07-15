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
import com.tlm.faelec.model.dao.IMregcg00Dao;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mregcg00;

@Repository
public class Mregcg00DaoImpl extends AbstractDAO<Mregcg00> implements IMregcg00Dao{
	

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
	public List<Mregcg00> listMregcg00ByCriteria(Mregcg00 mregcg00,	List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mregcg00.class);
		
		c.setFetchMode("mgenus001", FetchMode.DEFAULT).createAlias("mgenus001", "mgenus001", Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus002", FetchMode.DEFAULT).createAlias("mgenus002", "mgenus002", Criteria.LEFT_JOIN);
		
		if(mregcg00.isRegegc()!=false){
			c.add(Restrictions.eq("regegc", mregcg00.isRegegc()));			
		}
		
		return c.list();
	}
	
	public List<Mregcg00> listMregcg00Criteria(Mregcg00 mregcg00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mregcg00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(mregcg00.isRegegc()!= false)
		{
			c.add(Restrictions.eq("regegc", mregcg00.isRegegc()));
		}
		c.addOrder(Property.forName("coregc").asc());
		
		return c.list();
	 }


}
