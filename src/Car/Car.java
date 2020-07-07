package Car;
import java.io.Serializable;


public class Car implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	final private String car_brand; // final == can not change
	final private String car_mile;
	
	
	public Car(String car_brand, String car_mileage) { // setting values inside the c'tor
		this.car_brand = car_brand;
		this.car_mile = car_mileage;
	}
	
	// 'get' methods
	public String getCarBrand() {
		return car_brand;
	}
	
	public String getCarMile() {
		return car_mile;
	}
	
	// hash
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((car_brand == null) ? 0 : car_brand.hashCode())
				+ ((car_brand == car_mile) ? 0 : car_mile.hashCode());
		return result;
	}
	
	// equals
	@Override
	public boolean equals(Object obj) {
		
		if ( this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!Car.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Car other = (Car) obj;
		if ((this.car_brand == null) ? (other.car_brand != null) : !this.car_brand.equals(other.car_brand)) {
			return false;
		}
		if ((this.car_mile == null) ? (other.car_mile !=null) : !this.car_mile.equals(other.car_mile)) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "<Car brand: " + car_brand + ", Car mileage: " + car_mile + ">";
	}
	
}