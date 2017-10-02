package eu.beersafe.mooc.data.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import eu.beersafe.mooc.data.objects.Beer;
import eu.beersafe.mooc.logger.Logger;

public class BeerDAO {
	
	private static final String TABLE = "Beers";
	
	public List<Beer> findAll() throws SQLException {
		return findAllByName("");
	}
	
	public List<Beer> findAllByName(String filter) throws SQLException {
		List<Beer> results = new ArrayList<Beer>();
		
		String query = "SELECT * FROM " + TABLE + " WHERE name LIKE '%" + filter + "%'";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			Logger.debug("DATABASE - " + query);
			
			conn = DB.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);

			while(rs.next()) {
				results.add(processItem(rs));
			}

			rs.close();
			rs = null;
			statement.close();
			statement = null;
			conn.close();
			conn = null;
		}
		catch(Exception e) {
			throw new SQLException(e);
		}
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(statement != null) {
				statement.close();
				statement = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
		
		return results;
	}
	
	public Beer findOneById(int id) throws SQLException {
		Beer result = null;
		
		String query = "SELECT * FROM " + TABLE + " WHERE id = " + id;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			Logger.debug("DATABASE - " + query);
			
			conn = DB.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);

			while(rs.next()) {
				result = processItem(rs);
			}

			rs.close();
			rs = null;
			statement.close();
			statement = null;
			conn.close();
			conn = null;
		}
		catch(Exception e) {
			throw new SQLException(e);
		}
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(statement != null) {
				statement.close();
				statement = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
		
		return result;
	}
	
	protected Beer processItem(ResultSet rs) throws SQLException {
		return new Beer(rs.getLong("id"), rs.getString("name"), rs.getString("image"), rs.getDouble("alcohol"), rs.getString("color"), rs.getString("category"), rs.getString("description"));
	}
}
