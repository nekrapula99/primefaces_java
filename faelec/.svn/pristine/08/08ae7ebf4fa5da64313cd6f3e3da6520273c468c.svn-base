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
import com.tlm.faelec.model.dao.IMgencg00Dao;
import com.tlm.faelecEntities.model.entities.Mgencg00;
import com.tlm.faelecEntities.model.entities.Mgenus00;

@Repository
public class Mgencg00DaoImpl extends AbstractDAO<Mgencg00> implements IMgencg00Dao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Mgencg00> listMgencg00ByCriteria(Mgencg00 mgencg00,List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgencg00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		if(mgencg00.getMtipre00().getTipore()!=null && !mgencg00.getMtipre00().getTipore().equals(""))
		{
			c.add(Restrictions.eq("mtipre00.tipore", mgencg00.getMtipre00().getTipore()));
		}	
		
		if(mgencg00.isRegicg()!= false)
		{
			c.add(Restrictions.eq("regicg", mgencg00.isRegicg()));
		}
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mtipre00", FetchMode.DEFAULT).createAlias("mtipre00", "mtipre00", Criteria.LEFT_JOIN);		
		
		c.addOrder(Property.forName("mtipre00.tipore").asc());
		c.addOrder(Property.forName("mconca00.idccia").asc());
		c.addOrder(Property.forName("codicg").asc());
		
		return c.list();
	}
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Mgencg00 Mgencg00ByCodicg(String codicg) {
			Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgencg00.class);
			  if(codicg != null && !codicg.equals("")){
				  c.add(Restrictions.eq("codicg", codicg));
			  }		   
			  return (Mgencg00) c.uniqueResult();
	}
	
	@Override
	public Mgencg00 Mgencg00ByCodicgEv(String codicg, String codtcg) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgencg00.class);
		  if(codicg != null && !codicg.equals("")){
			  c.add(Restrictions.eq("codicg", codicg));
		  }
		  if(codtcg != null && !codicg.equals("")){
			 c.add(Restrictions.eq("mtipre00.tipore", codtcg));
		  }
		   
		  return (Mgencg00) c.uniqueResult();
	}
	
}
