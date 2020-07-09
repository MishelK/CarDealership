package Database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import Command.AddUserCommand;

public class SaleDataManagerTest {

	static DatabaseManager db = new DatabaseManager();
	
	@BeforeClass
    public static void loadDb() {
		
    	db.loadDatabase();
    	
	
    }
	
	@Test
	public void testSell() {
		

	}
	
	
	    
	
	
	
	
	
}
