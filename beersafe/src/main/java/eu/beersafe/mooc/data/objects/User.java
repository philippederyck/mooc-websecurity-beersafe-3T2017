package eu.beersafe.mooc.data.objects;

public class User {

	private long id;
	private String email;
	private String password;
	private String name;
	
	public User(long id, String email, String password, String name) {
		this.setId(id);
		this.setEmail(email);
		this.setPassword(password);
		this.setName(name);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

