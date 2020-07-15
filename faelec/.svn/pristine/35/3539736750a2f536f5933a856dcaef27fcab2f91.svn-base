package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMaccgr00Dao;
import com.tlm.faelecEntities.model.entities.Maccgr00;

@Repository
public class Maccgr00DaoImpl extends AbstractDAO<Maccgr00> implements IMaccgr00Dao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Maccgr00> listMaccgr00ByCriteria(Maccgr00 maccgr00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Maccgr00.class);
		
		if(maccgr00.getId().getCoapac()!=null && !maccgr00.getId().getCoapac().equals(""))
		{
			c.add(Restrictions.eq("id.coapac",maccgr00.getId().getCoapac()));	
		}
		if(maccgr00.getId().getCograc()!=null && !maccgr00.getId().getCograc().equals(""))
		{
			c.add(Restrictions.eq("id.cograc",maccgr00.getId().getCograc()));	
		}
		if(maccgr00.getId().getComeac()!=null && !maccgr00.getId().getComeac().equals(""))
		{
			c.add(Restrictions.eq("id.comeac",maccgr00.getId().getComeac()));	
		}
		if(maccgr00.getId().getOpmeac()!=null && !maccgr00.getId().getOpmeac().equals(""))
		{
			c.add(Restrictions.eq("id.opmeac",maccgr00.getId().getOpmeac()));	
		}
		
		
		c.setFetchMode("maplic00", FetchMode.DEFAULT)
		.createAlias("maplic00", "maplic00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mgrupo00", FetchMode.DEFAULT)
		.createAlias("mgrupo00", "mgrupo00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mmenus00", FetchMode.DEFAULT)
		.createAlias("mmenus00", "mmenus00", Criteria.LEFT_JOIN);
		
		 System.out.println("Tamaño lista mmenus  "+ c.list().size());
		return c.list();
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
