package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITqdfqf00Dao;

import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdrfg00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Repository
public class Tqdfqf00DaoImpl extends AbstractDAO<Tqdfqf00> implements ITqdfqf00Dao{


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
	public List<Tqdfqf00> listTqdfqf00ByCriteria(Tqdfqf00 tqdfqf00, List<String> listMusuco00) {
		String hql = "select tqdfqf00 FROM Tqdfqf00 as tqdfqf00 where tqdfqf00.tqigqg00 = :tqdfqf00 AND tqdfqf00.regcqf = true order by tqdfqf00.idcpqf,tqdfqf00.nrcpqf asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqdfqf00", tqdfqf00.getTqigqg00());
	    
	    List<Tqdfqf00> obj = query.list();
		for(Tqdfqf00 tqdfqf: obj){
			Hibernate.initialize(tqdfqf.getTfesfu00s());
		 }
	    return obj;	    
	   // return query.list();
	   
	}
	
	@SuppressWarnings("unchecked")
	public List<Tqdfqf00> listTqdfqf00ByCriteria(Tqigqg00 tqigqg00) {
		String hql = "select tqdfqf00 FROM Tqdfqf00 as tqdfqf00 where tqdfqf00.tqigqg00 = :tqdfqf00 AND tqdfqf00.regcqf = true order by tqdfqf00.idcpqf,tqdfqf00.nrcpqf asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqdfqf00", tqigqg00);
	    return query.list();
	   
	}
	
	
	public Tqdfqf00 cargarDetalles(Integer idcpqf){
		Tqdfqf00 tqdfqf00 = new Tqdfqf00();
		String hql = "select tqdrfg FROM Tqdrfg00 as tqdrfg where tqdrfg.tqdfqf00.idcpqf = :idcpqf";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idcpqf", idcpqf);
	    tqdfqf00.setTqdrfg00s((ArrayList<Tqdrfg00>)query.list());	    
	    
	    return tqdfqf00;
	}
	
	@Override
	public List<Tqdfqf00> getTqdfqf00ByObject(Tqigqg00 tqigqg00) {
		String hql = "SELECT tqdfqf00 FROM Tqdfqf00 AS tqdfqf00 WHERE  tqdfqf00.tqigqg00 = :tqigqg00 AND tqdfqf00.regcqf = true order by tqdfqf00.idcpqf,tqdfqf00.nrcpqf asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("tqigqg00", tqigqg00);
	    //query.setParameter("tqigqg00", tqdrqr00.getTqigqg00());
	    return query.list();
	}

		
		/*c.setFetchMode("mgenus001", FetchMode.DEFAULT).createAlias("mgenus001", "mgenus001",Criteria.LEFT_JOIN);
		c.setFetchMode("mgenus002", FetchMode.DEFAULT).createAlias("mgenus002", "mgenus002",Criteria.LEFT_JOIN);
		c.setFetchMode("mgente00", FetchMode.DEFAULT).createAlias("mgente00", "mgente00",Criteria.LEFT_JOIN);
		c.setFetchMode("mcotes00", FetchMode.DEFAULT).createAlias("mcotes00", "mcotes00",Criteria.LEFT_JOIN);
	
		if(tqdfqf00.isRegcqf()!=false){
			c.add(Restrictions.eq("regcqf", tqdfqf00.isRegcqf()));
			
		}
		
		return c.list();
	}*/
	
	/*public List<Tqdfqf00> listTqdfqf00Criteria(Tqdfqf00 tqdfqf00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqdfqf00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(tqdfqf00.isRegcqf()!= false)
		{
			c.add(Restrictions.eq("regcqf", tqdfqf00.isRegcqf()));
		}
		c.addOrder(Property.forName("nrcpqf").asc());
		
		return c.list();
	 }*/
	

}