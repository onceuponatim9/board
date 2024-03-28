package board;

public class User {
	private String name;
	private int code;
	
	public User(String name, int code) {
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCode() {
		return this.code;
	}

}
