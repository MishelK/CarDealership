package Database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserDataManagerTest {

	static DatabaseManager db = new DatabaseManager();
	
	@BeforeClass
	public static void loadDb() {
		db.loadDatabase();
		
	}
	
	@Test //making sure our root account is always present
	public void testRoot() {
		assertFalse("root user addition failed", UserDataManager.instance().getUsersData().isEmpty());
	}
	
	@Test //making sure adduser works as it should, add a new user and not allow to add same user again.
	public void testAddUser() {
		
		assertTrue("failed to add user", UserDataManager.instance().add("w", "w", "0"));
		assertFalse("added an existing user", UserDataManager.instance().add("w", "w", "0"));
		
	}
	
	@Test //testing if login works with newly added users
	public void testLogin() {
		
		assertTrue("login failed", UserDataManager.instance().login("w", "w"));
		
	}
	
	@Test //delete user should allow the manager to remove any user except the root user
	public void testDeleteUser() {
		
		assertTrue("failed to delete user", UserDataManager.instance().delete("w", "w"));
		assertFalse("removed root user", UserDataManager.instance().delete("root", "root"));
		
	}
	
	
	
}
