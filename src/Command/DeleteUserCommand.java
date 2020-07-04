package Command;

import CommandProcessor.Session;
import Database.UserDatabase;
import View.CommandLine;

public class DeleteUserCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("Not enough arguments were provided");
			return;
		}
		
		if(Session.instance().isAdmin()) {
			if(UserDatabase.instance().delete(args[1])) 
				CommandLine.instance().Print("Username (" + args[1] + ") successfully removed");
			else 
				CommandLine.instance().printError("Username (" + args[1] + ") does not exist");
		}
		else {
			CommandLine.instance().printError("Only admin can delete users");
		}
		

	}

}
