package by.htp.jwd.bean;

import java.io.Serializable;

public class ApplicationInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String payTerm;
	private String contact;
	private String adressEmail; 
	private String passwordEmail;
	private String letterReminder;
	private String letterBill;
	
	
	public ApplicationInfo() {
		super();
	}


	public ApplicationInfo(String payTerm, String contact, String adressEmail, String passwordEmail,
			String letterReminder, String letterBill) {
		super();
		this.payTerm = payTerm;
		this.contact = contact;
		this.adressEmail = adressEmail;
		this.passwordEmail = passwordEmail;
		this.letterReminder = letterReminder;
		this.letterBill = letterBill;
	}


	public ApplicationInfo(int id, String payTerm, String contact, String adressEmail, String passwordEmail,
			String letterReminder, String letterBill) {
		super();
		this.id = id;
		this.payTerm = payTerm;
		this.contact = contact;
		this.adressEmail = adressEmail;
		this.passwordEmail = passwordEmail;
		this.letterReminder = letterReminder;
		this.letterBill = letterBill;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressEmail == null) ? 0 : adressEmail.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + id;
		result = prime * result + ((letterBill == null) ? 0 : letterBill.hashCode());
		result = prime * result + ((letterReminder == null) ? 0 : letterReminder.hashCode());
		result = prime * result + ((passwordEmail == null) ? 0 : passwordEmail.hashCode());
		result = prime * result + ((payTerm == null) ? 0 : payTerm.hashCode());
		return result;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPayTerm() {
		return payTerm;
	}


	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getAdressEmail() {
		return adressEmail;
	}


	public void setAdressEmail(String adressEmail) {
		this.adressEmail = adressEmail;
	}


	public String getPasswordEmail() {
		return passwordEmail;
	}


	public void setPasswordEmail(String passwordEmail) {
		this.passwordEmail = passwordEmail;
	}


	public String getLetterReminder() {
		return letterReminder;
	}


	public void setLetterReminder(String letterReminder) {
		this.letterReminder = letterReminder;
	}


	public String getLetterBill() {
		return letterBill;
	}


	public void setLetterBill(String letterBill) {
		this.letterBill = letterBill;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationInfo other = (ApplicationInfo) obj;
		if (adressEmail == null) {
			if (other.adressEmail != null)
				return false;
		} else if (!adressEmail.equals(other.adressEmail))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (id != other.id)
			return false;
		if (letterBill == null) {
			if (other.letterBill != null)
				return false;
		} else if (!letterBill.equals(other.letterBill))
			return false;
		if (letterReminder == null) {
			if (other.letterReminder != null)
				return false;
		} else if (!letterReminder.equals(other.letterReminder))
			return false;
		if (passwordEmail == null) {
			if (other.passwordEmail != null)
				return false;
		} else if (!passwordEmail.equals(other.passwordEmail))
			return false;
		if (payTerm == null) {
			if (other.payTerm != null)
				return false;
		} else if (!payTerm.equals(other.payTerm))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ApplicationInfo [id=" + id + ", payTerm=" + payTerm + ", contact=" + contact + ", adressEmail="
				+ adressEmail + ", passwordEmail=" + passwordEmail + ", letterReminder=" + letterReminder
				+ ", letterBill=" + letterBill + "]";
	}


		
}
