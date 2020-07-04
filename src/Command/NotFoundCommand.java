package Command;

import View.CommandLine;

public class NotFoundCommand implements Command{
	
	@Override
	public void execute(String[] args) {
		
		CommandLine.instance().printError("Command not found");
		
	}

}
