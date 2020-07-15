package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMestad00Dao;
import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mestad00;
import com.tlm.faelecEntities.model.entities.Mtipre00;


@Repository
public class Mestad00DaoImpl extends AbstractDAO<Mestad00> implements IMestad00Dao
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
	public List<Mestad00> listMestad00ByCriteria(Mestad00 mestad00,  List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mestad00.class);
		
		if(mestad00.getRegtes()!= false)
		{
			c.add(Restrictions.eq("regtes", mestad00.getRegtes()));
		}
		if(mestad00.getMtiptx00()!=null && mestad00.getMtiptx00().getIdtptx()!=null)
		{
		  c.add(Restrictions.eq("mtiptx00.idtptx",mestad00.getMtiptx00().getIdtptx()));	
		}
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mtiptx00", FetchMode.DEFAULT).createAlias("mtiptx00", "mtiptx00", Criteria.LEFT_JOIN);	
		
		c.addOrder(Property.forName("mconca00").asc());
		c.addOrder(Property.forName("ideses").asc());
		
		//c.addOrder(Property.forName("mtiptx00.cotrtx").asc());
		//c.addOrder(Property.forName("cotres").asc());
		
		return c.list();
	}
	
	@Override	//Se busca el estado para el pedido
	public Mestad00 mestad00ByTcpedi00(Mestad00 mestad00){
		String hql = "SELECT mestad FROM Mestad00 AS mestad WHERE mestad.mconca00 = :mconca00 AND mestad.mtiptx00 = :mtiptx00 AND mestad.macdio00 = :macdio00";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("mconca00", mestad00.getMconca00());
		query.setParameter("mtiptx00", mestad00.getMtiptx00());
		//query.setParameter("macdio00", mestad00.getMacdio00());
		return (Mestad00) query.uniqueResult();
	}

	
	@Override
	public Mestad00 consultarObjetoMestad00(Mestad00 mestad00) {
		Criteria mestad = getSessionFactory().getCurrentSession().createCriteria(Mestad00.class);
		if(mestad00.getCotres()!=null && !mestad00.getCotres().equals(""))
		{
			mestad.add(Restrictions.eq("cotres", mestad00.getCotres()));	
		}
		return (Mestad00) mestad.uniqueResult();
		
	}
	
	
}