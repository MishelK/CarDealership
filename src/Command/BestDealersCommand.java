package Command;

import CommandProcessor.Session;
import Database.SaleDataManager;
import View.CommandLine;

public class BestDealersCommand implements Command {

	@Override
	public boolean execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed for bestDealers");
			return false;
		}
		
		if(SaleDataManager.instance().isEmpty()) {
			CommandLine.instance().printError("There have been no sales");
			return true;
		}
		
		SaleDataManager.instance().bestDealers();
		
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
		
	}
}
