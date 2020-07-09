package Command;

import View.CommandLine;

public class NotFoundCommand implements Command{
	
	@Override
	public boolean execute(String[] args) {
		
		CommandLine.instance().printError("Command not found");
		return true;
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().Print("Type 'help' to find the command you need");
	}

}
