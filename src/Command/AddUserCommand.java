package Command;



import Database.UserDatabase;
import View.CommandLine;

public class AddUserCommand implements Command {

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
		
		if(UserDatabase.instance().add(args[1], args[2])) 
			CommandLine.instance().Print("Username (" + args[1] + ") successfully added");
		else 
			CommandLine.instance().printError("Username (" + args[1] + ") already exists");
		
		
		
	}

}
