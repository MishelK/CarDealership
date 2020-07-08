package Database;

import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable{

	private int sale_id;
	private String seller_username;
	private String sale_price;
	private LocalDate sale_date;
	private int car_id;
	private String car_brand;
	private String car_model;
	private String car_year;
	
	public Sale(int sale_id, String seller_username, String sale_price, LocalDate sale_date, Car car) {
		super();
		this.sale_id = sale_id;
		this.seller_username = seller_username;
		this.sale_price = sale_price;
		this.sale_date = sale_date;
		this.car_id = car.getCar_id();
		this.car_brand = car.getCarBrand();
		this.car_model = car.getCar_model();
		// add this.car_year = car.getCar_year();
		
		
	}

	public Sale(int sale_id) {
		super();
		this.sale_id = sale_id;
	}


	public int getSale_id() {
		return sale_id;
	}

	public String getSeller_username() {
		return seller_username;
	}

	public String getSale_price() {
		return sale_price;
	}

	public LocalDate getSale_date() {
		return sale_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sale_date == null) ? 0 : sale_date.hashCode());
		result = prime * result + sale_id;
		result = prime * result + ((sale_price == null) ? 0 : sale_price.hashCode());
		result = prime * result + ((seller_username == null) ? 0 : seller_username.hashCode());
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
		Sale other = (Sale) obj;
		if (sale_id != other.sale_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sale [ Sale id = " + sale_id + ", seller = " + seller_username + ", Sale price = " + sale_price
				+ ", Sale date = " + sale_date + " ] Car [ ID = " + car_id + ", Brand = "+ car_brand + ", Model = "+ car_model + ", Year = " + car_year + " ]";
	}
	
	
	
	
	
}
