package Command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarBrandCommand implements Command {
	
	@Override
	public void execute(String[] args) {
		
		String brand = args[1];
		
		Set<Car> brandcars = new HashSet<Car>();
		
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
		
		brandcars = CarDataManager.instance().find_brand(brand); // gets full list of cars
		
		List<Car> carsSorted = brandcars.stream().collect(Collectors.toList()); // sorting list
		
		if(carsSorted.size() > 0) {
			for (Car car : carsSorted) { // print list
				CommandLine.instance().Print(car.toString());
			}
		}
		else
			CommandLine.instance().printError("No cars found");
	}
}
		

