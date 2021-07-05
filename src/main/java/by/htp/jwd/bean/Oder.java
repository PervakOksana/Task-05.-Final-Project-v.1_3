package by.htp.jwd.bean;

import java.io.Serializable;

public class Oder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// statusOder =0 didn't pay
	// statusOder =1 paied
	// statusOder =2 canceled
	// statusOder =3 canceled money return
	// statusOder =4 canceled by admin
	// statusOder =5 completed
	
	
	private int id; 
	private String dateStart; 
	private String dateEnd;
	private double costOder;
	private String dateOder; 
	private int statusOder;
	private Car car;
	private User user; 
	private Adress adressStart; 
	private Adress adressEnd;
	
	public Oder() {
		super();
	}
	
	public Oder(int id) {
		super();
		this.id = id;
	}

	public Oder(String dateStart, String dateEnd, double costOder, int statusOder,
			Car car, User user, Adress adressStart, Adress adressEnd) {
		super();
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;		
		this.costOder = costOder;
		this.statusOder = statusOder;
		this.car = car;
		this.user = user;
		this.adressStart = adressStart;
		this.adressEnd = adressEnd;
	}
	
	public Oder(int id, String dateStart, String dateEnd,  double costOder,
			int statusOder, Car car, User user) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.costOder = costOder;
		this.statusOder = statusOder;
		this.car = car;
		this.user = user;
		
	}

	public Oder(int id, String dateStart, String dateEnd, double costOder,
			int statusOder, Car car, User user, Adress adressStart, Adress adressEnd) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.costOder = costOder;
		this.statusOder = statusOder;
		this.car = car;
		this.user = user;
		this.adressStart = adressStart;
		this.adressEnd = adressEnd;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public double getCostOder() {
		return costOder;
	}

	public void setCostOder(double costOder) {
		this.costOder = costOder;
	}

	public String getDateOder() {
		return dateOder;
	}

	public void setDateOder(String dateOder) {
		this.dateOder = dateOder;
	}

	public int getStatusOder() {
		return statusOder;
	}

	public void setStatusOder(int statusOder) {
		this.statusOder = statusOder;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Adress getAdressStart() {
		return adressStart;
	}

	public void setAdressStart(Adress adressStart) {
		this.adressStart = adressStart;
	}

	public Adress getAdressEnd() {
		return adressEnd;
	}

	public void setAdressEnd(Adress adressEnd) {
		this.adressEnd = adressEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressEnd == null) ? 0 : adressEnd.hashCode());
		result = prime * result + ((adressStart == null) ? 0 : adressStart.hashCode());
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		long temp;
		temp = Double.doubleToLongBits(costOder);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateOder == null) ? 0 : dateOder.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + id;
		result = prime * result + statusOder;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Oder other = (Oder) obj;
		if (adressEnd == null) {
			if (other.adressEnd != null)
				return false;
		} else if (!adressEnd.equals(other.adressEnd))
			return false;
		if (adressStart == null) {
			if (other.adressStart != null)
				return false;
		} else if (!adressStart.equals(other.adressStart))
			return false;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (Double.doubleToLongBits(costOder) != Double.doubleToLongBits(other.costOder))
			return false;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateOder == null) {
			if (other.dateOder != null)
				return false;
		} else if (!dateOder.equals(other.dateOder))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		if (id != other.id)
			return false;
		if (statusOder != other.statusOder)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Oder [id=" + id + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", costOder=" + costOder
				+ ", dateOder=" + dateOder + ", statusOder=" + statusOder + ", car=" + car + ", user=" + user
				+ ", adressStart=" + adressStart + ", adressEnd=" + adressEnd + "]";
	}


	
}
