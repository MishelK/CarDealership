package Command;

import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class UpdateCarPriceCommand implements Command{

	@Override
	public void execute(String[] args) {
		
		int id = -1;
		int price = 0;
		
		if(args.length > 3) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 3) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
		
		try {
			id = Integer.parseInt(args[1]);
			price = Integer.parseInt(args[2]);
			
		    if(CarDataManager.instance().change_price(id, price)) {
		    	CommandLine.instance().Print("Car price has been updated");
		    }
		} catch (NumberFormatException e) {
			CommandLine.instance().printError("Wrong arguments were provided");
		}
		
	}
	
	

}
