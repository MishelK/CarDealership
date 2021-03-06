package Command;



import CommandProcessor.Session;
import Database.UserDataManager;
import View.CommandLine;

public class AddUserCommand implements Command {

	@Override
	public boolean execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
		if(args.length > 4) {
			CommandLine.instance().printError("Too many arguments");
			help();
			return false;
		}
		if(args.length < 4) {
			CommandLine.instance().printError("Not enough arguments were provided");
			help();
			return false;
		}
		
			
			if(!(args[3].equals("0") || args[3].equals("1"))) {
				CommandLine.instance().printError("Rank can only be 0 or 1");
				return false;
			}
		
		    if(UserDataManager.instance().add(args[1], args[2], args[3])) 
			    CommandLine.instance().Print("Username (" + args[1] + ") successfully added");
		    else {
			    CommandLine.instance().printError("Username (" + args[1] + ") already exists");
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
		CommandLine.instance().printError("The correct use is: adduser username password rank, where rank is (0 = dealer, 1 = manager)");
	}
}
