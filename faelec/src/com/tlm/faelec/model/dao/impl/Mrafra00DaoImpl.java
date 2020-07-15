package com.tlm.faelec.model.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlm.faelec.model.dao.AbstractDAO;
import com.tlm.faelec.model.dao.IMrafra00Dao;
import com.tlm.faelec.utils.Utils;
import com.tlm.faelecEntities.model.entities.Mrafra00;


@Repository
public class Mrafra00DaoImpl extends AbstractDAO<Mrafra00> implements IMrafra00Dao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mrafra00> listMrafra00ByCriteria(Mrafra00 mrafra00, List<String> listMusuco00) {
	    String hql = "select mrafra FROM Mrafra00 as mrafra left join mrafra.mconca00 as mconca where mconca.codcia in (:companias) or mconca is null order by mconca.codcia asc, mrafra.corara asc";
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	    if(!Utils.isEmptyOrNull(listMusuco00)){
	    	query.setParameterList("companias", listMusuco00);
	    }else{
	    	query.setParameter("companias","");
	    }
	    List<Mrafra00> listmrafra= query.list(); 
	    return listmrafra;
		
	 }

	public List<Mrafra00> listMrafra00Criteria(Mrafra00 mrafra00, List<String> listMusuco00) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Mrafra00.class);
		
		if(listMusuco00!=null)
		{
			c.add(Restrictions.or(Restrictions.in("mconca00.codcia", listMusuco00),Restrictions.isNull("mconca00")));
		}
		
		if(mrafra00.isRegrra()!= false)
		{
			c.add(Restrictions.eq("regrra", mrafra00.isRegrra()));
		}
		c.addOrder(Property.forName("corara").asc());
		
		return c.list();
	 }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mrafra00> listMrafra00(Mrafra00 mrafra00, List<String> companiasUsu) {
		String hql ="";
	
		 hql = "SELECT mrafra FROM Mrafra00 AS mrafra WHERE mrafra.mtiptx00 = :mtiptx00 " +
				    "and mrafra.regrra = :regrra";
		 
			  Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			  query.setParameter("mtiptx00", mrafra00.getMtiptx00());
			  query.setParameter("regrra", true);
			  
			  System.out.println(" mrafra00 en el query: "+ query.list());
			  
			  return query.list();
			  
			  
			  	
	}
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Mrafra00 cargarDetalles(Mrafra00 mrafra00){
		Session session = this.getSessionFactory().getCurrentSession();
		Mrafra00 mrafra = (Mrafra00)session.load(Mrafra00.class, mrafra00.getIdrara());
		
		Hibernate.initialize(mrafra.getTucauc00s());
		 //Hibernate.initialize(mrafra.getMvarpr00s());
		 
		 //for(Mvarpr00 mvarpr00 : mrafra.getMvarpr00s()){
			// Hibernate.initialize(mvarpr00.getMdevpr00s());
		// }

	    return mrafra;
	}
	//ESto tendra que arreglarse cuando se ingresen mas rangos
	public Mrafra00 getRegistroRango(int idTransaccion,Date fechahoy){
		System.out.println("idTransaccion en query: "+idTransaccion);
		System.out.println("fechahoy e query: "+fechahoy);
		Date fechaHoy = fechahoy;
		Date todayMorning = DateUtils.truncate(fechaHoy, Calendar.DATE);
		String hql = "select mrafra00 FROM Mrafra00 as mrafra00 where "+todayMorning+" BETWEEN (mrafra00.ravdra) AND (mrafra00.ravhra) AND mrafra00.mtiptx00.idtptx = "+idTransaccion+"";

		
		
		//String hql = "select mrafra00 FROM Mrafra00 as mrafra00 where DATE(mrafra00.ravdra) <="+todayMorning+"" +
			//													"AND DATE(mrafra00.ravhra)  >="+todayMorning+" AND mrafra00.mtiptx00.idtptx = "+idTransaccion+"";
	   // Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("idtptx", idTransaccion);
	    Query query = getSessionFactory().getCurrentSession().createQuery(hql);

	    System.out.println("En query Mrafra: "+(Mrafra00) query.uniqueResult());
	    return (Mrafra00) query.uniqueResult();
	}


}
