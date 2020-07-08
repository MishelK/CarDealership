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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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
	
    public void salesReport() {
		
		Sale[] salesArr = new Sale[salesData.size()];
		salesData.toArray(salesArr);

		Arrays.sort(salesArr, new Comparator<Sale>() {

			@Override
			public int compare(Sale o1, Sale o2) {
				if(o1.getSale_id() == o2.getSale_id())
				    return 0;
				if(o1.getSale_id() > o2.getSale_id())
					return 1;
				else
					return -1;
			}
			
		});
	
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
    
    public void bestSeller() {
    	
    	
    	Sale[] saleArr = new Sale[salesData.size()];
    	salesData.toArray(saleArr);
    	
    	Map<String,Integer> modelMap = new HashMap<String,Integer>();
    	for(int i = 0; i < salesData.size(); i++) {
    		
    		if(modelMap.containsKey(saleArr[i].getCar_model())) {
    		   modelMap.put(saleArr[i].getCar_model(), modelMap.get(saleArr[i].getCar_model())+1);
    		}
    		else
    			modelMap.put(saleArr[i].getCar_model(), 1);
    		
    	}
    	
    	int maxValueInMap = (Collections.max(modelMap.values()));
    	
    	while(maxValueInMap != 0) {
    		
    		maxValueInMap = (Collections.max(modelMap.values()));
    		
    		for (Entry<String, Integer> entry : modelMap.entrySet()) {  // Itrate through hashmap
                if (entry.getValue()==maxValueInMap && maxValueInMap != 0) {
                    CommandLine.instance().Print("Car of model = '" + entry.getKey() + "' sold (" +maxValueInMap + ") times.");// Print the key with max value
               
                    modelMap.put(entry.getKey(), 0);
                }
            }
    		
    	}
    	
    	
    	
    }
    	
    
	
    public void bestDealers() {
    	
    	Sale[] saleArr = new Sale[salesData.size()];
    	salesData.toArray(saleArr);
    	User[] userArr = new User[UserDataManager.instance().getUsersData().size()];
    	UserDataManager.instance().getUsersData().toArray(userArr);
    	
    	Map<String,Integer> sellerMap = new HashMap<String,Integer>();
    	for(int i = 0; i < salesData.size(); i++) {
    		
    		if(sellerMap.containsKey(saleArr[i].getSeller_username())) {
    			sellerMap.put(saleArr[i].getSeller_username(), sellerMap.get(saleArr[i].getSeller_username())+1);
    		}
    		else
    			sellerMap.put(saleArr[i].getSeller_username(), 1);
    		
    	}
    	
    	
int     maxValueInMap = (Collections.max(sellerMap.values()));
    	
    	while(maxValueInMap != 0) {
    		
    		maxValueInMap = (Collections.max(sellerMap.values()));
    		
    		for (Entry<String, Integer> entry : sellerMap.entrySet()) {  // Iterate through hashmap
                if (entry.getValue()==maxValueInMap && maxValueInMap != 0) {
                    CommandLine.instance().Print("Dealer = '" + entry.getKey() + "' sold (" +maxValueInMap + ") cars.");// Print the key with max value
               
                    sellerMap.put(entry.getKey(), 0);
                }
            }
    		
    	}
    	
    }
    
    public boolean isEmpty() {
    	
    	if(salesData.isEmpty())
    		return true;
    	return false;
    	
    }
    

}
