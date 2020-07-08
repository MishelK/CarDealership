package Command;

import CommandProcessor.Session;
import Database.UserDataManager;
import View.CommandLine;

public class LoginCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		if(args.length > 3) {
			CommandLine.instance().printError("Too many arguments");
			help();
			return;
		}
		if(args.length < 3) {
			CommandLine.instance().printError("Not enough arguments were provided");
			help();
			return;
		}
		
		if(UserDataManager.instance().login(args[1], args[2])) {
			CommandLine.instance().clearConsole();
			CommandLine.instance().Print("Username (" + args[1] + ") successfully logged in");
			CommandLine.instance().Print("Type 'help' to get the list of commands");
			
		}
		else 
			CommandLine.instance().printError("Wrong username or password");

	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().Print("The correct use is: login username password");
	}

}
