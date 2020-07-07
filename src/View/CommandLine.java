package View;

import java.io.IOException;

public class CommandLine {
	static CommandLine instance;
	
	public static CommandLine instance() {
		
		if(instance == null)
			instance = new CommandLine();
		return instance;
	}
	
	public void Print(String s) {
		System.out.println(s);
	}
	
	public void printError(String s) {
		System.err.println(s);
	}
	
	public void clearConsole() {
		
	}

}
