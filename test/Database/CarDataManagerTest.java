package Database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Command.AddCarCommand;
import Command.FindCarBrandCommand;
import Command.FindCarIdCommand;
import Command.FindCarMaxPriceCommand;
import Command.FindCarModelCommand;
import Command.LoginCommand;
import Command.LogoutCommand;
import Command.RemoveCarFromStock;
import Command.SellCarIdCommand;
import Command.UpdateCarPriceCommand;

public class CarDataManagerTest {

	CarDataManager carDM;
	static DatabaseManager db = new DatabaseManager();
	
	@BeforeClass
	public static void loadDb() {
		
		db.loadDatabase();
		LoginCommand cmd = new LoginCommand();
		String[] line = {"login","root","root"};
		cmd.execute(line);
	}
	@Before
	public void cardm() {
		carDM = new CarDataManager();
	}
	
	@Test
	public void testadd_car() {
		
		
		AddCarCommand cmd = new AddCarCommand();
		
		// add car normally
		String[] line = {"addcar","mazda","3","2015","200","green","3","1999"};
		assertTrue("failed to add car", cmd.execute(line));
		
		// add car without enough arguments
		String[] line1 = {"addcar", "car"};
		assertFalse("added car without enough arguments", cmd.execute(line1));
		
		// add car with too much arguments
		String[] line2 = {"addcar","mazda","3","2015","200","green","3","1999","another"};
		assertFalse("added car with too much arguments", cmd.execute(line2));
		
		// add car with wrong arguments
		String[] line3 = {"addcar","mazda","3","should","be","false","inthe","end"};
		assertFalse("added car with wrong parameters", cmd.execute(line3));
		
//		// logging out of manager
//		LogoutCommand cmd2 = new LogoutCommand(); 
//        String[] line4 = {"logout"};
//        assertTrue("failed to log out", cmd2.execute(line4));
//
//        // logging into dealer
//        LoginCommand cmd3 = new LoginCommand();
//        String[] line5 = {"login","w","w"};
//        assertTrue("failed to login", cmd3.execute(line5));
//		
//        // add car as a non-administrator
//     	String[] line6 = {"addcar","mazda","3","2015","200","green","3","1999"};
//     	assertFalse("not admin added a car", cmd.execute(line6));
	}

	@Test
	public void testFindCarBrandCommand() {
		
		FindCarBrandCommand cmd = new FindCarBrandCommand();
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		String brand = arr[0].getCarBrand();
		
		// find car normally
		String[] line = {"findbrand",brand};
		assertTrue("failed to find brand", cmd.execute(line));
		
		// find brand without enough arguments
		String[] line2 = {"findbrand"};
		assertFalse("found brand without enough parameters", cmd.execute(line2));
	
		// find brand with too many arguments
		String[] line3 = {"findbrand",brand,brand};
		assertFalse("found brand with too many parameters", cmd.execute(line3));
	}

	@Test
	public void testFindCarModelCommand() {
		
		FindCarModelCommand cmd = new FindCarModelCommand();
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		String model = arr[0].getCar_model();
		
		// find model normally
		String[] line = {"findmodel",model};
		assertTrue("failed to find brand", cmd.execute(line));
		
		// find model without enough arguments
		String[] line2 = {"findmodel"};
		assertFalse("found model without enough parameters", cmd.execute(line2));
	
		// find model with too many arguments
		String[] line3 = {"findmodel",model,model};
		assertFalse("found model with too many parameters", cmd.execute(line3));
	}

	@Test
	public void testFindCarIdCommand() { 

		FindCarIdCommand cmd = new FindCarIdCommand();
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		int id = arr[0].getCar_id();
		
		// find id normally
		String[] line = {"findid",Integer.toString(id)};
		assertTrue("failed to find id", cmd.execute(line));
		
		// find id with wrong argument
		String[] line2 = {"findid","hello"};
		assertFalse("found id with wring args", cmd.execute(line2));
		
		// find id without enough arguments
		String[] line3 = {"findid"};
		assertFalse("found id without enough parameters", cmd.execute(line3));
	
		// find id with too many arguments
		String[] line4 = {"findid",Integer.toString(id),Integer.toString(id+1)};
		assertFalse("found id with too many parameters", cmd.execute(line4));
	
		// find id with negative id
		String[] line5 = {"findid","-5"};
		assertFalse("found id with negative parameter", cmd.execute(line5));
	}
	
	@Test
	public void testFindCarMaxPriceCommand() {
		
		FindCarMaxPriceCommand cmd = new FindCarMaxPriceCommand();
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		int price = arr[0].getCar_price();
		
		// find price normally
		String[] line = {"findprice",Integer.toString(price)};
		assertTrue("failed to find cars < price", cmd.execute(line));
		
		// find price without enough arguments
		String[] line2 = {"findprice"};
		assertFalse("found price without enough parameters", cmd.execute(line2));
	
		// find price with too many arguments
		String[] line3 = {"findprice",Integer.toString(price),Integer.toString(price+1)};
		assertFalse("found model with too many parameters", cmd.execute(line3));
	
		// find price with wrong argument
		String[] line4 = {"findprice","hello"};
		assertFalse("found price with wrong args", cmd.execute(line4));
	}
	
	@Test
	public void testRemoveCarFromStock() {
	
		RemoveCarFromStock cmd = new RemoveCarFromStock();
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		int id = arr[0].getCar_id();
		
		// remove car normally
		String[] line = {"removecar",Integer.toString(id)};
		assertTrue("failed to remove car", cmd.execute(line));
		
		// remove car without enough arguments
		String[] line2 = {"removecar"};
		assertFalse("removed car without enough parameters", cmd.execute(line2));
	
		// remove car with too many arguments
		String[] line3 = {"removecar",Integer.toString(id),Integer.toString(id+1)};
		assertFalse("deleted car with too many parameters", cmd.execute(line3));
	
		// remove car with wrong argument
		String[] line4 = {"removecar","hello"};
		assertFalse("removed car with wrong args", cmd.execute(line4));
	}
	
	@Test
	public void testUpdateCarPriceCommand() {
		
		UpdateCarPriceCommand cmd = new UpdateCarPriceCommand();
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		int id = arr[0].getCar_id();
		int price = arr[0].getCar_price();
		
		// update price normally
		String[] line = {"updateprice",Integer.toString(id),Integer.toString(price+99)};
		assertTrue("failed to update price", cmd.execute(line));
		
		// update price without enough arguments
		String[] line2 = {"updateprice"};
		assertFalse("updated price without enough parameters", cmd.execute(line2));
	
		// update price with too many arguments
		String[] line3 = {"updateprice",Integer.toString(id),"1000","5000"};
		assertFalse("updated price with too many parameters", cmd.execute(line3));
	
		// update price with wrong arguments
		String[] line4 = {"updateprice","living","lie"};
		assertFalse("updated price with wrong args", cmd.execute(line4));
	}
	
	@Test
	public void testSellCarIdCommand() {
		
		SellCarIdCommand cmd = new SellCarIdCommand();
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		int id = arr[0].getCar_id();
		
		// sell car normally
		String[] line = {"sell",Integer.toString(id),"1000"};
		assertTrue("failed to sell car", cmd.execute(line));
		
		// sell car without enough arguments
		String[] line2 = {"sell"};
		assertFalse("sold car without enough parameters", cmd.execute(line2));
	
		// sell car with too many arguments
		String[] line3 = {"sell",Integer.toString(id),"1000","5000"};
		assertFalse("sold car with too many parameters", cmd.execute(line3));
	
		// update price with wrong arguments
		String[] line4 = {"sell","living","lie"};
		assertFalse("sold car with wrong args", cmd.execute(line4));
	}
	
	@Test
	public void testNoAdminAccess() {
		
		UpdateCarPriceCommand prc = new UpdateCarPriceCommand();
		RemoveCarFromStock rmv = new RemoveCarFromStock();
		AddCarCommand add = new AddCarCommand();
		
		
		Set<Car> carsData = CarDataManager.instance().getCarsData();
		Car[] arr = new Car[carsData.size()];
		carsData.toArray(arr);
		
		int id = arr[0].getCar_id();
		int price = arr[0].getCar_price();
		
		LogoutCommand cmd1 = new LogoutCommand();//logging out of manager
        String[] line = {"logout"};
        assertTrue("failed to log out", cmd1.execute(line));

        LoginCommand cmd2 = new LoginCommand();//logging into dealer
        String[] line2 = {"login","w","w"};
        assertTrue("failed to login", cmd2.execute(line2));
        
        
		// update price normally
		String[] line3 = {"updateprice",Integer.toString(id),Integer.toString(price+99)};
		assertFalse("failed to update price", prc.execute(line3));
        
		
		// remove car normally
		String[] line4 = {"removecar",Integer.toString(id)};
		assertFalse("failed to remove car", rmv.execute(line4));
        
		
        // add car as a non-administrator
     	String[] line6 = {"addcar","mazda","3","2015","200","green","3","1999"};
     	assertFalse("not admin added a car", add.execute(line6));
	}
}
