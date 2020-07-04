package Command;

import View.CommandLine;

public class PrintCommand implements Command {
	
	@Override
	public void execute(String[] args) {
		
		if(args.length > 2) {
			CommandLine.instance().printError("Too many arguments");
			return;
		}
		if(args.length < 2) {
			CommandLine.instance().printError("There were no arguments provided to Print");
			return;
		}
        CommandLine.instance().Print(args[1]);
		
	}

}
