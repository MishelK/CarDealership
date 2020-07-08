package CommandProcessor;

import java.util.HashMap;

import Command.AddCarCommand;
import Command.AddUserCommand;
import Command.Command;
import Command.DeleteUserCommand;
import Command.DoNothingCommand;
import Command.FindCarBrandCommand;
import Command.FindCarIdCommand;
import Command.FindCarMaxPriceCommand;
import Command.FindCarModelCommand;
import Command.FindCarPriceCommand;
import Command.LoginCommand;
import Command.LogoutCommand;
import Command.NotFoundCommand;
import Command.SellCarIdCommand;
import Command.ShowAllCarsCommand;
import View.CommandLine;

public class Processor {
	
	HashMap<String, Command> mCommandsMap;
	
	public Processor() {
		
		mCommandsMap = new HashMap<String, Command>();
		mCommandsMap.put( "exit" , new DoNothingCommand());
		mCommandsMap.put( "adduser" , new AddUserCommand());
		mCommandsMap.put( "deleteuser", new DeleteUserCommand());
		mCommandsMap.put( "login" , new LoginCommand());
		mCommandsMap.put( "logout", new LogoutCommand());
		// car commands
		mCommandsMap.put( "sell", new SellCarIdCommand());
		mCommandsMap.put( "findid", new FindCarIdCommand());
		mCommandsMap.put( "findbrand", new FindCarBrandCommand());
		mCommandsMap.put( "findmodel", new FindCarModelCommand()); 
		mCommandsMap.put( "findprice", new FindCarPriceCommand()); // NOT WORKING
		mCommandsMap.put( "allcars", new ShowAllCarsCommand()); 
		mCommandsMap.put( "maxprice", new FindCarMaxPriceCommand()); 
		mCommandsMap.put( "addcar", new AddCarCommand()); // ?
		
		
//		mCommandsMap.put( "update", new UpdateCarCommand();

	}

	public void processLine(String line) {
		
		String[] sepLine = line.split(" ");
		
		if(!Session.instance().isLoggedIn()) {
			if(!(sepLine[0].toLowerCase().equals("login") || sepLine[0].toLowerCase().equals("exit")|| sepLine[0].toLowerCase().equals("help"))) {
				CommandLine.instance().printError("Please login to the system, type 'help' for instructions");
				return;
			}
		}
			
	
		mCommandsMap.getOrDefault(sepLine[0].toLowerCase() , new NotFoundCommand()).execute(sepLine);
	}
	
}
