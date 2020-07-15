package com.tlm.faelec.model.dao;

import java.util.List;

//import com.tlm.faelec.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgente00;

public interface IMgente00Dao extends GenericDAO <Mgente00> {
	
	public List<Mgente00> listMgente00ByCriteria(Mgente00 mgente00, List<String> listMusuco00);
	public List<Mgente00> listMgente00Asesora(Mgente00 mgente00, List<String> listMusuco00);
	public List<Mgente00> listMgente00Cliente(Mgente00 mgente00, List<String> listMusuco00);
	public List<Mgente00> listMgente00ByCodtte(Mgente00 mgente00, List<String> listMusuco00);
	public Mgente00 MgenteByCodite (String codite);
	public Mgente00 cargarDetalles(Integer idtrte);
}
