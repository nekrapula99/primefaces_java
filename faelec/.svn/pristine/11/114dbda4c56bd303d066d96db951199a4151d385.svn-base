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
import com.tlm.faelec.model.dao.IMparme00Dao;
import com.tlm.faelecEntities.model.entities.Maplic00;
import com.tlm.faelecEntities.model.entities.Mparme00;

@Repository
public class Mparme00DaoImpl extends AbstractDAO<Mparme00> implements IMparme00Dao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Mparme00> getListMparme00s(Integer idccia, String aplime) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mparme00.class);
		
		if(idccia != null && !idccia.equals("")){
			c.add(Restrictions.or(Restrictions.eq("mconca00.idccia",idccia),Restrictions.isNull("mconca00.idccia")));
		}else{
		c.add(Restrictions.isNull("mconca00.idccia"));
		}
		
		if(aplime!=null && !aplime.equals(""))
		{
		  c.add(Restrictions.eq("maplic00.codapl", aplime));	
		}
		
		c.setFetchMode("maplic00",FetchMode.DEFAULT).createAlias("maplic00", "maplic00",Criteria.LEFT_JOIN);
		
		c.setFetchMode("mconca00",FetchMode.DEFAULT).createAlias("mconca00", "mconca00",Criteria.LEFT_JOIN);
		
		c.addOrder(Property.forName("mconca00.codcia").asc());		
		
		return c.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Mparme00> ListMparmeByCriteria(List<String> listMusuco00, Mparme00 mparme00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mparme00.class);		
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		c.setFetchMode("mconca00", FetchMode.DEFAULT) .createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);	

		c.addOrder(Property.forName("mconca00.codcia").asc());
		c.addOrder(Property.forName("nopame").asc());
		
		return c.list();
	}


	@Override
	public Maplic00 consultarObjetoMaplic00(Maplic00 maplic00) {
		Criteria maplic = getSessionFactory().getCurrentSession().createCriteria(Maplic00.class);
		if(maplic00.getCodapl()!=null && !maplic00.getCodapl().equals(""))
		{
			maplic.add(Restrictions.eq("codapl", maplic00.getCodapl()));	
		}
		return (Maplic00) maplic.uniqueResult();
	}
	
	@Override
	public Mparme00 retrieve(Mparme00 mparme){
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mparme00.class);		
		c.add(Restrictions.eq("nopame", mparme.getNopame()));
		return (Mparme00)c.uniqueResult();
	}

}
