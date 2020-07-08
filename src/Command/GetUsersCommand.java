package Command;

import CommandProcessor.Session;
import Database.SaleDataManager;
import Database.UserDataManager;
import View.CommandLine;

public class GetUsersCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed for getUsers");
			return;
		}
		
		UserDataManager.instance().printUsers();
		
	}
		else {
			CommandLine.instance().printError("Only managers can use this command");
		}
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}
}
