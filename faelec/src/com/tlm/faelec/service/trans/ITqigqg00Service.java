package com.tlm.faelec.service.trans;

import java.sql.SQLException;
import java.util.List;

import com.tlm.faelecEntities.model.entities.Taudit00;
import com.tlm.faelecEntities.model.entities.Tqesqe00;
import com.tlm.faelecEntities.model.entities.Tqigqg00;

public interface ITqigqg00Service {
	
	public List<Tqigqg00> listTqigqg00ByCriteria(Tqigqg00 tqigqg00,	List<String> listMusuco00);
	public List<Tqigqg00> listTqigqg00Criteria(Tqigqg00 tqigqg00, List<String> listMusuco00);
	public List<Tqesqe00> listTqigqg00ByCriteriaFechas(Tqesqe00 tqesqe00);
	public void save(Tqigqg00 tqigqg00, Taudit00 taudit00) throws SQLException;
	public void removeTqigqg00 (Tqigqg00 tqigqg00, Taudit00 taudit00);
	public Tqigqg00 cargarDetalles(Tqigqg00 tqigqg00);
	public Tqigqg00 cargarDetalles(Integer idtqqg);
	public Tqigqg00 cargarListaEstado(Integer idtqqg);
	public Tqigqg00 getObjectTqigqg00(Tqigqg00 tqigqg00);
}
