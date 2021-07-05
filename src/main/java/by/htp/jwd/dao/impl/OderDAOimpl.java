package by.htp.jwd.dao.impl;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.OderInfo;
import by.htp.jwd.bean.User;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.OderDAO;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class OderDAOimpl implements OderDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(OderDAOimpl.class);

	@Override
	public List<Adress> adressCar() throws DAOException {

		LOGGER.info("OderDAOimpl started adressCar.");

		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		List<Adress> adresses = new ArrayList<Adress>();
		Connection con = null;
		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_ADRESS_CAR);

			while (rs.next()) {

				int idAdress = rs.getInt(ADRESS_ID);
				String city = rs.getString(CITY);
				String country = rs.getString(COUNTRY);
				String street = rs.getString(STREET);
				String house = rs.getString(HOUSE);
				String status = rs.getString(STATUS);
				String adressAll = rs.getString(ADRESS_ALL);

				Adress adress = new Adress(idAdress, city, country, street, house, adressAll, status);
				adresses.add(adress);

			}
		} catch (SQLException e) {
			LOGGER.error("OderDAOimpl: SQLException - adressCar."+e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			
			LOGGER.error("OderDAOimpl: ConnectionPoolException - adressCar."+e);
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDAOimpl: SQLException - adressCar."+e);
				throw new DAOException(e);
			}
		}

		return adresses;

	}

	@Override
	public Adress adressCarId(String adress) throws DAOException {

		LOGGER.info("CarDaoImpl started lookingCar.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		ResultSet rs = null;
		Adress adressId = new Adress();
		Connection con = null;
		try {
			con = cp.takeConnection();

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ADRESS_CAR_ID);

			preparedStatement.setString(1, adress);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String city = rs.getString(CITY);
				String country = rs.getString(COUNTRY);
				String street = rs.getString(STREET);
				String house = rs.getString(HOUSE);
				String status = rs.getString(STATUS);
				String adressAll = rs.getString(ADRESS_ALL);
				adressId = new Adress(city, country, street, house, adressAll, status);

			}
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error("CarDao: SQLException - lookingCar.");
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

		return adressId;
	}

	@Override
	public void addOder(OderInfo oderInfo, String carId, String userId) throws DAOException {

		LOGGER.info("OderDaoImpl started addOder.");

		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(ODER_ADD);

			preparedStatement.setString(1, oderInfo.getDateStart());
			preparedStatement.setString(2, oderInfo.getDateEnd());
			preparedStatement.setDouble(3, oderInfo.getCostOder());
			preparedStatement.setString(4, "1");
			preparedStatement.setString(5, carId);
			preparedStatement.setString(6, userId);
			preparedStatement.setString(7, oderInfo.getAdressStartId());
			preparedStatement.setString(8, oderInfo.getAdressEndId());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			System.out.println("oderInfo.getAdressEndId() - " + oderInfo.getAdressEndId());
			System.out.println("carId - " + carId);

			PreparedStatement preparedStat = con.prepareStatement(UPDATE_CARS_ADRESS);
			preparedStat.setString(1, oderInfo.getAdressEndId());
			preparedStat.setString(2, carId);

			preparedStat.executeUpdate();
			preparedStat.close();

		} catch (SQLException e) {
			LOGGER.error("OderDaoImpl: SQLException - addOder.  - " + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDaoImpl: ConnectionPoolException - addOder");
			throw new DAOException(e);
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDaoImpl: SQLException - addOder.");
				throw new DAOException(e);
			}
		}

	}

	@Override
	public List<Oder> allOder(int numberPage) throws DAOException {
		LOGGER.info("OderDaoImpl started allOder.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		List<Oder> oders = new ArrayList<Oder>();

		Connection con = null;

		try {

			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ODER_ALL_FIRST_PAGE);
			if (numberPage > 1) {
				preparedStatement = con.prepareStatement(SELECT_ODER_ALL_NEXT_PAGE);
				preparedStatement.setInt(1, (numberPage * 15) - 15);
				preparedStatement.setInt(2, numberPage * 15);
			}

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int idOder = rs.getInt(ODER_ID);
				String dateStart = rs.getString(ODER_DATE_START);
				String dateEnd = rs.getString(ODER_DATE_END);
				Double costOder = rs.getDouble(ODER_COST);
				int statusOder = rs.getInt(ODER_STATUS);

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

				int idUser = rs.getInt(USER_ID);
				String name = rs.getString(USER_NAME);
				String surname = rs.getString(USER_SURNAME);
				String phone = rs.getString(USER_PHONE);
				String email = rs.getString(EMAIL);

				int idAdressStart = rs.getInt(ADRESS_ID_START);
				String AdressStartAll = rs.getString(ADRESS_START);

				int idAdressEnd = rs.getInt(ADRESS_ID_END);
				String AdressEndAll = rs.getString(ADRESS_END);

				Car car = new Car(idCar, brand, describeBrand, bodywork, power, transmission, numberDoors, year,
						photoPath, "1");
				User user = new User(idUser, surname, name, phone, email);
				Adress adressStart = new Adress(idAdressStart, AdressStartAll);
				Adress adressEnd = new Adress(idAdressEnd, AdressEndAll);

				Oder oder = new Oder(idOder, dateStart, dateEnd, costOder, statusOder, car, user, adressStart,
						adressEnd);

				oders.add(oder);

			}

			preparedStatement.close();
		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDao: ConnectionPoolException - allOder.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - allOder.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - allOder.");
				throw new DAOException(e);
			}
		}

		return oders;
	}

	@Override
	public Integer getNumberRows() throws DAOException {
		LOGGER.info("OderDaoImpl started getNumberRows.");

		Integer numOfRows = 0;
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		Connection con = null;
		try {
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_COUNT_ID_FROM_ODER);

			while (rs.next()) {

				numOfRows = rs.getInt(COUNT_ID);

			}
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - getNumberRows.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("OderrDao: ConnectionPoolException - getNumberRows.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - .");
				throw new DAOException(e);
			}
		}
		System.out.println("numfRows DAO - " + numOfRows);
		return numOfRows;
	}

	@Override
	public Oder findOderById(int id) throws DAOException {

		LOGGER.info("OderDaoImpl started findOderById.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		Oder oder = new Oder();

		Connection con = null;

		try {

			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ODER_BY_ID);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				oder = getOder(rs);
			}

			preparedStatement.close();
		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDao: ConnectionPoolException - findOderById.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - findOderById." + e);
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - findOderById.");
				throw new DAOException(e);
			}
		}

		return oder;
	}

	@Override
	public List<Oder> allOderByUser(int numberPage, int userId) throws DAOException {

		LOGGER.info("OderDaoImpl started allOderByUser.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		List<Oder> oders = new ArrayList<Oder>();

		Connection con = null;

		try {

			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ODER_ALL_BY_USER_FIRST_PAGE);
			preparedStatement.setInt(1, userId);
			if (numberPage > 1) {
				preparedStatement = con.prepareStatement(SELECT_ODER_ALL_BY_USER_NEXT_PAGE);
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, (numberPage * 15) - 15);
				preparedStatement.setInt(3, numberPage * 15);
			}

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Oder oder = getOder(rs);

				oders.add(oder);

			}

			preparedStatement.close();
		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDao: ConnectionPoolException - allOder.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - allOder.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - allOder.");
				throw new DAOException(e);
			}
		}

		return oders;
	}

	@Override
	public void cancelOder(int oderId, String status) throws DAOException {

		LOGGER.info("OderDaoImpl started cancelOder.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;

		Connection con = null;

		try {

			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ODER_STATUS);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, oderId);
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDao: ConnectionPoolException - cancelOder.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - cancelOder." + e);
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - cancelOder.");
				throw new DAOException(e);
			}
		}

	}

	@Override
	public List<Oder> allOderNewByUser(int userId) throws DAOException {

		LOGGER.info("OderDaoImpl started allOderNewByUser.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		List<Oder> oders = new ArrayList<Oder>();

		Connection con = null;

		try {

			String todayAsString = new SimpleDateFormat(PATTERN_DATE).format(new Date());

			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ODER_NEW_BY_USER);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, todayAsString);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Oder oder = getOder(rs);
				oders.add(oder);

			}

			preparedStatement.close();
		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDao: ConnectionPoolException - allOderNewByUser.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - allOderNewByUser." + e);
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - allOderNewByUser.");
				throw new DAOException(e);
			}
		}

		return oders;
	}

	@Override
	public List<Oder> allOderNew() throws DAOException {
		LOGGER.info("OderDaoImpl started allOderNew.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		List<Oder> oders = new ArrayList<Oder>();

		Connection con = null;

		try {

			String todayAsString = new SimpleDateFormat(PATTERN_DATE).format(new Date());

			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ODER_NEW);
			preparedStatement.setString(1, todayAsString);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Oder oder = getOder(rs);
				oders.add(oder);

			}
			

			preparedStatement.close();
		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDao: ConnectionPoolException - allOderNew.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - allOderNew." + e);
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - allOderNew.");
				throw new DAOException(e);
			}
		}

		return oders;
	}

	@Override
	public void oderСompleted(int oderId) throws DAOException {

		LOGGER.info("OderDaoImpl started oderСompleted.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		Connection con = null;

		try {

			con = cp.takeConnection();

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ID_ADRESS);
			preparedStatement.setInt(1, oderId);

			rs = preparedStatement.executeQuery();
			int idAdress = 0;
			while (rs.next()) {

				idAdress = rs.getInt(ORDER_ADRESS);

			}
			preparedStatement = con.prepareStatement(UPDATE_ODER_COMPLETED);
			preparedStatement.setInt(1, idAdress);
			preparedStatement.setInt(2, oderId);
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (ConnectionPoolException e) {
			LOGGER.error("OderDao: ConnectionPoolException - oderСompleted.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("OderDao: SQLException - oderСompleted." + e);
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("OderDao: SQLException - oderСompleted.");
				throw new DAOException(e);
			}
		}

	}

	private static Oder getOder(ResultSet rs) throws DAOException, SQLException {

		int idOder = rs.getInt(ODER_ID);
		String dateStart = rs.getString(ODER_DATE_START);
		String dateEnd = rs.getString(ODER_DATE_END);

		Double costOder = rs.getDouble(ODER_COST);
		int statusOder = rs.getInt(ODER_STATUS);

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
		String adressCar = rs.getString(CAR_ADRESS);

		int idUser = rs.getInt(USER_ID);
		String name = rs.getString(USER_NAME);
		String surname = rs.getString(USER_SURNAME);
		String phone = rs.getString(USER_PHONE);
		String email = rs.getString(EMAIL);

		int idAdressStart = rs.getInt(ADRESS_ID_START);
		String AdressStartAll = rs.getString(ADRESS_START);

		int idAdressEnd = rs.getInt(ADRESS_ID_END);
		String AdressEndAll = rs.getString(ADRESS_END);

		Car car = new Car(idCar, brand, describeBrand, bodywork, power, transmission, numberDoors, year, photoPath, "1");
		User user = new User(idUser, surname, name, phone, email);
		Adress adressStart = new Adress(idAdressStart, AdressStartAll);
		Adress adressEnd = new Adress(idAdressEnd, AdressEndAll);

		Oder oder = new Oder(idOder, dateStart, dateEnd, costOder, statusOder, car, user, adressStart, adressEnd);

		return oder;
	}

}
