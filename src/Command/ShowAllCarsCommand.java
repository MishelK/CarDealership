package Command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class ShowAllCarsCommand implements Command {

	@Override
	public void execute(String[] args) {
	
		Set<Car> allcars = new HashSet<Car>(); 
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed for 'stock'");
			return;
		}
		allcars = CarDataManager.instance().findall(); // gets full list of cars
		
		Car[] carArr = new Car[allcars.size()];
		allcars.toArray(carArr);
		
		Car[] sortedArr = CarDataManager.instance().sortArrById(carArr);
		
		if(allcars.size() > 0 ) {
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
		
	}
}
