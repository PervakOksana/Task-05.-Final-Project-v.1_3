package by.htp.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.dao.AdressDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class AdressDAOImpl implements AdressDAO {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AdressDAOImpl.class);

	@Override
	public void addAdress(Adress adress) throws DAOException {

		LOGGER.info("AdressDAOImpl started addAdress.");
		
		Connection con = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(ADRESS_ADD);
			preparedStatement.setString(1, adress.getCity());
			preparedStatement.setString(2, adress.getCountry());
			preparedStatement.setString(3, adress.getStreet());
			preparedStatement.setString(4, adress.getHouse());
			preparedStatement.setString(5, adress.getAdressAll());
			preparedStatement.setString(6, "1");

			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			LOGGER.error("AdressDAOImpl: SQLException - addAdress."+e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {

			LOGGER.error("AdressDAOImpl: ConnectionPoolException - addAdress.");
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				LOGGER.error("AdressDAOImpl: SQLException - addAdress.");
				throw new DAOException(e);
			}
		}
		
	}

}
