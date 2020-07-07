package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import CommandProcessor.Session;
import View.CommandLine;


public class UserDataManager {

	private Set<User> usersData = new HashSet<User>();
	private final String fileName = "users.dat";
	private static UserDataManager instance;
	
	public static UserDataManager instance() {
		
		if(instance == null)
			instance = new UserDataManager();
		return instance;
	}
	
	public boolean writeToFile() {
		
		
		boolean writeUserResult = false;
		
		try (OutputStream fileOutputStream = new FileOutputStream(fileName); 
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
			
			objectOutputStream.writeObject(usersData);
			writeUserResult = true;
			
		}catch (IOException e) {
			
			e.printStackTrace();
			writeUserResult = false;
			
		}
		
		return writeUserResult;
	}
	
	
	@SuppressWarnings("unchecked") // for casting
	public Set<User> readDataFromFile() {
		
		File file = new File(fileName);
		if (file.length() == 0) {
			return usersData;
		} // in case file is empty -> for the first time 
		
		try (InputStream fileInputStream = new FileInputStream(fileName);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			
			// carsData returns as 'Object' 
			usersData = (Set<User>) objectInputStream.readObject(); // casting back to set 
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			CommandLine.instance().printError("Exception while reading from file");
		}
		
		return usersData;
	}
	
	
	public boolean add(String username, String password, String rank) {
		
		if(usersData.contains(new User(username,password, Integer.parseInt(rank))))
			return false;
		
		User user = new User(username, password, Integer.parseInt(rank));
		if(usersData.add(user)) {
			writeToFile();
			return true;
		}
		
		return false;
	}
	
	public boolean delete(String username, String password) {
		
		if(!usersData.contains(new User(username,password,0)))
			return false;
	
		if(usersData.remove(new User(username,password,0))) {
		    writeToFile();
		    return true;
		}
		return false;
	}
	
	public boolean login(String username, String password) {
		
		User[] usersArr = new User[usersData.size()];
		usersData.toArray(usersArr);

		
	
		for(int i = 0; i < usersData.size(); i++) {
			if(usersArr[i].getUsername().equals(username))
				if(usersArr[i].getPassword().equals(password)) {
					Session.instance().setUser(usersArr[i]);
					return true;
				}
		}
		
		return false;
	}
	
	public void seedDatabase() {
		
		if(usersData.contains(new User("root","root",1)))
				return;
		else {
			usersData.add(new User("root","root",1));
			writeToFile();
		}
	
		
	}
	
	public void showdb() {
		
		User[] usersArr = new User[usersData.size()];
		usersData.toArray(usersArr);

		
	
		for(int i = 0; i < usersData.size(); i++) {
			CommandLine.instance().Print(usersArr[i].toString());	
			
		}
	}
		
}





/*
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
*/
