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
		for (User user : map.keySet()) {
			if (user.getId().equals(id))
				return user.getId();
		}
		return null;
	}
	
//	public User findUserByLog(int log) {
//		for (User user : map.keySet()) {
//			
//		}
//	}
	
	public int getUserLogByIdAndPassword(String id, String password) {
		int log = -1;
		int n = 0;
		for (User user : map.keySet()) {
			if(user.getId().equals(id) && user.getPassword().equals(password))
				log = n;
			n++;
		}
		return log;
	}
	
	public int getUserCount() {
		return this.map.size();
	}
	
}
