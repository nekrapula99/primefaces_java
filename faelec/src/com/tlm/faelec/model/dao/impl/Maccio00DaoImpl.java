package com.tlm.faelec.model.dao.impl;

import java.util.List;

//import org.hibernate.Criteria;
//import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMaccio00Dao;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Maccio00;
//import com.tlm.faelec.model.entities.Mgente00;

@Repository
public class Maccio00DaoImpl extends AbstractDAO<Maccio00> implements IMaccio00Dao
{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Maccio00> listMaccio00ByCriteria(Maccio00 maccio00, List<String> listMusuco00) {		
		String hql = "select maccio00 FROM Maccio00 as maccio00 left join maccio00.mconca00 as mconca where mconca.codcia in (:companias) or mconca is null " +
					 "order by maccio00.mconca00.idccia asc, maccio00.pgccio asc, maccio00.dsccio asc, maccio00.mtiptx00.idtptx asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameterList("companias", listMusuco00);
	    return query.list();   
	}
	
	public List<Maccio00> getListMaccio00(Maccio00 maccio00, List<String> listMusuco00){
		String hql = "select maccio00 FROM Maccio00 as maccio00 left join maccio00.mconca00 as mconca where (mconca.codcia in (:companias) or mconca is null) and maccio00.regcio = true";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    if(!Utils.isEmptyOrNull(listMusuco00)){
	    	query.setParameterList("companias", listMusuco00);
	    }else{
	    	query.setParameter("companias","");
	    }
	    return query.list();
	}
	
	@Override
	public Maccio00 getMaccio00ByTqigqg00 (Maccio00 maccio00, String tabla){
		String hql = "SELECT maccio00 FROM Maccio00 AS maccio00 WHERE maccio00.pgccio ='Se"+tabla+"' AND maccio00.mconca00 = :mconca00 AND maccio00.mtiptx00 = :mtiptx00";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    query.setParameter("mconca00", maccio00.getMconca00());
	    query.setParameter("mtiptx00", maccio00.getMtiptx00());
	    return (Maccio00) query.uniqueResult();
	}
	

}