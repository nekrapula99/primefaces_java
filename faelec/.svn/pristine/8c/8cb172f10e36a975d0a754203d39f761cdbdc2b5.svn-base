package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMgente00Dao;
import com.tlm.faelecEntities.model.entities.Mgente00;

@Repository
public class Mgente00DaoImpl extends AbstractDAO<Mgente00> implements IMgente00Dao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Mgente00> listMgente00ByCriteria(Mgente00 mgente00, List<String> listMusuco00) {
	    String hql = "select mgente FROM Mgente00 as mgente left join mgente.mconca00 as mconca where mconca.codcia in (:companias) or mconca is null " +
	    			 "order by mgente.mtipre00.idtrtr asc, mgente.mconca00.idccia asc, mgente.codite asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameterList("companias", listMusuco00);
	    List<Mgente00> listMgente= query.list(); 
	    return listMgente;
	}
	
	
	public Mgente00 cargarDetalles(Integer idtrte){
		Mgente00 mgente00 = new Mgente00();
		/*String hql = "select mgetcu FROM Mgetcu00 as mgetcu where mgetcu.mgente00.idtrte = :idtrte";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtrte", idtrte);
	    mgente00.setMgetcu00s((ArrayList<Mgetcu00>)query.list());
	    
	    hql = "select mgentc FROM Mgentc00 mgentc where mgentc.mgente001.idtrte = :idtrte order by mgentc.idtrtc asc";
	    query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtrte", idtrte);
	    mgente00.setMgentc00s1((ArrayList<Mgentc00>)query.list());
	    
	    hql = "select mgentd00 FROM Mgentd00 mgentd00 where mgentd00.mgente00.idtrte = :idtrte ";
	    query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtrte", idtrte);
	    mgente00.setMgentd00s((ArrayList<Mgentd00>)query.list());
	    
	    hql = "select mgetma FROM Mgetma00 mgetma where mgetma.mgente00.idtrte = :idtrte ";
	    query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtrte", idtrte);
	    mgente00.setMgetma00s((ArrayList<Mgetma00>)query.list());
	    */
	    return mgente00;
	}
	
	@Override
	public Mgente00 MgenteByCodite(String codite) {
		Criteria Mgente = getSessionFactory().getCurrentSession().createCriteria(Mgente00.class);
		  if(codite != null && !codite.equals("")){
			  Mgente.add(Restrictions.eq("codite", codite));
		  }
		   
		  return (Mgente00) Mgente.uniqueResult();
	}

	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Mgente00> listMgente00Asesora(Mgente00 mgente00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgente00.class);
		
		if(mgente00.getMtipre00().getTipore()!=null && !mgente00.getMtipre00().getTipore().equals(""))
		{
			c.add(Restrictions.eq("mtipre00.tipore", mgente00.getMtipre00().getTipore()));
		}
		
		if(mgente00.getRegite()!= false)
		{
			c.add(Restrictions.eq("regite", mgente00.getRegite()));
		}
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		c.setFetchMode("mtipre00", FetchMode.DEFAULT).createAlias("mtipre00", "mtipre00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		
		
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Mgente00> listMgente00Cliente(Mgente00 mgente00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgente00.class);
		
		if(mgente00.getMtipre00().getTipore()!=null && !mgente00.getMtipre00().getTipore().equals(""))
		{
			c.add(Restrictions.eq("mtipre00.tipore", mgente00.getMtipre00().getTipore()));
		}
		
		if(mgente00.getRegite()!= false)
		{
			c.add(Restrictions.eq("regite", mgente00.getRegite()));
		}
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		c.setFetchMode("mtipre00", FetchMode.DEFAULT).createAlias("mtipre00", "mtipre00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		
		
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Mgente00> listMgente00ByCodtte(Mgente00 mgente00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mgente00.class);
		
		if(mgente00.getMtipre00().getTipore()!=null && !mgente00.getMtipre00().getTipore().equals(""))
		{
			c.add(Restrictions.eq("mtipre00.tipore", mgente00.getMtipre00().getTipore()));
		}
		
		if(mgente00.getRegite()!= false)
		{
			c.add(Restrictions.eq("regite", mgente00.getRegite()));
		}
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		c.setFetchMode("mtipre00", FetchMode.DEFAULT).createAlias("mtipre00", "mtipre00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);

		c.setFetchMode("mregcg005", FetchMode.DEFAULT).createAlias("mregcg005", "mregcg005", Criteria.LEFT_JOIN);

		return c.list();
	}


}
