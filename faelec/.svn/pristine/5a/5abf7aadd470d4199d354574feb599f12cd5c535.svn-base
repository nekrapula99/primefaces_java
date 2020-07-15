package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMopcme00Dao;
import com.tlm.faelecEntities.model.entities.Mopcme00;

@Repository
public class Mopcme00DaoImpl extends AbstractDAO<Mopcme00> implements IMopcme00Dao{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<Mopcme00> listMopcme00ByCriteria(Mopcme00 mopcme00, String gruopg) {
		
//		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mopcme00.class);
//
//		 
//		if(mopcme00.getAplopm() != null && !mopcme00.getAplopm().equals("")){
//			c.add(Restrictions.eq("aplopm",mopcme00.getAplopm()));
//		}
//		
//		if(mopcme00.getMenopm() !=null && !mopcme00.getMenopm().equals(""))
//		{
//			c.add(Restrictions.eq("menopm", mopcme00.getMenopm()));
//		}
		
		String hql = "select mopcme FROM Mopcme00 as mopcme,Mopcgr00 as mopcgr WHERE mopcgr.id.menopg=mopcme.menopm and mopcgr.id.numopg=mopcme.numopm and mopcme.aplopm="+mopcme00.getAplopm() +" and mopcgr.id.gruopg="+gruopg +" and mopcgr.id.aplopg = "
		+mopcme00.getAplopm()+" and mopcme.menopm="+ mopcme00.getMenopm();
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
				
	
		return query.list();		
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	
	
}
