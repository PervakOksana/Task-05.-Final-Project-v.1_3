package by.htp.jwd.dao;

import java.util.List;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.OderInfo;

public interface OderDAO {

	final String ADRESS_ID = "Id";
	final String CITY = "city";
	final String COUNTRY = "country";
	final String STREET = "street";
	final String HOUSE = "house";
	final String STATUS = "status";
	final String ADRESS_ALL = "adress_all";
	final String ODER_ID = "Id";
	final String ODER_DATE_START = "date_of_start";
	final String ODER_DATE_END = "date_of_end";
	final String ODER_TIME_START = "time_of_start";
	final String ODER_TIME_END = "time_of_end";
	final String ODER_COST = "cost_oder";
	final String ODER_STATUS = "status_oder";
	final String BRAND = "brand";
	final String DISCRIBE_OF_BRAND = "describe_of_brand";
	final String BODYWORK = "bodywork";
	final String POWER = "power";
	final String TRANSMISSION = "transmission";
	final String NUMBER_OF_DOORS = "number_of_doors";
	final String YEAR = "year";
	final String PHOTO_PATH = "photoPath";
	final String PHOTO = "photo";
	final String CAR_ID = "car.Id";
	final String CAR_ADRESS = "car.adressId";
	final String ORDER_ADRESS = "oder.adressIdend";
	final String USER_ID = "userId";
	final String USER_NAME = "name";
	final String USER_SURNAME = "surname";
	final String USER_PHONE = "phone";
	final String EMAIL = "email";
	final String ADRESS_ID_START = "adressId";
	final String ADRESS_ID_END = "adressIdend";
	final String ADRESS_START = "adressStart.adress_all";
	final String ADRESS_END = "adressEnd.adress_all";
	final String PATTERN_DATE = "yyyy-MM-dd HH:mm:ss";

	final String SELECT_ADRESS_CAR = "SELECT * FROM adress WHERE status = 1";
	final String SELECT_ADRESS_CAR_ID = "SELECT * FROM adress WHERE Id = ?";
	final String ODER_ADD = "INSERT INTO oder (date_of_start, date_of_end, cost_oder, status_oder, carId , userId, adressId, adressIdend  ) values (?, ?, ?, ?, ?, ?, ?, ?)";
	final String UPDATE_CARS_ADRESS = "UPDATE car SET adressId=? WHERE Id =?";
	final String SELECT_ODER_ALL_FIRST_PAGE = "SELECT * FROM oder LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id LEFT JOIN adress as adressEnd ON oder.adressIdend = adressEnd.Id LEFT JOIN adress as adressStart ON oder.adressId = adressStart.Id order by oder.date_of_start desc LIMIT 0,15";
	final String SELECT_ODER_ALL_NEXT_PAGE = "SELECT * FROM oder LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id LEFT JOIN adress as adressEnd ON oder.adressIdend = adressEnd.Id LEFT JOIN adress as adressStart ON oder.adressId = adressStart.Id order by oder.date_of_start desc  LIMIT ?,?";
	final String SELECT_COUNT_ID_FROM_ODER = "SELECT COUNT(Id) FROM oder";
	final String COUNT_ID = "COUNT(Id)";
	final String SELECT_ODER_BY_ID = "SELECT * FROM oder LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id LEFT JOIN adress as adressEnd ON oder.adressIdend = adressEnd.Id LEFT JOIN adress as adressStart ON oder.adressId = adressStart.Id WHERE oder.Id=?";
	final String SELECT_ODER_ALL_BY_USER_FIRST_PAGE = "SELECT * FROM oder LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id LEFT JOIN adress as adressEnd ON oder.adressIdend = adressEnd.Id LEFT JOIN adress as adressStart ON oder.adressId = adressStart.Id WHERE user.Id= ?  order by oder.date_of_start desc LIMIT 0,15";
	final String SELECT_ODER_ALL_BY_USER_NEXT_PAGE = "SELECT * FROM oder LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id LEFT JOIN adress as adressEnd ON oder.adressIdend = adressEnd.Id LEFT JOIN adress as adressStart ON oder.adressId = adressStart.Id WHERE user.Id= ? order by oder.date_of_start desc  LIMIT ?,?";
	final String UPDATE_ODER_STATUS = "UPDATE oder SET status_oder=? WHERE Id =?";
	final String SELECT_ODER_NEW_BY_USER = "SELECT * FROM oder LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id LEFT JOIN adress as adressEnd ON oder.adressIdend = adressEnd.Id LEFT JOIN adress as adressStart ON oder.adressId = adressStart.Id WHERE user.Id= ? AND status_oder =1 AND oder.date_of_start > ? order by oder.date_of_start desc";
	final String SELECT_ODER_NEW = "SELECT * FROM oder LEFT JOIN car ON oder.carId = car.Id LEFT JOIN user ON oder.userId = user.Id LEFT JOIN adress as adressEnd ON oder.adressIdend = adressEnd.Id LEFT JOIN adress as adressStart ON oder.adressId = adressStart.Id WHERE oder.date_of_start > ? order by oder.date_of_start desc";
	final String SELECT_ID_ADRESS = "SELECT oder.adressIdend FROM car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN oder ON oder.carId = car.Id WHERE oder.Id = ?";
	final String UPDATE_ODER_COMPLETED ="UPDATE car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN oder ON oder.carId = car.Id SET status_oder=5, car.adressId = ?	WHERE oder.Id =?";
	public List<Adress> adressCar() throws DAOException;

	public Adress adressCarId(String adress) throws DAOException;

	public void addOder(OderInfo oderInfo, String carId, String userId ) throws DAOException;
	
	public List<Oder> allOder(int numberPage) throws DAOException;
	
	public List<Oder> allOderByUser(int numberPage, int userId) throws DAOException;
	
	public List<Oder> allOderNewByUser( int userId) throws DAOException;
	
	public List<Oder> allOderNew( ) throws DAOException;
	
	public Integer getNumberRows() throws DAOException;
	
	public Oder findOderById(int id) throws DAOException;
	
	public void cancelOder(int oderId, String status) throws DAOException;
	
	public void oderСompleted(int oderId) throws DAOException;

}
