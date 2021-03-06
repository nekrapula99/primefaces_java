package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.utils.Utils;
import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITqigqg00Dao;

import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Tqdpqp00;
import com.tlm.faelecEntities.model.entities.Tqdrqr00;
import com.tlm.faelecEntities.model.entities.Tqesqe00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

@Repository
public class Tqigqg00DaoImpl extends AbstractDAO<Tqigqg00> implements ITqigqg00Dao{


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
	public List<Tqigqg00> listTqigqg00ByCriteria(Tqigqg00 tqigqg00, List<String> listMusuco00) {
		
		String hql = "select tqigqg FROM Tqigqg00 as tqigqg left join tqigqg.mtiptx00 as mtiptx where tqigqg.regcqg = true order by tqigqg.idtqqg,tqigqg.nroqqg asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);

		List<Tqigqg00> obj = query.list();
		for(Tqigqg00 tqigqg: obj){
			Hibernate.initialize(tqigqg.getTqesqe00s());
		 }
	    return obj;
	}
	
	public Tqigqg00 getObjectTqigqg00(Tqigqg00 tqigqg00) {
		
		String hql = "select tqigqg00 FROM Tqigqg00 as tqigqg where  tqigqg.idtqqg =  (SELECT MAX(idtqqg) FROM com.tlm.faelecEntities.model.entities.Tqigqg00) AND tqigqg.regcqg = true order by tqigqg.fecrqg desc";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		return (Tqigqg00) query.uniqueResult();
	}
	
	
	
	
	public List<Tqigqg00> listTqigqg00Criteria(Tqigqg00 tqigqg00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqigqg00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(tqigqg00.isRegcqg()!= false)
		{
			c.add(Restrictions.eq("regcqg", tqigqg00.isRegcqg()));
		}
		c.addOrder(Property.forName("nroqqg").asc());
		
		return c.list();
	 }
	
	public Tqigqg00 cargarDetalles(Tqigqg00 tqigqg00){
		Session session = this.getSessionFactory().getCurrentSession();
		Tqigqg00 tqigqg = (Tqigqg00)session.load(Tqigqg00.class, tqigqg00.getIdtqqg());
		
		// Hibernate.initialize(mpropr.getMdespr00s());
		 //Hibernate.initialize(mpropr.getMvarpr00s());
		 
		 //for(Mvarpr00 mvarpr00 : mpropr.getMvarpr00s()){
			// Hibernate.initialize(mvarpr00.getMdevpr00s());
		// }

	    return tqigqg;
	}

	@Override
	public List<Tqesqe00> listTqigqg00ByCriteriaFechas(Tqesqe00 tqesqe00) {
		
			String hql = "SELECT tqesqe00 FROM Tqesqe00 AS tqesqe00 WHERE tqesqe00.tqigqg00 =:tqigqg00 AND tqesqe00.mestad00 = :mestad00 ORDER BY tqesqe00.feacqe DESC";
		    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		    query.setParameter("tqigqg00", tqesqe00.getTqigqg00());
		    query.setParameter("mestad00", tqesqe00.getMestad00());
		    
		    return query.list();
		    
		    //Criteria c = getSessionFactory().getCurrentSession().createCriteria(Tqigqg00.class);
	}
		
		//c.addOrder(Property.forName("feacqe").asc());	
	
	
	public Tqigqg00 cargarDetalles(Integer idtqqg){
		Tqigqg00 tqigqg00 = new Tqigqg00();
		String hql = "select tqdrqr FROM Tqdrqr00 as tqdrqr where tqdrqr.tqigqg00.idtqqg = :idtqqg";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtqqg", idtqqg);
	    tqigqg00.setTqdrqr00s((ArrayList<Tqdrqr00>)query.list());	    
	    
	    return tqigqg00;
	}
	
	//Pendiente por realizar
	public Tqigqg00 cargarListaEstado(Integer idtqqg){
		Tqigqg00 tqigqg00 = new Tqigqg00();
		String hql = "select tqesqe FROM Tqesqe00 as tqesqe where tqesqe.tqigqg00.idtqqg = :idtqqg";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtqqg", idtqqg);
	    tqigqg00.setTqesqe00s((ArrayList<Tqesqe00>)query.list());	    
	    		
		//Tqigqg00 tqigqg = (Tqigqg00)session.load(Mpropr00.class, tqigqg00.getIdtqqg());
		
		List<Tqigqg00> obj = query.list();
		for(Tqigqg00 tqigqg: obj){
			Hibernate.initialize(tqigqg.getTqesqe00s());
		 }

	    return tqigqg00;
	}
		
}