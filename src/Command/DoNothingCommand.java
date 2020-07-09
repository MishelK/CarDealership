package Command;

import View.CommandLine;

public class DoNothingCommand implements Command{
	
	@Override
	public boolean execute(String[] args) {
		
		return true;
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}

}
