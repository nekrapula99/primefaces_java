package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITqdpqp00Dao;

import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Repository
public class Tqdpqp00DaoImpl extends AbstractDAO<Tqdpqp00> implements ITqdpqp00Dao{


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;	
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Tqdpqp00> listTqdpqp00ByCriteria(Tqdpqp00 tqdpqp00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqdpqp00.class);
		
		//c.setFetchMode("tqdpqp00", FetchMode.DEFAULT).createAlias("tqdpqp00", "tqdpqp00",Criteria.LEFT_JOIN);
		c.setFetchMode("mpropr00", FetchMode.DEFAULT).createAlias("mpropr00", "mpropr00",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus00", FetchMode.DEFAULT).createAlias("mgenus00", "mgenus00",Criteria.LEFT_JOIN);
	
		if(tqdpqp00.isRegcqp()!=false){
			c.add(Restrictions.eq("regcqp", tqdpqp00.isRegcqp()));
			
		}
		
		return c.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Tqdpqp00> listTqdpqp00ByCriteria(Tqdpqp00 tqdpqp00) {
		
		String hql = "select tqdpqp FROM Tqdpqp00 as tqdpqp left join tqdpqp.tqdrqr00 as tqdrqr where tqdpqp.tqdrqr00 = :tqdrqr00 AND tqdpqp.regcqp = true order by tqdpqp.idqpqp asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqdrqr00", tqdpqp00.getTqdrqr00());
	    return query.list();
	   
	}
	
	
	
	
	public List<Tqdpqp00> listTqdpqp00Criteria(Tqdpqp00 tqdpqp00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqdpqp00.class);
		
		if(tqdpqp00.isRegcqp()!= false)
		{
			c.add(Restrictions.eq("regcqp", tqdpqp00.isRegcqp()));
		}
		c.addOrder(Property.forName("cacoqp").asc());
		
		return c.list();
	 }

}