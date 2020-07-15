package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMrerer00Dao;
import com.tlm.faelecEntities.model.entities.Mrerer00;

@Repository
public class Mrerer00DaoImpl extends AbstractDAO<Mrerer00> implements IMrerer00Dao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mrerer00> listMrerer00ByCriteria(Mrerer00 mrerer00,	List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mrerer00.class);
		
		c.setFetchMode("mregcg001", FetchMode.DEFAULT).createAlias("mregcg001", "mregcg001",Criteria.LEFT_JOIN);
		c.setFetchMode("mregcg002", FetchMode.DEFAULT).createAlias("mregcg002", "mregcg002",Criteria.LEFT_JOIN);
		
		if(mrerer00.isRegrer()!=false){
			c.add(Restrictions.eq("regrer", mrerer00.isRegrer()));
			
		}
		
		return c.list();
	}
	
	@Override
	public boolean obtenerRegistroValido (Integer idComprador, Integer idVendedor){
		boolean flag = true;
		String hql = "SELECT mrerer00 FROM Mrerer00 AS mrerer00 WHERE mrerer00.mregcg001 = "+idComprador+" AND mrerer00.mregcg002 = "+idVendedor+"";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    
	    if(query.list().isEmpty()){
	    	flag = false;
	    }
	    return flag;
	}



}
