package Database;

import java.io.Serializable;

import Car.Car;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	final private String username;
	final private String password;
	final private int rank;
	
	
	public User(String username, String password, int rank) {
		super();
		this.username = username;
		this.password = password;
		this.rank = rank;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public int getRank() {
		return rank;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode())
				+ ((username == password) ? 0 : password.hashCode());
		return result;
	}
	
	
    public boolean equals(Object obj) {
		
		if ( this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!User.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final User other = (User) obj;
		if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
			return false;
		}
		
		return true;
	}


	@Override
	public String toString() {
		
		if(this.rank == 0) 
			return "Username [" + this.username + "] Role [Dealer]";
			
		if(this.rank == 1)
			return "Username [" + this.username + "] Role [Manager]";
		
		return null;
	}
	
    


	
	
	
	
}




/*
 public class User {

	private String username;
	private String password;
	private int rank;
	

	public User(String username, String password, int rank) {
		super();
		this.username = username;
		this.password = password;
		this.rank = rank;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
}
 */


