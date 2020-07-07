package Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CommandProcessor.Processor;
import CommandProcessor.Session;
import Database.UserDataManager;

public class Main {

	public static void main(String[] args) {
		
		UserDataManager.instance().readDataFromFile();
		UserDataManager.instance().seedDatabase();
		UserDataManager.instance().showdb();
	
		
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
