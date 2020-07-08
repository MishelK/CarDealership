package Command;
import java.time.LocalDate;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class SellCarIdCommand implements Command {

	// need to save dates and price when using this command
	@Override
	public void execute(String[] args) { // deletes car by id from stock set
		
		Car car = new Car();
		int id = -1;
		LocalDate date = LocalDate.now(); // Create a date object
		int price =  0; // change

		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}

		try {
			id = Integer.parseInt(args[1]);
		
			car = CarDataManager.instance().find(id);
			if (car != null)
				price = car.getCar_price();
		
			if(CarDataManager.instance().delete(id)) {
				CommandLine.instance().Print("Car (" + args[1] + ") sold for " + price + " on " + date);
			}
			} catch (NumberFormatException e) {
				CommandLine.instance().printError("Wrong arguments were provided");
			}
	}
}

