package Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarMaxPriceCommand implements Command {

	@Override
	public boolean execute(String[] args) {
		
		int max_price = -1;
		Set<Car> priceCars = new HashSet<Car>(); 
	
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			help();
			return false;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			help();
			return false;
		}
		
		try {
			max_price = Integer.parseInt(args[1]);
			
			Car[] sortedArr = CarDataManager.instance().find_maxPrice(max_price); // gets full list of cars
				
			if(sortedArr != null) {
				CommandLine.instance().Print("Here is the list of cars within the price range, sorted by price:");
				for (Car car : sortedArr) { // print list
					CommandLine.instance().Print(car.toString());
				}
			}
			else {
				CommandLine.instance().printError("No cars found");
				return false;
			}
		} catch (NumberFormatException e) {
			CommandLine.instance().printError("Wrong arguments were provided");
			return false;
		}
		return true;
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().printError("The correct use is: findmaxprice maxprice");
	}
}