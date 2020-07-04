package CommandProcessor;

import java.util.HashMap;

import Command.AddUserCommand;
import Command.Command;
import Command.DeleteUserCommand;
import Command.DoNothingCommand;
import Command.LoginCommand;
import Command.NotFoundCommand;
import Command.PrintCommand;

public class Processor {
	
	HashMap<String, Command> mCommandsMap;
	
	public Processor() {
		
		mCommandsMap = new HashMap<String, Command>();
		mCommandsMap.put( "exit" , new DoNothingCommand());
		mCommandsMap.put( "print" , new PrintCommand());
		mCommandsMap.put( "adduser" , new AddUserCommand());
		mCommandsMap.put( "deleteuser", new DeleteUserCommand());
		mCommandsMap.put( "login" , new LoginCommand());
		
		
	}

	public void processLine(String line) {
		
		String[] sepLine = line.split(" ");
		mCommandsMap.getOrDefault(sepLine[0].toLowerCase() , new NotFoundCommand()).execute(sepLine);
	}
	
}
