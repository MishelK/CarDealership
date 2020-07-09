package Command;

import CommandProcessor.Session;
import View.CommandLine;

public class LogoutCommand implements Command{

	@Override
	public boolean execute(String[] args) {
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed to logout");
			return false;
		}
		
		Session.instance().logout();
		CommandLine.instance().Print("Logged out successfully");
		return true;
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}
	

}
