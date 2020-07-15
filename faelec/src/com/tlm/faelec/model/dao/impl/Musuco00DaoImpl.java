package com.tlm.faelec.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMusuco00Dao;
import com.tlm.faelecEntities.model.entities.Musuco00;
import com.tlm.faelecEntities.vo.Musuco00VO;

@Repository
public class Musuco00DaoImpl extends AbstractDAO<Musuco00> implements IMusuco00Dao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Musuco00VO> listMusuco00ByCriteria(Musuco00VO musuco00vo) {
		List<Musuco00VO> resultList = new ArrayList<Musuco00VO>();
		try {
			Query query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT * FROM MUSUCO00 WHERE USUUCO = :usuuco  AND APLUCO = :apluco");
			query.setParameter("usuuco", musuco00vo.getUsuuco());  
			query.setParameter("apluco", musuco00vo.getApluco());
			
			List list = query.list();			
			
			for (Object object : list) {
				Object[]  map = (Object[]) object;
				Musuco00VO obj = new Musuco00VO();
				obj.setApluco(String.valueOf(map[0]));
				obj.setGruuco(String.valueOf(map[1]));
				obj.setAreuco(String.valueOf(map[2]));
				obj.setUsuuco(String.valueOf(map[3]));
				obj.setCiauco(String.valueOf(map[4]));
				obj.setIncdco(String.valueOf(map[5]));
				resultList.add(obj);
			}
			
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return resultList;
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	
}
