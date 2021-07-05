package by.htp.jwd.bean;

import java.io.Serializable;


public class UserDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String passwordNumber;
	private String drivingLicanseNumber;
	private String drivingLicanseDate;
	private String passwordDate;
	private User user;
	
	
	public UserDetail() {
		super();
	}


	public UserDetail(String passwordNumber, String drivingLicanseNumber, String drivingLicanseDate,
			String passwordDate, User user) {
		super();
		this.passwordNumber = passwordNumber;
		this.drivingLicanseNumber = drivingLicanseNumber;
		this.drivingLicanseDate = drivingLicanseDate;
		this.passwordDate = passwordDate;
		this.user = user;
	}

	public UserDetail(String passwordNumber, String drivingLicanseNumber, String drivingLicanseDate,
			String passwordDate) {
		super();
		this.passwordNumber = passwordNumber;
		this.drivingLicanseNumber = drivingLicanseNumber;
		this.drivingLicanseDate = drivingLicanseDate;
		this.passwordDate = passwordDate;
		
	}

	public UserDetail(int id, String passwordNumber, String drivingLicanseNumber, String drivingLicanseDate,
			String passwordDate, User user) {
		super();
		this.id = id;
		this.passwordNumber = passwordNumber;
		this.drivingLicanseNumber = drivingLicanseNumber;
		this.drivingLicanseDate = drivingLicanseDate;
		this.passwordDate = passwordDate;
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPasswordNumber() {
		return passwordNumber;
	}


	public void setPasswordNumber(String passwordNumber) {
		this.passwordNumber = passwordNumber;
	}


	public String getDrivingLicanseNumber() {
		return drivingLicanseNumber;
	}


	public void setDrivingLicanseNumber(String drivingLicanseNumber) {
		this.drivingLicanseNumber = drivingLicanseNumber;
	}


	public String getDrivingLicanseDate() {
		return drivingLicanseDate;
	}


	public void setDrivingLicanseDate(String drivingLicanseDate) {
		this.drivingLicanseDate = drivingLicanseDate;
	}


	public String getPasswordDate() {
		return passwordDate;
	}


	public void setPasswordDate(String passwordDate) {
		this.passwordDate = passwordDate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drivingLicanseDate == null) ? 0 : drivingLicanseDate.hashCode());
		result = prime * result + ((drivingLicanseNumber == null) ? 0 : drivingLicanseNumber.hashCode());
		result = prime * result + id;
		result = prime * result + ((passwordDate == null) ? 0 : passwordDate.hashCode());
		result = prime * result + ((passwordNumber == null) ? 0 : passwordNumber.hashCode());
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
		UserDetail other = (UserDetail) obj;
		if (drivingLicanseDate == null) {
			if (other.drivingLicanseDate != null)
				return false;
		} else if (!drivingLicanseDate.equals(other.drivingLicanseDate))
			return false;
		if (drivingLicanseNumber == null) {
			if (other.drivingLicanseNumber != null)
				return false;
		} else if (!drivingLicanseNumber.equals(other.drivingLicanseNumber))
			return false;
		if (id != other.id)
			return false;
		if (passwordDate == null) {
			if (other.passwordDate != null)
				return false;
		} else if (!passwordDate.equals(other.passwordDate))
			return false;
		if (passwordNumber == null) {
			if (other.passwordNumber != null)
				return false;
		} else if (!passwordNumber.equals(other.passwordNumber))
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
		return "UserDetail [id=" + id + ", passwordNumber=" + passwordNumber + ", drivingLicanseNumber="
				+ drivingLicanseNumber + ", drivingLicanseDate=" + drivingLicanseDate + ", passwordDate=" + passwordDate
				+ ", user=" + user + "]";
	}
	
	
}
