package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
	Map<User, Board> map = new HashMap<User, Board>();
	//private ArrayList<User> users = new ArrayList<User>();
	
	public UserManager() {
		
	}
	
	public User createUser(String name, String id, String password) {
		User user = new User(name, id, password);
		Board board = new Board();
		map.put(user, board);
		return user.clone();
	}
	
	public void removeUser(int index) {
		map.remove(index);
	}
	
	public String findUserId(String id) {
		System.out.println(map.keySet());
		for (User user : map.keySet()) {
			System.out.println(user.getId());
			if (user.getId().equals(id))
				return user.getId();
		}
		return null;
	}
	
	public int getUserCount() {
		return this.map.size();
	}

}
