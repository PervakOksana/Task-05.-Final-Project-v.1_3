package by.htp.jwd.dao;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.User;

public interface AdressDAO {
	
	final String ADRESS_ID = "adress.Id";
	final String CITY = "city";
	final String COUNTRY = "country";
	final String STREET = "street";
	final String HOUSE = "house";
	final String ADRESS_ALL = "adress_all";
	final String ADRESS_STATUS = "adress.status";
	
	final String ADRESS_ADD ="INSERT INTO adress ( city, country, street, house, adress_all, status) VALUES (?, ?, ?,?,?,?)";

	void addAdress(Adress adress) throws DAOException;
}
