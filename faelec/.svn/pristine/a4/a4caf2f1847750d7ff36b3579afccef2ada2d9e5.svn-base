package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMgenus00Dao;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mdesgr00;
import com.tlm.faelecEntities.model.entities.Mgenus00;

@Repository
public class Mgenus00Daoimpl extends AbstractDAO<Mgenus00> implements IMgenus00Dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@SuppressWarnings("unchecked")
	public List<Mgenus00> listMgenus00ByCriteriaAndlistMusuco00(Mgenus00 mgenus00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgenus00.class);
		
		if(mgenus00.getMtipre00().getTipore()!=null && !mgenus00.getMtipre00().getTipore().equals(""))
		{
		   c.add(Restrictions.eq("mtipre00.tipore", mgenus00.getMtipre00().getTipore()));
		}
		
		if(mgenus00.getRegius()!= false)
		{
			c.add(Restrictions.eq("regius", mgenus00.getRegius()));
		}
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		c.setFetchMode("mtipre00", FetchMode.DEFAULT).createAlias("mtipre00", "mtipre00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
				
		return c.list();
	}
	
	public Mgenus00 cargarDetalles(Integer idtrus){
		Mgenus00 mgenus00 = new Mgenus00();
		String hql = "select mdesgr FROM Mdesgr00 as mdesgr where mdesgr.mgenus00.idtrus = :idtrus ";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtrus", idtrus);
	    mgenus00.setMdesgr00s((ArrayList<Mdesgr00>)query.list());	    
	    
	    return mgenus00;
	}


	@Override
	public Mgenus00 MgenusByCodius(String codius, String codtus) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgenus00.class);
		  if(codius != null && !codius.equals("")){
			  c.add(Restrictions.eq("codius", codius));
		  }
		  if(codtus != null && !codius.equals("")){
			 c.add(Restrictions.eq("mtipre00.tipore", codtus));
		  }
		   
		  return (Mgenus00) c.uniqueResult();
	}
	
	@Override
	public List<Mgenus00> MgenusByIdcmus(Mgenus00 mgenus00, Mconca00 mconca00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgenus00.class);
		
		
		if(mgenus00.getMtipre00().getTipore()!=null && !mgenus00.getMtipre00().getTipore().equals(""))
		{
		   c.add(Restrictions.eq("mtipre00.tipore", mgenus00.getMtipre00().getTipore()));
		}
		
		if(mgenus00.getRegius()!= false)
		{
			c.add(Restrictions.eq("regius", mgenus00.getRegius()));
		}
		
		c.setFetchMode("mtipre00", FetchMode.DEFAULT).createAlias("mtipre00", "mtipre00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
				
		return c.list();
	}
	
	@Override
	public Map<String, String> getMapMgenus00ByCodtus(String codtus) {
		Map<String, String> mapRs = new HashMap<String, String>();
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgenus00.class);
		  if(codtus != null && !codtus.equals("")){
			  c.add(Restrictions.eq("mtipre00.tipore", codtus));
		  }
		  @SuppressWarnings("unchecked")
		List<Mgenus00> listAux = c.list();
			 for (Mgenus00 mgenus00 : listAux) {
				 mapRs.put(mgenus00.getCodius(), mgenus00.getDcttus());
			 }
		return mapRs;
	}
	
	@Override
	public Mgenus00 find(Integer idtrus){
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgenus00.class);
		c.add(Restrictions.eq("idtrus", idtrus));
		return (Mgenus00)c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mgenus00> listMgenus00ByCriteria(Mgenus00 mgenus00, List<String> listMusuco00) {
		 String hql = "select mgenus FROM Mgenus00 as mgenus left join mgenus.mconca00 as mconca join mgenus.mtipre00 as mtipre where mconca.codcia in (:companias) or mconca is null order by mtipre.tipore asc, mgenus.idtrus asc";
		    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameterList("companias", listMusuco00);
		  	
		    List<Mgenus00> listmprorpr= query.list(); 
		    return listmprorpr;
	}
	
	@Override
	public Mgenus00 findMgenus(Mgenus00 mgenus){
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgenus00.class);
		c.add(Restrictions.eq("codius", mgenus.getCodius()));
		c.add(Restrictions.eq("mtipre00.tipore", mgenus.getMtipre00().getTipore()));
		return (Mgenus00)c.uniqueResult();
	}
	
}
