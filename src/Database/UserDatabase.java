package Database;

import java.util.HashMap;

import CommandProcessor.Session;


public class UserDatabase {

	HashMap<String , User> usersMap;
	private static UserDatabase instance;
	
	public static UserDatabase instance() {
		
		if(instance == null)
			instance = new UserDatabase();
		return instance;
	}
	
	private UserDatabase() {
		usersMap = new HashMap<String, User>();
	}
	
	public boolean add(String username, String password) {
		
		if(usersMap.containsKey(username))
			return false;
		
		User user = new User(username, password, 0);
		usersMap.put(username, user);
		return true;
	}
	
	public boolean delete(String username) {
		
		if(!usersMap.containsKey(username))
			return false;
		
		usersMap.remove(username);
		return true;
	}
	
	public boolean login(String username, String password) {
		
		if(usersMap.containsKey(username))
			if(usersMap.get(username).getPassword().equals(password)) {
				Session.instance().setUser(usersMap.get(username));
				return true;
			}
			
		return false;
	}
	
	public void seedDatabase() {
		usersMap.put("admin", new User("admin","admin",1));
	}
	
}
