package Command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarModelCommand implements Command {
	
	@Override
	public void execute(String[] args) {
		
		Set<Car> modelcars = new HashSet<Car>();
	
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
		modelcars = CarDataManager.instance().find_model(args[1]);
	
		List<Car> carsSorted = modelcars.stream().collect(Collectors.toList()); // sorting list
		
		if(carsSorted.size() > 0) { // if list isnt empty
			for (Car car : carsSorted) { // print list
				CommandLine.instance().Print(car.toString());
			}
		}
		else
			CommandLine.instance().printError("No cars found");
	}
}
