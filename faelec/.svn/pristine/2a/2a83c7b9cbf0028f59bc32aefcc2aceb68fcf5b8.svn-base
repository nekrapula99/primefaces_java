package com.tlm.faelec.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMusuar00Dao;
import com.tlm.faelecEntities.model.entities.Musuar00;

@Repository
public class Musuar00DaoImpl extends AbstractDAO<Musuar00> implements
		IMusuar00Dao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Musuar00 retrieveMusuar00(Musuar00 musuar00) {

		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Musuar00.class);

		if (musuar00.getId().getAplusu() != null
				&& !musuar00.getId().getAplusu().equals("")) {
			c.add(Restrictions.eq("id.aplusu", musuar00.getId().getAplusu()));
		}
		if (musuar00.getId().getCodusu() != null
				&& !musuar00.getId().getCodusu().equals("")) {
			c.add(Restrictions.eq("id.codusu", musuar00.getId().getCodusu()));
		}
		
		
		if(!c.list().isEmpty()){
			return (Musuar00) c.list().get(0);
		} else return null;
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	protected SessionFactory getSessionFactory() {
		// TODO Apéndice de método generado automáticamente
		return sessionFactory;
	}

}
