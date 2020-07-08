package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import View.CommandLine;

public class SaleDataManager {
	
	private Set<Sale> salesData = new HashSet<Sale>();
	private final String fileName = "sales.dat";
	private static SaleDataManager instance;
	
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

}
