package by.htp.jwd.bean;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String login;
	private String password;
	private String surname;
	private String role;
	private String name;
	private String phone;
	private String email;
	private Adress adress;

	public User() {
		super();
	}

	
	public User(int id, String surname, String name, String phone,String email) {
		super();
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}


	public User(String login, String password, String surname, String role, String name, String phone, String email) {
		super();
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public User(int id,String login, String password, String surname, String role, String name, String phone, String email) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public User(String login, String password, String surname, String role, String name, String phone, String email,
			Adress adress) {
		super();
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
	}
	
	public User(int id, String login, String password, String surname, String name, String phone,
			String email, Adress adress) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
	}

	public User(int id, String login, String password, String surname, String role, String name, String phone,
			String email, Adress adress) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		User other = (User) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", surname=" + surname + ", role="
				+ role + ", name=" + name + ", phone=" + phone + ", email=" + email + ", adress=" + adress + "]";
	}

	
}