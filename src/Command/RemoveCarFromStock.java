package Command;

import Database.CarDataManager;
import View.CommandLine;

public class RemoveCarFromStock implements Command{

	@Override
	public void execute(String[] args) {
		
		int id = -1;
		
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

		    if(CarDataManager.instance().delete(id)) {
		    	CommandLine.instance().Print("Car ID " + id + " has been removed from stock");
		    }
		    else
		    	CommandLine.instance().printError("Car ID " + id + " is not in stock");
		} catch (NumberFormatException e) {
			CommandLine.instance().printError("Wrong arguments were provided");
		}
		
	}
}
