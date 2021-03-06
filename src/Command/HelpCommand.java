package Command;

import CommandProcessor.Session;
import View.CommandLine;

public class HelpCommand implements Command {

	@Override
	public boolean execute(String[] args) {

		if(!Session.instance().isLoggedIn()) {
			CommandLine.instance().Print("To log in please type 'login' followed up with your username and password");
			CommandLine.instance().Print("In case this is your first time using Dealership Plus, please contact our support to get the root manager account" );
			return true;
		}
		
		else
			printInfo(); // once user is logged in - prints the opening sentence of 'help' command
		
		if(Session.instance().isAdmin()) { //prints all commands
			printManagerCmds();
			printBaseCmds();
		}
		
		else { // prints basic commands
			printBaseCmds();
		}	
		return true;
	}
	
	public void printInfo() {
		CommandLine.instance().Print("These are all the commands your role has access to:");
		CommandLine.instance().Print("(type in a command to see which arguments it gets)");
		// To get further assistance regarding which arguments to send to command just type in the command
		CommandLine.instance().Print("--------------------------------------------------");
	}
	
	public void printManagerCmds() {
		
		CommandLine.instance().Print("'adduser'	- adds new user to the system");
		CommandLine.instance().Print("'deleteuser'	- deletes an existing user from the system");
		CommandLine.instance().Print("'users'		- shows all registered users and thier roles");
		CommandLine.instance().Print("'addcar'	- adds a car to the stock");
		CommandLine.instance().Print("'updateprice'	- updates the price of a car in stock");
		CommandLine.instance().Print("'removecar'	- removes a car from the stock");
		CommandLine.instance().Print("'bestselling'	- shows the best selling models in order");
		CommandLine.instance().Print("'bestdealers'	- shows a list of all dealers ranked by their number of sales");
		
	}
	
	public void printBaseCmds() {
		
		CommandLine.instance().Print("'login'		- used to log in to your account");
		CommandLine.instance().Print("'logout'	- used to log out of current account");
		CommandLine.instance().Print("'findid'	- returns details about the car using id");
		CommandLine.instance().Print("'findbrand'	- shows all cars with the provided brand");
		CommandLine.instance().Print("'findmodel'	- shows all cars with the provided model");
		CommandLine.instance().Print("'findmaxprice'	- shows all cars under specified price");
		CommandLine.instance().Print("'sell'		- used to sell a vehicle");
		CommandLine.instance().Print("'stock'		- shows all cars in stock");
		CommandLine.instance().Print("'calcbonus'	- calculates your sale bonus so far");
		CommandLine.instance().Print("'sales'		- shows list of all sales");
		CommandLine.instance().Print("'exit'		- terminates the program");
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}

}
