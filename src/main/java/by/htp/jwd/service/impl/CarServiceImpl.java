package by.htp.jwd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.dao.CarDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.dao.impl.OderDAOimpl;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;

public class CarServiceImpl implements CarService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

	@Override
	public boolean addCarService(Car car) throws ServiceException {

		LOGGER.info("CarServiceimpl started addCarService.");
		
		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		boolean result = false;

		try {
			result = carDAO.addCar(car);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<PriceList> allCar(int numberPage) throws ServiceException {

		LOGGER.info("CarServiceimpl started allCar.");
		
		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		List<PriceList> cars = null;

		try {
			cars = carDAO.allCar(numberPage);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return cars;
	}

	@Override
	public boolean addPriceToCarService(PriceList priceList) throws ServiceException {
		
		LOGGER.info("CarServiceimpl started addPriceToCarService.");

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		boolean result = false;

		try {
			result = carDAO.addPriceToCar(priceList);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Integer getNumberOfRows() throws ServiceException {
		
		LOGGER.info("CarServiceimpl started agetNumberOfRows.");

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		Integer number = 0;
		try {
			number = carDAO.getNumberOfRows();

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return number;
	}

	@Override
	public List<PriceList> lookingCar(String adressStart_s, String dateStart_s, String dateEnd_s) throws ServiceException {
		
		LOGGER.info("CarServiceimpl started lookingCar.");

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		List<PriceList> cars = null;
//		List<PriceList> carsAll = null;
//		List<PriceList> carsFree = new ArrayList<PriceList>();
//		int count = 0;

		try {
			cars = carDAO.lookingCar(adressStart_s, dateStart_s, dateEnd_s);

//			carsAll = carDAO.allCar();
//			for (int i = 0; i < carsAll.size(); i++) {
//				for (int j = 0; j < cars.size(); j++) {
//
//					if (carsAll.get(i).getCar().getId() == cars.get(j).getCar().getId()) {
//						count++;
//					}
//				}
//				if (count == 0) {
//					carsFree.add(carsAll.get(i));
//				}
//				count = 0;
//			}

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return cars;
	}

	@Override
	public PriceList infoAboutCar(String id) throws ServiceException {
		
		LOGGER.info("CarServiceimpl started infoAboutCar.");

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		PriceList car = new PriceList();

		try {
			car = carDAO.infoAboutCar(id);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return car;
	}

	@Override
	public boolean updateCar(PriceList priceList) throws ServiceException {

		LOGGER.info("CarServiceimpl started addPriceToCarService.");

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		boolean result = false;

		try {
			result = carDAO.updateCar(priceList);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean deleteCar(int idCar) throws ServiceException {

		LOGGER.info("CarServiceimpl started deleteCar.");

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		boolean result = false;

		try {
			result = carDAO.deleteCar(idCar);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

}
