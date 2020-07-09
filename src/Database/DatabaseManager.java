package Database;

public class DatabaseManager {
	
	public void loadDatabase() {
		
		UserDataManager.instance().readDataFromFile();
		UserDataManager.instance().seedDatabase();

		
		CarDataManager.instance().readDataFromFile();
		CarDataManager.instance().readCarIdDataFromFile();
		CarDataManager.instance().seedDatabase();

		
		SaleDataManager.instance().readDataFromFile();
		SaleDataManager.instance().readSaleIdDataFromFile();
		
	}

}
