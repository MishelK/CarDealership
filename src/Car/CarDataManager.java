package Car;

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


public class CarDataManager {
	
	private Set<Car> carsData = new HashSet<Car>(); // set without 2 same objects
	private final String fileName = "cars.dat";
	private static CarDataManager instance;
	
	public static CarDataManager getInstance() {
		if(instance == null) {
			instance = new CarDataManager();
		}
		return instance;
	}
	
	public boolean addAndWriteToFile(Car car) { // get a car and add to set 
		
		boolean addCarResult = carsData.add(car);
		boolean writeCarResult = false;
		
		// try-with-resources
		try (OutputStream fileOutputStream = new FileOutputStream(fileName); 
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
			
				objectOutputStream.writeObject(carsData);
				writeCarResult = true;
			
		} catch (IOException e) {
			e.printStackTrace();
			CommandLine.instance().printError("Exception while writing to file");
			writeCarResult = false;
		}
		return addCarResult && writeCarResult;
	}
	
		@SuppressWarnings("unchecked") // for casting
		public Set<Car> readDataFromFile() {
			
			File file = new File(fileName);
			if (file.length() == 0) {
				System.out.println("File is empty");
				return carsData;
			} // in case file is empty -> for the first time 
			
			try (InputStream fileInputStream = new FileInputStream(fileName);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
				
				// carsData returns as 'Object' 
				carsData = (Set<Car>) objectInputStream.readObject(); // casting back to set 
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Exception while reading from file");
			}
			
			return carsData;
		}
	

}

