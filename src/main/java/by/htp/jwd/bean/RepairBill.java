package by.htp.jwd.bean;

import java.io.Serializable;

public class RepairBill implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String dateRepair;
	private String content;
	private String costRepair; 
	private String statusRepairBill; 
	private Oder oder;
	
	public RepairBill() {
		super();
	}

	public RepairBill(String dateRepair, String content, String costRepair, String statusRepairBill, Oder oder) {
		super();
		this.dateRepair = dateRepair;
		this.content = content;
		this.costRepair = costRepair;
		this.statusRepairBill = statusRepairBill;
		this.oder = oder;
	}

	public RepairBill(int id, String dateRepair, String content, String costRepair, String statusRepairBill,
			Oder oder) {
		super();
		this.id = id;
		this.dateRepair = dateRepair;
		this.content = content;
		this.costRepair = costRepair;
		this.statusRepairBill = statusRepairBill;
		this.oder = oder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateRepair() {
		return dateRepair;
	}

	public void setDateRepair(String dateRepair) {
		this.dateRepair = dateRepair;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCostRepair() {
		return costRepair;
	}

	public void setCostRepair(String costRepair) {
		this.costRepair = costRepair;
	}

	public String getStatusRepairBill() {
		return statusRepairBill;
	}

	public void setStatusRepairBill(String statusRepairBill) {
		this.statusRepairBill = statusRepairBill;
	}

	public Oder getOder() {
		return oder;
	}

	public void setOder(Oder oder) {
		this.oder = oder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((costRepair == null) ? 0 : costRepair.hashCode());
		result = prime * result + ((dateRepair == null) ? 0 : dateRepair.hashCode());
		result = prime * result + id;
		result = prime * result + ((oder == null) ? 0 : oder.hashCode());
		result = prime * result + ((statusRepairBill == null) ? 0 : statusRepairBill.hashCode());
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
		RepairBill other = (RepairBill) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (costRepair == null) {
			if (other.costRepair != null)
				return false;
		} else if (!costRepair.equals(other.costRepair))
			return false;
		if (dateRepair == null) {
			if (other.dateRepair != null)
				return false;
		} else if (!dateRepair.equals(other.dateRepair))
			return false;
		if (id != other.id)
			return false;
		if (oder == null) {
			if (other.oder != null)
				return false;
		} else if (!oder.equals(other.oder))
			return false;
		if (statusRepairBill == null) {
			if (other.statusRepairBill != null)
				return false;
		} else if (!statusRepairBill.equals(other.statusRepairBill))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RepairBill [id=" + id + ", dateRepair=" + dateRepair + ", content=" + content + ", costRepair="
				+ costRepair + ", statusRepairBill=" + statusRepairBill + ", oder=" + oder + "]";
	}
	
	
	

}
