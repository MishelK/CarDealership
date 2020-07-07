package Command;

import CommandProcessor.Session;
import View.CommandLine;

public class LogoutCommand implements Command{

	@Override
	public void execute(String[] args) {
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed to logout");
			return;
		}
		
		Session.instance().logout();
		CommandLine.instance().Print("Logged out successfully");
	}
	

}
