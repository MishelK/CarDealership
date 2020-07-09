package Command;

import CommandProcessor.Session;
import Database.CarDataManager;
import View.CommandLine;

public class RemoveCarFromStock implements Command{

	@Override
	public void execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
		int id = -1;
		
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
		else {
			CommandLine.instance().printError("Only managers can use this command");
		}
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().printError("The correct use is: removecar id");
	}
}
