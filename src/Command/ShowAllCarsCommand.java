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
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		allcars = CarDataManager.instance().findall(); // gets full list of cars
		
		List<Car> carsSorted = allcars.stream().collect(Collectors.toList()); // sorting list
		
		if(allcars.size() > 0 ) {
			for (Car car : carsSorted) { // print list
				CommandLine.instance().Print(car.toString());
			}
		}
		else
			CommandLine.instance().printError("No cars found");
	}
}
