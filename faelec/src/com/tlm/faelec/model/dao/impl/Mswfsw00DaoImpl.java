package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMswfsw00Dao;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Mswfsw00;


@Repository
public class Mswfsw00DaoImpl extends AbstractDAO<Mswfsw00> implements IMswfsw00Dao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mswfsw00> listMswfsw00ByCriteria(Mswfsw00 mswfsw00, List<String> listMusuco00) {
	    String hql = "select mswfsw FROM Mswfsw00 as mswfsw left join mswfsw.mconca00 as mconca where mconca.codcia in (:companias) or mconca is null order by mconca.codcia asc, mswfsw.coswsw asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    if(!Utils.isEmptyOrNull(listMusuco00)){
	    	query.setParameterList("companias", listMusuco00);
	    }else{
	    	query.setParameter("companias","");
	    }
	    List<Mswfsw00> listmswfsw= query.list(); 
	    return listmswfsw;
		
	 }

	public List<Mswfsw00> listMswfsw00Criteria(Mswfsw00 mswfsw00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mswfsw00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(mswfsw00.isRegrsw()!= false)
		{
			c.add(Restrictions.eq("regrsw", mswfsw00.isRegrsw()));
		}
		c.addOrder(Property.forName("coswsw").asc());
		
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
