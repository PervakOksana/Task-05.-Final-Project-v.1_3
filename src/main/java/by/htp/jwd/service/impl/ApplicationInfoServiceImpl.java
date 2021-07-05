package by.htp.jwd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.dao.ApplicationInfoDAO;
import by.htp.jwd.dao.CarDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.ServiceException;

public class ApplicationInfoServiceImpl implements ApplicationInfoService {

	private final Logger LOGGER = LoggerFactory.getLogger(ApplicationInfoServiceImpl.class);

	@Override
	public boolean updateApplicationInfo(ApplicationInfo applicationInfo) throws ServiceException {

		LOGGER.info("ApplicationInfoServiceImpl started updateApplicationInfo.");

		DAOProvider proviger = DAOProvider.getInstance();
		ApplicationInfoDAO applicationInfoDAO = proviger.getApplicationInfodao();

		try {
			applicationInfoDAO.updateApplicationInfo(applicationInfo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return false;
	}

	@Override
	public ApplicationInfo getApplicationInfo() throws ServiceException {
		
		LOGGER.info("ApplicationInfoServiceImpl started updateApplicationInfo.");

		ApplicationInfo applicationInfo = new ApplicationInfo();
		
		DAOProvider proviger = DAOProvider.getInstance();
		ApplicationInfoDAO applicationInfoDAO = proviger.getApplicationInfodao();

		try {
			applicationInfo = applicationInfoDAO.getApplicationInfo();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return applicationInfo;
	}

}
