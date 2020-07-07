package Database;
import java.io.Serializable;


public class Car implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int car_id;
	private String car_brand;
	private String car_model;
	private int car_mile;
	private String car_color;
	private int car_owners;
	private int car_price;
	private int car_sold;
	
	public Car(int id) {
		this.car_id = id;
	}
	
	public Car(String car_brand, int car_mileage) { // setting values inside the c'tor
		
		this.car_brand = car_brand;
		this.car_mile = car_mileage;
	}
	

	public String getCarBrand() { // get brand
		return car_brand;
	}
	
	public void setCarBrand(String car_brand) { // set brand
		this.car_brand = car_brand;
	}
	
	public int getCarMile() { // get mile
		return car_mile;
	}

	public void setCarMile(int car_mile) { // set mile
		this.car_mile = car_mile;
	}
	
	
	public int getCar_id() { // get id
		return car_id;
	}

	public void setCar_id(int car_id) { // set id
		this.car_id = car_id;
	}

	
	public String getCar_model() { // get model
		return car_model;
	}

	public void setCar_model(String car_model) { // set model
		this.car_model = car_model;
	}

	public String getCar_color() { // get color
		return car_color;
	}

	public void setCar_color(String car_color) { // set color
		this.car_color = car_color;
	}

	public int getCar_owners() { // get owners
		return car_owners;
	}

	public void setCar_owners(int car_owners) { // set owners
		this.car_owners = car_owners;
	}

	public int getCar_price() { // get price
		return car_price;
	}

	public void setCar_price(int car_price) { // set price
		this.car_price = car_price;
	}

	public int getCar_sold() { // get status [SOLD/NOT SOLD]
		return car_sold;
	}

	public void setCar_sold(int car_sold) { // set status [SOLD/NOT SOLD]
		this.car_sold = car_sold;
	}
	
	@Override
	public String toString() {
		return "Car [ID=" + car_id + ", BRAND=" + car_brand + ", MODEL=" + car_model + ", MILEAGE="
				+ car_mile + ", COLOR=" + car_color + ", OWNERS=" + car_owners + ", PRICE=" + car_price
				+ ", STATUS=" + car_sold + "]";
	}

// auto generated hash and equals for car unique ID feature
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + car_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (car_id != other.car_id)
			return false;
		return true;
	}

	
}
