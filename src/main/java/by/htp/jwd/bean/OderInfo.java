package by.htp.jwd.bean;

import java.io.Serializable;

public class OderInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String adressStart;
	String adressEnd;
	String adressStartId;
	String adressEndId;
	String dateStart;
	String dateEnd;
	double saleOder;
	double costOder;
	
	
	public OderInfo() {
		super();
		
		
	}


	public OderInfo(String adressStart, String adressEnd, String adressStartId, String adressEndId, String dateStart,
			String dateEnd) {
		super();
		this.adressStart = adressStart;
		this.adressEnd = adressEnd;
		this.adressStartId = adressStartId;
		this.adressEndId = adressEndId;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		
	}
	
	public OderInfo(String adressStart, String adressEnd, String adressStartId, String adressEndId, String dateStart,
			String dateEnd, double saleOder, double costOder) {
		super();
		this.adressStart = adressStart;
		this.adressEnd = adressEnd;
		this.adressStartId = adressStartId;
		this.adressEndId = adressEndId;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.saleOder=saleOder;
		this.costOder=costOder;
		
	}


	public String getAdressStartId() {
		return adressStartId;
	}


	public void setAdressStartId(String adressStartId) {
		this.adressStartId = adressStartId;
	}


	public String getAdressEndId() {
		return adressEndId;
	}


	public void setAdressEndId(String adressEndId) {
		this.adressEndId = adressEndId;
	}


	public String getAdressStart() {
		return adressStart;
	}

	public void setAdressStart(String adressStart) {
		this.adressStart = adressStart;
	}

	public String getAdressEnd() {
		return adressEnd;
	}

	public void setAdressEnd(String adressEnd) {
		this.adressEnd = adressEnd;
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


	public double getSaleOder() {
		return saleOder;
	}


	public void setSaleOder(double saleOder) {
		this.saleOder = saleOder;
	}


	public double getCostOder() {
		return costOder;
	}


	public void setCostOder(double costOder) {
		this.costOder = costOder;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressEnd == null) ? 0 : adressEnd.hashCode());
		result = prime * result + ((adressEndId == null) ? 0 : adressEndId.hashCode());
		result = prime * result + ((adressStart == null) ? 0 : adressStart.hashCode());
		result = prime * result + ((adressStartId == null) ? 0 : adressStartId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(costOder);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		temp = Double.doubleToLongBits(saleOder);
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
		OderInfo other = (OderInfo) obj;
		if (adressEnd == null) {
			if (other.adressEnd != null)
				return false;
		} else if (!adressEnd.equals(other.adressEnd))
			return false;
		if (adressEndId == null) {
			if (other.adressEndId != null)
				return false;
		} else if (!adressEndId.equals(other.adressEndId))
			return false;
		if (adressStart == null) {
			if (other.adressStart != null)
				return false;
		} else if (!adressStart.equals(other.adressStart))
			return false;
		if (adressStartId == null) {
			if (other.adressStartId != null)
				return false;
		} else if (!adressStartId.equals(other.adressStartId))
			return false;
		if (Double.doubleToLongBits(costOder) != Double.doubleToLongBits(other.costOder))
			return false;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		if (Double.doubleToLongBits(saleOder) != Double.doubleToLongBits(other.saleOder))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OderInfo [adressStart=" + adressStart + ", adressEnd=" + adressEnd + ", adressStartId=" + adressStartId
				+ ", adressEndId=" + adressEndId + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", saleOder="
				+ saleOder + ", costOder=" + costOder + "]";
	}


	
}
