package Command;
import java.time.LocalDate;

import Database.Car;
import Database.CarDataManager;
import Database.SaleDataManager;
import View.CommandLine;

public class SellCarIdCommand implements Command {

	// need to save dates and price when using this command
	@Override
	public void execute(String[] args) { // deletes car by id from stock set
		

		if(args.length > 3) {
			CommandLine.instance().printError("Too many arguments");
			help();
			return;
		}
		if(args.length < 3) {
			CommandLine.instance().printError("Not enough arguments were provided");
			help();
			return;
		}
		
		String id = args[1];
	    boolean numeric = true;

	    try {
	            Integer num = Integer.parseInt(id);
	    } catch (NumberFormatException e) {
	            numeric = false;
	      }

	    if(!numeric) {
	            CommandLine.instance().printError("Id needs to be a numeric value");;
	            return;
	    }
		
		String price = args[2];
	    numeric = true;

	    try {
	            Integer num = Integer.parseInt(price);
	    } catch (NumberFormatException e) {
	            numeric = false;
	      }

	    if(!numeric) {
	            CommandLine.instance().printError("Price needs to be a numeric value");;
	            return;
	    }
	    
		
		Car car = new Car();
		int idInt = Integer.parseInt(args[1]);
		LocalDate date = LocalDate.now(); // Create a date object
		

		car = CarDataManager.instance().getCarById(idInt);
		if(car == null) {
			CommandLine.instance().printError("Car [ Id = " + args[1] + " ] is not in stock (check the sales records to see if it has already been sold)");
			return;
		}
		
		if(SaleDataManager.instance().addsale(car, price, date))
			CarDataManager.instance().delete(idInt);
		CommandLine.instance().Print("Car [ Id = " + args[1] + " ] successfully sold for (" + price + ") on " + date);
     }

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().printError("The correct use is: sell id price");
	}
	}
