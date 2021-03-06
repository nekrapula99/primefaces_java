package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMpreci00Dao;
import com.tlm.faelecEntities.model.entities.Mpreci00;

@Repository
public class Mpreci00DaoImpl extends AbstractDAO<Mpreci00> implements IMpreci00Dao{
	
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
	public List<Mpreci00> listMpreci00ByCriteria(Mpreci00 mpreci00,	List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mpreci00.class);
		
		if(mpreci00.isRegcci()!=false){
			c.add(Restrictions.eq("regcci", mpreci00.isRegcci()));
			
		}
		
		if(listMusuco00!=null){
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00), Restrictions.isNull("mconca00")));
		}
		
		//c.setFetchMode("mgente00", FetchMode.DEFAULT).createAlias("mgente00", "mgente00",Criteria.LEFT_JOIN);
		//c.setFetchMode("mpropr00", FetchMode.DEFAULT).createAlias("mpropr00", "mpropr00",Criteria.LEFT_JOIN);
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00",Criteria.LEFT_JOIN);
		//c.setFetchMode("mgenus00", FetchMode.DEFAULT).createAlias("mgenus00", "mgenus00",Criteria.LEFT_JOIN);
		
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mpreci00> listMpreci00(Mpreci00 mpreci00, List<String> companiasUsu) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mpreci00.class);
		String hql ="";
		//System.out.println("mpreci00.getMgente00() en sql: "+mpreci00.getMgente00());
		if(mpreci00.getMgente00() == null){
			System.out.println("flag if");
			 hql = "SELECT mpreci FROM Mpreci00 AS mpreci WHERE mpreci.mgente00 IS NULL and mpreci.mpropr00 = :mpropr00 and " +
			    "mpreci.mconca00 = :mconca00 and mpreci.regcci = :regcci";
			 
			  Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			  query.setParameter("mpropr00", mpreci00.getMpropr00());
			  query.setParameter("mconca00", mpreci00.getMconca00());
			  query.setParameter("regcci", true);
			  return query.list();
			 
		}else {
			System.out.println("flag else");
		 hql = "SELECT mpreci FROM Mpreci00 AS mpreci WHERE mpreci.mgente00= :mgente00 and mpreci.mpropr00 = :mpropr00 and " +
				    "mpreci.mconca00 = :mconca00 and mpreci.regcci = :regcci";
		 
			  Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			  query.setParameter("mgente00", mpreci00.getMgente00());
			  query.setParameter("mpropr00", mpreci00.getMpropr00());
			  query.setParameter("mconca00", mpreci00.getMconca00());
			  query.setParameter("regcci", true);
			  return query.list();
			
		}	
			  
		
	}

}
