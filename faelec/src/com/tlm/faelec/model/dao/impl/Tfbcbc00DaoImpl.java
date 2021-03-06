package com.tlm.faelec.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.ITfbcbc00Dao;

import com.tlm.faelecEntities.model.entities.Tfbcbc00;

@Repository
public class Tfbcbc00DaoImpl extends AbstractDAO<Tfbcbc00> implements ITfbcbc00Dao{


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
	public List<Tfbcbc00> listTfbcbc00ByCriteria(Tfbcbc00 tfbcbc00, List<String> listMusuco00) {
		
		String hql = "select tqigqg FROM Tqigqg00 as tqigqg left join tqigqg.mtiptx00 as mtiptx where tqigqg.regcqg = true order by tqigqg.idtqqg,tqigqg.nroqqg asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    return query.list();
	   
	}
	
	@SuppressWarnings("unchecked")
	public List<Tfbcbc00> listTfbcbc00FacByCriteria(Tfbcbc00 tfbcbc00, List<String> listMusuco00) {
		
		String hql = "select tfbcbc00 FROM Tfbcbc00 as tfbcbc00 where tfbcbc00.regcbc = true order by tfbcbc00.idfcbc,tfbcbc00.nrcubc asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    return query.list();
	   
	}
	
	
	@Override
	public boolean validarBorrador (Tfbcbc00 tfbcbc00){
		boolean flag = true;
		
		String hql = "SELECT tfbcbc00 FROM Tfbcbc00 AS tfbcbc00 WHERE tfbcbc00.tqdfqf00 =:tqdfqf00";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("tqdfqf00", tfbcbc00.getTqdfqf00());
	    
	    if(query.list().isEmpty()){
	    	flag = false;
	    }
	    return flag;
	}
	
	@Override
	public Tfbcbc00 getTfbcbc00ByTqdfqf00 (Tfbcbc00 tfbcbc00){
		String hql = "SELECT tfbcbc00 FROM Tfbcbc00 AS tfbcbc00 WHERE tfbcbc00.tqdfqf00 =:tqdfqf00";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("tqdfqf00", tfbcbc00.getTqdfqf00());
	    return (Tfbcbc00) query.uniqueResult();
	}
	
	
}