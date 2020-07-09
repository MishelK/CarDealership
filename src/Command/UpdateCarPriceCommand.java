package Command;

import CommandProcessor.Session;
import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class UpdateCarPriceCommand implements Command{

	@Override
	public boolean execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
		int id = -1;
		int price = 0;
		
		if(args.length > 3) {
			CommandLine.instance().printError("Too many arguments");
			help();
			return false;
		}
		if(args.length < 3) {
			CommandLine.instance().printError("Not enough arguments were provided");
			help();
			return false;
		}
		
		try {
			id = Integer.parseInt(args[1]);
			price = Integer.parseInt(args[2]);
			
		    if(CarDataManager.instance().change_price(id, price)) {
		    	CommandLine.instance().Print("Car price has been updated");
		    }
		} catch (NumberFormatException e) {
			CommandLine.instance().printError("Wrong arguments were provided");
			return false;
		}
		
	}
		else {
			CommandLine.instance().printError("Only managers can use this command");
			return false;
		}
		return true;
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().printError("The correct use is: updateprice id newprice");
	}
	

}
