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
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.Oder;
import by.htp.jwd.bean.RepairBill;
import by.htp.jwd.bean.User;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.RepairBillDAO;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class RepairBillDAOImpl implements RepairBillDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(RepairBillDAOImpl.class);

	@Override
	public boolean addRepairBill(RepairBill repairBill) throws DAOException {

		LOGGER.info("RepairBillDAOImpl started addRepairBill.");

		boolean result = false;
		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(ADD_REPAIR_BILL);

			preparedStatement.setString(1, repairBill.getDateRepair());
			preparedStatement.setString(2, repairBill.getContent());
			preparedStatement.setString(3, repairBill.getCostRepair());
			preparedStatement.setString(4, repairBill.getStatusRepairBill());
			preparedStatement.setInt(5, repairBill.getOder().getId());

			result = true;
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			LOGGER.error("RepairBillDAOImpl: SQLException - addRepairBill. " + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("RepairBillDAOImpl: ConnectionPoolException - addRepairBill.");
			throw new DAOException(e);
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("RepairBillDAOImpl: SQLException - addRepairBill.");
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public List<RepairBill> allRepairBill(int numberPage) throws DAOException {

		LOGGER.info("RepairBillDAOImpl started allRepairBill.");

		List<RepairBill> repairBills = new ArrayList<RepairBill>();
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		
		RepairBill repairBill = new RepairBill();
		Connection con = null;
		try {
			
			con = cp.takeConnection();
			st = con.createStatement();
			PreparedStatement preparedStatement = con.prepareStatement(ALL_REPAIR_BILL_FIRST);
			if (numberPage > 1) {
				preparedStatement = con.prepareStatement(ALL_REPAIR_BILL_SECOND);
				preparedStatement.setInt(1, (numberPage * 15) - 15);
				preparedStatement.setInt(2, numberPage * 15);
			}
		
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				repairBill = getRepairBill(rs);
				repairBills.add(repairBill);
			
			}
		} catch (SQLException e) {
			LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("RepairBillDAOImpl: ConnectionPoolException - allRepairBill.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill.");
				throw new DAOException(e);
			}
		}

		return repairBills;
	}

	@Override
	public Integer getNumberRows() throws DAOException {

		LOGGER.info("RepairBillDAOImpl started getNumberRows.");

		Integer numOfRows = 0;
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		Connection con = null;
		try {
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_COUNT_ID_FROM_REPAIR);

			while (rs.next()) {

				numOfRows = rs.getInt(COUNT_ID);

			}
		} catch (SQLException e) {
			LOGGER.error("RepairBillDAOImpl: SQLException - getNumberRows.");
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("RepairBillDAOImpl: ConnectionPoolException - getNumberRows.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("RepairBillDAOImpl: SQLException - .");
				throw new DAOException(e);
			}
		}
		
		return numOfRows;
	}

	@Override
	public List<RepairBill> repairBillNotPay(int numberPage) throws DAOException {
		
		LOGGER.info("RepairBillDAOImpl started allRepairBill.");

		List<RepairBill> repairBills = new ArrayList<RepairBill>();
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;		
		RepairBill repairBill = new RepairBill();
		Connection con = null;
		try {
			
			con = cp.takeConnection();
			st = con.createStatement();
			PreparedStatement preparedStatement = con.prepareStatement(REPAIR_BILL_NOT_PAY_FIRST);
			if (numberPage > 1) {
				preparedStatement = con.prepareStatement(REPAIR_BILL_NOT_PAY_SECOND);
				preparedStatement.setInt(1, (numberPage * 15) - 15);
				preparedStatement.setInt(2, numberPage * 15);
			}
		
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				repairBill = getRepairBill(rs);
				repairBills.add(repairBill);
			}
		} catch (SQLException e) {
			LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("RepairBillDAOImpl: ConnectionPoolException - allRepairBill.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill.");
				throw new DAOException(e);
			}
		}

		return repairBills;
	}

	@Override
	public List<RepairBill> allRepairBillByUser(int numberPage, int userId) throws DAOException {
		
		LOGGER.info("RepairBillDAOImpl started allRepairBillByUser.");

		List<RepairBill> repairBills = new ArrayList<RepairBill>();
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;		
		RepairBill repairBill = new RepairBill();
		Connection con = null;
		try {
			
			con = cp.takeConnection();
			st = con.createStatement();
			PreparedStatement preparedStatement = con.prepareStatement(ALL_REPAIR_BILL_BY_USER_FIRST);
			preparedStatement.setInt(1, userId);
			if (numberPage > 1) {
				preparedStatement = con.prepareStatement(ALL_REPAIR_BILL_BY_USER_SECOND);
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, (numberPage * 15) - 15);
				preparedStatement.setInt(3, numberPage * 15);
			}
		
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				repairBill = getRepairBill(rs);
				repairBills.add(repairBill);
			}
		} catch (SQLException e) {
			LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("RepairBillDAOImpl: ConnectionPoolException - allRepairBill.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill.");
				throw new DAOException(e);
			}
		}

		return repairBills;
	}
	
	@Override
	public List<RepairBill> allRepairBillNotPayByUser(int numberPage, int userId) throws DAOException {


		LOGGER.info("RepairBillDAOImpl started allRepairBillNotPayByUser.");

		List<RepairBill> repairBills = new ArrayList<RepairBill>();
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;		
		RepairBill repairBill = new RepairBill();
		Connection con = null;
		try {
			
			con = cp.takeConnection();
			st = con.createStatement();
			PreparedStatement preparedStatement = con.prepareStatement(ALL_REPAIR_BILL_NOT_PAY_BY_USER_FIRST);
			preparedStatement.setInt(1, userId);
			if (numberPage > 1) {
				preparedStatement = con.prepareStatement(ALL_REPAIR_BILL_NOT_PAY_BY_USER_SECOND);
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, (numberPage * 15) - 15);
				preparedStatement.setInt(3, numberPage * 15);
			}
		
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				repairBill = getRepairBill(rs);
				repairBills.add(repairBill);
			}
		} catch (SQLException e) {
			LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("RepairBillDAOImpl: ConnectionPoolException - allRepairBill.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("RepairBillDAOImpl: SQLException - allRepairBill.");
				throw new DAOException(e);
			}
		}

		return repairBills;
	}

	private static RepairBill getRepairBill(ResultSet rs) throws DAOException, SQLException {

		int idOder = rs.getInt(ODER_ID);
		String dateStart = rs.getString(ODER_DATE_START);
		String dateEnd = rs.getString(ODER_DATE_END);
//		String timeStart = rs.getString(ODER_TINE_START);
//		String timeEnd = rs.getString(ODER_TINE_END);
		
		Double costOder = rs.getDouble(ODER_COST);
		int statusOder = rs.getInt(ODER_STATUS);

		int idCar = rs.getInt(CAR_ID);
		String brand = rs.getString(CAR_BRAND);
		String describeBrand = rs.getString(CAR_BRAND_DESCRIBE);
		String bodywork = rs.getString(CAR_BODYWORK);
		String power = rs.getString(CAR_POWER);
		String transmission = rs.getString(CAR_TRANSMISSION);
		String numberDoors = rs.getString(NUMBER_OF_DOORS);
		String year = rs.getString(YEAR);
		String photoPath = rs.getString(PHOTO_PATH);
		Blob photo = rs.getBlob(PHOTO);

		int idUser = rs.getInt(USER_ID);
		String name = rs.getString(USER_NAME);
		String surname = rs.getString(USER_SURNAME);
		String phone = rs.getString(USER_PHONE);
		String email = rs.getString(USER_EMAIL);

		
		
		int idRepair = rs.getInt(REPAIR_ID);
		String repairDate = rs.getString(REPAIR_DATE);
		String repairContent = rs.getString(REPAIR_CONTENT);
		String repairCost = rs.getString(REPAIR_COST);
		String repairStatus = rs.getString(REPAIR_STATUS);

		Car car = new Car(idCar, brand, describeBrand, bodywork, power, transmission, numberDoors, year, photoPath, "1");
		User user = new User(idUser, surname, name, phone, email);
		

		Oder oder = new Oder(idOder, dateStart, dateEnd,  costOder, statusOder, car, user);
		
		RepairBill repairBill = new RepairBill(idRepair, repairDate, repairContent, repairCost, repairStatus, oder);
		
		return repairBill;
	}

	@Override
	public void payRepairBill(int repairBillId) throws DAOException {
       
		LOGGER.info("RepairBillDAOImpl started payRepairBill.");

		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(REPAIR_BILL_PAY);

			preparedStatement.setInt(1, repairBillId);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			LOGGER.error("RepairBillDAOImpl: SQLException - payRepairBill. " + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("RepairBillDAOImpl: ConnectionPoolException - payRepairBill.");
			throw new DAOException(e);
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("RepairBillDAOImpl: SQLException - payRepairBill.");
				throw new DAOException(e);
			}
		}

		
	}

	
}
