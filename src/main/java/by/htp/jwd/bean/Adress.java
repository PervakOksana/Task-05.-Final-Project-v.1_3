package by.htp.jwd.bean;

import java.io.Serializable;

public class Adress implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String city;
	private String country;
	private String street;
	private String house;
	private String adressAll;
	private String status;
	
	
	public Adress() {
		super();
	}

	public Adress(int id) {
		super();
		this.id = id;
		
	}
	
	public Adress(int id, String adressAll) {
		super();
		this.id = id;
		this.adressAll = adressAll;
	}


	public Adress(String city, String country, String street, String house) {
		super();
		this.city = city;
		this.country = country;
		this.street = street;
		this.house = house;
		
	}
	public Adress(String city, String country, String street, String house,  String status) {
		super();
		this.city = city;
		this.country = country;
		this.street = street;
		this.house = house;
		this.status = status;
	}

	public Adress(String city, String country, String street, String house, String adressAll, String status) {
		super();
		this.city = city;
		this.country = country;
		this.street = street;
		this.house = house;
		this.adressAll = adressAll;
		this.status = status;
	}


	public Adress(int id, String city, String country, String street, String house) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
		this.street = street;
		this.house = house;
		
	}
	
	public Adress(int id, String city, String country, String street, String house, String adressAll, String status) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
		this.street = street;
		this.house = house;
		this.adressAll = adressAll;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getHouse() {
		return house;
	}


	public void setHouse(String house) {
		this.house = house;
	}


	public String getAdressAll() {
		return adressAll;
	}


	public void setAdressAll(String adressAll) {
		this.adressAll = adressAll;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressAll == null) ? 0 : adressAll.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Adress other = (Adress) obj;
		if (adressAll == null) {
			if (other.adressAll != null)
				return false;
		} else if (!adressAll.equals(other.adressAll))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (house == null) {
			if (other.house != null)
				return false;
		} else if (!house.equals(other.house))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Adress [id=" + id + ", city=" + city + ", country=" + country + ", street=" + street + ", house="
				+ house + ", adressAll=" + adressAll + ", status=" + status + "]";
	}
	
	

}
