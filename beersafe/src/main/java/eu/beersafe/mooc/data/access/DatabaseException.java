package eu.beersafe.mooc.data.access;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String message) {
		super(message);
	}

}
