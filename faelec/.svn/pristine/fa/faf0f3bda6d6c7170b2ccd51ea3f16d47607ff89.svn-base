package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMmenus00Dao;
import com.tlm.faelecEntities.model.entities.Mmenus00;

@Repository
public class Mmenus00DaoImpl extends AbstractDAO<Mmenus00> implements IMmenus00Dao{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Mmenus00> listMmenus00(String aplmen, String gruopg) {
		
//		Criteria c =getSessionFactory().getCurrentSession().createCriteria(Mmenus00.class);
//		
//		if (aplmen != null && !aplmen.equals("")) {
//			c.add(Restrictions.eq("aplmen", aplmen));
//		}
//		
//		System.out.println("Tamaño lista  "+ c.list().size());
		
		String hql = "select distinct (mmenu) FROM Mmenus00 as mmenu,Mopcgr00 as mopcgr WHERE mopcgr.id.menopg=mmenu.codmen and mmenu.aplmen="+aplmen +" and mopcgr.id.gruopg="+gruopg +" and mopcgr.id.aplopg = "+aplmen ;
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		
		return query.list();
	}

}
