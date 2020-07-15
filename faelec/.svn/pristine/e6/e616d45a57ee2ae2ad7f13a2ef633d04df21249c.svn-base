package com.tlm.faelec.control.seguridad;

/**
 * 
 */


import java.sql.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tlm.faelec.utils.Utils;


/**
 * @author nosorio
 * 
 */
public class ConexionSafer {
	public static Connection getConexion() throws Exception {
		DataSource ds = null;
		Context ctx = new InitialContext();
		Properties props = Utils.leerPropiedades(new ConexionSafer());
		String applicationServer = "";
		if (props.getProperty("APPSERVER") != null)
			applicationServer = props.getProperty("APPSERVER");
		if (applicationServer.equals("was"))
			return ((DataSource) ctx.lookup("jdbc/safer")).getConnection();
		else if (applicationServer.equals("tomcat")) {
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/safer");
			return ds.getConnection();
		} else {
			return ((DataSource) ctx.lookup("jdbc/safer")).getConnection();
		}

	}
}
