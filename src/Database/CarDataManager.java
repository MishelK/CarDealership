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


public class CarDataManager {
	
	private Set<Car> carsData = new HashSet<Car>(); // set without 2 same objects
	private final String fileName = "cars.dat";
	private static CarDataManager instance;
	
	
	public static CarDataManager instance() { // singleton
		if(instance == null) {
			instance = new CarDataManager();
		}
		return instance;
	}
	
	// DELETE car from set
	public void delete(int id) {
		this.carsData.remove(new Car(id)); // deletes car only if car in set
		WriteToFile(); // saves changes to file
	}
	
	
	// ADD car to set
	public void add_car(Car car) { 
		// get a car and add to set 
		if (this.carsData.contains(car)) { // check if car already in set
			CommandLine.instance().printError("Car " + car.getCar_id() + " already exists");
		}
		this.carsData.add(car); // adds car to set
		WriteToFile(); // saves changes to file
	}
	
	
	// FIND car by ID or NULL
	public Car find(int id){
		if (this.carsData.contains( new Car(id) )) {
			for (Car car : carsData) {
				if (car.getCar_id() == id ) {
					return car;
				}
			}
		}
		CommandLine.instance().printError("Car ID: " + id + " not found");
		return null;
	}
	
	
	// FIND cars set by BRAND
	public Set<Car> find_brand(String brand) {
		Set<Car> newset = new HashSet<Car>();
		
		for (Car car : carsData) {
			if (car.getCarBrand() == brand ) {
				newset.add(car);
			}
		}
		return newset;
	}
	
	// FIND cars set by MODEL
	public Set<Car> find_model(String model) {
		Set<Car> newset = new HashSet<Car>();
		
		for (Car car : carsData) {
			if (car.getCar_model() == model ) {
				newset.add(car);
			}
		}
		return newset;
	}
	
	
	// SAVE to file
	public boolean WriteToFile() { 
		
		boolean writeCarResult = false;
		
		// try-with-resources
		try (OutputStream fileOutputStream = new FileOutputStream(fileName); 
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
			
				objectOutputStream.writeObject(carsData);
				writeCarResult = true;
			
		} catch (IOException e) {
			e.printStackTrace();
			CommandLine.instance().printError("Exception while saving to file");
			writeCarResult = false;
		}
		return writeCarResult;
	}
		
	
	
	// READ from file
	@SuppressWarnings("unchecked") // for casting
	public Set<Car> readDataFromFile() {
			
			File file = new File(fileName); 
			if (file.length() == 0) { // in case file is empty, for the first time 
				System.out.println("File is empty");
				return carsData;
			} 
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
	
	
	public void seedDatabase() {
		
			carsData.add(new Car(11));
			WriteToFile();
		}
	
		
	
	public void showdb() {
		
		Car[] carsArr = new Car[carsData.size()];
		carsData.toArray(carsArr);

		
	
		for(int i = 0; i < carsData.size(); i++) {
			CommandLine.instance().Print(carsArr[i].toString());	
		}
	}
	

}
