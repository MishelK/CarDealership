package Command;

import CommandProcessor.Session;
import Database.Car;
import Database.CarDataManager;
import View.CommandLine;

public class AddCarCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
		Car newcar = new Car();
		String brand;
		String model;
		int year;
		int mile;
		String color;
		int owners;
		int price;
		
		if(args.length > 8) {
			CommandLine.instance().printError("Too many arguments");
			help();
			return;
		}
		if(args.length < 8) {
			CommandLine.instance().printError("Not enough arguments were provided");
			help();
			return;
		}
		
		try {
		
			brand = args[1];
			model = args[2];
			year = Integer.parseInt(args[3]);
			mile = Integer.parseInt(args[4]);
			color = args[5];
			owners = Integer.parseInt(args[6]);
			price = Integer.parseInt(args[7]);
			
			newcar.setCarBrand(brand);
			newcar.setCar_model(model);
			newcar.setCar_year(year);
			newcar.setCarMile(mile);
			newcar.setCar_color(color);
			newcar.setCar_owners(owners);
			newcar.setCar_price(price);
			
			CarDataManager.instance().add_car(newcar);
			CommandLine.instance().Print("New car has been added");
			
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
		CommandLine.instance().printError("The correct use is: addcar brand model year mileage color owners price");
	}
}
