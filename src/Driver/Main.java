package Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CommandProcessor.Processor;
import CommandProcessor.Session;
import Database.CarDataManager;
import Database.DatabaseManager;
import Database.SaleDataManager;
import Database.UserDataManager;
import View.CommandLine;

public class Main {

	public static void main(String[] args) {
		
		DatabaseManager db = new DatabaseManager();
		db.loadDatabase();
	
		CommandLine.instance().Print("Welcome to Dealership Plus, Please login to the system");
		CommandLine.instance().Print("Having trouble logging into your account? type 'help'");
		
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
