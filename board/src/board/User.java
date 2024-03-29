package board;

public class User {
	private String name;
	private String id, password;
	
	public User() {
		
	}
	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public User clone() {
		return new User(this.name, this.id, this.password);
	}

}
