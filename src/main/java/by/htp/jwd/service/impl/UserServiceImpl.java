package by.htp.jwd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.User;
import by.htp.jwd.bean.UserDetail;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.dao.UserDAO;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.UserService;

public class UserServiceImpl implements UserService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User authorization(String login, String password) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started authorization.");

		if (login == null || "".equals(login)) {
			throw new ServiceException("Wrong login or password");
		}
		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		User user = null;
		try {
			user = userDAO.authorization(login, password);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public boolean registration(User user) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started registration.");

		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		boolean result = false;
		try {

			result = userDAO.registration(user);
			result = true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean registrationAddDetail(UserDetail userDetail) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started registrationAddDetail.");

		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		boolean result = false;
		try {
			result = userDAO.registrationAddDetail(userDetail);
			result = true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean registrationAddAdress(Adress adress) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started registrationAddAdress.");

		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		boolean result = false;
		try {
			result = userDAO.registrationAddAdress(adress);
			result = true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean registrationAll(UserDetail userDetail) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started registrationAll.");

		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		boolean result = false;
		try {

			result = userDAO.registrationAll(userDetail);
			result = true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public User findPassword(String email) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started findPassword.");

		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		User user = null;
		try {
			user = userDAO.findPassword(email);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public UserDetail findUserViaId(String id) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started findUserViaId.");

		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		UserDetail user = null;
		try {
			user = userDAO.findUserViaId(id);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public boolean updateUser(UserDetail userDetail) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started updateUser.");
		
		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		boolean result = false;
		try {

			result = userDAO.updateUser(userDetail);
			result = true;
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public User findLogin(String login) throws ServiceException {
		
		LOGGER.info("UserServiceimpl started findLogin.");

		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();

		User user = null;
		try {
			user = userDAO.findLogin(login);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

}
