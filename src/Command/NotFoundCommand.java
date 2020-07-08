package Command;

import View.CommandLine;

public class NotFoundCommand implements Command{
	
	@Override
	public void execute(String[] args) {
		
		CommandLine.instance().printError("Command not found");
		
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		CommandLine.instance().Print("Type 'help' to find the command you need");
	}

}
