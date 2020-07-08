package Command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarMaxPriceCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		int max_price = -1;
		Set<Car> priceCars = new HashSet<Car>(); 
	
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
		
		try {
			max_price = Integer.parseInt(args[1]);
			
			priceCars = CarDataManager.instance().find_maxPrice(max_price); // gets full list of cars
			
			List<Car> carsSorted = priceCars.stream().collect(Collectors.toList()); // sorting list
			
			if(priceCars.size() > 0 ) {
				for (Car car : carsSorted) { // print list
					CommandLine.instance().Print(car.toString());
				}
			}
			else
				CommandLine.instance().printError("No cars found");
		} catch (NumberFormatException e) {
			CommandLine.instance().printError("Wrong arguments were provided");
		}

	}
}