package com.tlm.faelec.model.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMpropr00Dao;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mparca00;
import com.tlm.faelecEntities.model.entities.Mpropr00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Repository
public class Mpropr00DaoImpl extends AbstractDAO<Mpropr00> implements IMpropr00Dao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mpropr00> listMpropr00ByCriteria(Mpropr00 mpropr00, List<String> listMusuco00) {
	    String hql = "select mpropr FROM Mpropr00 as mpropr left join mpropr.mconca00 as mconca where mconca.codcia in (:companias) or mconca is null order by mconca.codcia asc, mpropr.codcpr asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    if(!Utils.isEmptyOrNull(listMusuco00)){
	    	query.setParameterList("companias", listMusuco00);
	    }else{
	    	query.setParameter("companias","");
	    }
	    List<Mpropr00> listmprorpr= query.list(); 
	    return listmprorpr;
		
	 }

	public List<Mpropr00> listMpropr00Criteria(Mpropr00 mpropr00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mpropr00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(mpropr00.getRegipr()!= false)
		{
			c.add(Restrictions.eq("regipr", mpropr00.getRegipr()));
		}
		c.addOrder(Property.forName("codcpr").asc());
		
		return c.list();
	 }
	
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Mpropr00 cargarDetalles(Mpropr00 mpropr00){
		Session session = this.getSessionFactory().getCurrentSession();
		Mpropr00 mpropr = (Mpropr00)session.load(Mpropr00.class, mpropr00.getIdcapr());
		
		Hibernate.initialize(mpropr.getMdespr00s());
		 //Hibernate.initialize(mpropr.getMvarpr00s());
		 
		 //for(Mvarpr00 mvarpr00 : mpropr.getMvarpr00s()){
			// Hibernate.initialize(mvarpr00.getMdevpr00s());
		// }

	    return mpropr;
	}

}
