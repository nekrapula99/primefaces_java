package com.tlm.faelec.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMcampo00Dao;
import com.tlm.faelecEntities.model.entities.Mcampo00;

@Repository
public class Mcampo00DaoImpl extends AbstractDAO<Mcampo00> implements IMcampo00Dao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Mcampo00> listMcampo00ByCriteria(Mcampo00 mcampo00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mcampo00.class);
		
		if(mcampo00.getMtabla00().getId().getCodtab()!=null && mcampo00.getMaplic00().getCodapl()!=null)
		{
	      c.add(Restrictions.eq("mtabla00.id.codtab", mcampo00.getMtabla00().getId().getCodtab()));
	      c.add(Restrictions.eq("maplic00.codapl", mcampo00.getMaplic00().getCodapl()));
		}
		
		c.setFetchMode("mtabla00", FetchMode.DEFAULT)
		.createAlias("mtabla00", "mtabla00", Criteria.LEFT_JOIN);
		
		c.setFetchMode("maplic00", FetchMode.DEFAULT)
		.createAlias("maplic00", "maplic00", Criteria.LEFT_JOIN);
		
		return c.list();
	}
	
	@Override
	public Map<String,Mcampo00> listMcampo00(Mcampo00 mcampo00)
	{
		Map<String, Mcampo00> mcampos = new HashMap<String, Mcampo00>();
		
		List<Mcampo00> list= listMcampo00ByCriteria(mcampo00);
		
		for(Mcampo00 mca:list)
		{
			mcampos.put(mca.getId().getCodcam().toLowerCase(), mca);
		}
		
		return mcampos;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected SessionFactory getSessionFactory() {
		// TODO Apéndice de método generado automáticamente
		return this.sessionFactory;
	}

}
