package by.htp.jwd.dao;

import java.util.List;

import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;

public interface CarDAO {

	final String BRAND = "brand";
	final String DISCRIBE_OF_BRAND = "describe_of_brand";
	final String BODYWORK = "bodywork";
	final String POWER = "power";
	final String TRANSMISSION = "transmission";
	final String NUMBER_OF_DOORS = "number_of_doors";
	final String YEAR = "year";
	final String PHOTO_PATH = "photoPath";
	final String PHOTO = "photo";
	final String CAR_STATUS = "status";
	final String COST_HOUR = "cost_hour";
	final String COST_DAY = "cost_dayr";
	final String SALE = "sale";
	final String CAR_ID = "car.Id";
	final String PRICE_LIST_ID = "price_list.Id";
	final String ADRESS_ID = "adress.Id";
	final String ADRESS_CITY = "adress.city";
	final String ADRESS_COUNTRY = "adress.country";
	final String ADRESS_STREET = "adress.street";
	final String ADRESS_HOUSE = "adress.house";
	final String ADRESS_ALL = "adress.adress_all";
	final String PHOTO_IPG= "data:image/jpeg;base64,";

	final String CAR_ADD = "INSERT INTO car (brand, describe_of_brand, bodywork, power, transmission, number_of_doors, year, photo, photoPath, status ,adressId ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?)";
	final String SELECT_CARS_ALL = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN adress ON car.adressId = adress.Id WHERE car.status=1";
	final String SELECT_CARS_ALL_FIRST_PAGE = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN adress ON car.adressId = adress.Id WHERE car.status=1 LIMIT 0,15";
	final String SELECT_CARS_ALL_NEXT_PAGE = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN adress ON car.adressId = adress.Id WHERE car.status=1 LIMIT ?,?";
	final String SELECT_MAX_ID_FROM_CAR = "SELECT MAX(Id) FROM car";
	final String PRICE_ADD = "INSERT INTO price_list (cost_hour, cost_dayr, sale, carId  ) values (?, ?, ?, ?)";
	final String MAX_ID = "MAX(Id)";
	final String SELECT_COUNT_ID_FROM_CAR = "SELECT COUNT(Id) FROM car WHERE car.status=1";
	final String COUNT_ID = "COUNT(Id)";
	final String INFO_CAR = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN adress ON car.adressId = adress.Id WHERE car.Id =?";
	final String FIND_CARS = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN adress ON car.adressId = adress.Id WHERE car.Id NOT IN (SELECT carId FROM oder WHERE oder.date_of_start >= ?) AND car.adressId=? AND car.status=1";
	final String CAR_UPDATE = "UPDATE car SET brand=?, describe_of_brand=?, bodywork=?, power=?, car.transmission=?, number_of_doors=?, year=?, photo=?, photoPath=?  WHERE car.Id=?";
	final String CAR_DELETE = "UPDATE car SET status=0 WHERE car.Id=?";
	final String PRICE_UPDATE = "UPDATE price_list SET cost_hour=?, cost_dayr=?, sale=?, carId =? WHERE price_list.Id=?";

	public boolean addCar(Car car) throws DAOException;

	public boolean addPriceToCar(PriceList priceList) throws DAOException;

	public List<PriceList> allCar(int numberPage) throws DAOException;

	public List<PriceList> allCar() throws DAOException;

	public PriceList infoAboutCar(String id) throws DAOException;

	public List<PriceList> lookingCar(String adressStart_s, String dateStart_s, String dateEnd_s) throws DAOException;

	public Integer getNumberOfRows() throws DAOException;

	public boolean updateCar(PriceList priceList) throws DAOException;
	
	public boolean deleteCar(int idCar) throws DAOException;
}
