package com.tlm.faelec.service.maestros;

import java.sql.SQLException;
import java.util.List;

//import com.tlm.faelec.model.entities.Mconca00;
import com.tlm.faelecEntities.model.entities.Mgente00;
import com.tlm.faelecEntities.model.entities.Taudit00;

public interface IMgente00Service {

	//mgente00 
	public List<Mgente00> listMgente00ByCryteria(Mgente00 mgente00, List<String> listMusuco00);
	//public List<Mgente00> listmgente00AsesoraGerente(Mgentc00 mgentc00, Mconca00 companias);
	public List<Mgente00> listMgente00Asesora(Mgente00 mgente00, List<String> listMusuco00);
	public List<Mgente00> listMgente00Cliente(Mgente00 mgente00, List<String> listMusuco00);
	public List<Mgente00> listMgente00ByCodtte(Mgente00 mgente00, List<String> listMusuco00);
	public Mgente00 MgenteByCodite (String codite);
	public void save(Mgente00 mgente00, Taudit00 taudit00) throws SQLException;
	public void remove(Mgente00 mgente00,Taudit00 taudit00);
	public Mgente00 cargarDetalles(Integer idtrte);
}
