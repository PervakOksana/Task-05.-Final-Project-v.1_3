package by.htp.jwd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Adress;
import by.htp.jwd.bean.ApplicationInfo;
import by.htp.jwd.dao.AdressDAO;
import by.htp.jwd.dao.ApplicationInfoDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.service.AdressService;
import by.htp.jwd.service.ApplicationInfoService;
import by.htp.jwd.service.ServiceException;

public class AdressServiceImpl implements AdressService{

	private final Logger LOGGER = LoggerFactory.getLogger(AdressServiceImpl.class);
	
	@Override
	public void addAdress(Adress adress) throws ServiceException {

		LOGGER.info("AdressServiceImpl started addAdress.");

		DAOProvider proviger = DAOProvider.getInstance();
		AdressDAO adressDAO = proviger.getAdressDao();

		try {
			adressDAO.addAdress(adress);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	

}
