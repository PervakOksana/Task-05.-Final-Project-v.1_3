package by.htp.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.UserDAO;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class UserDAOimpl implements UserDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(UserDAOimpl.class);

	@Override
	public boolean registration(User user) throws DAOException {

		LOGGER.info("UserDAOimpl started registration.");

		boolean result = false;
		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(USER_ADD);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setString(6, user.getPhone());
			preparedStatement.setString(7, user.getEmail());

			result = true;
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
		
			LOGGER.error("UserDAO: SQLException - registration.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			
			LOGGER.error("UserDAO: ConnectionPoolException - registration.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			
				LOGGER.error("UserDAO: SQLException - registration.");
				throw new DAOException(e);
			}
		}

		return result;

	}

	@Override
	public User authorization(String login, String password) throws DAOException {

		LOGGER.info("UserDAOimpl started authorization.");

		User user = new User();
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		ResultSet rs = null;
		Connection con = null;

		try {
			con = cp.takeConnection();

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER);

			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(ID_USER);
				login = rs.getString(LOGIN);
				password = rs.getString(PASSWORD);
				String name = rs.getString(NAME);
				String surname = rs.getString(SURNAME);
				String role = rs.getString(ROLE);
				String email = rs.getString(EMAIL);
				String phone = rs.getString(PHONE);

				user = new User(id, login, password, surname, role, name, phone, email);

			}
		} catch (SQLException e) {
			LOGGER.error("UserDAO: SQLException - authorization.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("UserDAO: ConnectionPoolException - authorization.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("UserDAO: SQLException - authorization.");
				throw new DAOException(e);
			}
		}

		return user;
	}

	@Override
	public boolean registrationAddDetail(UserDetail userDetail) throws DAOException {

		LOGGER.info("UserDAOimpl started registrationAddDetail.");

		boolean result = true;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_MAX_ID_FROM_USER);
			int userId = 0;
			while (rs.next()) {
				userId = rs.getInt(MAX_ID);
			}

			PreparedStatement preparedStatement = con.prepareStatement(USER_ADD_DETAIL);

			preparedStatement.setString(1, userDetail.getPasswordNumber());
			preparedStatement.setString(2, userDetail.getDrivingLicanseNumber());
			preparedStatement.setString(3, userDetail.getDrivingLicanseDate());
			preparedStatement.setString(4, userDetail.getPasswordDate());
			preparedStatement.setInt(5, userId);

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
	public boolean registrationAddAdress(Adress adress) throws DAOException {

		LOGGER.info("UserDAOimpl started registrationAddAdress.");

		boolean result = true;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_MAX_ID_FROM_USER);
			int userId = 0;
			while (rs.next()) {
				userId = rs.getInt(MAX_ID);
			}

			PreparedStatement preparedStatement = con.prepareStatement(USER_ADD_ADRESS);

			preparedStatement.setString(1, adress.getCity());
			preparedStatement.setString(2, adress.getCountry());
			preparedStatement.setString(3, adress.getStreet());
			preparedStatement.setString(4, adress.getHouse());
			preparedStatement.setString(5, "0");

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
	public boolean registrationAll(UserDetail userDetail) throws DAOException {
		
		LOGGER.info("UserDAOimpl started registrationAll.");

		boolean result = true;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		try {
			con = cp.takeConnection();
			st = con.createStatement();

			////////////////////////////// ADRESS/////////////////////////////////////
			PreparedStatement preparedStatement = con.prepareStatement(USER_ADD_ADRESS);

			preparedStatement.setString(1, userDetail.getUser().getAdress().getCity());
			preparedStatement.setString(2, userDetail.getUser().getAdress().getCountry());
			preparedStatement.setString(3, userDetail.getUser().getAdress().getStreet());
			preparedStatement.setString(4, userDetail.getUser().getAdress().getHouse());
			preparedStatement.setString(5, "0");
			preparedStatement.setString(6, userDetail.getUser().getAdress().getCity() + userDetail.getUser().getAdress().getStreet());

			preparedStatement.executeUpdate();

			////////////////////////////// USER/////////////////////////////////////

			rs = st.executeQuery(SELECT_MAX_ID_FROM_ADRESS);
			int adressId = 0;
			while (rs.next()) {
				adressId = rs.getInt(MAX_ID);
			}
			preparedStatement = con.prepareStatement(USER_ADD);

			preparedStatement.setString(1, userDetail.getUser().getLogin());
			preparedStatement.setString(2, userDetail.getUser().getPassword());
			preparedStatement.setString(3, userDetail.getUser().getName());
			preparedStatement.setString(4, userDetail.getUser().getSurname());
			preparedStatement.setString(5, userDetail.getUser().getRole());
			preparedStatement.setString(6, userDetail.getUser().getPhone());
			preparedStatement.setString(7, userDetail.getUser().getEmail());
			preparedStatement.setInt(8, adressId);

			result = true;
			preparedStatement.executeUpdate();

			//////////////////////////// USER_DETAIL/////////////////////////////////////

			rs = st.executeQuery(SELECT_MAX_ID_FROM_USER);
			int userId = 0;
			while (rs.next()) {
				userId = rs.getInt(MAX_ID);
			}

			preparedStatement = con.prepareStatement(USER_ADD_DETAIL);

			preparedStatement.setString(1, userDetail.getPasswordNumber());
			preparedStatement.setString(2, userDetail.getDrivingLicanseNumber());
			preparedStatement.setString(3, userDetail.getDrivingLicanseDate());
			preparedStatement.setString(4, userDetail.getPasswordDate());
			preparedStatement.setInt(5, userId);

			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("DAO registration 3 -- " + e);
			LOGGER.error("UserDAO: SQLException - registration.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			System.out.println("DAO registration 4");
			LOGGER.error("UserDAO: ConnectionPoolException - registration.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DAO registration 4");
				LOGGER.error("UserDAO: SQLException - registration.");
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public User findPassword(String email) throws DAOException {

		LOGGER.info("UserDAOimpl started findPassword.");

		User user = new User();
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		ResultSet rs = null;
		Connection con = null;

		try {
			con = cp.takeConnection();

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_EMAIL);

			preparedStatement.setString(1, email);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String login = rs.getString(LOGIN);
				String password = rs.getString(PASSWORD);
				String name = rs.getString(NAME);
				String surname = rs.getString(SURNAME);
				String role = rs.getString(ROLE);
				String phone = rs.getString(PHONE);

				user = new User(login, password, surname, role, name, phone, email);

			}
		} catch (SQLException e) {
			LOGGER.error("UserDAO: SQLException - findPassword." + e);

			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("UserDAO: ConnectionPoolException - findPassword.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("UserDAO: SQLException - findPassword.");
				throw new DAOException(e);
			}
		}

		return user;
	}

	@Override
	public UserDetail findUserViaId(String id) throws DAOException {

		LOGGER.info("UserDAOimpl started findUserViaId.");

		User user = new User();
		Adress adress = new Adress();
		UserDetail userDetail = new UserDetail();

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		ResultSet rs = null;
		Connection con = null;

		try {
			con = cp.takeConnection();

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_ID);

			preparedStatement.setString(1, id);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				int idUser = rs.getInt(ID_USER);
				String login = rs.getString(LOGIN);
				String password = rs.getString(PASSWORD);
				String name = rs.getString(NAME);
				String surname = rs.getString(SURNAME);
				String role = rs.getString(ROLE);
				String phone = rs.getString(PHONE);
				String email = rs.getString(EMAIL);

				int idAdress = rs.getInt(ADRESS_ID);
				System.out.println("int idAdress - " +idAdress);
				String city = rs.getString(CITY);
				String country = rs.getString(COUNTRY);
				String street = rs.getString(STREET);
				String house = rs.getString(HOUSE);
				String adressAll = rs.getString(HOUSE);
				String status = rs.getString(ADRESS_STATUS);

				String numberPassport = rs.getString(PASSWORD_NUMBER);
				String numberDrivingLicense = rs.getString(DRIVING_LICENSE_NUMBER);
				String dateDrivingLicense = rs.getString(DRIVING_LICENSE_DATE);
				String datePassport = rs.getString(PASSWORD_DATE);
				int detailsUserId = rs.getInt(ID_USER_DETAIL);
				
				adress = new Adress(idAdress, city, country, street, house, adressAll, status);
				user = new User(idUser, login, password, surname, role, name, phone, email,  adress);
				userDetail = new UserDetail(detailsUserId, numberPassport, numberDrivingLicense, dateDrivingLicense,
						datePassport, user);
			}
		} catch (SQLException e) {
			LOGGER.error("UserDAO: SQLException - findUserViaId." + e);

			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("UserDAO: ConnectionPoolException - findUserViaId.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("UserDAO: SQLException - findUserViaId.");
				throw new DAOException(e);
			}
		}

		return userDetail;
	}

	@Override
	public boolean updateUser( UserDetail userDetail) throws DAOException {

		LOGGER.info("UserDAOimpl started updateUser.");

		boolean result = true;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		try {
			con = cp.takeConnection();
			st = con.createStatement();

			////////////////////////////// ADRESS/////////////////////////////////////
			PreparedStatement preparedStatement = con.prepareStatement(USER_UPDATE_ADRESS);

			preparedStatement.setString(1, userDetail.getUser().getAdress().getCity());
			preparedStatement.setString(2, userDetail.getUser().getAdress().getCountry());
			preparedStatement.setString(3, userDetail.getUser().getAdress().getStreet());
			preparedStatement.setString(4, userDetail.getUser().getAdress().getHouse());
			preparedStatement.setString(5, "0");
			preparedStatement.setInt(6, userDetail.getUser().getAdress().getId());

			preparedStatement.executeUpdate();

			////////////////////////////// USER/////////////////////////////////////

			preparedStatement = con.prepareStatement(USER_UPDATE);

			preparedStatement.setString(1, userDetail.getUser().getLogin());
			preparedStatement.setString(2, userDetail.getUser().getPassword());
			preparedStatement.setString(3, userDetail.getUser().getName());
			preparedStatement.setString(4, userDetail.getUser().getSurname());
			preparedStatement.setString(5, userDetail.getUser().getRole());
			preparedStatement.setString(6, userDetail.getUser().getPhone());
			preparedStatement.setString(7, userDetail.getUser().getEmail());
			preparedStatement.setInt(8, userDetail.getUser().getAdress().getId());
			preparedStatement.setInt(9, userDetail.getUser().getId());

			result = true;
			preparedStatement.executeUpdate();

			//////////////////////////// USER_DETAIL/////////////////////////////////////

			preparedStatement = con.prepareStatement(USER_UPDATE_DETAIL);

			preparedStatement.setString(1, userDetail.getPasswordNumber());
			preparedStatement.setString(2, userDetail.getDrivingLicanseNumber());
			preparedStatement.setString(3, userDetail.getDrivingLicanseDate());
			preparedStatement.setString(4, userDetail.getPasswordDate());
			preparedStatement.setInt(5, userDetail.getUser().getId());
			preparedStatement.setInt(6, userDetail.getId());

			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {

			LOGGER.error("UserDAO: SQLException - updateUser." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {

			LOGGER.error("UserDAO: ConnectionPoolException - updateUser.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {

				LOGGER.error("UserDAO: SQLException - updateUser.");
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public User findLogin(String login) throws DAOException {
		
		LOGGER.info("UserDAOimpl started findLogin.");

		User user = new User();
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		ResultSet rs = null;
		Connection con = null;

		try {
			con = cp.takeConnection();

			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_LOGIN);

			preparedStatement.setString(1, login);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String loginFind = rs.getString(LOGIN);
				String password = rs.getString(PASSWORD);
				String name = rs.getString(NAME);
				String surname = rs.getString(SURNAME);
				String role = rs.getString(ROLE);
				String phone = rs.getString(PHONE);
				String email = rs.getString(EMAIL);

				user = new User(loginFind, password, surname, role, name, phone, email);

			}
		} catch (SQLException e) {
			LOGGER.error("UserDAO: SQLException - findLogin." + e);

			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("UserDAO: ConnectionPoolException - findLogin.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("UserDAO: SQLException - findLogin.");
				throw new DAOException(e);
			}
		}

		return user;
	}

}
