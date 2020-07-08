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
		
		
		Set<Car> carsSorted = new HashSet<Car>();
		
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			help();
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			help();
			return;
		}
		
		String brand = args[1];
		
		carsSorted = CarDataManager.instance().find_brand(brand); // gets full list of cars
		
		Car[] carArr = new Car[carsSorted.size()];
		carsSorted.toArray(carArr);
		
		Car[] sortedArr = CarDataManager.instance().sortArrById(carArr);
		
		if(carsSorted.size() > 0) {
			for (Car car : sortedArr) { // print list
				CommandLine.instance().Print(car.toString());
			}
		}
		else
			CommandLine.instance().printError("No cars found");
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().Print("The correct use is: findbrand brand");
	}
}
		

