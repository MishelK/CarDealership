package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import CommandProcessor.Session;
import View.CommandLine;

public class SaleDataManager {
	
	private final double BONUS_PERCENTAGE = 0.05; //5% bonus
	
	private Set<Sale> salesData = new HashSet<Sale>();
	private final String fileName = "sales.dat";
	private final String idFileName = "saleid.dat";
	private static SaleDataManager instance;
	private Integer lastID;
	
	public static SaleDataManager instance() {
		
		if(instance == null)
			instance = new SaleDataManager();
		return instance;
	}
	
	public boolean writeToFile() {
		
		
		boolean writeUserResult = false;
		
		try (OutputStream fileOutputStream = new FileOutputStream(fileName); 
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
			
			objectOutputStream.writeObject(salesData);
			writeUserResult = true;
			
		}catch (IOException e) {
			
			e.printStackTrace();
			writeUserResult = false;
			
		}
		
		return writeUserResult;
	}
	
	
	@SuppressWarnings("unchecked") // for casting
	public Set<Sale> readDataFromFile() {
		
		File file = new File(fileName);
		if (file.length() == 0) {
			return salesData;
		} // in case file is empty -> for the first time 
		
		try (InputStream fileInputStream = new FileInputStream(fileName);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			
			// salesData returns as 'Object' 
			salesData = (Set<Sale>) objectInputStream.readObject(); // casting back to set 
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			CommandLine.instance().printError("Exception while reading from file");
		}
		
		return salesData;
	}
	
    public void showdb() {
		
		Sale[] salesArr = new Sale[salesData.size()];
		salesData.toArray(salesArr);

		
	
		for(int i = 0; i < salesData.size(); i++) {
			CommandLine.instance().Print(salesArr[i].toString());
		}
	}
    
    public boolean delete(int id) {
    	
    	if(this.salesData.remove(new Sale(id))) {
			writeToFile(); // saves changes to file
			return true; // deletes car only if car in set
		}
		return false;
    	
    	
    }
    
    public boolean addsale(Car car, String price, LocalDate date) {
    	//Sale(int sale_id, String seller_username, String sale_price, LocalDate sale_date)
    	lastID++;
    	Sale sale = new Sale(lastID, Session.instance().getUsername(), price ,date, car );
    	if(salesData.add(sale)) {
    		writeToFile();
    		WriteIdToFile();
    		return true;
    	}
    	
    	return false;
		
    }
    
    public Integer readSaleIdDataFromFile() {
		
		File file = new File(idFileName); 
		if (file.length() == 0) { // in case file is empty, for the first time 
			lastID = 0;
			return lastID;
		} 
		try (InputStream fileInputStream = new FileInputStream(idFileName);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			
			lastID = (Integer) objectInputStream.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			CommandLine.instance().printError("Exception while reading from file");
		}
		return lastID;
		
	}
	
    public boolean WriteIdToFile() { 
		
		boolean writeIdResult = false;
		
		// try-with-resources
		try (OutputStream fileOutputStream = new FileOutputStream(idFileName); 
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
			
				objectOutputStream.writeObject(lastID);
				writeIdResult = true;
			
		} catch (IOException e) {
			e.printStackTrace();
			CommandLine.instance().printError("Exception while saving to file");
			writeIdResult = false;
		}
		return writeIdResult;
	}
    
    public int calcBonus() {
    	
    	String username = Session.instance().getUsername();
    	int bonus = 0;
    	Sale[] saleArr = new Sale[salesData.size()];
    	salesData.toArray(saleArr);
    	
    	for(int i = 0; i < salesData.size(); i++) {
    		if(saleArr[i].getSeller_username().equals(username))
    			bonus += Integer.parseInt(saleArr[i].getSale_price()) * BONUS_PERCENTAGE; // adds 5% of every sale the username has made
    		
    	}
    	
    	return bonus;
    }
	

}
