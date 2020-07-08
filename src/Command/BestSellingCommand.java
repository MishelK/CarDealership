package Command;

import CommandProcessor.Session;
import Database.SaleDataManager;
import View.CommandLine;

public class BestSellingCommand implements Command {

	@Override
	public void execute(String[] args) {
		
		if(Session.instance().isAdmin()) {
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed for bestSellers");
			return;
		}
		
		if(SaleDataManager.instance().isEmpty()) {
			CommandLine.instance().printError("There have been no sales");
			return;
		}
		
		SaleDataManager.instance().bestSeller();
		
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
