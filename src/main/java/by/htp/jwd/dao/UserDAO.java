package by.htp.jwd.dao;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;

public interface UserDAO {

	final String ID_USER = "user.Id";
	final String LOGIN = "logine";
	final String PASSWORD = "passworde";
	final String NAME = "name";
	final String SURNAME = "surname";
	final String ROLE = "role";
	final String PHONE = "phone";
	final String EMAIL = "email";
	final String PASSWORD_NUMBER = "number_passport";
	final String DRIVING_LICENSE_NUMBER = "number_driving_license";
	final String DRIVING_LICENSE_DATE = "date_driving_license";
	final String PASSWORD_DATE = "date_passport";
	final String ID_USER_DETAIL = "details_user.Id";
	
	final String ADRESS_ID = "adress.Id";
	final String CITY = "city";
	final String COUNTRY = "country";
	final String STREET = "street";
	final String HOUSE = "house";
	final String ADRESS_ALL = "adress_all";
	final String ADRESS_STATUS = "adress.status";

	final String USER_ADD ="INSERT INTO user (logine, passworde,name ,surname, role,phone, email, adressId ) VALUES (?,?,?,?,?,?,?,?)";
	final String USER_ADD_ADRESS ="INSERT INTO adress (city, country,street ,house, status,adress_all) VALUES (?,?,?,?,?,?)";
	final String SELECT_USER ="SELECT * FROM user WHERE logine =? AND passworde =?";
	final String SELECT_USER_EMAIL ="SELECT * FROM user WHERE email =?";
	final String SELECT_USER_LOGIN ="SELECT * FROM user WHERE logine =?";
	final String SELECT_USER_ID ="SELECT * FROM user LEFT JOIN details_user ON details_user.userId = user.Id LEFT JOIN adress ON adress.Id = user.adressId  WHERE user.Id =?";
	final String USER_ADD_DETAIL ="INSERT INTO details_user (number_passport, number_driving_license ,date_driving_license ,date_passport, userId) VALUES (?,?,?,?,?)";
	final String SELECT_MAX_ID_FROM_USER = "SELECT MAX(Id) FROM user";
	final String MAX_ID =  "MAX(Id)";
	final String SELECT_MAX_ID_FROM_ADRESS = "SELECT MAX(Id) FROM adress";
	final String USER_UPDATE_ADRESS ="UPDATE adress SET city=?, country=?,street=? ,house=?, status=? WHERE adress.Id=?";
	final String USER_UPDATE ="UPDATE user SET logine=?, passworde=?,name=? ,surname=?, role=?,phone=?, email=?, adressId=? WHERE user.Id=?";
	final String USER_UPDATE_DETAIL ="UPDATE details_user SET number_passport=?, number_driving_license=? ,date_driving_license=? ,date_passport=?, userId=? WHERE details_user.Id=?";
 	
	boolean registration(User user) throws DAOException;
	
	boolean registrationAll( UserDetail userDetail) throws DAOException;
	
	boolean updateUser(UserDetail userDetail) throws DAOException;
	
	boolean registrationAddDetail (UserDetail userDetail) throws DAOException;

	boolean registrationAddAdress (Adress adress) throws DAOException;
	
	User authorization(String login, String password) throws DAOException;
	
	User findPassword (String email) throws DAOException;
	
	UserDetail findUserViaId (String id) throws DAOException;
	
	User findLogin (String login) throws DAOException;
}
