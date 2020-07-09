package Database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import Command.AddUserCommand;
import Command.BestDealersCommand;
import Command.BestSellingCommand;
import Command.CalcBonusCommand;
import Command.LoginCommand;
import Command.LogoutCommand;
import Command.SalesLogCommand;
import Command.SellCarIdCommand;

public class SaleDataManagerTest {

	static DatabaseManager db = new DatabaseManager();
	
	@BeforeClass
    public static void loadDb() {
		
    	db.loadDatabase();
    	LoginCommand cmd = new LoginCommand();
		String[] line = {"login","root","root"};
		cmd.execute(line);
	
    }
	
	@Test
	public void testSell() {
		
		SellCarIdCommand cmd1 = new SellCarIdCommand();
		Set<Car> carsdata = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsdata.size()];
		carsdata.toArray(arr);

		int id = arr[0].getCar_id();
		String[] line = {"sellcar",Integer.toString(id),"100"};//trying to sell first car in stock
		assertTrue("failed to sell car", cmd1.execute(line)); 
		
		assertFalse("sold same car twice", cmd1.execute(line));//trying to sell the same car twice
		
		carsdata = CarDataManager.instance().getCarsData();
		arr = new Car[carsdata.size()];
		carsdata.toArray(arr);
		id = arr[0].getCar_id();
		String[] line2 = {"sellcar",Integer.toString(id),"w"};//trying to sell car with wrong arguments
		assertFalse("sold card with bad arguments", cmd1.execute(line2));
		
		String[] line3 = {"sellcar",Integer.toString(id),"10","w"};//trying to sell car with too many arguments
		assertFalse("sold card with bad arguments", cmd1.execute(line3));
		
		String[] line4 = {"sellcar",Integer.toString(id)};//trying to sell car with not enough arguments
		assertFalse("sold card with bad arguments", cmd1.execute(line4));

	}
	
	@Test
	public void testSales() {
		
		SalesLogCommand cmd = new SalesLogCommand();
		String[] line = {"sales"};
		if(SaleDataManager.instance().isEmpty())
			assertFalse("did not abort empty sales log print", cmd.execute(line));
		else
			assertTrue("aborted sales log print", cmd.execute(line));
	}
	
	
	@Test
	public void calcBonus() { //test to see if after each sale bonus goes up by 5% of latest sale
		
		CalcBonusCommand cmd = new CalcBonusCommand();
		int bonusbefore = SaleDataManager.instance().calcBonus();
		
		SellCarIdCommand cmd1 = new SellCarIdCommand();
		Set<Car> carsdata = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsdata.size()];
		carsdata.toArray(arr);

		int price = 10000;
		int id = arr[0].getCar_id();
		String[] line = {"sellcar",Integer.toString(id),Integer.toString(price)};
		cmd1.execute(line);
		
		int bonusafter = SaleDataManager.instance().calcBonus();
		
		assertTrue("bonus did not go up by 5%", bonusbefore == bonusafter-(price*0.05)); 
		
		
	}
	
	
	@Test
	public void testRole() {
		
		LogoutCommand cmd1 = new LogoutCommand();//logging out of manager
		String[] line = {"logout"};
		assertTrue("failed to log out", cmd1.execute(line));
		
		LoginCommand cmd2 = new LoginCommand();//logging into dealer
		String[] line2 = {"login","w","w"};
		assertTrue("failed to login", cmd2.execute(line2));
		
		BestDealersCommand cmd3 = new BestDealersCommand();
		BestSellingCommand cmd4 = new BestSellingCommand();
		String[] line3 = {"bestdealers"};
		String[] line4 = {"bestselling"};
		
		assertFalse("dealer accessed manager function", cmd3.execute(line3)); //trying to access manager commands
		assertFalse("dealer accessed manager function", cmd4.execute(line4));
		
	}
	
	
	
}
