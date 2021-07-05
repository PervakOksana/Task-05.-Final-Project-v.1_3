package by.htp.jwd.dao.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.dao.CarDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class CarDAOimpl implements CarDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(CarDAOimpl.class);

	@Override
	public boolean addCar(Car car) throws DAOException {

		LOGGER.info("CarDaoImpl started addCar.");

		boolean result = false;
		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(CAR_ADD);

			preparedStatement.setString(1, car.getBrand());
			preparedStatement.setString(2, car.getDescribeBrand());
			preparedStatement.setString(3, car.getBodywork());
			preparedStatement.setString(4, car.getPower());
			preparedStatement.setString(5, car.getTransmission());
			preparedStatement.setString(6, car.getNumberDoors());
			preparedStatement.setString(7, car.getYear());
			preparedStatement.setString(9, car.getPhotoPath());
			InputStream instream = null;
			instream = Files.newInputStream(Paths.get(car.getPhotoPath()));
			preparedStatement.setBinaryStream(8, instream);
			preparedStatement.setInt(10, car.getAdressCar().getId());
			result = true;
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - addCar." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - addCar");
			throw new DAOException(e);
		} catch (IOException e) {
			LOGGER.error("CarDao: IOException - addCar");
			throw new DAOException(e);
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - addCar.");
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public boolean updateCar(PriceList priceList) throws DAOException {

		LOGGER.info("CarDaoImpl started updateCar.");

		boolean result = false;
		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(CAR_UPDATE);
			System.out.println("0");
			preparedStatement.setString(1, priceList.getCar().getBrand());
			preparedStatement.setString(2, priceList.getCar().getDescribeBrand());
			preparedStatement.setString(3, priceList.getCar().getBodywork());
			preparedStatement.setString(4, priceList.getCar().getPower());
			preparedStatement.setString(5, priceList.getCar().getTransmission());
			preparedStatement.setString(6, priceList.getCar().getNumberDoors());
			preparedStatement.setString(7, priceList.getCar().getYear());

			InputStream instream = null;
			instream = Files.newInputStream(Paths.get(priceList.getCar().getPhotoPath()));
			preparedStatement.setBinaryStream(8, instream);
			preparedStatement.setString(9, priceList.getCar().getPhotoPath());
			preparedStatement.setInt(10, priceList.getCar().getId());

			preparedStatement.executeUpdate();

			preparedStatement = con.prepareStatement(PRICE_UPDATE);
			preparedStatement.setDouble(1, priceList.getCostHour());
			preparedStatement.setDouble(2, priceList.getCostDayr());
			preparedStatement.setDouble(3, priceList.getSale());
			preparedStatement.setInt(4, priceList.getCar().getId());
			preparedStatement.setInt(5, priceList.getId());

			result = true;
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - updateCar." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - updateCar");
			throw new DAOException(e);
		} catch (IOException e) {
			LOGGER.error("CarDao: IOException - updateCar");
			throw new DAOException(e);
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - addCar.");
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public List<PriceList> allCar(int numberPage) throws DAOException {

		LOGGER.info("CarDaoImpl started allCar.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		List<PriceList> cars = new ArrayList<PriceList>();
		Connection con = null;

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_CARS_ALL_FIRST_PAGE);
			if (numberPage > 1) {
				preparedStatement = con.prepareStatement(SELECT_CARS_ALL_NEXT_PAGE);
				preparedStatement.setInt(1, (numberPage * 5) - 5);
				preparedStatement.setInt(2, numberPage * 5);
			}

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				PriceList priceList = nextCar(rs);
				cars.add(priceList);

			}
			preparedStatement.close();
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - allCar." + e);
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - allCar." + e);
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - allCar.");
				throw new DAOException(e);
			}
		}

		return cars;

	}

	@Override
	public boolean addPriceToCar(PriceList priceList) throws DAOException {

		LOGGER.info("CarDaoImpl started addPriceToCar.");

		boolean result = true;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_MAX_ID_FROM_CAR);
			int carId = 0;
			while (rs.next()) {
				carId = rs.getInt(MAX_ID);
			}

			PreparedStatement preparedStatement = con.prepareStatement(PRICE_ADD);

			preparedStatement.setDouble(1, priceList.getCostHour());
			preparedStatement.setDouble(2, priceList.getCostDayr());
			preparedStatement.setDouble(3, priceList.getSale());
			preparedStatement.setInt(4, carId);

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			result = false;
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public Integer getNumberOfRows() throws DAOException {

		LOGGER.info("CarDaoImpl started getNumberOfRows.");

		Integer numOfRows = 0;
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		Connection con = null;
		try {
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_COUNT_ID_FROM_CAR);

			while (rs.next()) {

				numOfRows = rs.getInt(COUNT_ID);

			}
		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - getNumberOfRows.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - getNumberOfRows.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - getNumberOfRows.");
				throw new DAOException(e);
			}
		}

		return numOfRows;
	}

	@Override

	public List<PriceList> lookingCar(String adressStart, String dateStart, String dateEnd) throws DAOException {

		LOGGER.info("CarDaoImpl started lookingCar.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		ResultSet rs = null;
		List<PriceList> cars = new ArrayList<PriceList>();
		Connection con = null;
		try {
			con = cp.takeConnection();

			cars = new ArrayList<PriceList>();

			PreparedStatement preparedStatement = con.prepareStatement(FIND_CARS);

			preparedStatement.setString(1, dateStart);
			preparedStatement.setString(2, adressStart);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				PriceList priceList = nextCar(rs);
				cars.add(priceList);

			}
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - lookingCar." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - lookingCar.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - lookingCar.");
				throw new DAOException(e);
			}
		}

		return cars;
	}

	@Override
	public List<PriceList> allCar() throws DAOException {

		LOGGER.info("CarDaoImpl started allCar.");

		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		List<PriceList> cars = new ArrayList<PriceList>();
		Connection con = null;
		try {
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_CARS_ALL);
			cars = new ArrayList<PriceList>();

			while (rs.next()) {

				PriceList priceList = nextCar(rs);
				cars.add(priceList);

			}
		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - allCar.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - allCar.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - allCar.");
				throw new DAOException(e);
			}
		}

		return cars;
	}

	@Override
	public PriceList infoAboutCar(String id) throws DAOException {

		LOGGER.info("CarDaoImpl started infoAboutCar.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		ResultSet rs = null;
		PriceList car = new PriceList();
		Connection con = null;
		try {
			con = cp.takeConnection();

			PreparedStatement preparedStatement = con.prepareStatement(INFO_CAR);
			preparedStatement.setString(1, id);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				car = nextCar(rs);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - infoAboutCar.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - infoAboutCar.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - lookingCar.");
				throw new DAOException(e);
			}
		}
		System.out.println("DAO - " + car.toString());
		return car;
	}

	@Override
	public boolean deleteCar(int idCar) throws DAOException {

		LOGGER.info("CarDaoImpl started updateCar.");

		boolean result = false;
		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(CAR_DELETE);

			preparedStatement.setInt(1, idCar);

			result = true;
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - updateCar." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("CarDao: ConnectionPoolException - updateCar");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("CarDao: SQLException - addCar.");
				throw new DAOException(e);
			}
		}

		return result;
	}

	private PriceList nextCar(ResultSet rs) throws DAOException {

		LOGGER.info("CarDaoImpl started nextCar.");

		PriceList priceList = new PriceList();

		try {
			int idCar = rs.getInt(CAR_ID);
			String brand = rs.getString(BRAND);
			String describeBrand = rs.getString(DISCRIBE_OF_BRAND);
			String bodywork = rs.getString(BODYWORK);
			String power = rs.getString(POWER);
			String transmission = rs.getString(TRANSMISSION);
			String numberDoors = rs.getString(NUMBER_OF_DOORS);
			String year = rs.getString(YEAR);
			String photoPath = rs.getString(PHOTO_PATH);
			Blob photo = rs.getBlob(PHOTO);
			String statusCar = rs.getString(CAR_STATUS);
			int idPriceList = rs.getInt(PRICE_LIST_ID);
			double costHour = rs.getDouble(COST_HOUR);
			double costDayr = rs.getDouble(COST_DAY);
			double sale = rs.getDouble(SALE);
			int idAdress = rs.getInt(ADRESS_ID);
			String adressAll = rs.getString(ADRESS_ALL);

			byte[] photoByte = photo.getBytes(1, (int) photo.length());
			String urlPhoto = PHOTO_IPG + Base64.getEncoder().encodeToString(photoByte);

			Adress adress = new Adress(idAdress, adressAll);
			Car car = new Car(idCar, brand, describeBrand, bodywork, power, transmission, numberDoors, year, photoPath,
					statusCar, urlPhoto, adress);
			priceList = new PriceList(idPriceList, costHour, costDayr, sale, car);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("CarDao: SQLException - nextCar." + e);
			throw new DAOException(e);
		}

		return priceList;
	}

}
