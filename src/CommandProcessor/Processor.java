package CommandProcessor;

import java.util.HashMap;

import Command.AddCarCommand;
import Command.AddUserCommand;
import Command.BestDealersCommand;
import Command.BestSellingCommand;
import Command.CalcBonusCommand;
import Command.Command;
import Command.DeleteUserCommand;
import Command.DoNothingCommand;
import Command.FindCarBrandCommand;
import Command.FindCarIdCommand;
import Command.FindCarMaxPriceCommand;
import Command.FindCarModelCommand;
import Command.GetUsersCommand;
import Command.HelpCommand;
//import Command.FindCarPriceCommand;
import Command.LoginCommand;
import Command.LogoutCommand;
import Command.NotFoundCommand;
import Command.RemoveCarFromStock;
import Command.SalesLogCommand;
import Command.SellCarIdCommand;
import Command.ShowAllCarsCommand;
import Command.UpdateCarPriceCommand;
import View.CommandLine;

public class Processor {
	
	HashMap<String, Command> mCommandsMap;
	
	public Processor() {
		
		mCommandsMap = new HashMap<String, Command>();
		mCommandsMap.put( "help", new HelpCommand());
		// user commands
		mCommandsMap.put( "exit" , new DoNothingCommand()); //base
		mCommandsMap.put( "adduser" , new AddUserCommand()); //manager
		mCommandsMap.put( "deleteuser", new DeleteUserCommand()); //manager
		mCommandsMap.put( "login" , new LoginCommand()); //base
		mCommandsMap.put( "logout", new LogoutCommand()); //base
		mCommandsMap.put( "users", new GetUsersCommand()); //manager
		// car commands
		mCommandsMap.put( "findid", new FindCarIdCommand()); //base
		mCommandsMap.put( "findbrand", new FindCarBrandCommand()); //base
		mCommandsMap.put( "findmodel", new FindCarModelCommand());  //base
		mCommandsMap.put( "stock", new ShowAllCarsCommand()); //base
		mCommandsMap.put( "findmaxprice", new FindCarMaxPriceCommand()); //base
		mCommandsMap.put( "addcar", new AddCarCommand()); //manager
		mCommandsMap.put( "updateprice", new UpdateCarPriceCommand()); //manager
		mCommandsMap.put( "removecar", new RemoveCarFromStock()); //manager
		// sale commands
		mCommandsMap.put( "calcbonus", new CalcBonusCommand()); //base
		mCommandsMap.put( "bestselling", new BestSellingCommand()); //manager
	    mCommandsMap.put( "bestdealers", new BestDealersCommand()); //manager
		mCommandsMap.put( "sales", new SalesLogCommand()); //base
		mCommandsMap.put( "sell", new SellCarIdCommand()); //base


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
