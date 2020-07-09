package Database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import Command.AddUserCommand;
import Command.DeleteUserCommand;
import Command.GetUsersCommand;
import Command.LoginCommand;
import Command.LogoutCommand;

public class UserDataManagerTest {

	static DatabaseManager db = new DatabaseManager();
	
	@BeforeClass
	public static void loadDb() {
		db.loadDatabase();
		LoginCommand cmd = new LoginCommand();
		String[] line = {"login","root","root"};
		cmd.execute(line);
		
	}
	
	@Test //making sure our root account is always present
	public void testRoot() {
		assertFalse("root user addition failed", UserDataManager.instance().getUsersData().isEmpty());
	}
	
	@Test 
	public void testAddUser() {
		AddUserCommand cmd = new AddUserCommand();
		String[] line = {"adduser","root","root","0"};
		
		assertFalse("added an existing user", cmd.execute(line)); //making sure we cant add existing users
		
		String[] line2 = {"adduser","w","w","0"};
		assertTrue("failed to add new user", cmd.execute(line2)); //making sure we can add new users
		
		String[] line3 = {"adduser","w","w"}; //not enough arguments test
		assertFalse("bad input accepted", cmd.execute(line3));
		
		String[] line4 = {"adduser","w","w","0","0"}; //too many arguments
		assertFalse("bad input accepted", cmd.execute(line4));
		
		LogoutCommand cmd2 = new LogoutCommand();//logging out of manager
		String[] line5 = {"logout"};
		assertTrue("failed to log out", cmd2.execute(line5));
		
		LoginCommand cmd3 = new LoginCommand();//logging into dealer
		String[] line6 = {"login","w","w"};
		assertTrue("failed to login", cmd3.execute(line6));
		
		String[] line7 = {"adduser","r","r","0"};//trying manager command from dealer account
		assertFalse("added user from dealer account", cmd.execute(line7));
		
		GetUsersCommand cmd4 = new GetUsersCommand();
		String[] line8 = {"users"};
		assertFalse("dealer accessed manager command", cmd4.execute(line8));
	}
	
	
	@Test //delete user should allow the manager to remove any user except the root user
	public void testDeleteUser() {
		
		String[] line = {"deleteuser","w","w"}; //trying to delete user from dealer account
		DeleteUserCommand cmd = new DeleteUserCommand();
		assertFalse("deleted user from dealer account", cmd.execute(line));
		
		LogoutCommand cmd2 = new LogoutCommand();//logging out of dealer
		String[] line2 = {"logout"};
		assertTrue("failed to log out", cmd2.execute(line2));
		
		LoginCommand cmd3 = new LoginCommand();//logging into manager
		String[] line3 = {"login","root","root"};
		assertTrue("failed to login", cmd3.execute(line3));
		
		String[] line4 = {"deleteuser","w","w"}; //trying to delete user from manager account
		assertTrue("failed to delete user", cmd.execute(line4));
		
		String[] line5 = {"deleteuser","root","root"}; //trying to delete root account
		assertFalse("deleted root account", cmd.execute(line5));
		
	}
	
	
	
}
