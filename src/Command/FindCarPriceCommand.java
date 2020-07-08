package Command;

import java.util.HashSet;
import java.util.Set;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarPriceCommand implements Command {
	@Override
	public void execute(String[] args) {
		int min_price = Integer.parseInt(args[1]);
		int max_price = Integer.parseInt(args[2]);
		
		Set<Car> pricecars = new HashSet<Car>();
	
		if(args.length > 3) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 3) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
		
		pricecars = CarDataManager.instance().find_price(min_price, max_price);
		
		for (Car car : pricecars) { // print set
			car.toString();
			}
	}

}
