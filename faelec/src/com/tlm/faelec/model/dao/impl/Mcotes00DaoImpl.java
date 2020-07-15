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
import com.tlm.faelec.model.dao.IMcotes00Dao;
import com.tlm.faelecEntities.model.entities.Mcotes00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Repository
public class Mcotes00DaoImpl extends AbstractDAO<Mcotes00> implements IMcotes00Dao{

	
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
	public List<Mcotes00> listMcotes00ByCriteria(Mcotes00 mcotes00,	List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mcotes00.class);
		
		c.setFetchMode("mgenus001", FetchMode.DEFAULT).createAlias("mgenus001", "mgenus001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus002", FetchMode.DEFAULT).createAlias("mgenus002", "mgenus002",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus003", FetchMode.DEFAULT).createAlias("mgenus003", "mgenus003",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus004", FetchMode.DEFAULT).createAlias("mgenus004", "mgenus004",Criteria.LEFT_JOIN);
		c.setFetchMode("mgente001", FetchMode.DEFAULT).createAlias("mgente001", "mgente001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus005", FetchMode.DEFAULT).createAlias("mgenus005", "mgenus005",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus006", FetchMode.DEFAULT).createAlias("mgenus006", "mgenus006",Criteria.LEFT_JOIN);
		
		if(mcotes00.isRegtes()!=false){
			c.add(Restrictions.eq("regtes", mcotes00.isRegtes()));
			
		}
		
		return c.list();
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mcotes00> listMcotes00ByCriteria(Mcotes00 mcotes00, List<String> listMusuco00) {
		
		String hql = "select mcotes00 FROM Mcotes00 as mcotes00  where mcotes00.regtes = true order by mcotes00.idctes,mcotes00.coctes asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    return query.list();
	   
	}
	
	
	public List<Mcotes00> listMcotes00Criteria(Mcotes00 mcotes00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mcotes00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(mcotes00.isRegtes()!= false)
		{
			c.add(Restrictions.eq("regtes", mcotes00.isRegtes()));
		}
		c.addOrder(Property.forName("coctes").asc());
		
		return c.list();
	 }



}