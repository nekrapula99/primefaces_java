package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITucauc00Dao;

import com.tlm.faelecEntities.model.entities.Mrafra00;
import com.tlm.faelecEntities.model.entities.Tucauc00;

@Repository
public class Tucauc00DaoImpl extends AbstractDAO<Tucauc00> implements ITucauc00Dao{


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
	public List<Tucauc00> listTucauc00ByCriteria(Mrafra00 mrafra00) {
		String hql = "select tucauc00 FROM Tucauc00 as tucauc00 where tucauc00.mrafra00 = :mrafra00 AND tucauc00.regruc = true order by tucauc00.idcouc asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("mrafra00", mrafra00);	    
		
	    if(query.list().isEmpty()){
	    	query.list().add(0);
	    }
	    
	    return query.list();
	}	

	

}