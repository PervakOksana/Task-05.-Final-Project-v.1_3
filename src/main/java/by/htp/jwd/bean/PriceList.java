package by.htp.jwd.bean;

import java.io.Serializable;

public class PriceList implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private double costHour;
	private double costDayr;
	private double sale;
	private Car car;

	public PriceList() {
		super();
	}

	public PriceList(double cost_hour, double cost_dayr, double sale) {
		super();
		this.costHour = cost_hour;
		this.costDayr = cost_dayr;
		this.sale = sale;

	}

	public PriceList(double cost_hour, double cost_dayr, double sale, Car car) {
		super();
		this.costHour = cost_hour;
		this.costDayr = cost_dayr;
		this.sale = sale;
		this.car = car;
	}

	public PriceList(int id, double cost_hour, double cost_dayr, double sale, Car car) {
		super();
		this.id = id;
		this.costHour = cost_hour;
		this.costDayr = cost_dayr;
		this.sale = sale;
		this.car = car;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCostHour() {
		return costHour;
	}

	public void setCostHour(double costHour) {
		this.costHour = costHour;
	}

	public double getCostDayr() {
		return costDayr;
	}

	public void setCostDayr(double costDayr) {
		this.costDayr = costDayr;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		long temp;
		temp = Double.doubleToLongBits(costDayr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(costHour);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(sale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		PriceList other = (PriceList) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (Double.doubleToLongBits(costDayr) != Double.doubleToLongBits(other.costDayr))
			return false;
		if (Double.doubleToLongBits(costHour) != Double.doubleToLongBits(other.costHour))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(sale) != Double.doubleToLongBits(other.sale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PriceList [id=" + id + ", costHour=" + costHour + ", costDayr=" + costDayr + ", sale=" + sale + ", car="
				+ car + "]";
	}

	

	
}
