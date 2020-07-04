package Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CommandProcessor.Processor;
import Database.UserDatabase;

public class Main {

	public static void main(String[] args) {
		
		UserDatabase.instance().seedDatabase();
		
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
		Processor processor = new Processor();
		String inputStr = null;
		
		do {
			
			try {
				inputStr = obj.readLine();
			}catch (IOException e) {
				System.err.println("Error reading from console");
			}
			processor.processLine(inputStr);
			
		} while(!inputStr.toLowerCase().equals("exit"));
		
	}

}
