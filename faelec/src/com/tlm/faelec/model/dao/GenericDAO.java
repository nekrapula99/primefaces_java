package com.tlm.faelec.model.dao;

import java.sql.SQLException;

//import org.hibernate.Session;

import com.tlm.faelecEntities.model.entities.Taudit00;

public interface GenericDAO<T> {
	
	public void save(T entity, Taudit00 taudit00) throws SQLException;

	public void update(T entity);
	
	public void delete(T entity, Taudit00 taudit00);

}
