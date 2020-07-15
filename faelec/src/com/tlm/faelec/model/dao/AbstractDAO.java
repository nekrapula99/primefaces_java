package com.tlm.faelec.model.dao;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tlm.faelecEntities.model.entities.Taudit00;

public abstract class AbstractDAO<T> implements GenericDAO<T>
{

	private Class<T> entityClass;
	
		
	protected abstract SessionFactory getSessionFactory();
	
	public void save(T entity, Taudit00 taudit00) throws SQLException {
		Session session = this.getSessionFactory().getCurrentSession();
		T entityAux=(T)session.merge(entity);		
		session.flush();	
		if(taudit00!=null)
		{
		  taudit00.setDacaud(entityAux.toString()); 
		}
		crearAuditoria(taudit00);
	}

	public void update(T entity) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(entity);
		session.flush(); 
	}
	
	public void delete(T entity, Taudit00 taudit00) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(entity);
		crearAuditoria(taudit00);
		session.flush();	
	}	
	
	public void crearAuditoria(Taudit00 taudit00){
    	if(taudit00 != null){
    		Session session = this.getSessionFactory().getCurrentSession();
    		session.saveOrUpdate(taudit00);
    	    session.flush();    
    	}
    }

}
