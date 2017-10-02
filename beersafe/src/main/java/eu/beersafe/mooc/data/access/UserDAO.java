package eu.beersafe.mooc.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eu.beersafe.mooc.data.objects.Beer;
import eu.beersafe.mooc.data.objects.User;
import eu.beersafe.mooc.logger.Logger;

public class UserDAO {

	private static final String TABLE = "Users";
	
	public List<User> findAllByEmail(String email) throws SQLException {
		List<User> results = new ArrayList<User>();
		
		String query = "SELECT * FROM " + TABLE + " WHERE email = ?";
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			Logger.debug("DATABASE - " + query);
			
			conn = DB.getConnection();
			statement = conn.prepareStatement(query);
			statement.setString(1, email);
			rs = statement.executeQuery();

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
	
	public User findOneById(int id) throws SQLException {
		User result = null;
		
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
	
	protected User processItem(ResultSet rs) throws SQLException {
		return new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"), rs.getString("name"));
	}
}
