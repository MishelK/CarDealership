package Command;

import Database.SaleDataManager;
import View.CommandLine;

public class SalesLogCommand implements Command{

	@Override
	public void execute(String[] args) {
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed for salesReport");
			return;
		}
		
		if(SaleDataManager.instance().isEmpty()) {
			CommandLine.instance().printError("There have been no sales");
			return;
		}
		
		SaleDataManager.instance().salesReport();
		
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}

}
