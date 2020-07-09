package Command;

import CommandProcessor.Session;
import Database.UserDataManager;
import View.CommandLine;

public class DeleteUserCommand implements Command {

	@Override
	public boolean execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
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
		
		if(Session.instance().isAdmin()) {
			if(args[1].equals("root")) {
				CommandLine.instance().printError("Cannot delete root manager");
				return false;
			}
			else {
			    if(UserDataManager.instance().delete(args[1],args[2])) 
				    CommandLine.instance().Print("Username (" + args[1] + ") successfully removed");
			    else {
				    CommandLine.instance().printError("Username (" + args[1] + ") does not exist");
				    return false;
			    }
		     }
		    }
		else {
			CommandLine.instance().printError("Only admins can delete users");
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
		CommandLine.instance().printError("The correct use is: deleteuser username password");
	}
}
