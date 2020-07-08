package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import View.CommandLine;


public class CarDataManager {
	
	private Set<Car> carsData = new HashSet<Car>(); // set without 2 same objects
	private final String fileName = "cars.dat";
	private final String idFileName = "carid.dat";
	private static CarDataManager instance;
	private Integer lastID;
	
	
	public static CarDataManager instance() { // singleton
		if(instance == null) {
			instance = new CarDataManager();
		}
		return instance;
	}
	
	// DELETE car from set
	public boolean delete(int id) {
		if(this.carsData.remove(new Car(id))) {
			WriteToFile(); // saves changes to file
			return true; // deletes car only if car in set
		}
		return false;
	}
	
	
	// ADD car to set
	public boolean add_car(Car car) { 
		// get a car and add to set 
		if (this.carsData.contains(car)) { // check if car already in set
			CommandLine.instance().printError("Car " + car.getCar_id() + " already exists");
			return false;
		}
		lastID++;
		car.setCar_id(lastID);
		if(this.carsData.add(car)) { // adds car to set
			WriteToFile(); // saves changes to file
			WriteIdToFile();
			return true;
		}
		return false;
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
		CommandLine.instance().printError("Car ID " + id + " not found");
		return null;
	}
	
	
	// FIND cars set by BRAND
	public Set<Car> find_brand(String brand) {
		
		Set<Car> brandCar = new HashSet<Car>();
		
		for (Car car : carsData) {
			if (car.getCarBrand().equals(brand)) {
				brandCar.add(car);
			}
		}
		return brandCar;
	}
	
	// FIND cars set by MODEL
	public Set<Car> find_model(String model) {
		Set<Car> modelCar = new HashSet<Car>();
		
		for (Car car : carsData) {
			if (car.getCar_model().equals(model)) {
				modelCar.add(car);
			}
		}
		return modelCar;
	}
	
	// FIND car by max price
	public Set<Car> find_maxPrice(int price) {
		Set<Car> priceCar = new HashSet<Car>();
		
		for (Car car : carsData) {
			if (car.getCar_price() < price)
				priceCar.add(car);
		}
		return priceCar;
	}
	
	// FIND cars by price
	public Set<Car> find_price(int min_price, int max_price) {
		Set<Car> priceCar = new HashSet<Car>();
		
		for (Car car : carsData) {
			if((car.getCar_price() >= min_price) & (car.getCar_price() < max_price) ) { // price between min and max
				priceCar.add(car);
			}
		}
		return priceCar;
	}
	
	// FIND ALL CARS
	public Set<Car> findall() {
		Set<Car> newset = new HashSet<Car>();
		
		for (Car car : this.carsData) {
				newset.add(car);
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
				return carsData;
			} 
			try (InputStream fileInputStream = new FileInputStream(fileName);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
				
				// carsData returns as 'Object' 
				carsData = (Set<Car>) objectInputStream.readObject(); // casting back to set 
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				CommandLine.instance().printError("Exception while reading from file");
			}
			return carsData;
		}
	
	public Integer readCarIdDataFromFile() {
		
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
	
	
	public void seedDatabase() {
		Car testcar = new Car();
		Car testcar2 = new Car();
		
		testcar.setCar_id(69);
		testcar.setCar_color("green");
		testcar.setCar_model("323");
		testcar.setCar_owners(3);
		testcar.setCar_price(15000);
		testcar.setCarBrand("mazda");
		testcar.setCarMile(140000);
		
		testcar2.setCar_id(1);
		testcar2.setCar_color("yellow");
		testcar2.setCar_model("lantis");
		testcar2.setCar_owners(33);
		testcar2.setCar_price(7000);
		testcar2.setCarBrand("mazda");
		testcar2.setCarMile(280000);
		
		this.add_car(testcar);
		this.add_car(testcar2);
		
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
