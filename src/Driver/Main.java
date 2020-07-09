package Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CommandProcessor.Processor;
import CommandProcessor.Session;
import Database.CarDataManager;
import Database.SaleDataManager;
import Database.UserDataManager;
import View.CommandLine;

public class Main {

	public static void main(String[] args) {
		
		UserDataManager.instance().readDataFromFile();
		UserDataManager.instance().seedDatabase();
		//UserDataManager.instance().showdb();
		
		CarDataManager.instance().readDataFromFile();
		CarDataManager.instance().readCarIdDataFromFile();
		CarDataManager.instance().seedDatabase();
		//CarDataManager.instance().showdb();
		
		SaleDataManager.instance().readDataFromFile();
		SaleDataManager.instance().readSaleIdDataFromFile();
		//SaleDataManager.instance().salesReport();
	
		CommandLine.instance().Print("Welcome to Dealership Plus, Please login to the system");
		CommandLine.instance().Print("Having trouble log? type 'help'");
		
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
