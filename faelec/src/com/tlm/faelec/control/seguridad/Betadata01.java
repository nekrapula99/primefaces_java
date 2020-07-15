package com.tlm.faelec.control.seguridad;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author nosorio
 *
 */
public class Betadata01 {

	private Connection conn;
	private int MaxRows=0;

	/**
	 * @param c
	 * @throws SQLException
	 */
	public Betadata01(Connection c) throws SQLException {
		this.conn = c;
	}

	/**
	* Busca la cantidad de ocurrencias de un caracter en una cadena
	*
	* @param token cadena en la que se buscar� el caracter search.
	* @search caracter a buscar
	* @return int con la cantidad de ocurrencias en el par�metro token.
	*/
	public int getOcurrencesCount(String token, char search){
		int iStart = 0;
		int icontOcur = 0;
		while (token.indexOf(search, iStart) != -1){
			icontOcur ++;
		  	iStart = token.indexOf(search, iStart) + 1;
		}
		return icontOcur;
	}

	/**
		* Valida que los par�metros enviados a un SQL esten completos y correctos.
		* 
		* @param string sql instruccion SQL.
		* @param param arreglo con los par�metros del SQL. Si el SQL no lleva par�metros este valor ser�a null.
		* @return PreparedStatement, en caso de que haya un problema con los par�metros se retornar�a null.
		* @throws SQLException
		*/     
		private PreparedStatement getPstmtWithArguments(String sql, Object param[]) throws SQLException {
			PreparedStatement ps = conn.prepareStatement(sql);
			if (param != null){
				@SuppressWarnings("unused")
				int iOcurrencesCount = getOcurrencesCount(sql, '?');
				for (int i = 0; i < param.length; i ++){
					if (param[i] instanceof String)
						ps.setString(i + 1, param[i].toString());
					else if (param[i] instanceof Timestamp) {
						ps.setTimestamp(i + 1, Timestamp.valueOf(param[i].toString()));
					}
					else if (param[i] instanceof STREAM) {
						ps.setBinaryStream(i + 1, ((STREAM)param[i]).getStream(), ((STREAM)param[i]).getSize());
					}
					else if (param[i] instanceof FetchSize) {
						ps.setFetchSize(((FetchSize)param[i]).getSize());
					}		
					else if (param[i] instanceof Double) {
						ps.setDouble(i + 1, Double.parseDouble(param[i].toString()));
					}						
					else if (param[i] == null){
						ps.setString(i + 1, null);
					}
				}
			}
			return ps;
		}
	/**
	* Ejecuta un comando SQL con instrucciones preparadas.
	*
	* @param sql consulta SQL.
	* @param param arreglo que determina los par�metros marcados de la instruccion preparada, su dimension debe ser igual a cuantos ? halla en el par�metro sql. Si su valor es null, indica que el SQL no tiene parametros.
	* @return boolean true si fue exitoso, false en caso contrario.
	* @throws SQLException
	*/
	public boolean execSql(String sql, Object param[]) throws SQLException {
		boolean ejecuto = false;
		PreparedStatement ps = null;
		if(param!=null)
		try{
			ps = getPstmtWithArguments(sql, param); 
			ps.execute();
			ejecuto = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ps.close();
		}
		return ejecuto;
	}
        
	/**
	* Ejecuta una consulta realizada con instrucciones preparadas.
	*
	* @param strQuery Consulta SQL.
	* @param parPrepar arreglo que determinan los par�metros marcados de la
	* instrucci�n preparada, su dimension debe ser igual a cuantos ? halla en query. 
	* Si la consulta no necesita par�metros su valor ser�a null.
	* @return CachedRowSetImpl
	* @throws SQLException
	*/
	public ResultSet getRecords(String sql, Object param[]) throws SQLException {
		PreparedStatement ps = getPstmtWithArguments(sql, param); 	
		return ps.executeQuery();
	}

	/**
	 * Cierra la conexi�n con la base de datos 
	 * @throws SQLException
	 */
	public boolean close() throws SQLException {
		boolean desconecto = false;
		conn.close();
		desconecto = true;
		return desconecto;
	}
	/**
	 * @return
	 */
	public int getMaxRows() {
		return MaxRows;
	}

	/**
	 * @param i
	 */
	public void setMaxRows(int i) {
		MaxRows = i;
	}

}
