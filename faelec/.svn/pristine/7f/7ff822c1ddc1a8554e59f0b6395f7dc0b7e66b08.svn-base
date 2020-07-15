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
import com.tlm.faelec.model.dao.IMtipre00Dao;
import com.tlm.faelecEntities.model.entities.Mtipre00;

@Repository
public class Mtipre00DaoImpl extends AbstractDAO<Mtipre00> implements IMtipre00Dao{

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
	@Override
	public List<Mtipre00> listMtipre00ByCriteria(Mtipre00 mtipre00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mtipre00.class);
		
		if(mtipre00.getTperre()!=null && !mtipre00.getTperre().equals(""))
		{
			c.add(Restrictions.eq("tperre", mtipre00.getTperre()));
		}	
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		if(mtipre00.getRegitr()!=null && mtipre00.getRegitr()!= false && !mtipre00.getRegitr().equals("")){
			c.add(Restrictions.eq("regitr", mtipre00.getRegitr()));
		}
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		
		c.addOrder(Property.forName("mconca00.codcia").asc());
		c.addOrder(Property.forName("tipore").asc());
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Mtipre00> listMtipre00ByCriteriaByTperre(Mtipre00 mtipre00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mtipre00.class);
		
		if(mtipre00.getTperre()!=null && !mtipre00.getTperre().equals(""))
		{
			c.add(Restrictions.eq("tperre", mtipre00.getTperre()));
		}
		
		return c.list();
	}



	@Override
	public Mtipre00 MtipreByTipore(String tipore) {
		Criteria Mtipre = getSessionFactory().getCurrentSession().createCriteria(Mtipre00.class);
		  if(tipore != null && !tipore.equals("")){
			  Mtipre.add(Restrictions.eq("tipore", tipore));
		  }
		   
		  return (Mtipre00) Mtipre.uniqueResult();
	}
}
