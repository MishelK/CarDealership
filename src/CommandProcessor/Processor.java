package CommandProcessor;

import java.util.HashMap;

import Command.AddUserCommand;
import Command.Command;
import Command.DeleteUserCommand;
import Command.DoNothingCommand;
import Command.LoginCommand;
import Command.LogoutCommand;
import Command.NotFoundCommand;
import View.CommandLine;

public class Processor {
	
	HashMap<String, Command> mCommandsMap;
	
	public Processor() {
		
		mCommandsMap = new HashMap<String, Command>();
		mCommandsMap.put( "exit" , new DoNothingCommand());
		mCommandsMap.put( "adduser" , new AddUserCommand());
		mCommandsMap.put( "deleteuser", new DeleteUserCommand());
		mCommandsMap.put( "login" , new LoginCommand());
		mCommandsMap.put( "logout", new LogoutCommand());
		
		
	}

	public void processLine(String line) {
		
		String[] sepLine = line.split(" ");
		
		if(!Session.instance().isLoggedIn()) {
			if(!(sepLine[0].toLowerCase().equals("login") || sepLine[0].toLowerCase().equals("exit")|| sepLine[0].toLowerCase().equals("help"))) {
				CommandLine.instance().printError("Please login to the system, type 'help' for instructions");
				return;
			}
		}
			
	
		mCommandsMap.getOrDefault(sepLine[0].toLowerCase() , new NotFoundCommand()).execute(sepLine);
	}
	
}
