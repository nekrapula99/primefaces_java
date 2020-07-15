package com.tlm.faelec.model.dao.impl;


import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMtabla00Dao;
import com.tlm.faelecEntities.model.entities.Mtabla00;
import com.tlm.faelecEntities.model.entities.Mtabla00PK;

@Repository
public class Mtabla00DaoImpl extends AbstractDAO<Mtabla00> implements IMtabla00Dao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Mtabla00 getMtabla00(Mtabla00PK mtabla00) {
		return (Mtabla00) getSessionFactory().getCurrentSession().get(Mtabla00.class,mtabla00);
	}
	

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Mtabla00> listMtabla00ByCriteria(String aplusu, Mtabla00 mtabla00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mtabla00.class);
		
			c.add(Restrictions.eq(("id.apltab"), aplusu));

		if (mtabla00.getUspaab() != null && !mtabla00.getUspaab().equals("")) {
			c.add(Restrictions.eq("uspaab", mtabla00.getUspaab()));
		}
		
		c.setFetchMode("maplic00", FetchMode.DEFAULT)
		  .createAlias("maplic00", "maplic00", Criteria.LEFT_JOIN);
		
		return c.list();
	}


	@Override
	public Mtabla00 MtablaByCodtab(String codtab) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mtabla00.class);
		  if(codtab != null && !codtab.equals("")){
		   c.add(Restrictions.eq("id.codtab", codtab));
		  }
		   
		  return (Mtabla00) c.list().get(0);
	}

}
