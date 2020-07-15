package com.tlm.faelec.control.seguridad;

/**
 * 
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author nosorio
 *
 */
public class usuAuditoriaDAO {
	private Betadata01 cAcdat;
	private ResourceBundle rb;

	public usuAuditoriaDAO() {
	}
	
	public usuAuditoriaDAO(Connection c) throws SQLException {
		cAcdat = new Betadata01(c);
		rb = ResourceBundle.getBundle("com.tlm.faelec.resources.sql.sql");		
	}
	
	public boolean FBIUSUADI00(Object obj) throws SQLException {
		Beusuaud00 audus = (Beusuaud00)obj;
		Object param[] = {audus.getUsuaud(),audus.getFecaud(),audus.getIpuaud(),audus.getSesaud()};				  
		String sql = rb.getString("MAUDUS00.IM");
	
		return cAcdat.execSql(sql, param);
		
	}

	public Object FBCUSUADI00(Object obj) throws SQLException {
		Beusuaud00 audus = (Beusuaud00)obj;
		Object param[] = {audus.getUsuaud()};
		String sql = rb.getString("MAUDUS00.CM");
		ResultSet rs = cAcdat.getRecords(sql, param);
		audus = null;
		if (rs.next()) {
			audus = new Beusuaud00();
			audus.setUsuaud(rs.getString("USUAUD"));
			audus.setFecaud(rs.getString("FECAUD"));
			audus.setIpuaud(rs.getString("IPUAUD"));
			audus.setSesaud(rs.getString("SESAUD"));
		}
		return audus;
	}

	public boolean FBRUSUADI00(Object obj) throws SQLException {
		Beusuaud00 audus = (Beusuaud00)obj;
		Object param[] = {audus.getUsuaud()};
		String sql = rb.getString("MAUDUS00.RM");
		return cAcdat.execSql(sql, param);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List FBLUSUADI00(Object obj) throws SQLException {
		List list = new LinkedList();
		String sql = rb.getString("MAUDUS00.LM");
		Beusuaud00 audus = null;
		ResultSet rs = cAcdat.getRecords(sql, null);
		while (rs.next()) {
			audus = new Beusuaud00();
			audus.setUsuaud(rs.getString("USUAUD"));
			audus.setFecaud(rs.getString("FECAUD"));
			audus.setIpuaud(rs.getString("IPUAUD"));
			audus.setSesaud(rs.getString("SESAUD"));
			list.add(audus);
		}
		return list;
	}
	
	public boolean FBCLOSECON() throws SQLException {
		return cAcdat.close();
	}
	
}
