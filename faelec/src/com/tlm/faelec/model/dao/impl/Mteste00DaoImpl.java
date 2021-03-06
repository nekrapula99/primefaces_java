package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMteste00Dao;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Macdio00;
import com.tlm.faelecEntities.model.entities.Mteste00;
import com.tlm.faelecEntities.model.entities.Taudit00;

@Repository
public class Mteste00DaoImpl extends AbstractDAO<Mteste00> implements IMteste00Dao{

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
	@Override
	public List<Mteste00> listMteste00ByCriteria(Mteste00 mteste00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mteste00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}else{
			c.add(Restrictions.isNull("mconca00"));
		}
		
		if(mteste00.isRegtte()){
			c.add(Restrictions.eq("regtte", mteste00.isRegtte()));
		}

		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		c.setFetchMode("mtiptx00", FetchMode.DEFAULT).createAlias("mtiptx00", "mtiptx00", Criteria.LEFT_JOIN);
		c.setFetchMode("mestad001", FetchMode.DEFAULT).createAlias("mestad001", "mestad001", Criteria.LEFT_JOIN);
		c.setFetchMode("mestad002", FetchMode.DEFAULT).createAlias("mestad002", "mestad002", Criteria.LEFT_JOIN);
		c.addOrder(Property.forName("mtiptx00.idtptx").asc());
		List<Mteste00> listMteste00 = c.list();
		List<Mteste00> listMteste00Return = new ArrayList<Mteste00>();
		
		//Se ejecuta este metodo para no tener valores repetido de mtiptx00
		 int codAnterior=0;
		 for (Mteste00 obj : listMteste00){
			 if(codAnterior==0){
				 codAnterior=obj.getMtiptx00().getIdtptx();
				 listMteste00Return.add(obj);
			 }else if(codAnterior != obj.getMtiptx00().getIdtptx()){
				 codAnterior=obj.getMtiptx00().getIdtptx();
				 listMteste00Return.add(obj);
			 }
		 }
		
		return listMteste00Return;
	}

	@Override
	public void saveLista(List<Mteste00> mteste00Detalles, Taudit00 taudit00) {
		Session session = this.getSessionFactory().getCurrentSession();
		for (Mteste00 mteste00 : mteste00Detalles) {
			session.merge(mteste00);
		}
		crearAuditoria(taudit00);
	}

	@Override
	public List<Mteste00> listMteste00Detalles(Mteste00 mteste00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mteste00.class);
		
		c.add(Restrictions.eq("mtiptx00", mteste00.getMtiptx00()));
		
		c.setFetchMode("mconca00", FetchMode.DEFAULT).createAlias("mconca00", "mconca00", Criteria.LEFT_JOIN);
		c.setFetchMode("mtiptx00", FetchMode.DEFAULT).createAlias("mtiptx00", "mtiptx00", Criteria.LEFT_JOIN);
		c.setFetchMode("mestad001", FetchMode.DEFAULT).createAlias("mestad001", "mestad001", Criteria.LEFT_JOIN);
		c.setFetchMode("mestad002", FetchMode.DEFAULT).createAlias("mestad002", "mestad002", Criteria.LEFT_JOIN);
		
		return c.list();
	}
	
	@Override //Elimina todos los registros Mteste00 que tengan como relacion el Tipo de transaccion en el objeto
	public void removeLista(Mteste00 mteste00){
		try {
			String hql = "delete FROM Mteste00 as mteste where mteste.mtiptx00 = :mtiptx";
		    Query query = getSessionFactory().getCurrentSession().createQuery(hql); 
		    query.setParameter("mtiptx", mteste00.getMtiptx00());   
		    query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean obtenerTransicionValida (Integer vtIdesqe, Integer macIdedio){
		boolean flag = true;
		
		String hql = "SELECT mteste00 FROM Mteste00 AS mteste00 WHERE mteste00.mestad001 = "+vtIdesqe+" AND mteste00.mestad002 = "+macIdedio+"";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    
	    if(query.list().isEmpty()){
	    	flag = false;
	    }
	    return flag;
	}
	
}
