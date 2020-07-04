package CommandProcessor;

import Database.User;

public class Session {
	
	User user;
	static Session instance;
	
	public static Session instance() {
		if(instance == null)
			instance = new Session();
		return instance;
	}
	
	public Session() {
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public boolean isAdmin() {
		return user.getRank()==1;
	}

}
