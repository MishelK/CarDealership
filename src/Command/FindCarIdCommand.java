package Command;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarIdCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		int id = Integer.parseInt(args[1]);
		Car car = null;
		
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
	    car = CarDataManager.instance().find(id);
	    if(car != null) {
	    	CommandLine.instance().Print(car.toString());
	    }
		else
			CommandLine.instance().printError("No cars found");
	}
}
