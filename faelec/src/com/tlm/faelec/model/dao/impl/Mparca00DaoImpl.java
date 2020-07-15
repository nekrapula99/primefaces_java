package com.tlm.faelec.model.dao.impl;

import java.sql.SQLException;
import java.util.List;

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
import com.tlm.faelec.model.dao.IMparca00Dao;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Mgenus00;
import com.tlm.faelecEntities.model.entities.Mparca00;


@Repository
public class Mparca00DaoImpl extends AbstractDAO<Mparca00> implements IMparca00Dao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mparca00> listMparca00ByCriteria(Mparca00 mparca00, List<String> listMusuco00) {
		String hql = "select mparca FROM Mparca00 as mparca left join  mparca.mconca00 as mconca where mconca.codcia in (:companias) or mconca is null";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		if(!Utils.isEmptyOrNull(listMusuco00)){ 
			query.setParameterList("companias", listMusuco00);
		}else{
			query.setParameter("companias", "");
		}
		 List <Mparca00> listMparca = query.list();
		return listMparca;		
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
