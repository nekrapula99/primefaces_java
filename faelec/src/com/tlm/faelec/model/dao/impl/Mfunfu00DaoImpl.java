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
import com.tlm.faelec.model.dao.IMfunfu00Dao;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mfunfu00;
import com.tlm.faelecEntities.model.entities.Mpropr00;

@Repository
public class Mfunfu00DaoImpl extends AbstractDAO<Mfunfu00> implements IMfunfu00Dao{

	
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
	public List<Mfunfu00> listMfunfu00ByCriteria(Mfunfu00 mfunfu00,	List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mfunfu00.class);
		
		c.setFetchMode("mgenus001", FetchMode.DEFAULT).createAlias("mgenus001", "mgenus001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus002", FetchMode.DEFAULT).createAlias("mgenus002", "mgenus002",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus003", FetchMode.DEFAULT).createAlias("mgenus003", "mgenus003",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus004", FetchMode.DEFAULT).createAlias("mgenus004", "mgenus004",Criteria.LEFT_JOIN);
		c.setFetchMode("mconca001", FetchMode.DEFAULT).createAlias("mconca001", "mconca001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus005", FetchMode.DEFAULT).createAlias("mgenus005", "mgenus005",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus006", FetchMode.DEFAULT).createAlias("mgenus006", "mgenus006",Criteria.LEFT_JOIN);
		
		if(mfunfu00.isRegufu()!=false){
			c.add(Restrictions.eq("regufu", mfunfu00.isRegufu()));
			
		}
		
		return c.list();
	}
	
	public List<Mfunfu00> listMfunfu00Criteria(Mfunfu00 mfunfu00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mfunfu00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(mfunfu00.isRegufu()!= false)
		{
			c.add(Restrictions.eq("regufu", mfunfu00.isRegufu()));
		}
		c.addOrder(Property.forName("cocufu").asc());
		
		return c.list();
	 }



}