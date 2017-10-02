package eu.beersafe.mooc.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eu.beersafe.mooc.data.objects.Beer;
import eu.beersafe.mooc.data.objects.Note;
import eu.beersafe.mooc.logger.Logger;

public class NoteDAO {

	private static final String TABLE = "Notes";
	
	public List<Note> findAllByBeerId(int id) throws SQLException {
		List<Note> results = new ArrayList<Note>();
		
		String query = "SELECT * FROM " + TABLE + " WHERE beerid = " + id;
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
	
	public List<Note> findAllByUserId(int id) throws SQLException {
		List<Note> results = new ArrayList<Note>();
		
		String query = "SELECT * FROM " + TABLE + " WHERE userid = " + id;
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
	
	public Note findOneById(int id) throws SQLException {
		Note result = null;
		
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
	
	public boolean updateNote(Note note) throws SQLException {
		boolean result = false;
		
		String query = "UPDATE " + TABLE + " SET title = ?, content = ?, public = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Logger.debug("DATABASE - " + query);
			
			conn = DB.getConnection();
			statement = conn.prepareStatement(query);
			statement.setString(1, note.getTitle());
			statement.setString(2, note.getContent());
			statement.setBoolean(3, note.isPublicNote());
			statement.setLong(4, note.getId());
			statement.executeUpdate();
			
			result = true;

			statement.close();
			statement = null;
			conn.close();
			conn = null;
		}
		catch(Exception e) {
			throw new SQLException(e);
		}
		finally {
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

	public boolean createNote(Note note) throws SQLException {
		boolean result = false;
		
		String query = "INSERT INTO " + TABLE + " (title, content, created, public, beerid, userid) VALUES (?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Logger.debug("DATABASE - " + query);
			
			conn = DB.getConnection();
			statement = conn.prepareStatement(query);
			statement.setString(1, note.getTitle());
			statement.setString(2, note.getContent());
			statement.setTimestamp(3,  note.getCreated());
			statement.setBoolean(4, note.isPublicNote());
			statement.setLong(5, note.getBeerid());
			statement.setLong(6, note.getUserid());
			statement.executeUpdate();
			
			result = true;

			statement.close();
			statement = null;
			conn.close();
			conn = null;
		}
		catch(Exception e) {
			throw new SQLException(e);
		}
		finally {
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
	

	protected Note processItem(ResultSet rs) throws SQLException {
		return new Note(rs.getLong("id"), rs.getTimestamp("created"), rs.getString("title"), rs.getString("content"), rs.getBoolean("public"), rs.getLong("userid"), rs.getLong("beerid"));
	}
}
