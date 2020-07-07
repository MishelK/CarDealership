package Command;

import CommandProcessor.Session;
import Database.UserDataManager;
import View.CommandLine;

public class LoginCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		if(args.length > 3) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 3) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
		
		if(UserDataManager.instance().login(args[1], args[2])) {
			CommandLine.instance().clearConsole();
			CommandLine.instance().Print("Username (" + args[1] + ") successfully logged in");
			
		}
		else 
			CommandLine.instance().printError("Wrong username or password");

	}

}
