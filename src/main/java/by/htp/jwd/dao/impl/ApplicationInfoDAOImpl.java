package by.htp.jwd.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.dao.ApplicationInfoDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class ApplicationInfoDAOImpl implements ApplicationInfoDAO{

	private final Logger LOGGER = LoggerFactory.getLogger(ApplicationInfoDAOImpl.class);
	
	
	@Override
	public boolean updateApplicationInfo(ApplicationInfo applicationInfo) throws DAOException {

		LOGGER.info("ApplicationInfoDAOImpl started updateApplicationInfo.");

		boolean result = false;
		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
	
		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(INFO_UPDATE);
		
			preparedStatement.setString(1, applicationInfo.getPayTerm());
			preparedStatement.setString(2, applicationInfo.getContact());
			preparedStatement.setString(3, applicationInfo.getAdressEmail());
			preparedStatement.setString(4, applicationInfo.getLetterReminder());
			preparedStatement.setString(5, applicationInfo.getLetterBill());
			preparedStatement.setString(6, applicationInfo.getPasswordEmail());
			
			preparedStatement.executeUpdate();
			result = true;
			preparedStatement.close();

		} catch (SQLException e) {
			LOGGER.error("ApplicationInfoDAOImpl: SQLException - updateApplicationInfo." + e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			LOGGER.error("ApplicationInfoDAOImpl: ConnectionPoolException - updateApplicationInfo.");
			throw new DAOException(e);
		} 

		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("ApplicationInfoDAOImpl: SQLException - updateApplicationInfo.");
				throw new DAOException(e);
			}
		}

		return result;
	}


	@Override
	public ApplicationInfo getApplicationInfo() throws DAOException {

		LOGGER.info("ApplicationInfo started ApplicationInfo.");

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		ResultSet rs = null;
		ApplicationInfo applicationInfo = new ApplicationInfo();
		Connection con = null;

		try {
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(INFO_GET);
			
			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(INFO_ID);
				String payTerm = rs.getString(INFO_PAY_TERM);
				String contact = rs.getString(INFO_CONTACT);
				String adressEmail = rs.getString(INFO_ADRESS_EMAIL);
				String letterReminder = rs.getString(INFO_REMINDER);
				String letterBill = rs.getString(INFO_BILL);
				String passwordEmail = rs.getString(INFO_PASSWORD_EMAIL);
				applicationInfo = new ApplicationInfo(id, payTerm, contact, adressEmail, passwordEmail,
						letterReminder, letterBill);
			}
			preparedStatement.close();
		} catch (ConnectionPoolException e) {
			LOGGER.error("ApplicationInfo: ConnectionPoolException - ApplicationInfo.");
			throw new DAOException(e);
		} catch (SQLException e) {
			LOGGER.error("ApplicationInfo: SQLException - ApplicationInfo.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("ApplicationInfo: SQLException - ApplicationInfo.");
				throw new DAOException(e);
			}
		}

		return applicationInfo;

	}

	

}
