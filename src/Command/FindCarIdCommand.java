package Command;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class FindCarIdCommand implements Command {

	@Override
	public boolean execute(String[] args) {
		
		int id = -1;
		Car car = null;
		
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
			id = Integer.parseInt(args[1]);
			if(id<0)
				return false;
		    car = CarDataManager.instance().find(id);
		    if(car != null) {
		    	CommandLine.instance().Print(car.toString());
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
		CommandLine.instance().printError("The correct use is: findid id");
	}
}
