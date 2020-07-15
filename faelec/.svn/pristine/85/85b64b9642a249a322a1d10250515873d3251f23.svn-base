package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITqdrqr00Dao;

import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Tqdfqf00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqesqe00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Repository
public class Tqdrqr00DaoImpl extends AbstractDAO<Tqdrqr00> implements ITqdrqr00Dao{


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
	public List<Tqdrqr00> listTqdrqr00ByCriteria(Tqdrqr00 tqdrqr00) {
		
		String hql = "select tqdrqr00 FROM Tqdrqr00 as tqdrqr00 where tqdrqr00.tqigqg00 = :tqigqg00 AND tqdrqr00.regcqr = true order by tqdrqr00.idtrqr,tqdrqr00.crqrqr asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqigqg00", tqdrqr00.getTqigqg00());	    

		List<Tqdrqr00> obj = query.list();
		for(Tqdrqr00 tqdrqr: obj){
			Hibernate.initialize(tqdrqr.getTresre00s());
		 }
	    return obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tqdrqr00> listTqdrqr00ByCriteria(Tqigqg00 tqigqg00) {
		
		String hql = "select tqdrqr00 FROM Tqdrqr00 as tqdrqr00 where tqdrqr00.tqigqg00 = :tqigqg00 AND tqdrqr00.regcqr = true order by tqdrqr00.idtrqr,tqdrqr00.crqrqr asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqigqg00", tqigqg00);
	    return query.list();   
	}
	
	@Override
	public List<Tqdrqr00> getTqdrqr00ByTqigqg00 (Tqdrqr00 tqdrqr00){
		String hql = "SELECT tqdrqr00 FROM Tqdrqr00 AS tqdrqr00 WHERE  tqdrqr00.tqigqg00 = :tqigqg00";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqigqg00", tqdrqr00.getTqigqg00());
	    return query.list();
	}
	
	public Tqdrqr00 cargarDetalles(Integer idtrqr){
		Tqdrqr00 tqdrqr00 = new Tqdrqr00();
		String hql = "select tqdpqp FROM Tqdpqp00 as tqdpqp where tqdpqp.tqdrqr00.idtrqr = :idtrqr";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtrqr", idtrqr);
	    tqdrqr00.setTqdpqp00s((ArrayList<Tqdpqp00>)query.list());	    
	    
	    return tqdrqr00;
	}

	@Override
	public Tqdrqr00 getTqdrqr00ByObject(Tqigqg00 tqigqg00) {
		String hql = "SELECT tqdrqr00 FROM Tqdrqr00 AS tqdrqr00 WHERE  tqdrqr00.tqigqg00 = :tqigqg00";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("tqigqg00", tqigqg00);
	    //query.setParameter("tqigqg00", tqdrqr00.getTqigqg00());
		return (Tqdrqr00) query.uniqueResult();
	}
	
	
	/*public Tqdrqr00 cargarDetalles(Tqdrqr00 tqdrqr00){
		Session session = this.getSessionFactory().getCurrentSession();
		Tqdrqr00 tqdrqr = (Tqdrqr00)session.load(Tqdrqr00.class, tqdrqr00.getIdtrqr());
		
		 Hibernate.initialize(tqdrqr.getTqdpqp00s());
		
	    return tqdrqr;
	}*/
	

}