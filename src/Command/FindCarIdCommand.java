package Command;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarIdCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		int id = -1; //Integer.parseInt(args[1]);
		Car car = null;
		
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
		    if(car != null) {
		    	CommandLine.instance().Print(car.toString());
		    }
		} catch (NumberFormatException e) {
			CommandLine.instance().printError("Wrong arguments were provided");
		}
	}
}
