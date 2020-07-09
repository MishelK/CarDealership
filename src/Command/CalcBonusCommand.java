package Command;

import Database.SaleDataManager;
import View.CommandLine;

public class CalcBonusCommand implements Command {

	@Override
	public boolean execute(String[] args) {
		
		if(args.length > 1) {
			CommandLine.instance().printError("No arguments are needed for calcBonus");
			return false;
		}
		
		
		int bonus = SaleDataManager.instance().calcBonus();
		CommandLine.instance().Print("So far your monthly bonus is at = " +bonus);
		return true;
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}
	
	

}
